package com.activenetwork.qa.awo.testcases.sanity.web;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class RA_MakeReservation_KOA extends WebTestCase {
	/**
	 * @since  2010/12/15
	 * @author VZHAO
	 */
	private String contract = "KOA"; //for creating schema name



	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);

		web.makeReservationToCart(bd);
		String resID = web.checkOutCart(pay);
		web.checkReservationExists(schema, resID);

		String status = "Confirmed";
		web.gotoMyReservationsAccount();
		web.gotoResDetailFromAccount(resID, bd.contractCode, status);
//		web.updateReservationDetails();
//		web.cancelReservation(pay);

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract;

		bd.state = "Montana";
		bd.conType = "Private";
		bd.arrivalDate = DataBaseFunctions.getLastKOAStartDate();//DateFunctions.getDateAfterToday(60);
		bd.lengthOfStay = "1";
		bd.contractCode = "KOA";
		bd.park = TestProperty.getProperty("web.koa.park");
		bd.parkId = DataBaseFunctions.getFacilityID(bd.park, schema);
		bd.siteID=TestProperty.getProperty("web.koa.site.id");
//		bd.siteID=DataBaseFunctions.getKOASiteID(bd.parkId,"FP"); //no site_cd named TE after change schema(JUL 01,2012)
		//select p.prd_id as id, p.prd_cd as code from p_prd p, p_prd_attr pa where p.prd_id=pa.prd_id and pa.attr_id=109 and p.park_id=196854 and pa.attr_value='55';
		bd.equip="Tent";
	}
}
