package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrMapViewPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddLoopToMap extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AddLoopToMap</b>
	 * Generated     : <b>Mar 3, 2010 1:10:59 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/03
	 * @author mchen
	 */
     public void execute() {
       im.loginInventoryManager(login);
       im.gotoFacilityDetailsPg(inventory.facilityName);
       im.addLoopToMap(siteAttr);
       this.vetifyAddNSSSite();
     }
     
     public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "SC Contract";
	   login.location = "Administrator/South Carolina State Park Service";
	   
	   inventory.facilityName = "SANTEE";
	   siteAttr.area = "automation test";
     }
     
     public void vetifyAddNSSSite() {
       InvMgrMapViewPage invMapPg = InvMgrMapViewPage.getInstance();
       
       invMapPg.selectMapped(siteAttr.area);
       invMapPg.clickRemove();
       invMapPg.clickSave();
       invMapPg.waitForPageLoad();
       
       invMapPg.clickClose();
     }
}

