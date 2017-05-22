package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P)
		1. Enter sample data in 'Where' field, pick up a state from autocomplete list and click 'Search';
		2. Check facility type filter;
		3. Check facility name filter;
		4. Check the header above the top result;
		5. Verify no distance showing for results;
		6. Check the order for the results;
		7. Check the paging control

		Expected Result:
		-Verify facility type filter on the top;
		-Verify there is a facility name filter;
		-Verify the header above the top result - Results within <state full name>;
		-Verify there is no distance showing for results;
		-Sorted alphabetically when multiple results returned;
		-Verify paging control 'Previous', 'Next' working well

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 13, 2012
 */
public class UI_ViewAsList extends RecgovTestCase {
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private String searchResultHeader;

	public void execute(){
		web.invokeURL(url);
		this.checkStateHeadingFullNameAndSorting();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");

		searchData.whereTextValue = "MAINE";//"ma";
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = "MAINE";
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		searchResultHeader = "Results within "+searchData.selectedAutoCompletedOption;
	}

	/**
	 * Verify facility type, facility name, search result header, park distances, 
	 * park name sorting, next and previous functional in view as list page
	 */
	private void checkStateHeadingFullNameAndSorting(){
		RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
		logger.info("Verify associated info about State earch result in view as list page.");

//		web.makeUnifiedSearch(searchData);
		this.gotoViewAsListPage(searchData);

		//Verify facility type filter on the top;
		viewAsListPg.verifySearchTypeFilterExist(true);

		//Verify there is a facility name filter;
		viewAsListPg.verifySearchNameFilterExist(true);

		//Verify the header above the top result - Results within <state full name>;
		viewAsListPg.verifyResultNearHeaderText(searchResultHeader);

		//Verify there is no distance showing for results;
		viewAsListPg.verifyAllParkWithoutDistance();
		viewAsListPg.gotoFirstPage();

		//Sorted alphabetically(Case Insensitive) when multiple results returned;
		List<String> allFacilityNames = viewAsListPg.getAllParkNames();
		viewAsListPg.verifySortByAlphabetical(allFacilityNames, false);
		viewAsListPg.gotoFirstPage();

		//Verify paging control 'Previous', 'Next' working well
		viewAsListPg.clickNext();
		viewAsListPg.waitLoading();
		viewAsListPg.verifyFirstParkName(allFacilityNames.get(0), false);

		viewAsListPg.clickPrevious();
		viewAsListPg.waitLoading();
		viewAsListPg.verifyFirstParkName(allFacilityNames.get(0), true);
	}
}
