package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @LinkSetUp:  d_inv_add_site:id=120(SITENAME='Non Site-Specific Child')
 * @author QA
 */
public class CreateNewNSSSite extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>CreateNewNSSSite</b>
	 * Generated     : <b>Mar 3, 2010 3:58:33 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/03
	 * @author mchen
	 */
     public String siteID="";
     
     /**
      * This case need to prepare a NSS group in ACORN CAMP EAST (CA) park. Because there is not any NSS group. 
      */
  	 public void execute() {
  	   im.loginInventoryManager(login);
  	   
  	   im.gotoFacilityDetailsPg(inventory.facilityName);
  	   im.goToSiteListPage();
  	   
  	   this.checkAndDeleteExistingSite("Site Code", siteAttr.siteCode);
  	   siteID=im.createSite(siteAttr);
       im.gotoSiteDetailPage(siteID, false, false);
       this.verifySiteDetails();
     
       im.logoutInvManager();
  	 }
  	 
  	 /**
  	  * 
  	  * @param searchBy
  	  * @param searchValue
  	  */
  	 private void checkAndDeleteExistingSite(String searchBy, String searchValue) {
  		InvMgrSitesPage siteListPage = InvMgrSitesPage.getInstance();
  		InvMgrSiteDetailPage siteDetailPage = InvMgrSiteDetailPage.getInstance();
  		
  		siteListPage.searchSite(searchBy, searchValue);
  		String siteIDs[] = im.getSiteIDs(new String[]{searchValue}, siteListPage);
  		if(!StringUtil.isEmpty(siteIDs[0])) {
  			siteListPage.clickSiteID(siteIDs[0]);
  			siteDetailPage.clickDeleteThisSite();
  			im.deleteSite(false);
  			logger.info("Site - " + searchValue + " has been deleted successfully.");
  		} else logger.info("Site - " + searchValue + " doesn't exist.");
	}

	public void wrapParameters(Object[] args) {
	  	 login.url = AwoUtil.getOrmsURL(env);
		 login.contract = "NRRS Contract";
		 login.location="Administrator/NRRS";
		 
		 schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "NRRS";
		 inventory.facilityName = DataBaseFunctions.getFacilityName("73494", schema);
		 siteAttr.siteCode = "1234";
		 siteAttr.siteName = "Qa auto test";
		 siteAttr.description = "qa auto test";
//		 siteAttr.lookingForCategory = "RV Site";
		 siteAttr.siteRelationType = "Non Site-Specific Child";
  	 }
  	 
  	 public void verifySiteDetails() {
       InvMgrSiteDetailPage invSiteDetailPg=InvMgrSiteDetailPage.getInstance();
       InvMgrSitesPage invSitePg=InvMgrSitesPage.getInstance();
       
       if(!invSiteDetailPg.getSiteType().equals(siteAttr.siteRelationType) || !invSiteDetailPg.getSiteCode().equals(siteAttr.siteCode)||!invSiteDetailPg.getSiteName().equals(siteAttr.siteName)||!invSiteDetailPg.getDescription().equals(siteAttr.description)){
          throw new ItemNotFoundException("The new site info is incorrect!");
       }
       
       invSiteDetailPg.clickDeleteSite();
       invSiteDetailPg.waitLoading();
       invSiteDetailPg.clickOK();
       invSitePg.waitLoading();
   }
}

