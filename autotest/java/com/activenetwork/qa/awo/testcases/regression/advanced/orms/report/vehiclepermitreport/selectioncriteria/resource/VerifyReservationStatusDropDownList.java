package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.vehiclepermitreport.selectioncriteria.resource;

import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;

public class VerifyReservationStatusDropDownList extends ResourceManagerTestCase{
	@Override
	public void execute() {
		// Login resource manager
		rm.loginResourceManager(login);
		// Goto ResMgrSelectReportPage
		rm.selectOneRpt(rd.group, rd.reportName);
		
		// Verify Reservation Status Drop Down List
		verifyReservationStatus();
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
	
	private void verifyReservationStatus(){
		List<String> dropdown=browser.getDropdownElements("id", "ReservationStatus");
		this.checkListContent(dropdown,"All");
		this.checkListContent(dropdown,"Pre Arrival");
		this.checkListContent(dropdown,"Checked In");
		this.checkListContent(dropdown,"Checked Out");
		if(dropdown.size()!=4){
			throw new ErrorOnDataException("drop down list doesn't have correct item");
		}
		
		String defaultValue=browser.getDropdownListValue("id", "ReservationStatus", 0);
		if(!"All".equals(defaultValue)){
			throw new ErrorOnDataException("All is not the default value");
		}
	}
	
	private void checkListContent(List<String> listValue,String content){
		if(!listValue.contains(content)){
			throw new ErrorOnDataException(content+"is not in the drop down list");
		}
	}
}
