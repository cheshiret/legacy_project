package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.transactionfeereport.selectioncriteria.resource;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;

public class VerifySelectionCriteria extends ResourceManagerTestCase{
	List<String> exception = new ArrayList<String>();
	List<String> reportFormat = new ArrayList<String>();
	List<String> deliveryMethod = new ArrayList<String>();
	
	@Override
	public void execute() {
		// login resource manager
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);
		reportSectionInfo();
		
		// verify exception selection
		verifyReportFormat("Exceptions",exception,"Yes",2);
		// verify report format selection
		verifyReportFormat("report_format",reportFormat,"XLS",2);
		// verify delivery method selection
		verifyReportFormat("deliveryprotocolid",deliveryMethod,"Email",4);//4

		// logout resource manager
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		// initialize report data
		rd.group = "All";
		rd.reportName = "Transaction Fee Report";
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
	
	private void reportSectionInfo(){
		exception.add("Yes");
		exception.add("No");
		
		reportFormat.add("XLS");
		reportFormat.add("CSV");
		
		deliveryMethod.add("Online");
		deliveryMethod.add("Email");
		deliveryMethod.add("SFTP");
		deliveryMethod.add("Fax");
	}
}
