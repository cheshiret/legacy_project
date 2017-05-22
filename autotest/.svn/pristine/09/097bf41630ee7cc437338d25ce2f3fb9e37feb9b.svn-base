package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.notesandalerts;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class EditAlert extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMess = "Your request does not include any changes. Please make the appropriate changes before making your request.";

	public void execute(){
		//Login inventory manager
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);

		//---For editing existed alert
		inventory.alertID = im.addNewAlertNote(inventory);

		//Verify warning message when doing nothing and click 'OK' button
		im.switchChangeEffectiveMode();
		im.updateNoteOrAlert(inventory);
		im.verifyWarningMessage(warningMess);

		//Verify request item details information when then change request invalidate
		inventory.alertEndDate = DateFunctions.getDateAfterToday(1);
		im.updateNoteOrAlert(inventory);

		changeReqItems.cRIReqItemDetailsFieldName.add("End Date");
		changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(inventory.alertEndDate, "MM/dd/yyyy"));
		im.verifyRequestItemDetails(changeReqItems);

		//Clear up
		im.switchChangeEffectiveMode();
		im.gotoNotesAndAlertsPg();
		im.deleteNotesOrAlerts(inventory, inventory.alertID);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";
		inventory.facilityName = "SANTEE";

		inventory.facilityName = "SANTEE";
		inventory.alertType = "Alert";
		inventory.alertStartDate = DateFunctions.getToday();
		inventory.alertEndDate = DateFunctions.getToday();
		inventory.description = "qa auto test";
		inventory.application = "Call Manager";
		inventory.selectApplication = true;

		changeReqItems.cRIRequestItemDetailsOriginalValue.add(DateFunctions.formatDate(inventory.alertEndDate, "MM/dd/yyyy"));
		changeReqItems.cRIRequestItemDetailsCurrentValue.add(DateFunctions.formatDate(inventory.alertEndDate, "MM/dd/yyyy"));
	}
}
