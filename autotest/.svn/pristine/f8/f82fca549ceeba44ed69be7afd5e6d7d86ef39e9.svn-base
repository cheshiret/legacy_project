package com.activenetwork.qa.awo.testcases.regression.basic.orms.report.ondemand.pdf.resource;

import com.activenetwork.qa.awo.testcases.abstractcases.ResourceManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class LotteryConfirmationLetterReport  extends ResourceManagerTestCase{
	/**
	 * Script Name   : <b>RM_rptLotteryConfirmationLetterReport</b>
	 * Generated     : <b>Jul 29, 2010 11:00:09 AM</b>
	 * Description   : Functional Test Script
	 * 
	 */
	public void execute() {
		//login resource manager
		rm.loginResourceManager(login);
		
		rm.selectOneRpt(rd.group, rd.reportName);
		rm.runSpecialRpt(rd, comparedPath);
		
		rm.logoutResourceManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";

		//initialize report data
		rd.group = "Operational Reports";
		rd.reportName = "Lottery Confirmation Letter Report";
		rd.lotteryName = "BWCAW Lottery 2009";
		rd.lotterySched = "ID 284497205 (12/01/2008 - 01/15/2009 )";
		rd.startDate="Mon Dec 1 2008";
		rd.endDate="Thu Jan 15 2009";
		rd.reportFormat = "PDF";
		rd.deliveryMethod = "Email";
	}
}
