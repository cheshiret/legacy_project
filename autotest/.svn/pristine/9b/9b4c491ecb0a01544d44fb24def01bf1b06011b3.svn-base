package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.leftoverpriv;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFAvailableHuntsPage;
import com.activenetwork.qa.awo.pages.web.hf.HFShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Multiple hunt is associated with leftover privilege
 * Go to available hunt page when more than one hunt are available for using leftover privilege
 * Verify error message in available hunt page when selected hunt is no longer available
 * Verify skip available hunt page when only one hunt is available for using leftover privilege
 * 
 * @Preconditions:
 * http://wiki.reserveamerica.com/display/dev/Import+DB+Scripts
 * FileImportConfiguration.sql
 * ConfigureHuntComponents.sql
 * 
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1060
 * d_hf_add_privilege_prd:id=2290
 * d_hf_add_pricing:id=3182
 * d_hf_assi_pri_to_store:id=1540
 * d_hf_add_prvi_license_year:id=2330
 * d_hf_add_qty_control:id=1520
 * d_hf_add_hunt:id=160,170
 * d_hf_assign_priv_to_hunt:id=160,170
 * d_hf_add_hunt_license_year:id=210,220
 * d_hf_add_hunt_quota:id=100
 * d_assign_feature:id=4502,4512,4522,4532,4542
 * 
 * @SPEC: 
 * TC101734:OTC/Leftover Privilege - Only one hunt available for selection
 * TC100416:OTC/Leftover Privilege - Hunt selection and error message verification
 * @Task#: Auto-1869
 * 
 * @author SWang
 * @Date  Sep 4, 2013
 */
public class MultiHuntsAsscociatedOnlyOneAvailable extends HFSKWebTestCase {
	private HFAvailableHuntsPage huntsPg = HFAvailableHuntsPage.getInstance();
	private HFShoppingCartPage shoppingCartPg = HFShoppingCartPage.getInstance();
	private HuntInfo hunt1, hunt2;
	private String quotaDesc, errorMes, hunt_license_year_quota_id;
	private int quantities[];

	public void execute() {
		//Go to available hunt page after click add to cart button in purchase license page when 2 available hunts associated
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.makePrivilegeOrder(cus, privilege, huntsPg);

		//In available hunt page verify error message when selected hunt is no longer available
		hf.updateHuntInventoryQuantities(schema, hunt_license_year_quota_id, quantities[0]-quantities[1]+1, 1);
		hf.addHuntFromAvailableHuntPg(hunt2.getHuntId());
		huntsPg.verifyErrorMsgExist(errorMes, true);

		//In available hunt page, click add to cart to shopping cart page when only one available hunt associated
		hf.updateHuntInventoryQuantities(schema, hunt_license_year_quota_id, quantities[0]-quantities[1]+2, 2);
		hf.addHuntFromAvailableHuntPg(hunt2.getHuntId());
		hf.continueShopping(privilege, shoppingCartPg);

		//Sign out
		hf.abandonCart();
		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00026@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		schema = DataBaseFunctions.getSchemaName("SK", env);

		cus.fName = "LeftoverPri06_FN";
		cus.lName = "LeftoverPri06_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4161";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;

		//Privilege parameters
		privilege.name = "HFSK LeftoverPriv005";
		privilege.licenseYear = hf.getFiscalYear(schema);

		hunt1 = new HuntInfo();
		hunt1.setHuntCode("LH7");
		hunt1.setDescription("Leftover sale hunt 07");
		hunt1.setHuntId(hf.getHuntIdByHuntCode(hunt1.getHuntCode(), schema));

		hunt2 = new HuntInfo();
		hunt2.setHuntCode("LH8");
		hunt2.setDescription("Leftover sale hunt 08");
		hunt2.setHuntId(hf.getHuntIdByHuntCode(hunt2.getHuntCode(), schema));
		quotaDesc = "Leftover sale quota 07";

		hunt_license_year_quota_id = lm.getHuntLicenseYearQuotaID(schema, StringUtil.EMPTY, hunt2.getHuntCode(), quotaDesc, privilege.licenseYear);
		quantities = lm.getHuntInventoryQuantities(schema, hunt_license_year_quota_id);

		errorMes = "The selected hunt is no longer available. Please select another hunt.";
	}
}
