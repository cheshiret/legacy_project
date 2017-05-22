/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafeethreshold;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:check point:1. verify error message for start counter
 *                          2. compare start counter
 * @Preconditions:
 * @SPEC:TC:042194
 * @Task#:AUTO-1335
 * 
 * @author szhou
 * @Date  Nov 12, 2012
 */
public class VerifyCounterSelection_Slip extends FinanceManagerTestCase {
	private ScheduleData schedule = new ScheduleData();
	private String expectError;

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeThresholdPage();

		// verify error message,counter is blank
		String error = fnm.addNewRaFeeThresholdSchedule(schedule);
		this.verifyErrorMessage(expectError, error);

		// verify error message,counter need greater than 0;
		expectError="The Starting Count must have a value greater than or equal to 0. Please change your entries.";
		schedule.startCounter = "-1";
		error = fnm.addNewRaFeeThresholdSchedule(schedule);
		this.verifyErrorMessage(expectError, error);

		// verify start counter
		schedule.startCounter = "0";
		schedule.scheduleId = fnm.addNewRaFeeThresholdSchedule(schedule);
		fnm.gotoRaFeeThresholdDetailPgBySchdId(schedule.scheduleId);
		this.verifyCounter(schedule.startCounter);

		fnm.logoutFinanceManager();
	}

	private void verifyCounter(String count) {
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage
				.getInstance();

		String countInPg = detailPg.getStartCounter();

		// verify date content
		boolean result = true;
		result &= MiscFunctions
				.compareResult("Start Counter", count, countInPg);
		if (!result) {
			throw new ErrorOnPageException(
					"Start Counter information is not correct. Please see log file...");
		}

		detailPg.clickCancel();
		ajax.waitLoading();
		searchPg.waitLoading();
	}

	private void verifyErrorMessage(String expect, String actually) {
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage
				.getInstance();
		
		if (!expect.equals(actually)) {
			throw new ErrorOnPageException(
					"Error is not correct, Expect Message : " + expect
							+ ";But Actually Message :" + actually);
		}
		
		detailPg.clickCancel();
		ajax.waitLoading();
		searchPg.waitLoading();
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
		schedule.effectiveDate = DateFunctions.getDateAfterToday(-5);
		
		expectError="The Starting Count is required. Please enter the Starting Count in the field provided.";
	}

}
