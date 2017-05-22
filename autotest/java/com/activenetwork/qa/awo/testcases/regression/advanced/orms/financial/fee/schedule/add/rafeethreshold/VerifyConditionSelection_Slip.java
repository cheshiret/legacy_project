package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafeethreshold;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:check point:1. verify sales channel drop down list element and default value
 *                          2. compare sales channel
 * @Preconditions:
 * @SPEC:TC:042167
 * @Task#:AUTO-1335
 * 
 * @author szhou
 * @Date  Nov 12, 2012
 */
public class VerifyConditionSelection_Slip extends FinanceManagerTestCase {
	private ScheduleData schedule = new ScheduleData();
	private List<String> content;

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeThresholdPage();

		// verify sales channel content
		this.gotoAddRaFeeThresholdPg();
		this.verifySalesChannel(content);

		// verify sale channel content is All
		schedule.scheduleId = fnm.addNewRaFeeThresholdSchedule(schedule);
		fnm.gotoRaFeeThresholdDetailPgBySchdId(schedule.scheduleId);
		this.verifySalesChannelValue(schedule.salesChannel);

		// verify sale channel content is Specific field
		schedule.salesChannel="Field";
		schedule.scheduleId = fnm.addNewRaFeeThresholdSchedule(schedule);
		fnm.gotoRaFeeThresholdDetailPgBySchdId(schedule.scheduleId);
		this.verifySalesChannelValue(schedule.salesChannel);

		fnm.logoutFinanceManager();
	}

	private void verifySalesChannelValue(String expect) {
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage
				.getInstance();

		String value = detailPg.getSaleChannel();
		if (!expect.equals(value)) {
			throw new ErrorOnPageException(
					"Sales Channel is not correct, Expect value : " + expect
							+ ";But Actually value :" + value);
		}
		
		detailPg.clickCancel();
		ajax.waitLoading();
		searchPg.waitLoading();
		
	}

	private void verifySalesChannel(List<String> expect) {
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage
				.getInstance();

		// verify default value
		String defaultValue = detailPg.getSaleChannel();
		if (!"All".equals(defaultValue)) {
			throw new ErrorOnPageException(
					"Sales Channel default value should be 'All'.");
		}
		// verify sale channel content
		List<String> value = detailPg.getSaleChannelElement();
		if (expect.size() != value.size()) {
			throw new ErrorOnPageException(
					"Sales Channel element size is not correct. Expect size is "
							+ expect.size() + ",but actually size is"
							+ value.size());
		}
		for (String element : expect) {
			if (!value.contains(element)) {
				throw new ErrorOnPageException(
						"Sales Channel element should be contains " + element);
			}
		}

		detailPg.clickCancel();
		ajax.waitLoading();
		searchPg.waitLoading();
	}

	private void gotoAddRaFeeThresholdPg() {
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage
				.getInstance();
		FinMgrFeeFindLocationPage findLocationPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();

		logger.info("Start to Add New RA Fee Threshold Schedule.");

		searchPg.clickAddNew();
		findLocationPg.waitLoading();
		findLocationPg.searchByLocationName(schedule.location,
				schedule.locationCategory);
		findLocationPg.selectLocation(schedule.location);
		detailPg.waitLoading();
		detailPg.selectProdCategory(schedule.productCategory);
		detailPg.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";

		// initialize schedule data
		schedule.location="Mayo River State Park";
	  	schedule.locationCategory="Park";
		schedule.productCategory = "Slip";
		schedule.startCounter = "1";
		schedule.effectiveDate = DateFunctions.getDateAfterToday(-5);
		schedule.salesChannel="All";
		
		content=new ArrayList<String>();
		content.add("All");
		content.add("Field");
		content.add("Call Center");
		content.add("Web");

	}

}
