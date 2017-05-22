package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.landowner;

import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderItems;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * Purchase a privilege which associated a bonus privilege.
 * The bonus doesn't have a print document.
 * @Preconditions:
 * 1.Privilege: LP3-LandownerWithBonusNoDoc
 * (This privilege is landowner associated with a bonus privilege.
 * And this privilege must have print documents.)
 * 2.Bonus privilege:BO1-BonusPriNoDoc
 * (This privilege doesn't have print documents.)
 * @LinkSetUp:  d_hf_add_privilege_prd:id=2440,2450
 * 				d_hf_add_print_doc:id=350
 * 			    d_hf_add_qty_control:id=1660,1630
 * 			    d_hf_assi_pri_to_store:id=1690,1650
 * 			    d_hf_add_pricing:id=3342,3302
 * 			    d_hf_add_pri_landowners:id=20,60
 * 				d_hf_add_cust_profile:id=2360
 * @SPEC: Purchase Privilege - Landowner Privilege Validation [TC:067604]
 * @Task#:Auto-1862
 * 
 * @author nding
 * @Date  Oct 17, 2013
 */
public class PurchaseLandownerWithBonusNoDoc extends LicenseManagerTestCase{
	String bonusPrivilege;
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);pay = new Payment("Cash");
		String orderNum = lm.processOrderCart(pay).split(" ")[0];

		lm.gotoPrivilegeOrderDetailPage(orderNum);
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
		
		// verify: there should be 1 order items for landowner privilege.
		List<OrderItems> orderItemInfoList = privOrderDetailsPage.getAllOrderItemInfo();
		if(null == orderItemInfoList || orderItemInfoList.size()!=1){
			throw new ErrorOnPageException("There should be 1 order items!!!");
		} else {
			if(!orderItemInfoList.get(0).product.contains(privilege.purchasingName)){
				throw new ErrorOnPageException("There should contain 1 order item, one is for privilege: "+privilege.purchasingName);
			}
		}
		
		lm.gotoHomePage();
		lm.reversePrivilegeOrderToCleanUp(orderNum, privilege.operateReason, privilege.operateNote, pay);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MO";
		
		login.contract = "MO Contract";
		login.location = "MO Mod 1/ACADEMY SPORTS & OUTDOORS(Store Loc)";

		privilege.code = "LP3";
		privilege.name = "LandownerWithBonusNoDoc";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = String.valueOf(lm.getFiscalYear(schema));
		privilege.qty = "1";
		HashMap<String, String> landownerDeclar = new HashMap<>();
		landownerDeclar.put("Scott", "9");
		landownerDeclar.put("Pike", "8");
		privilege.landownerDeclaration = landownerDeclar;
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation-PurchaseLandownerWithBonusNoDoc";
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-TianTian";
		cust.lName = "TEST-Zhang";
		cust.dateOfBirth = "May 09 2001";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "333444555";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		bonusPrivilege = "BO1-BonusPriNoDoc";
	}
}