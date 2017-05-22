package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.notesandalerts;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class ActivateDeactiveDeleteAlerts extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String alertID_1, alertID_2= "";
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to activate notes/alerts must include at least one note/alert which is currently not active.";
	String warningMessage_3 = "Your request to deactivate notes/alerts must include at least one note/alert which is currently active.";
	String warningMessage_4 = "Your request to activate the selected notes/alerts also contains notes/alerts which are currently active. These notes/alerts will be excluded. Do you want to continue with this change request item?";
	String warningMessage_5 = "Your request to deactivate the selected notes/alerts also contains notes/alerts which are currently not active. These notes/alerts will be excluded. Do you want to continue with this change request item?";

	public void execute(){
		im.loginInventoryManager(login);

		im.gotoFacilityDetailsPg(inventory.facilityName);
		alertID_1 = im.addNewAlertNote(inventory); //not active
		inventory.status = "Active";
		alertID_2 = im.addNewAlertNote(inventory); //active
		inventory.status = "All";

		//Verify warning message with no selection
		//1.Activate
		im.switchChangeEffectiveMode();
		im.activateOrDeactivateNoteOrAlert(inventory, "Activate", false, new String[]{});
		im.verifyWarningMessage(warningMessage_1);
		//2.DeActivate
		im.activateOrDeactivateNoteOrAlert(inventory, "Deactivate", false, new String[]{});
		im.verifyWarningMessage(warningMessage_1);
		//3.Delete
		im.deleteNotesOrAlerts(null, "");
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message and change request items details info when one or more notes/alerts selected and all selected notes/alerts active
		changeReqItems.cRIRequestItemDetailsIDs.add(alertID_2);
		//1.Activate
		im.activateOrDeactivateNoteOrAlert(inventory, "Activate", false, alertID_2);
		im.verifyWarningMessage(warningMessage_2);
		//2.Deactivate
		im.activateOrDeactivateNoteOrAlert(inventory,"Deactivate", false, alertID_2);
		im.verifyRequestItemDetails(changeReqItems);
		//3.Delete
		im.gotoNotesAndAlertsPg();
		im.deleteNotesOrAlerts(inventory, alertID_2);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message and change request items details info when one or more notes/alerts selected and all selected notes/alerts not active
		changeReqItems.cRIRequestItemDetailsIDs.clear();
		changeReqItems.cRIRequestItemDetailsIDs.add(alertID_1);
		//1.Activate
		im.gotoNotesAndAlertsPg();
		im.activateOrDeactivateNoteOrAlert(inventory,"Activate", false, alertID_1);
		im.verifyRequestItemDetails(changeReqItems);
		//2.Deactivate
		im.gotoNotesAndAlertsPg();
		im.activateOrDeactivateNoteOrAlert(inventory,"Deactivate", false, alertID_1);
		im.verifyWarningMessage(warningMessage_3);
		//3.Delete
		im.deleteNotesOrAlerts(inventory, alertID_1);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message and change request items details info when one or more notes/alerts selected and at least one selected note/alert not active;
		//1.Activate
		im.gotoNotesAndAlertsPg();
		im.activateOrDeactivateNoteOrAlert(inventory,"Activate", false, alertID_1, alertID_2);
		im.verifyWarningMessage(warningMessage_4);
		im.verifyRequestItemDetails(changeReqItems);
		//2.Deactivate
		im.gotoNotesAndAlertsPg();
		im.activateOrDeactivateNoteOrAlert(inventory,"Deactivate", false, alertID_1, alertID_2);
		im.verifyWarningMessage(warningMessage_5);
		changeReqItems.cRIRequestItemDetailsIDs.clear();
		changeReqItems.cRIRequestItemDetailsIDs.add(alertID_2);
		im.verifyRequestItemDetails(changeReqItems);
		//3.Delete
		changeReqItems.cRIRequestItemDetailsIDs.clear();
		changeReqItems.cRIRequestItemDetailsIDs.add(alertID_1);
		changeReqItems.cRIRequestItemDetailsIDs.add(alertID_2);
		changeReqItems.cRIRequestItemDetailsTypes.add(inventory.alertType);
		im.gotoNotesAndAlertsPg();
		im.deleteNotesOrAlerts(inventory, alertID_1, alertID_2);
		im.verifyRequestItemDetails(changeReqItems);

		//clear up
		im.gotoNotesAndAlertsPg();
		im.switchChangeEffectiveMode();
		im.deleteNotesOrAlerts(inventory, alertID_1, alertID_2);	

		im.logoutInvManager();
	}
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		inventory.alertType = "Alert";
		inventory.alertStartDate = DateFunctions.getToday();
		inventory.alertEndDate = DateFunctions.getToday();
		inventory.description = "qa auto test";
		inventory.status = "Deactive";
		inventory.application = "Call Manager";
		inventory.selectApplication = true;

		changeReqItems.cRIRequestItemDetailsIDs = new ArrayList<String>();
		changeReqItems.cRIRequestItemDetailsTypes = new ArrayList<String>();
		changeReqItems.cRIRequestItemDetailsTypes.add(inventory.alertType);
	}
}
