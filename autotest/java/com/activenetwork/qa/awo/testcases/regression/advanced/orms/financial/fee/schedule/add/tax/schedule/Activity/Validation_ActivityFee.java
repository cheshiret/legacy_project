package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.tax.schedule.Activity;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.tax.LicMgrTaxScheduleDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.tax.LicMgrTaxScheduleSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.page.AlertDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This cases verified flow for validation of add a tax schedule for activity fee
 * @LinkSetUp:  d_hf_add_activity:id=390(ACTIVITY_NAME='Activity For Tax Schedule 11')
 * @SPEC:[TC:110568] Add Tax Shcedule for Activity Fee  
 * @Task#: Auto-2137
 * @author Phoebe
 * @Date  May 19, 2014
 */
public class Validation_ActivityFee  extends LicenseManagerTestCase{
	private ScheduleData schedule = new ScheduleData();
	private ScheduleData duplicateSchedule = new ScheduleData();
	private LicMgrTaxScheduleDetailPage detailPg = LicMgrTaxScheduleDetailPage.getInstance();
	private LicMgrTaxScheduleSearchPage scheduleListPg = LicMgrTaxScheduleSearchPage.getInstance();
	private boolean passed = true;
	private String err_nameIsEmpty, err_feeTypeIsEmpty, err_startDateIsEmpty,
	err_startDateGreaterThanEnd, err_rateLessThanZero, err_rateMoreThanHundred, err_accountIsEmpty, err_duplicateTaxSchedule; 
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoTaxScheduelListPage();
		
		scheduleListPg.deactiveSchedulesForPrd(schedule.product);
		
		//Tax name is empty
		schedule.taxName = StringUtil.EMPTY;
		String actMsg = lm.addNewTaxSchedule(schedule);
		passed &=  MiscFunctions.compareResult("Name is Empty:", err_nameIsEmpty, actMsg);
		
		//Fee Type is empty
		schedule.taxName = "TaxForActivity";
		detailPg.selectTaxNameAndWaitRefresh(schedule.taxName);
		detailPg.selectFeeTypeAndWaitRefresh(StringUtil.EMPTY);
		actMsg = this.setUpTaxScheduleInDetailPg();
		passed &=  MiscFunctions.compareResult("Fee Type is Empty:", err_feeTypeIsEmpty, actMsg);
		
		//Effective start date is empty
		detailPg.selectFeeTypeAndWaitRefresh(schedule.feeType);
		actMsg = this.setUpEffectiveDate(StringUtil.EMPTY, schedule.effectiveEndDate, false);
		passed &=  MiscFunctions.compareResult("Start date is Empty:", err_startDateIsEmpty, actMsg);
		
		//Effective end date is earlier than start date
		actMsg = this.setUpEffectiveDate(schedule.effectiveDate, DateFunctions.getDateAfterGivenDay(schedule.effectiveDate, -1), true);
		passed &=  MiscFunctions.compareResult("Start date is later than end date:", err_startDateGreaterThanEnd, actMsg);
		
		//Tax rate is less than 0
		detailPg.setEffectiveStartDate(schedule.effectiveDate);
		detailPg.setEffectiveEndDate(schedule.effectiveEndDate);
		detailPg.setAccountRate("-2");
		actMsg = this.setUpTaxScheduleInDetailPg();
		passed &=  MiscFunctions.compareResult("Tax rate is less than zero:", err_rateLessThanZero, actMsg);
		
		//Tax rate is less than 0
		detailPg.setAccountRate("100.01");
		actMsg = this.setUpTaxScheduleInDetailPg();
		passed &=  MiscFunctions.compareResult("Tax rate is more than one hundred percent:", err_rateMoreThanHundred, actMsg);
		
		//Account is empty
		detailPg.setAccountRate(schedule.rate);
		detailPg.selectAccountCode(StringUtil.EMPTY);
		actMsg = this.setUpTaxScheduleInDetailPg();
		passed &=  MiscFunctions.compareResult("Account is empty:", err_accountIsEmpty, actMsg);
		
		//Add two schedule and active them
		detailPg.selectAccountCode(schedule.accountCode);
		schedule.scheduleId = this.addTaxScheduleInDetailPg();
		duplicateSchedule.scheduleId = lm.addNewTaxSchedule(duplicateSchedule);
		scheduleListPg.activeTaxSchedules(schedule.scheduleId, duplicateSchedule.scheduleId);
		err_duplicateTaxSchedule = "The Tax Schedule " + duplicateSchedule.scheduleId+ " cannot be activated because another identical active Tax Schedule " +
				schedule.scheduleId + " exists.";
		
		//Edit one of the schedule the same as another one
		lm.gotoTaxScheduleDetailPage(duplicateSchedule.scheduleId);
		actMsg = this.setUpEffectiveDate(schedule.effectiveDate, schedule.effectiveEndDate, false);
		passed &=  MiscFunctions.compareResult("Duplicate tax schedule infor:", err_duplicateTaxSchedule, actMsg);
		
		if(!passed){
			throw new ErrorOnPageException("Not all the check point is correct, please check the log above!");
		}
		logger.info("All validate is correct!");
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		this.setUpSchedule(schedule);
		
		//Set up the other schedule which will be duplicate after updated
		duplicateSchedule.taxName = "TaxForActivity";
		duplicateSchedule.productCategory = "Activity"; //This is the test point, can not be changed
		duplicateSchedule.feeType = "Activity Fee"; //This is the test point, can not be changed
		duplicateSchedule.productGroup = "FeeAddEditGroup";
		duplicateSchedule.product = "Activity For Tax Schedule 11";
		duplicateSchedule.effectiveDate = DateFunctions.getDateAfterToday(10);
		duplicateSchedule.effectiveEndDate = DateFunctions.getDateAfterToday(11);	  	
		duplicateSchedule.accountCode = "3461.3010.20000.6100.---.---.--.----.----.--------; Accounts Payable - RA Fees";
		duplicateSchedule.rate = "99.78";
		
		err_nameIsEmpty = "The Tax Name is required. Please select the Tax Name from the list provided.";
		err_feeTypeIsEmpty = "The Fee Type is required. Please select the Fee Type from the list provided.";
		err_startDateIsEmpty= "An Effective Start Date for the Tax Schedule is required. Please enter the Effective Start Date using the format Ddd Mmm dd, yyyy in the field provided.";
		err_startDateGreaterThanEnd= "End date should be greater than or equal to start date";
		err_rateLessThanZero= "The Tax Rate must be between 0% and 100%. Please change your entries.";
		err_rateMoreThanHundred= "The Tax Rate must be between 0% and 100%. Please change your entries.";
		err_accountIsEmpty = "An Account for the Tax Schedule is required. Please select the Account from the list provided.";
	}

	private void setUpSchedule(ScheduleData schedule) {
		schedule.taxName = "TaxForActivity";
	 	schedule.productCategory = "Activity"; //This is the test point, can not be changed
	  	schedule.feeType = "Activity Fee"; //This is the test point, can not be changed
	  	schedule.productGroup = "FeeAddEditGroup";
	 	schedule.product = "Activity For Tax Schedule 11";
	 	schedule.effectiveDate = DateFunctions.getDateAfterToday(0);
	 	schedule.effectiveEndDate = DateFunctions.getDateAfterToday(1);	  	
	  	schedule.accountCode = "3461.3010.20000.6100.---.---.--.----.----.--------; Accounts Payable - RA Fees";
	  	schedule.rate = "99.78";
	}
	
	public String setUpTaxScheduleInDetailPg(){
		detailPg.clickApply();
		ajax.waitLoading();
		detailPg.waitLoading();
		String errorMsg = "";
		errorMsg = detailPg.getErrorMsg();
		return errorMsg;
	}
	
	public String setUpEffectiveDate(String startDate, String endDate, boolean withPopup){
		AlertDialogPage alertDialog = AlertDialogPage.getInstance();
		detailPg.setEffectiveStartDate(startDate);
		detailPg.setEffectiveEndDate(endDate);
		String errMsg = "";
		if(withPopup){
			Browser.sleep(5);
			if(alertDialog.exists()){
				Browser.sleep(2);
				errMsg = alertDialog.text().replace("OK", "").trim();
				alertDialog.clickOK();
				ajax.waitLoading();
				detailPg.waitLoading();
			}
		}
		if(!withPopup){
			errMsg = this.setUpTaxScheduleInDetailPg();
		}
		return errMsg;
	}
	
	public String addTaxScheduleInDetailPg(){
		detailPg.clickApply();
		ajax.waitLoading();
		detailPg.waitLoading();
		String scheduleId = "";
		scheduleId = detailPg.getTaxScheduleId();
		detailPg.clickOk();
		ajax.waitLoading();
		scheduleListPg.waitLoading();
		return scheduleId;
	}
}
