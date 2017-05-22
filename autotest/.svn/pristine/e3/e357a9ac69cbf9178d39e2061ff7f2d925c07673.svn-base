package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.ServicesActivitiesEvents;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EventInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.serviceEvents.InvMgrEventDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class EditExistServiceActivityEvent extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	InvMgrEventDetailPage inEventDetailsPg = InvMgrEventDetailPage.getInstance();
	String warningMessage_1 = "Your request does not include any changes. Please make the appropriate changes before making your request.";
	String warningMessage_2 = "Event Name is required. Please enter a value in the field provided.";
	String warningMessage_3 = "Event Description is required. Please enter a value in the field provided.";
	String warningMessage_4 = "Publish Date must be a valid date and is required for an Event. Please enter the Publish Date using the format Mmm dd, yyyy or mm/dd/yyyy";
	String warningMessage_5 = "Start Date and End Date must be valid dates and are required for an Event. Please enter the dates using the format Mmm dd, yyyy or mm/dd/yyyy";

	public void execute(){
		//Login inventory manager and add new season
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoEventsPg();

		//Get event information
		event = im.getEventInformation(event);
		this.initializeChangeReqItemsInfo(event);
		im.gotoServicesActivityEventDetailsPg(event.eventID, false);

		//Verify warning message when doing nothing and click 'OK' button
		im.switchChangeEffectiveMode();
		im.updateServiceActivityEvent(null);
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message when set event name as " " and click 'Apply' button
		event.eventName = " ";
		im.updateServiceActivityEvent(event);
		im.verifyWarningMessage(warningMessage_2);

		//Verify warning message when set event description as" " and click 'Apply' button
		event.eventName = "Change Request Enhancement";
		event.eventDescription = " ";
		im.updateServiceActivityEvent(event);
		im.verifyWarningMessage(warningMessage_3);

		//Verify warning message when set event publish date as " " and click 'Apply' button
		event.eventDescription = event.eventName;
		event.publishDate = " ";
		im.updateServiceActivityEvent(event);
		im.verifyWarningMessage(warningMessage_4);

		//Verify warning message when set event publish date as " " and click 'Apply' button
		event.publishDate = DateFunctions.getDateAfterToday(0);
		event.eventStart = " ";
		event.eventEnd = " ";
		im.updateServiceActivityEvent(event);
		im.verifyWarningMessage(warningMessage_5);

		event.eventStart = DateFunctions.getDateAfterToday(1);
		event.eventEnd = DateFunctions.getDateAfterToday(3);
		im.updateServiceActivityEvent(event);

		changeReqItems.cRIReqItemDetailsFieldName.add("Start Date");
		changeReqItems.cRIReqItemDetailsFieldName.add("End Date");
		changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(event.eventStart, "MM/dd/yyyy"));
		changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(event.eventEnd, "MM/dd/yyyy"));
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
		changeReqItems.cRIRequestItemDetailsOriginalValue.add(DateFunctions.formatDate(event.eventStart, "MM/dd/yyyy"));
		changeReqItems.cRIRequestItemDetailsCurrentValue.add(DateFunctions.formatDate(event.eventStart, "MM/dd/yyyy"));
		changeReqItems.cRIRequestItemDetailsOriginalValue.add(DateFunctions.formatDate(event.eventEnd, "MM/dd/yyyy"));
		changeReqItems.cRIRequestItemDetailsCurrentValue.add(DateFunctions.formatDate(event.eventEnd, "MM/dd/yyyy"));
	}
}
