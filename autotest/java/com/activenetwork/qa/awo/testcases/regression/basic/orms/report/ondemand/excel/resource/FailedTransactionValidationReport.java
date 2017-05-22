package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * select oi.verify_status_id 
from o_order o, O_HF_ORD_PROFILE op, o_hf_ord_prof_identifier oi, c_identifier i 
where 
o.id = op.ord_id 
and op.id = oi.ord_prof_id 
and i.id = oi.identifier_id 
and o.ord_num = '' ;
 * @SPEC:TC:025038,25040,25041,25042
 * @Task#:Auto-1159
 * 
 * @author Phoebe Chen
 * @Date  Aug 10, 2012
 */
public class FailedTransactionValidationReport extends ResourceManagerTestCase {
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
		rd.reportName = "Failed Transaction Validation Report";
		rd.startDate = "07/09/2012";
		rd.reportFormat = "XLS";
		rd.productType = "HuntingAndFish";		
		
		//templatesPath = "C://ReportTemplates"; //locatl test
	}

}
