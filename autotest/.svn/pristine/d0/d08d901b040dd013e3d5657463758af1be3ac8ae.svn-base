package com.activenetwork.qa.awo.testcases.regression.advanced.orms.financial.fee.schedule.add.rafeethreshold;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.awo.testcases.abstractcases.FinanceManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:This test case contains the scenario:
 *              1. verify add ra fee threshold schedule with threshold range section
 *              2. verify add ra fee threshold schedule with start counter section
 * @Preconditions:
 * @SPEC:Threshold Range& Start Count [TC:042364]
 * @Task#:AUTO-1336
 * 
 * @author vzhang
 * @Date  Nov 13, 2012
 */

public class VerifyRangeAndStartCounter extends FinanceManagerTestCase{
	private ScheduleData schedule = new ScheduleData();
	private String message1,message2,message3;

	@Override
	public void execute() {
		// login finance manager
		fnm.loginFinanceManager(login);

		fnm.gotoFeeMainPage();
		fnm.gotoRaFeeThresholdPage();
		// verify error message, other range is less than 0
		schedule.otherRanges.add("-1");
		String error = fnm.addNewRaFeeThresholdSchedule(schedule);
        this.verifyErrorMessage(message1, error);
		
        //verify error message, other range is equal to start counter
		schedule.otherRanges.clear();
		schedule.otherRanges.add(schedule.startCounter);
        error = fnm.addNewRaFeeThresholdSchedule(schedule);
        this.verifyErrorMessage(message2, error);
        
        //verify error message, start counter is greater than other range
        schedule.startCounter = "1";
        error = fnm.addNewRaFeeThresholdSchedule(schedule);
        this.verifyErrorMessage(message3, error);
        
        //verify error message, other range is same
        schedule.otherRanges.clear();
		schedule.otherRanges.add("2");
		schedule.otherRanges.add("2");
        error = fnm.addNewRaFeeThresholdSchedule(schedule);
        this.verifyErrorMessage(message2, error);
        
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
	  	schedule.effectiveDate = DateFunctions.getToday();
	  	schedule.startCounter = "0";
	  	schedule.otherRanges = new ArrayList<String>();
	  	
	  	message1 = "All Threshold Ranges must have a value greater than or equal to 0. Please change your entries.";
	  	message2 = "Each Threshold Range, including the Starting Count, must be unique. Please change your entries.";
	  	message3 = "The Starting Count, which is the basis of the Base Threshold Range, cannot be greater than any of the Other Threshold Ranges. Please change your entries.";
	}
	
	private void verifyErrorMessage(String expect,String actually){
		if(!expect.equals(actually)){
			throw new ErrorOnPageException("Error is not correct, Expect Message : "+expect+";But Actually Message :"+actually);
		}
	}
	
}
