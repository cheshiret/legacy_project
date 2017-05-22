package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.campingunits;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_CampUnitsSites extends InventoryManagerTestCase{
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Camping Unit Sites";
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqID = "";
	
	public void execute(){   
		//Login inventory manager and add camping unit combo
		im.loginInventoryManager(login);
		//Delete all Draft change request items
		im.deleteDraftChangeRequestItems(requestType);
		
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoCampingUnitPg();
		
		//Assign site to units
		im.assignOrRemoveSitesAssociatedWithCampUnits("Assign", camp.comboID, camp.campUnitSitesIDs);

		//Remove site from combo in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		im.assignOrRemoveSitesAssociatedWithCampUnits("Remove", camp.comboID, camp.campUnitSitesIDs);
		changeReqItemsID_1 = im.makeChangeRequests(false, requestType);

		//Assign site to combo in  'Change Request Mode'     
		im.gotocampingUnitSitesPg(camp.comboID);
		im.switchChangeEffectiveMode();
		camp.campUnitSitesIDs = new String[]{"1609"};
		im.assignOrRemoveSitesAssociatedWithCampUnits("Assign", camp.comboID, camp.campUnitSitesIDs);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_2 = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];
		
		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		im.gotocampingUnitSitesPg(camp.comboID);
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";

		camp.comboID = "112";
		camp.campUnitSitesIDs = new String[]{"1608"};
		res.siteIDs=new String[]{"1608"};
	}
}
