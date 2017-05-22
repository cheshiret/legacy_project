package com.activenetwork.qa.awo.testcases.sanity.web;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.testcases.abstractcases.BWTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BW_SubmitLottery extends BWTestCase {
	/**
	 * @since  2010/12/15
	 * @author VZHAO
	 */
	private String email, pw, url;
	private PermitInfo bd;
	private BWCooperator bw = BWCooperator.getInstance();
	private String contract = "NRRS";//for creating schema name

	public void execute() {
		bw.invokeURL(url);
		bw.signInBW(email, pw);

		bw.submitLotteryToCart(bd, pay);
		String resId = bw.checkOutCart(pay);

		bw.gotoLotteryDetailsFromHome(resId);
		bw.verifyOrderStatus("Entered");

		bw.signOutBW();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.bw.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;

		bd = new PermitInfo();
		bd.permitType = "Overnight Paddle";
		bd.entrance = "24 Fall Lake (op,om)";
		bd.isRange = false;
		bd.entryDate = web.getLotteryInvStartDate(schema, "72600", "Web Sanity submit lottery"); //web.getLotteryAvailability(schema, 72600,"web");
		bd.personTypes = new String[]{"Adult"};
		bd.personNums = new String[]{"2"};
		bd.leaderPhone = "9052866600";
		
		email=DataBaseFunctions.getCooperatorLogin(schema, TestProperty.getProperty("bw.lottery.coop.id"));
		pw = TestProperty.getProperty("bw.coop.pwd");
	}
}
