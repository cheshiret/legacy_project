package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.ajaxprocessbar;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchInterstitialPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: goto Search For Places page, make search with Sample data,it goes to suggestion page
 * 2: click a result on suggestion page, check the  progress indicator displayed with text "Searching...";
 * @Preconditions:
 * @SPEC:AUTO-780
 * @Task#: StoryAB - Add Progress bar for facility search and ajax call Test cases.
 * 
 * @author bzhang
 * @Date  Jan 6, 2012
 */
public class BetweenSuggestionAndResult extends RecgovTestCase {
	private String url,clearCacheUrl, searchText;
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	
	public void execute() {
		web.invokeURL(clearCacheUrl); //clear cache in order to make progress bar will not be affected by cache.
		web.invokeURL(url);
		//1: goto Search For Places page, make search and check "Searching..." interestedly page will be displayed.
		this.verifyProgressPageDisplay(searchData, searchText);
	}

	public void wrapParameters(Object[] param) {
		clearCacheUrl = TestProperty.getProperty(env + ".web.recgov.clearCache.url");
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		
		searchData.whereTextValue = "NORTH DICKEY LA";
		searchData.contractCode = "NRSO";
		searchData.parkId = "73638";
		searchData.interestInValue = "Day use & Picnic areas";
		searchData.occupants = "2";
		searchData.arrivalDate = DateFunctions.getDateAfterToday(3);
		searchData.flexibleValue = "Not Flexible";
		searchData.lengthOfStay = "2";
		
		searchText = "Searching...";
	}
	
	/**
	 * Get the progress bar message on interstitial page
	 * @param searchCriteria
	 * @param msg
	 */
	private void verifyProgressPageDisplay(UwpUnifiedSearch searchCriteria, String msg){
		UwpUnifiedSearchInterstitialPage middlePage = UwpUnifiedSearchInterstitialPage.getInstance();
		UwpUnifiedSearchSuggestionPage suggestPg = UwpUnifiedSearchSuggestionPage.getInstance();
		
		web.makeUnifiedSearch(searchCriteria);
		suggestPg.clickFacilitySuggestion(searchCriteria.parkId , searchCriteria.contractCode);
		suggestPg.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
		
	}

}
