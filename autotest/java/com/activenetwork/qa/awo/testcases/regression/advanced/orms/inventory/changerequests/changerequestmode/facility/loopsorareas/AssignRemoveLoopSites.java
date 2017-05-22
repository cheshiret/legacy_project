package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.facility.loopsorareas;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class AssignRemoveLoopSites extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String[] loopIDs;
	String firstSiteInfo, secondSiteInfo = "";
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to assign sites which are currently assigned to a loop is a restricted change. The selected sites will be copied to the following page where you can provide additional details. Do you want to continue with this change request item?";
	String warningMessage_5 = "Your request to remove sites which are currently assigned to a loop is a restricted change. The selected sites will be copied to the following page where you can provide additional details. Do you want to continue with this change request item?";
	String warningMessage_4 = "Your request to assign sites to a loop is a restricted change. These sites will be excluded. Do you want to continue with this change request item?";
	String warningMessage_3 = "Your request to remove sites from a loop is a restricted change. These sites will be excluded. Do you want to continue with this change request item?";

	public void execute(){
		im.loginInventoryManager(login);

		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoLoopAreaPage();
		//Prepare data:The site 4108 should belong to loop "Cypress Lakefront B" at the beginning
		im.gotoLoopAreaSitePage(loop.loopOfSite[1]);
		im.assignOrRemoveSpecialSitesToLoopOrArea("Assign", loop.loopOfSite[1], loop.loopSitesIDs[1]);
		
		//Verify warning message with no selection
		//1. Assign
		im.switchChangeEffectiveMode();
		im.assignOrRemoveMultiSitesToLoopOrArea("Assign", loop.loopName, null);
		im.verifyWarningMessage(warningMessage_1);
		//2. Remove
		im.assignOrRemoveMultiSitesToLoopOrArea("Remove", loop.loopName, null);
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message and change request items details info when one site has been assigned to a loop
		changeReqItems.cRIRequestItemDetails = firstSiteInfo;
		//1. Remove
		im.assignOrRemoveSpecialSitesToLoopOrArea("Remove", loop.loopName, loop.loopSitesIDs[0]);
		im.verifyWarningMessage(warningMessage_3);
		im.verifyRequestItemDetails(changeReqItems);
		//2. Assign
		im.gotoLoopAreaSitePage(loop.loopName);
		im.assignOrRemoveSpecialSitesToLoopOrArea("Assign", loop.loopName, loop.loopSitesIDs[0]);
		im.verifyWarningMessage(warningMessage_2);

		im.verifyRequestItemDetails(changeReqItems);

		//Remove site from loop
		im.switchChangeEffectiveMode();
		im.gotoLoopAreaSitePage(loop.loopName);
		im.assignOrRemoveSpecialSitesToLoopOrArea("Assign", loop.loopName, loop.loopSitesIDs[0]);

		//Verify warning message and change request items details info when one site which have not assigned to a loop
		changeReqItems.cRIRequestItemDetails = secondSiteInfo;//update the site info for verification message
		//1. Assign
		im.switchChangeEffectiveMode();
		im.assignOrRemoveSpecialSitesToLoopOrArea("Assign", loop.loopName, loop.loopSitesIDs[1]);
		im.verifyWarningMessage(warningMessage_2);
		im.verifyRequestItemDetails(changeReqItems);
		//2. Remove
		im.gotoLoopAreaSitePage(loop.loopName);
		im.assignOrRemoveSpecialSitesToLoopOrArea("Remove", loop.loopName, loop.loopSitesIDs[1]);
		im.verifyWarningMessage(warningMessage_3);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message and change request items details info when one or more sites selected and at least one selected site not assigned to a loop
		changeReqItems.cRIRequestItemDetails = firstSiteInfo +" "+ secondSiteInfo;
		//1. Assign
		im.assignOrRemoveMultiSitesToLoopOrArea("Assign", loop.loopName, loop.loopSitesIDs);
		im.verifyWarningMessage(warningMessage_2);
		im.verifyRequestItemDetails(changeReqItems);
		//2. Remove
		im.gotoLoopAreaSitePage(loop.loopName);
		im.assignOrRemoveMultiSitesToLoopOrArea("Remove", loop.loopName, loop.loopSitesIDs);
		im.verifyWarningMessage(warningMessage_3);
		im.verifyRequestItemDetails(changeReqItems);

		im.logoutInvManager();
	}
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		loop.loopName = "Cypress Lakefront A";
		loop.loopSitesIDs = new String[]{"4085", "4108"};
		loop.loopSitesCodes = new String[]{"007","030"};
		loop.loopSitesNames= new String[]{"SANT_CypressLakefrontA_007", "SANT_CypressLakefrontB_030"};
		loop.loopOfSite = new String[]{"Cypress Lakefront A", "Cypress Lakefront B"};
		res.siteIDs=new String[]{"4085", "4108"};

		firstSiteInfo = loop.loopSitesIDs[0] +" : "+ loop.loopSitesCodes[0] +" : "+ loop.loopSitesNames[0] +" : "+ loop.loopOfSite[0];
		secondSiteInfo = loop.loopSitesIDs[1] +" : "+ loop.loopSitesCodes[1] +" : "+ loop.loopSitesNames[1] +" : "+ loop.loopOfSite[1];
	}
}
