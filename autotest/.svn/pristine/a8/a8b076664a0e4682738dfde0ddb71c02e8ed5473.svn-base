package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.agency;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchHelpInfoPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P (RFT)
 * No result filter displays
 * 1. Suggestion page without warning/error mseeage
 * 2. Suggestion page with error message
 * 3. Help info page with 'Length of stay must be a number between 1 and 365' error message
 * 4. Help info page with 'unable to find a location' error message
 * 5. View as list with no matching results warning message
 * 6. view as list with no matching results warning message
 * @Preconditions: 
 * @SPEC: Agency filter - Search flow - No filters showing [TC:043134] 
 * @Task#: AUTO-1230 
 * 
 * @author SWang
 * @Date  Sep 25, 2012
 */
public class NoFilterShowingSearchFlow extends RecgovTestCase{
	private UwpUnifiedSearchSuggestionPage suggestionPg = UwpUnifiedSearchSuggestionPage.getInstance();
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearchHelpInfoPage helpPage=UwpUnifiedSearchHelpInfoPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();

	public void execute() {
		//No result filter displays
		web.invokeURL(url);

		//1. Suggestion page
		web.makeUnifiedSearch(searchData);
		suggestionPg.verifyNoResultFilter();

		//2. Help info page with 'Length of stay must be a number between 1 and 365' error message
		searchData.whereTextValue = "Lower Pines";
		searchData.interestInValue = UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN;
		searchData.arrivalDate = DateFunctions.getDateAfterToday(0);
		web.makeUnifiedSearch(searchData);
		helpPage.verifyErrorMsgExist(true);
		helpPage.verifyNoResultFilter();

		//3. Help info page with 'unable to find a location' error message
		searchData.whereTextValue = "zzzzzzzzzz";
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		searchData.arrivalDate = "";
		web.makeUnifiedSearch(searchData);
		suggestionPg.verifyErrorMsgExist(true);
		suggestionPg.verifyNoResultFilter();

		//4. View as list with no matching results warning message
		searchData.whereTextValue = "NORTH POLE, AK 99705, USA";
		searchData.interestInValue = UwpUnifiedSearch.DAYUSEPICNICAREAS_INSTERETED_IN;
//		searchData.selectAutoCompleteOption = true;
		searchData.selectedAutoCompletedOption = searchData.whereTextValue;
		web.makeUnifiedSearch(searchData);
		viewAsListPg.verifyWarningMesExist(true);
		viewAsListPg.verifyResultFiltersDisplaying(false);

//		web.invokeURL(url);

		//5. Explore search, suggestion page with 'unable to find a location' error message
		searchData.whereTextValue = "zzzzzzzzzz";
		web.gotoExploreActivityFromHeaderBare(UwpUnifiedSearch.EXPLORE_WINTERSPORTS);
		web.findNearByLocationOrZipCode(searchData.whereTextValue, false, suggestionPg);
		suggestionPg.verifyErrorMsgExist(true);
		suggestionPg.verifyNoResultFilter();

		//6. Explore search, view as list with no matching results warning message
		searchData.whereTextValue = "HAWAII";
		web.gotoExploreActivityFromHeaderBare(UwpUnifiedSearch.EXPLORE_WINTERSPORTS);
		web.findNearByLocationOrZipCode(searchData.whereTextValue, true, viewAsListPg);
		viewAsListPg.verifyWarningMesExist(true);
		viewAsListPg.verifyResultFiltersDisplaying(false);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");

		searchData.whereTextValue = "lake";
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
	}
}
