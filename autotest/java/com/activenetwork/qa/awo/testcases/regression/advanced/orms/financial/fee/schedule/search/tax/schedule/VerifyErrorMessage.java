package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.tax.schedule;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description: verify the error message - 'No Tax Schedules found for search criteria'
 * @Preconditions:
 * @SPEC: Search Tax Schedule - no result found [TC:049972]
 * @Task#: AUTO-1440
 * 
 * @author qchen
 * @Date  Feb 17, 2013
 */
public class VerifyErrorMessage extends FinanceManagerTestCase{
	private FinMgrTaxSchedulePage taxSchdlPage = FinMgrTaxSchedulePage.getInstance();
	private String expectedMsg;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoTaxMainPage();
		fnm.gotoTaxSchedulePage();
		FeeScheduleData searchCriteria = new FeeScheduleData();
		searchCriteria.searchType = "Location";
		searchCriteria.searchValue = "Nowhere";
		taxSchdlPage.searchTaxSchedule(searchCriteria);
		String actualMsg = taxSchdlPage.getWarningMsg();
		this.verifyErrorMsg(expectedMsg, actualMsg);
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		expectedMsg = "No Tax Schedules found for search criteria.";
	}
	
	private void verifyErrorMsg(String expected, String actual) {
		if(!MiscFunctions.compareResult("Error message", expected, actual)) {
			throw new ErrorOnPageException("Error message is incorrect.");
		} else logger.info("Error message is correct.");
	}
}
