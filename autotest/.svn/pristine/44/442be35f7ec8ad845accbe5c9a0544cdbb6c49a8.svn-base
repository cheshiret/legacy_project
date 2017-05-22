package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.campersreport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class VerifyDisplay_NoAction extends ResourceManagerTestCase{

	List<String> reportSubtype = new ArrayList<String>();
	List<String> sortOrder = new ArrayList<String>();
	List<String> reportFormat = new ArrayList<String>();
	List<String> deliveryMethod = new ArrayList<String>();
	ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage
	.getInstance();
	String facility;
	
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rmCriteriaPg.selectReportLoc(facility);
		this.reportSectionInfo();
		
		//Verify the section of "Report Subtype".
		rm.VerifyReportSelectionCriteria("ReportSubType", reportSubtype, "Cancelled", reportSubtype.get(0));
		
		//Verify the section of "Sort Order".
		rm.VerifyReportSelectionCriteria("SortOrder", sortOrder, "Loop then Site", sortOrder.get(0));
		
		//Verify the section of "Report Format".
		rm.VerifyReportSelectionCriteria("report_format", reportFormat, "PDF", reportFormat.get(0));
		
		//Verify the section of "Delivery Method".
		rm.VerifyReportSelectionCriteria("deliveryprotocolid", deliveryMethod, "Email", deliveryMethod.get(0));

		//logout resource manager
		rm.logoutResourceManager();		
	}
	
	public void reportSectionInfo(){
		//reportSubtype
		reportSubtype.add("Cancelled");
		reportSubtype.add("Onsite");
		reportSubtype.add("Outgoing");
		reportSubtype.add("Incoming");
		//sortOrder
		sortOrder.add("Loop then Site");
		sortOrder.add("Loop then Customer");
		sortOrder.add("Customer");     
		sortOrder.add("Site");
		//reportFormat
		reportFormat.add("DHTML");
		reportFormat.add("PDF");
		//deliveryMethod
		deliveryMethod.add("Email");
//		deliveryMethod.add("FTP");
		deliveryMethod.add("Fax");
		deliveryMethod.add("Online");
	}
	
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		// Initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Campers Report";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
		String facilityID = "73494";
		facility = DataBaseFunctions.getFacilityName(facilityID, schema);
	}	
}
