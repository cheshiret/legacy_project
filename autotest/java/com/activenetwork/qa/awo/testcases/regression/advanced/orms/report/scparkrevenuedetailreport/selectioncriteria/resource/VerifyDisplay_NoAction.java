package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.scparkrevenuedetailreport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class VerifyDisplay_NoAction extends ResourceManagerTestCase{

	List<String> agency = new ArrayList<String>();
	List<String> region = new ArrayList<String>();
	List<String> park = new ArrayList<String>();
	List<String> collectLocation = new ArrayList<String>();
	List<String> includeAdjustments = new ArrayList<String>();
	List<String> includeRaFee = new ArrayList<String>();
	List<String> includeNonDepositabletems = new ArrayList<String>();
	List<String> reportFormat = new ArrayList<String>();
	List<String> deliveryMethod = new ArrayList<String>();
	
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		this.reportSectionInfo();
		//Verify the section of "Agency".
		rm.VerifyReportSelectionCriteria("AgencyID", agency, "Reserve America", agency.get(0));
		
		//Verify the section of "Region".
		rm.VerifyReportSelectionCriteria("RegionID", region, "All", region.get(0));
		
		//Verify the section of "park".
		rm.VerifyReportSelectionCriteria("FacilityID", park, "All", "All");
		
		//Verify the section of "Collect Location".
		rm.VerifyReportSelectionCriteria("CollectIssueLocation", collectLocation, "All", collectLocation.get(0));
		
		//Verify the section of "Include Adjustments".
		rm.VerifyReportSelectionCriteria("Adjusted Included", includeAdjustments, "No", includeAdjustments.get(0));
		
		//Verify the section of "Include RA Fee".
		rm.VerifyReportSelectionCriteria("IncludeRAFeeAccount", includeRaFee, "No", includeRaFee.get(0));
		
		//Verify the section of "Include Non-Depositable Items".
		rm.VerifyReportSelectionCriteria("YesNoFlag", includeNonDepositabletems, "No", includeNonDepositabletems.get(0));
		
		//Verify the section of "Report Format".
		rm.VerifyReportSelectionCriteria("report_format", reportFormat, "XLS", reportFormat.get(0));
		
		//Verify the section of "Delivery Method".
		rm.VerifyReportSelectionCriteria("deliveryprotocolid", deliveryMethod, "Email", deliveryMethod.get(0));

		//logout resource manager
		rm.logoutResourceManager();		
	}
	
	public void reportSectionInfo(){
		//agency
		agency.add("Reserve America");
		agency.add("SC parks");
		//region
		region.add("All");
		region.add("Call Center");
		region.add("Web");
		//collectLocation
		collectLocation.add("All");
		collectLocation.add("Call Center");
		collectLocation.add("Field (Including Central Credit Cards)");
		collectLocation.add("Field (excluding Central Credit Cards)");
		collectLocation.add("Non-Field");
		collectLocation.add("Web");
		//includeAdjustments
		includeAdjustments.add("No");
		includeAdjustments.add("Yes");
		//includeRaFee
		includeRaFee.add("Yes");
		includeRaFee.add("No");
		//includeNonDepositabletems
		includeNonDepositabletems.add("Yes");
		includeNonDepositabletems.add("No");
		//reportFormat
		reportFormat.add("PDF");
		reportFormat.add("XLS");
		//deliveryMethod
		deliveryMethod.add("Email");
//		deliveryMethod.add("FTP");
		deliveryMethod.add("Fax");
		deliveryMethod.add("Online");
	}
	
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";
		
		// Initialize report data
		rd.group = "Financial Reports";
		rd.reportName = "SC Park Revenue Detail Report";	
	}
}
