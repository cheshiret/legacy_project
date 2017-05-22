package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteOrAlertDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AlertNotesAndAlerts extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AlertNotesAndAlerts</b>
	 * Generated     : <b>Mar 1, 2010 10:08:14 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/01
	 * @author QA
	 */
      String newAlert="qa auto 1234";
      String noteOrAlertID = "";
	  public void execute(){
	    im.loginInventoryManager(login);
	    
	    im.gotoFacilityDetailsPg(inventory.facilityName);
	    noteOrAlertID = im.addNewAlertNote(inventory);
	    this.modifyAlert(newAlert);
	    inventory.description = newAlert;
	    im.deleteNotesOrAlerts(inventory, noteOrAlertID);
	    
	    im.logoutInvManager();
	  }
	  
	  public void wrapParameters(Object[] args) {
	     login.url = AwoUtil.getOrmsURL(env);
		 login.contract = "SC Contract";
		 login.location="Administrator/South Carolina State Park Service";
		 
		 inventory.facilityName = "SANTEE";
		 inventory.alertType = "Alert";
		 inventory.alertStartDate = DateFunctions.getToday();
		 inventory.alertEndDate = DateFunctions.getToday();
		 inventory.description = "qa auto test";
		 inventory.application = "Call Manager";
		 inventory.selectApplication = true;
		 inventory.appliesToIndex = 4;//add this for below keyword:invAlertListPg.getFirstAlertIDForEntrance()
	  }
	  
	  public void modifyAlert(String modifiedAlert){
	    InvMgrNoteAndAlertListPage invAlertListPg=InvMgrNoteAndAlertListPage.getInstance();
	    InvMgrNoteOrAlertDetailPage invAlertDetailsPg=InvMgrNoteOrAlertDetailPage.getInstance();
	    
	    invAlertListPg.selectType(inventory.alertType);
	    invAlertListPg.setStartDate(inventory.alertStartDate);
	    invAlertListPg.setEndDate(inventory.alertEndDate);
	    invAlertListPg.setAlertContent(inventory.description);
	    invAlertListPg.clickSearch();
	    invAlertListPg.waitLoading();
	    
	    invAlertListPg.clickAlertID(invAlertListPg.getFirstAlertIDForEntrance());
	    invAlertDetailsPg.waitLoading();
	    
	    invAlertDetailsPg.setDescription(modifiedAlert);
	    invAlertDetailsPg.clickApply();
	    invAlertDetailsPg.waitLoading();
	    
	    if(!invAlertDetailsPg.getErrorMessage().equalsIgnoreCase("Note/Alert modified successfully")&&!invAlertDetailsPg.getDescription().equals(modifiedAlert)){
	       throw new ItemNotFoundException("the alert modified failed");
	    }
	    
	    invAlertDetailsPg.clickOk();
	    invAlertListPg.waitLoading();
	    
	   // return invAlertListPg.getFirstAlertID();
	  }
}

