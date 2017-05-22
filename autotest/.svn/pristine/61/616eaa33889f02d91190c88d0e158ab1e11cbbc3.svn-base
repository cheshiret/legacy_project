package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.agency;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: p (RFT)
 * 1. On home page, click 'EXPLORE AND MORE' tab, click 'Hiking'
 * 2. Enter sample data in the fileld below 'Enter a location or zip code', don't pick up anything from auto-complete list, click 'Find hiking nearby!' link; 
 * Expected result
 * 1) It displays suggestion page;
 * 2) The entry data is populated in 'Where' field, 'Other Activities' is selected and 'Hiking' is checked;
 * 3. Click a results on suggestion page;
 * Expected result
 * 1) It shows search results page; 
 * 2) Filters (Type, Agency, First Letter of Name) are showing below the label "Filter Your Results" below the search form;
 *
 * 4. Click 'EXPLORE AND MORE' tab, click 'Camping'
 * 5. Enter sample data in the fileld below 'Enter a location or zip code',  pick it up from auto-complete list, click 'Find camping nearby!' link; 
 * Expected result as Step 3
 * 
 * @Preconditions:
 * @SPEC: Agency filter - Search flow - 'Explore and More' Activities page [TC:043198] 
 * @Task#: AUTO-1230 
 * 
 * @author SWang
 * @Date  Sep 25, 2012
 */
public class ExploreAndMoreSearchFlow extends RecgovTestCase{
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private UwpUnifiedSearchSuggestionPage suggestionPg = UwpUnifiedSearchSuggestionPage.getInstance();
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private String locationOrZipCode1, locationOrZipCode2;
	private String[] filterCategory;

	public void execute() {
		web.invokeURL(url);
		web.gotoExploreActivityFromHeaderBare(UwpUnifiedSearch.EXPLORE_HUNTING);

		//Find nearby location without picking up option to suggestion page
		//verify search criteria in search panel
		web.findNearByLocationOrZipCode(locationOrZipCode1, false, suggestionPg);
		searchData.otherActivitiesName = new String[]{UwpUnifiedSearch.EXPLORE_HUNTING};
		searchPanel.verifySearchCriteria(searchData);

		//Click suggestion option to view as list page check filter structure
		this.gotoViewAsListFromSuggestionPage(locationOrZipCode1, false);
		viewAsListPg.verifyFilterStructure(filterCategory);

		//Find nearby location selecting with picking up option to view as list page
		//check filter structure
		web.gotoExploreActivityFromHeaderBare(UwpUnifiedSearch.EXPLORE_HUNTING);
		web.findNearByLocationOrZipCode(locationOrZipCode2, true, viewAsListPg);
		viewAsListPg.verifyFilterStructure(filterCategory);
	}

	public void wrapParameters(Object[] param) {
		locationOrZipCode1 = "cali";
		locationOrZipCode2 = "FLORIDA";
		filterCategory = new String[]{UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, 
				UwpUnifiedSearch.FILTERCATEGORY_TYPE, 
				UwpUnifiedSearch.FILTERCATEGORY_AGENCY,
				UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME};

		url = TestProperty.getProperty(env + ".web.recgov.url");

		searchData.whereTextValue = locationOrZipCode1;
		searchData.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
	}
}
