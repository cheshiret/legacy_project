package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.maximumwindow.rec;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.keywords.web.UWP;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookTicketRollingOrganizationCategory extends RecgovTestCase {
	private String ruleName = "Maximum Window";
	
	private UWP web = UWP.getInstance();
	private String url, email, password;
	
	private TicketInfo ticket = new TicketInfo();
	
	private TimeZone timeZone = null;
	
	/**
	 * The Maximum Window Type=rolling, Length=10, Unit=month. 
	 * The maximum window rule takes effect to the Organization category tickets.
	 */
	public void execute() {
		web.invokeURL(url, false);
		web.signIn(email, password);
		/**
		 * Positively, verify the Maximum Window rule takes effect to the Organization category ticket
		 */
		ticket.ticketType = "Organization";
		ticket.tourDate = DateFunctions.getDateAfterGivenMonth(DateFunctions.getToday(timeZone), 10);
		
		web.gotoTourDetailsPage(ticket, true);//the true means Organization tour sales
		/*
		 * Verify the days beyond the maximum window are UNAVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtTourDetailPage(ruleName, DateFunctions.getDateAfterGivenDay(ticket.tourDate, 1), "unavailable");
		
		/*
		 * Verify the days within the maximum window are AVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtTourDetailPage(ruleName, ticket.tourDate, "available");
		
		/**
		 * Negatively, verify the Maximum Window rule takes effect to the Individual category ticket
		 */
		ticket.ticketType = "Individual";
		
		web.gotoTourDetailsPage(ticket, false);//the true means Individual tour sales
		
		/*
		 * Verify the days beyond the maximum window are AVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtTourDetailPage(ruleName, DateFunctions.getDateAfterGivenDay(ticket.tourDate, 1), "available");
		
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
		ticket.tourName = "Both Organization and Individual";
		ticket.ticketNums = "1";//ticket number
		ticket.isUnifiedSearch = isUnifiedSearch();
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		timeZone = DataBaseFunctions.getParkTimeZone(schema, ticket.park);
	}
}
