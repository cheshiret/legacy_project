package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.leftoverpriv;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicenseCategoriesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKUnlockedPrivTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: (p) First Leftover privileges are shown and after unlocked privileges are shown in category list page
 * 
 * @Preconditions: 
 * http://wiki.reserveamerica.com/display/dev/Import+DB+Scripts
 * FileImportConfiguration.sql
 * ConfigureHuntComponents.sql
 * 
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1010
 * d_hf_add_privilege_prd:id=2310
 * d_hf_add_pricing:id=3202
 * d_hf_assi_pri_to_store:id=1560
 * d_hf_add_prvi_license_year:id=2350
 * d_hf_add_qty_control:id=1540
 * d_hf_add_hunt:id=300
 * d_hf_assign_priv_to_hunt:id=300
 * d_hf_add_hunt_license_year:id=350
 * d_hf_add_hunt_quota:id=40
 * d_assign_feature:id=4502,4512,4522,4532,4542
 * 
 * @SPEC: TC099818:OTC/Leftover privilege on Purchase privilege page  
 * @Task#: Auto-1869
 * 
 * @author SWang
 * @Date  Sep 10, 2013
 */
public class UnlockPrivBehindLeftover extends HFSKUnlockedPrivTestCase{
	private HFLicenseCategoriesListPage categoriesListPg = HFLicenseCategoriesListPage.getInstance();
	private String category, subCategory, splitString1, splitString2;

	public void execute() {
		//Precondition: Import 1 unlocked privilege record
		this.prepareUnlockedPriv(fileName, privilege, cus.custNum, hunt);

		//Make privilege to Licence Details page
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.gotoLicenseCategoriesListPg(cus, true);
		verifyUnlockPrivBehindAllLeftover();

		hf.signOut();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Customer Info
		cus.fName = "LeftoverPri05_FN";
		cus.lName = "LeftoverPri05_LN";
		cus.custNum = hf.getCustomerNumByCustName(cus.lName, cus.fName, schema);
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4160";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;

		// Hunt info
		hunt = new HuntInfo();
		hunt.setHuntCode("UH1");
		hunt.setDescription("Unlock hunt 01");

		// Privilege Info
		privilege.licenseYear = hf.getFiscalYear(schema);
		privilege.code = "SUB";
		privilege.name = "AFSK UnlockPriv";

		fileName = "Unlock_Priv_Behind_Leftover";
		category = "HUNTING";
		subCategory = "Game Bird";
		splitString1 = "LeftoverPriv";
		splitString2 = "UnlockPriv";
	}

	private void verifyUnlockPrivBehindAllLeftover(){
		String priv = categoriesListPg.getPrivBasedOnSubCategory(category, subCategory).toString();
		boolean result = MiscFunctions.compareResult("Before "+splitString2+" has "+splitString1, priv.split(privilege.name)[0].contains(splitString1), true);
		result &= MiscFunctions.compareResult("Before "+splitString2+" doesn't have "+splitString1, priv.split(privilege.name)[0].contains(splitString2), false);
		result &= MiscFunctions.compareResult("After "+splitString2+" doesn't have "+splitString1, priv.split(privilege.name)[1].contains(splitString1), false);
		if(!result){
			throw new ErrorOnPageException("Failed to verify unlock privileges behind all leftover privileges. Please check the details from previous logs.");
		}
	}
}
