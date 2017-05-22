package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.notesandalerts;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class ViewChangeRequestItems_NotesAndAlertsList extends InventoryManagerTestCase{
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Notes/Alerts";
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqItemsID_3, changeReqItemsID_4,changeReqID = "";

	public void execute(){   
		//Login inventory manager and add new alert 
		im.loginInventoryManager(login);
		//Delete all Draft change request items
		im.deleteDraftChangeRequestItems(requestType);
		
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoNotesAndAlertsPg();
		this.getAlertID();

		//Deactivate alert in 'Change Immediate Mode'
		im.activateOrDeactivateNoteOrAlert(inventory, "Deactivate", true, inventory.alertID);
		
		//Activate alert in 'Change Request Mode' 
		im.switchChangeEffectiveMode();
		im.activateOrDeactivateNoteOrAlert(inventory, "Activate", false, inventory.alertID);
		changeReqItemsID_1 = im.makeChangeRequests(false, requestType);

		//Activate alert in 'Change Immediate Mode'
		im.gotoNotesAndAlertsPg();
		im.activateOrDeactivateNoteOrAlert(inventory, "Activate", true, inventory.alertID);

		//Deactivate alert in 'Change Request Mode'		
		im.switchChangeEffectiveMode();
		im.activateOrDeactivateNoteOrAlert(inventory, "Deactivate", false, inventory.alertID);
		changeReqItemsID_2 = im.makeChangeRequests(false, requestType);

		//Delete alert in 'Change Request Mode'
		im.gotoNotesAndAlertsPg();
		im.switchChangeEffectiveMode();
		im.deleteNotesOrAlerts(inventory, inventory.alertID);
		changeReqItemsID_3 = im.makeChangeRequests(false, requestType);

		//Add alert in 'Change Request Mode'
		inventory.alertType = "Alert";
		inventory.alertStartDate = DateFunctions.getToday();
		inventory.alertEndDate = DateFunctions.getToday();
		inventory.description = "qa auto test";
		inventory.application = "Call Manager";
		inventory.selectApplication = true;
		
		im.switchChangeEffectiveMode();
		im.addNewAlertNote(inventory);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_4 = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		changeReqItemsIDS.add(changeReqItemsID_3);
		changeReqItemsIDS.add(changeReqItemsID_4);
		im.gotoNotesAndAlertsPg();
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
//		inventory.alertType="Alert";//Not sure if the Note/Alert type
		inventory.status = "All";
	}
	
	private void getAlertID(){
		InvMgrNoteAndAlertListPage noteAndAlert = InvMgrNoteAndAlertListPage
		.getInstance();
//		inventory.alertID = noteAndAlert.getAlertIDWithLoops();		
		inventory.alertID = noteAndAlert.getFirstAlertIDWithLoops();
		String loopInfo = noteAndAlert.getLoopsInfoByAlertID(inventory.alertID);

		if(Integer.parseInt(loopInfo)<1){
			im.assignOrRemoveLoopsAssociatedWithAlertOrNote("Assign", inventory.alertID, "10575");
			im.gotoFacilityDetailsPg(inventory.facilityName);
			im.gotoNotesAndAlertsPg();
		}		
	}
}
