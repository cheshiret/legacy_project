package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.closure;

import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ActivateClosure extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningmString_2 = "Your request to activate closures must include at least one closure which is currently not active.";
	String warningMessage_3 = "Your request to activate the selected closures also contains closures which are currently active. These closures will be excluded. Do you want to continue with this change request item?";
	String closureID_1 = "503";  //Active
	String closureID_2 = "502"; //Deactive
	String closure2_Type;
	Closure closureSearchFilter = new Closure();
	InvMgrClosuresPage inClosurePg = InvMgrClosuresPage.getInstance();

	public void execute(){
		//Login inventory manager and go to closure page
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoClosurePage();
		//Deactive closure
		closure2_Type = im.getClosureType(closureID_2);
		im.activeOrDeactiveClosure("Deactivate", true, closureID_2);

		//Verify warning message when no closures selected
		im.switchChangeEffectiveMode();
		im.activeOrDeactiveClosure("Activate", "Null");
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message when one or more closures selected and all selected closures active
		inClosurePg.searchClosure("Start Date", closureSearchFilter);
		im.activeOrDeactiveClosure("Activate", closureID_1);
		im.verifyWarningMessage(warningmString_2);

		//Verify warning message when one or more closures selected and at least one selected closure not active
		inClosurePg.searchClosure("Start Date", closureSearchFilter);
		im.activeOrDeactiveClosure("Activate", false, closureID_1, closureID_2);
		im.verifyWarningMessage(warningMessage_3);
		//Verify Request Item Details 
		changeReqItems.cRIRequestItemDetailsIDs.add(closureID_2);
		changeReqItems.cRIRequestItemDetailsTypes.add(closure2_Type);
		im.verifyRequestItemDetails(changeReqItems);

		//When one or more closures selected AND all selected closures are deactive
		im.gotoClosurePage();
		inClosurePg.searchClosure("Start Date", closureSearchFilter);
		im.activeOrDeactiveClosure("Activate", closureID_2);
		//Verify Request Item Details 
		im.verifyRequestItemDetails(changeReqItems);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		
		closureSearchFilter.startDate = "09-01-2005";
		closureSearchFilter.endDate = "10-31-2005";
	}
}
