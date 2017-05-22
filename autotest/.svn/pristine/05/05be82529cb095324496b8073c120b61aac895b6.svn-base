package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.facility.facilityPrd;

import java.util.Arrays;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.FacilityPrdAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.SearchFacilityAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrAddFacilityProductWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityListPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt.LicMgrFacilityProductPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: (P) Verify below check points with permissions "ViewFacilityProduct", "CreateModifyFacilityProduct", "CreateNewFacilityProductGroupType" and "ViewFacilityProductInfoChangeHistory"
 * 1. "Facility Product" tab
 * 2. "Add Facility Product" and "Change History" buttons in facility product list
 * 3. "Add New" button in add facility product widget
 * @Preconditions:Role "SK Admin - Auto" has permission "ViewActivityProduct", "ViewActivityFacility" and " CreateModifyActivityFacility"
 * @LinkSetUp: 
 * d_assign_feature:id=4972, 4982, 4992
 * @SPEC:
 * Permission Tesing [TC:110139] 
 * View Facility Product Change History-Permisson Check [TC:111015] 
 * @Task#:Auto-2049
 * 
 * @author SWang
 * @Date  Jan 9, 2014
 */
public class AddFacilityProAndChangeHistoryPermission extends LicenseManagerTestCase {
	private LicMgrFacilityDetailsPage facilityDetailsPg = LicMgrFacilityDetailsPage.getInstance();
	private LicMgrFacilityListPage facilityListPg = LicMgrFacilityListPage.getInstance();
	private LicMgrFacilityProductPage facilityPrdPg = LicMgrFacilityProductPage.getInstance();
	private AdminManager am = AdminManager.getInstance();
	private LoginInfo loginAM = new LoginInfo();
	private RoleInfo role_1, role_2, role_3, role_4;
	private FacilityData fd = new FacilityData();
	private Data<SearchFacilityAttr> searchFacility = new Data<SearchFacilityAttr>();
	private Data<FacilityPrdAttr> fpd;

	public void execute() {
		//Precondition
		//* Activity facility
		lm.loginLicenseManager(login);
		lm.gotoFacilityListPgFromHomePg();
		if(!facilityListPg.hasFacility(searchFacility)){
			lm.addFacility(fd);
		}
		lm.logOutLicenseManager();

		//* Go to Admin Manager to Un-assign features "ViewFacilityProduct", "CreateModifyFacilityProduct", "CreateNewFacilityProductGroupType" and "ViewFacilityProductInfoChangeHistory"
		am.loginAdminMgr(loginAM);	
		am.AssignOrUnassignRoleFeatures(Arrays.asList(role_1, role_2, role_3, role_4));
		am.logoutAdminMgr();

		//Go to License Manager Facility Details page to check no "Facility Product" tab
		lm.loginLicenseManager(login);
		lm.gotoFacilityDetailsPgFromHomePg(searchFacility);
		facilityDetailsPg.verifyFacilityProTabExisted(false);
		lm.logOutLicenseManager();

		//Assign feature "ViewFacilityProduct" in Admin Manager
		am.loginAdminMgr(loginAM);	
		am.assignOrUnAssignRoleFeature(role_1, true);
		am.logoutAdminMgr();

		//Go to License Manager Facility Details page and then click "Facility Product" tab, verify no "Add Facility Product" button
		lm.loginLicenseManager(login);
		lm.gotoFacilityPrdListFromHome(searchFacility);
		facilityPrdPg.verifyAddFacilityProductButtonExisted(false);
		lm.logOutLicenseManager();

		//Assign feature "CreateModifyFacilityProduct" in Admin Manager
		am.loginAdminMgr(loginAM);	
		am.assignOrUnAssignRoleFeature(role_2, true);
		am.logoutAdminMgr();

		//Go to License Manager Facility Product list page, click Add Facility Product to widget page to check no Add New button
		lm.loginLicenseManager(login);
		lm.gotoFacilityPrdListFromHome(searchFacility);
		verifyAddNewButtonInAddFacilityPrdWidget(false);
		lm.logOutLicenseManager();

		//Assign feature "CreateNewFacilityProductGroupType" in Admin Manager
		am.loginAdminMgr(loginAM);	
		am.assignOrUnAssignRoleFeature(role_3, true);
		am.logoutAdminMgr();

		//Go to License Manager Facility Product list page, click Add Facility Product to widget page to check has Add New button
		lm.loginLicenseManager(login);
		lm.gotoFacilityPrdListFromHome(searchFacility);
		verifyAddNewButtonInAddFacilityPrdWidget(true);

		//Add facility prd
		facilityPrdPg.searchFacilityPrdWithPrdName(fpd.stringValue(FacilityPrdAttr.prdName));
		if(facilityPrdPg.getNumOfFacilityPrd()<1){
			lm.addFacilityPrd(fpd);
		}
		facilityPrdPg.verifyAddFacilityProductButtonExisted(false, fpd.stringValue(FacilityPrdAttr.prdCode), fpd.stringValue(FacilityPrdAttr.prdName));
		lm.logOutLicenseManager();

		//Assign feature "ViewFacilityProductInfoChangeHistory" in Admin Manager
		am.loginAdminMgr(loginAM);	
		am.assignOrUnAssignRoleFeature(role_4, true);
		am.logoutAdminMgr();

		//Go to License Manager Facility details page to check Change History button
		lm.loginLicenseManager(login);
		lm.gotoFacilityPrdListFromHome(searchFacility);
		facilityPrdPg.verifyAddFacilityProductButtonExisted(true, fpd.stringValue(FacilityPrdAttr.prdCode), fpd.stringValue(FacilityPrdAttr.prdName));
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//Login in License Manager
		login.contract = "SK Contract";
		login.location = "SK Admin - Auto/SASK";

		//Facility parameters
		fd = new FacilityData();
		fd.initializeFacilityData();
		fd.facilityName = "FacilityForAddFacilityProAndChangeHistoryPermission";
		searchFacility.put(SearchFacilityAttr.facilityName, fd.facilityName);

		//Facility product parameters
		fpd = new Data<FacilityPrdAttr>();
		fpd.put(FacilityPrdAttr.prdCode, "AddPrdCodeWithPermission");
		fpd.put(FacilityPrdAttr.prdName, "AddPrdNameWithPermission");
		fpd.put(FacilityPrdAttr.prdStatus, "Active");
		fpd.put(FacilityPrdAttr.prdDesc, "AddPrdDescWithPermission");
		fpd.put(FacilityPrdAttr.prdType, "FacilityPrdType");
		fpd.put(FacilityPrdAttr.capacity, "12");
		fpd.put(FacilityPrdAttr.handicappedAccessible, "Yes");

		//Login in Admin Manager
		loginAM.url = AwoUtil.getOrmsURL(env);
		loginAM.userName = TestProperty.getProperty("orms.adm.user");
		loginAM.password = TestProperty.getProperty("orms.adm.pw");
		loginAM.contract = "SK Contract";
		loginAM.location = "Administrator - Auto/Saskatchewan Ministry of Environment";

		role_1 = new RoleInfo();
		role_1.roleName = "SK Admin - Auto";
		role_1.feature = "ViewFacilityProduct";
		role_1.application = "LicenseManager";
		role_1.unAssignOrNot = true;

		role_2 = new RoleInfo();
		role_2.roleName = role_1.roleName;
		role_2.feature = "CreateModifyFacilityProduct";
		role_2.application = role_1.application;
		role_2.unAssignOrNot = role_1.unAssignOrNot;

		role_3 = new RoleInfo();
		role_3.roleName = role_1.roleName;
		role_3.feature = "CreateNewFacilityProductGroupType";
		role_3.application = role_1.application;
		role_3.unAssignOrNot = role_1.unAssignOrNot;

		role_4 = new RoleInfo();
		role_4.roleName = role_1.roleName;
		role_4.feature = "ViewFacilityProductInfoChangeHistory";
		role_4.application = role_1.application;
		role_4.unAssignOrNot = role_1.unAssignOrNot;
	}

	private void verifyAddNewButtonInAddFacilityPrdWidget(boolean addNewDisplaysInAddFacilityPrdWidget){
		LicMgrFacilityProductPage facilityPrdPg = LicMgrFacilityProductPage.getInstance();
		LicMgrAddFacilityProductWidget addFacilityPrdWidget = LicMgrAddFacilityProductWidget.getInstance();
		boolean result = true;

		facilityPrdPg.clickAddFacilityProductButton();
		ajax.waitLoading();
		addFacilityPrdWidget.waitLoading();

		if(!addNewDisplaysInAddFacilityPrdWidget){
			result &= MiscFunctions.compareResult("Add New button should not display when feature "+role_3.feature+" is unassigned", false, addFacilityPrdWidget.isAddNewExisted());
		}else {
			result &= MiscFunctions.compareResult("Add New button should display when feature "+role_3.feature+" is assigned", true, addFacilityPrdWidget.isAddNewExisted());
		}

		addFacilityPrdWidget.clickCancel();
		ajax.waitLoading();
		facilityPrdPg.waitLoading();

		if(!result){
			throw new ErrorOnPageException("Failed to check all check point about Add New button in Add Facility Product widget. Please check the details from previous logs.");
		}
		logger.info("Successcully verify all check points about Add New button in Add Facility Product widget. Please check the details from previous logs.");
	}
}

