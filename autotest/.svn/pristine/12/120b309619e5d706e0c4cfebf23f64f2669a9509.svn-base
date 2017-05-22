package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.campingunits;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.campingUnits.InvMgrCampingUnitPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_CampUnitsList extends InventoryManagerTestCase{
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Camping Units";
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqID = "";
	InvMgrCampingUnitPage inCampingUnitPg = InvMgrCampingUnitPage
			.getInstance();
	public void execute(){   
		//Login inventory manager and add camping unit combo
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		camp.comboID = im.addCampingUnitCombo(camp);

		//Delete camping unit in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		camp.comboIDs = new String[]{camp.comboID};
		inCampingUnitPg.turnToSiteExistPage(camp.comboIDs[0]);
		im.deleteCampingUnits(camp.comboIDs);
		changeReqItemsID_1 = im.makeChangeRequests(false, requestType);

		//Add new camping unit in 'Change Request Mode' 
		im.gotoCampingUnitPg();
		im.gotoCampUnitsDetailsPg(camp.comboID, true);
		im.switchChangeEffectiveMode();
		im.addCampingUnitCombo(camp);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_2 = ids.split(" ")[1];
        changeReqID = ids.split(" ")[0];
        
		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		im.gotoCampingUnitPg();
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		//Clear up
		im.gotoCampingUnitPg();
		inCampingUnitPg.turnToSiteExistPage(camp.comboIDs[0]);
		im.deleteCampingUnits(camp.comboIDs);
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";

		camp.name = "TestForChangeReqEnhancement";
		camp.boatIndex = 1;
		camp.campUnitSitesNames = new String[]{"SANT_LakeShoreArea_104"};
		camp.campUnitSitesIDs = new String[]{"4180"};
		res.siteIDs=new String[]{"4180"};
	}
}
