
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.campsitesearchform;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpDateRangeAvailabilityPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * Verify the Next Available Date button is right. 
 * It can go to Date Range Availability page and the first date on the calendar matrix should be an available Date we can reserved or display no availability through the end of booking window.
 * @Preconditions:
 * 1: the park we are going to book is not available on(and after) the date we set below. but available on the other days.
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 8, 2011
 */
public class NextAvailableDate extends RecgovTestCase {
	private String expectNextAvaiBtnText, noAvailabilityMsg1, noAvailabilityMsg2;
	private BookingData[] bd = new BookingData[2];
	
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd[0]);
		this.verifyNextAvailableDateButton(bd[0],noAvailabilityMsg1);
		
		web.gotoUnifiedSearchPanel(false);
		this.gotoViewAsListPage(bd[1]);
		this.verifyNextAvailableDateButton(bd[1],noAvailabilityMsg2);
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		expectNextAvaiBtnText = "Next Available Date";
		noAvailabilityMsg1 = "No availability through the end of booking window. Please try other campgrounds or perform another search with less criteria and/or earlier arrival date.";
		noAvailabilityMsg2 = "No availability through the end of booking window. Please try other campgrounds or perform another search with earlier arrival date.";
		
		bd[0]  = new BookingData();
		bd[0].isUnifiedSearch = this.isUnifiedSearch();
		
		bd[0].whereTextValue = "Camp Sherman Campground".toUpperCase();
		bd[0].interestInValue = "Camping & Lodging";
		bd[0].lookFor = "RV Sites";  //this is the key result in two different warning messages.
		bd[0].arrivalDate = DateFunctions.getDateAfterToday(300);
		bd[0].lengthOfStay = "1";
		bd[0].contractCode = "NRSO";
		bd[0].parkId = "72099";
		
		
		bd[1]  = new BookingData();
		bd[1].isUnifiedSearch = this.isUnifiedSearch();
		
		bd[1].whereTextValue = "Camp Sherman Campground".toUpperCase();
		bd[1].interestInValue = "Camping & Lodging";
		bd[1].lookFor = "Any type of site"; //this is the key result in two different warning messages.
		bd[1].arrivalDate = DateFunctions.getDateAfterToday(300);
		bd[1].lengthOfStay = "1";
		bd[1].contractCode = "NRSO";
		bd[1].parkId = "72099";
	}
	
	/**
	 * verify "Next available Date" button will be displayed on view as list page, if there is no site available on the given date.
	 * and after we click "Next Available Date" button, we should be redirect to camp date range available page.
	 * Start from view as list page, end at date range available page.
	 *
	 */
	public void verifyNextAvailableDateButton(BookingData bds,String warningMsg){
		RecgovViewAsListPage listPg = RecgovViewAsListPage.getInstance();
		UwpDateRangeAvailabilityPage dateRangePg = UwpDateRangeAvailabilityPage.getInstance();
		
		//verify the first park name, make sure the first park is the park we want.		
		listPg.verifyFirstParkName(bds.whereTextValue);
		
		//verify the Next Available Date button text
		listPg.verifyNextAvailableButtonText(bds.contractCode, bds.parkId, expectNextAvaiBtnText);
		
		//click the "Availability details" link goto campsite list page.
		listPg.clickNextAvailableDate(bds.contractCode,bds.parkId);
		dateRangePg.waitLoading();	
		
		//verify no Avaialbility through the end of booking window display
		dateRangePg.verifySubWarningMsg(warningMsg);
	}
}
