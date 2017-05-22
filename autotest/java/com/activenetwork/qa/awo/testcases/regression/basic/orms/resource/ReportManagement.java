package com.activenetwork.qa.awo.testcases.regression.basic.orms.resource;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRoleFeaturePage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author Ssong
 */
public class ReportManagement extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>ReportManagement</b>
	 * Generated     : <b>Mar 8, 2010 12:22:40 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/08
	 * @author Ssong
	 */
	private LoginInfo loginAm = new LoginInfo();
	private AdminManager adm = AdminManager.getInstance();
	private RoleInfo role = new RoleInfo();
	private	AdmMgrRoleFeaturePage featurePg = AdmMgrRoleFeaturePage.getInstance();
	
	public void execute() {
		//login admin manager and assign report management feature
		adm.loginAdminMgr(loginAm);
		adm.assignOrUnAssignRoleFeature(role,true);
		this.getUnAssignedReport();
		adm.logoutAdminMgr();
		
		//login resource manager and check a un-assign report exist
		rm.loginResourceManager(login,false);
		rm.gotoReportPage();
		//after assign report managerment feature,unassigned report should be display
		if(!rm.checkReportExists(rd.reportName)){
			throw new ErrorOnPageException(rd.reportName+" not exists.");  
		}
		rm.logoutResourceManager();
		
		//login admin manager and un-assign report management feature
		adm.loginAdminManager(loginAm,false);
		adm.assignOrUnAssignRoleFeature(role,false);
		String assignReport = this.getAssignedReport();
		adm.logoutAdminMgr();
	
		rm.loginResourceManager(login,false);
		rm.gotoReportPage();
		//after un-assign report managerment feature,unassigned report should not display
		if(rm.checkReportExists(rd.reportName)){
			throw new ErrorOnPageException(rd.reportName+" should not exists.");  
		}
		//assigned report should be display
		if(!rm.checkReportExists(assignReport)){
			throw new ErrorOnPageException(assignReport+" not exists.");  
		}
		rm.logoutResourceManager();
		
		//restore environment to default status
		adm.loginAdminManager(loginAm,false);
		adm.assignOrUnAssignRoleFeature(role,true);
		adm.logoutAdminMgr();
	}
	
	private void getUnAssignedReport()
	{
		featurePg.searchFeature("%Report","Unassigned features",role.application);
		rd.reportName = featurePg.getFeatureDescription();		
	}
	
	private String getAssignedReport()
	{
		featurePg.searchFeature("%Report","Assigned features",role.application);
		return featurePg.getFeatureDescription();		
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "RI Contract";
		login.location = "Administrator/State of Rhode Island Contract";
		
		loginAm.url = AwoUtil.getOrmsURL(env);
		loginAm.userName = TestProperty.getProperty("orms.adm.user");
		loginAm.password = TestProperty.getProperty("orms.adm.pw");
		loginAm.contract = "RI Contract";
		loginAm.location = "Administrator/State of Rhode Island Contract";

		//initialize role data
		role.roleName = "Administrator - Auto";
		role.feature = "ReportManagement";
		role.application = "ResourceManager";
	}
}

