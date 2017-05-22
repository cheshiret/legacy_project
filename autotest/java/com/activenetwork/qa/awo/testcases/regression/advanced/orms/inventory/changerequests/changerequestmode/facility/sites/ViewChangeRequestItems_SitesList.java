package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.sites;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ViewChangeRequestItems_SitesList extends InventoryManagerTestCase{
	InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
	String[] siteCodes, siteIDs;
	List<String> changeReqItemsIDS = new ArrayList<String>();
	String requestType = "Sites";
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqItemsID_3, changeReqItemsID_4, changeReqItemsID_5,changeReqID = "";

	public void execute(){   
		//Login inventory manager and add new loopArea 
		im.loginInventoryManager(login);
		//Delete all Draft change request items
		im.deleteDraftChangeRequestItems(requestType);
		
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.goToSiteListPage();
		
		//Get siteIDs and activate and deactivate sites
		siteIDs = im.getSiteIDs(siteCodes, invSitePg);
		im.activateOrDeactivateSites("Activate", true, siteIDs[0],siteIDs[2]);
		im.activateOrDeactivateSites("Deactivate", true, siteIDs[1],siteIDs[3]);
		
		//Activate site in 'Change Request Mode'
		//Site Specific site
		im.switchChangeEffectiveMode();
		im.activateOrDeactivateSites("Activate", true, siteIDs[1]);
		changeReqItemsID_1 = im.makeChangeRequests(false, requestType);
		//Non Site-Specific Child site
		im.goToSiteListPage();
		im.switchChangeEffectiveMode();
		im.activateOrDeactivateSites("Activate", true, siteIDs[3]);
		changeReqItemsID_2 = im.makeChangeRequests(false, requestType);
		//Deactivate site in 'Change Request Mode'
		//Site Specific site
		im.goToSiteListPage();
		im.switchChangeEffectiveMode();
		im.activateOrDeactivateSites("Deactivate", true, siteIDs[0]);
		changeReqItemsID_3 = im.makeChangeRequests(false, requestType);
		//Non Site-Specific Child site
		im.goToSiteListPage();
		im.switchChangeEffectiveMode();
		im.activateOrDeactivateSites("Deactivate", true, siteIDs[2]);
		changeReqItemsID_4 = im.makeChangeRequests(false, requestType);
		//
		//Add New site in 'Change Request Mode'
		im.goToSiteListPage();
		im.switchChangeEffectiveMode();
		im.createSite(siteAttr);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_5 = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];
		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		changeReqItemsIDS.add(changeReqItemsID_3);
		changeReqItemsIDS.add(changeReqItemsID_4);
		changeReqItemsIDS.add(changeReqItemsID_5);
		im.goToSiteListPage();
		im.verifyChangeRequestItems(changeReqID, changeReqItemsIDS);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";

		siteAttr.siteCode = "Automation testing";
		siteAttr.siteName = "Automation testing";
		siteAttr.description = "Automation testing";
		siteAttr.lookingForCategory = "RV Site";

		//Activate->Deactivate->Activate->Deactivate
		siteCodes = new String[]{"C-001","SSFoChangeResEnhancement","NSSFoChangeResEnhancement_1","NSSFoChangeResEnhancement_2"};
	}
}
