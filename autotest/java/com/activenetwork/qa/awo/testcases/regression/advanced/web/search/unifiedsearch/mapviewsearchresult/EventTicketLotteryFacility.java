package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: Search an event ticket lottery facility and check the search reuslt in view as map page
 * @Preconditions:
 * Make sure the facility has setup the attribute (8462, attr_name=EventTicketLottery): 
 * right now for automation, the value of the attribute is setup as "/ticketLotteryApplication.do".
 * About how to setup an event ticket lottery facility, please refer to http://wiki.reserveamerica.com/display/qa/Web+Event+Ticket+Lottery+Setup+%28QA+testing%29
 * @SPEC: 
 * Unified Search - Event Ticket Lottery - Map View Search results [TC:100161]
 * @LinkSetUp:
 * d_inv_new_facility:id=90
 * d_inv_upd_park_web_setting_attri:id=60
 * d_assign_user_roles:id=740
 * d_inv_create_tour:id=1190
 * d_inv_tour_inventory:id=1290
 * d_fin_ticket_fee_sched:id=1150
 * d_inv_new_lottery_program:id=570
 * @Task#: Auto-1959
 * 
 * @author Lesley Wang
 * @Date  Nov 26, 2013
 */
public class EventTicketLotteryFacility extends RecgovTestCase {
	private UwpUnifiedSearchViewAsMapPage viewAsMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
	private String nearByPark, facilityParent, facilityAgency, facilityAgeCode, nearMsg_1, nearMsg_2;
	
	@Override
	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		bd.park = "Auto National Christmas Tree Lighting Opening Ceremony";
		bd.parkId = DataBaseFunctions.getFacilityID(bd.park, schema); 
		bd.whereTextValue = bd.park;
		bd.contractCode = "NRSO";
//		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.interestInValue = UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN;//Quentin[20140516] update by Sara's suggestion
		bd.state = "District Of Columbia";
		bd.stateCode = "DC"; 
		nearMsg_1 = "Results near " + bd.park  + "\\s*\\[ \\* in straight line, not driving distance \\]";	
		
		nearByPark = web.getFacilityName("77812", schema); //FREDERICK DOUGLASS NATIONAL HISTORIC SITE TOURS
		facilityParent = "PLUMAS NF - FS";
		facilityAgency = "US Forest Service";
		facilityAgeCode = "USFS";
//		nearMsg_2 = "Results near " + nearByPark  + "\\s*\\[ \\* in straight line, not driving distance \\]";
		nearMsg_2 = "All Tours & Tickets Facilities\\s*\\[ \\* in straight line, not driving distance \\]";//Quentin[20140516] update by Sara's suggestion
		newBd.whereTextValue = nearByPark;
		newBd.interestInValue = bd.interestInValue;
	}

	public void execute() {
		web.invokeURL(url);
		
//		//1. search the facility with interested in = 'Everything' and check the result: map pin, faciilty name, description, header
//		web.gotoViewAsMapPage(bd);
//		viewAsMapPg.gotoMapBubbleWidget(bd.contractCode, bd.parkId);
//		this.verifyEventTicketLotteryFacilityInMapView(false, nearMsg_1);
//		
		//Sara[20140303] Have confirmed with James that elenium can't identify small map pin because Html5 tags have some new technology
//		//2. Click the small tour pin for the facility and check the details in map bubble
//		viewAsMapPg.gotoNext();
//		this.openMapBubbleBySmallTourPin();
//		this.verifyEventTicketLotteryFacilityInMapView(true);

		//3. search a facility which is close to this event lottery facilities and check the details in map bubble
		web.gotoViewAsMapPage(newBd);
		viewAsMapPg.gotoMapBubbleWidget(bd.contractCode, bd.parkId);
		this.verifyEventTicketLotteryFacilityInMapView(false, nearMsg_2);
		
	}

	private void verifyEventTicketLotteryFacilityInMapView(boolean bySmallPin, String nearMsg) {
		boolean result = true;
		
		// Verify facility info in Map Bubble
		if (bySmallPin) {
			viewAsMapPg.verifyFirstFacilityViewHeaderFormat(bd.whereTextValue, facilityParent, bd.stateCode, facilityAgeCode);	
		} else {
			viewAsMapPg.verifyFirstFacilityViewHeaderFormat(bd.whereTextValue, facilityParent, bd.state, facilityAgency);	
			result &= MiscFunctions.compareResult("Check Availability button shown on map", false, viewAsMapPg.isCheckAvailBtnOnMapBubbleExist());	
		}
		result &= MiscFunctions.compareResult("Description shown on Map Bubble", false, viewAsMapPg.isFacilityViewContentDisplayed());
		result &= MiscFunctions.compareResult("Tour and Tickets info shown on map", false, viewAsMapPg.isTourTitleOnMapBubbleExist());
		
		// verify header in map view
		result &= MiscFunctions.matchString("Facility map view header near message", viewAsMapPg.getFacilityMapViewNearMsg(), nearMsg);
		
		if (!result) {
			throw new ErrorOnPageException("The event ticket lottery is displayed wrongly in map bubble! Check logger error above!");
		}
		logger.info("Verify The event ticket lottery displayed correctly in map bubble!");
	}
	
	private void openMapBubbleBySmallTourPin() {
		int tourPinNum = viewAsMapPg.getSmallTourPinNum();
		for (int i = 0; i < tourPinNum; i++) {
			viewAsMapPg.clickSmallTourPin(i);
			viewAsMapPg.mapBubbleWidgetWaitExists();
			if (viewAsMapPg.getFirstFacilityViewHeader().contains(bd.park)) {
				logger.info("The searched park map bubble has been opened!");
				break;
			} 
		}
	}
}