package com.activenetwork.qa.awo.supportscripts.support.inventorymgr;

import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosureDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddClosure extends SupportCase
{
	/**
	 * Script Name   : <b>AddClosure</b>
	 * Generated     : <b>Feb 1, 2010 9:15:50 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/01
	 * @author dsui
	 */

  LoginInfo login=new LoginInfo();
  boolean loggedin=false;
  InventoryManager invMgr=InventoryManager.getInstance();
  InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();
  InvMgrClosuresPage invClosurePg=InvMgrClosuresPage.getInstance();
  InvMgrClosureDetailPage invClosureDetailPg=InvMgrClosureDetailPage.getInstance();
  InvMgrTopMenuPage invTpMenuPg=InvMgrTopMenuPage.getInstance();
  Closure closure=new Closure();
  String facilityId = "";
  String facilityName = "";
  String closureID="";
  String contract = "";
  String status = "Unknown";
	
  public void wrapParameters( Object[] param ) {
	startpoint = 0; //the start point in the datapool
	endpoint = 0; // the end point in the datapool

    //Initialize login informaiton
    String env = TestProperty.getProperty("target_env");	
  	login.url = AwoUtil.getOrmsURL(env);
    login.userName = TestProperty.getProperty("orms.adm.user");
    login.password = TestProperty.getProperty("orms.adm.pw"); 
    
    //Closure information
	closure.comment = "RA-331316: System Error Sites Dropping back into Inventory";
	if(closure.startDate==null || closure.startDate.trim().length()<1){
		closure.startDate = DateFunctions.getDateStamp(false);
	}
	if(closure.endDate==null || closure.endDate.trim().length()<1){
		closure.endDate = closure.startDate;
	}
	
	closure.notificationDate = closure.startDate;
	closure.type = "Problem";
	closure.status = "active";
    
    dataSource = casePath + "/" + caseName;
    
    logMsg=new String[5];
    logMsg[0]="Index";
    logMsg[1]="Closure Id";
    logMsg[2]="Status";
    logMsg[3]="Facility Name";
    logMsg[4]="Result";    
  }
  
  public void execute() {
    
	if(!contract.equalsIgnoreCase(login.contract) && loggedin){
		invMgr.switchToContract(login.contract, login.location);
	}
    if(!loggedin){
      invMgr.loginInventoryManager(login);
      loggedin=true;
    }
    
    if(invHmPg.exists()){
      invMgr.gotoFacilityDetailsPg(facilityName);
    }else{
    	invMgr.gotoInventoryHomePg();
    }
    
    invMgr.goToBookingRulePg();
    
    //add new closure
    closureID=invMgr.addClosure(closure).trim();

    invClosurePg.clickClosureID(closureID);
    invClosureDetailPg.waitLoading();
    
	if (invClosureDetailPg.isActive())
		status = "Active";
	else
		status = "Inactive";
	
	invMgr.gotoInventoryHomePg();
	
	//Set log Info
	logMsg[1]=closureID;
	logMsg[2]=status;
	if(closureID.length()>0){
	  logMsg[4]="Success";
	}
	contract = login.contract;
  }

	public void getNextData() {
		// facilityId=dpIter.dpString("id");
		facilityName = dpIter.dpString("name");
		
		if(dpIter.dpString("contract")!=null && dpIter.dpString("contract").length()>0){
			login.contract = dpIter.dpString("contract");
		}
		
		if(dpIter.dpString("location")!=null && dpIter.dpString("location").length()>0){
			login.location = dpIter.dpString("location");
		}
		
		closure.type = dpIter.dpString("closureType");
		if(dpIter.dpString("startDate").length() <1){
			closure.startDate = DateFunctions.getToday();//If start date is blank from datapool, the start date is today
		}else{
			closure.startDate = dpIter.dpString("startDate");
		}
		
		if(dpIter.dpString("endDate").length() <1){
			closure.endDate = DateFunctions.getToday();//If end date is blank from datapool, the end date is today
		}else{
			closure.endDate = dpIter.dpString("endDate");
		}
		
		if (dpIter.dpString("siteIDs").length() > 0) {
			closure.assignAll = false;
			closure.siteIds = dpIter.dpString("siteIDs").split(",");
		}

		logMsg[0] = cursor + "";
		logMsg[1] = "Unknown";
		logMsg[2] = status;
		logMsg[3] = facilityName;
		logMsg[4] = "Fail due to error";
	}
}

