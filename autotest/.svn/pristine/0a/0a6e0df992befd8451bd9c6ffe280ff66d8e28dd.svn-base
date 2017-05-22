package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.willcallreport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class VerifySelectionCriteria extends ResourceManagerTestCase{
	List<String> ticketCategory = new ArrayList<String>();
	List<String> homePhone = new ArrayList<String>();
	List<String> postCode = new ArrayList<String>();
	List<String> id = new ArrayList<String>();
	List<String> sortOrder = new ArrayList<String>();
	List<String> reportFormat = new ArrayList<String>();
	List<String> deliveryMethod = new ArrayList<String>();
	
	@Override
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		reportSectionInfo();
		
		// verify park selection
		verifyParkDefaultValue();
		// verify ticket category selection
		verifyReportFormat("TourCategory",ticketCategory,"All",3);
		// verify home phone selection
		verifyReportFormat("YesNoFlag",homePhone,"Yes",2);
		// verify zip code selection
		verifyReportFormat("ShowAllocation",postCode,"No",2);
		// verify id selection
		verifyReportFormat("Adjusted Included",id,"No",2);
		// verify sort order selection
		verifyReportFormat("SortBy",sortOrder,"Customer Name then Time",3);
		// verify report format selection
		verifyReportFormat("report_format",reportFormat,"PDF",2);
		// verify delivery method selection
		verifyReportFormat("deliveryprotocolid",deliveryMethod,"Email",4);//4

		// logout resource manager
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		// initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Will Call Report";
	}
	
	private void verifyParkDefaultValue(){
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
		.getInstance();
		
		String options = rptCriteriaPg.getAllSelectionCriteria("FacilityLocID");
		String[] optionsArray = options.split("#");
		
		if (!optionsArray[0].equals(rptCriteriaPg.getParkDefaultID())) {
			throw new ErrorOnPageException("Park default value is not "+optionsArray[0]);
		}
	}
	
	private void verifyReportFormat(String id,List<String> expectItem,String defaultValue,int size){
		List<String> dropdown=browser.getDropdownElements("id", id);
		for(int i=0;i<expectItem.size();i++){
			if(!dropdown.contains(expectItem.get(i))){
				throw new ErrorOnDataException(expectItem.get(i)+"is not in the drop down list");
			}
		}
		if(dropdown.size()!=size){
			throw new ErrorOnDataException("drop down list doesn't have correct item");
		}
		
		String value=browser.getDropdownListValue("id", id, 0);
		if(!defaultValue.equals(value)){
			throw new ErrorOnDataException(defaultValue+" is not the default value");
		}
	}
	
	public void reportSectionInfo(){
		ticketCategory.add("All");
		ticketCategory.add("Organization");
		ticketCategory.add("Individual");
		
		homePhone.add("Yes");
		homePhone.add("No");
		
		postCode.add("Yes");
		postCode.add("No");
		
		id.add("Yes");
		id.add("No");
		
		sortOrder.add("Customer Name then Time");
		sortOrder.add("Time then Tour Name");
		sortOrder.add("Tour Name then Time");
		
		reportFormat.add("PDF");
		reportFormat.add("XLS");
		
		deliveryMethod.add("Online");
		deliveryMethod.add("Email");
//		deliveryMethod.add("FTP");
		deliveryMethod.add("Fax");
	}
}
