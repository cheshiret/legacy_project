package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.permitsandwilderness.viapermittype;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovEntranceListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindPermitsPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Verify Permit types and entrance items in Permit search Panel
 * @Preconditions:
 * 1. It goes to "Entrance List" page
 * 2. The clicked permit type is populated in "Looking for" field
 * 3. Default Looking for and Entrance display
 * 4. Looking for and Entrance values
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Permit Search Results)
 *  UWP Unified Search_Search Form_UI (Permit Search Results)
 * @Task#:AUTO-783
 * 
 * @author SWang
 * @Date  Oct 31, 2011
 */
public class LookingForAndEntrance1 extends RecgovTestCase{
	private RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
	private String[] permitTypes;

	public void execute() {
		web.invokeURL(url);

		this.gotoViewAsListPage(bd);
		searchResult.verifyParkName(bd.contractCode, bd.parkId, bd.whereTextValue);

		this.verifyExpectedPage();
		this.verifyLookingForAndEntranceValue();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		bd.whereTextValue = "Boundary Waters Canoe Area Wilderness (Reservations)";//--, Superior National Forest, MN
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		bd.contractCode = "NRSO";
		bd.parkId = "72600";
	}

	private void verifyExpectedPage(){
		RecgovEntranceListPage entranceList = RecgovEntranceListPage.getInstance();

		permitTypes = searchResult.getFirstParkPermitTypes();
		searchResult.clickFirstParkPermitTypes(1);
		entranceList.waitLoading();

		entranceList.verifySearchResultType(RecgovEntranceListPage.ENTRANCE_SEARCH_RESULTS);
		entranceList.verifyEntranceListTabDisplaying(RecgovEntranceListPage.ENTRANCE_LIST, true);
	}

	private void verifyLookingForAndEntranceValue(){
		RecgovEntranceListPage entranceList = RecgovEntranceListPage.getInstance();
		UwpUnifiedFindPermitsPanel permitSearchPanel = UwpUnifiedFindPermitsPanel.getInstance();

		//Verify 'Looking for' field
		//1.Verify clicked permit type is populated in this field
		permitSearchPanel.verifyPermitTypeValue(permitTypes[1]);
		//2.Verify drop down list values
		permitSearchPanel.verifyAllPermitType(permitTypes);

		//Verify 'Entrance' field
		List<String> entrances = entranceList.getAllEntrances();

		//1.Verify default value
		String defaultEntrance = "";
		if(entrances.size()>1){
			defaultEntrance = permitSearchPanel.getAllEntrance().get(0);
		}else{
			defaultEntrance = entrances.get(0);
		}
		permitSearchPanel.verifyEntrance(defaultEntrance);
		//2. Verify drop down list values
		permitSearchPanel.verifyAllEntrance(entrances);
	}
}
