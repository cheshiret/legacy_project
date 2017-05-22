package com.activenetwork.qa.awo.testcases.regression.basic.web.lam;

import com.activenetwork.qa.awo.testcases.abstractcases.LAMTestCase;

/**
 * Note: blocked by DEFECT-30435 for now.
 * 
 * @Description: create new list (local attractions) on LAM website, verify the google map displayed on step2 and step4 page.
 * @Preconditions: register a new user in LAM(qa_automation@reserveamerica.com; orms1234)
 * @SPEC:
 * @Task#: AUTO-621(DEFECT-29213)
 * 
 * @author bzhang
 * @Date  Jun 20, 2011
 */
public class VerifyGoogleMapDisplayOnCreateAttractionProcess extends LAMTestCase {
	String errorMsg = "";

	public void execute() {
		lam.invokeURL(url);
		lam.login(login.userName, login.password);
		
		lam.gotoCreateNewListStep1Page();
		lam.setAttractionInfoOnStep1Page(info);
		lam.gotoCreateNewListStep2Page();
		lam.verifyGoogleMapDisplayOnCreateNewListPages();
		lam.gotoCreateNewListStep3Page();
		lam.setAttractionInfoOnStep3Page(info);
		lam.gotoCreateNewListStep4Page();
		lam.verifyGoogleMapDisplayOnCreateNewListPages();
		lam.gotoMakePaymentPage();
		lam.verifyCreateNewListSuccessful();		
		
		lam.logout();
	}

	@Override
	public void wrapParameters(Object[] param) {
	  	info.attractionName = "High Point Golf Club";
	  	info.category ="Sports and Recreations";
	  	info.phone = "9732933282";
	  	info.streetName = "2480 Meadowvale Blvd";
	  	info.city ="Mississauga";
	  	info.state ="Ontario";
	  	info.zip = "L5N 8M6";
	  	info.slogan = "QA AUTO TESTING SLOGAN";
	  	info.description = "QA AUTO TESTING DESCRIPTION FOR CREATE ATTRACTION INFO";
	  	info.acceptSpellingAsIs = true;

   	  	info.linkDescription ="Tee Times at "+info.attractionName;
	  	info.linkAddress ="www.activegolf.com/TeeTimes/golf-courses/High-Point-Golf-Club_Montague_NJ.aspx";
	}
}
