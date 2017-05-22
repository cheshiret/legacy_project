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
 * 2: enter a sample data in 'where' field , select "Everything" and then click search
 * 3: select the specific park from suggestion page, and then goto view as list(and view as map) page
 * 4: click the facility name hyperlink
 * 5: verify the progress indicator with text(Retrieving...)) 
 * @Preconditions:
 * 1: this test case must run under RFT.
 * @SPEC:AUTO-780
 * @Task#: StoryAB - Add Progress bar for facility search and ajax call Test cases.
 * 
 * @author bzhang
 * @Date  Jan 10, 2012
 */
public class ClickFacilityNameIndicator extends RecgovTestCase {
	private String url,clearCacheUrl, searchText;
	private BookingData[] bdata = new BookingData[4];
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
		
		//RIDB Facility
		bdata[0] = new BookingData();
		bdata[0].whereTextValue = "BLUE MARSH LAKE";
		bdata[0].interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bdata[0].isUnifiedSearch = this.isUnifiedSearch();
		bdata[0].contractCode = "NRSO";
		bdata[0].parkId = "195";
		
		//Camping
		bdata[1] = new BookingData();
		bdata[1].whereTextValue = "HAGER MOUNTAIN LOOKOUT";
		bdata[1].interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bdata[1].isUnifiedSearch = this.isUnifiedSearch();
		bdata[1].contractCode = "NRSO";
		bdata[1].parkId = "75027";
		
		//Permits
		bdata[2] = new BookingData();
		bdata[2].whereTextValue = "ENCHANTMENT PERMIT AREA";
		bdata[2].interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bdata[2].isUnifiedSearch = this.isUnifiedSearch();
		bdata[2].contractCode = "NRSO";
		bdata[2].parkId = "72280";
		
		//Tours
		bdata[3] = new BookingData();
		bdata[3].whereTextValue = "ROOSEVELT-VANDERBILT NATIONAL HISTORIC SITES";
		bdata[3].interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bdata[3].isUnifiedSearch = this.isUnifiedSearch();
		bdata[3].contractCode = "NRSO";
		bdata[3].parkId = "77814";
		
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
		
		//click Facility Name in top result or nearby results on search results page , check the progress indicator.
		listPg.clickParkName(bds.whereTextValue);
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		listPg.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
		
		//2: goto view as map page.
		web.gotoViewAsMapPage(bds);
		mapPg.clickMapPin(bds.contractCode, bds.parkId);
		mapPg.mapBubbleWidgetWaitExists();
		mapPg.clickParkNameInWidget(bds.whereTextValue);
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		mapPg.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
	}

}
