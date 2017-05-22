package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.redirectrequest;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchHelpInfoPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * NOTE: pass on local
 * @Description:
 * If unified search has been previously performed in the same session, the session information shall be cleared and the default search result page shall be displayed
 * @Preconditions:
 * @SPEC:Story Z
 * @Task#:AUTO-810
 * 
 * @author bzhang
 * @Date  Nov 14, 2011
 */
public class SameSessionRedirect extends RecgovTestCase {
	String envPrefix, urlRequests;
//	protected UwpUnifiedSearch unifiedSearch = new UwpUnifiedSearch(); 
	private RecgovViewAsListPage siteListPg = RecgovViewAsListPage.getInstance();
	private UwpUnifiedSearchHelpInfoPage helpPg = UwpUnifiedSearchHelpInfoPage.getInstance();
	
	public void execute() {
		//make unified search, check the first park match with given value.
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		siteListPg.verifyFirstParkName(bd.whereTextValue);
		
		//Verify the user gets redirected to the unifSearch.do page and not the previous sessions result
		web.invokeURL(urlRequests,false,false);
		helpPg.waitLoading();
		helpPg.checkInstructionMessageExisting();
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.whereTextValue = "ANACAPA ISLAND";
		bd.parkId = "70984";
		bd.contractCode = "NRSO";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		
		urlRequests = url + "/campgroundSearch.do?pstate=CA&siteType=2001&arvdate=09/31/2007&lengthOfStay=2&expwith=1&amenity=4005&expfits=1&hookup=3002&water=3006&sewerhookup=3007&pet";
	}
}
