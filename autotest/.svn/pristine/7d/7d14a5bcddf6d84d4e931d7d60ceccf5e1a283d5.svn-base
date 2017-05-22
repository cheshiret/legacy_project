package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.lotteryapplicationdetailreport.resource;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRoleFeaturePage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class VerifyReportPermission_1 extends ResourceManagerTestCase{

	private LoginInfo loginAm = new LoginInfo();
	private AdminManager adm = AdminManager.getInstance();
	private RoleInfo role = new RoleInfo();
	private	AdmMgrRoleFeaturePage featurePg = AdmMgrRoleFeaturePage.getInstance();
	
	public void execute() {
		//login admin manager and assign report feature
		adm.loginAdminMgr(loginAm);
		adm.assignOrUnAssignRoleFeature(role,false);
		featurePg.assignOrUnassignFeature("LotteryApplicationDetailsReport", role.application, true);
		adm.logoutAdminMgr();
		
		//login resource manager
		rm.loginResourceManager(login,false);
		rm.gotoReportPage();
		//verify assigned report should be display
		if(!rm.checkReportExists(rd.reportName)){
			throw new ErrorOnPageException(rd.reportName+" not exists.");  
		}
		//logout resource manager
		rm.logoutResourceManager();	
		
		//clean up to restore the report feature
		adm.loginAdminManager(loginAm,false);
		adm.assignOrUnAssignRoleFeature(role,true);
		adm.logoutAdminMgr();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NY Contract";
		login.location = "Administrator/Contract";
		
		loginAm.url = AwoUtil.getOrmsURL(env);
		loginAm.userName = TestProperty.getProperty("orms.adm.user");
		loginAm.password = TestProperty.getProperty("orms.adm.pw");
		loginAm.contract = "NY Contract";
		loginAm.location = "Administrator/Contract";
		
		//initialize role data
		role.roleName = "Administrator";
		role.feature = "ReportManagement";
		role.application = "ResourceManager";
		
		rd.reportName = "Lottery Application Details Report";
	}
}
