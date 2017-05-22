package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.ServicesActivitiesEvents;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.serviceEvents.InvMgrEventDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_ServiceActivityEventDetails extends InventoryManagerTestCase{
	InvMgrEventDetailPage eventDetailsPg = InvMgrEventDetailPage.getInstance();
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Service/Activity/Event Details";
	String ids, changeReqItemsID, changeReqID = "";

	public void execute(){   
		im.loginInventoryManager(login);
		//Delete all Draft change request items
		im.deleteDraftChangeRequestItems(requestType);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		//Get event info
		im.gotoEventsPg();
		event = im.getEventInformation(event);

		//Update Event details in 'Change Request Mode'
		im.gotoServicesActivityEventDetailsPg(event.eventID, false);
		im.switchChangeEffectiveMode();

		event.publishDate = "Thu Feb 16 2011";
		im.updateServiceActivityEvent(event);

		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		im.gotoServicesActivityEventDetailsPg(event.eventID, false);
		changeReqItemsIDS.add(changeReqItemsID);
		im.verifyChangeRequestItems(changeReqID,changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";
		inventory.facilityName = "SANTEE";

		event.eventName = "ChangeReqTest";
		event.eventDescription = event.eventName;
		event.publishDate = "Thu Feb 17 2011";
		event.eventStart = "Thu Feb 17 2011";
		event.eventEnd = "Thu Feb 17 2011";
	}
}
