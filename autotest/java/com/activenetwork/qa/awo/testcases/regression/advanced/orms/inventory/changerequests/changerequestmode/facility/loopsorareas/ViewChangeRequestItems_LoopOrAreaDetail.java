package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.loopsorareas;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_LoopOrAreaDetail extends InventoryManagerTestCase{
	String[] loopIDs;
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Loop Detail";
	String ids, changeReqItemsID, changeReqID = "";

	public void execute(){   
		//Login inventory manager and add new loopArea 
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoLoopAreaPage();

		//Update loop in 'Change Request Mode'
		im.gotoLoopAreaDetailsPage(loop.loopName, false);
		im.switchChangeEffectiveMode();
		im.updateLoopOrArea(loop);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID);
		im.gotoLoopAreaPage();
		im.gotoLoopAreaDetailsPage(loop.loopName, false);
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		loop.loopName = "Cypress Lakefront A";
		loop.parent = "Cabin Area";
	}
}
