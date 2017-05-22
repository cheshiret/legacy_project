package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.sites;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrNonSiteSpecGroupPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_NSSGroupsList extends InventoryManagerTestCase{
	InvMgrNonSiteSpecGroupPage imNonSiteSpecificGroupPg = InvMgrNonSiteSpecGroupPage
	.getInstance();
	String[] loopIDs, siteIDs, siteCodes, siteNames, loopNames;
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Non Site Specific Groups";
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqID = "";

	public void execute(){   
		//Login inventory manager and add new loopArea 
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoNonSiteSpecificGroupsPage();

		//Get site IDs and activate or deactivate specific site
		siteIDs = im.getSiteIDs(siteCodes, imNonSiteSpecificGroupPg);
		im.activateOrDeactivateNonSiteSpecificGroups("Activate", true, siteIDs[0]);
		im.activateOrDeactivateNonSiteSpecificGroups("Deactivate", true, siteIDs[1]);

		//Deactivate Non_Site_Specific Groups in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		im.activateOrDeactivateNonSiteSpecificGroups("Deactivate", true, siteIDs[0]);
		changeReqItemsID_1 = im.makeChangeRequests(false, requestType);

		//Activate Non_Site_Specific Groups in 'Change Request Mode'
		im.gotoNonSiteSpecificGroupsPage();
		im.switchChangeEffectiveMode();
		im.activateOrDeactivateNonSiteSpecificGroups("Activate", true, siteIDs[1]);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_2 = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		im.gotoNonSiteSpecificGroupsPage();
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";

		//Activate->Deactivate
		siteCodes = new String[]{"changeResEnhancement_1", "changeResEnhancement_2"};
		siteNames= new String[]{"changeResEnhancement_1", "changeResEnhancement_2"};
		loopNames = new String[]{"SANTEE", "SANTEE"};
	}
}
