package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.sites;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrNonSiteSpecGroupPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class ActivateDeactivateNSSGroupList extends InventoryManagerTestCase{
	InvMgrNonSiteSpecGroupPage imNonSiteSpecificGroupPg = InvMgrNonSiteSpecGroupPage
	.getInstance();
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String[] loopIDs, siteIDs, siteCodes, siteNames, loopNames;
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to activate sites must include at least one site which is currently not active.";
	String warningMessage_3 = "Your request to deactivate sites is a restricted change. The selected sites will be copied to the following page where you can provide additional details. Do you want to continue with this change request item?";
	String warningMessage_4 = "Your request to activate the selected sites also contains sites which are currently active. These sites will be excluded. Do you want to continue with this change request item?";
	public void execute(){
		im.loginInventoryManager(login);

		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoNonSiteSpecificGroupsPage();
		
        //Get NSS group site ID	and deactivate site with code changeResEnhancement_2	
		siteIDs = im.getSiteIDs(siteCodes, imNonSiteSpecificGroupPg);
		im.activateOrDeactivateNonSiteSpecificGroups("Activate", true, siteIDs[0]);
		im.activateOrDeactivateNonSiteSpecificGroups("Deactivate", true, siteIDs[1]);

		//Verify warning message with no selection
		//1. Activate
		im.switchChangeEffectiveMode();
		im.activateOrDeactivateNonSiteSpecificGroups("Activate", false, new String[]{});
		im.verifyWarningMessage(warningMessage_1);
		//2. Deactivate
		im.activateOrDeactivateNonSiteSpecificGroups("Deactivate", false, new String[]{});
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message and change request items details info when one or more NSS groups selected and all selected NSS groups active
		//1. Activate
		im.activateOrDeactivateNonSiteSpecificGroups("Activate", false, siteIDs[0]);
		im.verifyWarningMessage(warningMessage_2);
		//2. Deactivate
		im.activateOrDeactivateNonSiteSpecificGroups("Deactivate", false, siteIDs[0]);
		im.verifyWarningMessage(warningMessage_3);
		this.setUpRequestItemDetailsInfo(0, 1);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message and change request items details info when one or more NSS groups selected and all selected NSS groups not active
		//1. Activate
		im.gotoNonSiteSpecificGroupsPage();
		im.activateOrDeactivateNonSiteSpecificGroups("Activate", false, siteIDs[1]);
		this.setUpRequestItemDetailsInfo(1, 2);
		im.verifyRequestItemDetails(changeReqItems);
		//2. Deactivate      
		im.gotoNonSiteSpecificGroupsPage();
		im.activateOrDeactivateNonSiteSpecificGroups("Deactivate", false, siteIDs[1]);
		im.verifyWarningMessage(warningMessage_3);
		this.setUpRequestItemDetailsInfo(1, 3);
		changeReqItems.cRIRequestItemDetails = "None of the selected items are active";
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message and change request items details info when one or more NSS groups selected and at least one selected NSS group not active
		//1. Activate
		im.gotoNonSiteSpecificGroupsPage();
		im.activateOrDeactivateNonSiteSpecificGroups("Activate", false, siteIDs[0], siteIDs[1]);
		im.verifyWarningMessage(warningMessage_4);
		this.setUpRequestItemDetailsInfo(1, 2);
		im.verifyRequestItemDetails(changeReqItems);
		//2. Deactivate
		im.gotoNonSiteSpecificGroupsPage();
		im.activateOrDeactivateNonSiteSpecificGroups("Deactivate", false, siteIDs[0], siteIDs[1]);
		im.verifyWarningMessage(warningMessage_3);
		this.setUpRequestItemDetailsInfo(0, 1);
		im.verifyRequestItemDetails(changeReqItems);

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

	public void setUpRequestItemDetailsInfo(int index, int num){
		changeReqItems.cRIRequestItemDetails = "";
		changeReqItems.cRIRequestItemDetailsIDs.clear();
		changeReqItems.cRIRequestItemDetailsCodes.clear();
		changeReqItems.cRIRequestItemDetailsNames.clear();
		changeReqItems.cRIRequestItemDetailsLoops.clear();
		
		if(num==1){
			changeReqItems.cRIRequestItemDetails = siteIDs[index] +" : "+ siteCodes[index] +" : "+ siteNames[index] +" : "+ loopNames[index];
		}else if(num==2){
			changeReqItems.cRIRequestItemDetailsIDs.add(siteIDs[index]);
			changeReqItems.cRIRequestItemDetailsCodes.add(siteCodes[index]);
			changeReqItems.cRIRequestItemDetailsNames.add(siteNames[index]);
			changeReqItems.cRIRequestItemDetailsLoops.add(loopNames[index]);
		}
	}
}
