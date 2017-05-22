package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.agency;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * Check point starts from 'View As List' page
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
 * @SPEC: Agency filter - Search flow - State search [TC:043144] 
 * @Task#: AUTO-1230 
 * 
 * @author SWang
 * @Date  Sep 25, 2012
 */
public class StateListSearchFlow extends RecgovTestCase {
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private RecgovParkDetailsPage  campGroundDetailsPg = RecgovParkDetailsPage.getInstance();

	private Map<String, Integer> agencyNamesAndNums = new HashMap<String, Integer>();
	private String resultFiltersContent, agencyOptionWithNextButton;
	private int agencyFiltersResultTotalNum;

	public void execute() {
		web.invokeURL(url);

		//Get result filter content, all agency info and agency result total number
		this.gotoViewAsListPage(searchData);
		resultFiltersContent =  viewAsListPg.getResultFiltersContent();
		agencyNamesAndNums = viewAsListPg.getFiltersNamesAndNums(UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		agencyFiltersResultTotalNum = viewAsListPg.getFilterResultTotalNum(UwpUnifiedSearch.FILTERCATEGORY_AGENCY);

		//Total count number from agency filter options matches the total search results summary showing on the header
		viewAsListPg.verifySearchResultLabelEquals(viewAsListPg.generateSearchResultLabel(agencyFiltersResultTotalNum));
		//Each count number from agency filter options matches the each search results summary showing on the header
		this.verifyNumBehindAgencyFiltersMatchSearchResults();
		//Release the agency filter to show all the agency filter options after clicking 'X' behind agency category
		viewAsListPg.verifyResultFiltersContent(resultFiltersContent);

		//No any changes on filter selections after clicking next, previous button, if these 2 kind of buttons are available
		if(!StringUtil.isEmpty(agencyOptionWithNextButton)){
			viewAsListPg.filterResults("", agencyOptionWithNextButton, "");
			resultFiltersContent = viewAsListPg.getResultFiltersContent();
			viewAsListPg.OperatePaging(true);
			viewAsListPg.verifyResultFiltersContent(resultFiltersContent);
			viewAsListPg.OperatePaging(false);
			viewAsListPg.verifyResultFiltersContent(resultFiltersContent);
		}
		
		//No any changes on filter selections after finding other facilities
		web.gotoProducteDetailsPageFromViewAsListPage(campGroundDetailsPg);
		web.findOtherFacilities(viewAsListPg);

	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "CALIFORNIA";
		searchData.interestInValue = UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN;
		searchData.lookFor = "RV sites";
		searchData.arrivalDate = DateFunctions.getDateAfterToday(0);
		searchData.lengthOfStay = "2";
	}

	private void verifyNumBehindAgencyFiltersMatchSearchResults(){
		String searchResultLabel = "";

		//Verify each agency number
		Set<Map.Entry<String, Integer>> set = agencyNamesAndNums.entrySet();
		for (Iterator<Entry<String, Integer>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();

			searchResultLabel = viewAsListPg.generateSearchResultLabel(entry.getValue());
			viewAsListPg.filterResults("", entry.getKey(), "");
			viewAsListPg.verifySearchResultLabelEquals(searchResultLabel);
			logger.info("Successfully verify number behind agency:"+entry.getKey()+" filter match search results:"+searchResultLabel);

			//clear filter
			viewAsListPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_AGENCY);

			//Try to get the agency with number >10
			if(entry.getValue()>10){
				agencyOptionWithNextButton = entry.getKey();
			}
		}
	}
}

