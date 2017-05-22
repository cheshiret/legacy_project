package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.interactions.viewasmap;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
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
	private UwpUnifiedSearchViewAsMapPage viewAsMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
	private String agencyFilterOption, letterFilterOption, resultFilterConten, selectedTypeFilterContent, selectedTypeAndAgencyFilterContent;
	
	public void execute() {
		//Setup check in UI-option
		MiscFunctions.verifyFiltersSetupInUiOption();
				
		web.invokeURL(url);

		//Get result filter content after going to view as map page
		web.gotoViewAsMapPage(bd);
		resultFilterConten = viewAsMapPg.getResultFiltersContent();
		//Get result filter content after clicking type filter
		viewAsMapPg.filterResults(UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_LINKTITLE, StringUtil.EMPTY, StringUtil.EMPTY);
		selectedTypeFilterContent = viewAsMapPg.getResultFiltersContent();
		//Get result filter content after clicking agency filter
		viewAsMapPg.filterResults(StringUtil.EMPTY, agencyFilterOption, StringUtil.EMPTY);
		selectedTypeAndAgencyFilterContent = viewAsMapPg.getResultFiltersContent();

		//1.Verify 4 clearing filters(x) display after clicking type, agency and letter filter
		viewAsMapPg.filterResults(StringUtil.EMPTY, StringUtil.EMPTY, letterFilterOption);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_TYPE, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_AGENCY, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME, true);

		//2.Verify content and clearing filters after releasing letter filter only
		viewAsMapPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
		viewAsMapPg.verifyResultFiltersContent(selectedTypeAndAgencyFilterContent);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_TYPE, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_AGENCY, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME, false);

		//3.Verify content and clearing filters after releasing letter and agency filters
		viewAsMapPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		viewAsMapPg.verifyResultFiltersContent(selectedTypeFilterContent);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_TYPE, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_AGENCY, false);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME, false);

		//4.Verify content and clearing filters after releasing letter, agency and type filters
		viewAsMapPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_TYPE);
		viewAsMapPg.verifyResultFiltersContent(resultFilterConten);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, false);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_TYPE, false);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_AGENCY, false);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME, false);
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

