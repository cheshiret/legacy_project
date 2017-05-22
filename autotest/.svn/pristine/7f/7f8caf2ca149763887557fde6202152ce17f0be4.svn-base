package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege.landowner.voidpri;

import java.util.HashMap;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeItemDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * Purchase a privilege which associated a bonus privilege.
 * Then inactive base privilege. Verify status: base-Inactive, bonus-Active.
 * Void bonus privilege. Verify status: base-Inactive, bonus-Reserved.
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
 * 				d_hf_add_cust_profile:id=2360
 * @SPEC: Void Privilege Transaction - Landowner [TC:070284]
 * Void Privilege Transaction - Sales Location Status [TC:034827]
 * @Task#:Auto-1789
 * 
 * @author nding
 * @Date  Oct 22, 2013
 */
public class InactivateBaseVoidBonus extends LicenseManagerTestCase{
	private String bonusPrivilege, orderNum;
	
	public void execute() {
		lm.loginLicenseManager(login);
		lm.makePrivilegeOrderToCartFromPrivilegeQuickSearch(cust, privilege);pay = new Payment("Cash");
		orderNum = lm.processOrderCart(pay).split(" ")[0];

		// invalidate base privilege
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		LicMgrPrivilegeOrderDetailsPage privOrderDetailsPage = LicMgrPrivilegeOrderDetailsPage.getInstance();
		
		String allPrivNum = privOrderDetailsPage.getAllPrivilegesNum();
		String basePri = allPrivNum.split(" ")[0];
		String bonusPri = allPrivNum.split(" ")[1];

		lm.gotoPrivilegeDetailFromOrderPg(basePri);
		lm.invalidatePrivilegeItem(privilege.operateReason, privilege.operateReason);

		// 1. verify status: base - Inactive, bonus - Active
		this.verifyPrivilegeItemStatus(basePri, INVALID_STATUS);
		this.verifyPrivilegeItemStatus(bonusPri, ACTIVE_STATUS);
		
		// void bonus privilege only
		lm.gotoHomePage();
		lm.voidLandownerPriInstance(orderNum, bonusPrivilege, VOID_STATUS);
		lm.processOrderCart(pay);
		
		// 2. verify status: base - Inactive, bonus - Void
		this.verifyPrivilegeItemStatus(basePri, INVALID_STATUS);
		this.verifyPrivilegeItemStatus(bonusPri, VOIDED_STATUS);

		lm.logOutLicenseManager();
	}

	private void verifyPrivilegeItemStatus(String privilegeNumber, String expectStatus){
		LicMgrPrivilegeItemDetailPage itemDetailPg = LicMgrPrivilegeItemDetailPage.getInstance();
		
		logger.info("Verify status of privilege "+privilegeNumber);
		lm.gotoHomePage();
		lm.gotoPrivilegeOrderDetailPage(orderNum);
		lm.gotoPrivilegeDetailFromOrderPg(privilegeNumber);
		
		itemDetailPg.verifyPrivilegeStatus(expectStatus);
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
		cust.fName = "QA-Paul";
		cust.lName = "TEST-Walker";
		cust.dateOfBirth = "Mar 28 1982";
		cust.identifier.identifierType = "Passport";
		cust.identifier.identifierNum = "111444666";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

		bonusPrivilege = "BonusPri1";// code is BON.
	}
}