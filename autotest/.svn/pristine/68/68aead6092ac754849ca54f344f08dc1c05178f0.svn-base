package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.testcases.abstractcases.web.SiteTransferTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: (P) NRSO contract
 * 1. Make reservation using site 02
 * 2. Go to "Reservation Details" page to check site 02 associated info(no, loop, arrival and departure)
 * 3. Click "Change Reservation" link to "Change Reservation Details'" page to check site 02 associated info(no, loop, type, arriving, length of stay)
 * 4. Click "Change Date/Transfer to Another Site" button to "Change Reservation" page to check site 03 associated info (no, loop and type)
 * 5. Click "Book These Dates" to "Change Reservation Details" page to check site 02 and site 03 associated info(no, loop, type, arriving, length of stay)
 * 6. Finish this order
 * 7. Go to "Reservation Details" page to check site 002 associated info(no, loop, arrival and departure)
 * 
 * @Preconditions: 
 * @SPEC: "Site Transfer" section in
 * http://wiki.reserveamerica.com/display/qa/WEB+REGRESSION+LATEST+DETAILS+DRAFT%2C+please+change+this+title+date+when+you+UPDATE+this+document+Feb+06%2C+2012
 * @Task#:AUTO-805
 * 
 * @author SWang
 * @Date  April 6, 2012
 */
public class SiteTransfer_NRSO extends SiteTransferTestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		bd.isUnifiedSearch = true;

		bd.contractCode = "NRSO";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		
		bd.parkId = "73456";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"TWIN POINTS";
		bd.conType = "Federal";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";
		bd.isRange = false;

		bd.siteNo = "02";
		bd.siteID = "124538";
		bd.loop = "TPOI";
		siteType = "STANDARD ELECTRIC";
		bd.typeOfUse = "OverNight";

		newBd.siteNo = "03";
		newBd.siteName = newBd.siteNo;
		newBd.siteID = "124539";
		newBd.loop = bd.loop;
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";
		newBd.typeOfUse = bd.typeOfUse;

		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};
	}
}
