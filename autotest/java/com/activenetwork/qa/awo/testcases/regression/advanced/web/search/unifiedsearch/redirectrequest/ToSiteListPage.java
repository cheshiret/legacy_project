package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.redirectrequest;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**Note: pass on local
 * @Description:
 * Verify the user get redirected to campsite search.do siteListPage.
 * @Preconditions:
 * @SPEC:Story Z
* @Task#:AUTO-810
 * 
 * @author bzhang
 * @Date  Nov 14, 2011
 */
public class ToSiteListPage extends RecgovTestCase {
	String envPrefix;
	String[] urlRequests = new String[1]; 
	private RecgovSiteListPage siteListPg = RecgovSiteListPage.getInstance();
	
	public void execute() {
		for(String requestUrls: urlRequests){
			web.invokeURL(requestUrls);
			siteListPg.waitLoading();
			siteListPg.verifyCampGroundNameAndRelatedStateCode(bd.park, bd.stateCode);
		}
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.ra.url");
		bd.park = "ANACAPA ISLAND";
		bd.stateCode = "CA";
		urlRequests[0] = url + "camping/Anacapa_Island_Ca/r/campsiteSearch.do?search=site&page=siteresult&contractCode=NRSO&parkId=70984&topTabIndex=CampingSpot";
	}
}
