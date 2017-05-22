package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class VerifyWarning_RejectedChangeReqWithOneOrMoreClosedChangeReqItems extends InventoryManagerTestCase{
	ChangeRequestsInfo changeRequest = new ChangeRequestsInfo();
	FacilityData fd=new FacilityData();
	String requestTypes = "Facility Detail";
	
    public void execute(){
    	//Login inventory manager and make change requests
        im.loginInventoryManager(login);
        //Delete all Draft change request items with specific request type
        im.deleteDraftChangeRequestItems(requestTypes);
        //Make change request
        im.gotoFacilityDetailsPg(fd.facilityName);
    	im.makeMultiChangeRequest(fd, false,false);
    	String iDs = im.makeMultiChangeRequest(fd,true,true);
        changeRequest.cRID = iDs.split(" ")[0];
        changeRequest.changeReqItems.cRIID = iDs.split(" ")[1];
        
        //Make sure the status of one or more change request items is Closed 
        changeRequest.searchTypeIdOrValue = changeRequest.cRID;
        changeRequest.changeReqItems.cRIModifyStatus = "Approved";
        im.updateChangeRequestItems(changeRequest);
        changeRequest.changeReqItems.cRIModifyStatus = "Open";
        im.updateChangeRequestItems(changeRequest);
        changeRequest.changeReqItems.cRIModifyStatus = "Closed";
        im.updateChangeRequestItems(changeRequest);
        
        //Verify warning message in Change Request Detail page
        changeRequest.cRModifyStatus = "Rejected";
		changeRequest.cRWarningMessage  = "Change Request cannot be rejected since one or more Items have been closed.";
        im.updateChangeRequests(changeRequest);
        
        im.logoutInvManager();
      }
      
      public void wrapParameters(Object[] args) {
    	  login.url = AwoUtil.getOrmsURL(env);
	 	  login.contract = "SC Contract";
	 	  login.location="Administrator/South Carolina State Park Service";
	 	   
		  fd.facilityName="SANTEE";
		  fd.description = "Test for 'Change Requests'";
		  changeRequest.searchType = "Change Request ID";
      }
}
