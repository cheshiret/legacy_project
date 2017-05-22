package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.lotteryResultsReport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class VerifyDisplay_NoAction extends ResourceManagerTestCase{
	
	List<String> lotteryName = new ArrayList<String>();
	List<String> reportFormat = new ArrayList<String>();
	List<String> deliveryMethod = new ArrayList<String>();

	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		this.reportSectionInfo();
		//Verify the section of "Lottery Name"
		rm.VerifyReportSelectionCriteria("LotteryName", lotteryName, "Please Select", lotteryName.get(0));
		
		//Verify the section of "Report Format"
		rm.VerifyReportSelectionCriteria("report_format", reportFormat, "XLS", reportFormat.get(0));
		
		//Verify the section of "Delivery Method"
		rm.VerifyReportSelectionCriteria("deliveryprotocolid", deliveryMethod, "Email", deliveryMethod.get(0));
		
		//logout resource manager
		rm.logoutResourceManager();		
	}

	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		// Initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Lottery Results Report";			
	}
	
	public void reportSectionInfo(){		
		//lotteryName
		lotteryName.add("Please Select");
		lotteryName.add("BWCAW Test Lottery");
		lotteryName.add("BWCAW Lottery 2010");
		lotteryName.add("Four Rivers Lottery - Salmon River");
		
		//reportFormat
		reportFormat.add("XLS");
		
		//deliveryMethod
		deliveryMethod.add("Email");
	}

}
