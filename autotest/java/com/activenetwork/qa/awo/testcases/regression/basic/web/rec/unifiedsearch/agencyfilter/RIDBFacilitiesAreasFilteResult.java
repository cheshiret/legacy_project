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
 * @Description: p
 * Verify search result label and all search result display after Type(Other Recreation Facilities) and agency(Fish and Wildlife Service) filter
 * 
 * All RIDB area:
 * select r.recareaid, r.recareaname from recarea r, recareaaddress a where r.recareaid in (select recareaid from orgrecarea where orgid=127) and r.recareaid=a.recareaid and a.addressstatecode='WA'order by r.recareaname; 
 * 
 * All RIDB facility:
 * select f.facilityid, f.facilityname from facility f, facilityaddress a where f.facilityid in (select facilityid from orgfacility where orgid=127) and f.facilityid=a.facilityid and a.addressstatecode='WA'order by f.facilityname; 
 * 
 * @SPEC: Agency filter - verify the filtered results (RIDB facilities and areas) [TC:043130] 
 * @Task#: AUTO-1230 
 * 
 * @author SWang
 * @Date  Sep 20, 2012
 */
public class RIDBFacilitiesAreasFilteResult extends RecgovTestCase {
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();

	private String stateCode, searchResultLabel, agencyName, contractSchema;
	List<String> rIDBFacilityAndAreaNamesFromDB = new ArrayList<String>();
	List<String> rIDBFacilityAndAreaNamesFromUI = new ArrayList<String>();
	List<String> oRMSFacilityAndAreaAgencyFromUI = new ArrayList<String>();

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(searchData);

		//Verify specific agency filter options number
		viewAsListPg.verifyAgencyFilterOptionNum(agencyName, rIDBFacilityAndAreaNamesFromDB.size());
		
		//Make type and agency filter to view as list page
		viewAsListPg.filterResults(UwpUnifiedSearch.FILTER_OTHERACTIVITIES_LINKTITLE, agencyName, "");

		//Total search result
		searchResultLabel = viewAsListPg.generateSearchResultLabel(rIDBFacilityAndAreaNamesFromDB.size());
		viewAsListPg.verifySearchResultLabelEquals(searchResultLabel);

		//All expected parks display
		rIDBFacilityAndAreaNamesFromUI = viewAsListPg.getAllParkNames();
		viewAsListPg.verifyAllParkNames(rIDBFacilityAndAreaNamesFromDB, rIDBFacilityAndAreaNamesFromUI, false);
		
		//All displaying parks are within selected agency
		oRMSFacilityAndAreaAgencyFromUI = viewAsListPg.getAllParksAgency();
		viewAsListPg.verifyParksAgency(oRMSFacilityAndAreaAgencyFromUI, agencyName);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getRIDBSchemaName(env);
		contractSchema = DataBaseFunctions.getSchemaName("NRRS", env);
		searchData.whereTextValue = "WASHINGTON"; //State
		searchData.selectExactOption = true;
		stateCode = "WA";
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		agencyName = DataBaseFunctions.getAgencyName(OrmsConstants.AGENCY_FISHANDWILDLIFESERVICE_ID, contractSchema);
	
		rIDBFacilityAndAreaNamesFromDB.addAll(web.queryStateRIDBFacilityNames(schema, stateCode, "127"));
		rIDBFacilityAndAreaNamesFromDB.addAll(web.queryStateRIDBAreaNames(schema, stateCode, "127", "'12830'")); //San Juan Wilderness
	}
}
