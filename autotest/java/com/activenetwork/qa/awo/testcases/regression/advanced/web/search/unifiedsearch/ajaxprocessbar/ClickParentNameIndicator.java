package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.ajaxprocessbar;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchInterstitialPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
* NOTE: this test case is unstable due to Internet speed issue.
 * @Description:
 * 1: goto unified search page.
 * 2: enter a sample data in 'where' field , select" Everything" and then click search
 * 3: select the specific park from suggestion page, and then goto view as list(and view as map) page
 * 4: click the parent name hyperlink
 * 5: verify the progress indicator with text(Retrieving...)) 
 * @Preconditions:
 * 1: this test case must run under RFT.
 * @SPEC:AUTO-780
 * @Task#: StoryAB - Add Progress bar for facility search and ajax call Test cases.
 * 
 * @author bzhang
 * @Date  Jan 11, 2012
 */
public class ClickParentNameIndicator extends RecgovTestCase {
	private String url,clearCacheUrl, searchText;
	private BookingData[] bdata = new BookingData[3];
	public void execute() {
		web.invokeURL(clearCacheUrl); //clear cache in order to make progress bar will not be affected by cache.
		web.invokeURL(url);
		for(BookingData bookdata: bdata){
			this.verifyProgressPageDisplay(bookdata, searchText);
		}
	}

	public void wrapParameters(Object[] param) {
		clearCacheUrl = TestProperty.getProperty(env + ".web.recgov.clearCache.url");
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		
		//Camping
		bdata[0] = new BookingData();
		bdata[0].whereTextValue = "OVERLOOK PARK";
		bdata[0].interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bdata[0].isUnifiedSearch = this.isUnifiedSearch();
		bdata[0].contractCode = "NRSO";
		bdata[0].parkId = "73330";
		
		//Permits
		bdata[1] = new BookingData();
		bdata[1].whereTextValue = "CABLES ON HALF DOME";
		bdata[1].interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bdata[1].isUnifiedSearch = this.isUnifiedSearch();
		bdata[1].contractCode = "NRSO";
		bdata[1].parkId = "79064";
		
		//Tours
		bdata[2] = new BookingData();
		bdata[2].whereTextValue = "MAMMOTH CAVE NATIONAL PARK TOURS";
		bdata[2].interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bdata[2].isUnifiedSearch = this.isUnifiedSearch();
		bdata[2].contractCode = "NRSO";
		bdata[2].parkId = "77817";
		
		searchText = "Retrieving...";
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
		
		//click parent Name in top result or nearby results on search results page , check the progress indicator.
		listPg.clickParentParkName(bds.contractCode, bds.parkId);
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		listPg.inputEscKey();
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
		
		//2: goto view as map page.
		web.gotoViewAsMapPage(bds);
		mapPg.clickMapPin(bds.contractCode, bds.parkId);
		mapPg.mapBubbleWidgetWaitExists();
		mapPg.clickParentNameInWidget();
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		mapPg.inputEscKey();
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
	}

}
