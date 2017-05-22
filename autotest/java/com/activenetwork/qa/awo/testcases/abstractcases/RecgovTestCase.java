package com.activenetwork.qa.awo.testcases.abstractcases;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Brand;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.UwpUnifiedSearch;
import com.activenetwork.qa.awo.keywords.web.BWCooperator;
import com.activenetwork.qa.awo.pages.web.bw.BwSearchPanel;
import com.activenetwork.qa.awo.pages.web.recgov.RecgovViewAsListPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchSuggestionPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedSearchViewAsMapPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpViewAsListCommonPage;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


public abstract class RecgovTestCase extends WebTestCase {	
	protected BWCooperator bw=null;
	protected PermitInfo permit=null;
	
	protected RecgovTestCase(){
		permit=new PermitInfo();
		permit.isUnifiedSearch=true;
		
		bw=BWCooperator.getInstance();
		url = TestProperty.getProperty(env + ".web.recgov.url");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
	}
	
	protected boolean isUnifiedSearch() {
//		return Boolean.valueOf(TestProperty.getProperty("recgov.unified.search", "false"));
		return WebConfiguration.isUnifiedSearch(Brand.Rec_gov);
	}
	
	/**
	 * Goto view as list page/help page via home page unified search panel.
	 * @param bd
	 */
	protected void gotoViewAsListPage(UwpUnifiedSearch bd){
		RecgovViewAsListPage unifiedParkListPg = RecgovViewAsListPage.getInstance();
		UwpUnifiedSearchViewAsMapPage unifiedMapPg = UwpUnifiedSearchViewAsMapPage.getInstance();
		UwpUnifiedSearchSuggestionPage suggestPg = UwpUnifiedSearchSuggestionPage.getInstance();
		UwpViewAsListCommonPage viewAsListPg = UwpViewAsListCommonPage.getInstance();
		
		web.makeUnifiedSearch(bd);
		browser.waitExists(unifiedParkListPg, viewAsListPg, suggestPg);
		
		if(unifiedParkListPg.exists() || viewAsListPg.exists()){
			logger.info("Successfully go to view as list page.");
		}else if(suggestPg.exists()){
			if(!StringUtil.isEmpty(bd.parkId)&& !StringUtil.isEmpty(bd.contractCode)){
				suggestPg.waitForFacilitySuggestion(bd.parkId , bd.contractCode);
				suggestPg.clickFacilitySuggestion(bd.parkId , bd.contractCode);
			}else{
				if(bd.selectExactOption){
					suggestPg.clickExtraSuggestion(bd.whereTextValue);
				}else{
					suggestPg.clickFacilitySuggestion(bd.whereTextValue);
				}
			}
			Object objPage =browser.waitExists(unifiedParkListPg, unifiedMapPg);
			Browser.sleep(OrmsConstants.SHORT_SLEEP_BEFORE_CHECK);
			if (objPage ==unifiedMapPg){
				unifiedMapPg.clickViewAsList();
				unifiedParkListPg.waitLoading();
			}
		}
//		else throw new PageNotFoundException("Go to incorrect page.");
		
	}
	
	/**
	 * Go to view as list page from 
	 * @param contractCode
	 * @param parkID
	 */
	public void gotoViewAsListFromSuggestionPage(String contractCode, String parkID){
		UwpUnifiedSearchSuggestionPage suggestPg = UwpUnifiedSearchSuggestionPage.getInstance();
		RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
		UwpUnifiedSearchViewAsMapPage viewAsMap = UwpUnifiedSearchViewAsMapPage.getInstance();

		suggestPg.clickFacilitySuggestion(parkID , contractCode);

		Object objPage =browser.waitExists(viewAsList, viewAsMap);
		Browser.sleep(OrmsConstants.SHORT_SLEEP_BEFORE_CHECK);
		if (objPage ==viewAsMap){
			viewAsMap.clickViewAsList();
			viewAsList.waitLoading();
		}
	}
	
	public void gotoViewAsListFromSuggestionPage(String suggestion, boolean selectExactOption){
		UwpUnifiedSearchSuggestionPage suggestPg = UwpUnifiedSearchSuggestionPage.getInstance();
		RecgovViewAsListPage viewAsList = RecgovViewAsListPage.getInstance();
		UwpUnifiedSearchViewAsMapPage viewAsMap = UwpUnifiedSearchViewAsMapPage.getInstance();
		if(selectExactOption){
			suggestPg.clickExtraSuggestion(suggestion);
		}else{
			suggestPg.clickFacilitySuggestion(suggestion);
		}
		Object objPage =browser.waitExists(viewAsList, viewAsMap);
		Browser.sleep(OrmsConstants.SHORT_SLEEP_BEFORE_CHECK);
		if (objPage ==viewAsMap){
			viewAsMap.clickViewAsList();
			viewAsList.waitLoading();
		}
	}
	
	/**
	 * Go to 'Find Permits' page from 'View As List' page.
	 */
	public void gotoFindPermitsFromViewAsListPage(){
		RecgovViewAsListPage searchResult = RecgovViewAsListPage.getInstance();
		BwSearchPanel bwSearchPanel = BwSearchPanel.getInstance();
		logger.info("Go to 'Find Permits' page from 'View As List' page." );

		searchResult.clickFirstCheckAvailability();
		bwSearchPanel.waitLoading();
	}

	

}
