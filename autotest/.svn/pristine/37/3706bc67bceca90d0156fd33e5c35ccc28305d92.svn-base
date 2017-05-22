package com.activenetwork.qa.awo.supportscripts.support.inventorymgr;

import java.util.List;

import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrFacilityTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourTicketsPage;
import com.activenetwork.qa.awo.supportscripts.AbstractSingleDatapoolSupportCase;
import com.activenetwork.qa.awo.util.DatapoolUtil;
import com.activenetwork.qa.testapi.util.StringUtil;

public class UpdateExistingTourTicketCategory extends AbstractSingleDatapoolSupportCase{

	private boolean loggedIn = false;
	private InventoryManager im = InventoryManager.getInstance();
	private String previousContract;
	private String facilityName;

	private InventoryInfo invInfo;
	private String[] individualActive;
	private String[] individualFieldVisible;
	private String[] individualCallCenterVisible;
	private String[] individualWebVisible;
	private String[] individualIDRequired;
	private String[] groupActive;
	private String[] groupFieldVisible;
	private String[] groupCallCenterVisible;
	private String[] groupWebVisible;


	@Override
	protected void initRange() {
		startpoint = 0;
		endpoint = 999;
	}


	@Override
	protected void readDataPool(IDatapoolIterator dpIter) {
		invInfo = DatapoolUtil.fill(InventoryInfo.class, dpIter);
		facilityName = dpIter.dpString("facilityName");

		individualActive = StringUtil.splitByComma(dpIter.dpString("individualActive"));
		individualFieldVisible = StringUtil.splitByComma(dpIter.dpString("individualFieldVisible"));
		individualCallCenterVisible = StringUtil.splitByComma(dpIter.dpString("individualCallCenterVisible"));
		individualWebVisible = StringUtil.splitByComma(dpIter.dpString("individualWebVisible"));
		individualIDRequired = StringUtil.splitByComma(dpIter.dpString("individualIDRequired"));

		groupActive = StringUtil.splitByComma(dpIter.dpString("groupActive"));
		groupFieldVisible = StringUtil.splitByComma(dpIter.dpString("groupFieldVisible"));
		groupCallCenterVisible = StringUtil.splitByComma(dpIter.dpString("groupCallCenterVisible"));
		groupWebVisible = StringUtil.splitByComma(dpIter.dpString("groupWebVisible"));
	}

	@Override
	protected void execute(Orms orms) {
		try {

			InvMgrHomePage invHome = InvMgrHomePage.getInstance();
			InvMgrTopMenuPage top = InvMgrTopMenuPage.getInstance();
			InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
			InvMgrTourDetailsPage tourDetail = InvMgrTourDetailsPage.getInstance();
			InvMgrTourTicketsPage ticketsPg = InvMgrTourTicketsPage.getInstance();

			LoginInfo loginInfo = orms.loginInfo;

			if (!loginInfo.contract.equals(previousContract) && loggedIn) {
				im.logoutInvManager();
				loggedIn = false;
			}

			if (!loggedIn) {
				im = orms.loginInventoryManager();
				previousContract = loginInfo.contract;
				loggedIn = true;

			}

			Object page = browser.waitExists(facTour, invHome);

			if (page instanceof InvMgrFacilityTourPage) {
				top.clickSelectFacility();
				invHome.waitLoading();
			}
			im.gotoFacilityDetailsPg(facilityName);


			top.gotoSpecifiedDetailPage("Tour Set-up");
			facTour.waitLoading();

			logger.info("Go to tour details page.");

			facTour.searchTour("Active", "Tour Code",
					invInfo.tourCode);

			facTour.selectTourByCode(invInfo.tourCode);
			tourDetail.waitLoading();


			tourDetail.clickTourTickets();
			ticketsPg.waitLoading();

			if (individualActive != null) {
				for (String type : individualActive) {
					if (StringUtil.notEmpty(type)) {
						if (!ticketsPg.isIndividualTicketTypeExists(type)){
							logger.info("Add new Ticket Type <" + type + ">");
							ticketsPg.addIndividualTicketType(type);
						}
					}
				}
			}

			if (individualFieldVisible != null) {
				for (String type : individualFieldVisible) {
					if (StringUtil.notEmpty(type)) {
						if (!ticketsPg.isIndividualTicketTypeExists(type)){
							logger.info("Add new Ticket Type <" + type + ">");
							ticketsPg.addIndividualTicketType(type);
						}
					}
				}
			}

			if (individualCallCenterVisible != null) {
				for (String type : individualCallCenterVisible) {
					if (StringUtil.notEmpty(type)) {
						if (!ticketsPg.isIndividualTicketTypeExists(type)){
							logger.info("Add new Ticket Type <" + type + ">");
							ticketsPg.addIndividualTicketType(type);
						}
					}
				}
			}

			if (individualWebVisible != null) {
				for (String type : individualWebVisible) {
					if (!ticketsPg.isIndividualTicketTypeExists(type)){
						if (StringUtil.notEmpty(type)) {
							logger.info("Add new Ticket Type <" + type + ">");
							ticketsPg.addIndividualTicketType(type);
						}
					}
				}
			}

			if (individualIDRequired != null) {
				for (String type : individualIDRequired) {
					if (StringUtil.notEmpty(type)) {
						if (!ticketsPg.isIndividualTicketTypeExists(type)){
							logger.info("Add new Ticket Type <" + type + ">");
							ticketsPg.addIndividualTicketType(type);
						}
					}
				}
			}

			if (groupActive != null) {
				for (String type : groupActive) {
					if (StringUtil.notEmpty(type)) {
						if (!ticketsPg.isOrganizationTicketTypeExists(type)){
							logger.info("Add new Ticket Type <" + type + ">");
							ticketsPg.addOrganizationTicketType(type);
						}
					}
				}
			}

			if (groupFieldVisible != null) {
				for (String type : groupFieldVisible) {
					if (StringUtil.notEmpty(type)) {
						if (!ticketsPg.isOrganizationTicketTypeExists(type)){
							logger.info("Add new Ticket Type <" + type + ">");
							ticketsPg.addOrganizationTicketType(type);
						}
					}
				}
			}

			if (groupCallCenterVisible != null) {
				for (String type : groupCallCenterVisible) {
					if (StringUtil.notEmpty(type)) {
						if (!ticketsPg.isOrganizationTicketTypeExists(type)){
							logger.info("Add new Ticket Type <" + type + ">");
							ticketsPg.addOrganizationTicketType(type);
						}
					}
				}
			}

			if (groupWebVisible != null) {
				for (String type : groupWebVisible) {
					if (StringUtil.notEmpty(type)) {
						if (!ticketsPg.isOrganizationTicketTypeExists(type)){
							logger.info("Add new Ticket Type <" + type + ">");
							ticketsPg.addOrganizationTicketType(type);
						}
					}
				}
			}

			// ------------ Set value ----------------------
			if (individualActive != null) {
				for (String type : individualActive) {
					if (StringUtil.notEmpty(type)) {
						logger.info("Select Individual Active for <" + type + ">");
						ticketsPg.selectIndividualActiveCheckbox(type);
					}
				}
			}

			if (individualFieldVisible != null) {
				for (String type : individualFieldVisible) {
					if (StringUtil.notEmpty(type)) {
						logger.info("Select Individual Field Visible for <" + type + ">");
						ticketsPg.selectIndividualFieldVisibleCheckbox(type);
					}
				}
			}

			if (individualCallCenterVisible != null) {
				for (String type : individualCallCenterVisible) {
					if (StringUtil.notEmpty(type)) {
						logger.info("Select Individual Call Center Visible for <" + type + ">");
						ticketsPg.selectIndividualCallCenterVisibleCheckbox(type);
					}
				}
			}

			if (individualWebVisible != null) {
				for (String type : individualWebVisible) {
					if (StringUtil.notEmpty(type)) {
						logger.info("Select Individual Web Visible for <" + type + ">");
						ticketsPg.selectIndividualWebVisibleCheckbox(type);
					}
				}
			}

			if (individualIDRequired != null) {
				for (String type : individualIDRequired) {
					if (StringUtil.notEmpty(type)) {
						logger.info("Select Individual ID Required for <" + type + ">");
						ticketsPg.selectIndividualIDRequiredVisibleCheckbox(type);
					}
				}
			}

			if (groupActive != null) {
				for (String type : groupActive) {
					if (StringUtil.notEmpty(type)) {
						logger.info("Select Organization Active for <" + type + ">");
						ticketsPg.selectOrganizationActiveCheckbox(type);
					}
				}
			}

			if (groupFieldVisible != null) {
				for (String type : groupFieldVisible) {
					if (StringUtil.notEmpty(type)) {
						logger.info("Select Organization Field Visible for <" + type + ">");
						ticketsPg.selectOrganizationFieldVisibleCheckbox(type);
					}
				}
			}

			if (groupCallCenterVisible != null) {
				for (String type : groupCallCenterVisible) {
					if (StringUtil.notEmpty(type)) {
						logger.info("Select Organization Call Center Visible for <" + type + ">");
						ticketsPg.selectOrganizationCallCenterVisibleCheckbox(type);
					}
				}
			}

			if (groupWebVisible != null) {
				for (String type : groupWebVisible) {
					if (StringUtil.notEmpty(type)) {
						logger.info("Select Organization Web Visible for <" + type + ">");
						ticketsPg.selectOrganizationWebVisibleCheckbox(type);
					}
				}
			}

			ticketsPg.clickApply();
			browser.waitExists();

			List<String> errors = ticketsPg.getErrorMessage();

			if (errors != null && !errors.isEmpty()) {
				StringBuffer error = new StringBuffer();
				for (String err : errors) {
					error.append(err);
				}

				throw new RuntimeException(error.toString());

			} else {
				ticketsPg.clickOK();
				facTour.waitLoading();

				setResult("Success");
			}

		} catch (Exception e) {
			setResult("Error -- " + e.getMessage());

			logger.error(e);

			loggedIn = false;

			throw new RuntimeException(e);
		}
	}
}
