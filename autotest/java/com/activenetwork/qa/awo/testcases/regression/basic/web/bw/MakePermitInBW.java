package com.activenetwork.qa.awo.testcases.regression.basic.web.bw;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class MakePermitInBW extends WebTestCase
{
	/**
	 * Script Name   : <b>MakePermitInBW</b>
	 * Generated     : <b>Dec 3, 2009 12:39:12 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/12/03
	 * @author vzhao
	 */
  	private String email, pw, url;
	private PermitInfo bd;
	private BWCooperator bw = BWCooperator.getInstance();

	public void execute() {
		web.invokeURL(url);
		bw.signInBW(email, pw);

		bw.makePermitOrderToCart(bd);
		String resId = bw.checkOutCart(pay);

		bw.gotoPermitDetailsFromHome(resId);
		bw.verifyOrderStatus("Confirmed");
		bw.cancelPermitOrderFromDetails();

		bw.gotoPermitDetailsFromHome(resId);
		bw.verifyOrderStatus("Cancelled");

		bw.signOutBW();
	}

	public void wrapParameters(Object[] param) {
	  	schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		url = TestProperty.getProperty(env + ".web.bw.url");
	  	email = DataBaseFunctions.getCooperatorLogin(schema, TestProperty.getProperty("bw.permit.coop.id"));
		pw = TestProperty.getProperty("bw.coop.pwd");

		bd = new PermitInfo();
		bd.facility = "Boundary Waters Canoe Area Wilderness (Reservations)";
		bd.permitType = "Overnight Paddle";
		bd.entrance = "01 Trout Lake (op,om)";
		bd.isRange = false;
		bd.entryDate = DateFunctions.getDateAfterToday(3);
	}
}
