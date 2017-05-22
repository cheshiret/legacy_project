package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.redirectrequest;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchHelpInfoPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * NOTE: pass on local
 * @Description:
 * Verify the user get redirected to the default unified search result page - the page with the instructional text
 * @Preconditions:
 * @SPEC:Story Z
* @Task#:AUTO-810
 * 
 * @author bzhang
 * @Date  Nov 14, 2011
 */
public class ToHelpPage extends RecgovTestCase {
	String envPrefix;
	List<String> urlRequests = new ArrayList<String>();
	private UwpUnifiedSearchHelpInfoPage helpPg = UwpUnifiedSearchHelpInfoPage.getInstance();
	
	public void execute() {
		for(String requestUrls: urlRequests){
			web.invokeURL(requestUrls);
			helpPg.waitLoading();
			helpPg.checkInstructionMessageExisting();
		}
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		urlRequests.add(url + "/campgroundSearch.do?isDayUse=Y");
		urlRequests.add(url + "/campgroundSearchResult.do?topTabIndex=CampingSpot");
		urlRequests.add(url + "/campgroundSearchResult.do?isDayUse=Y&topTabIndex=DayUse");
		urlRequests.add(url + "/recFacilitySearchResult.do?topTabIndex=RecreationArea");
		urlRequests.add(url + "/recFacilitySearch.do");
		urlRequests.add(url + "/tourParkSearchResult.do?topTabIndex=FindTour");
		urlRequests.add(url + "/permitFacilityList.do?contractCode=NRSO&topTabIndex=Permits");
		urlRequests.add(url + "/browseMapsRecGov.do?topTabIndex=CampgroundMap/browseMapsRecGov.do?topTabIndex=CampgroundMap");
		urlRequests.add(url + "/camping/map_of_California_North/r/generateBrowseMapRecGov.do?topTabIndex=CampgroundMap&stateCode=CA&zoom=9&map=BROWSE&long=-122.2&lat=40.3");
		urlRequests.add(url + "/generateSearchMapRecGov.do?topTabIndex=CampgroundMap&pageOrigin=mapSearch&zoom=9&map=SEARCH&long=-122.2&lat=40.3");
		urlRequests.add(url + "/campgroundSearch.do?pstate=CA");
	}
}
