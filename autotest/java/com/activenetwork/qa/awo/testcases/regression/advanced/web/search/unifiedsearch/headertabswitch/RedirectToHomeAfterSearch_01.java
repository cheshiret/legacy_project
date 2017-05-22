package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.headertabswitch;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.UIOptions;
import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSignInSignUpPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchHelpInfoPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Note: pass on local
 * @Description:
 * Close all browser windows, open a new window
	Step 1. Land on Home page, enter a sample data in 'Where' field, click search;
	Step 2. Enter "abcd" in 'Where' field, select some options in search form, switch to 'My Account' tab then switch to 'Home' tab;
	Step 3. Enter "abcd" in 'Where' field, select some options in search form,switch to 'My Account' tab then switch to 'Home' tab
	
	Sample data:
	**68   -  validation error message
 * @Preconditions:
 * @SPEC: Story X
 * @Task#:AUTO-797
 * 
 * @author bzhang
 * @Date  Nov 10, 2011
 */
public class RedirectToHomeAfterSearch_01 extends RecgovTestCase{
	private String errorMsg = "";
	private BookingData bd1 = new BookingData();
	public void execute(){
		web.invokeURL(url);
		web.makeUnifiedSearch(bd);
		this.verifySearchCriteriaSwitchBetweenPages();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		errorMsg = "Where did you want to go? Enter at least 4 letters and you'll start seeing facilities and addresses that match your input.";
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		String threshold = WebConfiguration.getInstance().getUIOption(Conf.rec_UIOptions, UIOptions.Threshold).split(",")[1];
		bd.whereTextValue = "**68".substring(0, Integer.valueOf(threshold)-1);
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		
		//second search data info which used to setup search criteria but without search.
		bd1.isUnifiedSearch = bd.isUnifiedSearch;
		bd1.whereTextValue = "abcd";
		bd1.clickSearch = false;
		bd1.interestInValue = "Camping & Lodging";
		bd1.lookFor = "RV sites";
		bd1.length = "30";
		bd1.moreOptions = true;
		bd1.arrivalDate = DateFunctions.getDateAfterToday(5);
		bd1.lengthOfStay = "2";
		bd1.accessibilityNeeds = true;
		bd1.petsAllowed = true;
	}
	
	public void verifySearchCriteriaSwitchBetweenPages(){
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchHelpInfoPage helpPage=UwpUnifiedSearchHelpInfoPage.getInstance();
		UwpSignInSignUpPage signInOrSignUpPg = UwpSignInSignUpPage.getInstance(); //Sara[12/3/2013] Update because of new sign in and sign up mode in rec.gov website
		
		//1: verify warning message on help page.
		helpPage.verifyErrorMes(errorMsg);
		
		//2.1: setup search criteria on help page without click search button.
		web.makeUnifiedSearch(bd1);		
		//2.2: switch to "my account" page via header TAB		
		headerBar.gotoMyReservationsAccount();
//		singInPg.waitLoading();		
		signInOrSignUpPg.waitLoading();
		//2.3: goto home page via header TAB
		web.gotoHomePage();		
		searchPanel.verifySearchCriteria(bd);
		
		//3.1: setup search criteria on home page without click search button.
		web.makeUnifiedSearch(bd1);
		//3.2: switch to "my account" page via header TAB		
		headerBar.gotoMyReservationsAccount();
//		singInPg.waitLoading();
		signInOrSignUpPg.waitLoading();
		//3.3: goto home page via header TAB
		web.gotoHomePage();		
		searchPanel.verifySearchCriteria(bd);
	}
}
