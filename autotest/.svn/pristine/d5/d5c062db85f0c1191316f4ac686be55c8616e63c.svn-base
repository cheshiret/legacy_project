package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee.site;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the rates(Rate (New Units), Rate (Changed Units), Rate (Flat Amount), 
 * 					Other Rate (New Units), Other Rate (Changed Units), Other Rate (Flat Amount), and Maximum Fee Amount, etc.) validate correctly
 * @Preconditions:
 * @SPEC: Add RA Fee Schedule - Rate Validation [TC:056895]
 * @Task#: AUTO-1851
 * 
 * @author qchen
 * @Date  Aug 19, 2013
 */
public class RateValidation extends FinanceManagerTestCase {
	
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private FinMgrRaFeeSchDetailPage detailsPage = FinMgrRaFeeSchDetailPage.getInstance();
	private String errorMsg_rateNewUnitsIsBlank, errorMsg_rateNewUnitsIsInvalid, errorMsg_rateChangedUnitsIsInvalid, errorMsg_rateFlatAmountIsInvalid,
							errorMsg_otherRateNewUnitsIsInvalid, errorMsg_otherRateChangedUnitsIsInvalid, errorMsg_otherRateFlatAmountIsInvalid,
							errorMsg_maximumFeeAmountIsInvalid, errorMsg_baseRateLessThanOtherBaseRate;
	private String validRate;
	private boolean result = true;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		fnm.gotoRAFeeScheduleDetailPgByAddNew(schedule.location, schedule.locationCategory);
		detailsPage.selectPrdCategory(schedule.productCategory);
		detailsPage.waitLoading();
		detailsPage.setupRaFeeSchDetailInfo(schedule, false);
		
		//1. select Rate Applies To as 'New/Changed Unit(s)', and Rate (New Units) is left blank or not numeric value greater than or equal to $0.00
		detailsPage.setBaseRate(StringUtil.EMPTY);
		String actualMsg = this.triggerErrorMessage();
		result &= MiscFunctions.compareResult("Error message of Rate (New Units) is left blank", errorMsg_rateNewUnitsIsBlank, actualMsg);
		
		String baseRates[] = new String[] {StringUtil.getRandomString(3, false), "-12"};
		for(String s : baseRates) {
			detailsPage.setBaseRate(s);
			actualMsg = this.triggerErrorMessage();
			result &= MiscFunctions.compareResult("Error message of Rate (New Units) is invalid", errorMsg_rateNewUnitsIsInvalid, actualMsg);	
		}
		
		//2. select Rate Applies To as 'New/Changed Unit(s)', and Rate (Changed Units) is left blank or not numeric value greater than or equal to $0.00
		schedule.baseRate = validRate;
		detailsPage.setBaseRate(schedule.baseRate);
		String changedUnitsRates[] = new String[] {StringUtil.EMPTY, StringUtil.getRandomString(4, false), "-11"};
		for(String s : changedUnitsRates) {
			detailsPage.setRateChangedUnits(s);
			actualMsg = this.triggerErrorMessage();
			result &= MiscFunctions.compareResult("Error message of Rate (Changed Units) is invalid", errorMsg_rateChangedUnitsIsInvalid, actualMsg);
		}
		
		//3. select Rate Applies To as 'New/Changed Unit(s)', and Rate (Flat Amount) is specific but the entered value is not numeric value greater than or equal to $0.00
		schedule.changedUnitsRate = validRate;
		detailsPage.setRateChangedUnits(schedule.changedUnitsRate);
		String flatAmounts[] = new String[] {"-100", StringUtil.getRandomString(5, false)};
		for(String s : flatAmounts) {
			detailsPage.setRateFlatAmount(s);
			actualMsg = this.triggerErrorMessage();
			result &= MiscFunctions.compareResult("Error message of Rate (Flat Amount) is invalid", errorMsg_rateFlatAmountIsInvalid, actualMsg);
		}
		
		//4. select Rate Applies To as 'New/Changed Unit(s)', and 'Other Rate (New Units)' is invalid
		schedule.changedUnitsOtherRate = validRate;
		detailsPage.clickAddRate();
		detailsPage.waitLoading();
		detailsPage.setChangedUnitsRateOfOtherRate(schedule.changedUnitsOtherRate, 1);
		detailsPage.setBaseRateOfOtherRate(StringUtil.getRandomString(3, false), 1);
		actualMsg = this.triggerErrorMessage();
		result &= MiscFunctions.compareResult("Error message of Other Rate (New Units) is invalid", errorMsg_otherRateNewUnitsIsInvalid, actualMsg);
		
		//5. select Rate Applies To as 'New/Changed Unit(s)', and 'Other Rate (Changed Units)' is invalid
		detailsPage.setBaseRateOfOtherRate(validRate, 1);
		detailsPage.setChangedUnitsRateOfOtherRate("-10", 1);
		actualMsg = this.triggerErrorMessage();
		result &= MiscFunctions.compareResult("Error message of Other Rate (Changed Units) is invalid", errorMsg_otherRateChangedUnitsIsInvalid, actualMsg);
		
		//6. select Rate Applies To as 'New/Changed Unit(s)', and 'Other Rate (Flat Amount)' is invalid
		detailsPage.setChangedUnitsRateOfOtherRate(validRate, 1);
		detailsPage.setFlatAmountRateOfOtherRate(StringUtil.getRandomString(5, false), 1);
		actualMsg = this.triggerErrorMessage();
		result &= MiscFunctions.compareResult("Error message of Other Rate (Flat Amount) is invalid", errorMsg_otherRateFlatAmountIsInvalid, actualMsg);
		
		//7. select Rate Applies To as 'New/Changed Unit(s)', and Base Rate is less than Other Base Rate
		schedule.baseRate = validRate;
		schedule.otherRate = String.valueOf(Integer.parseInt(schedule.baseRate) + 1);
		detailsPage.setBaseRate(schedule.baseRate);
		detailsPage.setBaseRateOfOtherRate(schedule.otherRate, 1);
		actualMsg = this.triggerErrorMessage();
		result &= MiscFunctions.compareResult("Error message of Base Rate less than Other Base Rate", errorMsg_baseRateLessThanOtherBaseRate, actualMsg);
		
		//8. Maximum Fee Restriction is selected as 'Flat', and Maximum Fee Amount has been left blank or entered value is not numeric value greater than or equal to $0.00
		detailsPage.clickRemoveRate();
		detailsPage.waitLoading();
		detailsPage.selectMaximumFeeRestriction("Flat");
		detailsPage.waitLoading();
		String maxFeeAmounts[] = new String[] {StringUtil.EMPTY, StringUtil.getRandomString(6, false), "-99"};
		for(String s : maxFeeAmounts) {
			detailsPage.setMaximumFeeAmount(s);
			actualMsg = this.triggerErrorMessage();
			result &= MiscFunctions.compareResult("Error message of Maximum Fee Amount is invalid", errorMsg_maximumFeeAmountIsInvalid, actualMsg);
		}
		
		if(!result) {
			throw new ErrorOnPageException("Not all checkpoints are passed, please refer to log for details info.");
		} else logger.info("All checkpoints are PASSED.");
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "KY Contract";
		login.location = "Administrator - Auto/Commonwealth of Kentucky";
		
		validRate = "10";
		
		String facilityID = "90800";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "KY";
		schedule.location = DataBaseFunctions.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		schedule.productCategory = "Site";
		schedule.effectDate = DateFunctions.getDateAfterToday(-10);
		schedule.reverseAndRepriceIndicator = false;
		schedule.tranType = "Change Dates";//IMPORTANT
		schedule.applyRate = "Per Unit";//IMPORTANT
		schedule.rateAppliesTo = OrmsConstants.RATE_APPLIES_TO_NEW_CHANGED_UNITS;
		schedule.changedUnitsRate = validRate;
		
		//error message
		errorMsg_rateNewUnitsIsBlank = "The Base Rate is required. Please enter the Base Rate in the field provided";
		errorMsg_rateNewUnitsIsInvalid = "The Rate (New Units) must have a value greater than or equal to $0.00. Please change your entries.";
		errorMsg_rateChangedUnitsIsInvalid = "The Rate (Changed Units) must have a value greater than or equal to $0.00. Please change your entries.";
		errorMsg_rateFlatAmountIsInvalid = "The Rate (Flat Amount) must have a value greater than or equal to $0.00. Please change your entries.";
		errorMsg_otherRateNewUnitsIsInvalid = "The Threshold Rate (New Units) must have a value greater than or equal to $0.00. Please change your entries.";
		errorMsg_otherRateChangedUnitsIsInvalid = "The Threshold Rate (Changed Units) must have a value greater than or equal to $0.00. Please change your entries.";
		errorMsg_otherRateFlatAmountIsInvalid = "The Threshold Rate (Flat Amount) must have a value greater than or equal to $0.00. Please change your entries.";
		errorMsg_maximumFeeAmountIsInvalid = "The Maximum Fee Amount must have a value greater than or equal to $0.00. Please change your entries.";
		errorMsg_baseRateLessThanOtherBaseRate = "The Base Rate cannot be less than any of the corresponding Threshold Rates. Please change your entries.";
	}
	
	private String triggerErrorMessage() {
		detailsPage.clickApply();
		detailsPage.waitLoading();
		String msg = detailsPage.getErrorMsg();
		
		return msg;
	}
}
