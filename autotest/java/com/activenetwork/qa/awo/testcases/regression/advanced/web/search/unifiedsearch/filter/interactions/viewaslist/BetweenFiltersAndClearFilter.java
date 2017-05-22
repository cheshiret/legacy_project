package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.interactions.viewaslist;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: p
 * @Preconditions:
 * @SPEC: Interatcions between filters (Type, Agency and Letter) below the search form [TC:043199] 
 * @Task#: AUTO-1233 
 * 
 * @author SWang5
 * @Date  Oct 9, 2012
 */
public class BetweenFiltersAndClearFilter extends RecgovTestCase{
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private String agencyFilterOption, letterFilterOption, resultFilterConten, selectedTypeFilterContent, selectedTypeAndAgencyFilterContent;
	
	public void execute() {
		//Setup check in UI-option
		MiscFunctions.verifyFiltersSetupInUiOption();
		
		web.invokeURL(url);

		//Get result filter content after going to view as list page
		this.gotoViewAsListPage(bd);
		resultFilterConten = viewAsListPg.getResultFiltersContent();
		//Get result filter content after clicking type filter
		viewAsListPg.filterResults(UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_LINKTITLE, StringUtil.EMPTY, StringUtil.EMPTY);
		selectedTypeFilterContent = viewAsListPg.getResultFiltersContent();
		//Get result filter content after clicking agency filter
		viewAsListPg.filterResults(StringUtil.EMPTY, agencyFilterOption, StringUtil.EMPTY);
		selectedTypeAndAgencyFilterContent = viewAsListPg.getResultFiltersContent();

		//1.Verify 4 clearing filters(x) display after clicking type, agency and letter filter
		viewAsListPg.filterResults(StringUtil.EMPTY, StringUtil.EMPTY, letterFilterOption);
		viewAsListPg.verifyClearFilterDisplay(true, UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS);
		viewAsListPg.verifyClearFilterDisplay(true, UwpUnifiedSearch.FILTERCATEGORY_TYPE);
		viewAsListPg.verifyClearFilterDisplay(true, UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		viewAsListPg.verifyClearFilterDisplay(true, UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);

		//2.Verify content and clearing filters after releasing letter filter only
		viewAsListPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
		viewAsListPg.verifyResultFiltersContent(selectedTypeAndAgencyFilterContent);
		viewAsListPg.verifyClearFilterDisplay(true, UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS);
		viewAsListPg.verifyClearFilterDisplay(true, UwpUnifiedSearch.FILTERCATEGORY_TYPE);
		viewAsListPg.verifyClearFilterDisplay(true, UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		viewAsListPg.verifyClearFilterDisplay(false, UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);

		//3.Verify content and clearing filters after releasing letter and agency filters
		viewAsListPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		viewAsListPg.verifyResultFiltersContent(selectedTypeFilterContent);
		viewAsListPg.verifyClearFilterDisplay(true, UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS);
		viewAsListPg.verifyClearFilterDisplay(true, UwpUnifiedSearch.FILTERCATEGORY_TYPE);
		viewAsListPg.verifyClearFilterDisplay(false, UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		viewAsListPg.verifyClearFilterDisplay(false, UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);

		//4.Verify content and clearing filters after releasing letter, agency and type filters
		viewAsListPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_TYPE);
		viewAsListPg.verifyResultFiltersContent(resultFilterConten);
		viewAsListPg.verifyClearFilterDisplay(false, UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS);
		viewAsListPg.verifyClearFilterDisplay(false, UwpUnifiedSearch.FILTERCATEGORY_TYPE);
		viewAsListPg.verifyClearFilterDisplay(false, UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		viewAsListPg.verifyClearFilterDisplay(false, UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		agencyFilterOption = DataBaseFunctions.getAgencyName(OrmsConstants.AGENCY_USFORESTSERVICE_ID, schema);
		
		bd.whereTextValue = "MINNESOTA";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		letterFilterOption = "C";
	}
}

