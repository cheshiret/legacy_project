package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class TicketConfirmationLetterReprint extends ResourceManagerTestCase{
	public void execute() {

		rm.loginResourceManager(login);
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		rd.group = "Operational Reports";
		rd.reportName = "Ticket Confirmation Letter (Reprint)";

		rd.locationID = "WASHINGTON MONUMENT";
		
		rd.tourDate = "Tue Jul 17 2007";
		rd.orderDate = "Mon Jul 30 2007";

		rd.reportFormat = "PDF";
		rd.deliveryMethod = "EMAIL";
	}
}
