package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.maximumwindow.rec;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.keywords.web.UWP;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookTicketBlockWebSalesChannel extends RecgovTestCase {
	private String ruleName = "Maximum Window";
	
	private UWP web = UWP.getInstance();
	private String url, email, password;
	
	private TicketInfo ticket = new TicketInfo();
	
	/**
	 * The Maximum Window Type=block, Length=10, Unit=month, Block Release Length=1, Block Release Unit=month, Block Release Day Of Month=1
	 */
	public void execute() {
		web.invokeURL(url, false);
		web.signIn(email, password);
		
		//calculate the latest arrival date
		String monthOfToday = DateFunctions.getToday().split("/")[0];
		String yearOfToday = DateFunctions.getToday().split("/")[2];
		ticket.tourDate = DateFunctions.getDateAfterGivenDay(DateFunctions.getDateAfterGivenMonth(DateFunctions.combineStringToDate(yearOfToday, monthOfToday, "1"), 11), -1);
		
		web.gotoTourDetailsPage(ticket);
		/*
		 * Verify the days beyond the maximum window are UNAVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtTourDetailPage(ruleName, DateFunctions.getDateAfterGivenDay(ticket.tourDate, 1), "unavailable");
		
		/*
		 * Verify the days within the maximum window are AVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtTourDetailPage(ruleName, ticket.tourDate, "available");
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		password = TestProperty.getProperty("web.login.pw");
		
		ticket.park = "VOYAGEURS NATIONAL PARK TOURS";
		ticket.tourName = "Discovery Cruise";
		ticket.ticketNums = "1";//ticket number
		ticket.isUnifiedSearch = isUnifiedSearch();
	}
}
