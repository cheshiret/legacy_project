package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * NOTE: pass on local
 * @Description:
 * 1: verify search result when the search facility don't have lats and long info.
 * 2: verify the search result facility don't have distance info,b/c those facility don't have lats and long info.
 * 3: verify facility name link to facility details page.
 * 4: verify parent facility link to recareadetails page.
 * @Preconditions:
 * @SPEC:StoryC
 * @Task#: Auto-1818
 * 
 * @author bzhang, SWang
 * @Date  Oct 9, 2011, Aug 5, 2013
 */
public class WithoutDistanceInfo extends RecgovTestCase {
	private String searchNearByText, childParkID, childParkName, childFacilitiesType, ridbSchema;
	private RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();

	public void execute() {
		web.invokeURL(url);

		//Search by "parent park", verify all search results are without distance, then go back to first page to verify view header near value
		web.gotoViewAsListPage(bd);
		searchResult.verifyAllParkWithoutDistance();
		searchResult.OperatePageSelector("1");
		searchResult.verifyViewHeaderNearValue(searchNearByText);

		//Click child park under parent park to child park details page
		childParkName = searchResult.getChildFacilities(bd.contractCode, bd.parkId, childFacilitiesType).get(0);
		childParkID = DataBaseFunctions.getFacilityID(childParkName, schema);
		clickChildFacilityToItsDetailsPg(bd.contractCode, bd.parkId, childParkName);

		//Search by "parent park" again, the click parent park under child park to recreation details page
		web.findOtherFacilities(searchResult);
		web.gotoViewAsListPage(bd);
		verifyParentNameLinkToAreaDetailsPage(bd.contractCode, childParkID, bd.whereTextValue, bd.stateCode);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		ridbSchema = DataBaseFunctions.getRIDBSchemaName(env);
		bd.parkId = "1063";
		bd.whereTextValue = DataBaseFunctions.getRecreationAreaName(bd.parkId, ridbSchema); //"Eldorado National Forest";
		bd.stateCode = "CA";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.contractCode = "NRSO";

		childFacilitiesType = "Camping, Lodging and Day Use";
		searchNearByText = "Results part of " + bd.whereTextValue;
	}

	private void clickChildFacilityToItsDetailsPg(String contractCode, String parentParkID, String childParkName){
		RecgovParkDetailsPage campgroundDetailPg = RecgovParkDetailsPage.getInstance();
		searchResult.clickChildPark(contractCode, parentParkID, childParkName);
		campgroundDetailPg.waitLoading();
	}


	private void verifyParentNameLinkToAreaDetailsPage(String contractCode, String parentParkName, String recreationAreaName, String stateCode){
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		UwpRecreationAreaDetailsPage recAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();

		searchResult.clickParentParkName(contractCode, parentParkName );
		recAreaDetailsPg.waitLoading();		
		recAreaDetailsPg.verifyAreaNameAndRelatedCode(recreationAreaName, stateCode);
	}
}
