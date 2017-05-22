package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.season;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class ViewChangeRequestItems_SeasonList extends InventoryManagerTestCase{
	List<String> changeReqItemsIDS = new ArrayList<String>();
//	String requestType = "Seasons";
	String requestType = "*All";
	String facilityId; // Already used for change request ids:10461,10460,10433
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqItemsID_3, changeReqItemsID_4, changeReqID = "";
	InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
			.getInstance();
	SeasonData seasonNew = new SeasonData();
	public void execute(){
		//Clear data
		im.updateSeasonDeletaStatusViaDB(schema, facilityId, season);
		//Login inventory manager and add new season
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.deleteDraftChangeRequestItemsForFacility(requestType);
		im.gotoSeasonsPg();

		season.m_SeasonID = im.addNewSeason(season, true);
		season.m_searchStatus = "";
		//Deactivate season in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		seasonPg.searchSeason(season);
		im.activateOrDeactivateSeason("Deactivate", true, season.m_SeasonID);
		changeReqItemsID_1 = im.makeChangeRequests(false, requestType);

		//Deactivate season in 'Change Immediate Mode'
		im.gotoSeasonsPg();
		seasonPg.searchSeason(season);
		im.activateOrDeactivateSeason("Deactivate", true,season.m_SeasonID);

		//Activate season in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		seasonPg.searchSeason(season);
		im.activateOrDeactivateSeason("Activate", true, season.m_SeasonID);
		changeReqItemsID_2 = im.makeChangeRequests(false, requestType);

		//Delete season in 'Change Request Mode'
		im.gotoSeasonsPg();
		im.switchChangeEffectiveMode();
		seasonPg.searchSeason(season);
		im.deleteSeason(true,season.m_SeasonID);
		changeReqItemsID_3 = im.makeChangeRequests(false, requestType);

		//Add season in 'Change Request Mode'
		im.gotoSeasonsPg();
		im.switchChangeEffectiveMode();
		im.addNewSeason(seasonNew);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_4 = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		changeReqItemsIDS.add(changeReqItemsID_3);
		changeReqItemsIDS.add(changeReqItemsID_4);
		im.gotoSeasonsPg();
		im.verifyChangeRequestItems(changeReqID,changeReqItemsIDS);
		
//		//Active season in 'Change Immediate Mode'
//		im.gotoSeasonsPg();
//		seasonPg.searchSeason(seasonSearchFilter);
//		im.activateOrDeactivateSeason("Activate", true,seasonID);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";

		facilityId =  "10461";
		inventory.facilityName = im.getFacilityName(facilityId, schema); //CALHOUN FALLS
		
		seasonNew.m_StartDate = DateFunctions.getDateAfterToday(804);
		seasonNew.m_EndDate = DateFunctions.getDateAfterToday(805);
		
		season.m_StartDate = DateFunctions.getDateAfterToday(3200);
		season.m_EndDate = DateFunctions.getDateAfterToday(3202);
		season.m_PrdCategory = "Site";
		season.assignAll = true;
		season.isActiveSeason = true;
	}
	
}
