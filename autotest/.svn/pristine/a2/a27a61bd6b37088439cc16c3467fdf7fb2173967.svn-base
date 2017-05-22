package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchformintegration;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 1: Verify the Flexibility parameters will replace the \ufffdDate range\ufffd function. There is no \ufffdDate range\ufffd field.
 * 2: Verify there is a \ufffdClear Search\ufffd link in the  Facility Details Search Form top.
 * 3: Verify 'Looking for' is drop-down field and drop down list include o Values include: Any type of site (default value), RV sites, Trailer sites, Tent, Tent only sites, Cabins & Other Lodging, Lookouts, Group sites, Day Use & Picnic areas, Horse sites, Boat sites, Yurts.
 * 4: Verify the site attributes shall be dynamically displayed based on the \ufffdLooking for\ufffd selection made, removing the \ufffdSite with\ufffd\ufffd label. 
 * @Preconditions:
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 1, 2011
 */
public class AnyRecreation extends RecgovTestCase {
	private BookingData bd3 = new BookingData();
	private String[] expectLookingFor;
	private UwpUnifiedFindProductCommonPanel findSitesPanel = UwpUnifiedFindProductCommonPanel.getInstance();
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private UwpCampingPage campCommonPg = UwpCampingPage.getInstance();
	
	public void execute() {
		web.invokeURL(url);
		//1.populate Data to campground details search form from home page
		web.gotoCampgroundDetailsPg(bd);
		findSitesPanel.verifySearchFormElements(bd, expectLookingFor);
		
		
		//3: view as list search form data should not be affected when making changes or not on campground details page
		web.gotoViewAsListPage();
		searchPanel.verifySearchCriteria(bd,true);
		
		//4: populate data to home page from view as list page.(without change)
		searchPanel.setupUnifiedSearch(bd3); //change value on unified search page without click search.
		web.gotoHomePage();
		searchPanel.verifySearchCriteria(bd,true);
		
		//5: Populate data to home page from view as list page. (changes made on view as list page)
		this.gotoViewAsListPage(bd);
		searchPanel.verifySearchCriteria(bd,true);
		web.makeUnifiedSearch(bd);
		web.gotoHomePage();
		searchPanel.verifySearchCriteria(bd,true);
		
		//6.date populate from view as list page to camp ground details page(with change)
		web.gotoCampgroundDetailsPg(bd, false);
		findSitesPanel.verifySearchFormElements(bd, expectLookingFor);		
		
		//7. date populate from campground details page to view as list page(with change)
		findSitesPanel.makeSearch(bd3);
		campCommonPg.waitLoading();
		web.gotoViewAsListPage();
		searchPanel.verifySearchCriteria(bd,true); // the search parameter should retain what we entered in the view as list page rather than the changes we did on camp ground details page.
				
		//8. date populate from view as list page to camp ground details page(change without click search)
		bd3.clickSearch = false;
		web.gotoCampgroundDetailsPg(bd3, false);
		findSitesPanel.verifySearchFormElements(bd, expectLookingFor);
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		expectLookingFor = new String[]{"Any type of site", "RV sites", "Trailer sites", "Tent", "Tent-only sites", "Cabins", "Lookouts", "Group sites", "Day use & Picnic areas", "Horse sites", "Boat sites", "Yurts"};
		bd.isUnifiedSearch = this.isUnifiedSearch();
		
		//expect value 1
		bd.whereTextValue = "CAMP SHERMAN CAMPGROUND";
		bd.contractCode = "NRSO";
		bd.parkId = "72099";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		
		bd.lookFor = "Any type of site";
		bd.loop = "Any Loop";
		bd.flexibleValue = "Not Flexible";
		
		//booking data for campsite ground details page.
		bd3.isUnifiedSearch = bd.isUnifiedSearch;
		bd3.whereTextValue = "CAMP SHERMAN CAMPGROUND";
		bd3.contractCode = "NRSO";
		bd3.parkId = "72099";
		bd3.interestInValue = "Camping & Lodging";
		bd3.lookFor = "Any type of site";
		bd3.loop = "Any Loop";
		bd3.flexibleValue = "Not Flexible";
		bd3.lengthOfStay = "2";
		bd3.accessibilityNeeds = true;
	}
}
