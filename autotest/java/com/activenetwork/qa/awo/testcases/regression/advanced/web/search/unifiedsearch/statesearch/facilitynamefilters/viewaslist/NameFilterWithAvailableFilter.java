package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch.facilitynamefilters.viewaslist;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P)
		1. Enter "wash" in 'Where' field, pick up "Washington"  from autocomplete list, 
		select 'Camping & Lodging', enter current date in 'Arrival date', 'Not Flexible', Length of stay=2, click 'Search';
		2. Click on 'Show only available sites';
		3. Check paging control;
		4. Click on each clickable letter on facility name filter;

		Expected Result:
		1. It shows availability filters and facility name filter with default selection; 
		2. Make sure no change on facility name filter; make sure all available results displaying alphabetically; 'Search Results: x-xxof xx' updated accordingly;
		3. Make sure 'Previous', 'Next' working well
		4. Make sure  the availability filter be reset and re-evaluated based on the filtered site list (the availability filter will display when top 10 results not available; it shows "There are no available sites within the results" when no any site available;)

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 15, 2012
 */
public class NameFilterWithAvailableFilter extends RecgovTestCase {
	private RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private List<String> inicialFacilityNameFilters = new ArrayList<String>();
	private String noAvailableSitesWarnMes, showAllResultsSearchResultLabel;

	public void execute(){
		web.invokeURL(url);

		//Make state search
		this.gotoViewAsListPage(searchData);
		inicialFacilityNameFilters = viewAsList.getAllClickableSearchNameFiltersText();
		showAllResultsSearchResultLabel = viewAsList.getSearchResultsLabel();

		this.clickShowOnlyAvailableSites();

		//Verify search result label is changed
		viewAsList.verifySearchResultLabelNotEquals(showAllResultsSearchResultLabel);

		//Verify facility names sort
		this.verifyAvailableFacilityNamesSort();

		//Verify facility name filter are not changed 
		//After clicking "Next"
		this.actionPageControl(true);
		this.verifyNoChangeAboutFacilityNameFilters(inicialFacilityNameFilters.toString());

		//After clicking "previous"
		this.actionPageControl(false);
		this.verifyNoChangeAboutFacilityNameFilters(inicialFacilityNameFilters.toString());

		//Verify Availability filter validation after clicking facility name filters
		this.verifyAvailabilityAfterClickingNameFilters();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "wash";
//		searchData.selectAutoCompleteOption = true;
		searchData.selectedAutoCompletedOption = "WASHINGTON";
		searchData.interestInValue = "Camping & Lodging";
		searchData.arrivalDate = DateFunctions.getToday();
		searchData.lengthOfStay = "2";
		searchData.flexibleValue = "Not Flexible";

		noAvailableSitesWarnMes = "There are no available sites within the results";
	}

	private void clickShowOnlyAvailableSites(){
		viewAsList.clickShowOnlyAvailableSites();
		viewAsList.waitLoading();
	}

	/**
	 * Start to verify facility name alphabetically sort...
	 */
	private void verifyAvailableFacilityNamesSort(){
		logger.info("Start to verify facility name alphabetically sort...");
		List<String> availableFacilityNames = viewAsList.getAllParkNames();
		viewAsList.verifySortByAlphabetical(availableFacilityNames, false);
		viewAsList.gotoFirstPage();
	}

	/**
	 * Verify no change about facility name filter via gave one
	 * @param expectedFacilityNameFilters
	 */
	private void verifyNoChangeAboutFacilityNameFilters(String expectedFacilityNameFilters){
		logger.info("Start to verify facility name filter info...");
		String actualFacilityNameFilters = viewAsList.getAllClickableSearchNameFiltersText().toString();

		if(!expectedFacilityNameFilters.equals(actualFacilityNameFilters)){
			throw new ErrorOnDataException("Facility name filters is incorrect.",
					expectedFacilityNameFilters.toString(),actualFacilityNameFilters);
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
			viewAsList.clickNext();
		}else{
			viewAsList.clickPrevious();
		}

		viewAsList.waitLoading();
	}

	/**
	 * Verify availability filter after click facility name filters
	 * For top 10 parks
	 * 1. If all the the park has the site type titles: 0 matching sites available
	 *    Display warning message
	 * 2. More than one parks have the site type titles: First-come-first-serve\ufffd or \ufffdReservation via Call center or "1...matching sites available"
	 *    No availability filter info display
	 */
	private void verifyAvailabilityAfterClickingNameFilters(){
		for(int i=0; i<inicialFacilityNameFilters.size(); i++){
			logger.info("Verify availability info after clicking name filter"+inicialFacilityNameFilters.get(i));

			viewAsList.clickSearchNameFilter(inicialFacilityNameFilters.get(i));
			viewAsList.waitLoading();

			List<String> parkSiteTypeTitles = viewAsList.geParkSiteTypeTitlesInFirstPg();
			int availabilitySiteTypesCount = parkSiteTypeTitles.size();
			for(int j=0; j<parkSiteTypeTitles.size(); j++){
				if(parkSiteTypeTitles.get(j).equals("0 matching sites available")){
					availabilitySiteTypesCount--;
				}
			}
			if(availabilitySiteTypesCount==0){
				viewAsList.verifyWarningMes(noAvailableSitesWarnMes);
			}else{
				if(viewAsList.checkShowOnlyAvailableSitesLinkExist() || viewAsList.checkShowAllResultsLinkExist() || viewAsList.checkWarningMesExist()){
					throw new ErrorOnDataException("Availability filter('ShowOnlyAvailableSites' and 'ShowAllResults') and facility available warning message displays.");
				}else{
					logger.info("Successfylly verify no availability filter info and  message when it has more than one available site type title.");
				}
			}
		}
	}
}
