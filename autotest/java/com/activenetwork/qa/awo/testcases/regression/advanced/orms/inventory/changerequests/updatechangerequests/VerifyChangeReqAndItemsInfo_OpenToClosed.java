package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.updatechangerequests;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/** Change Request Status has been set to 'Closed'
 * that Change Request Item Status change from 'Open' to 'Closed'
 */
public class VerifyChangeReqAndItemsInfo_OpenToClosed extends InventoryManagerTestCase{
	ChangeRequestsInfo changeRequest = new ChangeRequestsInfo();
	FacilityData fd=new FacilityData();

	public void execute(){
		//Login inventory manager and make change requests
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		String iDs = im.makeChangeRequests(fd,true);
		changeRequest.cRID = iDs.split(" ")[0];
		changeRequest.changeReqItems.cRIID = iDs.split(" ")[1];

		//verify change request items status
		changeRequest.cRModifyStatus = "Approved";
		im.updateChangeRequests(changeRequest);
		changeRequest.cRModifyStatus = "Open";
		im.updateChangeRequests(changeRequest);
		changeRequest.changeReqItems.cRIStatus = "Open";
		im.gotoChangeRequestItemsPg(changeRequest.cRID);
		im.verifyChangeRequestItemsStatus(changeRequest.changeReqItems.cRIStatus);

		//verify change request information
		changeRequest.cRModifyStatus = "Closed";
		im.updateChangeRequests(changeRequest);
		changeRequest.changeReqItems.cRIStatus = "Closed";
		im.gotoChangeRequestDetailPg(changeRequest.cRID);
		im.verifyChangeRequestDetails(changeRequest);

		//verify change request items information
		changeRequest.changeReqItems.cRIStatus = "Closed";
		im.gotoChangeRequestItemsPg(changeRequest.cRID);
		im.verifyChangeRequestItemsStatus(changeRequest.changeReqItems.cRIStatus);
		im.gotoChangeRequestItemsDetailsPg(changeRequest.changeReqItems.cRIID);
		im.verifyChangeRequestItemsDetails(changeRequest.changeReqItems);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName="SANTEE";
		fd.description = "Test for 'Change Requests'";

		changeRequest.searchType = "Change Request ID";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "SC";
		changeRequest.cRRejectedDate = DateFunctions.getToday("MMM dd, yyyy z", DataBaseFunctions.getContractTimeZone(schema));
		changeRequest.cRClosedUser= "Test-Auto, QA-Auto";
		changeRequest.cRClosedLocation = "South Carolina State Park Service";

		String date = DateFunctions.formatDateToShort(DateFunctions.getDateAfterToday(0));
		changeRequest.changeReqItems.cRIClosedDate = date.split(",")[0] + date.split(",")[1];
		changeRequest.changeReqItems.cRIClosedUser = "Test-Auto";
		changeRequest.changeReqItems.cRIClosedLocation = "South Carolina State Park Service";
	}
}
