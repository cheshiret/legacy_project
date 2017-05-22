package com.activenetwork.qa.awo.testcases.sanity.web;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

public class REC_SubmitLottery extends RecgovTestCase {
	/**
	 * @since  2010/12/21
	 * @author VZHAO
	 */
	private PermitInfo bd;
	private BWCooperator bw = BWCooperator.getInstance();
	private String contract = "NRRS";//for creating schema name
	private boolean isRecgov = true;

	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);

		bw.submitLotteryToCart(bd, pay, isRecgov);
		String resId = bw.checkOutCart(pay);

		bw.gotoLotteryDetailsFromHome(resId, isRecgov);
		bw.verifyOrderStatus("Entered");

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;

		bd = new PermitInfo();
		bd.personTypes = new String[]{"Adult"};
		bd.personNums = new String[]{"2"};
		bd.facility = bw.getFacilityName("72600", schema);
		bd.parkId = "72600";
		bd.contractCode = "NRSO";
		bd.permitType = "Overnight Paddle"; 
		bd.entrance = "24 Fall Lake (op,om)";
		bd.isRange = false;
		bd.entryDate = web.getLotteryInvStartDate(schema, bd.parkId, "Web Sanity submit lottery"); //web.getLotteryAvailability(schema, 72600,"web");
		
		System.out.println(bd.entryDate);
		bd.isUnifiedSearch=isUnifiedSearch();
		bd.autoCompleteOptionIndex=1;
	}
}
