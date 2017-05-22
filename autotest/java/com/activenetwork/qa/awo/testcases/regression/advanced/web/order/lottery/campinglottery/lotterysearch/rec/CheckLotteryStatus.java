package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.campinglottery.lotterysearch.rec;

import java.util.Calendar;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class CheckLotteryStatus extends RecgovTestCase {
	private String email, pw, url;

	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);
		web.gotoParkList(bd);
//		this.verifyLotteryStatus(false, true);//verify lottery status on park list page
		
		web.gotoSiteListFromParkListPg(bd.park, bd);
		this.verifyLotteryStatus(true, false);//verify lottery status on site list page

		web.searchSiteFromSiteListToSiteDetails(bd);// go to site details page from site list page
		this.verifyLotteryStatus(false, false);//verify lottery status in site details page
		
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "South Carolina";
		bd.park = "CLARKS HILL";
		bd.parkId = "71090";
		bd.conType = "Federal";

		bd.contractCode = "NRSO";
		bd.loop = "ELIJ";
		bd.siteNo = "3";

		od.numOccupant = "2";
		od.numVehicles = "1";
		od.equipType = "Tent";
		
		bd.arrivalDate = this.getArrivalDateForLottery();// set the arrival date for lottery inventory date
		bd.lengthOfStay = "3";
		
		bd.isUnifiedSearch=isUnifiedSearch();
	}
	
	private String getArrivalDateForLottery(){		
		String startDate = web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop);
		String lotDate = DateFunctions.getDateAfterGivenDate(0, DateFunctions.getCalendarFromString(startDate), Calendar.MONDAY, "EEE MMM d yyyy");
		return lotDate;		
	}
	
	public void verifyLotteryStatus(boolean isSiteSearch, boolean isParkSearch) {
		RecgovParkListPage parkList = RecgovParkListPage.getInstance();
		RecgovSiteListPage siteList = RecgovSiteListPage.getInstance();
		RecgovSiteDetailsPage campsiteDetailPg = RecgovSiteDetailsPage.getInstance();
		
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
