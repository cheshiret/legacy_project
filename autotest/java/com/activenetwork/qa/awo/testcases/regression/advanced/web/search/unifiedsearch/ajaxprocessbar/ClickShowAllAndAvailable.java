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
 * 1: goto unified search page.
 * 2: setup search criteria using wrap parameter data and then click search button.
 * 3: select the specific park from suggestion page, and then goto view as list(and view as map) page
 * 4: click the hyperlink of "Show all" and "Show only availability"
 * 5: verify the progress indicator with text(Updating...)) 
 * @Preconditions:
 * 1: this test case must run under RFT.
 * @SPEC:AUTO-780
 * @Task#: StoryAB - Add Progress bar for facility search and ajax call Test cases.
 * 
 * @author bzhang
 * @Date  Jan 11, 2012
 */
public class ClickShowAllAndAvailable extends RecgovTestCase {
	private String url,clearCacheUrl, searchText;
	
	public void execute() {
		web.invokeURL(clearCacheUrl); //clear cache in order to make progress bar will not be affected by cache.
		web.invokeURL(url);
		this.verifyProgressPageDisplay(bd, searchText);
	}

	public void wrapParameters(Object[] param) {
		clearCacheUrl = TestProperty.getProperty(env + ".web.recgov.clearCache.url");
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		
		bd.whereTextValue = "SILVER SPRINGS";
		bd.contractCode = "NRSO";
		bd.parkId = "70607";
		bd.interestInValue = "Camping & Lodging";
		bd.lookFor = "Any type of site";
		bd.arrivalDate = DateFunctions.getDateAfterToday(4);
		bd.flexibleValue = "Not Flexible";
		bd.lengthOfStay = "1";

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
		
		//1: goto view as list page.
		this.gotoViewAsListPage(bds);
		
		//2: click show only availability hyper link verify the Progress indicator message
		listPg.clickShowOnlyAvailableSites();
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		listPg.inputEscKey();
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
		
		//3: click show only hyper link verify the progress indicator message
		this.gotoViewAsListPage(bds);
		listPg.clickShowOnlyAvailableSites();
		listPg.waitLoading();
		listPg.clickShowAllResults();
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		listPg.inputEscKey();
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
		
		//4: goto view as map page.
		web.gotoViewAsMapPage(bds);
		
		//5: click show only availability hyper link verify the Progress indicator message
		mapPg.clickShowOnlyAvailableSites();
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		mapPg.inputEscKey();
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
		
		//6: click show only hyper link verify the progress indicator message
		web.gotoViewAsMapPage(bds);
		mapPg.clickShowOnlyAvailableSites();
		mapPg.waitLoading();
		mapPg.clickShowAllResults();
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		mapPg.inputEscKey();
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
	}
}
