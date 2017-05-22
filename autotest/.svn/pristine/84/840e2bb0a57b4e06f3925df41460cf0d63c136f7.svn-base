package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrConfimActionPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrComboTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourTicketsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddComboTourTicketType extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AddComboTourTicketType</b>
	 * Generated     : <b>Mar 5, 2010 4:56:03 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * update p_prd set deleted_ind=1 where prd_name='qa auto test 1249';commit;
	 * @since  2010/03/05
	 * @author QA
	 */
     String type=""; 
     
     String organization="";
     
     public void execute() {
       this.updateTourDeletaStatusViaDB();
       im.loginInventoryManager(login);
       im.gotoFacilityDetailsPg(inventory.facilityName);
       im.gotoComboTourSearchPage();

       this.activeCombo(tour.tourName, "inactive");
       im.addComboTour(tour);
       this.activeCombo(tour.tourName, "active");
       this.addTourTicketType(tour.tourCode, type, organization);
       this.verifyAddedType();
       
       im.logoutInvManager();
     }
     
     public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "NRRS Contract";
	   login.location="Administrator/NRRS";
	   
	   schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
	   inventory.facilityName = "YOUNG LAKE (SOUTH) CABIN";
	   tour.tourCode = "1249";
	   tour.tourName = "qa auto test 1249";
	   tour.ticketTourName = "qa auto 1249";
	   tour.description = "qa auto 1249";
	   tour.count = "10";
	   tour.longDescription = "qa auto test 1249";
	   type = "Adult";
	   organization = "School";
     }
     
     public void addTourTicketType(String combo, String type, String organ) {
       InvMgrComboTourPage invComboPg=InvMgrComboTourPage.getInstance();
       InvMgrTourDetailsPage invTourDetailsPg=InvMgrTourDetailsPage.getInstance();
       InvMgrTourTicketsPage invTourTicketPg=InvMgrTourTicketsPage.getInstance();
       
       invComboPg.clickCombo(combo);
       invTourDetailsPg.waitLoading();
       
       invTourDetailsPg.clickTourTickets();
       invTourTicketPg.waitLoading();
       
       invTourTicketPg.clickAddTicketType();
       invTourTicketPg.waitLoading();
       invTourTicketPg.selectTicketType(type);
       
       invTourTicketPg.clickAddOrganType();
       invTourTicketPg.waitLoading();
       invTourTicketPg.selectOrganType(organ);
       
       invTourTicketPg.clickOK();
       invComboPg.waitLoading();
     }
     
     public void activeCombo(String combo, String action) {
       InvMgrComboTourPage invComboPg = InvMgrComboTourPage.getInstance();
       if(!action.equalsIgnoreCase("active")&&(invComboPg.getComboRow(combo) - 1)<0){
    	   logger.info("The combo" + combo + " does not exist, no need to deactive it!");
       }
       invComboPg.selectCombo(invComboPg.getComboRow(combo) - 1);
       if(action.equalsIgnoreCase("active")){
    	   invComboPg.clickActive();
       }else{
    	   invComboPg.clickDeactive();
       }
       
       invComboPg.waitLoading();
     }
          
     public void verifyAddedType() {
       InvMgrComboTourPage invComboPg = InvMgrComboTourPage.getInstance();
       InvMgrTourDetailsPage invTourDetailsPg = InvMgrTourDetailsPage.getInstance();
       InvMgrTourTicketsPage invTourTicketPg = InvMgrTourTicketsPage.getInstance();
//       InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage.getInstance();
		InvMgrConfimActionPage confirmActionPg = InvMgrConfimActionPage.getInstance();
       
       invComboPg.clickCombo(tour.tourCode);
       invTourDetailsPg.waitLoading();
       
       invTourDetailsPg.clickTourTickets();
       invTourTicketPg.waitLoading();
       
       if(! invTourTicketPg.checkTicketType(type) || ! invTourTicketPg.checkOrganType(organization)){
         throw new ItemNotFoundException("Add ticket type failed!");
       }
       
       invTourTicketPg.clickTourDetails();
       ajax.waitLoading();
       invTourDetailsPg.waitLoading();
       invTourDetailsPg.clickDeleteTour();
       confirmActionPg.waitLoading();
       
//       invSiteDetailPg.waitExists();
//       invSiteDetailPg.clickOK();
       confirmActionPg.clickOK();
       invComboPg.waitLoading();
     }
     
 	private void updateTourDeletaStatusViaDB() {
 		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
 		db.resetSchema(schema);
 		String sql = "update p_prd set deleted_ind=1 where prd_name='"+ tour.tourName +"'";
		db.executeUpdate(sql);
 	}

}

