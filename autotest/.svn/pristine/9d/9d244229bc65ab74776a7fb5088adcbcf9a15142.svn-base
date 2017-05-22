package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.campingunits;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.campingUnits.InvMgrCampingUnitPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class DeleteCampingUnits extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	  String requestTypes = "Camping Units";
	String warningMessage= "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	InvMgrCampingUnitPage inCampingUnitPg = InvMgrCampingUnitPage
			.getInstance();
	public void execute(){   
		//Login inventory manager and add camping unit combo
		im.loginInventoryManager(login);
        //Delete all Draft change request items
        im.deleteDraftChangeRequestItems(requestTypes);
        //Make change requests
		im.gotoFacilityDetailsPg(inventory.facilityName);
		camp.comboID = im.addCampingUnitCombo(camp);

		//Verify warning message when no camping units selected
		im.switchChangeEffectiveMode();
		im.deleteCampingUnits(camp.comboIDs);
		im.verifyWarningMessage(warningMessage);

		camp.comboIDs =new String[]{camp.comboID};
		//Verify change request item detail info
		inCampingUnitPg.turnToSiteExistPage(camp.comboIDs[0]);
		im.deleteCampingUnits(camp.comboIDs);
		changeReqItems.cRIRequestItemDetailsIDs.add(camp.comboID);
		changeReqItems.cRIRequestItemDetailsNames.add(camp.name);
		im.verifyRequestItemDetails(changeReqItems);
		im.switchChangeEffectiveMode();

		//Clear up
		im.gotoCampingUnitPg();
		inCampingUnitPg.turnToSiteExistPage(camp.comboIDs[0]);
		im.deleteCampingUnits(camp.comboIDs);
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";

		camp.name = "TestForChangeReqEnhancement";
		camp.boatIndex = 1;
	}
}
