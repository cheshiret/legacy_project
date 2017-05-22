package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafeethreshold;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrFeeFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsDetailPage;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case contains the scenario:
 *              1. verify add ra fee threshold schedule with effective date section
 *              2. verify add ra fee threshold schedule with start counter section
 * @Preconditions:
 * @SPEC:Add RA Fee Threshold Schedule - Combined check both fields"Effective" and "Start Counter" [TC:043595]
 * @Task#:AUTO-1336
 * 
 * @author vzhang
 * @Date  Nov 13, 2012
 */

public class VerifyEffectiveAndStartCounter_Slip extends FinanceManagerTestCase{
	private ScheduleData schedule = new ScheduleData();
	private String message1,message2;
	private List<String> expMessageList = new ArrayList<String>();

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeThresholdPage();
		//verify error message, effective date is blank, start counter is blank
		List<String> error = this.addNewRaFeeThresholdSchedule(schedule);
		expMessageList.add(message1);
		expMessageList.add(message2);
		this.verifyErrorMessage(expMessageList, error);

		//verify error message, effective date is blank
		schedule.startCounter = "0";
		error = this.addNewRaFeeThresholdSchedule(schedule);
		expMessageList.clear();
		expMessageList.add(message1);
		this.verifyErrorMessage(expMessageList, error);

		fnm.logoutFinanceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
		login.contract = "NC Contract";
		login.location = "Administrator/North Carolina State Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NC";

		// initialize schedule data
		schedule.location = fnm.getFacilityName("552818", schema);//Mayo River State Park
		schedule.locationCategory = "Park";
		schedule.productCategory = "Slip";

		message1 = "An Effective Date for the RA Fee Threshold Schedule is required. " +
		"Please enter the Effective Date using the format Mmm dd, yyyy in the field provided.";
		message2 = "The Starting Count is required. Please enter the Starting Count in the field provided.";
	}

	private void verifyErrorMessage(List<String> expect,List<String> actually){
		if(expect.size() != actually.size()){
			throw new ErrorOnPageException("The error message list is not correct.");
		}
		for(int i=0; i<expect.size(); i++){
			if(!expect.get(i).equals(actually.get(i))){
				throw new ErrorOnPageException("Error is not correct, Expect Message : "+expect.get(i)+";But Actually Message :"+actually.get(i));
			}
		}

	}

	private List<String> addNewRaFeeThresholdSchedule(ScheduleData schedule) {
		FinMgrRaFeeThresholdsSearchPage searchPg = FinMgrRaFeeThresholdsSearchPage.getInstance();
		FinMgrFeeFindLocationPage findLocationPg = FinMgrFeeFindLocationPage.getInstance();
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage.getInstance();

		logger.info("Start to Add New RA Fee Threshold Schedule.");

		searchPg.clickAddNew();
		findLocationPg.waitLoading();
		findLocationPg.searchByLocationName(schedule.location,
				schedule.locationCategory);
		findLocationPg.selectLocation(schedule.location);
		detailPg.waitLoading();
		detailPg.enterAllRAFeeThresholdSched(schedule);
		detailPg.clickApply();
		ajax.waitLoading();
		detailPg.waitLoading();
		List<String> scheduleMess =detailPg.getErrorMessageList();
		detailPg.clickCancel();
		searchPg.waitLoading();
		
		return scheduleMess;

	}

}
