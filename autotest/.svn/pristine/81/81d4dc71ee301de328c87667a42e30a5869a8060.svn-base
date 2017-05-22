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
 * @Description: Go to Permit Zone page according to click Check Availability button
 * 1. Verify the top result displayed as required
 * 2. Verify default value of 'Looking for' and 'Permit Zone' drop down list
 * 3. Verify all values of 'Looking for' and 'Permit Zone' drop down list
 * @Preconditions:
 * @SPEC:
 *  UWP Unified Search_FacilitySearch_UC (Permit Search Results)
 *  UWP Unified Search_Search Form_UI (Permit Search Results)
 * @Task#:AUTO-783
 * 
 * @author SWang
 * @Date  Oct 31, 2011
 */
public class LookingForAndPermitZone extends RecgovTestCase{
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
		bd.whereTextValue = "ENCHANTMENT PERMIT AREA";//--, Okanogan National Forest, WA
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		bd.contractCode = "NRSO";
		bd.parkId = "72280";
	}

	private void verifyExpectedPage(){
		RecgovEntranceListPage entranceList = RecgovEntranceListPage.getInstance();
		permitTypes = searchResult.getFirstParkPermitTypes();

		searchResult.clickFirstCheckAvailability();
		entranceList.waitLoading();

		entranceList.verifyEntranceListTabDisplaying(RecgovEntranceListPage.PERMIT_ZONE_LIST, true);
		entranceList.verifySearchResultType(RecgovEntranceListPage.PERMIT_ZONE_SEARCH_RESULTS);
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
		List<String> permitZone = entranceList.getAllEntrances();

		//Verify 'Permit Zone' field
		//1.Verify default value
		String defaultPermitZone = "";
		if(permitZone.size()>1){
			defaultPermitZone = permitSearchPanel.getAllEntrance().get(0);
		}else{
			defaultPermitZone = permitZone.get(0);
		}
		permitSearchPanel.verifyEntrance(defaultPermitZone);

		//2. Verify drop down list values
		permitSearchPanel.verifyAllEntrance(permitZone);
	}
}
