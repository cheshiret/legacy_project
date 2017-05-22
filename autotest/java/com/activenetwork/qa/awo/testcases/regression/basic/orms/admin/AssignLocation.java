package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin;

import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrUserLocationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AssignLocation extends AdminManagerTestCase
{
	/**
	 * Script Name   : <b>AssignLocation</b>
	 * Generated     : <b>Feb 27, 2010 2:48:59 AM</b>
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
        this.verifyAssigned(admUserInfo.location);
        
        am.assignLocation(admUserInfo.location,"Park",false);
        am.logoutAdminMgr();
       
     }
     
     public void wrapParameters(Object[] args) {
        login.contract="SC Contract";
        login.url = AwoUtil.getOrmsURL(env);
        login.location="Administrator/South Carolina State Park Service";
        
        admUserInfo.userName="qa-auto-adm";
        admUserInfo.location="GIVHANS FERRY";
     }
     
     public void verifyAssigned(String location){
        AdmMgrUserLocationPage userLocPg=AdmMgrUserLocationPage.getInstance();
        
        userLocPg.setLocationValue("");
        userLocPg.selectLocationShow("Assigned Locations");
        userLocPg.clickGo();
        
        userLocPg.waitLoading();
        
        if(!userLocPg.getAssignedLocation().matches(".*"+location+".*")){
           throw new ItemNotFoundException(location+" is not assigned");
        }
     }
}

