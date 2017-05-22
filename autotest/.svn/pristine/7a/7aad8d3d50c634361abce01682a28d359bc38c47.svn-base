package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.campsitelist;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: verify system display Error message: 'No Results found matching your search.
 * 2: verify show all will clear the search criteria(but keep the value of Arrival date, flexible, and length of stay.), and all site will be displayed on site list page.
 * 
 * @Preconditions:
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 14, 2011
 */
public class NoResultMatch extends RecgovTestCase {
	private RecgovSiteListPage siteListPg = RecgovSiteListPage.getInstance();
	private String msgTopOfPage;
	private BookingData bd1 = new BookingData();
	
	public void execute() {
		web.invokeURL(url);
		web.bookParkToSiteListPg(bd);
		//verify expect message and link display on site list page.
		this.verifyMsgAndLinksOnSiteListPg();
		
		//verify clear search works.
		this.verifySearchParameterAndSiteListInitialized();
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		msgTopOfPage = "No results found matching your search.";
		
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "CAMP SHERMAN CAMPGROUND";
		bd.park = bd.whereTextValue;
		bd.contractCode = "NRSO";
		bd.parkId = "72099";
		bd.interestInValue = "Camping & Lodging";
		bd.lookFor = "Yurts";  //this is the key value, after set this value we can get no result found match message,b/c there is no such type of site for the selected park.
		bd.flexibleValue = "Not Flexible";
		bd.loop = "Any Loop";
		bd.arrivalDate = DateFunctions.getDateAfterToday(365);  //set a date where we don't have any available site.
		bd.lengthOfStay = "1";
		
		//new Booking data used to check whether "Show all" link will clear all search criteria on Find sites panle.
		bd1.isUnifiedSearch = this.isUnifiedSearch();
		bd1.loop = "Any Loop";
		bd1.lookFor = "Any type of site";
		bd1.arrivalDate = bd.arrivalDate;
		bd1.lengthOfStay = bd.lengthOfStay;
		bd1.flexibleValue = bd.flexibleValue;
	}
	
	/**
	 * verify error message, and search park nearby links.
	 * start/ends at campsite list page.
	 */
	public void verifyMsgAndLinksOnSiteListPg(){
		//1: verify the message displayed on the top of the page.
		siteListPg.verifyMsgTopOfPage(msgTopOfPage);
		
		//2: verify Show all (clear search).
		siteListPg.verifyShowAllLinkExist();
		//3: verify Search Parks Nearby for availability.
		siteListPg.verifyParksNearbyLinkExist();
	}
	
	/**
	 * verify online availability column all display as "Enter Date";
	 * start and end at campsite site list page
	 * 
	 */
	public void verifySearchParameterAndSiteListInitialized(){
		UwpUnifiedFindProductCommonPanel findSitePanel = UwpUnifiedFindProductCommonPanel.getInstance();
		List<SiteInfoData> siteInfos = new ArrayList<SiteInfoData>();
		
		//1: click the show all link
		siteListPg.clickShowAllLink();
		siteListPg.waitLoading();
		
		//2: verify search widget data reset to initial value(looks like never entered search criteria before)
		//but keep the value of Arrival date, flexible, and length of stay.
		findSitePanel.verifySearchCriteria(bd1);
		
		siteListPg.getAllSiteInfo();
		
		//3: verify there is no site which Online availability column display as "Enter Date" button.
		siteInfos = siteListPg.getAllSiteInfo();
		
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
