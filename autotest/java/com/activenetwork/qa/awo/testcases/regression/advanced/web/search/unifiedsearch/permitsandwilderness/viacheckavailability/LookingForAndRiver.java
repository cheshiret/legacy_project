package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.permitsandwilderness.viacheckavailability;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovEntranceListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindPermitsPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: Go to River List page according to click Check Availability button
 * 1. Verify the top result displayed as required
 * 2. Verify default value of 'Looking for' and 'River' drop down list
 * 3. Verify all values of 'Looking for' and 'River' drop down list
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Permit Search Results)
 *  UWP Unified Search_Search Form_UI (Permit Search Results)
 * @Task#:AUTO-783
 * 
 * @author SWang
 * @Date  Oct 31, 2011
 */
public class LookingForAndRiver extends RecgovTestCase{
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
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

		bd.contractCode = "NRSO";
		bd.parkId = "75534";

		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema);//MIDDLE FORK OF THE SALMON
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
	}

	private void verifyExpectedPage(){
		RecgovEntranceListPage entranceList = RecgovEntranceListPage.getInstance();
		permitTypes = searchResult.getFirstParkPermitTypes();

		searchResult.clickFirstCheckAvailability();
		entranceList.waitLoading();

		entranceList.verifyEntranceListTabDisplaying(RecgovEntranceListPage.RIVER_LIST, true);
		entranceList.verifySearchResultType(RecgovEntranceListPage.RIVER_SEARCH_RESULTS);
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

		//2.There are two permit types
		permitSearchPanel.verifyAllPermitType(permitTypes);

		//Verify 'River' field
		List<String> river = entranceList.getAllEntrances();

		//1.Default entrance value should be "Any River"
		String defaultRiver = "";
		if(river.size()>1){
			defaultRiver = permitSearchPanel.getAllEntrance().get(0);
		}else{
			defaultRiver = river.get(0);
		}

		permitSearchPanel.verifyEntrance(defaultRiver);
		//2. There are two rivers
		permitSearchPanel.verifyAllEntrance(river);
	}
}
