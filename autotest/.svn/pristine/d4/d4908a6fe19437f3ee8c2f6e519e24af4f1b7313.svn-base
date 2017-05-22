package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddClosure extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AddClosure</b>
	 * Generated     : <b>Mar 4, 2010 2:45:40 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/04
	 * @author mchen
	 */
     String closureID="";
     
     public void execute(){
       im.loginInventoryManager(login);
       
       im.gotoFacilityDetailsPg(inventory.facilityName);
       im.gotoSeasonsPg();
       
       closureID=im.addClosure(closure);
       this.verifyAddClosure();
       
       im.logoutInvManager();
     }
     
     public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "SC Contract";
	   login.location="Administrator/South Carolina State Park Service";
		 
	   inventory.facilityName="SANTEE";
	   closure.startDate=DateFunctions.getToday();
	   closure.endDate=DateFunctions.getDateAfterToday(30);
	   closure.status="active";
	   closure.type="Maintenance";
     }
     
     public void verifyAddClosure(){
       InvMgrClosuresPage invClosurePg=InvMgrClosuresPage.getInstance();
       
       invClosurePg.searchClosure("Start Date", closure);
       invClosurePg.waitLoading();
       
       if(!invClosurePg.getClosureInfo().matches(".*"+closureID+".*")){
         throw new ItemNotFoundException("Closure added failed!");
       }
       
       invClosurePg.selectClosureCheckBoxByID(closureID);
       invClosurePg.clickDeactivate();
       
       invClosurePg.waitLoading();
     }
}

