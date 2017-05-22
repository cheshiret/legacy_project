package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.maximumwindow.rec;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.keywords.web.UWP;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookTicketRollingWebSalesChannel extends RecgovTestCase {
	private String ruleName = "Maximum Window";
	
	private UWP web = UWP.getInstance();
	private String url, email, password;
	
	private TicketInfo ticket = new TicketInfo();
	
	/**
	 * The Maximum Window Type=rolling, Length=11, Unit=month
	 */
	public void execute() {
		web.invokeURL(url);
		web.signIn(email, password);
		
		//get the latest arrival date of the maximum window as tour date
		ticket.tourDate = DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday(), 11);
		web.gotoTourDetailsPage(ticket);
		
		/*
		 * Verify the days within the maximum window are AVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtTourDetailPage(ruleName, ticket.tourDate, "available");
		
		/*
		 * Verify the days beyond the maximum window are UNAVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtTourDetailPage(ruleName, DateFunctions.getDateAfterGivenDay(ticket.tourDate, 1), "unavailable");
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		password = TestProperty.getProperty("web.login.pw");
		
		ticket.park = "VOYAGEURS NATIONAL PARK TOURS";
		ticket.tourName = "Ellsworth Rock Gardens Tour";
		ticket.ticketNums = "1";//ticket number
		ticket.isUnifiedSearch = isUnifiedSearch();
	}
}
