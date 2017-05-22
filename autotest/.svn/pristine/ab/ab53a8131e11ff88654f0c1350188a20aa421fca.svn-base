package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.maximumwindow.ra;

import java.util.TimeZone;

import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookSiteBlockMonthBlockReleaseUnit_BlockReleaseDayOfWeekWithinMonth extends RATestCase {
	private String ruleName = "Maximum Window";
	private String theLastestArrivalDate;
	private TimeZone timeZone = null;
	
	/**
	 * Blocked By Defect-30876, which will be fixed in a future release
	 * The Maximum Window Type=block, Length=10, Unit=month, Block Release Length=1, Block Release Unit=month,
	 * Block Release Day Of Week, Block Release Day Of Week Within Month=1st
	 */
	public void execute() {
		web.checkAndReleaseInventory(schema, DateFunctions.getDateAfterToday(300), DateFunctions.getDateAfterToday(330), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		
		String today = DateFunctions.getToday(timeZone);
		String monthOfToday = today.split("/")[0];
		String yearOfToday = today.split("/")[2];		
		String releaseDay = "";		
		String firstTueInThisMonth = DateFunctions.getDateByDayOfWeekWithinMonth(yearOfToday, monthOfToday, 1, "Tuesday");
		
		if (DateFunctions.compareDates(DateFunctions.parseDateString(today),DateFunctions.parseDateString(firstTueInThisMonth)) == -1){
			//get the last month release day
			if(Integer.parseInt(monthOfToday) == 1) {
				releaseDay = DateFunctions.getDateByDayOfWeekWithinMonth(String.valueOf(Integer.parseInt(yearOfToday) - 1), String.valueOf(12), 1, "Tuesday");
			} else {
				releaseDay = DateFunctions.getDateByDayOfWeekWithinMonth(yearOfToday, String.valueOf(Integer.parseInt(monthOfToday) - 1), 1, "Tuesday");
			}			
		} else {
			//get this month release day
			releaseDay = DateFunctions.getDateByDayOfWeekWithinMonth(yearOfToday, monthOfToday, 1, "Tuesday");
		}
		
		theLastestArrivalDate = DateFunctions.getDateAfterGivenMonth(releaseDay, 11);
		bd.arrivalDate = DateFunctions.getDateAfterGivenDay(theLastestArrivalDate, -2);
		bd.lengthOfStay = "2";
		
		web.bookParkToSiteListPg(bd);
		web.searchSiteFromSiteListToSiteDetails(bd);
		
		/*
		 * Verify the days beyond the maximum window are UNAVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, DateFunctions.getDateAfterGivenDay(theLastestArrivalDate, 1), 3, "unavailable");
		
		/*
		 * Verify the days within the maximum window are AVAILABLE
		 */
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, bd.arrivalDate, 2, "available");
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "KY";
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "KY";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		bd.state = "Kentucky";
		bd.park = "CARTER CAVES STATE RESORT PARK";
		bd.conType = "State";
		bd.contractCode = "KY";
		
		bd.clickSiteNum = true;
		bd.siteNo = "021";
		bd.siteID="1562";
	}
}
