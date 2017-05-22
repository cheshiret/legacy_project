package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.deeplink;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCurrentResListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
    Pre- condition: new session

    Step1. Close all browser windows then open a new window;
    Step2. Log in with a valid account at rec.gov;
    Step3. Click "Current Reservations" link on 'My Account' page;
    Step4. Click on facility name in 'Location' column;
    Step5. Click on 'Find other facilities' link or google map inset (if applicable);

	Expect behavior:
    the system shall use context facility as the \ufffdWhere\ufffd location and execute a new search.
    All other fields will remain as the default. Search results are in list view if clicked 
    'Find other facilities' link or in map view if clicked on Google map inset.

 * @Preconditions:
 * @SPEC: Story N - Find Other Facilities Contextual
 * @Task#:AUTO-864
 * 
 * @author Swang
 * @Date  Feb 13, 2012
 */
public class PasteLinkWithCurrentRes1 extends RecgovTestCase {
	UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();

	public void execute(){
		//Check and release
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);

		//Make reservation 
		web.invokeURL(url);
		web.gotoCampgroundDetailsPg(bd);
		web.bookSiteToOrderDetailPg(bd);
		web.signIn(email, pw);
		web.fillInOrderDetails(od, bd.contractCode, false);
		String resNum = web.checkOutCart(pay);

		//Cancel reservation
		String status = "Confirmed";
		web.gotoMyReservationsAccount();
		web.cancelReservation(new String[]{resNum}, bd.contractCode, status, pay);

		//After clicking Location name in "Current reservation list" page
		//1. Click "Find Other Facilities" hyperlink to verify the defaults page and search data populated
		this.pasteUrlCheckDefaultPageAndSearchData(true);
		
//        //Sara[10/29/2013] Confirmed with Lisha and delete below check point for rec.gov using unified search
//		//2: click the GOOGLE map to verify the default page and search data populated
//		this.pasteUrlCheckDefaultPageAndSearchData(false);

		web.signOut();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		bd.isUnifiedSearch = this.isUnifiedSearch();

		bd.parkId = "70975";
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"FORT HUNT";
		bd.park = bd.whereTextValue;
		bd.interestInValue = "Day use & Picnic areas";
		bd.flexibleValue = "Not Flexible";
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay = "2";

		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		bd.park = bd.whereTextValue;
		bd.loop = "Group Picnic Areas";
		bd.siteNo = "B-001";
		bd.contractCode = "NRSO";
		bd.parkId = "70975";
		bd.siteID = "206775";
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
		UwpCurrentResListPage currentResPg = UwpCurrentResListPage.getInstance();

		web.gotoCurrentReservationsPage();
		currentResPg.clickLocationViaContractCodeAndParkID(bd.contractCode, bd.parkId);
		campGroundDetailPg.exists();

		//Click "Find Other Facility" or "Fond Other Facilities On Map" or "Facility Google Map" 
		if(clickFindOtherFacility){
			campGroundDetailPg.clickFindOtherFacilities();
			parkViewPg.waitLoading();
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
		searchPanel.verifySearchCriteria(bd); //Sara[20140414], DEFECT-61501's check point is verified in this case, so please don't update it
	}
}
