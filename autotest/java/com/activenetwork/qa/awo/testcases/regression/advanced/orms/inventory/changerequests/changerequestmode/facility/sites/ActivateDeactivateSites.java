package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.sites;


import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ActivateDeactivateSites extends InventoryManagerTestCase{
	InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String[] loopIDs, siteIDs, siteCodes, siteNames, loopNames;
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to activate sites must include at least one site which is currently not active.";
	String warningMessage_3 = "Your request to deactivate sites is a restricted change. The selected sites will be copied to the following page where you can provide additional details. Do you want to continue with this change request item?";
	String warningMessage_4 = "Your request to activate the selected sites also contains sites which are currently active. These sites will be excluded. Do you want to continue with this change request item?";
	public void execute(){
		im.loginInventoryManager(login);

		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.goToSiteListPage();

		//Get site IDs information
		res.siteIDs = im.getSiteIDs(siteCodes, invSitePg);
		
		//Get site IDs and activate and deactivate the specific 
		siteIDs = im.getSiteIDs(siteCodes,invSitePg);
		im.activateOrDeactivateSites("Activate", true, siteIDs[0]);
		im.activateOrDeactivateSites("Activate", true, siteIDs[2]);
		im.activateOrDeactivateSites("Deactivate", true, siteIDs[1]);
		im.activateOrDeactivateSites("Deactivate", true, siteIDs[3]);

		//Verify warning message with no selection
		//1. Activate
		im.switchChangeEffectiveMode();
		im.activateOrDeactivateSites("Activate", false, new String[]{});
		im.verifyWarningMessage(warningMessage_1);
		//2. Deactivate
		im.activateOrDeactivateSites("Deactivate", false, new String[]{});
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message and change request items details info when one or more sites selected and all selected sites active
		//1. Activate
		//1.1 Site specific site
		im.activateOrDeactivateSites("Activate", false, siteIDs[0]);
		im.verifyWarningMessage(warningMessage_2);
		//1.2 Non Site-Specific Child site
		im.activateOrDeactivateSites("Activate", false, siteIDs[2]);
		im.verifyWarningMessage(warningMessage_2);
		//2. Deactivate
		//2.1 Site specific site
		im.activateOrDeactivateSites("Deactivate", false, siteIDs[0]);
		im.verifyWarningMessage(warningMessage_3);
		this.setUpRequestItemDetailsInfo(0, true);
		im.verifyRequestItemDetails(changeReqItems);
		//2.2 Non Site-Specific Child site
		im.goToSiteListPage();
		im.activateOrDeactivateSites("Deactivate", false, siteIDs[2]);
		im.verifyWarningMessage(warningMessage_3);
		this.setUpRequestItemDetailsInfo(2, true);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message and change request items details info when one or more sites selected and all selected sites not active
		// Activate
		//1. Site specific site
		im.goToSiteListPage();
		im.activateOrDeactivateSites("Activate", false, siteIDs[1]);
		this.setUpRequestItemDetailsInfo(1, false);
		im.verifyRequestItemDetails(changeReqItems);
		//2. Non Site-Specific Child site
		im.goToSiteListPage();
		im.activateOrDeactivateSites("Activate", false, siteIDs[3]);
		this.setUpRequestItemDetailsInfo(3, false);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message and change request items details info when one or more sites selected and at least one selected site not active
		//1. Activate
		//1.1 Site specific site
		im.goToSiteListPage();
		invSitePg.searchByParentLoc(loopNames[0]); //The two sites belong to the same loop
		im.activateOrDeactivateSites("Activate", false, siteIDs[0], siteIDs[1]);
		im.verifyWarningMessage(warningMessage_4);
		this.setUpRequestItemDetailsInfo(1, false);
		im.verifyRequestItemDetails(changeReqItems);
		//1.2 Non Site-Specific Child site
		im.goToSiteListPage();
		invSitePg.searchByParentLoc(loopNames[2]); //The two sites belong to the same loop
		im.activateOrDeactivateSites("Activate", false, siteIDs[2], siteIDs[3]);
		im.verifyWarningMessage(warningMessage_4);
		this.setUpRequestItemDetailsInfo(3, false);
		im.verifyRequestItemDetails(changeReqItems);
		//2. Deactivate
		//2.1 Site specific site
		im.goToSiteListPage();
		invSitePg.searchByParentLoc(loopNames[0]); //The two sites belong to the same loop
		im.activateOrDeactivateSites("Deactivate", false, siteIDs[0], siteIDs[1]);
		im.verifyWarningMessage(warningMessage_3);
		this.setUpRequestItemDetailsInfo(0, true);
		im.verifyRequestItemDetails(changeReqItems);
		//2.2 Site specific site
		im.goToSiteListPage();
		invSitePg.searchByParentLoc(loopNames[2]); //The two sites belong to the same loop
		im.activateOrDeactivateSites("Deactivate", false, siteIDs[2], siteIDs[3]);
		im.verifyWarningMessage(warningMessage_3);
		this.setUpRequestItemDetailsInfo(2, true);
		im.verifyRequestItemDetails(changeReqItems);

		im.logoutInvManager();
	}
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";

		//Activate->Deactivate->Activate->Deactivate
		siteCodes = new String[]{"C-001", "SSFoChangeResEnhancement", "NSSFoChangeResEnhancement_1", "NSSFoChangeResEnhancement_2"};
		siteNames= new String[]{"SANT_PierCabins_C-001", "SSFoChangeResEnhancement", "NSSFoChangeResEnhancement_1", "NSSFoChangeResEnhancement_2"};
		loopNames = new String[]{"Pier Cabins", "Pier Cabins", "Pier Cabins", "Pier Cabins"};
	}

	public void setUpRequestItemDetailsInfo(int index, boolean oneItem){
		changeReqItems.cRIRequestItemDetails = "";
		changeReqItems.cRIRequestItemDetailsIDs.clear();
		changeReqItems.cRIRequestItemDetailsCodes.clear();
		changeReqItems.cRIRequestItemDetailsNames.clear();
		changeReqItems.cRIRequestItemDetailsLoops.clear();

		if(oneItem){
			changeReqItems.cRIRequestItemDetails = siteIDs[index] +" : "+ siteCodes[index] +" : "+ siteNames[index] +" : "+ loopNames[index];
		}else{
			changeReqItems.cRIRequestItemDetailsIDs.add(siteIDs[index]);
			changeReqItems.cRIRequestItemDetailsCodes.add(siteCodes[index]);
			changeReqItems.cRIRequestItemDetailsNames.add(siteNames[index]);
			changeReqItems.cRIRequestItemDetailsLoops.add(loopNames[index]);
		}
	}
}
