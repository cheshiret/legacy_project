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
 * The base and bonus privilege have a print document.
 * @Preconditions:
 * 1.Privilege: LOP-LandOwnerPri
 * (This privilege is landowner associated with a bonus privilege.
 * And this privilege must have print documents.)
 * 2.Bonus privilege:BON-BonusPri1
 * (This privilege must have print documents.)
 * @LinkSetUp:  d_hf_add_privilege_prd:id=2400,2410
 * 				d_hf_add_print_doc:id=370,380
 * 			    d_hf_add_qty_control:id=1670,1640
 * 			    d_hf_assi_pri_to_store:id=1700,1660
 * 			    d_hf_add_pricing:id=3352,3312
 * 			    d_hf_add_pri_landowners:id=10,50
 * 				d_hf_add_cust_profile:id=2350
 * @SPEC: Purchase Privilege - Landowner Privilege Validation [TC:067604]
 * @Task#:Auto-1862
 * 
 * @author nding
 * @Date  Oct 17, 2013
 */
public class PurchaseLandownerWithBonus extends LicenseManagerTestCase{
	private String bonusPrivilege;
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);pay = new Payment("Cash");
		String orderNum = lm.processOrderCart(pay).split(" ")[0];

		lm.gotoPrivilegeOrderDetailPage(orderNum);
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
		
		// verify: there should be two order items.
		List<OrderItems> orderItemInfoList = privOrderDetailsPage.getAllOrderItemInfo();
		if(null == orderItemInfoList || orderItemInfoList.size()!=2){
			throw new ErrorOnPageException("There should be 2 order items!!!");
		} else {
			if(!orderItemInfoList.get(0).product.contains(privilege.purchasingName) 
					|| !orderItemInfoList.get(1).product.contains(bonusPrivilege)){
				throw new ErrorOnPageException("There should contain 2 order item, one is for privilege: "+privilege.purchasingName+", and the other one is for bonus privilege: "+bonusPrivilege);
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

		privilege.code = "LOP";
		privilege.name = "LandOwnerPri";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		privilege.qty = "1";
		HashMap<String, String> landownerDeclar = new HashMap<>();
		landownerDeclar.put("Scott", "9");
		privilege.landownerDeclaration = landownerDeclar;
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation-PurchaseLandownerWithBonus";	
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Cindy";
		cust.lName = "TEST-Tian";
		cust.dateOfBirth = "May 23 2003";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "333444333";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
		
		bonusPrivilege = "BON-BonusPri1";
	}
}