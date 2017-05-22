package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.campsitelist;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpDateRangeAvailabilityPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: verify system display Error message: 'No suitable availability shown and Find Next Available Date and Show all (clear search) link and Search Parks Nearby for availability link.
 * 2: verify 'Find next available date' link is working. It can go to Date Range Availability page display no availability through the end of booking window.
 * 3: verify show all will clear the search criteria(but keep the value of Arrival date, flexible, and length of stay.), and all site will be displayed on site list page.
 * 
 * @Preconditions:for given site should in closure and not available
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 9, 2011
 */
public class NoSuitableAnyMore extends RecgovTestCase {
	private String msgTopOfPage, msgError;
	private BookingData bd1 = new BookingData();
	private RecgovViewAsListPage parkViewListPg = RecgovViewAsListPage.getInstance();
	private UwpCampingPage campComPg = UwpCampingPage.getInstance();
	
	public void execute() {
		web.invokeURL(url);
		web.bookParkToSiteListPg(bd);
		//verify expect message and link display on site list page.
		this.verifyMsgAndLinksOnSiteListPg();
		
		//verify expect message and link display on Date range availability page.
		this.verifyMsgAndLinksOnDateRangePg();
		// goto find site page.
		parkViewListPg.clickParkName(bd.park);
		campComPg.waitLoading();
		
		//verify clear search works.
		this.verifySearchParameterAndSiteListInitialized();  
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		msgTopOfPage = "No suitable availability shown";
		msgError = "No availability through the end of booking window. Please try other campgrounds or perform another search with less criteria and/or earlier arrival date.";
		
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.park = "Camp Sherman Campground".toUpperCase();
		bd.contractCode = "NRSO";
		bd.parkId = "72099";
		bd.interestInValue = "Camping & Lodging";
		bd.lookFor = "Any type of site";
		bd.flexibleValue = "Not Flexible";
		bd.loop = "Any Loop";
		bd.arrivalDate = DateFunctions.getDateAfterToday(4); 
		bd.siteNo="005";//Site 005 is in closures
		bd.lengthOfStay = "1";
		
		//new Booking data used to check whether "Show all" link will clear all search criteria on Find sites panle.
		//but keep the value of Arrival date, flexible, and length of stay.
		bd1.isUnifiedSearch = this.isUnifiedSearch();
		bd1.loop = "Any Loop";
		bd1.lookFor = "Any type of site";
		bd1.arrivalDate = bd.arrivalDate;
		bd1.lengthOfStay = bd.lengthOfStay;
		bd1.flexibleValue = bd.flexibleValue;
	}
	
	/**
	 * verify error message, find next available date, show all ,and search park nearby links.
	 * start from campsite list page, ends at date range availability page.
	 */
	public void verifyMsgAndLinksOnSiteListPg(){
		RecgovSiteListPage campSiteListPg = RecgovSiteListPage.getInstance();
		UwpDateRangeAvailabilityPage campDateRangePg = UwpDateRangeAvailabilityPage.getInstance();
		
		//1: verify the message displayed on the top of the page.
		campSiteListPg.verifyMsgTopOfPage(msgTopOfPage);
		//2: Find Next Available Date
		campSiteListPg.verifyFindNextAvailableDateLinkExist();		
		//3: verify Show all (clear search).
		campSiteListPg.verifyShowAllLinkExist();
		//4: verify Search Parks Nearby for availability.
		campSiteListPg.verifyParksNearbyLinkExist();
		
		//5: verify Find next availability is working.
		campSiteListPg.clickFindNextAvailableDateLink();
		campDateRangePg.waitLoading();
		
	}
	
	public void verifyMsgAndLinksOnDateRangePg(){
		UwpDateRangeAvailabilityPage campDateRangePg = UwpDateRangeAvailabilityPage.getInstance();

		//1: verify there is no available through the end of date, b/c we set arrival date to one year later.
		campDateRangePg.verifyErrorMessage(msgError);
		
		//2: verify try other campgrounds link leads to park view as list page.
		campDateRangePg.clickTryOtherCampgrounds();
		parkViewListPg.waitLoading();
		parkViewListPg.verifyFirstParkName(bd.park);
	}
	
	/**
	 * verify online availability column all display as "Enter Date";
	 * start and end at campsite site list page
	 * 
	 */
	public void verifySearchParameterAndSiteListInitialized(){
		RecgovSiteListPage campSiteListPg = RecgovSiteListPage.getInstance();
		UwpUnifiedFindProductCommonPanel findSitePanel = UwpUnifiedFindProductCommonPanel.getInstance();
		List<SiteInfoData> siteInfos = new ArrayList<SiteInfoData>();
		
		//1: research again in order to get to site list page with initial status.
		findSitePanel.makeSearch(bd);
		campSiteListPg.waitLoading();
		
		//2: click the show all link
		campSiteListPg.clickShowAllLink();
		campSiteListPg.waitLoading();
		
		//3: verify search widget data reset to initial value(looks like never entered search criteria before)
		findSitePanel.verifySearchCriteria(bd1);
		
		campSiteListPg.getAllSiteInfo();
		
		//3: verify there is no site which Online availability column display as "Enter Date" button.
		siteInfos = campSiteListPg.getAllSiteInfo();
		
		if(null == siteInfos || siteInfos.size() == 0){
			throw new ErrorOnPageException("Warning: there should be at least on site for this campground to go through the check points for this script. ");
		}else{
			for(int i = 0 ; i < siteInfos.size(); i ++){
				//loop through each online availability column, check the text should NOT be "Enter Date", b/c
				//"Enter Date" can only be displayed when there is no arrival date specified.
				//"Enter Date" is the key to identify whether the search criteria is cleared or not.
				String onlineStatus = siteInfos.get(i).onlineAvailability;
				if(onlineStatus.equalsIgnoreCase("Enter Date")){
					logger.error("The expect  status on line#" + i + " should not be Enter Date" );
					throw new ErrorOnDataException("verify the Online Availability status failed.");
				}
			}
			logger.info("verify the Online Availability status successfully.");
		}
	}
}
