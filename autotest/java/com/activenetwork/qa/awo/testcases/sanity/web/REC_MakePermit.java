package com.activenetwork.qa.awo.testcases.sanity.web;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class REC_MakePermit extends RecgovTestCase {
	/**
	 * @since  2010/12/15
	 * @author VZHAO
	 */
	private String email, pw, url;
	private PermitInfo bd;
	private BWCooperator bw = BWCooperator.getInstance();
	private String contract = "NRRS";//for creating schema name
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
		bw.changeGroupSize(3,pay);

		bw.gotoPermitDetailsFromHome(resId, isRecgov);
		bw.verifyOrderStatus("Confirmed");
		bw.cancelPermitOrderFromDetails();

		bw.gotoPermitDetailsFromHome(resId, isRecgov);
		bw.verifyOrderStatus("Cancelled");

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd = new PermitInfo();
		bd.permitType = "Overnight Paddle";
		bd.entrance = "01 Trout Lake (op,om)";
		bd.exitPoint = "04 Crab Lake (op)";
		bd.entryDate = DateFunctions.getDateAfterToday(5);
		bd.groupSize = "2";
		bd.isRange = false;
		bd.isUnifiedSearch=isUnifiedSearch();
		bd.autoCompleteOptionIndex=1;
		bd.parkId="72600";
		bd.contractCode="NRSO";
		
        
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.facility = bw.getFacilityName("72600", schema);
	}
}
