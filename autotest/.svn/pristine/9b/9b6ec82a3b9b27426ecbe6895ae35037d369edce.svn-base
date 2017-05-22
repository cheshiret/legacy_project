package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.fees;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:
 * Verify search result when search by account code.
 * 1.Enter a valid Account code.
 * 2.Enter part of a valid Account code.
 * 3.Enter invalid account code
 * @Preconditions:
 * Linked data: d_fin_use_attr_fee_sched, ID:3660
 * @SPEC: Step6~8 of 'Search Fee Schedule - Search field [TC:048898]'
 * @Task#:Auto-1429
 * 
 * @author nding1
 * @Date  Jan 29, 2013
 */
public class VerifySearchByAccountCode extends FinanceManagerTestCase {
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private String expectMsg;
	
	public void execute() {
		fnm.loginFinanceManager(login);

		// 1.Enter a valid Account code.
		fnm.searchFeeSchedule(schedule);
		
		// column 'Account' should only contains the given value.
		feeMainPg.verifySearchResults(schedule.searchValue, schedule.searchType);

		// 2.Enter part of a valid Account code.
		schedule.searchValue = schedule.acctCode.split(";")[0].trim();
		fnm.searchFeeSchedule(schedule);
		
		// column 'Account' should only contains the given value.
		feeMainPg.verifySearchResults(schedule.searchValue, schedule.searchType);
		
		// 3.Enter invalid account code
		schedule.searchValue = "invalidaccountcode";
		fnm.searchFeeSchedule(schedule);
		
		// should display error message
		if(!MiscFunctions.compareResult("Error Message", expectMsg, feeMainPg.getErrorMsg())){
			throw new ErrorOnPageException("--Check logs above.");
		}
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schedule.searchType = "Account";// Don't change!
		schedule.acctCode = "3000.1601.000436200; Donations";
		schedule.searchValue = schedule.acctCode;
		
		expectMsg = "No Fee Schedules found for search criteria";
	}
}
