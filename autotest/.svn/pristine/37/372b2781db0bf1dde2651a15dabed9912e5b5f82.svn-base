package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.accesstime.rec;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookPermitOpenAlways extends RecgovTestCase {
	private String ruleName = "Access Time";
	private String errorMsg = "Please try again later at 12:00 AM";
	
	private PermitInfo permit = new PermitInfo();
	private BWCooperator bw = BWCooperator.getInstance();
	private String url, email, password;
	private boolean isRecgov = true;
	
	public void execute() {
		web.invokeURL(url, false);
		bw.signInRecGov(email, password);
		permit.entrance = permit.entrance.replaceAll(" - ", " ");
		bw.gotoBookPermitPage(permit, isRecgov);
		bw.verifyBusinessRuleEffectiveAtBookPermitPage(ruleName, errorMsg, permit);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		permit.facility = "Boundary Waters Canoe Area Wilderness (Reservations)";
		permit.permitCategory = "Non Commercial";
		permit.permitType = "Overnight Paddle";
		permit.entrance = "52 - Brant Lake (op)";
		permit.entryDate = DateFunctions.getDateAfterToday(6);
		permit.personType = "Adult";
		permit.personNum = "2";
		permit.issueTo = "Group Leader";
		permit.isRange = false;
		permit.isUnifiedSearch=isUnifiedSearch();
		
		permit.contractCode = "NRSO";
		permit.parkId = "72600";
		
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = bw.getNextEmail();
		password = TestProperty.getProperty("web.login.pw");
	}
}
