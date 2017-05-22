package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.campinglottery.lotterysearch.ra;

import com.activenetwork.qa.awo.pages.web.ra.RASiteDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class SubmitLotteryBeyondLotteryPeriod  extends RATestCase {

	private RASiteDetailsPage campsiteDetailPg = RASiteDetailsPage.getInstance();
	public void execute() {
		//go to camping lottery site details page
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd);// search park
		web.searchSiteFromSiteListToSiteDetails(bd); // search site

		String[] lotteryWindow = campsiteDetailPg.getLotteryWindow();
		String startDate = DateFunctions.getDateAfterGivenDay(lotteryWindow[0], -1);
		String endDate = DateFunctions.getDateAfterGivenDay(lotteryWindow[1], -1);

		//verify Start Date before lottery inventory period
		web.updateDateInSiteDetailPg(startDate, bd.lengthOfStay);
		web.verifyBusinessRuleInSiteDetailsPg("Start date before lottery period",
				"Inventory is not available. Site cannot be booked.*",false,false);

		//verify End Date after lottery inventory period
		web.updateDateInSiteDetailPg(endDate, bd.lengthOfStay);
		web.verifyBusinessRuleInSiteDetailsPg("End date after lottery period",
				".*Check that your dates are all within a lottery period.*",true,false);

		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NY";
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd.state = "New York";
		bd.park = "STONY BROOK STATE PARK";
		bd.conType = "State";
		bd.contractCode = "NY";

		bd.loop = "Sites 001-034";
		bd.siteNo = "019";
		bd.arrivalDate = DateFunctions.formatDate(web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop), "EEE MMM d yyyy"); //this.getArrivalDateForLottery();// set the arrival date for lottery inventory date
		bd.lengthOfStay = "3";
	}
}
