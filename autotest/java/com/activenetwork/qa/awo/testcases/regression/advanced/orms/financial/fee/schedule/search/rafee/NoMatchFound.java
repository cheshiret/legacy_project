package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.rafee;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description:Enter some search criteria and no item match Prompt "No RA Fee Schedules found for search criteria."  
 * @Preconditions:
 * @SPEC:TC:042264 -- No RA Fee Schedule found.
 * @Task#:Auto-1463
 * 
 * @author Jasmine
 * @Date Feb 6, 2013
 */
public class NoMatchFound extends FinanceManagerTestCase{
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchMainPage listPage = FinMgrRaFeeSchMainPage.getInstance();
	private FinMgrRaFeeSchDetailPage detailsPage = FinMgrRaFeeSchDetailPage.getInstance();
	private final String No_Matches_Found = "No Matches Found";
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		listPage.searchByFeeScheduleId(schedule.feeId);
		this.verifySearchMessage();
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		
		schedule.feeId = "98787";//Here hard code to create no matches found.
	}
	
	private void verifySearchMessage(){
		String msg = detailsPage.getErrorMsg();
		if(!msg.equals(No_Matches_Found)){
			throw new ErrorOnPageException("Error Message",No_Matches_Found,msg);
		}else{
			logger.info("Error message correct");
		}
	}

}
