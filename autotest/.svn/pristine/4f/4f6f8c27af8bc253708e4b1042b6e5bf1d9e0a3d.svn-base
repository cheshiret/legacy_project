package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryContractFeesPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryProductDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Verify the features about lottery product store assignments.
 * View Privilege Lottery Product Contractor Fees
 * 1. Unassign
 * 2. Assign
 * @Preconditions:
 * 1. make sure the role "HF HQ Role - Auto" has been assigned the feature "Create Modify Privilege Lottery Product"
 * 2. make sure the lottery product "ERF" exist
 * @LinkSetUp:
 * d_assign_user_roles:id=800
 * d_assign_feature:id=4912
 * d_hf_add_lottery_prd:id=10
 * @SPEC:
 * Contractor Fees - User's permission [TC:044951]
 * @Task#: Auto-2063
 * 
 * @author Lesley Wang
 * @Date  Jan 14, 2014
 */
public class VerifyLotteryContractorFeesFeatures extends LicenseManagerTestCase {
	private AdminManager am = AdminManager.getInstance();
	private LoginInfo loginAM = new LoginInfo();
	private String viewFeature;
	private RoleInfo roleInfo = new RoleInfo();
	private LicMgrLotteryProductDetailsPage detailsPg = LicMgrLotteryProductDetailsPage.getInstance();
	
	@Override
	public void execute() {
		//1. Unassign the feature in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(false);
		this.verifyFeaturesInLM(false);
		
		//2. Assign the feature in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(true);
		this.verifyFeaturesInLM(true);
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
		
		viewFeature = "ViewPrivilegeLotteryProductContractorFees";
		
		lotteryPrd.setCode("ERF");
	}
	
	private void assignOrUnassignLotteryProductFeatures(boolean assignedViewFeature){
		am.loginAdminMgr(loginAM);
		roleInfo.feature = viewFeature;
		am.assignOrUnAssignRoleFeature(roleInfo, assignedViewFeature);
		am.logoutAdminMgr();
	}
	
	private void verifyFeaturesInLM(boolean listShown) {
		boolean result = true;
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lotteryPrd.getCode());
		result &= MiscFunctions.compareResult("Contractor Fees List tab exist", listShown, detailsPg.isContractorFeesTabExist());
		if (listShown) {
			detailsPg.clickContractorFeesTab();
			ajax.waitLoading();
			LicMgrLotteryContractFeesPage.getInstance().waitLoading();	
		}
		
		if (!result) {
			throw new ErrorOnPageException("Failed to verify features in license manager! Check logger error above");
		}
		logger.info("Successfully to verify features in license manager!");
		lm.logOutLicenseManager();
	}
}
