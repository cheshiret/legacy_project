package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.fees.uesfee.marina;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case verify not specify account code when add use fee for slip.
 * @Preconditions:
 * @SPEC: Add Use Fee Schedule - Account validation [TC:043382]
 * @Task#: Auto-1327
 * 
 * @author nding1
 * @Date  Jan 14, 2013
 */
public class VerifyAccountCode extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData(true);
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	private String msg_noAccount;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoFeeScheduleDetailPageByAddNew(schedule);
		schedule.acctCode = "none";
		String message = this.addSlipUsenFee();
		if(!MiscFunctions.compareResult("Error Message", msg_noAccount, message)){
			throw new ErrorOnPageException("---Check logs above.");
		}
		fnm.logoutFinanceManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		// initialize login finance manager loginInfo
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";

		// use fee schedule info
		schedule.productCategory = "Slip";
		schedule.feeType = "Use Fee";
		schedule.locationCategory = "Park";
		schedule.location = "Jordan Lake State Rec Area";
		schedule.effectDate = DateFunctions.getDateAfterToday(2);
		schedule.startInv = DateFunctions.getDateAfterToday(10);
		schedule.endInv = DateFunctions.getDateAfterToday(19);
		
		// expect message
		msg_noAccount = "An Account for the Fee Schedule is required. Please select the Account from the list provided.";
	}
	
	private String addSlipUsenFee(){
		detailsPg.selectAssignProdCategory(schedule.assignPrdCategory);
		detailsPg.waitLoading();
		detailsPg.setEffectiveDate(schedule.effectDate);
		detailsPg.clickEffectiveLabel();
		detailsPg.selectAccountCode(schedule.acctCode);
		detailsPg.setInvStartDate(schedule.startInv);
		detailsPg.setInvEndDate(schedule.endInv);
		detailsPg.clickApply();
		detailsPg.waitLoading();
		String value = detailsPg.getErrorMsg();
		return value;
	}
}
