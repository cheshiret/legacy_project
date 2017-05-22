package com.activenetwork.qa.awo.testcases.production.web;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class REC_AbandonCartPermit extends RecgovTestCase {
	/**
	 * Script Name   : <b>REC_AbandonCartBoundaryWaters</b>
	 * Generated     : <b>Aug 5, 2009 1:31:56 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/08/05
	 * @author QA
	 */
	private String email, pw, url;

	private BWCooperator bw = BWCooperator.getInstance();

	private PermitInfo bd;

	public void execute() {
		web.invokeURL(url, false);
		web.signIn(email, pw);
		
		bw.makePermitOrderToCart(bd, true);//isRec=true
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
		bd = new PermitInfo();
		bd.permitType = "River Launch Permit (Private)";//"Overnight Paddle"
		bd.facility="MIDDLE FORK OF THE SALMON" ;//"Middle Fork Of The Salmon";
		bd.entryDate = DateFunctions.getDateAfterToday(0);
		bd.isRange = true;
		bd.contractCode = "NRSO";
		bd.parkId="75534";
//		bd.isUnifiedSearch=isUnifiedSearch();
		bd.isUnifiedSearch=true;
		
	}
}
