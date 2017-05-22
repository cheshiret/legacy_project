package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.otheractivities;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 1. Don't close browser and don't click Search
	All inputs/selections shall be retained
 * 2. Don't close browser and click Search
    All inputs/selections shall be cleared
 * 3. Close browser
	All inputs/selections shall be cleared
 * @Preconditions:
 * @SPEC:
 * UWP Unified Search_FacilitySearch_UC (Search Form - Expansion of 'Other Activities')
 * UWP Unified Search_Search Form_UI (Search Form - Expansion of 'Other Activities')
 * @Task#: AUTO-766
 * 
 * @author SWang
 * @Date  Oct 14, 2011
 */
public class InputsSlectionsValidation extends WebTestCase{
	private UwpUnifiedSearchSuggestionPage searchSuggestion = UwpUnifiedSearchSuggestionPage.getInstance();
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private UwpUnifiedSearch otherActivities = new UwpUnifiedSearch();

	public void execute() {
		web.invokeURL(url);

		//Don't close browser and don't click Search
		//All inputs/selections shall be retained
		otherActivities.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
		otherActivities.otherActivitiesName = new String[]{"Auto Touring"};
		this.checkOtherActivitiesUI(false, false);

		//Don't close browser and click Search
		//All the selections on the expanding form of "Other activities"  shall be cleared
		otherActivities.otherActivitiesName = new String[]{};
		this.checkOtherActivitiesUI(false, true);

		//Close browser
		//All inputs/selections shall be cleared
		this.checkOtherActivitiesUI(true, true);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");

		//Unified Search 
		otherActivities.whereTextValue = "inde";
	}

	/**
	 * Check Activities UI with a series of actions
	 * @param closeBrowser ---true:Close browser
	 *                     ---false:Don't close browser
	 * @param clickSearchAfterSearch  --true: Click Search button after Search
	 *                                    --false: Don't click Search button after Search
	 */
	private void checkOtherActivitiesUI(boolean closeBrowser, boolean clickSearchAfterSearch){
		//Make Unified Search and wait for suggestion page
		web.makeUnifiedSearch(otherActivities);
//		this.checkSuggestionPg(); //Sara[20140717] Per Lisha, we will drop suggestion page for July 2014 Release

		//Select 'Any creations'->(Click Search)||Close browser->->Select 'Other activities'
		searchPanel.selectInterestIn(UwpUnifiedSearch.EVERYTHING_INSTERETED_IN);
		if(clickSearchAfterSearch){
			searchPanel.clickSearch();
			searchPanel.waitLoading();
		}
		if(closeBrowser){
			browser.closeAllBrowsers();
			web.invokeURL(url);
		}
		searchPanel.selectInterestIn(otherActivities.interestInValue);

		//Check Other activities UI
		if(closeBrowser){
			searchPanel.verifyInterestInitializedUI(otherActivities.interestInValue);
		}else{
			searchPanel.verifySearchCriteria(otherActivities);
		}
	}

	/**Check suggestion page*/
	private void checkSuggestionPg(){
		if(!searchSuggestion.exists()){
			throw new ErrorOnDataException("Unified Search Suggestion Page should be existed.");
		}
	}
}
