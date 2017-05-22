package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.deeplink;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovPermitAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
	Step1. Run a search for "Sandy Lake" and it goes to search results page with "Sandy Lake" on the top ;
	Step2. Paste a deep link of facility/rec area details page and press "Enter";
	Step3. Click on 'Find other facilities' link;
	Step4. Click 'view as Map';
	Step5. Repeat step2-3;

	Expect behavior On step3:
	It will send the user to their previous search results using the same view, i.e. list view for "sandy lake" search results

	Expect behavior On step5:
	It will send the user to their previous search results using the same view, i.e. map view for "sandy lake" search results

 * @Preconditions:
 * @SPEC: Story N - Find Other Facilities Contextual
 * @Task#:AUTO-864
 * 
 * @author bzhang
 * @Date  Feb 3, 2012
 */
public class PasteLinkAfterSearch extends RecgovTestCase {
	private String[] urls = new String[5];
	UwpUnifiedSearch searchData = new UwpUnifiedSearch();

	public void execute(){
		//1: click Find Other Facilities hyperlink, verify the default page and search data populated.
		this.pasteUrlCheckDefaultPageAndSearchData();
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		searchData.whereTextValue = "SANDY LAKE";
		searchData.contractCode = "NRSO";
		searchData.parkId = "73389";

		//Campground 
		urls[0] = url + "/camping/Big_Trinity_Cabin/r/campgroundDetails.do?contractCode=NRSO&parkId=70630&topTabIndex=Search";

		//Permit facility
		urls[1] = url + "/permits/Boundary_Waters_Canoe_Area_Wilderness/r/wildernessAreaDetails.do?page=detail&contractCode=NRSO&parkId=72600";

		//Tour facility
		urls[2] = url + "/tourParkDetail.do?contractCode=NRSO&parkId=72369";

		//Rec area
		urls[3] = url + "/recAreaDetails.do?contractCode=NRSO&recAreaId=437&agencyCode=130";

		urls[4] = url + "/recAreaDetails.do?contractCode=NRSO&recAreaId=1273&agencyCode=127";
	}

	/**
	 * Verify default page and search Data
	 * @param clickFindOtherFacility  --true: Click "Find other facility" link
	 *                                --false: Click "Find other facilities on map" link 
	 *                                         or "find the Google map"
	 */
	private void pasteUrlCheckDefaultPageAndSearchData(){
		RecgovParkDetailsPage campGroundDetailPg = RecgovParkDetailsPage.getInstance();
		RecgovViewAsListPage parkViewPg = RecgovViewAsListPage.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchViewAsMapPage mapViewPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		UwpRecreationAreaDetailsPage recreationAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();
		RecgovPermitAreaDetailsPage permitAreaDetailsPg = RecgovPermitAreaDetailsPage.getInstance();
		
		for(int i = 0 ; i < urls.length; i ++ ){
			String tempUrl = urls[i];
			web.invokeURL(url);
			//Run a search for "Sandy Lake" and it goes to search results page with "Sandy Lake" on the top ;
			this.gotoViewAsListPage(searchData);
			//Paste a deep link of facility/rec area details page and press "Enter";
			web.invokeURL(tempUrl,false,false);
			Object page = browser.waitExists(campGroundDetailPg, permitAreaDetailsPg, recreationAreaDetailsPg);

			//Click on 'Find other facilities' link;
			if(page == campGroundDetailPg){
				campGroundDetailPg.clickFindOtherFacilities();
			}else if(page == permitAreaDetailsPg){
				permitAreaDetailsPg.clickFindOtherFacilities();
			}else recreationAreaDetailsPg.clickBackToSearch();

			//verify It will send the user to their previous search results using the same view, i.e. list view for "sandy lake" search results
			parkViewPg.waitLoading();
			searchPanel.verifySearchCriteria(searchData, true); //Sara[20140414], DEFECT-61501 is covered by other case

			parkViewPg.clickViewAsMap();
			mapViewPg.waitLoading();
			//Paste a deep link of facility/rec area details page and press "Enter";
			web.invokeURL(tempUrl,false,false);
			page = browser.waitExists(campGroundDetailPg, permitAreaDetailsPg, recreationAreaDetailsPg);
			
			//Click on 'Find other facilities' link;
			if(page == campGroundDetailPg){
				campGroundDetailPg.clickFindOtherFacilities();
			}else if(page == permitAreaDetailsPg){
				permitAreaDetailsPg.clickFindOtherFacilities();
			}else recreationAreaDetailsPg.clickBackToSearch();
			
			//verify It will send the user to their previous search results using the same view, i.e. map view for "sandy lake" search results
			mapViewPg.waitLoading();
			searchPanel.verifySearchCriteria(searchData, true);
		}
	}

}
