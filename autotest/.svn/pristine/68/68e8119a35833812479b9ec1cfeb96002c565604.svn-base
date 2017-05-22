package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.facilitydetails;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class VerifyChangeReqForFacilityDetails extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	FacilityData fd = new FacilityData();
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Facility Detail";
	String ids, changeReqItemsID,  changeReqID = "";
	String warningMessage = "Your request does not include any changes. If you are requesting to change Facility Product Category, this is a restricted change where you must provide the details of the change on the following page. For all other changes, you must make the appropriate change before making the request. Do you want to continue with this change request item.";

	public void execute(){
		im.loginInventoryManager(login);
        //Delete all Draft change request items with specific request type
        im.deleteDraftChangeRequestItems(requestType);
		im.gotoFacilityDetailsPg(inventory.facilityName);

		//Verify warning message and change request item details information with nothing updated
		im.switchChangeEffectiveMode();
		im.updateFacilityDetails(fd, false);
		im.verifyWarningMessage(warningMessage);

		changeReqItems.cRIRequestItemDetails = "";
		im.verifyChangeRequestItemsDetails(changeReqItems);

		//Verify change request item details information updated successfully
		changeReqItems.cRIRequestItemDetailsOriginalValue.add(fd.facilityName);
		changeReqItems.cRIRequestItemDetailsCurrentValue.add(fd.facilityName);
		fd.facilityName = "Change Request enhancement for facility";
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.updateFacilityDetails(fd, true);

		changeReqItems.cRIReqItemDetailsFieldName.add(fd.facilityName);
		changeReqItems.cRIReqItemDetailsRequestValue.add(fd.facilityName);
		im.verifyChangeRequestItemsDetails(changeReqItems);

		//Verify change request items IDs
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		changeReqItemsIDS.add(changeReqItemsID);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		fd.facilityName = "SANTEE";
	}
}
