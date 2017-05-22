package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TicketType;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TourInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrAssignTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrComboTourDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrComboTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrFacilityTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourTicketsPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;

public class CreateTourFunction extends FunctionCase{

	private LoginInfo login = new LoginInfo();
	private InventoryManager im = InventoryManager.getInstance();
	private TourInfo tour = new TourInfo();

	private InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
	private InvMgrComboTourPage invComboPg = InvMgrComboTourPage.getInstance();
	private InvMgrComboTourDetailsPage invComboTourDetailsPg = InvMgrComboTourDetailsPage.getInstance();
	private InvMgrTourDetailsPage invTourDetailsPg = InvMgrTourDetailsPage.getInstance();
	private InvMgrAssignTourPage assginPage = InvMgrAssignTourPage.getInstance();
	private String facilityName,contract= "";
	private boolean loggedIn = false;
	private InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedIn && isBrowserOpened) {
			im.switchToContract(login.contract, login.location);
		} 
		if (!loggedIn || !isBrowserOpened) { // Logged in
			im.loginInventoryManager(login);
			loggedIn = true;
		}
		contract = login.contract;
		if(!invHmPg.exists()){
			im.gotoInventoryHomePg();
		}
		
		//Go to the facility details page by facility name
		im.gotoFacilityDetailsPg(facilityName);
		// Go to the tour set up page
		im.gotoTourSetUpPg(); 
		// Create tour
		if(!tour.isComboTour) {
			im.addNewTour(tour);  
			if(facTour.exists()){
				logger.info("Create tour success for tour " +tour.tourCode + " which status is inactive");
			}else{
				String message = invTourDetailsPg.getErrorMessages().get(0);
				if(message.matches("Tour code must be unique within the Facility. Please re-enter the Tour Code.")){
					logger.info("This tour existing " +tour.tourCode);
					invTourDetailsPg.clickCancleButton();
					facTour.waitLoading();
				}else{
					throw new ErrorOnPageException("Create Tour Fail for tour "+tour.tourCode);
				}
			}
			// Active a new tour
			facTour.activeTour(tour.tourCode);	
			newAddValue = "This Tour Add Success.";
//			String messageOfTicket = this.addTourTicket(tour.tourCode, tour.individualTicketTypes, tour.organizationTicketTypes); 
			String messageOfTicket = this.addTourTicket(tour); //Lesley[20131125]update to setup min/max ticket info
			if(facTour.exists()){
				newAddValue = newAddValue + " And success Assign Ticket";
				logger.info("Create tour success for tour " +tour.tourCode + " which status is active and success add ticket");
			}else{
				newAddValue = messageOfTicket;
				throw new ErrorOnPageException("Create Tour Fail for tour "+tour.tourCode + " which status is active and failed add ticket");
			}
			
		} else if(tour.isComboTour) {
			im.addComboTour(tour); // Add a new combo tour
			if(invComboPg.exists()){
				logger.info("Create Combo tour success for tour " +tour.tourCode + " which status is inactive");
			}else{
				String message = invComboTourDetailsPg.getErrorMessages().get(0);
				if(message.matches("Tour code must be unique within the Facility. Please re-enter the Tour Code.")){
					logger.info("This Combo tour existing " +tour.tourCode);
					invComboTourDetailsPg.clickCancleButton();
					invComboPg.waitLoading();
				}else{
					throw new ErrorOnPageException("Create Combo Tour Fail for tour "+tour.tourCode);
				}
			}
			invComboPg.activeCombo(tour.tourCode);
			newAddValue = "This Tour Add Success.";
//			String messageOfTicket = this.addTourTicket(tour.tourCode, tour.individualTicketTypes, tour.organizationTicketTypes); 
			String messageOfTicket = this.addTourTicket(tour); //Lesley[20131125]: to support min/max individual tickets setup
			if(messageOfTicket.length()<1 && assginPage.exists()){
				newAddValue = newAddValue + " And Success Add Ticket.";
				logger.info("Create Combo tour success for tour " +tour.tourCode + " which status is active and success add ticket" );
			}else{
				newAddValue = messageOfTicket;
				throw new ErrorOnPageException("Create Combo Tour Fail for tour "+tour.tourCode + " which status is active and failed add ticket");
			}
			if(tour.subTourCodes.length>0)
				im.assignOrUnassignTourToComoTour(true, tour.subTourCodes);	
			newAddValue = newAddValue + " And Assign Unasign Tour.";
		} else {
			throw new ItemNotFoundException("The tour is not either Tour nor Combo Tour.");
		}        
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		
		facilityName = (String)param[1];
		tour = (TourInfo)param[2];
	}

	/**
	 * Set the tour ticket for the new tour
	 * @param tourCode -- the new tour's code 
	 * @param individualTypes -- ticket type,such as: Child, Youth, Young 
	 * @param organizationTypes -- organization type, such as: school
	 */
	public String addTourTicket(TourInfo tour){
		InvMgrTourDetailsPage invTourDetailsPg=InvMgrTourDetailsPage.getInstance();
		InvMgrTourTicketsPage invTourTicketPg=InvMgrTourTicketsPage.getInstance();
		String errorMessage = "";
		

		logger.info("Add tour ticket types...");
		if(tour.individualTicketTypes.length<1 && tour.organizationTicketTypes.length<1){
			logger.info("No types need be added....");
			return errorMessage;
		}
		facTour.clickTourCode(tour.tourCode);
		invTourDetailsPg.waitLoading();
		invTourDetailsPg.clickTourTickets();
		invTourTicketPg.waitLoading();

		if(invTourTicketPg.isIndividualTicketCategoryExists()) {
			if(tour.individualTicketTypes.length>0){
				for(int i = 0; i < tour.individualTicketTypes.length; i++){
					if(tour.individualTicketTypes[i].trim().length()>0){
						invTourTicketPg.clickAddTicketType();
						invTourTicketPg.waitLoading();
						invTourTicketPg.selectTicketType_Ind(tour.individualTicketTypes[i], i);
					}
				}
			}
			if (tour.minIndividualTickets != null && tour.minIndividualTickets.size() > 0) {
				TicketType ticket = new TicketType(); 
				for (int i = 0; i < tour.minIndividualTickets.size(); i++) {
					ticket = tour.minIndividualTickets.get(i);
					invTourTicketPg.clickAddTicketTypeIndMin();
					invTourTicketPg.waitLoading();
					invTourTicketPg.setMinIndTicket(ticket.type, ticket.minTicketNum, ticket.isApplyToAdvSaleOnly, i);
				}
			}
			if (tour.maxIndividualTickets != null && tour.maxIndividualTickets.size() > 0) {
				TicketType ticket = new TicketType(); 
				for (int i = 0; i < tour.maxIndividualTickets.size(); i++) {
					ticket = tour.maxIndividualTickets.get(i);
					invTourTicketPg.clickAddTicketTypeIndMax();
					invTourTicketPg.waitLoading();
					invTourTicketPg.setMaxIndTicket(ticket.type, ticket.minTicketNum, ticket.isApplyToAdvSaleOnly, i);
				}
			}
		}

		if(invTourTicketPg.isOrganizationTicketCategoryExists()) {
			if(tour.organizationTicketTypes.length>0){
				for(int j = 0; j < tour.organizationTicketTypes.length; j++){
					invTourTicketPg.clickAddOrganType();
					invTourTicketPg.waitLoading();
					invTourTicketPg.selectOrganType(tour.organizationTicketTypes[j], j);
				}
			}
			
			if (tour.minOrgTickets != null && tour.minOrgTickets.size() > 0) {
				TicketType ticket = new TicketType(); 
				for (int i = 0; i < tour.minOrgTickets.size(); i++) {
					ticket = tour.minOrgTickets.get(i);
					invTourTicketPg.clickAddTicketTypeOrgMin();
					invTourTicketPg.waitLoading();
					invTourTicketPg.setMinOrgTicket(ticket.type, ticket.minTicketNum, ticket.isApplyToAdvSaleOnly, i);
				}
			}
			if (tour.maxOrgTickets != null && tour.maxOrgTickets.size() > 0) {
				TicketType ticket = new TicketType(); 
				for (int i = 0; i < tour.maxOrgTickets.size(); i++) {
					ticket = tour.maxOrgTickets.get(i);
					invTourTicketPg.clickAddTicketTypeOrgMax();
					invTourTicketPg.waitLoading();
					invTourTicketPg.setMaxOrgTicket(ticket.type, ticket.minTicketNum, ticket.isApplyToAdvSaleOnly, i);
				}
			}
		}

		if(!invTourTicketPg.isAssignedToursLinkExist()){
			invTourTicketPg.clickOK();
			Object pages = browser.waitExists(facTour,invTourTicketPg);
			if(pages == invTourTicketPg){
				errorMessage = invTourTicketPg.getErrorMessage().get(0);
			}
		}else{
			invTourTicketPg.clickApply();
			invTourTicketPg.waitLoading();
			if(invTourTicketPg.checkErrorMessageExisting()){
				errorMessage = invTourTicketPg.getErrorMessage().get(0);
			}
			invTourTicketPg.clickAssignedToursLink();
			assginPage.waitLoading();
		}
		
		return errorMessage;

	}

}
