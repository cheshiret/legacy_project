package com.activenetwork.qa.awo.testcases.sanity.web;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.testcases.abstractcases.BWTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BW_WalkupPermit extends BWTestCase {
	/**
	 * Script Name   : <b>BW_WalkupPermit</b>
	 * Generated     : <b>Nov 26, 2008 1:59:41 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2008/11/26
	 * @author QA
	 */
	private String email, pw, url;
	private PermitInfo bd;
	private BWCooperator bw = BWCooperator.getInstance();
	private String contract = "NRRS";//for creating schema name

	public void execute() {//not finished
		bw.invokeURL(url);
		bw.signInBW(email, pw);

		bw.makePermitOrderToCart(bd);
		String resId = bw.checkOutCart(pay);

		bw.gotoPermitDetailsFromHome(resId);
		bw.verifyOrderStatus("Issued");
		bw.undoIssuePermitFromDetails();

		bw.gotoPermitDetailsFromHome(resId);
		bw.verifyOrderStatus("Confirmed");
		bw.cancelPermitOrderFromDetails();//can not cancel permit order after undo issue

		bw.gotoPermitDetailsFromHome(resId);
		bw.verifyOrderStatus("Cancelled");

		bw.signOutBW();
	}

	public void wrapParameters(Object[] param) {
		bd = new PermitInfo();
		bd.permitType = "Overnight Paddle";
		bd.entrance = "01 Trout Lake (op,om)";
		bd.exitPoint = "04 Crab Lake (op)";
		bd.entryDate = DateFunctions.getToday();
		bd.groupSize = "2";
		bd.issue = true;
		bd.isRange = false;

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;
		bd.facility = bw.getFacilityName("72600", schema);
		url = TestProperty.getProperty(env + ".web.bw.url");
		
		email=DataBaseFunctions.getCooperatorLogin(schema, TestProperty.getProperty("bw.permit.coop.id"));
		pw = TestProperty.getProperty("bw.coop.pwd");
	}
}
