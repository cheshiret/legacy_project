/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.ajaxprocessbar;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchInterstitialPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: goto Search For Places page, make search with Sample data,it goes to suggestion page
 * 2: click a result on suggestion page, it goes to search result page.
 * 3: on search result page(both list view and map view)  , click 'Check Availability' button in top result or nearby results on search results page , check the progress indicator.
 * @Preconditions:
 * 1: this test case must run under RFT.
 * @SPEC:AUTO-780
 * @Task#: StoryAB - Add Progress bar for facility search and ajax call Test cases.
 * 
 * @author bzhang
 * @Date  Jan 10, 2012
 */
public class ClickCheckAvailabilityIndicator extends RecgovTestCase {
	private String url,clearCacheUrl, searchText;
	
	public void execute() {
		web.invokeURL(clearCacheUrl); //clear cache in order to make progress bar will not be affected by cache.
		web.invokeURL(url);
		//1: goto Search For Places page, make search and check "Searching..." interestedly page will be displayed.
		this.verifyProgressPageDisplay(bd, searchText);
	}

	public void wrapParameters(Object[] param) {
		clearCacheUrl = TestProperty.getProperty(env + ".web.recgov.clearCache.url");
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		
		bd.whereTextValue = "NORTH DICKEY LA";
		bd.contractCode = "NRSO";
		bd.parkId = "73638";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		searchText = "Searching...";
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
		
		//1: goto view as list page.
		this.gotoViewAsListPage(bds);
		
		//click 'Check Availability' button in top result or nearby results on search results page , check the progress indicator.
		listPg.clickCheckAvailability(bds.parkId, bds.contractCode);
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		listPg.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
		
		//2: goto view as map page.
		web.gotoViewAsMapPage(bds);
		mapPg.clickMapPin(bds.contractCode, bds.parkId);
		mapPg.mapBubbleWidgetWaitExists();
		mapPg.clickCheckAvailabilityInWidget();
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		mapPg.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
	}

}
