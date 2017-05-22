package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.attributefee.marina;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.PricingBase;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: It is to check the validations when set up calculation Method as percentage and to set arrival,departure information for adding attribute fee schedule for slip product,the validate:
 *               1.Select Percentage as Calculation Method and no Arrival Percent and Departure Percent is defined;
 *               2.Arrival Percentage value is not defined for a specific Arrival Start Date and Arrival End Date;
 *               3.Arrival Start Date value is not defined for a specific Arrival Percentage and Arrival End Date;
 *               4.Arrival End Date value is not defined for a specific Arrival Percentage and Arrival Start Date;
 *               5.Arrival Start Date is not a valid value;
 *               6.Arrival End Date is not a valid value;
 *               7.Add another Arrival Percentage with Arrival Start Date and Arrival End Date overlaps with an existing Arrival Percentage;
 *               8.Add valid Arrival Percentage and Departure Percentage value has not be defined for a specific Departure Start Date and Departure End Date;
 *               9.Departure Start Date value is not defined for a specific Departure Percentage and Departure End Date;
 *               10.Departure End Date value is not defined for a specific Departure Percentage and Departure Start Date;
 *               11.Departure Start Date is not a valid value;
 *               12.Departure End Date is not a valid value;
 *               13.Add another Departure Percentage with Departure Start Date and Departure End Date overlaps with an existing Departure Percentage;
 * @Preconditions: None
 * @SPEC: TC:043416----<refer to>---->TC043390
 * @Task#: Auto-1329
 * @author Phoebe Chen
 * @Date  Jan 14, 2013
 */
public class Validate_CalculationMethod extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData();
	private String arrivalPercentIsEmptyError;
	private String arrivalPercentIsIncompleteError;
	private String arrivalPercentArrivalDateInfoIsInvalideError;
	private String arrivalPercentArrivalDateIsOverlappedError;
	private String departurePercentDateIsEmptyError;
	private String arrivalPercentDepartureDateInfoIsInvalideError;
	private String arrivalPercentDepartureDateIsOverlappedError;
	FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		
		//Go to add fee schedule detail page
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		
		//Error message 87:Select Percentage as Calculation Method and no Arrival Percent and Departure Percent is defined;
		detailsPg.fillAttributeFee(schedule);
		clickOkAndWaitPage();
		verifyErrorMessageAfterGiveValidteInfo("Arrival Percentage Is Empty ",arrivalPercentIsEmptyError);
		
		//Error message 76:Arrival Percentage value is not defined for a specific Arrival Start Date and Arrival End Date;
		this.setpricingBasedArrivalInfo(0, "Jan", "1", "Jan", "10", "");
		setUpCalculationMethodInfoAndClickOk("Arrival");
		verifyErrorMessageAfterGiveValidteInfo("Arrival Percentage Is Incomplete[Arrival Percentage value is not defined] ",arrivalPercentIsIncompleteError);
		
		//Error message 76:Arrival Start Date value is not defined for a specific Arrival Percentage and Arrival End Date;
		this.setpricingBasedArrivalInfo(0, "Month", "Day", "Jan", "10", "60");
		setUpCalculationMethodInfoAndClickOk("Arrival");
		verifyErrorMessageAfterGiveValidteInfo("Arrival Percentage Is Incomplete[Arrival Start Date value is not defined] ",arrivalPercentIsIncompleteError);
	
		//Error message 76:Arrival End Date value is not defined for a specific Arrival Percentage and Arrival Start Date;
		this.setpricingBasedArrivalInfo(0, "Jan", "1", "Month", "Day", "60");
		setUpCalculationMethodInfoAndClickOk("Arrival");
		verifyErrorMessageAfterGiveValidteInfo("Arrival Percentage Is Incomplete[Arrival End Date value is not defined] ",arrivalPercentIsIncompleteError);
	
		//Error message 80:Arrival Start Date is not a valid value;
		this.setpricingBasedArrivalInfo(0, "Feb", "30", "May", "10", "60");
		setUpCalculationMethodInfoAndClickOk("Arrival");
		verifyErrorMessageAfterGiveValidteInfo("Arrival Date Is Invalid[Arrival Start Date is not a valid value] ",arrivalPercentArrivalDateInfoIsInvalideError);
		
		//Error message 80:Arrival End Date is not a valid value (e.g. Feb 31);
		this.setpricingBasedArrivalInfo(0, "Feb", "20", "Feb", "31", "60");
		setUpCalculationMethodInfoAndClickOk("Arrival");
		verifyErrorMessageAfterGiveValidteInfo("Arrival Date Is Invalid[Arrival End Date is not a valid value] ",arrivalPercentArrivalDateInfoIsInvalideError);
		
		//Error message 77:Add another Arrival Percentage with Arrival Start Date and Arrival End Date overlaps with an existing Arrival Percentage;
		PricingBase arrivalPercentBase1 = new PricingBase();
		schedule.pricingBasedonArrival.add(arrivalPercentBase1);
		this.setpricingBasedArrivalInfo(0, "Jan", "1", "Jan", "10", "60");
		this.setpricingBasedArrivalInfo(1, "Jan", "5", "Jan", "15", "70");
		detailsPg.addOneMorePricingBaseonArrivalItem("Arrival");
		setUpCalculationMethodInfoAndClickOk("Arrival");
		verifyErrorMessageAfterGiveValidteInfo("Arrival Date Is Overlapped ",arrivalPercentArrivalDateIsOverlappedError);
		
		this.setpricingBasedArrivalInfo(1, "Oct", "5", "Oct", "15", "70");
		detailsPg.setPricingBasePercentInfo("Arrival", schedule.pricingBasedonArrival);
		
		//Error message 78:Add valid Arrival Percentage and Departure Percentage value has not be defined for a specific Departure Start Date and Departure End Date;
		
		PricingBase departurePercentBase = new PricingBase();
		schedule.pricingBasedonDeparture.add(departurePercentBase);
		this.setpricingBasedDepartureInfo(0, "Month", "Day", "Feb", "10", "");
		detailsPg.addOneMorePricingBaseonArrivalItem("Departure");
		setUpCalculationMethodInfoAndClickOk("Departure");
		verifyErrorMessageAfterGiveValidteInfo("Departure Date Is Empty[Departure date is none] ",departurePercentDateIsEmptyError);
		
		//Error message 78:Departure Start Date value is not defined for a specific Departure Percentage and Departure End Date;
		this.setpricingBasedDepartureInfo(0, "Month", "Day", "Feb", "10", "60");
		setUpCalculationMethodInfoAndClickOk("Departure");
		verifyErrorMessageAfterGiveValidteInfo("Departure Date Is Empty[Departure start date value is not defined] ",departurePercentDateIsEmptyError);
		
		//Error message 78:Departure End Date value is not defined for a specific Departure Percentage and Departure Start Date;
		this.setpricingBasedDepartureInfo(0, "Feb", "1", "Month", "Day",  "60");
		setUpCalculationMethodInfoAndClickOk("Departure");
		verifyErrorMessageAfterGiveValidteInfo("Departure Date Is Empty[Departure End Date value is not defined] ",departurePercentDateIsEmptyError);
		
		//Error message 81:Departure Start Date is not a valid value;
		this.setpricingBasedDepartureInfo(0, "Apr", "31", "May", "10",  "60");
		setUpCalculationMethodInfoAndClickOk("Departure");
		verifyErrorMessageAfterGiveValidteInfo("Departure Start Date Is invalid ",arrivalPercentDepartureDateInfoIsInvalideError);
		
		//Error message 81:	Departure End Date is not a valid value;
		this.setpricingBasedDepartureInfo(0, "Jun", "10", "Jun", "31", "60");
		setUpCalculationMethodInfoAndClickOk("Departure");
		verifyErrorMessageAfterGiveValidteInfo("Departure End Date Is invalid ",arrivalPercentDepartureDateInfoIsInvalideError);
		
		//Error message 79:Add another Departure Percentage with Departure Start Date and Departure End Date overlaps with an existing Departure Percentage;
		PricingBase departurePercentBase1 = new PricingBase();
		schedule.pricingBasedonDeparture.add(departurePercentBase1);
		this.setpricingBasedDepartureInfo(0, "Feb", "1", "Feb", "10", "70");
		this.setpricingBasedDepartureInfo(1, "Feb", "9", "Feb", "12", "90");
		detailsPg.addOneMorePricingBaseonArrivalItem("Departure");
		setUpCalculationMethodInfoAndClickOk("Departure");
		verifyErrorMessageAfterGiveValidteInfo("Departure Date Is overlapped ",arrivalPercentDepartureDateIsOverlappedError);
		
		fnm.logoutFinanceManager();
	}
	
	private void setpricingBasedArrivalInfo(int i, String startMonth, String startDate, String endMonth, String endDate, String percent){
		schedule.pricingBasedonArrival.get(i).startMonth = startMonth;
		schedule.pricingBasedonArrival.get(i).startDate = startDate;
		schedule.pricingBasedonArrival.get(i).endMonth = endMonth;
		schedule.pricingBasedonArrival.get(i).endDate = endDate;
		schedule.pricingBasedonArrival.get(i).percent = percent;
	}
	
	private void setpricingBasedDepartureInfo(int i, String startMonth, String startDate, String endMonth, String endDate, String percent){
		schedule.pricingBasedonDeparture.get(i).startMonth = startMonth;
		schedule.pricingBasedonDeparture.get(i).startDate = startDate;
		schedule.pricingBasedonDeparture.get(i).endMonth = endMonth;
		schedule.pricingBasedonDeparture.get(i).endDate = endDate;
		schedule.pricingBasedonDeparture.get(i).percent = percent;
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
		schedule.productCategory = "Slip";     //This is the check point, can not be changed
		schedule.feeType = "Attribute Fee";    //This is the check point, can not be changed
		
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

		schedule.marinaRateType = "Seasonal";
		schedule.unitOfStay = "Nightly";
		schedule.slipPricingUnit = "Length Range";

		FeeScheduleData.SlipFee fee = schedule.new SlipFee();
		fee.perSeasonFee = "5";
		fee.rangeFeet = "1";
		schedule.slipFees.add(fee);
		
		schedule.calculationMethod = "Percentage";
		
		PricingBase arrivalPercentBase = new PricingBase();
		schedule.pricingBasedonArrival.add(arrivalPercentBase);
		this.setpricingBasedArrivalInfo(0, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY);
		
		//Error messages information
		arrivalPercentIsEmptyError = "If the Calculation Method is Percentage, the details of at least one Arrival Percent or one Departure Percent is required. Please add an Arrival Percent or a Departure Percent.";
		arrivalPercentIsIncompleteError = "Incomplete entry for Arrival Percentage. Please enter all of: Arrival Start Day & Month, Arrival End Day& Month, and the Percent Value for each Arrival Percentage record in the fields provided.";
		arrivalPercentArrivalDateInfoIsInvalideError = "The Arrival Start Date and Arrival End Date need to be valid dates. Please change the Arrival Start Day & Month and/or the Arrival End Day & Month for the Arrival Percentage";
		arrivalPercentArrivalDateIsOverlappedError = "The Arrival Percentage time frames cannot overlap. Please change the Arrival Start Day & Month and/or the Arrival End Day & Month for the Arrival Percentage.";
		departurePercentDateIsEmptyError = "Incomplete entry for Departure Percentage. Please enter all of: Departure Start Day & Month, Departure End Day& Month, and the Percent Value for each Departure Percentage record in the fields provided.";
		arrivalPercentDepartureDateInfoIsInvalideError = "The Departure Start Date and Departure End Date need to be valid dates. Please change the Departure Start Day & Month and/or the Departure End Day & Month for the Departure Percentage";
		arrivalPercentDepartureDateIsOverlappedError = "The Departure Percentage time frames cannot overlap. Please change the Departure Start Day & Month and/or the Departure End Day & Month for the Departure Percentage.";
	}
	
	private void clickOkAndWaitPage(){
		detailsPg.clickOK();
		ajax.waitLoading();
		detailsPg.waitLoading();
	}
	
	private void setUpCalculationMethodInfoAndClickOk(String type){
		if("Arrival".equalsIgnoreCase(type)){
			detailsPg.setPricingBasePercentInfo("Arrival", schedule.pricingBasedonArrival);
		}else if("Departure".equalsIgnoreCase(type)){
			detailsPg.setPricingBasePercentInfo("Departure", schedule.pricingBasedonDeparture);
		}
		clickOkAndWaitPage();
	}
	
	private void verifyErrorMessageAfterGiveValidteInfo(String msgType, String expectError) {
		String errorMessage = detailsPg.getErrorMsg();
		if(!expectError.startsWith(errorMessage)){
			throw new ErrorOnPageException(msgType + " error message is not correct, EXPECT:" + expectError + "    ACTURAL:" + errorMessage);
		}
	}

}
