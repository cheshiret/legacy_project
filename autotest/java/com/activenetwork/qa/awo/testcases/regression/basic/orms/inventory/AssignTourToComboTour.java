package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.TourInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrConfimActionPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrAssignTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrComboTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ItemNotFoundException;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class AssignTourToComboTour extends InventoryManagerTestCase {
	/**
	 * Script Name   : <b>AssignTourToComboTour</b>
	 * Generated     : <b>Mar 5, 2010 6:01:58 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/05
	 * @author QA
	 */
	String tourCode="";
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoFacilityDetailsPg(inventory.facilityName);

		im.gotoTourSetUpPg();
		im.addComboTour(tour);
		this.activeCombo(tour.tourName);
		this.assignTour(tourCode);
		this.verifyAssignTour(tourCode);

		im.logoutInvManager();
	}

	public void wrapParameters(Object[] args) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "NRRS Contract";
		login.location="Administrator/NRRS";

		inventory.facilityName = "YOUNG LAKE (SOUTH) CABIN";
		tour.tourCode = "1250";
		tour.tourName = "qa auto test 1250";
		tour.ticketTourName = "qa auto 1250";
		tour.description = "qa auto 1250";
		tour.count = "10";
		tour.longDescription = "qa auto test 1250";
		tourCode = "12333";
	}

	public void activeCombo(String combo) {
		InvMgrComboTourPage invComboPg = InvMgrComboTourPage.getInstance();

		invComboPg.selectCombo(invComboPg.getComboRow(combo) - 1);
		invComboPg.clickActive();
		invComboPg.waitLoading();
	}

	public void assignTour(String code) {
		InvMgrComboTourPage invComboPg = InvMgrComboTourPage.getInstance();
		InvMgrTourDetailsPage invTourDetailsPg = InvMgrTourDetailsPage.getInstance();
		InvMgrAssignTourPage invAssignTourPg = InvMgrAssignTourPage.getInstance();

		invComboPg.clickCombo(tour.tourCode);
		invTourDetailsPg.waitLoading();

		invTourDetailsPg.clickAssignTours();
		invAssignTourPg.waitLoading();
		invAssignTourPg.searchTourCode(code);
		invAssignTourPg.selectAll();
		invAssignTourPg.clickAssign();

		invAssignTourPg.waitLoading();
	}

	public void verifyAssignTour(String code) {
		InvMgrAssignTourPage invAssignTourPg = InvMgrAssignTourPage.getInstance();
		InvMgrTourDetailsPage invTourDetailsPg = InvMgrTourDetailsPage.getInstance();
		//       InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage.getInstance();
		InvMgrComboTourPage invComboPg = InvMgrComboTourPage.getInstance();
		InvMgrConfimActionPage confirmActionPg = InvMgrConfimActionPage.getInstance();

		invAssignTourPg.searchTourCode(code);

		List<TourInfo> tours = invAssignTourPg.parseToursDetailsTable();
		TourInfo tour = tours.get(0);
		if(!tour.isActive.equalsIgnoreCase("Yes")) {
			throw new ItemNotFoundException("Assigned tour failed");
		}

		invAssignTourPg.selectAll();
		invAssignTourPg.clickRemove();

		invAssignTourPg.waitLoading();
		invAssignTourPg.clickTourDetails();
		invTourDetailsPg.waitLoading();
		invTourDetailsPg.clickDeleteTour();
		confirmActionPg.waitLoading();

		confirmActionPg.clickOK();
		//       invSiteDetailPg.waitExists();
		//       invSiteDetailPg.clickOK();
		invComboPg.waitLoading();
	}
}

