package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.filter.interactions;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpViewAsListCommonPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Check the filters on search result page when search an event ticket lottery facility
 * @Preconditions:
 * Make sure the facility has setup the attribute (8462, attr_name=EventTicketLottery): 
 * About how to setup an event ticket lottery facility, please refer to http://wiki.reserveamerica.com/display/qa/Web+Event+Ticket+Lottery+Setup+%28QA+testing%29
 * @SPEC: Unified Search - Event Ticket Lottery - Filters on search results page [TC:100168]
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
 * @Date  Nov 27, 2013
 */
public class EventTicketLotteryFacility extends RecgovTestCase {
	private UwpViewAsListCommonPage unifiedParkListPg = UwpViewAsListCommonPage.getInstance();
	private UwpUnifiedSearchViewAsMapPage viewAsMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
	private String filterType, filterAgency, filterLetter;
	
	@Override
	public void wrapParameters(Object[] param) {
		schema = DataBaseFunctions.getSchemaName("NRRS", env);
		bd.park = "AUTO NATIONAL CHRISTMAS TREE LIGHTING OPENING CEREMONY";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.state = "District Of Columbia";
		bd.whereTextValue = bd.state;
		
		filterType = "Tours & Tickets";
		filterAgency = "US Forest Service";
		filterLetter = bd.park.substring(0, 1);
	}

	public void execute() {
		web.invokeURL(url);
		// Check filter in view as list page
		web.gotoViewAsListPage(bd);
		bd.parkId = DataBaseFunctions.getFacilityID(bd.park, schema);
		bd.contractCode = "NRSO";
		unifiedParkListPg.filterResults(filterType, StringUtil.EMPTY, StringUtil.EMPTY);
		unifiedParkListPg.verifyParkName(bd.contractCode, bd.parkId, bd.park);
		unifiedParkListPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_TYPE);
		
		unifiedParkListPg.filterResults(StringUtil.EMPTY, filterAgency, StringUtil.EMPTY);
		unifiedParkListPg.verifyParkName(bd.contractCode, bd.parkId, bd.park);
		unifiedParkListPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		
		unifiedParkListPg.filterResults(StringUtil.EMPTY, StringUtil.EMPTY, filterLetter);
		unifiedParkListPg.verifyParkName(bd.contractCode, bd.parkId, bd.park);
		unifiedParkListPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
		
		// Check filter in view as map page
		web.gotoViewAsMapFromViewAsList();
		viewAsMapPg.filterResults(filterType, StringUtil.EMPTY, StringUtil.EMPTY);
		viewAsMapPg.verifyMapPinExist(bd.contractCode, bd.parkId, true);
		viewAsMapPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_TYPE);
		
		viewAsMapPg.filterResults(StringUtil.EMPTY, filterAgency, StringUtil.EMPTY);
		viewAsMapPg.verifyMapPinExist(bd.contractCode, bd.parkId, true);
		viewAsMapPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_AGENCY);
		
		viewAsMapPg.filterResults(StringUtil.EMPTY, StringUtil.EMPTY, filterLetter);
		viewAsMapPg.verifyMapPinExist(bd.contractCode, bd.parkId, true);
		viewAsMapPg.clearFilter(UwpUnifiedSearch.FILTERCATEGORY_FIRSTLETTEROFNAME);
		
	}
}
