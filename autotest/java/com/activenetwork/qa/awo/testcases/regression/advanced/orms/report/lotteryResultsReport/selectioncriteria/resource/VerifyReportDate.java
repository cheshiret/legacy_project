package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.lotteryResultsReport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class VerifyReportDate extends ResourceManagerTestCase{
	
	String lotteryName="";
	private List<String> errorMessage = new ArrayList<String>();

	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		this.errorMessageInfo();
		//verify the lottery name error message
		rm.verifyLotteryNameErrorMesg("Please Select", errorMessage.get(0));

		rm.selectLotteryName(lotteryName);
		//verify the lottery schedule error message
		rm.verifyLotteryScheduleErrorMesg("Please Select", errorMessage.get(1));					
		
	}

	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		// Initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Lottery Results Report";
		
		// Initialize lottery name
		lotteryName = "BWCAW Lottery 2009";		
	}
	
	private void errorMessageInfo(){
		//Error Message
		errorMessage.add("Please select the Lottery Name.");
		errorMessage.add("Please select the Lottery schedule.");
	}
}
