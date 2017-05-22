/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This report used to verify 'Include other managed Revenue Locations'=yes and 'Suppress Zero Amounts'=no
 * @Preconditions:
 * @SPEC:[TC:036156]
 * @Task#:Auto-1114
 * 
 * @author ssong
 * @Date  Jun 18, 2012
 */
public class ConcessionaireAnnualResReport_IncludeOtherLoc extends ResourceManagerTestCase{

	public void execute() {

		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName =rm.runSpecialRpt(rd, comparedPath);
		result=rm.skipVerifyOnlineReport(templatesPath, fileName);
		
		rm.verifyReportCorrect(result);
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/BEAVER CREEK (IDAHO)";//BEAVER CREEK (IDAHO) (ID)

		rd.group = "All";
		rd.reportName = "Concessionaire Annual Reservation Report";
		rd.yesNoFlag = "Yes";
		rd.supressEmptyLine = "No";
		rd.startDate="Wed Jul 1 2009";
		rd.endDate = "Wed Jul 1 2009";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rd.fileName = rd.reportName+"IncludeOtherLoc";
		
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Online";
	}
}
