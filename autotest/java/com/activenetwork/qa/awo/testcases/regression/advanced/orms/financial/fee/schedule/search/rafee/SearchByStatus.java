package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.rafee;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.TestCaseFailedException;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:Specify an entry for RA Fee Schedule Status [TC:042271]
 * @Task#:AUTO-1464
 * 
 * @author Jane
 * @Date  Feb 20, 2013
 */
public class SearchByStatus extends FinanceManagerTestCase {

	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchMainPage feeMainPg = FinMgrRaFeeSchMainPage.getInstance();
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		
		schedule.searchStatus=OrmsConstants.ACTIVE_STATUS;//search active status
		feeMainPg.searchSchedule(schedule);
		verifySearchByStatus();
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
	}
	
	private void verifySearchByStatus() {
		logger.info("Verify search result by status");
		
		List<String> statusList=feeMainPg.getSpecificColValueList("Active");
		
		if(statusList==null || statusList.size()<1) 
			throw new TestCaseFailedException("Failed to search RA Fee schedule by status.");
		for(String statusUI:statusList)
			if(!statusUI.equalsIgnoreCase(OrmsConstants.YES_STATUS))
				throw new TestCaseFailedException("Search RA Fee schedule status should be "+OrmsConstants.YES_STATUS);
		logger.info("---Verify search result by status successfully.");		
	}
}
