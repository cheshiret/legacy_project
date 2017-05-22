package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.interactions.viewasmap;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: P
 * Check search panel and result filter after clicking clear search link and home LOGO
 * Details checking points can be find in local case
 * @Preconditions:
 * @SPEC: Filters - clear search, home page [TC:043133] 
 * @Task#: AUTO-1233 
 * 
 * @author SWang
 * @Date  Oct 8, 2012
 */
public class ClearSearchAndHomePg extends RecgovTestCase{
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private UwpUnifiedSearchViewAsMapPage viewAsMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();

	public void execute() {
		//Setup check in UI-option
		MiscFunctions.verifyFiltersSetupInUiOption();

		web.invokeURL(url);
		web.gotoViewAsMapPage(bd);
		viewAsMapPg.verifyResultFiltersDisplaying(true);

		//Go to home page to check 
		//All the data entry and selection of search form shall be retained
		//All the filters below the search form shall be gone; 
		web.gotoHomePage();
		searchPanel.verifySearchCriteria(bd,true);
		searchPanel.verifyNoResultFilter();

		//Click 'Search', result filter displays
		web.gotoViewAsListPageFromSearchPanel();
		viewAsMapPg.verifyResultFiltersDisplaying(true);

		//Click 'Log in' link, then 'home' LOGO 
		//All the data entry and selection of search form shall be retained
		//All the filters below the search form shall be gone; 
		web.gotoLogInPage();
		web.gotoHomePage();
		searchPanel.verifySearchCriteria(bd,true);
		searchPanel.verifyNoResultFilter();

		//Click 'Search', click the 'x' clear search
		//Reset the search form to initial status
		//All the filters below the search form shall be gone;
		web.gotoViewAsListPageFromSearchPanel();
		searchPanel.clearSearch();
		searchPanel.verifyInterestInitializedUI(UwpUnifiedSearch.DEFAULT_INSTERETED_IN);
		searchPanel.verifyNoResultFilter();


		//Run a search, click home LOGO, click 'x' clear search to check 
		//Reset the search form to initial status
		//All the filters below the search form shall be gone;
		this.gotoViewAsListPage(bd);
		web.gotoHomePage();
		searchPanel.clearSearch();
		searchPanel.verifyInterestInitializedUI(UwpUnifiedSearch.DEFAULT_INSTERETED_IN);
		searchPanel.verifyNoResultFilter();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		bd.parkId = "73389";
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema);
		bd.interestInValue = UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN;
		bd.lookFor = "RV sites";
		bd.length = "10";
		bd.moreOptions = true;
		bd.electricHookupValue = "15 Amp (or more)";
		bd.pullthroughDriveWay = true;
		bd.arrivalDate = DateFunctions.getDateAfterToday(0);
		bd.flexibleValue = UwpUnifiedSearch.FLEXIBLE_NEXT2WEEK;
		bd.lengthOfStay = "2";
		bd.petsAllowed = true;
	}
}
