package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.interactions.viewaslist;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: p
 * Check the total count for each filter;
 * 1) Add all the count up for Type filter, it shall match the results summary "XX" ("Search Results: 1-10 of XX"); 
 * 2) Add all the count up for Agency filter, it shall match the results summary "XX" ("Search Results: 1-10 of XX"); 
 * 3) Add all the count up for Letter filter, it shall match the results summary "XX" ("Search Results: 1-10 of XX")

 * @Preconditions:
 * @SPEC: Interatcions between filters (Type, Agency and Letter) below the search form [TC:043199] 
 * @Task#: AUTO-1233 
 * 
 * @author SWang5
 * @Date  Oct 9, 2012
 */
public class ResultFilterTotalNum extends RecgovTestCase{
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private int totalCount;

	public void execute() {
		//Setup check in UI-option
		MiscFunctions.verifyFiltersSetupInUiOption();

		web.invokeURL(url);

		//Go to view as list page to get total result count
		this.gotoViewAsListPage(bd);
		totalCount = viewAsListPg.getTotalSearchResultNum();

		//Check the total count for each filter are same and match the results summary
		viewAsListPg.verifyFilterResultTotalNum(totalCount, UwpUnifiedSearch.FILTERCATEGORY_TYPE);
		viewAsListPg.verifyFilterResultTotalNum(totalCount, UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		viewAsListPg.verifyFilterResultTotalNum(totalCount, UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);

		bd.whereTextValue = "MINNESOTA";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
	}
}
