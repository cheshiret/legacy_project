package com.activenetwork.qa.awo.testcases.sanity.web;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class RA_MakeReservation_NC extends RATestCase {

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NC";
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "North Carolina";
		bd.parkId = "552903";
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"Jordan Lake State Rec Area";
		bd.conType = "State";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10,Calendar.MONDAY);
		bd.lengthOfStay = "2";
		bd.contractCode = "NC";

		bd.siteID = "3210";
		bd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, bd.siteID);
		bd.siteNo = DataBaseFunctions.getSiteNum(bd.siteID, schema);//A001

		newBd.siteID = "3210";
		newBd.siteNo = DataBaseFunctions.getSiteNum(newBd.siteID, schema);//A002
		newBd.siteName = DataBaseFunctions.getSiteName(newBd.siteID, schema);
		newBd.loop = DataBaseFunctions.getSiteLoopName(schema, bd.parkId, newBd.siteID);
		newBd.arrivalDate = bd.arrivalDate;
		newBd.lengthOfStay = "3";

		bd.siteIDs = new String[]{bd.siteID, newBd.siteID};

		signInMiddle = true;//middle sign in
		bd.clickSiteNum = true;//forced to click on site number link

		isSiteTransfer = MiscFunctions.isSiteTransfer("NC");
		isCancelAvail = !MiscFunctions.isNoCancel("NC"); 
		bd.isUpdateAble = !MiscFunctions.isNoChange("NC"); 
	}
}
