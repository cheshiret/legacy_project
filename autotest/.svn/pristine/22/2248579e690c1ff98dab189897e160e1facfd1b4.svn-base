package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:(Block by other activities search result summary)
		1. Enter sample data in 'Where' field, pick up a state from autocomplete list, select 'Any Recreation', click 'Search';
		2. Open another window, pick up the same state, select 'Camping & lodging', click Search';
		3. Open another window, pick up the same state, select 'Permits & Wilderness', click Search';
		4. Open another window, pick up the same state, select 'Tours & Tickets', click Search';
		5. Open another window, pick up the same state, select 'Other Activities', click Search';

		Expected Result: 
        Verify the search results summary with the summary in facility type filter, 
        1. It shows 'Camping & Day Use(21)' when search 'Any recreations' and
          'Search results 1-10 of 21' when search 'Camping & Lodging'; 
        2. it shows 'Permits(0)' when search 'Any recreations' and
          "There are no matching results based on the information you have provided. Please modify your search and try again." when search 'Permits & Wilderness'.
        3. The search results summary when search on 'Other Activities' (no any option checked) should match the summary in 'All(xx)' facility type filter when search on 'Any Recreations'

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 14, 2012
 */
public class FacilitySummary_DifferentSelection extends RecgovTestCase {
	private RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();

	public void execute(){
		web.invokeURL(url);

		//Make "Any Recreation" unified search 
		this.gotoViewAsListPage(searchData);
		List<String> facilityTypeFilters = viewAsList.getAllClickableSearchTypeFiltersDisplayText();

		searchData.interestInValue = "Camping & Lodging";
		String searchResultSummary = this.getSearchResultSummary();
		this.verifySearchResultSummary(facilityTypeFilters.get(0), searchResultSummary);

		searchData.interestInValue = "Permits & Wilderness";
		searchResultSummary = this.getSearchResultSummary();
		this.verifySearchResultSummary(facilityTypeFilters.get(1), searchResultSummary);

		searchData.interestInValue = "Tours & Tickets";
		searchResultSummary = this.getSearchResultSummary();
		this.verifySearchResultSummary(facilityTypeFilters.get(2), searchResultSummary);

		searchData.interestInValue = "Other Activities";
		searchResultSummary = this.getSearchResultSummary();
		this.verifySearchResultSummary(facilityTypeFilters.get(3), searchResultSummary);

	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "MINNESOTA";
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = searchData.whereTextValue;
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
	}

	/**
	 * Get search result summary from "View As List" page
	 * @return
	 */
	public String getSearchResultSummary(){
		web.makeUnifiedSearch(searchData);
		logger.info("Get search result label...");
		return viewAsList.getSearchResultsLabel();
	}

	/**
	 * Verify search result summary info between the value from different interest in section and facility type filter
	 * @param facilityTypeFromAny: Facility type filter info when do any recreation search
	 * @param searchResultSummaryFromDifferentSection: Search result summary from different interest in section
	 */
	public void verifySearchResultSummary(String facilityTypeFromAny, String searchResultSummaryFromDifferentSection){
		logger.info("Start to verify search result summary info for different interent in section.");
		//Get search result summary info from facility type filter
		String actualSearchResultSummaryFromDifferSection = viewAsList.getFirstPgSearchResultLabelViaFacilityTypeFilter(facilityTypeFromAny);

		//Verify search result summary info between facility type and different interest in section	
		if(!searchResultSummaryFromDifferentSection.equals(actualSearchResultSummaryFromDifferSection)){
			throw new ErrorOnDataException("Search Result summary is wrong for different section.", actualSearchResultSummaryFromDifferSection, searchResultSummaryFromDifferentSection);
		}
	}
}
