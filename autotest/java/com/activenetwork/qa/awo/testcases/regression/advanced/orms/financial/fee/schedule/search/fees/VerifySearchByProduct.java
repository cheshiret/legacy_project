package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.fees;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * Verify search result when search by product.
 * 1.Enter a valid product name.
 * 2.Enter part of a valid product name.
 * 3.Enter low-case product name.
 * 4.Enter invalid product name.
 * @Preconditions:
 * @SPEC: Step12~15 of 'Search Fee Schedule - Search field [TC:048898]'
 * @Task#:Auto-1429
 * 
 * @author nding1
 * @Date  Jan 29, 2013
 */
public class VerifySearchByProduct extends FinanceManagerTestCase {
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private String expectMsg, colName;

	public void execute() {
		fnm.loginFinanceManager(login);
		
		// 1.Enter a valid product name.
		fnm.searchFeeSchedule(schedule);
		feeMainPg.verifySearchResults(schedule.searchValue, colName);
		
		// 2.Enter part of a valid product name.
		schedule.searchValue = schedule.product.substring(0,15);
		fnm.searchFeeSchedule(schedule);
		feeMainPg.verifySearchResults(schedule.searchValue, colName);

		// 3.Enter low-case product name.(case non-sensitive)
		schedule.searchValue = schedule.product.toLowerCase();
		fnm.searchFeeSchedule(schedule);
		feeMainPg.verifySearchResults(schedule.product, colName);

		// 4.Enter invalid product name.
		schedule.searchValue = "invalidProduct";
		fnm.searchFeeSchedule(schedule);
		if(!MiscFunctions.compareResult("Error message", expectMsg, feeMainPg.getErrorMsg())){
			throw new ErrorOnPageException("--Check logs above.");
		}
		
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		colName = "Product or Product Group";
		
		schedule.searchType = "Product";// Don't change!
		schedule.product = "SEASONAL SWEATSHIRT - XL";
		schedule.searchValue = schedule.product;
		schedule.activeStatus = OrmsConstants.ACTIVE_STATUS;
		
		expectMsg = "No Fee Schedules found for search criteria";
	}
}
