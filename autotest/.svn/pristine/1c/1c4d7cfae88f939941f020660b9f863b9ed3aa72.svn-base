package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.ajaxprocessbar;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchInterstitialPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 *	1: goto Search For Places page, make search and check "Searching..." interestedly page will be displayed.
 *  2: update the search criteria in step1 make search, and check "Searching..." interstitual page will be displayed.
 * @Preconditions:
 * @SPEC:AUTO-780
 * @Task#: StoryAB - Add Progress bar for facility search and ajax call Test cases.
 * 
 * @author bzhang
 * @Date  Jan 6, 2012
 */
public class InterstitualSearchingProgress extends RecgovTestCase {
	private String url,clearCacheUrl,searchText;
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private UwpUnifiedSearch searchData2 = new UwpUnifiedSearch();
	
	public void execute() {
		web.invokeURL(clearCacheUrl); //clear cache in order to make progress bar will not be affected by cache.
		web.invokeURL(url);
		//1: goto Search For Places page, make search and check "Searching..." interestedly page will be displayed.
		this.verifyProgressPageDisplay(searchData, searchText);
		//2: update the search criteria in step1 make search, and check "Searching..." interstitual page will be displayed.
		this.verifyProgressPageDisplay(searchData2, searchText);
	}

	public void wrapParameters(Object[] param) {
		clearCacheUrl = TestProperty.getProperty(env + ".web.recgov.clearCache.url");
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		
		searchData.whereTextValue = "NORTH POLE";
		searchData.interestInValue = "Day use & Picnic areas";
		searchData.occupants = "2";
		searchData.arrivalDate = DateFunctions.getDateAfterToday(3);
		searchData.flexibleValue = "Not Flexible";
		searchData.lengthOfStay = "2";
		
		searchData2.whereTextValue = "Puer";
		searchData2.interestInValue = "Day use & Picnic areas";
		searchData2.occupants = "2";
		searchData2.arrivalDate = DateFunctions.getDateAfterToday(3);
		searchData2.flexibleValue = "Not Flexible";
		searchData2.lengthOfStay = "2";
		searchText = "Searching...";
	}
	
	/**
	 * Get the progress bar message on interstitial page
	 * @param searchCriteria
	 * @param msg
	 */
	private void verifyProgressPageDisplay(UwpUnifiedSearch searchCriteria, String msg){
		UwpUnifiedSearchInterstitialPage middlePage = UwpUnifiedSearchInterstitialPage.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		
		searchPanel.setupUnifiedSearch(searchCriteria);
		searchPanel.clickSearch();
		searchPanel.inputEscKey();
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
	}

}
