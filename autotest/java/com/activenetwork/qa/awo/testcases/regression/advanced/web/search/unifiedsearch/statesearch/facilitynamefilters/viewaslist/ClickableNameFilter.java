package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch.facilitynamefilters.viewaslist;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: (P)
		Click each clickable letter in facility name filter;

		Expected Result:
	    Make sure the facilities starting with that clickable letter displaying alphabetically; 
	    Make sure 'Search Results: x-x of xx' updated properly;

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 14, 2012
 */
public class ClickableNameFilter extends RecgovTestCase {
	private RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private List<String> clickableNameFilters = new ArrayList<String>();

	public void execute(){
		web.invokeURL(url);
//		web.makeUnifiedSearch(searchData);
		this.gotoViewAsListPage(searchData);

		//Starting with that clickable letter displaying alphabetically
		clickableNameFilters = viewAsList.getAllClickableSearchNameFiltersText();
		viewAsList.verifySortByAlphabetical(clickableNameFilters);

		//Verify all park names staring with clickable facility name filter
		//Make sure 'Search Results: x-x of xx' updated properly;
		this.verifyUpdatedSearchResults();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "TEXAS";
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = searchData.whereTextValue;
		searchData.interestInValue =UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
	}

	private void verifyAllFacilityNamesStartingWithClickableNameFilter(List<String> parkNames, String clickableNameFilter){
		logger.info("Start to verify all park names starting with the clickable letter-"+clickableNameFilter);
		for(int j=0; j<parkNames.size(); j++){
			if(!parkNames.get(j).startsWith(clickableNameFilter)){
				throw new ErrorOnDataException("The park name:"+parkNames.get(j)+" doesn't start with the clickable filter.", clickableNameFilter, parkNames.get(j));
			}else{
				logger.info("Successfully verify all park names starting with the clickable letter-"+clickableNameFilter);
			}
		}
	}

	private void verifyUpdateSearchResult(String expectedSearchResult, String actualSearchResult){
		logger.info("Start to verify search result is updated.");
		if(expectedSearchResult.equals(expectedSearchResult)){
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
		String firstParkName;
		String searchResultBeforeClicking;
		String searchResultAfterClicking;

		for(int i=0; i<clickableNameFilters.size(); i++){
			//Get search result summary info: Search result label + first park name
			firstParkName = viewAsList.getFirstParkName();
			searchResultBeforeClicking = viewAsList.getSearchResultsLabel() +", "+ firstParkName;

			//Get all park names after click search name filters
			viewAsList.clickSearchNameFilter(clickableNameFilters.get(i));
			viewAsList.waitLoading();
			List<String> parkNames = viewAsList.getFirstPgParkNames();
			firstParkName = parkNames.get(0);

			//Verify all park names starting with clickable search name filter
			this.verifyAllFacilityNamesStartingWithClickableNameFilter(parkNames, clickableNameFilters.get(i));

			//Verify search result is updated
			logger.info("Start to verify search result is updated after clicking te clickable filter-"+clickableNameFilters.get(i));
			searchResultAfterClicking = viewAsList.getSearchResultsLabel() +", "+ firstParkName;
			this.verifyUpdateSearchResult(searchResultBeforeClicking, searchResultAfterClicking);
		}
	}
}
