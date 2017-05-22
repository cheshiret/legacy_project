package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin;

import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRoleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRolePage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddNewRole extends AdminManagerTestCase
{
	/**
	 * Script Name   : <b>AddNewRole</b>
	 * Generated     : <b>Feb 27, 2010 5:20:50 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/27
	 * @author mchen
	 */
  	 public void execute(){
  	    am.loginAdminMgr(login);
  	    this.clearup(role.roleName);
  	    am.addNewRole(role);
  	    
  	    this.verifyAddNewRole();
  	    
  	    am.deleteRole(role.roleName);
  	    am.logoutAdminMgr();
  	 }
  	 
  	 public void wrapParameters(Object[] args) {
	  	 login.contract="SC Contract";
	     login.url = AwoUtil.getOrmsURL(env);
	     login.location="Administrator/South Carolina State Park Service";
	     
	     role.roleName="qa auto test";
	     role.description="qa auto test";
	     role.application="AdminManager";
	     role.feature="ViewLocation";
  	 }
  	 
  	 public void verifyAddNewRole(){
  	     AdmMgrRoleDetailsPage admRoleDetailsPg=AdmMgrRoleDetailsPage.getInstance();
  	     AdmMgrRolePage admRolePg=AdmMgrRolePage.getInstance();
  	     
  	     admRoleDetailsPg.clickRoleDetails();
  	     admRoleDetailsPg.waitLoading();
  	     admRoleDetailsPg.clickCancel();
  	     admRolePg.waitLoading();
  	     
  	     admRolePg.searchRoleName(role.roleName);
  	     admRolePg.waitLoading();
  	     
  	     admRolePg.gotoRoleDetails(role.roleName);
	     admRoleDetailsPg.waitLoading();
	     
	     admRoleDetailsPg.clickRoleApplication();
	     admRoleDetailsPg.waitLoading();
	     admRoleDetailsPg.searchApplication("Application",role.application);
	     
	     if(!admRoleDetailsPg.getApplicationStatus().equalsIgnoreCase("Yes")){
	        throw new ItemNotFoundException("Application's status is incorrect");
	     }
	     
	     admRoleDetailsPg.clickRoleFeature();
	     admRoleDetailsPg.waitLoading();
	     
	     admRoleDetailsPg.searchFeature(role.feature,role.application);
	     
	     if(!admRoleDetailsPg.getFeatureStatus().equalsIgnoreCase("Yes")){
	        throw new ItemNotFoundException("Application's status is incorrect");
	     }
	     
	     admRoleDetailsPg.clickRoleDetails();
	     admRoleDetailsPg.waitLoading();
	     admRoleDetailsPg.clickCancel();
	     admRolePg.waitLoading();
	     
  	 }
  	 
  	 public void clearup(String roleName){
  		AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();
	    AdmMgrRolePage admRolePg=AdmMgrRolePage.getInstance();
	    AdmMgrRoleDetailsPage admRoleDetailsPg = AdmMgrRoleDetailsPage.getInstance();
	    
	    admHmPg.clickRole();
	    admRolePg.waitLoading();
	    admRolePg.searchRoleName(roleName);
	    if(admRolePg.checkRoleNameIsExisting(roleName)){
	    	admRolePg.gotoRoleDetails(roleName);
	    	admRoleDetailsPg.waitLoading();
	    	admRoleDetailsPg.clickDeleteRole();
		    admRolePg.waitLoading();
	    }
  	 }
}

