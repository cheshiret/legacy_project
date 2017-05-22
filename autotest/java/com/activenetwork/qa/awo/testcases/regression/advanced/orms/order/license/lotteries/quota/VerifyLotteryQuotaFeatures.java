package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.quota;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteriesProductListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrQuotaListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the lottery quota related features
 * ViewPrivilegeLotteryQuotaList
 * CreateModifyPrivilegeLotteryQuota
 * ViewPrivilegeLotteryQuotaChangeHistory
 * ViewHuntQuotaLicenseYearList
 * @Preconditions:
 * @SPEC: 
 *  User has no Create/Modify Privilege Lottery Quota permission [TC:044485]
 *  User has no Create/Modify Privilege Lottery Quota permission [TC:044486]
 *  Permission [TC:044483]
 *  Permission [TC:044484]
 * @LinkSetUp:
 * d_assign_user_roles:id=800
 * d_assign_feature:id=4912
 * d_hf_add_hunt_quota:id=10
 * @Task#: Auto-2067, Auto-2068, Auto-2069, Auto-2070
 * @author Lesley Wang
 * @Date  Jan 21, 2014
 * @Note:blocked by defect DEFECT-61467
 */
public class VerifyLotteryQuotaFeatures extends LicenseManagerTestCase {
	private AdminManager am = AdminManager.getInstance();
	private LoginInfo loginAM = new LoginInfo();
	private String[] features;
	private String quotaDes;
	private RoleInfo roleInfo = new RoleInfo();
	private LicMgrQuotaListPage quotaLisPg = LicMgrQuotaListPage.getInstance();
	private LicMgrLotteriesProductListPage lotteriesProductListPg = LicMgrLotteriesProductListPage.getInstance();
	private LicMgrQuotaDetailsPage quotaDetailsPg = LicMgrQuotaDetailsPage.getInstance();
	
	@Override
	public void execute() {
		//1. Only assign the view feature in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(features, new Boolean[] {true, false, false});
		this.verifyFeaturesInLM(true, false, false);			
		
		//2. Assign the view and add features in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(features, new Boolean[] {true, true, false});
		this.verifyFeaturesInLM(true, true, false);	
		
		//3. Assign the view and add features in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(features, new Boolean[] {true, true, true});
		this.verifyFeaturesInLM(true, true, true);	
		
		//4. Unassign the features in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(features, new Boolean[] {false, false, false});
		this.verifyFeaturesInLM(false, false, false);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		loginAM.url = AwoUtil.getOrmsURL(env);
		loginAM.contract="MS Contract";
		loginAM.location="Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		loginAM.userName = TestProperty.getProperty("orms.adm.user");
		loginAM.password = TestProperty.getProperty("orms.adm.pw");
		
		roleInfo.roleName = "HF HQ Role - Auto";
		roleInfo.application = "LicenseManager";
		
		features = new String[] {"ViewPrivilegeLotteryQuotaList", "CreateModifyPrivilegeLotteryQuota", "ViewPrivilegeLotteryQuotaChangeHistory"};
		quotaDes = "quotaAddHunt";
	}
	
	private void assignOrUnassignLotteryProductFeatures(String[] features, Boolean[] isAssigned){
		am.loginAdminMgr(loginAM);
		for (int i = 0; i < features.length; i++) {
			roleInfo.feature = features[i];
			am.assignOrUnAssignRoleFeature(roleInfo, isAssigned[i]);
		}
		am.logoutAdminMgr();
	}
	
	private void verifyFeaturesInLM(boolean listShown, boolean addBtnShown, boolean historyShown) {
		boolean result = true;
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		result &= MiscFunctions.compareResult("Quota tab exist", listShown, lotteriesProductListPg.checkQuotaTabExists());
		if (listShown) {
			lm.gotoQuotaListPgFromLotteriesProdListPg();
			result &= MiscFunctions.compareResult("Add Quota Button exist", addBtnShown, quotaLisPg.checkAddQuotaExists());	
			String quotaID = quotaLisPg.getQuotaID(quotaDes);
			result &= MiscFunctions.compareResult("Quota ID link exist", addBtnShown, quotaLisPg.checkQuotaIDLinkExists(quotaID));	
			
			if (addBtnShown) {
				lm.gotoQuotaDetailsPgFromQuotaListPg(quotaID);
				result &= MiscFunctions.compareResult("Change History link", historyShown, quotaDetailsPg.checkChangeHistoryLinkExist());	
			}
		}
		
		if (!result) {
			throw new ErrorOnPageException("Failed to verify features in license manager! Check logger error above");
		}
		logger.info("Successfully to verify features in license manager!");
		lm.logOutLicenseManager();
	}

}
