package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**Upper letter for hunting and finishing
 * select * from D_VEHICLE_TYPE;
 * select * from E_VEHICLE;
 * @author Phoebe Chen
 * @Date  Aug 10, 2012
 */
public class VehicleInformationReport extends ResourceManagerTestCase {
	private static final boolean UPPER_CASE = true;

	@Override
	public void execute() {
		// Set upper cases
		rm.checkUpperCaseConfigInDB(schema, UPPER_CASE);
		// Login
		rm.loginResourceManager(login);

		rm.selectOneRpt(rd.group, rd.reportName);

		//Delivery through "email"		
		rd.deliveryMethod = "Email";
		rm.selectOneRpt(rd.group, rd.reportName);
        rm.runSpecialRpt(rd, comparedPath);

		//Delivery by "online"
        rd.deliveryMethod = "Online"; 
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);		
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);		
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));


		rm.verifyReportCorrect(result);
		//log out
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		// report information
		rd.group = "All";
		rd.reportName = "Vehicle Information Report";
		rd.vehicleType = "Boat";
		rd.vehicleID = "MI1380BU";
		rd.reportFormat = "PDF";
        rd.productType = "HuntingAndFish";	
        rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		
		//templatesPath = "C://ReportTemplates";  //online test
	}

}


