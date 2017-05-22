package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**Upper case for hunting and fishing report  !!
 * select * from D_CERTIFICATION_TYPE ; ["Commercial Fishing Certification"---->2;"Trapper Certification"----->1]
 * select * from C_CUST_HFP_CERTIFICATION where effective_to is not null; 
 * @Description:This case is used to check the support of uppercase of certification renewal report.
 * @Task#:Auto-1159
 * @author Phoebe Chen
 * @Date  Aug 10, 2012
 * Notice: No product data found!
 */
public class CertificationRenewalReport extends ResourceManagerTestCase {
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
		rd.reportName = "Certification Renewal Report";
		rd.certificationType = "Trapper Certification";
		rd.reportFormat = "PDF";
		rd.startDate = "01/01/2011";
		rd.endDate = "12/31/2011";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();		
		rd.productType = "HuntingAndFish";		
		rd.fileName = "CertificationRenewalReportHuntingAndFish";
		
		//templatesPath = "C://ReportTemplates"; //local test
	}
}
