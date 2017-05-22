/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;



import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class FiscalYearMonthlySalesStatisticsReport extends ResourceManagerTestCase {
	/**
	 * @Description:Fiscal Year Monthly Sales Statistics Report
	 * @Preconditions:
	 * @SPEC:Fiscal Year Monthly Sales Statistics Report
	 * @Task#:AUTO-1151
	 * 
	 * @author pzhu
	 * @Date  Aug 6, 2012
	 */
	private static final boolean UPPER_CASE = true;
	public void execute() {
		// Set upper cases
		rm.checkUpperCaseConfigInDB(schema, UPPER_CASE);
		rm.loginResourceManager(login);

		rd.deliveryMethod = "EMAIL";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);// PDF EMAIL
		
		
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));
		
		rm.verifyReportCorrect(result);

		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		rd.group = "Statistical Reports";
		rd.reportName = "Fiscal Year Monthly Sales Statistics Report";
		rd.locationID = "Mississippi Department of Wildlife, Fisheries, and Parks";
		rd.locationClass = "MDWFP District Office";
		rd.transactionTypes = new String[]{"Purchase Privilege"};
		rd.fiscalYear = "2012";
		rd.startDate = "01/01/2012";
		rd.endDate = "01/31/2012";
		rd.reportFormat = "PDF";
		rd.productType = "HuntingAndFish";	
		
		//templatesPath = "C://ReportTemplates"; //For run locally
	}
}

