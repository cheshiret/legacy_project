package com.activenetwork.qa.awo.supportscripts.support.financemgr;

import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxMainPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddTaxType extends SupportCase
{
	/**
	 * Script Name   : <b>AddTaxType</b>
	 * Generated     : <b>Feb 24, 2010 11:21:48 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/24
	 * @author Sara Wang
	 */
	
	private FinMgrTaxMainPage finTaxMainPg = FinMgrTaxMainPage.getInstance();
	private FinanceManager fin = FinanceManager.getInstance();
  	private FinMgrTaxMainPage fintaxMainPg = FinMgrTaxMainPage.getInstance();

	private LoginInfo login = new LoginInfo();
	private Tax tax = new Tax();
	private String taxName = "";
	private boolean loggined = false;
	private String currentContract = "";

	public void wrapParameters(Object[] param) {

		startpoint=0;   // the start point in the data pool
		endpoint=999;     // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");	
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
		login.envType = "QA";
		
        //log informaiton
		logMsg = new String[8];
		
		logMsg[0] = "cursor";
		logMsg[1] = "taxName";
		logMsg[2] = "taxCode";
		logMsg[3] = "taxDescription";
		logMsg[4] = "taxRateType";
		logMsg[5] = "feeTypes";
		
		logMsg[6]="ActiveOrInactive";
	    logMsg[7]="result";
	    
	}

	public void execute() {
		
		//Login Finance Manager
		if ((!loggined) || !currentContract.equalsIgnoreCase(login.contract)||(loggined && !finTaxMainPg.exists())) {
			fin.loginFinanceManager(login);
			loggined = true;
			
			fin.gotoTaxMainPage();
		}
		currentContract = login.contract;
		
        //add new tax type
		fin.addNewTax(tax);
		taxName = tax.taxName;
		 //Activate tax
		fintaxMainPg.changeTaxStatus(taxName,"Yes");
	    
	    //get current tax's status
	    if( fintaxMainPg.isTaxActive() && taxName.length()>0){
	    	logMsg[1] = taxName;
	        logMsg[6]="Active";
	        logMsg[7]="Success";
	      
	    } else{
	    	logMsg[1]=taxName;
	        logMsg[6]="Inactive";
	        logMsg[7]="Fail";
	    }
	}

	public void getNextData() {
		login.contract = dpIter.dpString("contract");
		login.location = dpIter.dpString("location");
		
		tax.feeTypes.clear();
		tax.taxName = dpIter.dpString("taxName");
		tax.taxCode = dpIter.dpString("taxCode");
		tax.taxDescription = dpIter.dpString("taxDescription");
		tax.taxRateType = dpIter.dpString("taxRateType");
		
		tax.feeTypes.add(dpIter.dpString("feeTypes"));
		
		logMsg[0] = cursor+" ";
		logMsg[1] = "Unknown";
		
		if(null!=tax.taxCode && tax.taxCode.length()>0){
			logMsg[2] = tax.taxCode;
		}else{
			logMsg[2] = "null";
		}
        if(null!=tax.taxDescription && tax.taxDescription.length()>0){
    		logMsg[3] = tax.taxDescription;
        }else{
    		logMsg[3] = "null";
        }
        if(null!=tax.taxRateType && tax.taxRateType.length()>0){
    		logMsg[4] = tax.taxRateType;	
        }else{
    		logMsg[4] = "null";
        }

		logMsg[5] = dpIter.dpString("feeTypes");
		
		logMsg[6]="Inactive";
	    logMsg[7]="Fail due to error";
	}
}

