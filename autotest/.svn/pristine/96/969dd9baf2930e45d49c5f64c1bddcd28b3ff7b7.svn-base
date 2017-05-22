package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin;

import com.activenetwork.qa.awo.datacollection.legacy.orms.User;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgGlobalUserListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/*User has the permission of “View Global User”*/
/**
 * @Description:This case is used to verify search in global user list and view
 * @Precondition:User has the permission of “View Global User”
 * @LinkSetUp:None
 * @SPEC:[TC:062694] Search Global User by conditions combined 
 * 		 [TC:062570] Search Global User-UI display 
 * 		 [TC:062684] Search Global User-no result found 
 *       [TC:062685] Search Global User by User Name 
 *       [TC:062686] Search Global User by Last Name 
 *       [TC:062687] Search Global User by First Name 
 *       [TC:062688] Search Global User by Locked Status 
 *       [TC:062689] Search Global User by Contract 
 *       [TC:062690] Search Global User by Active Status 
 *       [TC:062691] Search Global User by Scope
 *       [TC:062693] Search Global User by Email Address 
 *       [TC:062694] Search Global User by conditions combined 
 * @Task#: Auto-2112
 * @author Phoebe Chen
 * @Date  March 31, 2014
 */
public class SearchGlobalUser extends AdminManagerTestCase{
	private User userSearchCritials = new User();
	private AdmMgGlobalUserListPage userListPg = AdmMgGlobalUserListPage.getInstance();
	private String expMsg;
	@Override
	public void execute() {
		am.loginAdminMgr(login);
		am.gotoGlobalUserListPage();
		//Search user by user name
		userSearchCritials.userName = "ra-pchen";
		userListPg.searchUser(userSearchCritials);
		userListPg.verifyColumnValue(userSearchCritials.userName, "User Name");
		
		//Search user by first name
		this.setUserToBlank();
		userSearchCritials.fName = "Saranac Lake Islands";
		userListPg.searchUser(userSearchCritials);
		userListPg.verifyColumnValue(".*"+userSearchCritials.fName + ".*", "First Name");
		
		//Search user by last name
		this.setUserToBlank();
		userSearchCritials.lName = "Haltiwanger";
		userListPg.searchUser(userSearchCritials);
		userListPg.verifyColumnValue(userSearchCritials.lName, "Last Name");
		
		//Search user by email
		this.setUserToBlank();
		userSearchCritials.email = "jasmine.wang@activenetwork.com";
		userListPg.searchUser(userSearchCritials);
		userListPg.verifyColumnValue(userSearchCritials.email, "Email Address");
		
		this.setUserToBlank();
		userSearchCritials.lockedStatus = "Locked";
		userSearchCritials.activeStatus = ACTIVE_STATUS;
		userSearchCritials.contract = "North Carolina State Parks";
		userSearchCritials.scope = "Internal";
		userListPg.searchUser(userSearchCritials);
		userListPg.verifyColumnValue(userSearchCritials.activeStatus.equals(ACTIVE_STATUS)?"Yes":"No", "Active");	
		userListPg.verifyColumnValue(userSearchCritials.lockedStatus.equals("Locked")?"Yes":"No", "Locked?");	
		userListPg.verifyColumnValue(userSearchCritials.contract, "Contract");
		userListPg.verifyColumnValue(userSearchCritials.scope, "Scope");
		
		//Search a user not exist
		this.setUserToBlank();
		userSearchCritials.email = "emailNotExist@activenetwork.com";
		userListPg.searchUser(userSearchCritials);
		verifyMessage();
		
		am.logoutAdminMgr();
	}

	private void verifyMessage() {
		String actMsg = userListPg.getMessage();
		if(!actMsg.matches(expMsg)){
			throw new ErrorOnPageException("The message is not correct", expMsg, actMsg);
		}
		logger.info("Message for no result found is correct!");
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		 login.contract="RA Contract";
	     login.url = AwoUtil.getOrmsURL(env);
	     login.location="Administrator - Auto/RA Contract";
	     
	     expMsg = "No User Accounts found for search criteria.";
	}
	
	private void setUserToBlank(){
		userSearchCritials.userName = "";
		userSearchCritials.fName = "";
		userSearchCritials.lName = "";
		userSearchCritials.activeStatus = "";
		userSearchCritials.lockedStatus = "";
		userSearchCritials.contract = "";
		userSearchCritials.scope = "";
		userSearchCritials.email = "";
	}

}
