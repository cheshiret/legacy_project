package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.serviceEvents.InvMgrServiceActivitiesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AssignServiceToFacility extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AssignServiceToFacility</b>
	 * Generated     : <b>Mar 5, 2010 1:46:18 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/05
	 * @author QA
	 */
     String service="";
  	 public void execute() {
	  	 im.loginInventoryManager(login);
	     im.gotoFacilityDetailsPg(inventory.facilityName);
	     
	     im.gotoServicePage();
	     im.assignServiceToFacility(service);
	     this.verifyAssignResult();
	     
	     im.logoutInvManager();
  	 }
  	 
  	 public void wrapParameters(Object[] args) {
  	   login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "SC Contract";
	   login.location="Administrator/South Carolina State Park Service";
		 
	   inventory.facilityName = "SANTEE";
	   service = "Recreation Building";
  	 }
  	 
  	 public void verifyAssignResult() {
  	   InvMgrServiceActivitiesPage invServicePg = InvMgrServiceActivitiesPage.getInstance();
  	   ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
  	   
  	   if(!invServicePg.getServiceInfo().matches(".*Yes.*")){
  	      throw new ItemNotFoundException("Assign failed!");
  	   }
  	   
  	   invServicePg.selectAll();
  	   invServicePg.clickRemove();
  	   if(confirmDialogPg.exists()){
  		   confirmDialogPg.clickOK();
  	   }
  	   
  	   invServicePg.waitLoading();
  	 }
}

