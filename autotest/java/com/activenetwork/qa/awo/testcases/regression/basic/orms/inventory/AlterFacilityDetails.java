package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail.InvMgrFacilityDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AlterFacilityDetails extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AlterFacilityDetails</b>
	 * Generated     : <b>Mar 1, 2010 9:06:56 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/01
	 * @author QA
	 */
     String modifiedDetaile="qa auto test";
     public void execute() {
       im.loginInventoryManager(login);
       
       im.gotoFacilityDetailsPg(inventory.facilityName);
       this.modifyFacilityDetails(modifiedDetaile);
       
       im.logoutInvManager();
     }
     
     public void wrapParameters(Object[] args) {
         login.url = AwoUtil.getOrmsURL(env);
		 login.contract = "SC Contract";
		 login.location="Administrator/South Carolina State Park Service";
		 
		 inventory.facilityName = "SANTEE";
     }
     
     public void modifyFacilityDetails(String description){
       InvMgrFacilityDetailPage invDetails = InvMgrFacilityDetailPage.getInstance();
       InvMgrHomePage invHome = InvMgrHomePage.getInstance();
       
       String preDescription=invDetails.getDescription();
       invDetails.setDescription(description);
       invDetails.clickApply();
       invDetails.waitLoading();
       
       if(!invDetails.checkNoticeMessage("Operation succeeded")){
          throw new ItemNotFoundException("The notice should displayed");
       }
       if(!invDetails.getDescription().equals(description)){
         throw new ItemNotFoundException("The description could not be modified");
       }
       
       invDetails.setDescription(preDescription);
       invDetails.clickOK();
       
       invHome.waitLoading();
     }
}

