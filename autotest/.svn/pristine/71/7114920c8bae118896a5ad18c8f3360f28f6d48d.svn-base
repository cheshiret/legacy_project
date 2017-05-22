package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.closure;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosureDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class EditExistClosure extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	InvMgrClosureDetailPage inClosureDetailPg = InvMgrClosureDetailPage.getInstance();
	String warningMessage_1 = "Your request does not include any changes. Please make the appropriate changes before making your request.";
	String warningMessage_2 = "Start Date Invalid input";
	String warningMessage_3 = "End Date Invalid input";
	
    public void execute(){
    	//Login inventory manager and add new season
        im.loginInventoryManager(login);
        im.gotoFacilityDetailsPg(inventory.facilityName);
        closure.closureID = im.addClosure(closure);
        
        //Verify warning message when doing nothing and click 'OK' button
        im.switchChangeEffectiveMode();
        im.updateExistClosure(closure);
        im.verifyWarningMessage(warningMessage_1);
        
        //Verify warning message when set start date " " and click 'OK' button
  	    closure.startDate = " ";
        im.updateExistClosure(closure);
        im.verifyWarningMessage(warningMessage_2);
        
        //Verify warning message when set end date " " and click 'OK' button
  	    closure.startDate = DateFunctions.getDateAfterToday(341);
  	    closure.endDate = " ";
        im.updateExistClosure(closure);
        im.verifyWarningMessage(warningMessage_3);
        
        //Verify request item details information when then change request invalidate
  	    closure.endDate = DateFunctions.getDateAfterToday(343);
        im.updateExistClosure(closure);
        
		changeReqItems.cRIReqItemDetailsFieldName.add("Date Range - End");
        changeReqItems.cRIRequestItemDetailsOriginalValue.add(DateFunctions.formatDate(closure.endDate, "MM/dd/yyyy"));
        changeReqItems.cRIRequestItemDetailsCurrentValue.add(DateFunctions.formatDate(closure.endDate, "MM/dd/yyyy"));
        changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(closure.endDate, "MM/dd/yyyy"));
        im.verifyRequestItemDetails(changeReqItems);
        im.switchChangeEffectiveMode();
        
        im.logoutInvManager();
      }
      
      public void wrapParameters(Object[] args) {
    	  login.url = AwoUtil.getOrmsURL(env);
	 	  login.contract = "SC Contract";
	 	  login.location="Administrator/South Carolina State Park Service";
	 	  inventory.facilityName = "SANTEE";
	 	  
		  closure.startDate=DateFunctions.getDateAfterToday(341);
		  closure.endDate=DateFunctions.getDateAfterToday(342);
      }
}
