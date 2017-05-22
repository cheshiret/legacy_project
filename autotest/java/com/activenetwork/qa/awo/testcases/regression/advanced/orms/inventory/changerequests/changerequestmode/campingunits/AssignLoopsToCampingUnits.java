package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.campingunits;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class AssignLoopsToCampingUnits extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to assign loops must include at least one loop which is currently not assigned.";
	String warningMessage_3 = "Your request to assign sites also contains sites which are currently assigned. These sites will be excluded. " +
	"Do you want to continue with this achange request item?";

	public void execute(){   
		//Login inventory manager and add camping unit combo
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoCampingUnitPg();

		//Verify warning message when no camping unit Loops selected
		im.switchChangeEffectiveMode();
		im.assignOrRemoveLoopsAssociatedWithCampUnits("Assign", camp.comboID, "");
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message when one or more loops selected and all selected loops assigned to camping units
		im.assignOrRemoveLoopsAssociatedWithCampUnits("Assign", camp.comboID, camp.campUnitLoopsIDs);
		im.verifyWarningMessage(warningMessage_2);

		//Verify warning message when one or more sites selected and all selected sites not assigned to camping units
		camp.campUnitLoopsNames = new String[]{"Cypress Lakefront A"};
		camp.campUnitLoopsIDs = new String[]{"10593"};
		im.assignOrRemoveLoopsAssociatedWithCampUnits("Assign", camp.comboID, camp.campUnitLoopsIDs);
		changeReqItems.cRIRequestItemDetailsIDs.add(camp.campUnitLoopsIDs[0]);
		changeReqItems.cRIRequestItemDetailsNames.add(camp.campUnitLoopsNames[0]);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message when one or more loops selected and at least one selected loops not assigned to camping unit
		im.gotocampingUnitLoopsPg(camp.comboID);
		camp.campUnitLoopsNames = new String[]{"Tent Loop", "Cypress Lakefront A"};
		camp.campUnitLoopsIDs = new String[]{"10617","10593"};
		im.assignOrRemoveLoopsAssociatedWithCampUnits("Assign", camp.comboID, camp.campUnitLoopsIDs);
		im.verifyWarningMessage(warningMessage_3);
		changeReqItems.cRIRequestItemDetailsIDs.add(camp.campUnitLoopsIDs[1]);
		changeReqItems.cRIRequestItemDetailsNames.add(camp.campUnitLoopsNames[1]);
		im.verifyRequestItemDetails(changeReqItems);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";

		camp.name = "Area/Loop Specific Combo";
		camp.comboID = "112";
		//Assign
		camp.campUnitLoopsNames = new String[]{"Tent Loop"};
		camp.campUnitLoopsIDs = new String[]{"10617"};
	}
}
