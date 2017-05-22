package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrConfimActionPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrComboTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AddComboTour extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AddComboTour</b>
	 * Generated     : <b>Mar 5, 2010 4:02:56 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/05
	 * @author QA
	 */
     public void execute(){
       im.loginInventoryManager(login);
       im.gotoFacilityDetailsPg(inventory.facilityName);
       
       im.gotoTourSetUpPg();
       im.addComboTour(tour);
       this.verifyNewComboTour();
       
       im.logoutInvManager();
     }
     
     public void wrapParameters(Object[] args) {
       login.url = AwoUtil.getOrmsURL(env);
	   login.contract = "NRRS Contract";
	   login.location="Administrator/NRRS";
		 
	   inventory.facilityName = "YOUNG LAKE (SOUTH) CABIN";
	   tour.tourCode = "1248";
	   tour.tourName = "qa auto test 1248";
	   tour.ticketTourName = "qa auto 1248";
	   tour.description = "qa auto 1248";
	   tour.count = "10";
	   tour.longDescription = "qa auto test 1248";
     }
     
     public void verifyNewComboTour(){
       InvMgrComboTourPage invComboPg=InvMgrComboTourPage.getInstance();
       InvMgrTourDetailsPage invTourDetailsPg=InvMgrTourDetailsPage.getInstance();
//       InvMgrSiteDetailPage invSiteDetailPg=InvMgrSiteDetailPage.getInstance();
	   InvMgrConfimActionPage confirmActionPg = InvMgrConfimActionPage.getInstance();
       
       invComboPg.clickCombo(tour.tourCode);
       invTourDetailsPg.waitLoading();
       
       invTourDetailsPg.clickDeleteTour();
       confirmActionPg.waitLoading();
//       invSiteDetailPg.waitExists();
//       invSiteDetailPg.clickOK();
       
       confirmActionPg.clickOK();
       invComboPg.waitLoading();
     }
}

