package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.closure;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class AssignSitesToClosure extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to assign sites must include at least one site which is currently not assigned.";
	String warningMessage_3 = "Your request to assign sites also contains sites which are currently assigned. These sites will be excluded. Do you want to continue with this change request item?";
	InvMgrClosuresSitesPage closureSitesPg = InvMgrClosuresSitesPage
			.getInstance();
	String siteCodeSearchFilter;
    public void execute(){
    	//Login inventory manager and add new closure
        im.loginInventoryManager(login);
        im.gotoFacilityDetailsPg(inventory.facilityName);
        String closureID = im.addClosure(closure);

        //Verify warning message when clicking Assign button with no site selected
        im.switchChangeEffectiveMode();
        im.assignOrRemoveSiteAssociatedWithClosure("Assign", closureID, null, closure.loop);
        im.verifyWarningMessage(warningMessage_1);
        
        //Verify warning message when one or more seasons selected and all selected site assign to season
        closureSitesPg.searchSites(siteCodeSearchFilter);
        im.assignOrRemoveSiteAssociatedWithClosure("Assign", closureID, closure.siteIds, closure.loop);
        im.verifyWarningMessage(warningMessage_2);
        
        //When one or more sites selected and all selected sites not assign to season
        closure.siteIds = new String[]{"1609"};
        closure.siteCodes = new String[]{"P-002"};
        closure.siteNames = new String[]{"SANT_Primitive_P-002"};
        im.assignOrRemoveSiteAssociatedWithClosure("Assign", closureID, closure.siteIds, closure.loop);
        //Verify Request Item Details info
        changeReqItems.cRIRequestItemDetailsIDs.add(closure.siteIds[0]);
        changeReqItems.cRIRequestItemDetailsCodes.add(closure.siteCodes[0]);
        changeReqItems.cRIRequestItemDetailsNames.add(closure.siteNames[0]);
        changeReqItems.cRIRequestItemDetailsLoops.add(closure.loop);
        im.verifyRequestItemDetails(changeReqItems);
        
        //When one or more seasons selected and at least one selected site not assigned to season
        im.gotoClosurePage();
        im.gotoClosureSitesPg(closureID);
        closureSitesPg.searchSites(siteCodeSearchFilter);
        closure.siteIds = new String[]{"1608", "1609"};
        closure.siteCodes = new String[]{"P-001", "P-002"};
        closure.siteNames = new String[]{"SANT_Primitive_P-001", "SANT_Primitive_P-002"};
        im.assignOrRemoveSiteAssociatedWithClosure("Assign", closureID, closure.siteIds, closure.loop);
        im.verifyWarningMessage(warningMessage_3);
        //Verify Request Item Details info
        changeReqItems.cRIRequestItemDetailsIDs.add(closure.siteIds[1]);
        changeReqItems.cRIRequestItemDetailsCodes.add(closure.siteCodes[1]);
        changeReqItems.cRIRequestItemDetailsNames.add(closure.siteNames[1]);
        changeReqItems.cRIRequestItemDetailsLoops.add(closure.loop);
        im.verifyRequestItemDetails(changeReqItems);
        
        im.logoutInvManager();
      }
      
      public void wrapParameters(Object[] args) {
    	  login.url = AwoUtil.getOrmsURL(env);
	 	  login.contract = "SC Contract";
	 	  login.location="Administrator/South Carolina State Park Service";
	 	   
		  inventory.facilityName = "SANTEE";
		  closure.type="Maintenance";
		  closure.loop = "Primitive";
		  closure.startDate=DateFunctions.getDateAfterToday(341);
		  closure.endDate=DateFunctions.getDateAfterToday(342);
		  
		  //Info for assign specific sites to season
		  closure.assignAll = false;
		  closure.siteCodes = new String[]{"P-001"};
		  closure.siteIds= new String[]{"1608"};
		  closure.siteNames = new String[]{"SANT_Primitive_P-001"};
		  res.siteIDs=new String[]{"1608"};
		  
		  siteCodeSearchFilter = "P-00%";
      }
}
