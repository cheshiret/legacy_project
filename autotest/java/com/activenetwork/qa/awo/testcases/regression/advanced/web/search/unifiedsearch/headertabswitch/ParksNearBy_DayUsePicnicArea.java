package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.headertabswitch;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * NOTE:
  pass on local.
 * @Description:
 * Close all browser windows, open a new window
	Step 1. Land on Home page, enter a sample facility (facility A) in 'Where' field, click search;
	Step 2. Click the facility name in the second result (facility B);
	Step 3. Select 'Day use & Picnic area' from 'Looking for' drop down list on campsite search form, enter "Jan 22, 2012" in 'Arrival date' , "2" in 'Length of stay', click 'Search';
	Step 4. Click 'Parks Nearby" link (decision support) under the warning message;
	Step 5. Click on 'Home' tab

	Sample facility:
	NANCYTOWN GROUP CAMPGROUND (GA)  (facility B: FERN SPRINGS DAY USE PICNIC AREA (GA))
	TAYLOR (TX) (facility B: Wilson H Fox (TX))->WILLIS CREEK, TX
 * @Preconditions:
 * The two facilities must have the "Day use & Picnic areas" sites and the sites are unavailable for the booked dates. 
 * Make sure there is no season for the sites.
 * @SPEC: Story X
 * @Task#:AUTO-797
 * 
 * @author bzhang
 * @Date  Nov 10, 2011
 * 
 */
public class ParksNearBy_DayUsePicnicArea extends RecgovTestCase {
	private BookingData bd2,bd3;
	private String expectError;

	private String nearByPark1 = "FERN SPRINGS DAY USE PICNIC AREA";
	private String nearByPark2 = "WILSON H FOX";//"WILLIS CREEK"; //"WILSON H FOX";
	public void execute() {
		//1: first sample data verify
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		this.gotoCampgroundDetailsPage(nearByPark1);
		bd2.whereTextValue = nearByPark1;
		this.verifyDatePopulateBetweenPages(bd2);

		//1: second sample data verify, need to open a new window.
		web.checkAndReleaseInventory(schema, bd2.arrivalDate, Integer.parseInt(bd2.lengthOfStay), false, bd2.siteID);
		web.invokeURL(url);
		web.signIn(email, pw);
		bd2.siteNo = "G59";//Sara[12/5/2013]this site in the second page if not sprcified
		web.makeReservationToCart(bd2);
		String ordNum = web.checkOutCart(pay);
		bd2.siteNo = StringUtil.EMPTY;
		
		this.gotoViewAsListPage(bd3);
		this.gotoCampgroundDetailsPage(nearByPark2);
		bd2.whereTextValue = nearByPark2;
		this.verifyDatePopulateBetweenPages(bd2);
		
		web.gotoMyReservationsAccount();
		web.cancelReservation(new String[]{ordNum}, bd.contractCode, "Confirmed", pay);

	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		
		expectError = "No suitable availability shown"; 
		//first sample data
		bd.isUnifiedSearch = this.isUnifiedSearch();		
		bd.whereTextValue = "NANCYTOWN GROUP CAMPGROUND";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.contractCode = "NRSO";
		bd.parkId = "75351";

		//second sample data
		bd3 = new BookingData();
		bd3.isUnifiedSearch = this.isUnifiedSearch();	
		bd3.whereTextValue = "TAYLOR";
		bd3.park = bd3.whereTextValue;
		bd3.stateCode = "TX";
		bd3.contractCode = "NRSO";
		bd3.parkId = "71448";
		bd3.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;

		//booking data used on find sites panel.
		bd2 = new BookingData();
		bd2.parkId = "71505";
		bd2.park = DataBaseFunctions.getFacilityName(bd2.parkId, schema); //"WILSON H FOX";
		bd2.contractCode = "NRSO";
		bd2.isUnifiedSearch =this.isUnifiedSearch();
		bd2.interestInValue = "Camping & Lodging";
		bd2.loop = "Any Loop";
		bd2.lookFor = "Day use & Picnic areas";
		bd2.arrivalDate = DateFunctions.getDateAfterToday(6); //choose a date where we don't have any available site info,we can add this site in to closure and remove from season or change the site attribute.
		bd2.lengthOfStay = "2";
		bd2.siteID = "134127";

		bd2.flexibleValue = "Not Flexible";
	}

	/**
	 * click the park name goto facility details page. start from view as list page, ends at campsite details page.
	 * @param parkName
	 */
	private void gotoCampgroundDetailsPage(String parkName){
		RecgovViewAsListPage listPg = RecgovViewAsListPage.getInstance();
		RecgovParkDetailsPage  detailPg = RecgovParkDetailsPage.getInstance();

		listPg.clickParkName(parkName);
		detailPg.waitLoading();
	}

	private void verifyDatePopulateBetweenPages(BookingData searchData){
		RecgovViewAsListPage parkViewListPg = RecgovViewAsListPage.getInstance();
		RecgovSiteListPage campSiteListPg = RecgovSiteListPage.getInstance();
		UwpCampingPage campCommonPg = UwpCampingPage.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedFindProductCommonPanel findSitePanel = UwpUnifiedFindProductCommonPanel.getInstance();

		//3.1 setup search criteria in campground details page.
		findSitePanel.makeSearch(searchData);
		campCommonPg.waitLoading();  //using details page to waiting for camp site list page		
		//3.2 verify site list page with message "No suitable availabilty shown"
		campSiteListPg.verifyMsgTopOfPage(expectError);

		//4.1 click parks near by link
		campSiteListPg.clickParksNearbyLink();		
		//4.2 verify all search criteria on search form are populated on the search form.
		parkViewListPg.waitLoading();
		searchPanel.verifySearchCriteria(searchData);
		//4.3 verify facility B is the top result on search result page.
		parkViewListPg.verifyFirstParkName(searchData.whereTextValue);

		//5.1 click on Home TAB.
		web.gotoHomePage();
		//5.2 verify search form on home has the same inputs as what in the search form on search results page.
		searchPanel.verifySearchCriteria(searchData);

	}

}
