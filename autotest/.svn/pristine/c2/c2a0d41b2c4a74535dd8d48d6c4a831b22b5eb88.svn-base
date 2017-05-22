package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.ajaxprocessbar;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchInterstitialPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: goto Search For Places page, make search with Sample data,it goes to suggestion page
 * 2: click a result on suggestion page, it goes to search result page.
 * 3: on search result page(both list view and map view) , click 'Book Sites' button in top result or nearby results on search results page , check the progress indicator.
 * @Preconditions:
 * 1: this test case must run under RFT.
 * 2: the selected park must have available site for the search date.
 * @SPEC:AUTO-780
 * @Task#: StoryAB - Add Progress bar for facility search and ajax call Test cases.
 * 
 * @author bzhang
 * @Date  Jan 10, 2012
 */
public class ClickBookSitesIndicator extends RecgovTestCase {
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
		
		bd.whereTextValue = "UPPER PINES";
		bd.contractCode = "NRSO";
		bd.parkId = "70925";
		bd.interestInValue = "Camping & Lodging";
		bd.lookFor = "Any type of site";
		bd.occupants = "2";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.flexibleValue = "Not Flexible";
		bd.lengthOfStay = "2";
		
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
		
		//click 'Book sites' button in top result or nearby results on search results page , check the progress indicator.
		listPg.clickBookSitesByParkID(Integer.parseInt(bds.parkId));
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		listPg.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
		
		
		//2: goto view as map page.
		web.gotoViewAsMapPage(bds);
		mapPg.clickMapPin(bds.contractCode, bds.parkId);
		mapPg.mapBubbleWidgetWaitExists();
		mapPg.clickBookSitesInWidget();
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		mapPg.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
	}
}
