package com.activenetwork.qa.awo.testcases.regression.basic.web.rec.unifiedsearch;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovPermitAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: Passed under RFT. will use RFT to run, not Selenium because click() doen't work when trying to click park pin in 'View as Map' page under Selenium
 * 1. Using sample data to run a search;
 * 2. Click on the facility name in the top result;
 * 3. Click on 'Find Other Facilities' link; 
 * Expected result: It goes back to pervious search results page in Step 1 in list view; all the information on the seach form shall be retained; 
 * 4. Click on the facility name in another result other than the top target facility;
 * 5. Click on 'Find Other Facilities' link;
 * Expected result: It goes back to pervious search results page in Step 1 in list view; all the information on the seach form shall be retained; 
 * 
 * 6. Using sample data to run a search;
 * 7. Click on the facility name in the top result;
 * 8. Click on the google map below the link of 'Find Other Facilities';
 * Expected Result: It goes back to pervious search results page in Step 6 in map view; all the entry data and selection on the seach form shall be retained
 * 9. Click on the facility name in the bubble of another result other than the top result in Map view; 
 * Expected result : Click on 'Find Other Facilities' link;
 * 10. Click on 'Find Other Facilities' link;
 * Expected result: It goes back to pervious search results page in Step 6 in map view; all the information on the seach form shall be retained;
 * 
 * @Preconditions:
 * @SPEC: 'Interested in' - drop down mode ('Find other facilities'flow) [TC:043033] 
 * @Task#: AUTO-1234 
 * 
 * @author SWang
 * @Date 9/17/2012
 */
public class InterestInFindOtherFacilitiesFlow extends RecgovTestCase {
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearchViewAsMapPage viewAsMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
	private RecgovPermitAreaDetailsPage permitAreaDetailsPg = RecgovPermitAreaDetailsPage.getInstance();
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();

	private String secondParkNameOnList, secondParkIDOnList, parkNameOnMap , parkIdOnMap, parkStateOnMap;

	public void execute() {
		web.invokeURL(url);

		//Go to view as list page, get second park name and id
		this.gotoViewAsListPage(searchData);
		secondParkNameOnList = viewAsListPg.getSecondParkName();
		secondParkIDOnList = DataBaseFunctions.getFacilityID(secondParkNameOnList, schema);

		//For first park, click 'Find Other Facilities' link in camp ground details page to check
		web.gotoFacilityDetailsPageFromViewAsListPage(searchData.contractCode, searchData.parkId);
		web.findOtherFacilities(viewAsListPg);
		viewAsListPg.verifyFirstParkName(searchData.whereTextValue);
		viewAsListPg.verifySecondParkName(secondParkNameOnList);
		searchData.arrivalDate = UwpUnifiedSearch.ARRIVALDATE_MMDDYY;
		searchPanel.verifySearchCriteria(searchData); //Sara[20140414], DEFECT-61501's check point is verified in this case, so please don't update it

		//For second park, click 'Find Other Facilities' link in camp ground details page to check
		web.gotoFacilityDetailsPageFromViewAsListPage(searchData.contractCode, secondParkIDOnList);
		web.findOtherFacilities(viewAsListPg);
		viewAsListPg.verifyFirstParkName(searchData.whereTextValue);
		viewAsListPg.verifySecondParkName(secondParkNameOnList);
		searchPanel.verifySearchCriteria(searchData);

		//Go to view as list page using the same park name, but different interest in
		searchData.interestInValue = UwpUnifiedSearch.PERMITSANDWILDERNESS_INSTERETED_IN;
		this.gotoViewAsListPage(searchData);

		//Sara[11/5/2013], no 'Find Other Facilities on map' link in rec.gov website now
		//		//For first park, click 'Find Other Facilities on map' link to view as map page 
		//		web.gotoFacilityDetailsPageFromViewAsListPage(searchData.contractCode, searchData.parkId);
		//		this.findOtherFacilitiesOnMapFunctional();
		//		searchPanel.verifySearchCriteria(searchData);
		web.gotoViewAsMapFromViewAsList();

		//For other park other than the first park, click park name from map pin widget
		parkIdOnMap = String.valueOf(viewAsListPg.getSecondParkID()); //"72203";
		parkNameOnMap = DataBaseFunctions.getFacilityName(parkIdOnMap, schema);
		parkStateOnMap = DataBaseFunctions.getStateByParkId(parkIdOnMap, schema);
		web.gotoPermitAreaDetailstPageFromViewAsMapPage(searchData.contractCode, parkIdOnMap);
		permitAreaDetailsPg.verifyPermitParkNameAndRelatedStateCode(parkNameOnMap, parkStateOnMap); 
		web.findOtherFacilities(viewAsMapPg);
		searchPanel.verifySearchCriteria(searchData);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		searchData.contractCode = "NRSO";
		//		searchData.parkId = "70928"; //Lower Pines 		
		//		searchData.whereTextValue = DataBaseFunctions.getFacilityName(searchData.parkId, schema);
		searchData.parkId = "73318";
		searchData.whereTextValue = DataBaseFunctions.getFacilityName(searchData.parkId, schema); //OUTLET (POMONA LAKE), KS
		searchData.interestInValue = UwpUnifiedSearch.DAYUSEPICNICAREAS_INSTERETED_IN;
		searchData.flexibleValue = UwpUnifiedSearch.FLEXIBLE_DEFAULT;
	}

	/**
	 * In camp ground details page, click 'Find Other Facilities on map' link to view as map page
	 */
	private void findOtherFacilitiesOnMapFunctional(){
		RecgovParkDetailsPage campGroundDetailPg = RecgovParkDetailsPage.getInstance();
		campGroundDetailPg.clickFacilityGoogleMap();
		viewAsMapPg.waitLoading();
		viewAsMapPg.waitMapLoadingComplete();
	}
}
