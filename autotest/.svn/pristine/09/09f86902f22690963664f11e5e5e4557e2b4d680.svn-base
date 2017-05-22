package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.purchase;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeInventoryListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderItemsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:This test case was designed to verify process of replace privilege inventory;
 * Purchase privilege inventory, one's fulfillment method is select immediately, another one's  fulfillment method is using inventory on hand;
 * Replace privilege inventory for above two.
 * @Preconditions:
 * @LinkSetUp:
 * d_hf_add_privilege_prd:id=2910,2930,2940
 * d_hf_add_pricing:id=4342,4362,4372,4382,4392
 * d_hf_assi_pri_to_store:id=2130,2150,2160
 * d_hf_add_prvi_license_year:id=3030,3050,3060
 * d_hf_add_qty_control:id=2110,2130,2140
 * d_hf_add_cust_profile:id=3120 
 * d_assign_feature:id=5932
 * @SPEC: 
 * Purchase Process Privilege Sale - SK Seals and Ledgers [TC:100123] 
 * @Task#: Auto-2152
 * 
 * @author Jane
 * @Date Apr 28, 2014
 */
public class ReplacePrivilegeInventory extends LicenseManagerTestCase {

	private PrivilegeInfo priv01, priv02;
	
	@Override
	public void execute() {
		String ordNum=null, replaceOrdNums=null, replaceOrdNum01=null, replaceOrdNum02=null;
		try {
			lm.loginLicenseManager(login);
			lm.purchaseInventoryPrivilegeFromHomePage();
			lm.addPrivilegeItem(privilege);
			priv02.inventoryNum = privilege.inventoryNum;
			lm.goToCart();
			lm.processOrderCart(pay);
			
			lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
			lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
			priv01.inventoryNum = lm.addPrivilegeItem(priv01);
			lm.addPrivilegeItem(priv02);
			lm.goToCart();
			ordNum = lm.processOrderCart(pay);
			String licNums = lm.getPrivilegeNumByOrdNum(schema, ordNum);
			String priv01Num = licNums.split(",")[0];
			String priv02Num = licNums.split(",")[1];
			
			lm.gotoPrivilegeOrderDetailPageFromTopMenu(ordNum);
			String replacedInvNum01 = lm.replacePrivInventoryFromPriOrderDetailsPgToOrderCard(priv01Num, priv01.inventoryNum, StringUtil.EMPTY, privilege.operateReason, privilege.operateNote);
			
			lm.gotoPrivilegeOrderDetailPageFromTopMenu(ordNum);
			String replacedInvNum02 = lm.replacePrivInventoryFromPriOrderDetailsPgToOrderCard(priv02Num, priv02.inventoryNum, StringUtil.EMPTY, privilege.operateReason, privilege.operateNote);
			
			lm.verifyTransactionNameInOrderCart(OrmsConstants.TRANNAME_REPLACE_PRIVILEGE_INVENTORY);
			replaceOrdNums = lm.processOrderCart(pay);
			//all order numbers will be splited by space
			replaceOrdNum01 = replaceOrdNums.split(" ")[0];
			replaceOrdNum02 = replaceOrdNums.split(" ")[1];
			
			lm.gotoPrivilegeOrderDetailPageFromTopMenu(replaceOrdNum01);
			gotoLicenseInventoryListFromOrdDetailsPg(priv01Num);
			verifyInventoryNumStatus(priv01.inventoryNum, "Replaced");
			verifyInventoryNumStatus(replacedInvNum01, "Used");
			
			lm.gotoPrivilegeOrderDetailPageFromTopMenu(replaceOrdNum02);
			gotoLicenseInventoryListFromOrdDetailsPg(priv02Num);
			verifyInventoryNumStatus(priv02.inventoryNum, "Replaced");
			verifyInventoryNumStatus(replacedInvNum02, "Used");
			lm.logOutLicenseManager();
		} finally {
			//Clear privilege order
			lm.loginLicenseManager(login);
			if(StringUtil.notEmpty(replaceOrdNums)) {
				replaceOrdNum01 = replaceOrdNums.split(" ")[0];
				replaceOrdNum02 = replaceOrdNums.split(" ")[1];
				lm.reversePrivilegeOrderToCleanUp(replaceOrdNum01, "14 - Other", "Automation Test", pay);
				lm.reversePrivilegeOrderToCleanUp(replaceOrdNum02, "14 - Other", "Automation Test", pay);
			} else if(StringUtil.notEmpty(ordNum))
				lm.reversePrivilegeOrderToCleanUp(replaceOrdNum01, "14 - Other", "Automation Test", pay);
			lm.logOutLicenseManager();
		}
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SK Contract";
		login.location = "SK - Compliance Field Offices - Auto/Ministry of Environment - Big River(Store Loc)";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SK";
		
		//privilege for generic public customer
		privilege.code = "IP0";
		privilege.name = "InventoryPrd00";
		privilege.purchasingName = privilege.code + "-" + privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		privilege.operateReason = "104 - Other";
		privilege.operateNote = "Automation Test";
		
		//privilege for qty type as 'Fixed' and fulfillment method as 'Select Immediately'
		priv01 = new PrivilegeInfo();
		priv01.code = "IF1";
		priv01.name = "InvFixed01";
		priv01.purchasingName = "IF1-InvFixed01";
		priv01.licenseYear = privilege.licenseYear;
		priv01.qty = "1";
		priv01.fulfillmentMethod = "Select Immediately";
		
		//privilege for qty type as 'Fixed' and fulfillment method as 'Use Customer's On-hand Inventory'
		priv02 = new PrivilegeInfo();
		priv02.code = "IR1";
		priv02.name = "InvRange01";
		priv02.purchasingName = "IR1-InvRange01";
		priv02.licenseYear = privilege.licenseYear;
		priv02.qty = "1";
		priv02.fulfillmentMethod = "Use Customer's On-hand Inventory";
		
		cust.customerClass = INDIVIDUAL_CUSTOMER_CLASS;
		cust.dateOfBirth = "19810602";
		cust.identifier.identifierType = "Passport #";
		cust.identifier.identifierNum = "PASS19810602";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Saskatchewan Resident";
	}
	
	private void gotoLicenseInventoryListFromOrdDetailsPg(String licNum) {
		LicMgrPrivilegeOrderItemsPage privOrderItemsPg = LicMgrPrivilegeOrderItemsPage.getInstance();
		LicMgrPrivilegeInventoryListPage privInventoryListPg = LicMgrPrivilegeInventoryListPage.getInstance();
		
		logger.info("Go to license inventory list page from order details page by clicking license number:"+licNum);
		privOrderItemsPg.clickLicenseNum(licNum);
		privInventoryListPg.waitLoading();
	}
	
	private void verifyInventoryNumStatus(String number, String status) {
		LicMgrPrivilegeInventoryListPage privInventoryListPg = LicMgrPrivilegeInventoryListPage.getInstance();
		
		logger.info("Verify inventory num:"+number+" status as "+status);
		String statusUI = privInventoryListPg.getStatusByInvNum(number);
		if(!statusUI.equalsIgnoreCase(status))
			throw new ErrorOnPageException("Failed to verify inventory num "+number+" status.", status, statusUI);
		logger.info("---Verify inventory num "+number+" status as "+status+" successfully.");
	}

}
