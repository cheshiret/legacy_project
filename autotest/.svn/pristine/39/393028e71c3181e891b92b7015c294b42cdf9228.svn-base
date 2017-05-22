package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch.facilitynamefilters.viewasmap;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P RFT)
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
	private UwpUnifiedSearchViewAsMapPage viewAsMap = UwpUnifiedSearchViewAsMapPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private String initialSearchResultSummary, initialFacilityNameFilters;

	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(searchData);
		initialSearchResultSummary = viewAsMap.getSearchResultsLabel();
		initialFacilityNameFilters = viewAsMap.getAllClickableSearchNameFiltersText().toString();

		//After clicking "Previous"
		this.FindFacilityNameFilterWithNext();
		this.actionPageControl(true);
		this.actionPageControl(false);
		this.verifySearchResultSummaryAferClickingSearch();

		this.verifyAllFacilityNameFilterAferClickingSearch();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "TEXAS";
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = searchData.whereTextValue;
		searchData.interestInValue = "Everything";
	}

	/**
	 * Click the first facility name filter
	 * Make sure it has Next link  
	 */
	public void FindFacilityNameFilterWithNext(){
		boolean hasNextLink = false;

		List<String> facilityNamesFilters = viewAsMap.getAllClickableSearchNameFiltersText();
		for(int i=0; i<facilityNamesFilters.size(); i++){
			viewAsMap.clickSearchNameFilter(facilityNamesFilters.get(i));
			viewAsMap.waitLoading();

			if(viewAsMap.checkNext()){
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
	 * Click "Next" or "Previous" link
	 * @param isNext --true: Click "Next" link
	 *               --false: Click "Previous" link
	 */
	private void actionPageControl(boolean isNext){
		if(isNext){
			viewAsMap.clickNext();
		}else{
			viewAsMap.clickPrevious();
		}

		viewAsMap.waitLoading();
	}

	/**
	 * Verify Search result summary after clicking Search button
	 */
	private void verifySearchResultSummaryAferClickingSearch(){
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		logger.info("Start to verify Search result summary after clicking Search button.");
		searchPanel.clickSearch();
		viewAsMap.waitLoading();

		viewAsMap.verifySearchResultLabel(initialSearchResultSummary);
	}

	/**
	 * Verify facility name filter reset to 'All' after clicking Search button
	 */
	private void verifyAllFacilityNameFilterAferClickingSearch(){
		logger.info("Start to verify facility name filter reset to 'All' after clicking Search button.");
		String allClickableFacilityNameFilters = viewAsMap.getAllClickableSearchNameFiltersText().toString();

		if(!allClickableFacilityNameFilters.equals(initialFacilityNameFilters)){
			throw new ErrorOnDataException("The facility name filter doesn't reset to initial status.", initialFacilityNameFilters, allClickableFacilityNameFilters);
		}else{
			logger.info("Successfully verify facility name filter reset to initial status.");
		}
	}
}
