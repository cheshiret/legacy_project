/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafeethreshold;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule.FinMgrRaFeeThresholdsDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:check point:1. verify error message for effective date
 *                          2. compare effective date
 * @Preconditions:
 * @SPEC:TC:042166
 * @Task#:AUTO-1335
 * 
 * @author szhou
 * @Date  Nov 12, 2012
 */
public class VerifyDatesSelection_Slip extends FinanceManagerTestCase {
	private ScheduleData schedule = new ScheduleData();
	private String expectError;

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeThresholdPage();

		// verify error message
		String error = fnm.addNewRaFeeThresholdSchedule(schedule);
        this.verifyErrorMessage(expectError, error);
        
		// verify date content
        schedule.effectiveDate = DateFunctions.getDateAfterToday(-5);
		schedule.scheduleId = fnm.addNewRaFeeThresholdSchedule(schedule);
		fnm.gotoRaFeeThresholdDetailPgBySchdId(schedule.scheduleId);
		this.verifyDate(schedule.effectiveDate);
		
		fnm.logoutFinanceManager();

	}
	
	private void verifyDate(String date){
		FinMgrRaFeeThresholdsDetailPage detailPg = FinMgrRaFeeThresholdsDetailPage
				.getInstance();
		
		String dateInPg=detailPg.getEffectiveDate();
		
		//verify date content
		boolean result=true;
		result &= MiscFunctions.compareResult("Effective Date", date, dateInPg);
		if(!result){
			throw new ErrorOnPageException("Date information is not correct. Please see log file...");
		}
		
	}
	
	private void verifyErrorMessage(String expect,String actually){
		if(!expect.equals(actually)){
			throw new ErrorOnPageException("Error is not correct, Expect Message : "+expect+";But Actually Message :"+actually);
		}
	}


	@Override
	public void wrapParameters(Object[] param) {
		//initialize login finance manager loginInfo
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.contract = "NC Contract";
	  	login.location = "Administrator/North Carolina State Parks";
	  	
		//initialize schedule data
	  	schedule.location="Mayo River State Park";
	  	schedule.locationCategory="Park";
	  	schedule.productCategory = "Slip";
	  	schedule.startCounter = "1";

	  	expectError="An Effective Date for the RA Fee Threshold Schedule is required. Please enter the Effective Date using the format Mmm dd, yyyy in the field provided.";
	}

}
