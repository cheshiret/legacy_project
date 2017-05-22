package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.rafee;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.TestCaseFailedException;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:Specific Product Category [TC:042273]
 * @Task#:AUTO-1464
 * 
 * @author Jane
 * @Date  Feb 20, 2013
 */
public class SearchByPrdCategory extends FinanceManagerTestCase {

	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchMainPage feeMainPg = FinMgrRaFeeSchMainPage.getInstance();
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		
		feeMainPg.searchSchedule(schedule);
		// column 'Product Category' should only contains the given value.
		verifySearchByPrdCategory(schedule.productCategory);
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		schedule.productCategory="List";
	}
	
	private void verifySearchByPrdCategory(String prdCategory) {
		logger.info("Verify search result by product category");
		
		List<String> prdCategoryList=feeMainPg.getSpecificColValueList("Product Category");
		
		if(prdCategoryList==null || prdCategoryList.size()<1) 
			throw new TestCaseFailedException("Failed to search RA Fee schedule by product category.");
		for(String prdCategoryUI:prdCategoryList)
			if(!prdCategoryUI.equalsIgnoreCase(prdCategory))
				throw new TestCaseFailedException("Search RA Fee schedule product category should be "+prdCategory);
		logger.info("---Verify search result by product category successfully.");		
	}
}
