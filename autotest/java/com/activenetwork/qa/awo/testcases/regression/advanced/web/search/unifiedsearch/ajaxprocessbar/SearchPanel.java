package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.ajaxprocessbar;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchInterstitialPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * check the progress indicator for auto completion progress indicator for auto completion.
 * @Preconditions:
 * @SPEC: AUTO-780
 * @Task#: StoryAb - Add Progress bar for facility search and ajax call Test cases.
 * 
 * @author bzhang
 * @Date  Oct 24, 2011
 */
public class SearchPanel extends RecgovTestCase {
	private String url,clearCacheUrl,searchText;
	private String[] whereValue = {"(space)pine","zilpo - daniel"};
	private UwpUnifiedSearch dayUse = new UwpUnifiedSearch();
	public void execute() {
		web.invokeURL(clearCacheUrl); //clear cache in order to make progress bar will not be affected by cache.
		web.invokeURL(url);
		//verify progress indicator will show on search panel. can't automate the boundary testing Lisha mentioned in the StoryAB test case.
		this.verifySeachPanelProgressIndicatorDisplay(whereValue);
		this.verifyProgressPageDisplay(dayUse, searchText);
	}

	public void wrapParameters(Object[] param) {
		clearCacheUrl = TestProperty.getProperty(env + ".web.recgov.clearCache.url");
		url = TestProperty.getProperty(env + ".web.recgov.url");
		
		dayUse.whereTextValue = "NORTH POLE";
		dayUse.interestInValue = "Day use & Picnic areas";
		dayUse.clickSearch = false;
		dayUse.occupants = "2";
		dayUse.arrivalDate = DateFunctions.getDateAfterToday(3);
		dayUse.lengthOfStay = "2";
		
		searchText = "Searching...";
	}
	
	/**
	 * verify the progress indicator bar will display for search panel.
	 * @param wheres
	 */
	private void verifySeachPanelProgressIndicatorDisplay(String[] wheres){
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		for(String whereValue: wheres){
			searchPanel.clickClearSearch();
			searchPanel.verifyProgressBarDisplay(whereValue);
		}		
	}
	
	/**
	 * Get the progress bar message on interstitial page
	 * @param searchCriteria
	 * @param msg
	 */
	private void verifyProgressPageDisplay(UwpUnifiedSearch searchCriteria, String msg){
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchInterstitialPage middlePage = UwpUnifiedSearchInterstitialPage.getInstance();
		
		searchPanel.setupUnifiedSearch(searchCriteria);
		searchPanel.clickSearch();
		searchPanel.inputEscKey(); //click ESC as soon as we clicked the Facility name on suggestion page.
		middlePage.waitLoading();
		middlePage.verifyInterstitialMessage(msg);
	}

}
