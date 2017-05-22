package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch.facilitynamefilters.viewasmap;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (Some time will be failed because of the map pin location)
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
	private UwpUnifiedSearchViewAsMapPage viewAsMap = UwpUnifiedSearchViewAsMapPage.getInstance();
	private RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private List<String> inicialFacilityNameFilters = new ArrayList<String>();
	private String[] testClicableFacilityNameFilters;
	private String noAvailableSitesWarnMes, showAllResultsSearchResultLabel;

	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(searchData);
		inicialFacilityNameFilters = viewAsMap.getAllClickableSearchNameFiltersText();
		showAllResultsSearchResultLabel = viewAsMap.getSearchResultsLabel();

		this.clickShowOnlyAvailableSites();

		//Verify search result label is changed
		viewAsMap.verifySearchResultLabel(showAllResultsSearchResultLabel, false);

		//		//No need in "View As Map" page
		//		this.verifyAvailableFacilityNamesSort();

		//Verify facility name filter are not changed 
		//After clicking "Next"
		this.actionPageControl(true);
		this.verifyFacilityNameFilter(inicialFacilityNameFilters.toString());

		//After clicking "previous"
		this.actionPageControl(false);
		this.verifyFacilityNameFilter(inicialFacilityNameFilters.toString());

		//Verify Availability filter validation after clicking facility name filters
		this.verifyAvailabilityAfterClickingNameFilters();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "wash";
//		searchData.selectAutoCompleteOption = true;
		searchData.selectedAutoCompletedOption = "WASHINGTON";
		searchData.interestInValue = "Camping & Lodging";
		searchData.arrivalDate = DateFunctions.getDateAfterToday(1);
		searchData.lengthOfStay = "2";
		searchData.flexibleValue = "Not Flexible";
		searchData.contractCode = "NRSO";
		
		testClicableFacilityNameFilters = new String[]{"D", "H"};
		noAvailableSitesWarnMes = "There are no available sites within the results";
	}

	private void clickShowOnlyAvailableSites(){
		viewAsMap.clickShowOnlyAvailableSites();
		viewAsMap.waitLoading();
	}

	/**
	 * Verify facility name filter via gave one
	 * @param expectedFacilityNameFilters
	 */
	private void verifyFacilityNameFilter(String expectedFacilityNameFilters){
		logger.info("Start to verify facility name filter doesn't changed ...");
		String actualFacilityNameFilters = viewAsMap.getAllClickableSearchNameFiltersText().toString();

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
			viewAsMap.clickNext();
		}else{
			viewAsMap.clickPrevious();
		}

		viewAsMap.waitLoading();
	}

	/**
	 * Get park IDs which haven't the pin "No Map" from "View As List" page
	 */
	private List<String> getFirstPgParkIDsHasMap(){
		List<String> returnFirstPgParkIDs = new ArrayList<String>();
		logger.info("Start to get first page park IDs which haven't the pin 'No Map' from 'View As List' page.");

		List<String> firstPgParkPinTitles = viewAsList.getFirstPgPinTitles();
		List<String> firstPgParkIDs = viewAsList.getFirstPgParkIDs();

		for(int i=0; i<firstPgParkPinTitles.size(); i++){
			if(firstPgParkPinTitles.get(i).equals("View on Map")){
				returnFirstPgParkIDs.add(firstPgParkIDs.get(i));
			}else{
				logger.info("Park pin title is 'Not on Map' for park with id:"+firstPgParkIDs.get(i));
			}
		}

		return returnFirstPgParkIDs;
	}

	/**
	 * Get park IDs which haven't the pin "No Map" from "View As List" page
	 * Start page: View As Map page
	 * End page: View As Map page
	 * @return
	 */
	private List<String> gotoViewAsListToGetParkIDsHasMap(){
		logger.info("Start to get first page park pin text which haven't the pin 'No Map' from 'View As List' page.");
		viewAsMap.clickViewAsList();
		viewAsList.waitLoading();

		List<String> firstPgParkIDsHasMap = this.getFirstPgParkIDsHasMap();

		viewAsList.clickViewAsMap();
		viewAsMap.waitLoading();

		return firstPgParkIDsHasMap;
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
		for(int i=0; i<testClicableFacilityNameFilters.length; i++){
			logger.info("Verify availability info after click name filters---"+testClicableFacilityNameFilters[i]);

			logger.info("Click facility name filter---:"+testClicableFacilityNameFilters[i]);
			viewAsMap.clickSearchNameFilter(testClicableFacilityNameFilters[i]);
			viewAsMap.waitLoading();

			List<String> parkIDsHasMap = this.gotoViewAsListToGetParkIDsHasMap();
			int availabilitySiteTypesCount = parkIDsHasMap.size();
			for(int j=0; j<parkIDsHasMap.size(); j++){
				viewAsMap.clickMapPin(searchData.contractCode, parkIDsHasMap.get(j));
				viewAsMap.mapBubbleWidgetWaitExists();

				logger.info("Get the park name site type title with ID:"+parkIDsHasMap.get(j));
				String parkSiteTypeTitle = viewAsMap.getParkSiteTypeTitle();
				viewAsMap.closeMapBubbleWidget();
				if(parkSiteTypeTitle.equals("0 matching sites available")){
					availabilitySiteTypesCount--;
				}
			}
			logger.info("Availability site types count is:"+availabilitySiteTypesCount);

			if(availabilitySiteTypesCount==0){
				viewAsMap.verifyFacilityAvailableWarningMes(noAvailableSitesWarnMes);
			}else{
				if(viewAsMap.checkShowOnlyAvailableSitesLinkExist() || viewAsMap.checkShowAllResultsLinkExist() || viewAsMap.checkFacilityAvailableWarningMesExist()){
					throw new ErrorOnDataException("Availability filter('ShowOnlyAvailableSites' and 'ShowAllResults') and facility available warning message displays.");
				}else{
					logger.info("Successfylly verify no availability filter info and  message when it has more than one available site type title.");
				}
			}
		}
	}
}
