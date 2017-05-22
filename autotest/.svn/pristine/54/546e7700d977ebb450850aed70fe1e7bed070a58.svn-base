package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch.facilitynamefilters.viewasmap;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P RFT)
		1. Click each clickable letter in facility name filter;

		Expected Result:
	    1. Make sure the facilities starting with that clickable letter displaying alphabetically; 
	    make sure 'Search Results: x-x of xx' updated properly;

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 14, 2012
 */
public class ClickableNameFilter extends RecgovTestCase {
	private UwpUnifiedSearchViewAsMapPage viewAsMap = UwpUnifiedSearchViewAsMapPage.getInstance();
	private RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private List<String> clickableNameFilters = new ArrayList<String>();
	private int initialFirstParkId = -1;

	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(searchData);

		//Starting with that clickable letter displaying alphabetically
		clickableNameFilters = viewAsMap.getAllClickableSearchNameFiltersText();
		viewAsMap.verifySortByAlphabetical(clickableNameFilters);

		//Verify all park names staring with clickable facility name filter
		//Make sure 'Search Results: x-x of xx' updated properly;
		this.verifyUpdatedSearchResults();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "TEXAS";
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = searchData.whereTextValue;
		searchData.interestInValue = "Everything";

		searchData.contractCode = "NRSO";
	}

	/**
	 * Get park IDs and associated pin text which haven't the pin "No Map" from "View As List" page
	 */
	private List<String[]> getFirstPgParkIDsAndPinTextsHasMap(){
		List<String> firstPgParkIDs = new ArrayList<String>();
		List<String[]> firstPgParkIDsAndPinTextsHasMap = new ArrayList<String[]>();
		logger.info("Start to get first page park pin text which haven't the pin 'No Map' from 'View As List' page.");

		List<String> firstPgParkPinTitles = viewAsList.getFirstPgPinTitles();
		firstPgParkIDs= viewAsList.getFirstPgParkIDs();

		for(int i=0; i<firstPgParkPinTitles.size(); i++){
			if(firstPgParkPinTitles.get(i).equals("View on Map")){
				firstPgParkIDsAndPinTextsHasMap.add(new String[]{firstPgParkIDs.get(i), viewAsList.getMapPinText(searchData.contractCode, firstPgParkIDs.get(i))});
			}else{
				logger.info("Park pin title is 'Not on Map' for park with id:"+firstPgParkIDs.get(i));
			}
		}

		return firstPgParkIDsAndPinTextsHasMap;
	}

	/**
	 * Get park IDs which haven't the pin "No Map" from "View As List" page
	 * Start page: View As Map page
	 * End page: View As Map page
	 * @return
	 */
	private List<String[]> gotoViewAsListToGetParkIDsAndPinTexts(){
		logger.info("Start to get first page park pin text which haven't the pin 'No Map' from 'View As List' page.");
		viewAsMap.clickViewAsList();
		viewAsList.waitLoading();

		List<String[]> firstPgParkIDsAndPinTextsHasMap = this.getFirstPgParkIDsAndPinTextsHasMap();

		viewAsList.clickViewAsMap();
		viewAsMap.waitLoading();

		return firstPgParkIDsAndPinTextsHasMap;
	}


	private void verifyUpdateSearchResult(String expectedSearchResult, String actualSearchResult){
		logger.info("Start to verify search result is updated.");
		if(expectedSearchResult.equals(actualSearchResult)){
			throw new ErrorOnDataException("Search Result isn't updated.", expectedSearchResult, actualSearchResult);
		}else{
			logger.info("Successfully verify search result is updated.");
		}
	}

	/**
	 * Verify all park names staring with clickable facility name filter
	 * Verify 'Search Results: x-x of xx' updated properly
	 */
	public void verifyUpdatedSearchResults(){
		String searchResultBeforeClicking;
		String searchResultAfterClicking;

		logger.info("Start to get first park name and search result summary info: Search result label + first park name");
		searchResultBeforeClicking = viewAsMap.getSearchResultsLabel() +", "+ initialFirstParkId;

		for(int i=0; i<clickableNameFilters.size(); i++){
			//Click facility names
			viewAsMap.clickSearchNameFilter(clickableNameFilters.get(i));
			viewAsMap.waitLoading();

			//Goto view as list to get first page park IDs and pin texts
			List<String[]> firstPgParkIDAndPinTexts = this.gotoViewAsListToGetParkIDsAndPinTexts();

			//Verify all the pin texts exist in "View As Map", which comes from park IDs
			logger.info("Get all park pin texts exist after clicking search name filter-"+clickableNameFilters);
			for(int j=0; j<firstPgParkIDAndPinTexts.size(); j++){
				viewAsMap.checkMapPinExist(firstPgParkIDAndPinTexts.get(j)[1], searchData.contractCode, firstPgParkIDAndPinTexts.get(j)[0]);
			}
			initialFirstParkId = Integer.parseInt(firstPgParkIDAndPinTexts.get(0)[0]);

			//Verify search result is updated
			logger.info("Start to verify search result is updated after clicking te clickable filter-"+clickableNameFilters.get(i));
			searchResultAfterClicking = viewAsMap.getSearchResultsLabel() +", "+ initialFirstParkId;
			this.verifyUpdateSearchResult(searchResultBeforeClicking, searchResultAfterClicking);
		}
	}
}
