package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.user;

import com.activenetwork.qa.awo.datacollection.legacy.orms.User;
import com.activenetwork.qa.awo.pages.orms.licenseManager.user.LicMgrUserDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.user.LicMgrUserListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify editing user correctly
 * @Preconditions: 'User' related features have been assigned
 * @SPEC: Edit User [TC:025088]
 * @Task#: AUTO-1270
 * 
 * @author qchen
 * @Date  Sep 13, 2012
 */
public class Edit extends LicenseManagerTestCase {

	private User user = new User();
	private LicMgrUserListPage userListPage = LicMgrUserListPage.getInstance();
	private String EXPECTED_LAST_PASSWORD_CHANGE_DATE;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoUserListPageFromTopMenu();
		//add a new User
		user.userName = lm.createUser(user);
		
		//edit user
		this.initialEditUserInfo();
		lm.editUser(user);//edit 'User Must Change Password At Next Login' from unselecting to selecting
		
		//verify User list info
		userListPage.searchUser("User Name", user.userName);
		this.verifyUserListInfo(user);
		
		//verify details info
		lm.gotoUserDetailsPageFromListPage(user.userName);
		this.verifyUserDetailsInfo(user);
		String lastPwChangeDateInDB = lm.getUserLastPasswordChangeDate(schema, user.userName);
		this.verifyLastPasswordChangeDate(EXPECTED_LAST_PASSWORD_CHANGE_DATE, lastPwChangeDateInDB);
		
		//clean up
		lm.deleteUser();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		user.isActive = true;
		user.password = "Auto1234";
		user.isUserMustChangePasswordAtNextLogin = false;
		user.confirmPassword = user.password;
		user.fName = "Edit_before";
		user.lName = "User_before";
		user.phone = "02968685958";
		user.fax = "029686859583";
		user.email = "Hunting.Fishing@activenetwork.com";
		user.address = "Mississauga";
		user.state = "Mississippi";
		user.zip = "L5NM8H";
	}
	
	private void initialEditUserInfo() {
		user.isActive = false;
		user.password = StringUtil.EMPTY;
		user.isUserMustChangePasswordAtNextLogin = true;
		user.confirmPassword = StringUtil.EMPTY;
		user.fName = "Edit_after";
		user.lName = "User_after";
		user.phone = "02968685959";
		user.fax = "029686859584";
		user.email = "Fishing.Hunting@activenetwork.com";
		user.address = "Toronto";
		user.state = "Colorado";
		user.zip = "L5NM8I";
		
		//[current day - Max Password Age] = [current day - 90], the Max Password Age is hard-coded, will be future configurable
		EXPECTED_LAST_PASSWORD_CHANGE_DATE = DateFunctions.getDateAfterGivenDay(DateFunctions.formatDate(DateFunctions.getCurrentDate(null), "yyyy/M/d"), -90);
	}
	
	private void verifyUserListInfo(User expected) {
		logger.info("Verify User(UserName=" + expected.userName + ") list info.");
		
		boolean result = userListPage.compareUserInfo(expected);
		if(!result) {
			throw new ErrorOnPageException("User list info is incorrect.");
		} else logger.info("User list info is correct.");
	}
	
	private void verifyUserDetailsInfo(User expected) {
		logger.info("Verify User(UserName=" + expected.userName + ") details info.");
		
		boolean result = LicMgrUserDetailsPage.getInstance().compareUserInfo(expected);
		if(!result) {
			throw new ErrorOnPageException("User details info is incorrect.");
		} else logger.info("User details info is correct.");
	}
	
	private void verifyLastPasswordChangeDate(String expected, String actual) {
		if(DateFunctions.compareDates(expected, actual) != 0) {
			throw new ErrorOnDataException("The user Last Password Change Date is incorrect, expected is: " + expected + ", but actual in DB is: " + actual);
		} else logger.info("The user Last Password Change Date is correct.");
	}
}
