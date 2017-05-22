package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.fees;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description:This test case contains the scenario: 
 *              1.verify search criteria when select Slip as Product Category 
 *              2.verify no result found
 *              
 * @Preconditions:
 * @SPEC:TC049262,TC048323
 * @Task#:AUTO-1428
 * 
 * @author szhou
 * @Date  Jan 21, 2013
 */
public class VerifySearchCriteria_Slip extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData();
	private String error;
	
	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();

		// verify search criteria when select Slip as Product Category
        this.verifySearchCriteria(schedule.productCategory);
        
        // verify no result found 
        schedule.cleanUpSearchData();
        schedule.searchType="Location";
        schedule.searchValue=schedule.location;
        fnm.searchFeeSchedule(schedule);
        this.verifyErrorMess(error);
        
        fnm.logoutFinanceManager();
	}
	
	private void verifyErrorMess(String error){
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		
		String actual=feeMainPg.getErrorMsg();
		if(!error.equals(actual)){
			throw new ErrorOnPageException("error message",error,actual);
		}
	}

	private void verifySearchCriteria(String category) {
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();

		feeMainPg.selectProductCategory(category);
		ajax.waitLoading();
		feeMainPg.waitLoading();

		if (!feeMainPg.isMarinaRateTypeExist()) {
           throw new ErrorOnPageException("Marina Rate Type should display on the search criteria");
		}
		if (!feeMainPg.isRaftingExist()) {
			throw new ErrorOnPageException("Rafting should display on the search criteria");
		}
		if (!feeMainPg.isBoatCategoryExist()) {
			throw new ErrorOnPageException("Boat Category should display on the search criteria");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schedule.productCategory = "Slip";
		schedule.location = "abc";
		error="No Matches Found";//"No Fee Schedules found for search criteria";Vivian [2013/12/11]Error message is changed, please refer to DEFECT-58406 
	}

}
