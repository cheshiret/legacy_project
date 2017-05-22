
/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.attributefee.marina;


import org.junit.Assert;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 		1.Try to create attribute fee schedule for slip product.
 * 		2. Use empty effective date, check error msg.
 * 		3. Cancel, then go to fee schedule main page.
 * 		4. Use empty inventory date, check error msg.
 * 		5. add successfully, then check detail. 
 * 
 * @Preconditions:
 * 		1.Slip product is created in NC contract(Mayo River State Park), PRD_CD="PZH". in table "d_inv_add_slip"(ID=160)
 * @SPEC:
 * TC043406,Add Attr Fee Schedule - Effective Date validation 
 * TC043407,Add Attr Fee Schedule - Inventory Date validation 
 * TC043403,Add Attr Fee Schedule - Cancel
 * @Task#:AUTO-1328
 * 
 * @author pzhu
 * @Date Jan 7, 2013
 */

public class VerifyDateAndCancel extends FinanceManagerTestCase {

	private FeeScheduleData schedule = new FeeScheduleData();

	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();

	private FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
			.getInstance();
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	
	private static String EMPTY_EFFECTIVE_DATE =       	"An Effective Date for the Fee Schedule is required";
	private static String EMPTY_INVENTORY_START_DATE = 	"An Inventory Start Date for the Fee Schedule is required";
	private static String EMPTY_INVENTORY_END_DATE = 	"An Inventory End Date for the Fee Schedule is required";
	private static String INVENTORY_DATE_ERROR = 		"The Inventory Start Date must not be later than the Inventory End Date";
	
	
	
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);
		
		fnm.gotoFeeMainPage();
		this.gotoFeeDetailPage(schedule);
		
		//check point 1:
		this.verifyEmptyEffectiveDate(schedule);
		
		//check point 2:
		this.verifyCancel();
		this.gotoFeeDetailPage(schedule);
		
		//check point 3:
		this.verifyEmptyStartDate(schedule);
		this.verifyCancel();
		this.gotoFeeDetailPage(schedule);
		
		//check point 4:
		this.verifyEmptyEndDate(schedule);
		this.verifyCancel();
		this.gotoFeeDetailPage(schedule);
		
		//check point 5:
		this.verifyErrorInventoryDate(schedule);
		
		schedule.feeSchdId = this.addScheduleSuccessfully(schedule);
							
		// verify new added fee schedule status is inactive
		feeMainPg.verifyStatus(schedule.feeSchdId, "Inactive");
		// goto schedule details page and verify datails info
		fnm.searchToFeeScheduleDetailPg(schedule.feeSchdId);

		this.verifyScheduleCommonPart(schedule);
		this.verifyScheduleSlipPart(schedule);
		fnm.logoutFinanceManager();
	}



	private String addScheduleSuccessfully(FeeScheduleData fee) {
		fee.effectDate = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		fee.startInv = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1), "EEE MMM d yyyy");
		fee.endInv = DateFunctions.formatDate(DateFunctions.getDateAfterToday(2), "EEE MMM d yyyy");
		
		detailsPg.fillAttributeFee(fee);
		detailsPg.clickApply();
		detailsPg.waitLoading();
		
		String feeID = detailsPg.getFeeSchID(); 
		detailsPg.clickOK();
		feeMainPg.waitLoading();
		
		return feeID;
	}


	/**
	 * 
	 */
	private void verifyCancel() {
		detailsPg.clickCancel();
		feeMainPg.waitLoading();		
	}


	private void verifyEmptyEffectiveDate(FeeScheduleData fee) {
		fee.effectDate = "";
		fee.startInv = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-1), "EEE MMM d yyyy");
		fee.endInv = DateFunctions.formatDate(DateFunctions.getDateAfterToday(2), "EEE MMM d yyyy");	
		
		detailsPg.fillAttributeFee(fee);
		detailsPg.clickApply();
		detailsPg.waitLoading();
		String msg = detailsPg.getErrorMsg();
		if(!msg.toUpperCase().contains(EMPTY_EFFECTIVE_DATE.toUpperCase()))
		{
			throw new ErrorOnPageException("check error msg failed...", EMPTY_EFFECTIVE_DATE, msg);
		}else{
			logger.info("Check error msg for empty effective date passed!!!");
		}
		
	}
	
	private void verifyEmptyStartDate(FeeScheduleData fee) {
		fee.effectDate = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		fee.startInv = "";
		fee.endInv = DateFunctions.formatDate(DateFunctions.getDateAfterToday(2), "EEE MMM d yyyy");
		detailsPg.fillAttributeFee(fee);
		detailsPg.clickApply();
		detailsPg.waitLoading();
		String msg = detailsPg.getErrorMsg();
		if(!msg.toUpperCase().contains(EMPTY_INVENTORY_START_DATE.toUpperCase()))
		{
			throw new ErrorOnPageException("check error msg failed...", EMPTY_INVENTORY_START_DATE, msg);
		}else{
			logger.info("Check error msg for empty inventory start date passed!!!");
		}
		
	}
	
	private void verifyEmptyEndDate(FeeScheduleData fee) {
		fee.effectDate = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		fee.startInv = DateFunctions.formatDate(DateFunctions.getDateAfterToday(2), "EEE MMM d yyyy");		
		fee.endInv = "";
		
		detailsPg.fillAttributeFee(fee);
		detailsPg.clickApply();
		detailsPg.waitLoading();
		String msg = detailsPg.getErrorMsg();
		if(!msg.toUpperCase().contains(EMPTY_INVENTORY_END_DATE.toUpperCase()))
		{
			throw new ErrorOnPageException("check error msg failed...", EMPTY_INVENTORY_END_DATE, msg);
		}else{
			logger.info("Check error msg for empty inventory end date passed!!!");
		}
		
	}

	private void verifyErrorInventoryDate(FeeScheduleData fee) {
		fee.effectDate = DateFunctions.formatDate(DateFunctions.getToday(), "EEE MMM d yyyy");
		fee.startInv = DateFunctions.formatDate(DateFunctions
				.getDateAfterToday(-1), "EEE MMM d yyyy");
		fee.endInv = DateFunctions.formatDate(DateFunctions
				.getDateAfterToday(-5), "EEE MMM d yyyy");
		detailsPg.fillAttributeFee(fee);
		detailsPg.clickApply();
		detailsPg.waitLoading();
		String msg = detailsPg.getErrorMsg();
		if(!msg.toUpperCase().contains(INVENTORY_DATE_ERROR.toUpperCase()))
		{
			throw new ErrorOnPageException("check error msg failed...", INVENTORY_DATE_ERROR, msg);
		}else{
			logger.info("Check error msg for error inventory date passed!!!");
		}
		
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
		Assert.assertTrue(detailsPg.getAttrValue().contains(schedule.attrValue));
		Assert.assertEquals("Compare marina rate type..",schedule.marinaRateType,detailsPg.getMarinaRateTypeOfRadioButton());
		Assert.assertEquals("Compare monthly fee..",Double.valueOf(schedule.slipFees.get(0).monthlyFee),Double.valueOf(detailsPg.getSlipMonthlyFee(schedule.slipPricingUnit).get(0)));
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
		schedule.feeType = "Attribute Fee";
		schedule.location = fnm.getFacilityName("552818", schema);// Mayo River State Park
		schedule.locationCategory = "Park";
		schedule.dock = "OverlapSeasonDock";
		schedule.productGroup = "Full attributes";
		schedule.product = "PZH-slip for fee schedule";
//		schedule.effectDate = DateFunctions.formatDate(
//				DateFunctions.getToday(), "EEE MMM d yyyy");
//		schedule.startInv = DateFunctions.formatDate(DateFunctions
//				.getDateAfterToday(-1), "EEE MMM d yyyy");
//		schedule.endInv = DateFunctions.formatDate(DateFunctions
//				.getDateAfterToday(2), "EEE MMM d yyyy");
		schedule.season = "All";
		schedule.salesChannel = "All";
		schedule.state = "All";
		schedule.custType = "All";
		schedule.boatCategory = "All";
		schedule.attrType = "Electricity Hookup";
		schedule.attrValue = "50";

		schedule.marinaRateType = "Lease";
		schedule.unitOfStay = "Nightly";
		schedule.slipPricingUnit = "Length Range";

		FeeScheduleData.SlipFee fee = schedule.new SlipFee();
		fee.monthlyFee = "6";
		fee.rangeFeet = "1";
		schedule.slipFees.add(fee);
		
		
	}
}
