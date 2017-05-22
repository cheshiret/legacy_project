package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.campingunits;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class RemoveLoopsToCampingUnits extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to remove loops must include at least one loop which is currently assigned.";
	String warningMessage_3 = "Your request to remove sites also contains sites which are currently assigned. These sites will be excluded. " +
	"Do you want to continue with this achange request item?";

	public void execute(){   
		//Login inventory manager and add camping unit combo
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoCampingUnitPg();

		//Verify warning message when no camping unit Loops selected
		im.switchChangeEffectiveMode();
		im.assignOrRemoveLoopsAssociatedWithCampUnits("Remove", camp.comboID, "");
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message when one or more loops selected and all selected loops assigned to camping units
		im.assignOrRemoveLoopsAssociatedWithCampUnits("Remove", camp.comboID, camp.campUnitLoopsIDs[0]);
		this.initializeChangeItemDetailsInfo(0);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message when one or more sites selected and all selected sites not assigned to camping units
		im.gotocampingUnitLoopsPg(camp.comboID);
		im.assignOrRemoveLoopsAssociatedWithCampUnits("Remove", camp.comboID, camp.campUnitLoopsIDs[1]);
		this.initializeChangeItemDetailsInfo(1);
		im.verifyWarningMessage(warningMessage_2);

		//Verify warning message when one or more loops selected and at least one selected loops assigned to camping unit
		im.assignOrRemoveLoopsAssociatedWithCampUnits("Remove", camp.comboID, camp.campUnitLoopsIDs);
		im.verifyWarningMessage(warningMessage_3);
		this.initializeChangeItemDetailsInfo(0);
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

		//Active-Deactive
		camp.campUnitLoopsIDs = new String[]{"10508", "10575"};
		camp.campUnitLoopsNames = new String[]{"Cypress View Area", "Cabin Area"};
	}

	public void initializeChangeItemDetailsInfo(int index){
		changeReqItems.cRIRequestItemDetailsIDs.clear();
		changeReqItems.cRIRequestItemDetailsNames.clear();

		changeReqItems.cRIRequestItemDetailsIDs.add(camp.campUnitLoopsIDs[index]);
		changeReqItems.cRIRequestItemDetailsNames.add(camp.campUnitLoopsNames[index]);
	}
}
