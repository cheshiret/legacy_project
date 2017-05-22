package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.fees;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:
 * Verify search result when search by schedule ID.
 * 1.A number exactly match one fee schedule.Enter other search criteria which NOT match with the entered fee schedule ID
 * 2.A number exactly match one fee schedule.
 * 3.A number is part of an existing fee schedule ID.
 * 4.A random number and no fee schedule matches it.
 * 5.Enter some characters.
 * @Preconditions:
 * Existing fee schedule: d_fin_use_attr_fee_sched, ID:3660
 * @SPEC: Step1~5 of 'Search Fee Schedule - Search field [TC:048898]'
 * @Task#:Auto-1429
 * 
 * @author nding1
 * @Date  Jan 29, 2013
 */
public class VerifySearchByID extends FinanceManagerTestCase {
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private String expectMsg;
	
	public void execute() {
		fnm.loginFinanceManager(login);
		
		// get ID of an existing fee schedule
		fnm.searchFeeSchedule(schedule);
		schedule.feeSchdId = feeMainPg.getFeeID().get(0);
		schedule.searchType = "Fee Schedule ID";
		schedule.searchValue = schedule.feeSchdId;

		// 1.A number exactly match one fee schedule ID. Enter other search criteria which NOT match with the entered fee schedule ID
		this.updateSearchCriteria(false);
		fnm.searchFeeSchedule(schedule);
		
		// search result should contain ONLY one record.
		this.verifySearchResult(false);
				
		// 2.A number exactly match one fee schedule.
		this.updateSearchCriteria(true);// clean other search criteria
		fnm.searchFeeSchedule(schedule);
		
		// search result should contain ONLY one record.
		this.verifySearchResult(false);
		
		// 3.A number is part of an existing fee schedule ID.
		schedule.searchValue = schedule.feeSchdId.substring(0, 4);
		fnm.searchFeeSchedule(schedule);
		
		// should display error message
		this.verifySearchResult(true);
		
		// 4.A random number and no fee schedule matches it.
		schedule.searchValue = "001";
		fnm.searchFeeSchedule(schedule);
		
		// should display error message
		this.verifySearchResult(true);
		
		// 5.Enter some characters.
		schedule.searchValue = "testabc";
		fnm.searchFeeSchedule(schedule);
		
		// should display error message
		this.verifySearchResult(true);
		fnm.logoutFinanceManager();
	}
	
	private void verifySearchResult(boolean isMsg){
		if(isMsg){
			String actualMsg = feeMainPg.getErrorMsg();
			if(!MiscFunctions.compareResult("Error Message", expectMsg, actualMsg)){
				throw new ErrorOnPageException("--Check logs above.");
			}
		} else {
			List<String> idList = feeMainPg.getFeeID();
			if(!MiscFunctions.compareResult("Search result number", 1, idList.size())){
				throw new ErrorOnPageException("Search result should contain ONLY one record which ID is:"+schedule.searchValue);
			} else {
				if(!MiscFunctions.compareResult("Fee schedule ID", schedule.feeSchdId, idList.get(0))){
					throw new ErrorOnPageException("--Check logs above.");
				}
			}
		}
	}

	private void updateSearchCriteria(boolean isClean){
		if(isClean){
			schedule.productCategory = "";
			schedule.feeType = "";
			schedule.marinaRateType = "";
			schedule.boatCategory = "";
			schedule.rafting = "";
		} else {
			schedule.productCategory = "List";
		}
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		// schedule info the same with data in d_fin_use_attr_fee_sched, ID:3660
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.marinaRateType = "Transient";
		schedule.boatCategory = "Personal";
		schedule.rafting = "Excluding Rafting";
		
		expectMsg = "No Fee Schedules found for search criteria";
	}
}
