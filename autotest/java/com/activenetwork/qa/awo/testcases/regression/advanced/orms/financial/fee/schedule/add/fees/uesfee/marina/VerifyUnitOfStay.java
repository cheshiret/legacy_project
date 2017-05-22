/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.uesfee.marina;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This test case verify add use fee for slip
 *              check point:1.verify drop down list of Unit of Stay
 *                          
 * @Preconditions:
 * @SPEC: TC:047838
 * @Task#: AUTO-1331
 * @author szhou
 * @Date  Jan 08, 2013
 */
public class VerifyUnitOfStay extends FinanceManagerTestCase {
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage
			.getInstance();
	private FeeScheduleData schedule = new FeeScheduleData();
	private List<String> expectValue;

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		this.gotoFeeDetailPage(schedule);

		// verify drop down list of Unit of Stay.
		this.verifyDefaultValueForUnitOfStay();
		this.verifyValueForUnitOfStay(expectValue);
		
		expectValue.remove(1);
		detailsPg.selectMarinaRateType("Seasonal");
		ajax.waitLoading();
		this.verifyValueForUnitOfStay(expectValue);
		detailsPg.selectMarinaRateType("Lease");
		ajax.waitLoading();
		this.verifyValueForUnitOfStay(expectValue);

		fnm.logoutFinanceManager();
	}

	private void verifyValueForUnitOfStay(List<String> expect) {
		

		List<String> values = detailsPg.getUnitOfStayElement();
		if (expect.size() != values.size() ) {
			throw new ErrorOnPageException(
					"Unit Of Stay element size is not correct.");
		}
		for (String value : expect) {
			if (!values.contains(value)) {
				throw new ErrorOnPageException(
						"Unit Of Stay should contains value :" + value);
			}
		}
	}

	private void verifyDefaultValueForUnitOfStay() {

		String value = detailsPg.getUnitOfStay();
		if (!"Nightly".equals(value)) {
			throw new ErrorOnPageException("Unit of Stay", "Nightly", value);
		}
	}

	private void gotoFeeDetailPage(FeeScheduleData fd) {
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
				.getInstance();

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

	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";

		// initialize transaction fee schedule data
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.locationCategory = "Park";
		schedule.location = "Jordan Lake State Rec Area";
		expectValue = new ArrayList<String>();
		expectValue.add("Nightly");
		expectValue.add("Daily");

	}

}
