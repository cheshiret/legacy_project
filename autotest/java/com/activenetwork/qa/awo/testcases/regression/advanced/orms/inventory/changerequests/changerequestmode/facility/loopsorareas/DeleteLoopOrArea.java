package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.loopsorareas;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class DeleteLoopOrArea extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String[] loopIDs;
	String alertID_1, alertID_2= "";
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to delete one or more loops is a restricted change. The selected loops will be copied to the following page where you can provide additional details. Do you want to continue with this change request item?";

	public void execute(){
		im.loginInventoryManager(login);

		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoLoopAreaPage();
		
		//Verify warning message with no selection
		im.switchChangeEffectiveMode();
		im.deleteLoopInLoopsOrAreas(null, false);
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message and change request items details info when one loop or area selected at least
		loopIDs = new String[]{loop.loopID};
		im.deleteLoopInLoopsOrAreas(loopIDs, false);
		im.verifyWarningMessage(warningMessage_2);
		changeReqItems.cRIRequestItemDetails = loop.loopID +" : "+ loop.loopName;
		im.verifyRequestItemDetails(changeReqItems);

		im.logoutInvManager();
	}
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		loop.loopName = "Cypress Lakefront A";
		loop.loopID = "10593";
	}
}
