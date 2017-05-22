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
public class EditTaxType extends SupportCase
{
	/**
	 * Script Name   : <b>EditTaxType</b>
	 * Generated     : <b>Feb 9, 2010 4:33:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/09
	 * @author Sara Wang
	 */
	
	private FinMgrTaxMainPage finTaxMainPg = FinMgrTaxMainPage.getInstance();
	private FinanceManager fin = FinanceManager.getInstance();

	private LoginInfo login = new LoginInfo();
	private Tax tax = new Tax();
	private boolean loggined = false;
	private String newTaxName = "";

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
		
        //log information
		logMsg = new String[6];
		logMsg[0] = "taxName";
		logMsg[1] = "taxCode";
		logMsg[2] = "taxDescription";
		logMsg[3] = "taxRateType";
		logMsg[4] = "newTaxName";
		logMsg[5] = "result";
		
	}

	public void execute() {
		     
		//Login Finance Manager
		if ((!loggined) || (loggined && !finTaxMainPg.exists())) {
			fin.loginFinanceManager(login);
			loggined = true;
			fin.gotoTaxMainPage();
		}

        //update tax type exited
		newTaxName = fin.updateExistTax(tax);
		logMsg[4] = newTaxName;
		
		if((newTaxName.length()>0 && tax.newTaxName.equals(tax.taxName) && newTaxName.equals(tax.taxName)) ||
			newTaxName.length()>0 && !tax.newTaxName.equals(tax.taxName) && newTaxName.equals(tax.newTaxName)){
			logMsg[5] = "Successful";
		}
	}
	
	public void getNextData() {
		
		tax.feeTypes.clear();
		tax.taxName = dpIter.dpString("taxName");
		tax.newTaxName = dpIter.dpString("newTaxName");//The new tax name which you want to change to.
		tax.taxCode = dpIter.dpString("taxCode");
		tax.taxDescription = dpIter.dpString("taxDescription");
		tax.taxRateType = dpIter.dpString("taxRateType");
		tax.feeTypes.add(dpIter.dpString("feeTypes"));
		
		logMsg[0] = tax.taxName;
		logMsg[1] = tax.taxCode;
		logMsg[2] = tax.taxDescription;
		logMsg[3] = tax.taxRateType;
		logMsg[4] = "Unknown";
		logMsg[5] = "Fail";
		
	}
}

