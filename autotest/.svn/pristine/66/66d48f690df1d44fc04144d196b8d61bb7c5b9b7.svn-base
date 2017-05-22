/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafeethreshold;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:check point:1. verify transaction type content
 *                          2. verify transaction occurrence content
 * @Preconditions:
 * @SPEC:TC:042168
 * @Task#:AUTO-1335
 * 
 * @author szhou
 * @Date  Nov 14, 2012
 */
public class VerifyTransactionSelection_Slip extends FinanceManagerTestCase {
	private ScheduleData schedule = new ScheduleData();

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeThresholdPage();

		this.gotoAddRaFeeThresholdPg();
		// verify transaction type content
		this.verifyTransactionType();
		// verify transaction occurrence content
		this.verifyTransactionOccurrence();

		fnm.logoutFinanceManager();

	}

	private void verifyTransactionType() {
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();

		// verify default value
		String defaultValue = detailPg.getTranType();
		if (!"All".equals(defaultValue)) {
			throw new ErrorOnPageException(
					"Transaction Type default value should be 'All'.");
		}
		// verify transaction type content
		List<String> value = detailPg.getTranTypeElement();
		value.remove(value.indexOf("All"));
		for (int i = 0; i < value.size(); i++) {
			if (i + 1 < value.size()
					&& (value.get(i).compareTo(value.get(i + 1)) >= 0)) {
				throw new ErrorOnPageException(
						"Transaction Type element should order by alpha-numeric sorting in ascending order.");
			}
		}
	}

	private void verifyTransactionOccurrence() {
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();

		// verify default value
		String defaultValue = detailPg.getTranOccurrence();
		if (!"All".equals(defaultValue)) {
			throw new ErrorOnPageException(
					"Transaction Occurrence default value should be 'All'.");
		}
		// verify transaction Occurrence content
		List<String> value = detailPg.getTranOccurrenceElement();
		value.remove(value.indexOf("All"));
		for (int i = 0; i < value.size(); i++) {
			if (i + 1 < value.size()
					&& (value.get(i).compareTo(value.get(i + 1)) >= 0)) {
				throw new ErrorOnPageException(
						"Transaction Occurrence element should order by alpha-numeric sorting in ascending order.");
			}
		}
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

	}

}
