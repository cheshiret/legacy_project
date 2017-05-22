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
 * 
 * @Description: Goto River List page after clicking specific permit type from Park view as List page.
 * 1. Go to River List page
 * 2. Looking for drop down list value and showed value
 * 3. River drop down list value and showed value
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

		searchResult.clickFirstParkPermitTypes(0);
		entranceList.waitLoading();

		entranceList.verifyEntranceListTabDisplaying(RecgovEntranceListPage.RIVER_LIST, true);
		entranceList.verifySearchResultType(RecgovEntranceListPage.RIVER_SEARCH_RESULTS);
	}

	private void verifyLookingForAndEntranceValue(){
		RecgovEntranceListPage entranceList = RecgovEntranceListPage.getInstance();
		UwpUnifiedFindPermitsPanel permitSearchPanel = UwpUnifiedFindPermitsPanel.getInstance();

		//Verify 'Looking for' field
		//1.Verify clicked permit type is populated in this field
		permitSearchPanel.verifyPermitTypeValue(permitTypes[0]);
		//2.Verify drop down list values
		permitSearchPanel.verifyAllPermitType(permitTypes);

		//Verify 'River List' field
		List<String> rivers = entranceList.getAllEntrances();

		//1.Verify default value
		String defaultRiver = "";
		if(rivers.size()>1){
			defaultRiver = permitSearchPanel.getAllEntrance().get(0);
		}else{
			defaultRiver = rivers.get(0);
		}
		permitSearchPanel.verifyEntrance(defaultRiver);

		//2. Verify drop down list values
		permitSearchPanel.verifyAllEntrance(rivers);
	}
}
