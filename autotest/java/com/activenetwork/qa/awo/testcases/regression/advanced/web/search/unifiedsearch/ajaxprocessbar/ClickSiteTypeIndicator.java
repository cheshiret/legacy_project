package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.ajaxprocessbar;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchInterstitialPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: goto Search For Places page, make search with Sample data,it goes to suggestion page
 * 2: click a result on suggestion page, it goes to search result page.
 * 3: on View as List page, click the site type hyperlink or permit type hyperlink.
 * 4: verify after step3, there is a progress bar displayed with the specific text value.
 * @Preconditions:
 * 1: the selected park must have available site for the search date.
 * @SPEC:AUTO-780
 * @Task#: StoryAB - Add Progress bar for facility search and ajax call Test cases.
 * 
 * @author bzhang
 * @Date  Jan 10, 2012
 */
public class ClickSiteTypeIndicator extends RecgovTestCase {
	private String url,clearCacheUrl, searchText;
	private BookingData permitbd = new BookingData();
	
	public void execute() {
		web.invokeURL(clearCacheUrl); //clear cache in order to make progress bar will not be affected by cache.
		web.invokeURL(url);
		//click the site type hyperlink or permit type hyperlink. verify progress bar displayed.
		this.verifyProgressPageDisplay(bd, searchText, false);
		this.verifyProgressPageDisplay(permitbd, searchText, true);
	}

	public void wrapParameters(Object[] param) {
		clearCacheUrl = TestProperty.getProperty(env + ".web.recgov.clearCache.url");
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		
		//camping park info.
		bd.whereTextValue = "HIGHLAND RIDGE";
		bd.contractCode = "NRSO";
		bd.parkId = "73204";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.siteType = "STANDARD ELECTRIC";
		
		//permit park info.
		permitbd.whereTextValue = "CABLES ON HALF DOME";
		permitbd.contractCode = "NRSO";
		permitbd.parkId = "79064";
		permitbd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		permitbd.siteType = "STANDARD ELECTRIC";
		
		//progress bar displayed text info
		searchText = "Searching...";
	}
	
	/**
	 * Get the progress bar message on interstitial page
	 * @param searchCriteria
	 * @param msg
	 * @param isPermitPark
	 */
	private void verifyProgressPageDisplay(BookingData bds, String msg, boolean isPermitPark){
		UwpUnifiedSearchInterstitialPage middlePage = UwpUnifiedSearchInterstitialPage.getInstance();
		RecgovViewAsListPage listPg = RecgovViewAsListPage.getInstance();
		
		//1: goto view as list page.
		this.gotoViewAsListPage(bds);
		
		//click specific site type hyper link in top result or nearby results on search results page , check the progress indicator.
		if(!isPermitPark){
			listPg.clickSiteType(bds.parkId, bds.siteType);
		}else{
			//if the park is a permit park, then click the first permit type hyperlink
			listPg.clickFirstParkPermitTypes(0);
		}
		
		//b/c the progress page will disappear very quickly, we don't need page wait exist here;
		listPg.inputEscKey();
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
	}

}
