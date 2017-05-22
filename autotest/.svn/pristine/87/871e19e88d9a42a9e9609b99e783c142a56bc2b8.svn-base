/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:TC:070659,070124
 * @Task#:Auto-2310
 * 
 * @author ssong
 * @Date  Jul 30, 2014
 */
public class ParkDepositReport_Marina extends ResourceManagerTestCase{

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		result = rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath,rd.fileName, rd.emailSubject));
		
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
		
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NY Contract";
		login.location = "Administrator/Contract";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NY";

		rd.reportName = "Park Deposit Report";
		rd.agencyID = "OPRHP";
		rd.facilityID = rm.getFacilityName("3885", schema);//"Allan H. Treman State Marine Park";
		rd.paymentGroup = "All";
		rd.startDate = "Mon Jul 1 2013";
		rd.endDate = "Tue Oct 01 2013";
		
		rd.fileName = caseName;
		
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "EMAIL";
		
	}

}
