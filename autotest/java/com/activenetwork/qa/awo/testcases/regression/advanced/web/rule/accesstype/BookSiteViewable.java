package com.activenetwork.qa.awo.testcases.regression.advanced.web.rule.accesstype;

import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class BookSiteViewable extends WebTestCase {
	//private String ruleCondID = "120032111";
	private String ruleName = "Access Type";
	private String errorMsg = "The dates you have selected cannot be reserved on-line. Please select other dates or reserve by phone.";
	
	public void execute() {
		web.invokeURL(url);
		web.signIn(email, pw);
		/*
		 * make an advanced reservation and verify the rule validation message displayed at
		 * the site detail page in RA
		 */
		web.bookParkToSiteListPg(bd);
		web.bookSiteToOrderDetailPg(bd);
		web.verifyBusinessRuleEffectiveAtSiteDetailPage(ruleName, errorMsg, bd.siteNo);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		bd.state = "Kentucky";
		bd.park = "KENTUCKY HORSE PARK";
		bd.conType = "State";
		bd.contractCode = "KY";
		bd.arrivalDate =  DateFunctions.getDateAfterToday(15);
		bd.lengthOfStay = "3";
		
		bd.siteNo = "A046";
		bd.siteID="2034";
	}
}
