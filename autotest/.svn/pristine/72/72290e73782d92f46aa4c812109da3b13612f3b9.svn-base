package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.fees;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * Verify search result when search by location.
 * 1.Enter a valid location.
 * 2.Enter part of a valid location.
 * 3.Enter low-case location name.
 * @Preconditions:
 * @SPEC: Step9~11 of 'Search Fee Schedule - Search field [TC:048898]'
 * @Task#:Auto-1429
 * 
 * @author nding1
 * @Date  Jan 29, 2013
 */
public class VerifySearchByLocation extends FinanceManagerTestCase {
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();

	public void execute() {
		fnm.loginFinanceManager(login);

		// 1.Enter a valid location.
		fnm.searchFeeSchedule(schedule);
		
		// column 'Location' should only contains the given value.
		feeMainPg.verifySearchResults(schedule.searchValue, schedule.searchType);

		// 2.Enter part of a valid location.
		schedule.searchValue = schedule.location.substring(0,8);
		fnm.searchFeeSchedule(schedule);
		
		// column 'Location' should only contains the given value.
		feeMainPg.verifySearchResults(schedule.searchValue, schedule.searchType);
		
		// 3.Enter low-case location name.(case non-sensitive)
		schedule.searchValue = schedule.location.toLowerCase();
		fnm.searchFeeSchedule(schedule);
		
		// column 'Location' should only contains the given value.
		feeMainPg.verifySearchResults(schedule.location, schedule.searchType);
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		schedule.searchType = "Location";// Don't change!
		schedule.location = fnm.getFacilityName("552831", schema);// Eno River State Park
		schedule.searchValue = schedule.location;
		schedule.activeStatus = OrmsConstants.ACTIVE_STATUS;
		schedule.productCategory = "List";
	}
}
