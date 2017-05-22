package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.notesandalerts;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_AlertLoops extends InventoryManagerTestCase{
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Note/Alert Loops";
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqID = "";

	public void execute(){   
		//Login inventory manager and add new alert 
		im.loginInventoryManager(login);
		//Delete all Draft change request items
		im.deleteDraftChangeRequestItems(requestType);
		
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoNotesAndAlertsPg();
		this.getAlertID();

		im.gotoNoteOrAlertLoopsPg(inventory.alertID);
		//Remove alert loop in 'Change Immediate Mode'
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Remove", inventory.alertID, inventory.loopIDs);
		
		//Assign alert loop in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Assign", inventory.alertID, inventory.loopIDs);
		changeReqItemsID_1 = im.makeChangeRequests(false, requestType);

		//Assign alert loop in 'Change Immediate Mode'
		im.gotoNoteOrAlertLoopsPg(inventory.alertID);
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Assign", inventory.alertID, inventory.loopIDs);

		//Remove alert loop in 'Change Request Mode' 
		im.switchChangeEffectiveMode();
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Remove", inventory.alertID, inventory.loopIDs);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_2 = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		im.gotoNoteOrAlertLoopsPg(inventory.alertID);
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);
		
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		//Deactive
		inventory.loopIDs = new String[]{"10575"};
		inventory.loopNames = new String[]{"Cabin Area"};
	}
	
	private void getAlertID(){
		InvMgrNoteAndAlertListPage noteAndAlert = InvMgrNoteAndAlertListPage
		.getInstance();
		inventory.alertID = noteAndAlert.getAlertIDWithLoops();
	}
}
