package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.ServicesActivitiesEvents;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EventInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class RemoveEvnets extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMessage = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";

	public void execute(){
		im.loginInventoryManager(login);

		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoEventsPg();

		//Get event information
		event = im.getEventInformation(event);
		this.initializeChangeReqItemsInfo(event);
		
		//Verify warning message with no selection
		im.switchChangeEffectiveMode();
		im.removeEvents("");
		im.verifyWarningMessage(warningMessage);

		//Verify change request items details info when remove one or more events
		im.removeEvents(event.eventName);
		im.verifyRequestItemDetails(changeReqItems);

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
	
	public void initializeChangeReqItemsInfo(EventInfo event){
		changeReqItems.cRIRequestItemDetailsIDs.add(event.eventID);
		changeReqItems.cRIRequestItemDetailsNames.add(event.eventName);
	}
}
