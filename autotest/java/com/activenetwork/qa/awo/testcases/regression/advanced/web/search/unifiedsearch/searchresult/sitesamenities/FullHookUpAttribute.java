package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult.sitesamenities;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpMemberStatusBar;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * Full Hook-UP
 * Common Site Attributes (Has sites with: \ufffd) information based on availability 
 * @Preconditions:
 * 1: the specific site has season info.
 * 2: there is only one full hook-up site for this park
 * @SPEC: Story P - part 1 - camping & lodging search results - Common site attributes 
 * @Task#: AUTO-780
 * 
 * @author bzhang
 * @Date  Oct 28, 2011
 */
public class FullHookUpAttribute extends RecgovTestCase {
	private String attributesWithoutDate;
	
	public void execute() {
		web.invokeURL(url);
//		web.makeUnifiedSearch(bd);
		web.gotoViewAsListPage(bd);
		//1: search without setting specific date, verify the has sites info
		this.verifySiteAttributes(attributesWithoutDate, true);
		
		//2 prerequisite: check the only one Full hook-up site was taken already.
		bd.arrivalDate = DateFunctions.getDateAfterToday(5);
		bd.lengthOfStay = "1";
		this.checkSiteBooked(bd);
		
		//2: search with setting specific date,verify the has sites info.
//		web.makeUnifiedSearch(bd);
		web.gotoViewAsListPage(bd);
		this.verifySiteAttributes(attributesWithoutDate, false);
		
		//3: search with setting specific date , verify the has sites info after release taken site.
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteIDs);
		web.invokeURL(url);
//		web.makeUnifiedSearch(bd);
		web.gotoViewAsListPage(bd);
		this.verifySiteAttributes(attributesWithoutDate, true);
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		
		attributesWithoutDate = "Full Hookup(30)";
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.parkId = "70533";
		bd.whereTextValue = "SERRANO";
		bd.interestInValue = "Camping & Lodging";
		bd.lookFor = "RV sites";
		bd.accessibilityNeeds = true;
		
		//Booking data info
		bd.park = bd.whereTextValue;
		bd.contractCode = "NRSO";
		bd.siteNo = "064";// this is the only one accessible site that we need to book;
		bd.siteID = "88556";
		bd.siteIDs = new String[]{bd.siteID};
		bd.siteRelationTypeID = web.getSiteRelationTypeID(bd.siteID, schema);
	}
	
	/**
	 * verify site amenities info based on different park info.
	 * @param dayUseArray
	 * @param aminites
	 */
	private void verifySiteAttributes(String aminites, boolean withAttribute){
		RecgovViewAsListPage scResultPg = RecgovViewAsListPage.getInstance();
		
		String actualAmenities = scResultPg.getFirstParkAmenities();
		boolean flag = actualAmenities.contains(aminites);
		
		//verify amenities info
		if(withAttribute){
			//check site has the specified attribute info
			if(flag){
				logger.info("Verify amenities successfully for park:" + bd.whereTextValue);
			}else{
				logger.error("Ameinties verification failed for park:" + bd.whereTextValue);
				throw new ErrorOnPageException("Expect ameinties should contain " + aminites);
			}
		}else{
			//check site DON'T has the specified attribute info
			if(!flag){
				logger.info("Verify amenities successfully for park:" + bd.whereTextValue);
			}else{
				logger.error("Ameinties verification failed for park:" + bd.whereTextValue);
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
			bdata.isUnifiedSearch = this.isUnifiedSearch();
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
