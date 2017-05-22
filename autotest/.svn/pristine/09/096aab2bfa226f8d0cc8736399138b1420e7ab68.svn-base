package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class CustomerAgingReport extends ResourceManagerTestCase
{
	/**
	 * Script Name   : <b>CustomerAgingReport</b>
	 * Generated     : <b>Feb 4, 2010 12:18:26 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/02/04
	 * @author QA
	 */

	public void execute() {
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF online

		rd.deliveryMethod = "EMAIL";
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);//PDF Email
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		
		rd.reportName = "Customer Aging Report";
		rd.agencyID = "USFS";
		rd.facilityID = "RYAN PARK";
		rd.collectionStatus = "Good Standing";
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "ONLINE";
	}
}

