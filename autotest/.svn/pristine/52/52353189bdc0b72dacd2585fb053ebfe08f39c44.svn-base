package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.permitsandwilderness.viacheckavailability;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.bw.BwSearchPanel;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovEntranceListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovPermitAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindPermitsPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Go to Destination Zone page according to click Check Availability button
 * 1. Verify the top result displayed as required
 * 2. Verify default value of 'Looking for' and 'Destination Zone' drop down list
 * 3. Verify all values of 'Looking for' and 'Destination Zone' drop down list
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Permit Search Results)
 *  UWP Unified Search_Search Form_UI (Permit Search Results)
 * @Task#:AUTO-783
 * 
 * @author SWang
 * @Date  Oct 31, 2011
 */
public class LookingForAndDestinationZone extends RecgovTestCase{
	private RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
	private String[] permitTypes;
	private String permitType1;

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);

		searchResult.verifyParkName(bd.contractCode, bd.parkId, bd.whereTextValue);

		this.verifyExpectedPageAndPermitTypes();
		this.verifyLookingEntrances();
	}

	public void wrapParameters(Object[] param) {
		bd.contractCode = "NRSO";
		bd.parkId = "72202";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema);
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		permitType1 = "Overnight Permit";
	}

	//Verify expected page and permit types
	//New feature in Single Trip Itinerary, when at least one Itinerary Permit Type is applicable to the facility. 
	//The page contains "Permit Area Details" and "Permit Area Map" tabs, The "Entrance List" and "Date Range Availability" tabs are not displayed and cannot be navigated to 
	private void verifyExpectedPageAndPermitTypes(){
		RecgovEntranceListPage entranceList = RecgovEntranceListPage.getInstance();
		RecgovPermitAreaDetailsPage permitAreaDetailsPg = RecgovPermitAreaDetailsPage.getInstance();
		UwpUnifiedFindPermitsPanel permitSearchPanel = UwpUnifiedFindPermitsPanel.getInstance();
		String defaultPermitType = "";

		permitTypes = searchResult.getFirstParkPermitTypes();

		searchResult.clickFirstCheckAvailability();
//		entranceList.waitExists();
		permitAreaDetailsPg.waitLoading(); //Sara (7/21/2013) SPlease wait for permitAreaDetailsPg, not entranceList

		//Verify permit default value and drop down list value
		if(permitTypes.length>1){
			defaultPermitType = permitSearchPanel.getAllPermitTypes().get(0);
		}else{
			defaultPermitType = permitTypes[0];
		}
		defaultPermitType = permitSearchPanel.getAllPermitTypes().get(0);
		permitSearchPanel.verifyPermitTypeValue(defaultPermitType);
		permitSearchPanel.verifyAllPermitType(permitTypes);

		//Make sure 4 sub tab display, especially destination zone list tab
		findPermits(permitType1);
		entranceList.waitForEntranceListDisplaying(RecgovEntranceListPage.DESTINATION_ZONE_LIST);
		entranceList.verifySearchResultType(RecgovEntranceListPage.DESTINATION_ZONE_SEARCH_RESULTS);
	}

	//Verify entrances
	private void verifyLookingEntrances(){
		RecgovEntranceListPage entranceList = RecgovEntranceListPage.getInstance();
		UwpUnifiedFindPermitsPanel permitSearchPanel = UwpUnifiedFindPermitsPanel.getInstance();
		String defaultDestination = "";

		//Verify destination default value and drop down list value
		List<String> destinationZone = entranceList.getAllEntrances();
		if(destinationZone.size()>1){
			defaultDestination = permitSearchPanel.getAllEntrance().get(0);
		}else{
			defaultDestination = destinationZone.get(0);
		}
		permitSearchPanel.verifyEntrance(defaultDestination);
		permitSearchPanel.verifyAllEntrance(destinationZone);
	}

	private void findPermits(String specificPermitType){
		BwSearchPanel searchPanel = BwSearchPanel.getInstance();

		searchPanel.selectPermitType(specificPermitType);
		searchPanel.clickSearch();
	}
}
