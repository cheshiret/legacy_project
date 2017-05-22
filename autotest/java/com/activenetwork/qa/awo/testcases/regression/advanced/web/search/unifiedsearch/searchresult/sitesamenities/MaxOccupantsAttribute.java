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
 * Max Occupants
 * Common Site Attributes (Has sites with: \ufffd) information based on availability 
 * 1:  Enter "SIMAX GROUP CAMP (OR)" in Where field, select "Camping & Lodging" from "Interested in", select "Group sites" from "Looking for", click Search
 * 2:  Enter a sample date, enter "1" in Length of stay, click Search
 * 
 * @Preconditions:
 * 1: the specific site has season info.
 * 2: Only 1 group site Max Occupants over 40
 * 3: the only Max Occupants over 40 site is reserved on this date.
 * 
 * @SPEC: Story P - part 1 - camping & lodging search results - Common site attributes 
 * @Task#: AUTO-780
 * 
 * @author bzhang
 * @Date  Oct 31, 2011
 * Note: The park "SIMAX GROUP CAMP" has 5 sites, 
 * the "Max Occupants" which can be found in "Site List" page, displays as "Max # of people", 20, 50, 40, 40, 40. 
 * Case want to book the site with 50 so that all 50 site are booked, the 40 will display. 
 * The same, if all of the 40 sites are booked, 20 will display. 
 * If all of them are booked, no max occupant info display in view as list page.
 */

public class MaxOccupantsAttribute extends RecgovTestCase {
	private BookingData[] bds = new BookingData[1];
	private String attributesWithoutDate, attributesWithDate;

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		//1: search without setting specific date, verify the has sites info
		this.verifySiteAttributes(attributesWithoutDate, true);
		this.verifySiteAttributes(attributesWithDate, false);

		//2: search with setting specific date
		bd.arrivalDate = DateFunctions.getDateAfterToday(5);
		bd.lengthOfStay = "1";
		bds[0].arrivalDate = bd.arrivalDate;
		bds[0].lengthOfStay = bd.lengthOfStay;

		//Prerequisite: 
		//1.2: check the only Max Occupants over 40 site is reserved on this date. 
		this.checkSiteBooked(bds);

		this.gotoViewAsListPage(bd);
		//3: search with setting specific date,verify the has sites info.
		this.verifySiteAttributes(attributesWithoutDate, false);
		this.verifySiteAttributes(attributesWithDate, true);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

		attributesWithoutDate = "Max Occupants(50)";
		attributesWithDate = "Max Occupants(40)";

		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.clickClearSearch = true;
		bd.whereTextValue = "SIMAX GROUP CAMP";
		bd.parkId = "71545";
		bd.contractCode = "NRSO";
		bd.park = bd.whereTextValue;
		bd.interestInValue = "Camping & Lodging";
		bd.lookFor = "Group sites";

		//only Max Occupants over 40 site info. 
		bds[0] = new BookingData();
		bds[0].isUnifiedSearch = this.isUnifiedSearch();
		//		bds[0] = new BookingData();
		bds[0].whereTextValue = bd.whereTextValue;
		bds[0].park = bd.whereTextValue.toUpperCase();
		bds[0].parkId  = bd.parkId;
		bds[0].contractCode = bd.contractCode;
		bds[0].siteNo = "PAV1";
		bds[0].siteID = "17827";
		bds[0].siteRelationTypeID = web.getSiteRelationTypeID(bds[0].siteID, schema);
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
	private void checkSiteBooked(BookingData[] bdatas){
		UwpMemberStatusBar memberStatusBar = UwpMemberStatusBar.getInstance();

		web.invokeURL(url);
		//if there is available site on given date, book it.
		logger.info("Start verify whether the given date was booked already.");

		//initialize booking data arrival date and length of stay
		for(int i = 0 ; i < bds.length; i ++){
			bds[i].isUnifiedSearch = this.isUnifiedSearch();
			bds[i].arrivalDate = bd.arrivalDate;
			bds[i].lengthOfStay = bd.lengthOfStay;
		}	

		for(BookingData bdata: bdatas){
			web.checkAndReleaseInventory(schema, bdata.arrivalDate, Integer.parseInt(bdata.lengthOfStay), false, bdata.siteID);
			//			String booked = web.checkBookedInv(bdata.siteID, Integer.parseInt(bdata.siteType), bdata.arrivalDate, DateFunctions.getDateAfterGivenDay(bdata.arrivalDate, Integer.parseInt(bdata.lengthOfStay)), schema);
			//			if(null == booked || booked.length() == 0){
			//				//release the hold inventory
			//				web.releaseHoldInvFromDB(bdata.siteID, Integer.parseInt(bdata.siteType), bdata.arrivalDate, DateFunctions.getDateAfterGivenDay(bdata.arrivalDate, Integer.parseInt(bdata.lengthOfStay)), schema);
			//				
			//start booking the specified given date site.
			logger.info("Start to book the given date site.");
			if(memberStatusBar.checkSignInExist()){
				web.signIn(email, pw);
			}
			web.gotoUnifiedSearchPanel();
			web.makeReservationToCart(bdata);
			web.checkOutCart(pay);
		}
		//		}
		logger.info("Verify the given date site was booked successfully");
	}
}
