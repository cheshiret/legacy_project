package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class EditTaxSchedule extends SupportCase
{
	/**
	 * Script Name   : <b>EditTaxSchedule</b>
	 * Generated     : <b>Feb 10, 2010 2:30:38 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/10
	 * @author Sara Wang
	 */

	private FinMgrTaxMainPage finTaxMainPg = FinMgrTaxMainPage.getInstance();
	private FinanceManager fin = FinanceManager.getInstance();
	private FinMgrTaxSchedulePage finTaxSchPg = FinMgrTaxSchedulePage.getInstance();
	private FinMgrTaxSchDetailPage finTaxSchDetailPg = FinMgrTaxSchDetailPage.getInstance();
	
	private LoginInfo login = new LoginInfo();
	private ScheduleData schedule = new ScheduleData();
	private boolean loggined = false;

	public void wrapParameters(Object[] param) {
		
		startpoint=0;   // the start point in the data pool
		endpoint=999;     // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");	
	  	login.url = AwoUtil.getOrmsURL(env);
		login.userName = "qa-auto-adm";
		login.password = "auto1234";
		login.envType = "QA";
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
		
        //log informaiton
		logMsg = new String[11];
		logMsg[0] = "cusor";
		logMsg[1] = "taxSchNum";
		logMsg[2] = "productGroup";
		logMsg[3] = "product";
		logMsg[4] = "effectStartDate";
		logMsg[5] = "effectEndDate";
		logMsg[6] = "customType";
		logMsg[7] = "transactionType";
		logMsg[8] = "accountCode";
		logMsg[9] = "accountRate";
		logMsg[10] = "result";
	}

	public void execute() {

        //Login Finance Manager
		if ((!loggined) || (loggined &&!finTaxMainPg.exists())) {
			fin.loginFinanceManager(login);
			loggined = true;
			//goto tax main page
			fin.gotoTaxMainPage();
			//goto tax schedule page
	        fin.gotoTaxSchedulePage();
		}

       //search tax schedule by tax schedule id
        finTaxSchDetailPg.searchTaxSchedule("Tax Schedule Id", schedule.scheduleId);
       
		//goto tax schedule detail page from tax schedule page
		finTaxSchPg.clickTaxSchId(schedule.scheduleId);
		finTaxSchDetailPg.waitLoading();
        
        //set the type about tax schedule
        finTaxSchDetailPg.setupTaxSchedule(schedule);
        finTaxSchPg.waitLoading();
        
        if(finTaxSchPg.exists()){
        	logMsg[10] = "Successful";
        }
	}

	public void getNextData() {
		
		schedule.scheduleId = dpIter.dpString("taxSchNum");
		schedule.productGroup = dpIter.dpString("productGroup");
		schedule.product = dpIter.dpString("product");
		schedule.startDate = dpIter.dpString("effectStartDate");
		schedule.endDate = dpIter.dpString("effecEndDate");
		schedule.customerType = dpIter.dpString("custType");
		schedule.tranType = dpIter.dpString("transactionType");
		schedule.accountCode = dpIter.dpString("accountCode");
		schedule.rate = dpIter.dpString("accountRate");
		
		logMsg[0] =cursor+"";
		if(null!= schedule.scheduleId && schedule.scheduleId.length()>0){
			logMsg[1] = schedule.scheduleId;
		}else {
			logMsg[1] = "null";
		}
		
		if(null!=schedule.productGroup && schedule.productGroup.length()>0){
			logMsg[2] = schedule.productGroup;
		}else {
			logMsg[2] = "null";
		}
		
		if(null!=schedule.product && schedule.product.length()>0){
			logMsg[3] = schedule.product;
		}else{
			logMsg[3] = "null";
		}

		if(null!=schedule.startDate && schedule.startDate.length()>0){
			logMsg[4] = schedule.startDate;
		}else{
			logMsg[4] = "null";
		}

		if(null!=schedule.endDate && schedule.endDate.length()>0){
			logMsg[5] = schedule.endDate;
		}else{
			logMsg[5] = "null";
		}
		
		if(null!=schedule.customerType && schedule.customerType.length()>0){
			logMsg[6] = schedule.customerType;
		}else{
			logMsg[6] = "null";
		}
		
		if(null!=schedule.tranType && schedule.tranType.length()>0){
			logMsg[7] = schedule.tranType;
		}else{
			logMsg[7] = "null";
		}
		
		if(null!=schedule.accountCode && schedule.accountCode.length()>0){
			logMsg[8] = schedule.accountCode;
		}else{
			logMsg[8] = "null";
		}

		if(null!=schedule.rate && schedule.rate.length()>0){
			logMsg[9] = schedule.rate;
		}else{
			logMsg[9] = "null";
		}
		
		logMsg[10] = "Fail";
		
	}
}

