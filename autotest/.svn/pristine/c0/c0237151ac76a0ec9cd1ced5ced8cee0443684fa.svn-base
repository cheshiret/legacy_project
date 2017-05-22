package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.campinglottery.lotterydetails.ra;

import com.activenetwork.qa.awo.pages.web.ra.RAParkListPage;
import com.activenetwork.qa.awo.pages.web.ra.RASiteDetailsPage;
import com.activenetwork.qa.awo.pages.web.ra.RASiteListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class LotteryWindowViolation extends RATestCase {
	private RASiteDetailsPage siteDetailsPg = RASiteDetailsPage.getInstance();
	
	public void execute() {
		web.invokeURL(url,false);
		web.signIn(email, pw);
		web.gotoParkList(bd);

//		this.verifyLotteryStatus(false,true);//verify lottery status on park list page
		
		web.gotoSiteListFromParkListPg(bd.park, bd);
		this.verifyLotteryStatus(true,false);//verify lottery status on site list page

		gotoSiteDetailFromSiteListPg(bd.siteNo);// go to site details page from site list page
		this.verifyLotteryStatus(false, false);//verify lottery status in site details page
		
		web.signOut();
	}

	private void gotoSiteDetailFromSiteListPg(String siteNum){
		RASiteListPage siteList = RASiteListPage.getInstance();
		
		siteList.clickSiteNum(siteNum);
		siteDetailsPg.waitLoading();
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "New York";
		bd.parkId = "261";
		bd.contractCode = "NY";
		schema = DataBaseFunctions.getSchemaName(bd.contractCode, env);
		bd.park = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"STONY BROOK STATE PARK";
		bd.isUnifiedSearch = MiscFunctions.isRAUnifiedSearchOpen();
		
		bd.conType = "State";
		bd.arrivalDate = web.getLotteryAvailability(schema, 261,"web");
		bd.lengthOfStay = "3";

//		bd.loop = "Loop One";
		bd.siteNo = "020";
	}
	
	public void verifyLotteryStatus(boolean isSiteSearch, boolean isParkSearch) {
		RAParkListPage parkList = RAParkListPage.getInstance();
		RASiteListPage siteList = RASiteListPage.getInstance();
		
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
			if(siteDetailsPg.getDateAvailability(bd.arrivalDate).equals("L")) {
				logger.info("---site status is right in site details page.");
			} else {
				throw new ErrorOnPageException("site status is WRONG in site details page!");
			}
		}
	}
}
