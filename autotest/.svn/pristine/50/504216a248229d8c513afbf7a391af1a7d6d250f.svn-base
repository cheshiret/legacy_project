package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.ServicesActivitiesEvents;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class AssignRemoveServicesActivities extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String[] servicAndActivIDs, servicAndActivNames = null;
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to remove services/activities must include at least one service/activity which is currently assigned.";
	String warningMessage_3 = "Your request to assign services/activities must include at least one service/activity which is currently not assigned.";
	String warningMessage_4 = "Your request to assign services/activities also contains services/activities which are currently assigned. These services/activities will be excluded. Do you want to continue with this change request item?";
	String warningMessage_5 = "Your request to remove services/activities also contains services/activities which are currently not assigned. These services/activities will be excluded. Do you want to continue with this change request item?";

	public void execute(){
		im.loginInventoryManager(login);

		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoServicePage();

		//Verify warning message with no selection
		//1. Assign
		im.switchChangeEffectiveMode();
		im.assignOrRemoveServicesActivities("Assign", false, "");
		im.verifyWarningMessage(warningMessage_1);
		//2. Remove
		im.assignOrRemoveServicesActivities("Remove", false, "");
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message and change request items details info when all selected Services Activities not assign
		//1. Remove
		im.assignOrRemoveServicesActivities("Remove", false, servicAndActivNames[0]);
		im.verifyWarningMessage(warningMessage_2);
		//2. Assign
		im.assignOrRemoveServicesActivities("Assign", false, servicAndActivNames[0]);
		this.setUpRequestItemDetailsInfo(0);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message and change request items details info when all selected Services Activities assign
		//1. Assign
		im.gotoServicePage();
		im.assignOrRemoveServicesActivities("Assign", false, servicAndActivNames[1]);
		im.verifyWarningMessage(warningMessage_3);
		//2. Remove      
		im.assignOrRemoveServicesActivities("Remove", false, servicAndActivNames[1]);
		im.verifyWarningMessage(warningMessage_3);
		this.setUpRequestItemDetailsInfo(1);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message and change request items details info when at least one selected Services Activities not active
		//1. Assign
		im.gotoServicePage();
		im.assignOrRemoveServicesActivities("Assign", false, servicAndActivNames[0],servicAndActivNames[1]);
		im.verifyWarningMessage(warningMessage_4);
		this.setUpRequestItemDetailsInfo(0);
		im.verifyRequestItemDetails(changeReqItems);
		//2. Remove
		im.gotoServicePage();
		im.assignOrRemoveServicesActivities("Remove", false, servicAndActivNames[0],servicAndActivNames[1]);
		im.verifyWarningMessage(warningMessage_5);
		this.setUpRequestItemDetailsInfo(1);
		im.verifyRequestItemDetails(changeReqItems);

		im.logoutInvManager();
	}
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";

		//Remove->Assign
		servicAndActivNames = new String[]{"Amphitheater","Antiquing"};
		servicAndActivIDs = new String[]{"4","1467"};

		changeReqItems.cRIRequestItemDetailsIDs = new ArrayList<String>();
		changeReqItems.cRIRequestItemDetailsNames = new ArrayList<String>();
	}

	public void setUpRequestItemDetailsInfo(int index){
		changeReqItems.cRIRequestItemDetailsIDs.clear();
		changeReqItems.cRIRequestItemDetailsNames.clear();

		changeReqItems.cRIRequestItemDetailsIDs.add(servicAndActivIDs[index]);
		changeReqItems.cRIRequestItemDetailsNames.add(servicAndActivNames[index]);
	}
}
