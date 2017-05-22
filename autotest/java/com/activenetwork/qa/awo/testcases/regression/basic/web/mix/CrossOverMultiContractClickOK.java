package com.activenetwork.qa.awo.testcases.regression.basic.web.mix;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.uwp.UwpShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CrossOverMultiContractClickOK extends RATestCase {
	private BookingData cbd;
	String schema_parkOne;
	String schema_parkTwo;
	

	public void execute() {
		web.checkAndReleaseInventory(schema_parkOne, cbd.arrivalDate, Integer.parseInt(cbd.lengthOfStay), false, cbd.siteID);
		web.checkAndReleaseInventory(schema_parkTwo, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);

		web.invokeURL(url);
		web.signIn(email, pw);
		
		web.makeReservationToCart(bd);
		web.bookMoreReservations(false);//book another NRRS contract res

		web.bookParkToSiteListPg(cbd); // search park
		web.bookSiteToSiteDetails(cbd);
		web.crossOverVerification(email, pw,false);
		if(!isShoppingcartEmpty()) {
			web.abandonCart();
		}
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema_parkOne = DataBaseFunctions.getSchemaName("NRRS", env);
		schema_parkTwo = DataBaseFunctions.getSchemaName("SC", env);
		
		cbd = new BookingData();
		cbd.state = "South Carolina";
		cbd.parkId = "71464";
		cbd.park = DataBaseFunctions.getFacilityName(cbd.parkId, schema_parkOne); //"TWIN LAKES (SC)";
		cbd.conType = "Federal";
		cbd.contractCode = "NRSO";
		cbd.arrivalDate = DateFunctions.getDateAfterToday(10);
		cbd.lengthOfStay = "3";
		cbd.contractCode = "NRSO";
		cbd.siteNo = "014";
		cbd.siteID = "78699";
		cbd.isUnifiedSearch = MiscFunctions.isRAUnifiedSearchOpen();
		cbd.lookFor = "Any camping spot";
		
		bd.state = "South Carolina";
		bd.parkId = "10371";
		bd.contractCode = "SC";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema_parkTwo); //"Devils Fork";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10);
		bd.lengthOfStay = "2";
		bd.contractCode = "SC";
		bd.loop = "RV Site Loop 31-59";
		bd.siteNo = "052";
		bd.siteID="2322";
	}
	
	private boolean isShoppingcartEmpty() {
		UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();
		boolean toReturn = false;
		if(shoppingCart.isShoppingCartEmpty()) {
			toReturn = true;
		}
		
		return toReturn;
	}
}
