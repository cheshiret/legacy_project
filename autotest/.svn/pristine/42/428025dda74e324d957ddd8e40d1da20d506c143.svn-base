package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * select * from O_ORDER where id in (select ord_id from O_HF_ORD_PROFILE where RESIDENCY_PROOF_ID is not null);
 * @Task#:Auto-1159
 * @author Phoebe Chen
 * @Date  Aug 10, 2012
 * No data found by now
 */
public class AlternateProofofResidencyReport extends ResourceManagerTestCase {
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
		rd.reportName = "Alternate Proof of Residency Report";
		rd.locationID = "Mississippi Department of Wildlife, Fisheries, and Parks";
		rd.startDate = "07/01/2011";
		rd.endDate = "09/01/2011"; //maximum report period of 92 days
		rd.privilegeProductAPR = new String[]{"145 - NR WMA USER PERMIT"};
		rd.reportFormat = "PDF";
        rd.productType = "HuntingAndFish";		
		
        //templatesPath = "C://ReportTemplates"; //to run locally
	}

}

