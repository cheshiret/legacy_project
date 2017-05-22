package com.activenetwork.qa.awo.testcases.regression.advanced.orms.inventory.changerequests.changerequestmode.season;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest.InvMgrChangeReqItemDetail;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrSeasonDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;

public class AddNewSeason extends InventoryManagerTestCase{
	ChangeRequestsItemsInfo changeReqItems = new ChangeRequestsItemsInfo();
	InvMgrSeasonDetailPage inSeasonDetailPg = InvMgrSeasonDetailPage.getInstance();
	List <String> warningMessages = new ArrayList<String>();
	
    public void execute(){
    	//Login inventory manager
        im.loginInventoryManager(login);
        im.gotoFacilityDetailsPg(inventory.facilityName);
        
        //Go to season detail page
        im.gotoSeasonsPg();
        im.switchChangeEffectiveMode();
        im.gotoseasonDetailPg("", true);
        
        //Verify the button 'View Change Request Items'
        if(inSeasonDetailPg.checkViewChangeRequestItems()){
        	throw new ErrorOnDataException("The 'View Change Request Items' button should not exist.");
        }
        
        //Verify warning message and request item detail page
        this.verifyWarningMessageAndRequestItemDetail(warningMessages, season, changeReqItems);
        
        im.logoutInvManager();
      }
      
      public void wrapParameters(Object[] args) {
    	  login.url = AwoUtil.getOrmsURL(env);
	 	  login.contract = "SC Contract";
	 	  login.location="Administrator/South Carolina State Park Service";
	 	  inventory.facilityName = "SANTEE";
	 	  
		  season.m_SeasonType = "Peak";
		  season.m_StartDate = DateFunctions.getDateAfterToday(340);
		  season.m_EndDate = DateFunctions.getDateAfterToday(341);
		  season.m_Loop = "Primitive";
		  season.m_SiteType = "Boat In";
		  
		  changeReqItems.cRIReqItemDetailsFieldName.add("Active");
		  changeReqItems.cRIReqItemDetailsFieldName.add("Date Range - Start");
		  changeReqItems.cRIReqItemDetailsFieldName.add("Date Range - End");
		  changeReqItems.cRIReqItemDetailsFieldName.add("Site Type");
		  changeReqItems.cRIReqItemDetailsFieldName.add("Season Type");
		  changeReqItems.cRIReqItemDetailsFieldName.add("Loop/Area");

          changeReqItems.cRIReqItemDetailsRequestValue.add("N");
          changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(season.m_StartDate, "MM/dd/yyyy"));
          changeReqItems.cRIReqItemDetailsRequestValue.add(DateFunctions.formatDate(season.m_EndDate, "MM/dd/yyyy"));
          changeReqItems.cRIReqItemDetailsRequestValue.add(season.m_SiteType);
          changeReqItems.cRIReqItemDetailsRequestValue.add(season.m_SeasonType);
          changeReqItems.cRIReqItemDetailsRequestValue.add(season.m_Loop);
          
      	  warningMessages.add("End Date Invalid input");
      	  warningMessages.add("Start Date Invalid input");
      }
      
      public void verifyWarningMessageAndRequestItemDetail(List<String> warningMessages, SeasonData season, ChangeRequestsItemsInfo changeReqItems){
    	  InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail.getInstance();
    	  
    	  //Verify warning message when setting start date and click 'OK' button
    	  inSeasonDetailPg.setStartDate(season.m_StartDate);
          inSeasonDetailPg.clickOK();
          inSeasonDetailPg.waitLoading();
          im.verifyWarningMessage(warningMessages.get(0));
          
    	  //Verify warning message when setting end date and click 'OK' button
    	  inSeasonDetailPg.setEndDate(season.m_EndDate);
          inSeasonDetailPg.clickOK();
          inSeasonDetailPg.waitLoading();
          im.verifyWarningMessage(warningMessages.get(1));
            
          //Verify request item detail info
    	  inSeasonDetailPg.setUpSeason(season);
    	  inSeasonDetailPg.clickApply();
          inChangeReqItemDetailPg.waitLoading();
          im.verifyRequestItemDetails(changeReqItems);
      }
}
