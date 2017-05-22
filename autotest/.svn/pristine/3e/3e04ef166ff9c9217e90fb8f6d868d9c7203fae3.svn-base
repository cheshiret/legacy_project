package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.leftoverpriv;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFLicensePurchaseDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Based on quota option with threshold value = 10, verify in Licence Details page
 * 1. Hunt quota is "XX available" when more than 10 available hunt quota
 * 2. Hunt quota is "Hurry up! Only XX available" when equals or less than 10 available hunt quota
 * 
 * @Preconditions: 
 * http://wiki.reserveamerica.com/display/dev/Import+DB+Scripts
 * FileImportConfiguration.sql
 * ConfigureHuntComponents.sql
 * 
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1030
 * d_hf_add_privilege_prd:id=2270
 * d_hf_add_pricing:id=3162
 * d_hf_assi_pri_to_store:id=1520
 * d_hf_add_prvi_license_year:id=2310
 * d_hf_add_qty_control:id=1500
 * d_hf_add_hunt:id=140
 * d_hf_assign_priv_to_hunt:id=140
 * d_hf_add_hunt_license_year:id=190
 * d_hf_add_weapon:id=40
 * d_hf_add_hunt_location:id=60
 * d_hf_add_date_period:id=60
 * d_hf_add_hunt_quota:id=70
 * d_hf_add_species:id=40
 * d_assign_feature:id=4502,4512,4522,4532,4542
 * 
 * @SPEC: TC100410: OTC/Leftover privilege - Hunt Quota Quantity display
 * @Task#: Auto-1869
 * 
 * @author SWang
 * @Date  Sep 6, 2013
 */
public class HuntQuotaQuantityDisplays extends HFSKWebTestCase {
	private HFLicensePurchaseDetailsPage productDetailsPg = HFLicensePurchaseDetailsPage.getInstance();
	private HuntInfo hunt;
	private String quotaDesc, huntQuota1, huntQuota2;
	private int thresholdValue;

	public void execute() {
		//PreConditon: Update available quota, make sure only thresholdValue+1 quota is available
		String hunt_license_year_quota_id = lm.getHuntLicenseYearQuotaID(schema, StringUtil.EMPTY, hunt.getHuntCode(), quotaDesc, privilege.licenseYear);
		int quantities[] = lm.getHuntInventoryQuantities(schema, hunt_license_year_quota_id);
		hf.updateHuntInventoryQuantities(schema, hunt_license_year_quota_id, quantities[0]-quantities[1]+thresholdValue+1, thresholdValue+1);

		//Make privilege to Licence Details page
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.makePrivilegeOrderToLicenseDetailPg(cus, privilege);

		//Check point 1: Hunt quota is "11 available" 
		productDetailsPg.verifyHuntQuota(huntQuota1);

		//Check point 2: Hunt quota is "Hurry up! Only 10 available"
		hf.addPrivilegeToCartFromPrdDetailsPg(privilege);
		hf.continueShppingToPrdDetailsPg(privilege);
		productDetailsPg.verifyHuntQuota(huntQuota2);

		//Check point 3: Hunt quota is "11 available" 
		hf.checkoutNow();
		hf.abandonCart();
		hf.makePrivilegeOrderToLicenseDetailPg(cus, privilege);
		productDetailsPg.verifyHuntQuota(huntQuota1);

		//Sign out
		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00023@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		schema = DataBaseFunctions.getSchemaName("SK", env);

		cus.fName = "LeftoverPri03_FN";
		cus.lName = "LeftoverPri03_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4158";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;

		//Privilege parameters
		privilege.name = "HFSK LeftoverPriv003";
		privilege.licenseYear = hf.getFiscalYear(schema);

		hunt = new HuntInfo();
		hunt.setHuntCode("LH5");
		hunt.setHuntId(hf.getHuntIdByHuntCode(hunt.getHuntCode(), schema));
		hunt.setDescription("Leftover sale hunt 05");
		quotaDesc = "Leftover sale quota 04";

		thresholdValue = 10;
		huntQuota1 = thresholdValue+1 +" available";
		huntQuota2 = "Hurry up! Only "+thresholdValue+" available";
	}
}
