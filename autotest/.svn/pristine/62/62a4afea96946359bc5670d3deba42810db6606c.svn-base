package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.agency;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpViewAsListCommonPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * Check point starts from 'View As Map' page
 * 1. Check the total count number from agency filter options;
 * Expected result: It matches the total search results summary showing on the header:
 * 2. Click on each agency filter option
 * Expected result
 *   Summary of search results is updated accordinately and it matches the count of the selected agency filter option;
 *   It shows all the search results belongs to the selected agency;
 * 3. Click on 'Next' or 'Previous', or select a page number in page selector;
 * Expected result: No any changes on filter selections;
 * 4. Click a facility name from the search results;
 * 5. Click 'Find other facilities' link;
 * Expected result:  No any changes on filter selections;
 * 6. Click "X" which is next to agency filter title (Agency);
 * Expected result: 
 *   It will release the agency filter to show all the agency filter options as link again;
 *   Search results are updated accordinately;
 * @Preconditions:
 * @SPEC: Agency filter - Search flow - Facility/Rec area (No GPS / Not Parent) [TC:043157] 
 * @Task#: AUTO-1230 
 * 
 * @author SWang
 * @Date  Sep 28, 2012
 */
public class NoParentRecAreaNoGpsMapSearchFlow extends RecgovTestCase {
	private UwpUnifiedSearchViewAsMapPage viewAsMap = UwpUnifiedSearchViewAsMapPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private UwpRecreationAreaDetailsPage recAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();
	private UwpViewAsListCommonPage viewAsListPg = UwpViewAsListCommonPage.getInstance();
	
	private String resultFiltersContent, agencyOptionWithNextButton;
	private Map<String, Integer> agencyNamesAndNums = new HashMap<String, Integer>();
	private int agencyFiltersResultTotalNum;
	private String onMapParkId;
	
	public void execute() {
		web.invokeURL(url);

		//Get result filter content, all agency info and agency result total number
		web.gotoViewAsMapPage(searchData);
		resultFiltersContent =  viewAsMap.getResultFiltersContent();
		agencyNamesAndNums = viewAsMap.getAllAgencyFiltersNamesAndNums();
		agencyFiltersResultTotalNum = viewAsMap.getAgencyFiltersResultTotalNum();

		//Total count number from agency filter options matches the total search results summary showing on the header
		viewAsMap.verifySearchResultLabel(viewAsMap.generateSearchResultLabel(agencyFiltersResultTotalNum));
		//Each count number from agency filter options matches the each search results summary showing on the header
		this.verifyNumBehindAgencyFiltersMatchSearchResults();
		//Release the agency filter to show all the agency filter options after clicking 'X' behind agency category
		viewAsMap.verifyResultFiltersContent(resultFiltersContent);

		//No any changes on filter selections after clicking next, previous button, if these 2 kind of buttons are available
		if(!StringUtil.isEmpty(agencyOptionWithNextButton)){
			viewAsMap.filterResults("", agencyOptionWithNextButton, "");
			resultFiltersContent = viewAsMap.getResultFiltersContent();
			viewAsMap.OperatePaging(true);
			viewAsMap.verifyResultFiltersContent(resultFiltersContent);
			viewAsMap.OperatePaging(false);
			viewAsMap.verifyResultFiltersContent(resultFiltersContent);
		}

//		web.gotoViewAsListPageFromViewAsMap();
//		onMapParkId = viewAsListPg.getFirstParkIdOnMap(bd.contractCode);
//		web.gotoViewAsMapFromViewAsList();
		
		//No any changes on filter selections after finding other facilities
		web.gotoProducteDetailsPageFromViewAsMapPage(recAreaDetailsPg, searchData.contractCode, onMapParkId);
		web.findOtherFacilities(viewAsMap);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "Death Valley Wilderness";
		searchData.interestInValue = UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN;
		searchData.contractCode = "NRSO";
		onMapParkId = "77813"; //Carlsbad Caverns National Park Tours
	}

	private void verifyNumBehindAgencyFiltersMatchSearchResults(){
		String searchResultLabel = "";

		//Verify each agency number
		Set<Map.Entry<String, Integer>> set = agencyNamesAndNums.entrySet();
		for (Iterator<Entry<String, Integer>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();

			searchResultLabel = viewAsMap.generateSearchResultLabel(entry.getValue());
			viewAsMap.filterResults("", entry.getKey(), "");
			viewAsMap.verifySearchResultLabel(searchResultLabel);
			logger.info("Successfully verify number behind agency:"+entry.getKey()+" filter match search results:"+searchResultLabel);

			//clear filter
			viewAsMap.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_AGENCY);

			//Try to get the agency with number >10
			if(entry.getValue()>10){
				agencyOptionWithNextButton = entry.getKey();
			}
		}
	}
}
