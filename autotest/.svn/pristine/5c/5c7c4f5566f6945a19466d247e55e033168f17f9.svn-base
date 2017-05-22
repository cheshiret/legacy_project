package com.activenetwork.qa.awo.testcases.abstractcases;

import com.activenetwork.qa.awo.datacollection.legacy.RuleDataInfo;
import com.activenetwork.qa.awo.datacollection.legacy.ServiceAndActivity;
import com.activenetwork.qa.awo.datacollection.legacy.orms.AdminUserInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.RoleInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * Description   : Functional Test Script
 * @author QA
 */
public abstract class AdminManagerTestCase extends OrmsTestCase
{
	/**
	 * Script Name   : <b>AdminManagerTestCase</b>
	 * Generated     : <b>Feb 26, 2010 9:19:35 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2010/02/26
	 * @author mchen
	 */

     protected AdminManager am;
     protected AdminUserInfo admUserInfo;
     protected RoleInfo role;
     protected ServiceAndActivity service;
     protected RuleDataInfo rule;

     public AdminManagerTestCase() {
         super();

         am=AdminManager.getInstance();
         admUserInfo=new AdminUserInfo();
         service =new ServiceAndActivity();
         role=new RoleInfo();
         rule=new RuleDataInfo();
         login.userName = TestProperty.getProperty("orms.adm.user");
		 login.password = TestProperty.getProperty("orms.adm.pw");
     }
}

