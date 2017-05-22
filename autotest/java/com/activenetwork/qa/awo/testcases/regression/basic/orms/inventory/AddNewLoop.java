package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddNewLoop extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AddNewLoop</b>
	 * Generated     : <b>Mar 3, 2010 3:14:13 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/03
	 * @author mchen
	 */
     public void execute(){
       im.loginInventoryManager(login);
       
       im.gotoFacilityDetailsPg(inventory.facilityName);
       im.gotoLoopAreaPage();
       im.addNewLoop(loop);
       im.deleteLoopInLoopsOrAreasDetails(loop.loopName, true);
       
       im.logoutInvManager();
     }
     
     public void wrapParameters(Object[] args) {
         login.url = AwoUtil.getOrmsURL(env);
		 login.contract = "SC Contract";
		 login.location="Administrator/South Carolina State Park Service";
		 
		 inventory.facilityName = "SANTEE";
		 
		 loop.loopName = "qa auto test";
		 loop.description = "qa auto test";
     }
}

