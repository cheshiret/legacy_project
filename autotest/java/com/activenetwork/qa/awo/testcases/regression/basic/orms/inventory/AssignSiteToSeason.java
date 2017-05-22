package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrSeasonSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AssignSiteToSeason extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AssignSiteToSeason</b>
	 * Generated     : <b>Mar 4, 2010 1:21:19 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/04
	 * @author QA
	 */
     public String siteID="";
     private InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();
     private SeasonData seasonSearchFilter = new SeasonData();
  	 public void execute() {
  	   im.loginInventoryManager(login);
  	   
  	   im.gotoFacilityDetailsPg(inventory.facilityName);
     
       im.goToSiteListPage();
       siteID=im.createSite(siteAttr);
       im.activeSite(siteID);
       im.gotoSeasonsPg();
       seasonPg.searchSeason(seasonSearchFilter);
       im.assignSiteToSeason(season, siteAttr.siteName);
       this.verifyAssignToSeason(siteAttr.siteName);
       
       im.gotoSiteDetailPage(siteID, false, false);
       im.deleteSite(true);
       im.logoutInvManager();
  	 }
  	 
  	 public void wrapParameters(Object[] args) {
  	   login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "SC Contract";
	   login.location="Administrator/South Carolina State Park Service";
		 
	   inventory.facilityName = "SANTEE";
	   season.m_SeasonID = "108165101";
	   siteAttr.siteCode = DataBaseFunctions.getEmailSequence()+"";
	   siteAttr.siteName = "Qa auto test";
	   siteAttr.description = "qa auto test";
	   siteAttr.lookingForCategory = "RV Site";
	   
	   seasonSearchFilter.m_DateType = "StartDate";
	   seasonSearchFilter.m_FromDate = "Fri Jan 01 2010";
	   seasonSearchFilter.m_ToDate = "Sun Feb 28 2010";
  	 }
  	 
  	 public void verifyAssignToSeason(String siteNum) {
  	    InvMgrSeasonSitesPage seasonSitesPg=InvMgrSeasonSitesPage.getInstance();
  	    
  	    seasonSitesPg.searchSites(siteNum);
  	    seasonSitesPg.waitLoading();
  	    
  	    if(!seasonSitesPg.getAssignStatus().equals("Yes")){
  	      throw new ItemNotFoundException("Assigned site to sason failed");
  	    }
  	 }
}

