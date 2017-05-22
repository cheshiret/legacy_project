package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin;

import com.activenetwork.qa.awo.datacollection.legacy.orms.AdminUserInfo;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrUserRolePage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AssignRole extends AdminManagerTestCase
{
	/**
	 * Script Name   : <b>AssignRole</b>
	 * Generated     : <b>Feb 27, 2010 4:08:35 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/27
	 * @author mchen
	 */
     public void execute(){
        am.loginAdminMgr(login);
        
        am.gotoUserDetailesPage(admUserInfo.userName);
        am.assignLocation(admUserInfo.location,"Park",true);
        
        am.assignRole(admUserInfo);
        this.verifyAddedRole(admUserInfo);
        this.deleteNewRole();
        
        am.assignLocation(admUserInfo.location,"Park",false);
        
        am.logoutAdminMgr();
     }
     
     public void wrapParameters(Object[] args) {
         login.contract="SC Contract";
         login.url = AwoUtil.getOrmsURL(env);
         login.location="Administrator/South Carolina State Park Service";
       
         admUserInfo.userName="qa-auto-adm";
         admUserInfo.roleName="SC - Field Supervisor - Auto";
         admUserInfo.location="EDISTO BEACH";
     }
     
     public void verifyAddedRole(AdminUserInfo roleInfo){
       AdmMgrUserRolePage rolePg=AdmMgrUserRolePage.getInstance();
       String roleInfos = rolePg.getRoleInfo();
       if(!roleInfos.matches(".*"+roleInfo.roleName + "(| )"+ roleInfo.location+".*")){
           throw new ItemNotFoundException("the role added failed");
       }
     }
     
     public void deleteNewRole(){
    	 AdmMgrUserRolePage rolePg=AdmMgrUserRolePage.getInstance();
    	 rolePg.selectAddedRole();
    	 rolePg.clickDelete();
         
    	 rolePg.waitLoading();
     }
}

