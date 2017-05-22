package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrTopMenuPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class ActiveAndDeactiveFeeSchedule extends SupportCase
{
	/**
	 * Script Name   : <b>ActiveAndDeactiveFeeSchedule</b>
	 * Generated     : <b>Feb 2, 2010 9:35:29 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/02
	 * @author dsui
	 */
  LoginInfo login=new LoginInfo();
  boolean loggedin=false;
  FinanceManager finMgr=FinanceManager.getInstance();
  FinMgrTopMenuPage finTpMenuPg=FinMgrTopMenuPage.getInstance();
  FinMgrFeeMainPage finFeeMainPg=FinMgrFeeMainPage.getInstance();
  String scheduleID="";
  boolean toActive=false;
  
  public void wrapParameters( Object[] param ) {
	startpoint = 0; //the start point in the datapool
	endpoint = 1; // the end point in the datapool

    //Initialize login informaiton
	String env = TestProperty.getProperty("target_env");	
  	login.url = AwoUtil.getOrmsURL(env);
    login.userName="qa-auto-adm";
    login.password="auto1234";
    login.contract="NRRS Contract";
    login.location="Administrator/NRRS";
    
    dataSource = casePath + "/" + caseName;
    
    logMsg=new String[4];
    logMsg[0]="Index";
    logMsg[1]="FeeID";
    logMsg[2]="ActiveStatus";
    logMsg[3]="Result";

  }

  public void execute() {

    //Login finance manager  
    if(!loggedin || (loggedin && !finTpMenuPg.exists())){
      finMgr.loginFinanceManager(login);
      loggedin = true;
    }
    
    //Go to fee schedule page
    if(!finFeeMainPg.exists()){
      finTpMenuPg.selectFeesSchedules();
      finFeeMainPg.waitLoading();
    }
   
    //Acitvate or deactivate fee schedule
    finMgr.activeOrDeactiveFeeSchedule(scheduleID,toActive);
    
    //get current fee schedule's status
    if(finFeeMainPg.isFeeScheduleActive(scheduleID)){
      logMsg[2]="Active";
    }
    else{
      logMsg[2]="Inactive";
    }
      
    //verify the result and set log information
    if(toActive){
      if(finFeeMainPg.isFeeScheduleActive(scheduleID)){
        logMsg[3]="Success";
      }
    }
    else{
      if(!finFeeMainPg.isFeeScheduleActive(scheduleID)){
        logMsg[3]="Success";
      } 
    }
  }
  
  public void getNextData() {
    
    scheduleID=dpIter.dpString("feeId");
    toActive=dpIter.dpBoolean("toActive");
    
    logMsg[0]= cursor + "";
    logMsg[1]=scheduleID;
    logMsg[2]="Unknown";
  }

}

