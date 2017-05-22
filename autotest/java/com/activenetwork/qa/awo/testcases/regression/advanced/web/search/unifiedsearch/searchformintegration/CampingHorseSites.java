package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchformintegration;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 1: verify when select Camping & Lodging - Looking for: Horse sites, the data populated in the campground details page is correct.
 * @Preconditions:
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 2, 2011
 */
public class CampingHorseSites extends RecgovTestCase {
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
		bd2.isUnifiedSearch = this.isUnifiedSearch();
		
		//expect value 1
		bd2.whereTextValue = "CAMP SHERMAN CAMPGROUND";
		bd2.interestInValue = "Camping & Lodging";
		bd2.contractCode = "NRSO";
		bd2.parkId = "72099";
		
		bd2.lookFor = "Horse sites";
		bd2.length = "2";
		bd2.moreOptions = true;
		bd2.waterHookup = true;
		bd2.waterFront = true;
		bd2.pullthroughDriveWay = true;
		
		bd2.arrivalDate = DateFunctions.getDateAfterToday(3);
		bd2.flexibleValue = "Flexible for next 2 weeks";
		bd2.lengthOfStay = "3";
		bd2.accessibilityNeeds = true;
		bd2.petsAllowed = true;
		bd2.loop = "Any Loop";
		
		//expect value 2
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "CAMP SHERMAN CAMPGROUND";
		bd.contractCode = "NRSO";
		bd.parkId = "72099";
		bd.interestInValue = "Camping & Lodging";
		bd.lookFor = "Any type of site";
		
		bd.flexibleValue = "Not Flexible";
		bd.loop = "Any Loop";
		
	}
}
