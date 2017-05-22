package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.campdetaillayout;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpCampgroundMapPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpDateRangeAvailabilityPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1:	Verify the Left Column is displayed the same way in all Campground Detail Subpages:
 * "Campground Details" subpage
 * "Campground Map" subpage
 * "Campsite Search Result" subpage
 * "Date Range Availability" subpage
 * 
 * @Preconditions:
 * @SPEC: Story Q 
 * @Task#: AUTO - 849
 * 
 * @author bzhang
 * @Date  Jan 4, 2012
 */
public class LeftColumnPart extends RecgovTestCase{
	private RecgovParkDetailsPage campGroundDetailPg = RecgovParkDetailsPage.getInstance();

	public void execute(){
		web.invokeURL(url);
		web.gotoCampgroundDetailsPg(bd);
		this.verifyLeftColumnOnDifferentSubPages();
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "CAMP SHERMAN CAMPGROUND";
		bd.park = bd.whereTextValue;
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.stateCode = "OR";
		bd.contractCode ="NRSO";
		bd.parkId = "72099";
	}

	private void verifyLeftColumnCompoments() {
		//1: verify "Find Other Facilites" DIV exist.
		campGroundDetailPg.verifyFindOtherFacilitiesExist();
//		//2: verify left column, Google map DIV exist.//Sara[10/29/2013] Confirmed with Lisha and delete this check point for rec.gov using unified search
//		campGroundDetailPg.verifyGoogleMapExist();
		//3: verify left column Search widget exist.
		campGroundDetailPg.verifySearchFormExist();
		//4: verify left column AD Div Exist.
		campGroundDetailPg.verifyAdvertisementExist();
	}

	public void verifyLeftColumnOnDifferentSubPages(){
		UwpCampgroundMapPage mapPg = UwpCampgroundMapPage.getInstance();
		RecgovSiteListPage siteListPg = RecgovSiteListPage.getInstance();
		UwpDateRangeAvailabilityPage dateRangePg = UwpDateRangeAvailabilityPage.getInstance();

		//1: verify left side components on Facility Details sub page.
		this.verifyLeftColumnCompoments();

		//2: verify left side components on Facility Map page.
		campGroundDetailPg.clickCampgroundMap();
		mapPg.waitLoading();		
		this.verifyLeftColumnCompoments();

		//3: verify left side components on Site List page.
		mapPg.clickCampsiteList();
		siteListPg.waitLoading();
		this.verifyLeftColumnCompoments();

		//4: verify left side components on Date Range Availability page.
		siteListPg.clickDateRangeAvailability();
		dateRangePg.waitLoading();
		this.verifyLeftColumnCompoments();
	}
}
