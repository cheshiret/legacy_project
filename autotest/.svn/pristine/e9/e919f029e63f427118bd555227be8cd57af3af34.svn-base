/**
 * 
 */
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
 * @Description:check point:1. verify marina rate type drop down list element and default value
 *                          2. compare marina rate type
 * @Preconditions:
 * @SPEC:TC:042195
 * @Task#:AUTO-1335
 * 
 * @author szhou
 * @Date  Nov 13, 2012
 */
public class VerifyMarinaRateTypeSelection_Slip extends FinanceManagerTestCase {
	private ScheduleData schedule = new ScheduleData();
	private List<String> content;

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeThresholdPage();

		// verify Marina rate type content
		this.gotoAddRaFeeThresholdPg();
		this.verifyMarinaRateType(content);

		// verify Marina rate type content is Specific field
		schedule.marinaRateType = "Transient";
		schedule.scheduleId = fnm.addNewRaFeeThresholdSchedule(schedule);
		fnm.gotoRaFeeThresholdDetailPgBySchdId(schedule.scheduleId);
		this.verifyMarinaRateTypeValue(schedule.marinaRateType);

		fnm.logoutFinanceManager();
	}

	private void verifyMarinaRateTypeValue(String expect) {
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage
				.getInstance();

		String value = detailPg.getMarinaRateType();
		if (!expect.equals(value)) {
			throw new ErrorOnPageException(
					"Marina rate type is not correct, Expect value : " + expect
							+ ";But Actually value :" + value);
		}

		detailPg.clickCancel();
		ajax.waitLoading();
		searchPg.waitLoading();

	}

	private void verifyMarinaRateType(List<String> expect) {
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage
				.getInstance();

		// verify default value
		String defaultValue = detailPg.getMarinaRateType();
		if (!"All".equals(defaultValue)) {
			throw new ErrorOnPageException(
					"Marina rate type default value should be 'All'.");
		}
		
		// verify sale channel content and sorting
		List<String> value = detailPg.getMarinaRateTypeElement();
		value.remove(value.indexOf("All"));
		if (expect.size() != value.size()) {
			throw new ErrorOnPageException(
					"Marina rate type element size is not correct. Expect size is "
							+ expect.size() + ",but actually size is"
							+ value.size());
		}
//		Collections.sort(expect);
		for (int i = 0; i < expect.size(); i++) {
			if (!expect.get(i).equals(value.get(i))) {
				throw new ErrorOnPageException(
						"Marina rate type element should be " + expect.get(i)
								+ " ,but actually the value is " + value.get(i));
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
		schedule.location = "Mayo River State Park";
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
		schedule.startCounter = "1";
		schedule.effectiveDate = DateFunctions.getDateAfterToday(-5);

		content = new ArrayList<String>();
		content.add("Seasonal");
		content.add("Lease");
		content.add("Transient");
		
		

	}

}
