package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.permitsandwilderness.viapermittype;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovEntranceListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindPermitsPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: Goto Destination Zone List page after clicking specific permit type from Park view as List page.
 * 1. Go to Destination Zone List page
 * 2. Looking for drop down list value and showed value
 * 3. Destination Zone drop down list value and showed value
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

	public void execute() {
		web.invokeURL(url);

		this.gotoViewAsListPage(bd);
		searchResult.verifyParkName(bd.contractCode, bd.parkId, bd.whereTextValue);

		this.verifyExpectedPage();
		this.verifyLookingForAndEntranceValue();
	}

	public void wrapParameters(Object[] param) {
		bd.contractCode = "NRSO";
		bd.parkId = "72202";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema);//--, Eldorado National Forest, CA
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
	}

	private void verifyExpectedPage(){
		RecgovEntranceListPage entranceList = RecgovEntranceListPage.getInstance();
		permitTypes = searchResult.getFirstParkPermitTypes();

		searchResult.clickFirstParkPermitTypes(1);
		entranceList.waitLoading();

		entranceList.verifySearchResultType(RecgovEntranceListPage.DESTINATION_ZONE_SEARCH_RESULTS);
		entranceList.verifyEntranceListTabDisplaying(RecgovEntranceListPage.DESTINATION_ZONE_LIST, true);
	}

	private void verifyLookingForAndEntranceValue(){
		RecgovEntranceListPage entranceList = RecgovEntranceListPage.getInstance();
		UwpUnifiedFindPermitsPanel permitSearchPanel = UwpUnifiedFindPermitsPanel.getInstance();

		//Verify 'Looking for' field
		//1.Verify clicked permit type is populated in this field
		permitSearchPanel.verifyPermitTypeValue(permitTypes[1]);
		//2.Verify drop down list values
		permitSearchPanel.verifyAllPermitType(permitTypes);

		//Verify 'Permit Zone' field
		List<String> destinationZone = entranceList.getAllEntrances();
		//1.Verify default value
		String defaultPermitZone = "";
		if(destinationZone.size()>1){
			defaultPermitZone = permitSearchPanel.getAllEntrance().get(0);
		}else{
			defaultPermitZone = destinationZone.get(0);
		}
		permitSearchPanel.verifyEntrance(defaultPermitZone);
		//2. Verify drop down list values
		permitSearchPanel.verifyAllEntrance(destinationZone);
	}
}
