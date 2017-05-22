package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.pagestrigger.ra;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AccountOverviewPageTrigger extends WebTestCase {
	private String email, pw, url;

	public void execute() {
		//verify from RA
		web.invokeURL(url);
		web.signIn(email, pw); // trigger customer logged in
		web.accountPanelVerification("Account Overview");
		web.accountPanelVerification(email);
		web.gotoMyReservationsAccount();// trigger customer click My Reservation & Account link
		web.accountPanelVerification("Account Overview");
		web.accountPanelVerification(email);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
	}
}
