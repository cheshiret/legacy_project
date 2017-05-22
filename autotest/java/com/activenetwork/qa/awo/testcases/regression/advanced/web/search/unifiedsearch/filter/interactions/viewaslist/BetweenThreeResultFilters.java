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
public class BetweenThreeResultFilters extends RecgovTestCase{
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private int specificTypeFilterNum, specificAgencyFilterNum, specificFirstLetterOfNameFilterNum;
	private String agencyFilterOption, letterFilterOption;

	public void execute() {
		//Setup check in UI-option
		MiscFunctions.verifyFiltersSetupInUiOption();
		
		web.invokeURL(url);

		//Go to view as list page to get specific type filter option number
		this.gotoViewAsListPage(bd);
		specificTypeFilterNum = viewAsListPg.getFacilityTypeFilterOptionResultNum(UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_LINKTITLE);

		//Click specific type filter option to check
		viewAsListPg.filterResults(UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_LINKTITLE, StringUtil.EMPTY, StringUtil.EMPTY);
		//Results summary
		viewAsListPg.verifySearchResultLabelEquals(viewAsListPg.generateSearchResultLabel(specificTypeFilterNum));
		//Total count for agency filter and letter filter
		viewAsListPg.verifyFilterResultTotalNum(specificTypeFilterNum, UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		viewAsListPg.verifyFilterResultTotalNum(specificTypeFilterNum, UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
		//Check only all and type clear filter(x) display
		viewAsListPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, true);
		viewAsListPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_TYPE, true);
		viewAsListPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_AGENCY, false);
		viewAsListPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME, false);
		//Get specific agency filter option count
		specificAgencyFilterNum = viewAsListPg.getAgencyFilterOptionNum(agencyFilterOption);

		//Click specific agency filter option to check
		viewAsListPg.filterResults(StringUtil.EMPTY, agencyFilterOption, StringUtil.EMPTY);
		//Results summary
		viewAsListPg.verifySearchResultLabelEquals(viewAsListPg.generateSearchResultLabel(specificAgencyFilterNum));
		//Total count for letter filter
		viewAsListPg.verifyFilterResultTotalNum(specificAgencyFilterNum, UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
		//Check all,  type and agency clear filter(x) display
		viewAsListPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, true);
		viewAsListPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_TYPE, true);
		viewAsListPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_AGENCY, true);
		viewAsListPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME, false);
		//Get specific letter filter option count
		specificFirstLetterOfNameFilterNum = viewAsListPg.getFirstLetterOfNameFilterOptionNum(letterFilterOption);

		//Click specific letter filter option to check
		viewAsListPg.filterResults(StringUtil.EMPTY, StringUtil.EMPTY, letterFilterOption);
		//Results summary
		viewAsListPg.verifySearchResultLabelEquals(viewAsListPg.generateSearchResultLabel(specificFirstLetterOfNameFilterNum));
		//Check all,  type, agency and letter filter clear filter(x) display
		viewAsListPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FILTERYOURRESULTS, true);
		viewAsListPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_TYPE, true);
		viewAsListPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_AGENCY, true);
		viewAsListPg.verifyClearingFilterExist(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME, true);
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

