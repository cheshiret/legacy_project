package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch.facilitynamefilters.viewasmap;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: (P RFT)
		1. Enter sample data in 'Where' field, pick up a state from autocomplete list and click 'Search';
		2. Click on facility type filters;
		3. Select a facility type filter (not 'All'), then click on a clickable letter on facility name filter, click 'Previous' or 'Next' to browse the results;
		4. Click on a clickable letter on facility name filter, then click on facility type filter;
		5. Click 'Search' button again

		Expected Result:
		1. It shows facility type filters and facility name filter; 
		default selections are 'All' for both filters; 
		make sure the summary in facility type filter 
		and 'Search Results: x-xx of xx' matching;
		2. Make sure facility name filter will be reset 
		and  'Search Results: x-xx of xx' will be updated when click on facility type filters; 
		and always displaying from the first page when click on facility type fitlers;
		
//      //Handel in script: SearchResultMatchFacilityTypeFilters
//		make sure the search results matching the facility type filter selected 
//		3. Make sure no any change on facility type filter 
//		when click a letter on faiclity name filter;  
//		'Search Results: x-xx of xx' will be updated according to the letter selected on facility name filter; 
//		make sure the search results matching the selected facility type filter and selected letter on facility name filter;
		
		4. Make sure facility name filter will be reset 
		and  'Search Results: x-xx of xx' will be updated 
		and search results will displays from the first page when click on facility type filters;
		5. Make sure both of the facility type filter and facility name filter are reset to default status, s
		earch results are displaying from the first page

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 14, 2012
 */

public class NameFilterWithTypeFilter extends RecgovTestCase {
	private UwpUnifiedSearchViewAsMapPage viewAsMap = UwpUnifiedSearchViewAsMapPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private List<String> searchTypeFilters = new ArrayList<String>();
	private List<String> searchNameFilters = new ArrayList<String>();
	private String searchTypeFilterForAll;

	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(searchData);

		//Verify initial facility name, type and search result label
		this.verifyInitialNameTypeFilterAndSearchResultLabel();

		//Verify update facility name filter
		//Verify first page displays
		//Verify no change for facility name filter when clicking facility type filter
		this.verifyFacilityNameAndDisplaiedFirstPg();

		//Verify facility name, type and search result label after clicking Search button
		this.clickSearchToViewAsMapPage();
		this.verifyInitialNameTypeFilterAndSearchResultLabel();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "CALIFORNIA";//"cali";
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = "CALIFORNIA";
		searchData.interestInValue = "Everything";
	}

	/**
	 * Verify facility name filter doesn't start with ALL
	 * @param expectedFacilityNameFilter
	 * @param startFacilityNameFilter: ALL
	 */
	private void verifyFacilityNameFilter(String expectedFacilityNameFilter, String startFacilityNameFilter){
		if(expectedFacilityNameFilter.startsWith(startFacilityNameFilter)){
			throw new ErrorOnDataException("Search name filter should don't start with 'ALL' option.");
		}else{
			logger.info("Successfully verify search name filter starts with 'ALL' option.");
		}
	}

	/**
	 * Verify facility name filter doesn't start with ALL
	 * @param expectedFacilityTypeFilter
	 * @param startFacilityTypeFilter: all
	 */
	private void verifyFacilityTypeFilter(String expectedFacilityTypeFilter, String startFacilityTypeFilter){
		if(expectedFacilityTypeFilter.startsWith(startFacilityTypeFilter)){
			throw new ErrorOnDataException("Search type filter should don't start with 'All' option.");
		}else{
			logger.info("Successfully verify search type filter starts with 'All' option.");
		}
	}

	private void verifyInitialNameTypeFilterAndSearchResultLabel(){

		logger.info("Verify initial facility name filter is not start with ALL");
		searchNameFilters = viewAsMap.getAllClickableSearchNameFiltersText();
		this.verifyFacilityNameFilter(searchNameFilters.toString(), "ALL");

		logger.info("Verify initial facility type filter is not start with All");
		searchTypeFilters = viewAsMap.getAllClickableSearchTypeFiltersDisplayText();
		this.verifyFacilityTypeFilter(searchTypeFilters.toString(), "All");

		logger.info("Verify initial search result label");
		searchTypeFilterForAll =  viewAsMap.getSearchTypeAllFilterText();
		viewAsMap.generateFirstPgSearchResultLabelViaFacilityTypeFilter(searchTypeFilterForAll);
	}


	private void verifyFacilityNameFilterUpdated(String facilityTypeFilter){
		logger.info("Start to verify facility name update after clicking type filter:"+facilityTypeFilter);
		List<String> facilityNameFilterBeforeClickingType = viewAsMap.getAllClickableSearchNameFiltersText();

		viewAsMap.clickSearchTypeFilter(facilityTypeFilter);
		viewAsMap.waitLoading();

		String facilityNameFiltersAfterClickingType = viewAsMap.getAllClickableSearchNameFiltersText().toString();

		boolean facilityNameFilterMatch = facilityNameFilterBeforeClickingType.equals(facilityNameFiltersAfterClickingType);
		boolean searchResultLabelMatch = viewAsMap.generateFirstPgSearchResultLabelViaFacilityTypeFilter(facilityTypeFilter).equals(viewAsMap.getSearchResultsLabel());

		if(facilityNameFilterMatch && searchResultLabelMatch){
			throw new ErrorOnDataException("Facility name filter doesn't update after clicking type filter:"+facilityTypeFilter);
		}else{
			logger.info("Successfully verify facility name update after clicking type filter:"+facilityTypeFilter);
		}
	}
	/**
	 * Start to verify first page display:"Next" link & "Previous"  link
	 */
	private void verifyFirstPageDisplay(){
		logger.info("Start to verify \"Next\" link & \"Previous\"  link in first page display.");
		boolean previousStatusMatch = !viewAsMap.checkPrevious();
		boolean nextStatusMatch = (viewAsMap.getTotalSearchResultNum()>10 && viewAsMap.checkNext()) || (viewAsMap.getTotalSearchResultNum()<=10 && !viewAsMap.checkNext());
		if(!previousStatusMatch  || !nextStatusMatch){
			throw new ErrorOnDataException("Failly verify first page display:"+(!previousStatusMatch?"\"Previous\" link":"")+(!nextStatusMatch?"\"Next\" Link":""));
		}else{
			logger.info("Successfully  verify \"Next\" link & \"Previous\"  link in first page display.");
		}
	}

	private void verifyNoChangeForFaciltyNameAfterClickingType(String expectedFacilityName, String actualFacilityName){
		logger.info("Verify facility name filter text.");
		if(!expectedFacilityName.equals(actualFacilityName)){
			throw new ErrorOnDataException("Facility name filter is wrong.", expectedFacilityName, actualFacilityName);
		}else{
			logger.info("Successfully verify facility name filter text.");
		}
	}

	private void verifyFacilityNameAndDisplaiedFirstPg(){
		for(int i=0; i<searchTypeFilters.size(); i++){
			//Verify update facility name filter
			this.verifyFacilityNameFilterUpdated(searchTypeFilters.get(i));
			//Verify first page displays
			this.verifyFirstPageDisplay();

			//Verify no change for facility name filter when clicking facility type filter
			String facilityNameFiltersBeforeClicking = viewAsMap.getAllClickableSearchNameFiltersText().toString();
			for(int j=0; j<searchTypeFilters.size(); j++){
				viewAsMap.clickSearchTypeFilter(searchTypeFilters.get(j));
				viewAsMap.waitLoading();
				String facilityNameFiltersAfterClicking= viewAsMap.getAllClickableSearchTypeFiltersDisplayText().toString();

				this.verifyNoChangeForFaciltyNameAfterClickingType(facilityNameFiltersBeforeClicking, facilityNameFiltersAfterClicking);
			}
		}
	}
	/**
	 * Go to view as map page according to clicking Search button in search panel.
	 * From:viewAsMap
	 * TO:viewAsMap
	 */
	private void clickSearchToViewAsMapPage(){
		logger.info("Go to view as map page according to clicking Search button in search panel.");
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		searchPanel.clickSearch();
		viewAsMap.waitLoading();
	}
}
