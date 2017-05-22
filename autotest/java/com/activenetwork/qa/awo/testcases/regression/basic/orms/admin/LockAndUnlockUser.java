package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin;

import com.activenetwork.qa.awo.datacollection.legacy.orms.User;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgGlobalUserListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
/*User has the permission of “View Global User”*/
/**
 * @Description:This case is used to verify change user locked status
 * @Precondition:User has the permission of “View Global User”
 * @LinkSetUp:None
 * @SPEC:[TC:062570] Search Global User-UI display 
 * @Task#: Auto-2112
 * @author Phoebe Chen
 * @Date  March 31, 2014
 */
public class LockAndUnlockUser extends AdminManagerTestCase{
	private User userSearchCritials = new User();
	private AdmMgGlobalUserListPage userListPg = AdmMgGlobalUserListPage.getInstance();
	@Override
	public void execute() {
		am.loginAdminMgr(login);
		am.gotoGlobalUserListPage();
		
		userListPg.searchUser(userSearchCritials);
		boolean isUserLocked = userListPg.isUserLocked(userSearchCritials.userName);
		boolean changedLockedStatus = isUserLocked?false:true;
		
		//Change user locked status
		this.lockOrUnlockUser(changedLockedStatus);
		userListPg.verifyUserLockedStatus(userSearchCritials.userName, changedLockedStatus);
		
		//Change user locked status back
		this.lockOrUnlockUser(isUserLocked);
		userListPg.verifyUserLockedStatus(userSearchCritials.userName, isUserLocked);
		
		am.logoutAdminMgr();
	}
	
	

	private void lockOrUnlockUser(boolean locked) {
		userListPg.selectCheckBoxBeforUser(userSearchCritials.userName);
		if(locked){
			userListPg.clickLock();
		}else{
			userListPg.clickUnlock();
		}
		ajax.waitLoading();
		userListPg.waitLoading();
	}



	@Override
	public void wrapParameters(Object[] param) {
		 login.contract="RA Contract";
	     login.url = AwoUtil.getOrmsURL(env);
	     login.location="Administrator - Auto/RA Contract";
	     
	     userSearchCritials.userName = "ra-nding";
	}

}
