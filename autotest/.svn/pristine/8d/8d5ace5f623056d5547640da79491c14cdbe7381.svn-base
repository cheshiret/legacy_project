package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class MakeResForTourAndCamping extends RecgovTestCase {
	/**
	 * Script Name   : <b>REC_MakeResForTourAndCamping</b>
	 * Generated     : <b>Nov 1, 2009 9:27:29 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/11/01
	 * @author vzhao
	 */
	private TicketInfo ti;
	private String[] items = new String[2];

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);

		web.bookTourIntoCart(ti);//add a tour item to shopping cart
		web.bookParkToSiteListPg(bd); // search park
		web.bookSiteToOrderDetailPg(bd); // search site
		web.fillInOrderDetails(od,bd.contractCode); // fill order detail and go to cart
		web.verifyItemsInShopCartOrConfirmPg(2, items);// two items, one tour and one camp

		web.abandonCart(); // abandon cart
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		
		ti = new TicketInfo();
		ti.contractCode = "NRSO";
		ti.park = "Roosevelt-Vanderbilt National Historic Sites";
		ti.tourName = "Eleanor(Val Kill) Indiv";
		ti.tourDate = DateFunctions.getDateAfterToday(2);
		ti.ticketNums = "3";
		ti.parkId = "77814";
		
		ti.isUnifiedSearch=isUnifiedSearch();

		bd.state = "South Carolina";
		bd.stateCode = "SC";
		bd.conType = "State";
		bd.arrivalDate = ti.tourDate;
		bd.lengthOfStay = "2";
		bd.contractCode = "NRSO";
		bd.siteNo = "005";
		bd.siteID="78690";
		bd.parkId="71464";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema);//TWIN LAKES";
		bd.isUnifiedSearch=isUnifiedSearch();
		

		items[0] = ti.tourName;
		items[1] = bd.park;
	}
}
