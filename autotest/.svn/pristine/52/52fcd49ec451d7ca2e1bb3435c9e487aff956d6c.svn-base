package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.uesfee.marina;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFee;
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
 * @Description:This test case verify unit is 'Length Increments' when add use fee for slip.
 * 1.No increment record;
 * 2.Increment is empty;
 * 3.Increment is less than 1;
 * 4.Rate is empty;
 * 5.Rate is negative value;
 * 6.Duplicate increment record;
 * 7.Rate is defined for one Length increments, it must be defined for all Length increments.
 * @Preconditions:
 * @SPEC: Add Use Fee Schedule - Unit "Length Increments" validation [TC:043389]
 * @Task#: Auto-1327
 * 
 * @author nding1
 * @Date  Jan 15, 2013
 */
public class VerifyUnitLengthIncrement extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData(true);
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	private String errorMsg_noRecord, errorMsg_incrementEmpty, errorMsg_increment0, errorMsg_rateEmpty, errorMsg_rateNegative,
	   			   errorMsg_duplicateIncrement, errorMsg_multUnit;

	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		
		// 1.No increment record;
		String message = this.addSlipUseFee(true);// remove all increment
		verifyMessage("Error Message - No increment record", errorMsg_noRecord, message);
		
		// 2.Increment is empty;
		List<SlipFee> slipFees = new ArrayList<SlipFee>();
		SlipFee slipFee = schedule.new SlipFee();
		slipFee.increment = StringUtil.EMPTY; // Don't change!
		slipFee.dailyNightlyFee = "98";
		slipFees.add(slipFee);
		schedule.slipFees = slipFees;
		this.gotoFeeMainPage();
		message = this.addSlipUseFee(false);
		verifyMessage("Error Message - Increment is empty", errorMsg_incrementEmpty, message);
		
		// 3.Increment is less than 1;
		schedule.slipFees.get(0).increment = "0"; // Don't change!
		this.gotoFeeMainPage();
		message = this.addSlipUseFee(false);
		verifyMessage("Error Message - Increment is less than 1", errorMsg_increment0, message);
		
		// 4.Rate is empty;
		schedule.slipFees.get(0).increment = "1";
		schedule.slipFees.get(0).dailyNightlyFee = StringUtil.EMPTY; // Don't change!
		this.gotoFeeMainPage();
		message = this.addSlipUseFee(false);
		verifyMessage("Error Message - Rate is empty", errorMsg_rateEmpty, message);
		
		// 5.Rate is negative value;
		schedule.slipFees.get(0).dailyNightlyFee = "-1.39"; // Don't change!
		this.gotoFeeMainPage();
		message = this.addSlipUseFee(false);
		verifyMessage("Error Message - Rate is negative value", errorMsg_rateNegative, message);

		// 6.Duplicate increment record;
		schedule.slipFees.get(0).dailyNightlyFee = "0.68"; // Don't change!
		slipFee = schedule.new SlipFee();
		slipFee.increment = schedule.slipFees.get(0).increment; // Don't change!
		slipFee.dailyNightlyFee = schedule.slipFees.get(0).dailyNightlyFee; // Don't change!
		slipFees.add(slipFee);
		schedule.slipFees = slipFees;
		this.gotoFeeMainPage();
		message = this.addSlipUseFee(false);
		verifyMessage("Error Message - Duplicate increment record", errorMsg_duplicateIncrement, message);
		
		// 7.Rate is defined for one Length increments, it must be defined for all Length increments.
		schedule.slipFees.get(0).increment = "2";
		schedule.slipFees.get(0).dailyNightlyFee = "8.35";
		schedule.slipFees.get(0).weeklyFee = "7.58"; // Don't change!
		this.gotoFeeMainPage();
		message = this.addSlipUseFee(false);
		verifyMessage("Error Message - Multi Unit", errorMsg_multUnit, message);
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
		schedule.slipPricingUnit = "Length Increments"; // Don't change!

		// expect message
		errorMsg_noRecord = "At least one Increment value and rates must be entered.";
		errorMsg_incrementEmpty = "The Increment Value for each Increment record is required. Please enter the Increment Value for each Increment record in the field provided.";
		errorMsg_increment0 = "Each Increment Value must be greater than 1. Please change your entry";
		errorMsg_rateEmpty = "Incomplete entry for Fee by Increment. At least one Rate and Increment Value for each Increment record is required. Please enter both the Increment Value and at least one Rate for each Increment record in the field provided.";
		errorMsg_rateNegative = "All Rates in the Fee Schedule must have a value greater than or equal to $0.00. Please change your entries.";
		
		// TODO DEFECT-40734 won't fix, expect message in Spec doen't contain 'Error'
		errorMsg_duplicateIncrement = "Error Each Increment Value must be unique. Please change your entries.";
		errorMsg_multUnit = "When defining a multi-unit rate for one Length Increment, rates must be defined for the same multi-unit for all Length increments (e.g. if a value for weekly is defined for one Length increments, weekly values must be defined for all Length increments)";
	}
	
	private String addSlipUseFee(boolean isRemoveAll){
		fnm.gotoFeeScheduleDetailPageByAddNew(schedule);
		detailsPg.selectAssignProdCategory(schedule.assignPrdCategory);
		detailsPg.waitLoading();
		detailsPg.setEffectiveDate(schedule.effectDate);
		detailsPg.clickEffectiveLabel();
		detailsPg.setInvStartDate(schedule.startInv);
		detailsPg.setInvEndDate(schedule.endInv);
		schedule.acctCode = detailsPg.getAccountCode(1);
		detailsPg.selectAccountCode(schedule.acctCode);
		detailsPg.selectUnit(schedule.slipPricingUnit);
		ajax.waitLoading();
		detailsPg.waitLoading();
		if(isRemoveAll){
			detailsPg.removeAllRangeIncrement();
		} else {
			detailsPg.setSlipFees(schedule.slipFees,schedule.slipPricingUnit);
		}
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
