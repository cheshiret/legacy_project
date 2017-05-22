package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.tax.schedule;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxAssignLocSearchPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.tax.FinMgrTaxSchedulePage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: It is to check the validations when add tax schedule for list product,the validate:
 *               1.Make sure the tax which will be assigned the schedule to is already exist
 *               2.Set tax name as blank and verify the error message
 *               3.Set fee type as blank and verify the error message
 *               4.Set effective start date as blank and verify the error message
 *               5.Set effective start date greater than end date and verify the error message
 *               6.Set effective start date equal to effective end date, check the schedule is added successfully
 * @Preconditions:
 * 1. A tax has been setup:add_list_schedule_validate_mainFields
 * @SPEC: TC:049787,049788,049789
 * @Task#: Auto-1435
 * @author Phoebe Chen
 * @Date  Jan 09, 2013
 */
public class Add_List_Validation_Main_Fields extends FinanceManagerTestCase{
	private Tax tax = new Tax();
	private ScheduleData schedule = new ScheduleData();
	FinMgrTaxAssignLocSearchPage assignLocSrchPg = FinMgrTaxAssignLocSearchPage
			.getInstance();
	FinMgrTaxSchDetailPage schDetailsPg = FinMgrTaxSchDetailPage
			.getInstance();
	FinMgrTaxSchedulePage taxSchPg = FinMgrTaxSchedulePage.getInstance();
	String taxNameBlankError;
	String feeTypeBlankError;
	String startDateBlankError;
	String startDateLateThanEndDateError;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
	    fnm.gotoTaxMainPage();
	    
	    //Check data prepare 
	    if(!fnm.checkTaxExistOrNotFromDB(tax.taxName, schema)){
			 fnm.addNewTax(tax);
		 }
	    
	    fnm.addNewTaxSchedule(schedule, false);
	    
	    //Set tax name as blank and verify the error message
//	    schDetailsPg.setTaxNameAsBlank();
	    verifyErrorMessageAfterGiveValidteInfo("Tax Name blank",taxNameBlankError);
	    
	    //Set fee type as blank and verify the error message
	    schedule.taxName = tax.taxName;
	    schDetailsPg.selectTaxName(schedule.taxName);
	    schDetailsPg.waitLoading();
	    schDetailsPg.setFeeTypeAsBlank();
	    verifyErrorMessageAfterGiveValidteInfo("Fee type blank",feeTypeBlankError);
	    
	    //Set effective start date as blank and verify the error message
	    schDetailsPg.selectFeeType(schedule.feeType);
	    schDetailsPg.waitLoading();
	    schDetailsPg.setEffectiveStartDate(StringUtil.EMPTY);
	    verifyErrorMessageAfterGiveValidteInfo("Effective start date blank",startDateBlankError);
	    
	    //Set effective start date greater than end date and verify the error message
	    schedule.startDate = "2020-12-1";
	    schDetailsPg.setEffectiveStartDate(schedule.startDate);
	    verifyErrorMessageAfterGiveValidteInfo("Start Date greater than end date",startDateLateThanEndDateError);
	    
	    //Set effective start date equal to effective end date, check the schedule is added successfully
	    schedule.startDate = schedule.endDate;
	    schDetailsPg.setEffectiveStartDate(schedule.startDate);
	    verifyValidationIsPassedAndScheduleAddedSuccesfully();
	    
		//Logout finance manager
	    fnm.logoutFinanceManager();
	}


	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.contract = "NC Contract";
	  	login.location = "Administrator - Auto/North Carolina State Parks";
	  	
	  	schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
	  	
	  	//initialize Tax info
	  	tax.taxName = "add_list_schedule_validate_mainFields";
	  	tax.taxCode = "ValidListMain";
	  	tax.taxDescription = "add tax schedule List and verified";
	  	tax.taxRateType ="Percentage";
	  	tax.feeTypes.add("Attribute Fee");
	  	tax.feeTypes.add("Transaction Fee");
	  	tax.feeTypes.add("Use Fee");
	  	tax.feeTypes.add("Entrance-DayUse Fee");
	  	tax.feeTypes.add("POS Fee");
	  	
	  	schedule.location = fnm.getFacilityName("552861", schema);//"South Mountains State Park";
	 	schedule.locationCategory = "Park";
	 	
	 	schedule.taxName = StringUtil.EMPTY;
	 	schedule.productCategory = "List"; //This is the test point, can not be changed
	 	schedule.productGroup = "All";
	 	schedule.product = "All";
	 	schedule.appPrdCategory = "Slip";
	  	schedule.feeType = "Transaction Fee"; //This is the test point, can not be changed
	  	schedule.startDate = "2008-2-1";
	  	schedule.endDate = "2008-12-31";
	  	schedule.rateType = "Percentage";
	  	schedule.tranType = "Add to Waiting List";
	  	schedule.customerType = "Standard";
	  	
	  	schedule.accountCode = "2000.1601.000300000; Default Overpayment Deposit";
	  	schedule.rate = "99.78";
	  	schedule.activeStatus = OrmsConstants.NO_STATUS;
	  	
	  	//Set error
	  	taxNameBlankError = "The Tax Name is required. Please select the Tax Name from the list provided.";
		feeTypeBlankError = "The Fee Type is required. Please select the Fee Type from the list provided.";
		startDateBlankError = "An Effective Start Date for the Tax Schedule is required. Please enter the Effective Start Date using the format Ddd Mmm dd, yyyy in the field provided.";
		startDateLateThanEndDateError = "The Effective Start Date must not be later than the Effective End Date. Please change your entries.";
	}
		
	private void verifyErrorMessageAfterGiveValidteInfo(String msgType, String expectError) {
		schDetailsPg.clickOk();
		ajax.waitLoading();
		schDetailsPg.waitLoading();
		String errorMessage = schDetailsPg.getErrorMessage();
		if(!expectError.equalsIgnoreCase(errorMessage)){
			throw new ErrorOnPageException(msgType + " error message is not correct, EXPECT:" + expectError + "    ACTURAL:" + errorMessage);
		}
	}
	
	private void verifyValidationIsPassedAndScheduleAddedSuccesfully() {
		schDetailsPg.clickApply();
		ajax.waitLoading();
		schDetailsPg.waitLoading();
	    schedule.scheduleId = schDetailsPg.getTaxSchID();
	    schDetailsPg.clickOk();
		ajax.waitLoading();
		taxSchPg.waitLoading();
		if(!taxSchPg.checkScheduleInfoInList(schedule)){
			throw new ErrorOnPageException(schedule.scheduleId +" not been found or the information is wrong in the assigned schedule list for tax(" + schedule.taxName + "), please check the log above");
		}
	}
}
