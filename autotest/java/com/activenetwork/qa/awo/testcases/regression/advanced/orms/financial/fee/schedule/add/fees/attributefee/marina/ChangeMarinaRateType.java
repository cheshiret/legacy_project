/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.attributefee.marina;


import org.junit.Assert;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.common.OrmsConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 		1.Set fee schedule as Transient.
 * 		2. change to Lease. check message displayed.
 * 		3. change to Seasonal. check message displayed.
 * 		4. change to Transient again, and cancel.
 * 		5. check data on page.
 * 
 * @Preconditions:
 * 		1.Slip product is created in NC contract(Mayo River State Park), PRD_CD="PZH". in table "d_inv_add_slip"(ID=160)
 * @SPEC:
 * TC049193,Add Attr Fee Schedule - Change Marina Rate Type
 * @Task#:AUTO-1328
 * 
 * @author pzhu
 * @Date Jan 9, 2013
 */

public class ChangeMarinaRateType extends FinanceManagerTestCase {

	private FeeScheduleData schedule = new FeeScheduleData();

	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();

	private FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
			.getInstance();
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	
	private OrmsConfirmDialogWidget confirm = OrmsConfirmDialogWidget.getInstance();
	
	private static String MARINA_TYPE_CHANGE_MSG =  "Switching the Marina Rate Type will automatically result in the removal of existing Fee entries. Are you sure you want to continue?";
	
	
	
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);
		
		fnm.gotoFeeMainPage();
		this.gotoFeeDetailPage(schedule);
		
		//set transient fee schedule firstly.
		this.setTransientFeeScheduelData(schedule);
		
		//check point 1: check message when change.
		this.changeAndVerifyMarinaRateType("Lease");		
		this.setLeaseFeeScheduleData(schedule);
		
		//check point 2:check message when change.
		this.changeAndVerifyMarinaRateType("Seasonal");		
		this.setSeasonalFeeScheduleData(schedule);
		
		
		this.changeAndCancelMarinaRateType("Transient");
		//check point 3:check if we cancel change, data on page is not changed.
		this.verifyScheduleCommonPart(schedule);
		this.verifyScheduleSlipPart(schedule);
		

		fnm.logoutFinanceManager();
	}



	/**
	 * @param string
	 */
	private void changeAndCancelMarinaRateType(String type) {
		detailsPg.selectMarinaRateType(type);
		ajax.waitLoading();
		confirm.waitLoading();
		String msg = confirm.getMessage();
		
		Assert.assertEquals("Compare confirmation message...", MARINA_TYPE_CHANGE_MSG.replaceAll("\\pP", ""), msg.replaceAll("\\pP", ""));
		confirm.clickCancel();
		ajax.waitLoading();
		detailsPg.waitLoading();
		
		logger.info("Cancel Change to "+type+"...");
		
	}


	private void setSeasonalFeeScheduleData(FeeScheduleData schedule) {
		this.setSeasonalData(schedule);
		detailsPg.fillAttributeFee(schedule);
	}

	

	private void setLeaseFeeScheduleData(Object sch) {
		this.setLeaseData(schedule);
		detailsPg.fillAttributeFee(schedule);	
		
	}


	private void changeAndVerifyMarinaRateType(String type) {
		
		detailsPg.selectMarinaRateType(type);
		ajax.waitLoading();
		confirm.waitLoading();
		String msg = confirm.getMessage();
		
		Assert.assertEquals("Compare confirmation message...", MARINA_TYPE_CHANGE_MSG.replaceAll("\\pP", ""), msg.replaceAll("\\pP", ""));
		confirm.clickOK();
		ajax.waitLoading();
		detailsPg.waitLoading();
		
		logger.info("Change to "+type+" passed...");
	}



	private void setTransientFeeScheduelData(FeeScheduleData schedule) {
		this.setTransientData(schedule);
		detailsPg.fillAttributeFee(schedule);		
	}

	private void gotoFeeDetailPage(FeeScheduleData fd) {
		logger.info("Start to Add New Fee Schedules.");

		feeMainPg.clickAddNew();
		ajax.waitLoading();
		findLocPg.waitLoading();
		findLocPg.searchByLocationName(fd.location, fd.locationCategory);
		findLocPg.selectLocation(fd.location);
		ajax.waitLoading();
		detailsPg.waitLoading();
		detailsPg.selectPrdCategory(fd.productCategory);
		ajax.waitLoading();
		detailsPg.selectFeeType(fd.feeType);
		ajax.waitLoading();
		detailsPg.waitLoading();
	}
	
	
	private void verifyScheduleCommonPart(FeeScheduleData schedule) {
		detailsPg.verifyFeeScheduleDetails(schedule);
	}

	private void verifyScheduleSlipPart(FeeScheduleData schedule) {
		logger.info("checking schedule for slip...");
		Assert.assertEquals("Compare attribute type..", schedule.attrType, detailsPg.getAttrType());
		Assert.assertEquals("Compare marina rate type..",schedule.marinaRateType,detailsPg.getMarinaRateTypeOfRadioButton());
		Assert.assertEquals("Compare Seasonal fee..",Double.valueOf(schedule.slipFees.get(0).perSeasonFee),Double.valueOf(detailsPg.getSlipSeasonFee(schedule.slipPricingUnit).get(0)));
		logger.info("Passed!!!.....");
	}


	private void setTransientData(FeeScheduleData schedule) {
		schedule.marinaRateType = "Transient";
		schedule.unitOfStay = "Nightly";
		schedule.rafting = "All";
		schedule.slipPricingUnit = "Length Range";
		
		schedule.slipFees.clear();
		FeeScheduleData.SlipFee fee = schedule.new SlipFee();
		fee.dailyNightlyFee = "6";
		fee.rangeFeet = "1";
		schedule.slipFees.add(fee);
		
	}
	
	
	private void setSeasonalData(FeeScheduleData schedule) {
		schedule.marinaRateType = "Seasonal";
		schedule.unitOfStay = "Nightly";
		schedule.rafting = "";
		schedule.slipPricingUnit = "Length Range";
		
		schedule.slipFees.clear();		
		FeeScheduleData.SlipFee fee = schedule.new SlipFee();
		fee.perSeasonFee = "9";
		fee.rangeFeet = "1";
		schedule.slipFees.add(fee);

	}
	
	private void setLeaseData(FeeScheduleData schedule) {
		schedule.marinaRateType = "Lease";
		schedule.unitOfStay = "Nightly";
		schedule.rafting = "";
		schedule.slipPricingUnit = "Length Range";
		
		schedule.slipFees.clear();		
		FeeScheduleData.SlipFee fee = schedule.new SlipFee();
		fee.monthlyFee = "6";
		fee.rangeFeet = "1";
		schedule.slipFees.add(fee);

	}
	
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";

		// initialize attribute fee schedule data
		schedule.productCategory = "Slip";
		schedule.feeType = "Attribute Fee";
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
		schedule.attrType = "Electricity Hookup";
		schedule.attrValue = "50";
			
		
	}


}
