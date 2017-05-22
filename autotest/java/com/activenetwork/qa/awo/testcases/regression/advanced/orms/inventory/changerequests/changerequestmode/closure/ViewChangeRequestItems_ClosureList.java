package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.closure;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class ViewChangeRequestItems_ClosureList extends InventoryManagerTestCase {
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Closures";
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqItemsID_3, changeReqID = "";
	Closure closureSearchFilter = new Closure();
	InvMgrClosuresPage inClosurePg = InvMgrClosuresPage.getInstance();

	public void execute(){   
		//Login inventory manager and add one closure
		im.loginInventoryManager(login);
		//Delete all Draft change request items
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.deleteDraftChangeRequestItemsForFacility(requestType);
		im.gotoClosurePage();
		
		//Deactivate closure in 'Change Immediate Mode'
		inClosurePg.searchClosure(closure.closureID);
		im.activeOrDeactiveClosure("Deactivate", closure.closureID);

		//Activate closure in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		im.activeOrDeactiveClosure("Activate", closure.closureID);
		changeReqItemsID_1 = im.makeChangeRequests(false, requestType);

		//Activate closure in 'Change Immediate Mode'
		im.gotoClosurePage();
		inClosurePg.searchClosure(closure.closureID);
		im.activeOrDeactiveClosure("Activate", closure.closureID);

		//Deactivate closure in 'Change Request Mode'     
		im.switchChangeEffectiveMode();
		im.activeOrDeactiveClosure("Deactivate", closure.closureID);
		changeReqItemsID_2 = im.makeChangeRequests(false, requestType);

		//Add closure in 'Change Request Mode'
		im.gotoClosurePage();
		im.switchChangeEffectiveMode();
		im.addClosure(closure);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_3 = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		changeReqItemsIDS.add(changeReqItemsID_3);
		im.gotoClosurePage();
		im.verifyChangeRequestItems(changeReqID,changeReqItemsIDS);

		//Active closure in 'Change Immediate Mode'
		im.gotoClosurePage();
		im.activeOrDeactiveClosure("Active", closure.closureID);
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		closure.closureID = "503";  //Active

		closure.type="General";
		closure.startDate=DateFunctions.getDateAfterToday(341);
		closure.endDate=DateFunctions.getDateAfterToday(342);
		closure.comment = "Test for change request enhancement";
		closure.notificationDate = DateFunctions.getDateAfterToday(0);
	}
}
