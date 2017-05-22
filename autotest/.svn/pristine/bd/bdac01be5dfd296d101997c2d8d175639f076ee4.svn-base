package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class MakePermitReservation extends RecgovTestCase
{
	/**
	 * Script Name   : <b>MakePermitReservation</b>
	 * Generated     : <b>Dec 2, 2009 4:23:00 AM</b>
	 * @since  2009/12/02
	 * @author vzhao
	 */
  	private String email, pw, url;
	private PermitInfo bd;
	private BWCooperator bw = BWCooperator.getInstance();
	private boolean isRecgov = true;

	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);

		bw.makePermitOrderToCart(bd, isRecgov);
		String resId = bw.checkOutCart(pay);

		bw.gotoPermitDetailsFromHome(resId, isRecgov);
		bw.verifyOrderStatus("Confirmed");
		bw.changeGroupSize(4,pay);
		
		bw.gotoPermitDetailsFromHome(resId, isRecgov);
		bw.verifyOrderStatus("Confirmed");
		bw.cancelPermitOrderFromDetails();

		bw.gotoPermitDetailsFromHome(resId, isRecgov);
		bw.verifyOrderStatus("Cancelled");

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
	  	url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd = new PermitInfo();
		bd.facility = "Boundary Waters Canoe Area Wilderness (Reservations)";
		bd.permitType = "Overnight Paddle";
		bd.entrance = "01 Trout Lake (op,om)";
		bd.isRange = false;
		bd.entryDate = DateFunctions.getDateAfterToday(10);
		bd.isUnifiedSearch=isUnifiedSearch();
		bd.contractCode="NRSO";
		bd.parkId="72600";
	}
}
