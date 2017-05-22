package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.campinglottery.lotterysearch.ra;

import java.util.Calendar;

import com.activenetwork.qa.awo.pages.web.ra.RAParkListPage;
import com.activenetwork.qa.awo.pages.web.ra.RASiteDetailsPage;
import com.activenetwork.qa.awo.pages.web.ra.RASiteListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CheckLotteryStatus  extends RATestCase {

	public void execute() {
		web.checkAndReleaseInventory(schema, bd.arrivalDate,Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		web.gotoParkList(bd);
		this.verifyLotteryStatus(false,true);//verify lottery status on park list page
		
		web.gotoSiteListFromParkListPg(bd.park, bd );
		this.verifyLotteryStatus(true,false);//verify lottery status on site list page
		
		web.searchSiteFromSiteListToSiteDetails(bd);// go to site details page from site list page
		this.verifyLotteryStatus(false, false);//verify lottery status in site details page
		
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema=TestProperty.getProperty(env + ".db.schema.prefix") + "NY";
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "New York";
		bd.park = "STONY BROOK STATE PARK";
		bd.conType = "State";
		bd.contractCode = "NY";

		bd.loop = "Sites 001-034";
		bd.siteNo = "019";
		bd.siteID = "248436";
		
		bd.arrivalDate = this.getArrivalDateForLottery();// set the arrival date for lottery inventory date
		bd.lengthOfStay = "3";
	}
	
	private String getArrivalDateForLottery(){		
			String startDate = web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop);
			String lotDate = DateFunctions.getDateAfterGivenDate(0, DateFunctions.getCalendarFromString(startDate), Calendar.MONDAY, "EEE MMM d yyyy");
			return lotDate;		
		}
	
	public void verifyLotteryStatus(boolean isSiteSearch, boolean isParkSearch) {
		RAParkListPage parkList = RAParkListPage.getInstance();
		RASiteListPage siteList = RASiteListPage.getInstance();
		RASiteDetailsPage campsiteDetailPg = RASiteDetailsPage.getInstance();
		
		if(isSiteSearch) {
			if(siteList.getSiteStaus(bd.siteNo).equals("Accepting Lottery")) {
				logger.info("---site status is right.");
			} else {
				throw new ErrorOnPageException("site status is wrong!");
			}
		} else if(isParkSearch) {
			if(parkList.getParkStatus(bd.park).equals("Accepting Lotteries")) {
				logger.info("---park status is right.");
			} else {
				throw new ErrorOnPageException("park status is wrong!");
			}
		} else {
			if(campsiteDetailPg.getDateAvailability(bd.arrivalDate).equals("L")) {
				logger.info("---site status is right in site details page.");
			} else {
				throw new ErrorOnPageException("site status is WRONG in site details page!");
			}
		}
	}
}
