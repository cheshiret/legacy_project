package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.agency;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: p
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
 * @Date  Sep 28, 2012
 */
public class AllParksWithSpecificAgencyFilter extends RecgovTestCase {
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private String onlyAgencyOptionContentExpected;

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyOnlyAgencyFilterName(onlyAgencyOptionContentExpected);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "Boise National Forest";
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		onlyAgencyOptionContentExpected = "US Forest Service";
	}
}

