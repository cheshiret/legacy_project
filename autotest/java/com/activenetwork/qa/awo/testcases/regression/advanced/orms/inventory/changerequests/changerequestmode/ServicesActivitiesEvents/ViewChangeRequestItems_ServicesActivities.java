package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.ServicesActivitiesEvents;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_ServicesActivities extends InventoryManagerTestCase{
	String[] servicAndActivNames = null;
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Services/Activities";
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqID = "";

	public void execute(){   
		//Login inventory manager and add new loopArea 
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoServicePage();

		//Assign Services Activities in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		im.assignOrRemoveServicesActivities("Assign", true, servicAndActivNames[0]);
		changeReqItemsID_1 = im.makeChangeRequests(false, requestType);

		//Remove Services Activities in 'Change Request Mode'
		im.gotoServicePage();
		im.switchChangeEffectiveMode();
		im.assignOrRemoveServicesActivities("Remove", true, servicAndActivNames[1]);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_2 = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		im.gotoServicePage();
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";

		//Remove->Assign
		servicAndActivNames = new String[]{"Amphitheater","Antiquing"};
	}
}
