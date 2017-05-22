/**
 * 
 */
package com.activenetwork.qa.awo.keywords.web;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.maintenanceapps.MarketingSpotAttr;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.MarketingSpotDetailPage;
import com.activenetwork.qa.awo.pages.web.maintenanceapps.MarketingSpotSummaryPage;
import com.activenetwork.qa.testapi.datacollection.Data;

/**
 * @Description: These methods are for only for Marketing Spot Manager Application 
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Dec 16, 2012
 */
public class MarketingSpotManagerApplication extends MaintenanceApplication {
	private static MarketingSpotManagerApplication _instance = null;

	public static MarketingSpotManagerApplication getInstance() {
		if (null == _instance)
			_instance = new MarketingSpotManagerApplication();

		return _instance;
	}
	
	protected MarketingSpotManagerApplication() {
	}
	
	public void addNewSport(String title, String spotLocal, String priority, String website){
		MarketingSpotSummaryPage marketingSpotSummaryPg = MarketingSpotSummaryPage.getInstance();
		MarketingSpotDetailPage marketingSpotDetailPg = MarketingSpotDetailPage.getInstance();
		
		marketingSpotSummaryPg.clickAddNewSpotButton();
		marketingSpotDetailPg.waitLoading();
		
		marketingSpotDetailPg.setMarketingSpotDetail(title, spotLocal, priority, website);
		marketingSpotDetailPg.clickSubmit();
		marketingSpotSummaryPg.waitLoading();
	}
	
	public void addNewSpot(Data<MarketingSpotAttr> src) {
		MarketingSpotSummaryPage marketingSpotSummaryPg = MarketingSpotSummaryPage.getInstance();
		MarketingSpotDetailPage marketingSpotDetailPg = MarketingSpotDetailPage.getInstance();
		
		logger.info("Add new marketing spot from home page...");
		marketingSpotSummaryPg.clickAddNewSpotButton();
		marketingSpotDetailPg.waitLoading();
		
		marketingSpotDetailPg.setMarketingSpotDetail(src);
		marketingSpotDetailPg.clickSubmit();
		marketingSpotSummaryPg.waitLoading();
	}
}
