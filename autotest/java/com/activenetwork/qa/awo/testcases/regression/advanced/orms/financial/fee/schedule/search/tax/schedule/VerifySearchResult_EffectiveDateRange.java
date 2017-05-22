package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.search.tax.schedule;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: This case is used to verify when search by the range of effect start date
 * @Preconditions:A List named 'EditTaxSchdStatusUsed' has been created, the precondition for this list is that a slip code is 'FULT' has been added
 * 				d_inv_add_list:id=60(LISTNAME='EditTaxSchdStatusUsed')
 *				d_inv_add_slip:id=290(SLIPNAME='For Used List Test')
 * @SPEC:Search Tax Schedule - Effective Date Range [TC:049975]
 * @Task#: Auto-1441
 * @author Phoebe
 * @Date  Feb 7 2013
 */
public class VerifySearchResult_EffectiveDateRange extends FinanceManagerTestCase {
	private FinMgrTaxSchedulePage schedulePg = FinMgrTaxSchedulePage.getInstance();
	private String fromDate, toDate;
	private Tax tax = new Tax();
	private ScheduleData schedule = new ScheduleData();
	@Override
	public void execute() {
		// Login finance manager and go to tax schedule list page
		fnm.loginFinanceManager(login);
		fnm.gotoTaxMainPage();
		
		//Check if the Tax Type exists in System, if not, create it
		if(!fnm.checkTaxExistOrNotFromDB(tax.taxName, schema)) {
			fnm.addNewTax(tax);
		}
		
		//Add a new schedule for the tax
	    fnm.addNewTaxSchedule(schedule, true);
		
		//Just search by effective date start from
		fromDate = DateFunctions.getDateAfterGivenDay(schedule.startDate, -2);
		toDate = StringUtil.EMPTY;
		searchByEffectiveStartDate(fromDate, toDate);
		verifyResultOfEffectvieStartDateColumn(fromDate, toDate);
		
		//Just search by effective date start to
		fromDate = StringUtil.EMPTY;
		toDate = DateFunctions.getDateAfterGivenDay(schedule.endDate, 2);
		searchByEffectiveStartDate(fromDate, toDate);
		verifyResultOfEffectvieStartDateColumn(fromDate, toDate);
		
		//Search by both effective date start from and to
		fromDate = StringUtil.EMPTY;
		toDate = DateFunctions.getDateAfterGivenDay(schedule.endDate, 2);
		searchByEffectiveStartDate(fromDate, toDate);
		verifyResultOfEffectvieStartDateColumn(fromDate, toDate);
		
		fnm.logoutFinanceManager();
	}

	private void searchByEffectiveStartDate(String fromDate, String toDate) {
		schedulePg.selectDateTypeOption("Effective Start Date");
		schedulePg.setFromDate(fromDate);
		schedulePg.setToDate(toDate);
		schedulePg.clickGo();
		ajax.waitLoading();
		schedulePg.waitLoading();
	}

	/**
	 * Verify search result for form date and to date
	 * @param fromDate
	 * @param toDate
	 */
	private void verifyResultOfEffectvieStartDateColumn(String fromDate, String toDate) {
		boolean passed = true;
		List<String> start_resultlist = schedulePg.getColumnByName("Effective Start Date");
		for (int i = 1; i < start_resultlist.size(); i++) {
			String result = start_resultlist.get(i);
			if(StringUtil.notEmpty(fromDate)&& 
					DateFunctions.compareDates(fromDate, result) == 1){
				passed = false;
				logger.error("The " + i + "th record is not correct, for date:" + result + " is less than from date:" + fromDate);
			}
			if(StringUtil.notEmpty(toDate)&& 
					DateFunctions.compareDates(toDate, result) == -1){
				passed = false;
				logger.error("The " + i + "th record is not correct, for date:" + result + " is greater than to date:" + toDate);
			}
		}
		if(!passed){
			throw new ErrorOnPageException("Records searched by Effective Start date is not correct, please check the log above!");
		}
		logger.info("Search result for fromDate:" + fromDate + " and toDate:" + toDate + " is correct!");
	}
	
	
	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.contract = "NC Contract";
	  	login.location = "Administrator - Auto/North Carolina State Parks";
	  	
	  	schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facility = fnm.getFacilityName("552903", schema); //Jordan Lake State Rec Area
	  	//initialize Tax info
	  	tax.taxName = "verify_searchSDate";
	  	tax.taxCode = "VerifySearchSDate";
	  	tax.taxDescription = "verify search by start date";
	  	tax.taxRateType ="Percentage";
	  	tax.feeTypes.add("Transaction Fee");
	  	
	  	schedule.location = facility;
	 	schedule.locationCategory = "Park";
	 		 	
	 	schedule.taxName = tax.taxName;
	 	schedule.productCategory = "List"; //This is the test point, can not be changed
	  	schedule.feeType = "Transaction Fee"; 
	  	schedule.appPrdCategory = "Slip";
	  	schedule.product = "EditTaxSchdStatusUsed";
	  	schedule.tranType = "Add to Transfer List";
	  	schedule.startDate = "3/1/2010";
	  	schedule.endDate = "3/31/2010";
	  	schedule.customerType = "Standard";
	  	schedule.accountCode = "2000.1601.000300000; Default Overpayment Deposit";
	  	schedule.rate = "83.83";
	}
}
