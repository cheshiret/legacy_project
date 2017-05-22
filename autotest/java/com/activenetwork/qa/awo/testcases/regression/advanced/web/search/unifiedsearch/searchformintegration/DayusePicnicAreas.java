package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchformintegration;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * NOTE: pass on local
 * @Description:
 * 1: verify when select Day use & Picnic areas for Interested in dropdown list, the data populated in the campground details page is correct.
 * 2: verify when go back to home page via header bar, the next search criteria will be populated into search form on campground details page.
 * 3:
 * @Preconditions:
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 2, 2011
 */
public class DayusePicnicAreas extends RecgovTestCase {
	private BookingData bd2 = new BookingData();
	private String[] expectLookingFor;
	private UwpUnifiedFindProductCommonPanel findSitesPanel = UwpUnifiedFindProductCommonPanel.getInstance();
	private UwpUnifiedSearchPanel searchPanel = UwpUnifiedSearchPanel.getInstance();
	private UwpCampingPage campCommonPg = UwpCampingPage.getInstance();
	
	public void execute() {
		web.invokeURL(url);
		//1.populate Data to campground details search form from home page
		web.gotoCampgroundDetailsPg(bd);
		findSitesPanel.verifySearchFormElements(bd, expectLookingFor);
		
		//2.populate Data to campground details search form from home page(get to home page from top bar)
		web.gotoCampgroundDetailsPg(bd2);
		findSitesPanel.verifySearchFormElements(bd2, expectLookingFor);
		
		//3: view as list search form data should not be affected when making changes or not on campground details page
		web.gotoViewAsListPage();
		searchPanel.verifySearchCriteria(bd2);
		
		//4: populate data to home page from view as list page.(without change)
		searchPanel.setupUnifiedSearch(bd); //change value on unified search page without click search.
		web.gotoHomePage();
		searchPanel.verifySearchCriteria(bd2);
		
		//5: populdate data to home page from view as list page. (changes made on view as list page)
		this.gotoViewAsListPage(bd2);
		searchPanel.verifySearchCriteria(bd2);
		web.makeUnifiedSearch(bd);
		web.gotoHomePage();
		searchPanel.verifySearchCriteria(bd);
		
		//6.date populate from view as list page to camp ground details page(with change)
		web.gotoCampgroundDetailsPg(bd, false);
		findSitesPanel.verifySearchFormElements(bd, expectLookingFor);		
		
		//7. date populate from campground details page to view as list page(with change)
		findSitesPanel.makeSearch(bd2);
		campCommonPg.waitLoading();
		web.gotoViewAsListPage();
		searchPanel.verifySearchCriteria(bd); // the search parameter should retain what we entered in the view as list page rather than the changes we did on camp ground details page.
				
		//8. date populate from view as list page to camp ground details page(change without click search)
		bd2.clickSearch = false;
		web.gotoCampgroundDetailsPg(bd2, false);
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
		bd.interestInValue = "Day use & Picnic areas";
		
		bd.lookFor = "Day use & Picnic areas";
		bd.occupants = "2";
		bd.moreOptions = true;
		bd.electricHookupValue = "15 Amp (or more)";
		bd.waterFront = true;
		
		bd.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd.flexibleValue = "Flexible for next 2 weeks";
		bd.lengthOfStay = "3";
		bd.accessibilityNeeds = true;
		bd.petsAllowed = true;
		bd.loop = "Any Loop";
		
		//expect value 2
		bd2.isUnifiedSearch = bd.isUnifiedSearch;
		bd2.whereTextValue = "CAMP SHERMAN CAMPGROUND";
		bd2.contractCode = "NRSO";
		bd2.parkId = "72099";
		bd2.interestInValue = "Day use & Picnic areas";
		bd2.lookFor = "Day use & Picnic areas";
		bd2.flexibleValue = "Not Flexible";
		bd2.loop = "Any Loop";
	}
}
