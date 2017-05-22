package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.adhocticketingordercubereport.resource.selectioncriteria;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class VerifyDisplay_NoAction extends ResourceManagerTestCase{
	List<String> agency = new ArrayList<String>();
	List<String> state = new ArrayList<String>();
	List<String> district = new ArrayList<String>();
	List<String> fieldOffice = new ArrayList<String>();
	List<String> park = new ArrayList<String>();
	List<String> dateType = new ArrayList<String>();
	List<String> reportSet = new ArrayList<String>();
	
	String parkID_1;
	String parkID_2;
	String parkID_3;
	
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		this.reportSectionInfo();
		//Verify the section of "Agency".
		rm.VerifyReportSelectionCriteria("AgencyID", agency, "BLM", agency.get(0));
		
		//Verify the section of "State".
		rm.VerifyReportSelectionCriteria("RegionID", state, "All", state.get(0));
		
		//Verify the section of "District".
		rm.VerifyReportSelectionCriteria("DistrictID", district, "All", district.get(0));
		
		//Verify the section of "field Office".
		rm.VerifyReportSelectionCriteria("ProjectID", fieldOffice, "All", fieldOffice.get(0));
		
		//Verify the section of "park".
		rm.VerifyReportSelectionCriteria("FacilityID", park, "All", park.get(0));
		
		//Verify the section of "date Type".
		rm.VerifyReportSelectionCriteria("DateType", dateType, "Order Date", dateType.get(0));
		
		//Verify the section of "report Set".
		rm.VerifyReportSelectionCriteria("report_set", reportSet, "/Public/Ticketing Order Report", reportSet.get(0));

		//logout resource manager
		rm.logoutResourceManager();		
	}
	
	public void reportSectionInfo(){
		//agency
		agency.add("BLM");
		agency.add("FWS");
		agency.add("NPS");
		//state
		state.add("All");
		state.add("OR");
		//district
		district.add("All");
		district.add("080");
		district.add("100");     
		district.add("9100");
		//fieldOffice
		fieldOffice.add("All");
		fieldOffice.add("084");
		fieldOffice.add("104");
		fieldOffice.add("125");
		//park
		park.add("All");
		park.add(rm.getFacilityName(parkID_1, schema));
		park.add(rm.getFacilityName(parkID_2, schema));
		park.add(rm.getFacilityName(parkID_3, schema));
		//dateType
		dateType.add("Order Date");
		dateType.add("Arrival Date");
		//reportSet
		reportSet.add("/Public/Ticketing Order Report");
	}
	
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
		
		parkID_1 = "72041";//Eagleview Group Campground (Reservation)
		parkID_2 = "72044";//ROCK CREEK PAVILION
		parkID_3 = "72045";//TYEE PAVILION
		
		// Initialize report data
		rd.group = "Ad hoc Cubes";
		rd.reportName = "Ad hoc Ticketing Order Cube Report";	
	}	
}
