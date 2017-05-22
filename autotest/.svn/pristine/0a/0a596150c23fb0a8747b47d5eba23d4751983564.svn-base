package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.permitsandwilderness.viacheckavailability;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovEntranceListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindPermitsPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Go to Entrance page according to click Check Availability button
 * 1. Verify the top result displayed as required
 * 2. Verify default value of 'Looking for' and 'Entrance' drop down list
 * 3. Verify all values of 'Looking for' and 'Entrance' drop down list
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Permit Search Results)
 *  UWP Unified Search_Search Form_UI (Permit Search Results)
 * @Task#:AUTO-783
 * 
 * @author SWang
 * @Date  Oct 31, 2011
 */
public class LookingForAndEntrance extends RecgovTestCase{
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
		bd.whereTextValue = "WOLF ROCK CLIMBING PERMITS"; //"Boundary Waters Canoe Area Wilderness (Reservations)";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		bd.contractCode = "NRSO";
		bd.parkId = "72342"; // "72600";
	}

	private void verifyExpectedPage(){
		RecgovEntranceListPage entranceList = RecgovEntranceListPage.getInstance();
		permitTypes = searchResult.getFirstParkPermitTypes();

		searchResult.clickFirstCheckAvailability();
		entranceList.waitLoading();
		
		entranceList.verifySearchResultType(RecgovEntranceListPage.ENTRANCE_SEARCH_RESULTS);
		entranceList.verifyEntranceListTabDisplaying(RecgovEntranceListPage.ENTRANCE_LIST, true);
	}

	private void verifyLookingForAndEntranceValue(){
		RecgovEntranceListPage entranceList = RecgovEntranceListPage.getInstance();
		UwpUnifiedFindPermitsPanel permitSearchPanel = UwpUnifiedFindPermitsPanel.getInstance();

		//Verify 'Looking for' field

		//1.Verify default permit type
		String defaultPermitType = "";
		if(permitTypes.length>1){
			defaultPermitType = permitSearchPanel.getAllPermitTypes().get(0);
		}else{
			defaultPermitType = permitTypes[0];
		}
		permitSearchPanel.verifyPermitTypeValue(defaultPermitType);

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
