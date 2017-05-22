package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.campingunits;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

public class AssignSitesToCampingUnits extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String requestType = "Camping Unit Sites";
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to assign sites must include at least one site which is currently not assigned.";
	String warningMessage_3 = "Your request to assign sites also contains sites which are currently assigned. These sites will be excluded. " +
	"Do you want to continue with this achange request item?";

	public void execute(){   
		//Login inventory manager and add camping unit combo
		im.loginInventoryManager(login);
		//Delete all Draft change request items
		im.deleteDraftChangeRequestItems(requestType);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoCampingUnitPg();

		//Verify warning message when no camping unit sites selected
		im.switchChangeEffectiveMode();
		im.assignOrRemoveSitesAssociatedWithCampUnits("Assign", camp.comboID, null);
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message when one or more sites selected and all selected sites assigned to camping units
		im.assignOrRemoveSitesAssociatedWithCampUnits("Assign", camp.comboID, camp.campUnitSitesIDs);
		im.verifyWarningMessage(warningMessage_2);

		//Verify warning message when one or more sites selected and all selected sites not assigned to camping units
		camp.campUnitSitesNames = new String[]{"SANT_Primitive_P-002"};
		camp.campUnitSitesIDs = new String[]{"1609"};
		im.assignOrRemoveSitesAssociatedWithCampUnits("Assign", camp.comboID, camp.campUnitSitesIDs);
		this.initializeChangeItemDetailsInfo(0);
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message when one or more sites selected and at least one selected site not assigned to camping unit
		im.gotocampingUnitSitesPg(camp.comboID);
		camp.campUnitSitesNames = new String[]{"SANT_Primitive_P-001", "SANT_Primitive_P-002"};
		camp.campUnitSitesIDs = new String[]{"1608","1609"};
		im.assignOrRemoveSitesAssociatedWithCampUnits("Assign", camp.comboID, camp.campUnitSitesIDs);
		im.verifyWarningMessage(warningMessage_3);
		this.initializeChangeItemDetailsInfo(1);
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

		camp.campUnitSitesNames = new String[]{"SANT_Primitive_P-001"};
		camp.campUnitSitesIDs = new String[]{"1608"};
		res.siteIDs=new String[]{"1608"};
	}

	public void initializeChangeItemDetailsInfo(int index){
		changeReqItems.cRIRequestItemDetailsIDs.clear();
		changeReqItems.cRIRequestItemDetailsCodes.clear();
		changeReqItems.cRIRequestItemDetailsNames.clear();
		changeReqItems.cRIRequestItemDetailsLoops.clear();

		changeReqItems.cRIRequestItemDetailsIDs.add(camp.campUnitSitesIDs[index]);
		changeReqItems.cRIRequestItemDetailsCodes.add(camp.campUnitSitesNames[index].split("_")[2]);
		changeReqItems.cRIRequestItemDetailsNames.add(camp.campUnitSitesNames[index]);
		changeReqItems.cRIRequestItemDetailsLoops.add("Primitive");//Lake Shore Area
	}
}
