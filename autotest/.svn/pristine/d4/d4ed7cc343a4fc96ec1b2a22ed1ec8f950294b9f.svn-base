package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.campingunits;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class RemoveSitesFromCampingUnits extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to remove sites must include at least one site which is currently assigned.";
	String warningMessage_3 = "Your request to remove sites also contains sites which are currently assigned. These sites will be excluded. " +
	"Do you want to continue with this achange request item?";

	public void execute(){   
		//Login inventory manager and add camping unit combo
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoCampingUnitPg();
		
		//Assign site to units
		im.assignOrRemoveSitesAssociatedWithCampUnits("Assign", camp.comboID, camp.campUnitSitesIDs);

		//Verify warning message when no camping unit sites selected
		im.switchChangeEffectiveMode();
		im.assignOrRemoveSitesAssociatedWithCampUnits("Remove", camp.comboID, null);
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message when one or more sites selected and all selected sites assigned to camping units
		im.assignOrRemoveSitesAssociatedWithCampUnits("Remove", camp.comboID, camp.campUnitSitesIDs);
		changeReqItems.cRIRequestItemDetailsIDs.add(camp.campUnitSitesIDs[0]);
		changeReqItems.cRIRequestItemDetailsCodes.add("P-001");
		changeReqItems.cRIRequestItemDetailsNames.add(camp.campUnitSitesNames[0]);
		changeReqItems.cRIRequestItemDetailsLoops.add("Primitive");
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message when one or more sites selected and all selected sites not assigned to camping units
		im.gotocampingUnitSitesPg(camp.comboID);
		camp.campUnitSitesIDs = new String[]{"1609"};
		im.assignOrRemoveSitesAssociatedWithCampUnits("Remove", camp.comboID, camp.campUnitSitesIDs);
		im.verifyWarningMessage(warningMessage_2);

		//Verify warning message when one or more sites selected and at least one selected site not assigned to camping unit
		camp.campUnitSitesNames = new String[]{"SANT_Primitive_P-001", "SANT_Primitive_P-002"};
		camp.campUnitSitesIDs = new String[]{"1608","1609"};
		im.assignOrRemoveSitesAssociatedWithCampUnits("Remove", camp.comboID, camp.campUnitSitesIDs);
		im.verifyWarningMessage(warningMessage_3);
		changeReqItems.cRIRequestItemDetailsIDs.add(camp.campUnitSitesIDs[0]);
		changeReqItems.cRIRequestItemDetailsCodes.add("P-001");
		changeReqItems.cRIRequestItemDetailsNames.add(camp.campUnitSitesNames[0]);
		im.verifyRequestItemDetails(changeReqItems);
		im.switchChangeEffectiveMode();

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";

		camp.name = "Area/Loop Specific Combo";
		camp.comboID = "112";

		camp.campUnitSitesIDs = new String[]{"1608"};
		camp.campUnitSitesNames = new String[]{"SANT_Primitive_P-001"};
		res.siteIDs=new String[]{"1608"};
	}
}
