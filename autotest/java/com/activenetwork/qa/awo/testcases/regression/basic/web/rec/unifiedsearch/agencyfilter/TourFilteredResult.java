package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.unifiedsearch.agencyfilter;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: P
 * Verify search result label and all search result display after agency(National Park Service) filter
 * @SPEC: Agency filter - verify the filtered results (Permits, Tours) [TC:043132] 
 * @Task#: AUTO-1230 
 * 
 * @author SWang
 * @Date  Sep 24, 2012
 */
public class TourFilteredResult extends RecgovTestCase {
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();

	private String agencyName, searchResultLabel;
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
		viewAsListPg.verifyAllParkNames(oRMSFacilityAndAreaNamesFromDB, oRMSFacilityAndAreaNamesFromUI);

		//All displaying parks are within selected agency
		oRMSFacilityAndAreaAgencyFromUI = viewAsListPg.getAllParksAgency();
		viewAsListPg.verifyParksAgency(oRMSFacilityAndAreaAgencyFromUI, agencyName);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		searchData.whereTextValue = "VIRGINIA";
		agencyName = DataBaseFunctions.getAgencyName(OrmsConstants.AGENCY_NATIONALPARKSERVICE_ID, schema);
		searchData.interestInValue = UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN;

		oRMSFacilityAndAreaNamesFromDB.addAll(web.queryContractFacilityNames(schema, "", OrmsConstants.AGENCY_NATIONALPARKSERVICE_ID, OrmsConstants.PRDCAT_TICKET, ""));
	}
}