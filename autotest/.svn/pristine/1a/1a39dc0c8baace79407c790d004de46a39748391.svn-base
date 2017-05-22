package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee.marina.slip;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify canceling action during adding process at fee details page works correctly
 * @Preconditions:
 * @SPEC: Add TRNS Fee Schedule - Cancel [TC:043392]
 * @Task#: AUTO-1330
 * 
 * @author qchen
 * @Date  Jan 25, 2013
 */
public class CancelAddingProcess extends FinanceManagerTestCase {
	
	private FeeScheduleData schedule = new FeeScheduleData();
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoAddNewFeeScheduleDetailPg(schedule.location, schedule.locationCategory, schedule.productCategory, schedule.feeType);
		FinMgrFeeSchDetailPage.getInstance().clickCancel();
		this.verifyFeeMainExists();
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "NC Contract";
		login.location = "Administrator - Auto/North Carolina State Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		String facilityID = "552903";//Jordan Lake State Rec Area
		
		schedule.location = fnm.getFacilityName(facilityID, schema);
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";
		schedule.feeType = "Transaction Fee";
	}
	
	private void verifyFeeMainExists() {
		FinMgrFeeMainPage mainPage = FinMgrFeeMainPage.getInstance();
		try {
			mainPage.waitLoading();
		} catch(Exception e) {
			if(!mainPage.exists()) {
				throw new ErrorOnPageException("FinMgrFeeMainPage should exist after canceling from details page.");
			}
		}
	}
}
