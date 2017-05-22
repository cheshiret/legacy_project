package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.campdetaillayout;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 *	1: verify the Overview section will appear on all Overview sub pages.
 *  2: verify Book Now button appear on all Overview sub pages.
 * @Preconditions:
 * @SPEC: Story Q 
 * @Task#: AUTO - 849
 * 
 * @author bzhang
 * @Date  Jan 5, 2012
 */
public class OverviewNavigation extends RecgovTestCase{

	public void execute(){
		web.invokeURL(url);
		web.gotoCampgroundDetailsPg(bd);
		this.verifyOverviewSectionOnAllSubPages();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "AMITY";
		bd.park = bd.whereTextValue;
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.contractCode ="NRSO";
		bd.parkId = "71005";
	}
	
	public void verifyOverviewSectionOnAllSubPages(){
		RecgovParkDetailsPage detailPg = RecgovParkDetailsPage.getInstance();
		
		//1: verify Overview widget exist the first time Facility Details page loaded.
		detailPg.verifyOverviewWidgetExist();
		detailPg.verifyBookNowButtonExist();
		
		//2: verify Overview widget and BookNow exist on "Season Dates" subPage
		detailPg.clickSeasonDates();
		detailPg.waitLoading();
		detailPg.verifyOverviewWidgetExist();
		detailPg.verifyBookNowButtonExist();
		
		//3: verify Overview widget and BookNow exist on "Booking Window" subPage
		detailPg.clickBookingWindow();
		detailPg.waitLoading();
		detailPg.verifyOverviewWidgetExist();
		detailPg.verifyBookNowButtonExist();
		
		//4: verify Overview widget and BookNow exist on "Fees and Cancellation" subPage
		detailPg.clickFeesAndCancellation();
		detailPg.waitLoading();
		detailPg.verifyOverviewWidgetExist();
		detailPg.verifyBookNowButtonExist();
	}

}
