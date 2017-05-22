package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.undistributedfundsreport.selectioncriteria.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifyReportingDate_MandatoryInput extends ResourceManagerTestCase{

	@Override
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		//verify report date is mandatory input
		String alertMsg = rm.getReportDateErrorMesg(rd.startDate,rd.endDate);
		if(alertMsg.equals("")){
			throw new ErrorOnPageException("No Error Message Prompt For Blank Date.");
		}
		
		//logout resource manager
		rm.logoutResourceManager();		
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		//initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "UnDistributed Funds Report";
		rd.startDate = "";
	}
}
