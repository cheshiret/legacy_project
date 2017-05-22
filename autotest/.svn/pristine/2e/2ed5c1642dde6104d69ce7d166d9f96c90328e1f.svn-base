package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.attributefee.marina;

import org.junit.Assert;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: It is to check that when account code is not set, the attribute fee for slip could be added successfully
 * @Preconditions: None
 * @SPEC: TC:047690
 * @Task#: Auto-1329
 * @author Phoebe Chen
 * @Date  Jan 16, 2013
 */
public class Validate_EmptyAccountCode extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData();
	private FinMgrFeeSchDetailPage detailPg = FinMgrFeeSchDetailPage
			.getInstance();
	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		// Add a new Fee schedule data
		schedule.feeSchdId = fnm.addNewFeeSchedule(schedule);
		
		// goto schedule details page and verify datails info
		fnm.searchToFeeScheduleDetailPg(schedule.feeSchdId);

		this.verifyScheduleDetailInfo(schedule);
		
		fnm.logoutFinanceManager();
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
		schedule.productCategory = "Slip";      //This is the check point, can not be changed
		schedule.feeType = "Attribute Fee";     //This is the check point, can not be changed
		
		schedule.productGroup = "Full attributes";
		schedule.effectDate = DateFunctions.formatDate(
				DateFunctions.getDateAfterToday(-10), "EEE MMM d yyyy");
		schedule.startInv = DateFunctions.formatDate(DateFunctions
				.getDateAfterToday(-9), "EEE MMM d yyyy");
		schedule.endInv = DateFunctions.formatDate(DateFunctions
				.getDateAfterToday(-8), "EEE MMM d yyyy");
		schedule.acctCode = "none";       //This is the check point, which means the account code must be empty
		schedule.attrType = "Electricity Hookup";
		schedule.attrValue = "15";

		schedule.season = "All";
		schedule.salesChannel = "All";
		schedule.state = "All";
		schedule.custType = "All";
		schedule.boatCategory = "All";
		
		schedule.marinaRateType = "Seasonal";
		schedule.unitOfStay = "Nightly";
		schedule.slipPricingUnit = "Length Range";

		FeeScheduleData.SlipFee fee = schedule.new SlipFee();
		fee.perSeasonFee = "3";
		fee.rangeFeet = "1";
		schedule.slipFees.add(fee);
		
		schedule.calculationMethod = "Daily";
	}
	
	private void verifyScheduleDetailInfo(FeeScheduleData schedule) {
		detailPg.verifyFeeScheduleDetails(schedule);
		Assert.assertEquals("Compare attribute type..", schedule.attrType, detailPg.getAttrType());
		Assert.assertTrue(detailPg.getAttrValue().contains(schedule.attrValue));
		Assert.assertEquals("Compare marina rate type..",schedule.marinaRateType,detailPg.getMarinaRateTypeOfRadioButton());
		Assert.assertEquals("Compare season fee..",Double.valueOf(schedule.slipFees.get(0).perSeasonFee),Double.valueOf(detailPg.getSlipSeasonFee(schedule.slipPricingUnit).get(0)));
	}
}
