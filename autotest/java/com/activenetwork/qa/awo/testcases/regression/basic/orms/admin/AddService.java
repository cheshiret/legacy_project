package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin;

import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrServicePage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddService extends AdminManagerTestCase
{
	/**
	 * Script Name   : <b>AddService</b>
	 * Generated     : <b>Mar 1, 2010 12:38:23 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/01
	 * @author mchen
	 */
     public void execute(){
        am.loginAdminMgr(login);
        
        am.addServiceOrActivity(service);
        this.verifyAddService();
        am.deleteService(service.name);
        
        am.logoutAdminMgr();
        
     }
     
     public void wrapParameters(Object[] args) {
        login.contract="SC Contract";
        login.url = AwoUtil.getOrmsURL(env);
        login.location="Administrator/South Carolina State Park Service";
        
        service.category="services";
        service.type="Comfort Services";
        service.name="qa auto test" + DateFunctions.getCurrentTime();
        service.icon="Parking";
        service.description="qa auto test";
     }
     
     public void verifyAddService(){
        AdmMgrServicePage admServicePg=AdmMgrServicePage.getInstance();
        
        admServicePg.searchService("Name",service.name);
        
        if(!admServicePg.serviceInfo().matches(".*"+service.name+".*")){
            throw new ItemNotFoundException(service.category+" created failed");
        }
     }
}

