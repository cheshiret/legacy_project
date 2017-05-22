package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.product.huntassignment;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryHuntsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryProductDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the features about lottery product hunt assignment.
 * ViewPrivilegeLotteryHuntAssignmentList
 * AssignUnassignHunttofromPrivilegeLottery
 * 1. Unassign both
 * 2. Only assign ViewPrivilegeLotteryHuntAssignmentList
 * 3. Assign both
 * @Preconditions:
 * 1. make sure the role "HF HQ Role - Auto" has been assigned the feature "Create Modify Privilege Lottery Product"
 * 2. make sure the lottery product "ERF" exist
 * @SPEC: 
 * Hunts Sub Tab - user's permissions [TC:046281]
 * @LinkSetUp:
 * d_assign_user_roles:id=800
 * d_assign_feature:id=4912
 * d_hf_add_lottery_prd:id=10
 * @Task#: Auto-2062
 * 
 * @author Lesley Wang
 * @Date  Jan 14, 2014
 */
public class VerifyLotteryHuntsFeatures extends LicenseManagerTestCase {
	private AdminManager am = AdminManager.getInstance();
	private LoginInfo loginAM = new LoginInfo();
	private String viewFeature, assignUnassginFeature;
	private RoleInfo roleInfo = new RoleInfo();
	private LicMgrLotteryProductDetailsPage detailsPg = LicMgrLotteryProductDetailsPage.getInstance();
	private LicMgrLotteryHuntsPage huntsPg = LicMgrLotteryHuntsPage.getInstance();
	
	@Override
	public void execute() {
		//1. Assign the two features in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(true, true);
		this.verifyFeaturesInLM(true, true);
		
		//2. Only assign the View feature in Admin Manager and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(true, false);
		this.verifyFeaturesInLM(true, false);
		
		//3. Unassign both features in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(false, false);
		this.verifyFeaturesInLM(false, false);
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
		
		viewFeature = "ViewPrivilegeLotteryHuntAssignmentList";
		assignUnassginFeature = "AssignUnassignHunttofromPrivilegeLottery";
		
		lotteryPrd.setCode("ERF");
	}
	
	private void assignOrUnassignLotteryProductFeatures(boolean assignedViewFeature, boolean assignedCreatedFeature){
		am.loginAdminMgr(loginAM);
		roleInfo.feature = viewFeature;
		am.assignOrUnAssignRoleFeature(roleInfo, assignedViewFeature);
		roleInfo.feature = assignUnassginFeature;
		am.assignOrUnAssignRoleFeature(roleInfo, assignedCreatedFeature);
		am.logoutAdminMgr();
	}
	
	private void verifyFeaturesInLM(boolean listShown, boolean assignEnabled) {
		boolean result = true;
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lotteryPrd.getCode());
		if (!listShown) {
			result &= MiscFunctions.compareResult("Hunts sub tab exist", false, detailsPg.isHuntsTabExist());
		} else {
			detailsPg.clickHuntsTab();
			ajax.waitLoading();
			huntsPg.waitLoading();	
			result &= MiscFunctions.compareResult("Assign Hunt Button exist", assignEnabled, huntsPg.isAssignHuntExist());
		}
		
		if (!result) {
			throw new ErrorOnPageException("Failed to verify features in license manager! Check logger error above");
		}
		logger.info("Successfully to verify features in license manager!");
		lm.logOutLicenseManager();
	}
}
