package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.season;

import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class ActivateSeason extends InventoryManagerTestCase{
	InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage.getInstance();
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningmString_2 = "Your request to activate seasons must include at least one season which is currently not active.";
	String warningMessage_3 = "Your request to activate the selected seasons also contains seasons which are currently active. These seasons will be excluded. Do you want to continue with this change request item?";
	SeasonData seasonSearchFilter = new SeasonData();
	public void execute(){
		this.updateSeasonDeletaStatusViaDB();
		//Login inventory manager and go to season page
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoSeasonsPg();

		//Add two seasons and just activate one season
		this.deleteSeason();
		String seasonID_1 = im.addNewSeason(season);  

		season.m_StartDate = DateFunctions.getDateAfterToday(852);
		season.m_EndDate = DateFunctions.getDateAfterToday(853);
		season.isActiveSeason = false;
		String seasonID_2 = im.addNewSeason(season); 

		//Verify warning message when no seasons selected
		im.switchChangeEffectiveMode();
		im.activateOrDeactivateSeason("Activate",false, new String[]{});
		im.verifyWarningMessage(warningMessage_1);

		//Verify warning message when one or more seasons selected AND all selected season active
		im.activateOrDeactivateSeason("Activate", false, seasonID_1);
		im.verifyWarningMessage(warningmString_2);

		//Verify warning message when one or more seasons selected AND at least one selected season not active
		seasonPg.searchSeason(seasonSearchFilter);
		im.activateOrDeactivateSeason("Activate", false, seasonID_1, seasonID_2);
		im.verifyWarningMessage(warningMessage_3);
		//Verify generate 'Implementable' CR Item to activate the selected (not active) seasons
		changeReqItems.cRIRequestItemDetailsIDs.add(seasonID_2);
		changeReqItems.cRIRequestItemDetailsTypes.add(season.m_SeasonType);
		im.verifyRequestItemDetails(changeReqItems);

		//When one or more seasons selected AND all selected season are deactive
		im.goBackToSeasonPg();
		seasonPg.searchSeason(seasonSearchFilter);
		im.activateOrDeactivateSeason("Activate", false, seasonID_2);
		//Verify generate 'Implementable' CR Item to activate the selected (not active) seasons
		im.verifyRequestItemDetails(changeReqItems);

		//go back to Season page and delete seasons
		im.switchChangeEffectiveMode();
		im.deleteSeason(true, seasonID_1,seasonID_2);
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";

		inventory.facilityName = "SANTEE";
		season.m_SeasonType = "Peak";
		season.m_StartDate = DateFunctions.getDateAfterToday(850);
		season.m_EndDate = DateFunctions.getDateAfterToday(851);
		season.isActiveSeason = true;
		
		seasonSearchFilter.m_StartDate = DateFunctions.getDateAfterToday(850);
		seasonSearchFilter.m_EndDate = DateFunctions.getDateAfterToday(853);
	}

	private void deleteSeason(){
		int seasonNum = im.getSeasonNum(season);
		if(seasonNum>0){
			season.m_SeasonID = im.getSeasonID(season);
			im.deleteSeason(true, season.m_SeasonID );
		}else{
			logger.info("No season is found.");
		}
	}
	
	private void updateSeasonDeletaStatusViaDB() {
 		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
 		db.resetSchema(schema);
 		String sql = "update I_SEASON_SCHDL set deleted_ind=1 where loc_id=10227 and ((start_date >= to_date('" + seasonSearchFilter.m_StartDate + "','mm/dd/yyyy') and start_date <= to_date('" + seasonSearchFilter.m_EndDate + "','mm/dd/yyyy')) or " +
 				"(end_date >= to_date('" + seasonSearchFilter.m_StartDate + "','mm/dd/yyyy') and end_date <= to_date('" + seasonSearchFilter.m_EndDate + "','mm/dd/yyyy')))";
 		logger.info("Run sql:" + sql);
		db.executeUpdate(sql);
 	}
}
