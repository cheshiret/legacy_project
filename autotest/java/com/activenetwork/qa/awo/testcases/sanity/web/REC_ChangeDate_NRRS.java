package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class REC_ChangeDate_NRRS extends RecgovTestCase {
	/**
	 * @since  2010/12/15
	 * @author VZHAO
	 */
	private String contract = "NRRS"; //for creating schema name

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate , Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		
		web.invokeURL(url);
		web.signIn(email, pw);
		
		web.makeReservationToCart(bd);
		web.changeDateFromCartToCart(bd.arrivalDate, bd.maxLoop);
		String resID = web.checkOutCart(pay);
		web.checkReservationExists(schema, resID);
		
		String status = "Confirmed";
		web.gotoMyReservationsAccount();
		web.gotoResDetailFromAccount(resID, bd.contractCode, status);
		web.updateReservationDetails();
		web.cancelReservation(pay);

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;

		bd.state = "South Carolina";
		bd.conType = "Federal";
		bd.arrivalDate = DateFunctions.getDateAfterToday(5,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "NRSO";
		bd.parkId="71464";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"TWIN LAKES";
		bd.siteNo = "015";
		bd.siteID="78700";
		bd.isUnifiedSearch=isUnifiedSearch();
	}
}
