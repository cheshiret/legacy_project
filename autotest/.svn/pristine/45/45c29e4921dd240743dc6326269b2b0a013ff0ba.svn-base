package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.campinglottery.lotterysearch.rec;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * Note: the case is blocked by the defect #29149
 */
public class SubmitLotteryBeyondLotteryPeriod  extends RecgovTestCase {
	private RecgovSiteDetailsPage campsiteDetailPg = RecgovSiteDetailsPage.getInstance();
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
				".*Check that your dates are all within a lottery period.*",false,false);
		
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
		bd.siteNo = "2";
		
		bd.arrivalDate = DateFunctions.formatDate(web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop), "EEE MMM d yyyy"); //this.getArrivalDateForLottery();// set the arrival date for lottery inventory date
		bd.lengthOfStay = "3";
		
		bd.isUnifiedSearch=isUnifiedSearch();
	}
}
