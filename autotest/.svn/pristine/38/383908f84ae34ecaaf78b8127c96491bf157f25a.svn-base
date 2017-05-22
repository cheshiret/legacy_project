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
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Note: pass on local
 * @Description:
 * Close all browser windows, open a new window
	Step 1. Land on Home page, enter a sample facility (facility A) in 'Where' field, click search;
	Step 2. Click the facility name in the second result (facility B);
	Step 3. Select 'RV sites' from 'Looking for' drop down list on campsite search form, enter "50" in 'Length(ft)' , check 'Pets allowed', click 'Search';
	Step 4. Click 'Parks Nearby" link (decision support) under the warning message;
	Step 5. Click on 'Home' tab
	
	Sample facility:
	BLACK CANYON CAMPGROUND (OR) (facility B: RUJADA DAY USE PICNIC AREA (OR))
	JIMS LAKE CABIN (AK) (facility B: SPORTSMEN CABIN (AK))
 * @Preconditions:
 * @SPEC: Story X
 * @Task#:AUTO-797
 * 
 * @author bzhang
 * @Date  Nov 10, 2011
 */
public class ParksNearBy_RVsites extends RecgovTestCase {
	private RecgovViewAsListPage listPg = RecgovViewAsListPage.getInstance();
	private BookingData bd2,bd3;
	private String expectError;
	
	private String nearByPark1 = "RUJADA DAY USE PICNIC AREA";
	private String nearByPark2 = "SPORTSMEN CABIN";
	public void execute() {
		//1: first sample data verify
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);		
		this.gotoCampgroundDetailsPage(nearByPark1);
		bd2.whereTextValue = nearByPark1;
		this.verifyDatePopulateBetweenPages(bd2);
		
		//1: second sample data verify, need to open a new window.
		web.invokeURL(url);
		this.gotoViewAsListPage(bd3);
		this.gotoCampgroundDetailsPage(nearByPark2);
		bd2.whereTextValue = nearByPark2;
		this.verifyDatePopulateBetweenPages(bd2);
		
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		expectError = "No results found matching your search.";
		
		//first sample data
		bd.isUnifiedSearch = this.isUnifiedSearch();		
		bd.whereTextValue = "BLACK CANYON CAMPGROUND";
		bd.contractCode = "NRSO";
		bd.parkId = "75061";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		
		//second sample data
		bd3 =   new BookingData();
		bd3.isUnifiedSearch = this.isUnifiedSearch();		
		bd3.whereTextValue = "JIMS LAKE CABIN";
		bd3.contractCode = "NRSO";
		bd3.parkId = "71741";
		bd3.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		
		//booking data used on find sites panel.
		bd2 =  new BookingData();
		bd2.isUnifiedSearch = bd.isUnifiedSearch;
		bd2.interestInValue = "Camping & Lodging";
		bd2.loop = "Any Loop";
		bd2.lookFor = "RV sites";
		bd2.length = "50";
		
		bd2.flexibleValue = "Not Flexible";
		bd2.petsAllowed = true;
	}
	
	/**
	 * click the park name goto facility details page. start from view as list page, ends at campsite details page.
	 * @param parkName
	 */
	private void gotoCampgroundDetailsPage(String parkName){
		RecgovParkDetailsPage  detailPg = RecgovParkDetailsPage.getInstance();
		
		listPg.clickParkName(parkName);
		detailPg.waitLoading();
	}
	
	private void verifyDatePopulateBetweenPages(BookingData searchData){
		RecgovSiteListPage campSiteListPg = RecgovSiteListPage.getInstance();
		UwpCampingPage campCommonPg = UwpCampingPage.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		UwpUnifiedFindProductCommonPanel findSitePanel = UwpUnifiedFindProductCommonPanel.getInstance();

		//3.1 setup search criteria in campground details page.
		findSitePanel.makeSearch(searchData);
		campCommonPg.waitLoading();  //using details page to waiting for camp site list page		
		//3.2 verify site list page with message "No results found"
		campSiteListPg.verifyMsgTopOfPage(expectError);
		
		//4.1 click parks near by link
		campSiteListPg.clickParksNearbyLink();		
		//4.2 verify all search criteria on search form are populated on the search form.
		listPg.waitLoading();
		searchPanel.verifySearchCriteria(searchData);
		//4.3 verify facility B is the top result on search result page.
		listPg.verifyFirstParkName(searchData.whereTextValue);
		
		//5.1 click on Home TAB.
		web.gotoHomePage();
		//5.2 verify search form on home has the same inputs as what in the search form on search results page.
		searchPanel.verifySearchCriteria(searchData);
		
	}
	
}
