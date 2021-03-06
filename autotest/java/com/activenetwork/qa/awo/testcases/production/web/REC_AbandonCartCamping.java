package com.activenetwork.qa.awo.testcases.production.web;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class REC_AbandonCartCamping extends RecgovTestCase {
	/**
	 * Script Name   : <b>REC_AbandonCartReservation_CA</b>
	 * Generated     : <b>Jul 22, 2009 4:50:44 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/22
	 * @author QA
	 */
	private String email, pw, url;

	public void execute() {
		web.invokeURL(url, false);
		web.signIn(email, pw);
		
		web.bookParkToSiteListPg(bd);//search park
		web.bookSiteToOrderDetailPg(bd);//search site
		web.fillInOrderDetails(od,bd.contractCode);
		web.abandonCart();
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
	  
	  	if(env.equalsIgnoreCase("live")) {
	  		AwoUtil.loadLiveInformation();
	  	  	bd.isProduction=true;
	  	  	email = TestProperty.getProperty(env+".recgov.email");
	  	  	pw = TestProperty.getProperty(env+".recgov.pw");
	  	} else {
	  	  	email = web.getNextEmail();
	  	  	pw = TestProperty.getProperty("web.login.pw");
	  	}
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.park ="Santa Cruz Scorpion";//Santa Cruz Scorpion (CA)
		bd.isRange = true;
		bd.contractCode = "NRSO";
		bd.parkId="70980";
		bd.arrivalDate = DateFunctions.getDateAfterToday(20);// Lesley[20130924]: change from 10 to 20 due to inventory on Production
		bd.lengthOfStay = "3";
//		bd.isUnifiedSearch=isUnifiedSearch();
		bd.isUnifiedSearch=true;
	}
}
