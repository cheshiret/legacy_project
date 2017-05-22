package com.activenetwork.qa.awo.testcases.regression.advanced.web.order.lottery.campinglottery.lotteryrules.rec;

import java.util.Calendar;

import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Note: bussiness rules only work when we set lottery attribute to "Site Name/Code For" level. it will not work if we select "Loop/Area for" ;
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author bzhang
 * @Date  Sep 21, 2011
 */
public class BusinessRules_BlockStay extends RecgovTestCase {

	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);
		web.bookParkToSiteListPg(bd); // search park
		web.searchSiteFromSiteListToSiteDetails(bd); // search site
		web.verifyBusinessRuleInSiteDetailsPg("Block stay", ".*must also stay.*");
		
		web.updateDateInSiteDetailPg(DateFunctions.getDateAfterGivenDay(bd.arrivalDate, 2), bd.lengthOfStay);
		web.fillInLotteryDetails(bd, od);
		web.abandonCart();
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
		bd.loop = "COVE";
		bd.siteNo = "03";//create block stay rule on FRI, SAT for this site
		
		od.numOccupant = "2";
		od.numVehicles = "1";
		od.equipType = "Tent";
		
		bd.arrivalDate =this.getArrivalDateForLottery();// set  the arrival date and start from Sat
		bd.lengthOfStay = "3";
		bd.isUnifiedSearch=isUnifiedSearch();
		}
	
	private String getArrivalDateForLottery(){		
			String startDate = web.queryLotteryInventoryStartDate(schema, bd.park, bd.loop);
			String lotDate = DateFunctions.getDateAfterGivenDate(0, DateFunctions.getCalendarFromString(startDate), Calendar.SATURDAY, "EEE MMM d yyyy");
			return lotDate;		
		}
}
