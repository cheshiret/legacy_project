package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.agency;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: P
 * Check point starts from 'View As List' page
 * 1. Check no map
 * 2. Check parent park
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
 * @SPEC: Agency filter - Search flow - Rec area (Parent /No GPS) search [TC:043155] 
 * @Task#: AUTO-1230 
 * 
 * @author SWang
 * @Date  Sep 28, 2012
 */
public class ParentRecAreaNoGpsListSearchFlow extends RecgovTestCase {
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private UwpRecreationAreaDetailsPage recAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();

	private String resultFiltersContent, childParkID, childParkContractCode;

	public void execute() {
		web.invokeURL(url);

		//Get result filter content, all agency info and agency result total number
		this.gotoViewAsListPage(searchData);
		resultFiltersContent =  viewAsListPg.getResultFiltersContent();
		
		viewAsListPg.verifyNoMapPinExist(searchData.whereTextValue);
		viewAsListPg.verifyParkWithPartOf(childParkContractCode, childParkID, searchData.whereTextValue);

		//No any changes on filter selections after clicking next, previous button
		viewAsListPg.OperatePaging(true);
		viewAsListPg.verifyResultFiltersContent(resultFiltersContent);

		viewAsListPg.OperatePaging(false);
		viewAsListPg.verifyResultFiltersContent(resultFiltersContent);

		//No any changes on filter selections after finding other facilities
		web.gotoProducteDetailsPageFromViewAsListPage(recAreaDetailsPg);
		web.backToSearch(viewAsListPg);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		searchData.whereTextValue = "Mt. Baker-Snoqualmie National Forest";
		searchData.interestInValue = UwpUnifiedSearch.OTHERACTIVITIES_INSTERETED_IN;
		searchData.otherActivitiesName = new String[]{"Wildlife Viewing"};
		
		childParkID = "70763"; //BEAVER CREEK (WA)
		childParkContractCode = "NRSO";
	}
}
