package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrMapViewPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddServiceToMap extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AddServiceToMap</b>
	 * Generated     : <b>Mar 5, 2010 2:24:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/05
	 * @author QA
	 */
     String service = "";
     public void execute() {
       im.loginInventoryManager(login);
       im.gotoFacilityDetailsPg(inventory.facilityName);
       im.addServiceToMap(service);
       
       this.removeServiceMap();
     }
     
     public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "SC Contract";
	   login.location="Administrator/South Carolina State Park Service";
	   
	   inventory.facilityName = "SANTEE";
	   service = "Walking";
     }
     
     public void removeServiceMap() {
       InvMgrMapViewPage invMapPg = InvMgrMapViewPage.getInstance();
       
       invMapPg.selectAmenity();
       invMapPg.selectMappedService(service);
       invMapPg.removeService();
       invMapPg.clickSave();
       
       invMapPg.waitLoading();
       invMapPg.clickClose();
     }
}

