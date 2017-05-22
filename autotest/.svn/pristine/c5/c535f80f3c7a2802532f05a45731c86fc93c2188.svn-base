package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.transactionfee;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeMainPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinMgrFeeSchDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case contains the scenario:
 *              1.verify error message when effective date is blank
 *              2.verify error message when account code is none
 *              3.verify error message when fee is blank
 *              4.verify error message when fee is negative fee
 *              5.verify could add success when fee is 0
 * @Preconditions:
 * @SPEC:Add Fee Schedule - Effective Date validation [TC:042704]
 *       Add Fee Schedule - Account Code validation [TC:042705]
 *       Add Fee Schedule - Fee validation [TC:042691]
 *       
 * @Task#:AUTO-1346
 * 
 * @author vzhang
 * @Date  Nov 19, 2012
 */

public class VerifyAddMarinaLotteryTranFee_Validation extends FinanceManagerTestCase{
	private FeeScheduleData schedule = new FeeScheduleData(true);
	private FinMgrFeeSchDetailPage detailsPg = FinMgrFeeSchDetailPage.getInstance();
	private String msg_effDateEmpt,msg_noAccount,msg_noFee,msg_nagetiveFee;

	@Override
	public void execute() {
		//login finance manager
		fnm.loginFinanceManager(login);
		fnm.gotoFeeMainPage();
		
		fnm.gotoFeeScheduleDetailPageByAddNew(schedule);
		//verify error message when effective date is blank
		String accountCode = detailsPg.getAccountCode(1);
		schedule.acctCode = accountCode;
		String message = this.addMarinaLotteryTranFee(schedule);
		this.verifyErrorMessage(msg_effDateEmpt,message);
		
		//verify error message when account code is none
		schedule.effectDate = DateFunctions.getToday();
		schedule.acctCode = "none";
		message = this.addMarinaLotteryTranFee(schedule);
		this.verifyErrorMessage(msg_noAccount,message);
		
		//verify error message when fee is blank
		schedule.acctCode = accountCode;
		schedule.tranFee = "";
		message = this.addMarinaLotteryTranFee(schedule);
		this.verifyErrorMessage(msg_noFee,message);
		
		//verify error message when fee is negative fee
		schedule.tranFee = "-1";
		message = this.addMarinaLotteryTranFee(schedule);
		this.verifyErrorMessage(msg_nagetiveFee,message);
		
		//verify could add success when fee is 0
		schedule.tranFee = "0";
		schedule.feeSchdId = this.addMarinaLotteryTranFee(schedule);
		fnm.searchToFeeScheduleDetailPg(schedule.feeSchdId);
		this.verifyFeeInfo(schedule.tranFee);
		
		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.contract = "NC Contract";
	  	login.location = "Administrator/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NC";
		
	  	//initialize schedule data
		schedule.activeStatus = OrmsConstants.NO_STATUS;
	  	schedule.locationCategory = "Park";
		schedule.location = fnm.getFacilityName("552903", schema);//Jordan Lake State Rec Area
	  	schedule.productCategory = "Lottery";
	  	schedule.feeType = "Transaction Fee";
	  	schedule.assignPrdCategory = "Slip";
	  	schedule.tranFee = "2";
		
		msg_effDateEmpt = "An Effective Date for the Fee Schedule is required. Please enter the Effective Date using the format Ddd Mmm dd yyyy in the field provided.";
		msg_noAccount = "An Account for the Fee Schedule is required. Please select the Account from the list provided.";
		msg_noFee = "The Fee is required. Please enter the Fee in the field provided.";
		msg_nagetiveFee = "The Fee must have a value greater than or equal to $0.00. Please change your entries.";
	}
	
	private String addMarinaLotteryTranFee(FeeScheduleData fd){
		FinMgrFeeMainPage feeMainPg = FinMgrFeeMainPage.getInstance();
		
		detailsPg.selectAssignProdCategory(fd.assignPrdCategory);
		detailsPg.waitLoading();
		if(fd.effectDate.length()>0){
			detailsPg.setEffectiveDate(fd.effectDate);
		}
		detailsPg.clickEffectiveLabel();
		detailsPg.selectAccountCode(fd.acctCode);
		detailsPg.setTransactionFee(fd.tranFee);
		detailsPg.clickApply();
		detailsPg.waitLoading();
		String value = "";
		
		if(detailsPg.checkErrorMessageExisting()){
			value = detailsPg.getErrorMsg();
		}else{
			value = detailsPg.getFeeSchID();
			detailsPg.clickCancel();
			feeMainPg.waitLoading();
		}
		
		return value;
	}
	
	private void verifyErrorMessage(String expErrorMsg,String actErrorMsg){
		boolean result = MiscFunctions.compareResult("Error Message", expErrorMsg, actErrorMsg);
		if(!result){
			throw new ErrorOnPageException("Error Message not correct.");
		}else logger.info("Error Message is correct.");
	}
	
	private void verifyFeeInfo(String expFee){
		String actFee = detailsPg.getTransactionFee();
		boolean result = MiscFunctions.compareResult("Fee Info", Double.valueOf(expFee), Double.valueOf(actFee));
		if(!result){
			throw new ErrorOnPageException("Fee Info not correct.");
		}else logger.info("Fee Info is correct.");
	}

}
