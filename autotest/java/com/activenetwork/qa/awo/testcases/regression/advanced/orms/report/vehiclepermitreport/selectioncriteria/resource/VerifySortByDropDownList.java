package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.vehiclepermitreport.selectioncriteria.resource;

import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;

public class VerifySortByDropDownList extends ResourceManagerTestCase{
	
	@Override
	public void execute() {
		// Login resource manager
		rm.loginResourceManager(login);
		// Goto ResMgrSelectReportPage
		rm.selectOneRpt(rd.group, rd.reportName);
		
		// Verify Sort By Drop Down List
		verifySortByList();
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
	
	private void verifySortByList(){
		List<String> dropdown=browser.getDropdownElements("id", "SortBy");
		this.checkListContent(dropdown,"Loop");
		this.checkListContent(dropdown,"Site");
		this.checkListContent(dropdown,"Order No");
		this.checkListContent(dropdown,"Customer");
		this.checkListContent(dropdown,"Arrival Date");
		if(dropdown.size()!=5){
			throw new ErrorOnDataException("drop down list doesn't have correct item");
		}
		
		String defaultValue=browser.getDropdownListValue("id", "SortBy", 0);
		if(!"Loop".equals(defaultValue)){
			throw new ErrorOnDataException("Loop is not the default value");
		}
	}
	
	private void checkListContent(List<String> listValue,String content){
		if(!listValue.contains(content)){
			throw new ErrorOnDataException(content+"is not in the drop down list");
		}
	}
}
