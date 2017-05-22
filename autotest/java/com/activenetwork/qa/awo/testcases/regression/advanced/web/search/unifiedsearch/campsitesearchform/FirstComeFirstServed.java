
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.campsitesearchform;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpDateRangeAvailabilityPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: Verify the Availability detail link is right. It can go to Date Range
Availability page and the first date on the calendar matrix should be the arrival date we entered.
 * @Preconditions:
 * 1: the park we are going to book is "W" only on the date we set below.
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 7, 2011
 */
public class FirstComeFirstServed extends RecgovTestCase {
	private String expectResDirective, expectAvaiText;
	
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		this.verifyParkResDirectiveAndAvailabilityTest();
		this.verifyStartDateOnDateRangeAvailabilityPg();		
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		expectResDirective = "First-Come-First-Served.";
		expectAvaiText = "Availability Details";
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		
		bd.whereTextValue = "Camp Sherman Campground".toUpperCase();
		bd.interestInValue = "Camping & Lodging";
		bd.arrivalDate = DateFunctions.getDateAfterToday(0);
		bd.lengthOfStay = "1";
		bd.contractCode = "NRSO";
		bd.parkId = "72099";
	}
	
	/**
	 * 
	 * verify the park reservation directive message display correctely, verify the availability message display correctly, verify the availability link can lead people to date range available page.
	 * start from search result page, end at campsite date range availability page.
	 */
	public void verifyParkResDirectiveAndAvailabilityTest(){
		RecgovViewAsListPage listPg = RecgovViewAsListPage.getInstance();
		UwpDateRangeAvailabilityPage dateRangePg = UwpDateRangeAvailabilityPage.getInstance();
		
		//verify the first park name, make sure the first park is the park we want.		
		listPg.verifyFirstParkName(bd.whereTextValue);
		
		//get the first come first served info on page, compare with the expect value.		
		listPg.verifyParkResDirectiveText(bd.contractCode, bd.parkId, expectResDirective);
		
		//verify availability details link text match with the given value
		listPg.verifyAvailabilityDetailsText(bd.contractCode, bd.parkId, expectAvaiText);
		
		//click the "Availability details" link goto campsite list page.
		listPg.clickAvailabilityDetails(bd.contractCode, bd.parkId);
		dateRangePg.waitLoading();		
	}
	
	/**
	 * 
	 * verify the start date on the matrix is the date we entered in the search panel arrive date feild.
	 * start and end at campsite date range availability page.
	 */
	public void verifyStartDateOnDateRangeAvailabilityPg(){
		UwpDateRangeAvailabilityPage dateRangePg = UwpDateRangeAvailabilityPage.getInstance();
		
		String currentStartDate = dateRangePg.getStartDateForDateRangeMatrix();
		if(DateFunctions.compareDates(currentStartDate, bd.arrivalDate)!=0){
			logger.error("Expect  Date Is:" + bd.arrivalDate);
			logger.error("Current Date Is:" + currentStartDate);
			throw new ErrorOnPageException("Date availabity page matrix start date verification failed.");
		}else{
			logger.info("Verify the start date on Date Range Availability matrix successfully.");
		}
		
		
	}
}
