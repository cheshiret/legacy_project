/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.uesfee.marina;


import org.junit.Assert;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.PricingBase;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 		1. Go to add use fee page.
 * 		2. Check No Arrival And Departure Percent
 * 		3. check No Arrival Percentage Value
 *		4. Check No Arrival Start And End Date
 *		5. Check Arrival Start And End Date Overlapped
 *		6. check No Departure Percentage Value
 *		7. Check No Departure Start And End Date
 *		8. Check Departure Start And End Date Overlapped
 *		9. at last, create a correct fee schedule with Arrival and Departure percent		
 *		10.Check Fee schedule info is correct.
 * 
 * @Preconditions:
 * 		1.Slip product is created in NC contract(Mayo River State Park), PRD_CD="PZH". in table "d_inv_add_slip"(ID=160)
 * @SPEC:TC043390,Add Use Fee Schedule - Calculation Method validation 
 * @Task#:AUTO-1328
 * 
 * @author pzhu
 * @Date Jan 9, 2013
 */

public class VerifyCalculationMethod_Percent extends FinanceManagerTestCase {

	private FeeScheduleData schedule = new FeeScheduleData();

	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();

	private FinMgrFeeSchDetailPage detailPg = FinMgrFeeSchDetailPage.getInstance();
		
	private FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage.getInstance();
	
	private static String NO_ARRIVAL_AND_DEPARTURE = "If the Calculation Method is Percentage, the details of at least one Arrival Percent or one Departure Percent is required. Please add an Arrival Percent or a Departure Percent.";
	private static String NO_PERCENT_VALUE = "Incomplete entry for Arrival Percentage. Please enter all of: Arrival Start Day & Month, Arrival End Day& Month, and the Percent Value for each Arrival Percentage record in the fields provided.";
	private static String NO_START_END_DATE = "The Arrival Start Date and Arrival End Date need to be valid dates. Please change the Arrival Start Day & Month and/or the Arrival End Day & Month for the Arrival Percentage.";
	private static String OVERLAP_START_END_DATE = "The Arrival Percentage time frames cannot overlap. Please change the Arrival Start Day & Month and/or the Arrival End Day & Month for the Arrival Percentage.";
	
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login); 
		fnm.gotoFeeMainPage();
		
		this.gotoFeeDetailPage(schedule);

		//check point 1: check No Arrival And Departure Percent
		this.verifyNoArrivalAndDeparturePercent(schedule, NO_ARRIVAL_AND_DEPARTURE);
		
		//check point 2: check No Arrival Percentage Value
		this.verifyNoArrivalPercentageValue(schedule,NO_PERCENT_VALUE);
		
		//check point 3: Check No Arrival Start And End Date
		this.verifyNoArrivalStartAndEndDate(schedule, NO_START_END_DATE);
		
		//check point 4: Check Arrival Start And End Date Overlapped
		this.verifyArrivalStartAndEndDateOverlap(schedule, OVERLAP_START_END_DATE);
		
		
		//check point 5: check No Departure Percentage Value
		this.verifyNoDeparturePercentageValue(schedule,NO_PERCENT_VALUE.replaceAll("Arrival", "Departure"));
		
		//check point 6: Check No Departure Start And End Date
		this.verifyNoDepartureStartAndEndDate(schedule, NO_START_END_DATE.replaceAll("Arrival", "Departure"));
		
		//check point 7: Check Departure Start And End Date Overlapped
		this.verifyDepartureStartAndEndDateOverlap(schedule, OVERLAP_START_END_DATE.replaceAll("Arrival", "Departure"));
		
		//at last, create a correct fee schedule with Arrival and Departure percent		
		this.AddNewFeeSchedule(schedule);

		// goto schedule details page and verify detailed info
		fnm.searchToFeeScheduleDetailPg(schedule.feeSchdId);

		//check point 8: Check Fee schedule info is correct.
		this.verifyScheduleCommonPart(schedule);
		this.verifyScheduleSlipPart(schedule);
		fnm.logoutFinanceManager();
	}

	private void AddNewFeeSchedule(FeeScheduleData schedule) {
		schedule.pricingBasedonArrival.clear();		
		PricingBase arrival = new PricingBase();
		arrival.startMonth = "Feb";
		arrival.startDate = "1";
		arrival.endMonth = "Feb";
		arrival.endDate = "5";
		arrival.percent = "6";
		schedule.pricingBasedonArrival.add(arrival);
		
		schedule.pricingBasedonDeparture.clear();		
		PricingBase departure = new PricingBase();
		departure.startMonth = "Feb";
		departure.startDate = "11";
		departure.endMonth = "Feb";
		departure.endDate = "15";
		departure.percent = "9";
		schedule.pricingBasedonDeparture.add(departure);
				
		detailPg.removeAllPricingBaseOn("Remove Arrival Percent");
		detailPg.removeAllPricingBaseOn("Remove Departure Percent");
		
		detailPg.fillUseFee(schedule);
		detailPg.clickApply();
		detailPg.waitLoading();
		schedule.feeSchdId = detailPg.getFeeSchID();
		detailPg.clickOK();
		feeMainPg.waitLoading();

	}

	private void verifyArrivalStartAndEndDateOverlap(FeeScheduleData schedule, String expected) {
		schedule.pricingBasedonArrival.clear();		
		PricingBase arrival1 = new PricingBase();
		PricingBase arrival2 = new PricingBase();
		arrival1.startMonth = "Feb";
		arrival1.startDate = "1";
		arrival1.endMonth = "Feb";
		arrival1.endDate = "10";
		arrival1.percent = "6";
		
		arrival2.startMonth = "Feb";
		arrival2.startDate = "5";
		arrival2.endMonth = "Feb";
		arrival2.endDate = "8";
		arrival2.percent = "6";
		schedule.pricingBasedonArrival.add(arrival1);
		schedule.pricingBasedonArrival.add(arrival2);
		
		this.fillAndVerifyErrorMsg(schedule, expected);
	}
	
	private void verifyDepartureStartAndEndDateOverlap(FeeScheduleData schedule, String expected) {
		schedule.pricingBasedonArrival.clear();
		schedule.pricingBasedonDeparture.clear();		
		PricingBase arrival1 = new PricingBase();
		PricingBase arrival2 = new PricingBase();
		arrival1.startMonth = "Feb";
		arrival1.startDate = "1";
		arrival1.endMonth = "Feb";
		arrival1.endDate = "10";
		arrival1.percent = "6";
		
		arrival2.startMonth = "Feb";
		arrival2.startDate = "5";
		arrival2.endMonth = "Feb";
		arrival2.endDate = "8";
		arrival2.percent = "6";
		schedule.pricingBasedonDeparture.add(arrival1);
		schedule.pricingBasedonDeparture.add(arrival2);
		
		this.fillAndVerifyErrorMsg(schedule, expected);
	}
	
	private void verifyNoDepartureStartAndEndDate(FeeScheduleData schedule, String expected) {
		schedule.pricingBasedonArrival.clear();
		schedule.pricingBasedonDeparture.clear();		
		PricingBase arrival = new PricingBase();
		arrival.startMonth = "Feb";
		arrival.startDate = null;
		arrival.endMonth = "Feb";
		arrival.endDate = null;
		arrival.percent = "5";
		schedule.pricingBasedonDeparture.add(arrival);
		
		this.fillAndVerifyErrorMsg(schedule, expected);
	}
	
	private void verifyNoArrivalStartAndEndDate(FeeScheduleData schedule, String expected) {
		schedule.pricingBasedonArrival.clear();		
		PricingBase arrival = new PricingBase();
		arrival.startMonth = "Feb";
		arrival.startDate = null;
		arrival.endMonth = "Feb";
		arrival.endDate = null;
		arrival.percent = "5";
		schedule.pricingBasedonArrival.add(arrival);
		
		this.fillAndVerifyErrorMsg(schedule, expected);
	}

	private void verifyNoDeparturePercentageValue(FeeScheduleData schedule, String expected) {
		schedule.pricingBasedonArrival.clear();
		schedule.pricingBasedonDeparture.clear();		
		PricingBase arrival = new PricingBase();
		arrival.startMonth = "Feb";
		arrival.startDate = "1";
		arrival.endMonth = "Feb";
		arrival.endDate = "10";
		arrival.percent = null;
		schedule.pricingBasedonDeparture.add(arrival);
		this.fillAndVerifyErrorMsg(schedule, expected);
			
	}
	
	private void verifyNoArrivalPercentageValue(FeeScheduleData schedule, String expected) {
		schedule.pricingBasedonArrival.clear();		
		PricingBase arrival = new PricingBase();
		arrival.startMonth = "Feb";
		arrival.startDate = "1";
		arrival.endMonth = "Feb";
		arrival.endDate = "10";
		arrival.percent = null;
		schedule.pricingBasedonArrival.add(arrival);
		this.fillAndVerifyErrorMsg(schedule, expected);
			
	}
	private void verifyNoArrivalAndDeparturePercent(FeeScheduleData schedule, String expected) {
		this.fillAndVerifyErrorMsg(schedule, expected);
	}
	
	private void fillAndVerifyErrorMsg(FeeScheduleData schedule,String expected) {
		
		detailPg.removeAllPricingBaseOn("Remove Arrival Percent");
		detailPg.removeAllPricingBaseOn("Remove Departure Percent");
		
		detailPg.fillUseFee(schedule);
		detailPg.clickApply();
		detailPg.waitLoading();
		String msg = detailPg.getErrorMsg();
		
		if(!msg.replaceAll("\\pP", "").equalsIgnoreCase(expected.replaceAll("\\pP", "")))
		{
			throw new ErrorOnPageException("Check error message failed..", expected, msg);
		}
		logger.info("Check error msg passed!!!");
		
	}
	
	private void gotoFeeDetailPage(FeeScheduleData fd) {
		logger.info("Start to Add New Fee Schedules.");

		feeMainPg.clickAddNew();
		ajax.waitLoading();
		findLocPg.waitLoading();
		findLocPg.searchByLocationName(fd.location, fd.locationCategory);
		findLocPg.selectLocation(fd.location);
		ajax.waitLoading();
		detailPg.waitLoading();
		detailPg.selectPrdCategory(fd.productCategory);
		ajax.waitLoading();
		detailPg.selectFeeType(fd.feeType);
		ajax.waitLoading();
		detailPg.waitLoading();
	}
	
	private void verifyScheduleCommonPart(FeeScheduleData schedule) {
		detailPg.verifyFeeScheduleDetails(schedule);
	}

	private void verifyScheduleSlipPart(FeeScheduleData schedule) {
		logger.info("checking Pricing base on Arrival/Departure for slip...");
		//because there is only 1 arrival record and 1 departure record. so we just compare 1st record.
		Assert.assertEquals("Compare Pricing base on Arrival..",StringUtil.ObjToString(schedule.pricingBasedonArrival.get(0)),StringUtil.ObjToString(detailPg.getPricingBasedOn("Arrival").get(0)));
		Assert.assertEquals("Compare Pricing base on Departure..",StringUtil.ObjToString(schedule.pricingBasedonDeparture.get(0)),StringUtil.ObjToString(detailPg.getPricingBasedOn("Departure").get(0)));
		logger.info("Passed!!!.....");
	}

	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";

		// initialize attribute fee schedule data
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.location = fnm.getFacilityName("552818", schema);// Mayo River State Park
		schedule.locationCategory = "Park";
		schedule.dock = "OverlapSeasonDock";
		schedule.productGroup = "Full attributes";
		schedule.product = "PZH-slip for fee schedule";
		schedule.effectDate = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		schedule.startInv = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1), "EEE MMM d yyyy");
		schedule.endInv = DateFunctions.formatDate(DateFunctions.getDateAfterToday(2), "EEE MMM d yyyy");
		schedule.season = "All";
		schedule.salesChannel = "All";
		schedule.state = "All";
		schedule.custType = "All";
		schedule.boatCategory = "All";
	

		schedule.marinaRateType = "Seasonal";
		schedule.unitOfStay = "Nightly";
		schedule.slipPricingUnit = "Length Range";

		FeeScheduleData.SlipFee fee = schedule.new SlipFee();
		fee.perSeasonFee = "6";
		fee.rangeFeet = "1";
		schedule.slipFees.add(fee);
		
		schedule.minimumUseFee = "3";
		schedule.calculationMethod = "Percentage";
		
		
	}
}
