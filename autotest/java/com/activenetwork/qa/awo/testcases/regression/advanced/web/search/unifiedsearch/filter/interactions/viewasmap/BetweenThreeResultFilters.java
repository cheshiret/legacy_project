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
public class BetweenThreeResultFilters extends RecgovTestCase{
	private UwpUnifiedSearchViewAsMapPage viewAsMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
	private int specificTypeFilterNum, specificAgencyFilterNum, specificFirstLetterOfNameFilterNum;
	private String agencyFilterOption, letterFilterOption;

	public void execute() {
		//Setup check in UI-option
		MiscFunctions.verifyFiltersSetupInUiOption();
		web.invokeURL(url);

		//Go to view as map page to get specific type filter option number
		web.gotoViewAsMapPage(bd);
		specificTypeFilterNum = viewAsMapPg.getFacilityTypeFilterOptionResultNum(UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_LINKTITLE);

		//2. Click specific type filter option to check
		viewAsMapPg.filterResults(UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_LINKTITLE, StringUtil.EMPTY, StringUtil.EMPTY);
		//Results summary
		viewAsMapPg.verifySearchResultLabel(viewAsMapPg.generateSearchResultLabel(specificTypeFilterNum));
		//Total count for agency filter and letter filter
		viewAsMapPg.verifyAgencyFilterResultTotalNum(specificTypeFilterNum);
		viewAsMapPg.verifyFirstLetterOfNameFilterResultTotalNum(specificTypeFilterNum);
		//Check only all and type clear filter(x) display
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_TYPE, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_AGENCY, false);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME, false);
		//Get specific agency filter option count
		specificAgencyFilterNum = viewAsMapPg.getAgencyFilterOptionNum(agencyFilterOption);

		//3. Click specific agency filter option to check
		viewAsMapPg.filterResults(StringUtil.EMPTY, agencyFilterOption, StringUtil.EMPTY);
		//Results summary
		viewAsMapPg.verifySearchResultLabel(viewAsMapPg.generateSearchResultLabel(specificAgencyFilterNum));
		//Total count for letter filter
		viewAsMapPg.verifyFirstLetterOfNameFilterResultTotalNum(specificAgencyFilterNum);
		//Check all,  type and agency clear filter(x) display
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_TYPE, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_AGENCY, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME, false);
		//Get specific letter filter option count
		specificFirstLetterOfNameFilterNum = viewAsMapPg.getFirstLetterOfNameFilterOptionNum(letterFilterOption);

		//4. Click specific letter filter option to check
		viewAsMapPg.filterResults(StringUtil.EMPTY, StringUtil.EMPTY, letterFilterOption);
		//Results summary
		viewAsMapPg.verifySearchResultLabel(viewAsMapPg.generateSearchResultLabel(specificFirstLetterOfNameFilterNum));
		//Check all,  type, agency and letter filter clear filter(x) display
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_TYPE, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_AGENCY, true);
		viewAsMapPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME, true);
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

