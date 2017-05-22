package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.financialsessionsummaryreport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;

public class VerifySelectionCriteria extends ResourceManagerTestCase{
	List<String> status = new ArrayList<String>();
	List<String> paymentGroup = new ArrayList<String>();
	List<String> paymentMethod = new ArrayList<String>();
	List<String> reportFormat = new ArrayList<String>();
	List<String> deliveryMethod = new ArrayList<String>();
	
	@Override
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		reportSectionInfo();
		
		// verify status selection
		verifyReportFormat("FinancialSessionStatus",status,"All",4);
		// verify payment group selection
		verifyReportFormat("PaymentGrp",paymentGroup,"All",8);
//		// verify payment method selection
//		verifyReportFormat("PaymentType",paymentMethod,"All",1);
		// verify report format selection
		verifyReportFormat("report_format",reportFormat,"PDF",2);
		// verify delivery method selection
		verifyReportFormat("deliveryprotocolid",deliveryMethod,"Email",3);//4

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
		rd.group = "Financial Reports";
		rd.reportName = "Financial Session Summary Report";
	}
	
	private void verifyReportFormat(String id,List<String> expectItem,String defaultValue,int size){
		List<String> dropdown=browser.getDropdownElements("id", id);
		for(int i=0;i<expectItem.size();i++){
			if(!dropdown.contains(expectItem.get(i))){
				throw new ErrorOnDataException(expectItem.get(i)+"is not in the drop down list");
			}
		}
		
		String value=browser.getDropdownListValue("id", id, 0);
		if(!defaultValue.equals(value)){
			throw new ErrorOnDataException(defaultValue+" is not the default value");
		}
	}
	
	public void reportSectionInfo(){
		status.add("All");
		status.add("Open");
		status.add("Closed");
		status.add("Deposited");
		
		paymentGroup.add("All");
		paymentGroup.add("Cash");
		paymentGroup.add("Non Cash Depositable");
		paymentGroup.add("Credit Card");
		paymentGroup.add("Non Depositable");
		paymentGroup.add("ACH");
		paymentGroup.add("Voucher");//Add a short Voucher program in Finance Manager
		paymentGroup.add("Deferred");
		
//		paymentMethod.add("All");
		
		reportFormat.add("PDF");
		reportFormat.add("DHTML");
		
		deliveryMethod.add("Online");
		deliveryMethod.add("Email");
//		deliveryMethod.add("FTP");
		deliveryMethod.add("Fax");
	}
}
