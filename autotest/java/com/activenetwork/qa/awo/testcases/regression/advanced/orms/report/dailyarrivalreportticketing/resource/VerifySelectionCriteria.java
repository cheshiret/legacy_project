package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.dailyarrivalreportticketing.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC: Permission and Selection Criteria in VM/RM [TC:107480] 
 * @Task#: Auto-2311
 * 
 * @author Jane Wang
 * @Date  July 31, 2014
 */
public class VerifySelectionCriteria extends ResourceManagerTestCase{

	ReportData rd = new ReportData();
	ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage.getInstance();
	String errorMessage = "Missing Required Field: Date.*";
	List<String> ticketCategory = new ArrayList<String>();
	List<String> reportFormat = new ArrayList<String>();
	List<String> deliveryMethod = new ArrayList<String>();
	
	@Override
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		// Verify facility dropdown list NOT existed on page
		verifyFacilityNotExisted();
				
		//Verify dropdoown list values and default value
		verifyListValuesAndDefaultValue("TicketCategory", ticketCategory, "All");
		verifyListValuesAndDefaultValue("report_format", reportFormat, "PDF");
		verifyListValuesAndDefaultValue("deliveryprotocolid", deliveryMethod, "Email");
				
		// Not enter Start Date
		rm.verifyReportDate(StringUtil.EMPTY, StringUtil.EMPTY, errorMessage);
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		// Initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Daily Arrival Report-Ticketing";	
		
		ticketCategory.add("All");
		ticketCategory.add("Organization");
		ticketCategory.add("Individual");

		reportFormat.add("PDF");
		
		deliveryMethod.add("Email");
		deliveryMethod.add("Online");
	}
	
	private void verifyFacilityNotExisted() {
		logger.info("Verify facility on report selection criteria page.");
		boolean existed  = rptCriteriaPg.checkFacilityDropdownListExisted();
		if(!existed)
			throw new ErrorOnPageException("Facility drop down list should exist on page.");
		logger.info("---Verify facility drop down list on page successfully.");
	}
	
	private void verifyListValuesAndDefaultValue(String id,List<String> expectItem,String defaultValue){
		List<String> dropdown=browser.getDropdownElements(".id", id);
		
		if(dropdown.size()!=expectItem.size()){
			throw new ErrorOnDataException("Size of drop down list elments is not correct", expectItem.size(), dropdown.size());
		}
		
		for(int i=0;i<expectItem.size();i++){
			if(!dropdown.contains(expectItem.get(i))){
				throw new ErrorOnDataException(expectItem.get(i)+" is not in the drop down list");
			}
		}
		
		String value=browser.getDropdownListValue(".id", id, 0);
		if(!defaultValue.equals(value)){
			throw new ErrorOnDataException(defaultValue+" is not the default value");
		}
	}

}
