/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee.marina.slip;

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
 * @Description:This test case verify add transaction fee for slip
 *              check point:1.Location at a Facility Level
 *                          2.Location at a level higher than Facility Level
 * @Preconditions:
 * @SPEC: TC:047800
 * @Task#: AUTO-1331
 * @author szhou
 * @Date  Jan 07, 2013
 */
public class VerifyAssignmentSelectionForSlip_Product extends
		FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData();
	private String schema, locationID;

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		// Location at a Facility Level
		this.gotoTransactionFeeDetailPage(schedule);
		this.verifyDefaultValueForProduct();
		this.verifyProductElement(schema, locationID, false);

		// Location at a level higher than Facility Level
		schedule.locationCategory = "Agency";
		schedule.location = "NC Division of Parks and Recreation";
		this.gotoTransactionFeeDetailPage(schedule);
		this.verifyDefaultValueForProduct();
		this.verifyProductElement(schema, locationID, true);

		fnm.logoutFinanceManager();

	}

	private void verifyProductElement(String schema, String locationID,
			boolean ishighthanFacility) {
		FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();

		List<String> actually = detailsPg.getAssignProductElements();
		if (ishighthanFacility) {
			if (actually.size() != 1) {
				throw new ErrorOnPageException(
						"Product element should only have 'All'.");
			}
		} else {
			List<String[]> dataInDB = fnm
					.getProductNameUsingFeeSchdDetailPgFromDB(schema, "20", "",
							locationID, "");
			if (actually.size() - 1 != dataInDB.size()) {
				throw new ErrorOnPageException("Product element ",
						dataInDB.size(), actually.size());
			}
		}

		detailsPg.clickCancel();
		feeMainPg.waitLoading();

	}

	private void verifyDefaultValueForProduct() {
		FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();

		String value = detailsPg.getAssignProduct();
		if (!"All".equals(value)) {
			throw new ErrorOnPageException("Product", "All", value);
		}
	}

	private void gotoTransactionFeeDetailPage(FeeScheduleData fd) {
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		FinMgrFeeFindLocationPage findLocPg = FinMgrFeeFindLocationPage
				.getInstance();
		FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();

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
		schedule.feeType = "Transaction Fee";
		schedule.locationCategory = "Park";
		schedule.location = "Jordan Lake State Rec Area";
		locationID = "552903";

	}

}