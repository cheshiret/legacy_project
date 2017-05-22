package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.interactions.viewasmap;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
/**
 * @Description: P
 * @Preconditions:
 * @SPEC: Interatcions between filters Availability filter and filteres below the search form [TC:043271] 
 * @Task#: AUTO-1233 
 * 
 * @author SWang5
 * @Date  Oct 10, 2012
 */
public class FiltersAndAvailabilityFilter extends RecgovTestCase{
	private UwpUnifiedSearchViewAsMapPage viewAsMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
	private String agencyFilterOption, resultFilterContent, letterFilterOption1, letterFilterOption2;

	public void execute() {
		//Setup check in UI-option
		MiscFunctions.verifyFiltersSetupInUiOption();

		web.invokeURL(url);
		web.gotoViewAsMapPage(bd);
		resultFilterContent = viewAsMapPg.getResultFiltersContent();

		//Check result filter and available filter(link:Show only available sites) display
		viewAsMapPg.verifyResultFiltersDisplaying(true);
		viewAsMapPg.verifyShowOnlyAvailableSitesLinkExist();

		//Click on 'Show only available sites' link to check
		viewAsMapPg.filterAvailabilityFilter(false);
		int totalCount = viewAsMapPg.getTotalSearchResultNum();
		//1."Show all results" available filter displays
		//2.No "x" showing next to 'Filter Your Results'
		viewAsMapPg.verifyShowAllResultsLinkExist();
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, false);
		//3.Check the total count for filter is changed
		viewAsMapPg.verifyAgencyFilterResultTotalNum(totalCount);

		//Click on 'Show all results' link to check
		viewAsMapPg.filterAvailabilityFilter(true);
		totalCount = viewAsMapPg.getTotalSearchResultNum();
		//1."Show only available sites" available filter displays
		//2.No "x" showing next to 'Filter Your Results'
		viewAsMapPg.verifyShowOnlyAvailableSitesLinkExist();
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, false);
		//3.Check the total count for each filter is changed
		viewAsMapPg.verifyFirstLetterOfNameFilterResultTotalNum(totalCount);

		//Click agency filter to check
		//No any changes on Availability filter selection;
		//There is an "X" showing next to the selected filter title and "Filter Your Results" label;
		viewAsMapPg.filterAvailabilityFilter(false);
		viewAsMapPg.filterResults(StringUtil.EMPTY, agencyFilterOption, StringUtil.EMPTY);
		viewAsMapPg.verifyShowAllResultsLinkExist();
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, true);

		//Click on "x" next to "Agency" to check
		//No any changes on Availability filter selection
		//"X" next to filter title and "X" next to 'Filter Your Results' label are disappeared
		viewAsMapPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		viewAsMapPg.verifyShowAllResultsLinkExist();
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, false);

		//Click on "x" next to "Filter Your Results" to check
		//Top results have no availability Show only available sites
		//All the filters are released to show all the search results, including Availability fitler, it will change back to:
		viewAsMapPg.filterAvailabilityFilter(true);
		viewAsMapPg.filterResults(StringUtil.EMPTY, agencyFilterOption, letterFilterOption1);
		viewAsMapPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS);
		viewAsMapPg.verifyShowOnlyAvailableSitesLinkExist();
		viewAsMapPg.verifyResultFiltersContent(resultFilterContent);

		//Availability filter will be re-evalated based on the filtered results:
		viewAsMapPg.filterResults(StringUtil.EMPTY, StringUtil.EMPTY, letterFilterOption2);
		viewAsMapPg.verifyNoAvailabilityFilterDisplay();
		viewAsMapPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
		viewAsMapPg.verifyShowOnlyAvailableSitesLinkExist();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		bd.whereTextValue = "MONTANA";
		bd.interestInValue = UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN;
		bd.arrivalDate = DateFunctions.getDateAfterToday(355);
		bd.lengthOfStay = "2";

		agencyFilterOption = DataBaseFunctions.getAgencyName(OrmsConstants.AGENCY_USFORESTSERVICE_ID, schema);
		letterFilterOption1 = "B"; 
		letterFilterOption2 = "Z";
	}
}

