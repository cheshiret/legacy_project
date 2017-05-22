package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.landowner.voidpri;

import java.util.HashMap;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * Purchase a privilege which associated a bonus privilege.
 * Then void the bonus privilege.
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
 * 				d_hf_add_cust_profile:id=2380
 * @SPEC: Void Privilege Transaction - Landowner [TC:070284]
 * Void Privilege Transaction - Sales Location Status [TC:034827]
 * @Task#:Auto-1789
 * 
 * @author nding
 * @Date  Oct 22, 2013
 */
public class VoidBonusPri extends LicenseManagerTestCase{
	String bonusPrivilege;
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);pay = new Payment("Cash");
		String orderNum = lm.processOrderCart(pay).split(" ")[0];
		
		// void bonus privilege only
		lm.voidLandownerPriInstance(orderNum, bonusPrivilege, "Reverse");
		lm.processOrderCart(pay);
		
		// verify, both privilege status should be Reversed.
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();

		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();
		String basePri = allPrivNum.split(" ")[0];
		String bonusPri = allPrivNum.split(" ")[1];
		lm.verifyPrivilegeItemStatusFromOrderPg(basePri, REVERSED_STATUS);
		lm.verifyPrivilegeItemStatusFromOrderPg(bonusPri, REVERSED_STATUS);

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MO";

		login.contract = "MO Contract";
		login.location = "MO Mod 1/ACADEMY SPORTS & OUTDOORS(Store Loc)"; 
		
		privilege.code = "LOP";
		privilege.name = "LandOwnerPri";
		privilege.purchasingName = privilege.code+"-"+privilege.name;
		privilege.licenseYear = String.valueOf(lm.getFiscalYear(schema));
		privilege.qty = "1";
		HashMap<String, String> landownerDeclar = new HashMap<>();
		landownerDeclar.put("Scott", "9");
		landownerDeclar.put("Pike", "8");
		privilege.landownerDeclaration = landownerDeclar;
		
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.fName = "QA-Angela";
		cust.lName = "TEST-Wang";
		cust.dateOfBirth = "Mar 28 2008";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "333444666";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

		bonusPrivilege = "BonusPri1";// code is BON.
	}
}