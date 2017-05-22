/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.finance;


import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class CreateBaseFeeFunction extends FunctionCase
{


	private FinanceManager fin = FinanceManager.getInstance();
	private FinMgrFeeMainPage finFeeMainPg = FinMgrFeeMainPage.getInstance();
	private FinMgrHomePage homePg = FinMgrHomePage.getInstance();
	
	private LoginInfo login;
	private FeeScheduleData feeSchData;
	private String feeScheduleId = "",contract = "";
	private boolean loggedIn = false;
	
	public void wrapParameters(Object[] param) {
		this.login = (LoginInfo)param[0];
		this.feeSchData = (FeeScheduleData)param[1];
	}
	
	public void execute() {
		 //Login Finance Manager
		if(!contract.equalsIgnoreCase(login.contract) && loggedIn && isBrowserOpened){
			fin.switchToContract(login.contract, login.location);
		}
		if (!loggedIn || !isBrowserOpened) {
			fin.loginFinanceManager(login);
			loggedIn = true;
		}
		contract = login.contract;
		
		if(!homePg.exists()){
			fin.gotoHomePage();
		}
		
		fin.gotoFeeMainPage();
		fin.clickFeeTab();
		
		//add use fee Fee schedule
		feeScheduleId = fin.addNewFeeSchedule(feeSchData);
		if(!feeScheduleId.matches("\\d+")){
			throw new ErrorOnPageException("Create Base Fee Schedule Fail.");
		}
		newAddValue = this.feeScheduleId;
		 //Activate Fee Schedule
		finFeeMainPg.changeScheduleStatus(feeScheduleId,"Active");
	    
	    //get current fee schedule's status
	    if(!finFeeMainPg.isFeeScheduleActive(feeScheduleId)){
	        throw new ErrorOnPageException("Create Base Fee Schedule Fail. Please check new schedule ID-->"+this.feeScheduleId);
	    }

	}
	
}

