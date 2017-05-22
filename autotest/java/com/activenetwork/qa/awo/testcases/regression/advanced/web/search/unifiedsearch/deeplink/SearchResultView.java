package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.deeplink;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovPermitAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchHelpInfoPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
	Step1. Close all browser windows then open a new window;
	Step2. Paste a deep link of facility/rec area details page and press "Enter";
	Step3. Click on 'Find other facilities' link;
	Step4. Repeat step 1-2;
	Step5. Click on the google map inset(if applicable);

	Expect behavior:
	On step3:
	the system shall use context facility as the \ufffdWhere\ufffd location and execute a new search.  
	All other fields will remain as the default. Search results are in list view.

	On step5:
	the system shall use context facility as the \ufffdWhere\ufffd location and execute a new search.  
	All other fields will remain as the default. Search results are in map view.

 * @Preconditions:
 * @SPEC: Story N - Find Other Facilities Contextual
 * @Task#:AUTO-864
 * 
 * @author bzhang
 * @Date  Jan 16, 2012
 */
public class SearchResultView extends RecgovTestCase {
	private String[][] urls = new String[4][2];
	UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	
	public void execute(){
		//1: click Find Other Facilities hyperlink, verify the default page and search data populated.
		this.pasteUrlCheckDefaultPageAndSearchData(true);
		
//		//Sara[10/29/2013] Confirmed with Lisha and delete below check point for rec.gov using unified search
//		//2: click Find other facilities on map hyperlink,  verify the default page and search data populated.
//		this.pasteUrlCheckDefaultPageAndSearchData(false);
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		//Campground 
		urls[0][0] = url + "/camping/Big_Trinitcampingy_Cabin/r/campgroundDetails.do?contractCode=NRSO&parkId=70630&topTabIndex=Search";
		urls[0][1] = "BIG TRINITY CABIN";

		//Permit facility
		urls[1][0] = url + "/permits/Boundary_Waters_Canoe_Area_Wilderness/r/wildernessAreaDetails.do?page=detail&contractCode=NRSO&parkId=72600";
		urls[1][1] = "Boundary Waters Canoe Area Wilderness (Reservations)";

		//Tour facility
		urls[2][0] = url + "/tourParkDetail.do?contractCode=NRSO&parkId=72369";
		urls[2][1] = "PEARL HARBOR HISTORIC SITES (USS Arizona)";

		//Rec area
		urls[3][0] = url + "/recAreaDetails.do?contractCode=NRSO&recAreaId=437&agencyCode=130";
		urls[3][1] = DataBaseFunctions.getRecreationAreaName("437", DataBaseFunctions.getRIDBSchemaName(env)); //"Okeechobee Lake";

//      Based on Lisha's reply, I will ignore below test data.
//      We display \ufffdNo Map\ufffd pins for recreation areas or facilities which don\ufffdt have GPS information. 
//		On their details page there will be no Google map showing because no GPS  data in database.
//		urls[4][0] = url + "/recAreaDetails.do?contractCode=NRSO&recAreaId=1273&agencyCode=127";
//		urls[4][1] = "Archie Carr National Wildlife Refuge";
	}

	/**
	 * Verify default page and search Data
	 * @param clickFindOtherFacility  --true: Click "Find other facility" link
	 *                                --false: Click "Find other facilities on map" link 
	 *                                         or "find the Google map"
	 */
	private void pasteUrlCheckDefaultPageAndSearchData(boolean clickFindOtherFacility){
		RecgovParkDetailsPage campGroundDetailPg = RecgovParkDetailsPage.getInstance();
		RecgovViewAsListPage parkViewPg = RecgovViewAsListPage.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchViewAsMapPage mapViewPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		UwpRecreationAreaDetailsPage recreationAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();
		RecgovPermitAreaDetailsPage permitAreaDetailsPg = RecgovPermitAreaDetailsPage.getInstance();
		UwpUnifiedSearchHelpInfoPage helpPage=UwpUnifiedSearchHelpInfoPage.getInstance();
		
		for(int i = 0 ; i < urls.length; i ++ ){
			String tempUrl = urls[i][0];
			searchData.whereTextValue = urls[i][1];

			web.invokeURL(tempUrl);
			Object page = browser.waitExists(campGroundDetailPg, permitAreaDetailsPg, recreationAreaDetailsPg);
			
			if(clickFindOtherFacility){
				if(page == campGroundDetailPg){
					campGroundDetailPg.clickFindOtherFacilities();
					parkViewPg.waitLoading();
				}else if(page == permitAreaDetailsPg){
					permitAreaDetailsPg.clickFindOtherFacilities();
					parkViewPg.waitLoading();
				}else {
					recreationAreaDetailsPg.clickBackToSearch();
					helpPage.waitLoading();
				}
				
			}else{
				//if Find other facilities on map hyperlink exist, click it. 
				//otherwise click find the Google map on the left top conner of the page;
				if(campGroundDetailPg.checkFindOtherFacilitiesOnMap()){
					campGroundDetailPg.clickFindOtherFacilitiesOnMap();
				}else{
					campGroundDetailPg.clickFacilityGoogleMap();
				}
				mapViewPg.waitLoading();
			}
			searchPanel.verifySearchCriteria(searchData);
		}
	}
}
