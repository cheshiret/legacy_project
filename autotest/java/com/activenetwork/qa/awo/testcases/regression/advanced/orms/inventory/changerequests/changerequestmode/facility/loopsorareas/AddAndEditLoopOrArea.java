package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.loopsorareas;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrLoopDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;

public class AddAndEditLoopOrArea extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	InvMgrLoopDetailsPage inLoopOrAreaDetailsPg = InvMgrLoopDetailsPage.getInstance();
	String[] loopIDs;
	String warningMessage_1 = "Loop/Area Name Invalid input";
	String warningMessage_2 = "Your request does not include any changes. Please make the appropriate changes before making your request.";

	public void execute(){
		//Login inventory manager
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);

		//---For new loop or area
		im.gotoAddNewLoopAreaDetailsPage();
		im.switchChangeEffectiveMode();

		//Verify the button 'View Change Request Items'
		if(inLoopOrAreaDetailsPg.checkViewChangeRequestItems()){
			throw new ErrorOnDataException("The 'View Change Request Items' button should not exist.");
		}

		//Verify warning message and request item detail page
		im.updateLoopOrArea(null);
		im.verifyWarningMessage(warningMessage_1);

		im.updateLoopOrArea(loop);
		im.verifyRequestItemDetails(changeReqItems);

		//---For existing loop or area
		im.gotoLoopAreaDetailsPage(loop.loopName, false);
		im.updateLoopOrArea(loop);
		im.verifyWarningMessage(warningMessage_2);

		//Verify request item details information when then change request invalidate
		changeReqItems.cRIRequestItemDetailsOriginalValue.add(loop.parent);
		changeReqItems.cRIRequestItemDetailsCurrentValue.add(loop.parent);
		loop.parent = "Primitive";
		im.updateLoopOrArea(loop);

		changeReqItems.cRIReqItemDetailsFieldName.clear();
		changeReqItems.cRIReqItemDetailsRequestValue.clear();
		changeReqItems.cRIReqItemDetailsFieldName.add("Parent");
		changeReqItems.cRIReqItemDetailsRequestValue.add(loop.parent);
		im.verifyRequestItemDetails(changeReqItems);

		im.logoutInvManager();
	}
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		loop.loopName = "Cabin Area";
		loop.parent = "SANTEE";

		changeReqItems.cRIReqItemDetailsFieldName.add("Loop/Area Name");
		changeReqItems.cRIReqItemDetailsFieldName.add("Parent");
		changeReqItems.cRIReqItemDetailsRequestValue.add(loop.loopName);
		changeReqItems.cRIReqItemDetailsRequestValue.add(loop.parent);
	}
}
