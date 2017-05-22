package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.season;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class AssignSitesToSeason extends  InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to assign seasons must include at least one season which is currently not assigned.";
	String warningMessage_3 = "Your request to assign sites also contains sites which are currently assigned. These sites will be excluded. Do you want to continue with this change request item?";
	InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
			.getInstance();
	public void execute(){
		//Login inventory manager and go to season site page
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoSeasonsPg();
		String seasonID = im.addNewSeason(season); 

		//Verify warning message when clicking Assign button with no site selected
		seasonPg.searchSeason(season);
		im.switchChangeEffectiveMode();
		im.assignOrRemoveSiteAssociatedWithSeason("Assign", seasonID, null);
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message when one or more seasons selected and all selected site assign to season
		season.siteIds = new String[]{"1608"};
		season.prd_num = new String[]{"SANT_Primitive_P-001"};
		im.assignOrRemoveSiteAssociatedWithSeason("Assign", seasonID, season.siteIds);
		im.verifyWarningMessage(warningMessage_2);

		//When one or more sites selected and all selected sites not assign to season
		season.siteIds = new String[]{"1609"};
		season.prd_num = new String[]{"SANT_Primitive_P-002"};
		im.assignOrRemoveSiteAssociatedWithSeason("Assign", seasonID, season.siteIds);
		//Verify Request Item Details info
		changeReqItems.cRIRequestItemDetailsIDs.add(season.siteIds[0]);
		changeReqItems.cRIRequestItemDetailsCodes.add(season.prd_num[0]);
		changeReqItems.cRIRequestItemDetailsNames.add(season.prd_num[0]);
		changeReqItems.cRIRequestItemDetailsLoops.add(season.m_Loop);
		im.verifyRequestItemDetails(changeReqItems);

		//When one or more seasons selected and at least one selected site not assigned to season
		im.goBackToSeasonPg();
		seasonPg.searchSeason(season);
		season.siteIds = new String[]{"1608", "1609"};
		season.prd_num = new String[]{"SANT_Primitive_P-001", "SANT_Primitive_P-002"};
		im.assignOrRemoveSiteAssociatedWithSeason("Assign", seasonID, season.siteIds);
		im.verifyWarningMessage(warningMessage_3);
		//Verify Request Item Details info
		changeReqItems.cRIRequestItemDetailsIDs.add(season.siteIds[1]);
		changeReqItems.cRIRequestItemDetailsCodes.add(season.prd_num[1]);
		changeReqItems.cRIRequestItemDetailsNames.add(season.prd_num[1]);
		changeReqItems.cRIRequestItemDetailsLoops.add(season.m_Loop);
		im.verifyRequestItemDetails(changeReqItems);

		//Clear up
		im.switchChangeEffectiveMode();
		im.goBackToSeasonPg();
		seasonPg.searchSeason(season);
		im.deleteSeason(true, seasonID);
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		season.m_Loop = "Primitive";
		season.m_SeasonType = "Peak";
		season.m_StartDate = DateFunctions.getDateAfterToday(800);
		season.m_EndDate = DateFunctions.getDateAfterToday(801);
		//Info for assign specific sites to season
		season.assignAll = false;
		season.prd_num = new String[]{"SANT_Primitive_P-001"};
		season.siteIds = new String[]{"1608"};
		res.siteIDs=new String[]{"1608"};
	}
}
