package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.season;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class DeleteSeason extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	String warningMessage_1 = "Your request does not include any selected items. Please select the appropriate item(s) before making your request.";
	String warningMessage_2 = "Your request to delete a season is a restricted change. The specified season will be copied to the following page where you can provide additional details. Do you want to continue with this change request item?";
	InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
			.getInstance();
	public void execute(){   
		//Login inventory manager and go to season page
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.gotoSeasonsPg();

		//Add first season
		if(im.getSeasonNum(season)<1){
			season.m_SeasonID= im.addNewSeason(season);
		}else {
			season.m_SeasonID = im.getSeasonID(season);
		}

		//Verify warning message when no seasons selected
		im.switchChangeEffectiveMode();
		seasonPg.searchSeason(season);
		im.deleteSeason(false, new String[]{});
		im.verifyWarningMessage(warningMessage_1);

		//Verify change request item detail info when has season selected
		im.goBackToSeasonPg();
		seasonPg.searchSeason(season);
		im.deleteSeason(false, season.m_SeasonID);
		im.verifyWarningMessage(warningMessage_2);
		changeReqItems.cRIRequestItemDetails = season.m_SeasonID +" : "+ season.m_SeasonType;
		im.verifyRequestItemDetails(changeReqItems);

		//go back to Season page and delete seasons
		im.switchChangeEffectiveMode();
		im.goBackToSeasonPg();
		seasonPg.searchSeason(season);
		im.deleteSeason(true, season.m_SeasonID);
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "SC Contract";
		login.location="Administrator/South Carolina State Park Service";

		inventory.facilityName = "SANTEE";
		season.m_SeasonType = "Peak";
		season.m_StartDate = DateFunctions.getDateAfterToday(802);
		season.m_EndDate = DateFunctions.getDateAfterToday(803);
		season.isActiveSeason = false;
		season.m_searchStatus = "";
	}
}
