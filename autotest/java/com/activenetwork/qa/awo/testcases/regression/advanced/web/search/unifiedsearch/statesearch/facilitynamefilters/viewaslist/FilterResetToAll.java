package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch.facilitynamefilters.viewaslist;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P)
		Click on a letter link, click 'Next' or 'Previous', then click 'Search' again;

		Expected Result:
		1. the filter is reset to 'All'
		2. 'Search Results: x-x of xx' updated accordingly

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 14, 2012
 */
public class FilterResetToAll extends RecgovTestCase {
	private RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private String initialSearchResultSummary, initialFacilityNameFilters;

	public void execute(){
		web.invokeURL(url);
//		web.makeUnifiedSearch(searchData);
		this.gotoViewAsListPage(searchData);
		initialSearchResultSummary = viewAsList.getSearchResultsLabel();
		initialFacilityNameFilters = viewAsList.getAllClickableSearchNameFiltersText().toString();

		//After clicking "Next"
		this.findAFacilityNameFilterWithNext();
		this.clickNext();
		this.verifySearchResultSummaryAferClickingSearch();
		this.verifyAllFacilityNameFilterAferClickingSearch();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "TEXAS";
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = searchData.whereTextValue;
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
	}

	/**
	 * Click the first facility name filter
	 * Make sure it has Next link  
	 */
	private void findAFacilityNameFilterWithNext(){
		logger.info("Start to find the facility name filter with Next link.");
		boolean hasNextLink = false;

		List<String> facilityNamesFilters = viewAsList.getAllClickableSearchNameFiltersText();
		for(int i=0; i<facilityNamesFilters.size(); i++){
			viewAsList.clickSearchNameFilter(facilityNamesFilters.get(i));
			viewAsList.waitLoading();

			if(viewAsList.checkNext()){
				logger.info("Click facility name filter "+facilityNamesFilters.get(i));
				hasNextLink = true;
				break;
			}
		}

		if(!hasNextLink){
			throw new ErrorOnDataException("Can't find the pre-condition condition with Next Link.");
		}
	}

	/**
	 * Click "Next" link
	 */
	private void clickNext(){
		viewAsList.clickNext();
		viewAsList.waitLoading();
	}

	/**
	 * Verify Search result summary after clicking Search button
	 */
	private void verifySearchResultSummaryAferClickingSearch(){
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		logger.info("Start to verify Search result summary after clicking Search button.");
		searchPanel.clickSearch();
		viewAsList.waitLoading();

		viewAsList.verifySearchResultLabelEquals(initialSearchResultSummary);
	}

	/**
	 * Verify facility name filter reset to 'All' after clicking Search button
	 */
	private void verifyAllFacilityNameFilterAferClickingSearch(){
		logger.info("Start to verify facility name filter reset to 'All' after clicking Search button.");
		String allClickableFacilityNameFilters = viewAsList.getAllClickableSearchNameFiltersText().toString();

		if(!allClickableFacilityNameFilters.equals(initialFacilityNameFilters)){
			throw new ErrorOnDataException("The facility name filter doesn't reset to initial status.", initialFacilityNameFilters, allClickableFacilityNameFilters);
		}else{
			logger.info("Successfully verify facility name filter reset to initial status.");
		}
	}
}
