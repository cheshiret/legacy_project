package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.closure;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest.InvMgrChangeReqItemDetail;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosureDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class AddNewClosure extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	InvMgrClosureDetailPage inClosureDetailPg = InvMgrClosureDetailPage.getInstance();
	List <String> warningMessages = new ArrayList<String>();
	
    public void execute(){
    	//Login inventory manager
        im.loginInventoryManager(login);
        im.gotoFacilityDetailsPg(inventory.facilityName);
        
        //Go to closure detail page
        im.switchChangeEffectiveMode();
        im.gotoSeasonsPg();
        im.gotoClosureDetaiPg("", true);
        
        //Verify the button 'View Change Request Items'
        if(inClosureDetailPg.checkViewChangeRequestItems()){
        	throw new ErrorOnDataException("The 'View Change Request Items' button should not exist.");
        }
        
        //Verify warning message and request item detail page
        this.verifyWarningMessageAndRequestItemDetail(warningMessages, closure, changeReqItems);
        
        im.logoutInvManager();
      }
      
      public void wrapParameters(Object[] args) {
    	  login.url = AwoUtil.getOrmsURL(env);
	 	  login.contract = "SC Contract";
	 	  login.location="Administrator/South Carolina State Park Service";
	 	  inventory.facilityName = "SANTEE";
	 	  
		  closure.type="General";
		  closure.startDate=DateFunctions.getDateAfterToday(341);
		  closure.endDate=DateFunctions.getDateAfterToday(342);
		  closure.status="active";
		  closure.comment = "Test for change request enhancement";
		  closure.notificationDate = DateFunctions.getDateAfterToday(0);
		  
		  changeReqItems.cRIReqItemDetailsFieldName.add("Comments");
		  changeReqItems.cRIReqItemDetailsFieldName.add("Active");
		  changeReqItems.cRIReqItemDetailsFieldName.add("Date Range - Start");
		  changeReqItems.cRIReqItemDetailsFieldName.add("Date Range - End");
		  changeReqItems.cRIReqItemDetailsFieldName.add("Closure Type");
		  changeReqItems.cRIReqItemDetailsFieldName.add("Occurrence Pattern");
		  changeReqItems.cRIReqItemDetailsFieldName.add("Notification Date");

          changeReqItems.cRIReqItemDetailsRequestValue.add(closure.comment);
          changeReqItems.cRIReqItemDetailsRequestValue.add("Y");
          changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(closure.startDate, "MM/dd/yyyy"));
          changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(closure.endDate, "MM/dd/yyyy"));
          changeReqItems.cRIReqItemDetailsRequestValue.add(closure.type);
          changeReqItems.cRIReqItemDetailsRequestValue.add("All");
          changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(closure.notificationDate, "MM/dd/yyyy"));
          
      	  warningMessages.add("End Date Invalid input");
      	  warningMessages.add("Start Date Invalid input");
      }
      
      public void verifyWarningMessageAndRequestItemDetail(List<String> warningMessages, Closure closure, ChangeRequestsItemsInfo changeReqItems){
    	  InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail.getInstance();
    	  ConfirmDialogPage confirmPage = ConfirmDialogPage.getInstance();
    	  
    	  //Verify warning message when setting start date and click 'OK' button
		  inClosureDetailPg.setStartDate(closure.startDate);
		  inClosureDetailPg.clickOK();
		  inClosureDetailPg.waitLoading();
          im.verifyWarningMessage(warningMessages.get(0));
          
    	  //Verify warning message when setting end date and click 'OK' button
		  inClosureDetailPg.setStartDate(" ");
		  confirmPage.waitLoading();
          inClosureDetailPg.setEndDate(closure.endDate);
          inClosureDetailPg.clickOK();
          inClosureDetailPg.waitLoading();
          im.verifyWarningMessage(warningMessages.get(1));
            
          //Verify request item detail info
    	  inClosureDetailPg.fillClosureDetails(closure);
          inClosureDetailPg.clickOK();
          inChangeReqItemDetailPg.waitLoading();
          im.verifyRequestItemDetails(changeReqItems);
      }
}
