package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee.site;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.NumberUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify Rates(Rate(New Units), Rate(Changed Units), Rate(Flat Amount) and Maximum Fee Amount) validate correctly when adding transaction fee schedule with Apply Fee - 'Per Unit'
 * @Preconditions:
 * @SPEC: Add Fee Schedule - Rate Validation [TC:056924]
 * @Task#: AUTO-1851
 * 
 * @author qchen
 * @Date  Aug 19, 2013
 */
public class RateValidation extends FinanceManagerTestCase {

	private FeeScheduleData schedule = new FeeScheduleData();
	private String errorMsg_baseRateIsInvalid, errorMsg_changedUnitsRateIsInvalid, errorMsg_flatAmountRateIsInvalid, errorMsg_maximumFeeAmountIsInvalid;
	private String validRate, actualMsg;
	private FinMgrFeeSchDetailPage detailsPage = FinMgrFeeSchDetailPage.getInstance();
	private String invalidRates[];
	private boolean result = true;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		detailsPage.setupTransactionFeeContent(schedule, false);
		
		//precondition: select Transaction Type as 'Change Dates' and Apply Fee as 'Per Unit' and Rate Applies To as 'New/Changed Unit(s)'
		//1. Transaction Fee Rate for New Units has been left blank or entered value is not numeric value greater than or equal to $0.00
		for(int i = 0; i < invalidRates.length; i ++) {
			detailsPage.setTransactionFee(invalidRates[i]);
			actualMsg = this.triggerErrorMessage();
			result &= MiscFunctions.compareResult("Error message of Rate (New Units) is invalid", errorMsg_baseRateIsInvalid, actualMsg);
		}
		
		//2. Transaction Fee Rate for Changed Units has been left blank or entered value is not numeric value greater than or equal to $0.00
		detailsPage.setTransactionFee(validRate);
		for(int i = 0; i < invalidRates.length; i ++) {
			detailsPage.setTransactionFeeChangedUnits(invalidRates[i]);
			actualMsg = this.triggerErrorMessage();
			result &= MiscFunctions.compareResult("Error message of Rate (Changed Units) is invalid", errorMsg_changedUnitsRateIsInvalid, actualMsg);
		}
		
		//3. Transaction Fee Rate for Flat Amount has been specified and the entered value is not numeric value greater or equal to $0.00
		detailsPage.setTransactionFeeChangedUnits(validRate);
		for(int i = 1; i < invalidRates.length; i ++) {//IMPORTANT: the Rate (Flat Amount) can be blank, so the invalid input must be from 1
			detailsPage.setTransactionFeeFlatAmount(invalidRates[i]);
			actualMsg = this.triggerErrorMessage();
			result &= MiscFunctions.compareResult("Error message of Rate (Flat Amount) is invalid", errorMsg_flatAmountRateIsInvalid, actualMsg);
		}
		
		//4. set Maximum Fee Restriction as 'Flat' or 'Combination of Flat and Penalty Charges', Maximum Fee Amount has been left blank
		//or entered value is not numeric value greater than or equal to $0.00
		detailsPage.setTransactionFeeFlatAmount(validRate);
		schedule.maximumFeeRestriction = "Flat";
		detailsPage.selectMaximumFeeRestriction(schedule.maximumFeeRestriction);
		for(int i = 0; i < invalidRates.length; i ++) {//IMPORTANT: the Rate (Flat Amount) can be blank, so the invalid input must be from 1
			detailsPage.setMaximumFeeRate(invalidRates[i]);
			actualMsg = this.triggerErrorMessage();
			result &= MiscFunctions.compareResult("Error message of Maximum Fee Amount is invalid", errorMsg_maximumFeeAmountIsInvalid, actualMsg);
		}
		
		if(!result) {
			throw new ErrorOnPageException("Not all checkpoints are passed, please refer log for details info.");
		} else logger.info("All checkpoints are PASSED.");
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "KY Contract";
		login.location = "Administrator - Auto/Commonwealth of Kentucky";
		
		validRate = "14";
		invalidRates = new String[] {StringUtil.EMPTY, StringUtil.getRandomString(5, false), "-" + NumberUtil.getRandomInt(10, 100)};
		
		//transaction fee schedule info
		String facilityID = "90800";//KENTUCKY HORSE PARK
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "KY";
		schedule.location = DataBaseFunctions.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		schedule.productCategory = "Site";
		schedule.feeType = "Transaction Fee";
		schedule.effectDate = DateFunctions.getDateAfterToday(-10);
		schedule.tranType = "Change Dates";
		schedule.tranOccur = "All";
		schedule.tranFeeOption = "Per Unit";//IMPORTANT
		schedule.rateApplyTo = OrmsConstants.RATE_APPLIES_TO_NEW_CHANGED_UNITS;//IMPORTANT
		schedule.tranFee = validRate;
		schedule.tranFeeChangedUnits = validRate;
		schedule.tranFeeFlatAmount = validRate;
		
		errorMsg_baseRateIsInvalid = "The Rate (New Units) must have a value greater than or equal to $0.00. Please change your entries.";
		errorMsg_changedUnitsRateIsInvalid = "The Rate (Changed Units) must have a value greater than or equal to $0.00. Please change your entries.";
		errorMsg_flatAmountRateIsInvalid = "The Rate (Flat Amount) must have a value greater than or equal to $0.00. Please change your entries.";
		errorMsg_maximumFeeAmountIsInvalid = "The Maximum Fee Amount must have a value greater than or equal to $0.00. Please change your entries.";
	}
	
	private String triggerErrorMessage() {
		detailsPage.clickApply();
		detailsPage.waitLoading();
		String msg = detailsPage.getErrorMsg();
		
		return msg;
	}
}
