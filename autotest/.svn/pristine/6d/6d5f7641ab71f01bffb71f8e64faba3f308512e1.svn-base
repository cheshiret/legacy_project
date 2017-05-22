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
 * @Description:This test case verify Minimum Use Fee when add use fee for slip.
 * 1.negative value
 * 2.not a number
 * 3.more than 2 decimals
 * @Preconditions:
 * @SPEC: Add Use Fee Schedule - Minimum Use Fee validation [TC:043384]
 * @Task#: Auto-1327
 * 
 * @author nding1
 * @Date  Jan 14, 2013
 */
public class VerifyMinUseFeeErrMsg extends FinanceManagerTestCase {
	private FeeScheduleData schedule = new FeeScheduleData(true);
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	private String msg_invalidFee;
	
	@Override
	public void execute() {
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		fnm.gotoFeeScheduleDetailPageByAddNew(schedule);
		
		// 1. negative value
		boolean result = true;
		schedule.minimumUseFee = "-8.35";
		String message = this.addSlipUsenFee();
		result &= MiscFunctions.compareResult("Error Message - negative value", msg_invalidFee, message);
		
		// 2.not a number
		schedule.minimumUseFee = "abc";
		message = this.addSlipUsenFee();
		result &= MiscFunctions.compareResult("Error Message - not a number", msg_invalidFee, message);
		
		// 3.more than 2 decimals
		schedule.minimumUseFee = "6.981";
		message = this.addSlipUsenFee();
		result &= MiscFunctions.compareResult("Error Message - more than 2 decimals", msg_invalidFee, message);
		
		if(!result){
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
		schedule.startInv = DateFunctions.getDateAfterToday(20);
		schedule.endInv = DateFunctions.getDateAfterToday(29);
		
		// expect message
		msg_invalidFee = "The Minimum fee must be a positive value in dollars and cents.";
	}
	
	private String addSlipUsenFee(){
		detailsPg.selectAssignProdCategory(schedule.assignPrdCategory);
		detailsPg.waitLoading();
		detailsPg.setEffectiveDate(schedule.effectDate);
		detailsPg.clickEffectiveLabel();
		detailsPg.setInvStartDate(schedule.startInv);
		detailsPg.setInvEndDate(schedule.endInv);
		schedule.acctCode = detailsPg.getAccountCode(1);
		detailsPg.selectAccountCode(schedule.acctCode);
		detailsPg.setMinimumUseFee(schedule.minimumUseFee);
		detailsPg.clickApply();
		detailsPg.waitLoading();
		String value = detailsPg.getErrorMsg();
		return value;
	}
}
