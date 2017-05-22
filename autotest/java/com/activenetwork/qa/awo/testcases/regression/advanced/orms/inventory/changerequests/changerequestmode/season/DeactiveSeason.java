package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.season;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class DeactiveSeason extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to deactivate seasons is a restricted change. The selected seasons will be copied to the following page where you can provide additional details. Do you want to continue with this change request item?";
	SeasonData season2 = new SeasonData();
	SeasonData seasonSearchFilter = new SeasonData();
	InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
			.getInstance();
	String facilityId;
	public void execute(){   
		//Login inventory manager and go to season page
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoSeasonsPg();

		//Add two seasons and just activate one season
		//Check if there is the existing same season
		String seasonID_1 = this.searchSeason(season);
		logger.info("The seasonID_1 is " + seasonID_1);
		if (seasonID_1 == null) {
			im.updateSeasonDeletaStatusViaDB(schema, facilityId, season);
			seasonID_1 = im.addNewSeason(season);
		}
		//Check if there is the existing same season
		String seasonID_2 = this.searchSeason(season2);
		logger.info("The seasonID_2 is " + seasonID_2);
		if (seasonID_2 == null) {
			im.updateSeasonDeletaStatusViaDB(schema, facilityId, season2);
			seasonID_2 = im.addNewSeason(season2);
		}
//		String seasonID_2 = im.addNewSeason(season); 

		//Verify warning message when no seasons selected
		im.switchChangeEffectiveMode();
		im.activateOrDeactivateSeason("Deactivate", false, new String[]{});
		im.verifyWarningMessage(warningMessage_1);

		//Verify change request item detail info when one or more seasons selected AND all selected season deactive
		seasonPg.searchSeason(seasonSearchFilter);
		im.activateOrDeactivateSeason("Deactivate", false, seasonID_2);   
		im.verifyWarningMessage(warningMessage_2);
		changeReqItems.cRIRequestItemDetails = "None of the selected items are active";
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message when one or more seasons selected AND at least one selected season active
		im.goBackToSeasonPg();
		seasonPg.searchSeason(seasonSearchFilter);
		im.activateOrDeactivateSeason("Deactivate", false, seasonID_1, seasonID_2);
		im.verifyWarningMessage(warningMessage_2);
		//Verify change request item detail info
		changeReqItems.cRIRequestItemDetails = seasonID_1 +" : "+ "Mid";
		im.verifyRequestItemDetails(changeReqItems);

		//Verify warning message when one or more seasons selected AND all selected season are active
		im.goBackToSeasonPg();
		seasonPg.searchSeason(seasonSearchFilter);
		im.activateOrDeactivateSeason("Deactivate", false, seasonID_1);
		im.verifyWarningMessage(warningMessage_2);
		//Verify change request item detail info
		im.verifyRequestItemDetails(changeReqItems);

		//go back to Season page and delete seasons
		im.switchChangeEffectiveMode();
		im.goBackToSeasonPg();
		seasonPg.searchSeason(seasonSearchFilter);
		im.deleteSeason(true ,seasonID_2);
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
		facilityId = "10227";
		inventory.facilityName = im.getFacilityName(facilityId, schema);//"SANTEE";
		season.m_SeasonType = "Mid";
//		season.m_StartDate = DateFunctions.getDateAfterToday(900);
		season.m_StartDate = "03/05/2005";
//		season.m_EndDate = DateFunctions.getDateAfterToday(901);
		season.m_EndDate = "03/06/2005";
		season.isActiveSeason = false;
		season.m_searchStatus = OrmsConstants.ACTIVE_STATUS;
		
		season2.m_SeasonType = "Peak";
//		season.m_StartDate = DateFunctions.getDateAfterToday(900);
		season2.m_StartDate = "04/01/2005";
//		season.m_EndDate = DateFunctions.getDateAfterToday(901);
		season2.m_EndDate = "04/02/2005";
		season2.isActiveSeason = false;
		season2.m_searchStatus = OrmsConstants.INACTIVE_STATUS;
		
		seasonSearchFilter.m_StartDate = "03/01/2005";
		seasonSearchFilter.m_EndDate = "04/10/2005";
	}
	
	
	private String searchSeason(SeasonData season) {
		logger.info("Find if there is the existing searson with start date = " + season.m_StartDate + " end date = " + season.m_EndDate);
		seasonPg.searchSeason(season);
		if (seasonPg.getSeasonNum() > 0) {
			return seasonPg.getSeasonID();
		} else {
			return null;
		}
	}
}
