package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.facility.add;

import java.util.Arrays;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddNewFacilityWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrActivityMGTCommonPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Check "Facility" tab after select "Activity MGT" from top menu DDL and "Add Facility" button in Facility list page with feature "ViewActivityFacility" and "CreateModifyActivityFacility"
 * @Preconditions: 
 * @LinkSetUp: 
 * d_assign_feature:id=4872
 * @SPEC:Add Facility-Permission [TC:110302] 
 * @Task#:AUTO-2048 
 * 
 * @author SWang
 * @Date  Jan 6, 2014
 */
public class AddFacilityWithPermission  extends LicenseManagerTestCase {
	private LicMgrActivityMGTCommonPage activityCommonPg = LicMgrActivityMGTCommonPage.getInstance();
	private AdminManager am = AdminManager.getInstance();
	private LoginInfo loginAM = new LoginInfo();
	private RoleInfo role_1, role_2;

	public void execute() {
		//Precondition: Un-assign used two features "ViewActivityFacility" and "CreateModifyActivityFacility" in Admin Manager
		am.loginAdminMgr(loginAM);	
		am.AssignOrUnassignRoleFeatures(Arrays.asList(role_1, role_2));
		am.logoutAdminMgr();

		//Go to License Manager to check no "Facility" tab after select "Activity MGT" from top munu DDL
		lm.loginLicenseManager(login);
		lm.gotoActivityPgFromHomePg();
		activityCommonPg.verifyFacilityTabExisted(false);
		lm.logOutLicenseManager();

		//Assign feature "ViewActivityFacility" in Admin Manager
		am.loginAdminMgr(loginAM);	
		am.assignOrUnAssignRoleFeature(role_1, true);
		am.logoutAdminMgr();

		//Go to License Manager, select "Activity MGT" from top menu DDL, check has "Facility" tab, then click to check having disabled "Add Facility" button
		lm.loginLicenseManager(login);
		lm.gotoActivityPgFromHomePg();
		verifyAddFacilityButtonInFacilityPage(false);
		lm.logOutLicenseManager();

		//Assign feature "CreateModifyActivityFacility" in Admin Manager
		am.loginAdminMgr(loginAM);	
		am.assignOrUnAssignRoleFeature(role_2, true);
		am.logoutAdminMgr();

		//Go to License Manager, select "Activity MGT" from top menu DDL and click "Facility" tab, then check  "Add Facility" button is enabled and clickable
		lm.loginLicenseManager(login);
		lm.gotoActivityPgFromHomePg();
		verifyAddFacilityButtonInFacilityPage(true);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//Login in License Manager
		login.contract = "MO Contract";
		login.location = "MO Admin - Auto/MO Department of Conservation";

		//Login in Admin Manager
		loginAM.url = AwoUtil.getOrmsURL(env);
		loginAM.userName = TestProperty.getProperty("orms.adm.user");
		loginAM.password = TestProperty.getProperty("orms.adm.pw");
		loginAM.contract = "MO Contract";
		loginAM.location = "Administrator - Auto/Missouri Department of Conservation";

		role_1 = new RoleInfo();
		role_1.roleName = "MO-ADMIN - Auto";
		role_1.feature = "ViewActivityFacility";
		role_1.application = "LicenseManager";
		role_1.unAssignOrNot = true;

		role_2 = new RoleInfo();
		role_2.roleName = role_1.roleName;
		role_2.feature = "CreateModifyActivityFacility";
		role_2.application = role_1.application;
		role_2.unAssignOrNot = role_1.unAssignOrNot;
	}

	private void verifyAddFacilityButtonInFacilityPage(boolean hasCreateModifyActivityFacilityFeature){
		LicMgrFacilityListPage facilityPg = LicMgrFacilityListPage.getInstance();
		LicMgrAddNewFacilityWidget addNewFacilityWidget = LicMgrAddNewFacilityWidget.getInstance();
		boolean result = true;

		activityCommonPg.clickFacilityTab();
		ajax.waitLoading();
		facilityPg.waitLoading();

		if(!hasCreateModifyActivityFacilityFeature){
			result &= MiscFunctions.compareResult("Add Facility button is diabled when feature "+role_2.feature+" is unassigned", true, !facilityPg.isAddFacilityButtonDisplayed() && facilityPg.isAddFacilitySpanDisplayed());
		}else {
			result &= MiscFunctions.compareResult("Add Facility button is able when feature "+role_2.feature+" is assigned", true, facilityPg.isAddFacilityButtonDisplayed() && !facilityPg.isAddFacilitySpanDisplayed());
			facilityPg.clickAddFacilityButton();
			ajax.waitLoading();
			addNewFacilityWidget.waitLoading();
			addNewFacilityWidget.clickCancel();
			ajax.waitLoading();
			facilityPg.waitLoading();
		}

		if(!result){
			throw new ErrorOnPageException("Failed to check all check point about Add Facility button in Facility page. Please check the details from previous logs.");
		}
	}

}
