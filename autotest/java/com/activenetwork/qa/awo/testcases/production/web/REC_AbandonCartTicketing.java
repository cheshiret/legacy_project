package com.activenetwork.qa.awo.testcases.production.web;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class REC_AbandonCartTicketing extends RecgovTestCase {
	/**
	 * Script Name   : <b>REC_TicketReservation_NY</b>
	 * Generated     : <b>Jul 22, 2009 4:52:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/22
	 * @author QA
	 */
	private String email, pw, url;

	private TicketInfo ti;

	public void execute() {
		web.invokeURL(url, false);
		web.signIn(email, pw);
		web.bookTourIntoCart(ti);
		web.abandonCart();
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		
		if(env.equalsIgnoreCase("live")) {
			AwoUtil.loadLiveInformation();
	  	  	email = TestProperty.getProperty(env+".recgov.email");
	  	  	pw = TestProperty.getProperty(env+".recgov.pw");
	  	} else {
	  	  	email = web.getNextEmail();
	  	  	pw = TestProperty.getProperty("web.login.pw");
	  	}
		url = TestProperty.getProperty(env + ".web.recgov.url");
		ti = new TicketInfo();
		ti.park = "Roosevelt-Vanderbilt National Historic Sites";
		ti.tourName = "Vanderbilt Mansion Indiv";
//		ti.isUnifiedSearch=isUnifiedSearch();
		ti.contractCode="NRSO";
		ti.parkId="77814";
		ti.isUnifiedSearch=true;
	}
}
