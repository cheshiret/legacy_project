package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.season;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class RemoveSitesToSeason extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to remove sites from a season is a restricted change. The selected sites will be copied to the following page where you can provide addition details. Do you want to continue with this change request item?";
	InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
			.getInstance();
	public void execute(){
		//Login inventory manager and go to season site page
		this.updateSeasonDeletaStatusViaDB();
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoSeasonsPg();
		String seasonID = im.addNewSeason(season); 

		//Verify warning message when clicking Assign button with no site selected
		im.switchChangeEffectiveMode();
		
		seasonPg.searchSeason(season);
		im.assignOrRemoveSiteAssociatedWithSeason("Remove", seasonID, null);
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message when one or more seasons selected and all selected site assign to season
		season.siteIds = new String[]{"1608"};
		season.prd_num = new String[]{"SANT_Primitive_P-001"};
		im.assignOrRemoveSiteAssociatedWithSeason("Remove", seasonID, season.siteIds);
		im.verifyWarningMessage(warningMessage_2);
		changeReqItems.cRIRequestItemDetails = season.siteIds[0] +" : "+ 
		season.prd_num[0].split("_")[2]+" : "+season.prd_num[0]+" : "+season.m_Loop;
		im.verifyRequestItemDetails(changeReqItems);

		//When one or more sites selected and all selected sites not assign to season
		im.goBackToSeasonPg();
		season.siteIds = new String[]{"1609"};
		season.prd_num = new String[]{"SANT_Primitive_P-002"};
		im.assignOrRemoveSiteAssociatedWithSeason("Remove", seasonID, season.siteIds);
		//Verify Request Item Details info
		changeReqItems.cRIRequestItemDetails = "None of the selected sites are assigned";
		im.verifyRequestItemDetails(changeReqItems);

		//When one or more seasons selected and at least one selected site not assigned to season
		im.goBackToSeasonPg();
		season.siteIds = new String[]{"1608", "1609"};
		season.prd_num = new String[]{"SANT_Primitive_P-001", "SANT_Primitive_P-002"};
		im.assignOrRemoveSiteAssociatedWithSeason("Remove", seasonID, season.siteIds);
		im.verifyWarningMessage(warningMessage_2);
		//Verify Request Item Details info
		changeReqItems.cRIRequestItemDetails = season.siteIds[0] +" : "+ 
		season.prd_num[0].split("_")[2]+" : "+season.prd_num[0]+" : "+season.m_Loop;
		im.verifyRequestItemDetails(changeReqItems);

		//Clear up
		im.switchChangeEffectiveMode();
		im.deleteSeason(true, seasonID);
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
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
		season.isActiveSeason = true;
	}
	
	private void updateSeasonDeletaStatusViaDB() {
 		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
 		db.resetSchema(schema);
 		String sql = "update I_SEASON_SCHDL set deleted_ind=1 where loc_id=10807 and ((start_date >= to_date('" + season.m_StartDate + "','mm/dd/yyyy') and start_date <= to_date('" + season.m_EndDate + "','mm/dd/yyyy')) or " +
 				"(end_date >= to_date('" + season.m_StartDate + "','mm/dd/yyyy') and end_date <= to_date('" + season.m_EndDate + "','mm/dd/yyyy')))";
 		logger.info("Run sql:" + sql);
		db.executeUpdate(sql);
 	}
}
