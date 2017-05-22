package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.pricing;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description: (P) Verify purchase type "Replace Privilege Inventory" in order card
 * @LinkSetUp:
 * d_hf_add_cust_profile:id=2710
 * d_hf_add_privilege_prd:id=2590 --Privileges,ConvPack Type
 * d_hf_add_pricing:id=3692,3702 --Privilege Inventory Replacement,Original
 * d_hf_assi_pri_to_store:id=1810 
 * d_hf_add_prvi_license_year:id=2700
 * d_hf_add_qty_control:id=1790 
 * @SPEC:
 * Add Product Pricing - Transaction fee of replace privilege inventory [TC:099212]
 * @Task#:AUTO-2050
 * 
 * @author SWang
 * @Date  Jan 25, 2014
 */
public class VerifyPurchaseTypeInOrderCard extends LicenseManagerTestCase{
	private String orderNum, replaceReason, replaceNotes, replacedLicenceNum, transactionType;

	public void execute() {
		//Prepare privilege order
		try{
			lm.loginLicenseManager(login);
			privilege.inventoryNum = lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege).get(0);
			orderNum = lm.processOrderCart(pay).split(" ")[0];
			replacedLicenceNum = "0"+String.valueOf(Integer.valueOf(privilege.inventoryNum)+1);

			//Verify transaction type "Replace Privilege Inventory" in order card
			lm.gotoPrivilegeOrderDetailPageFromTopMenu(orderNum);
			lm.replacePrivInventoryFromPriOrderDetailsPgToOrderCard(privilege.inventoryNum, replacedLicenceNum, replaceReason, replaceNotes);
			verifyReplacePrivilegeInventoryInOrderCard();
		}finally{
			//Clear privilege order
			lm.gotoHomePage();
			lm.reversePrivilegeOrderToCleanUp(orderNum, privilege.operateReason, privilege.operateNote, pay);
			lm.logOutLicenseManager();
		}
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("SK", env);
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";

		//customer parameters
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Advanced12";
		cust.lName = "TEST-Advaced12";
		cust.dateOfBirth = "19890113";
		cust.residencyStatus = "Saskatchewan Resident - RCMP #";
		cust.identifier.identifierType = "RCMP #";
		cust.identifier.identifierNum = "PricePTypes1";
		cust.identifier.state = "Saskatchewan";

		//privilege parameters
		privilege.code = "SU4";
		privilege.name = "PrivilegeForSetup4";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = "2014"; //lm.getFiscalYear(schema);
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation Test";
		//		privilege.inventoryNum = "084";

		replaceReason = "104 - Other";
		replaceNotes = "Automation test";

		//		replacedLicenceNum = "085";
		transactionType = "Replace Privilege Inventory";
	}

	private void verifyReplacePrivilegeInventoryInOrderCard(){
		OrmsOrderCartPage cartPg = OrmsOrderCartPage.getInstance();
		String transTypeFromUI = cartPg.getTransactionType();
		if(!transTypeFromUI.equals(transactionType)){
			throw new ErrorOnPageException("Transaction type is wrong!",transactionType,transTypeFromUI);
		}
		logger.info("Successfully verify transaction type as "+transactionType);
		lm.cancelCart();
	}
}
