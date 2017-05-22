package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.agency;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
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
 * @SPEC: Agency filter - Search flow - Google address search [TC:043209] 
 * @Task#: AUTO-1230 
 * 
 * @author SWang
 * @Date  Sep 27, 2012
 */
public class GoogleAddressMapSearchFlow extends RecgovTestCase {
	private UwpUnifiedSearchViewAsMapPage viewAsMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private RecgovParkDetailsPage campGroundDetailsPg = RecgovParkDetailsPage.getInstance();

	private Map<String, Integer> agencyNamesAndNums = new HashMap<String, Integer>();
	private String resultFiltersContent;
	private int agencyFiltersResultTotalNum;
	private String agencyOptionWithNextButton;

	public void execute() {
		web.invokeURL(url);

		//Get result filter content, all agency info and agency result total number
		web.gotoViewAsMapPage(searchData);
		resultFiltersContent =  viewAsMapPg.getResultFiltersContent();
		agencyNamesAndNums = viewAsMapPg.getAllAgencyFiltersNamesAndNums();
		agencyFiltersResultTotalNum = viewAsMapPg.getAgencyFiltersResultTotalNum();

		//Total count number from agency filter options matches the total search results summary showing on the header
		viewAsMapPg.verifySearchResultLabel(viewAsMapPg.generateSearchResultLabel(agencyFiltersResultTotalNum));
		//Each count number from agency filter options matches the each search results summary showing on the header
		this.verifyNumBehindAgencyFiltersMatchSearchResults();
		//Release the agency filter to show all the agency filter options after clicking 'X' behind agency category
		viewAsMapPg.verifyResultFiltersContent(resultFiltersContent);

		//No any changes on filter selections after clicking next, previous button, if these 2 kind of buttons are available
		if(!StringUtil.isEmpty(agencyOptionWithNextButton)){
			viewAsMapPg.filterResults("", agencyOptionWithNextButton, "");
			resultFiltersContent = viewAsMapPg.getResultFiltersContent();
			viewAsMapPg.OperatePaging(true);
			viewAsMapPg.verifyResultFiltersContent(resultFiltersContent);
			viewAsMapPg.OperatePaging(false);
			viewAsMapPg.verifyResultFiltersContent(resultFiltersContent);
		}

		//No any changes on filter selections after finding other facilities
		web.gotoProducteDetailsPageFromViewAsMapPage(campGroundDetailsPg);
		web.findOtherFacilities(viewAsMapPg);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "SOUTHSIDE DRIVE, YOSEMITE NATIONAL PARK";
		                                                                        
		searchData.interestInValue = UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN;
		searchData.lookFor = "Tent";
		searchData.moreOptions = true;
		searchData.waterFront = true;
		searchData.arrivalDate = DateFunctions.getDateAfterToday(0);
		searchData.flexibleValue = UwpUnifiedSearch.FLEXIBLE_NEXT2WEEK;
		searchData.lengthOfStay = "2";
		searchData.petsAllowed = true;
	}

	private void verifyNumBehindAgencyFiltersMatchSearchResults(){
		String searchResultLabel = "";

		//Verify each agency number
		Set<Map.Entry<String, Integer>> set = agencyNamesAndNums.entrySet();
		for (Iterator<Entry<String, Integer>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();

			searchResultLabel = viewAsMapPg.generateSearchResultLabel(entry.getValue());
			viewAsMapPg.filterResults("", entry.getKey(), "");
			viewAsMapPg.verifySearchResultLabel(searchResultLabel);
			logger.info("Successfully verify number behind agency:"+entry.getKey()+" filter match search results:"+searchResultLabel);

			//clear filter
			viewAsMapPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_AGENCY);

			//Try to get the agency with number >10
			if(entry.getValue()>10){
				agencyOptionWithNextButton = entry.getKey();
			}
		}
	}
}
