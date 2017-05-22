/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.twoScheWithNonOverlapInv;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This case was designed as a boundary case for creating and activating pos fee schedules, to cover Defect-34432.
 * @Preconditions: 
 * @SPEC:
 * @Task#: Auto-1051
 * 
 * @author Jane Wang
 * @Date  May 14, 2012
 */
public class PosFee extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData();
	private List<String> ids = new ArrayList<String>();
	
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		//deactivate existing fee schedules with same parameters
		deactiveExsitedFeeSchedules();
		//add and active a fee schedule
		schedule.toDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-48), "EEE MMM d yyyy");
		addAndActiveFeeSchedule();

		schedule.fromDate = DateFunctions.getDateAfterGivenDay(schedule.fromDate, -50);
		schedule.toDate = DateFunctions.getDateAfterGivenDay(schedule.toDate, -49);
		//add another fee schedule with same parameters and non-overlap inventory
		//and active above fee schedule
		addAndActiveFeeSchedule();
		//deactivate new created fee schedules
		cleanUp();
		
		fnm.logoutFinanceManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "NRRS Contract";
	  	login.location = "Administrator/NRRS";
	  	schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
	  	
	  	schedule.productCategory = "POS";
	  	schedule.feeType ="POS Fee";
		schedule.locationCategory = "Park";
	  	schedule.location = "RYAN PARK";
	  	schedule.productGroup = "AREA DAYU VEH";//ANNUAL DAY USE PASS
	  	schedule.product = "POS for ChangeTaxDecimalsFrom2To4";//All
	  	schedule.salesChannel = "All";
	  	schedule.rate = "2.00";
	  	schedule.acctCode = "USFS.3000.8200.1027; Walk To Revenue";
	  	
	  	// Search criteria
	  	schedule.searchType = "Product Group";
	  	schedule.searchValue = "AREA DAYU VEH";//ANNUAL DAY USE PASS
	  	schedule.activeStatus = OrmsConstants.ACTIVE_STATUS;
	  	schedule.dateType = "Effective (Start) Date";
	  	schedule.fromDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-49), "EEE MMM d yyyy");
	  	schedule.toDate = DateFunctions.formatDate(DateFunctions.getDateAfterToday(-47), "EEE MMM d yyyy");
	}

	private void deactiveExsitedFeeSchedules() {
		logger.info("Search and Deactive existing fee schedule.");
		List<FeeScheduleData> list = fnm.searchFeeScheduleAndGetResults(schedule);
		if (null != list) {
			for (int i = 0; i < list.size(); i++) {
				fnm.activeOrDeactiveFeeSchedule(list.get(i).feeSchdId, false);
			}
		}
	}

	private void addAndActiveFeeSchedule() {
		FinMgrFeeMainPage finFeeMainPg = FinMgrFeeMainPage.getInstance();
		// Add Use Fee schedule
		schedule.feeSchdId = fnm.addNewFeeSchedule(schedule);
		ids.add(schedule.feeSchdId);
		// Activate Fee Schedule
		String err = fnm.activeOrDeactiveFeeSchedule(schedule.feeSchdId, true);
		if (null != err) {
			logger.error("Error occured when activate Fee Schedule:"
					+ schedule.feeSchdId);
			throw new TestCaseFailedException("Error Message:" + err);
		}
		logger.info("Verify active new fee schedule success or not.");
		if (finFeeMainPg.isFeeScheduleActive(schedule.feeSchdId)
				&& schedule.feeSchdId.length() > 0) {
			logger.info("Fee Schedule:" + schedule.feeSchdId
					+ " has been created successfully.\n");
		} else {
			throw new TestCaseFailedException(
					"Failed to activate Fee Schedule id:"
							+ schedule.feeSchdId);
		}
	}
	
	private void cleanUp(){
		logger.info("Deactive new created fee schedule:"+ids);
		for (int i = 0; i < ids.size(); i++) {
			fnm.activeOrDeactiveFeeSchedule(ids.get(i), false);
		}
	}
}
