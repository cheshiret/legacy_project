package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.deeplink;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovPermitAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: (P)
		Step1. Close all browser windows then open a new window;
		Step2. Enter "abcd1234" in 'Where' field and search to get a "No location found" message;
		Step3. Paste a deep link of facility/rec area details page and press "Enter";
		Step4. Click on 'Find other facilities' link or google map inset (if applicable);

	Expect behavior:
		On step4:
	    It shows "No location found" message and all input/selection on search form shall be retained.

 * @Preconditions:
 * @SPEC: Story N - Find Other Facilities Contextual
 * @Task#:AUTO-864
 * 
 * @author SWang
 * @Date  Mar 1, 2012
 */
public class PasteLinkAfterNoLocationResult extends RecgovTestCase {
	private String[] urls = new String[5];
	private String errorMes;
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();

	public void execute(){
		//1: click Find Other Facilities hyperlink, verify the default page and search data populated.
		this.pasteUrlCheckDefaultPageAndSearchData(true);
		
		//Sara[10/29/2013] Confirmed with Lisha and delete below check point for rec.gov using unified search
//		//2: click Find other facilities on map hyperlink, verify the default page and search data populated.
//		this.pasteUrlCheckDefaultPageAndSearchData(false);
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

		searchData.whereTextValue = "abcd1234";
		errorMes = "We are unable to find a location that matches \""+searchData.whereTextValue+"\". Please try a different location and search again.";
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		//Campground 
		urls[0]= url + "/camping/Big_Trinity_Cabin/r/campgroundDetails.do?contractCode=NRSO&parkId=70630&topTabIndex=Search";

		//Permit facility
		urls[1] = url + "/permits/Boundary_Waters_Canoe_Area_Wilderness/r/wildernessAreaDetails.do?page=detail&contractCode=NRSO&parkId=72600";

		//Tour facility
		urls[2] = url + "/tourParkDetail.do?contractCode=NRSO&parkId=72369";

		//Rec area
		urls[3]= url + "/recAreaDetails.do?contractCode=NRSO&recAreaId=437&agencyCode=130";
		urls[4] = url + "/recAreaDetails.do?contractCode=NRSO&recAreaId=1273&agencyCode=127";
	}

	/**
	 * Verify default page, warning message and search Data
	 * @param clickFindOtherFacility  --true: Click "Find other facility" link
	 *                                --false: Click "Find other facilities on map" link 
	 *                                         or "find the Google map"
	 */
	private void pasteUrlCheckDefaultPageAndSearchData(boolean clickFindOtherFacility){
		RecgovParkDetailsPage campGroundDetailPg = RecgovParkDetailsPage.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchSuggestionPage suggestionPg = UwpUnifiedSearchSuggestionPage.getInstance();
		UwpRecreationAreaDetailsPage recreationAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();
		RecgovPermitAreaDetailsPage permitAreaDetailsPg = RecgovPermitAreaDetailsPage.getInstance();
		
		for(int i = 0 ; i < urls.length; i ++ ){
			//1: Enter associated sample data to get "No location found" message
			web.invokeURL(url);
			web.makeUnifiedSearch(searchData);
			searchPanel.verifySearchCriteria(searchData);
			suggestionPg.verifyErrorMsg(errorMes);

			//2:Paste a deep link of Campground/Permit/Tour/rec area details page and press "Enter";
			String tempUrl = urls[i];
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

			//Verify the retained input/selection on search form
			suggestionPg.waitLoading();
			searchPanel.verifySearchCriteria(searchData);
			suggestionPg.verifyErrorMsg(errorMes);
		}
	}
}
