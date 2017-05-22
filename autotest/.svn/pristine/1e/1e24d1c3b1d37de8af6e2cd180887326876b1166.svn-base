package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult.sitesamenities;

import java.util.Calendar;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpMemberStatusBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * Accessible
 * Common Site Attributes (Has sites with: \ufffd) information based on availability 
 * @Preconditions:
 * 1: the specific site has season info.
 * 2: there is only one accessible site for the park
 * @SPEC: Story P - part 1 - camping & lodging search results - Common site attributes 
 * @Task#: AUTO-780
 * 
 * @author bzhang
 * @Date  Oct 27, 2011
 */
public class AccessibleAttribute extends RecgovTestCase {
	private String aminites;
	
	public void execute() {
		web.invokeURL(url);
		//1: search without setting specific date, verify the has sites info DO have the specified amenities info
		this.verifySiteAttributes(bd, true);
		
		//2: search with setting specific date
		bd.arrivalDate = DateFunctions.getDateAfterToday(5, Calendar.MONDAY);
		bd.lengthOfStay = "1";
		//Prerequisite: check the only one accessible site was taken already.
		this.checkSiteBooked(bd);
		
		//search with setting specific date,verify the has sites info DON'T have the specified amenities
		this.verifySiteAttributes(bd, false);
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.unifSearch.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		
		aminites = "Accessible";
		
		bd.whereTextValue = "Camp Sherman Campground";
		bd.parkId = "72099";
		bd.contractCode = "NRSO";
		bd.interestInValue = "Camping & Lodging";
		
		//Booking data info
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.park = bd.whereTextValue.toUpperCase();
		bd.contractCode = "NRSO";
		bd.loop = "CAMP SHERMAN CAMPGROUND";
		bd.siteNo = "015";// this is the only one accessible site that we need to book;
		bd.siteID = "283844";
		bd.siteRelationTypeID = web.getSiteRelationTypeID(bd.siteID, schema);
	}
	
	/**
	 * verify site amenities info based on different park info.
	 * @param dayUseArray
	 * @param aminites
	 */
	private void verifySiteAttributes(BookingData tempBd, boolean withAttribute){
		RecgovViewAsListPage scResultPg = RecgovViewAsListPage.getInstance();
		UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
		
		searchPanel.clickClearSearch();
		web.makeUnifiedSearch(tempBd);
		this.gotoViewAsListPage(bd);
		String actualAmenities = scResultPg.getFirstParkAmenities();
		boolean flag = actualAmenities.matches("Has sites with: .*" + aminites + ".*");
		
		//verify amenities info
		if(withAttribute){
			//check site has the specified attribute info
			if(flag){
				logger.info("Verify amenities successfully for park:" + tempBd.whereTextValue);
			}else{
				logger.error("Ameinties verification failed for park:" + tempBd.whereTextValue);
				throw new ErrorOnPageException("Expect ameinties should contain " + aminites);
			}
		}else{
			//check site DON'T has the specified attribute info
			if(!flag){
				logger.info("Verify amenities successfully for park:" + tempBd.whereTextValue);
			}else{
				logger.error("Ameinties verification failed for park:" + tempBd.whereTextValue);
				throw new ErrorOnPageException("Expect ameinties should NOT contain " + aminites);
			}
		}
	}
	
	/**
	 * check the site is no longer available on the given date, if still available will book it.
	 * This work flow start from home page, ends at home page.
	 * @param bdata
	 */
	private void checkSiteBooked(BookingData bdata){
		UwpMemberStatusBar memberStatusBar = UwpMemberStatusBar.getInstance();
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
