/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.resource;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This test case is to verify the Daily Sales Activity Breakdown by Customer Report shall not be triggered through Resource Manager regardless the permission have or not. 
 * @Preconditions:
 * @SPEC:[TC:042426]
 * @Task#:Auto-1157
 * 
 * @author ssong
 * @Date  Aug 7, 2012
 */
public class DailySalesActivityBreakdownReport_TriggerInRM extends ResourceManagerTestCase{

	private LoginInfo loginAm = new LoginInfo();
	private AdminManager adm = AdminManager.getInstance();
	private RoleInfo role = new RoleInfo();
	private String reportName = "Daily Sales Activity Breakdown by Customer Report";
	
	@Override
	public void execute() {
		adm.loginAdminMgr(loginAm);
		adm.assignOrUnAssignRoleFeature(role,true);
		adm.logoutAdminMgr();
		
		//login resource manager and check report exist or not
		rm.loginResourceManager(login,false);
		rm.gotoReportPage();
		//after assign report management feature,check report exists
		if(rm.checkReportExists(reportName)){
			throw new ErrorOnPageException(reportName+" should not exists in Resource Manager.");  
		}
		rm.logoutResourceManager();
		
		adm.loginAdminManager(loginAm, false);
		adm.assignOrUnAssignRoleFeature(role,false);
		changeRolesInfo();
		adm.assignOrUnAssignRoleFeature(role,true);
		adm.logoutAdminMgr();
		
		rm.loginResourceManager(login,false);
		rm.gotoReportPage();
		//after assign report management feature,check report exists
		if(rm.checkReportExists(reportName)){
			throw new ErrorOnPageException(reportName+" should not exists in Resource Manager.");  
		}
		rm.logoutResourceManager();
	}
	
	private void changeRolesInfo(){
		role.roleName = "Administrator";
		role.feature = "DailySalesActivityBreakdownByCustomerReport";
		role.application = "ResourceManager";
	}

	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "ID Contract";
		login.location = "Administrator/Idaho Contract";
		
		loginAm.url = AwoUtil.getOrmsURL(env);
		loginAm.userName = TestProperty.getProperty("orms.adm.user");
		loginAm.password = TestProperty.getProperty("orms.adm.pw");
		loginAm.contract = "ID Contract";
		loginAm.location = "Administrator/Idaho Contract";

		//initialize role data
		role.roleName = "Administrator";
		role.feature = "ReportManagement";
		role.application = "ResourceManager";
		
	}

}
