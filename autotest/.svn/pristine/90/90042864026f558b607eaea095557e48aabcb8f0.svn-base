package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee.marina.slip;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify error messages displayed correctly when filling invalid values to 'Effective Date', 'Account Code' and 'Transaction Fee' fields
 * @Preconditions: Need an existing slip product - 'EFS-Edit Fee Schedule', which has been inserted into support table
 * @SPEC: Add TRNS Fee Schedule - Effective Date validation [TC:043395]
 * 				Add TRNS Fee Schedule - Account validation [TC:043396]
 * 				Add TRNS Fee Schedule - Fee validation [TC:043397]
 * @Task#: AUTO-1330
 * 
 * @author qchen
 * @Date  Jan 25, 2013
 */
public class VerifyErrorMessages extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData();
	private String errorMsg_effectiveDateEmpty, errorMsg_accountEmpty, errorMsg_feeEmpty, errorMsg_feeInvalid;
	private boolean result = true;
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		//1. enter all required fields and keep the Effective Date as empty
		schedule = this.initializeOriginalFeeScheduleInfo();
		schedule.effectDate = StringUtil.EMPTY;
		fnm.addNewFeeSchedule(schedule);
		String actualMsg = detailsPg.getErrorMsg();
		result &= MiscFunctions.compareResult("Error msg of empty Effective Date", errorMsg_effectiveDateEmpty, actualMsg);
		fnm.cancelFromFeeScheduleDetailsPage();
		
		//2. enter all required fields and keep the Account as empty
		this.initializeOriginalFeeScheduleInfo();
		schedule.acctCode = "none";
		fnm.addNewFeeSchedule(schedule);
		actualMsg = detailsPg.getErrorMsg();
		result &= MiscFunctions.compareResult("Error msg of empty Account Code", errorMsg_accountEmpty, actualMsg);
		fnm.cancelFromFeeScheduleDetailsPage();
		
		//3. enter all required fields and keep the Fee as blank
		this.initializeOriginalFeeScheduleInfo();
		schedule.tranFee = StringUtil.EMPTY;
		fnm.addNewFeeSchedule(schedule);
		actualMsg = detailsPg.getErrorMsg();
		result &= MiscFunctions.compareResult("Error msg of empty Transaction Fee", errorMsg_feeEmpty, actualMsg);
		fnm.cancelFromFeeScheduleDetailsPage();
		
		//4. enter all required fields and enter a negative value for Fee(-10)
		this.initializeOriginalFeeScheduleInfo();
		schedule.tranFee = "-10.01";
		fnm.addNewFeeSchedule(schedule);
		actualMsg = detailsPg.getErrorMsg();
		result &= MiscFunctions.compareResult("Error msg of negative Transaction Fee", errorMsg_feeInvalid, actualMsg);
		fnm.cancelFromFeeScheduleDetailsPage();
		
		//final check
		if(!result) {
			throw new ErrorOnPageException("Not all checkpoints are passed, please refer to log for details.");
		} else logger.info("All checkpoints are PASSED.");
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		this.initializeOriginalFeeScheduleInfo();
		
		errorMsg_effectiveDateEmpty = "An Effective Date for the Fee Schedule is required. Please enter the Effective Date using the format Ddd Mmm dd yyyy in the field provided.";
		errorMsg_accountEmpty = "An Account for the Fee Schedule is required. Please select the Account from the list provided.";
		errorMsg_feeEmpty = "The Fee is required. Please enter the Fee in the field provided.";
		errorMsg_feeInvalid = "The Fee must have a value greater than or equal to $0.00. Please change your entries.";
	}
	
	private FeeScheduleData initializeOriginalFeeScheduleInfo() {
		String facilityID = "552903";//Jordan Lake State Rec Area
		schedule.location = fnm.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
		schedule.feeType = "Transaction Fee";
		
		schedule.activeStatus = OrmsConstants.NO_STATUS;
		schedule.dock = "AutoDock";
		schedule.productGroup = "Full attributes";
		schedule.product = "EFS-Edit Fee Schedule";
		schedule.effectDate = DateFunctions.getDateAfterToday(10);
		schedule.salesChannel = "FieldMgr";
		schedule.state = "In State";
		schedule.tranType = "Transfer Same Facility - Same Value";
		schedule.tranOccur = "Prior to X days before Arrival Date";
		schedule.acctCode = "3000.1601.000200000; Default Overage/Shortage";
		schedule.marinaRateType = "Transient";
		schedule.tranFeeOption = "Per Transaction";
		schedule.tranFee = "20131.26";
		
		return schedule;
	}
}
