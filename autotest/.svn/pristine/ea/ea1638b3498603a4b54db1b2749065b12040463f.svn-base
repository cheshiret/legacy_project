package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.ajaxprocessbar;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchInterstitialPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: goto Search For Places page, make search with Sample data,it goes to suggestion page
 * 2: click a result on suggestion page, it goes to search result page.
 * 3: on list view and map view, click the facility filters (All, Camping, Tours, Permits, Other), check the progress indicator.
 * @Preconditions:
 * 1: there should be SearchSiteTypeFilters hyperlink on the top of the search result page.
 * @SPEC:AUTO-780
 * @Task#: StoryAB - Add Progress bar for facility search and ajax call Test cases.
 * 
 * @author bzhang
 * @Date  Jan 11, 2012
 */
public class ClickFacilityFilterIndicator extends RecgovTestCase {
	private String url,clearCacheUrl, searchText;
	
	public void execute() {
		web.invokeURL(clearCacheUrl); //clear cache in order to make progress bar will not be affected by cache.
		web.invokeURL(url);
		this.verifyProgressPageDisplay(bd, searchText);
	}

	public void wrapParameters(Object[] param) {
		clearCacheUrl = TestProperty.getProperty(env + ".web.recgov.clearCache.url");
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		
		bd.whereTextValue = "POKEGAMA DAM CAMPGROUND";
		bd.contractCode = "NRSO";
		bd.parkId = "73347";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.arrivalDate = DateFunctions.getDateAfterToday(300);

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
		List<String> filters = new ArrayList<String>();
		
		//1: goto view as list page.
		this.gotoViewAsListPage(bds);
		
		// get all facility filter hyperlinks.
		filters = listPg.getAllClickableSearchTypeFiltersDisplayText();
		if(null == filters || filters.size() <1){
			//make sure the search result page meets prerequisite.
			throw new ErrorOnPageException("There is no Search Type filters hyperlink, this test case can't continue any more");
		}
		
		//loop through each filters hyper link verify the progress indicator with given text.
		for(String filter: filters){
			this.gotoViewAsListPage(bds);
			logger.info("Click the site type filer of:" + filter);
			listPg.clickSearchTypeFilter(filter);
			//b/c the progress page will disappear very quickly, we don't need page wait exist here;
			listPg.inputEscKey();
			middlePage.waitLoading();
			middlePage.verifyInterstitialMessage(msg);
		}
		
		
		//2: goto view as map page.
		web.gotoViewAsMapPage(bds);
		// get all facility filter hyperlinks.
		filters = mapPg.getAllClickableSearchTypeFiltersDisplayText();
		if(null == filters || filters.size() <1){
			//make sure the search result page meets prerequisite.
			throw new ErrorOnPageException("There is no Search Type filters hyperlink, this test case can't continue any more");
		}
		
		//loop through each filters hyper link verify the progress indicator with given text.
		for(String filter: filters){
			web.gotoViewAsMapPage(bds);
			logger.info("click the site type filter of :" + filter);
			mapPg.clickSearchTypeFilter(filter);
			//b/c the progress page will disappear very quickly, we don't need page wait exist here;
			mapPg.inputEscKey();
			middlePage.waitLoading();
			middlePage.verifyInterstitialMessage(msg);
		}
	}

}
