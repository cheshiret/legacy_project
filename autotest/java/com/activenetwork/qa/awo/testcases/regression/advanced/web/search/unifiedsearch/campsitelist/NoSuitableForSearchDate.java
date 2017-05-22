package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.campsitelist;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpDateRangeAvailabilityPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpMemberStatusBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: verify system display Error message: 'No suitable availability shown and Find Next Available Date and Show all (clear search) link and Search Parks Nearby for availability link.
 * 2: verify 'Find next available date' link is working. It can go to Date Range Availability page display first date on the calendar matrix should be an available Date.
 * 3: verify show all will clear the search criteria(but keep the value of Arrival date, flexible, and length of stay.), and all site will be displayed on site list page.
 * 
 * @Preconditions:
 * 1: the site is not available for the given date.
 * 1: there is available site after the given search date
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 14, 2011
 */
public class NoSuitableForSearchDate extends RecgovTestCase {
	private RecgovSiteListPage campSiteListPg = RecgovSiteListPage.getInstance();
	private String msgTopOfPage;
	private BookingData bd1 = new BookingData();
	
	public void execute() {
		web.invokeURL(url);
		//set the specific site to unavailable status.
		this.checkSiteBooked(bd);
		
		web.bookParkToSiteListPg(bd);
		
		this.verifyItemsOnSiteList();
		this.verifySearchParameterAndSiteListInitialized();
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		msgTopOfPage = "No suitable availability shown";
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.park = "Camp Sherman Campground".toUpperCase();
		bd.parkId = "72099";
		bd.interestInValue = "Camping & Lodging";
		bd.lookFor = "Any type of site";
		bd.flexibleValue = "Not Flexible";
		bd.loop = "Any Loop";
		bd.arrivalDate = DateFunctions.getDateAfterToday(10, Calendar.TUESDAY);  //set a date where we don't have any available site. b/c this set has block stay rule for Fri and Saturday, so wet a week day either Fir or Saturday.
		bd.lengthOfStay = "1";
		
		bd.contractCode = "NRSO";
		bd.siteNo = "003";// this is the first Electric Hookup site that we need to book;
		bd.siteID = "283837";
		bd.siteRelationTypeID = web.getSiteRelationTypeID(bd.siteID, schema);
		
		//new Booking data used to check whether "Show all" link will clear all search criteria on Find sites panel.
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
	public void verifyItemsOnSiteList(){
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
		//: check the site available after click "Find next availablity" button.
		if(campDateRangePg.checkSiteAvailable(bd.siteNo)){
			logger.info("verify site availability after click \"Find next Available\" button successfully.");
		}else{
			throw new ErrorOnPageException("verify site availability failed, after click \"Find next Available\" button.");
		}
	}
	
	/**
	 * verify "Clear search" button works.
	 * start and end at campsite site list page
	 * 
	 */
	public void verifySearchParameterAndSiteListInitialized(){
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
		
		//4:  verify there is no site which Online availability column display as "Enter Date" button. b/c clear search and show all will retain the search Date ,length of stay, and flexible info previously entered.
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
	
	/**
	 * check the site is no longer available on the given date, if still available will book it.
	 * This work flow start from home page, ends at home page.
	 * @param bdata
	 */
	private void checkSiteBooked(BookingData bdata){
		UwpMemberStatusBar memberStatusBar = UwpMemberStatusBar.getInstance();
		
		web.invokeURL(url);		
		//if there is available site on given date, book it.
		logger.info("Start verify whether the given date was booked already.");
		
		String booked = web.checkBookedInv(bdata.siteID, bdata.siteRelationTypeID, bdata.arrivalDate, DateFunctions.getDateAfterGivenDay(bdata.arrivalDate, Integer.parseInt(bdata.lengthOfStay)), schema);
		if(null == booked || booked.length() == 0){
			//release the hold inventory
			web.releaseHoldInvFromDB(bdata.siteID, bdata.siteRelationTypeID, bdata.arrivalDate, DateFunctions.getDateAfterGivenDay(bdata.arrivalDate, Integer.parseInt(bdata.lengthOfStay)), schema);
			
			//start booking the specified given date site.
			logger.info("Start to book the given date site.");
			if(memberStatusBar.checkSignInExist()){
				web.signIn(email, pw);
			}
			
			web.gotoUnifiedSearchPanel();
			web.makeReservationToCart(bdata);
			web.checkOutCart(pay);
		}
		logger.info("Verify the given date site was booked successfully");
	}
}
