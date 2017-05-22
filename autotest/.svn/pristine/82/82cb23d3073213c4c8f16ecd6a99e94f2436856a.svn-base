package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.uesfee.marina;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData.PricingBase;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Add a marina fee schedule with "Seasonal" as Marina Rate Type .
 * @Preconditions:
 * @SPEC:  TC:043377
 * @Task#: Auto-1326
 * 
 * @author Jasmine
 * @Date  Jan 7, 2013(p)
 */
public class AddSeasonal extends FinanceManagerTestCase{
	 private FeeScheduleData schedule = new FeeScheduleData();
	 private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
	 private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	 private FeeScheduleData.SlipFee slipFee1 =schedule.new SlipFee();
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		schedule.feeSchdId = fnm.addNewFeeSchedule(schedule);
		feeMainPg.searchByFeeScheduleId(schedule.feeSchdId);
		fnm.gotoFeeSchedulePageByScheduleId(schedule.feeSchdId);
		detailsPg.verifyFeeScheduleDetails(schedule);
		this.goBackFeeMainPage();
		this.changeFeeScheduleValue();
		schedule.feeSchdId = fnm.addNewFeeSchedule(schedule);
		feeMainPg.searchByFeeScheduleId(schedule.feeSchdId);
		fnm.gotoFeeSchedulePageByScheduleId(schedule.feeSchdId);
		detailsPg.verifyFeeScheduleDetails(schedule);
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.url = AwoUtil.getOrmsURL(env);
		login.location = "Administrator - Auto/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		
		schedule.location = fnm.getFacilityName("552831", schema);// Eno River State Park";
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.effectDate = DateFunctions.getToday();
		schedule.startInv = DateFunctions.getToday();
		schedule.endInv = DateFunctions.getToday();
		schedule.marinaRateType = "Seasonal";
		schedule.unitOfStay = "Nightly";
		schedule.slipPricingUnit = "Length Range";
		slipFee1.perSeasonFee = "2.0";
		schedule.slipFees.add(slipFee1);
		schedule.minimumUseFee = "3.0";
		schedule.calculationMethod = "Daily";
	}

	private void changeFeeScheduleValue(){
		schedule.slipPricingUnit = "Length Increments";
		schedule.baseRatePerSeason = "30.0";
		schedule.slipFees.clear();
		slipFee1.increment = "1";
		slipFee1.perSeasonFee = "4.0";
		schedule.slipFees.add(slipFee1);
		schedule.calculationMethod = "Percentage";
		PricingBase arrivalPercent = new PricingBase();
		arrivalPercent.percent = "20";
		arrivalPercent.startMonth = "Dec";
		arrivalPercent.startDate = "17";
		arrivalPercent.endMonth = "Jul";
		arrivalPercent.endDate = "19";
		schedule.pricingBasedonArrival.add(arrivalPercent);
		
	}
	
	private void goBackFeeMainPage(){
		detailsPg.clickCancel();
		ajax.waitLoading();
		feeMainPg.waitLoading();
	}
}
