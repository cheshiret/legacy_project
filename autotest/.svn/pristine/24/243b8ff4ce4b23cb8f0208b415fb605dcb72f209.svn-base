package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.agency;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * Check point starts from 'View As Map' page
 * 1. Click on 'Next' or 'Previous', or select a page number in page selector;
 * Expected result: No any changes on filter selections;
 * 2. Click a facility name from the search results;
 * 3. Click 'Find other facilities' link;
 * Expected result:  No any changes on filter selections;
 * 4. Click "X" which is next to agency filter title (Agency);
 * Expected result: 
 *   It will release the agency filter to show all the agency filter options as link again;
 *   Search results are updated accordinately;
 * @Preconditions:
 * @SPEC: Agency filter - Search flow - Rec area (Parent /No GPS) search [TC:043155] 
 * @Task#: AUTO-1230 
 * 
 * @author SWang
 * @Date  Sep 28, 2012
 */
public class ParentRecAreaNoGpsMapSearchFlow extends RecgovTestCase {
	private UwpUnifiedSearchViewAsMapPage viewAsMap = UwpUnifiedSearchViewAsMapPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private UwpRecreationAreaDetailsPage recAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private String resultFiltersContent, facilityIdOnMap;

	public void execute() {
		web.invokeURL(url);

		//Get result filter content, all agency info and agency result total number
		web.gotoViewAsListPage(searchData);
		facilityIdOnMap = viewAsListPg.getFirstParkIdOnMap(searchData.contractCode);
		web.gotoViewAsMapFromViewAsList();
		resultFiltersContent =  viewAsMap.getResultFiltersContent();
		
		//No any changes on filter selections after clicking next, previous button
		viewAsMap.OperatePaging(true);
		viewAsMap.verifyResultFiltersContent(resultFiltersContent);

		viewAsMap.OperatePaging(false);
		viewAsMap.verifyResultFiltersContent(resultFiltersContent);

		//No any changes on filter selections after finding other facilities
		web.gotoProducteDetailsPageFromViewAsMapPage(recAreaDetailsPg, searchData.contractCode, facilityIdOnMap);
		web.findOtherFacilities(viewAsMap);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "Mt. Baker-Snoqualmie National Forest";
		searchData.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
		searchData.otherActivitiesName = new String[]{"Wildlife Viewing"};
		searchData.contractCode = "NRSO";
	}
}
