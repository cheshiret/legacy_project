package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.statesearch.facilitynamefilters.viewasmap;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
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
		make sure the search results matching the facility type filter selected 


       //Handle in script: NameFilterWithTypeFilter
//		1. It shows facility type filters and facility name filter; 
//		default selections are 'All' for both filters; 
//		make sure the summary in facility type filter 
//		and 'Search Results: x-xx of xx' matching;
//		2. Make sure facility name filter will be reset 
//		and  'Search Results: x-xx of xx' will be updated when click on facility type filters; 

//		and always displaying from the first page when click on facility type fitlers;
//		3. Make sure no any change on facility type filter 
//		when click a letter on faiclity name filter;  
//		'Search Results: x-xx of xx' will be updated according to the letter selected on facility name filter; 
//		make sure the search results matching the selected facility type filter and selected letter on facility name filter;
//		4. Make sure facility name filter will be reset 
//		and  'Search Results: x-xx of xx' will be updated 
//		and search results will displays from the first page when click on facility type filters;
//		5. Make sure both of the facility type filter and facility name filter are reset to default status, s
//		earch results are displaying from the first page

 * @Preconditions:
 * @SPEC: Story P - State Search in search form (list view)
 * @Task#:AUTO-924
 * 
 * @author SWang
 * @Date  Mar 14, 2012
 */
public class SearchResultMatchFacilityTypeFilters extends RecgovTestCase {
	private UwpUnifiedSearchViewAsMapPage viewAsMap = UwpUnifiedSearchViewAsMapPage.getInstance();
	private RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private List<String> searchTypeFilters = new ArrayList<String>();

	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(searchData);
		searchTypeFilters = viewAsMap.getAllClickableSearchTypeFiltersDisplayText();

		this.verifyNameAndTypeFilterAndSearchResultLabelWhenClickNameFilter();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "CALIFORNIA";//"cali";
//		searchData.selectAutoCompleteOption = true;
//		searchData.selectedAutoCompletedOption = "CALIFORNIA";
		searchData.interestInValue = "Everything";

		searchData.contractCode = "NRSO";
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
	 * Verify facility name filter and search result label and the page from first page
	 */
	private void verifyNameAndTypeFilterAndSearchResultLabelWhenClickNameFilter(){
		for(int i=0; i<searchTypeFilters.size(); i++){
			viewAsMap.clickSearchTypeFilter(searchTypeFilters.get(i));
			viewAsMap.waitLoading();

			String siteTypeFilter = "";
			//Change facility type filter to site type title
			if(searchTypeFilters.get(i).startsWith("Camping & Day Use\\(\\d+\\)")){
				siteTypeFilter = "Camping and Lodging";
			}else if(searchTypeFilters.get(i).startsWith("Permits\\(\\d+\\)")){
				siteTypeFilter = "Permits and Wilderness";
			}else if(searchTypeFilters.get(i).startsWith("Tours\\(\\d+\\)")){
				siteTypeFilter = "Tours and Tickets";
			}else  if(searchTypeFilters.get(i).matches("Other Recreation Facilities\\(\\d+\\)")){
				siteTypeFilter = "Other";
			}else{
				throw new ErrorOnDataException("No matched site type filter is found.");
			}

			List<String> firstPgParkIDsHasMap = this.gotoViewAsListToGetParkIDsHasMap();
			logger.info("Verify park site type match selected facility type filter.");
			for(int j=0; j<firstPgParkIDsHasMap.size(); j++){
				viewAsMap.clickMapPin(searchData.contractCode, firstPgParkIDsHasMap.get(j));
				viewAsMap.mapBubbleWidgetWaitExists();

				String parkSiteTypeTitle = viewAsMap.getParkSiteTypeTitle();
				viewAsMap.closeMapBubbleWidget();

				if(!siteTypeFilter.equals("Other")){
					if(!parkSiteTypeTitle.equals(siteTypeFilter)){
						throw new ErrorOnDataException("Park site type doesn't match selected facility type filter", siteTypeFilter, parkSiteTypeTitle);
					}
				}else{
					if(parkSiteTypeTitle.length()>=1){
						throw new ErrorOnDataException("No Park site type titles info for other recreation facilities.");
					}
				}
			}
		}
	}
}
