package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * Verify Buck Ridge displays as Target Location
 * Verify the Target displays as Day Use and Picnic Shelter label
 * Verify the Target does not display the distance
 * Verify [312] results display with the 200 miles radius distance, the total number [312] will be get from DB query.
 * Verify the Footnote for the distance display is [* in straight line, not driving distance"] appears on the tab
 * @Preconditions:
 * @SPEC: StoryC
 * @Task#: Auto-1818
 * 
 * @author bzhang, Swang
 * @Date  Oct 19, 2011, Aug 9, 2013
 */
public class FacilityHasLatsAndLongs extends RecgovTestCase {
	private RecgovViewAsListPage parkViewListPg = RecgovViewAsListPage.getInstance();
	private String ridbSchema, latitude, longitude, expectedFacility, nearText, siteTitle, recFacilityID, parentFacilityOrRegion;
	private List<String> recFacilityInDB = new ArrayList<String>();
	private int distance;

	public void execute() {
		web.invokeURL(url);
		web.gotoViewAsListPage(bd);

		//1: verify the lable day use and picnic area: displays
		parkViewListPg.verifyFirstParkSiteTypesTitle(siteTitle);		

		//2: verify first facility view header format
		this.verifyFirstFacilityViewHeaderFormat(bd.whereTextValue, parentFacilityOrRegion, "California", "US Army Corps of Engineers");	

		//3: verify search result near summary info.
		this.verifySearchResultSummaryInfo(nearText);

		//4: verify search result header summary info.
		//this.verifySearchResultSummaryInfo(headerText, true);
		this.verifyExpectedFacilityInViewList(expectedFacility);

	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		bd.parkId = "73103";
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"BUCK RIDGE";
		bd.park = bd.whereTextValue;
		bd.contractCode = "NRSO";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.stateCode = "CA";

		//used for calculate near by facilities
		ridbSchema = DataBaseFunctions.getRIDBSchemaName(env);
//		distance = Integer.parseInt(TestProperty.getProperty("unified.search.distance"));
		distance = (int)Double.parseDouble(TestProperty.getProperty("unified.search.distance"));

		latitude = web.queryLatitudeInfo(schema, bd.parkId);
		longitude = web.queryLongitudeInfo(schema, bd.parkId);

		recFacilityInDB = web.queryNearByRIDBFacilities(ridbSchema, latitude, longitude, distance);
		if(recFacilityInDB.toString().contains("243524")){
			recFacilityID = "243524";
		}else recFacilityID = recFacilityInDB.get(0);
		expectedFacility = DataBaseFunctions.getFacilityNameFromRidb(recFacilityID, ridbSchema);

		nearText = "Results near "+bd.whereTextValue+" [ * in straight line, not driving distance ]";
		siteTitle = "Day use and Picnic areas";
		
		parentFacilityOrRegion = DataBaseFunctions.getRecreationAreaName("481", ridbSchema); //Hensley Lake
	}

	/**
	 * verify expected facility display in view list.
	 * @param facilityName
	 */
	public void verifyExpectedFacilityInViewList(String facilityName){
		boolean isEqual = false;
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
//		List<FacilityData> allFacilities = searchResult.getAllFacilityInfo();
		do {
			List<String> allFacilityNames = searchResult.getFirstPgParkNames();
			if (allFacilityNames.contains(facilityName)) {
				isEqual = true;
				break;
			}
		} while (!isEqual && searchResult.gotoNext());
//		for(int i=0;i<allFacilities.size();i++){
//			if(allFacilities.get(i).facilityName.equals(facilityName)){
//				isEqual = true;
//			}
//		}
		if(!isEqual){
			throw new ErrorOnPageException("The facility"+facilityName+"should be display on view list");
		}
	}

	/**
	 * Verify first facility view header format based on given Facility Name, Parent Facility or Region and State, Agency
	 * @param facilityName
	 * @param parentFacilityOrRegion
	 * @param state
	 * @param agency
	 */
	private void verifyFirstFacilityViewHeaderFormat(String facilityName, String parentFacilityOrRegion, String state, String agency){
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		String facilityInfoRegx = facilityName+" ?, "+state+" part of"+parentFacilityOrRegion + " ?, " + agency+".*";
		String firstFacilityViewHeader = searchResult.getFirstFacilityViewHeader();
		if(!MiscFunctions.matchString("First facility view header", firstFacilityViewHeader, facilityInfoRegx)){
			throw new ErrorOnDataException();
		}
	}

	/**
	 * verify the search result list header info match with the given text.
	 * @param expectMsg
	 * @param isHeader
	 */
	private void verifySearchResultSummaryInfo(String expectMsg){
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		String actualMsg = searchResult.getViewHeaderNearValue();
		if (!MiscFunctions.compareResult("Search result summary info", expectMsg, actualMsg)){
			throw new ErrorOnDataException();
		}
	}
}
