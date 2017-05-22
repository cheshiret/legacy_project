package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrMapViewPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class VerifyChangeRequestForMap extends InventoryManagerTestCase {
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String[] eventNames = null;
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Maps";
	String ids, changeReqItemsID,  changeReqID = "";
	String warningMessage = "Your request does not include any changes. If you are requesting to add/remove Map Areas, or changes to Map Scales, this is a restricted change where you must provide the details of the change on the following page. For all other changes, you must make the appropriate change before making the request. Do you want to continue with this change request item?";

	public void execute() {
		im.loginInventoryManager(login);
		//Delete all Draft change request items
		im.deleteDraftChangeRequestItems(requestType);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		
		//Verify Read-only Field
		im.switchChangeEffectiveMode();
		im.gotoMapViewPg();
//		this.verifyReadOnlyAndDisableFields();

		//verify Request Item Details
		//1.Nothing to do
		im.addSiteToMap(siteAttr, false);
		im.verifyWarningMessage(warningMessage);
		changeReqItems.cRIRequestItemDetails = "";
		im.verifyRequestItemDetails(changeReqItems);

		//2.Add site to map 
		siteAttr.parentLoop = "All";
		siteAttr.siteCode = "007 : Cypress Lakefront A";
		im.addSiteToMap(siteAttr, true);

		changeReqItems.cRIReqItemDetailsFieldName.add("X");
		changeReqItems.cRIReqItemDetailsFieldName.add("Y");
		changeReqItems.cRIReqItemDetailsRequestValue.add("80.0");
		changeReqItems.cRIReqItemDetailsRequestValue.add("79.54");
		im.verifyRequestItemDetails(changeReqItems);

		//Verify change request items IDs
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		changeReqItemsIDS.add(changeReqItemsID);
		im.gotoMapViewPg();
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location = "Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
	}

	public void verifyReadOnlyAndDisableFields(){
		InvMgrMapViewPage invMapPg = InvMgrMapViewPage.getInstance();

		invMapPg.waitLoading();
		//Ready-only
		invMapPg.verifyReadOnlyValue("newMapArea", "true");

		invMapPg.verifyReadOnlyValue("iconSize", "true");
		invMapPg.verifyReadOnlyValue("fontSize", "true");
		invMapPg.verifyReadOnlyValue("loopWidth", "true");
		invMapPg.verifyReadOnlyValue("roadWidth", "true");

		//Disable
		invMapPg.verifyCheckObjectExist("link_removearea", "true");
		invMapPg.verifyCheckObjectExist("link_addarea", "true");
	}
}
