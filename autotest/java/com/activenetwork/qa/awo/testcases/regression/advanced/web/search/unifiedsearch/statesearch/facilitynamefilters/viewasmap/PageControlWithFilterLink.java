package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch.facilitynamefilters.viewasmap;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P RFT)
		Click on a letter link, verify the paging control;

		Expected Result:
	    1. 'Previous', 'Next' works well
		2. 'Search Results: x-x of xx' updated accordingly;

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 14, 2012
 */
public class PageControlWithFilterLink  extends RecgovTestCase {
	private UwpUnifiedSearchViewAsMapPage viewAsMap = UwpUnifiedSearchViewAsMapPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private String secondPageSearchResultSummary, facilityTypeAllFilter;

	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(searchData);

		//Get initial search result label and click facility name filter 
		this.FindFacilityNameFilterWithNext();
		facilityTypeAllFilter = viewAsMap.getSearchTypeAllFilterText();
		String initialSearchResultSummary = viewAsMap.getSearchResultsLabel();

		//After clicking "Next"
		this.actionPageControl(true);
		secondPageSearchResultSummary = viewAsMap.generateSearchResultLabelByFacilityTypeFilter(facilityTypeAllFilter, 2);
		viewAsMap.verifySearchResultLabel(secondPageSearchResultSummary);

		//After clicking "Previous"
		this.actionPageControl(false);
		viewAsMap.verifySearchResultLabel(initialSearchResultSummary);
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
	private void FindFacilityNameFilterWithNext(){
		boolean hasNextLink = false;

		logger.info("Find and click the facility name filter with Next link.");
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
		logger.info("Click "+(isNext?"Next":"Previous")+" link");
		if(isNext){
			viewAsMap.clickNext();
		}else{
			viewAsMap.clickPrevious();
		}

		viewAsMap.waitLoading();
	}
}
