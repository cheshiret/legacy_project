package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.rafee;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: Search RA Fee Schedules by product category, and verify the column Rate is exist in search result list.
 * @Preconditions:
 * @SPEC: Rate in the search criteria [TC:042263]
 * @Task#: Auto-1462
 * 
 * @author nidng1
 * @Date Feb 20, 2013
 */
public class RateInSearchList extends FinanceManagerTestCase{
	private RaFeeScheduleData raFeeDataInfo = new RaFeeScheduleData();
	private FinMgrRaFeeSchMainPage listPage = FinMgrRaFeeSchMainPage.getInstance();

	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		
		// 1. search by Slip
		raFeeDataInfo.productCategory = "Slip";// Don't change!
		this.searchAndVerify();

		// 2. search by List
		raFeeDataInfo.productCategory = "List";// Don't change!
		this.searchAndVerify();
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
	}
	
	private void searchAndVerify(){
		logger.info("Search by produclt category:"+raFeeDataInfo.productCategory);
		listPage.searchByProduct(raFeeDataInfo.productCategory, OrmsConstants.ACTIVE_STATUS);
		
		// verify Rate is exist in search result or not.
		if(listPage.isRateExist()){
			throw new ErrorOnPageException("The column Rate should exist in this page.");
		} else {
			logger.info("Rate is exist in search list.");
		}
	}
}
