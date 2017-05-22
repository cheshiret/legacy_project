package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.notesandalerts;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteOrAlertDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class ViewChangeRequestItems_AlertDetail extends InventoryManagerTestCase{
	InvMgrNoteOrAlertDetailPage invAlertDetailsPg = InvMgrNoteOrAlertDetailPage.getInstance();
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Note/Alert Detail";
	String ids, changeReqItemsID, changeReqID = "";

	public void execute(){   
		//Login inventory manager and add new alert 
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		inventory.alertID = im.addNewAlertNote(inventory);

		//Update alert in 'Change Request Mode'
		im.gotoNoteOrAlertDetaiPg(inventory, false);
		im.switchChangeEffectiveMode();

		inventory.alertEndDate = DateFunctions.getDateAfterToday(1);
		im.updateNoteOrAlert(inventory);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID);
		im.gotoNoteOrAlertDetaiPg(inventory, false);
		im.verifyChangeRequestItems(changeReqID,changeReqItemsIDS);

		//Clear up
		im.gotoNotesAndAlertsPg();
		im.deleteNotesOrAlerts(inventory, inventory.alertID);
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
		inventory.application = "Call Manager";
		inventory.selectApplication = true;
	}
}
