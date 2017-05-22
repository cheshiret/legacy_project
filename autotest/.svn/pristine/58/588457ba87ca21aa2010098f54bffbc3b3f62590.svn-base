package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class VerifyValidStatusInChangeReqDetailsPg extends InventoryManagerTestCase{
	ChangeRequestsInfo changeRequest = new ChangeRequestsInfo();
	FacilityData fd=new FacilityData();
	List<String> exceptStatus = new ArrayList<String>();
	  String requestTypes = "Facility Detail";
	
    public void execute(){
    	//Login inventory manager
        im.loginInventoryManager(login);
        //Delete all Draft change request items
        im.deleteDraftChangeRequestItems(requestTypes);
        //Make change requests
        im.gotoFacilityDetailsPg(inventory.facilityName);
        changeRequest.cRID = im.makeChangeRequests(fd,true).split(" ")[0];
        
        //Change Request Status--->List of Valid Value: Submitted--->Approved,Rejected
  	    exceptStatus.add("Submitted");
 	    exceptStatus.add("Approved");
 	    exceptStatus.add("Rejected");
        im.gotoChangeRequestDetailPg(changeRequest.cRID);
        im.verifyValidStatusRelativeChangeRequestStatus(exceptStatus.get(0), exceptStatus);
        
        //Change Request Status--->List of Valid Value: Approved--->Open,Rejected
        im.updateChangeRequestStatus("Approved");
        exceptStatus.clear();
  	    exceptStatus.add("Approved");
 	    exceptStatus.add("Open");
 	    exceptStatus.add("Rejected");
        im.verifyValidStatusRelativeChangeRequestStatus(exceptStatus.get(0), exceptStatus);
        
        //Change Request Status--->List of Valid Value: Open--->Closed,Rejected
        im.updateChangeRequestStatus("Open");
        exceptStatus.clear();
  	    exceptStatus.add("Open");
 	    exceptStatus.add("Closed");
 	    exceptStatus.add("Rejected");
        im.verifyValidStatusRelativeChangeRequestStatus(exceptStatus.get(0), exceptStatus);
        
        //Change Request Status--->List of Valid Value: Closed--->N/A
        im.updateChangeRequestStatus("Closed");
        exceptStatus.clear();
  	    exceptStatus.add("Closed");
        im.verifyValidStatusRelativeChangeRequestStatus(exceptStatus.get(0), exceptStatus);
        
        //Change Request Status--->List of Valid Value: Rejected--->N/A
        gotoInventoryHomePg();
        changeRequest.cRStatus = "Closed";
        im.gotoFacilityDetailsPg(inventory.facilityName);
        changeRequest.cRID = im.makeChangeRequests(fd,true).split(" ")[0];
        
        im.gotoChangeRequestDetailPg(changeRequest.cRID);
        
        im.updateChangeRequestStatus("Rejected");
        exceptStatus.clear();
  	    exceptStatus.add("Rejected");
        im.verifyValidStatusRelativeChangeRequestStatus(exceptStatus.get(0), exceptStatus);
        
        im.logoutInvManager();
      }
      
      public void wrapParameters(Object[] args) {
    	  login.url = AwoUtil.getOrmsURL(env);
	 	  login.contract = "SC Contract";
	 	  login.location="Administrator/South Carolina State Park Service";
	 	   
		  inventory.facilityName="SANTEE";
		  fd.description = "Test for 'Change Requests'";
      }
      
      public void gotoInventoryHomePg(){
    	  InvMgrTopMenuPage inTopMenuPg = InvMgrTopMenuPage.getInstance();
    	  InvMgrHomePage inHomePg = InvMgrHomePage.getInstance();
          inTopMenuPg.clickHome();
          inHomePg.waitLoading();
      }
}
