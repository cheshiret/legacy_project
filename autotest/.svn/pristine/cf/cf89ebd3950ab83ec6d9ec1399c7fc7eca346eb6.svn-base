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
public class FiltersWithOnlyFilterOption extends RecgovTestCase{
	private UwpUnifiedSearchViewAsMapPage viewAsMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
	private String agencyFilterOption, letterFilterOption;

	public void execute() {
		//Setup check in UI-option
		MiscFunctions.verifyFiltersSetupInUiOption();

		web.invokeURL(url);
		web.gotoViewAsMapPage(bd);

		//Check only type filter
		viewAsMapPg.verifyOnlyTypeFilterName(UwpUnifiedSearch.FILTER_CAMPINGANDDAYUSE_NAME);

		//Check only agency filter after letter filter
		viewAsMapPg.filterResults(StringUtil.EMPTY, StringUtil.EMPTY, letterFilterOption);
		viewAsMapPg.verifyOnlyAgencyFilterName(agencyFilterOption);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);

		bd.parkId = "73389";
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema); //Sandy Lake
		bd.interestInValue = UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN;
        bd.contractCode = "NRSO";
        
		agencyFilterOption = DataBaseFunctions.getAgencyName(OrmsConstants.AGENCY_USARMYCORPSOFENGINEERS_ID, schema);
		letterFilterOption = "G"; //"H";
	}

}

