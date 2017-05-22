package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**Upper letter for hunting and finishing
 * "select CUST_NUMBER as MDWFP from C_CUST_HFPROFILE;" "select cust_id from c_cust"
 * select C_CUST_HFPROFILE.cust_number, c_cust.* from c_cust
left join C_CUST_HFPROFILE on C_CUST_HFPROFILE.cust_id = c_cust.cust_id
left join C_CUST_HFP_EDUCATION on C_CUST_HFP_EDUCATION.prof_id = C_CUST_HFPROFILE.id
where C_CUST_HFPROFILE.cust_number is not null and C_CUST_HFP_EDUCATION.status_id=4
 * @Task#:Auto-1159
 * @author Phoebe Chen
 * @Date  Aug 10, 2012
 */
public class CustomerInformationReport extends ResourceManagerTestCase {
	private static final boolean UPPER_CASE = true;

	@Override
	public void execute() {
		// Set upper cases
//		rm.updateUpperCaseConfigInDB(schema,UPPER_CASE);
		rm.checkUpperCaseConfigInDB(schema, UPPER_CASE);
		// Login
		rm.loginResourceManager(login);
		
		//Quentin[20131108] according to newest <<Customer Information Report.RPT>>, the option 'Email' has been removed from Delivery Method dropdown list
//		//Delivery through "email"		
//		rd.deliveryMethod = "Email";
//		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
//		rm.selectOneRpt(rd.group, rd.reportName);
//        rm.runSpecialRpt(rd, comparedPath);
		
		//Delivery by "online"
        rd.deliveryMethod = "Online"; 
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);		
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);		
		
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
		rd.reportName = "Customer Information Report";
		String customerId = "22000192";
		rd.customerNumber = rm.getCustomerNumByCustId(customerId, schema);		
		rd.reportFormat = "PDF";
        rd.productType = "HuntingAndFish";		
        rd.emailSubject = rd.reportName + DateFunctions.getCurrentTime();	
        rd.productType = "HuntingAndFish";	
        rd.fiscalYear = "2";//it will run the data of last fiscal year
	}

}
