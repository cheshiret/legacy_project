package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.pages.orms.common.OrmsConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrConfimActionPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddSeason extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AddSeason</b>
	 * Generated     : <b>Mar 3, 2010 1:38:31 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/03
	 * @author mchen
	 */
     public void execute() {
       im.loginInventoryManager(login);
       this.updateSeasonDeletaStatusViaDB();
       im.gotoFacilityDetailsPg(inventory.facilityName);
       im.gotoSeasonsPg();
       im.addNewSeason(season);
       
       this.verifyAddSeason();
       im.logoutInvManager();
     }
 
	public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "SC Contract";
	   login.location="Administrator/South Carolina State Park Service";
		 
	   schema = TestProperty.getProperty(env + ".db.schema.prefix") + "SC";
	   inventory.facilityName = "SANTEE";
	   season.m_SeasonType = "Peak";
	   season.m_StartDate = DateFunctions.getDateAfterToday(730);
	   season.m_EndDate = DateFunctions.getDateAfterToday(735);
//	   season.m_Loop = "";
	   season.m_Loop = "All";
	   season.m_SiteType = "All";
//	   season.m_SiteType = "";
	   season.isActiveSeason = true;
     }
     
     public void verifyAddSeason() {
       InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage.getInstance();
       InvMgrConfimActionPage confirmActionPg = InvMgrConfimActionPage.getInstance();
       OrmsConfirmDialogWidget confirmWidge = OrmsConfirmDialogWidget.getInstance();
       seasonPg.searchSeason(season);
       
       List<SeasonData> seasons = seasonPg.parseSeasonDetailsTable();
       SeasonData seasonFromPage = seasons.get(0);
       
       if(!seasonFromPage.m_SeasonType.equalsIgnoreCase(season.m_SeasonType)) {
         throw new ItemNotFoundException("The season type is incorrect");
       }
       if(!seasonFromPage.m_Loop.equalsIgnoreCase(season.m_Loop)) {
         throw new ItemNotFoundException("The season loop is incorrect");
       }
       if(!seasonFromPage.m_SiteType.equalsIgnoreCase(season.m_SiteType)) {
         throw new ItemNotFoundException("The season type is incorrect");
       }
       if(!DateFunctions.formatToFullDate(seasonFromPage.m_StartDate).equals(DateFunctions.formatToFullDate(season.m_StartDate))) {
         throw new ItemNotFoundException("The season start date is incorrect");
       }
       if(!DateFunctions.formatToFullDate(seasonFromPage.m_EndDate).equals(DateFunctions.formatToFullDate(season.m_EndDate))) {
         throw new ItemNotFoundException("The season end date is incorrect");
       }
       
       seasonPg.selectFirstSeasonCheckBox();
       seasonPg.clickDelete();
       Object pages = browser.waitExists(confirmWidge, confirmActionPg);
       if(pages.equals(confirmActionPg)){
    	   confirmActionPg.clickOK();
       }else{
    	   confirmWidge.clickOK();
       }
       ajax.waitLoading();
       seasonPg.waitLoading();
     }
     
 	private void updateSeasonDeletaStatusViaDB() {
 		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
 		db.resetSchema(schema);
 		String sql = "update I_SEASON_SCHDL set deleted_ind=1 where loc_id=10227 and ((start_date >= to_date('" + season.m_StartDate + "','mm/dd/yyyy') and start_date <= to_date('" + season.m_EndDate + "','mm/dd/yyyy')) or " +
 				"(end_date >= to_date('" + season.m_StartDate + "','mm/dd/yyyy') and end_date <= to_date('" + season.m_EndDate + "','mm/dd/yyyy')))";
 		logger.info("Run sql:" + sql);
		db.executeUpdate(sql);
 	}
}

