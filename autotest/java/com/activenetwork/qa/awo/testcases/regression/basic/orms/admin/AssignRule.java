package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin;

import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRuleDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AssignRule extends AdminManagerTestCase
{
	/**
	 * Script Name   : <b>AssignRule</b>
	 * Generated     : <b>Mar 1, 2010 2:41:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/01
	 * @author mchen
	 */
     public void execute(){
        am.loginAdminMgr(login);
        am.assignRule(rule);
        this.verifyAssignRule();
        
        am.logoutAdminMgr();
     }
     
     public void wrapParameters(Object[] args) {
        login.contract="SC Contract";
	    login.url = AwoUtil.getOrmsURL(env);
	    login.location="Administrator/South Carolina State Park Service";
	    
	    rule.ruleName="Time To Clear";
	    rule.location="SC - PARKS CENTRAL OFFICE";
	    rule.ruleCategory="Park";
	    rule.status="Active";
	    rule.startDate=DateFunctions.getDateAfterToday(-30);
	    rule.endDate=DateFunctions.getDateAfterToday(30);
	    rule.effectiveDate=DateFunctions.getToday();
     }
     
     public void verifyAssignRule(){
       AdmMgrRuleDetailPage admRuleDetailsPg=AdmMgrRuleDetailPage.getInstance();
       
       admRuleDetailsPg.selectFirstRule();
       admRuleDetailsPg.clickDelect();
       
       admRuleDetailsPg.waitLoading();
     }
}

