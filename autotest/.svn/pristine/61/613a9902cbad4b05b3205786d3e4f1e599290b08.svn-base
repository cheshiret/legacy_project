package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkDetailsPage;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovParkListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchPanel;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class LocationHierarchy extends RecgovTestCase {
	/**
	 * Script Name   : <b>LocationHierarchy</b>
	 * Generated     : <b>Jan 26, 2010 9:50:42 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/01/26
	 * @author vzhao
	 */
  	private String regionName;

	public void execute() {
		web.invokeURL(url);

//		this.gotoSearchPark();
		bd.parkId = "70984";
		this.searchAndVerifyParkRegion("Anacapa Island");
		bd.parkId = "70980";
		this.searchAndVerifyParkRegion("Santa Cruz Scorpion");
		bd.parkId = "70928";
		this.searchAndVerifyParkRegion("Lower Pines");
	}

	public void wrapParameters(Object[] param) {
		url = TestProperty.getProperty(env + ".web.recgov.url");
		
		bd.state = "California";
		bd.lengthOfStay = "2";
		
		bd.contractCode = "NRSO";
		bd.isUnifiedSearch=isUnifiedSearch();
	}

//	// go to search park panel
//	public void gotoSearchPark() {
//		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
//		UnifiedSearchPanel unifiedSearchPanel = UnifiedSearchPanel.getInstance();
//		
//		if(!bd.isUnifiedSearch){
//			headerBar.gotoFindCampSpot();			
//		}else{
//			if(!unifiedSearchPanel.exists()){
//				headerBar.gotoHome();
//			}
//		}
//	}
	
	//search and verify each park's region
	public void searchAndVerifyParkRegion(String park) {
		UwpHeaderBar headerBar = UwpHeaderBar.getInstance();
		UwpSearchPanel searchPanel = UwpSearchPanel.getInstance();
	  	RecgovParkListPage parkListPg = RecgovParkListPage.getInstance();
	  	UwpUnifiedSearchPanel unifiedSearch = UwpUnifiedSearchPanel.getInstance();
	  	RecgovParkDetailsPage parkDetailPg = RecgovParkDetailsPage.getInstance();
	  	UwpUnifiedSearchSuggestionPage suggestionPage=UwpUnifiedSearchSuggestionPage.getInstance();
	  	
	  	logger.info("Search and verify park " + park + "'s region.");
	  	bd.park = park;
	  	if(!bd.isUnifiedSearch){
			headerBar.gotoFindCampSpot();
			searchPanel.waitLoading();
		  	searchPanel.setupSearchCriteria(bd);
		  	searchPanel.removeStartDate();// search without any dates
			searchPanel.clickSearch();
			searchPanel.searchParkWaitExists();			
			regionName=parkListPg.getParkRegionName(park);//get the region name
		}else{
			// for unified search panel, the page mark is not unique now
//			if(!unifiedSearch.exists()){
//				headerBar.gotoHome();
//			}
			headerBar.clickHomeLink();
			unifiedSearch.waitLoading();
			unifiedSearch.setupCampingSearchCriteria(bd);	  		
	  		unifiedSearch.clickSearch();
	  		
	  		Object page=browser.waitExists(suggestionPage,parkListPg);
        	if(page==suggestionPage){
        		suggestionPage.clickFacilitySuggestion(bd.parkId, bd.contractCode);
			}
        	
	  		parkListPg.waitLoading();
	  		parkListPg.clickFacility(park);
	  		parkDetailPg.waitLoading();
	  		regionName=parkDetailPg.getParkRegionName();
		}
	  		  	
		if(park.equalsIgnoreCase("Anacapa Island")) {
		  	if(regionName.equalsIgnoreCase("Channel Islands National Park")) {
		  	  	logger.info("Region name is right.");
		  	}else {
		  	  	throw new ItemNotFoundException("Region name is wrong!");
		  	}
		}else if(park.equalsIgnoreCase("Santa Cruz Scorpion")) {
		  	if(regionName.equalsIgnoreCase("Channel Islands National Park")) {
		  	  	logger.info("Region name is right.");
		  	}else {
		  	  	throw new ItemNotFoundException("Region name is wrong!");
		  	}
		}else if(park.equalsIgnoreCase("Lower Pines")) {
		  	if(regionName.equalsIgnoreCase("Yosemite National Park")) {
		  	  	logger.info("Region name is right.");
		  	}else {
		  	  	throw new ItemNotFoundException("Region name is wrong!");
		  	}
		}
	}
}
