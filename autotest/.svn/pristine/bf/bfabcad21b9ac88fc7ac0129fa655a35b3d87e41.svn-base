package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin;

import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.User;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgGlobalUserListPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrUserDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description:This case is used to verify change user locked status
 * @Precondition:User has the permission of “View Global User”
 * @LinkSetUp:None
 * @SPEC:[TC:062652] Edit Global User 
 * @Task#: Auto-2112
 * @author Phoebe Chen
 * @Date  March 31, 2014
 */
public class VerifyEditUserPermission extends AdminManagerTestCase{
	private RoleInfo role=new RoleInfo();
	private User userSearchCritials = new User();
	private String[] features = {"ViewGlobalUser","ModifyGlobalUser"};
	private AdmMgGlobalUserListPage userListPg = AdmMgGlobalUserListPage.getInstance();
	private AdmMgrUserDetailsPage userDetailPg = AdmMgrUserDetailsPage.getInstance();
	@Override
	public void execute() {
		am.loginAdminMgr(login);
		this.assignOrUnassignFeature(true, false);
		am.logoutAdminMgr();
		
		am.loginAdminManager(login, false);
		am.gotoGlobalUserListPage();
		this.gotoUserDetailPg();
		verifyUIDisplay(false);
		
		this.gotoHomePageFromTopDropDownList();
		this.assignOrUnassignFeature(true, true);
		am.logoutAdminMgr();
		
		am.loginAdminManager(login, false);
		am.gotoGlobalUserListPage();
		this.gotoUserDetailPg();
		verifyUIDisplay(true);
		
		am.logoutAdminMgr();
	}

	private void verifyUIDisplay(boolean featureAssigned) {
		boolean passed = true;
		boolean expectStatus = featureAssigned;
		passed = MiscFunctions.compareResult("Password editable", expectStatus, userDetailPg.isPasswordEditable());
		passed = MiscFunctions.compareResult("Confirm password editale", expectStatus, userDetailPg.isConfirmPasswordEditable());
		passed = MiscFunctions.compareResult("First name editable", expectStatus, userDetailPg.isFirstNameEditable());
		passed = MiscFunctions.compareResult("Last name editable", expectStatus, userDetailPg.isLastNameEditable());
		passed = MiscFunctions.compareResult("Phone editable", expectStatus, userDetailPg.isPhoneEditable());
		passed = MiscFunctions.compareResult("Fax editable", expectStatus, userDetailPg.isFaxEditable());
		passed = MiscFunctions.compareResult("Email editable", expectStatus, userDetailPg.isEmailEditable());
		passed = MiscFunctions.compareResult("Address editable", expectStatus, userDetailPg.isAddressEditable());
		passed = MiscFunctions.compareResult("State editable", expectStatus, userDetailPg.isStateEditalble());
		passed = MiscFunctions.compareResult("Zip/Postal code editable", expectStatus, userDetailPg.isZipCodeEditable());
		if(!passed){
			throw new ErrorOnPageException("The item editable status is not correct");
		}
		logger.info("The item editable status is correct!");
	}

	@Override
	public void wrapParameters(Object[] param) {
		 login.contract="RA Contract";
	     login.url = AwoUtil.getOrmsURL(env);
	     login.location="Administrator - Auto/RA Contract";
	     
	     userSearchCritials.userName = "ra-pchen";
	     
		role.roleName = "Administrator - Auto";
		role.application = "AdminManager";
	}
	
	private void assignOrUnassignFeature(boolean assignView, boolean assignModify) {
		//Goto Admin Manager to unassigned all the feature
		role.feature = features[0];
		am.assignOrUnAssignRoleFeature(role, assignView);
		role.feature = features[1];
		am.assignOrUnAssignRoleFeature(role, assignModify); 
	}
	
	private void gotoUserDetailPg(){
		userListPg.searchUser(userSearchCritials);
		userListPg.clickUserName(userSearchCritials.userName);
		ajax.waitLoading();
		userDetailPg.waitLoading();
	}
	
	public void gotoHomePageFromTopDropDownList(){
		AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();
		logger.info("Go to user,role and location page");
		admHmPg.selectPageName("Security - Users, Roles, and Locations");
		ajax.waitLoading();
		admHmPg.waitLoading();
	}

}
