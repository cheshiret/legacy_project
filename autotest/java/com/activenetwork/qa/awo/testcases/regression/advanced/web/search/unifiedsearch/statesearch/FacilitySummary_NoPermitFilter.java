package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:(P)
		1. Enter sample data in 'Where' field, pick up a state from autocomplete list, select 'Any Recreation', click 'Search';
		2. Open another window, pick up the same state, select 'Permits & Wilderness', click Search';

		Expected Result: 
        Verify the search results summary with the summary in facility type filter, 
        when search 'Any recreations', it shows 'Permits(0)' 
        when search 'Permits & Wilderness', it displays: There are no matching results based on the information you have provided. Please modify your search and try again." 

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 14, 2012
 */
public class FacilitySummary_NoPermitFilter extends RecgovTestCase {
	private RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private String noMatchingResult;

	public void execute(){
		web.invokeURL(url);

		//Make "Any Recreation" unified search to check permit type filter as "Permits(0)"
		this.gotoViewAsListPage(searchData);
		this.verifyZeroPermitType();

		//Verify no matching result for permit
		searchData.interestInValue = "Permits & Wilderness";
		web.makeUnifiedSearch(searchData);
		viewAsList.verifyWarningMes(noMatchingResult);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "IOWA";
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = searchData.whereTextValue;
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		noMatchingResult = "There are no matching results based on the information you have provided. Please modify your search and try again.";
	}

	/**
	 * Verify the permit type filter text is: Permits(0)
	 */
	private void verifyZeroPermitType(){
		logger.info("Start to verify permit type filter is Permits(0)");
		List<String> disabledFacilityTypeFilters = viewAsList.getAllDisabledSearchTypeFiltersDisplayText();
		String zeroPermitTypeFilter = "Permits(0)";
		if(!disabledFacilityTypeFilters.contains(zeroPermitTypeFilter)){
			throw new ErrorOnDataException("Permit type filter info is incorrect.", zeroPermitTypeFilter, disabledFacilityTypeFilters.toString());
		}
	}
}
