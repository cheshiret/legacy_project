package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddTaxSchedule extends SupportCase
{
	/**
	 * Script Name   : <b>AddTaxSchedule</b>
	 * Generated     : <b>Feb 25, 2010 1:19:19 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/25
	 * @author Sara Wang
	 */
	private FinanceManager fin = FinanceManager.getInstance();
	private FinMgrTaxSchedulePage finTaxSchPg = FinMgrTaxSchedulePage.getInstance();
	
	private LoginInfo login = new LoginInfo();
	private ScheduleData schedule = new ScheduleData();
	
	private String taxScheduleID = "";
	private boolean loggined = false;
	private String currentContract = "";

	public void wrapParameters(Object[] param) {
		startpoint=66;   // the start point in the data pool
		endpoint=66;     // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");	
	  	login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
		login.envType = "QA";
				
        //log informaiton
		logMsg = new String[9];
		logMsg[0] = "cusor";
		logMsg[1] = "location";
		logMsg[2] = "locationCategory";
		logMsg[3] = "taxName";
		logMsg[4] = "productCategory";
		logMsg[5] = "feeType";
		
		logMsg[6]="taxScheduleID";
		logMsg[7]="ActiveOrInactive";
	    logMsg[8]="result";

	}

	public void execute() {
		//Login Finance Manager
		if ((!loggined) || (loggined && !finTaxSchPg.exists())||(!currentContract.equals(login.contract))) {
			fin.loginFinanceManager(login);
			loggined = true;
			//goto tax main page
			fin.gotoTaxMainPage();
			//goto tax schedule page
	        fin.gotoTaxSchedulePage();
		}
        
		//add new tax schedule
		taxScheduleID = fin.addNewTaxSchedule(schedule);
		
		//Activate tax schedule
		finTaxSchPg.changeTaxScheduleStatus(taxScheduleID,"Active");
	    
	    //get current taxSchedule's status
	    if( finTaxSchPg.isTaxScheduleActive(taxScheduleID)&& taxScheduleID.length()>0){
	    	logMsg[6] = taxScheduleID;
	        logMsg[7]="Active";
	        logMsg[8]="Success";
	      
	    } else{
	    	logMsg[6]=taxScheduleID;
	        logMsg[7]="Inactive";
	        logMsg[8]="Fail";
	    }
	}

	public void getNextData() {
		login.contract = dpIter.dpString("contract");
		login.location = dpIter.dpString("logLocation");

		//taxName,feeType,effectiveStartDate,accountCode ---- must set up  
		//accountRate ---- default:0.0%
		 
		schedule.location = dpIter.dpString("location");
		schedule.locationCategory = dpIter.dpString("locationCategory");
		
		schedule.taxName = dpIter.dpString("taxName");
		schedule.productCategory = dpIter.dpString("productCategory");
		schedule.feeType = dpIter.dpString("feeType");
		schedule.productGroup = dpIter.dpString("productGroup");
		schedule.product = dpIter.dpString("product");
		schedule.startDate = dpIter.dpString("startDate");
		schedule.endDate = dpIter.dpString("endDate");
		schedule.customerType = dpIter.dpString("customerType");
		schedule.accountCode = dpIter.dpString("accountCode");
		schedule.rate = dpIter.dpString("accountRate");
		schedule.tranType=dpIter.dpString("tranType");
		
		logMsg[0] = cursor+"";
		logMsg[1] = schedule.location;
		logMsg[2] = schedule.locationCategory;
		
		logMsg[3] = schedule.taxName;
		logMsg[4] = schedule.productCategory;
		logMsg[5] = schedule.feeType;
		
		logMsg[6]="Unknown";
		logMsg[7]="Inactive";
	    logMsg[8]="Fail due to error";
		
	}
}

