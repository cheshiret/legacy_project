package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.unifiedsearch;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: P
 * 1. Using the sample data to run a search to get search results page
 * Expected result: shows results within <state> on search results page; 
 * 2. Click on 'Camping & Day use' fileter option; 
 * Expected Result: Interest in is "Everything"
 * 3. Click on the facility name in the first result;
 * 4. Select 'Yurts' from 'Looking for' drop down list on campsite search form,  enter "3" in 'Occupants' and check 'Waterfron' under 'more options...', click 'Search'; 
 * Expected result: shows "No results found matching your search."; verify there is a link of 'Parks Nearby'; 
 * 5. Click on 'Parks Nearby'; 
 * Expected result: 
 *  It goes to rearch results page;
 *  The contextual facility name is populated in 'Where' field; 
 *  'Camping & lodging' option is selected, 'Yurts' is selected in 'Looking for' drop down list;
 * All the entry data and selection in 'Occupants' filed and 'more options..' shall be populated on campground search form

 * @Preconditions:
 * @SPEC: 'Interested in' - drop down mode (decision support flow) [TC:043032] 
 * @Task#: AUTO-1234 
 * 
 * @author SWang
 * @Date 9/14/2012
 */
public class InterestInDecisionSupportFlow extends RecgovTestCase {
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedFindProductCommonPanel findSitePanel = UwpUnifiedFindProductCommonPanel.getInstance();
	private RecgovSiteListPage siteListPg = RecgovSiteListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();

	private String viewHeaderNearValue, warnMesInSiteListPg;

	public void execute() {
		web.invokeURL(url);

		//Verify view header near value after State search
		this.gotoViewAsListPage(searchData);
		viewAsListPg.verifyViewHeaderNearValue(viewHeaderNearValue);

		//Click results filter to check interest in value
		viewAsListPg.filterResults(UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_LINKTITLE, "", "");
		searchPanel.verifyInterestInSelectedValue(UwpUnifiedSearch.EVERYTHING_INSTERETED_IN);

		//Click facility name:BIRCH LAKE CAMPGROUND to facility details page
		searchData.parkId = "73749";
		web.gotoFacilityDetailsPageFromViewAsListPage(searchData.contractCode, searchData.parkId);

		//Find sites to verify warning message
		findSitePanel.makeSearch(bd);
		siteListPg.verifyMsgTopOfPage(warnMesInSiteListPg);

		//Click parks nearby to view as list to check search panel fields values
		web.goBackToViewAsListPageFromSiteListPage(searchData.contractCode, searchData.parkId);
		this.initialSearchData();
		searchPanel.verifySearchCriteria(searchData);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		searchData.contractCode = "NRSO";

		searchData.whereTextValue = "MINNESOTA"; //State
		searchData.interestInValue = UwpUnifiedSearch.DEFAULT_INSTERETED_IN;
		viewHeaderNearValue = "Results within "+searchData.whereTextValue;

		bd.lookFor = "Yurts";
		bd.occupants = "3";
		bd.moreOptions= true;
		bd.waterFront = true;
		warnMesInSiteListPg = "No results found matching your search.";
	}

	/**
	 * Initial search data
	 */
	public void initialSearchData(){
		searchData.whereTextValue = DataBaseFunctions.getFacilityName(searchData.parkId, schema);
		searchData.interestInValue = UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN;
		searchData.lookFor = bd.lookFor;
		searchData.occupants = bd.occupants;
		searchData.moreOptions = bd.moreOptions;
		searchData.waterFront = bd.waterFront;
		searchData.flexibleValue = UwpUnifiedSearch.FLEXIBLE_DEFAULT;
		searchData.arrivalDate = UwpUnifiedSearch.ARRIVALDATE_MMDDYY;
	}
}
