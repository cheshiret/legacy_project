package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.ServicesActivitiesEvents;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_Events extends InventoryManagerTestCase{
	String[] eventNames = null;
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Events";
	String ids, changeReqItemsID,  changeReqID = "";

	public void execute(){   
		//Login inventory manager and add new loopArea 
		im.loginInventoryManager(login);
		//Delete all Draft change request items
		im.deleteDraftChangeRequestItems(requestType);
		//Go to event page
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoEventsPg();
		//Get event info
		event = im.getEventInformation(event);

		//Remove Event in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		im.removeEvents(event.eventName);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID);
		im.gotoEventsPg();
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

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
