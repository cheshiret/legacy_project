package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.user;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.User;
import com.activenetwork.qa.awo.datacollection.legacy.orms.User.UserRoleLocationAssignment;
import com.activenetwork.qa.awo.pages.orms.licenseManager.user.LicMgrUserRolesLocationsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description: verify the assigning/viewing Role/Location to User functionality works correctly
 * @Preconditions: 'User' related features have been assigned
 * @SPEC: Assign User Roles [TC:025086], View User Roles [TC:025091]
 * @Task#: AUTO-1270
 * 
 * @author qchen
 * @Date  Sep 25, 2012
 */
public class AssignRoleLocation extends LicenseManagerTestCase {
	
	private User user = new User();
	private LicMgrUserRolesLocationsPage roleLocationPage = LicMgrUserRolesLocationsPage.getInstance();
	private List<UserRoleLocationAssignment> expected = new ArrayList<User.UserRoleLocationAssignment>();
	private String warningMsg;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoUserListPageFromTopMenu();
		//create a new user
		user.userName = lm.createUser(user);
		
		//assign role/location
		lm.gotoUserDetailsPageFromListPage(user.userName);
		lm.assignRoleLocation(user.roles, user.locations);
		
		//verify the user-role/location assignment list info
		this.verifyUserRoleLocationAssignmentInfo(expected);
		
		//clean up
		roleLocationPage.deleteUserRoleLocationAssignment(expected.toArray(new UserRoleLocationAssignment[0]));
		this.verifyWariningMsgExists(warningMsg);
		lm.deleteUser();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator - Auto/Mississippi Department of Wildlife, Fisheries, and Parks";//role must be 'Administrator' not 'HF Administrator'
		
		user.isActive = true;
		user.password = "Auto1234";
		user.isUserMustChangePasswordAtNextLogin = false;
		user.confirmPassword = user.password;
		user.fName = "Assign";
		user.lName = "RoleLocation";
		user.phone = "02968685958";
		user.fax = "029686859585";
		user.email = "Hunting.Fishing@activenetwork.com";
		user.address = "Mississauga";
		user.state = "Mississippi";
		user.zip = "L5NM8H";
		user.roles = new String[]{"HF Administrator", "HF HQ Role - Auto"};//if these 2 roles don't exist in the Role dropdown list, need to assign them to 'HF Administrator - Auto' from 'Assignable Roles' tab in ADM
		user.locations = new String[]{"Mississippi Department of Wildlife, Fisheries, and Parks", "WAL-MART"};
		
		//expected role-location assignment info
		UserRoleLocationAssignment assignment1 = user.new UserRoleLocationAssignment();
		assignment1.role = user.roles[0];
		assignment1.location = user.locations[0];
		assignment1.locationLevel = "Contract";
		UserRoleLocationAssignment assignment2 = user.new UserRoleLocationAssignment();
		assignment2.role = user.roles[1];
		assignment2.location = user.locations[1];
		assignment2.locationLevel = "Facility";
		expected.add(assignment1);
		expected.add(assignment2);
		
		warningMsg = "No roles found.";
	}
	
	private void verifyUserRoleLocationAssignmentInfo(List<UserRoleLocationAssignment> expected) {
		logger.info("Verify the User - Role/Location assignment info.");
		
		boolean result = roleLocationPage.compareUserRoleLocationAssignment(expected);
		if(!result) {
			throw new ErrorOnPageException("User - Role/Location assignment info is incorrect.");
		} else logger.info("User - Role/Location assignment info is correct.");
	}
	
	private void verifyWariningMsgExists(String expected) {
		String actual = roleLocationPage.getWarningMessage();
		if(!expected.equals(actual)) {
			throw new ErrorOnPageException("Warning message doesn't macth, expected is: " + expected + ", but actual is: " + actual);
		} else logger.info("Warning message is correct.");
	}
}
