package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpExternalWebsitePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpGoogleStateMapPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationAreaDetailsPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpRecreationSearchHomePage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class RecreationAreaSearch extends RecgovTestCase {
	/**
	 * Script Name   : <b>REC_RecreationAreaSearch</b>
	 * Generated     : <b>Nov 10, 2009 2:25:24 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/11/10
	 * @author vzhao
	 */

	public void execute() {
		web.invokeURL(url);

		if(bd.isUnifiedSearch){
			web.gotoHomePage();
			web.gotoUnifiedSearchSuggestionOrResultPage(bd.area, true);
			this.verifyGoogleMapDisplayViaUnifiedSearch();
			//Sara, 7/19/2013, per Simon, Originally the 'Visit Agency Website' button was speced to go to the agency web site. However, the client change that to go to the official web site of the facility. So we do not have a link to the agency anymore.
            //this.verifyExternalLinkViaUnifiedSearch(); 
		}else{
			if (!web.gotoRecreationAreaDetailsPg(bd))
				throw new ItemNotFoundException("The area name in detail page is NOT the same!");
			this.verifyGoogleMapDisplay();
			this.verifyExternalLink(); // verify whether link to http://www.af.mil/
		}
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.area="National Museum of the USAF";
		bd.agency = "US Air Force";
		
		bd.isUnifiedSearch=isUnifiedSearch();
	}

	public void verifyGoogleMapDisplay() {
		UwpRecreationSearchHomePage recreation = UwpRecreationSearchHomePage
				.getInstance();
		UwpGoogleStateMapPage stateMap = UwpGoogleStateMapPage.getInstance();

		recreation.gotoRegionalMap();
		stateMap.waitLoading();

		if (!web.verifyGoogleMapDisplay(bd.isUnifiedSearch))
			throw new ErrorOnPageException("Map displays error!");

		stateMap.gotoSpecialAreaDetailsPage("National Museum of the USAF, OH");
		recreation.waitLoading();
		recreation.gotoAgencySite("US Air Force");
	}

	public void verifyExternalLink() {
		UwpExternalWebsitePage extSite = UwpExternalWebsitePage.getInstance();

		logger.info("Verify the agency symbol link to US Air Force site.");
		extSite.waitLoading();
		extSite.waitForAirForcePageLoad();
		if(!extSite.isSpecialURLOpen("http://www.af.mil/")){
			throw new ItemNotFoundException(
					"The Air Force web site did not display");
		}
	}
	
	public void verifyGoogleMapDisplayViaUnifiedSearch(){
		RecgovViewAsListPage unifiedSearchResultPg = RecgovViewAsListPage.getInstance();
		UwpGoogleStateMapPage stateMap = UwpGoogleStateMapPage.getInstance();
		UwpRecreationAreaDetailsPage recAreaDetailPg = UwpRecreationAreaDetailsPage.getInstance();
		UwpUnifiedSearchViewAsMapPage mapPg =  UwpUnifiedSearchViewAsMapPage.getInstance();
		
//		unifiedSearchResultPg.waitLoading();
//		unifiedSearchResultPg.clickViewAsMap();
//		stateMap.waitLoading();
//		stateMap.waitMapFinishLoading();
//		
//		stateMap.clickAFromMapSeachViewInMap();
//		stateMap.waitLoading();
//		stateMap.gotoSpecialAreaDetailsPageFromMap("National Museum of the USAF");
		
		unifiedSearchResultPg.clickMapPin(bd.area); //(bd.contractCode, bd.parkId);
		mapPg.waitLoading();
		mapPg.mapBubbleWidgetWaitExists();
		mapPg.waitForParkNameInWidget(bd.whereTextValue);
		mapPg.clickParkNameInWidget(bd.area);
		recAreaDetailPg.waitLoading();
		
	}
	
	public void verifyExternalLinkViaUnifiedSearch() {
		UwpRecreationAreaDetailsPage recAreaDetailPg = UwpRecreationAreaDetailsPage.getInstance();
		UwpExternalWebsitePage extSite = UwpExternalWebsitePage.getInstance();

		logger.info("Verify the agency symbol link to US Air Force site.");
		recAreaDetailPg.gotoAgencySite("US Air Force");
		extSite.waitLoading();
		extSite.waitForAirForcePageLoad();
		if(!extSite.isSpecialURLOpen("http://www.af.mil/")){
			throw new ItemNotFoundException(
					"The Air Force web site did not display");
		}
	}
}
