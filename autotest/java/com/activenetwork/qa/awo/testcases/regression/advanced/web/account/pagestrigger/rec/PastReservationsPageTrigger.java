package com.activenetwork.qa.awo.testcases.regression.advanced.web.account.pagestrigger.rec;

import com.activenetwork.qa.awo.pages.web.uwp.UwpAccountPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPastResListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

public class PastReservationsPageTrigger extends WebTestCase {
	private String email, pw, recUrl;

	public void execute() {
		//verify from REC
		web.invokeURL(recUrl);
		web.signIn(email, pw); 
		//selects the \ufffdPast Reservation\ufffd link from the \ufffdMy Reservation & Account\ufffd page
		this.gotoPastReservationsPg();
		web.accountPanelVerification("Any value");//not check res num, only check page exists
		//selects \ufffdPast Reservations\ufffd link from the \ufffdReservations\ufffd section in the Account Overview Page
		web.gotoMyReservationsAccount();
		web.gotoOtherPagesByReservationsSection("Past Reservations");
		web.accountPanelVerification("Any value");//not check res num, only check page exists
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		recUrl = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
	}
	
	/**Go to Past Reservations page from account panel.*/
	public void gotoPastReservationsPg() {
		UwpAccountPanel accountPanel = UwpAccountPanel.getInstance();
		UwpPastResListPage pastResPg = UwpPastResListPage.getInstance();	
		logger.info("Go to past reservations page from account panel.");
		
		accountPanel.gotoPastReservations();
		pastResPg.waitLoading();
	}
}
