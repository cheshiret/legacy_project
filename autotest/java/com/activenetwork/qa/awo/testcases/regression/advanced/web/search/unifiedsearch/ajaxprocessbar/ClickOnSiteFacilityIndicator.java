package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.ajaxprocessbar;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchInterstitialPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: goto unified search page.
 * 2: enter a sample data in 'where' field , select "Everything" and then make search
 * 3: select the specific park from suggestion page, and then goto view as list page
 * 4: click one of the child facility (Associated Facility)
 * 5: verify the progress indicator with text(Retrieving...)) 
 * @Preconditions:
 * 1: the facility we selected in wrap parameter is a RIDB facility with child facilities info
 * @SPEC:AUTO-780
 * @Task#: StoryAB - Add Progress bar for facility search and ajax call Test cases.
 * 
 * @author bzhang
 * @Date  Jan 11, 2012
 */
public class ClickOnSiteFacilityIndicator extends RecgovTestCase {
	private String url,clearCacheUrl, searchText;
	
	public void execute() {
		web.invokeURL(clearCacheUrl); //clear cache in order to make progress bar will not be affected by cache.
		web.invokeURL(url);
		this.verifyProgressPageDisplay(bd, searchText);
	}

	public void wrapParameters(Object[] param) {
		clearCacheUrl = TestProperty.getProperty(env + ".web.recgov.clearCache.url");
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		
		bd.whereTextValue = "Boise National Forest";
		bd.contractCode = "NRSO";
		bd.parkId = "1023";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

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
		
		//1: goto view as list page.
		this.gotoViewAsListPage(bds);
		
		//click child facility in search results page , check the progress indicator.
		listPg.clickFirstAssociatedFacility();
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		listPg.inputEscKey();
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
	}


}
