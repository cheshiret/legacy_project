package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.notesandalerts;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteOrAlertDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest.InvMgrChangeReqItemDetail;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class AddNewAlert extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	InvMgrNoteOrAlertDetailPage inNoteOrAlertDetailsPg = InvMgrNoteOrAlertDetailPage.getInstance();
	List <String> warningMessages = new ArrayList<String>();

	public void execute(){
		//Login inventory manager
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);

		//---For new alert
		im.gotoNoteOrAlertDetaiPg(null, true);
		im.switchChangeEffectiveMode();

		//Verify the button 'View Change Request Items'
		if(inNoteOrAlertDetailsPg.checkViewChangeRequestItems()){
			throw new ErrorOnDataException("The 'View Change Request Items' button should not exist.");
		}

		//Verify warning message and request item detail page
		this.verifyWarningMessageAndRequestItemDetail(warningMessages, changeReqItems);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";
		inventory.facilityName = "SANTEE";

		inventory.facilityName = "SANTEE";
		inventory.alertType = "Alert";
		inventory.alertStartDate = DateFunctions.getToday();
		inventory.alertEndDate = DateFunctions.getToday();
		inventory.description = "qa auto test";
		inventory.application = "Call Manager";
		inventory.selectApplication = true;

		inventory.appliesToIndex = 2;

		changeReqItems.cRIReqItemDetailsFieldName.add("Note/Alert Text");
		changeReqItems.cRIReqItemDetailsFieldName.add("Note/Alert Type");
		changeReqItems.cRIReqItemDetailsFieldName.add("Active");
		changeReqItems.cRIReqItemDetailsFieldName.add("Start Date");
		changeReqItems.cRIReqItemDetailsFieldName.add("End Date");
		changeReqItems.cRIReqItemDetailsFieldName.add("Delete");
		changeReqItems.cRIReqItemDetailsFieldName.add("Include in Confirmation Letter");
		changeReqItems.cRIReqItemDetailsFieldName.add("Applies To");
		changeReqItems.cRIReqItemDetailsFieldName.add("Ticket Category");
		changeReqItems.cRIReqItemDetailsFieldName.add("Include in Printed Permit");

		changeReqItems.cRIReqItemDetailsRequestValue.add(inventory.description);
		changeReqItems.cRIReqItemDetailsRequestValue.add(inventory.alertType);
		changeReqItems.cRIReqItemDetailsRequestValue.add("N");
		changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(inventory.alertStartDate, "MM/dd/yyyy"));
		changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(inventory.alertEndDate, "MM/dd/yyyy"));
		changeReqItems.cRIReqItemDetailsRequestValue.add("N");
		changeReqItems.cRIReqItemDetailsRequestValue.add("N");
		changeReqItems.cRIReqItemDetailsRequestValue.add("None");//Loops/Sites
		changeReqItems.cRIReqItemDetailsRequestValue.add("All");
		changeReqItems.cRIReqItemDetailsRequestValue.add("N");

		warningMessages.add("Please enter the Note/Alert type");
		warningMessages.add("Please enter the start date of the Note/Alert");
		warningMessages.add("Please enter the end date of the Note/Alert");
		warningMessages.add("Please enter the note/alert text");
		warningMessages.add("At least one Application must by selected");
//		warningMessages.add("Your request does not include any changes. Please make the appropriate changes before making your request.");
	    //This is original code in the case, updated by phoebe in 2012.12.13
	}

	public void verifyWarningMessageAndRequestItemDetail(List<String> warningMessages,ChangeRequestsItemsInfo changeReqItems){
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail.getInstance();
		List <String> warningMessagesUI = new ArrayList<String>();

		//Verify warning message in alert detail page
		inNoteOrAlertDetailsPg.addNewAlert(null);
		ajax.waitLoading();
		inNoteOrAlertDetailsPg.waitLoading();
		String warningMess = inNoteOrAlertDetailsPg.getErrorMessage();
		for(int i=0; i<warningMess.split("#").length-1; i++){
			warningMessagesUI.add(warningMess.split("#")[i]);
			if(!warningMessagesUI.get(i).startsWith(warningMessages.get(i))){
				throw new ErrorOnDataException("The warning message " +i+ "not correct. Expect:" + warningMessages.get(i) + "    Autual:" + warningMessagesUI.get(i));
			}
		}

		//Verify request item detail info
		inNoteOrAlertDetailsPg.addNewAlert(inventory);
		inChangeReqItemDetailPg.waitLoading();
		im.verifyRequestItemDetails(changeReqItems);
	}
}
