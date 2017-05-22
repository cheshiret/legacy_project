package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.unifiedsearch.agencyfilter;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: p
 * Verify search result label and all search result display after agency(National Park Service) filter
 * 
 * All park under state(Washington) and agency(National Park Service)
 * select id, name from d_loc where cd like '|1|70904|%' and state='WA' and LEVEL_NUM=40 and delete_ind=0 and active_ind=1 order by name; 
 * 
 * @SPEC: Agency filter - verify the filtered results (ORMS facilities - campground) [TC:043131] 
 * @Task#: AUTO-1230 
 * 
 * @author SWang
 * @Date  Sep 20, 2012
 */
public class CampingLodgingFilteredResult extends RecgovTestCase {
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();

	private String stateCode, agencyCode, agencyName, searchResultLabel;
	List<String> oRMSFacilityAndAreaNamesFromDB = new ArrayList<String>();
	List<String> oRMSFacilityAndAreaNamesFromUI = new ArrayList<String>();
	List<String> oRMSFacilityAndAreaAgencyFromUI = new ArrayList<String>();

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(searchData);

		//Verify specific agency filter options number
		viewAsListPg.verifyAgencyFilterOptionNum(agencyName, oRMSFacilityAndAreaNamesFromDB.size());

		//Make type and agency filter to view as list page
		viewAsListPg.filterResults("", agencyName, "");

		//Total search result
		searchResultLabel = viewAsListPg.generateSearchResultLabel(oRMSFacilityAndAreaNamesFromDB.size());
		viewAsListPg.verifySearchResultLabelEquals(searchResultLabel);

		//All expected parks display
		oRMSFacilityAndAreaNamesFromUI = viewAsListPg.getAllParkNames();
		viewAsListPg.gotoFirstPage();
		viewAsListPg.verifyAllParkNames(oRMSFacilityAndAreaNamesFromDB, oRMSFacilityAndAreaNamesFromUI);

		//All displaying parks are within selected agency
		oRMSFacilityAndAreaAgencyFromUI = viewAsListPg.getAllParksAgency();
		viewAsListPg.verifyParksAgency(oRMSFacilityAndAreaAgencyFromUI, agencyName);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		searchData.whereTextValue = "WASHINGTON"; //State
		searchData.selectExactOption = true;
		stateCode = "WA";
		agencyCode = "70904"; 
		agencyName = DataBaseFunctions.getAgencyName(agencyCode, schema);//"National Park Service";
		searchData.interestInValue = UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN;

		oRMSFacilityAndAreaNamesFromDB.addAll(web.queryContractFacilityNames(schema, stateCode, agencyCode, "", ""));
	}
}
