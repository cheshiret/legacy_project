package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.maximumwindow.rec;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.keywords.web.UWP;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookPermitRollingNonCommercialCategory extends RecgovTestCase {
	private String ruleName = "Maximum Window";
	
	private UWP web = UWP.getInstance();
	private String url, email, password;
	private BWCooperator bw = BWCooperator.getInstance();
	private boolean isRecgov = true;
	
	private PermitInfo permit = new PermitInfo();
	
	/**
	 * The Maximum Window Type=rolling, Length=10, Unit=month. 
	 * The maximum window rule takes effect to the Non Commercial category permits.
	 * And the permit category of permit sold at the REC is only 'Non Commercial'
	 */
	public void execute() {
		web.invokeURL(url);
		bw.signInRecGov(email, password);
		
		permit.entrance = permit.entrance.replaceAll(" - ", " ");
		permit.permitCategory = "Non Commerical";
		//calculate the latest arrival date
		permit.entryDate = DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday(), 10);
		
		bw.gotoBookPermitPage(permit, isRecgov);
		/*
		 * Verify the days beyond the maximum window are UNAVAILABLE
		 */
		bw.verifyBusinessRuleEffectiveAtBookPermitPage(ruleName, DateFunctions.getDateAfterGivenDay(permit.entryDate, 1), "unavailable");
		
		/*
		 * Verify the days within the maximum window are AVAILABLE
		 */
		bw.verifyBusinessRuleEffectiveAtBookPermitPage(ruleName, permit.entryDate, "available");
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		password = TestProperty.getProperty("web.login.pw");
		
		permit.facility = "Boundary Waters Canoe Area Wilderness (Reservations)";
		permit.permitType = "Overnight Paddle";
		permit.entrance = "69 - John Lake (op)";
		permit.isRange = false;
		
		permit.isUnifiedSearch=isUnifiedSearch();
		permit.contractCode = "NRSO";
		permit.parkId = "72600";
	}
}