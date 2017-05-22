package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.deeplink;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovPermitAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (Block by known issue->DEFECT-34736->Defect-40014)
		Step1. Close all browser windows then open a new window;
		Step2. Enter "flor" in 'Where' field and search to get a suggestion page;
		Step3. Paste a deep link of facility/rec area details page and press "Enter";
		Step4. Click on 'Find other facilities' link or google map inset (if applicable);

	Expect behavior:
		On step4:
	  It shows suggestion page and all input/selection on search form shall be retained.

 * @Preconditions:
 * @SPEC: Story N - Find Other Facilities Contextual
 * @Task#:AUTO-864
 * 
 * @author SWang
 * @Date  Mar 1, 2012
 */
public class PasteLinkAfterSuggestionPg extends RecgovTestCase {
	private String[][] urls = new String[3][2]; //6 Sara[20140408] DEFECT-40014 is closed by steve as won't fix because 1yr ago, still pending. So comment related check point. 
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();
	private String ridbSchema;

	public void execute(){
		//1: click Find Other Facilities hyperlink, verify the default page and search data populated.
		this.pasteUrlCheckDefaultPageAndSearchData(true);
		//2: click Find other facilities on map hyperlink, verify the default page and search data populated.
		this.pasteUrlCheckDefaultPageAndSearchData(false);
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		ridbSchema = DataBaseFunctions.getRIDBSchemaName(env);

		searchData.whereTextValue = "flor";
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		//Campground 
		urls[0][0] = url + "/camping/Big_Trinity_Cabin/r/campgroundDetails.do?contractCode=NRSO&parkId=70630&topTabIndex=Search";
		urls[0][1] = DataBaseFunctions.getFacilityName("70630", schema); //"BIG TRINITY CABIN";

		//Permit facility
		urls[1][0] = url + "/permits/Boundary_Waters_Canoe_Area_Wilderness/r/wildernessAreaDetails.do?page=detail&contractCode=NRSO&parkId=72600";
		urls[1][1] = DataBaseFunctions.getFacilityName("72600", schema); //"Boundary Waters Canoe Area Wilderness (Reservations)";

		//Tour facility
		urls[2][0] = url + "/tourParkDetail.do?contractCode=NRSO&parkId=72369";
		urls[2][1] = DataBaseFunctions.getFacilityName("72369", schema); //"PEARL HARBOR HISTORIC SITES (USS Arizona)";

//		//Rec area
//		urls[3][0] = url + "/recAreaDetails.do?contractCode=NRSO&recAreaId=437&agencyCode=130";
//		urls[3][1] = DataBaseFunctions.getRecreationAreaName("437", ridbSchema); //"Okeechobee Lake";
//
//		urls[4][0] = url + "/recAreaDetails.do?contractCode=NRSO&recAreaId=1273&agencyCode=127";
//		urls[4][1] = DataBaseFunctions.getRecreationAreaName("1273", ridbSchema); //"Archie Carr National Wildlife Refuge";
//
//		//RIDB
//		urls[5][0] = url + "/recAreaDetails.do?contractCode=NRSO&recAreaId=1061&agencyCode=70903"; 
//		urls[5][1] = DataBaseFunctions.getRecreationAreaName("1061", ridbSchema); //Angeles National Forest
	}

	/**
	 * Verify default page and search Data
	 * @param clickFindOtherFacility  --true: Click "Find other facility" link
	 *                                --false: Click "Find other facilities on map" link 
	 *                                         or "find the Google map"
	 */
	private void pasteUrlCheckDefaultPageAndSearchData(boolean clickFindOtherFacility){
		RecgovParkDetailsPage campGroundDetailPg = RecgovParkDetailsPage.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPg = UwpUnifiedSearchSuggestionPage.getInstance();
		UwpRecreationAreaDetailsPage recreationAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();
		RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
		RecgovPermitAreaDetailsPage permitAreaDetailsPg = RecgovPermitAreaDetailsPage.getInstance();
		
		for(int i = 0 ; i < urls.length; i ++ ){
			//1: Enter associated sample data to go to suggestion page
			web.invokeURL(url);
			web.makeUnifiedSearch(searchData);
			suggestionPg.waitLoading();
			searchPanel.verifySearchCriteria(searchData);

			//2:Paste a deep link of Campground/Permit/Tour/rec area details page and press "Enter";
			String tempUrl = urls[i][0];
			web.invokeURL(tempUrl,false,false);
			Object page = browser.waitExists(campGroundDetailPg, permitAreaDetailsPg, recreationAreaDetailsPg);

			//3:Click on 'Find other facilities' link or google map inset (if applicable);
			if(clickFindOtherFacility){
				if(page == campGroundDetailPg){
					campGroundDetailPg.clickFindOtherFacilities();
				}else if(page == permitAreaDetailsPg){
					permitAreaDetailsPg.clickFindOtherFacilities();
				}else recreationAreaDetailsPg.clickBackToSearch();
			}else{
				if(i==urls.length-1){//The last RIDB PARK no map link
					logger.info("It is the RIDB facility without map pin.");
					break;
				}else{
					//if Find other facilities on map hyperlink exist, click it. 
					//otherwise click find the Google map on the left top conner of the page;
					if(page == campGroundDetailPg){
						if(campGroundDetailPg.checkFindOtherFacilitiesOnMap()){
							campGroundDetailPg.clickFindOtherFacilitiesOnMap();
						}else{
							campGroundDetailPg.clickFacilityGoogleMap();
						}
					}else{
						if(recreationAreaDetailsPg.checkFindOtherFacilitiesOnMap()){
							recreationAreaDetailsPg.clickFindOtherFacilitiesOnMap();
						}else{
							recreationAreaDetailsPg.clickFacilityGoogleMap();
						}
					}
				}
			}

			//Verify the retained input/selection on search form
			searchData.whereTextValue = urls[i][1];
			viewAsListPg.waitLoading();
			searchPanel.verifySearchCriteria(searchData);
			searchData.whereTextValue = "flor";

			//			suggestionPg.waitExists();
			//			searchPanel.verifySearchCriteria(searchData);
		}
	}
}
