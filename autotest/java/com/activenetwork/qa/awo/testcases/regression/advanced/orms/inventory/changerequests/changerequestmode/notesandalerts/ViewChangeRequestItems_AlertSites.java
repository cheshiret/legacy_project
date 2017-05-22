package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.notesandalerts;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_AlertSites extends InventoryManagerTestCase{
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Note/Alert Sites";
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqID = "";

	public void execute(){   
		//Login inventory manager and add new alert 
		im.loginInventoryManager(login);
		//Delete all Draft change request items
		im.deleteDraftChangeRequestItems(requestType);
		im.gotoFacilityDetailsPg(inventory.facilityName);

		im.gotoNotesAndAlertsPg();
		this.getAlertID();
		
		//Remove alert site in 'Change Immediate Mode'
		im.gotoNoteOrAlertSitesPg(inventory.alertID);
		im.assignOrRemoveSitesAssociatedWithAlertOrNote("Remove", inventory.alertID, inventory.siteIds);
		
		//Assign alert site in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		im.assignOrRemoveSitesAssociatedWithAlertOrNote("Assign", inventory.alertID, inventory.siteIds);
		changeReqItemsID_1 = im.makeChangeRequests(false, requestType);

		//Assign alert site in 'Change Immediate Mode'
		im.gotoNoteOrAlertSitesPg(inventory.alertID);
		im.assignOrRemoveSitesAssociatedWithAlertOrNote("Assign", inventory.alertID, inventory.siteIds);

		//Remove alert site in 'Change Request Mode' 
		im.switchChangeEffectiveMode();
		im.assignOrRemoveSitesAssociatedWithAlertOrNote("Remove", inventory.alertID, inventory.siteIds);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_2 = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];
		
		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		im.gotoNoteOrAlertSitesPg(inventory.alertID);
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
        //Deactive
		inventory.siteCodes = new String[]{"010"};
		inventory.siteIds= new String[]{"4088"};
		inventory.siteNames = new String[]{"SANT_CypressLakefrontA_010"}; // Sara[12112013] SANT_CypressLakefrontA_007
		inventory.loop = new String[]{"Cypress Lakefront A"};
		res.siteIDs=new String[]{"4088"};
	}
	
	private void getAlertID(){
		InvMgrNoteAndAlertListPage noteAndAlert = InvMgrNoteAndAlertListPage
		.getInstance();
		inventory.alertID = noteAndAlert.getAlertIDWithLoops();
	}
}
