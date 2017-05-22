package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.facilitydetails;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class VerifyChangeReqForFacilitySupplementaryInfo extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	FacilityData fd = new FacilityData();
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Facility Supplementary Info";
	String ids, changeReqItemsID,  changeReqID = "";
	String warningMessage = "Your request does not include any changes. Please make the appropriate changes before making your request.";

	public void execute(){
		im.loginInventoryManager(login);
		im.gotoFacilitySupplementaryInfo(inventory.facilityName);

		//Verify warning message and change request item details information with nothing updated
		im.switchChangeEffectiveMode();
		im.updateFacilitySupplementaryInfo(fd);
		im.verifyWarningMessage(warningMessage);

		//Verify change request item details information updated successfully
		fd.voiceLine = "Yes";
		im.updateFacilitySupplementaryInfo(fd);
		changeReqItems.cRIRequestItemDetailsOriginalValue.add("");
		changeReqItems.cRIRequestItemDetailsCurrentValue.add("");
		changeReqItems.cRIReqItemDetailsFieldName.add("Voice Line");
		changeReqItems.cRIReqItemDetailsRequestValue.add("Y");
		im.verifyChangeRequestItemsDetails(changeReqItems);

		//Verify change request items ID
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		changeReqItemsIDS.add(changeReqItemsID);
		im.gotoFacilitySupplementaryInfo(inventory.facilityName);
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
	}
}
