package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.accesstime.rec;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookTicketOpenAlways extends RecgovTestCase {
	private String ruleName = "Access Time";
	private String errorMsg = "Please try again later at 12:00 AM";
	private String url, email, password;
	private TicketInfo ticket = new TicketInfo();
	
	public void execute() {
		web.invokeURL(url, false);
		//before sign in rec
		web.gotoTourDetailsPage(ticket);
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, errorMsg, ticket);
		
		//after sign in rec
		web.signIn(email, password);
		web.gotoTourDetailsPage(ticket);
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, errorMsg, ticket);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		password = TestProperty.getProperty("web.login.pw");
		
		ticket.park = "VOYAGEURS NATIONAL PARK TOURS";
		ticket.tourName = "Gold Mine Tour";
		ticket.tourDate = DateFunctions.getDateAfterToday(2);
		ticket.ticketNums = "1";//ticket#
		ticket.deliveryMethod = "Print at Home";
		
		ticket.isUnifiedSearch=isUnifiedSearch();
	}
}
