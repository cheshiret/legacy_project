package com.activenetwork.qa.awo.testcases.regression.advanced.web.search.unifiedsearch.mapviewsearchresult;

import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description(P):
 * 1: Enter ARCHES NATIONAL PARK, UT  and click Search	3 results display
   2: verify there is a  Pin for the Target Result displays
   3: verify Results part of Arches National Park displays on the header
   4: verify A displays as Devils Garden Campground UT
   5: verify B displays as Fiery Furnace Tour - Arches UT
   6: Click on View as Map tab	A and B pins should display in view on the map
   7: Click on A pin	Bubble should not open as there is no Target pin
   8: Click on B pin	Bubble for A opens for Devils Garden Campground UT
   9: Bubble closes for A and opens for B Fiery Furnace Tour - Arches

 * @Preconditions:
 * The search location don't have latitude longitude, but has child facility.
 * must run under RFT.
 * @SPEC: Story J 
 * @Task#: AUTO - 839
 * 
 * @author bzhang
 * @Date  Dec 20, 2011
 */
public class NoLatLogWithChild extends RecgovTestCase {
	String headerNearMsg;
	int totalResult;
	//two child facility for "ARCHES NATIONAL PARK"
	UwpUnifiedSearch unified1 = new UwpUnifiedSearch();
	UwpUnifiedSearch unified2 = new UwpUnifiedSearch();
	
	public void execute(){
		web.invokeURL(url);
		this.gotoViewAsListPage(bd);
		this.verifySearchResultsInfoOnViewAsList();
		this.verifySearchResultInfoOnViewAsMap();
	}
	
	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		bd.isUnifiedSearch = this.isUnifiedSearch();
		bd.whereTextValue = "Mammoth Cave National Park";//"ARCHES NATIONAL PARK";
		bd.interestInValue = UwpUnifiedSearch.EVERYTHING_INSTERETED_IN;
		bd.contractCode ="NRSO";
		bd.parkId = "2818";//"2573";
		
		totalResult = 5; 
//		headerNearMsg = "Results part of Arches National Park";
		headerNearMsg = "Results part of " + bd.whereTextValue;
		//child facility 1
		unified1.whereTextValue = "MAMMOTH CAVE CAMPGROUND";//"DEVILS GARDEN CAMPGROUND";
		unified1.contractCode = bd.contractCode;
		unified1.parkId = "70947";//"74066";
		
		//child facility 2
		unified2.whereTextValue = "MAMMOTH CAVE NATIONAL PARK TOURS";//"FIERY FURNACE TOUR - ARCHES";
		unified2.contractCode = bd.contractCode;
		unified2.parkId = "77817";//"93768";
	}
	
	public void verifySearchResultsInfoOnViewAsList(){
		RecgovViewAsListPage listPg = RecgovViewAsListPage.getInstance();
		
		//3 results display
		listPg.verifyTotalSearchResults(totalResult);
		//there should be a  Pin for the Target Result displays[requirement changed see mail from Lisha Mon 1/16/2012 11:13 PM]
		listPg.verifyMapPinExists(bd.contractCode, bd.parkId, true);
		//Results part of Arches National Park displays on the header
		listPg.verifyViewHeaderNearValue(headerNearMsg);
	}
	
	public void verifySearchResultInfoOnViewAsMap(){
		RecgovViewAsListPage listPg = RecgovViewAsListPage.getInstance();
		UwpUnifiedSearchViewAsMapPage mapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		
		//click "View As Map" tab, goto map view page.
		listPg.clickViewAsMap();
		mapPg.waitLoading();
		mapPg.waitMapLoadingComplete();
		//verify Bubble should not open as there is no Target pin
		if(mapPg.checkMapBubbleWidgetExist()){
			throw new ErrorOnPageException("Bubble should not open as there is no Target pin.");
		}
		
		//Sara[20140303] Comment because Selenium can't identify Map pin in view as map page, only do javascript click it in view as list page
//		//A and B pins should display in view on the map
//		mapPg.checkMapPinExists("A");
//		mapPg.checkMapPinExists("B");
		
		//Click		Bubble for A opens for Devils Garden Campground UT
		mapPg.clickMapPin(unified1.contractCode, unified1.parkId);
		mapPg.mapBubbleWidgetWaitExists();
		if(mapPg.checkMapBubbleExist(unified1.whereTextValue)){
			logger.info("verify bubble widget display for park:" + unified1.whereTextValue + " successfully.");	
		}else{
			throw new ErrorOnPageException("verify bubble widget display for park:" + unified1.whereTextValue + " failed.");
		}
		
		//pan down two times to waiting for park B displayed.(this section needs to be optimized later time, it is better we can automatically control whether to click pan down/up ,left/right to find the target facility we want.)
		mapPg.clickPanDown();
		mapPg.clickPanDown();
		
		//Bubble closes for A and opens for B Fiery Furnace Tour - Arches
		mapPg.clickMapPin(unified2.contractCode, unified2.parkId);
		mapPg.mapBubbleWidgetWaitExists();
		if(mapPg.checkMapBubbleExist(unified2.whereTextValue)){
			logger.info("verify bubble widget display for park:" + unified2.whereTextValue + " successfully.");	
		}else{
			throw new ErrorOnPageException("verify bubble widget display for park:" + unified2.whereTextValue + " failed.");
		}
		
		if(mapPg.checkMapBubbleExist(unified1.whereTextValue)){
			throw new ErrorOnPageException("verify bubble widget display for park:" + unified1.whereTextValue + " failed.");
		}else{
			logger.info("verify bubble widget DON'T display for park:" + unified1.whereTextValue + " successfully.");	
		}
	}

}