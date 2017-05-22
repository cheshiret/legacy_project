package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.landowner;

import java.util.HashMap;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderItems;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * Purchase a privilege which isn't associated a bonus privilege.
 * @Preconditions:
 * 1.Privilege: LNB-LandOwnerWithoutBonus
 * (This privilege must have print documents.)
 * @LinkSetUp:  d_hf_add_privilege_prd:id=2430
 * 				d_hf_add_print_doc:id=360
 * 			    d_hf_add_qty_control:id=1680
 * 			    d_hf_assi_pri_to_store:id=1670
 * 			    d_hf_add_pricing:id=3322
 * 			    d_hf_add_pri_landowners:id=40
 * 				d_hf_add_cust_profile:id=2080
 * @SPEC: Purchase Privilege - Landowner Privilege Validation [TC:067604]
 * @Task#:Auto-1862
 * 
 * @author nding
 * @Date  Oct 17, 2013
 */
public class PurchaseLandownerOnly extends LicenseManagerTestCase{
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);pay = new Payment("Cash");
		String orderNum = lm.processOrderCart(pay).split(" ")[0];

		lm.gotoPrivilegeOrderDetailPage(orderNum);
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
		
		// verify: there should be 1 order items.
		List<OrderItems> orderItemInfoList = privOrderDetailsPage.getAllOrderItemInfo();
		if(null == orderItemInfoList || orderItemInfoList.size()!=1){
			throw new ErrorOnPageException("There should be 1 order items!!!");
		} else {
			if(!orderItemInfoList.get(0).product.contains(privilege.purchasingName)){
				throw new ErrorOnPageException("There should contain 1 order item, for privilege: "+privilege.purchasingName);
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

		privilege.code = "LNB";
		privilege.name = "LandOwnerWithoutBonus";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		privilege.qty = "1";
		HashMap<String, String> landownerDeclar = new HashMap<>();
		landownerDeclar.put("Scott", "9");
		privilege.landownerDeclaration = landownerDeclar;
		privilege.operateReason = "14 - Other";
		privilege.operateNote = "Automation-PurchaseLandownerOnly";	
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Kimi";
		cust.lName = "TEST-Lin";
		cust.dateOfBirth = "Apr 23 2005";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "333444888";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}
}