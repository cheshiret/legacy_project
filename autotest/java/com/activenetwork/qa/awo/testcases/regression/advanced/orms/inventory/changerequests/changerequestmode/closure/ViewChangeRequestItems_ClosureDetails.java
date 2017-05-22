package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.closure;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class ViewChangeRequestItems_ClosureDetails extends InventoryManagerTestCase{
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Closure Detail";
	String ids, changeReqItemsID, changeReqID = "";

	public void execute(){   
		//Login inventory manager and add one closure
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		closure.closureID = im.addClosure(closure);

		//Update closure in 'Change Request Mode'
		im.gotoClosureDetaiPg(closure.closureID, false);
		im.switchChangeEffectiveMode();

		closure.startDate=DateFunctions.getDateAfterToday(343);
		closure.endDate=DateFunctions.getDateAfterToday(344);
		im.updateClosure(closure);

		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		im.gotoClosureDetaiPg(closure.closureID, false);
		changeReqItemsIDS.add(changeReqItemsID);
		im.verifyChangeRequestItems(changeReqID,changeReqItemsIDS);

		//Clear up
		im.gotoClosurePage();
		im.activeOrDeactiveClosure("Deactivate", closure.closureID);
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
	}
}
