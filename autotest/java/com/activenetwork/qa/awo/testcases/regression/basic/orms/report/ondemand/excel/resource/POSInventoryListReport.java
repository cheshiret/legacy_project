package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.excel.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * 
 * @Description: verify POS Inventory List Report with XLS format generated correctly
 * @Preconditions: Need production data
 * @SPEC: POS Inventory List Report [TC:038856]
 * @Task#: AUTO-1142
 * 
 * @author qchen
 * @Date  Aug 2, 2012
 */
public class POSInventoryListReport extends ResourceManagerTestCase {

	@Override
	public void execute() {
		rm.loginResourceManager(login);
		
		//select and run this report
		rm.selectOneRpt(rd.group, rd.reportName);
		fileName = rm.runSpecialRpt(rd, comparedPath);
		
		//compare with report template
		result = rm.skipVerifyOnlineReport(templatesPath, fileName);
		rm.verifyReportCorrect(result);
		
		rm.logoutResourceManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		//TODO wait for production data ready
		rd.group = "Operational Reports";
		rd.reportName = "POS Inventory List Report";
		rd.agencyID = "STATE PARKS";
		rd.regionID = "DISTRICT 5";
		rd.yesNoFlag = "No";
		rd.facilityID = "LAKE LINCOLN";
		rd.productGroup = "Magazines";
		rd.inventoryType = "Non-Restrictive Inventory";
		rd.reportFormat = "XLS";
		rd.deliveryMethod = "Online";
	}
}
