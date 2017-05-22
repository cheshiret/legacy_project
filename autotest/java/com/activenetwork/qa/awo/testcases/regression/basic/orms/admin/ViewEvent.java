package com.activenetwork.qa.awo.testcases.regression.basic.orms.admin;

import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrServiceDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.AdminManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class ViewEvent extends AdminManagerTestCase
{
	/**
	 * Script Name   : <b>ViewEvent</b>
	 * Generated     : <b>Mar 1, 2010 1:55:33 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/01
	 * @author mchen
	 */
     public String eventID="";
  	 public void execute(){
  	   am.loginAdminMgr(login);
  	   am.viewEvent(eventID);
  	   
  	   this.verifyEventDetails();
  	   am.logoutAdminMgr();
  	   
  	 }
  	 
  	public void wrapParameters(Object[] args) {
  	   login.contract="SC Contract";
       login.url = AwoUtil.getOrmsURL(env);
       login.location="Administrator/South Carolina State Park Service";
       
  	   eventID="2718368";
  	}
  	
  	public void verifyEventDetails(){
  	  	AdmMgrServiceDetailsPage admServiceDetailsPg=AdmMgrServiceDetailsPage.getInstance();
  	  	
  	  	if(!admServiceDetailsPg.checkObjEnable("datg")&&!admServiceDetailsPg.checkObjEnable("type")&&!admServiceDetailsPg.checkObjEnable("inpName")&&!admServiceDetailsPg.checkObjEnable("dscr")&&!admServiceDetailsPg.checkObjEnable("icon")){
  	  	    throw new ItemNotFoundException("th event details should be read only");
  	  	}
  	}
  	
}

