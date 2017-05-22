package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.adhocticketingordercubereport.resource.reportpermission;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.ResourceManager;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class RMReportPermission_3 extends AdminManagerTestCase{
	List<RoleInfo> roleList = new ArrayList<RoleInfo>();
	List<String> roleStatus = new ArrayList<String>();
	RoleInfo role_1 = new RoleInfo();
	ResourceManager rm = ResourceManager.getInstance();
	LoginInfo loginRM = new LoginInfo();
    String reportName = "Ad hoc Ticketing Order Cube Report";
    
    public void execute(){
 		 //login admin manager and UnAssign the feature 'AdhocTicketingCubeReport' and UnAssign feature 'ReportManagement'
   	    am.loginAdminMgr(login);
   	    am.AssignOrUnassignRoleFeatures(roleList, roleStatus);
   	    am.logoutAdminMgr();
   	    
   	    //login resource manager and check the 'Ad hoc Ticketing Order Cube Report' is not in the 'Select Report' 
   	    rm.loginResourceManager(loginRM);
   	    rm.gotoReportPage();
   	    this.checkReportExistOrNot(reportName, false);
   	    rm.logoutResourceManager();
   	    
		 //login admin manager and Assign the feature 'ReportManagement'
   	    am.loginAdminMgr(login);
   	    roleList.clear();
   	    roleList.add(role_1);
   	    roleStatus.clear();
   	    roleStatus.add("Yes");
   	    am.AssignOrUnassignRoleFeatures(roleList, roleStatus);
   	    am.logoutAdminMgr();
   	 }
   	 
   	 public void wrapParameters(Object[] args) {
   		 //login information for Admin Manager
   		 login.contract="ELS Contract";
 	     login.url = AwoUtil.getOrmsURL(env);
 	     login.location="Administrator/ELS";
 	     
 		 role.roleName = "Administrator - Auto";
		 role.feature = "AdhocTicketingCubeReport"; 
		 role.application = "ResourceManager";
		 roleList.add(role);
		 roleStatus.add("No");
		
		 role_1.roleName = "Administrator - Auto";
		 role_1.feature = "ReportManagement";
		 role_1.application = "ResourceManager";
		 roleList.add(role_1);
		 roleStatus.add("No");
		 
		 //login information for Resource Manager
		 loginRM.url = AwoUtil.getOrmsURL(env);
		 loginRM.userName = TestProperty.getProperty("orms.rm.user");
		 loginRM.password = TestProperty.getProperty("orms.rm.pw");
		 loginRM.contract = "ELS Contract";
		 loginRM.location = "Administrator/ELS";
   	 }
   	 
   	 //Check the report name exist in 'Select Report' list or not
   	 public void checkReportExistOrNot(String reporName, boolean existExpect){		
		boolean exist = rm.checkReportExists(reporName);
		if(existExpect && !exist){
			throw new ErrorOnDataException("The 'Select Report' list should include the specific report name.");
		}else if(!existExpect && exist){
			throw new ErrorOnDataException("The 'Select Report' list should not include the specific report name.");
		}
   	 }
}
