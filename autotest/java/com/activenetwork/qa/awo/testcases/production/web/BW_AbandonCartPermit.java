package com.activenetwork.qa.awo.testcases.production.web;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class BW_AbandonCartPermit extends WebTestCase {
	/**
	 * Script Name   : <b>BW_AbandonCartPermit</b>
	 * Generated     : <b>Aug 5, 2009 6:27:49 AM</b>
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
		bw.invokeURL(url, false);
		bw.signInBW(email, pw);
		bw.bookPermitByFindGroupLeader(bd, false);//isRec=false
		bw.abandonCart();
		bw.signOutBW();
	}

	public void wrapParameters(Object[] param) {
		
		if(env.equalsIgnoreCase("live")) {
			AwoUtil.loadLiveInformation();
			email = TestProperty.getProperty(env+".bw.coop.email");
			pw = TestProperty.getProperty(env+".bw.coop.pw");
		} else {
			schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
			email = DataBaseFunctions.getCooperatorLogin(schema, TestProperty.getProperty("bw.permit.coop.id"));
			pw = TestProperty.getProperty("bw.coop.pwd");
		}

		url = TestProperty.getProperty(env + ".web.bw.url");
		bd = new PermitInfo();
		bd.groupSize = "2";
		bd.issue = false;
		bd.isRange = true;
		bd.isDistrict = false;
//		bd.startDate=DateFunctions.getd

		bd.groupLeader.setAsDefaultGroupLeader();
	}
}
