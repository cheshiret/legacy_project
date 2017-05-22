package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.campingunits;

import com.activenetwork.qa.awo.datacollection.legacy.CampingUnit;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.campingUnits.InvMgrCampingUnitDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest.InvMgrChangeReqItemDetail;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;

public class AddNewCampingUnits extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	InvMgrCampingUnitDetailsPage imCampingUnitDetailsPg = InvMgrCampingUnitDetailsPage.getInstance();
	String warningMessages = "";

	public void execute(){   
		//Login inventory manager and add camping unit combo
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);

		//Go to camping unit detail page 
		im.gotoCampingUnitPg();
		im.switchChangeEffectiveMode();
		im.gotoCampUnitsDetailsPg("", true);

		//Verify the button 'View Change Request Items'
		if(imCampingUnitDetailsPg.checkViewChangeRequestItems()){
			throw new ErrorOnDataException("The 'View Change Request Items' button should not exist.");
		}

		//Verify warning message and request item detail page
		this.verifyWarningMessageAndRequestItemDetail(warningMessages, camp, changeReqItems);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";

		camp.name = "TestForChangeReqEnhancement";

		changeReqItems.cRIReqItemDetailsFieldName.add("Name");
		changeReqItems.cRIReqItemDetailsFieldName.add("Applies To Whole Facility");
		changeReqItems.cRIReqItemDetailsFieldName.add("Equipment Required");
		changeReqItems.cRIReqItemDetailsFieldName.add("Max Camping Units - Max of All Combined");
		changeReqItems.cRIReqItemDetailsFieldName.add("Max Camping Units - Max of Special Combined");

		changeReqItems.cRIRequestItemDetailsOriginalValue.add("None"); // update from "" to "None" per defect-34502
		changeReqItems.cRIRequestItemDetailsOriginalValue.add("N");
		changeReqItems.cRIRequestItemDetailsOriginalValue.add("N");
		changeReqItems.cRIRequestItemDetailsOriginalValue.add("None");
		changeReqItems.cRIRequestItemDetailsOriginalValue.add("None");

		changeReqItems.cRIRequestItemDetailsCurrentValue.add("None");
		changeReqItems.cRIRequestItemDetailsCurrentValue.add("N");
		changeReqItems.cRIRequestItemDetailsCurrentValue.add("N");
		changeReqItems.cRIRequestItemDetailsCurrentValue.add("None");
		changeReqItems.cRIRequestItemDetailsCurrentValue.add("None");

		changeReqItems.cRIReqItemDetailsRequestValue.add(camp.name);
		changeReqItems.cRIReqItemDetailsRequestValue.add("N");
		changeReqItems.cRIReqItemDetailsRequestValue.add("N");
		changeReqItems.cRIReqItemDetailsRequestValue.add("0");
		changeReqItems.cRIReqItemDetailsRequestValue.add("0");

		warningMessages = "Name field is required and has not been entered or has been entered incorrectly.";
	}

	public void verifyWarningMessageAndRequestItemDetail(String warningMessage, CampingUnit camp, ChangeRequestsItemsInfo changeReqItems){
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail.getInstance();

		//Verify warning message when setting start date and click 'OK' button
		imCampingUnitDetailsPg.clickApply();
		imCampingUnitDetailsPg.waitLoading();
		im.verifyWarningMessage(warningMessage);

		//Verify request item detail info
		imCampingUnitDetailsPg.setCampingName(camp.name);
		imCampingUnitDetailsPg.clickOK();
		inChangeReqItemDetailPg.waitLoading();
		im.verifyRequestItemDetails(changeReqItems);
	}
}
