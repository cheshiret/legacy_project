package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.hf.purchaseprivilege.leftoverpriv;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.pages.web.hf.HFAvailableHuntsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.hf.HFSKWebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P)
 * In available hunt page, verify 3 hunts initial quota (LH2:x, LH3:x, and LH4:x)
 * check hunts quota (LH2:x-1, LH3:x, and LH4:x) after lock quota of hunt LH2
 * check hunts quota (LH2:x-1, LH3:x-1, and LH4:x-1) after lock same quota of hunt LH3 and LH4
 * check initial hunts quota (LH2:x, LH3:x, and LH4:x) after abandon
 * 
 * @Preconditions: 3 hunts, 2 of them has same quota.
 * http://wiki.reserveamerica.com/display/dev/Import+DB+Scripts
 * FileImportConfiguration.sql
 * ConfigureHuntComponents.sql
 * 
 * @LinkSetUp:
 * d_web_hf_signupaccount:id=1020
 * d_hf_add_privilege_prd:id=2260
 * d_hf_add_pricing:id=3152
 * d_hf_assi_pri_to_store:id=1510
 * d_hf_add_prvi_license_year:id=2300
 * d_hf_add_qty_control:id=1490
 * d_hf_add_hunt:id=110,120,130
 * d_hf_assign_priv_to_hunt:id=110,120,130
 * d_hf_add_hunt_license_year:id=160,170,180
 * d_hf_add_weapon:id=40
 * d_hf_add_hunt_location:id=60
 * d_hf_add_date_period:id=60
 * d_hf_add_hunt_quota:id=50
 * d_hf_add_species:id=40
 * d_assign_feature:id=4502,4512,4522,4532,4542
 * 
 * @SPEC: TC101735: OTC/Leftover Privilege - Hunt Quota Quantity - Lock Inventory
 * @Task#: Auto-1869
 * 
 * @author SWang
 * @Date  Sep 6, 2013
 */
public class LockHuntQuotaQuantity extends HFSKWebTestCase {
	private HFAvailableHuntsPage huntsPg = HFAvailableHuntsPage.getInstance();
	private HuntInfo hunt1, hunt2, hunt3;
	private String quotaDesc1, quotaDesc2;
	private int availableQuota1, availableQuota2;
	private String[] huntIDs;

	public void execute() {
		//Get initial available quota based on hunt code and quota description
		availableQuota1 = hf.getHuntInventoryQuantities(schema, hf.getHuntLicenseYearQuotaID(schema, StringUtil.EMPTY, hunt1.getHuntCode(), quotaDesc1, privilege.licenseYear))[1];
		availableQuota2 = hf.getHuntInventoryQuantities(schema, hf.getHuntLicenseYearQuotaID(schema, StringUtil.EMPTY, hunt2.getHuntCode(), quotaDesc2, privilege.licenseYear))[1];

		//Make privilege to available hunt page
		hf.invokeURL(url);
		hf.lookupAccountToAcctOverviewPg(cus);
		hf.makePrivilegeOrderToLicenseDetailPg(cus, privilege);
		hf.addPrivilegeFromPrdDetailsPg(privilege, huntsPg);

		//Check point 1: check initial hunts (LH2, LH3, LH4) quota
		huntsPg.verifyHuntQuota(huntIDs, new int[]{availableQuota1, availableQuota2, availableQuota2});

		//Check point 2: check hunts quota after lock quota of hunt LH2
		hf.addHuntFromAvailableHuntPg(hunt1.getHuntId());
		hf.continueShopping(privilege, huntsPg);
		huntsPg.verifyHuntQuota(huntIDs, new int[]{availableQuota1-1, availableQuota2, availableQuota2});

		//Check point 3: check hunts quota after lock same quota of hunt LH3 and LH4
		hf.addHuntFromAvailableHuntPg(hunt2.getHuntId());
		hf.continueShopping(privilege, huntsPg);
		huntsPg.verifyHuntQuota(huntIDs, new int[]{availableQuota1-1, availableQuota2-1, availableQuota2-1});

		//Check point 4: check initial hunts quota after abandon
		hf.checkoutNow();
		hf.abandonCart();
		hf.makePrivilegeOrder(cus, privilege, huntsPg);
		huntsPg.verifyHuntQuota(huntIDs, new int[]{availableQuota1, availableQuota2, availableQuota2});

		//Sign out
		hf.signOut();
	}

	public void wrapParameters(Object[] param) {
		cus.email = "hfsk_00022@webhftest.com";
		cus.password = TestProperty.getProperty("web.login.pw");
		schema = DataBaseFunctions.getSchemaName("SK", env);

		cus.fName = "LeftoverPri02_FN";
		cus.lName = "LeftoverPri02_LN";
		cus.dateOfBirth = DateFunctions.getYearAfterCurrentYear(-16)+"-01-01";
		cus.identifier.identifierType = IDENT_TYPE_RCMP;
		cus.identifier.identifierNum = "1R9Y4x4157";
		cus.identifier.state = "Ontario";
		cus.residencyStatus = RESID_STATUS_SK;

		//Privilege parameters
		privilege.name = "HFSK LeftoverPriv002";
		privilege.licenseYear = hf.getFiscalYear(schema);

		hunt1 = new HuntInfo();
		hunt1.setHuntCode("LH2");
		hunt1.setDescription("Leftover sale hunt 02");
		hunt1.setHuntId(hf.getHuntIdByHuntCode(hunt1.getHuntCode(), hunt1.getDescription(), schema));
		quotaDesc1 = "Leftover sale quota 02";

		hunt2 = new HuntInfo();
		hunt2.setHuntCode("LH3");
		hunt2.setDescription("Leftover sale hunt 03");
		hunt2.setHuntId(hf.getHuntIdByHuntCode(hunt2.getHuntCode(), hunt2.getDescription(), schema));
		quotaDesc2 = "Leftover sale quota 03";

		hunt3 = new HuntInfo();
		hunt3.setHuntCode("LH4");
		hunt3.setDescription("Leftover sale hunt 04");
		hunt3.setHuntId(hf.getHuntIdByHuntCode(hunt3.getHuntCode(), hunt3.getDescription(), schema));

		huntIDs = new String[]{hunt1.getHuntId(), hunt2.getHuntId(), hunt3.getHuntId()};
	}
}
