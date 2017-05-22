/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This report used to cover report by type is 'standard' and Group By Privilege Expiry Year is 'Yes' and all other include drop down is 'Yes'
 * @Preconditions:
 * @SPEC:
 * @Task#:Auto-1391
 * 
 * @author ssong
 * @Date  Jan 30, 2013
 */
public class PrivilegeProductFulfillmentReport extends ResourceManagerTestCase{
	
	public void execute() {
		// Login
		rm.loginResourceManager(login);
		
		//Delivery through "email"		
		rd.deliveryMethod = "Email";
		rd.emailSubject = rd.reportName+DateFunctions.getCurrentTime();
		rm.selectOneRpt(rd.group, rd.reportName);
        rm.runSpecialRpt(rd, comparedPath);

        rd.deliveryMethod = "Online";
		rm.selectOneRpt(rd.group, rd.reportName);
		String fileName = rm.runSpecialRpt(rd, comparedPath);

		result = rm.skipVerifyOnlineReport(templatesPath, fileName);		
		
		result &= rm.verifyEmailReport(templatesPath,rm.getReportFromMailBox(host, username, password, comparedPath, rd.emailSubject));

		rm.verifyReportCorrect(result);
		
		// log out
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract"; 
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		// report information
		rd.group = "All";
		rd.reportName = "Privilege Product Fulfillment Report";
		rd.reportByType = "Standard";
		rd.locationID = "Mississippi Department of Wildlife, Fisheries, and Parks";
		rd.privilegeProduct = new String[] { "101 - RES ALL GAME HUNT/FRESH" };
		rd.includeDOB = "Yes";
		rd.includePrivilegeCode = "Yes";
		rd.includePrivilegeDescription = "Yes";
		rd.displayPrivDescription = "Before DOB";
		rd.includePrivEffeciveToDate = "Yes";
		rd.includePrivEffectiveFromDate = "Yes";
		rd.includeDriverLicenseNum = "Yes";
		rd.includeHunterEducationNum = "Yes";
		rd.customerAttribute = "Gender";
		rd.otherPrivilegeProducts = new String[]{"107 - RES WMA USER PERMIT(WMA)"};
		rd.includePrivilegeActivity = "Yes";
		rd.yesNoFlag = "Yes";
		rd.includeAP = "Yes";
		rd.includeCPF = "Yes";
		rd.startDate = "Mon Oct 01 2012";
		rd.endDate = "Mon Oct 01 2012";
		rd.reportFormat = "XLS";
	}
}
