package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.notesandalerts;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class AssignRemoveAlertLoops extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to assign sites must include at least one site which is currently not assigned.";
	String warningMessage_3 = "Your request to remove sites must include at least one site which is currently assigned.";
	String warningMessage_4 = "Your request to assign loops also contains loops which are currently assigned. These loops will be excluded. Do you want to continue with this change request item?";
	String warningMessage_5 = "Your request to remove loops also contains loops which are currently not assigned. These loops will be excluded. Do you want to continue with this change request item?";

	public void execute(){
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoNotesAndAlertsPg();
		this.getNoteAlertID();

		//Assign and Remove loop
		im.gotoNoteOrAlertLoopsPg(inventory.alertID);
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Assign", inventory.alertID, inventory.loopIDs[0]);
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Remove", inventory.alertID, inventory.loopIDs[1]);

		//Verify warning message with no selection
		//1.Assign
		im.switchChangeEffectiveMode();
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Assign", inventory.alertID, new String[]{});
		im.verifyWarningMessage(warningMessage_1);
		//2.Remove
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Remove", inventory.alertID, new String[]{});
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message and change request items details info when one or more loops selected and all selected sites assigned to note/alert
		//1.Assign
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Assign", inventory.alertID, inventory.loopIDs[0]);
		im.verifyWarningMessage(warningMessage_2);
		//2.Remove
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Remove", inventory.alertID, inventory.loopIDs[0]);
		this.initializeChangeItemDetailsInfo(0);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message and change request items details info when one or more loops selected and all selected sites not assigned to note/alert
		//1.Remove
		im.gotoNoteOrAlertLoopsPg(inventory.alertID);
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Remove", inventory.alertID, inventory.loopIDs[1]);
		im.verifyWarningMessage(warningMessage_3);
		//2.Assign
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Assign", inventory.alertID, inventory.loopIDs[1]);
		this.initializeChangeItemDetailsInfo(1);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message and change request items details info when one or more loops selected and at least one selected site not assigned to note/alert
		//1.Assign
		im.gotoNoteOrAlertLoopsPg(inventory.alertID);
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Assign", inventory.alertID, inventory.loopIDs);
		im.verifyWarningMessage(warningMessage_4);
		this.initializeChangeItemDetailsInfo(1);
		im.verifyRequestItemDetails(changeReqItems);
		//2.Remove
		im.gotoNotesAndAlertsPg();
		im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Remove", inventory.alertID, inventory.loopIDs);
		im.verifyWarningMessage(warningMessage_5);
		this.initializeChangeItemDetailsInfo(0);
		im.verifyRequestItemDetails(changeReqItems);

		im.logoutInvManager();
	}
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";
		inventory.facilityName = "SANTEE";

		//Active-Deactive
		inventory.loopIDs = new String[]{"10617", "10593"};
		inventory.loopNames = new String[]{"Tent Loop", "Cypress Lakefront A"};
	}

	public void initializeChangeItemDetailsInfo(int index){
		changeReqItems.cRIRequestItemDetailsIDs.clear();
		changeReqItems.cRIRequestItemDetailsNames.clear();

		changeReqItems.cRIRequestItemDetailsIDs.add(inventory.loopIDs[index]);
		changeReqItems.cRIRequestItemDetailsNames.add(inventory.loopNames[index]);
	}

	private void getNoteAlertID(){
		InvMgrNoteAndAlertListPage noteAndAlert = InvMgrNoteAndAlertListPage
		.getInstance();
		inventory.alertID = noteAndAlert.getAlertIDWithLoops();
	}
}
