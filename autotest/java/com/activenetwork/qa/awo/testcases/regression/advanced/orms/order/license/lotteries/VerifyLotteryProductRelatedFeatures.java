package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.lotteries;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteriesProductListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the two features "View Privilege Lottery Product List" and "Create Modify Privilege Lottery Product" when
 * 1. assign to the role "MO Mod 1 - Auto" and login in with a park level location -> can view Privilege Lottery Product List but can't add/edit lottery product
 * 2. assign both to the role "MO Mod 1 - Auto" and login in with Chart of Accounts Location -> can view Privilege Lottery Product List and add/edit lottery product
 * 3. unassign both  to the role "MO Mod 1 - Auto" and login in with Chart of Accounts Location -> no Lotteries option in admin drop down
 * 4. only assign "View Privilege Lottery Product List" -> can view Privilege Lottery Product List but can't add/edit lottery product
 * 5. only assign "Create Modify Privilege Lottery Product" -> can view Privilege Lottery Product List and add/edit lottery product
 * @Preconditions:
 * The lottery product with code='001' from production data exist
 * @SPEC: 
 * Privilege Lottery Product - User's Permissions [TC:045993]
 * @LinkSetUp:
 * d_assign_user_roles:id=790
 * @Task#: Auto-2059
 * 
 * @author Lesley Wang
 * @Date  Jan 6, 2014
 */
public class VerifyLotteryProductRelatedFeatures extends LicenseManagerTestCase {
	private AdminManager am = AdminManager.getInstance();
	private LoginInfo loginAM = new LoginInfo();
	private String adminLoc, viewFeature, createFeature, prodCode;
	private RoleInfo roleInfo = new RoleInfo();
	
	@Override
	public void execute() {
		//1. Assign the two features in Admin Manager, and verify in License Manager with  park level location
		this.assignOrUnassignLotteryProductFeatures(true, true);
		this.verifyFeaturesInLM(true, false);
		
		//2. Login in License Manager with Chart of Accounts Location and verify
		login.location = adminLoc;
		this.verifyFeaturesInLM(true, true);
		
		//3. Unassign the two features in Admin Manager and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(false, false);
		this.verifyFeaturesInLM(false, false);
		
		//4. Assign one feature "View Privilege Lottery Product List" in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(true, false);
		this.verifyFeaturesInLM(true, false);
		
		//5. Assign one feature "Create Modify Privilege Lottery Product" in Admin Manager, and verify in License Manager
		this.assignOrUnassignLotteryProductFeatures(false, true);
		this.verifyFeaturesInLM(true, true);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MO Contract";
		login.location = "MO Mod 1 - Auto/ACADEMY SPORTS & OUTDOORS(Store Loc)";
		login.station = "Gate house 1 AM";
		adminLoc = "MO Mod 1 - Auto/MO Department of Conservation";
		
		loginAM.url = AwoUtil.getOrmsURL(env);
		loginAM.contract="MO Contract";
		loginAM.location="Administrator/Missouri Department of Conservation";
		loginAM.userName = TestProperty.getProperty("orms.adm.user");
		loginAM.password = TestProperty.getProperty("orms.adm.pw");
		
		roleInfo.roleName = "MO Mod 1 - Auto";
		roleInfo.application = "LicenseManager";
		
		viewFeature = "ViewPrivilegeLotteryProductList";
		createFeature = "CreateModifyPrivilegeLotteryProduct";
		prodCode = "001";
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
		LicMgrTopMenuPage topMenuPg = LicMgrTopMenuPage.getInstance();
		LicMgrLotteriesProductListPage lotteriesProductListPg = LicMgrLotteriesProductListPage.getInstance();
		
		boolean result = true;
		
		lm.loginLicenseManager(login);
		if (!listShown) {
			result &= MiscFunctions.compareResult("Lotteries option exist in Admin list", false, topMenuPg.isLotteriesOptionExist());
		} else {
			lm.gotoLotteriesProductListPgFromTopMenu();
			result &= MiscFunctions.compareResult("Add Lottery Product button exist", addEditEnabled, lotteriesProductListPg.isAddLotteryProductLinkExist());
			result &= MiscFunctions.compareResult("Edit Display Order button exist", addEditEnabled, lotteriesProductListPg.isEditDisplayOrderLinkExist());
			result &= MiscFunctions.compareResult("Product Code Link exist", addEditEnabled, lotteriesProductListPg.checkLotteryProduct(prodCode));
		}
		
		if (!result) {
			throw new ErrorOnPageException("Failed to verify features in license manager! Check logger error above");
		}
		logger.info("Successfully to verify features in license manager!");
		lm.logOutLicenseManager();
	}
}
