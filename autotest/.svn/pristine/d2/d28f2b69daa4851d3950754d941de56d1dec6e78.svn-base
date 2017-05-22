package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.campingunits;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.campingUnits.InvMgrCampingUnitDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class EditExistCampingUnits extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String ids, changeReqItemsID, changeReqID = "";
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Camping Unit Detail";
	InvMgrCampingUnitDetailsPage imCampingUnitDetailsPg = InvMgrCampingUnitDetailsPage.getInstance();
	String warningMessage = "Your request does not include any changes. Please make the appropriate changes before making your request.";

	public void execute(){
		//Login inventory manager and add new season
		im.loginInventoryManager(login);
		//Delete all Draft change request items
		im.deleteDraftChangeRequestItems(requestType);
		
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoCampingUnitPg();

		//Verify warning message and request item details when doing nothing and click 'OK' button
		im.switchChangeEffectiveMode();
		im.updateExistCampingUnits(camp);
		im.verifyWarningMessage(warningMessage);

		//Verify request item details information
		camp.name = "TestForChangeReqEnhancement_Update camping units";
		changeReqItems.cRIReqItemDetailsRequestValue.add(camp.name);
		im.updateExistCampingUnits(camp);
		im.verifyRequestItemDetails(changeReqItems);
		im.switchChangeEffectiveMode();

		//Verify change request items id
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID);
		im.gotoCampUnitsDetailsPg(camp.comboID, false);
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";
		inventory.facilityName = "SANTEE";

		camp.name = "Standard Combo";
		camp.comboID = "111";

		changeReqItems.cRIReqItemDetailsFieldName.add("Name");
		changeReqItems.cRIRequestItemDetailsOriginalValue.add(camp.name);
		changeReqItems.cRIRequestItemDetailsCurrentValue.add(camp.name);
	}
}
