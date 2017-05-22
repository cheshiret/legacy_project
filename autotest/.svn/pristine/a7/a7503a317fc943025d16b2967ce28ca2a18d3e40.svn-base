package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.interactions.viewaslist;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
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
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private String agencyFilterOption, resultFilterContent, letterFilterOption1, letterFilterOption2;

	public void execute() {
		//Setup check in UI-option
		MiscFunctions.verifyFiltersSetupInUiOption();
		
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		resultFilterContent = viewAsListPg.getResultFiltersContent();

		//Check result filter and available filter(link:Show only available sites) display
		viewAsListPg.verifyResultFiltersDisplaying(true);
		viewAsListPg.verifyShowOnlyAvailableSitesLinkExist();

		//Click on 'Show only available sites' link to check
		viewAsListPg.filterAvailabilityFilter(false);
		int totalCount = viewAsListPg.getTotalSearchResultNum();
		//1."Show all results" available filter displays
		//2.No "x" showing next to 'Filter Your Results'
		viewAsListPg.verifyShowAllResultsLinkExist();
		viewAsListPg.verifyClearFilterDisplay(false, UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS);
		//3.Check the total count for filter is changed
		viewAsListPg.verifyFilterResultTotalNum(totalCount, UwpUnifiedSearch.FILTERCATEGORY_AGENCY);

		//Click on 'Show all results' link to check
		viewAsListPg.filterAvailabilityFilter(true);
		totalCount = viewAsListPg.getTotalSearchResultNum();
		//1."Show only available sites" available filter displays
		//2.No "x" showing next to 'Filter Your Results'
		viewAsListPg.verifyShowOnlyAvailableSitesLinkExist();
		viewAsListPg.verifyClearFilterDisplay(false, UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS);
		//3.Check the total count for each filter is changed
		viewAsListPg.verifyFilterResultTotalNum(totalCount, UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);

		//Click agency filter to check
		//No any changes on Availability filter selection;
		//There is an "X" showing next to the selected filter title and "Filter Your Results" label;
		viewAsListPg.filterAvailabilityFilter(false);
		viewAsListPg.filterResults(StringUtil.EMPTY, agencyFilterOption, StringUtil.EMPTY);
		viewAsListPg.verifyShowAllResultsLinkExist();
		viewAsListPg.verifyClearFilterDisplay(true, UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS);

		//Click on "x" next to "Agency" to check
		//No any changes on Availability filter selection
		//"X" next to filter title and "X" next to 'Filter Your Results' label are disappeared
		viewAsListPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		viewAsListPg.verifyShowAllResultsLinkExist();
		viewAsListPg.verifyClearFilterDisplay(false, UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS);

		//Click on "x" next to "Filter Your Results" to check
		//Top results have no availability Show only available sites
		//All the filters are released to show all the search results, including Availability fitler, it will change back to:
		viewAsListPg.filterAvailabilityFilter(true);
		viewAsListPg.filterResults("", agencyFilterOption, letterFilterOption1);
		viewAsListPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS);
		viewAsListPg.verifyShowOnlyAvailableSitesLinkExist();
		viewAsListPg.verifyResultFiltersContent(resultFilterContent);

		//Availability filter will be re-evalated based on the filtered results:
		viewAsListPg.filterResults(StringUtil.EMPTY, StringUtil.EMPTY, letterFilterOption2);
		viewAsListPg.verifyNoAvailabilityFilterDisplay();
		viewAsListPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
		viewAsListPg.verifyShowOnlyAvailableSitesLinkExist();
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

