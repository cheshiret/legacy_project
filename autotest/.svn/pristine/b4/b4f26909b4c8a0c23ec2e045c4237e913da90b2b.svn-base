package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.activityprd;

import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.licenseManager.feeschedule.LicMgrFeeScheduleDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:This case is used to verify the validation when add a activity fee schedule for activity
 * @LinkSetUp:  d_assign_feature:id=5722
 *              d_hf_add_facility:id=10
 *              d_hf_add_facility_prd:id=10
 *              d_hf_add_instructor:id=10
 *              d_hf_add_activity:id=110
 * @SPEC:[TC:109717] Add Activity Fee Schedule for Activity Product  
 * @Task#: Auto-2135
 * @author Phoebe Chen
 * @Date  April 8, 2014
 */
public class Validation_AddActivityFee extends LicenseManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData();
	private String errMsg_rateAmountIsEmptyOrLessThanZero, errMsg_duplicateAccount, errMsg_percentFundValueLessThanZeroOrMoreThanhundred,
	errMsg_amountFundValueLessThenZeroOrGreatThanTotal;
	private String account1,account2;
	private boolean passed = true;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoFeeScheduleListPageFromTopMenu();
		
		//The rate amount is empty
		this.setScheduleInfo(StringUtil.EMPTY, "Percent", account1, account2, "50", "50");
		String actErrMsg = lm.addNewFeeSchedule(schedule);
		passed &=  MiscFunctions.compareResult("Rate Amount is Empty:", errMsg_rateAmountIsEmptyOrLessThanZero, actErrMsg);
		
		//The rate amount is less than zero
		this.setScheduleInfo("-1.00", "Percent", account1, account2, "50", "50");
		actErrMsg = this.setUpNewFeeScheduleAndClickApply(schedule);
		passed &=  MiscFunctions.compareResult("Rate Amount is less than zero:", errMsg_rateAmountIsEmptyOrLessThanZero, actErrMsg);
		
		//Duplicate accounts
		this.setScheduleInfo("6.00", "Percent", account1, account1, "50", "50");
		actErrMsg = this.setUpNewFeeScheduleAndClickApply(schedule);
		passed &=  MiscFunctions.compareResult("Duplicate account:", errMsg_duplicateAccount, actErrMsg);
		
		//Percent fund value less than zero
		this.setScheduleInfo("6.00", "Percent", account1, account2, "-10", "50");
		actErrMsg = this.setUpNewFeeScheduleAndClickApply(schedule);
		passed &=  MiscFunctions.compareResult("Percent fund value less than zero:", errMsg_percentFundValueLessThanZeroOrMoreThanhundred, actErrMsg);
				
		//Amount fund value less than zero
		this.setScheduleInfo("6.00", "Amount", account1, account2, "-1.00", "5.00");
		actErrMsg = this.setUpNewFeeScheduleAndClickApply(schedule);
		passed &=  MiscFunctions.compareResult("Percent fund value less than zero:", errMsg_amountFundValueLessThenZeroOrGreatThanTotal, actErrMsg);
				
		//It will be added successful for no matter split by percent or amount if one of the value is zero for account.
		int index = new Random().nextInt(2);
		this.setScheduleInfo("6.00", new String[] {"Percent", "Amount"}[index], account1, account2, "0.00", new String[]{"100","6.00"}[index]);
		schedule.feeSchdId = this.setUpNewFeeScheduleAndClickApply(schedule);
		if(!schedule.feeSchdId.matches("\\d+")){
			passed = false;
			logger.info("The fee schedule is not added correctly when the account value is zero!");
		}
		
		if(!passed){
			throw new ErrorOnPageException("Not all the check point is correct, please check the log above!");
		}
		logger.info("All validate is correct!");
	}
	
	public String setUpNewFeeScheduleAndClickApply(FeeScheduleData schedule){
		LicMgrFeeScheduleDetailsPage detailPg = LicMgrFeeScheduleDetailsPage.getInstance();
		detailPg.setUpFeeScheduleInfoAndClickApply(schedule);
		String idOrErrMsg = detailPg.getFeeScheduleId();
		if(idOrErrMsg.equalsIgnoreCase("New")){
			idOrErrMsg = detailPg.getErrorMsg();
		}
		return idOrErrMsg;
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		//login information
		String facilityID = "1";  //Mississippi Department of Wildlife, Fisheries, and Parks
		String facilityName = lm.getFacilityName(facilityID, schema);
		login.contract = "MS Contract";
		login.location = "HF Administrator/" + facilityName;
		
		//Set up fee schedule information
		schedule.productCategory = "Activity";
		schedule.feeType = "Activity Fee";  //Do not change, this is the check point
		
		schedule.productGroup = "FeeAddEditGroup";
		schedule.product = "Activity fee 08";
		schedule.scheduleName = "AddActivityFee";
		schedule.effectStartDate = DateFunctions.getDateAfterToday(-1);
		schedule.effectEndDate = DateFunctions.getDateAfterToday(1);
		schedule.fromLicenseYear = String.valueOf(DateFunctions.getCurrentYear());
		schedule.toLicenseYear = String.valueOf(DateFunctions.getCurrentYear());
		schedule.salesChannel = "CallCenter";
		schedule.locationClass = "MDWFP Internet";
		schedule.applyRate = "Per Unit";
		schedule.splitIntoNum = "2";
		
		account1 = "3450.2050.41502.0044.130.158.AD.W799.----.--------; NR Saltwater Fishing - 100%";
		account2 = "3450.2050.41502.0046.130.168.AD.W799.----.--------; Exempt NR 3 Day Saltwater Fish - 100%";
		
		
		this.setScheduleInfo("6.00", "Percent", account1, account2, "50", "50");
		
		//Error message
		errMsg_rateAmountIsEmptyOrLessThanZero = "The Rate (Flat Amount) must have a value greater than or equal to $0.00. Please change your entries.";
		errMsg_duplicateAccount = "Duplicate Accounts for the Activity Fee Fund Splits is not allowed.";
		errMsg_percentFundValueLessThanZeroOrMoreThanhundred = "A Percentage Fund Split value entered for Activity Fee is not valid. Please enter a numeric value between 0.00% and 100.00%.";
		errMsg_amountFundValueLessThenZeroOrGreatThanTotal = "An Amount Fund Split value entered for Activity Fee is not valid. Please enter either a numeric value greater than or equal to $0.00 or select the Balance option.";
	}
	
	private void setScheduleInfo(String rateAmount, String splitBy, String account1, String account2, String account_value1, String account_value2){
		schedule.accounts.clear();
		schedule.percentOrAmountForEachAccount.clear();
		schedule.rateAmount = rateAmount;
		schedule.splitRateBy = splitBy;
		schedule.accounts.add(account1);
		schedule.accounts.add(account2);
		schedule.percentOrAmountForEachAccount.add(account_value1);
		schedule.percentOrAmountForEachAccount.add(account_value2);
	}

}
