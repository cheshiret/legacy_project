package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.ajaxprocessbar;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchInterstitialPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
* NOTE: this test case is unstable due to Internet speed issue.
 * @Description:
 * 1: goto unified search page.
 * 2: enter a sample data in 'where' field , select "Everything" and then make search
 * 3: on search result page(View As List and View As Map Page), click Next/Previous button
 * 4: verify the progress indicator with text(Updating...)) 
 * @Preconditions:
 * @SPEC:AUTO-780
 * @Task#: StoryAB - Add Progress bar for facility search and ajax call Test cases.
 * 
 * @author bzhang
 * @Date  Jan 11, 2012
 */
public class ClickNextPreviousIndicator extends RecgovTestCase {
	private String url,clearCacheUrl, searchText;
	
	public void execute() {
		web.invokeURL(clearCacheUrl); //clear cache in order to make progress bar will not be affected by cache.
		web.invokeURL(url);
		this.verifyProgressPageDisplay(bd, searchText);
	}

	public void wrapParameters(Object[] param) {
		clearCacheUrl = TestProperty.getProperty(env + ".web.recgov.clearCache.url");
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "WASHINGTON";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		searchText = "Updating...";
	}
	
	/**
	 * Get the progress bar message on interstitial page
	 * @param searchCriteria
	 * @param msg
	 */
	private void verifyProgressPageDisplay(BookingData bds, String msg){
		UwpUnifiedSearchInterstitialPage middlePage = UwpUnifiedSearchInterstitialPage.getInstance();
		RecgovViewAsListPage listPg = RecgovViewAsListPage.getInstance();
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		UwpUnifiedSearchSuggestionPage suggestPg = UwpUnifiedSearchSuggestionPage.getInstance();
		
		//verify on View as List page
		web.makeUnifiedSearch(bds);
		suggestPg.clickStatesSuggestion(bds.whereTextValue);
		listPg.waitLoading();
		//1: click Next button verify the indicator
		listPg.clickNext();
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		listPg.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
		
		//2: click Previous button verify the indicator
		web.makeUnifiedSearch(bds);
		suggestPg.clickStatesSuggestion(bds.whereTextValue);
		listPg.waitLoading();
		
		listPg.clickNext();
		listPg.waitLoading();
		listPg.clickPrevious();
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		listPg.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
		
		//verify on View as Map page
		web.makeUnifiedSearch(bds);
		suggestPg.clickStatesSuggestion(bds.whereTextValue);
		listPg.waitLoading();
		listPg.clickViewAsMap();
		mapPg.waitLoading();
		
		//verify on View as List page
		//1: click Next button verify the indicator
		mapPg.clickNext();
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		mapPg.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
		
		//2: click Previous button verify the indicator
		web.makeUnifiedSearch(bds);
		suggestPg.clickStatesSuggestion(bds.whereTextValue);
		listPg.waitLoading();
		listPg.clickViewAsMap();
		mapPg.waitLoading();
		
		mapPg.clickNext();
		mapPg.waitLoading();
		mapPg.clickPrevious();
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		mapPg.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
	}
}
