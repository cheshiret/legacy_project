/*
 * Created on May 1, 2009
 */
package com.activenetwork.qa.awo.keywords.orms;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ProductGroup;
import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.datacollection.legacy.ServiceAndActivity;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AdminUserInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgGlobalUserListPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrEditProductGroupWidget;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrProductGroupListPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRoleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRoleFeaturePage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRolePage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRuleDetailPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRuleListPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrSearchLocationPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrSelectLocationPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrServiceDetailsPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrServicePage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrUserDetailsPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrUserLocationPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrUserRolePage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrCustomerAuditLogsPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrFeeAuditLogsPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.audit.AdminMgrInventoryAuditLogsPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsHomePage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author raonqa
 */
public class AdminManager extends Orms {
	private static AdminManager _instance = null;

	public static AdminManager getInstance() {
		if (null == _instance)
			_instance = new AdminManager();

		return _instance;
	}

	public void loginAdminManager(LoginInfo login, boolean newBrowser) {
		AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();
		login(login,"Admin Manager",newBrowser);
		admHmPg.waitLoading();
	}

	public void loginAdminMgr(LoginInfo login) {
		loginAdminManager(login, true);
	}

	/**
	 * Logout admin manager
	 *
	 */
	public void logoutAdminMgr() {
	  AdmTopMenuPage admTopMenuPg=AdmTopMenuPage.getInstance();
	  OrmsHomePage ormHmPg=OrmsHomePage.getInstance();

	  logger.info("Logout Admin Manager.");

//	  if(admTpMnuPg.exists()){
	    admTopMenuPg.clickLogout();
	    ormHmPg.waitLoading();
//	  }
	}

	/**
	 * Go to Admin manager home page
	 */
	public void gotoHomePage(){
		AdmTopMenuPage topPg = AdmTopMenuPage.getInstance();
		AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();

		logger.info("Go To Home Page.");

		topPg.clickHome();
		admHmPg.waitLoading();
	}

	/**Go to select location page from rule detail page*/
	public void goToSelectLocationPg() {
	  AdmMgrRuleDetailPage admRuleDetPg=AdmMgrRuleDetailPage.getInstance();
	  AdmMgrSelectLocationPage admLocPg=AdmMgrSelectLocationPage.getInstance();

	  logger.info("Go to the select location page.");

	  admRuleDetPg.clickSelectLocation();
	  admLocPg.waitLoading();
	}

	/**
	 * The process of add a new user in admin manager
	 * @param admUser -- the admin user's info want to added
	 */
	public void addNewUser(AdminUserInfo admUser) {
	  AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();
	  AdmMgrUserDetailsPage admUserDetailePg=AdmMgrUserDetailsPage.getInstance();

	  logger.info("Add a new user");

	  admHmPg.clickAddNewUser();
	  admUserDetailePg.waitLoading();
	  admUserDetailePg.setUserInfo(admUser.userName,admUser.password,admUser.confirmPassword,admUser.firstName,admUser.lastName,admUser.middleName,admUser.email,admUser.fax,admUser.address,admUser.province,admUser.zip,admUser.comment,admUser.activeStatus);
	  admUserDetailePg.clickOK();
	  admHmPg.waitLoading();
	}

	/**
	 * Search the user
	 * @param searchTye -- search cetegory
	 * @param searchValue -- search value
	 * @param show -- status
	 */
	public void searchUser(String searchTye,String searchValue,String show) {
	    AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();

	    logger.info("Search the user "+searchValue);
	    
	    if(!searchTye.equals("")) {
	      admHmPg.searchBy(searchTye);
	    }
	    admHmPg.setSearchValue(searchValue);
	    if(!show.equals("")){
	      	admHmPg.selectShow(show);
	    }
	    admHmPg.clickGo();
	    
	    admHmPg.waitLoading();
	}

	/**
	 * lock the user
	 * @param searchby
	 * @param value
	 * @param show
	 */
	public void lockUser(String searchby,String value,String show) {
	     AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();

	     logger.info("lock the user "+value);
	     searchUser(searchby,value,show);
	     admHmPg.selectAll();
	     admHmPg.clickLock();
	     admHmPg.waitLoading();
	}

	/**
	 * Activate or Deactivate User
	 * @param userName
	 * @param activate
	 */
	public void changeUserStatus(String userName,boolean activate){
         AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();

	     logger.info((activate?"Activate":"Deactivate")+" the user "+activate);

	     searchUser("User Name",userName,"*All Users");

	     admHmPg.selectAll();
	     if(activate){
	    	 admHmPg.clickActivate();
	     }else{
	    	 admHmPg.clickDeactivate();
	     }
	     admHmPg.waitLoading();
	}



	/**
	 * goto the user details page
	 * @param userName
	 */
	public void gotoUserDetailesPage(String userName) {
	     AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();
	     AdmMgrUserDetailsPage admUserDetailePg=AdmMgrUserDetailsPage.getInstance();

	     logger.info("goto the user '" + userName + "' details page");
	     searchUser("", userName, "");

	     admHmPg.clickUser(userName);
	     admUserDetailePg.waitLoading();
	}

	/**
	 * Assign location for one user
	 * @param location
	 * @param assign -- true: assign the location, false: unAssign the location
	 */
	public void assignLocation(String location,String level,boolean assign) {
	     AdmMgrUserDetailsPage admUserDetailePg=AdmMgrUserDetailsPage.getInstance();
	     AdmMgrUserLocationPage locPg = AdmMgrUserLocationPage.getInstance();

	     logger.info("Assign the new location '"+location+"'.");
	     admUserDetailePg.clickUserLocation();
	     locPg.waitLoading();

	     locPg.setLocationValue(location);
	     locPg.selectLocationLevel(level);
	     locPg.clickGo();
	     locPg.waitLoading();
	    
	     if(assign) {
	    	 if(!locPg.isLocationAssigned(location)) {
	    		 locPg.selectLocation();
	    		 locPg.clickAssign();
	    	 }
	     }else{
	    	 if(locPg.isLocationAssigned(location)) {
	    		 locPg.selectLocation();
	    		 locPg.clickUnAssign();
	    	 }
	     }

	     locPg.waitLoading();
	}

	/**
	 * Assign the new Role
	 * @param user
	 */
	public void assignRole(AdminUserInfo user) {
	    AdmMgrUserDetailsPage admUserDetailePg=AdmMgrUserDetailsPage.getInstance();
	    AdmMgrUserRolePage rolePg = AdmMgrUserRolePage.getInstance();

	    logger.info("Assign the new role");
	    admUserDetailePg.clickUserRole();
	    rolePg.waitLoading();
	    rolePg.selectRoleName(user.roleName);
	    rolePg.selectLocationName(user.location);
	    rolePg.clickAddRole();

	    rolePg.waitLoading();
	}

	/**
	 * the Process of Add new Role
	 * @param role
	 */
	public void addNewRole(RoleInfo role) {
	    AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();
	    AdmMgrRolePage admRolePg=AdmMgrRolePage.getInstance();
	    AdmMgrRoleDetailsPage admRoleDetailsPg=AdmMgrRoleDetailsPage.getInstance();

	    logger.info("Add new Role");
	    admHmPg.clickRole();
	    admRolePg.waitLoading();

	    admRolePg.clickAddNewRole();
	    admRoleDetailsPg.waitLoading();

	    admRoleDetailsPg.setRoleName(role.roleName);
	    admRoleDetailsPg.setDescription(role.description);

	    admRoleDetailsPg.clickOK();
	    admRolePg.waitLoading();

	    admRolePg.searchRoleName(role.roleName);
	    admRolePg.waitLoading();

	    admRolePg.gotoRoleDetails(role.roleName);
	    admRoleDetailsPg.waitLoading();

	    admRoleDetailsPg.clickRoleApplication();
	    admRoleDetailsPg.waitLoading();

	    admRoleDetailsPg.addRoleApplication(role.application);

	    admRoleDetailsPg.clickRoleFeature();
	    admRoleDetailsPg.waitLoading();

	    admRoleDetailsPg.addRoleFeature(role.feature,role.application);
	    admRoleDetailsPg.waitLoading();
	}

	/**
	 * This method used to assign or unassign a role feature
	 * @param role
	 * @param assign- used to determine assign or un-assign
	 */
	public void assignOrUnAssignRoleFeature(RoleInfo role, boolean assign) {
		AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();
		AdmMgrRolePage admRolePg=AdmMgrRolePage.getInstance();
		AdmMgrRoleDetailsPage roleDetailsPg=AdmMgrRoleDetailsPage.getInstance();
		AdmMgrRoleFeaturePage featurePg = AdmMgrRoleFeaturePage.getInstance();

		String log = assign?"Assign":"UnAssign";
		logger.info(log+" Role Feature - " + role.feature);

		admHmPg.clickRole();
		admRolePg.waitLoading();
		admRolePg.searchRoleName(role.roleName);
		admRolePg.gotoRoleDetails(role.roleName);
		roleDetailsPg.waitLoading();
		roleDetailsPg.clickRoleFeature();
		featurePg.waitLoading();
		featurePg.searchFeature(role.feature,role.featureType,role.application);
		//assign role'feature
		if(assign){
			featurePg.searchFeature(role.feature,"Unassigned features",role.application);
			if(featurePg.getRoleFeaturesNumber()==1){
				featurePg.selectFirstFeature();
				featurePg.clickAssign();
				featurePg.waitLoading();
			}else{
				if(featurePg.getRoleFeaturesNumber()>=2){
					do{
						featurePg.selectAllFeature();
						featurePg.clickAssign();
	                    featurePg.waitLoading();
					}while(featurePg.getRoleFeaturesNumber()>0);
				}else{
					logger.info("No feature to assign");
				}
			}
		}else{
			 //unassign role'feature
			featurePg.searchFeature(role.feature,"Assigned features",role.application);
			if(featurePg.getRoleFeaturesNumber()==1){
				featurePg.selectFirstFeature();
				featurePg.clickUnAssign();
			}else{
				if(featurePg.getRoleFeaturesNumber()>=2){
					do{
						featurePg.selectAllFeature();
						featurePg.clickUnAssign();
	                    featurePg.waitLoading();
					}while(featurePg.getRoleFeaturesNumber()>0);
				}else{
					logger.info("No feature to unassign");
				}
			}
		}

		featurePg.waitLoading();
	}

	/**
	 * This method used to check a role feature
	 * @param role
	 * @param assign- used to determine assign or un-assign
	 */
	public String checkRoleFeature(RoleInfo role, boolean assign) {
		AdmMgrRoleFeaturePage featurePg = AdmMgrRoleFeaturePage.getInstance();

		String log = assign?"Assign":"UnAssign";
		logger.info("Check "+log+" of Role Feature - " + role.feature);

		String result = "";

		//if assign=true, we search 'Unassigned features'. if assign=false, we search 'Assigned features'.
		featurePg.searchFeature(role.feature,(assign?"Unassigned features":"Assigned features"),role.application);
		if(featurePg.getRoleFeaturesNumber()==0)
		{
			result = "All feature "+log+" successfully...";

		}else{
			List<RoleInfo> rs = featurePg.getAllRecordsOnPage();
			result +="Following features are not assigned properly!!!-->";
			
			for(RoleInfo record: rs)
			{
				result +=record.feature+"||";
			}
			logger.info("Failed asssign role feature-->"+result);
			throw new ErrorOnPageException("Assign role feature failed!!!");
		}
		return result;
		
	}
	
	
	/**
	 * Delete the Role
	 * @param roleName
	 */
	public void deleteRole(String roleName) {
	    AdmMgrRolePage admRolePg=AdmMgrRolePage.getInstance();
	    AdmMgrRoleDetailsPage admRoleDetailsPg=AdmMgrRoleDetailsPage.getInstance();

	    logger.info("Delete Role");
	    admRolePg.searchRoleName(roleName);
	    admRolePg.gotoRoleDetails(roleName);
	    admRoleDetailsPg.waitLoading();

	    admRoleDetailsPg.clickDeleteRole();
	    admRolePg.waitLoading();
	}

	/**
	 * Add a service or activity
	 * @param sa -- service or activity info
	 */
	public void addServiceOrActivity(ServiceAndActivity sa) {
	   AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();
	   AdmMgrServicePage admServicePg=AdmMgrServicePage.getInstance();
	   AdmMgrServiceDetailsPage admServiceDetailsPg=AdmMgrServiceDetailsPage.getInstance();

	   logger.info("Add a new service or activity");
	   admHmPg.selectPageName("Services, Activities, Events");
	   admServicePg.waitLoading();

	   admServicePg.addNewService();
	   admServiceDetailsPg.waitLoading();

	   admServiceDetailsPg.setServiceInfo(sa);
	   admServiceDetailsPg.clickOK();

	   admServicePg.waitLoading();
	}

	/**
	 * Delete the service
	 * @param serviceName
	 */
	public void deleteService(String serviceName) {
	  AdmMgrServicePage admServicePg=AdmMgrServicePage.getInstance();
	  ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();

	  logger.info("delete the service "+serviceName);
	  admServicePg.searchService("Name",serviceName);

	  admServicePg.selectServiceCheckbox();
	  admServicePg.clickDelete();
	  browser.waitExists(admServicePg, confirmDialog);
	}

	/**
	 * The process of viewing the event
	 * @param eventID
	 */
	public void viewEvent(String eventID) {
	   AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();
	   AdmMgrServicePage admServicePg=AdmMgrServicePage.getInstance();
	   AdmMgrServiceDetailsPage admServiceDetailsPg=AdmMgrServiceDetailsPage.getInstance();

	   logger.info("View the event");
	   admHmPg.selectPageName("Services, Activities, Events");
	   admServicePg.waitLoading();

	   admServicePg.selectCategory("events");
	   admServicePg.waitLoading();

	   admServicePg.searchBy("ID");
	   admServicePg.setService(eventID);
	   admServicePg.clickSearch();
	   admServicePg.waitLoading();

	   admServicePg.clickEventID(eventID);
	   admServiceDetailsPg.waitLoading();
	}

	/**
	 * Assign new rule to location
	 * @param rule
	 */
	public void assignRule(RuleDataInfo rule) {
	   AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();
	   AdmMgrRuleListPage admRuleListPg=AdmMgrRuleListPage.getInstance();
	   AdmMgrSearchLocationPage admSearchLocationPg=AdmMgrSearchLocationPage.getInstance();
	   AdmMgrRuleDetailPage admRuleDetailsPg=AdmMgrRuleDetailPage.getInstance();

	   logger.info("Assign the new rule");
	   admHmPg.selectPageName("Contract - Rules");
	   admRuleListPg.waitLoading();

	   admRuleListPg.clickRuleName(rule.ruleName);
	   admSearchLocationPg.waitLoading();

	   admSearchLocationPg.searchLocation(rule.location,rule.ruleCategory);
	   admSearchLocationPg.clickSelect();

	   admRuleDetailsPg.waitLoading();
	   admRuleDetailsPg.addNewRule(rule);

	   admRuleDetailsPg.waitLoading();
	}

	/**
	 * Assign or UnAssign one or more role's features
	 * @param roleList
	 * @param roleStatusList,the values must be 'Yes' or 'No'.
	 * Make sure the roleList members and roleStatusList members one-to-one correspondence.
	 */
	public void AssignOrUnassignRoleFeatures(List<RoleInfo> roleList, List<String> roleStatusList){
		AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();
		AdmMgrRolePage admRolePg=AdmMgrRolePage.getInstance();
		AdmMgrRoleDetailsPage roleDetailsPg=AdmMgrRoleDetailsPage.getInstance();
		AdmMgrRoleFeaturePage featurePg = AdmMgrRoleFeaturePage.getInstance();

		logger.info("Go to Role Details page to assign or un-assign role's features");
		admHmPg.clickRole();
		admRolePg.waitLoading();
		for(int i=0; i<roleList.size(); i++){
			admRolePg.searchRoleName(roleList.get(i).roleName);
			admRolePg.gotoRoleDetails(roleList.get(i).roleName);
			roleDetailsPg.waitLoading();
			roleDetailsPg.clickRoleFeature();
			featurePg.waitLoading();

			featurePg.searchFeature(roleList.get(i).feature,roleList.get(i).featureType,roleList.get(i).application);
			do{
				featurePg.selectAllFeature();
				if(((null==roleStatusList?roleList.get(i).unAssignOrNot:roleStatusList.get(i).equals("No"))) && !featurePg.getAssignedStatus().equals(featurePg)){
					featurePg.clickUnAssign();
				}else if(((null==roleStatusList?(!roleList.get(i).unAssignOrNot):roleStatusList.get(i).equals("Yes"))) && !featurePg.getAssignedStatus().equals(featurePg)){
					featurePg.clickAssign();
				}else
					throw new ErrorOnDataException("The values of role status lists members should be 'Yes' or 'No'");

				featurePg.waitLoading();
			} while(featurePg.gotoNext());

			featurePg.clickRole();
			admRolePg.waitLoading();
		}
	}

	public void AssignOrUnassignRoleFeatures(List<RoleInfo> roleList){
		AssignOrUnassignRoleFeatures(roleList, null);
	}
	
	public void gotoGlobalUserListPage(){
		AdmMgrHomePage homePage = AdmMgrHomePage.getInstance();
		AdmMgGlobalUserListPage userListPg = AdmMgGlobalUserListPage.getInstance();
		logger.info("Go to global users list page");
		homePage.selectPageName("Security - Global Users");
		ajax.waitLoading();
		userListPg.waitLoading();
	}
	
	/**
	 * According to tab name, go to differrnt audit log page.
	 *
	 * @param tabName
	 */
	public void gotoAuditLogsPage(String tabName) {
		AdmMgrHomePage homePage = AdmMgrHomePage.getInstance();
		AdminMgrInventoryAuditLogsPage invAuditLogPage = AdminMgrInventoryAuditLogsPage.getInstance();
		
		logger.info("According to tab name, go to differrnt audit log page.");
		homePage.selectPageName("Audit");
		invAuditLogPage.waitLoading();
		
		if(!"Inventory Audit Logs".equals(tabName)) {
			invAuditLogPage.clickAuditLogTab(tabName);
		}
		
		if("Customer Audit Logs".equals(tabName)){
			AdminMgrCustomerAuditLogsPage.getInstance().waitLoading();
		} else if("Fee Audit Logs".equals(tabName)) {
			AdminMgrFeeAuditLogsPage.getInstance().waitLoading();
		}
	}
	
	public void gotoInventoryAuditLogsPage() {
		gotoAuditLogsPage("Inventory Audit Logs");
	}
	
	public void gotoCustomerAuditLogsPage() {
		gotoAuditLogsPage("Customer Audit Logs");
	}
	
	public void gotoFeeAuditLogsPage() {
		gotoAuditLogsPage("Fee Audit Logs");
	}
	
	/**
	 * Go to contract rule page from admin home page
	 */
	public void gotoContractRulesPage(){
		AdmMgrHomePage homePg = AdmMgrHomePage.getInstance();
		AdmMgrRuleListPage admRuleListPg = AdmMgrRuleListPage.getInstance();
		
		logger.info("Go to contract rule page from admin home page.");
		
		homePg.selectContractRules();
		admRuleListPg.waitLoading();
	}
	
	/**
	 * 
	 * @param ruleData
	 */
	public void createNewRule(RuleDataInfo ruleData, Object startPage){
		AdmMgrRuleListPage admRuleListPg = AdmMgrRuleListPage.getInstance();
		AdmMgrSelectLocationPage admLocPg = AdmMgrSelectLocationPage.getInstance();
		AdmMgrRuleDetailPage admRuleDetailPg = AdmMgrRuleDetailPage.getInstance();
		
		if(startPage == admRuleListPg){
			logger.info("Create a new rule of "+ruleData.ruleName+" from admin rule list page.");
			admRuleListPg.enterRuleDetail(ruleData.ruleName);
			admLocPg.waitLoading();
			// Select location
			admLocPg.searchLocation(ruleData.locationCategory, ruleData.location);
			admLocPg.clickSelectButton();
			admRuleDetailPg.waitLoading();
		}else if(startPage == admRuleDetailPg){
			logger.info("Create a new rule of "+ruleData.ruleName+" from admin rule list page.");
			String currLocation = admRuleDetailPg.getCurrentLocationName();
			if(!currLocation.equalsIgnoreCase(ruleData.location)){
				logger.info("Go to rule list page and re-choose location.");
				admRuleDetailPg.clickFindRules();
				admRuleListPg.waitLoading();
				createNewRule(ruleData, admRuleListPg);
			}
		}else{
			throw new ErrorOnDataException("Unhandled start page for create a new rule.");
		}
		
		// Add a new rule
		admRuleDetailPg.addNewRule(ruleData);
		admRuleDetailPg.waitLoading();
	}
	
	/**
	 * Delete rule by id, starts from Admin home page and ends with rule detail page
	 * @param ruleName
	 * @param ruleId
	 * @param location
	 * @param locCategory
	 */
	public void deleteRule(String ruleName, String ruleId, String location, String locCategory) {
		AdmMgrRuleDetailPage ruleDetailPage = AdmMgrRuleDetailPage.getInstance();
		
		logger.info("Delete " + ruleName + " rule(ID=" + ruleId + ").");
		gotoContractRulesPage();
		gotoRuleDetailPage(ruleName, location, locCategory);
		ruleDetailPage.deleteRuleByID(ruleId);
	}
	
	/**
	 * Deactivate rule by id, starts from Admin home page and ends with rule detail page
	 * @param ruleName
	 * @param ruleId
	 * @param location
	 * @param locCategory
	 */
	public void deactivateRule(String ruleName, String ruleId, String location, String locCategory) {
		AdmMgrRuleDetailPage ruleDetailPage = AdmMgrRuleDetailPage.getInstance();
		
		logger.info("Deactivate " + ruleName + " rule(ID=" + ruleId + ").");
		gotoContractRulesPage();
		gotoRuleDetailPage(ruleName, location, locCategory);
		ruleDetailPage.deactivateRuleByID(ruleId);
	}
	
	/**
	 * Go to specific rule details page, startds from rule list page.
	 * @param ruleName
	 * @param location
	 * @param locCategory
	 */
	public void gotoRuleDetailPage(String ruleName, String location, String locCategory) {
		AdmMgrRuleListPage ruleListPage = AdmMgrRuleListPage.getInstance();
		AdmMgrSelectLocationPage selectLocationPage = AdmMgrSelectLocationPage.getInstance();
		AdmMgrRuleDetailPage ruleDetailPage = AdmMgrRuleDetailPage.getInstance();
		
		logger.info("Go to Rule - '" + ruleName + "' detail page.");
		ruleListPage.enterRuleDetail(ruleName);
		selectLocationPage.waitLoading();
		selectLocationPage.searchLocation(locCategory, location);
		selectLocationPage.selectLocation(location);
		ruleDetailPage.waitLoading();
	}
	
	public void gotoRuleListPage() {
		AdmMgrRuleListPage ruleListPage = AdmMgrRuleListPage.getInstance();
		
		ruleListPage.clickFindRule();
		ruleListPage.waitLoading();
	}
	
	public void gotoUserListPage(){
		AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();
		admHmPg.clickUser();
	    admHmPg.waitLoading();
	}
	
	/** Edit product group details from home page */
	public void editProductGroupDetails(ProductGroup prdGrp) {
		AdmMgrHomePage homePg = AdmMgrHomePage.getInstance();
		AdmMgrProductGroupListPage prdGrpListPg = AdmMgrProductGroupListPage.getInstance();
		AdmMgrEditProductGroupWidget prdGrpWidget = AdmMgrEditProductGroupWidget.getInstance();
		logger.info("edit product group details from home page...");
		
		homePg.selectPageName("Contract - Configuration");
		prdGrpListPg.waitLoading();
		prdGrpListPg.searchPrdGrp(prdGrp.getPrdGrpCatgory(), prdGrp.getPrdGrpName(), prdGrp.getShowStatus());
		prdGrpListPg.clickFirstPrdGrp();
		ajax.waitLoading();
		prdGrpWidget.waitLoading();
		prdGrpWidget.setPrdocutGroupDetails(prdGrp);
		prdGrpWidget.clickOK();
		ajax.waitLoading();
		browser.waitExists(prdGrpListPg, prdGrpWidget);
	}
}
