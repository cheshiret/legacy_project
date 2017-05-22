package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.season;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class ViewChangeRequestItems_SeasonSites extends InventoryManagerTestCase{
	List<String> changeReqItemsIDS = new ArrayList<String>();
//	String requestType = "Season Sites";
	String ids, changeReqItemsID_1, changeReqItemsID_2, changeReqID = "";
	String requestType = "*All";
	String facilityId; // Already used for change request ids:10461,10460,10433
	InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
			.getInstance();
	SeasonData seasonSearchFilter = new SeasonData();

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

		//Remove sites from Season in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		seasonPg.searchSeason(season);
		im.assignOrRemoveSiteAssociatedWithSeason("Remove", season.m_SeasonID, season.siteIds);
		changeReqItemsID_1 = im.makeChangeRequests(false, requestType);

		//Remove sites from season in 'Change Immediate Mode'
		im.gotoSeasonsPg();
		seasonPg.searchSeason(season);
		im.assignOrRemoveSiteAssociatedWithSeason("Remove", season.m_SeasonID, season.siteIds);

		//Assign sites to Season in 'Change Request Mode'
		im.switchChangeEffectiveMode();
		im.assignOrRemoveSiteAssociatedWithSeason("Assign", season.m_SeasonID, season.siteIds);
		ids = im.makeChangeRequests(true, requestType);
		changeReqItemsID_2 = ids.split(" ")[1];
		changeReqID = ids.split(" ")[0];

		//Verify change request items IDs
		changeReqItemsIDS.add(changeReqItemsID_1);
		changeReqItemsIDS.add(changeReqItemsID_2);
		im.gotoSeasonsPg();
		seasonPg.searchSeason(season);
		im.gotoSeasonSitesPg(season.m_SeasonID);
		im.verifyChangeRequestItems(changeReqID,changeReqItemsIDS);
		
//		//Assign sites from season in 'Change Immediate Mode'
//		im.gotoSeasonsPg();
//		seasonPg.searchSeason(season);
//		im.assignOrRemoveSiteAssociatedWithSeason("Assign", seasonID, season.siteIds);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
		
		facilityId =  "10460";
		inventory.facilityName = im.getFacilityName(facilityId, schema); //LAKE WATEREE
		
//		res.siteIDs=new String[]{"1137"};
		

		seasonSearchFilter.m_StartDate = "03-01-2005";
		seasonSearchFilter.m_EndDate = "10-01-2005";
		
		season.m_StartDate = DateFunctions.getDateAfterToday(3200);
		season.m_EndDate = DateFunctions.getDateAfterToday(3202);
		season.m_PrdCategory = "Site";
		season.siteIds = new String[]{"3443"};  //Site number:LAW2_CampingArea_001
		season.assignAll = false;
		season.isActiveSeason = true;
	}
}
