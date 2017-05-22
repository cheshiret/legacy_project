package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import com.activenetwork.qa.awo.datacollection.legacy.ChartOfAccountData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrTopMenuPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddNewAccount extends SupportCase
{
	/**
	 * Script Name   : <b>AddNewAccount</b>
	 * Generated     : <b>Feb 4, 2010 1:49:30 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/04
	 * @author dsui
	 */
  LoginInfo login=new LoginInfo();
  ChartOfAccountData cd=new ChartOfAccountData();
  boolean loggedin=false;
  FinanceManager finMgr=FinanceManager.getInstance();
  FinMgrTopMenuPage finTpMenuPg=FinMgrTopMenuPage.getInstance();

  String accountCode="";
  String accountType="";
  public void wrapParameters( Object[] param ) {
	startpoint = 0; //the start point in the datapool
	endpoint = 0; // the end point in the datapool

    //Initialize login information
	String env = TestProperty.getProperty("target_env");	
  	login.url = AwoUtil.getOrmsURL(env);
    login.userName="qa-auto-adm";
    login.password="auto1234";
    login.contract="ID Contract";//The support script is for ID contract
    login.location="Administrator/Idaho Contract";
    
    dataSource = casePath + "/" + caseName;
    
    logMsg=new String[4];
    logMsg[0]="Index";
    logMsg[1]="AccountCode";
    logMsg[2]="AccountName";
    logMsg[3]="Result";
 
  }

  public void execute() {
    
    //Login finance manager
    if((!loggedin) || (loggedin && !finTpMenuPg.exists())){
      finMgr.loginFinanceManager(login);
      loggedin = true;
    }
    
    finMgr.goToAccountsAndJournalsPage();
    
    //Add new account
    accountCode=finMgr.addNewAccount(cd);
    
    if(!accountCode.equalsIgnoreCase("null")){
      logMsg[3]="Success";
    }
    
  }
  public void getNextData() {
	cd.name = dpIter.dpString("name");
	cd.typeDesc = dpIter.dpString("Type Desc");
	cd.locationName=dpIter.dpString("LocationName");
	cd.revenueCode = dpIter.dpString("Revenue Code");
	cd.subRevenue = dpIter.dpString("Sub Revenue");
	cd.object = dpIter.dpString("Object");
	cd.subObject = dpIter.dpString("Sub Object");
	cd.siteType = dpIter.dpString("Site Type");
	cd.description = dpIter.dpString("Description");
	accountType=dpIter.dpString("AccountType");
	
    if(accountType!=null && accountType.matches("Yes|yes|True|true|t|T")) {
      cd.accountType=true;
    } else {
      cd.accountType=false;
    }
	
	logMsg[0]= cursor + "";
    logMsg[1]="null";
    logMsg[2]="null";
    logMsg[3]="Fail due to error";
  }

}

