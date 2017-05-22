package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.uesfee.marina;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFee;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFeeCustomDuration;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case verify unit is 'Length Range' when add use fee for slip.
 * 1.Range Value less than 1;
 * 2.Range Value is empty;
 * 3.Rate is negative value;
 * 4.Rate is empty;
 * 5.Duplicate range;
 * 6.Rate for custom duration is empty;
 * 7.Custom Quantity for custom duration is empty;
 * 8.Duplicate custom duration.
 * @Preconditions:
 * @SPEC: Add Use Fee Schedule - Unit "Length Range" validation [TC:043388]
 * @Task#: Auto-1327
 * 
 * @author nding1
 * @Date  Jan 14, 2013
 */
public class VerifyUnitLengthRange extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData(true);
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	private String errorMsg_range0, errorMsg_rangeEmpty, errorMsg_negativeRate, errorMsg_rateEmpty, errorMsg_custDuraRateEmpty,
				   errorMsg_custDuraQuantityEmpty, errorMsg_duplicateRange, errorMsg_duplicateCustDuration;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		
		// 1.unit is "Length Range" and range is 0.
		List<SlipFee> slipFees = new ArrayList<SlipFee>();
		SlipFee slipFee = schedule.new SlipFee();
		slipFee.dailyNightlyFee = "5";
		slipFees.add(slipFee);// already exist one range.
		slipFee = schedule.new SlipFee();
		slipFee.rangeFeet = "0";// Don't change!
		slipFees.add(slipFee);
		schedule.slipFees = slipFees;
		String message = this.addSlipUseFee();
		verifyMessage("Error Message - range is 0", errorMsg_range0, message);

		// 2.unit is "Length Range" and range is empty.
		schedule.slipFees.get(1).rangeFeet = StringUtil.EMPTY;// Don't change!
		schedule.slipFees.get(1).weeklyFee = "7.77";
		this.gotoFeeMainPage();
		message = this.addSlipUseFee();
		verifyMessage("Error Message - range is empty", errorMsg_rangeEmpty, message);
		
		// 3.rate is negative value
		schedule.slipFees.remove(1);
		schedule.slipFees.get(0).dailyNightlyFee = "-3.65";// Don't change!
		this.gotoFeeMainPage();
		message = this.addSlipUseFee();
		verifyMessage("Error Message - rate is negative", errorMsg_negativeRate, message);

		// 4.Enter a valid Range Value and keep Rate is empty
		schedule.slipFees.get(0).dailyNightlyFee = StringUtil.EMPTY;// Don't change!
		this.gotoFeeMainPage();
		message = this.addSlipUseFee();
		verifyMessage("Error Message - rate is empty", errorMsg_rateEmpty, message);

		// 5.Two same range.
		slipFees = new ArrayList<SlipFee>();
		slipFee = schedule.new SlipFee();
		slipFee.rangeFeet = "1";
		slipFee.dailyNightlyFee = "5.97";
		slipFee.weeklyFee = "55";
		slipFee.monthlyFee = "109";
		slipFees.add(slipFee);
		slipFees.add(slipFee);
		schedule.slipFees = slipFees;
		this.gotoFeeMainPage();
		message = this.addSlipUseFee();
		verifyMessage("Error Message - duplicate range", errorMsg_duplicateRange, message);
		
		// 6.Add a custom duration, enter valid Custom Quantity and keep Rate as empty
		SlipFeeCustomDuration customDuration = new SlipFeeCustomDuration();
		customDuration.customDuration = "1";
		customDuration.rate = StringUtil.EMPTY;// Don't change!
		List<SlipFeeCustomDuration> customDurations = new ArrayList<SlipFeeCustomDuration>();
		customDurations.add(customDuration);
		schedule.slipFees.get(0).customDurations = customDurations;
		schedule.slipFees.remove(1);
		this.gotoFeeMainPage();
		message = this.addSlipUseFee();
		verifyMessage("Error Message - rate for custom duration is empty", errorMsg_custDuraRateEmpty, message);

		// 7.Add a custom duration, enter Rate and keep Custom Quantity as empty
		schedule.slipFees.get(0).customDurations.get(0).customDuration = StringUtil.EMPTY;// Don't change!
		schedule.slipFees.get(0).customDurations.get(0).rate = "2.3";
		this.gotoFeeMainPage();
		message = this.addSlipUseFee();
		verifyMessage("Error Message - Quantity for custom duration is empty", errorMsg_custDuraQuantityEmpty, message);
		
		// 8.Two same custom duration.
		customDurations = new ArrayList<SlipFeeCustomDuration>();
		customDuration = new SlipFeeCustomDuration();
		customDuration.customDuration = "1";
		customDuration.rate = "2.58";
		customDurations.add(customDuration);// Don't change!
		customDurations.add(customDuration);// Don't change!
		schedule.slipFees.get(0).customDurations = customDurations;
		this.gotoFeeMainPage();
		message = this.addSlipUseFee();
		verifyMessage("Error Message - duplicate custom duration", errorMsg_duplicateCustDuration, message);
		fnm.logoutFinanceManager();
	}
	
	private void verifyMessage(String dscr, String expectMessage, String actualMessage){
		if(!MiscFunctions.compareResult(dscr, expectMessage, actualMessage)){
			throw new ErrorOnPageException("---Check logs above.");
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";

		// use fee schedule info
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.locationCategory = "Park";
		schedule.location = "Jordan Lake State Rec Area";
		schedule.effectDate = DateFunctions.getDateAfterToday(2);
		schedule.startInv = DateFunctions.getDateAfterToday(20);
		schedule.endInv = DateFunctions.getDateAfterToday(29);
		schedule.slipPricingUnit = "Length Range"; // Don't change!

		// expect message
		errorMsg_range0 = "Each Range Value must be greater than 1. Please change your entry.";
		errorMsg_rangeEmpty = "The Range Value for each Range record is required. Please enter the Range Value for each Range record in the field provided.";
		errorMsg_negativeRate = "All Rates in the Fee Schedule must have a value greater than or equal to $0.00. Please change your entries.";
		errorMsg_rateEmpty = "Incomplete entry for Fee by Range. At least one Rate and Range Value for each Range record is required. Please enter both the Range Value and at least one Rate for each Range record in the field provided.";
		errorMsg_custDuraRateEmpty = "Incomplete entry for Fee by Custom Duration for a Range. Please enter both the Custom Quantity and the corresponding Rate.";
		errorMsg_custDuraQuantityEmpty = errorMsg_custDuraRateEmpty;
		errorMsg_duplicateRange = "Each Range Value must be unique. Please change your entries.";
		errorMsg_duplicateCustDuration = "Each Custom Quantity value for a specific Range record must be unique. Please change your entries.";
	}
	
	private String addSlipUseFee(){
		fnm.gotoFeeScheduleDetailPageByAddNew(schedule);
		detailsPg.selectAssignProdCategory(schedule.assignPrdCategory);
		detailsPg.waitLoading();
		detailsPg.setEffectiveDate(schedule.effectDate);
		detailsPg.clickEffectiveLabel();
		detailsPg.setInvStartDate(schedule.startInv);
		detailsPg.setInvEndDate(schedule.endInv);
		schedule.acctCode = detailsPg.getAccountCode(1);
		detailsPg.selectAccountCode(schedule.acctCode);
		detailsPg.setMinimumUseFee(schedule.minimumUseFee);
		detailsPg.setSlipFees(schedule.slipFees, schedule.slipPricingUnit);
		detailsPg.clickApply();
		detailsPg.waitLoading();
		String value = "";
		
		if(detailsPg.checkErrorMessageExisting()){
			value = detailsPg.getErrorMsg();
		}
		return value;
	}
	
	private void gotoFeeMainPage(){
		FinMgrFeeMainPage feePg = FinMgrFeeMainPage.getInstance();
		detailsPg.clickCancel();
		ajax.waitLoading();
		feePg.waitLoading();
	}
}
