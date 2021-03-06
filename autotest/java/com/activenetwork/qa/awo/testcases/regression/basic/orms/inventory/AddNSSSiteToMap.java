package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrMapViewPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddNSSSiteToMap extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AddNSSSiteToMap</b>
	 * Generated     : <b>Mar 2, 2010 8:59:17 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/02
	 * @author QA
	 */
     String siteID = "";
     public void execute() {
       im.loginInventoryManager(login);
       im.gotoFacilityDetailsPg(inventory.facilityName);
       im.goToSiteListPage();
       siteID = im.createSite(siteAttr);
       this.activeSite(siteID);
       
       im.addSiteToMap(siteAttr, true);
       this.vetifyAddSite();
       
       im.loginInventoryManager(login);
       im.gotoFacilityDetailsPg(inventory.facilityName);
       im.goToSiteListPage();
       im.gotoSiteDetailPage(siteID, false, false);
       this.deleteSite();
       
       im.logoutInvManager();
     }
     
     public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "NRRS Contract";
	   login.location="Administrator/NRRS";
	   
	   schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
	   inventory.facilityName = DataBaseFunctions.getFacilityName("73494", schema);
	   siteAttr.area = "auto test";
	   siteAttr.siteCode = DataBaseFunctions.getEmailSequence()+"";
	   siteAttr.siteName = "qa auto test 1239";
	   siteAttr.description = "qa auto test 1239";
//	   siteAttr.lookingForCategory = "RV Site,Trailer Site";
	   siteAttr.siteRelationType = "Non Site-Specific Child";
	   siteAttr.parentLoop = "WESA";
     }
     
     public void vetifyAddSite() {
       InvMgrMapViewPage invMapPg = InvMgrMapViewPage.getInstance();
       
       invMapPg.selectLoop(siteAttr.parentLoop);
       invMapPg.selectMappedSite(siteAttr.siteCode);
       invMapPg.clickRemoveNSS();
       invMapPg.clickSave();
       invMapPg.waitLoading();
       
       invMapPg.clickClose();
     }
     
     public void activeSite(String siteID) {
       InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
       
       invSitePg.searchSite("Site ID", siteID); 
	   
	   invSitePg.selectSiteCheckBoxBySiteID(siteID);
	   invSitePg.clickActivate();
	   invSitePg.waitLoading();
     }
     
     public void deleteSite() {
       InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage.getInstance();
       InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
       
       invSiteDetailPg.clickDeleteSite();
       invSiteDetailPg.waitLoading();
       invSiteDetailPg.clickOK();
       invSitePg.waitLoading();
     }
}

