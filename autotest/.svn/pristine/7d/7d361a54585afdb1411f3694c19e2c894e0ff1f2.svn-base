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
 * Electric Hook-UP
 * Common Site Attributes (Has sites with: \ufffd) information based on availability 
 * 1:  Enter "Dogwood (CA)" in Where field, select "Camping & Lodging" from "Interested in", select "Trailer sites" from "Looking for", enter "40" in 'Length (ft)', check 'more options', select "15 Amp (or more)' for Electric hookup, click Search
 * (Only 2 trailer sites with electric hook-up 50amp out of 6 matching sites )
 * 2:  Enter a sample date, enter "1" in Length of stay, click Search
 * (Sample date: all the sites with electric hook-up 50amp are reserved on this date)
 * @Preconditions:
 * 1: the specific site has season info.
 * 2: all Electric Hook-up(50) will be booked/already occupied before we run this script.
 * when the time I code this script, there are only 2 site with Hook-up(50), if there are more than 2 sites with Hook-up(50), you need to book them all to meet the prerequisite.
 * @SPEC: Story P - part 1 - camping & lodging search results - Common site attributes 
 * @Task#: AUTO-780
 * 
 * @author bzhang
 * @Date  Oct 28, 2011
 */

public class ElectricHookUpAttribute extends RecgovTestCase {
	private BookingData bd2 = new BookingData();
	private String attributesWithoutDate, attributesWithDate;

	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);

		//Condition 1: "Electric Hook-up(50)" displays because sites (026, 027) having "50" are available
		this.verifySiteAttributes(attributesWithoutDate, true);
		this.verifySiteAttributes(attributesWithDate, false);

		//2: search with setting specific date
		bd.arrivalDate = DateFunctions.getDateAfterToday(4);
		bd.lengthOfStay = "1";
		bd2.arrivalDate = bd.arrivalDate;
		bd2.lengthOfStay = bd.lengthOfStay;	

		//Book these 2 sites with "Electric Hook-up(50)"
		this.checkSiteBooked(bd);		
		this.checkSiteBooked(bd2);

		//Check point 2: Display "Electric Hook-up(15)" because only these kinds of sites are available
		this.gotoViewAsListPage(bd);
		this.verifySiteAttributes(attributesWithoutDate, false);
		this.verifySiteAttributes(attributesWithDate, true);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";

		attributesWithoutDate = "Electric Hook-up(50)";
		attributesWithDate = "Electric Hook-up(15)";

		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.clickClearSearch = true;
		bd.whereTextValue = "Dogwood";
		bd.parkId = "70258";
		bd.contractCode = "NRSO";
		bd.interestInValue = "Camping & Lodging";
		bd.lookFor = "Trailer sites";
		bd.length = "40";
		bd.moreOptions = true; 
		bd.electricHookupValue = "15 Amp (or more)";


		//Booking data info for first site.
		bd.park = bd.whereTextValue.toUpperCase();
		bd.contractCode = "NRSO";
		bd.siteNo = "026";// this is the first Electric Hookup site that we need to book;
		bd.siteID = "3739";
		bd.siteRelationTypeID = web.getSiteRelationTypeID(bd.siteID, schema);

		//Booking data info for second site.
		bd2.isUnifiedSearch = this.isUnifiedSearch();
		bd2.whereTextValue = bd.whereTextValue;
		bd2.park = bd2.whereTextValue.toUpperCase();
		bd2.parkId = bd.parkId;
		bd2.contractCode = bd.contractCode;
		bd2.siteNo = "027";// this is the second Electric Hookup site that we need to book;
		bd2.siteID = "3740";
		bd2.siteRelationTypeID = web.getSiteRelationTypeID(bd2.siteID, schema);

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
	private void checkSiteBooked(BookingData bdata){
		UwpMemberStatusBar memberStatusBar = UwpMemberStatusBar.getInstance();

		String booked = web.checkBookedInv(bdata.siteID, bdata.siteRelationTypeID, bdata.arrivalDate, DateFunctions.getDateAfterGivenDay(bdata.arrivalDate, Integer.parseInt(bdata.lengthOfStay)), schema);
		if(StringUtil.isEmpty(booked)){
			if(memberStatusBar.needToSignOut()){
				web.signOut();
				email = web.getNextEmail();
			}
			web.signIn(email, pw);
			web.gotoUnifiedSearchPanel();
			web.makeReservationToCart(bdata);
			web.checkOutCart(pay);
		}else logger.info("-----Site which id="+bdata.siteID+" is booked.");
	}
}
