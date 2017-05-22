package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin;

import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrHomePage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddNewUser extends AdminManagerTestCase
{
	/**
	 * Script Name   : <b>AddNewUser</b>
	 * Generated     : <b>Feb 26, 2010 9:19:01 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/26
	 * @author mchen
	 */
     private String searchType="";
     private String show="";
     java.util.Random r=new java.util.Random();
     public void execute(){
        am.loginAdminMgr(login);
        
        am.addNewUser(admUserInfo);
        am.searchUser(searchType,admUserInfo.userName,show);
        this.verifyUserAdded(admUserInfo.userName);
        
        am.lockUser(searchType,admUserInfo.userName,show);
        am.logoutAdminMgr();
     }
     
     public void wrapParameters(Object[] args) {
        login.contract="SC Contract";
        login.url = AwoUtil.getOrmsURL(env);
        login.location="Administrator/South Carolina State Park Service";
        
        admUserInfo.userName="auto-test-"+r.nextInt(99999);
        admUserInfo.password="test1234";
        admUserInfo.confirmPassword="test1234";
        admUserInfo.firstName="auto";
        admUserInfo.lastName="test";
        admUserInfo.activeStatus=true;
        
        searchType="User Name";
        show="*All Users";
     }
     
     public void verifyUserAdded(String username){
       AdmMgrHomePage admHmPg=AdmMgrHomePage.getInstance();
       
       if(!admHmPg.getUserStatus().equalsIgnoreCase("true")&&!admHmPg.checkUserExist(username)){
           throw new ItemNotFoundException("Add new user failed");
       }
     }
}

