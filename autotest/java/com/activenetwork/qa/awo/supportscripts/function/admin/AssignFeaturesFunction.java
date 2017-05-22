/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.admin;



import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;

public class AssignFeaturesFunction extends FunctionCase {

	private AdminManager am = AdminManager.getInstance();
	private LoginInfo login;
	private boolean isLoggedIn = false;
	private String loggedInContract = "";
	private RoleInfo role;

	public void wrapParameters(Object[] param) {
		this.login = (LoginInfo)param[0];
		this.role = (RoleInfo)param[1];		
	}

	public void execute() {
		if(!loggedInContract.equalsIgnoreCase(login.contract) && isLoggedIn && isBrowserOpened) {
			am.switchToContract(login.contract, login.location);
		}
		
		if(!isLoggedIn || !isBrowserOpened) {
			am.loginAdminMgr(login);
			isLoggedIn = true;
		}
		loggedInContract = login.contract;
		
		// Assign all voucher feature for finance manager
		am.assignOrUnAssignRoleFeature(role, !role.unAssignOrNot);
		
		newAddValue = am.checkRoleFeature(role, !role.unAssignOrNot);	
		
	}
	
}
