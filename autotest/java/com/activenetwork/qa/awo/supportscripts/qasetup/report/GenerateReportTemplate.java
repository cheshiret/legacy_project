/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.report;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.report.GenerateReportTemplateFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This script is used to generate report template after schema changed, the script fit for those report data changed report after schema change
 * 
 * @author ssong
 * @Date  Feb 1, 2013
 */
public class GenerateReportTemplate extends SetupCase{
	
	private ReportData rd = new ReportData();
	private LoginInfo login = new LoginInfo();
	private GenerateReportTemplateFunction func = new GenerateReportTemplateFunction();
	
	/* (non-Javadoc)
	 * @see testAPI.abstractcases.SetupCase#executeSetup()
	 */
	@Override
	public void executeSetup() {
		func.execute(login,rd);
		
	}

	/* (non-Javadoc)
	 * @see testAPI.abstractcases.SetupCase#readDataFromDatabase()
	 */
	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("role_location");
		rd.reportName = datasFromDB.get("report_name");
		rd.group = datasFromDB.get("group");
		rd.locationID = datasFromDB.get("LocationID");
		rd.privilegeProduct = datasFromDB.get("privilege_product").split(",");
		rd.includePrivilegeCode = datasFromDB.get("include_privilege_code");
		rd.startDate = datasFromDB.get("start_date");
		rd.endDate = datasFromDB.get("end_date");
		rd.reportFormat = datasFromDB.get("report_format");
		rd.deliveryMethod = datasFromDB.get("delivery_method");
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.fileName = datasFromDB.get("saveAsName");
	}

	/* (non-Javadoc)
	 * @see testAPI.abstractcases.FunctionCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_setup_report";
		
	}

}
