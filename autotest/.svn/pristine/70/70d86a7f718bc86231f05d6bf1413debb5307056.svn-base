package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.rafee;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:Specify an entry for Location [TC:042272]
 * @Task#:AUTO-1464
 * 
 * @author Jane
 * @Date  Feb 20, 2013
 */
public class SearchByLocation extends FinanceManagerTestCase {

	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchMainPage feeMainPg = FinMgrRaFeeSchMainPage.getInstance();
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		
		feeMainPg.searchSchedule(schedule);
		// column 'Location' should only contains the given value.
		verifySearchByLocation(schedule.location);
		
		// Enter low-case location name.(case non-sensitive)
		schedule.searchValue = schedule.location.toLowerCase();
		feeMainPg.searchSchedule(schedule);
		verifySearchByLocation(schedule.location);
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		schedule.searchBy = "Location";
		schedule.location = fnm.getFacilityName("552818", schema);//Mayo River State Park
		schedule.searchValue = schedule.location;
	}
	
	private void verifySearchByLocation(String location) {
		logger.info("Verify search result by location");
		
		List<String> locList=feeMainPg.getSpecificColValueList("Location");
		
		if(locList==null || locList.size()<1) 
			throw new TestCaseFailedException("Failed to search RA Fee schedule by location.");
		for(String locUI:locList)
			if(!locUI.equalsIgnoreCase(location))
				throw new TestCaseFailedException("Search RA Fee schedule location should be "+location);
		logger.info("---Verify search result by location successfully.");		
	}

}
