package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * select p_prd.prd_name, o_priv_inst.* from p_prd, o_priv_inst where p_prd.prd_id=o_priv_inst.prd_id and p_prd.prd_name='FMAP'; 
 * @Task#:Auto-1159
 * @author Phoebe Chen
 * @Date  Aug 10, 2012
 */
public class LapsedCustomerReport extends ResourceManagerTestCase {
	private static final boolean UPPER_CASE = true;

	@Override
	public void execute() {
		// Set upper cases
		rm.checkUpperCaseConfigInDB(schema, UPPER_CASE);
		// Login
		rm.loginResourceManager(login);

		//Delivery through "email"		
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
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
		rd.reportName = "Lapsed Customer Report";
		rd.locationID = "Mississippi Department of Wildlife, Fisheries, and Parks";
		rd.privilegeProduct = new String[]{"108 - FMAP"};
		rd.privilegeHeldStartDate = "01/01/2012";
		rd.privilegeHeldEndDate = "01/31/2013";
		rd.privilegeLapsedStartDate = "01/01/2014";
		rd.privilegeLapsedEndDate = "01/01/2015";
		rd.reportFormat = "XLS";
		rd.productType = "HuntingAndFish";	
		
		//templatesPath = "C://ReportTemplates";  //local test
	}

}
