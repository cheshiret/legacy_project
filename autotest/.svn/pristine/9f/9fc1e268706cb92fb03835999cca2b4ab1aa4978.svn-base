package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult.sitesamenities;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpMemberStatusBar;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * Pets Allowed Hook-UP
 * Common Site Attributes (Has sites with: \ufffd) information based on availability 
 * 1:  Enter "Reversed Creek Campground (CA)" in Where field, select "Camping & Lodging" from "Interested in", select "RV sites" from "Looking for", click Search
 * 2:  Enter a sample date, enter "1" for 'Length of stay', click Search
 *(Sample date: the RV sites with pull-throught driveway available for online-reservation are reserved on this date)
 * 
 * @Preconditions:
 * 1: the specific site has season info.
 * 2: all the Pull - through site was taken, if not, the script will book this site.
 * 3: at least one site with(Max Occupants(6), Driveway Entry (Back-In)) is available on the given date.
 * 
 * @SPEC: Story P - part 1 - camping & lodging search results - Common site attributes 
 * @Task#: AUTO-780, Auto-1818
 * 
 * @author bzhang, Swang
 * @Date  Oct 31, 2011, Aug 8, 8
 */

public class DrivewayEntryAttribute extends RecgovTestCase {
	private BookingData[] bds = new BookingData[4];
	private String attributesWithoutDate, attributesWithDate;

	public void execute() {
		//Condition 1: search without setting specific date, verify first park has "Pull-Through" Driveway Entry attribute
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		this.verifySiteAttributes(bd.whereTextValue, attributesWithoutDate);

		//Condition 2: search with setting specific date (the day in the end of current year so that the precondition booked sites can be recycling used)
		bd.arrivalDate = "12/31/"+DateFunctions.getCurrentYear();
		bd.lengthOfStay = "1";

		//Check and make sure at least 1 site is available for Max Occupants(6) and Driveway Entry (Back-In). All "Pull-Through" attribute sites are booked.
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		checkSiteBooked(bds);

		//search with setting specific date, verify first park doesn't have "Pull-Through" Driveway Entry attribute
		this.gotoViewAsListPage(bd);
		this.verifySiteAttributes(bd.whereTextValue, attributesWithDate);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		schema = DataBaseFunctions.getSchemaName("NRRS", env);

		attributesWithoutDate = "Max Occupants(6), Pets Allowed(Domestic), Driveway Entry (Back-In; Pull-Through).";
		attributesWithDate = "Max Occupants(6), Pets Allowed(Domestic), Driveway Entry (Back-In).";

		bd.whereTextValue = "Reversed Creek Campground";
		bd.interestInValue = UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN;
		bd.lookFor = "RV sites";

		//Under park "Reversed Creek Campground", only below 4 sites have "Pull-Through" attribute
		//Booking data info for #0 site.
		bds[0] = new BookingData();
		bds[0].siteNo = "005";
		bds[0].siteID = "299294";

		//Booking data info for #1 site.
		bds[1] = new BookingData();
		bds[1].siteNo = "008";
		bds[1].siteID = "299297";

		//Booking data info for #2 site.
		bds[2] = new BookingData();
		bds[2].siteNo = "014";
		bds[2].siteID = "299303";

		//Booking data info for #3 site.
		bds[3] = new BookingData();
		bds[3].siteNo = "015";
		bds[3].siteID = "299304";

		//Available site (code:001) info for : Max Occupants(6), Driveway Entry (Back-In).
		bd.park = bd.whereTextValue.toUpperCase();
		bd.parkId = "72148";
		bd.contractCode = "NRSO";
		bd.siteNo = "001";
		bd.siteID = "299291";
		bd.siteRelationTypeID = web.getSiteRelationTypeID(bd.siteID, schema);
	}

	/**
	 * verify site amenities info based on different park info.
	 * @param parkName
	 * @param aminites
	 */
	private void verifySiteAttributes(String parkName, String aminites){
		RecgovViewAsListPage scResultPg = RecgovViewAsListPage.getInstance();
		String actualAmenities = scResultPg.getFirstParkAmenities();

		if(!MiscFunctions.compareResult("Amenities of park '"+parkName+"'", "Has sites with: " + aminites, actualAmenities)){
			throw new ErrorOnPageException();
		}
	}

	/**
	 * check the site is no longer available on the given date, if still available will book it.
	 * This work flow start from home page, ends at home page.
	 * @param bdata
	 */
	private void checkSiteBooked(BookingData[] bdatas){
		UwpMemberStatusBar memberStatusBar = UwpMemberStatusBar.getInstance();

		//initialize booking data arrival date and length of stay
		for(int i = 0 ; i < bds.length; i ++){
			bds[i].isUnifiedSearch = this.isUnifiedSearch();
			bds[i].park = bd.whereTextValue.toUpperCase();
			bds[i].parkId = bd.parkId;
			bds[i].siteRelationTypeID = web.getSiteRelationTypeID(bds[i].siteID, schema);
			bds[i].contractCode = "NRSO";
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
		logger.info("Verify the given date site was booked successfully");
	}
}
