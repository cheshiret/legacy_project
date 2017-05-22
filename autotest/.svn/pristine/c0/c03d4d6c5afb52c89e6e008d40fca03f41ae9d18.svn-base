package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.ServicesActivitiesEvents;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EventInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest.InvMgrChangeReqItemDetail;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.serviceEvents.InvMgrEventDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class AddNewServiceActivityEvent extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	InvMgrEventDetailPage inEventDetailsPg = InvMgrEventDetailPage.getInstance();
	List <String> warningMessages = new ArrayList<String>();

	public void execute(){
		//Login inventory manager
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);

		//Go to Service, Activity and event detail page
		im.gotoServicesActivityEventDetailsPg("", true);
		im.switchChangeEffectiveMode();

		//Verify the button 'View Change Request Items'
		if(inEventDetailsPg.checkViewChangeRequestItems()){
			throw new ErrorOnDataException("The 'View Change Request Items' button should not exist.");
		}

		//Verify warning message and request item detail page
		this.verifyWarningMessageAndRequestItemDetail(warningMessages, event, changeReqItems);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";
		inventory.facilityName = "SANTEE";

		event.eventType ="General Event";
		event.eventName = "Change Request Enhancement";
		event.eventDescription = event.eventName;
		event.eventIcon = "Fishing";
		event.distanceCode = "Within Facility";
		event.publishDate = DateFunctions.getDateAfterToday(0);
		event.eventStart = event.publishDate;
		event.eventEnd = DateFunctions.getDateAfterToday(2); 

		changeReqItems.cRIReqItemDetailsFieldName.add("Start Date");
		changeReqItems.cRIReqItemDetailsFieldName.add("End Date");
		changeReqItems.cRIReqItemDetailsFieldName.add("Location");
		changeReqItems.cRIReqItemDetailsFieldName.add("Add to Conf Letter");
		changeReqItems.cRIReqItemDetailsFieldName.add("Order Notification");
		changeReqItems.cRIReqItemDetailsFieldName.add("Publish Date");
		changeReqItems.cRIReqItemDetailsFieldName.add("Distance Code");
		changeReqItems.cRIReqItemDetailsFieldName.add("Type");
		changeReqItems.cRIReqItemDetailsFieldName.add("Name");
		changeReqItems.cRIReqItemDetailsFieldName.add("Description");
		changeReqItems.cRIReqItemDetailsFieldName.add("Category");
		changeReqItems.cRIReqItemDetailsFieldName.add("Icon");

		changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(event.eventStart, "MM/dd/yyyy"));
		changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(event.eventEnd, "MM/dd/yyyy"));
		changeReqItems.cRIReqItemDetailsRequestValue.add(inventory.facilityName);
		changeReqItems.cRIReqItemDetailsRequestValue.add("Y");
		changeReqItems.cRIReqItemDetailsRequestValue.add("Y");
		changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(event.publishDate, "MM/dd/yyyy"));
		changeReqItems.cRIReqItemDetailsRequestValue.add(event.distanceCode);
		changeReqItems.cRIReqItemDetailsRequestValue.add(event.eventType);
		changeReqItems.cRIReqItemDetailsRequestValue.add(event.eventName);
		changeReqItems.cRIReqItemDetailsRequestValue.add(event.eventDescription);
		changeReqItems.cRIReqItemDetailsRequestValue.add("events");
		changeReqItems.cRIReqItemDetailsRequestValue.add(event.eventIcon);


		warningMessages.add("Event Name is required. Please enter a value in the field provided.");
		warningMessages.add("Event Description is required. Please enter a value in the field provided.");
		warningMessages.add("Publish Date must be a valid date and is required for an Event. Please enter the Publish Date using the format Mmm dd, yyyy or mm/dd/yyyy");
		warningMessages.add("Start Date and End Date must be valid dates and are required for an Event. Please enter the dates using the format Mmm dd, yyyy or mm/dd/yyyy");
	}

	public void verifyWarningMessageAndRequestItemDetail(List<String> warningMessages, EventInfo event, ChangeRequestsItemsInfo changeReqItems){
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail.getInstance();
		InvMgrEventDetailPage inEventDetailsPg = InvMgrEventDetailPage.getInstance();

		//Verify warning message in event details page
		inEventDetailsPg.clickOK();
		ajax.waitLoading();
		inEventDetailsPg.waitLoading();
		for(int i=0; i<warningMessages.size(); i++){
			if(!warningMessages.get(i).equals(inEventDetailsPg.getMoreThanOneWarningMessage().get(i))){
				throw new ErrorOnDataException("The error message"+"'" +warningMessages.get(i)+ "'"+"incorrect.");
			}
		}
		//Verify request item detail info
		inEventDetailsPg.setEventInfo(event);
		inEventDetailsPg.clickOK();
		inChangeReqItemDetailPg.waitLoading();
		im.verifyRequestItemDetails(changeReqItems);
	}
}
