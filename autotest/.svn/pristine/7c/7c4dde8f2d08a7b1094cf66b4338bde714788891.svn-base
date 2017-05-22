package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.customerlabelsreport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.resourceManager.ResMgrReportCriteriaPage;
import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;

public class VerifySelectionCriteriaForPermit extends ResourceManagerTestCase{
	List<String> productGroup = new ArrayList<String>();
	List<String> layout = new ArrayList<String>();
	List<String> reportBy = new ArrayList<String>();
	List<String> customer = new ArrayList<String>();
	List<String> emailAddress = new ArrayList<String>();
	List<String> sortBy = new ArrayList<String>();
	List<String> reportFormat = new ArrayList<String>();
	List<String> deliveryMethod = new ArrayList<String>();
	
	@Override
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		selectProductCategory(rd.productGroup);
		reportSectionInfo();
		// verify product category selection
		verifyReportFormat("ProductCategory",productGroup,"Permit",3);
		// verify report layout selection
		verifyReportFormat("ReportLayout",layout,"Address Labels (30 per sheet)",2);
		// verify report by selection
		verifyReportFormat("ReportBy",reportBy,"Arrival Date",2);
		// verify customer selection
		verifyReportFormat("Customer",customer,"Billing Customer",2);
		// verify sort by selection
		verifyReportFormat("SortBy",sortBy,"Name",2);
		// verify customers with email addresses selection
		verifyReportFormat("Email",emailAddress,"Include",2);
		// verify report format selection
		verifyReportFormat("report_format",reportFormat,"PDF",1);
		// verify delivery method selection
		verifyReportFormat("deliveryprotocolid",deliveryMethod,"Online",2);

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
		rd.group = "All";
		rd.reportName = "Customer Labels Report";
		rd.productGroup = "Permit";
	}
	
	private void selectProductCategory(String productGroup) {
		ResMgrReportCriteriaPage rptCriteriaPg = ResMgrReportCriteriaPage
				.getInstance();
		rptCriteriaPg.selectProductCategory(productGroup);
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
		productGroup.add("Site");
		productGroup.add("Ticket");
		productGroup.add("Permit");
		
		layout.add("Address Labels (30 per sheet)");
		layout.add("Shipping Labels (6 per sheet)");
		
		reportBy.add("Arrival Date");
		reportBy.add("Order Date");
		
		customer.add("Billing Customer");
		customer.add("Group Leader");
		
		sortBy.add("Name");
		sortBy.add("Zip Code");
		
		emailAddress.add("Include");
		emailAddress.add("Exclude");
		
		reportFormat.add("PDF");
		
		deliveryMethod.add("Online");
		deliveryMethod.add("Email");
	}
}
