package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries.product.pricing;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryProductDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the two features "View Privilege Lottery Product Pricing" and "Create/Modify Privilege Lottery Product Pricing" when
 * 1. unassign both  to the role "HF HQ Role - Auto" -> no Pricing tab shown
 * 2. only assign "View Privilege Lottery Product Pricing" to the role "HF HQ Role - Auto"  -> can view Privilege Lottery Product Pricing but can't add/edit lottery product Pricing
 * 3. only assign "Create/Modify Privilege Lottery Product Pricing" -> can view Privilege Lottery Product Pricing and add/edit lottery product Pricing
 * 4. assign both -> can view Privilege Lottery Product Pricing and add/edit lottery product Pricing
 * @Preconditions:
 * 1. make sure the role "HF HQ Role - Auto" has been assigned the feature "Create Modify Privilege Lottery Product"
 * 2. make sure the lottery product "ERF" exist
 * @SPEC: 
 * Pricing - user's permissions [TC:049749]
 * @LinkSetUp:
 * d_assign_user_roles:id=800
 * d_assign_feature:id=4912
 * d_hf_add_lottery_prd:id=10
 * d_hf_add_pricing:id=3292
 * @Task#: Auto-2060
 * 
 * @author Lesley Wang
 * @Date  Jan 7, 2014
 */
public class VerifyLotteryPricingFeatures extends LicenseManagerTestCase {
	private AdminManager am = AdminManager.getInstance();
	private LoginInfo loginAM = new LoginInfo();
	private String viewFeature, createFeature, code;
	private RoleInfo roleInfo = new RoleInfo();
	private LicMgrLotteryProductDetailsPage detailsPg = LicMgrLotteryProductDetailsPage.getInstance();
	private LicMgrLotteryPricingPage pricingPg = LicMgrLotteryPricingPage.getInstance();
	
	@Override
	public void execute() {
		//1. Unassign the two features in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(false, false);
		this.verifyFeaturesInLM(false, false);
		
		//2. Assign one feature "View Privilege Lottery Product Pricing" in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(true, false);
		this.verifyFeaturesInLM(true, false);
		
		//3. Assign one feature "Create Modify Privilege Lottery Product Prcing" in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(false, true);
		this.verifyFeaturesInLM(true, true);

		//4. Assign the two features in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(true, true);
		this.verifyFeaturesInLM(true, true);
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
		
		viewFeature = "ViewPrivilegeLotteryProductPricing";
		createFeature = "CreateModifyPrivilegeLotteryProductPricing";
		code = "ERF";
	}
	
	private void assignOrUnassignLotteryProductFeatures(boolean assignedViewFeature, boolean assignedCreatedFeature){
		am.loginAdminMgr(loginAM);
		roleInfo.feature = viewFeature;
		am.assignOrUnAssignRoleFeature(roleInfo, assignedViewFeature);
		roleInfo.feature = createFeature;
		am.assignOrUnAssignRoleFeature(roleInfo, assignedCreatedFeature);
		am.logoutAdminMgr();
	}
	
	private void verifyFeaturesInLM(boolean listShown, boolean addEditEnabled) {
		boolean result = true;
		
		lm.loginLicenseManager(login);
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(code);
		if (!listShown) {
			result &= MiscFunctions.compareResult("Pricing tab exist", false, detailsPg.isPricingTabExist());
		} else {
			result &= MiscFunctions.compareResult("Add Product Pricing button exist", addEditEnabled, pricingPg.isAddProductPricingExist());
			String pricingID = pricingPg.getFirstPricingID();
			result &= MiscFunctions.compareResult("Pricing ID Link exist", addEditEnabled, pricingPg.checkPricingRecordExists(pricingID));
		}
		
		if (!result) {
			throw new ErrorOnPageException("Failed to verify features in license manager! Check logger error above");
		}
		logger.info("Successfully to verify features in license manager!");
		lm.logOutLicenseManager();
	}
}
