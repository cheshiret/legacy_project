
/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.attributefee.marina;


import org.junit.Assert;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: 
 * 		1.create attribute fee schedule for slip product, Marina Rate Type of which is "Lease".
 * 
 * @Preconditions:
 * 		1.Slip product is created in NC contract(Mayo River State Park), PRD_CD="PZH". in table "d_inv_add_slip"(ID=160)
 * @SPEC:TC043405,Add Attr Fee Schedule - Lease
 * @Task#:AUTO-1328
 * 
 * @author pzhu
 * @Date Jan 7, 2013
 */

public class AddLease extends FinanceManagerTestCase {

	private FeeScheduleData schedule = new FeeScheduleData();

	private FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();

	private FinMgrFeeSchDetailPage detailPg = FinMgrFeeSchDetailPage
			.getInstance();

	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		// Add a new Fee schedule data
		schedule.feeSchdId = fnm.addNewFeeSchedule(schedule);
		// verify new added fee schedule status is inactive
		feeMainPg.verifyStatus(schedule.feeSchdId, "Inactive");
		// goto schedule details page and verify datails info
		fnm.searchToFeeScheduleDetailPg(schedule.feeSchdId);

		this.verifyScheduleCommonPart(schedule);
		this.verifyScheduleSlipPart(schedule);
		fnm.logoutFinanceManager();
	}

	
	private void verifyScheduleCommonPart(FeeScheduleData schedule) {
		detailPg.verifyFeeScheduleDetails(schedule);
	}

	private void verifyScheduleSlipPart(FeeScheduleData schedule) {
		logger.info("checking schedule for slip...");
		Assert.assertEquals("Compare attribute type..", schedule.attrType, detailPg.getAttrType());
		Assert.assertTrue(detailPg.getAttrValue().contains(schedule.attrValue));
		Assert.assertEquals("Compare marina rate type..",schedule.marinaRateType,detailPg.getMarinaRateTypeOfRadioButton());
		Assert.assertEquals("Compare monthly fee..",Double.valueOf(schedule.slipFees.get(0).monthlyFee),Double.valueOf(detailPg.getSlipMonthlyFee(schedule.slipPricingUnit).get(0)));
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
		schedule.effectDate = DateFunctions.formatDate(
				DateFunctions.getToday(), "EEE MMM d yyyy");
		schedule.startInv = DateFunctions.formatDate(DateFunctions
				.getDateAfterToday(-1), "EEE MMM d yyyy");
		schedule.endInv = DateFunctions.formatDate(DateFunctions
				.getDateAfterToday(2), "EEE MMM d yyyy");
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
