package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.season;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrSeasonDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

public class EditExistSeason extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	InvMgrSeasonDetailPage inSeasonDetailPg = InvMgrSeasonDetailPage.getInstance();
	String requestType = "*All";
    String warningMessage = "Your request does not include any changes. If you are requesting to change Season Type, Start Date, End Date or Active this is a restricted change where you must provide the details of the change on the following page. For all other changes, you must make the appropriate change before making the request. Do you want to continue with this change request item?";
    InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
			.getInstance();
    String facilityId; // Already used for change request ids:10461,10460,10107
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

        //Verify read-only fields
        seasonPg.searchSeason(season);
        im.gotoseasonDetailPg(season.m_SeasonID, false);
        im.switchChangeEffectiveMode();
        this.verifyReadOnlyFields();
        
        //Verify warning message and request item details when doing nothing and click 'OK' button
        im.updateExistSeason(season, false);
        im.verifyWarningMessage(warningMessage);    //DEFECT-26791 just fixed in orms300.00
        
        //Verify request item details information
        im.gotoSeasonsPg();
        seasonPg.searchSeason(season);
        changeReqItems.cRIRequestItemDetailsOriginalValue.add(season.m_Loop);
        changeReqItems.cRIRequestItemDetailsCurrentValue.add(season.m_Loop);
		season.m_Loop = "All";
		season.m_searchSeasonID = season.m_SeasonID;
        im.updateExistSeason(season, false);
        
		changeReqItems.cRIReqItemDetailsFieldName.add("Loop/Area");
        changeReqItems.cRIReqItemDetailsRequestValue.add(season.m_Loop);
        im.verifyRequestItemDetails(changeReqItems);
//        im.switchChangeEffectiveMode();
//        
//        //Clear up
//        im.goBackToSeasonPg();
//        season.m_Loop = "Primitive";
//        seasonPg.searchSeason(season);
//        im.deleteSeason(true, season.m_SeasonID);
//        seasonPg.searchSeason(season);
        im.logoutInvManager();
      }
      
      public void wrapParameters(Object[] args) {
    	  login.url = AwoUtil.getOrmsURL(env);
	 	  login.contract = "SC Contract";
	 	  login.location="Administrator/South Carolina State Park Service";
	 	  
	 	  schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
	 	 
	 	  facilityId =  "10107";
		  inventory.facilityName = im.getFacilityName(facilityId, schema); //COLLETON
	 	  
		  season.m_SeasonType = "Peak";
		  season.m_StartDate = DateFunctions.getDateAfterToday(3300);
		  season.m_EndDate = DateFunctions.getDateAfterToday(3302);
		  season.m_Loop = "Primitive";
		  season.m_SiteType = "Boat In";
		  season.isActiveSeason = false;
		  season.m_searchStatus = "";
      }
      
      public void verifyReadOnlyFields(){
    	  InvMgrSeasonDetailPage inSeasonDetailPg = InvMgrSeasonDetailPage.getInstance();
    	  
    	  //Season Type
    	  if(!inSeasonDetailPg.GetPropertyDisable("SeasonScheduleView.seasonTypeID").equals("true")){
    		  throw new ErrorOnDataException("Season Type field should be read-only.");
    	  }
    	  //Start Date
    	  if(!inSeasonDetailPg.GetPropertyDisable("SeasonScheduleView.startDate_ForDisplay").equals("true")){
    		  throw new ErrorOnDataException("Start Date field should be read-only.");
    	  }
    	  //End Date
    	  if(!inSeasonDetailPg.GetPropertyDisable("SeasonScheduleView.endDate_ForDisplay").equals("true")){
    		  throw new ErrorOnDataException("End Date field should be read-only.");
    	  }
    	  //Active
    	  if(!inSeasonDetailPg.GetPropertyDisable("SeasonScheduleView.active").equals("true")){
    		  throw new ErrorOnDataException("Active field should be read-only.");
    	  }
      }
      
}
