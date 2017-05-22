package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.campingunits;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_CampUnitsloops extends InventoryManagerTestCase{
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Camping Unit Loops";
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqID = "";

	public void execute(){   
		//Login inventory manager and add camping unit combo
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoCampingUnitPg();

		//Remove loops from combo in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		im.assignOrRemoveLoopsAssociatedWithCampUnits("Remove", camp.comboID, camp.campUnitLoopsIDs[0]);
		changeReqItemsID_1 = im.makeChangeRequests(false, requestType);

		//Assign loops to combo in  'Change Request Mode'     
		im.gotocampingUnitLoopsPg(camp.comboID);
		im.switchChangeEffectiveMode();
		im.assignOrRemoveLoopsAssociatedWithCampUnits("Assign", camp.comboID, camp.campUnitLoopsIDs[1]);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_2 = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		im.gotocampingUnitLoopsPg(camp.comboID);
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		camp.comboID = "112";

		//Active-Deactive
		camp.campUnitLoopsIDs = new String[]{"10508", "10575"};
		camp.campUnitLoopsNames = new String[]{"Cypress View Area", "Cabin Area"};
	}
}
