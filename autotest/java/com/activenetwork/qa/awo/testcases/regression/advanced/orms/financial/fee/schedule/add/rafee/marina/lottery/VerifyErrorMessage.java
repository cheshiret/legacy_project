package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafee.marina.lottery;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.RaFeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeSchMainPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify error message for 'Effective Date', 'Account Code' and 'Base Rate' validations
 * @Preconditions:
 * @SPEC: Add RA Fee Schedule - Effective Date validation [TC:042723]
 * 				Add RA Fee Schedule - Account Code validation [TC:042724]
 * 				Add RA Fee Schedule - Fee validation [TC:042725]
 * @Task#: AUTO-1348
 * 
 * @author qchen
 * @Date  Dec 24, 2012
 */
public class VerifyErrorMessage extends FinanceManagerTestCase {
	private RaFeeScheduleData schedule = new RaFeeScheduleData();
	private String actualMsg, errorMsg_BlankEffectiveDate, errorMsg_BlankAccountCode, errorMsg_BlankBaseRate, errorMsg_NegativeValue;
	private boolean result = true;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeSchedulePage();
		
		//1. keep Effective Date as blank, click 'Apply' and error message 2 displayed on the top of page
		schedule.effectDate = StringUtil.COMMA;//input an invalid date to make it blank
		actualMsg = this.addRAFeeSchedule(schedule);
		result &= MiscFunctions.compareResult("when Effective Date is blank", errorMsg_BlankEffectiveDate, actualMsg);
		
		//2. keep Account Code as blank, click 'Apply' and error message 6 displayed on the top of page
		schedule.effectDate = DateFunctions.getToday();
		schedule.acctCode = "None";
		actualMsg = this.addRAFeeSchedule(schedule);
		result &= MiscFunctions.compareResult("when Account Code is blank", errorMsg_BlankAccountCode, actualMsg);
		
		//3. keep Base Rate as blank, click 'Apply' and error message 8 displayed on the top of page
		schedule.acctCode = "2000.1601.532170021; Admin Svc-Vendor Commission (RA Fees)";
		schedule.baseRate = StringUtil.EMPTY;
		actualMsg = this.addRAFeeSchedule(schedule);
		result &= MiscFunctions.compareResult("when Base Rate is blank", errorMsg_BlankBaseRate, actualMsg);
		
		//4. enter a negative value of Base Rate, click 'Apply' and error message 9 displayed on the top of page
		schedule.baseRate = "-13";
		actualMsg = this.addRAFeeSchedule(schedule);
		result &= MiscFunctions.compareResult("when Base Rate is negative value", errorMsg_NegativeValue, actualMsg);
		
		if(!result) {
			throw new ErrorOnPageException("Not all checkpoint are passed, please refer log for details.");
		} else logger.info("All checkpoints are PASSED.");
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facilityID = "552832";//Jones Lake State Park
		schedule.location = DataBaseFunctions.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		schedule.activeStatus = OrmsConstants.NO_STATUS;
		schedule.productCategory = "Lottery";
		schedule.applicableProductCategory = "Slip";
		schedule.product = "TestSlipLottery";
		schedule.effectDate = DateFunctions.getToday();
		schedule.salesChannel = "FieldMgr";
		schedule.raFeeOption = "Order Confirmed (will not be reversed due to non payment)";
		schedule.tranType = "Submit Lottery Entry";
		schedule.acctCode = "2000.1601.532170021; Admin Svc-Vendor Commission (RA Fees)";
		schedule.baseRate = "14.6";
		
		//expected error message
		errorMsg_BlankEffectiveDate = "An Effective Date for the RA Fee Schedule is required. Please enter the Effective Date using the format Ddd Mmm dd yyyy in the field provided";
		errorMsg_BlankAccountCode = "An Account for the RA Fee Schedule is required. Please select the Account from the list provided";
		errorMsg_BlankBaseRate = "The Base Rate is required. Please enter the Base Rate in the field provided";
		errorMsg_NegativeValue = "All Rates in the RA Fee Schedule must have a value greater than or equal to $0.00. Please change your entries.";
	}
	
	private String addRAFeeSchedule(RaFeeScheduleData schedule) {
		FinMgrRaFeeSchMainPage listPage = FinMgrRaFeeSchMainPage.getInstance();
		FinMgrRaFeeSchDetailPage detailsPage = FinMgrRaFeeSchDetailPage.getInstance();
		
		fnm.gotoRAFeeScheduleDetailPgByAddNew(schedule.location,
				schedule.locationCategory);

		detailsPage.selectPrdCategory(schedule.productCategory);
		detailsPage.waitLoading();
		detailsPage.setupRaFeeSchDetailInfo(schedule, false);
		detailsPage.clickApply();
		ajax.waitLoading();
		detailsPage.waitLoading();
		String msg = detailsPage.getErrorMsg();
		
		detailsPage.clickCancel();
		listPage.waitLoading();
		
		return msg;
	}
}
