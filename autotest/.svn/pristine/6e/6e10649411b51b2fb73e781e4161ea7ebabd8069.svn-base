package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.product.storeassignment;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryStoreAssignmentsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the features about lottery product store assignments.
 * ViewPrivilegeLotteryProductStoreAssignmentViaLocClass
 * AssignPrivilegeLotteryProductToStoresViaLocClass
 * UnassignPrivilegeLotteryProductFromStoresViaLocClass
 * 1. Unassign all
 * 2. Assign All
 * 3. Assign View, not other two features.
 * @Preconditions:
 * 1. make sure the role "HF HQ Role - Auto" has been assigned the feature "Create Modify Privilege Lottery Product"
 * 2. make sure the lottery product "ERF" exist
 * @LinkSetUp:
 * d_assign_user_roles:id=800
 * d_assign_feature:id=4912
 * d_hf_add_lottery_prd:id=10
 * @SPEC:
 * Store Assignments - User's Permissions [TC:046274]
 * @Task#: Auto-2061
 * 
 * @author Lesley Wang
 * @Date  Jan 13, 2014
 */
public class VerifyLotteryStoreAssignFeatures extends LicenseManagerTestCase {
	private AdminManager am = AdminManager.getInstance();
	private LoginInfo loginAM = new LoginInfo();
	private String viewFeature, assignFeature, unassignFeature;
	private RoleInfo roleInfo = new RoleInfo();
	private LicMgrLotteryProductDetailsPage detailsPg = LicMgrLotteryProductDetailsPage.getInstance();
	private LicMgrLotteryStoreAssignmentsPage storeAssignPg = LicMgrLotteryStoreAssignmentsPage.getInstance();
	
	@Override
	public void execute() {
		//1. Unassign the three features in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(false, false, false);
		this.verifyFeaturesInLM(false, false, false);
		
		//2. Assign one feature "ViewPrivilegeLotteryProductStoreAssignmentViaLocClass" in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(true, false, false);
		this.verifyFeaturesInLM(true, false, false);
		
		//4. Assign the three features in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(true, true, true);
		this.verifyFeaturesInLM(true, true, true);
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
		
		viewFeature = "ViewPrivilegeLotteryProductStoreAssignmentViaLocClass";
		assignFeature = "AssignPrivilegeLotteryProductToStoresViaLocClass";
		unassignFeature = "UnassignPrivilegeLotteryProductFromStoresViaLocClass";
		
		lotteryPrd.setCode("ERF");
	}
	
	private void assignOrUnassignLotteryProductFeatures(boolean assignedViewFeature, boolean assignedAssignFeature, boolean assigendUnassignFeature){
		am.loginAdminMgr(loginAM);
		roleInfo.feature = viewFeature;
		am.assignOrUnAssignRoleFeature(roleInfo, assignedViewFeature);
		roleInfo.feature = assignFeature;
		am.assignOrUnAssignRoleFeature(roleInfo, assignedAssignFeature);
		roleInfo.feature = unassignFeature;
		am.assignOrUnAssignRoleFeature(roleInfo, assigendUnassignFeature);
		am.logoutAdminMgr();
	}
	
	private void verifyFeaturesInLM(boolean listShown, boolean assignEnabled, boolean unassignEnabled) {
		boolean result = true;
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(lotteryPrd.getCode());
		if (!listShown) {
			result &= MiscFunctions.compareResult("Store Assignments tab exist", false, detailsPg.isStoreAssignmentsTabExist());
		} else {
			detailsPg.clickAgentAssignmentsTab();
			ajax.waitLoading();
			storeAssignPg.waitLoading();
			
			result &= MiscFunctions.compareResult("Assign to Agents button exist", assignEnabled, storeAssignPg.isAssignToStoresLinkExist());
			result &= MiscFunctions.compareResult("Unassign to Agents button exist", unassignEnabled, storeAssignPg.isUnassignToStoresLinkExist());
		}
		
		if (!result) {
			throw new ErrorOnPageException("Failed to verify features in license manager! Check logger error above");
		}
		logger.info("Successfully to verify features in license manager!");
		lm.logOutLicenseManager();
	}
}
