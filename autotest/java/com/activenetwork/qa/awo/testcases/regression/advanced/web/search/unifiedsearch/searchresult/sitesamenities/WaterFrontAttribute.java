package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult.sitesamenities;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpMemberStatusBar;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * Waterfront Hook-UP
 * 1: book the only one WaterFront site for a specific date and then search park use this specific date.
 * verify the site amentities won't show WaterFront value
 * 2: search WITH OUT setting arrival date(date equal to the day when WaterFront was booked in #1 above), site will display amenities with : WaterFront 
 * 4: release the site booked in step1 above.
 * 3: search with setting arrival date (date equal to the day when WaterFront was booked in #1 above), site will display amenities with : Water Front
 * @Preconditions:
 * 1: the specific site has season info.
 * 2: there are 4 WaterFront sites.
 * 2: The 4 WaterFront sites is available on Web.
 * 
 * @SPEC: Story P - part 1 - camping & lodging search results - Common site attributes 
 * @Task#: AUTO-780
 * 
 * @author bzhang
 * @Date  Oct 31, 2011
 */

public class WaterFrontAttribute extends RecgovTestCase {
	private BookingData[] bds = new BookingData[4];
	private String attributesWithoutDate;

	public void execute() {
		web.invokeURL(url);
		//1: check the 4 WaterFront sites were taken already for a specific date and then search park use this specific date.
		//verify the site amenities won't show WaterFront value
		this.checkSiteBooked(bds);
		this.gotoViewAsListPage(bd);
		this.verifySiteAttributes(attributesWithoutDate,false);

		//2: search WITH OUT setting arrival date(date equal to the day when WaterFront was booked in #1 above), site will display amenities with : WaterFront
		bd.arrivalDate = "";
		bd.lengthOfStay = "";
		this.gotoViewAsListPage(bd);
		this.verifySiteAttributes(attributesWithoutDate,true);

		//3: release one site "bds[0]" booked in step1 above.
		bd.arrivalDate = DateFunctions.getDateAfterToday(5);  //make sure this date value equal to the date we booked
		bd.lengthOfStay = "1";
		web.checkAndReleaseInventory(schema, bds[0].arrivalDate, Integer.parseInt(bds[0].lengthOfStay), false, bds[0].siteIDs);

		//4: search with setting arrival date (date equal to the day when WaterFront was booked in #1 above), site will display amenities with : Water Front
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		this.verifySiteAttributes(attributesWithoutDate,true);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

		attributesWithoutDate = "Waterfront";

		bd.clickClearSearch = true;
		bd.parkId="71697";
		bd.whereTextValue = "PACKARD CREEK";
		bd.interestInValue = "Camping & Lodging";
		bd.lookFor = "RV sites";

		bd.arrivalDate = DateFunctions.getDateAfterToday(5);
		bd.lengthOfStay = "1";

		//The facility have 4 waterFront site.
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.park = bd.whereTextValue;
		bd.parkId = "71697";
		bd.contractCode = "NRSO";

		//Booking data info for #0 site.
		bds[0] = new BookingData();
		bds[0].siteNo = "034";
		bds[0].siteID = "146506";
		bds[0].siteIDs = new String[]{bds[0].siteID};
		bds[0].siteRelationTypeID = web.getSiteRelationTypeID(bds[0].siteID, schema);

		//Booking data info for #1 site.
		bds[1] = new BookingData();
		bds[1].siteNo = "035";
		bds[1].siteID = "146507";
		bds[1].siteRelationTypeID = web.getSiteRelationTypeID(bds[1].siteID, schema);

		//Booking data info for #2 site.
		bds[2] = new BookingData();
		bds[2].siteNo = "036";
		bds[2].siteID = "146508";
		bds[2].siteRelationTypeID = web.getSiteRelationTypeID(bds[2].siteID, schema);

		//Booking data info for #3 site.
		bds[3] = new BookingData();
		bds[3].siteNo = "037";
		bds[3].siteID = "146509";
		bds[3].siteRelationTypeID = web.getSiteRelationTypeID(bds[3].siteID, schema);
	}

	/**
	 * verify site amenities info based on different park info.
	 * @param dayUseArray
	 * @param aminites
	 */
	private void verifySiteAttributes(String aminites, boolean withAttribute){
		RecgovViewAsListPage scResultPg = RecgovViewAsListPage.getInstance();

		String actualAmenities = scResultPg.getFirstParkAmenities();
		if(actualAmenities.contains(aminites)!=withAttribute){
			throw new ErrorOnPageException("Failed to verify ameinties should "+(withAttribute?"":"not ")+ "contain "+aminites +"for park:" + bd.whereTextValue);
		}else logger.info("Successfully verify ameinties "+(withAttribute?"":"doesn't ")+ "contain "+aminites+"for park:" + bd.whereTextValue);
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
			bds[i].whereTextValue = bd.whereTextValue;
			bds[i].park = bd.park;
			bds[i].parkId = bd.parkId;
			bds[i].contractCode = bd.contractCode;
			bds[i].arrivalDate = bd.arrivalDate;
			bds[i].lengthOfStay = bd.lengthOfStay;

			String booked = web.checkBookedInv(bdatas[i].siteID, bdatas[i].siteRelationTypeID, bdatas[i].arrivalDate, DateFunctions.getDateAfterGivenDay(bdatas[i].arrivalDate, Integer.parseInt(bdatas[i].lengthOfStay)), schema);
			if(StringUtil.isEmpty(booked)){
				if(memberStatusBar.needToSignOut()){
					web.signOut();
					email = web.getNextEmail();
				}
				web.signIn(email, pw);
				web.gotoUnifiedSearchPanel();
				web.makeReservationToCart(bdatas[i]);
				web.checkOutCart(pay);
			}else logger.info("-----Site which id="+bdatas[i].siteID+" is booked.");
		}
	}
}
