package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.closure;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class ViewChangeRequestItems_ClosureSites extends InventoryManagerTestCase{
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Closure Sites";
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqID = "";
	InvMgrClosuresPage inClosurePg = InvMgrClosuresPage.getInstance();

	public void execute(){   
		//Login inventory manager and add one closure
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		closure.closureID = im.addClosure(closure);

		//Remove sites from closure in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		inClosurePg.searchClosure(closure.closureID);
		im.assignOrRemoveSiteAssociatedWithClosure("Remove", closure.closureID, closure.siteIds, closure.loop);
		changeReqItemsID_1 = im.makeChangeRequests(false, requestType);

		//Add new camping unit in 'Change Request Mode'
		im.gotoClosureSitesPg(closure.closureID);
		im.switchChangeEffectiveMode();
		closure.siteIds= new String[]{"4080"};  //SANT_CypressViewArea_002
		im.assignOrRemoveSiteAssociatedWithClosure("Assign", closure.closureID, closure.siteIds, closure.loop);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_2 = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		im.gotoClosureSitesPg(closure.closureID);
		im.verifyChangeRequestItems(changeReqID,changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";

		closure.type="General";
		closure.startDate=DateFunctions.getDateAfterToday(341);
		closure.endDate=DateFunctions.getDateAfterToday(342);
		closure.comment = "Test for change request enhancement";
		closure.notificationDate = DateFunctions.getDateAfterToday(0);

		//Info for assign specific sites to season
		closure.assignAll = false;
		closure.siteIds= new String[]{"4079"};  //SANT_CypressViewArea_001
		res.siteIDs=new String[]{"4079"};
		
		closure.loop = "Cypress View Area";
	}
}
