package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.loopsorareas;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_LoopAndAreaList extends InventoryManagerTestCase{
	String[] loopIDs;
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Loops";
	String ids, changeReqItemsID, changeReqID = "";

	public void execute(){   
		//Login inventory manager and add new loopArea 
		im.loginInventoryManager(login);
		//Delete all Draft change request items
		im.deleteDraftChangeRequestItems(requestType);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoLoopAreaPage();

		//Delete loopArea in 'Change Request Mode'
		loopIDs= new String[]{loop.loopID};
		im.switchChangeEffectiveMode();
		im.deleteLoopInLoopsOrAreas(loopIDs, true);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID);
		im.gotoLoopAreaPage();
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		loop.loopID = "10593";
	}
}
