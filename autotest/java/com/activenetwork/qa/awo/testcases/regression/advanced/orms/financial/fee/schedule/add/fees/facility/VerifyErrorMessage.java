package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.facility;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.PromoCode;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.page.AlertDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: This case verify the error message when add,edit equipment rental
 * @LinkSetUp:  D_INV_ADD_EQUIPMENT:id=20
 * 				D_INV_ADD_EQUIPMENT:id=20
 * @SPEC:[TC:122519] Add Fee Schedule - Pass Usage for Equipment Rentals - Error Message Validation 
 * @Task#: Auto-2195
 * @author Phoebe
 * @Date  May 30, 2013
 */
public class VerifyErrorMessage extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData();
	private FeeScheduleData scheduleDuplicate = new FeeScheduleData();
	private FinMgrFeeSchDetailPage detailPg=FinMgrFeeSchDetailPage.getInstance();
	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	private String errMsg_rateAmountIsEmptyOrLessThanZero, errMsg_startDateWithWrongFormat, errMsg_endDateWithWrongFormat,
	errMsg_startDateEmpty, errMsg_endDateEarlierThanStart, errMsg_duplicatePromoCode, errMsg_promoCodeIsEmpty, errMsg_duplicateInfo;
	private boolean passed = true;
	@Override
	public void execute() {
		 //login finance manager
	    fnm.loginFinanceManager(login);
	    fnm.gotoFeeMainPage();
	    
	    feeMainPg.deactiveAllTheFeeForProduct(schedule.product, FEETYPE_NAME_FACILITYFEE);
	    
	    //Add a Fee schedule data
	    scheduleDuplicate.feeSchdId = fnm.addNewFeeSchedule(scheduleDuplicate);
	    fnm.activeOrDeactiveFeeSchedule(scheduleDuplicate.feeSchdId, true);
	   
	    //The rate amount is empty
	    fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		
		//The rate amount is less than zero
		this.setScheduleInfo("-1.00", "50", "50");
		String actErrMsg = this.setUpNewFeeScheduleAndClickApply(schedule);
		passed &=  MiscFunctions.compareResult("Rate Amount is less than zero:", errMsg_rateAmountIsEmptyOrLessThanZero, actErrMsg);

		this.setScheduleInfo("6.00", "50", "50");
		detailPg.fillUpFaiclityFee(schedule);
		
		//Set start date with invalid format
		actErrMsg = this.setUpStartDateAndEndDate("start date", DateFunctions.getDateAfterToday(2), false);
		passed &=  MiscFunctions.compareResult("Start date with wrong format:", errMsg_startDateWithWrongFormat, actErrMsg);

		//Set end date with invalid format
		actErrMsg = this.setUpStartDateAndEndDate( DateFunctions.getDateAfterToday(2), "end date", false);
		passed &=  MiscFunctions.compareResult("End date with wrong format:", errMsg_endDateWithWrongFormat, actErrMsg);
		
		//Do not special start date
		actErrMsg = this.setUpStartDateAndEndDate( StringUtil.EMPTY , DateFunctions.getDateAfterToday(2), true);
		passed &=  MiscFunctions.compareResult("Do not set start date:", errMsg_startDateEmpty, actErrMsg);
		
		//End date greater than start date
		actErrMsg = this.setUpStartDateAndEndDate( DateFunctions.getDateAfterToday(5) , DateFunctions.getDateAfterToday(2), true);
		passed &=  MiscFunctions.compareResult("End date earlier than start date:", errMsg_endDateEarlierThanStart, actErrMsg);
		this.setUpStartDateAndEndDate(DateFunctions.getDateAfterToday(1), DateFunctions.getDateAfterToday(2), false);

		actErrMsg = this.setupPromoCode(schedule.new PromoCode("Abc001", "Add Promo Des First"),
				schedule.new PromoCode("abC001", "Add Promo Des Second"));
		passed &=  MiscFunctions.compareResult("Set with duplicate promo code:", errMsg_duplicatePromoCode, actErrMsg);
		
		actErrMsg = this.setupPromoCode(schedule.new PromoCode("", "Add Promo Des First"),
				schedule.new PromoCode("abC001", "Add Promo Des Second"));
		passed &=  MiscFunctions.compareResult("Set with empty promo code:", errMsg_promoCodeIsEmpty, actErrMsg);
		
		setUpFeeScheduleInfo(schedule);
		schedule.feeSchdId = this.setUpInfoToFeeScheduleMainPage(schedule);
		fnm.activeOrDeactiveFeeSchedule(schedule.feeSchdId, true);
		errMsg_duplicateInfo = "Fee schedule having ID = " + schedule.feeSchdId+ " cannot be activated because identical Fee Schedule with ID = " + scheduleDuplicate.feeSchdId + " already exists";
		 //Go to schedule detail page
	    fnm.searchToFeeScheduleDetailPg(schedule.feeSchdId);		
	    actErrMsg = this.setUpStartDateAndEndDate( scheduleDuplicate.effectStartDate , scheduleDuplicate.effectEndDate, true);
	    passed &=  MiscFunctions.compareResult("Duplicate information:", errMsg_duplicateInfo, actErrMsg);
	    
		if(!passed){
			throw new ErrorOnPageException("Not all the check point is correct, please check the log above!");
		}
		logger.info("All validate are correct!");
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
		//initialize login finance manager loginInfo
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.contract = "NRRS Contract";
	  	login.location = "Administrator/NRRS";
	  	
	  	//initialize attribute fee schedule data
	  	this.setUpFeeScheduleInfo(schedule);
	  	this.setUpFeeScheduleInfo(scheduleDuplicate);
		
	  	//The effective date is different for the two schedule at the very first
	  	scheduleDuplicate.effectStartDate = DateFunctions.getDateAfterGivenDay(schedule.effectStartDate, 5);
	  	scheduleDuplicate.effectEndDate = DateFunctions.getDateAfterGivenDay(schedule.effectEndDate, 5);
	  	
		 errMsg_rateAmountIsEmptyOrLessThanZero = "The Rate (Flat Amount) must have a value greater than or equal to $0.00. Please change your entries.";
		 errMsg_startDateWithWrongFormat = "Invalid date. The date format should be: YYYYMMDD";
		 errMsg_endDateWithWrongFormat = "Invalid date. The date format should be: YYYYMMDD";
		 errMsg_startDateEmpty = "An Effective Start Date for the Fee Schedule is required. Please enter the Effective Start Date using the format Ddd Mmm dd yyyy in the field provided.";
		 errMsg_endDateEarlierThanStart = "The Effective Start Date must not be later than the Effective End Date. Please change your entries.";
		 errMsg_duplicatePromoCode = "Each Promo Code for the Discount must be unique. Please change your entries.";
		 errMsg_promoCodeIsEmpty = "The Description for a Promo Code was entered without its corresponding Code. Please enter the Code in the field provided.";
	}
	
	
	public void setUpFeeScheduleInfo(FeeScheduleData schedule){
	  	//Set up discount fee schedule informaton
		schedule.location = fnm.getFacilityName("75387", schema);//"ALDER DUNE";
		schedule.locationCategory = "Park";
		schedule.productCategory = "Facility";
	  	schedule.productSubCategory = "Equipment";
	  	schedule.feeType = "Facility Fee";
	  	schedule.scheduleType ="Discount";
	  	schedule.scheduleName = "AddEquipmentFee";
	  	schedule.discountType = "Automatic";
	  	schedule.productGroup = "All";
	  	schedule.product = "Equipment fee 02";
	  	schedule.effectStartDate = DateFunctions.getDateAfterToday(1);
	  	schedule.effectEndDate = DateFunctions.getDateAfterGivenDay(schedule.effectStartDate, 2);
	  	schedule.orderCreateStartDate = schedule.effectStartDate;
	  	schedule.orderCreateEndDate = schedule.effectEndDate;
	  	schedule.salesChannel = "CallCenter";
	  	schedule.custPass = "Interagency Senior Pass";
	  	schedule.promoCodes.clear();
	  	schedule.promoCodes.add(schedule.new PromoCode("Add Promo Code First", "Add Promo Des First"));
	  	schedule.promoCodes.add(schedule.new PromoCode("Add Promo Code Second", "Add Promo Des Second"));
	  	schedule.feeApplyTo = "Equipment Rental";
	  	schedule.applyRate = "Per Unit";
	  	schedule.rateAmount = "3.24";
		schedule.splitRateBy = "Percent";
		schedule.splitIntoNum = "2";
		schedule.accounts.clear();
		schedule.percentOrAmountForEachAccount.clear();
		schedule.accounts.add("USFS.3000.8200.1004; Equestrian Non Electric Revenue");
		schedule.accounts.add("USFS.3000.8200.1013; Group Standard Non Electric Revenue");
		schedule.percentOrAmountForEachAccount.add("50");
		schedule.percentOrAmountForEachAccount.add("50");
	}
	
	public String fillUpFeeScheduleInfAndClickApply(){
		detailPg.setupFacilityFeeAndClickApply(schedule);
		ajax.waitLoading();
		detailPg.waitLoading();
		String errMsg = detailPg.getErrorMsg();
		return errMsg;
	}
	
	public String setUpInfoToFeeScheduleMainPage(FeeScheduleData schedule){
		String id = detailPg.setupFacilityFee(schedule);
		ajax.waitLoading();
		feeMainPg.waitLoading();
		return id;
	}
	
	public String setUpNewFeeScheduleAndClickApply(FeeScheduleData schedule){
		detailPg.setupFacilityFeeAndClickApply(schedule);
		String idOrErrMsg = detailPg.getFeeSchID();
		if(idOrErrMsg.equalsIgnoreCase("New")){
			idOrErrMsg = detailPg.getErrorMsg();
		}
		return idOrErrMsg;
	}
	
	public String setUpStartDateAndEndDate(String startDate, String endDate, boolean clickApply){
		String errMsg = "";
		AlertDialogPage alertDialog = AlertDialogPage.getInstance();
		detailPg.setEffectStartDate(startDate);
		Browser.sleep(3);
		if(alertDialog.exists()){
			errMsg = alertDialog.text().replace("OK", "").trim();
			alertDialog.clickOK();
			ajax.waitLoading();
		}
		detailPg.exists();
		detailPg.setEffectEndDate(endDate);
		Browser.sleep(3);
		if(alertDialog.exists()){
			errMsg = alertDialog.text().replace("OK", "").trim();
			alertDialog.clickOK();
			ajax.waitLoading();
		}
		detailPg.exists();
		if(clickApply){
			detailPg.clickApply();
			ajax.waitLoading();
			errMsg = detailPg.getErrorMsg();
		}
		return errMsg;
	}
	
	private void setScheduleInfo(String rateAmount, String account_value1, String account_value2){
		schedule.percentOrAmountForEachAccount.clear();
		schedule.rateAmount = rateAmount;
		schedule.percentOrAmountForEachAccount.add(account_value1);
		schedule.percentOrAmountForEachAccount.add(account_value2);
	}
	
	private String setupPromoCode(PromoCode promoCode1, PromoCode promoCode2) {
		detailPg.setUpPromoCode(promoCode1, 0);
		detailPg.setUpPromoCode(promoCode2, 1);
		detailPg.clickApply();
		ajax.waitLoading();
		String errMsg = detailPg.getErrorMsg();
		return errMsg;
	}
}
