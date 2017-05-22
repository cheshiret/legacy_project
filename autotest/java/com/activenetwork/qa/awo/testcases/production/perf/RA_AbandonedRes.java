/**
 * 
 */
package com.activenetwork.qa.awo.testcases.production.perf;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * The script is setup for Production testing after Production performance testing work done.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  2012-4-11
 */
public class RA_AbandonedRes extends  RATestCase {
	public void execute() {
		web.invokeURL(url);
		//Verify Sign In using web customer account.
		web.signIn(email, pw);
		web.makeReservationToCart(bd);
		web.abandonCart();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "California";
		bd.park = "San Luis Reservoir SRA";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(20,Calendar.MONDAY);
		bd.lengthOfStay = "1";
		bd.contractCode = "CA";

		bd.clickParkName = true;//forced to click on park name link
		bd.searchInParkDetail = true;
	}
}
