/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.maintenanceapps.admindo;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AdminUserInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrHomePage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.WebMaintenanceAppSignInPage;
import com.activenetwork.qa.awo.testcases.abstractcases.web.AdminDoTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Verify the message when sign in Admin.do with an account with expired password
 * @Preconditions:
 * @SPEC:
 * Sign in with a valid account but password expired [TC:016739]
 * @Task#: Auto-1402
 * 
 * @author Lesley Wang
 * @Date  Jan 7, 2013
 */
public class VerifyPasswordExpiredErrMessage extends AdminDoTestCase {

	private WebMaintenanceAppSignInPage signInPg = WebMaintenanceAppSignInPage.getInstance();
	private AdminUserInfo admUserInfo = new AdminUserInfo();
	
	public void execute() {
		// Go to admin manager to prepare a user account
		this.prepareUserAccountInAdminMgr();
		
		// Change the account password to expire in DB
		ma.updateOrmsUserPasswordChangeDate(schema, admUserInfo.userName, DateFunctions.getDateAfterToday(-365));
		
		// Login in Admin.do with the account and check the error message
		ma.invokeURL(url, false, false);
		ma.signIn(admUserInfo.userName, admUserInfo.password);
		signInPg.verifyTopErrMsg(signInPg.topMsg_pwExpired, "Password Expired message");
		
		// Change the account password not expired in DB
		ma.updateOrmsUserPasswordChangeDate(schema, admUserInfo.userName, DateFunctions.getToday());
		
		// Login in Admin.do with the account successfully
		ma.invokeURL(url, false, false);
		ma.signIn(admUserInfo.userName, admUserInfo.password);
		ma.logOutFromAdminDo();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		// Login info for Admin Manager
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.adm.user");
		login.password = TestProperty.getProperty("orms.adm.pw");
		login.contract = "RA Contract";
		login.location="Administrator/RA Contract";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "RA";

		// added user info for login in Admin.do
		admUserInfo.userName="AdminExpiredUserTest";
        admUserInfo.password="test1234";
        admUserInfo.confirmPassword="test1234";
        admUserInfo.firstName="auto";
        admUserInfo.lastName="test";
        admUserInfo.activeStatus=true;
        admUserInfo.location=login.contract;
        admUserInfo.roleName="Admin.do User";
    }
	
	/**
	 * Add a new user account and assign role/location in Admin Manager
	 */
	private void prepareUserAccountInAdminMgr() {
		AdminManager admin = AdminManager.getInstance();
		AdmMgrHomePage admHmPg = AdmMgrHomePage.getInstance();
		
		admin.loginAdminMgr(login);
		admin.searchUser("", admUserInfo.userName, "");
		if (!admHmPg.checkUserExist(admUserInfo.userName)) {
			// Add the account if not exist
			admin.addNewUser(admUserInfo);
		}
		admin.gotoUserDetailesPage(admUserInfo.userName);
		admin.assignLocation(admUserInfo.location, "Contract", true);
		admin.assignRole(admUserInfo);
		admin.logoutAdminMgr();
	}
}
