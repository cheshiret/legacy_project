package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult.sitesamenities;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpMemberStatusBar;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * Pets Allowed Hook-UP
 * Park "PRAIRIE CREEK (AR)" has 7 sites, only one site (ID:304759) has max.occupants over 100 and no "Pets Allowed" attribute, others have "Pets Allowed" attribute but max.ocupants is less than 100
 * 1:  search without setting specific date, "Pets Allowed" attribute of this park displays.
 * 2:  search with setting specific date and occupants=120, "Pets Allowed" attribute of this park doesn't display
 * 
 * @Preconditions:
 * 1: the specific site has season info.
 * 2: the facility has 7 group sites among which only 1 site not-pets allowed and max occupants over 100, the other left 6 sites were booked/already occupied before we run this script.
 * 
 * @SPEC: Story P - part 1 - camping & lodging search results - Common site attributes 
 * @Task#: AUTO-780, Auto-1818
 * 
 * @author bzhang, Swang
 * @Date  Oct 28, 2011, Aug 8, 8
 */

public class PetsAllowedAttribute extends RecgovTestCase {
	private BookingData[] bds = new BookingData[6];
	private String attributes;

	public void execute() {
		//Condition 1: search without setting specific date, verify first park "PRAIRIE CREEK (AR)" has "Pets Allowed" amenties because of at lease one site has this attribute
		web.invokeURL(url);
		web.gotoViewAsListPage(bd);
		this.verifySiteAttributes(attributes, true);

		//Condition 2: search with setting specific date (the day in the end of current year so that the precondition booked sites can be recycling used)
		bd.arrivalDate = "12/31/"+DateFunctions.getCurrentYear(); 
		bd.lengthOfStay = "1";
		bd.occupants = "120";

		//Only the site (ID:304759) which max.occupants over 100 and no "Pets Allowed" attribute is available, other 6 sites should be booked. 
		web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		web.invokeURL(url);
		checkSiteBooked(bds);

		//Go to view as list page to check first park doesn't have "Pets Allowed" amenties because the only available site is no this attribute.
		web.gotoViewAsListPage(bd);
		this.verifySiteAttributes(attributes, false);
	}

	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");
		bd.isUnifiedSearch = this.isUnifiedSearch();

		attributes = "Pets Allowed";

		bd.parkId = "71366";
		bd.whereTextValue = DataBaseFunctions.getFacilityName(bd.parkId, schema); //"PRAIRIE CREEK (AR)";
		bd.interestInValue = UwpUnifiedSearch.CAMPINGANDLODGING_INSTERETED_IN;
		bd.lookFor = "Group sites";

		bd.park = bd.whereTextValue;
		bd.clickClearSearch = true;
		bd.contractCode = "NRSO";

		//Booking data info for #6 site.
		bd.siteNo = "Prairie Creek Grand Shelter";
		bd.siteID = "304759";
		bd.siteRelationTypeID = web.getSiteRelationTypeID(bd.siteID, schema);

		//Booking data info for #0 site.
		bds[0] = new BookingData();
		bds[0].siteNo = "EAST";
		bds[0].siteID = "75848";
		bds[0].siteRelationTypeID = web.getSiteRelationTypeID(bds[0].siteID, schema);

		//Booking data info for #1 site.
		bds[1] = new BookingData();
		bds[1].siteNo = "GS1";
		bds[1].siteID = "148575";
		bds[1].siteRelationTypeID = web.getSiteRelationTypeID(bds[1].siteID, schema) ;

		//Booking data info for #2 site.
		bds[2] = new BookingData();
		bds[2].siteNo = "GS2";
		bds[2].siteID = "148576";
		bds[2].siteRelationTypeID = web.getSiteRelationTypeID(bds[2].siteID, schema);

		//Booking data info for #3 site.
		bds[3] = new BookingData();
		bds[3].siteNo = "GS3";
		bds[3].siteID = "148577";
		bds[3].siteRelationTypeID = web.getSiteRelationTypeID(bds[3].siteID, schema);

		//Booking data info for #4 site.
		bds[4] = new BookingData();
		bds[4].siteNo = "OVER";
		bds[4].siteID = "148574";
		bds[4].siteRelationTypeID = web.getSiteRelationTypeID(bds[4].siteID, schema);

		//Booking data info for #5 site.
		bds[5] = new BookingData();
		bds[5].siteNo = "WEST";
		bds[5].siteID = "75847";
		bds[5].siteRelationTypeID = web.getSiteRelationTypeID(bds[5].siteID, schema);
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

		//initialize booking data arrival date and length of stay
		for(int i = 0 ; i < bds.length; i ++){
			bds[i].isUnifiedSearch = this.isUnifiedSearch();
			bds[i].whereTextValue = bd.whereTextValue;
			bds[i].park = bd.park;
			bds[i].parkId = bd.parkId;
			bds[i].contractCode = bd.contractCode;
			bds[i].arrivalDate = bd.arrivalDate;
			bds[i].lengthOfStay = bd.lengthOfStay;

			//Book site if it is available
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
