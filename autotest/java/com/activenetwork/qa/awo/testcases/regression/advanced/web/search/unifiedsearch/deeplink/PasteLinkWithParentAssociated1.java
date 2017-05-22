package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.deeplink;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedCabinDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
    Step1. Close all browser windows then open a new window;
    Step2. Paste a deep link of parent rec area details page and press "Enter";
    Step3. Click on an associated facility;
    Step4. Click on 'Find other facilities' link;
    Step5. Repeat step 1-3;
    Step6. Click on the google map inset(if applicable);

	Expect behavior:
	On step4:
    the system shall use context facility as the \ufffdWhere\ufffd location and execute a new search.  
    All other fields will remain as the default. Search results are in list view.

    On step6:
    the system shall use context facility as the \ufffdWhere\ufffd location and execute a new search.  
    All other fields will remain as the default. Search results are in map view.

 * @Preconditions:
 * @SPEC: Story N - Find Other Facilities Contextual
 * @Task#:AUTO-864
 * 
 * @author Swang
 * @Date  Feb 13, 2012
 */
public class PasteLinkWithParentAssociated1 extends RecgovTestCase {
	private String[][] urls = new String[1][2];
	private String tempUrl;
	private UwpUnifiedSearch searchData = new UwpUnifiedSearch();

	public void execute(){
		//After clicking associated facility in "Recreation Area Details" page
		//1. Click "Find Other Facilities hyperlink" to verify the defaule page and search data populated
		this.pasteUrlCheckDefaultPageAndSearchData(true);
		
//		//Sara[10/29/2013] Confirmed with Lisha and delete below check point for rec.gov using unified search
//		//2: click the GOOGLE map to verify the default page and search data populated
//		this.pasteUrlCheckDefaultPageAndSearchData(false);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

      //urls[0][0] = url + "/recAreaDetails.do?contractCode=NRSO&recAreaId=2336&agencyCode=131";
		urls[0][0] = url + "/recreationalAreaDetails.do?contractCode=NRSO&recAreaId=2336&agencyCode=70903";
		urls[0][1] = "ADMIRALTY COVE CABIN";

		tempUrl = urls[0][0];
		searchData.whereTextValue = urls[0][1];
		searchData.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		searchData.contractCode = "NRSO";
		searchData.parkId = "71744";
	}

	/**
	 * Verify default page and search Data
	 * @param clickFindOtherFacility  --true: Click "Find other facility" link
	 *                                --false: Click "Find other facilities on map" link 
	 *                                         or "find the Google map"
	 */
	private void pasteUrlCheckDefaultPageAndSearchData(boolean clickFindOtherFacility){
		UwpUnifiedCabinDetailsPage cabinDetailPg = UwpUnifiedCabinDetailsPage.getInstance();
		RecgovViewAsListPage parkViewPg = RecgovViewAsListPage.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedSearchViewAsMapPage mapViewPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		UwpRecreationAreaDetailsPage recreationAreaDetailsPg = UwpRecreationAreaDetailsPage.getInstance();

		//Go to "Recreation Area Details" page via paste URL
		web.invokeURL(tempUrl);
		recreationAreaDetailsPg.waitLoading();

		//Click on an associated facility(ADMIRALTY COVE CABIN) and it goes to "Cabin Details" page
		recreationAreaDetailsPg.clickAssociatedFacility(searchData.contractCode, searchData.parkId);
		cabinDetailPg.waitLoading();

		//Click "Find Other Facility" or "Fond Other Facility On Map" or "Facility Google Map" 
		//To check expected page 
		//To check remain fields value from "Search Panel"
		if(clickFindOtherFacility){
			cabinDetailPg.clickFindOtherFacilities();
			parkViewPg.waitLoading();
		}else{
			//if Find other facilities on map hyperlink exist, click it. 
			//otherwise click find the Google map on the left top conner of the page;
			if(cabinDetailPg.checkFindOtherFacilitiesOnMap()){
				cabinDetailPg.clickFindOtherFacilitiesOnMap();
			}else{
				cabinDetailPg.clickFacilityGoogleMap();
			}
			mapViewPg.waitLoading();
		}
		searchPanel.verifySearchCriteria(searchData);
	}
}
