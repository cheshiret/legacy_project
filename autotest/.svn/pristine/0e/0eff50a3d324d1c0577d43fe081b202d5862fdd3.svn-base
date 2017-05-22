package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.attributefee.marina;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.SlipFeeCustomDuration;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: It is to check the validations when set up length range information for adding attribute fee schedule for slip product,the validate:
 *               1.Select Unit as "Length Range" and enter a Range Value less than 1 (0);
 *               2.Keep the Range Value as empty;
 *               3.Select Unit as "Length Range" and enter a Rate less than 0 (-3);
 *               4.Select Unit as "Length Range", enter a valid Rate and keep Range Value as empty;
 *               5.Add Custom Duration, enter Custom Quantity and keep Rate as empty;
 *               6.Add Custom Duration, enter Rate and keep Custom Quantity as empty;
 *               7.Add another Range with same Range valud as the existing length range;
 *               8.Add a duplicate Custom Quantity value for a specific Range value;
 * @Preconditions: None
 * @SPEC: TC:043414----<refer to>---->TC043388
 * @Task#: Auto-1329
 * @author Phoebe Chen
 * @Date  Jan 14, 2013
 */
public class Validate_UnitLengthRange extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData();
	private String rangeValueLessThanOneError;
	private String rangeValueIsBlankError;
	private String rateIsLessThanZeroError;
	private String rateValueIsBlankError; 
	private String customeDurationIncompleteError; 
	private String duplicateRangeInfoError;
	private String duplicateCustomDurationInfoError;
	FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		
		//Go to add fee schedule detail page
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		
		//Error message 88:Select Unit as "Length Range" and enter a Range Value less than 1 (0);
		detailsPg.fillAttributeFee(schedule);
		clickOkAndWaitPage();
		verifyErrorMessageAfterGiveValidteInfo("Range Value Less Than One ",rangeValueLessThanOneError);
		
		//Error message 65:Keep the Range Value as empty; 
		this.setSlipFeesInfo(1, StringUtil.EMPTY, "4");
		this.clearDate();
		setUpUnitLengthRangeInfoAndClickOk();
		verifyErrorMessageAfterGiveValidteInfo("Range Value Is Blank ",rangeValueIsBlankError);
		
		// due to DEFECT-42698 commit this code
		//Error message 20:Select Unit as "Length Range" and enter a Rate less than 0 (-3);
//		this.setSlipFeesInfo(1, "2", "-3");
//		this.clearDate();
//		setUpUnitLengthRangeInfoAndClickOk();
//		verifyErrorMessageAfterGiveValidteInfo("Rate Value Is Less Than Zero ",rateIsLessThanZeroError);
		
		//Error message 66:Select Unit as "Length Range", enter a valid Rate and keep Range Value as empty; 
		this.setSlipFeesInfo(1, "2", StringUtil.EMPTY);
		this.clearDate();
		setUpUnitLengthRangeInfoAndClickOk();
		verifyErrorMessageAfterGiveValidteInfo("Length range Is Incomplete ",rateValueIsBlankError);
		
		//Error message 68:Add another Range with same Range valud as the existing length range;
		this.setSlipFeesInfo(1, schedule.slipFees.get(0).rangeFeet, schedule.slipFees.get(0).monthlyFee);
		this.clearDate();
		setUpUnitLengthRangeInfoAndClickOk();
		verifyErrorMessageAfterGiveValidteInfo("Range Information Duplicat ",duplicateRangeInfoError);
		
		//Error message 67:Add Custom Duration(custom quantity),  keep Rate as empty; 
		schedule.slipFees.remove(1);
		this.clearDate();
		SlipFeeCustomDuration cd = new SlipFeeCustomDuration();
		schedule.slipFees.get(0).customDurations.add(cd);
		setCustomDuration(0, "1", StringUtil.EMPTY);
		setUpUnitLengthRangeInfoAndClickOk();
		verifyErrorMessageAfterGiveValidteInfo("Custom Duration Incomplete[rate is empty] ",customeDurationIncompleteError);
		
		//Error message 67:Add Custom Duration, enter Rate and keep Custom Quantity(Custom Duration) as empty;
		setCustomDuration(0, StringUtil.EMPTY, "2");
		this.clearDate();
		setUpUnitLengthRangeInfoAndClickOk();
		verifyErrorMessageAfterGiveValidteInfo("Custom Duration Incomplete[custom quantity is empty] ",customeDurationIncompleteError);
		
		//Error message 69:Add a duplicate Custom Quantity value for a specific Range value;
		SlipFeeCustomDuration cd1 = new SlipFeeCustomDuration();
		schedule.slipFees.get(0).customDurations.add(cd1);
		setCustomDuration(0, "1", "2");
		setCustomDuration(1, "1", "2");
		this.clearDate();
		setUpUnitLengthRangeInfoAndClickOk();
		verifyErrorMessageAfterGiveValidteInfo("Customer Duration Duplicat ",duplicateCustomDurationInfoError);
		
		fnm.logoutFinanceManager();
	}

	
	public void setSlipFeesInfo(int i, String rangeFee, String monthlyFee){
		schedule.slipFees.get(i).rangeFeet = rangeFee;
		schedule.slipFees.get(i).monthlyFee = monthlyFee;
	}
	
	public void setCustomDuration(int i, String customDuration, String rate){
		schedule.slipFees.get(0).customDurations.get(i).customDuration = customDuration;
		schedule.slipFees.get(0).customDurations.get(i).rate = rate;
	}
	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";

		//Location information for schedule
		schedule.location = fnm.getFacilityName("552818", schema);// Mayo River State Park
		schedule.locationCategory = "Park";
		
		// initialize attribute fee schedule data
		schedule.productCategory = "Slip";    //This is the check point, can not be changed
		schedule.feeType = "Attribute Fee";   //This is the check point, can not be changed
		
		schedule.productGroup = "Full attributes";
		schedule.effectDate = DateFunctions.formatDate(
				DateFunctions.getDateAfterToday(-10), "EEE MMM d yyyy");
		schedule.startInv = DateFunctions.formatDate(DateFunctions
				.getDateAfterToday(-9), "EEE MMM d yyyy");
		schedule.endInv = DateFunctions.formatDate(DateFunctions
				.getDateAfterToday(-8), "EEE MMM d yyyy");
		schedule.acctCode = "3000.1601.000200000; Default Overage/Shortage";

		schedule.boatCategory = "Personal";
		schedule.attrType = "Electricity Hookup";
		schedule.attrValue = "15";

		schedule.marinaRateType = "Lease";
		schedule.unitOfStay = "Nightly";
		schedule.slipPricingUnit = "Length Range";

		FeeScheduleData.SlipFee fee = schedule.new SlipFee();
		fee.monthlyFee = "3";
		fee.rangeFeet = "1";
		schedule.slipFees.add(fee);
		FeeScheduleData.SlipFee fee1 = schedule.new SlipFee();
		fee1.rangeFeet = "0";
		schedule.slipFees.add(fee1);
		
		rangeValueLessThanOneError = "Each Range Value must be greater than 1. Please change your entry.";
		rangeValueIsBlankError = "The Range Value for each Range record is required. Please enter the Range Value for each Range record in the field provided.";
		rateIsLessThanZeroError = "All Rates in the Fee Schedule must have a value greater than or equal to $0.00. Please change your entries.";
		rateValueIsBlankError = "Incomplete entry for Fee by Range. At least one Rate and Range Value for each Range record is required. Please enter both the Range Value and at least one Rate for each Range record in the field provided.";
		customeDurationIncompleteError = "Incomplete entry for Fee by Custom Duration for a Range. Please enter both the Custom Quantity and the corresponding Rate.";
		duplicateRangeInfoError = "Each Range Value must be unique. Please change your entries.";
		duplicateCustomDurationInfoError = "Each Custom Quantity value for a specific Range record must be unique. Please change your entries.";
	}
	
	private void clickOkAndWaitPage(){
		detailsPg.clickOK();
		ajax.waitLoading();
		detailsPg.waitLoading();
	}
	
	private void setUpUnitLengthRangeInfoAndClickOk(){
		detailsPg.setSlipFees(schedule.slipFees,schedule.slipPricingUnit);
		clickOkAndWaitPage();
	}
	
	private void clearDate(){
		detailsPg.removeAllLengthRangeExceptTheDefaultOne();
		detailsPg.removeAllCustomDurationForSpecialLengthRange(0);
	}
	
	private void verifyErrorMessageAfterGiveValidteInfo(String msgType, String expectError) {
		String errorMessage = detailsPg.getErrorMsg();
		if(!expectError.startsWith(errorMessage)){
			throw new ErrorOnPageException(msgType + " error message is not correct, EXPECT:" + expectError + "    ACTURAL:" + errorMessage);
		}
	}
}
