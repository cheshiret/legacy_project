package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.dailyarrivalreport.selectioncriteria.resource;

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
	List<String> reportFormat = new ArrayList<String>();
	List<String> deliveryMethod = new ArrayList<String>();
	
	String parkID_1;
	String parkID_2;
	String parkID_3;
	String parkID_4;
	String parkID_5;
	String parkID_6;
	String parkID_7;
	String parkID_8;
	String parkID_9;
	String parkID_10;
	
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		
		this.reportSectionInfo();
		//Verify the section of "Agency".
		rm.VerifyReportSelectionCriteria("AgencyID", agency, agency.get(0), agency.get(0));
		
		//Verify the section of "State".
		rm.VerifyReportSelectionCriteria("RegionID", state, state.get(0), state.get(0));
		
		//Verify the section of "District".
		rm.VerifyReportSelectionCriteria("DistrictID", district, district.get(0), district.get(0));
		
		//Verify the section of "Field Office".
		rm.VerifyReportSelectionCriteria("ProjectID", fieldOffice, fieldOffice.get(0), fieldOffice.get(0));
		
		//Verify the section of "Park".
		rm.VerifyReportSelectionCriteria("FacilityID", park, park.get(0), park.get(0));
		
		//Verify the section of "Report Format".
		rm.VerifyReportSelectionCriteria("report_format", reportFormat, "PDF", reportFormat.get(0));
		
		//Verify the section of "Delivery Method".
		rm.VerifyReportSelectionCriteria("deliveryprotocolid", deliveryMethod, deliveryMethod.get(0), deliveryMethod.get(0));

		//logout resource manager
		rm.logoutResourceManager();		
	}
	
	public void reportSectionInfo(){
		//agency
		agency.add("BLM");
		agency.add("BOR");
		agency.add("FS BOUNDARY WATERS");
		agency.add("FWS");
		agency.add("NARA");
		agency.add("NCMO");
		agency.add("NPS");
		agency.add("Reserve America");
		agency.add("USACE");
		agency.add("USFS");
		//state
		state.add("All");
		state.add("OR");
		//district
		district.add("All");
		district.add("080");
		district.add("100");     
//		district.add("120");
		//fieldOffice
		fieldOffice.add("All");
		fieldOffice.add("084");
		fieldOffice.add("104");     
		fieldOffice.add("125");
		//park
		//park.add("Add Facility Of Permit - 1323941940739"); not exist on QA4 env.
		park.add(rm.getFacilityName(parkID_1, schema));
		park.add(rm.getFacilityName(parkID_2, schema));
		park.add(rm.getFacilityName(parkID_3, schema));
		park.add(rm.getFacilityName(parkID_4, schema));     
		park.add(rm.getFacilityName(parkID_5, schema));
		park.add(rm.getFacilityName(parkID_6, schema));
		park.add(rm.getFacilityName(parkID_7, schema));
		park.add(rm.getFacilityName(parkID_8, schema));
		park.add(rm.getFacilityName(parkID_9, schema));
		park.add(rm.getFacilityName(parkID_10, schema));
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
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
		
		// Initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Daily Arrival Report";	
		
		 parkID_1="72168";//Carrizo Plain National Monument Tours
		 parkID_2="72041";//Eagleview Group Campground (Reservation)
		 parkID_3="74081";//FISHERMENS BEND
		 parkID_4="72042";//Lone Pine Group Campground (Reservation)
		 parkID_5="74083";//LOON LAKE RECREATION SITE
		 parkID_6="72043";//MILLPOND PAVILION
		 parkID_7="75016";//OLD MINERS MEADOW GROUP USE
		 parkID_8="72044";//ROCK CREEK PAVILION
		 parkID_9="72045";//TYEE PAVILION
		 parkID_10="74082";//WILDWOOD RECREATION SITE
		
	}
}
