package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.vehiclepermitreport.selectioncriteria.resource;

import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;

public class VerifyReportFormatDropDownList extends ResourceManagerTestCase{
	@Override
	public void execute() {
		// Login resource manager
		rm.loginResourceManager(login);
		// Goto ResMgrSelectReportPage
		rm.selectOneRpt(rd.group, rd.reportName);
		
		// Verify Report Format Drop Down List
		verifyReportFormat();
		// Logout resource manager
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// Login info for resource manager
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		// Report info
		rd.group = "All";
		rd.reportName = "Vehicle Permits Report";
	}
	
	private void verifyReportFormat(){
		List<String> dropdown=browser.getDropdownElements("id", "report_format");
		this.checkListContent(dropdown,"DHTML");
		this.checkListContent(dropdown,"PDF");
		this.checkListContent(dropdown,"XLS");
		if(dropdown.size()!=3){
			throw new ErrorOnDataException("drop down list doesn't have correct item");
		}
		
		String defaultValue=browser.getDropdownListValue("id", "report_format", 0);
		if(!"PDF".equals(defaultValue)){
			throw new ErrorOnDataException("PDF is not the default value");
		}
	}
	
	private void checkListContent(List<String> listValue,String content){
		if(!listValue.contains(content)){
			throw new ErrorOnDataException(content+"is not in the drop down list");
		}
	}
}
