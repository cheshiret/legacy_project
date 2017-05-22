package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.headertabswitch;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSignInSignUpPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Note: pass on local
 * @Description:
  * @Description:
	Close all browser windows, open a new window
	Step 1. Land on Home page, enter a sample data in 'Where' field, click search;
	Step 2. Click 'Clear Search', switch to 'My Account' tab then switch to 'Home' tab;
	Step 3. Enter "abcd" in 'Where' field, select "Camping & Lodging", then "Tent", click 'Clear Search', switch to 'My Account' tab then switch to 'Home' tab; * @Preconditions:
	Sample data:
 	flor  - Suggestion page
 	Expect Results:
 	Step 1. It goes to search results page and no any tab is selected;
	Step 2&3. In search form on Home page, the previous sample data and criteria entered in step 1 are cleared.

 * @Preconditions:
 * @SPEC: Story X
 * @Task#:AUTO-797
 * 
 * @author bzhang
 * @Date  Nov 10, 2011
 */
public class RedirectToHomeAfterClearSearch_04 extends RecgovTestCase{
	private String suggestionMsg = "";
	private BookingData bd1 = new BookingData();
	private BookingData bd2 = new BookingData();
	public void execute(){
		web.invokeURL(url);
		web.makeUnifiedSearch(bd);
		this.verifySearchCriteriaSwitchBetweenPages();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		suggestionMsg = "We're unable to tell where you wanted to search. Did you mean any of the following?";
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "flor";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		
		//second search data info which used to setup search criteria but without search.
		bd1.isUnifiedSearch = bd.isUnifiedSearch;
		bd1.whereTextValue = "abcd";
		bd1.clickSearch = false;
		bd1.interestInValue = "Camping & Lodging";
		bd1.lookFor = "Tent";
		bd1.moreOptions = true;
		bd1.waterFront = true;
		bd1.arrivalDate = DateFunctions.getDateAfterToday(5);
		bd1.lengthOfStay = "2";
		bd1.accessibilityNeeds = true;
		bd1.petsAllowed = true;
		
		bd2.isUnifiedSearch = bd.isUnifiedSearch;
		bd2.whereTextValue = UwpUnifiedSearch.DEFAULT_WHEREVALUE;
		bd2.interestInValue = UwpUnifiedSearch.DEFAULT_INSTERETED_IN;
		
	}
	
	public void verifySearchCriteriaSwitchBetweenPages(){
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchSuggestionPage suggestPg = UwpUnifiedSearchSuggestionPage.getInstance();
		UwpSignInSignUpPage signInOrSignUpPg = UwpSignInSignUpPage.getInstance(); //Sara[12/3/2013] Update because of new sign in and sign up mode in rec.gov website
		
		//1: verify warning message on suggestion  page.
//		suggestPg.verifySuggestionMessage(suggestionMsg);
		
		//2: click 'clear search' switch to 'My Account' Tab, then switch to "home" Tab.
		//the previous sample data and criteria entered in are cleared.
		searchPanel.clickClearSearch();		
		headerBar.gotoMyReservationsAccount();
//		singInPg.waitLoading();		
		signInOrSignUpPg.waitLoading();
		web.gotoHomePage();		
		searchPanel.verifySearchCriteria(bd2);
		
		//3.1: setup search criteria on home page without click search button.
		web.makeUnifiedSearch(bd1);
		searchPanel.clickClearSearch();	
		//3.2: switch to "my account" page via header TAB		
		headerBar.gotoMyReservationsAccount();
//		singInPg.waitLoading();
		signInOrSignUpPg.waitLoading();
		//3.3: goto home page via header TAB
		web.gotoHomePage();		
		searchPanel.verifySearchCriteria(bd2);
	}

}
