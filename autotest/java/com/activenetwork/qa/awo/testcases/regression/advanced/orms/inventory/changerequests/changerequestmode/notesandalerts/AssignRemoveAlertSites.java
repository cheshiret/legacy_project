package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.notesandalerts;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * data preparation for this test case.
 * we need to make sure siteid "4084" was assigned to the corresponding Note, but "4085" was not assigned};
 * @author QA
 *
 */
public class AssignRemoveAlertSites extends InventoryManagerTestCase{
	InvMgrNoteAndAlertListPage noteAndAlert = InvMgrNoteAndAlertListPage.getInstance();
	InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to assign sites must include at least one site which is currently not assigned.";
	String warningMessage_3 = "Your request to remove sites must include at least one site which is currently assigned.";
	String warningMessage_4 = "Your request to assign sites also contains sites which are currently assigned. These sites will be excluded. Do you want to continue with this change request item?";
	String warningMessage_5 = "Your request to remove sites also contains sites which are currently not assigned. These sites will be excluded. Do you want to continue with this change request item?";
    String searchBy, searchValue;
    
	public void execute(){
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		
		//Get site IDs in site list page
		im.goToSiteListPage();
		inventory.siteIds = im.getSiteIDs(inventory.siteCodes,invSitePg);
		
		
		//Assgn or remove site to or from notes/alerts
		im.gotoNotesAndAlertsPg();
		inventory.alertID = noteAndAlert.getAlertIDWithLoops();
		im.assignOrRemoveSitesAssociatedWithAlertOrNoteBasedOnSearch(searchBy, searchValue, "Remove", inventory.alertID, inventory.siteIds[1]);
		im.assignOrRemoveSitesAssociatedWithAlertOrNoteBasedOnSearch(searchBy, searchValue, "Assign", inventory.alertID, inventory.siteIds[0]);
		
		//Verify warning message with no selection
		//1.Assign
		im.switchChangeEffectiveMode();
		im.assignOrRemoveSitesAssociatedWithAlertOrNoteBasedOnSearch(searchBy, searchValue, "Assign", inventory.alertID, "");
		im.verifyWarningMessage(warningMessage_1);
		//2.Remove
		im.assignOrRemoveSitesAssociatedWithAlertOrNoteBasedOnSearch(searchBy, searchValue, "Remove", inventory.alertID, "");
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message and change request items details info when one or more sites selected and all selected sites assigned to note/alert
		//1.Assign
		im.assignOrRemoveSitesAssociatedWithAlertOrNoteBasedOnSearch(searchBy, searchValue, "Assign", inventory.alertID, inventory.siteIds[0]);
		im.verifyWarningMessage(warningMessage_2);
		//2.Remove
		im.assignOrRemoveSitesAssociatedWithAlertOrNoteBasedOnSearch(searchBy, searchValue, "Remove", inventory.alertID, inventory.siteIds[0]);
		this.initializeChangeItemDetailsInfo(0);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message and change request items details info when one or more sites selected and all selected sites not assigned to note/alert
		//1.Remove
		im.gotoNoteOrAlertSitesPg(inventory.alertID);
		im.assignOrRemoveSitesAssociatedWithAlertOrNoteBasedOnSearch(searchBy, searchValue, "Remove", inventory.alertID, inventory.siteIds[1]);
		im.verifyWarningMessage(warningMessage_3);
		//2.Assign
		im.assignOrRemoveSitesAssociatedWithAlertOrNoteBasedOnSearch(searchBy, searchValue, "Assign", inventory.alertID, inventory.siteIds[1]);
		this.initializeChangeItemDetailsInfo(1);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message and change request items details info when one or more sites selected and at least one selected site not assigned to note/alert
		//1.Assign
		im.gotoNoteOrAlertSitesPg(inventory.alertID);
		im.assignOrRemoveSitesAssociatedWithAlertOrNoteBasedOnSearch(searchBy, searchValue, "Assign", inventory.alertID, inventory.siteIds);
		im.verifyWarningMessage(warningMessage_4);
		this.initializeChangeItemDetailsInfo(1);
		im.verifyRequestItemDetails(changeReqItems);
		//2.Remove
		im.gotoNoteOrAlertSitesPg(inventory.alertID);
		im.assignOrRemoveSitesAssociatedWithAlertOrNoteBasedOnSearch(searchBy, searchValue, "Remove", inventory.alertID, inventory.siteIds);
		im.verifyWarningMessage(warningMessage_5);
		this.initializeChangeItemDetailsInfo(0);
		im.verifyRequestItemDetails(changeReqItems);

		im.logoutInvManager();
	}
	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		inventory.status = "All";

		//Active-Deactive
		inventory.siteCodes = new String[]{"006", "007"};
		inventory.siteNames = new String[]{"SANT_CypressLakefrontA_006", "SANT_CypressLakefrontA_007"};
		inventory.loop = new String[]{"Cypress Lakefront A", "Cypress Lakefront A"};
		res.siteIDs=new String[]{"4084","4085"};
		
		searchBy = "Parent Area";
		searchValue = inventory.loop[0];
	}

	public void initializeChangeItemDetailsInfo(int index){
		changeReqItems.cRIRequestItemDetailsIDs.clear();
		changeReqItems.cRIRequestItemDetailsCodes.clear();
		changeReqItems.cRIRequestItemDetailsNames.clear();
		changeReqItems.cRIRequestItemDetailsLoops.clear();

		changeReqItems.cRIRequestItemDetailsIDs.add(inventory.siteIds[index]);
		changeReqItems.cRIRequestItemDetailsCodes.add(inventory.siteCodes[index]);
		changeReqItems.cRIRequestItemDetailsNames.add(inventory.siteNames[index]);
		changeReqItems.cRIRequestItemDetailsLoops.add(inventory.loop[index]);
	}
}
