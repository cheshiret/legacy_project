package com.activenetwork.qa.awo.testcases.sanity.web;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.testcases.abstractcases.BWTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BW_ChangeGroupSize  extends BWTestCase {
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

		bw.makePermitOrderToCart(bd);
		String resId = bw.checkOutCart(pay);

		bw.gotoPermitDetailsFromHome(resId);
		bw.verifyOrderStatus("Confirmed");
		bw.changeGroupSize(4, pay);

		bw.gotoPermitDetailsFromHome(resId);
		bw.verifyOrderStatus("Confirmed");
		bw.changeGroupSize(3,pay);

		bw.gotoPermitDetailsFromHome(resId);
		bw.verifyOrderStatus("Confirmed");
		bw.cancelPermitOrderFromDetails();

		bw.gotoPermitDetailsFromHome(resId);
		bw.verifyOrderStatus("Cancelled");

		bw.signOutBW();
	}

	public void wrapParameters(Object[] param) {
		bd = new PermitInfo();
		bd.facility = "Boundary Waters Canoe Area Wilderness (Reservations)";
		bd.permitType = "Overnight Paddle";
		bd.entrance = "01 Trout Lake (op,om)";
		bd.exitPoint = "04 Crab Lake (op)";
		bd.entryDate = DateFunctions.getDateAfterToday(5);
		bd.groupSize = "2";
		bd.isRange = false;

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;
		url = TestProperty.getProperty(env + ".web.bw.url");
		
		email=DataBaseFunctions.getCooperatorLogin(schema, TestProperty.getProperty("bw.permit.coop.id"));
		pw = TestProperty.getProperty("bw.coop.pwd");
	}
}
