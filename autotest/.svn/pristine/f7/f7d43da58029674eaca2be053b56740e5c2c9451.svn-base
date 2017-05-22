package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.testcases.abstractcases.web.SiteTransferTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P) MD contract
 * 1. Make reservation using site 07
 * 2. Go to "Reservation Details" page to check site 07 associated info(no, loop, arrival and departure)
 * 3. Click "Change Reservation" link to "Change Reservation Details'" page to check site 07 associated info(no, loop, type, arriving, length of stay)
 * 4. Click "Change Date/Transfer to Another Site" button to "Change Reservation" page to check site 08 associated info (no, loop and type)
 * 5. Click "Book These Dates" to "Change Reservation Details" page to check site 07 and site 08 associated info(no, loop, type, arriving, length of stay)
 * 6. Finish this order
 * 7. Go to "Reservation Details" page to check site 08 associated info(no, loop, arrival and departure)
 * 
 * @Preconditions: 
 * @SPEC: "Site Transfer" section in
 * http://wiki.reserveamerica.com/display/qa/WEB+REGRESSION+LATEST+DETAILS+DRAFT%2C+please+change+this+title+date+when+you+UPDATE+this+document+Feb+06%2C+2012
 * @Task#:AUTO-805
 * 
 * @author SWang
 * @Date  April 6, 2012
 */
public class SiteTransfer_MD extends SiteTransferTestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.md.url");
		pw = TestProperty.getProperty("web.login.pw");
		email = web.getNextEmail();

		bd.contractCode = "MD";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + bd.contractCode;
		
		bd.conType = "State";
		bd.parkId = "380506";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"GREENBRIER STATE PARK";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";

		bd.siteNo = "07";
		bd.siteID = "4224";
		bd.loop = "BIRCH LOOP";
		siteType = "Standard";
		bd.typeOfUse = "OverNight";

		newBd.siteNo = "08";
		newBd.siteName = newBd.siteNo;
		newBd.siteID = "4225";
		newBd.loop = bd.loop;
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		newBd.typeOfUse = "OverNight";

		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
	}
}

