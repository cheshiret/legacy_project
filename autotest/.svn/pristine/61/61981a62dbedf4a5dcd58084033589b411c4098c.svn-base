package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.tax.schedule;

import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * Search tax schedule by search criteria as below:
 * 1.Fee Type(Use Fee), Rate Type(Percentage), Customer Type(Senior).
 * 2.Fee Type(Attribute Fee), Rate Type(Percentage), Customer Type(Standard).
 * 3.Fee Type(Transaction Fee) and Transaction Type(No Show).
 * @Preconditions:
 * Need two tax schedules as below:
 * 1.Fee Type(Use Fee), Rate Type(Percentage), Customer Type(Senior).(d_fin_tax_schedule, id:1400)
 * 2.Fee Type(Attribute Fee), Rate Type(Percentage), Customer Type(Standard).(d_fin_tax_schedule, id:1410)
 * 3.Fee Type(Transaction Fee) and Transaction Type(No Show).(d_fin_tax_schedule, id:1430)
 * @SPEC:
 * Search Tax Schedule - Fee Type [TC:049978]
 * Search Tax Schedule - Rate Type [TC:049979]
 * Search Tax Schedule - Customer Type [TC:049980]
 * Search Tax Schedule - Transaction Type [TC:049981]
 * @Task#: Auto-1442
 * 
 * @author nding1
 * @Date  Feb 6 2013
 */
public class VerifySearchResult extends FinanceManagerTestCase {
	private FinMgrTaxSchedulePage schedulePg = FinMgrTaxSchedulePage.getInstance();
	private String feeType, custType, rateType, transType;
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoTaxMainPage();
		fnm.gotoTaxSchedulePage();
		
		// 1.Fee Type(Use Fee), Rate Type(Percentage), Customer Type(Senior).
		feeType = "Use Fee";
		custType = "Senior";
		this.searchTaxSchedule(feeType, custType, null);
		schedulePg.verifySearchResultByColumn("Fee Type", feeType);
		schedulePg.verifySearchResultByColumn("Rate Type", rateType);
		schedulePg.verifySearchResultByColumn("Customer Type", custType);

		// 2.Fee Type(Attribute Fee), Rate Type(Percentage), Customer Type(Standard).
		feeType = "Attribute Fee";
		custType = "Standard";
		this.searchTaxSchedule(feeType, custType, null);
		schedulePg.verifySearchResultByColumn("Fee Type", feeType);
		schedulePg.verifySearchResultByColumn("Rate Type", rateType);
		schedulePg.verifySearchResultByColumn("Customer Type", custType);
		
		// 3.Fee Type(Transaction Fee) and Transaction Type.
		feeType = "Transaction Fee";
		transType = "No Show";
		custType = StringUtil.EMPTY;
		this.searchTaxSchedule(feeType, null, transType);
		schedulePg.verifySearchResultByColumn("Fee Type", FEETYPE_NAME_TRANSACTIONFEE);
		schedulePg.verifySearchResultByColumn("Transaction Type", transType);
		fnm.logoutFinanceManager();
	}
	
	private void searchTaxSchedule(String feeType, String custType, String transType){
		logger.info("Search tax schedule...");
		schedulePg.selectFeeType(feeType);
		if("Transaction Fee".equals(feeType)){
			schedulePg.selectTransactionType(transType);
		} else {
			schedulePg.selectRateType(rateType);
		}
		schedulePg.selectCustomerType(custType);
		
		schedulePg.clickGo();
		ajax.waitLoading();
		schedulePg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		rateType = "Percentage";
	}
}
