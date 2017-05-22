package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.user;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.User;
import com.activenetwork.qa.awo.pages.orms.licenseManager.user.LicMgrUserListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the searching functionality works correctly in User Search/List page
 * @Preconditions: 'User' related features should be assigned
 * @SPEC: Search User [TC:025089]
 * @Task#: AUTO-1270
 * 
 * @author qchen
 * @Date  Sep 14, 2012
 */
public class Search extends LicenseManagerTestCase {
	
	private User user = new User();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoUserListPageFromTopMenu();
		//create a new user
		user.userName = lm.createUser(user);
		
		//assign a role/location
		lm.gotoUserDetailsPageFromListPage(user.userName);
		lm.assignRoleLocation(user.roles, user.locations);
		lm.gotoUserListPageFromDetailsPage();
		
		//verify the search functionality
		this.verifyUserSearchResult();
		
		//clean up
		lm.deleteUser(user.userName);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		user.isActive = true;
		user.password = "Auto1234";
		user.isUserMustChangePasswordAtNextLogin = false;
		user.confirmPassword = user.password;
		user.fName = "Search";
		user.lName = "theUser";
		user.phone = "02968685958";
		user.fax = "029686859584";
		user.email = "Hunting.Fishing@activenetwork.com";
		user.address = "Mississauga";
		user.state = "Mississippi";
		user.zip = "L5NM8H";
		
		user.roles = new String[]{"HF HQ Role - Auto"};
		user.locations = new String[]{"WAL-MART"};
		user.numOfRoles = user.roles.length;
		user.numOfLocations = user.locations.length;
	}
	
	private void verifyUserSearchResult() {
		LicMgrUserListPage listPage = LicMgrUserListPage.getInstance();
		
		String searchBy = "";
		logger.info("Verify the User searching result is correct.");
		//search by User Name
		searchBy = "User Name";
		listPage.searchUser(searchBy, user.userName);
		listPage.verifySearchResult(searchBy, user.userName);
		
		//search by First Name
		searchBy = "First Name";
		listPage.searchUser(searchBy, user.fName);
		listPage.verifySearchResult(searchBy, user.fName);
		
		//search by Last Name
		searchBy = "Last Name";
		listPage.searchUser(searchBy, user.lName);
		listPage.verifySearchResult(searchBy, user.lName);
		
		//search by status, but combined with First Name
		searchBy = "Active";
		String status = user.isActive ? OrmsConstants.ACTIVE_STATUS : OrmsConstants.INACTIVE_STATUS;
		listPage.searchUser("First Name", user.fName, status, null);
		listPage.verifySearchResult(searchBy, status);
		
		//search by 'Assigned with Role' and verify it exists by user name
		listPage.searchUser(null, null, null, user.roles[0]);
		searchBy = "User Name";
		listPage.verifySearchResult(searchBy, user.userName);
		
		//search user and verify the numOfLocations and numOfRoles
		searchBy = "User Name";
		listPage.searchUser(searchBy, user.userName);
		listPage.verifySearchResult("Locations", String.valueOf(user.numOfLocations));
		listPage.verifySearchResult("Roles", String.valueOf(user.numOfRoles));
	}
}
