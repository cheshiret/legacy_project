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
 * 2: enter a sample data in 'where' field , select 'Camping & Lodging" from the dropdown list.
 * 3: set a arrival date to a date where there is no available site (in order to get Next Available Date Button)
 * 4: set length of stay to "1" and then click search button.
 * 5: select the specific park from suggestion page, and then goto view as list(and view as map) page
 * 6: click the "Next Available Date" button ;
 * 7: verify the progress indicator with text(Searching...)) 
 * @Preconditions:
 * 1: this test case must run under RFT.
 * @SPEC:AUTO-780
 * @Task#: StoryAB - Add Progress bar for facility search and ajax call Test cases.
 * 
 * @author bzhang
 * @Date  Jan 10, 2012
 */
public class ClickNextAvailableDateIndicator extends RecgovTestCase {
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
		
		bd.whereTextValue = "POKEGAMA DAM CAMPGROUND";
		bd.contractCode = "NRSO";
		bd.parkId = "73347";
		bd.interestInValue = "Camping & Lodging";
		bd.lookFor = "Any type of site";
		//make sure the date is within maximum window but don't have inventory.
		bd.arrivalDate = DateFunctions.getDateAfterToday(300);
		bd.lengthOfStay = "1";
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
		listPg.clickNextAvailableDate(bds.contractCode, bds.parkId);
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		listPg.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
		
		//2: goto view as map page.
		web.gotoViewAsMapPage(bds);
		mapPg.clickMapPin(bds.contractCode, bds.parkId);
		mapPg.mapBubbleWidgetWaitExists();
		mapPg.clickNextAvailableDateInWidget();
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		mapPg.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
	}

}
