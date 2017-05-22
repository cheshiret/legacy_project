package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/** @Description(P):
 * 1: click the parent name in the bubble, verify recreation area details page displays.
 * @Preconditions:
 * This test case must run under RFT
 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 19, 2011
 */
public class ParentNameRedirect extends RecgovTestCase {
	public void execute(){
		web.invokeURL(url);
		web.gotoViewAsMapPage(bd);
		this.verifyParentNameRedirect();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "North Pines";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.contractCode ="NRSO";
		bd.parkId = "70927";
	}
	
	/**
	 * click the parent name in the bubble, verify recreation area details page displays.
	 */
	public void verifyParentNameRedirect(){
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		UwpRecreationAreaDetailsPage recAreaPg = UwpRecreationAreaDetailsPage.getInstance();
		
		mapPg.clickMapPin(bd.contractCode, bd.parkId);
		mapPg.waitForMapBubbleWidget();
		
		mapPg.waitForParentNameInWidget();
		mapPg.clickParentNameInWidget();
		recAreaPg.waitLoading();
	}

}
