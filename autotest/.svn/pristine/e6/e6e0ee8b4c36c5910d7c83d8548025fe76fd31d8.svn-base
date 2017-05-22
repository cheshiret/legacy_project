package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.deeplink;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCurrentResListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
    Pre- condition: Search results page

    Step1. Run a search for "Sandy Lake" and it goes to search results page with "Sandy Lake" on the top in List view;
    Step2. Log in with a valid account at rec.gov;
    Step3. Click "Current Reservations" link on 'My Account' page;
    Step4. Click on facility name in 'Location' column;
    Step5. Click on 'Find other facilities' link or google map inset (if applicable);

	Expect behavior:
    It will send the user to their previous search results for "Sandy Lake". 
    All other fields will remain as the default. Search results are in list 
    view if clicked 'Find other facilities' link or in map view if clicked on Google map inset.

 * @Preconditions:
 * @SPEC: Story N - Find Other Facilities Contextual
 * @Task#:AUTO-864
 * 
 * @author Swang
 * @Date  Feb 13, 2012
 */
public class PasteLinkWithCurrentRes2 extends RecgovTestCase {
	private RecgovViewAsListPage viewAsListPg = RecgovViewAsListPage.getInstance();
	private BookingData resBd = new BookingData();

	public void execute(){
		//Check and release
		web.checkAndReleaseInventory(schema, resBd.arrivalDate, Integer.parseInt(resBd.lengthOfStay), false, resBd.siteID);

		//Make reservation 
		web.invokeURL(url);
		web.gotoCampgroundDetailsPg(resBd);
		web.bookSiteToOrderDetailPg(resBd);
		web.signIn(email, pw);
		web.fillInOrderDetails(od, resBd.contractCode, false);
		String resNum = web.checkOutCart(pay);

		//Cancel reservation
		String status = "Confirmed";
		web.gotoMyReservationsAccount();
		web.cancelReservation(new String[]{resNum}, bd.contractCode, status, pay);

		//Sigh out
		web.signOut();

		//Go to "Park View As List" page and verify the first park name
		this.makeUnifiedSearchAndFirstParkName();

		web.signIn(email, pw);
		//After clicking Location name in "Current reservation list" page
		//1. Click "Find Other Facilities" hyperlink to verify the defaults page and search data populated
		this.pasteUrlCheckDefaultPageAndSearchData(true);
		
//		//Sara[10/29/2013] Confirmed with Lisha and delete below check point for rec.gov using unified search
//		//2: click the GOOGLE map to verify the default page and search data populated
//		this.pasteUrlCheckDefaultPageAndSearchData(false);

		//Sigh out
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

		bd.whereTextValue = "SANDY LAKE";
		bd.interestInValue = "Day use & Picnic areas";
		bd.flexibleValue = "Not Flexible";
		bd.contractCode = "NRSO";
		bd.parkId = "73389";

		resBd.isUnifiedSearch = this.isUnifiedSearch();
		resBd.whereTextValue = "Fort Hunt";
		resBd.interestInValue = "Day use & Picnic areas";
		resBd.flexibleValue = "Not Flexible";
		resBd.arrivalDate = DateFunctions.getDateAfterToday(3);
		resBd.lengthOfStay = "2";

		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		resBd.park = bd.whereTextValue;
		resBd.loop = "Group Picnic Areas";
		resBd.siteNo = "C-001";
		resBd.contractCode = "NRSO";
		resBd.parkId = "70975";
		resBd.siteID = "206776";
	}

	private void makeUnifiedSearchAndFirstParkName(){

		this.gotoViewAsListPage(bd);
		viewAsListPg.verifyFirstParkName(bd.whereTextValue);
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
		UwpUnifiedSearchViewAsMapPage mapViewPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		UwpCurrentResListPage currentResPg = UwpCurrentResListPage.getInstance();

		web.gotoCurrentReservationsPage();
		currentResPg.clickLocationViaContractCodeAndParkID(resBd.contractCode, resBd.parkId);
		campGroundDetailPg.exists();

		//Click "Find Other Facility" or "Fond Other Facilities On Map" or "Facility Google Map" 
		if(clickFindOtherFacility){
			campGroundDetailPg.clickFindOtherFacilities();
			viewAsListPg.waitLoading();
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
		searchPanel.verifySearchCriteria(bd);
	}
}
