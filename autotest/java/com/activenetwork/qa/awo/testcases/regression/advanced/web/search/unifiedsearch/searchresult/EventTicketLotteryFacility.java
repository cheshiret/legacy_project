package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpViewAsListCommonPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description: Search an event ticket lottery facility and check the search reuslt in view as list page
 * @Preconditions:
 * Make sure the facility has setup the attribute (8462, attr_name=EventTicketLottery): 
 * right now for automation, the value of the attribute is setup as "/ticketLotteryApplication.do".
 * About how to setup an event ticket lottery facility, please refer to http://wiki.reserveamerica.com/display/qa/Web+Event+Ticket+Lottery+Setup+%28QA+testing%29
 * @SPEC: 
 * Unified Search - Event Ticket Lottery - Auto Complete / Suggestion page [TC:100162]
 * Unified Search - Event Ticket Lottery - List View Search results [TC:070812]
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
	private UwpViewAsListCommonPage unifiedParkListPg = UwpViewAsListCommonPage.getInstance();
	private String nearByPark, viewHeader, parkDes;
	
	@Override
	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		bd.park = "Auto National Christmas Tree Lighting Opening Ceremony";
		bd.parkId = DataBaseFunctions.getFacilityID(bd.park, schema);
		bd.whereTextValue = bd.park.toUpperCase();
		bd.contractCode = "NRSO";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.state = "District Of Columbia";
				
		nearByPark = web.getFacilityName("77812", schema); //FREDERICK DOUGLASS NATIONAL HISTORIC SITE TOURS
		viewHeader = bd.park + ", " + bd.state + "\\s*" + "part of\\s*PLUMAS NF - FS\\s*,\\s*US Forest Service";
		parkDes = "QA automation test for ticket lottery";
	}

	public void execute() {
		web.invokeURL(url);
		
		//1. search the facility in REC.gov and check it shown in auto-complete list.
		this.verifyInAutoCompleteBox("Auto National");
		
		//2. search the facility with interested in = 'Everything' and check the result: map pin, faciilty name, description, header
		web.gotoViewAsListPage(bd);
		unifiedParkListPg.verifyFirstParkName(bd.park);
		this.verifyEventTicketLotteryFacilityInListView();
		
		//3. search the facility with interested in = 'Tour & Tickets' and check the facility shown
		bd.interestInValue = UwpUnifiedSearch.TOURSTICKETS_INSTERETED_IN;
		web.gotoViewAsListPage(bd);
		unifiedParkListPg.verifyFirstParkName(bd.park);
		
		//4. search the facility with keywords = "District Of Columbia" and interested in = 'Tour & Tickets' and check the facility shown
		bd.whereTextValue = bd.state;
		bd.contractCode = "";
		bd.parkId = "";
		web.gotoViewAsListPage(bd);
		List<String> parks = unifiedParkListPg.getAllParkNames();
		unifiedParkListPg.verifyParkDisplays(bd.park, parks, true);
		
		//5. search a facility which is the closest one  to this event lottery facilities and check the near by list
		bd.whereTextValue = nearByPark;
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		web.gotoViewAsListPage(bd);
		unifiedParkListPg.verifyFirstParkName(bd.whereTextValue);
		parks = unifiedParkListPg.getFirstPgParkNames();
		unifiedParkListPg.verifyParkDisplays(bd.park, parks, true);
	}
	
	private void verifyInAutoCompleteBox(String keyword) {
		UwpUnifiedSearchPanel panel=UwpUnifiedSearchPanel.getInstance();
		List<String> parks;
		List<String> parkNames=new ArrayList<String>();
		panel.waitLoading();
		panel.setWhere(keyword);
		panel.triggerAutoCompleteBoxDisplay();
		parks=panel.getFacilityOptions();
		panel.removeFocus();
			
		if(parks==null ||parks.size()==0){
			throw new ErrorOnPageException("There should be at least one option in auto complete list");
		}
			
		for(String park:parks){
			parkNames.add(park.split(",")[0].trim());
		}
		
		if(!parkNames.contains(bd.whereTextValue.toUpperCase())){
			throw new ErrorOnPageException("Event Ticket Lottery Park:"+bd.whereTextValue+" should be displayed in auto complete list when searching");
		}

		logger.info("Verify Event Ticket Lottery Park:"+bd.whereTextValue+" shown in autocomplete-box successfully.");
	}
	
	private void verifyEventTicketLotteryFacilityInListView() {
		boolean result = true;
		// map pin
		unifiedParkListPg.verifyMapPinExists(bd.contractCode, bd.parkId, true);
		
		// header info: name, state, parent name, agency name
		String firstFacilityViewHeader = unifiedParkListPg.getFirstFacilityViewHeader();
		result &= MiscFunctions.matchString("Facility header info", firstFacilityViewHeader, viewHeader);
		
		// facility description
		String actaulDes = unifiedParkListPg.getParkDescription(bd.contractCode, bd.parkId);
		result &= MiscFunctions.compareString("Facility description", parkDes, actaulDes);
		
		// no product description or action button shown
		result &= MiscFunctions.compareResult("Product Description shown", false, unifiedParkListPg.isParkTourDescriptionExist(bd.contractCode, bd.parkId));
		result &= MiscFunctions.compareResult("Action Button exist", false, unifiedParkListPg.isCheckAvailableButtonExist(bd.contractCode, bd.parkId));
		
		if (!result) {
			throw new ErrorOnPageException("The event ticket lottery is displayed wrongly in view as list page! Check logger error above!");
		}
		logger.info("Verify The event ticket lottery displayed correctly in view as list page!");
	}
}
