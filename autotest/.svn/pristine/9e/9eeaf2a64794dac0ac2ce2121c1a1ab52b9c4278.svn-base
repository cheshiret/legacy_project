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
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: It is to check the validations when add tax schedule for slip product,the validate:
 *               1.Make sure the tax which will be assigned the schedule to is already exist
 *               2.Set account code  as blank and verify the error message
 *               3.Set account rate a negative number and verify the error message
 *               4.Set account rate bigger than 100 and verify the error message
 *               5.Set account rate equal to 100, check the schedule is added successfully
 *               6.Set account rate equal to 0, check the schedule is added successfully
 * @Preconditions:
 * 1. A tax has been setup:add_slip_schedule_validate_accountRel
 * @SPEC: TC:049785,049786
 * @Task#: Auto-1435
 * @author Phoebe Chen
 * @Date  Jan 09, 2013
 */
public class Add_Slip_Validation_Account_Related extends FinanceManagerTestCase{
	private Tax tax = new Tax();
	private ScheduleData schedule = new ScheduleData();
	FinMgrTaxAssignLocSearchPage assignLocSrchPg = FinMgrTaxAssignLocSearchPage
			.getInstance();
	FinMgrTaxSchDetailPage schDetailsPg = FinMgrTaxSchDetailPage
			.getInstance();
	FinMgrTaxSchedulePage taxSchPg = FinMgrTaxSchedulePage.getInstance();
	String accountCodeBlankError;
	String accountRateInvalidNumberError;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
	    fnm.gotoTaxMainPage();
	    
	    //Check data prepare 
	    if(!fnm.checkTaxExistOrNotFromDB(tax.taxName, schema)){
			 fnm.addNewTax(tax);
		 }
	    
	    fnm.addNewTaxSchedule(schedule, false);
	    
	    //Set account code  as blank and verify the error message
	    schDetailsPg.setAccountCodeAsBlank();
	    verifyErrorMessageAfterGiveValidteInfo("Account code blank",accountCodeBlankError);
	    
	    //Set account rate a negative number and verify the error message
	    schDetailsPg.selectAccountCode(schedule.accountCode);
	    schedule.rate = "-1";
	    schDetailsPg.setRate(schedule.rate);
	    verifyErrorMessageAfterGiveValidteInfo("Account rate negative",accountRateInvalidNumberError);
	    
	    //Set account rate bigger than 100 and verify the error message
	    schedule.rate = "100.002";
	    schDetailsPg.setRate(schedule.rate);
	    verifyErrorMessageAfterGiveValidteInfo("Account rate negative",accountRateInvalidNumberError);
	    
	    //Set account rate equal to 100, check the schedule is added successfully
	    schedule.rate = "100";
	    schDetailsPg.setRate(schedule.rate);
	    verifyValidationIsPassedAndScheduleAddedSuccesfully();
		
	    //Set account rate equal to 0, check the schedule is added successfully
	    schedule.rate = "0";
	    schedule.scheduleId = fnm.addNewTaxSchedule(schedule);
	    verifyScheduleInfoInAssignedList();
	    
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
	  	tax.taxName = "add_slip_schedule_validate_accountRel";
	  	tax.taxCode = "ValidSlipAccount";
	  	tax.taxDescription = "add tax schedule Slip and verified";
	  	tax.taxRateType ="Percentage";
	  	tax.feeTypes.add("Attribute Fee");
	  	
	  	schedule.location = fnm.getFacilityName("552861", schema);//"South Mountains State Park";
	 	schedule.locationCategory = "Park";
	 	
	 	schedule.taxName = tax.taxName;
	 	schedule.productCategory = "Slip";   //This is the test point, can not be changed
	 	schedule.productGroup = "Full attributes";
	 	schedule.product = "All";
	  	schedule.feeType = "Attribute Fee"; //This is the test point, can not be changed
	  	schedule.startDate = "2018-1-1";
	  	schedule.endDate = "2018-1-31";
	  	schedule.rateType = "Percentage";
	  	schedule.tranType = "All";
	  	schedule.marinaRateType = "Seasonal";
	  	schedule.customerType = "Senior";
	  	schedule.accountCode = "2000.1601.000211940; sales tax payable";
	  	schedule.rate = "65.56";
	  	schedule.activeStatus = OrmsConstants.NO_STATUS;
	  	
	  	//Set error
		accountCodeBlankError = "An Account for the Tax Schedule is required. Please select the Account from the list provided.";
		accountRateInvalidNumberError = "The Tax Rate must be between 0% and 100%. Please change your entries.";
	}
	
	private void verifyValidationIsPassedAndScheduleAddedSuccesfully() {
		FinMgrTaxSchDetailPage finTaxSchDetailPg = FinMgrTaxSchDetailPage.getInstance();
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
		logger.info("Tax Schedule list info is correct");
		fnm.gotoTaxScheduleDetailPageFromListPage(schedule.scheduleId);
		if(!finTaxSchDetailPg.checkScheduleDetailInfo(schedule)){
			throw new ErrorOnPageException(schedule.scheduleId +" for tax(" + schedule.taxName + ") detail information is not quite right, please check the log above");
		}
		logger.info("Tax Schedule detail info is correct");
		finTaxSchDetailPg.clickCancel();
		taxSchPg.waitLoading();
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

	private void verifyScheduleInfoInAssignedList() {
		FinMgrTaxSchDetailPage finTaxSchDetailPg = FinMgrTaxSchDetailPage.getInstance();
		if(!taxSchPg.checkScheduleInfoInList(schedule)){
			throw new ErrorOnPageException(schedule.scheduleId +" not been found or the information is wrong in the assigned schedule list for tax(" + schedule.taxName + "), please check the log above");
		}
		logger.info("Tax Schedule list info is correct.");
		fnm.gotoTaxScheduleDetailPageFromListPage(schedule.scheduleId);
		if(!finTaxSchDetailPg.checkScheduleDetailInfo(schedule)){
			throw new ErrorOnPageException(schedule.scheduleId +" for tax(" + schedule.taxName + ") detail information is not quite right, please check the log above");
		}
		logger.info("Tax Schedule detail info is correct");
	}
}
