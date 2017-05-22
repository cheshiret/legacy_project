
package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.searchresult;

import java.util.List;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovSiteListPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * NOTE: pass on local
 * @Description:
 * 1: verify site type link lead to site list page.
 * 2: verify site list page display site for the selected site type.
 * @Preconditions:
 * @SPEC: Story S - Campsite Search form and integration with Campground search form
 * @Task#: AUTO-786
 * 
 * @author bzhang
 * @Date  Nov 4, 2011
 */
public class SiteTypeRedirectVerify extends RecgovTestCase {
	private RecgovSiteListPage siteListPg = RecgovSiteListPage.getInstance();
	public void execute() {
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		this.gotoSiteListPage();
		
		//click the site type name, goto site list page.
		this.verifySiteInfoOnSiteListPg();
	}
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		
		bd.isUnifiedSearch = this.isUnifiedSearch();
		
		bd.whereTextValue = "CAMP SHERMAN CAMPGROUND";
		bd.interestInValue = "Camping & Lodging";
		
		bd.siteType = "STANDARD NONELECTRIC";
		bd.parkId = "72099";
		bd.contractCode = "NRSO";
	}
	
	/**
	 * 
	 * goto site list page via click the first park's given site type hyperlink.
	 */
	private void gotoSiteListPage(){
		RecgovViewAsListPage viewListPg = RecgovViewAsListPage.getInstance();
		
		viewListPg.clickSiteType(bd.parkId, bd.siteType);
		siteListPg.waitLoading();
	}
	
	/**
	 * verify the site display on site list page are all sites within the given site type.
	 */
	private void verifySiteInfoOnSiteListPg(){
		List<String> siteTypes = siteListPg.getAllSiteTypeNames();
		if(! (siteTypes.size() >0)){
			logger.error("There should be at least one site with the given site type displayed on site list page.");
			throw new ErrorOnPageException("The site list page should only display site with type of :" + bd.siteType);
		}
		
		for (int i = 0 ; i <siteTypes.size(); i ++){
			if(!siteTypes.get(i).equalsIgnoreCase(bd.siteType)){
				logger.error("The current #" + i + " site type is:" + siteTypes.get(i));
				logger.error("The expect  #" + i + " site type is:" + bd.siteType);
				throw new ErrorOnPageException("The site list page should only display site with type of :" + bd.siteType);
			}
		}
		logger.info("Verification for the site types on site list page successfully.");
	}

}
