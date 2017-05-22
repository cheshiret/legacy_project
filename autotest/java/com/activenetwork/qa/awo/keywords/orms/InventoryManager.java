package com.activenetwork.qa.awo.keywords.orms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentHourAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentInvAttr;
import com.activenetwork.qa.awo.datacollection.legacy.CampingUnit;
import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.datacollection.legacy.FeeValidationData;
import com.activenetwork.qa.awo.datacollection.legacy.LoopInfoData;
import com.activenetwork.qa.awo.datacollection.legacy.Lottery;
import com.activenetwork.qa.awo.datacollection.legacy.LotterySchedule;
import com.activenetwork.qa.awo.datacollection.legacy.LotterySearchCriteria;
import com.activenetwork.qa.awo.datacollection.legacy.SeasonData;
import com.activenetwork.qa.awo.datacollection.legacy.SiteAttributes;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeRequestsItemsInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceAttributesInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EventInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.FacilityData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram.LoyaltyProgramSchedule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo.RevenueLocation;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInventoryAdjust;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPassInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderItemInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeDetailInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitTypeInformation;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PointType;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuotaTypeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TourInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TourTicket;
import com.activenetwork.qa.awo.datacollection.legacy.orms.WarehouseInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.DockInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.ListInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.common.OrmsConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.common.OrmsHomePage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsLaunchPadPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsSigninPage;
import com.activenetwork.qa.awo.pages.orms.common.dialog.ConfirmationDialogWidget;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSOverwriteRevenueLocationWidget;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPosSelectRevenueLocationWidget;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSInventoryReconciliationLogPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSInventoryReconciliationPage;
import com.activenetwork.qa.awo.pages.orms.common.reports.OrmsOnlineReportProcessingPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrAddFacilityDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrAddFacilitySelectLocationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrConfirmDialogWidget;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrMapViewPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrMarinaMapViewPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteAndAlertListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteOrAlertAssignToDocksPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteOrAlertAssignToSlipsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteOrAlertDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteOrAlertEntrancesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteOrAlertLoopsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrNoteOrAlertSitesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.campingUnits.InvMgrCampingUnitDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.campingUnits.InvMgrCampingUnitLoopsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.campingUnits.InvMgrCampingUnitPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.campingUnits.InvMgrCampingUnitSitesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest.InvMgrChangeReqItemDetail;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest.InvMgrChangeReqItems;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest.InvMgrChangeRequest;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.changeRequest.InvMgrChangeRequestDetail;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip.InvMgrDockDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip.InvMgrDockListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip.InvMgrDockSlipCommonPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip.InvMgrDockSlipsListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip.InvMgrSlipDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip.InvMgrSlipListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip.InvMgrSlipNSSGroupDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip.InvMgrSlipNSSGroupListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.equipment.InvMgrAddEquipmentHourWidget;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.equipment.InvMgrEquipmentDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.equipment.InvMgrEquipmentHoursPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.equipment.InvMgrEquipmentInventoryDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.equipment.InvMgrEquipmentInventorySearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.equipment.InvMgrEquipmentSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosureDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresSitesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrConfimActionPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrSeasonClosureCommonPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrSeasonDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrSeasonSitesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrSeasonSlipsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail.InvMgrFacilityDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityDetail.InvMgrFacilitySupplementaryInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrAddNewEntrancePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrEditEntrancePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityEntrance.InvMgrEntranceListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory.InvMgrFacilityInventoryPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory.InvMgrInventoryDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityInventory.InvMgrSlipInventoryListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityPermitType.InvMgrPermitTypeDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityPermitType.InvMgrPermitTypeListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityQuotaType.InvMgrAddNewQuotaTypePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityQuotaType.InvMgrQuotaTypeListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrAssignTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrComboTourDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrComboTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrFacilityTourPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrParticipantDataPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourAllocationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourConfirmActionPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour.InvMgrTourTicketsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrCloseListWidget;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListParticpantPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.list.InvMgrListSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrLoopAreaSitePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrLoopAreasPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrLoopDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrNonSiteSpecGroupPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteValidateFeesDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSiteValidateFeesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite.InvMgrSitesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LocationSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryAssignNewParticipationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryParticipationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotteryScheduleSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.lottery.LotterySearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram.InvMgrEarnScheduleDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram.InvMgrEarnScheduleListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram.InvMgrLoyaltyProgramDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram.InvMgrLoyaltyProgramListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram.InvMgrLoyaltyProgramLocationSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram.InvMgrPointTypeDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram.InvMgrPointTypeListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram.InvMgrRedeemScheduleDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram.InvMgrRedeemScheduleListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram.InvMgrScheduleFindLocationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrMasterPosSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosPrintBarcodePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetBarcodePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetupDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetupListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrAddPOSPassNumberWidget;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSImportInventoryFilePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryAdjustmentPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryCallCenterAllocationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryFieldAllocationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryFileLogPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryManagementPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryReconciliationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryWebAllocationPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSPassSetupPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrCancelPOSPurchaseOrderPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrClosePOSPurchaseOrderPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrNewPurchaseOrderPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPOSPurchaseOrderSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrPurchaseOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder.InvMgrReceivePurchaseOrderPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSConfirmSupplierDeactivationWidget;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierProductAssignmentDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierProductAssignmentListPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.serviceEvents.InvMgrEventDetailPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.serviceEvents.InvMgrEventsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.serviceEvents.InvMgrServiceActivitiesPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse.InvMgrStcTransRecLocationsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse.InvMgrWarehouseDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse.InvMgrWarehouseSearchPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse.InvMgrWarehouseSelectLocationPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.page.FileDownloadDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author raonqa
 * 
 */
public class InventoryManager extends Orms {
	private static InventoryManager _instance = null;

	public static String TOUR = "Tour";
	public static String COMBO_TOUR = "Combo Tour";

	public static InventoryManager getInstance() {
		if (null == _instance)
			_instance = new InventoryManager();

		return _instance;
	}
	
	protected InventoryManager() {
	}

	/** log into inventory manager. */
	public OrmsPage loginInventoryManager(LoginInfo login) {
		return loginInventoryManager(login, true);
	}

	/**
	 * This method is a overload method,used for improve performance
	 * 
	 * @param login
	 * @param newBrowser
	 *            -determined whether we need to open a new browser
	 */
	public OrmsPage loginInventoryManager(LoginInfo login, boolean newBrowser) {
		 InvMgrHomePage invHmPg = InvMgrHomePage.getInstance();
		 InvMgrFacilityDetailPage inFacilityDetailPg =InvMgrFacilityDetailPage.getInstance();
		 InvMgrWarehouseDetailsPage warhouseDetailsPg = InvMgrWarehouseDetailsPage.getInstance();
		
		 login(login,"Inventory Manager",newBrowser);
		
		 return (OrmsPage)browser.waitExists(invHmPg, inFacilityDetailPg,warhouseDetailsPg);
	}

	/**
	 * Login Inventory manager. Start page is orms signin page, and then go to
	 * orms lauch page page, select role, login inventory manager
	 * 
	 * @param login
	 */
	public void loginInventoryManagerFromLaunchPad(LoginInfo login) {
		OrmsSigninPage ormSignInPg = OrmsSigninPage.getInstance();
		OrmsLaunchPadPage ormLauPadPg = OrmsLaunchPadPage.getInstance();
		InvMgrHomePage InvMgrHmPg = InvMgrHomePage.getInstance();

		logger.info("Login Inventory Manager with url=" + login.url);

		browser.closeAllBrowsers();
		browser.open(login.url);
		ormSignInPg.waitLoading();
		ormSignInPg.login(login.userName, login.password, login.envType);
		ormLauPadPg.waitLoading();

		ormLauPadPg.selectContract(login.contract);
		ormLauPadPg.selectLocation(login.location);
		ormLauPadPg.clickInventoryManager();
		InvMgrHmPg.waitLoading();

	}

	public void invokeInventoryManager(LoginInfo login) {
		InvMgrHomePage InvMgrHmPg = InvMgrHomePage.getInstance();

		if (!InvMgrHmPg.exists()) {
			loginInventoryManager(login);
		}

	}
	
	public void gotoSiteInventoryListPage(String parkName) {
		InvMgrHomePage invHome = InvMgrHomePage.getInstance();
		InvMgrFacilityDetailPage facilityPg = InvMgrFacilityDetailPage
				.getInstance();
		InvMgrFacilityInventoryPage inventoryPg = InvMgrFacilityInventoryPage
				.getInstance();
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();

		invHome.searchFacilities("Facility Name", parkName);
		invHome.waitLoading();
		invHome.selectFacilityByName(parkName);

		facilityPg.waitLoading();
		topMenu.gotoSpecifiedDetailPage("Inventory");

		inventoryPg.waitLoading();
	}

	/**
	 * This method goes to specified facility's details page. It starts at
	 * inventory home page, and ends at facility details page
	 * 
	 * @param facility
	 *            - Facility Name
	 * @return the facility ID
	 */
	public String gotoFacilityDetailsPg(String facility) {
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		InvMgrHomePage invHome = InvMgrHomePage.getInstance();
		InvMgrFacilityDetailPage invDetails = InvMgrFacilityDetailPage
				.getInstance();
		String facilityID = "";
		String pageName = "";

		logger.info("Go to facility details page.");
		if (topMenu.checkTopDropDownListExist() && !invHome.exists()) {
			String topDropDownListElements = topMenu
					.getTopDropDownListElements().toString();
			if (topDropDownListElements.contains("Facility Setup")) {
				pageName = "Facility Setup";
			} else {
				pageName = "Facility Details";
			}
			topMenu.gotoSpecifiedDetailPage(pageName);
//			invHome.waitExists();
			browser.waitExists(invHome, invDetails);
		}

		if(invHome.exists()){
			invHome.searchFacilities("Facility Name", facility);
			facilityID = invHome.selectFacilityByName(facility);
			invDetails.waitLoading();	
		}

		return facilityID;
	}

	/**
	 * Go to facility supplementary info page from inventory home page
	 * 
	 * @param facility
	 */
	public void gotoFacilitySupplementaryInfo(String facility) {
		InvMgrFacilityDetailPage invDetails = InvMgrFacilityDetailPage
				.getInstance();
		InvMgrFacilitySupplementaryInfo invSupplementaryInfo = InvMgrFacilitySupplementaryInfo
				.getInstance();

		logger.info("Go to facility supplementary info page.");
		this.gotoFacilityDetailsPg(facility);
		invDetails.clickSupplementaryInfoTab();
		invSupplementaryInfo.waitLoading();

	}

	/**
	 * Go to facility detail page from inventory home page by searching facility
	 * ID
	 * 
	 * @param facilityID
	 */
	public void gotoFacilityDetailPageById(String facilityID) {
		InvMgrHomePage InvHmPg = InvMgrHomePage.getInstance();
		InvMgrFacilityDetailPage invFaciDetailPg = InvMgrFacilityDetailPage
				.getInstance();
		InvMgrTopMenuPage invTpMeuPg = InvMgrTopMenuPage.getInstance();

		logger.info("Go to facility(ID#=" + facilityID + ") details page.");
		if (!InvHmPg.exists()) {
			invTpMeuPg.clickSelectFacility();
			InvHmPg.waitLoading();
		}

		InvHmPg.searchFacilities("Facility ID", facilityID);
		InvHmPg.waitLoading();

		InvHmPg.selectFacilityById(facilityID);
		invFaciDetailPg.waitLoading();
	}

	/** Go to site list page from loop page to site list page */
	public void goToSiteListPage() {
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		InvMgrTopMenuPage invTpMenPg = InvMgrTopMenuPage.getInstance();
		InvMgrLoopAreasPage invLoopAreaPg = InvMgrLoopAreasPage.getInstance();
		InvMgrSiteDetailPage siteDetailPg = InvMgrSiteDetailPage.getInstance();
		logger.info("Go to site list page.");

		if (!invSitePg.exists() && !invLoopAreaPg.isSiteTabExist() && !siteDetailPg.exists()) {
			invTpMenPg.gotoSpecifiedDetailPage("Loop/Site Set-up");
			invLoopAreaPg.waitLoading();
		}

		invLoopAreaPg.clickSites();
		invSitePg.waitLoading();
	}
	
	public void gotoAddSiteDetailPageFromSiteListPage(){
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		InvMgrSiteDetailPage siteDetailPg = InvMgrSiteDetailPage.getInstance();
		
		logger.info("Go to add site detail page from site list page.");
		invSitePg.clickAddNewSite();
		siteDetailPg.waitLoading();
	}

	/** Go to Non-Site-Specific Groups page from loop page */
	public void gotoNonSiteSpecificGroupsPage() {
		InvMgrTopMenuPage invTpMenPg = InvMgrTopMenuPage.getInstance();
		InvMgrLoopAreasPage invLoopAreaPg = InvMgrLoopAreasPage.getInstance();
		InvMgrNonSiteSpecGroupPage imNonSiteSpecificGroupPg = InvMgrNonSiteSpecGroupPage
				.getInstance();
		InvMgrSiteDetailPage siteDetailpg = InvMgrSiteDetailPage.getInstance();
		logger.info("Go to Non-Site-Specific Groups page.");

		if (!imNonSiteSpecificGroupPg.exists()
				&& !invLoopAreaPg.isNSSTabExist() && !siteDetailpg.exists()) {
			invTpMenPg.gotoSpecifiedDetailPage("Loop/Site Set-up");
			invLoopAreaPg.waitLoading();
		}
		invLoopAreaPg.clickNonSpecSiteGroup();
		imNonSiteSpecificGroupPg.waitLoading();
	}

	/** Go to Non-Site-Specific Groups page from Sites page */
	public void gotoNSSGroupsPageFromSitesPage() {
		InvMgrSitesPage sitePg = InvMgrSitesPage.getInstance();
		InvMgrNonSiteSpecGroupPage imNonSiteSpecificGroupPg = InvMgrNonSiteSpecGroupPage
				.getInstance();

		logger.info("Go to Non-Site-Specific Groups page from Sites page.");

		sitePg.clickNonSpecificSiteTab();
		imNonSiteSpecificGroupPg.waitLoading();
	}

	/**
	 * Switch the page Sites and Non-Site-Specific Group page
	 * 
	 * @param startPg
	 */
	public void swichSitesAndNonSiteSpecificGroupsPage(OrmsPage startPg) {
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		InvMgrNonSiteSpecGroupPage imNonSiteSpecificGroupPg = InvMgrNonSiteSpecGroupPage
				.getInstance();
		if (startPg == invSitePg) {
			invSitePg.clickNonSpecificSiteTab();
			imNonSiteSpecificGroupPg.waitLoading();
		} else if (startPg == imNonSiteSpecificGroupPg) {
			imNonSiteSpecificGroupPg.clickSites();
			invSitePg.waitLoading();
		}
	}

	/**
	 * Go to site detail page by selecting site ID It start from Loops/Areas
	 * page page and end with site detail page
	 * 
	 * @param loopAreaID
	 * @param siteID
	 * @param isNSS
	 *            : Non-Site-Specific Groups : Add new site/Add new Group
	 * @param isNew
	 */
	public void gotoSiteDetailFromLoopSite(String loopAreaID, String siteID,
			boolean isNSS, boolean isNew) {
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage
				.getInstance();
		InvMgrTopMenuPage invTpMenPg = InvMgrTopMenuPage.getInstance();
		InvMgrLoopAreasPage invLoopAreaPg = InvMgrLoopAreasPage.getInstance();
		InvMgrNonSiteSpecGroupPage invNonStSpecGrpPg = InvMgrNonSiteSpecGroupPage
				.getInstance();
		InvMgrLoopAreaSitePage imLoopAreaSitePg = InvMgrLoopAreaSitePage
				.getInstance();
		logger.info("Go to Site Detail Page From loop site.");
		if(!imLoopAreaSitePg.exists()){
			if (!invLoopAreaPg.exists()) {
				invTpMenPg.gotoSpecifiedDetailPage("Loop/Site Set-up");
	
				Object page = browser.waitExists(invLoopAreaPg, invNonStSpecGrpPg,
						invSitePg);
	
				if (page == invSitePg) {
					invSitePg.clickLoopsAreas();
				} else if (page == invNonStSpecGrpPg) {
					invNonStSpecGrpPg.clickLoopsAreas();
				}
				invLoopAreaPg.waitLoading();
			}
				
			invLoopAreaPg.clickLoopAreaSiteNum(loopAreaID);
			imLoopAreaSitePg.waitLoading();
		}
		// Select status as 'All Sites'
//		String expectStatusValue = "All Sites";
//		if (!imLoopAreaSitePg.checkStatus(expectStatusValue)) {
//		imLoopAreaSitePg.searchSites("Site ID", siteID, expectStatusValue, "", "", "");
//		}
		//It can not use search site by id method for some cases(eg.VerifyFeeCalculatValidatForDaySiteFromLoopsSite) need the sites before and after the special one,so use following method:
		imLoopAreaSitePg.turnToFirstPage(); //Can not be removed for the next step it may be on the last page (eg.VerifyFeeCalculatValidatForDaySiteFromLoopsSite)
		imLoopAreaSitePg.turnToSiteExistPage(siteID);
		
		imLoopAreaSitePg.clickSiteID(siteID);
		invSiteDetailPg.waitLoading();
	}

	/**
	 * Go to site detail page by selecting site ID It start from site search
	 * page and end with site detail page
	 * 
	 * @param siteID
	 * @param isNSS
	 *            : Non-Site-Specific Groups : Add new site/Add new Group
	 * @param isNew
	 */
	public void gotoSiteDetailPage(String siteID, boolean isNSS, boolean isNew) {
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage
				.getInstance();
		InvMgrTopMenuPage invTpMenPg = InvMgrTopMenuPage.getInstance();
		InvMgrLoopAreasPage invLoopAreaPg = InvMgrLoopAreasPage.getInstance();
		InvMgrNonSiteSpecGroupPage invNonStSpecGrpPg = InvMgrNonSiteSpecGroupPage
				.getInstance();

		logger.info("Go to Site Detail Page.");

		if (!invSitePg.exists()) {
			invTpMenPg.gotoSpecifiedDetailPage("Loop/Site Set-up");

			Object page = browser.waitExists(invLoopAreaPg, invNonStSpecGrpPg,
					invSitePg);

			if (page == invLoopAreaPg) {
				invLoopAreaPg.clickSites();
			} else if (page == invNonStSpecGrpPg) {
				invNonStSpecGrpPg.clickSites();
			}
			invSitePg.waitLoading();
		}
		if (!isNSS && !isNew) {
			invSitePg.searchSite("Site ID", siteID);
			invSitePg.waitLoading();

			invSitePg.clickSiteID(siteID);
		} else if (!isNSS && isNew) {
			invSitePg.clickAddNewSite();
		} else if (isNSS && !isNew) {
			invSitePg.clickNonSpecificSiteTab();
			invNonStSpecGrpPg.waitLoading();

			invNonStSpecGrpPg.searchSite("Site ID", siteID);
			invNonStSpecGrpPg.waitLoading();

			invNonStSpecGrpPg.selectSiteBySiteID(siteID);
		} else if (isNSS && isNew) {
			invSitePg.clickNonSpecificSiteTab();
			invNonStSpecGrpPg.waitLoading();

			invNonStSpecGrpPg.clickAddNewGroup();

			invNonStSpecGrpPg.selectSiteBySiteID(siteID);
		}

		invSiteDetailPg.waitLoading();
	}

	/**
	 * Modify site attributes
	 * 
	 * @param siteAttr
	 */
	public void modifySiteAttributes(SiteAttributes siteAttr) {
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage
				.getInstance();
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		InvMgrNonSiteSpecGroupPage invNonSiteSpecGpPg = InvMgrNonSiteSpecGroupPage
				.getInstance();
		InvMgrConfimActionPage invConfirmActPg = InvMgrConfimActionPage
				.getInstance();

		invSiteDetailPg.setSiteInfoAndAttributes(siteAttr);
		invSiteDetailPg.clickOK();

		Object page = browser.waitExists(invNonSiteSpecGpPg, invSitePg,
				invConfirmActPg);

		if (page == invConfirmActPg) {
			invConfirmActPg.clickOK();
			invSiteDetailPg.waitLoading();
			invSiteDetailPg.clickOK();

			browser.waitExists(invSitePg, invNonSiteSpecGpPg);
		}

	}

	public String createSite(SiteAttributes siteAttr, OrmsPage startPg) {
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		InvMgrNonSiteSpecGroupPage imNonSiteSpecificGroupPg = InvMgrNonSiteSpecGroupPage
				.getInstance();
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage
				.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();

		logger.info("Create a NSS Group.");

		if (!invSiteDetailPg.exists()) {
			if (startPg == imNonSiteSpecificGroupPg) {
				imNonSiteSpecificGroupPg.clickAddNewGroup();
			} else if (startPg == invSitePg) {
				invSitePg.clickAddNewSite();
			}
			invSiteDetailPg.waitLoading();
		}

		invSiteDetailPg.setSiteInfoAndAttributes(siteAttr);
		invSiteDetailPg.clickApply();

		Object page = browser.waitExists(inChangeReqItemDetailPg, invSiteDetailPg);
		if (page == invSiteDetailPg && !invSiteDetailPg.isErrorExist()) {
			siteAttr.siteID = invSiteDetailPg.getSiteID();
			invSiteDetailPg.clickOK();
			browser.waitExists(imNonSiteSpecificGroupPg, invSitePg,
					inChangeReqItemDetailPg);
		}

		return siteAttr.siteID;
	}

	/**
	 * Create site none nss group
	 * 
	 * @param siteInfo
	 * @return
	 */
	public String createSite(SiteAttributes siteAttr) {
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		return this.createSite(siteAttr, invSitePg);
	}

	/**
	 * Create site nss group
	 * 
	 * @param siteInfo
	 * @return
	 */
	public String createNonSiteSpecGroup(SiteAttributes siteAttr) {
		InvMgrNonSiteSpecGroupPage imNonSiteSpecificGroupPg = InvMgrNonSiteSpecGroupPage
				.getInstance();
		return this.createSite(siteAttr, imNonSiteSpecificGroupPg);
	}

	/**
	 * Update site information
	 * 
	 * @param siteAttr
	 * @param dismiss
	 * @return
	 */
	public String updateSite(SiteAttributes siteAttr, boolean dismiss) {
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		//add NSS page mgr
		InvMgrNonSiteSpecGroupPage invNonStSpecGrpPg = InvMgrNonSiteSpecGroupPage
				.getInstance();
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage
				.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();

		logger.info("Update site info.");

		invSiteDetailPg.setSiteInfoAndAttributes(siteAttr);

		invSiteDetailPg.clickApply();

		confirmDialog.setDismissible(dismiss);
		Object page = browser.waitExists(confirmDialog, inChangeReqItemDetailPg, invSiteDetailPg);
		if (page == invSiteDetailPg) {
			siteAttr.siteID = invSiteDetailPg.getSiteID();
			invSiteDetailPg.clickOK();
//			invSitePg.waitExists(); add wait two kind of page:no-Nss and Nss
			browser.waitExists(invSitePg, invNonStSpecGrpPg);
		}

		return siteAttr.siteID;
	}

	public void activeSite(String siteID, OrmsPage startPg) {
		InvMgrSitesPage sitePg = InvMgrSitesPage.getInstance();
		InvMgrNonSiteSpecGroupPage nssGroupPg = InvMgrNonSiteSpecGroupPage
				.getInstance();

		logger.info("Active the site.");
		if (startPg == sitePg) {
			sitePg.searchSite("Site ID", siteID);
			sitePg.selectSiteCheckBoxBySiteID(siteID);
			sitePg.clickActivate();
			sitePg.waitLoading();
		} else if (startPg == nssGroupPg) {
			nssGroupPg.selectNonSiteSpecificGroupsCheckBoxBySiteID(siteID);
			nssGroupPg.clickActivate();
			nssGroupPg.waitLoading();
		}
	}

	/**
	 * active none nss group
	 * 
	 * @param siteID
	 */
	public void activeSite(String siteID) {
		InvMgrSitesPage sitePg = InvMgrSitesPage.getInstance();
		this.activeSite(siteID, sitePg);
	}

	/**
	 * active nss group
	 * 
	 * @param siteID
	 */
	public void activeNssGroup(String siteID) {
		InvMgrNonSiteSpecGroupPage nssGroupPg = InvMgrNonSiteSpecGroupPage
				.getInstance();
		this.activeSite(siteID, nssGroupPg);
	}

	/**
	 * Activate or deactivate site
	 * 
	 * @param order
	 * @param siteID
	 */
	public void activeOrDeactiveSite(String order, String... siteID) {
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		logger.info("Active or Deactive a site.");

		invSitePg.selectSiteCheckBoxBySiteIDs(siteID);

		if (order.equalsIgnoreCase("Activate")) {
			invSitePg.clickActivate();
		}

		if (order.equalsIgnoreCase("Deactivate")) {
			invSitePg.clickDeactivate();
		}

		
		invSitePg.waitLoading();

	}

	/**
	 * Activate or deactivate one or more season
	 * 
	 * @param order
	 *            : Activate and Deactivate
	 * @param dismissable
	 *            : For confirmDialog, dismiss or not.
	 * @param seasons
	 */
	public void activateOrDeactivateSeason(String order, boolean dismissable,
			String... seasonIDs) {
		InvMgrFacilityBookingRulesPage seasons = InvMgrFacilityBookingRulesPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrConfimActionPage imConfimActionPg = InvMgrConfimActionPage
				.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();

		logger.info(order + " a season.");

		if (order.equalsIgnoreCase("Activate")) {
			seasons.activateSeason(seasonIDs);
		}

		if (order.equalsIgnoreCase("Deactivate")) {
			seasons.deactivateSeason(seasonIDs);
		}

		confirmDialogPg.setDismissible(dismissable);
		Object pages = browser.waitExists(confirmWidget, imConfimActionPg, seasons,
				confirmDialogPg, inChangeReqItemDetailPg);

		if (pages.equals(imConfimActionPg)) {
			imConfimActionPg.clickOK();
			seasons.waitLoading();
		} else {
			if(dismissable){
				if(pages.equals(confirmDialogPg)){
					confirmDialogPg.clickOK();

				}else if (pages.equals(confirmWidget)){
					confirmWidget.clickOK();
					ajax.waitLoading();
				}

				browser.waitExists(seasons, inChangeReqItemDetailPg);
			}
			//			if (dismissable && pages.equals(confirmDialogPg)) {
			//				confirmDialogPg.clickOK();
			//				browser.waitExists(seasons, inChangeReqItemDetailPg);
			//			}
			//			if(pages.equals(confirmWidget)){
			//				confirmWidget.clickOK();
			//				ajax.waitLoading();
			//				seasons.waitExists();
			//			}
		}
	}

	/**
	 * Activate or deactivate one or more notes or alerts
	 * 
	 * @param inventory
	 *            : for search notes or alerts
	 * @param order
	 *            : Activate and Deactivate
	 * @param dismissable
	 *            : For confirmDialog, dismiss or not.
	 * @param noteOrAlertIDs
	 */
	public void activateOrDeactivateNoteOrAlert(InventoryInfo inventory,
			String order, boolean dismissable, String... noteOrAlertIDs) {
		InvMgrNoteAndAlertListPage invAlertListPg = InvMgrNoteAndAlertListPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();

		logger.info(order + " a note or alert.");

		if (null != noteOrAlertIDs) {
			invAlertListPg.searchNotesOrAlerts(inventory);
		}
		
		PagingComponent turnPageComponent = new PagingComponent();
		boolean found=invAlertListPg.isNoteOrAlertIDExisted(inventory.alertID);
		while(!found && turnPageComponent.nextExists()) {
			turnPageComponent.clickNext();
			invAlertListPg.waitLoading();
			found=invAlertListPg.isNoteOrAlertIDExisted(inventory.alertID);
		}

		if(!found)
			throw new ActionFailedException("Note alert with id:"+inventory.alertID+" not found on page");
			
		if (order.equalsIgnoreCase("Activate")) {
			invAlertListPg.activeNotesOrAlerts(noteOrAlertIDs);
		}

		if (order.equalsIgnoreCase("Deactivate")) {
			invAlertListPg.deactiveNotesOrAlerts(noteOrAlertIDs);
		}

		confirmDialogPg.setDismissible(dismissable);
		Object pages = browser.waitExists(invAlertListPg, confirmDialogPg,
				inChangeReqItemDetailPg);

		if (dismissable && pages == confirmDialogPg) {
			confirmDialogPg.clickOK();
			browser.waitExists(invAlertListPg, inChangeReqItemDetailPg);
		}
	}

	/**
	 * Activate or deactivate one or more sites in site list
	 * 
	 * @param siteAttr
	 * @param order
	 *            : Activate and Deactivate
	 * @param dismissable
	 *            : For confirmDialog, dismiss or not.
	 * @param siteIDS
	 */
	public void activateOrDeactivateSites(String order, boolean dismissable,
			String... siteIDs) {
		InvMgrSitesPage imSitePg = InvMgrSitesPage.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		InvMgrConfimActionPage confirmAction = InvMgrConfimActionPage
				.getInstance();

		logger.info(order + " a site.");

		if (order.equalsIgnoreCase("Activate")) {
			imSitePg.activeSite(siteIDs);
		}
		if (order.equalsIgnoreCase("Deactivate")) {
			imSitePg.deactiveNotesOrAlerts(siteIDs);
		}

		confirmDialogPg.setDismissible(dismissable);
		Object pages = browser.waitExists(imSitePg, confirmDialogPg,
				inChangeReqItemDetailPg, confirmAction);

		if (pages.equals(confirmAction)) {
			confirmAction.clickOK();
			imSitePg.waitLoading();
		} else if (dismissable && pages.equals(confirmDialogPg)) {
			confirmDialogPg.clickOK();
			browser.waitExists(imSitePg, inChangeReqItemDetailPg);
		}
	}
	
	public void activateAllSites(){
		InvMgrSitesPage imSitePg = InvMgrSitesPage.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		InvMgrConfimActionPage confirmAction = InvMgrConfimActionPage
				.getInstance();

		imSitePg.activeAllSites();
		confirmDialogPg.setDismissible(true);
		Object pages = browser.waitExists(imSitePg, confirmDialogPg,
				inChangeReqItemDetailPg, confirmAction);

		if (pages.equals(confirmAction)) {
			confirmAction.clickOK();
			imSitePg.waitLoading();
		} else if (pages.equals(confirmDialogPg)) {
			confirmDialogPg.clickOK();
			browser.waitExists(imSitePg, inChangeReqItemDetailPg);
		}
	}

	/**
	 * Activate or deactivate one or more Non Site Specific Groups
	 * 
	 * @param siteAttr
	 * @param order
	 *            : Activate and Deactivate
	 * @param dismissable
	 *            : For confirmDialog, dismiss or not.
	 * @param siteIDS
	 */
	public void activateOrDeactivateNonSiteSpecificGroups(String order,
			boolean dismissable, String... siteIDs) {
		InvMgrNonSiteSpecGroupPage imNonSiteSpecificGroupPg = InvMgrNonSiteSpecGroupPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		InvMgrConfimActionPage confirmActionPg = InvMgrConfimActionPage
				.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();

		logger.info(order + " a Non Site Specific Groups.");

		if (order.equalsIgnoreCase("Activate")) {
			imNonSiteSpecificGroupPg.activeSite(siteIDs);
		}
		if (order.equalsIgnoreCase("Deactivate")) {
			imNonSiteSpecificGroupPg.deactiveNotesOrAlerts(siteIDs);
		}

		confirmDialogPg.setDismissible(dismissable);
		Object pages = browser.waitExists(imNonSiteSpecificGroupPg,
				confirmDialogPg, inChangeReqItemDetailPg, confirmActionPg, confirmWidget);

		if (pages==confirmActionPg) {
			confirmActionPg.clickOK();
			imNonSiteSpecificGroupPg.waitLoading();
		} else if (dismissable && pages.equals(confirmDialogPg)) {
			confirmDialogPg.clickOK();
			browser.waitExists(imNonSiteSpecificGroupPg,
					inChangeReqItemDetailPg);
		} else if(pages == confirmWidget) {
			confirmWidget.clickOK();
			imNonSiteSpecificGroupPg.waitLoading();
		}
	}

	/**
	 * This method goes to specified tour's details page. It starts from
	 * inventory details page top menu, ends at tour's detail page
	 * 
	 * @param invInfo
	 *            - inventory data
	 * @return the tour's code
	 */
	public String gotoTourDetailsPg(InventoryInfo invInfo) {
		InvMgrTopMenuPage top = InvMgrTopMenuPage.getInstance();
		InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
		InvMgrTourDetailsPage tourDetail = InvMgrTourDetailsPage.getInstance();

		logger.info("Go to tour details page.");
		if (!facTour.exists()) {
			top.gotoSpecifiedDetailPage("Tour Set-up");
			facTour.waitLoading();
		}
		facTour.searchTour(invInfo.tourStatus, invInfo.tourType,
				invInfo.tourValue);

		invInfo.tourCode = facTour.selectTourByName(invInfo.tourName);
		tourDetail.waitLoading();

		return invInfo.tourCode;
	}

	/**
	 * Search tour in facility tour page.
	 */
	public void searchComboTour(String status, String searchType,
			String searchValue) {
		InvMgrFacilityTourPage page = InvMgrFacilityTourPage.getInstance();

		logger.info("Search combo tour.");

		page.searchTour(status, searchType, searchValue);
		page.waitLoading();

	}

	/**
	 * go to combo tour details page from facility details page.
	 * 
	 * @param invInfo
	 * @return
	 */
	public String gotoComboTourDetailsPage(InventoryInfo invInfo) {
		InvMgrTopMenuPage top = InvMgrTopMenuPage.getInstance();
		InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
		InvMgrComboTourDetailsPage details = InvMgrComboTourDetailsPage
				.getInstance();

		logger.info("Go to combo tour details page.");
		top.gotoSpecifiedDetailPage("Tour Set-up");
		facTour.waitLoading();
		facTour.clickComboTour();
		facTour.waitLoading();
		facTour.searchTour(invInfo.tourStatus, invInfo.tourType,
				invInfo.tourValue);
		invInfo.tourCode = facTour.selectTourByName(invInfo.tourName);
		details.waitLoading();

		return invInfo.tourCode;
	}
	
	/**
	 * go to combo tour details page from facility details page.
	 * @param comboTourCode
	 */
	public void gotoComboTourDetailsPgFromFacilityTourPgByCode(String comboTourCode) {
		InvMgrTopMenuPage top = InvMgrTopMenuPage.getInstance();
		InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
		InvMgrComboTourDetailsPage details = InvMgrComboTourDetailsPage
				.getInstance();

		logger.info("Go to combo tour details page.");
		top.gotoSpecifiedDetailPage("Tour Set-up");
		facTour.waitLoading();
		facTour.clickComboTour();
		facTour.waitLoading();
		facTour.searchTour("All", "Tour Code", comboTourCode);
		facTour.selectTourByCode(comboTourCode);
		details.waitLoading();
	}
	
	/**
	 * go to combo tour details page from facility details page By tour name.
	 * @param comboTourName
	 */
	public void gotoComboTourDetailsPgFromFacilityTourPgByName(String comboTourName) {
		InvMgrTopMenuPage top = InvMgrTopMenuPage.getInstance();
		InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
		InvMgrComboTourDetailsPage details = InvMgrComboTourDetailsPage
				.getInstance();

		logger.info("Go to combo tour details page.");
		top.gotoSpecifiedDetailPage("Tour Set-up");
		facTour.waitLoading();
		facTour.clickComboTour();
		facTour.waitLoading();
		facTour.searchTour("All", "Tour Name", comboTourName);
		facTour.selectTourByName(comboTourName);
		details.waitLoading();
	}

	/**
	 * go to combo tour search page from facility details page
	 */
	public void gotoComboTourSearchPage() {
		InvMgrTopMenuPage top = InvMgrTopMenuPage.getInstance();
		InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
		InvMgrComboTourPage comboTourPg=InvMgrComboTourPage.getInstance();
		logger.info("Go to combo tour search page.");

		top.gotoSpecifiedDetailPage("Tour Set-up");
		facTour.waitLoading();
		facTour.clickComboTour();
		comboTourPg.waitLoading();
	}

	/**
	 * goto Tour Tiket Page From Facility Datial Page
	 */
	public void gotoTourTicketPage(InventoryInfo invInfo) {
		InvMgrTourDetailsPage tourDetail = InvMgrTourDetailsPage.getInstance();
		InvMgrTourTicketsPage ticketPage = InvMgrTourTicketsPage.getInstance();

		logger.info("Go to tour ticket page.");
		this.gotoTourDetailsPg(invInfo);

		tourDetail.clickTourTickets();
		ticketPage.waitLoading();
	}

	/**
	 * goto combo tour ticket page from facility details page
	 * 
	 * @param invInfo
	 */
	public void gotoComboTourTiketPage(InventoryInfo invInfo) {
		InvMgrComboTourDetailsPage tourDetail = InvMgrComboTourDetailsPage
				.getInstance();
		InvMgrTourTicketsPage ticketPage = InvMgrTourTicketsPage.getInstance();

		logger.info("Go to combo tour ticket page.");
		this.gotoComboTourDetailsPage(invInfo);

		tourDetail.clickTourTickets();
		ticketPage.waitLoading();
	}

	/**
	 * goto Assign tours page from combo tour details page
	 */
	public void gotoAssignToursPage() {
		InvMgrComboTourDetailsPage details_page = InvMgrComboTourDetailsPage
				.getInstance();
		InvMgrAssignTourPage assignedTourpage = InvMgrAssignTourPage
				.getInstance();

		logger.info("Go to assign tour page.");
		details_page.clickAssignedTours();

		assignedTourpage.waitLoading();
	}

	/**
	 * search assigned tour in combo tour/assigned tours page
	 */
	public void searchAssignedTours(String status, String type, String value) {
		InvMgrAssignTourPage assignedTourpage = InvMgrAssignTourPage
				.getInstance();
		logger.info("Search assign tour");

		assignedTourpage.searchTour(status, type, value);
		assignedTourpage.waitLoading();
	}

	/**
	 * goto tour allocation page from tour datials page.
	 * 
	 * @param invInfo
	 */
	public void gotoTourAllocation(InventoryInfo invInfo) {
		InvMgrTourDetailsPage tourDetail = InvMgrTourDetailsPage.getInstance();
		InvMgrTourAllocationPage tourAllocationPg = InvMgrTourAllocationPage
				.getInstance();

		logger.info("Go to tour allocation page");

		this.gotoTourDetailsPg(invInfo);

		tourDetail.clickTourAllocation();
		tourAllocationPg.waitLoading();
	}

	/**
	 * verify assigned tour search result in assign tour page
	 * 
	 * @param type
	 *            Tour Code/Name/Type,Description
	 * @param value
	 *            the value inputed
	 * 
	 */

	public void verifyTourSearchInAssignTour(String type, String value) {
		InvMgrAssignTourPage page = InvMgrAssignTourPage.getInstance();
		List<TourInfo> list = page.parseToursDetailsTable();

		logger.info("Verify Tour Search");

		for (TourInfo tour : list) {
			if (type.equals("Tour Code") && tour.tourCode.equals(value)) {
				return;
			}
			if (type.equals("Tour Name") && tour.tourName.equals(value)) {
				return;
			}

			if (type.equals("Tour Type")) {
				if (!tour.tourType.equals(value)) {
					throw new ActionFailedException(
							"There is at least one tour type not" + value);
				}
			}

			if (type.equals("Description")) {
				if (!tour.description.equals(value)) {
					throw new ActionFailedException(
							"There is at least one tour description not equal with"
									+ value);
				}
			}
		}

		while (!page.isNextbuttonDisable()) {
			page.clickNextButton();
			list = page.parseToursDetailsTable();

			for (TourInfo tour : list) {
				if (type.equals("Tour Code") && tour.tourCode.equals(value)) {
					return;
				}
				if (type.equals("Tour Name") && tour.tourName.equals(value)) {
					return;
				}

				if (type.equals("Tour Type")) {
					if (!tour.tourType.equals(value)) {
						throw new ActionFailedException(
								"There is at least one tour type not" + value);
					}
				}

				if (type.equals("Description")) {
					if (!tour.description.equals(value)) {
						throw new ActionFailedException(
								"There is at least one tour description not equal with"
										+ value);
					}
				}
			}

		}

	}

	/**
	 * this keyword is used to edit tour ticket info in tour ticket page in
	 * inventory manager
	 * 
	 * @param tiket
	 */
	public void editTourTicket(TourTicket tiket) {
		InvMgrTourTicketsPage tourTicket = InvMgrTourTicketsPage.getInstance();

		logger.info("Edit Tour Ticket");
		//to do select Min/Max ticket number ticket type info and could add multip ticket min/max ticket number info
		if (!StringUtil.isEmpty(tiket.minIndiv)) {
			if(!tourTicket.isMinimumIndTicketExsting()){
				tourTicket.clickAddTicketTypeIndMin();
				tourTicket.waitLoading();
			}
			tourTicket.setMinimumIndTicket(tiket.minIndiv);
		}
		// update max ticket to a different num
		if (!StringUtil.isEmpty(tiket.maxIndiv)) {
			if(!tourTicket.isMaxmumIndTicketExsting()){
				tourTicket.clickAddTicketTypeIndMax();
				tourTicket.waitLoading();
			}
			tourTicket.setMaximumIndTicket(tiket.maxIndiv);
		}
		
		if (!StringUtil.isEmpty(tiket.minOrg)) {
			if(!tourTicket.isMinimumOrgTicketExsting()){
				tourTicket.clickAddTicketTypeOrgMin();
				tourTicket.waitLoading();
			}
			tourTicket.setMinimumOrgTicket(tiket.minOrg);
		}
		
		if (!StringUtil.isEmpty(tiket.maxOrg)) {
			if(!tourTicket.isMaxmumOrgTicketExsting()){
				tourTicket.clickAddTicketTypeOrgMax();
				tourTicket.waitLoading();
			}
			tourTicket.setMaximumOrgTicket(tiket.maxOrg);
		}
		
		if (tiket.indv_types != null && tiket.indv_types.size() > 0) {
			for (int i = 0; i < tiket.indv_types.size(); i++) {
				tourTicket.clickAddTicketType();
				tourTicket.waitLoading();
				tourTicket
						.selectTicketType_Ind(tiket.indv_types.get(i).type, i);
				if (tiket.indv_types.get(i).active) {
					tourTicket.selectActiveCheckBox("ind", i);
				} else {
					tourTicket.unCheckActive("ind", i);
				}

				if (tiket.indv_types.get(i).field_visible) {
					tourTicket.selectFieldVisibleCheckBox("ind", i);
				} else {
					tourTicket.unCheckFieldVisible("ind", i);
				}

				if (tiket.indv_types.get(i).call_center_visible) {
					tourTicket.selectCallCenterVisibleCheckBox("ind", i);
				} else {
					tourTicket.unCheckCallCenter("ind", i);
				}

				if (tiket.indv_types.get(i).web_visible) {
					tourTicket.selectWebVisibleCheckBox("ind", i);
				} else {
					tourTicket.unCheckWebVisible("ind", i);
				}

				if (tiket.indv_types.get(i).id_required) {
					tourTicket.selectIDRequiredCheckBox("ind", i);
				} else {
					tourTicket.unCheckIDRequired("ind", i);
				}
			}
		}

		if (tiket.org_types != null && tiket.org_types.size() > 0) {
			for (int i = 0; i < tiket.org_types.size(); i++) {
				tourTicket.clickAddOrganType();
				tourTicket.waitLoading();
				tourTicket.selectTicketType_Org(tiket.org_types.get(i).type, i);
				if (tiket.org_types.get(i).active) {
					tourTicket.selectActiveCheckBox("org", i);
				} else {
					tourTicket.unCheckActive("org", i);
				}

				if (tiket.org_types.get(i).field_visible) {
					tourTicket.selectFieldVisibleCheckBox("org", i);
				} else {
					tourTicket.unCheckFieldVisible("org", i);
				}

				if (tiket.org_types.get(i).call_center_visible) {
					tourTicket.selectCallCenterVisibleCheckBox("org", i);
				} else {
					tourTicket.unCheckCallCenter("org", i);
				}

				if (tiket.org_types.get(i).web_visible) {
					tourTicket.selectWebVisibleCheckBox("org", i);
				} else {
					tourTicket.unCheckWebVisible("org", i);
				}

				if (tiket.org_types.get(i).id_required) {
					tourTicket.selectIDRequiredCheckBox("org", i);
				} else {
					tourTicket.unCheckIDRequired("org", i);
				}
			}
		}

		tourTicket.waitLoading();
		tourTicket.clickApply();
		ajax.waitLoading();
		tourTicket.waitLoading();

	}

	/**
	 * verify error message in tour ticket page,if the message is not the same
	 * as msg,throw exception.
	 * 
	 * @param msg
	 *            the expected message
	 */
	public void verifyTourTicketErrorMessageExist(String msg) {
		InvMgrTourTicketsPage tourTicket = InvMgrTourTicketsPage.getInstance();
		logger.info("Verify Tour Ticket Error Message");

		List<String> list = tourTicket.getErrorMessage();
		if (list.size() > 0) {
			if (list.contains(msg)) {
				logger.info("'" + msg + "'  is existent !~");
				return;
			}
		}
		throw new ActionFailedException("'" + msg + "' is not existend ~!");

	}

	/**
	 * Set tour details in tour datials page
	 * 
	 * @param tour
	 */
	public void setTour(TourInfo tour) {
		InvMgrTourDetailsPage tourDetail = InvMgrTourDetailsPage.getInstance();
		InvMgrFacilityTourPage facilitypage = InvMgrFacilityTourPage
				.getInstance();
		logger.info("Set tour information");
		tourDetail.setTour(tour);
		tourDetail.clickOK();
		browser.waitExists(facilitypage, tourDetail);
	}

	/**
	 * this keyword is for getting info from tour details page and just in
	 * details page.
	 * 
	 * @return
	 */
	public TourInfo getTour() {
		InvMgrTourDetailsPage tourDetail = InvMgrTourDetailsPage.getInstance();
		logger.info("Get tour information");

		return tourDetail.getTour();
	}

	/**
	 * go to tour set up page from facility details page
	 */
	public void gotoTourSetUpPg() {
		InvMgrTopMenuPage top = InvMgrTopMenuPage.getInstance();
		InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
		InvMgrFacilityDetailPage facDetailsPg = InvMgrFacilityDetailPage
				.getInstance();

		logger.info("Go to tour setup page.");
		if (top.checkSpecificDetailsPageExist("Tour Set-up")) {
			top.gotoSpecifiedDetailPage("Tour Set-up");
		} else {
			facDetailsPg.selectCategory("Ticket");
			top.gotoSpecifiedDetailPage("Tour Set-up");
		}

		facTour.waitLoading();
	}

	/**
	 * the process of adding combo tour
	 * 
	 * From:falicility tour page To:tour details page End:falicility tour page
	 * 
	 * @param tour
	 */
	public void addComboTour(TourInfo tour) {
		InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
		InvMgrComboTourPage invComboPg = InvMgrComboTourPage.getInstance();
		InvMgrComboTourDetailsPage invTourDetailsPg = InvMgrComboTourDetailsPage
				.getInstance();

		logger.info("Add a new combo tour");
		if(!invComboPg.exists()){
			facTour.clickComboTour();
			invComboPg.waitLoading();
		}

		invComboPg.clickAddNewComboTour();
		invTourDetailsPg.waitLoading();
		invTourDetailsPg.setComboTour(tour);
		invTourDetailsPg.clickOk();
		browser.waitExists(invComboPg, invTourDetailsPg);
	}

	/** log out inventory manager. */
	public void logoutInvManager() {
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		OrmsHomePage omHmPg = OrmsHomePage.getInstance();

		logger.info("Logout Inventory Manager.");
		topMenu.clickSignOut();
		omHmPg.waitLoading();
	}

	/**
	 * This method will enter BookingRules Page from the Inventory Manager top
	 * menu bar It starts from Inventory manager top menu bar and ends at
	 * Seasons page This top menu bar must have the page_name dropdown list
	 */
	public void gotoSeasonsPg() {
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();
		logger.info("Go to seasons page.");

		topMenu.gotoSpecifiedDetailPage("Booking Rules");
		seasonPg.waitLoading();
	}

	/**
	 * The method execute the process going to season detail page from season
	 * page
	 * 
	 * @param seasonID
	 * @param isNew
	 */
	public void gotoseasonDetailPg(String seasonID, boolean isNew) {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrSeasonDetailPage inSeasonDetailPg = InvMgrSeasonDetailPage
				.getInstance();
		logger.info("Go to season detail page.");

		if (!seasonPg.exists()) {
			gotoSeasonsPg();
		}
		if (isNew) {
			seasonPg.clickAddNew();
		} else {
			seasonPg.gotoSeasonDetails(seasonID);
		}
		inSeasonDetailPg.waitLoading();
	}

	/**
	 * This method execute going to closure detail page from season page or
	 * closure page.
	 * 
	 * @param closureID
	 * @param isNew
	 */

	public void gotoClosureDetaiPg(String closureID, boolean isNew) {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrClosureDetailPage closureDetailPg = InvMgrClosureDetailPage
				.getInstance();
		InvMgrClosuresPage closurePg = InvMgrClosuresPage.getInstance();

		logger.info("Go to closure detail page.");
		if (!closurePg.exists()) {
			if (!seasonPg.exists()) {
				this.gotoSeasonsPg();
			}
			seasonPg.clickClosures();
			closurePg.waitLoading();
		}

		if (isNew) {
			closurePg.clickAddNew();
		} else {
			closurePg.searchClosure(closureID);
			closurePg.clickClosureID(closureID);
		}

		closureDetailPg.waitLoading();
	}

	/**
	 * Go to the camping unit page
	 * 
	 */
	public void gotoCampingUnitPg() {
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		InvMgrCampingUnitPage invCampingPg = InvMgrCampingUnitPage
				.getInstance();
		InvMgrCampingUnitDetailsPage inCampDetailsPg = InvMgrCampingUnitDetailsPage
				.getInstance();
		InvMgrCampingUnitSitesPage inCampSitesPg = InvMgrCampingUnitSitesPage
				.getInstance();
		InvMgrCampingUnitLoopsPage inCampLoopsPg = InvMgrCampingUnitLoopsPage
				.getInstance();

		logger.info("Go to camping unit page");
		String defaultPageName = topMenu.getDefaultPageName();
		if (defaultPageName.equals("Camping Units")) {
			if (inCampDetailsPg.exists()) {
				inCampDetailsPg.clickCampingUnitsTab();
			} else if (inCampSitesPg.exists()) {
				inCampSitesPg.clickCamplingUnitsTab();
			} else if (inCampLoopsPg.exists()) {
				inCampLoopsPg.clickCamplingUnitsTab();
			}
		} else {
			topMenu.gotoSpecifiedDetailPage("Camping Units");
		}

		invCampingPg.waitLoading();
	}

	public void gotoInventoryPg() {
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		InvMgrFacilityInventoryPage invInvenPg = InvMgrFacilityInventoryPage
				.getInstance();

		logger.info("Go to inventory page");
		topMenu.gotoSpecifiedDetailPage("Inventory");
		invInvenPg.waitLoading();
	}
	
	/**
	 * This method is used to go to slip inventory list page
	 */
	public void gotoSlipInventoryListPg(){
		InvMgrFacilityInventoryPage invInvenPg = InvMgrFacilityInventoryPage
				.getInstance();
		InvMgrSlipInventoryListPage slipInvPg = InvMgrSlipInventoryListPage.getInstance();
		gotoInventoryPg();
		invInvenPg.clickSlipInventoryListLabel();
		ajax.waitLoading();
		slipInvPg.waitLoading();
	}

	public void gotoServicePage() {
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		InvMgrServiceActivitiesPage invServicePg = InvMgrServiceActivitiesPage
				.getInstance();

		logger.info("Go to Services and Activities page.");
		topMenu.gotoSpecifiedDetailPage("Services, Activities, Events");
		invServicePg.waitLoading();
	}

	/**
	 * Go to Evnet list page
	 */
	public void gotoEventsPg() {
		InvMgrServiceActivitiesPage invServicePg = InvMgrServiceActivitiesPage
				.getInstance();
		InvMgrEventsPage imEventPg = InvMgrEventsPage.getInstance();

		logger.info("Go to event page.");
		if (!imEventPg.exists()) {
			this.gotoServicePage();
		}

		invServicePg.clickEvent();
		imEventPg.waitLoading();
	}

	/**
	 * Add the new event
	 * 
	 * @param event
	 */
	public String addEvent(EventInfo event) {
		InvMgrServiceActivitiesPage invServicePg = InvMgrServiceActivitiesPage
				.getInstance();
		InvMgrEventsPage invEventPg = InvMgrEventsPage.getInstance();
		InvMgrEventDetailPage invEventDetailsPg = InvMgrEventDetailPage
				.getInstance();

		logger.info("Add a event.");
		invServicePg.clickEvent();
		invEventPg.waitLoading();

		invEventPg.clickAddNewEvent();
		invEventDetailsPg.waitLoading();

		invEventDetailsPg.setEventInfo(event);
		invEventDetailsPg.clickApply();
		invEventDetailsPg.waitLoading();

		String eventID = invEventDetailsPg.getEventID();
		invEventDetailsPg.clickOK();
		invEventPg.waitLoading();

		return eventID;
	}

	/**
	 * Assign service to facility
	 * 
	 * @param service
	 */
	public void assignServiceToFacility(String service) {
		InvMgrServiceActivitiesPage invServicePg = InvMgrServiceActivitiesPage
				.getInstance();

		logger.info("Assign Service to Facility");
//		invServicePg.searchBy("Name");
//		invServicePg.setSearchValue(service);
//		invServicePg.clickSearch();
//
//		invServicePg.waitLoading();
		invServicePg.searchServiceOrActivities("Name", service, null, null, null);
		invServicePg.selectAll();
		invServicePg.clickAssign();

		invServicePg.waitLoading();

	}
	
	public void unAssignServiceToFacilityByType(String type){
		InvMgrServiceActivitiesPage invServicePg = InvMgrServiceActivitiesPage
				.getInstance();
//		OrmsConfirmDialogWidget confirmDialogPg= OrmsConfirmDialogWidget.getInstance();
//		ConfirmationDialogWidget confirmDialogPg = new ConfirmationDialogWidget();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		logger.info("Assign Service to Facility");
		invServicePg.searchServiceOrActivities(null, null, "Services", type, "Assigned");
		invServicePg.selectAll();
		invServicePg.clickRemove();
		ajax.waitLoading();
		if(confirmDialogPg.exists()){
			confirmDialogPg.clickOK();
	  		ajax.waitLoading();
	  	}
		invServicePg.waitLoading();
	}

	/**
	 * the process of add camping unit combo
	 * 
	 * @param camp
	 * @return
	 */
	public String addCampingUnitCombo(CampingUnit camp) {
		InvMgrCampingUnitPage invCampingPg = InvMgrCampingUnitPage
				.getInstance();
		InvMgrCampingUnitDetailsPage invCampDetailsPg = InvMgrCampingUnitDetailsPage
				.getInstance();
		InvMgrChangeReqItemDetail changeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		InvMgrCampingUnitLoopsPage campUnitLoopsPg = InvMgrCampingUnitLoopsPage
				.getInstance();

		logger.info("Add new camping units");

		if (!invCampDetailsPg.exists()) {
			if (!invCampingPg.exists()) {
				gotoCampingUnitPg();
			}
			invCampingPg.clickAddNewCombo();
			invCampDetailsPg.waitLoading();
		}

		invCampDetailsPg.setupCampingUnit(camp);
		invCampDetailsPg.clickApply();
		Browser.sleep(SHORT_SLEEP_BEFORE_CHECK);
		Object page = browser.waitExists(invCampDetailsPg, changeReqItemDetail);

		if (page == invCampDetailsPg) {
			camp.comboID = invCampDetailsPg.getComboID();

			// Assign sites to camping units
			this.gotocampingUnitSitesPg(camp.comboID);
			this.assignOrRemoveSitesAssociatedWithCampUnits("Assign", camp.campUnitSitesNames);

			// Assign loops to camping units
			invCampDetailsPg.clickLoops();
			campUnitLoopsPg.waitLoading();
			this.assignOrRemoveLoopsAssociatedWithCampUnits("Assign",
					camp.comboID, camp.campUnitLoopsIDs);

			campUnitLoopsPg.clickCampUnitComboDetails();
			ajax.waitLoading();
			invCampDetailsPg.waitLoading();

			invCampDetailsPg.clickOK();
			invCampingPg.waitLoading();
		}

		return camp.comboID;
	}

	/**
	 * Update camp unit details data
	 * 
	 * @param camp
	 */
	public void updateCampUnitDetails(CampingUnit camp) {
		InvMgrCampingUnitDetailsPage invCampDetailsPg = InvMgrCampingUnitDetailsPage
				.getInstance();
		InvMgrChangeReqItemDetail changeReqItemDetailsPg = InvMgrChangeReqItemDetail
				.getInstance();

		invCampDetailsPg.setupCampingUnit(camp);
		invCampDetailsPg.clickApply();

		browser.waitExists(invCampDetailsPg, changeReqItemDetailsPg);
	}

	/**
	 * Update exist camping units in camping units details page
	 * 
	 * @param camp
	 * @return comboID
	 */
	public String updateExistCampingUnits(CampingUnit camp) {
		InvMgrCampingUnitPage invCampingPg = InvMgrCampingUnitPage
				.getInstance();
		InvMgrCampingUnitDetailsPage invCampDetailsPg = InvMgrCampingUnitDetailsPage
				.getInstance();
		InvMgrChangeReqItemDetail changeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrConfimActionPage confimActionPg = InvMgrConfimActionPage
				.getInstance();
		String comboID = "";

		logger.info("Update exist camping units");

		if (!invCampDetailsPg.exists()) {
			if (!invCampingPg.exists()) {
				gotoCampingUnitPg();
			}
			invCampingPg.clickComboID(camp.comboID);
			invCampDetailsPg.waitLoading();
		}

		invCampDetailsPg.setupCampingUnit(camp);
		invCampDetailsPg.clickApply();

		Object page = browser.waitExists(confimActionPg, invCampDetailsPg,
				confirmDialogPg, changeReqItemDetail);

		if (page.equals(invCampDetailsPg)) {
			comboID = invCampDetailsPg.getComboID();
		}

		return comboID;
	}

	/**
	 * This method will do activate/deactivate/delete seasons operation
	 * 
	 * @param seasonIDs
	 *            - the array of season IDs
	 * @param command
	 *            - can be one of "Activate", "Deactivate", "Delete"
	 */
	public void operateSeasons(String[] seasonIDs, String command) {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();
//		InvMgrConfimActionPage confirmActionPg = InvMgrConfimActionPage
//				.getInstance();
		OrmsConfirmDialogWidget confirmActionPg = OrmsConfirmDialogWidget.getInstance();
		logger.info(command + " seasons.");

		if (seasonIDs == null || seasonIDs.length < 1) {
			return;
		}

		seasonPg.selectSeasonsCheckBoxByIDs(seasonIDs);
		if (command.equalsIgnoreCase("Activate")) {
			seasonPg.clickActivate();
		} else if (command.equalsIgnoreCase("Deactivate")) {
			seasonPg.clickDeactivate();
			confirmActionPg.waitLoading();
			confirmActionPg.clickOK();
			String msg = seasonPg.getStatusMessage();
			if (msg.matches("(Deactivation|Deletion) of season schedule \\d+ is not allowed\\. .*")) {
				throw new ItemNotFoundException("Error: " + msg);
			}

		} else if (command.equalsIgnoreCase("Delete")) {
			seasonPg.clickDelete();
			confirmActionPg.waitLoading();
			confirmActionPg.clickOK();
			seasonPg.waitLoading();
			String msg = seasonPg.getStatusMessage();
			if (msg.matches("(Deactivation|Deletion) of season schedule \\d+ is not allowed\\.")) {
				throw new ItemNotFoundException("Error: " + msg);
			}
		} else {
			throw new ItemNotFoundException(
					"Unknown season operation command: " + command
							+ ". Must be one of Activate, Deactivate or Delete");
		}
		seasonPg.waitLoading();
	}
	
	public String addNewSeason(SeasonData season) {
		return addNewSeason(season, true);
	}
	
	/**
	 * This method was used to create a new season, start from InvMgrFacilityBookingRulesPage, and ends at InvMgrSeasonDetailPage
	 * Please use assignSiteOrSlipToSeason method to assign site/slip to season
	 * and use activeSeasonByID method to active/deactive season
	 * @param season
	 * @return
	 */
	public String newSeason(SeasonData season) {
		InvMgrSeasonDetailPage seasonDetailsPg = InvMgrSeasonDetailPage.getInstance();
		String seasonID = "";

		logger.info("Add a new season");
		this.gotoAddNewSeasonDetailsPage(season.m_PrdCategory);
		// Set up season data in Season Details page
		seasonDetailsPg.setUpSeason(season);
		seasonDetailsPg.clickApply();
		ajax.waitLoading();
		seasonDetailsPg.waitLoading();
		seasonID = seasonDetailsPg.getSeasonID();
		
		if (StringUtil.isEmpty(seasonID)||!seasonID.matches("\\d+"))
			throw new ItemNotFoundException("Failed to create the new season.");
		
		return seasonID;
	}
	
	/**
	 * This method was used to assign site/slip to season
	 * Starts at InvMgrSeasonDetailPage, and ends at InvMgrFacilityBookingRulesPage
	 * @param season
	 */
	public void assignSiteOrSlipToSeason(SeasonData season) {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage.getInstance();
		InvMgrSeasonDetailPage seasonDetailsPg = InvMgrSeasonDetailPage.getInstance();
		InvMgrSeasonSitesPage seasonSitesPg = InvMgrSeasonSitesPage.getInstance();
		InvMgrSeasonSlipsPage seasonSlipsPg = InvMgrSeasonSlipsPage.getInstance();
		
		if(season.m_PrdCategory.equalsIgnoreCase("Site")) {
			seasonDetailsPg.clickSeasonSitesTab();
			seasonSitesPg.waitLoading();

			seasonSitesPg.searchSites(season.prd_num, "NOT Assigned", season.m_Loop);
			seasonSitesPg.waitLoading();

			// Assign all or partial sites to specific season
			if (season.assignAll) {
				seasonSitesPg.selectAll();
			} else {
				seasonSitesPg.selectSiteCheckBoxBySiteIDs(season.siteIds);
			}

			seasonSitesPg.clickAssign();
			seasonSitesPg.waitLoading();
			seasonSitesPg.clickSeasonsTab();
		}else if(season.m_PrdCategory.equalsIgnoreCase("Slip")) {
			seasonDetailsPg.clickSeasonSlipsTab();
			seasonSlipsPg.waitLoading();
			seasonSlipsPg.assignSlipByNumber(season.prd_num, season.m_Slip_ResPeriodType, season.m_Slip_Shared,season.m_Loop);
			seasonSlipsPg.clickSeasonsTab();
		}else {
			throw new ErrorOnDataException("Un-handled season product category. Check your data.");
		}
		seasonPg.waitLoading();
		
	}
	
	/**
	 * This method was used to active/deactive a season by ID
	 * @param seasonID
	 * @param seasonType
	 * @param active
	 */
	public void activeOrDeactiveSeasonByID(String seasonID, String seasonType, boolean active) {
		InvMgrFacilityBookingRulesPage seasonPage = InvMgrFacilityBookingRulesPage.getInstance();
		
		logger.info(active==true?"Active":"Deactive"+" season by ID "+seasonID);
		seasonPage.selectSeasonType(seasonType);
		if(active) {
			seasonPage.selectSearchActive("Inactive");
			seasonPage.clickSearch();
			ajax.waitLoading();
			seasonPage.waitLoading();
			
			seasonPage.activateSeason(seasonID);
			seasonPage.waitLoading();
		}else {
			seasonPage.selectSearchActive("Active");
			seasonPage.clickSearch();
			ajax.waitLoading();
			seasonPage.waitLoading();
			
			seasonPage.deactivateSeason(seasonID);
			seasonPage.waitLoading();
		}
	}

	public void gotoAddNewSeasonDetailsPage(String productCategory) {
		InvMgrFacilityBookingRulesPage seasonPage = InvMgrFacilityBookingRulesPage.getInstance();
		InvMgrSeasonDetailPage seasonDetailsPage = InvMgrSeasonDetailPage.getInstance();
		
		logger.info("Goto add new season details page.");
		seasonPage.clickAddNew();
		seasonDetailsPage.waitLoading();
		if(seasonDetailsPage.isPrdCategoryExisted()) {
			seasonDetailsPage.selectPrdCategory(productCategory);
			seasonDetailsPage.waitLoading();
		}
	}
	
	/**
	 * Add new season, and assgin production according to assigned parameter
	 * @param season
	 * @param assgined
	 * @return
	 */
	public String addNewSeason(SeasonData season, boolean assgined) {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrSeasonDetailPage seasonDetailsPg = InvMgrSeasonDetailPage
				.getInstance();
		InvMgrSeasonSitesPage seasonSitesPg = InvMgrSeasonSitesPage
				.getInstance();
		InvMgrSeasonSlipsPage seasonSlipsPg = InvMgrSeasonSlipsPage
				.getInstance();
		InvMgrChangeReqItemDetail imChnageReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		String seasonID = "";

		logger.info("Add a new season");
		this.gotoAddNewSeasonDetailsPage(season.m_PrdCategory);
		// Set up season data in Season Details page
		seasonDetailsPg.setUpSeason(season);
		seasonDetailsPg.clickApply();
		ajax.waitLoading();
		Object page = browser.waitExists(seasonDetailsPg, imChnageReqItemDetailPg);

		// If Season Details page exists, get assign season sites and get season id
		if (page==seasonDetailsPg) {
			seasonID = seasonDetailsPg.getSeasonID();
			
			if (StringUtil.isEmpty(seasonID)||!seasonID.matches("\\d+"))
				throw new ItemNotFoundException("Failed to create the new season.");

			if(!assgined) {
				seasonDetailsPg.clickOK();
				ajax.waitLoading();
				return seasonID;
			}
			
			if(season.m_PrdCategory.equalsIgnoreCase("Site")) {
				seasonDetailsPg.clickSeasonSitesTab();
				seasonSitesPg.waitLoading();

				seasonSitesPg.searchSites(season.prd_num, "NOT Assigned", season.m_Loop);
				seasonSitesPg.waitLoading();

				// Assign all or partial sites to specific season
				if (season.assignAll) {
					seasonSitesPg.selectAll();
				} else {
					seasonSitesPg.selectSiteCheckBoxBySiteIDs(season.siteIds);
				}

				seasonSitesPg.clickAssign();
				seasonSitesPg.waitLoading();
				seasonSitesPg.clickSeasonsTab();
			}else if(season.m_PrdCategory.equalsIgnoreCase("Slip")) {
				seasonDetailsPg.clickSeasonSlipsTab();
				seasonSlipsPg.waitLoading();
				seasonSlipsPg.assignSlipByNumber(season.prd_num, season.m_Slip_ResPeriodType, season.m_Slip_Shared,season.m_Loop);
				seasonSlipsPg.clickSeasonsTab();
			}else {
				throw new ErrorOnDataException("Un-handled season product category. Check your data.");
			}
			
			seasonPg.waitLoading();
			// Activate or deactivate season
			if (season.isActiveSeason) {
				season.m_searchStatus = "Inactive";
				seasonPg.searchSeason(season);
				seasonPg.activateSeason(seasonID);
				seasonPg.waitLoading();
				season.m_searchStatus = "Active";
			}

			logger.info("A new season#" + seasonID + " is created.");
		}

		return seasonID;
	}

	public void activateSeasonInDetailsPage() {
		InvMgrSeasonDetailPage detailsPage = InvMgrSeasonDetailPage.getInstance();
		InvMgrSeasonSlipsPage seasonSlipsPage = InvMgrSeasonSlipsPage.getInstance();
		InvMgrSeasonSitesPage seasonSitesPage = InvMgrSeasonSitesPage.getInstance();
		
		logger.info("Activate season in details page.");
		if(seasonSlipsPage.exists()) {
			seasonSlipsPage.clickSeasonDetailsTab();
		} else if(seasonSitesPage.exists()) {
			seasonSitesPage.clickSeasonDetailsTab();
		}
		ajax.waitLoading();
		detailsPage.waitLoading();
		detailsPage.selectActiveCheckBox();
		detailsPage.clickApply();
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	/**
	 * Get how many seasons in season page
	 * 
	 * @param season
	 * @return
	 */
	public int getSeasonNum(SeasonData season) {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();

		logger.info("Check season exist in seasons page");

		seasonPg.searchSeason(season);
		return seasonPg.getSeasonNum();
	}

	/**
	 * Get season id for specific season in season page
	 * 
	 * @param season
	 * @return
	 */
	public String getSeasonID(SeasonData season) {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();

		logger.info("Check season exist in seasons page");
		seasonPg.searchSeason(season);
		return seasonPg.getSeasonID();
	}
	
	public String getClosureType(String closuresID){
		InvMgrClosuresPage inClosurePg = InvMgrClosuresPage.getInstance();
		inClosurePg.searchClosure(closuresID);
	    return inClosurePg.getClosureType();
	}

	/**
	 * Get sites(Non Site Specific Group IDs
	 * 
	 * @param siteCodes
	 * @return
	 */
	public String[] getSiteIDs(String[] siteCodes, OrmsPage startPage) {
		InvMgrNonSiteSpecGroupPage imNonSiteSpecificGroupPg = InvMgrNonSiteSpecGroupPage
				.getInstance();
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();

		String[] siteIDs = new String[siteCodes.length];
		for (int i = 0; i < siteCodes.length; i++) {
			if (startPage == imNonSiteSpecificGroupPg) {
				imNonSiteSpecificGroupPg.searchSite("Site Code", siteCodes[i]);
				siteIDs[i] = imNonSiteSpecificGroupPg.getSiteID(siteCodes[i]);
			} else if (startPage == invSitePg) {
				invSitePg.searchSite("Site Code", siteCodes[i]);
				siteIDs[i] = invSitePg.getSiteID(siteCodes[i]);
			} else
				throw new PageNotFoundException("Start page --- " + startPage
						+ " --- can't find.");
		}

		return siteIDs;
	}

	/**
	 * Update exist season in season detail page
	 * 
	 * @param season
	 * @param isNew
	 * @return
	 */
	public String updateExistSeason(SeasonData season, boolean isNew) {
		InvMgrSeasonDetailPage seasonDetailsPg = InvMgrSeasonDetailPage
				.getInstance();
		InvMgrConfimActionPage confimActionPg = InvMgrConfimActionPage
				.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
		.getInstance();
		String seasonID = "";

		logger.info("Modify a exist season.");

		if (!seasonDetailsPg.exists()) {
			gotoseasonDetailPg(season.m_SeasonID, isNew);
		}

		seasonDetailsPg.fillSeasonDetails(season);
		seasonDetailsPg.clickOK();

		browser.waitExists(confirmWidget, confimActionPg, seasonDetailsPg, confirmDialogPg,
				inChangeReqItemDetailPg);
		if (confirmWidget.exists()) {
			confirmWidget.clickOK();
			browser.waitExists(seasonPg,seasonDetailsPg, inChangeReqItemDetailPg,
					confirmDialogPg);
			if (seasonDetailsPg.exists()) {
				seasonID = seasonDetailsPg.getSeasonID();
			}
		}else{
		
			if (confimActionPg.exists()) {
				confimActionPg.clickOK();
				browser.waitExists(seasonDetailsPg, inChangeReqItemDetailPg,
						confirmDialogPg);
				if (seasonDetailsPg.exists()) {
					seasonID = seasonDetailsPg.getSeasonID();
				}
			}
		}

		return seasonID;
	}

	/**
	 * This method will enter Inventory Manager home page, which is also the
	 * select facility page It will starts from top menu bar and ends at the
	 * home page
	 */
	public void gotoInventoryHomePg() {
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		InvMgrHomePage homePg = InvMgrHomePage.getInstance();
		InvMgrWarehouseSearchPage whouseSearchPg = InvMgrWarehouseSearchPage.getInstance();

		logger.info("Go to inventory home page");
		topMenu.clickHome();
		
		Object o = browser.waitExists(whouseSearchPg,homePg);
		if(o instanceof InvMgrWarehouseSearchPage)
		{
			whouseSearchPg.clickHome();
			homePg.waitLoading();
		}
		
		
	}

	/** Go to booking rule page */
	public void goToBookingRulePg() {
		InvMgrFacilityBookingRulesPage invFacBookRulePg = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrTopMenuPage invTpMenuPg = InvMgrTopMenuPage.getInstance();

		logger.info("Go to booking rule detail page");

		invTpMenuPg.gotoSpecifiedDetailPage("Booking Rules");
		invFacBookRulePg.waitLoading();
	}

	/** Go to Loop/Site Set-up page */
	public void goToLoopOrSiteSetUpPg() {
		InvMgrLoopAreasPage invLoopAreasPg = InvMgrLoopAreasPage.getInstance();
		InvMgrTopMenuPage invTpMenuPg = InvMgrTopMenuPage.getInstance();

		logger.info("Loop/Site Set-up detail page");

		invTpMenuPg.gotoSpecifiedDetailPage("Loop/Site Set-up");
		invLoopAreasPg.waitLoading();
	}

	public void addClosureForSlip(Closure closure, boolean isSite){
		if(isSite){
			this.addClosure(closure);
		} else {// slip closure
			
		}
	}
	
	/**
	 * Add site closure
	 * @param closure
	 * @return
	 */
	public String addClosure(Closure closure){
		return this.addClosure(closure, true);
	}
	
	/**
	 * Add slip closure
	 * @param closure
	 * @return
	 */
	public String addClosureForSlip(Closure closure){
		return this.addClosure(closure, false);
	}
	
	/**
	 * Add closure for site and slip
	 * 
	 * @param closue
	 * @param isSite
	 * @return
	 */
	public String addClosure(Closure closure, boolean isSite) {
		InvMgrFacilityBookingRulesPage invFacBookRulePg = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrClosureDetailPage invClosureDetailPg = InvMgrClosureDetailPage
				.getInstance();
		InvMgrTopMenuPage invTpMenuPg = InvMgrTopMenuPage.getInstance();
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		InvMgrClosuresSitesPage inClosureSitesPg = InvMgrClosuresSitesPage
				.getInstance();
		InvMgrChangeReqItemDetail imChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		String closureID = "";

		logger.info("Add a new closure.");

		if (!invClosurePg.exists()) {
			if (!invFacBookRulePg.exists()) {
				invTpMenuPg.gotoSpecifiedDetailPage("Booking Rules");
				invFacBookRulePg.waitLoading();
			}

			invFacBookRulePg.clickClosures();
			invClosurePg.waitLoading();
		}

		invClosurePg.clickAddNew();
		invClosureDetailPg.waitLoading();

		invClosureDetailPg.fillClosureDetails(closure);
		invClosureDetailPg.clickApply();
		invClosureDetailPg.waitLoading();
		
		closureID = invClosureDetailPg.getClosureID();
		invClosureDetailPg.clickOK();
		// Wait for two pages: InvMgrClosuresPage and InvMgrChangeReqItemDetail
		Object page = browser.waitExists(invClosurePg, imChangeReqItemDetailPg);

		if (page.equals(invClosurePg)) {
			this.gotoClosureSitesPg(closureID);
			
			inClosureSitesPg.searchByShow("NOT Assigned");
			
			// Assign all or partial sites to specific closure
			if (closure.assignAll) {
				inClosureSitesPg.selectAll();
				inClosureSitesPg.clickAssign();
				inClosureSitesPg.waitLoading();
			} else {
				if(isSite){
					if(closure.siteIds != null){
						inClosureSitesPg.assignSites(closure.siteIds);
					}
				} else {
					inClosureSitesPg.assignSlips(closure.slipCD);
				}
			}
			inClosureSitesPg.clickClosuresTab();
			invClosurePg.waitLoading();
			logger.info("A new closure#" + closureID + "is created.");
		}

		return closureID;
	}

	/**
	 * Update closure from Closure Detail page or Closure page
	 * 
	 * @param closure
	 */
	public void updateClosure(Closure closure) {
		InvMgrClosureDetailPage invClosureDetailPg = InvMgrClosureDetailPage
				.getInstance();
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		InvMgrChangeReqItemDetail imChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();

		logger.info("Update closure.");

		if (!invClosureDetailPg.exists()) {
			invClosurePg.searchClosure(closure.closureID);
			invClosurePg.clickClosureID(closure.closureID);
			invClosureDetailPg.waitLoading();
		}

		invClosureDetailPg.fillClosureDetails(closure);
		invClosureDetailPg.clickOK();

		browser.waitExists(invClosurePg, imChangeReqItemDetailPg);
	}

	/**
	 * Update Note or Alert from note and alert Detail page or note and alert
	 * list page
	 * 
	 * @param inventory
	 */
	public void updateNoteOrAlert(InventoryInfo inventory) {
		InvMgrNoteOrAlertDetailPage invAlertDetailsPg = InvMgrNoteOrAlertDetailPage
				.getInstance();
		InvMgrNoteAndAlertListPage invNoteAndAlertPg = InvMgrNoteAndAlertListPage
				.getInstance();
		InvMgrChangeReqItemDetail imChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();

		logger.info("Update Note or Alert.");

		if (!invAlertDetailsPg.exists()) {
			logger.info("Go to alter id:"+inventory.alertID+" details page.");
			invNoteAndAlertPg.clickNoteOrAlertID(inventory.alertID);
			invAlertDetailsPg.waitLoading();
		}

		invAlertDetailsPg.addNewAlert(inventory);

		browser.waitExists(invAlertDetailsPg, imChangeReqItemDetailPg);
	}

	public void gotoNoteOrAlertListFromDetailsPg() {
		InvMgrNoteOrAlertDetailPage invAlertDetailsPg = InvMgrNoteOrAlertDetailPage
				.getInstance();
		InvMgrNoteAndAlertListPage noteAndAlert = InvMgrNoteAndAlertListPage
				.getInstance();

		invAlertDetailsPg.clickOk();
		noteAndAlert.waitLoading();
	}
	
	/**
	 * Update Service Activity and Event details information
	 * 
	 * @param: EventInfo event
	 */
	public void updateServiceActivityEvent(EventInfo event) {
		InvMgrEventDetailPage inEventDetailPg = InvMgrEventDetailPage
				.getInstance();
		InvMgrChangeReqItemDetail imChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();

		logger.info("Update Service Activity and Event details.");

		if (!inEventDetailPg.exists()) {
			this.gotoServicesActivityEventDetailsPg(event.eventID, false);
		}

		inEventDetailPg.setEventInfo(event);
		inEventDetailPg.clickApply();

		browser.waitExists(SHORT_SLEEP_BEFORE_CHECK,inEventDetailPg, imChangeReqItemDetailPg);
	}

	/**
	 * Update Loop or Alert from Loop or Alert Detail page or Loop or Alert list
	 * page
	 * 
	 * @param inventory
	 */
	public void updateLoopOrArea(LoopInfoData loop) {
		InvMgrLoopDetailsPage invLoopDetailsPg = InvMgrLoopDetailsPage
				.getInstance();
		InvMgrChangeReqItemDetail imChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		InvMgrLoopAreasPage invLoopAreaPg = InvMgrLoopAreasPage.getInstance();

		logger.info("Update Loop or Area.");

		if (!invLoopDetailsPg.exists()) {
			gotoLoopAreaDetailsPage(loop.loopName, false);
		}

		invLoopDetailsPg.updateLoopInfo(loop);

		browser.waitExists(invLoopDetailsPg, imChangeReqItemDetailPg,
				invLoopAreaPg);
	}

	/**
	 * This method will enter BookingRules Page from the Inventory Manager top
	 * menu bar It starts from Inventory manager top menu bar and ends at
	 * closure page This top menu bar must have the page_name dropdown list
	 */
	public void gotoClosurePage() {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();

		logger.info("Go to closure page.");

		this.gotoSeasonsPg();
		seasonPg.clickClosures();
		invClosurePg.waitLoading();
	}
	
	/**
	 * This method will update one existed closure and it starts from closure
	 * page and ends at closure page
	 * 
	 * @param closure
	 * @return closure id
	 */
	public String updateExistClosure(Closure closure) {
		InvMgrClosureDetailPage invClosureDetailPg = InvMgrClosureDetailPage
				.getInstance();
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		InvMgrChangeReqItemDetail inChangeRequestItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();

		logger.info("Modify a exite closure.");

		if (!invClosureDetailPg.exists()) {
			invClosurePg.searchClosure(closure.closureID);
			invClosurePg.clickClosureID(closure.closureID);
			invClosureDetailPg.waitLoading();
		}

		invClosureDetailPg.fillClosureDetails(closure);
		invClosureDetailPg.clickOK();

		browser.waitExists(invClosurePg, invClosureDetailPg,
				inChangeRequestItemDetailPg);
		if (invClosurePg.exists()) {
			closure.closureID = invClosurePg.getClosureId();
		}

		return closure.closureID;
	}
	
	public boolean isClosureActive(String closureId){
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		
		invClosurePg.searchByClosureID(closureId);
		return invClosurePg.isClosureActive(closureId);
	}

	/**
	 * Add a new alert or note
	 * 
	 * @param invent
	 * @return note or alert id
	 */
	public String addNewAlertNote(InventoryInfo invent) {
		InvMgrNoteAndAlertListPage invAlertListPg = InvMgrNoteAndAlertListPage
				.getInstance();
		InvMgrNoteOrAlertDetailPage invAlertDetailsPg = InvMgrNoteOrAlertDetailPage
				.getInstance();
		InvMgrChangeReqItemDetail imChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		String AlertOrNoteID = "";

		logger.info("Add new alert or note.");
		gotoNotesAndAlertsPg();

		invAlertListPg.clickAddNew();
		invAlertDetailsPg.waitLoading();

		invAlertDetailsPg.addNewAlert(invent);
		ajax.waitLoading();

		Object pages = browser.waitExists(invAlertDetailsPg,
				imChangeReqItemDetailPg);
		if (pages == invAlertDetailsPg) {
			AlertOrNoteID = invAlertDetailsPg.getNoteOrAlertID();

			invAlertDetailsPg.clickOk();
			invAlertListPg.waitLoading();
		}
		logger.info("The ID of new added alert is:"+AlertOrNoteID);

		return AlertOrNoteID;
	}

	/**
	 * add Note/Alert from an existing slip details page
	 * @param noteAlertInfo
	 * @return
	 */
	public String addNoteAlertToSlip(InventoryInfo noteAlertInfo) {
		InvMgrSlipDetailsPage slipDetailsPage = InvMgrSlipDetailsPage.getInstance();
		InvMgrNoteOrAlertDetailPage noteAlertDetailsPage = InvMgrNoteOrAlertDetailPage.getInstance();
		
		logger.info("Add Note/Alert from slip(Slip CD: " + slipDetailsPage.getSlipCode() + ") details.");
		slipDetailsPage.clickAddNotesAlerts();
		ajax.waitLoading();
		noteAlertDetailsPage.waitLoading();
		noteAlertDetailsPage.addNewAlert(noteAlertInfo);
		noteAlertDetailsPage.clickApply();
		ajax.waitLoading();
		noteAlertDetailsPage.waitLoading();
		String toReturn = "";
		if(noteAlertDetailsPage.isSuccessfullyMsgExists()) {
			toReturn = noteAlertDetailsPage.getNoteOrAlertID();
			noteAlertDetailsPage.clickOk();
			ajax.waitLoading();
			slipDetailsPage.waitLoading();
		} else {
			toReturn = noteAlertDetailsPage.getErrorMessage();
		}
		
		return toReturn;
	}
	
	/**
	 * Add a note or alert form facility detail page
	 * @param noteAlertInfo
	 * @return
	 */
	public String addNoteAlertToFacility(InventoryInfo noteAlertInfo){
		InvMgrFacilityDetailPage invFaciDetailPg = InvMgrFacilityDetailPage.getInstance();
        InvMgrNoteOrAlertDetailPage noteAlertDetailsPage = InvMgrNoteOrAlertDetailPage.getInstance();
		logger.info("Add Note/Alert from facility(facility id: " + invFaciDetailPg.getFacilityID() + ") details.");
		invFaciDetailPg.clickAddNotesAlerts();
		ajax.waitLoading();
		noteAlertDetailsPage.waitLoading();
		noteAlertDetailsPage.addNewAlert(noteAlertInfo);
		noteAlertDetailsPage.clickApply();
		ajax.waitLoading();
		noteAlertDetailsPage.waitLoading();
		String toReturn = "";
		if(noteAlertDetailsPage.isSuccessfullyMsgExists()) {
			toReturn = noteAlertDetailsPage.getNoteOrAlertID();
			noteAlertDetailsPage.clickOk();
			ajax.waitLoading();
			invFaciDetailPg.waitLoading();
		} else {
			toReturn = noteAlertDetailsPage.getErrorMessage();
		}
		return toReturn;
	}
	
	public void gotoSlipAssignmentListPgFromNoteAlertDetailPg(){
		InvMgrNoteOrAlertDetailPage noteAlertDetailsPage = InvMgrNoteOrAlertDetailPage.getInstance();
		InvMgrNoteOrAlertAssignToSlipsPage slipAssignPg = InvMgrNoteOrAlertAssignToSlipsPage.getInstance();
		logger.info("Go to slip assignemnt page from note/alert list page.");
		noteAlertDetailsPage.clickSlipLable();
		ajax.waitLoading();
		slipAssignPg.waitLoading();
	}
	
	public void gotoNoteAlertListPgFromSlipDetailPg(){
		InvMgrSlipDetailsPage slipDetailsPage = InvMgrSlipDetailsPage.getInstance();
		InvMgrNoteAndAlertListPage noteAndAlertPg = InvMgrNoteAndAlertListPage.getInstance();
		logger.info("Go to note alert list page from slip detail page");
		slipDetailsPage.clickNotesAlerts();
		ajax.waitLoading();
		noteAndAlertPg.waitLoading();
	}
	
	public void gotoNoteAlertListPgFromFacilityDetailPg(){
		InvMgrFacilityDetailPage invFaciDetailPg = InvMgrFacilityDetailPage.getInstance();
		InvMgrNoteAndAlertListPage noteAndAlertPg = InvMgrNoteAndAlertListPage.getInstance();
		logger.info("Go to note alert list page from facility detail page");
		invFaciDetailPg.clickNotesAlerts();
		ajax.waitLoading();
		noteAndAlertPg.waitLoading();
	}
	
	/**
	 * delete the notes or alerts
	 * 
	 * @param inventory
	 * @param noteOrAlertID
	 */
	public void deleteNotesOrAlerts(InventoryInfo inventory,
			String... noteOrAlertIDs) {
		InvMgrNoteAndAlertListPage invAlertListPg = InvMgrNoteAndAlertListPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail imChangeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();

		logger.info("Delete the alert.");
		if (null != inventory) {
			invAlertListPg.searchNotesOrAlerts(inventory);
			PagingComponent turnPageComponent = new PagingComponent();
			boolean found=invAlertListPg.isNoteOrAlertIDExisted(inventory.alertID);
			while(!found && turnPageComponent.nextExists()) {
				turnPageComponent.clickNext();
				invAlertListPg.waitLoading();
				found=invAlertListPg.isNoteOrAlertIDExisted(inventory.alertID);
			}

			if(!found)
				throw new ActionFailedException("Note alert with id:"+inventory.alertID+" not found on page");
				
			invAlertListPg.selectNoteOrAlertCheckBoxByIDs(noteOrAlertIDs);
		}

		invAlertListPg.clickDelete();
		Object page = browser.waitExists(confirmDialogPg, invAlertListPg,
				imChangeReqItemDetail);

		if (page == confirmDialogPg) {
			confirmDialogPg.clickOK();
			invAlertListPg.waitLoading();
		}
//		invAlertListPg.waitExists();
	}

	/**
	 * Delete all notes or alerts in the list.
	 */
	public void deleteAllNotesOrAlerts() {
		InvMgrNoteAndAlertListPage invAlertListPg = InvMgrNoteAndAlertListPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail imChangeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();

		logger.info("Delete all alerts in the list...");
		invAlertListPg.selectAllAlerts();
		browser.waitExists();
		invAlertListPg.clickDelete();
		Object page = browser.waitExists(confirmDialogPg, invAlertListPg,
				imChangeReqItemDetail);

		if (page == confirmDialogPg) {
			confirmDialogPg.clickOK();
			invAlertListPg.waitLoading();
		}
	}
	
	/**
	 * Add NSS site to map
	 * 
	 * @param siteAttr
	 */
	public void addLoopToMap(SiteAttributes siteAttr) {
		InvMgrMapViewPage invMapPg = InvMgrMapViewPage.getInstance();

		logger.info("Add Loop to the map.");
		this.gotoMapViewPg();
		invMapPg.setArea(siteAttr.area);
		invMapPg.clickAdd();
		invMapPg.clickSave();
		invMapPg.waitForPageLoad();
	}

	/**
	 * Go to Map view page
	 */
	public void gotoMapViewPg() {
		InvMgrTopMenuPage invTopPg = InvMgrTopMenuPage.getInstance();
		InvMgrMapViewPage invMapPg = InvMgrMapViewPage.getInstance();
		logger.info("Go to Map View Page");

		invTopPg.clickMap();
		invMapPg.waitLoading();
		invMapPg.waitForPageLoad();
	}

	/*
	 * The process of add service to map
	 */
	public void addServiceToMap(String service) {
		InvMgrMapViewPage invMapPg = InvMgrMapViewPage.getInstance();

		logger.info("Add service to the map.");
		this.gotoMapViewPg();
		invMapPg.selectAmenity();
		invMapPg.selectService(service);
		invMapPg.clickAddNew();
		invMapPg.clickSave();

		invMapPg.waitForPageLoad();
	}

	/**
	 * Add NSS site to map
	 * 
	 * @param siteAttr
	 */
	public void addSiteToMap(SiteAttributes siteAttr, boolean isDismiss) {
		InvMgrMapViewPage invMapPg = InvMgrMapViewPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();

		logger.info("Add NSS site to the map.");
		this.gotoMapViewPg();

		if (null != siteAttr.parentLoop && siteAttr.parentLoop.length() > 0
				&& null != siteAttr.siteCode && siteAttr.siteCode.length() > 0) {
			invMapPg.selectLoop(siteAttr.parentLoop);
			invMapPg.selectUnMapped(siteAttr.siteCode);
			invMapPg.clickAddNSS();
		}

		invMapPg.clickSave();
		confirmDialog.setDismissible(isDismiss);
		if(invMapPg.exists()){
			invMapPg.waitForPageLoad();
		}else{
			browser.waitExists(inChangeReqItemDetailPg,confirmDialog);
		}

	}

	/**
	 * Go to the area loop page
	 */
	public void gotoLoopAreaPage() {
		InvMgrTopMenuPage invTpMenPg = InvMgrTopMenuPage.getInstance();
		InvMgrLoopAreasPage invLoopAreaPg = InvMgrLoopAreasPage.getInstance();
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		InvMgrNonSiteSpecGroupPage imNonSiteSpecificGroupPg = InvMgrNonSiteSpecGroupPage
				.getInstance();

		logger.info("Go to the loop area page.");
		if (browser.equals(invSitePg)) {
			invSitePg.clickLoopsAreas();
			invLoopAreaPg.waitLoading();
		}
		if (browser.equals(imNonSiteSpecificGroupPg)) {
			imNonSiteSpecificGroupPg.clickLoopsAreas();
			invLoopAreaPg.waitLoading();
		}
		if (!invLoopAreaPg.exists()
				&& !invTpMenPg.getDefaultPageName().equalsIgnoreCase(
						"Loop/Site Set-up")) {
			invTpMenPg.gotoSpecifiedDetailPage("Loop/Site Set-up");
			invLoopAreaPg.waitLoading();
		}
	}

	/**
	 * Go to the add new area loop details page
	 */
	public void gotoAddNewLoopAreaDetailsPage() {
		gotoLoopAreaDetailsPage("", true);
	}

	/**
	 * Go to the area loop details page
	 * 
	 * @param loopOrAreaName
	 * @param isNew
	 */
	public void gotoLoopAreaDetailsPage(String loopOrAreaName, boolean isNew) {
		InvMgrLoopAreasPage invLoopAreaPg = InvMgrLoopAreasPage.getInstance();
		InvMgrLoopDetailsPage invLoopDetailsPg = InvMgrLoopDetailsPage
				.getInstance();

		logger.info("Go to loop area details page.");
		if (!invLoopAreaPg.exists()) {
			gotoLoopAreaPage();
		}

		if (isNew) {
			invLoopAreaPg.clickAddNewLoopArea();
		} else {
			invLoopAreaPg.clickLoopLink(loopOrAreaName);
		}

		invLoopDetailsPg.waitLoading();
	}

	/**
	 * Go to the area loop site page
	 * 
	 */
	public void gotoLoopAreaSitePage(String loopAreaName) {
		InvMgrLoopAreasPage invLoopAreaPg = InvMgrLoopAreasPage.getInstance();
		InvMgrLoopAreaSitePage imLoopAreaSitePg = InvMgrLoopAreaSitePage
				.getInstance();

		logger.info("Go to the loop area site page.");
		gotoLoopAreaPage();

		String loopAreaID = invLoopAreaPg.getLoopAreaID(loopAreaName);
		invLoopAreaPg.clickLoopAreaSiteNum(loopAreaID);
		imLoopAreaSitePg.waitLoading();
	}

	/**
	 * Add new loop
	 * 
	 * @param loop
	 * @return loopArea id
	 */
	public String addNewLoop(LoopInfoData loop) {
		InvMgrLoopAreasPage invLoopAreaPg = InvMgrLoopAreasPage.getInstance();
		InvMgrLoopDetailsPage invLoopDetailsPg = InvMgrLoopDetailsPage
				.getInstance();
		InvMgrChangeReqItemDetail imChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();

		logger.info("Add new loop.");
		invLoopAreaPg.clickAddNewLoopArea();
		invLoopDetailsPg.waitLoading();

		invLoopDetailsPg.addLoop(loop);

		Object page = browser.waitExists(invLoopDetailsPg,
				imChangeReqItemDetailPg, invLoopAreaPg);

		if (page == invLoopAreaPg) {
			loop.loopID = invLoopAreaPg.getLoopAreaID(loop.loopName);

		}

		return loop.loopID;

	}

	/**
	 * The process of the assign site to season
	 * 
	 * @param season
	 * @param siteNum
	 */
	public void assignSiteToSeason(SeasonData season, String siteNum) {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrSeasonDetailPage seasonDetailsPg = InvMgrSeasonDetailPage
				.getInstance();
		InvMgrSeasonSitesPage seasonSitesPg = InvMgrSeasonSitesPage
				.getInstance();

		logger.info("Add the site to the season.");
		seasonPg.gotoSeasonDetails(season.m_SeasonID);
		seasonDetailsPg.waitLoading();
		seasonDetailsPg.clickSeasonSitesTab();
		seasonSitesPg.waitLoading();

		seasonSitesPg.searchSites(siteNum);
		seasonSitesPg.waitLoading();
		seasonSitesPg.selectAll();
		seasonSitesPg.clickAssign();

		seasonSitesPg.waitLoading();
	}

	/**
	 * The method execute assigning sites to season or removing sites from
	 * season and the start page is season page
	 * 
	 * @param status
	 *            : Assign or Remove
	 * @param seasonID
	 * @param siteIDs
	 */
	public void assignOrRemoveSiteAssociatedWithSeason(String status,
			String seasonID, String[] siteIDs) {
		InvMgrSeasonSitesPage seasonSitesPg = InvMgrSeasonSitesPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		InvMgrConfimActionPage inConfimActionPg = InvMgrConfimActionPage
				.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		logger.info(status + " the site associated with season.");

		if (!seasonSitesPg.exists()) {
			this.gotoSeasonSitesPg(seasonID);
		}

		seasonSitesPg.searchSites(null, "Assigned or Not", "");
		seasonSitesPg.waitLoading();

		seasonSitesPg.selectSiteCheckBoxBySiteIDs(siteIDs);

		if (status.equalsIgnoreCase("Assign")) {
			seasonSitesPg.clickAssign();
		} else if (status.equalsIgnoreCase("Remove")) {
			seasonSitesPg.clickRemove();
		}
		ajax.waitLoading();
		Object pages = browser.waitExists(confirmWidget,inConfimActionPg, confirmDialogPg,
				seasonSitesPg, inChangeReqItemDetail);
		if (pages == inConfimActionPg) {
			inConfimActionPg.clickOK();
			seasonSitesPg.waitLoading();

		}
		if (pages == confirmWidget) {
			confirmWidget.clickOK();
			ajax.waitLoading();
			seasonSitesPg.waitLoading();
		}
	}
	
	public void assignOrRemoveSlipClosure(String closureID, String slipCode, String status, String dock){
		InvMgrClosuresSitesPage closureSitesPg = InvMgrClosuresSitesPage.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetail = InvMgrChangeReqItemDetail.getInstance();
		
		logger.info(status + " the slip associated with closure.");
		if (!closureSitesPg.exists()) {
			this.gotoClosureSitesPg(closureID);
		}

		String show = "Assigned or Not";
		if(status.equalsIgnoreCase("Assign")){
			show = "NOT Assigned";
		} else {
			show = "Assigned";
		}
		closureSitesPg.searchSites(new String[]{slipCode}, show, dock);
		closureSitesPg.selectAll();

		if (status.equalsIgnoreCase("Assign")) {
			closureSitesPg.clickAssign();
		} else if (status.equalsIgnoreCase("Remove")) {
			closureSitesPg.clickRemove();
		}

		browser.waitExists(confirmDialogPg, closureSitesPg, inChangeReqItemDetail);
	}
	
	/**
	 * The method execute assigning sites to closure or removing sites from
	 * closure and the start page is season page
	 * 
	 * @param status
	 *            : Assign or Remove
	 * @param closureID
	 * @param siteIDs
	 */
	public void assignOrRemoveSiteAssociatedWithClosure(String status,
			String closureID, String[] siteIDs, String loop) {
		InvMgrClosuresSitesPage closureSitesPg = InvMgrClosuresSitesPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		logger.info(status + "the site associated with closure.");

		if (!closureSitesPg.exists()) {
			this.gotoClosureSitesPg(closureID);
		}
	
		closureSitesPg.searchSites(null, "Assigned or Not", loop);
		
		
		closureSitesPg.waitLoading();

		closureSitesPg.selectSiteCheckBoxBySiteIDs(siteIDs);

		if (status.equalsIgnoreCase("Assign")) {
			closureSitesPg.clickAssign();
		} else if (status.equalsIgnoreCase("Remove")) {
			closureSitesPg.clickRemove();
		}

		browser.waitExists(confirmDialogPg, closureSitesPg,
				inChangeReqItemDetail);
	}

	/**
	 * The method execute assigning sites to camping units or removing sites
	 * from camping units and the start page is Camping unit sites page
	 * 
	 * @param status
	 *            : Assign or Remove
	 * @param campComboID
	 * @param siteIDs
	 */
	public void assignOrRemoveSitesAssociatedWithCampUnits(String status,
			String campComboID, String[] siteIDs) {
		InvMgrCampingUnitSitesPage campUnitSitesPg = InvMgrCampingUnitSitesPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		logger.info(status + " the site associated with camping units.");

		if (!campUnitSitesPg.exists()) {
			this.gotocampingUnitSitesPg(campComboID);
		}

		campUnitSitesPg.selectSiteCheckBoxBySiteIDs(siteIDs);

		if (status.equalsIgnoreCase("Assign")) {
			campUnitSitesPg.clickAssign();
		} else if (status.equalsIgnoreCase("Remove")) {
			campUnitSitesPg.clickRemove();
		}

		browser.waitExists(confirmDialogPg, campUnitSitesPg,
				inChangeReqItemDetail);
	}
	
	public void assignOrRemoveSitesAssociatedWithCampUnits(String status, String[] siteNames) {
		InvMgrCampingUnitSitesPage campUnitSitesPg = InvMgrCampingUnitSitesPage
				.getInstance();
		
		for(String name:siteNames){
			boolean isAssign = (status).equalsIgnoreCase("Assign");
			campUnitSitesPg.assignOrRemoveSite(name, isAssign);			
		}		
	}

	/**
	 * The method execute assigning loops to camping units or removing loops
	 * from camping units and the start page is Camping unit Loops page
	 * 
	 * @param status
	 *            : Assign or Remove
	 * @param campComboID
	 * @param loopIDs
	 */
	public void assignOrRemoveLoopsAssociatedWithCampUnits(String status,
			String campComboID, String... loopIDs) {
		InvMgrCampingUnitLoopsPage campUnitLoopsPg = InvMgrCampingUnitLoopsPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		logger.info(status + "the Loops associated with camping units.");

		if (!campUnitLoopsPg.exists()) {
			this.gotocampingUnitLoopsPg(campComboID);
		}

		campUnitLoopsPg.selectLoopCheckBoxByLoopsIDs(loopIDs);

		if (status.equalsIgnoreCase("Assign")) {
			campUnitLoopsPg.clickAssign();
		} else if (status.equalsIgnoreCase("Remove")) {
			campUnitLoopsPg.clickRemove();
		}
		Browser.sleep(SHORT_SLEEP_BEFORE_CHECK);
		browser.waitExists(confirmDialogPg, campUnitLoopsPg,
				inChangeReqItemDetail);
	}

	/**
	 * The method execute assigning Services Activities and the start page is
	 * Services Activities list page
	 * 
	 * @param status
	 *            : Assign or Remove
	 * @param servicesActivitiesIDs
	 * @param dismissable
	 */
	public void assignOrRemoveServicesActivities(String status,
			boolean dismissable, String... servicesActivitiesNames) {
		InvMgrServiceActivitiesPage invServicePg = InvMgrServiceActivitiesPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		String[] rowNums = new String[] {};

		logger.info(status + " a services Activities.");

		if (!invServicePg
				.CheckStringArrayExistNullValue(servicesActivitiesNames)) {
			rowNums = invServicePg.getSpecificServicActivitRowNum(
					servicesActivitiesNames).split("#");
			invServicePg.selectServicActivitCheckBoxByIDs(rowNums);
		}

		if (status.equalsIgnoreCase("Assign")) {
			invServicePg.clickAssign();
		}

		if (status.equalsIgnoreCase("Remove")) {
			invServicePg.clickRemove();
		}

		confirmDialogPg.setDismissible(dismissable);
		browser.waitExists(invServicePg, confirmDialogPg,
				inChangeReqItemDetailPg);
	}

	/**
	 * The method execute Remove Event and the start page is Services Activities
	 * list page or Event page
	 * 
	 * @param eventIDs
	 */
	public void removeEvents(String... eventNames) {
		InvMgrEventsPage invEventPg = InvMgrEventsPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		String[] rowNums = new String[] {};

		logger.info("Remove" + " a Event.");

		if (!invEventPg.CheckStringArrayExistNullValue(eventNames)) {
			rowNums = invEventPg.getSpecificServicActivitRowNum(eventNames)
					.split("#");
			invEventPg.selectEventCheckBoxByIDs(rowNums);
		}

		invEventPg.clickRemove();
        Browser.sleep(SHORT_SLEEP_BEFORE_CHECK);
		browser.waitExists(invEventPg, inChangeReqItemDetailPg);
	}

	/**
	 * Go to Note or Alert sites page from Note or Alert list page
	 * 
	 * @param noteOrAlertID
	 */
	public void gotoNoteOrAlertSitesPg(String noteOrAlertID) {
		InvMgrNoteAndAlertListPage inNoteAndAlertPg = InvMgrNoteAndAlertListPage
				.getInstance();
		InvMgrNoteOrAlertSitesPage inNoteAndAlertSitePg = InvMgrNoteOrAlertSitesPage
				.getInstance();
		logger.info("Go to Note or Alert sites page.");

		if (!inNoteAndAlertPg.exists()) {
			this.gotoNotesAndAlertsPg();
		}
		PagingComponent turnPageComponent = new PagingComponent();
		boolean found=inNoteAndAlertPg.isNoteOrAlertIDExisted(noteOrAlertID);
		while(!found && turnPageComponent.nextExists()) {
			turnPageComponent.clickNext();
			inNoteAndAlertPg.waitLoading();
			found=inNoteAndAlertPg.isNoteOrAlertIDExisted(noteOrAlertID);
		}
		if(!found)
			throw new ActionFailedException("Could not find note alert id:"+noteOrAlertID);
		
		inNoteAndAlertPg.clickNumSites(noteOrAlertID);
		inNoteAndAlertSitePg.waitLoading();
	}
	
	/**
	 * Go to Note or Alert sites page from Note or Alert list page
	 * 
	 * @param noteOrAlertID
	 */
	public void gotoNoteOrAlertEntrancePg(String noteOrAlertID) {
		InvMgrNoteAndAlertListPage inNoteAndAlertPg = InvMgrNoteAndAlertListPage
				.getInstance();
		InvMgrNoteOrAlertDetailPage invAlertDetailsPg = InvMgrNoteOrAlertDetailPage
		.getInstance();
		InvMgrNoteOrAlertEntrancesPage inNoteAndAlertEntrancesPg = InvMgrNoteOrAlertEntrancesPage
				.getInstance();
		logger.info("Go to Note or Alert entrance page.");

		if (!inNoteAndAlertPg.exists()) {
			this.gotoNotesAndAlertsPg();
		}
		
		PagingComponent turnPageComponent = new PagingComponent();
		boolean found=inNoteAndAlertPg.isNoteOrAlertIDExisted(noteOrAlertID);
		while(!found && turnPageComponent.nextExists()) {
			turnPageComponent.clickNext();
			inNoteAndAlertPg.waitLoading();
			found=inNoteAndAlertPg.isNoteOrAlertIDExisted(noteOrAlertID);
		}
		if(!found)
			throw new ActionFailedException("Could not find note alert id:"+noteOrAlertID);
		
		inNoteAndAlertPg.clickNoteOrAlertID(noteOrAlertID);
		invAlertDetailsPg.waitLoading();
		
		invAlertDetailsPg.clickEntranceTab();
		inNoteAndAlertEntrancesPg.waitLoading();
	}

	/**
	 * Go to Note or Alert loops page from Note or Alert list page
	 * 
	 * @param noteOrAlertID
	 */
	public void gotoNoteOrAlertLoopsPg(String noteOrAlertID) {
		InvMgrNoteAndAlertListPage inNoteAndAlertPg = InvMgrNoteAndAlertListPage
				.getInstance();
		InvMgrNoteOrAlertLoopsPage inNoteOrAlertLoopsPg = InvMgrNoteOrAlertLoopsPage
				.getInstance();
		PagingComponent turnPageComponent = new PagingComponent();
		logger.info("Go to Note or Alert " + noteOrAlertID + " loops page.");

		if (!inNoteAndAlertPg.exists()) {
			this.gotoNotesAndAlertsPg();
		}
		boolean found=inNoteAndAlertPg.isNoteOrAlertIDExisted(noteOrAlertID);
		while(!found && turnPageComponent.nextExists()) {
			turnPageComponent.clickNext();
			inNoteAndAlertPg.waitLoading();
			found=inNoteAndAlertPg.isNoteOrAlertIDExisted(noteOrAlertID);
		}
		if(!found)
			throw new ActionFailedException("Could not find note alert id:"+noteOrAlertID);
		inNoteAndAlertPg.clickNumLoops(noteOrAlertID);
		inNoteOrAlertLoopsPg.waitLoading();
	}

	/**
	 * The method execute assigning sites to note or alert or removing sites
	 * from note or alert
	 * 
	 * @param status
	 *            : Assign or Remove
	 * @param alertOrNoteID
	 * @param siteIDs
	 */
	public void assignOrRemoveSitesAssociatedWithAlertOrNoteBasedOnSearch(String searshBy, String searchValue, String status, String alertOrNoteID, String... siteIDs) {
		InvMgrNoteOrAlertSitesPage inNoteAndAlertSitePg = InvMgrNoteOrAlertSitesPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		logger.info(status + "the site associated with Note or Alert.");

		if (!inNoteAndAlertSitePg.exists()) {
			gotoNoteOrAlertSitesPg(alertOrNoteID);
		}

		//Sara[12/12/2013] add search by and value. Becasue some cases want to select two more sites in the same page but actually they are in different page
		inNoteAndAlertSitePg.searchSites((StringUtil.isEmpty(searshBy)?"":searshBy), (StringUtil.isEmpty(searchValue)?"":searchValue), "All");
		
		inNoteAndAlertSitePg.selectSiteCheckBoxBySiteIDs(siteIDs);
		if (status.equalsIgnoreCase("Assign")) {
			inNoteAndAlertSitePg.clickAssign();
		} else if (status.equalsIgnoreCase("Remove")) {
			inNoteAndAlertSitePg.clickRemove();
		}

		browser.waitExists(confirmDialogPg, inNoteAndAlertSitePg,
				inChangeReqItemDetail);
	}
	
	public void assignOrRemoveSitesAssociatedWithAlertOrNote(String status, String alertOrNoteID, String... siteIDs) {
		assignOrRemoveSitesAssociatedWithAlertOrNoteBasedOnSearch(StringUtil.EMPTY, StringUtil.EMPTY, status, alertOrNoteID, siteIDs);
	}
	
	/**
	 * The method execute assigning entrances to note or alert or removing sites
	 * from note or alert
	 * 
	 * @param status
	 *            : Assign or Remove
	 * @param alertOrNoteID
	 * @param entranceCodes
	 */
	public void assignOrRemoveEntrancesToAlertOrNote(String status,
			String alertOrNoteID, String... entranceCodes) {
		InvMgrNoteOrAlertEntrancesPage inNoteAndAlertEntrancePg = InvMgrNoteOrAlertEntrancesPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		logger.info(status + "the entrance associated with Note or Alert.");

		if (!inNoteAndAlertEntrancePg.exists()) {
			gotoNoteOrAlertEntrancePg(alertOrNoteID);
		}

		inNoteAndAlertEntrancePg.searchEntrances("", "", "All");
		inNoteAndAlertEntrancePg.selectEntranceCheckBoxByEntranceIDs(entranceCodes);

		if (status.equalsIgnoreCase("Assign")) {
			inNoteAndAlertEntrancePg.clickAssign();
		} else if (status.equalsIgnoreCase("Remove")) {
			inNoteAndAlertEntrancePg.clickRemove();
		}

		browser.waitExists(confirmDialogPg, inNoteAndAlertEntrancePg,
				inChangeReqItemDetail);
	}

	/**
	 * The method execute assigning loops to note or alert or removing loops
	 * from note or alert
	 * 
	 * @param status
	 *            : Assign or Remove
	 * @param alertOrNoteID
	 * @param loopsIDs
	 */
	public void assignOrRemoveLoopsAssociatedWithAlertOrNote(String status,
			String alertOrNoteID, String... loopsIDs) {
		InvMgrNoteOrAlertLoopsPage inNoteAndAlertLoopsPg = InvMgrNoteOrAlertLoopsPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		logger.info(status + "the loops associated with Note or Alert.");

		if (!inNoteAndAlertLoopsPg.exists()) {
			gotoNoteOrAlertLoopsPg(alertOrNoteID);
		}

		inNoteAndAlertLoopsPg.searchLoops("", "", "All");
		inNoteAndAlertLoopsPg.selectLoopCheckBoxByLoopIDs(loopsIDs);

		if (status.equalsIgnoreCase("Assign")) {
			inNoteAndAlertLoopsPg.clickAssign();
		} else if (status.equalsIgnoreCase("Remove")) {
			inNoteAndAlertLoopsPg.clickRemove();
		}

		browser.waitExists(confirmDialogPg, inNoteAndAlertLoopsPg,
				inChangeReqItemDetail);
	}

	public void assignOrRemoveSpecialSitesToLoopOrArea(String status,
			String loopAreaName, String siteID) {
		InvMgrLoopAreaSitePage imLoopAreaSitePg = InvMgrLoopAreaSitePage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		InvMgrConfimActionPage imConfirmActionPg = InvMgrConfimActionPage
				.getInstance();
		logger.info(status + " the site associated with Loop or Area.");

		if (!imLoopAreaSitePg.exists()) {
			gotoLoopAreaSitePage(loopAreaName);
		}

		imLoopAreaSitePg.searchSites("Site ID", siteID, "All Sites", "", "", "");
		imLoopAreaSitePg.selectSiteCheckBoxBySiteID(siteID);

		if (status.equalsIgnoreCase("Assign")) {
			imLoopAreaSitePg.clickAssign();
		} else if (status.equalsIgnoreCase("Remove")) {
			imLoopAreaSitePg.clickRemove();
		}

		Object page = browser.waitExists(imConfirmActionPg, confirmDialogPg,
				imLoopAreaSitePg, inChangeReqItemDetail);
		if (page == imConfirmActionPg) {
			imConfirmActionPg.clickOK();
			imLoopAreaSitePg.waitLoading();
		}
	}

	public void assignSlipToDock(String dockName,String slipId){
		InvMgrDockSlipsListPage assignPg = InvMgrDockSlipsListPage.getInstance();
		InvMgrConfirmDialogWidget confirmPg = InvMgrConfirmDialogWidget.getInstance();
		
		logger.info("Assign slip "+slipId+" to Dock "+dockName);
		
		if(!assignPg.exists()){
			this.gotoDockSlipPgFromDockListPgByClickSlipNumber(dockName);
		}
		
		assignPg.assignSlip(slipId);
		Object page = browser.waitExists(confirmPg,assignPg);
		if(page == confirmPg){
			confirmPg.clickOK();
			ajax.waitLoading();
			assignPg.waitLoading();
		}
	}
	
	/**
	 * The method execute assigning sites to loop or area or removing sites from
	 * loop or area
	 * 
	 * @param status
	 *            : Assign or Remove
	 * @param loopAreaName
	 * @param siteIDs
	 */
	public void assignOrRemoveMultiSitesToLoopOrArea(String status,
			String loopAreaName, String[] siteIDs) {
		InvMgrLoopAreaSitePage imLoopAreaSitePg = InvMgrLoopAreaSitePage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		InvMgrConfimActionPage confirmActionPg = InvMgrConfimActionPage
				.getInstance();
		logger.info(status + " the site associated with Loop or Area.");

		if (!imLoopAreaSitePg.exists()) {
			gotoLoopAreaSitePage(loopAreaName);
		}
		if(siteIDs != null && siteIDs.length > 0) {
			if(siteIDs.length < 2){
				imLoopAreaSitePg.searchSites("Site ID", siteIDs[0], "All Sites", "", "", "");
			}else{
				imLoopAreaSitePg.searchSites("", "", "All Sites", "", "", "");
			}
			imLoopAreaSitePg.selectSiteCheckBoxBySiteIDs(siteIDs);
		}
		
		if (status.equalsIgnoreCase("Assign")) {
			imLoopAreaSitePg.clickAssign();
		} else if (status.equalsIgnoreCase("Remove")) {
			imLoopAreaSitePg.clickRemove();
		}

		Object page = browser.waitExists(confirmActionPg, confirmDialogPg,
				imLoopAreaSitePg, inChangeReqItemDetail);
		if (page == confirmActionPg) {
			confirmActionPg.clickOK();
			imLoopAreaSitePg.waitLoading();
		}
	}

	/**
	 * Assign or Unassign tour to combo tour in Assigned tours page from
	 * 'Assigned Tours' page
	 * 
	 * @param isAssign
	 * @param tourCode
	 *            tour code
	 * @Return void
	 * @Throws
	 */
	public void assignOrUnassignTourToComoTour(boolean isAssign,
			String... tourCodes) {
		InvMgrAssignTourPage assginPage = InvMgrAssignTourPage.getInstance();

		logger.info(isAssign == true ? "Assgin" : "Unassgin"
				+ " Tour to combotour...");

		for (String tourCode : tourCodes) {
			assginPage.searchTour("All Tours", "Tour Code", tourCode);
			assginPage.selectTour(tourCode);
			if (isAssign) {
				assginPage.clickAssign();
			} else {
				assginPage.clickRemove();
			}
			assginPage.waitLoading();
		}
	}

	/*
	 * Assign site to closure
	 */
	public void assignSiteToClosure(Closure closure, String siteCode) {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		InvMgrClosureDetailPage invClosureDetailsPg = InvMgrClosureDetailPage
				.getInstance();
		InvMgrClosuresSitesPage invClosureSitePg = InvMgrClosuresSitesPage
				.getInstance();

		logger.info("Assign site to closure.");
		if (!invClosurePg.exists()) {
			seasonPg.clickClosures();
			invClosurePg.waitLoading();
		}
		
		invClosurePg.searchClosure(closure.closureID);

		invClosurePg.clickClosureID(closure.closureID);
		invClosureDetailsPg.waitLoading();

		invClosureDetailsPg.clickClosureSite();
		ajax.waitLoading();
		invClosureSitePg.waitLoading();

		invClosureSitePg.searchSiteByCode(siteCode);
		invClosureSitePg.waitLoading();
		invClosureSitePg.selectAll();
		invClosureSitePg.clickAssign();

		invClosureSitePg.waitLoading();
	}

	/**
	 * Delete the site from the site details page
	 * 
	 * @param dismissable
	 *            : true-> dismiss
	 */
	public void deleteSite(boolean dismissable) {
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage
				.getInstance();
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail imChangeReqItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		InvMgrConfimActionPage confirmActionPg = InvMgrConfimActionPage
				.getInstance();

		logger.info("Delete the site.");
		invSiteDetailPg.clickDeleteSite();

		confirmDialogPg.setDismissible(dismissable);
		Object page = browser.waitExists(confirmActionPg, confirmDialogPg,
				invSiteDetailPg, imChangeReqItemDetail);
		if (page == confirmActionPg) {
			confirmActionPg.clickOK();
			invSitePg.waitLoading();
		}
		if (page == invSiteDetailPg) {
			invSiteDetailPg.clickOK();
			invSitePg.waitLoading();
		}
	}
	
	public String addNewFacility(FacilityData fd) {
		logger.info("Add a new facility...");
		
		return this.addOrEditNewFacility(fd,false);
	}
	
	public String editFacility(FacilityData fd) {
		logger.info("Edit a facility...");
		
		return this.addOrEditNewFacility(fd,true);
	}

	/**
	 * This method goes to add a new facility, it starts and ends at inventory
	 * home page.
	 * 
	 * @param fd
	 *            - facility data
	 * @return facilityID - new facility ID
	 */
	private String addOrEditNewFacility(FacilityData fd,boolean isEdit) {
		InvMgrHomePage invHmPg = InvMgrHomePage.getInstance();
		InvMgrAddFacilitySelectLocationPage selectLocationPg = InvMgrAddFacilitySelectLocationPage
				.getInstance();
		InvMgrAddFacilityDetailsPage addFacilityDetailPg = InvMgrAddFacilityDetailsPage
				.getInstance();
		String message="";
		
		if(!isEdit){
			invHmPg.clickFacilitiesAddNew();
			selectLocationPg.waitLoading();
			selectLocationPg.setupLocationData(fd);
			selectLocationPg.clickOK();
			addFacilityDetailPg.waitLoading();
		}
	
		addFacilityDetailPg.setupFacilityDetailsData(fd);
		addFacilityDetailPg.clickApply();
		addFacilityDetailPg.waitLoading();
		if(addFacilityDetailPg.checkErrorExist()){
			message=addFacilityDetailPg.getErrorMess();
		}else{
			message = addFacilityDetailPg.getFacilityID();
			if ("".equals(message)) {
				// hard to verify operation succeed msg, so verify this
				throw new ErrorOnPageException("Failed to add new facility.");
			}
			// make facility as production
			addFacilityDetailPg.clickOK();
			invHmPg.waitLoading();
		}
		
		return message;
	}

	/**
	 * Add new tour From:facility tour page To:tour details page End:falicility
	 * tour page /tour details page
	 * 
	 * @param tour
	 *            -- Tour information
	 */
	public void addNewTour(TourInfo tour) {
		InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
		InvMgrTourDetailsPage invTourDetailsPg = InvMgrTourDetailsPage
				.getInstance();

		logger.info("Add a new tour...");
		facTour.clickTourAddNew();
		invTourDetailsPg.waitLoading();
		invTourDetailsPg.setTour(tour);
		invTourDetailsPg.clickOK();
		browser.waitExists(invTourDetailsPg, facTour);
	}

	/**
	 * Activate a tour in facility tour page
	 * 
	 * @param tourName
	 */
	public void activeTour(String tourName) {
		InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
		logger.info("Active tour");

		this.verifyTourExist("All", "Tour Name", tourName, true);
		facTour.activeTour(tourName);
		facTour.waitLoading();
	}

	/**
	 * Deactivate a tour in facility tour page
	 * 
	 * @param tourName
	 */
	public void deactivateTour(String tourName) {
		InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
		logger.info("Deactive tour");

		this.verifyTourExist("All", "Tour Name", tourName, true);
		facTour.deactiveTour(tourName);
		facTour.waitLoading();
	}

	/**
	 * judge if the tour is active or not in facility tour page.
	 * 
	 * @param tourName
	 * @return
	 */
	public boolean isActive(String tourName) {
		InvMgrFacilityTourPage facTour = InvMgrFacilityTourPage.getInstance();
		logger.info("Verify tour is active or not");

		boolean flag = false;
		facTour.searchTour("All", "Tour Name", tourName);
		flag = facTour.isThisTourActive(tourName);
		return flag;
	}

	/**
	 * verify if all search results are active in facility tour page.
	 */
	public void verifyAllTourSearchResultActive() {
		InvMgrFacilityTourPage page = InvMgrFacilityTourPage.getInstance();
		logger.info("Verify tour searching results.");

		if (!page.isAllResultActive()) {
			throw new ActionFailedException("Not all results are active ~!");
		}
		while (!page.isNextDisabled()) {
			page.clickNextbutton();
			if (!page.isAllResultActive()) {
				throw new ActionFailedException("Not all results are active ~!");
			}
		}
	}

	/***
	 * verify if all search results are Inactive in facility tour page.
	 */
	public void isAllResultsInactive() {
		InvMgrFacilityTourPage page = InvMgrFacilityTourPage.getInstance();
		logger.info("Verify tour searching results.");

		if (!page.isAllResultInActive()) {
			throw new ActionFailedException("Not all results are Inactive ~!");
		}
		while (!page.isNextDisabled()) {
			page.clickNextbutton();
			if (!page.isAllResultInActive()) {
				throw new ActionFailedException(
						"Not all results are Inactive ~!");
			}
		}
	}

	/**
	 * verify if all results searched by Description are the same description
	 */
	public void verifyAllSearchTourType_DescSame(String type) {
		InvMgrFacilityTourPage page = InvMgrFacilityTourPage.getInstance();
		logger.info("Verify tour searching type.");

		if (!page.isSameType_Desc(type)) {
			if (type.equals("Tour Type"))
				throw new ActionFailedException("Some results are not " + type);
			if (type.equals("Description"))
				throw new ActionFailedException(
						"There is at least one result's description not equal'"
								+ type + "'");
		}
		while (!page.isNextDisabled()) {
			page.clickNextbutton();
			if (!page.isSameType_Desc(type)) {
				if (type.equals("Tour Type"))
					throw new ActionFailedException("Some results are not "
							+ type);
				if (type.equals("Description"))
					throw new ActionFailedException(
							"There is at least one result's description not equal'"
									+ type + "'");
			}
		}

	}

	/**
	 * verify if the status of this tour is as expected in
	 * InvMgrFacilityTourPage.
	 * 
	 * @param tourName
	 * @param except
	 */
	public void verifyThisTourActiveOrNot(String tourName, boolean expect) {
		logger.info("Verify tour active or not");

		boolean flag = this.isActive(tourName);
		if (flag != expect) {
			throw new ActionFailedException("The status of this tour is not"
					+ expect);
		}
	}

	/**
	 * Delete a tour from tour detailsPage
	 */
	public void deleteTour() {
		InvMgrHomePage invHmPg = InvMgrHomePage.getInstance();
		InvMgrTourDetailsPage tourdetail = InvMgrTourDetailsPage.getInstance();
		InvMgrTourConfirmActionPage actionpage = InvMgrTourConfirmActionPage
				.getInstance();
		InvMgrFacilityTourPage faciltyTourPg = InvMgrFacilityTourPage
				.getInstance();
		InvMgrComboTourPage comboTourPg = InvMgrComboTourPage.getInstance();

		logger.info("Delete a tour...");
		tourdetail.clickDeleteTour();
		actionpage.waitLoading();
		actionpage.clickOkButton();
		browser.waitExists(invHmPg,faciltyTourPg, comboTourPg);
	}

	/**
	 * verify if the tour code is existent in the search results; From:tour
	 * facility page. End: tour facility page.
	 */
	public boolean verifyTourExistInOneSearch(String tourcode) {
		InvMgrFacilityTourPage page = InvMgrFacilityTourPage.getInstance();
		logger.info("Verify tour exist in the search results");

		if (page.isTourCodeExist(tourcode)) {
			return true;
		}
		while (!page.isNextDisabled()) {
			page.clickNextbutton();
			if (page.isTourCodeExist(tourcode)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * judge if the searched tour is existent by searching in tour facility
	 * page.
	 * 
	 * @param status
	 *            (All,Active,Inactive)
	 * @param c_n_t_d
	 *            (Tour name/code/type,description)
	 * @param value
	 *            true-existent,false-inexistent
	 */
	public void verifyTourExist(String status, String c_n_t_d, String value,
			boolean expect) {

		InvMgrFacilityTourPage toursearch = InvMgrFacilityTourPage
				.getInstance();

		boolean flag = false;
		ArrayList<String> flags = new ArrayList<String>();

		toursearch.searchTour(status, c_n_t_d, value);
		logger.info("verify if tour exists ~!");
		flag = toursearch.isThisTourExist(status, c_n_t_d, value);

		while (!toursearch.isNextDisabled()) {
			toursearch.clickNextbutton();
			toursearch.waitLoading();
			flags.add(String.valueOf(toursearch.isThisTourExist(status,
					c_n_t_d, value)));
		}

		if (flags.contains("false")) {
			flag = false;
		}

		if (flag != expect) {
			throw new ActionFailedException(
					"Search results don't display as expected when searching with status="
							+ status + "," + c_n_t_d + "=" + value);
		}
		logger.info("Verify successfully");
	}

	public void gotoComboToursSearchPageFromdetailsPage() {
		InvMgrComboTourDetailsPage page = InvMgrComboTourDetailsPage
				.getInstance();
		InvMgrComboTourPage tourPage = InvMgrComboTourPage.getInstance();
		logger.info("go to combo tour search page");

		page.clickComboToursLink();
		tourPage.waitLoading();
	}

	public void gotoToursSearchPageFromdetailsPage() {
		InvMgrTourDetailsPage page = InvMgrTourDetailsPage.getInstance();
		InvMgrFacilityTourPage facilityTourPg = InvMgrFacilityTourPage
				.getInstance();
		logger.info("go to tour search page");

		page.clickToursLink();
		facilityTourPg.waitLoading();
	}

	/**
	 * verify if error message is existent in tour details page. From:tour
	 * details page To:tour details page End:tour details page
	 * 
	 * @param expectmsg
	 *            (the excepted error message)
	 * @param tourType
	 *            (Add Tour/Combo Tour)
	 */
	public void verifyTourErrorMessageExistent(String expectmsg, String tourType) {
		ArrayList<String> list = null;

		logger.info("Verify Error Messages....");

		if (tourType.equalsIgnoreCase(TOUR)) {
			InvMgrTourDetailsPage invTourDetailsPg = InvMgrTourDetailsPage
					.getInstance();
			list = (ArrayList<String>) invTourDetailsPg.getErrorMessages();
		} else if (tourType.equalsIgnoreCase(COMBO_TOUR)) {
			InvMgrComboTourDetailsPage comboTourPage = InvMgrComboTourDetailsPage
					.getInstance();
			list = (ArrayList<String>) comboTourPage.getErrorMessages();
		}

		if (list != null) {
			if (list.contains(expectmsg)) {

				if (list.size() > 1 && !MiscFunctions.blockByDefect()) {
					// DEFECT-28229
					throw new ActionFailedException(
							"At least one unnecessary message displayed besides the right message~! ");
				} else {
					logger.info("Message is right.");
					return;
				}
			} else {
				throw new ActionFailedException("The message '" + expectmsg
						+ "' is not existend.");
			}

		} else {
			throw new ActionFailedException("No any error message displayed.");
		}

	}

	/**
	 * this keyword is for edit tour details From:tour details page To:tour
	 * details page End:tour details page
	 * 
	 * @param tourname
	 *            the tour name of tour to be edited
	 * @param tour
	 *            The tour after edited
	 * 
	 * @return the tour before edited.
	 */
	public TourInfo editTour(TourInfo tour) {
		TourInfo edittour = new TourInfo();
		InvMgrTourDetailsPage tourdetails = InvMgrTourDetailsPage.getInstance();
		InvMgrFacilityTourPage facilitypage = InvMgrFacilityTourPage
				.getInstance();
		logger.info("Edit tour ....");

		tourdetails.setTour(tour);
		edittour = this.getTour();
		tourdetails.clickOK();
		browser.waitExists(tourdetails, facilitypage);
		return edittour;
	}

	/**
	 * judge whether those two tours are equal or not,if the result is not
	 * according to the expect value, a Exception will be thrown.
	 * 
	 * From: To: End:
	 * 
	 * @param tour1
	 * @param tour2
	 * @param expect
	 */
	public void verifyEqualTours(TourInfo tour1, TourInfo tour2, boolean isEqual) {
		boolean flag = false;
		logger.info("Verify those two tours are "
				+ (isEqual ? "Equal" : "not equal."));
		flag = tour1.equals(tour2);
		if (flag != isEqual) {
			throw new ActionFailedException("This two tour are not equal.");
		}
		logger.info("Verify sucessfully");
	}

	/**
	 * goto TourDetailsPage From FacilityTourpage
	 * 
	 * @param tourCode
	 */
	public void gotoTourDetailsFromFacilityTourpageByTourName(String tourName) {
		InvMgrFacilityTourPage facilityTour = InvMgrFacilityTourPage
				.getInstance();
		InvMgrTourDetailsPage tourDetails = InvMgrTourDetailsPage.getInstance();
		logger.info("go to tour detail page from facility tour page");

		facilityTour.searchTour("All", "Tour Name", tourName);
		if (!facilityTour.isThisTourExist("All", "Tour Name", tourName)) {
			throw new ItemNotFoundException("The tour which Name is '" + tourName
					+ "' is not found in the list table.");
		}
		String code=facilityTour.getTourCodeByName(tourName);
		facilityTour.clickTourCode(code);
		browser.waitExists(facilityTour, tourDetails);

	}
	
	/**
	 * goto TourDetailsPage From FacilityTourpage
	 * 
	 * @param tourCode
	 */
	public void gotoTourDetailsFromFacilityTourpageByCode(String tourCode) {
		InvMgrFacilityTourPage facilityTour = InvMgrFacilityTourPage
				.getInstance();
		InvMgrTourDetailsPage tourDetails = InvMgrTourDetailsPage.getInstance();
		InvMgrComboTourDetailsPage comboTourDetails = InvMgrComboTourDetailsPage.getInstance();
		
		logger.info("go to tour detail page from facility tour page");

		facilityTour.searchTour("All", "Tour Code", tourCode);
		if (facilityTour.isTourCodeExist(tourCode)) {
			facilityTour.clickTourCode(tourCode);
		} else {
			throw new ItemNotFoundException("The tour which code is" + tourCode
					+ " is not found in the list table.");
		}
		browser.waitExists(VERY_LONG_SLEEP , tourDetails, comboTourDetails);//SHORT_SLEEP

	}
	
	
	
	/**
	 * Go to 'Tour Participant Data' page for 'Tour Details' page or "Combo Tour Details" page
	 * @param isComboTour
	 */
	public void gotoParticipantDataPageFromTourDetailspage(boolean isComboTour) {
		InvMgrTourDetailsPage tourDetails = InvMgrTourDetailsPage.getInstance();
		InvMgrComboTourDetailsPage comboTourDetails = InvMgrComboTourDetailsPage.getInstance();
		InvMgrParticipantDataPage participantDataPg = InvMgrParticipantDataPage.getInstance();

		logger.info("Go to 'Tour Participant Data' page for 'Tour Details' page.");
		if(isComboTour){
			comboTourDetails.clickParticipantData();
		}else{
			tourDetails.clickParticipantData();
		}
		ajax.waitLoading();
		participantDataPg.waitLoading();
	}

	/**
	 * Go to the Lotteries page
	 */
	public void gotoAddNewLottery(Lottery lottery) {
		InvMgrHomePage invHmPg = InvMgrHomePage.getInstance();
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		LocationSearchPage locationSearchPg = LocationSearchPage.getInstance();

		logger.info("Go to the add new lottery page");
		if(!lotterySearchPg.exists()){
			invHmPg.selectPageFromDropDownList("Lottery Setup");
			lotterySearchPg.waitLoading();
		}
		lotterySearchPg.clickAddNew();
		locationSearchPg.waitLoading();

		locationSearchPg.setSearchValue(lottery.location);
		locationSearchPg.selectLocationCategory(lottery.locationCategory);
		locationSearchPg.clickGo();
		locationSearchPg.clickSelect();
		lotteryDetailsPg.waitLoading();
	}

	public void selectLotterySetup() {
		InvMgrHomePage invHmPg = InvMgrHomePage.getInstance();
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		invHmPg.selectPageFromDropDownList("Lottery Setup");
		lotterySearchPg.waitLoading();
	}

	/**
	 * The method used to add new lottery
	 * 
	 * @param lottery
	 * @param isPermit
	 *            -indicate the product is permit or site
	 */
	public String addNewLottery(Lottery lottery) {
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		logger.info("Add New Lottery.");

		this.gotoAddNewLottery(lottery);
		String lotteryID = lotteryDetailsPg.addLotteryInfo(lottery);
		ajax.waitLoading();
		lotterySearchPg.waitLoading();
		logger.info("New Lottery id is: " + lotteryID);
		return lotteryID;
	}

	public String editLottery(Lottery lottery) {
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		logger.info("Edit existing Lottery.");

		lotteryDetailsPg.updateLotteryDetail(lottery.description, lottery.category,
				lottery.permitTypes, lottery.maxNumber);

		lotteryDetailsPg.clickApply();
		lotteryDetailsPg.waitLoading();

		String lotteryID = null;
		if (lotteryDetailsPg.isErrorOccur()) {
			throw new ErrorOnDataException(
					"Error on creating lottery application, please check your data.");
		} else {
			lotteryID = lotteryDetailsPg.getLotteryId();
		}
		return lotteryID;
	}

	/**
	 * Go to lottery details page from lottery search page
	 * @param lottery
	 */
	public void gotoLotteryDetailPageFromSearchPage(Lottery lottery){
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		logger.info("Go to lottery details page");
		/*if (lottery.id != null) {
			lotterySearchPg.setLotteryID(lottery.id);
		} else*/ if (lottery.name != null) {
			lotterySearchPg.setLotteryName(lottery.name);
		}

		lotterySearchPg.clickGo();
		Browser.sleep(1);
		lotterySearchPg.waitLoading();
		lotterySearchPg.clickLotteryID();
		lotteryDetailsPg.waitLoading();
	}
	
	/**
	 * go to lottery details page from inventory home page
	 * 
	 * @param lottery
	 */
	public void gotoLotteryDetailsPageFromHomePage(Lottery lottery) {
		logger.info("Go to lottery search page");
		this.gotoLotterySearchPage();
		this.gotoLotteryDetailPageFromSearchPage(lottery);
	}
	
	/**
	 * go to lottery details page from inventory home page
	 * 
	 * @param lottery
	 */
	public void gotoLotteryDetailsPageFromLotterySearchPage(Lottery lottery) {
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();

		logger.info("Go to lottery details page");
		if (lottery.id != null) {
			lotterySearchPg.setLotteryID(lottery.id);
		} else if (lottery.name != null) {
			lotterySearchPg.setLotteryName(lottery.name);
		}

		lotterySearchPg.clickGo();
		lotterySearchPg.waitLoading();
		lotterySearchPg.clickLotteryID();
		lotteryDetailsPg.waitLoading();
	}

	/**
	 * Go to lottery schedule search page from lottery detail page
	 */
	public void gotoLotteryScheduleSearchPgFromLotteryDetailPg() {
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage
				.getInstance();
		LotteryScheduleDetailsPage lotteryScheduleDetailsPg = LotteryScheduleDetailsPage
				.getInstance();

		logger.info("Go to lottery schedule detail page from lottery detail page.");
		lotteryDetailsPg.clickLotterySchedule();
		browser.waitExists(lotteryScheduleSearchPg, lotteryScheduleDetailsPg);
	}

	/**
	 * Go to lottery search/list page from inventory manager home page
	 */
	public void gotoLotterySearchPage() {
		InvMgrHomePage invHomePage = InvMgrHomePage.getInstance();
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();

		logger.info("Go to Lottery Search page from Inv Mgr home page.");
		invHomePage.selectPageFromDropDownList("Lottery Setup");
		lotterySearchPg.waitLoading();
	}

	/*
	 * Go to lottery schedule detail page
	 */
	public void gotoLotterySchdDetailPgFromLotterySchdSearchPg(
			String lotterySchdID) {
		LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage
				.getInstance();
		LotteryScheduleDetailsPage lotteryScheDetailPg = LotteryScheduleDetailsPage
				.getInstance();

		logger.info("Go to lottery schedule " + lotterySchdID + " detail page");
		this.searchLotterySchedule(lotterySchdID);
		lotteryScheduleSearchPg.clickLotteryScheduleID(lotterySchdID);
		lotteryScheDetailPg.waitLoading();
	}	

	/**
	 * Search lottery schedule id
	 * 
	 * @param lotterySchdID
	 */
	public void searchLotterySchedule(String lotterySchdID) {
		LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage
				.getInstance();

		logger.info("Search lottery schedule ID.");
		lotteryScheduleSearchPg.setLotteryScheduleID(lotterySchdID);
		lotteryScheduleSearchPg.clickGO();
		lotteryScheduleSearchPg.waitLoading();
	}

	/**
	 * set combo tour details From:combo tour details page To:combo tour details
	 * page End:combo tour details page
	 * 
	 * @param tour
	 */
	public void setComboTour(TourInfo tour) {
		InvMgrComboTourDetailsPage page = InvMgrComboTourDetailsPage
				.getInstance();
		logger.info("set combo tour information");

		page.setComboTour(tour);
		page.clickApplyButton();
		page.waitLoading();
	}

	/**
	 * get combo tour details From: Combo Tour Details page To : Combo Tour
	 * Details page End:Combo tour Details page
	 * 
	 * @return
	 */
	public TourInfo getComboTour() {
		InvMgrComboTourDetailsPage page = InvMgrComboTourDetailsPage
				.getInstance();
		logger.info("get combo tour information");

		TourInfo tour = new TourInfo();
		tour = page.getComboTour();
		return tour;
	}

	public String addInactiveLotterySchedule(LotterySchedule lotterySchedule,String prd_category) {
		return addLotterySchedule(lotterySchedule, false,prd_category);
	}
	
	public void addLotteryScheduleToScheduleDetailPg(){
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		LotteryScheduleDetailsPage lotteryScheduleDetailsPg = LotteryScheduleDetailsPage
				.getInstance();
		LotteryScheduleSearchPage lotteryScheSearchPg = LotteryScheduleSearchPage
				.getInstance();
		logger.info("Add Lottery Schedule to lottery schedule detail page");

		lotteryDetailsPg.clickLotterySchedule();
		Object objs = browser.waitExists(lotteryScheSearchPg,
				lotteryScheduleDetailsPg);
		if (lotteryScheSearchPg == objs) {
			lotteryScheSearchPg.clickAddNew();
			lotteryScheduleDetailsPg.waitLoading();
		}
	}

	/**
	 * The process of Adding Lottery Schedule
	 * 
	 * @param lotterySchedule
	 * @param isActive
	 *            - is active the created schedule
	 */
	public String addLotterySchedule(LotterySchedule lotterySchedule,
			boolean isActive,String prd_category) {
		LotteryScheduleDetailsPage lotteryScheduleDetailsPg = LotteryScheduleDetailsPage
				.getInstance();
		LotteryScheduleSearchPage lotteryScheSearchPg = LotteryScheduleSearchPage
				.getInstance();
		String toReturn = "";

		logger.info("Add Lottery Schedule");
		if(!lotteryScheduleDetailsPg.exists()){
			this.addLotteryScheduleToScheduleDetailPg();
		}
		lotteryScheduleDetailsPg.setupLotteryScheduleInfo(lotterySchedule,prd_category);

		lotteryScheduleDetailsPg.clickApply();
		ajax.waitLoading();
		lotteryScheduleDetailsPg.waitLoading();
		
		if(lotteryScheduleDetailsPg.checkErrorMessageIsExists()){
			toReturn = lotteryScheduleDetailsPg.getErrorMessage();
		} else {
			toReturn = lotteryScheduleDetailsPg.getLotteryScheduleID();
			logger.info("New Lottery Schedule id is: " + toReturn);
			lotteryScheduleDetailsPg.clickOK();
			ajax.waitLoading();
			Object pages = browser.waitExists(lotteryScheSearchPg,
					lotteryScheduleDetailsPg);

			if (isActive) {
				if (pages == lotteryScheSearchPg) {
					lotteryScheSearchPg.activeScheduleByID(toReturn);
				}
				if (pages == lotteryScheduleDetailsPg) {
					throw new ItemNotFoundException(
							"Schedule create failed, please check your data.");
				}
			}
		}

		return toReturn;
	}

	/*
	 * Edit Lottery schedule info
	 */
	public String editLotterySchedule(LotterySchedule lotterySchedule,String prd_category) {
		LotteryScheduleDetailsPage lotteryScheduleDetailsPg = LotteryScheduleDetailsPage
				.getInstance();
		LotteryScheduleSearchPage lotteryScheSearchPg = LotteryScheduleSearchPage
				.getInstance();

		logger.info("Edit Lottery Schedule Info.");
		if(!lotteryScheduleDetailsPg.exists()){
			this.gotoLotterySchdDetailPgFromLotterySchdSearchPg(lotterySchedule.id);
		}
		lotteryScheduleDetailsPg.setupLotteryScheduleInfo(lotterySchedule,prd_category);
		String toReturn = "";
		lotteryScheduleDetailsPg.clickApply();
		ajax.waitLoading();
		lotteryScheduleDetailsPg.waitLoading();
		toReturn = lotteryScheduleDetailsPg.getLotteryScheduleID();
		lotteryScheduleDetailsPg.clickOK();
		ajax.waitLoading();
		browser.waitExists(lotteryScheduleDetailsPg, lotteryScheSearchPg);
		
		if(lotteryScheduleDetailsPg.exists()){
			if(lotteryScheduleDetailsPg.checkErrorMessageIsExists()){
				toReturn = lotteryScheduleDetailsPg.getErrorMessage();
			}
		}
		return toReturn;
	}

	/**
	 * Verify the Lottery schedule Added
	 */
	public boolean verifyAddLotterySchedule(String errorMsg) {
		LotteryScheduleDetailsPage lotteryScheduleDetailsPg = LotteryScheduleDetailsPage
				.getInstance();

		logger.info("Verify the Add lottery Schedule");
		if (!lotteryScheduleDetailsPg.getErrorMessage().equals(errorMsg)) {
			logger.error("Expect message should be " + errorMsg
					+ ", but actually is "
					+ lotteryScheduleDetailsPg.getErrorMessage());
			return false;
		}

		return true;
	}

	/**
	 * Assign location to lottery
	 * 
	 * @param lottery
	 */
	public void assignLocationToLottery(Lottery lottery, boolean assign) {
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		LotteryParticipationPage lotteryPaticipationPg = LotteryParticipationPage
				.getInstance();
		LotteryAssignNewParticipationPage lotteryAssignNewParticiptionPg = LotteryAssignNewParticipationPage
				.getInstance();

		logger.info("Assign new location to lottery");
		if(lotteryDetailsPg.exists()){
			lotteryDetailsPg.clickLotteryParticiption();
			browser.waitExists(lotteryPaticipationPg,
					lotteryAssignNewParticiptionPg);
		}
		if (lotteryPaticipationPg.exists()) {
			lotteryPaticipationPg.clickAssignNew();
			lotteryAssignNewParticiptionPg.waitLoading();
		}

		lotteryAssignNewParticiptionPg.setUpParticipationInfo(lottery);
		//lotteryAssignNewParticiptionPg.clickOK();
		// If assign is true, click OK button. Otherwise click Cancel button. By lesley wang
		if (assign) {
			lotteryAssignNewParticiptionPg.clickOK();
			browser.waitExists(lotteryAssignNewParticiptionPg,lotteryPaticipationPg);
		} else {
			lotteryAssignNewParticiptionPg.clickCancel();
			lotteryPaticipationPg.waitLoading();
		}
		
	}

	/**
	 * Assign location to lottery
	 * 
	 * @param lottery
	 */

	public void assignLocationToLottery(Lottery lottery) {
		this.assignLocationToLottery(lottery, true);
	}

	/**
	 * Unassigns location to lottery
	 */
	public void unassignLocationToLottery() {
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		LotteryParticipationPage lotteryPaticipationPg = LotteryParticipationPage
				.getInstance();
		LotteryAssignNewParticipationPage newPage = LotteryAssignNewParticipationPage
				.getInstance();

		logger.info("Unassign new location to lottery");
		Object page = null;
		if (!lotteryPaticipationPg.exists()) {
			lotteryDetailsPg.clickLotteryParticiption();
			page = browser.waitExists(newPage, lotteryPaticipationPg);
		} else {
			page = lotteryPaticipationPg;
		}

		if (page instanceof LotteryParticipationPage) {
			lotteryPaticipationPg.selectAll();
			lotteryPaticipationPg.clickUnassign();

			lotteryPaticipationPg.waitLoading();
		}
	}

	/**
	 * Check if the location has been assigned to lottery
	 * 
	 * @author Lesley Wang
	 */
	public boolean checkLocationAssigned(String info) {
		LotteryDetailsPage lotteryDetailsPg = LotteryDetailsPage.getInstance();
		LotteryParticipationPage lotteryPaticipationPg = LotteryParticipationPage
				.getInstance();
		LotteryAssignNewParticipationPage lotteryAssignNewParticiptionPg = LotteryAssignNewParticipationPage
				.getInstance();
		boolean hasAssigned = false;
		
		logger.info("Check if the location: " + info + " has been assigned.");
		
		lotteryDetailsPg.clickLotteryParticiption();
		Object page = browser.waitExists(lotteryPaticipationPg,
				lotteryAssignNewParticiptionPg);
		if (page == lotteryPaticipationPg) {
			hasAssigned = lotteryPaticipationPg.getLotteryInfo().contains(info);
		}
		logger.info("It is " + hasAssigned + " that the location: " + info + " has been assigned");
		return hasAssigned;		
	}
	
	/**
	 * Search Lottery in lottery search page
	 * 
	 * @param criteria
	 *            -- Search Criteria
	 */
	public void searchLottery(LotterySearchCriteria criteria) {
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		InvMgrHomePage invHmPg = InvMgrHomePage.getInstance();

		logger.info("Search the lottery by search criteria");
		if (!lotterySearchPg.exists()) {
			invHmPg.selectPageFromDropDownList("Lottery Setup");
			lotterySearchPg.waitLoading();
		}

		if (criteria.lotteryId != null) {
			lotterySearchPg.setLotteryID(criteria.lotteryId);
		}
		if (criteria.lotteryName != null) {
			lotterySearchPg.setLotteryName(criteria.lotteryName);
		}
		if (StringUtil.notEmpty(criteria.coverage)) {
			lotterySearchPg.setCoverage(criteria.coverage);
		}

		if (StringUtil.notEmpty(criteria.revenueLocation)) {
			lotterySearchPg.setRevenLocation(criteria.revenueLocation);
		}

		if (StringUtil.notEmpty(criteria.status)) {
			if (criteria.status.equalsIgnoreCase("Active")) {
				lotterySearchPg.selectStatus("Active");
			} else if (criteria.status.equalsIgnoreCase("Inactive")) {
				lotterySearchPg.selectStatus("Inactive");
			}
		}

		if (StringUtil.notEmpty(criteria.applicableProductCategory)) {
			lotterySearchPg
					.selectProductCategory(criteria.applicableProductCategory);
		}

		if (StringUtil.notEmpty(criteria.applicableProductGroup)) {
			lotterySearchPg.selectProductGroup(criteria.applicableProductGroup);
		}

		if (StringUtil.notEmpty(criteria.applicablePermitCategory)) {
			lotterySearchPg
					.selectPermitCategory(criteria.applicablePermitCategory);
		}

		if (StringUtil.notEmpty(criteria.applicablePermitType)) {
			lotterySearchPg.selectPermitType(criteria.applicablePermitType);
		}

		lotterySearchPg.clickGo();
		lotterySearchPg.waitLoading();
	}

	/**
	 * 
	 * @param deactive
	 * @param lottery
	 */
	public void changeLotteryStatus(boolean deactive, Lottery lottery) {
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();

		logger.info("Deactive/active the lottery");
		if (lottery.id != null) {
			lotterySearchPg.setLotteryID(lottery.id);
		}
		if (lottery.name != null) {
			lotterySearchPg.setLotteryName(lottery.name);
		}

		lotterySearchPg.clickGo();
		lotterySearchPg.waitSearchResult(lottery.id);
		lotterySearchPg.waitLoading();

		lotterySearchPg.selectLottery();
		if (deactive) {
			lotterySearchPg.clickDeactive();
			ajax.waitLoading();
		} else {
			lotterySearchPg.clickActive();
			ajax.waitLoading();
		}
		lotterySearchPg.waitLoading();

	}

	/**
	 * This method is used to go to site detail page
	 * 
	 * @param facilityName
	 * @param siteID
	 * @param isNSS
	 *            : Non-Site-Specific Groups : Add new site/Add new Group
	 * @param isNew
	 * @return siteInfo
	 */
	public String gotoSiteDetailforCalculation(String facilityName,
			String siteID, boolean isNSS, boolean isNew) {
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage
				.getInstance();
		InvMgrSiteValidateFeesPage invFeePg = InvMgrSiteValidateFeesPage
				.getInstance();

		logger.info("Go to the site detail page");
		// Go to facility detail page
		this.gotoFacilityDetailsPg(facilityName);
		// Go to site detail page
		this.gotoSiteDetailPage(siteID, isNSS, isNew);

		// Get the Site table information
		String siteInfo = invSiteDetailPg.getSiteInfo();

		invSiteDetailPg.clickValidateFees();
		invFeePg.waitLoading();

		return siteInfo;
	}

	/**
	 * This method is used to go to site detail page
	 * 
	 * @param facilityName
	 * @param loopAreaID
	 * @param siteID
	 * @param isNSS
	 *            : Non-Site-Specific Groups : Add new site/Add new Group
	 * @param isNew
	 * @return siteInfo
	 */
	public String gotoSiteDetailFromLoopSiteForCalculation(String facilityName,
			String loopAreaID, String siteID, boolean isNSS, boolean isNew) {
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage
				.getInstance();

		logger.info("Go to the site detail page");
		// Go to facility detail page
		this.gotoFacilityDetailsPg(facilityName);
		// Go to site detail page
		this.gotoSiteDetailFromLoopSite(loopAreaID, siteID, isNSS, isNew);

		// Get the Site table information
		String siteInfo = invSiteDetailPg.getSiteInfo();

		this.gotoCalculationFromSiteDetail();

		return siteInfo;
	}

	public void gotoCalculationFromSiteDetail() {
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage
				.getInstance();
		InvMgrSiteValidateFeesPage invFeePg = InvMgrSiteValidateFeesPage
				.getInstance();

		invSiteDetailPg.clickValidateFees();
		invFeePg.waitLoading();
	}

	/**
	 * This method is used to go to site detail page
	 * 
	 * @param facilityName
	 * @param siteID
	 * @return siteInfo
	 */
	public String gotoSiteDetailforCalculation(String facilityName,
			String siteID) {
		logger.info("Go to the site detail page");

		// Go to facility detail page->Go to site detail page->Get site table
		// info
		return this.gotoSiteDetailforCalculation(facilityName, siteID, false,
				false);
	}

	/**
	 * This method is used to calculate fee
	 * 
	 * @param facilityName
	 * @param siteIDs
	 */
	public void calculateFee(FeeValidationData feeData) {
		InvMgrSiteValidateFeesPage invFeePg = InvMgrSiteValidateFeesPage
				.getInstance();
		logger.info("calculate fee");

		invFeePg.setFeeCalculationParameters(feeData);
		invFeePg.clickCalculateFee();
		invFeePg.waitLoading();
	}

	public void modifyAutoInvalidateTickets(String isAutoIncaliTic) {
		InvMgrFacilityDetailPage invDetails = InvMgrFacilityDetailPage
				.getInstance();
		InvMgrHomePage invHome = InvMgrHomePage.getInstance();
		logger.info("modify auto invalidate tickets");

		invDetails.selectAutoInvalidateTickets(isAutoIncaliTic);
		invDetails.setDescription("Auto test");//update it for avoiding warning message due to PCR for Description 
		invDetails.clickOK();
		ajax.waitLoading();
		invHome.waitLoading();
	}

	/**
	 * This method get check-in time and check-out time from facility page
	 * 
	 * @return
	 */
	public String[] getTimeFromFaciltyPg() {
		InvMgrTopMenuPage invTpMenPg = InvMgrTopMenuPage.getInstance();
		InvMgrFacilityDetailPage invDetails = InvMgrFacilityDetailPage
				.getInstance();
		logger.info("get check in and check out time from facility page");

		invTpMenPg.gotoSpecifiedDetailPage("Facility Details");
		invDetails.waitLoading();
		String[] time = new String[2];
		time[0] = invDetails.getCheckinTime();
		time[1] = invDetails.getCheckoutTime();
		return time;

	}

	/**
	 * This method get check-in time and check-out time from site detail page
	 * 
	 * @return
	 */
	public String[] getTimeFromSiteDetailPg() {
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage
				.getInstance();
		logger.info("get check in and check out time from site detail page");

		String[] time = new String[2];
		time[0] = invSiteDetailPg.getCheckinTime();
		time[1] = invSiteDetailPg.getCheckoutTime();
		return time;
	}

	/**
	 * This method modify check-in time or check-out time on site detail page
	 * 
	 * @param time
	 * @param isCheckin
	 * @param isCheckout
	 */
	public void updateCheckinAndCheckoutTime(String time, boolean isCheckin,
			boolean isCheckout) {
		InvMgrSiteDetailPage invSiteDetailPg = InvMgrSiteDetailPage
				.getInstance();
		InvMgrSitesPage invSitePg = InvMgrSitesPage.getInstance();
		logger.info("update check in and check out time from site detail page");

		if (isCheckin) {
			invSiteDetailPg.setCheckInTime(time);
		}
		if (isCheckout) {
			invSiteDetailPg.setCheckOutTime(time);
		}
		invSiteDetailPg.clickOK();
		invSitePg.waitLoading();
	}

	/**
	 * This method modify check-in time or check-out time on facility detail
	 * page
	 * 
	 * @param time
	 * @param isCheckin
	 * @param isCheckout
	 */
	public void updateCheckinAndCheckoutTimeFromFacility(String time,
			boolean isCheckin, boolean isCheckout) {
		InvMgrFacilityDetailPage invDetails = InvMgrFacilityDetailPage
				.getInstance();
		InvMgrHomePage invHome = InvMgrHomePage.getInstance();
		InvMgrTopMenuPage invTpMenPg = InvMgrTopMenuPage.getInstance();
		logger.info("update check in and check out time from facility page");

		invTpMenPg.gotoSpecifiedDetailPage("Facility Details");
		invDetails.waitLoading();

		if (isCheckin) {
			invDetails.setCheckInTime(time);
		}
		if (isCheckout) {
			invDetails.setCheckOutTime(time);
		}
		invDetails.clickOK();
		invHome.waitLoading();
	}

	/**
	 * Verify specific drop drop down list elements
	 * 
	 * @param objId
	 *            : the value of '.id' property
	 * @param expected
	 *            : all the value expected of specific drop down list
	 * @param defaultOpt
	 *            : the default value of specific drop down list
	 * @param firstOpt
	 *            : the first value of specific drop down list
	 */
	public void VerifyDropDownListElements(String objId, String defaultOpt,
			String firstOpt, List<String> expected) {
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		logger.info("Verify dropdown list options.");

		// Verify default value of drop down list
		String defaultValue = invClosurePg
				.getDefaultValueForDropDownList(objId);
		if (!defaultValue.equals(defaultOpt)) {
			throw new ErrorOnDataException(objId + " default value is not '"
					+ defaultOpt + "'.");
		}

		String options = invClosurePg.getDropDownElements(objId);
		String[] optionsArray = options.split("#");

		// Verify first value of drop down list
		if (!optionsArray[0].equals(firstOpt)) {
			throw new ErrorOnDataException(objId + "First Option Is Not'"
					+ firstOpt + "'.");
		}

		// Verify all value of drop down list
		for (int i = 0; i < expected.size(); i++) {
			if (!options.contains(expected.get(i))) {
				throw new ErrorOnDataException(objId + " Not Contain '"
						+ expected.get(i) + "' Option.");
			}
		}
	}
	
	/**
	 * This method create a new closure with out assign any site/slip to the closure
	 * @param closure
	 * @return
	 */
	public String createClosure(Closure closure) {
		InvMgrClosureDetailPage invClosureDetailPg = InvMgrClosureDetailPage.getInstance();
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		String closureID = "";

		logger.info("Add a new closure.");

		invClosurePg.clickAddNew();
		invClosureDetailPg.waitLoading();

		invClosureDetailPg.fillClosureDetails(closure);
		invClosureDetailPg.clickApply();
		invClosureDetailPg.waitLoading();
		
		closureID = invClosureDetailPg.getClosureID();
		logger.info("Closure:" + closureID +" has been created successfully!");
		invClosureDetailPg.clickOK();
		invClosurePg.waitLoading();
		
		return closureID;
	}

	/**
	 * Deactivate closure via closureID The start page and end page both are
	 * 'Site Closures page'
	 * 
	 * @param closureID
	 */
	public void deactivateClosure(String closureID) {
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		logger.info("Deactive closure");

		invClosurePg.selectClosureCheckBoxByID(closureID);
		invClosurePg.clickDeactivate();
		invClosurePg.waitLoading();
	}

	/**
	 * In closure page, verify the result of closure search
	 * 
	 * @param affectedOrdInd
	 * @param affectedOrdInstr
	 * @param createdAppID
	 * @param cloExpected
	 * @param countNum
	 */
	public void verifyClosureSearch(String affectedOrdInd,
			String affectedOrdInstr, String createdAppID,
			List<String> cloExpected, int countNum) {
		InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
		List<String> closureIDs = new ArrayList<String>();
		int count = 0;
		logger.info("verify closure search results");

		// initialize closure search criteria
		invClosurePg.selectAffectedReservations("All");
		invClosurePg.selectInstruction("All");
		invClosurePg.selectApplication("All");
		// select closure search criteria
		if (null != affectedOrdInd && affectedOrdInd.length() > 0) {
			invClosurePg.selectAffectedReservations(affectedOrdInd);
		}
		if (null != affectedOrdInstr && affectedOrdInstr.length() > 0) {
			invClosurePg.selectInstruction(affectedOrdInstr);
		}
		if (null != createdAppID && createdAppID.length() > 0) {
			invClosurePg.selectApplication(createdAppID);
		}

		invClosurePg.clickGo();
		invClosurePg.waitLoading();
		
		IHtmlObject[] objs = invClosurePg.getClosureListTable();
		IHtmlTable table = (IHtmlTable) objs[0];
		closureIDs = table.getColumnValues(1);
		for (int i = 0; i < closureIDs.size(); i++) {
			for (int j = 0; j < cloExpected.size(); j++) {
				if (closureIDs.get(i).equals(cloExpected.get(j))) {
					++count;
					break;
				}
			}
		}
		if (count != countNum) {
			throw new ErrorOnDataException(
					"The result of closures searching doesn't correct.");
		}
		Browser.unregister(objs);
	}

	/**
	 * The method execute going to change requests page from 1. Inventory
	 * Manager home page when user's login location is Region, Agency or
	 * Contract type 2. Facility detail page when user's login location is
	 * Facility type
	 */
	public void gotoChangeRequestPg() {
		InvMgrHomePage invMgrHmPg = InvMgrHomePage.getInstance();
		InvMgrChangeRequest imChangeRequest = InvMgrChangeRequest.getInstance();
		InvMgrFacilityDetailPage inFacilityDetailPg = InvMgrFacilityDetailPage
				.getInstance();
		logger.info("Goto Change Request page from Inventory Manager home page.");

		if (invMgrHmPg.exists()) {
			if (invMgrHmPg.checkTopDropDownListExist()) {
				invMgrHmPg.gotoSpecifiedDetailPage("Change Request");
			} else {
				if (invMgrHmPg.checkChangeRequestsExist()) {
					invMgrHmPg.clickChangeRequests();
				}
			}
		} else {
			if (inFacilityDetailPg.exists()) {
				invMgrHmPg.gotoSpecifiedDetailPage("Change Requests");
			}
		}

		imChangeRequest.waitLoading();
	}

	/**
	 * Verify search criteria in Change Request page
	 * 
	 * @param objId
	 * @param expected
	 * @param defaultOpt
	 * @param firstOpt
	 */
	public void VerifySearchCriteriaInChangeRequestPg(String objId,
			String defaultOpt, String firstOpt, List<String> expected) {
		InvMgrChangeRequest imChangeRequest = InvMgrChangeRequest.getInstance();
		logger.info("Verify selection criteria in change request page.");

		// Verify the default value
		String defaultValue = imChangeRequest
				.getDefaultValueOfDropDownList(objId);
		if (!defaultValue.equals(defaultOpt)) {
			throw new ErrorOnDataException(objId + " default value is not '"
					+ defaultOpt + "'.");
		}

		String options = imChangeRequest.getAllDropDownListElements(objId);
		String[] optionsArray = options.split("#");

		// Verify first value
		if (!optionsArray[0].equals(firstOpt)) {
			throw new ErrorOnDataException(objId + "First Option Is Not'"
					+ firstOpt + "'.");
		}

		// Verify all options for each sections.
		for (int i = 0; i < expected.size(); i++) {
			if (!options.contains(expected.get(i))) {
				throw new ErrorOnDataException(objId + " Not Contain '"
						+ expected.get(i) + "' Option.");
			}
		}
	}

	/**
	 * The method execute making change requests from Facility detail page and
	 * the end page is Change Requests page
	 * 
	 * @param fd
	 * @param isSubmitted
	 *            : --true: submitted the Change Request Items in Change Request
	 *            Items page --false: Doesn't submit the Change Request Items in
	 *            Change Request Items page
	 * @return
	 */
	public String makeChangeRequests(FacilityData fd, boolean isSubmitted) {
		InvMgrFacilityDetailPage inFacilityDetailPg = InvMgrFacilityDetailPage
				.getInstance();
		InvMgrTopMenuPage invTpMenPg = InvMgrTopMenuPage.getInstance();
		InvMgrChangeReqItemDetail inChangeRequestItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		InvMgrChangeReqItems inChangeRequestItems = InvMgrChangeReqItems
				.getInstance();
		InvMgrChangeRequest inChangeRequestsPg = InvMgrChangeRequest
				.getInstance();
		String changeRequestItemID, cjamgeRequestID = "";

		logger.info("Make change request.");

		invTpMenPg.clickChangeRequestMode();
		inFacilityDetailPg.waitLoading();

		updateFacilityDetails(fd, true);

		inChangeRequestItemDetail.clickApply();
		inChangeRequestItemDetail.waitLoading();

		changeRequestItemID = inChangeRequestItemDetail
				.getChangeRequestItemID();
		logger.info("-----Get change request item ID:"+changeRequestItemID);
		inChangeRequestItemDetail.clickOK();
		inChangeRequestItems.waitLoading();

		if (isSubmitted) {
			// Submitted all the Change Request Items with the status 'Draft'
			inChangeRequestItems.searchItemViaStatus("Draft");
			inChangeRequestItems.clickSearch();
			inChangeRequestItems.waitLoading();

			inChangeRequestItems.clickAllCheckBox();
			inChangeRequestItems.clickSubmit();
			inChangeRequestItems.waitLoading();

			// Get Change Request ID via searching associated change request
			// items
			inChangeRequestItems.clearUpSearchCriteria();
			inChangeRequestItems.searchItem(changeRequestItemID);

			cjamgeRequestID = inChangeRequestItems.getChangeRequestsID();
			inChangeRequestItems.clickChangeRequestsTab();
			inChangeRequestsPg.waitLoading();

			this.switchChangeEffectiveMode();
			inChangeRequestsPg.waitLoading();

			return cjamgeRequestID + " " + changeRequestItemID;
		} else {
			return changeRequestItemID;
		}

	}

	/**
	 * Make change request from change request items page
	 * 
	 * @param isSubmitted
	 *            --true: submitted the Change Request Items in Change Request
	 *            Items page --false: Doesn't submit the Change Request Items in
	 *            Change Request Items page
	 * @return
	 */
	public String makeChangeRequests(boolean isSubmitted, String requestType) {
		InvMgrChangeReqItemDetail inChangeRequestItemDetail = InvMgrChangeReqItemDetail
				.getInstance();
		InvMgrChangeReqItems inChangeRequestItems = InvMgrChangeReqItems
				.getInstance();
		InvMgrChangeRequest inChangeRequestsPg = InvMgrChangeRequest
				.getInstance();
		String changeRequestItemID, cjamgeRequestID = "";

		logger.info("Make change request.");

		inChangeRequestItemDetail.waitLoading();
		inChangeRequestItemDetail.clickApply();
		inChangeRequestItemDetail.waitLoading();

		changeRequestItemID = inChangeRequestItemDetail
				.getChangeRequestItemID();
		inChangeRequestItemDetail.clickOK();
		inChangeRequestItems.waitLoading();

		if (isSubmitted) {
			// Submitted all the Change Request Items with the status 'Draft'
			inChangeRequestItems.searchItemViaStatus("Draft");
			inChangeRequestItems.selectRequestType(requestType);
			inChangeRequestItems.clickSearch();
			inChangeRequestItems.waitLoading();

			inChangeRequestItems.clickAllCheckBox();
			inChangeRequestItems.clickSubmit();
			inChangeRequestItems.waitLoading();

			// Get Change Request ID via searching associated change request
			// items
			inChangeRequestItems.clearUpSearchCriteria();
			inChangeRequestItems.searchItem(changeRequestItemID);
			inChangeRequestItems.clickSearch();
			inChangeRequestItems.waitLoading();

			cjamgeRequestID = inChangeRequestItems.getChangeRequestsID();
			inChangeRequestItems.clickChangeRequestsTab();
			inChangeRequestsPg.waitLoading();

			this.switchChangeEffectiveMode();
			inChangeRequestsPg.waitLoading();

			return cjamgeRequestID + " " + changeRequestItemID;
		} else {

			this.switchChangeEffectiveMode();
			inChangeRequestItems.waitLoading();
			return changeRequestItemID;
		}
	}

	/**
	 * The method execute making multiple change requests from Facility detail
	 * page and the end page is Change Requests page
	 * 
	 * @param fd
	 * @param done
	 *            --true: Need make another change request --false: Needn't make
	 *            another change request
	 * @param isSubmitted
	 *            : --true: submitted the Change Request Items in Change Request
	 *            Items page --false: Doesn't submit the Change Request Items in
	 *            Change Request Items page
	 * @return
	 */
	public String makeMultiChangeRequest(FacilityData fd, boolean isSubmitted,
			boolean done) {
		InvMgrTopMenuPage invTpMenPg = InvMgrTopMenuPage.getInstance();
		InvMgrHomePage inHomePg = InvMgrHomePage.getInstance();
		logger.info("Make multiple change request.");

		String ids = this.makeChangeRequests(fd, isSubmitted);
		if (!done) {
			invTpMenPg.clickHome();
			inHomePg.waitLoading();
			this.gotoFacilityDetailsPg(fd.facilityName);
		}

		return ids;
	}

	public void updateFacilityDetails(FacilityData fd) {
		updateFacilityDetails(fd, true);
	}
	
	/**
	 * Update facility details data
	 * 
	 * @param fd
	 * @param isDismissable
	 */
	public void updateFacilityDetails(FacilityData fd, boolean isDismissable) {
		InvMgrFacilityDetailPage inFacilityDetailPg = InvMgrFacilityDetailPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail changeReqItemDetailsPg = InvMgrChangeReqItemDetail
				.getInstance();

		logger.info("Modify facility details page data.");

		if (!StringUtil.isEmpty(fd.facilityName)) {
			inFacilityDetailPg.modifyFacilityName(fd.facilityName);
		}
		if (!StringUtil.isEmpty(fd.shortName)) {
			inFacilityDetailPg.modifyFacilityShortName(fd.shortName);
		}
		if (!StringUtil.isEmpty(fd.description)) {
			inFacilityDetailPg.modifyFacilityDescription(fd.description);
		}
		if (!StringUtil.isEmpty(fd.productCategory)) {
			inFacilityDetailPg.selectProductCategory(fd.productCategory);
			inFacilityDetailPg.waitLoading();
		}
		if (!StringUtil.isEmpty(fd.mailingState)) {
			inFacilityDetailPg.selectMailToState(fd.mailingState);
		}
		if (!StringUtil.isEmpty(fd.brochureDescription)) {
			inFacilityDetailPg.modifyBrochureInfo(fd.brochureDescription);
		}
		if (!StringUtil.isEmpty(fd.drivingDirection)) {
			inFacilityDetailPg.modifyDriveDirection(fd.drivingDirection);
		}
		if (!StringUtil.isEmpty(fd.importantInfo)) {
			inFacilityDetailPg.modifyImportantInfo(fd.importantInfo);
		}

		//Financial Info
		if(!StringUtil.isEmpty(fd.financialSessionType)) {
			inFacilityDetailPg.selectFinancialSessionType(fd.financialSessionType);
		}
		inFacilityDetailPg.selectOpeningFloatRequired(fd.openingFloatRequired);
		inFacilityDetailPg.selectCloseBlindly(fd.closeBlindly);
		inFacilityDetailPg.selectTransmittals(fd.transmittals);
		inFacilityDetailPg.selectTransmittalTraceNumRequired(fd.transmittalTraceNumRequired);
		
		// List Management
		if(inFacilityDetailPg.isWaitingListDropDownListExist()){
			inFacilityDetailPg.selectWaitingList(fd.getIsWaitingList());
		}
		if(inFacilityDetailPg.isTransferListExist()){
			inFacilityDetailPg.selectTransferList(fd.getIsTransferList());
		}
		if(inFacilityDetailPg.ListEntrywithSlipRes()){
			inFacilityDetailPg.selectListEntrywithSlipRes(fd.getListEntryWithSlipRes());
		}
		inFacilityDetailPg.clickApply();
		Browser.sleep(SHORT_SLEEP_BEFORE_CHECK);
		confirmDialogPg.setDismissible(isDismissable);
		browser.waitExists(inFacilityDetailPg, confirmDialogPg,
				changeReqItemDetailsPg);
	}

	/**
	 * Update facility supplementary info data
	 * 
	 * @param fd
	 */
	public void updateFacilitySupplementaryInfo(FacilityData fd) {
		InvMgrFacilitySupplementaryInfo invSupplementaryInfo = InvMgrFacilitySupplementaryInfo
				.getInstance();

		logger.info("Modify facility supplementary info page data.");
		// Telecommunications
		if (null != fd.voiceLine && fd.voiceLine.length() > 0) {
			invSupplementaryInfo.selectVoiceLine(fd.voiceLine);
		}
		if (null != fd.faxLine && fd.faxLine.length() > 0) {
			invSupplementaryInfo.selectFaxLine(fd.faxLine);
		}
		if (null != fd.faxLocation && fd.faxLocation.length() > 0) {
			invSupplementaryInfo.selectFaxLocation(fd.faxLocation);
		}
		if (null != fd.faxDistanceToPark && fd.faxDistanceToPark.length() > 0) {
			invSupplementaryInfo.selectFaxDistanceToPark(fd.faxDistanceToPark);
		}
		// Internet
		if (null != fd.internetCommectivity
				&& fd.internetCommectivity.length() > 0) {
			invSupplementaryInfo
					.selectInternetConnectivity(fd.internetCommectivity);
		}
		if (null != fd.internetProvidedByRa
				&& fd.internetProvidedByRa.length() > 0) {
			invSupplementaryInfo
					.selectInternetProvidedByRa(fd.internetProvidedByRa);
		}
		if (null != fd.hardwareProvidedByRa
				&& fd.hardwareProvidedByRa.length() > 0) {
			invSupplementaryInfo
					.selectHardwareProvidedByRa(fd.hardwareProvidedByRa);
		}
		// Staffing
		if (null != fd.fullTimeStaff && fd.fullTimeStaff.length() > 0) {
			invSupplementaryInfo.selectFullTimeStaff(fd.fullTimeStaff);
		}
		if (null != fd.seasonalStaff && fd.seasonalStaff.length() > 0) {
			invSupplementaryInfo.selectSeasonalStaff(fd.seasonalStaff);
		}
		if (null != fd.summerVisitsPerWeek
				&& fd.summerVisitsPerWeek.length() > 0) {
			invSupplementaryInfo
					.selectSummerVisitsPerWeek(fd.summerVisitsPerWeek);
		}
		if (null != fd.winterVisitsPerWeek
				&& fd.winterVisitsPerWeek.length() > 0) {
			invSupplementaryInfo
					.selectWinterVisitsPerWeek(fd.winterVisitsPerWeek);
		}
		if (null != fd.distanceTravelledPerVisit
				&& fd.distanceTravelledPerVisit.length() > 0) {
			invSupplementaryInfo
					.selectDistanceTravelledPerVisit(fd.distanceTravelledPerVisit);
		}
		if (fd.isSummer) {
			invSupplementaryInfo.selectSummer();
		}
		if (fd.isWinter) {
			invSupplementaryInfo.selectWinter();
		}
		// Park Configuration
		if (null != fd.staffedEntrances && fd.staffedEntrances.length() > 0) {
			invSupplementaryInfo.selectStaffedEntrancesNum(fd.staffedEntrances);
		}
		if (null != fd.nonStaffedEntrances
				&& fd.nonStaffedEntrances.length() > 0) {
			invSupplementaryInfo
					.selectNonStaffedEntrancesNum(fd.nonStaffedEntrances);
		}
		if (null != fd.remoteSites && fd.remoteSites.length() > 0) {
			invSupplementaryInfo.selectRemoteSitesNum(fd.remoteSites);
		}
	}

	/**
	 * Go to Change Request items page from Change Request page
	 * 
	 * @param changeReqID
	 */
	public void gotoChangeRequestItemsPg(String changeReqID) {
		InvMgrChangeRequest inChangeRequestsPg = InvMgrChangeRequest
				.getInstance();
		InvMgrChangeRequestDetail inChangeRequestsDetailPg = InvMgrChangeRequestDetail
				.getInstance();
		InvMgrChangeReqItems inChangeRequestItemsPg = InvMgrChangeReqItems
				.getInstance();
		logger.info("Go to Change Request items(with id:"+changeReqID+") page.");

		if (!inChangeRequestsPg.exists()) {
			if (inChangeRequestsDetailPg.exists()) {
				inChangeRequestsDetailPg.clickChangeRequestsTab();
				inChangeRequestsPg.waitLoading();
			}
		}

		inChangeRequestsPg.searchChangeRequest(changeReqID);
		inChangeRequestsPg.clickChangeRequestItmes(changeReqID);
		inChangeRequestItemsPg.waitLoading();

	}

	/**
	 * Go to Change Request items page from Change Request items page
	 * 
	 * @param cRIID
	 *            -----Change Request Items ID
	 */
	public void gotoChangeRequestItemsDetailsPg(String cRIID) {
		InvMgrChangeReqItems inChangeResItemsPg = InvMgrChangeReqItems
				.getInstance();
		InvMgrChangeReqItemDetail inChangeResItemsDetailsPg = InvMgrChangeReqItemDetail
				.getInstance();
		logger.info("Go to Change Request items details page.");

		inChangeResItemsPg.searchItem(cRIID);

		inChangeResItemsPg.clickChangeRequestItmes(cRIID);
		inChangeResItemsDetailsPg.waitLoading();

	}

	/**
	 * Verify Change Request items status in change request items page
	 * 
	 * @param changeRequestItemsStatus
	 */
	public void verifyChangeRequestItemsStatus(String changeRequestItemsStatus) {
		InvMgrChangeReqItems inChangeRequestItemsPg = InvMgrChangeReqItems
				.getInstance();
		logger.info("Verify Change Request items status.");

		if (!inChangeRequestItemsPg.getChangeRequestsItemsStatus().equals(
				changeRequestItemsStatus)) {
			throw new ErrorOnDataException(
					"The status of change request items is not correct.");
		}

	}

	/**
	 * Update Change Requests status in change request detail page from change
	 * requests page and the end page is change requests page
	 * 
	 * @param changeRequest
	 */
	public void updateChangeRequests(ChangeRequestsInfo changeRequest) {
		InvMgrChangeRequest inChangeRequestsPg = InvMgrChangeRequest
				.getInstance();
		InvMgrChangeRequestDetail inChangeRequestDetailPg = InvMgrChangeRequestDetail
				.getInstance();
		InvMgrChangeReqItems inChangeRequestReqItemPg = InvMgrChangeReqItems
				.getInstance();
		logger.info("Update Change requests.");

		if (!inChangeRequestsPg.exists()) {
			if (inChangeRequestDetailPg.exists()) {
				inChangeRequestDetailPg.clickChangeRequestsTab();
			} else if (inChangeRequestReqItemPg.exists()) {
				inChangeRequestReqItemPg.clickChangeRequestsTab();
			}
			inChangeRequestsPg.waitLoading();
		}

		this.gotoChangeRequestDetailPg(changeRequest.cRID);

		this.updateChangeRequestStatus(changeRequest.cRModifyStatus);

		if (null != changeRequest.cRWarningMessage
				&& changeRequest.cRWarningMessage.length() > 0) {
			if (!inChangeRequestDetailPg.getWarningMessage().equals(
					changeRequest.cRWarningMessage)) {
				throw new ErrorOnDataException(
						"Warning message is not correct in Change Request Detail page.");
			}
		}

		inChangeRequestDetailPg.clickOK();
		inChangeRequestsPg.waitLoading();
	}

	/**
	 * Update Change Requests items status in change request items detail page
	 * from change requests page and the end page is change requests items page
	 * 
	 * @param changeRequest
	 */
	public void updateChangeRequestItems(ChangeRequestsInfo changeRequest) {
		InvMgrChangeReqItems inChangeRequestReqItemPg = InvMgrChangeReqItems
				.getInstance();
		InvMgrChangeReqItemDetail inChangeRequestReqItemDetailsPg = InvMgrChangeReqItemDetail
				.getInstance();
		logger.info("Update Change requests items.");

		if (!inChangeRequestReqItemPg.exists()) {
			this.gotoChangeRequestItemsPg(changeRequest.cRID);
		}

		this.gotoChangeRequestItemsDetailsPg(changeRequest.changeReqItems.cRIID);

		this.modifyChangeRequestItemsStatus(changeRequest.changeReqItems.cRIModifyStatus);

		inChangeRequestReqItemDetailsPg.clickOK();
		inChangeRequestReqItemPg.waitLoading();
	}

	/**
	 * Update change request status in change request detail page
	 * 
	 * @param modifyStatus
	 */
	public void updateChangeRequestStatus(String updateStatus) {
		InvMgrChangeRequestDetail inChangeRequestsDetailPg = InvMgrChangeRequestDetail
				.getInstance();
		logger.info("Update change request status in change request detail page.");

		inChangeRequestsDetailPg.selectStatus(updateStatus);
		inChangeRequestsDetailPg.clickApply();
//		ajax.waitLoading();
		inChangeRequestsDetailPg.waitLoading();
	}

	/**
	 * Modify change request status in change request items detail page
	 * 
	 * @param modifyStatus
	 */
	public void modifyChangeRequestItemsStatus(String modifyStatus) {
		InvMgrChangeReqItemDetail inChangeRequestsItemsDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		logger.info("Modify change request status in change request items detail page.");

		inChangeRequestsItemsDetailPg.selectStatus(modifyStatus);
		inChangeRequestsItemsDetailPg.clickApply();
		inChangeRequestsItemsDetailPg.waitLoading();
	}

	/**
	 * The method execute the work flow of verify all search result include
	 * every column whether match the search criteria
	 * 
	 * @param changeRequest
	 *            --The ChangeRequestsInfo changeRequest
	 */
	public void verifyChangeRequestSearchResult(ChangeRequestsInfo changeRequest) {
		InvMgrChangeRequest inChangeRequestsPg = InvMgrChangeRequest
				.getInstance();
		List<List<String>> changeRequestInfo = new ArrayList<List<String>>();
		changeRequestInfo.clear();
		List<String> changeRequestInfoRow = new ArrayList<String>();
		int colnum = 0;
		String uiDate = "";

		logger.info("Verify Change requests Search Result");

		changeRequestInfo = inChangeRequestsPg
				.retriveFirstPageChangeRequestsInfo();

		// Verify the search criteria 'Search Type' and 'Search Type ID' or
		// 'Search Type Value'
		if (null != changeRequest.searchType
				&& changeRequest.searchType.length() > 0) {
			if (changeRequest.searchType.equals("Change Request ID")) {
				colnum = inChangeRequestsPg.getColNum("Change Request ID");
			} else if (changeRequest.searchType
					.equals("Submitted User (Last Name)")) {
				colnum = inChangeRequestsPg.getColNum("Submitted User");
			} else if (changeRequest.searchType
					.equals("Closed User (Last Name)")) {
				colnum = inChangeRequestsPg.getColNum("Closed User");
			}

			if (changeRequestInfo.size() > 1) {
				for (int i = 1; i < changeRequestInfo.size(); i++) {
					changeRequestInfoRow = changeRequestInfo.get(i);

					if (!changeRequestInfoRow.get(colnum).toString().split(",")[0]
							.equalsIgnoreCase(changeRequest.searchTypeIdOrValue)) {
						throw new ItemNotFoundException(
								"The search Type ID or Value"
										+ changeRequestInfoRow.get(colnum)
										+ " doesn't match search criteria");
					}
				}
			} else {
				throw new ItemNotFoundException("No matched headline found ");
			}
		}

		// Verify the search criteria 'Status'
		if (null != changeRequest.cRStatus
				&& changeRequest.cRStatus.length() > 0) {
			colnum = inChangeRequestsPg.getColNum("Status");

			if (changeRequestInfo.size() > 1) {
				for (int i = 1; i < changeRequestInfo.size(); i++) {
					changeRequestInfoRow = changeRequestInfo.get(i);

					if (!changeRequestInfoRow.get(colnum).toString()
							.equalsIgnoreCase(changeRequest.cRStatus)) {
						throw new ItemNotFoundException("The Status "
								+ changeRequestInfoRow.get(colnum)
								+ " doesn't match search criteria");
					}
				}
			} else {
				throw new ItemNotFoundException("No matched location found ");
			}
		}

		// Verify the search criteria 'Date'
		if (null != changeRequest.searchDate
				&& changeRequest.searchDate.length() > 0) {
			if (changeRequest.searchDate.equals("Submitted Date")) {
				colnum = inChangeRequestsPg.getColNum("Submitted Date");
			} else {
				if (changeRequest.searchDate.equals("Closed Date")) {
					colnum = inChangeRequestsPg.getColNum("Closed Date");
				}
			}

			if (changeRequestInfo.size() > 1) {
				for (int i = 1; i < changeRequestInfo.size(); i++) {
					changeRequestInfoRow = changeRequestInfo.get(i);
					// Format date from UI
					uiDate = changeRequestInfoRow.get(colnum).toString();
					if (uiDate.contains("EST")) {
						uiDate = uiDate.split(" EST")[0];
					} else {
						uiDate = uiDate.split(" EDT")[0];
					}
					// Verify the date information
					if (null != changeRequest.searchFromDate
							&& changeRequest.searchFromDate.length() > 0
							&& changeRequest.searchToDate.equals("")) {
						logger.info("************************SearchFrom*****************************");
						if (DateFunctions.formatDate(uiDate, "MM/dd/yyyy")
								.compareTo(changeRequest.searchFromDate) < 0) {
							throw new ErrorOnDataException(
									"The Search Date for 'From' date "
											+ changeRequestInfoRow.get(colnum)
											+ " doesn't match search criteria");
						}
					} else if (null != changeRequest.searchToDate
							&& changeRequest.searchToDate.length() > 0
							&& changeRequest.searchFromDate.equals("")) {
						logger.info("************************SearchTo*****************************");
						if (DateFunctions.formatDate(uiDate, "MM/dd/yyyy")
								.compareTo(changeRequest.searchToDate) > 0) {
							throw new ErrorOnDataException(
									"The Search Date for 'To' date "
											+ changeRequestInfoRow.get(colnum)
											+ " doesn't match search criteria");
						}
					} else if (null != changeRequest.searchFromDate
							&& changeRequest.searchFromDate.length() > 0
							&& null != changeRequest.searchToDate
							&& changeRequest.searchToDate.length() > 0) {
						logger.info("************************SearchFromAndTo*****************************");
						if (DateFunctions.formatDate(uiDate, "MM/dd/yyyy")
								.compareTo(changeRequest.searchFromDate) < 0
								|| DateFunctions.formatDate(uiDate,
										"MM/dd/yyyy").compareTo(
										changeRequest.searchToDate) > 0) {
							throw new ErrorOnDataException(
									"The Search Date for 'From' and 'To' date "
											+ changeRequestInfoRow.get(colnum)
											+ " doesn't match search criteria");
						}
					}
				}
			} else {
				throw new ItemNotFoundException("No matched author found ");
			}
		}

	}

	/**
	 * Verify specific change request exist
	 * 
	 * @param changeRequestID
	 * @param exist
	 *            : if the change request should be exist
	 */
	public void verifySpecChangeRequsetsExist(String changeRequestID,
			boolean exist) {
		InvMgrChangeRequest inChangeRequestsPg = InvMgrChangeRequest
				.getInstance();
		List<String> changeRequestIDs = new ArrayList<String>();
		int flag = 0;
		logger.info("Verify specific Change Request ID exist ~~~");

		if (exist) {
			if (inChangeRequestsPg.checkObjectExist("Last")) {
				inChangeRequestsPg.clickLast();
				inChangeRequestsPg.waitLoading();
			}

			changeRequestIDs = inChangeRequestsPg.getChangeRequestIDs();

			for (int i = 0; i < changeRequestIDs.size(); i++) {
				if (changeRequestIDs.get(i).equals(changeRequestID)) {
					++flag;
					break;
				}
			}
			if (flag != 1) {
				throw new ErrorOnDataException(
						"The change request id should exist.");
			}
		} else
			logger.info("Not need to verify specific change request.");

	}

	/**
	 * The method execute going to Change Request Detail page from the Change
	 * Request page
	 * 
	 * @param changeReqID
	 */
	public void gotoChangeRequestDetailPg(String changeReqID) {
		InvMgrChangeRequest inChangeRequestsPg = InvMgrChangeRequest
				.getInstance();
		InvMgrChangeRequestDetail inChangeRequestsDetailPg = InvMgrChangeRequestDetail
				.getInstance();

		logger.info("Go to change request detail page.");

		inChangeRequestsPg.searchChangeRequest(changeReqID);
		inChangeRequestsPg.clickSearch();
		inChangeRequestsPg.waitLoading();

		inChangeRequestsPg.clickChangeRequestId(changeReqID);
		inChangeRequestsDetailPg.waitLoading();
	}

	/**
	 * Verify list values relative Change Request Status in change request
	 * detail page
	 * 
	 * @param defaultStatus
	 * @param validStatus
	 */
	public void verifyValidStatusRelativeChangeRequestStatus(
			String defaultStatus, List<String> validStatus) {
		InvMgrChangeRequestDetail inChangeRequestsDetailPg = InvMgrChangeRequestDetail
				.getInstance();
		List<String> uiStatus = new ArrayList<String>();
		logger.info("Verify List values relatived Change Request Status.");
        String status = inChangeRequestsDetailPg.getStatusDefaultElement();
		if (!status.equals(defaultStatus)) {
			throw new ErrorOnDataException(
					"The default value of status is not correct.", defaultStatus, status);
		}

		uiStatus = inChangeRequestsDetailPg.getStatusElements();
		for (int i = 0; i < uiStatus.size(); i++) {
			if (!validStatus.get(i).equals(uiStatus.get(i))) {
				throw new ErrorOnDataException(
						"The List values relative Change Request Status is not correct.", validStatus.get(i), uiStatus.get(i));
			}
		}
	}

	/**
	 * Verify Change Request details info in change request details page
	 * 
	 * @param changeRequest
	 */
	public void verifyChangeRequestDetails(ChangeRequestsInfo changeRequest) {
		InvMgrChangeRequestDetail inChangeRequestsDetailPg = InvMgrChangeRequestDetail
				.getInstance();
		logger.info("Verify change request details infomation");

		// 'Approved'
		if (null != changeRequest.cRApprovedDate
				&& changeRequest.cRApprovedDate.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cRApprovedDate)) {
				throw new ObjectNotFoundException("The object "
						+ changeRequest.cRApprovedDate + " not found.");
			}
		}
		if (null != changeRequest.cRApprovedUser
				&& changeRequest.cRApprovedUser.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cRApprovedUser)) {
				throw new ObjectNotFoundException("The object "
						+ changeRequest.cRApprovedUser + " not found.");
			}
		}
		if (null != changeRequest.cRApprovedLocation
				&& changeRequest.cRApprovedLocation.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cRApprovedLocation)) {
				throw new ObjectNotFoundException("The object "
						+ changeRequest.cRApprovedLocation + " not found.");
			}
		}
		// 'Rejected'
		if (null != changeRequest.cRRejectedDate
				&& changeRequest.cRRejectedDate.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cRRejectedDate)) {
				throw new ObjectNotFoundException("The object "
						+ changeRequest.cRRejectedDate + " not found.");
			}
		}
		if (null != changeRequest.cRRejectedUser
				&& changeRequest.cRRejectedUser.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cRRejectedUser)) {
				throw new ObjectNotFoundException("The object "
						+ changeRequest.cRRejectedUser + " not found.");
			}
		}
		if (null != changeRequest.cRRejectedLocation
				&& changeRequest.cRRejectedLocation.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cRRejectedLocation)) {
				throw new ObjectNotFoundException("The object "
						+ changeRequest.cRRejectedLocation + " not found.");
			}
		}
		// 'Opened'
		if (null != changeRequest.cROpenDate
				&& changeRequest.cROpenDate.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cROpenDate)) {
				throw new ObjectNotFoundException("The object "
						+ changeRequest.cROpenDate + " not found.");
			}
		}
		if (null != changeRequest.cROpenUser
				&& changeRequest.cROpenUser.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cROpenUser)) {
				throw new ObjectNotFoundException("The object "
						+ changeRequest.cROpenUser + " not found.");
			}
		}
		if (null != changeRequest.cROpenLocation
				&& changeRequest.cROpenLocation.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cROpenLocation)) {
				throw new ObjectNotFoundException("The object "
						+ "changeRequest.openLocation" + " not found.");
			}
		}
		// 'Closed'
		if (null != changeRequest.cRClosedDate
				&& changeRequest.cRClosedDate.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cRClosedDate)) {
				throw new ObjectNotFoundException("The object "
						+ changeRequest.cRClosedDate + " not found.");
			}
		}
		if (null != changeRequest.cRClosedUser
				&& changeRequest.cRClosedUser.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cRClosedUser)) {
				throw new ObjectNotFoundException("The object "
						+ changeRequest.cRClosedUser + " not found.");
			}
		}
		if (null != changeRequest.cRClosedLocation
				&& changeRequest.cRClosedLocation.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cRClosedLocation)) {
				throw new ObjectNotFoundException("The object "
						+ "changeRequest.closedLocation" + " not found.");
			}
		}
		// 'Request Comments'
		if (null != changeRequest.cRRequestComments
				&& changeRequest.cRRequestComments.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cRRequestComments)) {
				throw new ObjectNotFoundException("The object "
						+ changeRequest.cRRequestComments + " not found.");
			}
		}
		// 'Add to Comments'
		if (null != changeRequest.cRAddToComments
				&& changeRequest.cRAddToComments.length() > 0) {
			if (!inChangeRequestsDetailPg
					.checkObjectExist(changeRequest.cRAddToComments)) {
				throw new ObjectNotFoundException("The object "
						+ changeRequest.cRAddToComments + " not found.");
			}
		}
	}

	/**
	 * Verify Change Request details info in change request details page
	 * 
	 * @param changeRequest
	 */
	public void verifyChangeRequestItemsDetails(
			ChangeRequestsItemsInfo changeReqItems) {
		InvMgrChangeReqItemDetail inChangeReqItemsDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		logger.info("Verify change request items details infomation");

		// 'Draft'
		if (null != changeReqItems.cRIDraftDate
				&& changeReqItems.cRIDraftDate.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIDraftDate)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIDraftDate + " not found.");
			}
		}
		if (null != changeReqItems.cRIDraftUser
				&& changeReqItems.cRIDraftUser.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIDraftUser)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIDraftUser + " not found.");
			}
		}
		if (null != changeReqItems.cRIDraftLocation
				&& changeReqItems.cRIDraftLocation.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIDraftLocation)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIDraftLocation + " not found.");
			}
		}

		// 'Submit'
		if (null != changeReqItems.cRISubmmitedDate
				&& changeReqItems.cRISubmmitedDate.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRISubmmitedDate)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRISubmmitedDate + " not found.");
			}
		}
		if (null != changeReqItems.cRISubmmitedUser
				&& changeReqItems.cRISubmmitedUser.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRISubmmitedUser)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRISubmmitedUser + " not found.");
			}
		}
		if (null != changeReqItems.cRISubmmitedLocation
				&& changeReqItems.cRISubmmitedLocation.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRISubmmitedLocation)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRISubmmitedLocation + " not found.");
			}
		}

		// 'Approved'
		if (null != changeReqItems.cRIApprovedDate
				&& changeReqItems.cRIApprovedDate.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIApprovedDate)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIApprovedDate + " not found.");
			}
		}
		if (null != changeReqItems.cRIApprovedUser
				&& changeReqItems.cRIApprovedUser.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIApprovedUser)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIApprovedUser + " not found.");
			}
		}
		if (null != changeReqItems.cRIApprovedLocation
				&& changeReqItems.cRIApprovedLocation.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIApprovedLocation)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIApprovedLocation + " not found.");
			}
		}
		// 'Rejected'
		if (null != changeReqItems.cRIRejectedDate
				&& changeReqItems.cRIRejectedDate.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIRejectedDate)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIRejectedDate + " not found.");
			}
		}
		if (null != changeReqItems.cRIRejectedUser
				&& changeReqItems.cRIRejectedUser.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIRejectedUser)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIRejectedUser + " not found.");
			}
		}
		if (null != changeReqItems.cRIRejectedLocation
				&& changeReqItems.cRIRejectedLocation.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIRejectedLocation)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIRejectedLocation + " not found.");
			}
		}
		// 'Opened'
		if (null != changeReqItems.cRIOpenDate
				&& changeReqItems.cRIOpenDate.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIOpenDate)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIOpenDate + " not found.");
			}
		}
		if (null != changeReqItems.cRIOpenUser
				&& changeReqItems.cRIOpenUser.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIOpenUser)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIOpenUser + " not found.");
			}
		}
		if (null != changeReqItems.cRIOpenLocation
				&& changeReqItems.cRIOpenLocation.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIOpenLocation)) {
				throw new ObjectNotFoundException("The object "
						+ "changeRequest.openLocation" + " not found.");
			}
		}
		// 'Closed'
		if (null != changeReqItems.cRIClosedDate
				&& changeReqItems.cRIClosedDate.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIClosedDate)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIClosedDate + " not found.");
			}
		}
		if (null != changeReqItems.cRIClosedUser
				&& changeReqItems.cRIClosedUser.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIClosedUser)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIClosedUser + " not found.");
			}
		}
		if (null != changeReqItems.cRIClosedLocation
				&& changeReqItems.cRIClosedLocation.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIClosedLocation)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIClosedLocation + " not found.");
			}
		}
		// 'Request Item Details'
		if (null != changeReqItems.cRIFieldName
				&& changeReqItems.cRIFieldName.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIFieldName)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIFieldName + " not found.");
			}
		}
		if (null != changeReqItems.cRIOriginalValue
				&& changeReqItems.cRIOriginalValue.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIOriginalValue)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIOriginalValue + " not found.");
			}
		}
		if (null != changeReqItems.cRICurrentValue
				&& changeReqItems.cRICurrentValue.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRICurrentValue)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRICurrentValue + " not found.");
			}
		}
		if (null != changeReqItems.cRIRequestValue
				&& changeReqItems.cRIRequestValue.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIRequestValue)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIRequestValue + " not found.");
			}
		}
		// 'Request Comments'
		if (null != changeReqItems.cRIRequestItemComments
				&& changeReqItems.cRIRequestItemComments.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIRequestItemComments)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIRequestItemComments + " not found.");
			}
		}
		// 'Add to Comments'
		if (null != changeReqItems.cRIAddToComments
				&& changeReqItems.cRIAddToComments.length() > 0) {
			if (!inChangeReqItemsDetailPg
					.checkObjectExist(changeReqItems.cRIAddToComments)) {
				throw new ObjectNotFoundException("The object "
						+ changeReqItems.cRIAddToComments + " not found.");
			}
		}
	}

	/**
	 * Verify warning message
	 * 
	 * @param warningMessage
	 */
	public void verifyWarningMessage(String warningMessage) {
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();

		InvMgrFacilityBookingRulesPage seasons = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrSeasonDetailPage inSeasonDetailPg = InvMgrSeasonDetailPage
				.getInstance();
		InvMgrSeasonSitesPage inSeasonSitesPg = InvMgrSeasonSitesPage
				.getInstance();

		InvMgrClosuresPage inClosurePg = InvMgrClosuresPage.getInstance();
		InvMgrClosureDetailPage inClosureDetailPg = InvMgrClosureDetailPage
				.getInstance();
		InvMgrClosuresSitesPage closureSitesPg = InvMgrClosuresSitesPage
				.getInstance();

		InvMgrCampingUnitPage inCampingUnitPg = InvMgrCampingUnitPage
				.getInstance();
		InvMgrCampingUnitSitesPage inCampingUnitSitePg = InvMgrCampingUnitSitesPage
				.getInstance();
		InvMgrCampingUnitLoopsPage inCampingUnitLoopsPg = InvMgrCampingUnitLoopsPage
				.getInstance();
		InvMgrCampingUnitDetailsPage inCampingUnitDetailsPg = InvMgrCampingUnitDetailsPage
				.getInstance();

		InvMgrNoteAndAlertListPage imNoteOrAlertList = InvMgrNoteAndAlertListPage
				.getInstance();
		InvMgrNoteOrAlertDetailPage imNoteOrAlertDetailsPg = InvMgrNoteOrAlertDetailPage
				.getInstance();
		InvMgrNoteOrAlertSitesPage inNoteAndAlertSitePg = InvMgrNoteOrAlertSitesPage
				.getInstance();
		InvMgrNoteOrAlertLoopsPage inNoteAndAlertloopsPg = InvMgrNoteOrAlertLoopsPage
				.getInstance();

		InvMgrLoopAreasPage invLoopAreaPg = InvMgrLoopAreasPage.getInstance();
		InvMgrLoopAreaSitePage invLoopAreaSitePg = InvMgrLoopAreaSitePage
				.getInstance();
		InvMgrLoopDetailsPage invLoopAreaDetailsPg = InvMgrLoopDetailsPage
				.getInstance();

		InvMgrSitesPage imSitesPg = InvMgrSitesPage.getInstance();
		InvMgrNonSiteSpecGroupPage imNonSiteSpecGroupPg = InvMgrNonSiteSpecGroupPage
				.getInstance();

		InvMgrServiceActivitiesPage imServiceActivitiesPg = InvMgrServiceActivitiesPage
				.getInstance();
		InvMgrEventsPage imEventPg = InvMgrEventsPage.getInstance();
		InvMgrEventDetailPage imEventDetailsPg = InvMgrEventDetailPage
				.getInstance();

		InvMgrMapViewPage invMapPg = InvMgrMapViewPage.getInstance();

		InvMgrFacilityDetailPage invDetails = InvMgrFacilityDetailPage
				.getInstance();
		InvMgrFacilitySupplementaryInfo invSupplementaryInfo = InvMgrFacilitySupplementaryInfo
				.getInstance();
		
        String actualErrorMes = "";
		logger.info("Verify warning message...");
		if (confirmDialogPg.exists()) {
			String confirmDialogMes = confirmDialogPg.text().toString().trim().replace(".", "\\.").replace("?", "\\?").replace(" ", "");
//        	String confirmDialogMes = confirmDialogPg.text().toString().trim();
			warningMessage = warningMessage.replace(" ", "");
			
			if (!warningMessage.matches(confirmDialogMes)) {
				throw new ErrorOnDataException("Warning message is wrong!", warningMessage, confirmDialogMes);
			}
			confirmDialogPg.clickOK();
			inChangeReqItemDetailPg.waitLoading();
		} else {
			// For season
			if (seasons.exists()) {
				actualErrorMes = seasons.getWarningMessage();
				if (!actualErrorMes.equals(warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in season page.", warningMessage, actualErrorMes);
				}
			} else if (inSeasonDetailPg.exists()) {
				actualErrorMes = inSeasonDetailPg.getWarningMessage();
				if (!actualErrorMes.equals(warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in season detail page.",warningMessage, actualErrorMes);
				}
			} else if (inSeasonSitesPg.exists()) {
				actualErrorMes = inSeasonSitesPg.getWarningMessage();
				if (!actualErrorMes.equals(warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in season site page.",warningMessage, actualErrorMes);
				}
				// For closure
			} else if (inClosurePg.exists()) {
				actualErrorMes = inClosurePg.getWarningMessage();
				if (!actualErrorMes.equals(warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in closure list.",warningMessage, actualErrorMes);
				}
			} else if (inClosureDetailPg.exists()) {
				actualErrorMes = inClosureDetailPg.getWarningMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in closure detail page.",warningMessage, actualErrorMes);
				}
			} else if (closureSitesPg.exists()) {
				actualErrorMes = closureSitesPg.getWarningMessage();
				if (!actualErrorMes.equals(warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in closure site page.",warningMessage, actualErrorMes);
				}
				// For camping units
			} else if (inCampingUnitPg.exists()) {
				actualErrorMes = inCampingUnitPg.getWarningMessage();
				if (!actualErrorMes.equals(warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in camping units page.",warningMessage, actualErrorMes);
				}
			} else if (inCampingUnitSitePg.exists()) {
				actualErrorMes = inCampingUnitSitePg.getWarningMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in camping units sites page.",warningMessage, actualErrorMes);
				}
			} else if (inCampingUnitLoopsPg.exists()) {
				actualErrorMes = inCampingUnitLoopsPg.getWarningMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in camping units loops page.",warningMessage, actualErrorMes);
				}
			} else if (inCampingUnitDetailsPg.exists()) {
				if (!inCampingUnitDetailsPg.checkWarningMessage(warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in camping units details page.");
				}
				// For Note or Alerts
			} else if (imNoteOrAlertList.exists()) {
				actualErrorMes = imNoteOrAlertList.getWarningMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in note or alerts page.",warningMessage, actualErrorMes);
				}
			} else if (inNoteAndAlertSitePg.exists()) {
				actualErrorMes = inNoteAndAlertSitePg.getWarningMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in note or alerts sites page.",warningMessage, actualErrorMes);
				}
			} else if (inNoteAndAlertloopsPg.exists()) {
				actualErrorMes = inNoteAndAlertloopsPg.getWarningMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in note or alerts loops page.",warningMessage, actualErrorMes);
				}
			} else if (imNoteOrAlertDetailsPg.exists()) {
				actualErrorMes = imNoteOrAlertDetailsPg.getNoticeMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in note or alerts details page.",warningMessage, actualErrorMes);
				}
				// For Loop or Area
			} else if (invLoopAreaPg.exists()) {
				actualErrorMes = invLoopAreaPg.getWarningMessage();
				if (!actualErrorMes.equals(warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in loop or area list page.",warningMessage, actualErrorMes);
				}
			} else if (invLoopAreaSitePg.exists()) {
				actualErrorMes = invLoopAreaSitePg.getWarningMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in loop or area site page.",warningMessage, actualErrorMes);
				}
			} else if (invLoopAreaDetailsPg.exists()) {
				actualErrorMes = invLoopAreaDetailsPg.getWarningMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in loop or area details page.",warningMessage, actualErrorMes);
				}
				// For Site
			} else if (imSitesPg.exists()) {
				actualErrorMes = imSitesPg.getWarningMessage();
				if (!actualErrorMes.equals(warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in site page.",warningMessage, actualErrorMes);
				}
			} else if (imNonSiteSpecGroupPg.exists()) {
				actualErrorMes = imNonSiteSpecGroupPg.getWarningMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in non site specific group page.",warningMessage, actualErrorMes);
				}
			} else if (imNonSiteSpecGroupPg.exists()) {
				actualErrorMes = imNonSiteSpecGroupPg.getWarningMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in non site specific group page.",warningMessage, actualErrorMes);
				}
			}
			// For services, activities and event
			else if (imServiceActivitiesPg.exists()) {
				actualErrorMes = imServiceActivitiesPg.getWarningMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in services and activities page.",warningMessage, actualErrorMes);
				}
			} else if (imEventPg.exists()) {
				actualErrorMes = imEventPg.getWarningMessage();
				if (!actualErrorMes.equals(warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in event page.",warningMessage, actualErrorMes);
				}
			} else if (imEventDetailsPg.exists()) {
				actualErrorMes = imEventDetailsPg.getOneWarningMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in event details page.",warningMessage, actualErrorMes);
				}
			}
			// For Map
			else if (invMapPg.exists()) {
				actualErrorMes = invMapPg.getWarningMessage();
				if (!actualErrorMes.equals(warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in Map details page.",warningMessage, actualErrorMes);
				}
			}
			// For facility details
			else if (invDetails.exists()) {
				actualErrorMes = invDetails.getWarningMessage();
				if (!actualErrorMes.equals(warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in facility details page.",warningMessage, actualErrorMes);
				}
			} else if (invSupplementaryInfo.exists()) {
				actualErrorMes = invSupplementaryInfo.getWarningMessage();
				if (!actualErrorMes.equals(
						warningMessage)) {
					throw new ErrorOnDataException(
							"Warning message is not correct in facility supplementary info page.",warningMessage, actualErrorMes);
				}
			}
		}
	}

	/** Switch between Change Request Mode and Change Immediate Mode */
	public void switchChangeEffectiveMode() {
		InvMgrTopMenuPage inTopMenuePg = InvMgrTopMenuPage.getInstance();

		if (inTopMenuePg.isChangeRequestMode()) {
			inTopMenuePg.clickChangeImmediateMode();
		} else {
			inTopMenuePg.clickChangeRequestMode();
		}
		ajax.waitLoading();
		inTopMenuePg.waitLoading();
	}

	/**
	 * Delete the season from the season page
	 * 
	 * @param dismissableConfirmDialog
	 *            : If make the confirmDialog exist.
	 * @param dismissable
	 *            : For confirmDialog, dismiss or not.
	 * @param seasonIDs
	 */
	public void deleteSeason(boolean dismissable, String... seasonIDs) {
		InvMgrFacilityBookingRulesPage inSeason = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrConfimActionPage inConfirmActionPg = InvMgrConfimActionPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		ConfirmationDialogWidget confirmWidgt = new ConfirmationDialogWidget();
		logger.info("Delete the seasons.");
				if (!inSeason.exists()) {
					goBackToSeasonPg();
				}

				inSeason.selectSeasonsCheckBoxByIDs(seasonIDs);
				inSeason.clickDelete();
				ajax.waitLoading();
		
				confirmDialogPg.setDismissible(dismissable);
		Object pages = browser.waitExists(confirmWidgt, inConfirmActionPg, confirmDialogPg,
				inSeason, inChangeReqItemDetailPg);
		
		if (pages.equals(inConfirmActionPg)) {
			inConfirmActionPg.clickOK();
			inSeason.waitLoading();
		} else {
			if (dismissable ){
				if(pages.equals(confirmDialogPg)){
					confirmDialogPg.clickOK();
					browser.waitExists(inSeason, inChangeReqItemDetailPg);
				}else{
					if(pages.equals(confirmWidgt)){
						confirmWidgt.clickOK();
						ajax.waitLoading();
					}
				}
			}
		}
	}
	
	public void deleteAllSeasons(boolean dismissable) {
		InvMgrFacilityBookingRulesPage inSeason = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrConfimActionPage inConfirmActionPg = InvMgrConfimActionPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		logger.info("Delete all seasons.");
//
//		if (!inSeason.exists()) {
//			goBackToSeasonPg();
//		}

		inSeason.selectAllSeasonCheckBoxs();
		inSeason.clickDelete();

		confirmDialogPg.setDismissible(dismissable);
		Object pages = browser.waitExists(inConfirmActionPg, confirmDialogPg,
				inSeason, inChangeReqItemDetailPg);

		if (pages.equals(inConfirmActionPg)) {
			inConfirmActionPg.clickOK();
			inSeason.waitLoading();
		} else {
			if (dismissable && pages.equals(confirmDialogPg)) {
				confirmDialogPg.clickOK();
				browser.waitExists(inSeason, inChangeReqItemDetailPg);
			}
		}
	}
	

	/** Go back to season page */
	public void goBackToSeasonPg() {
		InvMgrFacilityBookingRulesPage inSeason = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrTopMenuPage inTopMenu = InvMgrTopMenuPage.getInstance();
		logger.info("Go back to season page");

		inTopMenu.gotoSpecifiedDetailPage("Booking Rules");
		ajax.waitLoading();
		inSeason.waitLoading();
	}

	/**
	 * Verify the information in the section of 'Request Item Details' in Change
	 * Request Item Details page
	 * 
	 * @param changeRqItems
	 */
	public void verifyRequestItemDetails(ChangeRequestsItemsInfo changeRqItems) {
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		// Request Item Details: ID, Type
		// Application: Activate-->season, closure
		// Deactivate-->season, closure
		// Assign-->camping unit loops
		// Remove-->Camping unit loops
		if (null != changeRqItems.cRIRequestItemDetailsIDs
				&& changeRqItems.cRIRequestItemDetailsIDs.size() > 0) {
			for (int i = 0; i < changeRqItems.cRIRequestItemDetailsIDs.size(); i++) {
				if (!inChangeReqItemDetailPg
						.checkObjectExist(changeRqItems.cRIRequestItemDetailsIDs
								.get(i))) {
					throw new ErrorOnDataException(
							changeRqItems.cRIRequestItemDetailsIDs.get(i)
									+ " should exist.");
				}
			}
		}
		if (null != changeRqItems.cRIRequestItemDetailsTypes
				&& changeRqItems.cRIRequestItemDetailsTypes.size() > 0) {
			for (int i = 0; i < changeRqItems.cRIRequestItemDetailsTypes.size(); i++) {
				if (!inChangeReqItemDetailPg
						.checkObjectExist(changeRqItems.cRIRequestItemDetailsTypes
								.get(i))) {
					throw new ErrorOnDataException(
							changeRqItems.cRIRequestItemDetailsTypes.get(i)
									+ " should exist.");
				}
			}
		}
		// Request Item Details: ID, Code, Name, Loop
		// Application: Assign-->season site, closure site, camping unit sites
		// Remove-->season site, closure site
		if (null != changeRqItems.cRIRequestItemDetailsCodes
				&& changeRqItems.cRIRequestItemDetailsCodes.size() > 0) {
			for (int i = 0; i < changeRqItems.cRIRequestItemDetailsCodes.size(); i++) {
				if (!inChangeReqItemDetailPg
						.checkObjectExist(changeRqItems.cRIRequestItemDetailsCodes
								.get(i))) {
					throw new ErrorOnDataException(
							changeRqItems.cRIRequestItemDetailsCodes.get(i)
									+ " should exist.");
				}
			}
		}
		if (null != changeRqItems.cRIRequestItemDetailsNames
				&& changeRqItems.cRIRequestItemDetailsNames.size() > 0) {
			for (int i = 0; i < changeRqItems.cRIRequestItemDetailsNames.size(); i++) {
				if (!inChangeReqItemDetailPg
						.checkObjectExist(changeRqItems.cRIRequestItemDetailsNames
								.get(i))) {
					throw new ErrorOnDataException(
							changeRqItems.cRIRequestItemDetailsNames.get(i)
									+ " should exist.");
				}
			}
		}
		if (null != changeRqItems.cRIRequestItemDetailsLoops
				&& changeRqItems.cRIRequestItemDetailsLoops.size() > 0) {
			for (int i = 0; i < changeRqItems.cRIRequestItemDetailsLoops.size(); i++) {
				if (!inChangeReqItemDetailPg
						.checkObjectExist(changeRqItems.cRIRequestItemDetailsLoops
								.get(i))) {
					throw new ErrorOnDataException(
							changeRqItems.cRIRequestItemDetailsLoops.get(i)
									+ " should exist.");
				}
			}
		}
		// Request Item Details: Field/Attribute Name, Original Value, Current
		// Value, Request Value
		// For the preceding two items: Add new season
		// For all the items: Edit season, add new camping units
		if (null != changeRqItems.cRIReqItemDetailsFieldName
				&& changeRqItems.cRIReqItemDetailsFieldName.size() > 0) {
			for (int i = 0; i < changeRqItems.cRIReqItemDetailsFieldName.size(); i++) {
				if (!inChangeReqItemDetailPg
						.checkObjectExist(changeRqItems.cRIReqItemDetailsFieldName
								.get(i))) {
					throw new ErrorOnDataException(
							changeRqItems.cRIReqItemDetailsFieldName.get(i)
									+ " should exist.");
				}
			}
		}
		if (null != changeRqItems.cRIReqItemDetailsRequestValue
				&& changeRqItems.cRIReqItemDetailsRequestValue.size() > 0) {
			for (int i = 0; i < changeRqItems.cRIReqItemDetailsFieldName.size(); i++) {
				if (!inChangeReqItemDetailPg
						.checkObjectExist(changeRqItems.cRIReqItemDetailsRequestValue
								.get(i))) {
					throw new ErrorOnDataException(
							changeRqItems.cRIReqItemDetailsRequestValue.get(i)
									+ " should exist.");
				}
			}
		}
		if (null != changeRqItems.cRIRequestItemDetailsOriginalValue
				&& changeRqItems.cRIRequestItemDetailsOriginalValue.size() > 0) {
			for (int i = 0; i < changeRqItems.cRIReqItemDetailsFieldName.size(); i++) {
				if (!inChangeReqItemDetailPg
						.checkObjectExist(changeRqItems.cRIRequestItemDetailsOriginalValue
								.get(i))) {
					throw new ErrorOnDataException(
							changeRqItems.cRIRequestItemDetailsOriginalValue
									.get(i) + " should exist.");
				}
			}
		}
		if (null != changeRqItems.cRIRequestItemDetailsCurrentValue
				&& changeRqItems.cRIRequestItemDetailsCurrentValue.size() > 0) {
			for (int i = 0; i < changeRqItems.cRIReqItemDetailsFieldName.size(); i++) {
				if (!inChangeReqItemDetailPg
						.checkObjectExist(changeRqItems.cRIRequestItemDetailsCurrentValue
								.get(i))) {
					throw new ErrorOnDataException(
							changeRqItems.cRIRequestItemDetailsCurrentValue
									.get(i) + " should exist.");
				}
			}
		}
		// Request Item Details information
		if (null != changeRqItems.cRIRequestItemDetails
				&& changeRqItems.cRIRequestItemDetails.length() > 0) {
			if (!inChangeReqItemDetailPg.getRequestItemDetail().replaceAll("\\s", "").equals(
					changeRqItems.cRIRequestItemDetails.replaceAll("\\s", ""))) {
				throw new ErrorOnDataException(
						"The 'Request Item Details' info --- "
								+ changeRqItems.cRIRequestItemDetails
								+ " --- is not correct.",changeRqItems.cRIRequestItemDetails, inChangeReqItemDetailPg.getRequestItemDetail());
			}
		}
	}

	/**
	 * The method execute going to season site page from season page
	 * 
	 * @param seasonID
	 */
	public void gotoSeasonSitesPg(String seasonID) {
		InvMgrFacilityBookingRulesPage season = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrSeasonSitesPage seasonSitePg = InvMgrSeasonSitesPage
				.getInstance();
		logger.info("Go to season sites page from season page.");

		if (!season.exists()) {
			this.gotoSeasonsPg();
		}
		season.clickNumSites(seasonID);
		seasonSitePg.waitLoading();
	}
	
	/**
	 * The method execute going to season slip page from season page
	 * 
	 * @param seasonID
	 */
	public void gotoSeasonSlipsPg(String seasonID) {
		InvMgrFacilityBookingRulesPage season = InvMgrFacilityBookingRulesPage
				.getInstance();
		InvMgrSeasonSlipsPage seasonSlipPg = InvMgrSeasonSlipsPage
				.getInstance();
		logger.info("Go to season slips page from season page.");

		if (!season.exists()) {
			this.gotoSeasonsPg();
		}
		season.clickNumSites(seasonID);
		seasonSlipPg.waitLoading();
	}

	/**
	 * The method execute going to closure site page from closure page
	 * 
	 * @param closureID
	 */
	public void gotoClosureSitesPg(String closureID) {
		InvMgrClosuresPage inClosurePg = InvMgrClosuresPage.getInstance();
		InvMgrClosuresSitesPage closureSitesPg = InvMgrClosuresSitesPage
				.getInstance();
		logger.info("Go to closure sites page from closure page.");

		if (!inClosurePg.exists()) {
			this.gotoClosurePage();
		}
		inClosurePg.searchClosure(closureID);
		inClosurePg.clickNumSites(closureID);
		closureSitesPg.waitLoading();
	}

	/**
	 * Activate or deactivate one or more closure
	 * 
	 * @param status
	 *            : Activate and Deactivate
	 * @param status
	 *            : setDismissable
	 * 
	 * @param ClosureIDs
	 */

	public void activeOrDeactiveClosure(String status, boolean setDismissable,
			String... ClosureIDs) {
		InvMgrClosuresPage inClosurePg = InvMgrClosuresPage.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();

		logger.info(status + " a closure.");

		if (status.equalsIgnoreCase("Activate")) {
			inClosurePg.activeClosures(ClosureIDs);
		}

		if (status.equalsIgnoreCase("Deactivate")) {
			inClosurePg.deactivateClosures(ClosureIDs);
		}

		confirmDialogPg.setDismissible(setDismissable);
		browser.waitExists(inClosurePg, confirmDialogPg,
				inChangeReqItemDetailPg);
	}

	public void activeOrDeactiveClosure(String status, String... ClosureIDs) {
		if(ClosureIDs!=null&&ClosureIDs.length==1){//handle operate one closure scenario, add a search before select
			InvMgrClosuresPage inClosurePg = InvMgrClosuresPage.getInstance();
			inClosurePg.searchClosure(ClosureIDs[0]);
		}
		this.activeOrDeactiveClosure(status, false, ClosureIDs);
	}

	/**
	 * Delete Camping Units
	 * 
	 * @param comboIDs
	 */
	public void deleteCampingUnits(String[] comboIDs) {
		InvMgrCampingUnitPage inCampingUnitPg = InvMgrCampingUnitPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();
		InvMgrCampingUnitDetailsPage inCampingUnitDetailsPg = InvMgrCampingUnitDetailsPage
				.getInstance();
		logger.info("Delete the camping unit.");

		if (!inCampingUnitPg.exists()) {
			if (inCampingUnitDetailsPg.exists()) {
				inCampingUnitDetailsPg.clickCampingUnitsTab();
				inCampingUnitPg.waitLoading();
			} else {
				this.gotoCampingUnitPg();
			}
		}

		inCampingUnitPg.selectComboNameCheckBoxs(comboIDs);
		inCampingUnitPg.clickDelete();

		confirmDialogPg.setDismissMethod(false);
		browser.waitExists(confirmDialogPg, inCampingUnitPg,
				inChangeReqItemDetailPg);
	}

	/**
	 * Go to camping unit sites page from camping unit page
	 * 
	 * @param comboID
	 */
	public void gotocampingUnitSitesPg(String comboID) {
		InvMgrCampingUnitPage inCampingUnitPg = InvMgrCampingUnitPage
				.getInstance();
		InvMgrCampingUnitDetailsPage inCampingUnitDetailsPg = InvMgrCampingUnitDetailsPage
				.getInstance();
		InvMgrCampingUnitSitesPage inCampingUnitSitePg = InvMgrCampingUnitSitesPage
				.getInstance();
		logger.info("Go to camping unit sites page.");

		if (!inCampingUnitDetailsPg.exists()) {
			if (!inCampingUnitPg.exists()) {
				this.gotoCampingUnitPg();
			}
			inCampingUnitPg.clickComboID(comboID);
			inCampingUnitDetailsPg.waitLoading();
		}

		inCampingUnitDetailsPg.clickSites();
		inCampingUnitSitePg.waitLoading();
	}

	/**
	 * Go to camping unit loops page from camping unit page
	 * 
	 * @param comboID
	 */
	public void gotocampingUnitLoopsPg(String comboID) {
		InvMgrCampingUnitPage inCampingUnitPg = InvMgrCampingUnitPage
				.getInstance();
		InvMgrCampingUnitDetailsPage inCampingUnitDetailsPg = InvMgrCampingUnitDetailsPage
				.getInstance();
		InvMgrCampingUnitLoopsPage inCampingUnitLoopsPg = InvMgrCampingUnitLoopsPage
				.getInstance();
		InvMgrCampingUnitSitesPage inCampingUnitSitesPg = InvMgrCampingUnitSitesPage
				.getInstance();
		logger.info("Go to camping unit loops page.");

		if (!inCampingUnitDetailsPg.exists()) {
			if (!inCampingUnitPg.exists()) {
				this.gotoCampingUnitPg();
			}
			inCampingUnitPg.clickComboID(comboID);
			inCampingUnitDetailsPg.waitLoading();
			inCampingUnitDetailsPg.clickLoops();
		} else if (inCampingUnitSitesPg.exists()) {
			inCampingUnitSitesPg.clickLoops();
		}

		inCampingUnitLoopsPg.waitLoading();
	}

	/**
	 * Verify change request items IDs in change request items page
	 * 
	 * @param changeReqItems
	 */
	public void verifyChangeRequestItems(String changeReqID,
			List<String> changeReqItems) {
		InvMgrCampingUnitPage imCampUnitPg = InvMgrCampingUnitPage
				.getInstance();
		InvMgrCampingUnitSitesPage imCampUnitSitesPg = InvMgrCampingUnitSitesPage
				.getInstance();
		InvMgrCampingUnitLoopsPage imCampUnitLoopsPg = InvMgrCampingUnitLoopsPage
				.getInstance();
		InvMgrCampingUnitDetailsPage imCampUnitDetailsPg = InvMgrCampingUnitDetailsPage
				.getInstance();

		InvMgrClosuresPage imClosuresPg = InvMgrClosuresPage.getInstance();
		InvMgrClosuresSitesPage imClosuresSitesPg = InvMgrClosuresSitesPage
				.getInstance();
		InvMgrClosureDetailPage imClosuresDetailPg = InvMgrClosureDetailPage
				.getInstance();

		InvMgrSeasonSitesPage imSeasonSitesPg = InvMgrSeasonSitesPage
				.getInstance();
		InvMgrFacilityBookingRulesPage imSeasonPg = InvMgrFacilityBookingRulesPage
				.getInstance();

		InvMgrNoteAndAlertListPage imAlertListPg = InvMgrNoteAndAlertListPage
				.getInstance();
		InvMgrNoteOrAlertSitesPage imNoteOrAlertSitesPg = InvMgrNoteOrAlertSitesPage
				.getInstance();
		InvMgrNoteOrAlertLoopsPage imNoteOrAlertLoopsPg = InvMgrNoteOrAlertLoopsPage
				.getInstance();
		InvMgrNoteOrAlertDetailPage imNoteOrAlertDetailsPg = InvMgrNoteOrAlertDetailPage
				.getInstance();

		InvMgrLoopAreasPage invLoopAreaPg = InvMgrLoopAreasPage.getInstance();
		InvMgrLoopAreaSitePage invLoopAreaSitePg = InvMgrLoopAreaSitePage
				.getInstance();
		InvMgrLoopDetailsPage invLoopAreaDetailsPg = InvMgrLoopDetailsPage
				.getInstance();

		InvMgrSitesPage imSitePg = InvMgrSitesPage.getInstance();
		InvMgrNonSiteSpecGroupPage imNonSiteSpecificGroupPg = InvMgrNonSiteSpecGroupPage
				.getInstance();
		InvMgrSiteDetailPage imSiteDetailPg = InvMgrSiteDetailPage
				.getInstance();

		InvMgrServiceActivitiesPage imServiceActivitiesPg = InvMgrServiceActivitiesPage
				.getInstance();
		InvMgrEventsPage imEventPg = InvMgrEventsPage.getInstance();
		InvMgrEventDetailPage imEventDetailPg = InvMgrEventDetailPage
				.getInstance();

		InvMgrMapViewPage imMapViewPage = InvMgrMapViewPage.getInstance();

		InvMgrFacilityDetailPage invDetails = InvMgrFacilityDetailPage
				.getInstance();
		InvMgrFacilitySupplementaryInfo invSupplementaryInfo = InvMgrFacilitySupplementaryInfo
				.getInstance();

		InvMgrChangeReqItems imChangeReqItemsPg = InvMgrChangeReqItems
				.getInstance();
		logger.info("Verify Change Request Items id.");
		// camping unit
		if (imCampUnitSitesPg.exists()) {
			imCampUnitSitesPg.clickViewChangeRequestItems();
		} else if (imCampUnitPg.exists()) {
			imCampUnitPg.clickViewChangeRequestItems();
		} else if (imCampUnitLoopsPg.exists()) {
			imCampUnitLoopsPg.clickViewChangeRequestItems();
		} else if (imCampUnitDetailsPg.exists()) {
			imCampUnitDetailsPg.clickViewChangeRequestItems();
			// closure
		} else if (imClosuresPg.exists()) {
			imClosuresPg.clickViewChangeRequestItems();
		} else if (imClosuresSitesPg.exists()) {
			imClosuresSitesPg.clickViewChangeRequestItems();
		} else if (imClosuresDetailPg.exists()) {
			imClosuresDetailPg.clickViewChangeRequestItems();
			// season
		} else if (imSeasonPg.exists()) {
			imSeasonPg.clickViewChangeRequestItems();
		} else if (imSeasonSitesPg.exists()) {
			imSeasonSitesPg.clickViewChangeRequestItems();
			// note or alert
		} else if (imAlertListPg.exists()) {
			imAlertListPg.clickViewChangeRequestItems();
		} else if (imNoteOrAlertSitesPg.exists()) {
			imNoteOrAlertSitesPg.clickViewChangeRequestItems();
		} else if (imNoteOrAlertLoopsPg.exists()) {
			imNoteOrAlertLoopsPg.clickViewChangeRequestItems();
		} else if (imNoteOrAlertDetailsPg.exists()) {
			imNoteOrAlertDetailsPg.clickViewChangeRequestItems();
			// loop or area
		} else if (invLoopAreaPg.exists()) {
			invLoopAreaPg.clickViewChangeRequestItems();
		} else if (invLoopAreaSitePg.exists()) {
			invLoopAreaSitePg.clickViewChangeRequestItems();
		} else if (invLoopAreaDetailsPg.exists()) {
			invLoopAreaDetailsPg.clickViewChangeRequestItems();
			// site
		} else if (imSitePg.exists()) {
			imSitePg.clickViewChangeRequestItems();
		} else if (imNonSiteSpecificGroupPg.exists()) {
			imNonSiteSpecificGroupPg.clickViewChangeRequestItems();
		} else if (imSiteDetailPg.exists()) {
			imSiteDetailPg.clickViewChangeRequestItems();
		}
		// Service, Activities and Event
		else if (imServiceActivitiesPg.exists()) {
			imServiceActivitiesPg.clickViewChangeRequestItems();
		} else if (imEventPg.exists()) {
			imEventPg.clickViewChangeRequestItems();
		} else if (imEventDetailPg.exists()) {
			imEventDetailPg.clickViewChangeRequestItems();
		}
		// Map
		else if (imMapViewPage.exists()) {
			imMapViewPage.clickViewChangeRequestItems();
		}
		// Facility details
		else if (invDetails.exists()) {
			invDetails.clickViewChangeRequestItems();
		} else if (invSupplementaryInfo.exists()) {
			invSupplementaryInfo.clickViewChangeRequestItems();
		}

		imChangeReqItemsPg.waitLoading();
		imChangeReqItemsPg.searchItem("Change Request ID", changeReqID);

		List<String> changeReqItemsUI = imChangeReqItemsPg
				.getChangeRequestsItemsIDs();
		/*for (int i = 0; i < changeReqItems.size(); i++) {
			for (int j = 0; j < changeReqItemsUI.size(); j++) {
				if (!changeReqItems.get(i).equals(changeReqItemsUI.get(i))) {
					throw new ErrorOnDataException(changeReqItems.get(i)
							+ " doesn't equal to " + changeReqItemsUI.get(i));
				}
			}
		}*/ //The logic is not quite right,so updated it by Phoebe, 12-19-2012
		for (int i = 0; i < changeReqItems.size(); i++) {
			for (int j = 0; j < changeReqItemsUI.size(); j++) {
				if (changeReqItems.get(i).equals(changeReqItemsUI.get(j))) {
					break;
				}
			}
			if(changeReqItemsUI.size() == i){
				throw new ErrorOnDataException("There is no change request Items whose id is:" + changeReqItems.get(i)
						);
			}
		}
	}

	/**
	 * Goto camping units details page
	 * 
	 * @param comboID
	 * @param isNew
	 */
	public void gotoCampUnitsDetailsPg(String comboID, boolean isNew) {
		InvMgrCampingUnitPage inCampingUnitPg = InvMgrCampingUnitPage
				.getInstance();
		InvMgrCampingUnitDetailsPage inCampingUnitDetailsPg = InvMgrCampingUnitDetailsPage
				.getInstance();
		logger.info("Go to camping units Details page.");

		if (!inCampingUnitPg.exists()) {
			this.gotoCampingUnitPg();
		}
		if (isNew) {
			inCampingUnitPg.clickAddNewCombo();
		} else {
			inCampingUnitPg.clickComboID(comboID);
		}

		inCampingUnitDetailsPg.waitLoading();
	}

	/**
	 * Go to notes and alerts page
	 */
	public void gotoNotesAndAlertsPg() {
		InvMgrNoteAndAlertListPage NoteAndAlert = InvMgrNoteAndAlertListPage
				.getInstance();
		InvMgrTopMenuPage inTopMenu = InvMgrTopMenuPage.getInstance();
		logger.info("Go to note and alert page");

		inTopMenu.gotoSpecifiedDetailPage("Notes and Alerts");
		NoteAndAlert.waitLoading();
	}

	/**
	 * This method execute going to note or alert detail page
	 * 
	 * @param noteOrAlertID
	 * @param isNew
	 */

	public void gotoNoteOrAlertDetaiPg(InventoryInfo inventory, boolean isNew) {
		InvMgrNoteAndAlertListPage noteAndAlertPg = InvMgrNoteAndAlertListPage
				.getInstance();
		InvMgrNoteOrAlertDetailPage noteAndAlertDetailPg = InvMgrNoteOrAlertDetailPage
				.getInstance();

		logger.info("Go to Note or alert detail page.");
		if (!noteAndAlertPg.exists()) {
			this.gotoNotesAndAlertsPg();
		}

		if (null != inventory) {
			noteAndAlertPg.searchNotesOrAlerts(inventory);
		}

		if (isNew) {
			noteAndAlertPg.clickAddNew();
		} else {
			PagingComponent turnPageComponent = new PagingComponent();
			boolean found=noteAndAlertPg.isNoteOrAlertIDExisted(inventory.alertID);
			while(!found && turnPageComponent.nextExists()) {
				turnPageComponent.clickNext();
				noteAndAlertPg.waitLoading();
				found=noteAndAlertPg.isNoteOrAlertIDExisted(inventory.alertID);
			}

			if(!found)
				throw new ActionFailedException("Note alert with id:"+inventory.alertID+" not found on page");
				
			noteAndAlertPg.clickNoteOrAlertID(inventory.alertID);
		}

		noteAndAlertDetailPg.waitLoading();
	}
	
	public void assignSlipToNoteAlert(InventoryInfo inventory, String slipName, String slipId){
		this.assignOrRemoveSlipToNoteAlert(inventory, slipName, slipId, true);
	}
	
	public void assignOrRemoveSlipToNoteAlert(InventoryInfo inventory, String slipName, String slipId, boolean isAssigned){
		InvMgrNoteOrAlertDetailPage noteAndAlertDetailPg = InvMgrNoteOrAlertDetailPage.getInstance();
		InvMgrNoteOrAlertAssignToSlipsPage assignSlipPg = InvMgrNoteOrAlertAssignToSlipsPage.getInstance();
		if(!noteAndAlertDetailPg.exists()){
			this.gotoNoteOrAlertDetaiPg(inventory, false);
		}
		noteAndAlertDetailPg.clickSlipLable();
		ajax.waitLoading();
		Browser.sleep(3);
		assignSlipPg.waitLoading();
		logger.info(isAssigned?"Assigned ":"Remove slip:" + slipName + " for note/alert " + inventory.alertID);
		assignSlipPg.searchSlipByName(slipName);
		assignSlipPg.selectCheckBoxBeforeSlip(slipId);
		if(isAssigned){
			assignSlipPg.clickAssign();
		}else{
			assignSlipPg.clickRemove();
		}
		ajax.waitLoading();
		assignSlipPg.waitLoading();
	}
	
	public void assignDockToNoteAlert(InventoryInfo inventory, String dockName, String dockId){
		this.assignOrRemoveDockToNoteAlert(inventory, dockName, dockId, true);
	}
	
	public void assignOrRemoveDockToNoteAlert(InventoryInfo inventory, String dockName, String dockId, boolean isAssigned){
		InvMgrNoteOrAlertDetailPage noteAndAlertDetailPg = InvMgrNoteOrAlertDetailPage.getInstance();
		InvMgrNoteOrAlertAssignToDocksPage assignDockPg = InvMgrNoteOrAlertAssignToDocksPage.getInstance();
		if(!noteAndAlertDetailPg.exists()){
			this.gotoNoteOrAlertDetaiPg(inventory, false);
		}
		noteAndAlertDetailPg.clickDocksLabel();
		ajax.waitLoading();
		assignDockPg.waitLoading();
		logger.info(isAssigned?"Assigned ":"Remove dock:" + dockName + " for note/alert " + inventory.alertID);
		assignDockPg.searchDockByName(dockName);
		assignDockPg.selectCheckBoxBeforeDock(dockId);
		if(isAssigned){
			assignDockPg.clickAssign();
		}else{
			assignDockPg.clickRemove();
		}
		ajax.waitLoading();
		assignDockPg.waitLoading();
	}

	/**
	 * Delete one loop in loops or areas details page
	 * 
	 * @param loopName
	 * @param dismissable
	 *            : For confirmDialog, dismiss or not.
	 */
	public void deleteLoopInLoopsOrAreasDetails(String loopName,
			boolean dismissable) {
		InvMgrLoopAreasPage invLoopAreaPg = InvMgrLoopAreasPage.getInstance();
		InvMgrLoopDetailsPage invLoopDetailsPg = InvMgrLoopDetailsPage
				.getInstance();
		InvMgrConfimActionPage confirmActionPg = InvMgrConfimActionPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail inChangeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();

		logger.info("Delete one loop in Loops Or Areas details page.");
		invLoopAreaPg.clickLoopLink(loopName);
		invLoopDetailsPg.waitLoading();

		invLoopDetailsPg.clickDeleteThisLoopArea();

		confirmDialogPg.setDismissMethod(dismissable);
		Object page = browser.waitExists(confirmActionPg, invLoopAreaPg,
				confirmDialogPg);
		if (page == confirmActionPg) {
			confirmActionPg.clickOK();
			invLoopAreaPg.waitLoading();
		} else if (dismissable && page.equals(confirmDialogPg)) {
			confirmDialogPg.clickOK();
			browser.waitExists(invLoopAreaPg, inChangeReqItemDetailPg);
		}
	}

	/**
	 * Delete one or more loops in loops or areas page
	 * 
	 * @param LoopOrAreaIDs
	 * @param dismissable
	 *            : For confirmDialog, dismiss or not.
	 */
	public void deleteLoopInLoopsOrAreas(String[] LoopOrAreaIDs,
			boolean dismissable) {
		InvMgrLoopAreasPage invLoopAreaPg = InvMgrLoopAreasPage.getInstance();
		InvMgrConfimActionPage confirmActionPg = InvMgrConfimActionPage
				.getInstance();
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
		InvMgrChangeReqItemDetail changeReqItemDetailPg = InvMgrChangeReqItemDetail
				.getInstance();

		logger.info("Delete one or more loops in Loops Or Areas page.");
		invLoopAreaPg.selectLoopOrAreaCheckBoxs(LoopOrAreaIDs);
		invLoopAreaPg.clickDelete();

		confirmDialogPg.setDismissible(dismissable);
		Object page = browser.waitExists(confirmActionPg, invLoopAreaPg,
				confirmDialogPg, changeReqItemDetailPg);
		if (page == confirmActionPg) {
			confirmActionPg.clickOK();
			invLoopAreaPg.waitLoading();
		}
	}

	/**
	 * Goto Fee Validation Details page from Fee Calculation Validation page via
	 * site ID and fee Schedule ID
	 * 
	 * @param siteIDs
	 * @param feeSchedulID
	 */
	public void gotoFeeValidationDetailsPg(String feeSchedulID) {
		InvMgrSiteValidateFeesPage imFeeCaculationValidationPg = InvMgrSiteValidateFeesPage
				.getInstance();
		InvMgrSiteValidateFeesDetailsPage imFeeValidationDetailsPg = InvMgrSiteValidateFeesDetailsPage
				.getInstance();

		logger.info("Goto Fee Validation Details page from Fee Calculation Validation page.");
		imFeeCaculationValidationPg.clickScheduleID(feeSchedulID);
		imFeeValidationDetailsPg.waitLoading();
	}

	/**
	 * Verify the information associated with fee validation details page
	 * 
	 * @param facilityName
	 * @param feeType
	 *            : Just be used for logger info
	 * @param siteID
	 * @param discountName
	 *            : Be used for selecting corresponding discount
	 * @param siteInfo
	 *            : The info of Site selection in Site Details page, Fee
	 *            Calculation Validation page and Fee Validation Details page
	 */
	public void verifyFeeValidationDetails(String facilityName, String feeType,
			String siteID, String discountName, String siteInfo) {
		InvMgrSiteValidateFeesPage imFeeCaculationValidationPg = InvMgrSiteValidateFeesPage
				.getInstance();
		InvMgrSiteValidateFeesDetailsPage imFeeValidationDetailsPg = InvMgrSiteValidateFeesDetailsPage
				.getInstance();
		logger.info("Verify " + feeType + " Fee Validation Detail information");
		Object page = "";

		imFeeCaculationValidationPg.selectDiscount(discountName);

		String feeSchedulID = imFeeCaculationValidationPg
				.getScheduleID(feeType);
		this.gotoFeeValidationDetailsPg(feeSchedulID);

		// Verify Site table info
		if (!imFeeValidationDetailsPg.getSiteInfo().equals(siteInfo)) {
			throw new ErrorOnDataException(
					"The System shall display the same Site info as per Site Detail and Fee Calculation Validation pages.");
		}
		// Verify Fee Schedule id, Location, 'Add Event/Holiday','Remove
		// Event/Holiday', read-only field.
		imFeeValidationDetailsPg.verifyFeeValidationDetail(facilityName,
				feeSchedulID, feeType);

		// Verify Fee Calculation Validation exist or not after clicking OK
		// button
		imFeeValidationDetailsPg.clickOK();
		page = browser.waitExists(imFeeCaculationValidationPg);
		if (page != imFeeCaculationValidationPg) {
			if (imFeeCaculationValidationPg.checkFeeValidationDetailsTab()) {
				throw new ErrorOnDataException(
						"Fee Validation Details tab should don't exist.");
			}
			throw new ItemNotFoundException(
					"Fee Calculation Validation page should exist.");
		}

		// Verify Fee Calculation Validation exist or not after clicking Fee
		// Calculation Validation tab
		imFeeCaculationValidationPg.selectDiscount(discountName);
		this.gotoFeeValidationDetailsPg(feeSchedulID);

		imFeeValidationDetailsPg.clickFeeCalculationValidationTab();
		page = browser.waitExists(imFeeCaculationValidationPg);
		if (page != imFeeCaculationValidationPg) {
			if (imFeeCaculationValidationPg.checkFeeValidationDetailsTab()) {
				throw new ErrorOnDataException(
						"Fee Validation Details tab should don't exist.");
			}
			throw new ItemNotFoundException(
					"Fee Calculation Validation page should exist.");
		}
	}

	/**
	 * Verify site info with different action
	 * 
	 * @param isFirst
	 *            : the site is the first site in site list page
	 * @param isLast
	 *            : the site is the last site in site list page
	 * @param siteinfos
	 *            : site section info
	 */
	public void verifySiteInfo(boolean isFirst, boolean isLast,
			List<String> siteinfos) {
		InvMgrSiteValidateFeesPage imFeeCaculationValidationPg = InvMgrSiteValidateFeesPage
				.getInstance();

		// Verify the site info with different action
		if (isFirst) {
			imFeeCaculationValidationPg.verifySiteInfo("First",
					siteinfos.get(0));
			// Click Next button
			imFeeCaculationValidationPg.clickNext();
			imFeeCaculationValidationPg.waitLoading();
			imFeeCaculationValidationPg
					.verifySiteInfo("Next", siteinfos.get(2));
			// Click Previous button
			imFeeCaculationValidationPg.clickPrevious();
			imFeeCaculationValidationPg.waitLoading();
			imFeeCaculationValidationPg.verifySiteInfo("Previous",
					siteinfos.get(1));
			// Click Last button
			imFeeCaculationValidationPg.clickLast();
			imFeeCaculationValidationPg.waitLoading();
			imFeeCaculationValidationPg
					.verifySiteInfo("Last", siteinfos.get(3));
		} else if (isLast) {
			imFeeCaculationValidationPg
					.verifySiteInfo("Last", siteinfos.get(3));
			// Click Previous button
			imFeeCaculationValidationPg.clickPrevious();
			imFeeCaculationValidationPg.waitLoading();
			imFeeCaculationValidationPg.verifySiteInfo("Previous",
					siteinfos.get(1));
			// Click Next button
			imFeeCaculationValidationPg.clickNext();
			imFeeCaculationValidationPg.waitLoading();
			imFeeCaculationValidationPg
					.verifySiteInfo("Next", siteinfos.get(2));
			// Click First button
			imFeeCaculationValidationPg.clickFirst();
			imFeeCaculationValidationPg.waitLoading();
			imFeeCaculationValidationPg.verifySiteInfo("First",
					siteinfos.get(0));
		} else {
			imFeeCaculationValidationPg
					.verifySiteInfo("Next", siteinfos.get(2));
			// Click Previous button
			imFeeCaculationValidationPg.clickPrevious();
			imFeeCaculationValidationPg.waitLoading();
			imFeeCaculationValidationPg.verifySiteInfo("Previous",
					siteinfos.get(1));
			// Click Next button
			imFeeCaculationValidationPg.clickNext();
			imFeeCaculationValidationPg.waitLoading();
			imFeeCaculationValidationPg
					.verifySiteInfo("Next", siteinfos.get(2));
			// Click First button
			imFeeCaculationValidationPg.clickFirst();
			imFeeCaculationValidationPg.waitLoading();
			imFeeCaculationValidationPg.verifySiteInfo("First",
					siteinfos.get(0));
			// Click Last button
			imFeeCaculationValidationPg.clickLast();
			imFeeCaculationValidationPg.waitLoading();
			imFeeCaculationValidationPg
					.verifySiteInfo("Last", siteinfos.get(3));
		}
	}

	/**
	 * Verify Custom fee
	 * 
	 * @param feeType
	 * @param rate
	 * @param rateUnit
	 * @param appliesTo
	 * @param feeDates
	 */
	public boolean verifyCustomFee(List<String> feeType, List<String> rate,
			List<String> rateUnit, List<String> appliesTo, List<String> feeDates) {
		// Use Fees, Discounts on Use Fees, Taxes on Use Fee, Fee Penalties on
		// Use Fees, Taxes on Use Fee Penalties
		InvMgrSiteValidateFeesPage imFeeCaculationValidationPg = InvMgrSiteValidateFeesPage
				.getInstance();
		boolean systemAdjustExist = false;
		// Fee Type
		for (int i = 2; i < feeType.size() + 2; i++) {
			if (!imFeeCaculationValidationPg.getCustomFeeCell(i, 0).trim().equals(feeType.get(i - 2))) {
				throw new ErrorOnDataException("Fee Type should be "+ feeType.get(i - 2)+", but actula one is:"+imFeeCaculationValidationPg.getCustomFeeCell(i, 0).trim());
			}
			if (imFeeCaculationValidationPg.getCustomFeeCell(i, 0).trim()
					.equals("Attribute Fee")) {
				systemAdjustExist = true;
			}
		}
		// Rate
		for (int i = 2; i < rate.size() + 2; i++) {
			if (!imFeeCaculationValidationPg.getCustomFeeCell(i, 2).trim()
					.equals(rate.get(i - 2))) {
				throw new ErrorOnDataException("Rate should be "
						+ rate.get(i - 2));
			}
		}
		// Rate Unit
		for (int i = 2; i < rateUnit.size() + 2; i++) {
			if (!imFeeCaculationValidationPg.getCustomFeeCell(i, 4).trim()
					.equals(rateUnit.get(i - 2))) {
				throw new ErrorOnDataException("Rate Unit should be "
						+ rateUnit.get(i - 2));
			}
		}

		// Applies To
		for (int i = 2; i < appliesTo.size() + 2; i++) {
			if (!imFeeCaculationValidationPg.getCustomFeeCell(i, 5).trim()
					.equals(appliesTo.get(i - 2))) {
				throw new ErrorOnDataException("Applies To should be "
						+ appliesTo.get(i - 2));
			}
		}
		// Fee Dates
		for (int i = 2; i < feeDates.size() + 2; i++) {
			if (!imFeeCaculationValidationPg.getCustomFeeCell(i, 6).trim().equals(feeDates.get(i - 2))) {
				throw new ErrorOnDataException("Fee Date should be " + feeDates.get(i - 2) + ", but actual is :" + imFeeCaculationValidationPg.getCustomFeeCell(i, 6));
			}
		}

		return systemAdjustExist;
	}

	/**
	 * Verify specific discount details info
	 * 
	 * @param discountName
	 * @param disctTierItems
	 * @param specificDisctDetailInfo
	 */
	public void verifyDiscoutDetails(boolean isTier1, String discountName,
			List<String> disctTierItems,
			List<List<String>> specificDisctDetailInfo) {
		InvMgrSiteValidateFeesPage imFeeCaculationValidationPg = InvMgrSiteValidateFeesPage
				.getInstance();
		List<String> rowItems = new ArrayList<String>();
		logger.info("Verify specific discount details info.");

		if (null != discountName && discountName.length() > 0) {
			if (isTier1) {
				imFeeCaculationValidationPg.selectTierTwoNull();
				imFeeCaculationValidationPg.waitLoading();
				imFeeCaculationValidationPg.selectTierOne(discountName);
			} else {
				imFeeCaculationValidationPg.selectTierOneNull();
				imFeeCaculationValidationPg.waitLoading();
				imFeeCaculationValidationPg.selectTierTwo(discountName);
			}

			imFeeCaculationValidationPg.waitLoading();
			for (int i = 0; i < specificDisctDetailInfo.size(); i++) {
				rowItems = imFeeCaculationValidationPg
						.getDiscountDetailTableInfo().get(i);
				for (int j = 0; j < rowItems.size(); j++) {
					if (!rowItems.get(j).equals(
							specificDisctDetailInfo.get(i).get(j))) {
						throw new ErrorOnDataException(
								"The specific discount with: row number:" + i
										+ "column num:" + j + " not correct.", specificDisctDetailInfo.get(i).get(j), rowItems.get(j));
					}
				}
			}
		}
	}

	/**
	 * Verify Discounts /Promotions section info
	 * 
	 * @param isTier1
	 *            : true: the discount in Tier 1 false: the discount in Tier 2
	 * @param tierDisctTier
	 *            : the specific discount's name of in Tier 1 or Tier 2
	 * @param disctTierItems
	 *            : All the items in Tier 1 or Tier 2 drop down list
	 * @param specificDisctDetailInfo
	 *            : the discount details information for specific discount
	 */
	public void verifyDiscounts(boolean isTier1, String tierDisctTier,
			List<String> disctTierItems,
			List<List<String>> specificDisctDetailInfo) {
		InvMgrSiteValidateFeesPage imFeeCaculationValidationPg = InvMgrSiteValidateFeesPage
				.getInstance();
		List<String> tier = new ArrayList<String>();

		// Verify Discounts /Promotions
		imFeeCaculationValidationPg.clickRefreshAvailableDiscount();
		imFeeCaculationValidationPg.waitLoading();

		if (isTier1) {
			tier = imFeeCaculationValidationPg.getItemsFromTierOne();
		} else {
			tier = imFeeCaculationValidationPg.getItemsFromTierTwo();
		}

		for (int j = 0; j < disctTierItems.size(); j++) {
			if (!tier.contains(disctTierItems.get(j))) {
				throw new ErrorOnDataException(disctTierItems.get(j)+" should be displayed in the tier list.");
			}
		}

		this.verifyDiscoutDetails(isTier1, tierDisctTier, disctTierItems,
				specificDisctDetailInfo);
	}

	/**
	 * Verify information in Fee calculation Validation page
	 * 
	 * @param feeValidData
	 */
	public void verifyFeeCalculationValidation(FeeValidationData feeValidData,
			List<FeeValidationData> arrivalDepartureNightsOrDays,
			boolean isFirst, boolean isLast, List<String> siteInfo,
			List<String> feeType, List<String> rate, List<String> rateUnit,
			List<String> appliesTo, List<String> feeDates) {
		InvMgrSiteValidateFeesPage imFeeCaculationValidationPg = InvMgrSiteValidateFeesPage
				.getInstance();
		logger.info("Verify the information in Fee Caculation Validation page.");

		logger.info("Verify Stay Dates.");
		imFeeCaculationValidationPg
				.VerifyInitialiedNumOfNightsOrDays(feeValidData.typeOfUse);
		imFeeCaculationValidationPg
				.verifyArrivalDepartureNumOfStay(arrivalDepartureNightsOrDays);

		logger.info("Verify Rate Type.");
		imFeeCaculationValidationPg.verifyRateType(feeValidData.rateType);
		if (feeValidData.rateType.equals("Group")) {
			imFeeCaculationValidationPg.setOccupantNum(feeValidData.occuptants);
			imFeeCaculationValidationPg.setVehicleNum(feeValidData.vehicles);
		}

		logger.info("Verify Discounts /Promotions.");
		imFeeCaculationValidationPg.verifyInitialDiscountsOrPromotions();
		this.verifyDiscounts(true, feeValidData.disctTier1,
				feeValidData.disctTier1Items,
				feeValidData.specificTier1DisctDetail);
		this.verifyDiscounts(false, feeValidData.disctTier2,
				feeValidData.disctTier2Items,
				feeValidData.specificTier2DisctDetail);

		logger.info("Verify Custom Fee.");
		imFeeCaculationValidationPg.selectTierOne(feeValidData.disctTier1);
		imFeeCaculationValidationPg.waitLoading();
		imFeeCaculationValidationPg.selectTierTwo(feeValidData.disctTier2);
		imFeeCaculationValidationPg.waitLoading();
		imFeeCaculationValidationPg.clickCalculateFee();
		ajax.waitLoading();
		imFeeCaculationValidationPg.waitLoading();
		boolean systemAdjustExist = this.verifyCustomFee(feeType, rate,
				rateUnit, appliesTo, feeDates);
		// Verify System Adjustment
		if (systemAdjustExist) {
			if (imFeeCaculationValidationPg.getSystemAdjustmentNum() != 2) {
				throw new ErrorOnDataException(
						"The System Adjustment for Use Fee and Tax should exist when Attribute Fee exists.");
			}
		}
		// Verify Total
		if (systemAdjustExist) {
			if (imFeeCaculationValidationPg.getTotalNum() != 7) {
				throw new ErrorOnDataException(
						"The Total items are not correct.");
			}
		}

		logger.info("Verify First, Previous, Next, Last button and associated site info.");
		imFeeCaculationValidationPg
				.verifyFirstPreviousNextLast(isFirst, isLast);
		this.verifySiteInfo(isFirst, isLast, siteInfo);
	}

	/**
	 * Go to Services, Activity and Event page
	 * 
	 * @param: eventID
	 * @param: isNew true: Go to details page according to clicking 'Add New
	 *         Event' False: Go to details page via Event name
	 */
	public void gotoServicesActivityEventDetailsPg(String eventID, boolean isNew) {
		InvMgrEventsPage imEventPg = InvMgrEventsPage.getInstance();
		InvMgrEventDetailPage imEventDetailPg = InvMgrEventDetailPage
				.getInstance();
		logger.info("Go to Services, Activity and Eveng details page.");

		if (!imEventPg.exists()) {
			this.gotoEventsPg();
		}

		if (isNew) {
			imEventPg.clickAddNewEvent();
		} else {
			imEventPg.clickEventID(eventID);
		}

		imEventDetailPg.waitLoading();
	}

	/**
	 * Get first event info
	 * 
	 * @param event
	 * @return
	 */
	public EventInfo getEventInformation(EventInfo event) {
		InvMgrEventsPage imEventPg = InvMgrEventsPage.getInstance();
		logger.info("Get first event info");
		if (imEventPg.getEventNum() == 0) {
			this.addEvent(event);
		}

		return imEventPg.getEventDetailInfo();
	}
	
	public void clearDraftChangeRequestItem(String requestType){
		InvMgrChangeReqItems changeReqItemsPg = InvMgrChangeReqItems
				.getInstance();
		ConfirmDialogPage confrimDialogPg = ConfirmDialogPage.getInstance();
		changeReqItemsPg.selectSearchStatus("Draft");
		changeReqItemsPg.selectRequestType(requestType);
		changeReqItemsPg.clickSearch();
		changeReqItemsPg.waitLoading();
		do {
			changeReqItemsPg.clickAllCheckBox();
			changeReqItemsPg.clickDelete();
			Object page = browser.waitExists(confrimDialogPg, changeReqItemsPg);
			if (page == confrimDialogPg) {
				confrimDialogPg.clickOK();
				changeReqItemsPg.waitLoading();
			}
		} while (changeReqItemsPg.checkNext()
				&& changeReqItemsPg.getchangeRequestItemsNum() != 0);
	}

	/**
	 * Delete all Draft change request items
	 * 
	 * @param requestType
	 */
	public void deleteDraftChangeRequestItems(String requestType) {
		InvMgrHomePage invHmPg = InvMgrHomePage.getInstance();
		InvMgrTopMenuPage topMenuPg = InvMgrTopMenuPage.getInstance();
		InvMgrChangeReqItems changeReqItemsPg = InvMgrChangeReqItems
				.getInstance();
		InvMgrChangeRequest changeReqPg = InvMgrChangeRequest.getInstance();

		logger.info("Delete draft change request items");
		if (invHmPg.checkChangeReqItemsExist()) {
			invHmPg.clickChangeReqItems();
		} else {
			if (invHmPg.checkTopDropDownListExist()) {
				invHmPg.gotoSpecifiedDetailPage("Change Request");
				changeReqPg.waitLoading();
				changeReqPg.clickChangeReqItemsTab();
			}
		}

		changeReqItemsPg.waitLoading();
		clearDraftChangeRequestItem(requestType);

		topMenuPg.gotoSpecifiedDetailPage("Facility Setup");

		invHmPg.waitLoading();
	}
	
	
	/**
	 * Delete all Draft change request items for special facility
	 * 
	 * @param requestType
	 */
	public void deleteDraftChangeRequestItemsForFacility(String requestType) {
		InvMgrTopMenuPage topMenuPg = InvMgrTopMenuPage.getInstance();
		InvMgrChangeReqItems changeReqItemsPg = InvMgrChangeReqItems
				.getInstance();
		InvMgrChangeRequest changeReqPg = InvMgrChangeRequest.getInstance();

		topMenuPg.gotoSpecifiedDetailPage("Change Requests");
		changeReqPg.waitLoading();
		changeReqPg.clickChangeReqItemsTab();
		
		changeReqItemsPg.waitLoading();
		clearDraftChangeRequestItem(requestType);
	}

	/**
	 * Delete a facility from DB identified by facility id
	 * 
	 * @param facilityID
	 * @param schema
	 */
	public void deleteFacilityFromDB(String facilityID, String schema) {
		logger.info("Delete facility(ID#=" + facilityID + ") from DB.");
		db.resetSchema(schema);
		String sql = "update d_loc set delete_ind=1 where id=" + facilityID;
		int result = db.executeUpdate(sql);
		if (result != 1) {
			throw new ErrorOnDataException(
					"There is not 1 record has been updated.");
		} else {
			logger.info("Delete facility sucessfully.");
		}
	}
	
	/**
	 * Inactive a facility from DB identified by facility id
	 * @param facilityID
	 * @param schema
	 */
	public void inactiveFacilityFromDB(String facilityID, String schema) {
		logger.info("Inactive facility(ID#=" + facilityID + ") from DB.");
		db.resetSchema(schema);
		String sql = "update d_loc set ACTIVE_IND=0 where id=" + facilityID;
		int result = db.executeUpdate(sql);
		if (result != 1) {
			throw new ErrorOnDataException(
					"There is not 1 record has been updated.");
		} else {
			logger.info("Inactive facility sucessfully.");
		}
	}

	/**
	 * Verify whether the facility is created successfully and matches with
	 * expected info
	 * 
	 * @param expectedFacility
	 */
	public void verifyFacilityDetailInfo(FacilityData expectedFacility) {
		InvMgrHomePage invHomePage = InvMgrHomePage.getInstance();
		InvMgrAddFacilityDetailsPage facilityDetailsPage = InvMgrAddFacilityDetailsPage
				.getInstance();

		logger.info("Verify whether the facility(ID#="
				+ expectedFacility.facilityID + ") match with expected or not.");

		invHomePage
				.searchFacilities("Facility ID", expectedFacility.facilityID);
		invHomePage.selectFacilityById(expectedFacility.facilityID);
		facilityDetailsPage.waitLoading();
		boolean result = facilityDetailsPage
				.compareFacilityInfo(expectedFacility);

		if (!result) {
			throw new ErrorOnPageException("The facility(ID#="
					+ expectedFacility.facilityID
					+ ") detail info doesn't match the expected.");
		} else {
			logger.info("The facility detail info match the expected.");
		}

		facilityDetailsPage.clickOK();
		invHomePage.waitLoading();
	}

	/**
	 * Verify whether the facility list info is correct or not
	 * 
	 * @param expectedFacility
	 */
	public void verifyFacilityListInfo(FacilityData expectedFacility) {
		InvMgrHomePage invHomePage = InvMgrHomePage.getInstance();

		logger.info("Verify facility(ID#=" + expectedFacility.facilityID
				+ ") list info correct or not.");
		boolean result = invHomePage.compareFacilityInfo(expectedFacility);

		if (!result) {
			throw new ErrorOnPageException("The facility(ID#="
					+ expectedFacility.facilityID
					+ ") list info doesn't match the expected.");
		} else {
			logger.info("The facility list info match the expected.");
		}
	}

	public boolean checkLotteryExist(String name) {
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		lotterySearchPg.waitLoading();

		lotterySearchPg.setLotteryName(name);

		lotterySearchPg.clickGo();
		lotterySearchPg.waitLoading();

		List<String> allLotteryNames = lotterySearchPg
				.getTableInfo("Lottery Name");
		return allLotteryNames.contains(name);
	}

	public boolean isLotteryActive(String name) {
		LotterySearchPage lotterySearchPg = LotterySearchPage.getInstance();
		lotterySearchPg.waitLoading();

		List<String> allLotteryNames = lotterySearchPg
				.getTableInfo("Lottery Name");
		List<String> allLotteryStatus = lotterySearchPg.getTableInfo("Active");
		for (int i = 0; i < allLotteryNames.size(); i++) {
			if (allLotteryNames.get(i).equalsIgnoreCase(name)) {
				if (allLotteryStatus.get(i).equalsIgnoreCase("Yes")) {
					return true;
				}
			}
		}
		return false;
	}

	public void deactiveAllLotterSchedule() {
		LotteryScheduleSearchPage lotteryScheduleSearchPg = LotteryScheduleSearchPage
				.getInstance();
		LotteryScheduleDetailsPage lotteryScheduleDetailsPg = LotteryScheduleDetailsPage
				.getInstance();

		Object page = browser.waitExists(lotteryScheduleDetailsPg,
				lotteryScheduleSearchPg);
		if (page instanceof LotteryScheduleSearchPage) {
			lotteryScheduleSearchPg.deactiveAllSchedule();
		}
	}

	public void clickLotteryTab() {
		LotterySearchPage page = LotterySearchPage.getInstance();
		page.clickLotteries();
		page.waitLoading();
	}

	public boolean isLotterySetup() {
		LotterySearchPage page = LotterySearchPage.getInstance();
		return page.isLotterySetup();
	}

	/**
	 * Go to each facility category page according to the given facility
	 * category.
	 * 
	 * @param facilityCategory
	 */
	public void gotoEntranceListPage(String facilityCategory) {
		InvMgrFacilityDetailPage facilityDetailsPg = InvMgrFacilityDetailPage
				.getInstance();
		logger.info("Go to each facility category page according to the given facility");
		facilityDetailsPg.selectFacilityCategory(facilityCategory);

		if (facilityCategory.equals("Entrance Set-up")) {
			InvMgrEntranceListPage page = InvMgrEntranceListPage.getInstance();
			page.waitLoading();
		}

		if (facilityCategory.equals("Facility Details")) {
			InvMgrFacilityDetailPage page = InvMgrFacilityDetailPage
					.getInstance();
			page.waitLoading();
		}

		if (facilityCategory.equals("Inventory")) {
			InvMgrInventoryDetailPage page = InvMgrInventoryDetailPage
					.getInstance();
			page.waitLoading();
		}

		if (facilityCategory.equals("Notes and Alerts")) {
			InvMgrNoteAndAlertListPage page = InvMgrNoteAndAlertListPage
					.getInstance();
			page.waitLoading();
		}

	}

	/**
	 * Go to add new entrance page.
	 */
	public void gotoNewEntrancePage() {
		InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage
				.getInstance();
		InvMgrAddNewEntrancePage newEntrancePg = InvMgrAddNewEntrancePage
				.getInstance();

		logger.info("Go to add new entrance page.");
		// go to add new entrance page.
		entranceListPg.clickAddNew();
		newEntrancePg.waitLoading();
	}

	/**
	 * Add new Entrance.
	 * 
	 * @param entranceInfo
	 * @param permitTypeInfo
	 * @param perminTypeFlag
	 */
	public Object addNewEntrance(EntranceInfo entranceInfo,
			EntranceAttributesInfo entranceAttriInfo,
			PermitTypeInformation permitTypeInfo, boolean isUpdateResWinBlank) {
		InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage
				.getInstance();
		InvMgrAddNewEntrancePage newEntrancePg = InvMgrAddNewEntrancePage
				.getInstance();
		logger.info("Add new entrance.");

		// go to add new entrance page
		entranceListPg.clickAddNew();
		newEntrancePg.waitLoading();
		newEntrancePg.setUpInfoForNewEntrance(entranceInfo, entranceAttriInfo,
				permitTypeInfo, isUpdateResWinBlank);
		newEntrancePg.clickOK();
		Object page = browser.waitExists(entranceListPg, newEntrancePg);
		return page;
	}

	public void gotoPermitTypesListPage() {
		InvMgrPermitTypeListPage permitListPg = InvMgrPermitTypeListPage
				.getInstance();
		browser.clickGuiObject(".class", "Html.A", ".text", "Permit Types");
		permitListPg.waitLoading();
	}

	/**
	 * add new permit type. from permit type list page return back to permit
	 * type list page
	 * 
	 * @param PermitTypeDetailInfo
	 * 
	 */
	public String addNewPermitType(PermitTypeDetailInfo permit) {
		InvMgrPermitTypeListPage permitListPg = InvMgrPermitTypeListPage
				.getInstance();
		InvMgrPermitTypeDetailPage permitDetailPg = InvMgrPermitTypeDetailPage
				.getInstance();

		permitListPg.clickAddNew();
		permitDetailPg.waitLoading();
		String permitTypeID = permitDetailPg.addPermiType(permit);

		permitDetailPg.waitLoading();
		permitDetailPg.clickOk();
		permitListPg.waitLoading();
		return permitTypeID;
	}

	/**
	 * Go to edit entrance page.
	 * 
	 * @param entranceCode
	 */
	public void gotoEditEntrancePage(EntranceInfo entranceInfo) {
		logger.info("Go to edit entrance page.");
		InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage
				.getInstance();
		InvMgrEditEntrancePage editEntrancePg = InvMgrEditEntrancePage
				.getInstance();

		// go to edit entrance page.
		entranceListPg.searchEntrance(entranceInfo);
		entranceListPg.clickEntranceCodeLink(entranceInfo.entranceCode);
		editEntrancePg.waitLoading();
	}
	
	public void gotoEntranceDetailPage(String entranceCode){
		InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage
				.getInstance();
		InvMgrEditEntrancePage editEntrancePg = InvMgrEditEntrancePage
				.getInstance();
		
		entranceListPg.searchEntranceByCode(entranceCode);
		entranceListPg.clickEntranceCodeLink(entranceCode);
		editEntrancePg.waitLoading();
	}
	
	/**
	 * Go to entrance details page
	 * @param facilityCategory: "Entrance Set-up", "Facility Details", "Inventory" and "Notes and Alerts"
	 * @param entranceCode
	 */
	public void gotoEntranceDetailPage(String facilityCategory, String entranceCode){
		gotoEntranceListPage(facilityCategory);
		gotoEntranceDetailPage(entranceCode);
	}
	
	/**
	 * Go to edit entrance page from entrance list page
	 * @param entranceCode
	 */
	public void gotoEditEntrancePageFromEntranceListPage(String entranceCode){
		logger.info("Go to edit entrance page from entrance list page by code:"+entranceCode+".");
		InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage
				.getInstance();
		InvMgrEditEntrancePage editEntrancePg = InvMgrEditEntrancePage
				.getInstance();

		entranceListPg.clickEntranceCodeLink(entranceCode);
		editEntrancePg.waitLoading();
	}

	/**
	 * @override below entranceInfo method
	 * @param entranceInfo
	 * @param entranceAttriInfo
	 * @param permitTypeInfo
	 * @param addNewPermitInfo
	 * @return
	 */
	public String editEntrance(EntranceInfo entranceInfo,
			EntranceAttributesInfo entranceAttriInfo,
			PermitTypeInformation permitTypeInfo, boolean addNewPermitInfo) {
		logger.info("Edit entrance information.");
		InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage
				.getInstance();
		InvMgrEditEntrancePage editEntrancePg = InvMgrEditEntrancePage
				.getInstance();

		// go to edit entrance page.
		this.gotoEditEntrancePage(entranceInfo);

		// set up information.
		editEntrancePg.setUpInfoForEdit(entranceInfo, entranceAttriInfo,
				permitTypeInfo, addNewPermitInfo);
		editEntrancePg.clickOK();
		Object page = browser.waitExists(entranceListPg, editEntrancePg);
		String message = "";
		if (page == entranceListPg) {
			message = entranceListPg.getNotesInfo();
		}
		if (page == editEntrancePg) {
			message = editEntrancePg.getErrMsg();
		}
		return message;
	}
	
	/**
	 * Add permit types to entrance
	 * @param permitTypeInfos
	 * @return
	 */
	public String addPermitTypesToEntrance(List<PermitTypeInformation> permitTypeInfos){
		logger.info("Add entrance attribute and add permit types.");
		InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage
				.getInstance();
		InvMgrEditEntrancePage editEntrancePg = InvMgrEditEntrancePage
				.getInstance();
		IHtmlObject[] objs = browser.getDropdownList(".id", "ept_permit_type");
		int existIndex = objs.length;
		Browser.unregister(objs);
		for (int i = 0; i < permitTypeInfos.size(); i++) {
			PermitTypeInformation permitType = permitTypeInfos.get(i);
			editEntrancePg.clickAdd();
			ajax.waitLoading();
			
			editEntrancePg.setupPermitTypeInfo(permitType, existIndex+i);
		}
		
		editEntrancePg.clickOK();
		Object page = browser.waitExists(entranceListPg, editEntrancePg);
		String message = "";
		if (page == entranceListPg) {
			message = entranceListPg.getNotesInfo();
		}
		if (page == editEntrancePg) {
			message = editEntrancePg.getErrMsg();
		}
		return message;
	}

	public String addEntranceAttributeAndPermitTypes(EntranceInfo entranceInfo,
			EntranceAttributesInfo entranceAttriInfo,
			List<PermitTypeInformation> permitTypeInfos) {
		logger.info("Add entrance attribute and add permit types.");
		InvMgrEditEntrancePage editEntrancePg = InvMgrEditEntrancePage
				.getInstance();

		// go to edit entrance page.
		this.gotoEditEntrancePage(entranceInfo);

		// set up information.
		if(null != entranceInfo){
			editEntrancePg.setUpEntranceInfo(entranceInfo);
		}
		
		// set up entrance attributes.
		if(null != entranceAttriInfo){
			editEntrancePg.setUpEntranceAttriInfo(entranceAttriInfo);
		}
		
		// set up permit type information.
		return this.addPermitTypesToEntrance(permitTypeInfos);
	}

	/**
	 * Go to add new quota type page. Starts from Entrances list page and ends
	 * on new quota type page.
	 */
	public void gotoNewQuotaTypePage() {
		InvMgrEntranceListPage Entrancelistpage = InvMgrEntranceListPage
				.getInstance();
		InvMgrQuotaTypeListPage QuotaTypelistpage = InvMgrQuotaTypeListPage
				.getInstance();
		InvMgrAddNewQuotaTypePage NewQuotaTypePage = InvMgrAddNewQuotaTypePage
				.getInstance();

		logger.info("Go to new qupta type page according to the given facility");

		Entrancelistpage.waitLoading();
		Entrancelistpage.clickQuotaTypesTab();
		QuotaTypelistpage.waitLoading();

		QuotaTypelistpage.clickAddNew();
		NewQuotaTypePage.waitLoading();
	}

	public void gotoQuotaTypeListPage() {
		InvMgrEntranceListPage Entrancelistpage = InvMgrEntranceListPage
				.getInstance();
		InvMgrQuotaTypeListPage QuotaTypelistpage = InvMgrQuotaTypeListPage
				.getInstance();

		logger.info("Go to quotaType list page");

		Entrancelistpage.waitLoading();
		Entrancelistpage.clickQuotaTypesTab();
		QuotaTypelistpage.waitLoading();
	}

	public void gotoAddNewQuotaTypePageFromQuotaTypeListPage() {
		InvMgrQuotaTypeListPage QuotaTypelistpage = InvMgrQuotaTypeListPage
				.getInstance();
		InvMgrAddNewQuotaTypePage NewQuotaTypePage = InvMgrAddNewQuotaTypePage
				.getInstance();

		QuotaTypelistpage.clickAddNew();
		NewQuotaTypePage.waitLoading();
	}

	/**
	 * Go to permit type page. Starts from Entrances list page and ends on
	 * permit type list page.
	 * 
	 * @author pzhu
	 */
	public void gotoPermitTypePage() {
		InvMgrEntranceListPage Entrancelistpage = InvMgrEntranceListPage
				.getInstance();
		InvMgrQuotaTypeListPage QuotaTypelistpage = InvMgrQuotaTypeListPage
				.getInstance();
		InvMgrAddNewQuotaTypePage NewQuotaTypePage = InvMgrAddNewQuotaTypePage
				.getInstance();
		logger.info("Go to new qupta type page according to the given facility");

		Entrancelistpage.waitLoading();
		Entrancelistpage.clickQuotaTypesTab();
		QuotaTypelistpage.waitLoading();

		QuotaTypelistpage.clickAddNew();
		NewQuotaTypePage.waitLoading();
	}

	/**
	 * Create a new quota type. Starts from new quota type page and ends on
	 * Entrances list page.
	 */
	public void addNewQuotaType(QuotaTypeInfo quotaTypeInfo) {
		InvMgrAddNewQuotaTypePage NewQuotaTypePage = InvMgrAddNewQuotaTypePage
				.getInstance();
		InvMgrQuotaTypeListPage QuotaTypelistpage = InvMgrQuotaTypeListPage
				.getInstance();

		logger.info("Go to new qupta type page according to the given facility");

		NewQuotaTypePage.setInfoForNewQuptaType(quotaTypeInfo);

		QuotaTypelistpage.waitLoading();
	}

	public boolean verifyQuotaTypeExist(String quotaTypeCode) {
		InvMgrQuotaTypeListPage QuotaTypelistpage = InvMgrQuotaTypeListPage
				.getInstance();

		logger.info("Verify the quota type existed or not according to given quota type code!");

		return QuotaTypelistpage.checkQuotaTypeExists(quotaTypeCode);
	}

	/**
	 * This method used to change schedule status
	 * 
	 * @param entranceCode
	 *            - entranceCode
	 * @param status
	 */

	public void changeEntranceStatus(EntranceInfo entranceInfo, String status) {
		InvMgrEntranceListPage entranceListPg = InvMgrEntranceListPage
				.getInstance();

		logger.info("Start to Change Fee Schedule Status to " + status);

		entranceListPg.searchEntrance(entranceInfo);
		entranceListPg.selectEntranceCheckBox();

		if (status.equalsIgnoreCase("Active") && entranceListPg.getEntranceStatus().equalsIgnoreCase("No")) {
			entranceListPg.clickActivate();
		} else if (status.equalsIgnoreCase("Inactive")  && entranceListPg.getEntranceStatus().equalsIgnoreCase("Yes")) {
			entranceListPg.clickDeactivate();
		} else{
			logger.info("Entrance ("+entranceInfo.searchValue+") has the status - "+status+", no need any changed.");
		}
		entranceListPg.waitLoading();
	}

	/**
	 * go to warehouse search page.
	 */
	public void gotoWarehousesSearchPg() {
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		InvMgrWarehouseSearchPage whouseSearchPg = InvMgrWarehouseSearchPage.getInstance();
		logger.info("Go to ware house search page.");

		topMenu.gotoSpecifiedDetailPage("Warehouse Setup");
		ajax.waitLoading();
		whouseSearchPg.waitLoading();
	}

	/**
	 * add ware house.
	 * 
	 * @param whouseInfo
	 *            - the warehouse info.
	 */
	public String addWareHouse(WarehouseInfo whouseInfo) {
		InvMgrWarehouseSearchPage whouseSearchPg = InvMgrWarehouseSearchPage.getInstance();
		InvMgrWarehouseSelectLocationPage whouseSelectPg = InvMgrWarehouseSelectLocationPage
				.getInstance();
		InvMgrWarehouseDetailsPage whouseDetailsPg = InvMgrWarehouseDetailsPage
				.getInstance();
		logger.info("add warehouse");

		whouseSearchPg.clickAddNewButton();
		ajax.waitLoading();
		whouseSelectPg.waitLoading();
		whouseSelectPg.selectLocationInfo(whouseInfo);
		whouseSelectPg.clickOkButton();
		ajax.waitLoading();
		whouseDetailsPg.waitLoading();
		whouseDetailsPg.setWarehouseDetailsInfo(whouseInfo);
		//whouseDetailsPg.clickApplyButton();
		//ajax.waitLoading();
		//whouseDetailsPg.waitExists();// DEFECT-36327 
		whouseDetailsPg.clickOkButton();
		ajax.waitLoading();
		
		Object pgs = browser.waitExists(whouseDetailsPg,whouseSearchPg);
		if(pgs instanceof InvMgrWarehouseDetailsPage)
		{
			String error = whouseDetailsPg.getErrorMsg();
			logger.info("Adding new warehouse failed-->"+error);
			whouseDetailsPg.clickCancelButton();
			ajax.waitLoading();
			whouseSearchPg.waitLoading();
			return error;
			
		}else{
	
			whouseSearchPg.waitLoading();
			whouseSearchPg.searchWarehouseByName(whouseInfo.getName());
			String id = whouseSearchPg.getWarehouseID(whouseInfo.getName());
			return id;
		}
	}
	/**
	 * edit ware house info.
	 * @param warehouse
	 */
	public void editWarehouse(WarehouseInfo warehouse){
		InvMgrWarehouseSearchPage whouseSearchPg = InvMgrWarehouseSearchPage.getInstance();
		InvMgrWarehouseDetailsPage whouseDetailsPg = InvMgrWarehouseDetailsPage.getInstance();
		
		logger.info("edit ware house info");
		whouseDetailsPg.setWarehouseDetailsInfo(warehouse);
		whouseDetailsPg.clickOkButton();
		ajax.waitLoading();
		whouseSearchPg.waitLoading();
	}

	/**
	 * This method is used to search warehouse, start from warehouse search
	 * page, end at warehouse search page
	 * 
	 * @param searchType
	 * @param searchValue
	 */
	public void searchWarehouse(String searchType, String searchValue) {
		InvMgrWarehouseSearchPage whouseSearchPg = InvMgrWarehouseSearchPage.getInstance();

		logger.info("Search warehouse.");
		whouseSearchPg.setSearchCriteria(searchType, searchValue);
		whouseSearchPg.clickSearch();
		ajax.waitLoading();
		whouseSearchPg.waitLoading();
	}

	/**
	 * Go to warehouse detail page, trough lick warehouse ID
	 * 
	 * @param warehouseID
	 */
	public void gotoWarehouseDetailPgThroughClickWarehouseID(String warehouseID) {
		InvMgrWarehouseSearchPage whouseSearchPg = InvMgrWarehouseSearchPage.getInstance();
		InvMgrWarehouseDetailsPage whouseDetailsPg = InvMgrWarehouseDetailsPage
				.getInstance();

		logger.info("Go to warehouse detail page through click warehouse ID. WarehouseID = "
				+ warehouseID);
		whouseSearchPg.setSearchCriteria("Warehouse ID", warehouseID);
		whouseSearchPg.clickSearch();
		ajax.waitLoading();
		whouseSearchPg.clickWarehouseID(warehouseID);
		ajax.waitLoading();
		whouseDetailsPg.waitLoading();
	}

	/**
	 * This method is used to goto warehouse detail page though warehouse name
	 * @param warehouseName
	 */
	public void gotoWarehouseDetailPgThroughWarehouseName(String warehouseName){
		InvMgrWarehouseSearchPage whouseSearchPg = InvMgrWarehouseSearchPage.getInstance();
		InvMgrWarehouseDetailsPage whouseDetailPg = InvMgrWarehouseDetailsPage.getInstance();
		
		logger.info("Go to warehouse detail page through warehouse name. WarehouseName = " + warehouseName);
		
		whouseSearchPg.searchWarehouse("Warehouse Name", warehouseName);
		String warehouseID = whouseSearchPg.getWarehouseID(warehouseName);
		whouseSearchPg.clickWarehouseID(warehouseID);
		ajax.waitLoading();
		whouseDetailPg.waitLoading();
	}
	
	public void gotoPOSInventoryReconciliationPageFromTopMenu() {
		InvMgrTopMenuPage topMenuPage = InvMgrTopMenuPage.getInstance();
		InvMgrPOSInventoryReconciliationPage reconciliationPage = InvMgrPOSInventoryReconciliationPage.getInstance();
		
		logger.info("Goto POS Inventory Reconciliation Page from top menu.");
		topMenuPage.gotoSpecifiedDetailPage("POS Physical Inventory Reconciliation");
		reconciliationPage.waitLoading();
	}
	
	public void gotoPOSInventoryReconciliationPage() {
		InvMgrPOSInventoryReconciliationPage reconciliationPage = InvMgrPOSInventoryReconciliationPage.getInstance();
		
		logger.info("Goto POS Inventory Reconciliation Page.");
		reconciliationPage.clickPOSInventoryReconciliationTab();
		reconciliationPage.waitLoading();
	}
	
	public void gotoPOSInventoryReconciliationLogPage() {
		OrmsPOSInventoryReconciliationPage reconciliationPage = OrmsPOSInventoryReconciliationPage.getInstance();
		OrmsPOSInventoryReconciliationLogPage logPage = OrmsPOSInventoryReconciliationLogPage.getInstance();
		
		logger.info("Goto POS Inventory Reconciliation Log page.");
		reconciliationPage.clickViewInventoryReconciliationLog();

		logPage.waitLoading();
	}
	
	/**
	 * this method is used to import POS Inventory File(.csv, .text) into ORMS system.
	 * @param filePath
	 * @return
	 */
	public String importPOSInventoryFile(String filePath) {
		InvMgrPOSInventoryReconciliationPage reconciliationPage = InvMgrPOSInventoryReconciliationPage.getInstance();
		InvMgrPOSImportInventoryFilePage importFilePage = InvMgrPOSImportInventoryFilePage.getInstance();
//		FileUploadDialogPage uploadPage = FileUploadDialogPage.getInstance();
		
		logger.info("Import POS Inventory File into ORMS system.");
		reconciliationPage.clickImportInventoryFile();
		importFilePage.waitLoading();
//		if(SysInfo.getIEVersion() >= 8) {
//			importFilePage.clickBrowse();
//			uploadPage.uploadFile(filePath);
//		} else {
		importFilePage.setInventoryFilePath(filePath);
//		}
		importFilePage.waitLoading();
		importFilePage.clickOK();
		importFilePage.waitLoading();
		
		String id = "";
		if(importFilePage.isSuccess()) {
			id = importFilePage.getFileImportID();
		}
		return id;
	}
	
	public void setPOSPhysicalQtyOnHand(String barcode, String name, String physicalQty) {
		InvMgrPOSInventoryReconciliationPage reconciliationPage = InvMgrPOSInventoryReconciliationPage.getInstance();
		
		reconciliationPage.searchPOSProduct(barcode, name);
		reconciliationPage.setPhysicalQtyOnHand(name, physicalQty);
		reconciliationPage.clickApply();
		//reconciliationPage.clickOK();
	}
	
	public void reconcilePOSPhysicalInventory(String barcodes[], String names[], String physicalQtys[]) {
		if(names.length != physicalQtys.length) {
			throw new ErrorOnPageException("The POS product names' length should be same with qty's.");
		}
		for(int i = 0; i < names.length; i ++) {
			reconcilePOSPhysicalInventory(barcodes[i], names[i], physicalQtys[i]);
		}
	}
	
	public String reconcilePOSPhysicalInventory(String barcode, String name) {
		return reconcilePOSPhysicalInventory(barcode, name, null);
	}
	
	/**
	 * reconcile POS inventory physical qty
	 * @param barcode
	 * @param name
	 * @param physicalQty
	 * @return
	 */
	public String reconcilePOSPhysicalInventory(String barcode, String name, String physicalQty) {
		InvMgrPOSInventoryReconciliationPage reconciliationPage = InvMgrPOSInventoryReconciliationPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Reconcile POS(Name=" + name + ") Physical Inventory Qty: " + physicalQty);
		if((!StringUtil.isEmpty(barcode) || !StringUtil.isEmpty(name)) && !StringUtil.isEmpty(physicalQty)) {
			setPOSPhysicalQtyOnHand(barcode, name, physicalQty);
		}
		reconciliationPage.clickApply();

		ajax.waitLoading();
		reconciliationPage.clickReconcilePhysicalInventory();
		ajax.waitLoading();
		confirmWidget.waitLoading();
		confirmWidget.clickOK();
		ajax.waitLoading();
		reconciliationPage.waitLoading();
		String id = reconciliationPage.getReconciliationID();
		
		return id;
	}
	
	public String reconcilePOSPhysicalInventory() {
		return reconcilePOSPhysicalInventory("", "", "");
	}
	
	/**
	 * print inventory list in reconciliation page, actually it is running 'POS Inventory List Report'
	 * @param path - report file store path
	 * @return
	 */
	public String printInventoryList(String path) {
		InvMgrPOSInventoryReconciliationPage reconciliationPage = InvMgrPOSInventoryReconciliationPage.getInstance();
		OrmsOnlineReportProcessingPage reportProcessPage = OrmsOnlineReportProcessingPage.getInstance();
		
		logger.info("Print Inventory List - 'POS Inventory List Report'.");
		reconciliationPage.clickPrintInventoryList();
		reportProcessPage.waitLoading();
		
		File file = new File(path);
		if (!file.exists()) {
			boolean success = file.mkdir();
			if (!success) {
				throw new ItemNotFoundException(
				"Failed To Make Directory.");
			}
		}
		String fullPathAndFileName = file.getAbsolutePath() + "\\POSInventoryListReport_" + DateFunctions.getTimeStamp() + ".pdf";
		downloadReport(fullPathAndFileName);
		
		//reportProcessPage.close();
		reconciliationPage.waitLoading();
		
		return fullPathAndFileName;
	}
	
	public void gotoPOSInventoryReconciliationFromImportFilePage() {
		InvMgrPOSInventoryReconciliationPage reconciliationPage = InvMgrPOSInventoryReconciliationPage.getInstance();
		InvMgrPOSImportInventoryFilePage importFilePage = InvMgrPOSImportInventoryFilePage.getInstance();
		
		logger.info("Goto POS Inventory Reconciliation page from Import Inventory File page.");
		importFilePage.clickCancel();
		reconciliationPage.waitLoading();
	}
	
	public void gotoPOSInventoryFileLogPage() {
		InvMgrPOSInventoryFileLogPage fileLogPage = InvMgrPOSInventoryFileLogPage.getInstance();
		InvMgrPOSInventoryReconciliationPage reconciliationPage = InvMgrPOSInventoryReconciliationPage.getInstance();
		
		logger.info("Goto POS Inventory File Log page.");
		reconciliationPage.clickViewInventoryFileLog();

		
		fileLogPage.waitLoading();
	}
	
	public void gotoPOSInventoryManagementPageFromTopMenuPage() {
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		InvMgrPOSInventoryManagementPage managementPage = InvMgrPOSInventoryManagementPage.getInstance();
		
		logger.info("Go to POS Inventory Management page from top meun.");
		topMenu.gotoSpecifiedDetailPage("POS Inventory Management");
		managementPage.waitLoading();
	}
	
	public void gotoPOSPassSetupPage(){
		InvMgrPOSInventoryManagementPage managementPage = InvMgrPOSInventoryManagementPage.getInstance();
		InvMgrPOSPassSetupPage setupPg = InvMgrPOSPassSetupPage.getInstance();
		
		this.gotoPOSInventoryManagementPageFromTopMenuPage();
		managementPage.clickPOSPassSetup();
		setupPg.waitLoading();
	}
	
	public void addPosPassNum(POSPassInfo pass){
		InvMgrPOSPassSetupPage setupPg = InvMgrPOSPassSetupPage.getInstance();
		InvMgrAddPOSPassNumberWidget addPassWidget = InvMgrAddPOSPassNumberWidget.getInstance();
		
		logger.info("Add Pass Number#"+pass.getPassNum()+" for pos "+pass.getPassName());
		
		if(!setupPg.exists()) {
			this.gotoWarehousesSearchPg();
			this.gotoWarehouseDetailPgThroughWarehouseName(pass.getWarehousName());
			this.gotoPOSPassSetupPage();
		}
		setupPg.clickAddPassNum();
		ajax.waitLoading();
		addPassWidget.waitLoading();
		addPassWidget.setupPassNum(pass.getPassName(), pass.getPassNum());
		addPassWidget.clickOk();
		ajax.waitLoading();
		setupPg.waitLoading();
	}
	
	public void makePOSInventoryAdjust(POSInfo pos, POSInventoryAdjust adj){
		InvMgrPOSInventoryAdjustmentPage adjustPage = InvMgrPOSInventoryAdjustmentPage.getInstance();
		InvMgrPOSInventoryManagementPage invManagePg = InvMgrPOSInventoryManagementPage.getInstance();
		
		logger.info("Search Pos and make pos inventory adjustment...");
		invManagePg.searchPOS(null, pos.product, null);
		invManagePg.selectPOS(pos.product);
		invManagePg.clickAdjustInventory();
		ajax.waitLoading();
		adjustPage.waitLoading();
		
		if(pos.inventoryType.equalsIgnoreCase(SERIALIZED_INVENTORY_TYPE) )
		{
			if(StringUtil.isEmpty(adj.getSupplier()) )
			{
				if(null != adjustPage.getSupplier() && adjustPage.getSupplier().size()>1){
					adjustPage.selectFirstSupplier();
				}
			}else{
				adjustPage.selectSupplier(adj.getSupplier());
			}
			
			if(!StringUtil.isEmpty(adj.getDateSuppliesReceived()))
			{
				adjustPage.setDateSuppliesReceived(adj.getDateSuppliesReceived());
			}
			
			if((adj.getSerializationRange()!=null)){
				if(adj.getSerializationRange().size()>0)
				{
					adjustPage.setSerializationRange(adj.getSerializationRange());	
				}
			}
			if(!StringUtil.isEmpty(adj.getNotes()))
			{
				adjustPage.setNotes(adj.getNotes());
			}
			
			
		}
		
		adjustPage.clickOK();
		ajax.waitLoading();
		invManagePg.waitLoading();
	}
	
	public void gotoPOSInventoryManagementPage() {
		InvMgrPOSInventoryManagementPage managementPage = InvMgrPOSInventoryManagementPage.getInstance();
		
		logger.info("Go to POS Inventory Management page.");
		managementPage.clickPOSInventoryManagementTab();
		managementPage.waitLoading();
	}
	
	public void gotoPOSInventoryWebAllocationPage() {
		InvMgrPOSInventoryManagementPage managementPage = InvMgrPOSInventoryManagementPage.getInstance();
		InvMgrPOSInventoryWebAllocationPage webAllocationPage = InvMgrPOSInventoryWebAllocationPage.getInstance();
		
		logger.info("Go to Web POS Inventory page");
		managementPage.clickWebPOSInventory();
		webAllocationPage.waitLoading();
	}
	
	public void allocatePosProductToWeb(POSInfo pos)
	{
		InvMgrPOSInventoryWebAllocationPage webAllocationPage = InvMgrPOSInventoryWebAllocationPage.getInstance();
		logger.info("Allocate "+pos.product+" to Web on InvMgrPOSInventoryWebAllocationPage.... ");
		
		webAllocationPage.searchPOS(pos.product, pos.productGroup, null);
		webAllocationPage.allocatePOS(pos.product);
	}
	
	
	public void deallocatePosProductToWeb(POSInfo pos)
	{
		InvMgrConfirmDialogWidget confirm = InvMgrConfirmDialogWidget.getInstance();
		InvMgrPOSInventoryWebAllocationPage webAllocationPage = InvMgrPOSInventoryWebAllocationPage.getInstance();
		logger.info("Deallocate "+pos.product+" to Web on InvMgrPOSInventoryWebAllocationPage.... ");
		
		webAllocationPage.searchPOS(pos.product, pos.productGroup, null);
		webAllocationPage.selectPOS(pos.product);
		webAllocationPage.clickDeallocateSelectedPOSProducts();
		ajax.waitLoading();
		
		confirm.waitLoading();
		confirm.clickOK();
		ajax.waitLoading();
		webAllocationPage.waitLoading();
		
	}
	
	public void allocatePosProductToCallCenter(POSInfo pos) {
		InvMgrPOSInventoryCallCenterAllocationPage callCenterAllocationPage = InvMgrPOSInventoryCallCenterAllocationPage.getInstance();
		logger.info("Allocate "+pos.product+" to Call Center on InvMgrPOSInventoryCallCenterAllocationPage.... ");
		
		callCenterAllocationPage.searchPOS(pos.allocateStatus,pos.product, pos.productGroup, null);
		
		String message = null;
		try{
			callCenterAllocationPage.getMessage();
		} catch(ItemNotFoundException e){// can't find message
			logger.info(pos.product+" has been allocated!!");
		}
		
		// POS has been allocated, no need to allocate again
		if(!"No POS Products/Serialized Ranges found for the search criteria".equalsIgnoreCase(message)) {
			callCenterAllocationPage.allocatePOS(pos.product);
		}
	}
	
	public void deallocatePosProductToCallCenter(POSInfo pos)
	{
		InvMgrConfirmDialogWidget confirm = InvMgrConfirmDialogWidget.getInstance();
		InvMgrPOSInventoryCallCenterAllocationPage callCenterAllocationPage = InvMgrPOSInventoryCallCenterAllocationPage.getInstance();
		logger.info("Deallocate "+pos.product+" to Call Center on InvMgrPOSInventoryCallCenterAllocationPage.... ");
		
		callCenterAllocationPage.searchPOS(pos.product, pos.productGroup, null);
		callCenterAllocationPage.selectPOS(pos.product);
		callCenterAllocationPage.clickDeallocateSelectedPOSProducts();
		ajax.waitLoading();
		
		confirm.waitLoading();
		confirm.clickOK();
		ajax.waitLoading();
		callCenterAllocationPage.waitLoading();
		
	}
	public void gotoPOSInventoryCallCenterAllocationPage() {
		InvMgrPOSInventoryManagementPage managementPage = InvMgrPOSInventoryManagementPage.getInstance();
		InvMgrPOSInventoryCallCenterAllocationPage callAllocationPage = InvMgrPOSInventoryCallCenterAllocationPage.getInstance();

		logger.info("Go to Call Center POS Inventory page");
		managementPage.clickCallCenterPOSInventory();
		callAllocationPage.waitLoading();
	}
	
	public void adjustPOSInventory(POSInfo pos) {
		
	}
	
	/**
	 * 
	 * @param poss
	 */
	public void allocatePOSInventoryToWeb(POSInfo... poss) {
		InvMgrPOSInventoryWebAllocationPage webAllocationPage = InvMgrPOSInventoryWebAllocationPage.getInstance();
		
		for(POSInfo pos:poss) {
			logger.info("Allocate POS Inventory(Name=" + pos.product + ") to Web");
			webAllocationPage.searchPOS(pos.product, pos.productGroup, pos.inventoryType);
			webAllocationPage.allocatePOS(pos.product);
		}
	}
	
	/**
	 * Allocate POS inventories to Call Center sales channel
	 * @param poss
	 */
	public void allocatePOSInventoryToCallCenter(POSInfo... poss) {
		InvMgrPOSInventoryCallCenterAllocationPage callAllocationPage = InvMgrPOSInventoryCallCenterAllocationPage.getInstance();
		
		for(POSInfo pos:poss) {
			logger.info("Allocate POS Inventory(Name=" + pos.product + ") to Web");
//			callAllocationPage.searchPOS(pos.product, pos.productGroup, pos.inventoryType);
			callAllocationPage.searchPOS(pos, pos.serializedRangeFrom, pos.serializedRangeTo);
			callAllocationPage.allocatePOS(pos.product);
		}
	}
	
	public void gotoPOSInventoryFieldAllocationPage(RevenueLocation rev) {
		InvMgrPOSInventoryManagementPage managementPage = InvMgrPOSInventoryManagementPage.getInstance();
		InvMgrPOSInventoryFieldAllocationPage fieldAllocationPage = InvMgrPOSInventoryFieldAllocationPage.getInstance();
		OrmsPosSelectRevenueLocationWidget selectRevenueLocation = OrmsPosSelectRevenueLocationWidget
				.getInstance();
		
		logger.info("Go to Field POS Inventory page");
		managementPage.clickFieldPOSInventory();
		
		selectRevenueLocation.waitLoading();
		selectRevenueLocation.selectLocation(rev);
		selectRevenueLocation.clickOK();
		ajax.waitLoading();
		fieldAllocationPage.waitLoading();
	}
	
	public void allocatePOSInventoryToField(POSInfo... poss) {
		InvMgrPOSInventoryFieldAllocationPage fieldAllocationPage = InvMgrPOSInventoryFieldAllocationPage.getInstance();
		
		for(POSInfo pos:poss) {
			logger.info("Allocate POS Inventory(Name=" + pos.product + ") to Field.");
			if(pos.serilizeNumberType.equalsIgnoreCase("Pre-Defined")){
				for(String passNum:pos.passNums){
					fieldAllocationPage.searchByNameAndNumber(pos.product, pos.serilizeNumberType,passNum);
					fieldAllocationPage.allocatePOS(pos.product);
				}
			}else{
				fieldAllocationPage.searchPOS(pos, pos.serializedRangeFrom, pos.serializedRangeTo);
				fieldAllocationPage.allocatePOS(pos.product);
			}
		}
	}
	
	/**
	 * This method is used to assign pos product to warehouse
	 * @param posNames
	 */
	public void assignPOSToWarehouse(String... posNames){
		InvMgrPosSetupListPage posSetupListPg = InvMgrPosSetupListPage.getInstance();
		logger.info("Assign POS to warehouse.");
		
		for(String posName : posNames){
			posSetupListPg.selectCheckBox(posName);
		}
		
		posSetupListPg.clickAssignSelectedPOSProducts();
		ajax.waitLoading();
		posSetupListPg.waitLoading();
	}
	
	/**
	 * This method is used to goto print pos barcode page.
	 */
	public void gotoPrintPOSBarcodePage() {
		InvMgrTopMenuPage invTpMenPg = InvMgrTopMenuPage.getInstance();
		InvMgrPosSetupListPage posSetupListPg = InvMgrPosSetupListPage.getInstance();
		InvMgrPosPrintBarcodePage invPrintPOSBarcodePg = InvMgrPosPrintBarcodePage
				.getInstance();

		logger.info("Go to print pos barcode page.");
		invTpMenPg.gotoSpecifiedDetailPage("POS Product Setup");
		posSetupListPg.waitLoading();
		posSetupListPg.clickPOSPrintBarcodes();
		invPrintPOSBarcodePg.waitLoading();
	}

	/**
	 * This method is used to search pos inventory info from pos print barcode
	 * page, start at pos print barcode page, end at pos print barcode page
	 * 
	 * This method is uedt to goto pos setup list page
	 */
	public void gotoPOSSetupListPage(){
		InvMgrTopMenuPage invTpMenPg = InvMgrTopMenuPage.getInstance();
		InvMgrPosSetupListPage posSetupListPg = InvMgrPosSetupListPage.getInstance();
			
		logger.info("Go to print pos barcode page.");
		invTpMenPg.gotoSpecifiedDetailPage("POS Product Setup");
		posSetupListPg.waitLoading();
	}
	
	/**
	 * This method is used to search pos inventory info from pos print barcode page,
	 * start at pos print barcode page, end at pos print barcode page
	 * @param posName
	 * @param posGroup
	 * @param qtyOnHand
	 */
	public void searchPOSInventoryFromPOSPrintBarcodePage(String posName,
			String posGroup, String qtyOnHand) {
		InvMgrPosPrintBarcodePage posPrintBarcodPg = InvMgrPosPrintBarcodePage
				.getInstance();

		logger.info("Search pos inventory from pos print barcode page.");
		posPrintBarcodPg.setSearchCriteria(posName, posGroup, qtyOnHand);
		posPrintBarcodPg.clickGo();
		ajax.waitLoading();
		posPrintBarcodPg.waitLoading();
	}

	/**
	 * down load a report
	 * 
	 * @param path
	 *            file path and name
	 */
	public void downloadReport(String path) {
		FileDownloadDialogPage filePage = FileDownloadDialogPage.getInstance();
		File pdfFile = new File(path);

		if (pdfFile.exists()) {
			boolean delFile = pdfFile.delete();
			if (!delFile) {
				throw new ErrorOnDataException("Fail to Delete File.");
			}
		}
		filePage.downloadSaveFile(path);

		int i = 1;
		while (!pdfFile.exists()) {
			Browser.sleep(1);
			i++;
			if (i > 120)
				break;
		}
		if (pdfFile.exists()) {
			logger.info("Download Report cost " + i + " seconds!");
		} else {
			logger.error("Can not download Report in " + i + " seconds!");
			throw new ItemNotFoundException("Can not download PDF in " + i
					+ " seconds!");
		}

		if (filePage.exists()) {
			filePage.clickClose();
		}
	}

	/**
	 * This method is used to print POS barcode
	 * 
	 * @param posInfos
	 * @param path
	 * @return error message, or file path
	 */
	public String printPOSBarcodes(HashMap<String, String> posInfos, String path) {
		InvMgrPosPrintBarcodePage posPrintBarcodPg = InvMgrPosPrintBarcodePage
				.getInstance();
		OrmsOnlineReportProcessingPage onlineRptPg = OrmsOnlineReportProcessingPage
				.getInstance();
		FileDownloadDialogPage downloadPage = FileDownloadDialogPage.getInstance();
		
		String value = "";
		logger.info("Print POS barcode info.");
		for (Entry<String, String> e : posInfos.entrySet()) {
			posPrintBarcodPg.selectCheckBox(e.getKey());
			posPrintBarcodPg.setNumberOfPrint(e.getKey(), e.getValue());
		}

		posPrintBarcodPg.clickPrintBarcodes();
		ajax.waitLoading();
		downloadPage.setDismissible(false);
		downloadPage.setBeforePageLoading(false);
		Object pages = browser.waitExists(onlineRptPg, downloadPage, posPrintBarcodPg);
		if (pages == posPrintBarcodPg) {
			value = posPrintBarcodPg.getMessage();
		} else {
			File file = new File(path);
			if (!file.exists()) {
				boolean success = file.mkdir();
				if (!success) {
					throw new RuntimeException("Failed to create directory "
							+ path);
				}
			}

			value = file.getAbsolutePath() + "\\" + "Barcodes_IM.pdf";
			downloadReport(value);
			//onlineRptPg.close();
			posPrintBarcodPg.waitLoading();
		}

		return value;
	}

	/**
	 * Verify One Report by compare the report with given template file
	 * 
	 * @param templatesPath
	 * @param comparedPath
	 * @param errorResultPath
	 */
	public boolean verifyPdfReport(String templatesPath, String comparedPath) {
		logger.info("Verify PDF format Report.");

		boolean isCorrect = true;
		try {
			if (!PDFParser.verifyRptRunDate(comparedPath)) {
				isCorrect = false;
			}
			File file = new File(comparedPath);
			List<String> list = PDFParser.comparePDFFile(templatesPath + "/"
					+ file.getName(), comparedPath);
			for (Object l : list) {
				if (l instanceof String) {
					isCorrect = false;
				} else if (l instanceof List<?>) {
					if (((List<?>) l).size() > 0) {
						isCorrect = false;
					}
				} else {
					throw new RuntimeException("Unknown Object Type.");
				}
			}
		} catch (IOException e) {
			throw new ItemNotFoundException(e.getMessage());
		}
		return isCorrect;
	}
	/***
	 * add or edit POS supplier success.
	 * @param supplier
	 * @return
	 */
	//public String addOrEditPosSupplier(PosSupplier supplier){
		//return addOrEditPosSupplier(supplier,true);
	//}
	/**
	 * From InvMgrPosSuppierSearchPage to InvMgrPosSuppierSearchPage page.
	 * add POs supplier.
	 */
	public String addPosSupplier(PosSupplier supplier){
		return this.addPosSupplier(supplier, true);
	}
	/**
	 * add POs supplier with cancel button.
	 * @param supplier
	 * @return
	 */
	public String addPosSupplierCancel(PosSupplier supplier){
		return this.addPosSupplier(supplier, false);
	}
	/**
	 * From InvMgrPosSuppierDetailsPage to InvMgrPosSuppierSearchPage page.
	 * edit Pos supplier.
	 * @param supplier
	 * @return
	 */
	public String editPosSupplier(PosSupplier supplier){
		return this.editPosSupplier(supplier, true);
	}
	/**
	 * edit Pos supplier cancel.
	 * @param supplier
	 * @return
	 */
	public String editPosSupplierCancel(PosSupplier supplier){
		return this.editPosSupplier(supplier,false);
	}
	
	//
	/**
	 * From InvMgrPosSuppierSearchPage to InvMgrPosSuppierSearchPage page.
	 * add pos supplier.
	 * 
	 * @param supplier
	 *            - the pos supplier info.
	 */
	private String addPosSupplier(PosSupplier supplier,boolean isOk) {
		InvMgrPOSSupplierSearchPage supplierSarchPg = InvMgrPOSSupplierSearchPage
				.getInstance();
		InvMgrPOSSupplierDetailsPage supplierDetailPg = InvMgrPOSSupplierDetailsPage
				.getInstance();

		
		logger.info("Add pos supplier");
		supplierSarchPg.clickAddSupplier();
		ajax.waitLoading();
		supplierDetailPg.waitLoading();
		String id = editPosSupplier(supplier, isOk);
		logger.info("Add POS Supplier successfully - " + id);
		return id;
	}
	
	
	/**
	 * from InvMgrPosSuppierDetailsPage to InvMgrPosSuppierDetailsPage.
	 * @param supplier
	 * @param isOk
	 * @return
	 */
	private String editPosSupplier(PosSupplier supplier,boolean isOk){
		String toReturn = "";
		InvMgrPOSSupplierSearchPage supplierSarchPg = InvMgrPOSSupplierSearchPage
				.getInstance();
		InvMgrPOSSupplierDetailsPage supplierDetailPg = InvMgrPOSSupplierDetailsPage
				.getInstance();
		logger.info("Add pos supplier");
		
		supplierDetailPg.setPosSupplierInfo(supplier);
		if(isOk){
			supplierDetailPg.clickApplyButton();
		}else{
			supplierDetailPg.clickCancel();
		}
		ajax.waitLoading();
//		supplierDetailPg.waitExists();
		Object page = browser.waitExists(supplierSarchPg, supplierDetailPg);
		if (page == supplierDetailPg) {
			if(supplierDetailPg.checkErrorMessageExist()){
				toReturn = supplierDetailPg.getErrorMessage();
			}else{
				toReturn = supplierDetailPg.getSupplierId();
				supplierDetailPg.clickOkbutton();
				ajax.waitLoading();
				supplierSarchPg.waitLoading();
			}
		}
		
		return toReturn;
	}
	/**
	 * go to supplier search page.
	 */
	public void gotoPosSupplierSearchPageFromTopMenu() {
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		InvMgrPOSSupplierSearchPage supplierSearchPg = InvMgrPOSSupplierSearchPage
				.getInstance();
		logger.info("Go to pos supplier setup page.");

		topMenu.gotoSpecifiedDetailPage("POS Supplier Setup");
		ajax.waitLoading();
		supplierSearchPg.waitLoading();
	}

	/**
	 * go to pos supplier details page.
	 * 
	 * @param supplierId
	 *            - the pos supplier details page.
	 */
	public void gotoPOSSupplierDetailsPage(String supplierId) {
		InvMgrPOSSupplierSearchPage supplierSearchPg = InvMgrPOSSupplierSearchPage
				.getInstance();
		InvMgrPOSSupplierDetailsPage supplierDetailPg = InvMgrPOSSupplierDetailsPage
				.getInstance();

		logger.info("Go to POS Supplier(ID=" + supplierId + ") details page.");
		supplierSearchPg.searchPosSupplierById(supplierId);
		supplierSearchPg.clickSupplierId(supplierId);
		ajax.waitLoading();
		supplierDetailPg.waitLoading();
	}
	
	public void activatePOSSupplier(String... supplierIds) {
		InvMgrPOSSupplierSearchPage supplierSearchPg = InvMgrPOSSupplierSearchPage
		.getInstance();
		InvMgrPOSConfirmSupplierDeactivationWidget confirmSupplierDeWgt = InvMgrPOSConfirmSupplierDeactivationWidget
				.getInstance();
		
		for(String id : supplierIds) {
			logger.info("Activate POS Supplier - " + id);
			supplierSearchPg.searchPosSupplierById(id);
			supplierSearchPg.selectSupplierCheckBox(id);
			supplierSearchPg.clickActivateSelectedSuppliers();
			ajax.waitLoading();
			Object page = browser
					.waitExists(confirmSupplierDeWgt, supplierSearchPg);
			if (confirmSupplierDeWgt == page) {
				confirmSupplierDeWgt.clickOK();
				ajax.waitLoading();
				supplierSearchPg.waitLoading();
			}
		}
	}
	
	/**
	 * deactive POS supplier with clicking OK button.
	 * @param supplierIdList
	 * @return
	 */
	public void deactivatePOSSupplier(String... supplierIdList){
		this.deActivePosSupplier(true,supplierIdList);
	}
	
	/**
	 * deactive POS supplier with clicking cancel button.
	 * @param supplierIdList
	 */
	public void deActivePosSupplierCancel(String... supplierIdList){
		this.deActivePosSupplier(false, supplierIdList);
	}
	/**
	 * Deactive pos supplier.
	 * 
	 * @param supplierIdlist
	 *            - the pos supplier list.
	 */
	private void deActivePosSupplier(boolean isOK,String... supplierIdList) {
		InvMgrPOSSupplierSearchPage supplierSearchPg = InvMgrPOSSupplierSearchPage
				.getInstance();
		InvMgrPOSConfirmSupplierDeactivationWidget confirmSupplierDeWgt = InvMgrPOSConfirmSupplierDeactivationWidget
				.getInstance();
		
		for(String id : supplierIdList) {
			logger.info("Deacctivate pos supplier - " + id);
			supplierSearchPg.searchPosSupplierById(id);
			supplierSearchPg.selectSupplierCheckBox(id);
			supplierSearchPg.clickDeactivateSelectedSuppliers();
			ajax.waitLoading();
			Object page = browser
					.waitExists(confirmSupplierDeWgt, supplierSearchPg);
			if (confirmSupplierDeWgt == page) {
				if(isOK){
					confirmSupplierDeWgt.clickOK();
				}else{
					confirmSupplierDeWgt.clickCancel();
				}
				ajax.waitLoading();
				supplierSearchPg.waitLoading();
			}
		}
	}
	
	/**
	 * add a new pos info for warehouse.
	 * This workflow starts at warehouse pos search page, and ends at warehouse page.
	 * @param posInfo - the info of pos product.
	 */
	public String addPOSProduct(POSInfo posInfo){
		InvMgrPosSetupListPage posSetupPg = InvMgrPosSetupListPage.getInstance();
		InvMgrPosSetupDetailsPage posSetupDetailsPg = InvMgrPosSetupDetailsPage.getInstance();
		InvMgrPosSetBarcodePage posBarcodePg = InvMgrPosSetBarcodePage.getInstance();
		OrmsConfirmDialogWidget confirmWidget =  OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Add pos product");
		
		posSetupPg.clickAddPosProduct();
		ajax.waitLoading();
		posSetupDetailsPg.waitLoading();
		setupPosInfo(posInfo);
		posSetupDetailsPg.clickApplyButton();
		Object page = browser.waitExists(confirmWidget,posBarcodePg,posSetupDetailsPg);
		if(page == confirmWidget){
			confirmWidget.clickOK();
			ajax.waitLoading();
			posBarcodePg.waitLoading();
		}
		if(page == posSetupDetailsPg){
			return posSetupDetailsPg.getMessage();
		}
		posInfo.productID = posBarcodePg.getPosProductId();
		posBarcodePg.clickOkButton();
		ajax.waitLoading();
		posSetupPg.waitLoading();
		
		logger.info(posInfo.inventoryType+" POS product is created, of which the name is "+posInfo.product);
		return posInfo.productID;
	}
	
	/**
	 * Go to Add Master POS Product page. Starts from master POS search page and ends at add master POS product page
	 * 
	 * @author Lesley Wang
	 * @date Aug 20, 2012
	 */
	public void gotoAddMasterPOSProductPage() {
		InvMgrMasterPosSearchPage masterPosSetupPg = InvMgrMasterPosSearchPage.getInstance();
		InvMgrPosSetupDetailsPage posSetupDetailsPg = InvMgrPosSetupDetailsPage.getInstance();
		
		logger.info("Go to Add master pos product page.");
		
		masterPosSetupPg.clickAddPosProduct();
		ajax.waitLoading();
		posSetupDetailsPg.waitLoading();
	}
	
	/**
	 * Add a new pos product from master pos setup page
	 * This workflow starts at master pos search page, and ends at master page.
	 * @param posInfo - the info of pos product.
	 */
	public String addMasterPosProduct(POSInfo posInfo){
		InvMgrMasterPosSearchPage masterPosSetupPg = InvMgrMasterPosSearchPage.getInstance();
		InvMgrPosSetupDetailsPage posSetupDetailsPg = InvMgrPosSetupDetailsPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget =  OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Add master pos product.");
		
//		masterPosSetupPg.clickAddPosProduct();
//		ajax.waitLoading();
//		posSetupDetailsPg.waitExists();
		gotoAddMasterPOSProductPage();
		setupPosInfo(posInfo);
		posSetupDetailsPg.clickApplyButton();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmWidget,posSetupDetailsPg);
		if(page == confirmWidget){
			confirmWidget.clickOK();
			ajax.waitLoading();
			posSetupDetailsPg.waitLoading();
		}
		posInfo.productID = posSetupDetailsPg.getProductId();
		posSetupDetailsPg.clickOkButton();
		ajax.waitLoading();
		masterPosSetupPg.waitLoading();
		return posInfo.productID;
	}
	
	/**
	 * set the pos info.
	 * 
	 * @param posInfo
	 *            - the info of pos.
	 */
	public void setupPosInfo(POSInfo posInfo) {
		InvMgrPosSetupDetailsPage posDetailsPg = InvMgrPosSetupDetailsPage
				.getInstance();
//		OrmsPosSelectRevenueLocationWidget selectRevenueLocation = OrmsPosSelectRevenueLocationWidget
//				.getInstance();

		posDetailsPg.selectOrderType(posInfo.orderType);
		posDetailsPg.setPosProductCode(posInfo.productCode);
		posDetailsPg.setPosProdectName(posInfo.product);
		posDetailsPg.selectProductClass(posInfo.productClass);
		posDetailsPg.selectProductSubClass(posInfo.productSubClass);

		if(!posDetailsPg.getProductGroup().equalsIgnoreCase(posInfo.productGroup)){
			posDetailsPg.selectProductGroup(posInfo.productGroup);
			ajax.waitLoading();
			posDetailsPg.waitLoading();
		}
		
		// added by Nicole start
		if(null != posInfo.productRelactionshipType && !posDetailsPg.getRelationType().equalsIgnoreCase(posInfo.productRelactionshipType)){
			posDetailsPg.selectRelationType(posInfo.productRelactionshipType);// added by Nicole
			ajax.waitLoading();
			posDetailsPg.waitLoading();
		}
		
		// if relation type is Product Package, the inventory type is set to No Inventory automatically.
		if(!"Product Package".equalsIgnoreCase(posInfo.productRelactionshipType)){
			if (!posDetailsPg.getSelectedInventoryType().equalsIgnoreCase(posInfo.inventoryType)) {
				posDetailsPg.selectInventoryType(posInfo.inventoryType);
				ajax.waitLoading();
				posDetailsPg.waitLoading();
			}
		}
		// end

		posDetailsPg.setPosProductDescription(posInfo.productDescription);
		posDetailsPg.setBarcode(posInfo.barcodeList);

		if (posDetailsPg.checkSerializationTypeExist()) {
			posDetailsPg.selectSerializationType(posInfo.serilizType);
			ajax.waitLoading();
		}
		if (posDetailsPg.checkSerializationReferenceId()) {
			posDetailsPg
					.selectSerializationReferenceId(posInfo.serilizReferenceId);
			ajax.waitLoading();
		}
		
		if(posDetailsPg.checkSerializationNumTypeExists()){
			posDetailsPg.selectSerializationNumType(posInfo.serilizeNumberType);
			ajax.waitLoading();
		}
		
		if (posDetailsPg.checkSerializationFormat()) {
			posDetailsPg.setSerializationFormat(posInfo.serilizFormat);
		}
		if (posDetailsPg.checkSerializationIncrement()) {
			posDetailsPg.setSerializationIncrement(posInfo.serilizIncrement);
		}
		if (posDetailsPg.checkSerializationExpiryDateRule()) {
			posDetailsPg.selectSerializationExpiryDateRule(posInfo.serilizRule);
			ajax.waitLoading();
		}
		if (posDetailsPg.isExtraDecimalIndicatorExisted()) {
			posDetailsPg
					.selectExtraDecimalIndicator(posInfo.extraDecimalIndicator);
		}

		this.changePOSProductAvailableLocation(posInfo.availableLocation);
		// add pos for specificLocation
		if (posInfo.specificLocation) {
//			posDetailsPg.selectSpecificRevLoc();
//			ajax.waitLoading();
//			selectRevenueLocation.waitExists();
//			selectRevenueLocation.selectLocation(posInfo.revLocation);
//			selectRevenueLocation.clickOK();
//			ajax.waitLoading();
//			posDetailsPg.waitExists();
			this.selectPOSRevenueSpecificLocation(posInfo.revLocation, true);
		}

		// add POS Attributes. The display of these fields is based on the selected product group
		if(StringUtil.notEmpty(posInfo.acquierZipCodeInSale)){
			posDetailsPg.selectAcquireZipCode(posInfo.acquierZipCodeInSale);
		}
		if(StringUtil.notEmpty(posInfo.applicationCustomer)){
			posDetailsPg.selectApplicationCustomer(posInfo.applicationCustomer);
		}
		if(StringUtil.notEmpty(posInfo.state)){
			posDetailsPg.selectState(posInfo.state);
		}
		if(StringUtil.notEmpty(posInfo.numOfOccupants)){
			posDetailsPg.setNumOfOccupants(posInfo.numOfOccupants);
		}
		if(StringUtil.notEmpty(posInfo.vehicle)){
			posDetailsPg.selectVehicle(posInfo.vehicle);
		}
		if (posDetailsPg.isAccountScheduleDropdownListEnabled()) {
			// When there are more account schedules for the specific product or product group, the dropdown list is enabled. Otherwise it is disabled.
			if(Boolean.valueOf(TestProperty.getProperty("forceOperation"))) {//support scripts, ignore the account value
				//posDetailsPg.selectAccount(1); //For page change, there are different account should be choose for different location
				posDetailsPg.selectDefaultAccountForAllAgent();
			} else {//for cases, select given value
				if(StringUtil.notEmpty(posInfo.account)){
					posDetailsPg.selectAccount(posInfo.account);
				} else {
					//posDetailsPg.selectAccount(1);  //For page change, there are different account should be choose for different location
					posDetailsPg.selectDefaultAccountForAllAgent();
				}
			}
		}
		
		if(StringUtil.notEmpty(posInfo.overrideUnitPrice)) {
			if(posDetailsPg.isDefaultUnitPriceExists()) {
				posDetailsPg.setDefaultUnitPrice(posInfo.defaultUnitPrice);
			}
			if(posDetailsPg.isOverrideDefaultUnitPriceExists()) {
				posDetailsPg.selectOverrideDefaultUnitPrice(posInfo.overrideUnitPrice);
			}
		}
		
		
		// add POS Sales Attributes
		posDetailsPg.setPosSalesAttributes(posInfo.attributesArray);
	}

	/**
	 * Select POS Revenue Specifc Location on POS Setup Details page. 
	 * @param revLoc
	 * @param isOK
	 */
	public void selectPOSRevenueSpecificLocation(RevenueLocation revLoc, boolean isOK) {
		InvMgrPosSetupDetailsPage posDetailsPg = InvMgrPosSetupDetailsPage
			.getInstance();
		OrmsPosSelectRevenueLocationWidget selectRevenueLocation = OrmsPosSelectRevenueLocationWidget
			.getInstance();
		
		logger.info("Select POS Revenue Specifc Location on POS Setup Details page...");
		if (!posDetailsPg.isSpecificLocationSelected()) {
			posDetailsPg.selectSpecificRevLoc();
		} else if (posDetailsPg.isChangeLocationButtonExist()) {
			posDetailsPg.clickChangeLocation();
		}
		ajax.waitLoading();
		selectRevenueLocation.waitLoading();
		selectRevenueLocation.selectLocation(revLoc);
		if (isOK) {
			selectRevenueLocation.clickOK();
		} else {
			selectRevenueLocation.clickCancel();
		}	
		ajax.waitLoading();
		posDetailsPg.waitLoading();
	}
	
	/**
	 * go to pos product setup page.
	 */
	public void gotoPosProductSetupPage(){
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		InvMgrPosSetupListPage posSetUpPg = InvMgrPosSetupListPage.getInstance();
		logger.info("Go to pos supplier setup page.");
		
		topMenu.gotoSpecifiedDetailPage("POS Product Setup");
		ajax.waitLoading();
		posSetUpPg.waitLoading();
	}
	
	/**
	 * go to Master POS setup page from inventory top menu
	 */
	public void gotoMasterPosSetupPage(){
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		InvMgrMasterPosSearchPage masterPosSetupPg = InvMgrMasterPosSearchPage.getInstance();
		logger.info("Go to Master POS Setup page.");
		
		topMenu.gotoSpecifiedDetailPage("Master POS Setup");
		ajax.waitLoading();
		masterPosSetupPg.waitLoading();
	}
	
	public void deactivatePOS(String... ids) {
		InvMgrMasterPosSearchPage posSearchPage = InvMgrMasterPosSearchPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Deactivate POS - " + StringUtil.arrayToString(ids));
		for(String id : ids) {
			posSearchPage.deactivatePOS(id);
			confirmWidget.waitLoading();
			confirmWidget.clickOK();
			ajax.waitLoading();
			posSearchPage.waitLoading();
		}
	}
	
	/** Activate POS on Master POS Product List page */
	public void activatePOS(String... ids) {
		InvMgrMasterPosSearchPage posSearchPage = InvMgrMasterPosSearchPage.getInstance();
		
		logger.info("Activate POS - " + StringUtil.arrayToString(ids));
		for(String id : ids) {
			posSearchPage.activatePOS(id);
			posSearchPage.waitLoading();
		}
	}
	
	/**
	 * go to pos product supplier setup page.
	 * @param supplierId- supplier id.
	 */
	public void gotoPosProductSupplierSetupPage(){
		InvMgrPOSSupplierDetailsPage supplierDetailPg = InvMgrPOSSupplierDetailsPage.getInstance();
		InvMgrPOSSupplierProductAssignmentListPage productSupplierSetupPg = InvMgrPOSSupplierProductAssignmentListPage.getInstance();
		
		logger.info("go to product supplier setup page");
		supplierDetailPg.clickProductSupplerSetup();
		ajax.waitLoading();
		productSupplierSetupPg.waitLoading();
	}
	
	/**
	 * go to pos supplier details page.
	 * @param posId -  the pos id.
	 */
	public void gotoSupplierPOSAssignmentDetailsPage(String posId){
		InvMgrPOSSupplierProductAssignmentListPage productSupplierSetupPg = InvMgrPOSSupplierProductAssignmentListPage.getInstance();
		InvMgrPOSSupplierProductAssignmentDetailsPage posSupplierProductDetailsPg = InvMgrPOSSupplierProductAssignmentDetailsPage.getInstance();
		
		logger.info("go to pos supplier product details page");
		productSupplierSetupPg.clickProductId(posId);
		ajax.waitLoading();
		posSupplierProductDetailsPg.waitLoading();
	}
	
	public void gotoSupplierPOSAssignmentListPageFromDetailsPage() {
		InvMgrPOSSupplierProductAssignmentListPage listPage = InvMgrPOSSupplierProductAssignmentListPage.getInstance();
		InvMgrPOSSupplierProductAssignmentDetailsPage detailPage = InvMgrPOSSupplierProductAssignmentDetailsPage.getInstance();
		
		logger.info("Go back to POS Product-Supplier setup page from detail page.");
		detailPage.clickCancelButton();
		ajax.waitLoading();
		listPage.waitLoading();
	}
	
	/**
	 * edit pos supplier assignment.
	 * @param productCode
	 * @param unitCode
	 */
	public void editSupplierPOSAssignment(String productCode,String unitCode){
		InvMgrPOSSupplierProductAssignmentDetailsPage posSupplierProductDetailsPg = InvMgrPOSSupplierProductAssignmentDetailsPage.getInstance();
		InvMgrPOSSupplierProductAssignmentListPage productSupplierSetupPg = InvMgrPOSSupplierProductAssignmentListPage.getInstance();
		
		logger.info("Edit Supplier-POS Assignment detail info.");
		posSupplierProductDetailsPg.setSupplierProductCode(productCode);
		posSupplierProductDetailsPg.setSupplierUnitCost(unitCode);
		posSupplierProductDetailsPg.clickOkButton();
		ajax.waitLoading();
		productSupplierSetupPg.waitLoading();
	}
	
	/**
	 * Release inventory for the given site for the given date period.
	 * This keyword start from Inventory list page and end at Inventory List page
	 * @param siteNum
	 * @param dateFrom
	 * @param dateTo
	 */
	public void releaseInventory(String siteNum, String dateFrom, String dateTo) {
		InvMgrFacilityInventoryPage invListPg = InvMgrFacilityInventoryPage
				.getInstance();
		InvMgrInventoryDetailPage invDetail = InvMgrInventoryDetailPage
				.getInstance();
		ConfirmDialogPage confirmDialog = ConfirmDialogPage.getInstance();

		logger.info("Release site#"+siteNum+" inventory from "+dateFrom+" to "+dateTo);
		
		invListPg.setFromDate(dateFrom);
		invListPg.setToDate(dateTo);
		invListPg.setSiteNumber(siteNum);
		invListPg.clickGo();

		boolean found = invListPg.checkResult();
		if (found) {
			String[] invIDs = invListPg.getInventoryIDs();
			for (String id : invIDs) {
				invListPg.clickInventoryID(id);
				invDetail.waitLoading();
				invDetail.clickReleaseInvButton();
				browser.waitExists(confirmDialog, invListPg);
			}
		}

	}
	
	/**
	 * Release slip inventory, start from inventory detail page.
	 * Click OK button in confirm widget.
	 */
	public void releaseSlipInventory(){
		this.releaseSlipInventory(true);
	}
	
	/**
	 * Release slip inventory, start from inventory detail page.
	 * @param isOK is click OK in confirm widget.
	 */
	public String releaseSlipInventory(boolean isOK){
		InvMgrSlipInventoryListPage slipInvListPg = InvMgrSlipInventoryListPage.getInstance();
		InvMgrInventoryDetailPage invDetail = InvMgrInventoryDetailPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		String message = "";
		
		invDetail.clickReleaseInvButton();
		browser.waitExists(confirmWidget, slipInvListPg);
		if(confirmWidget.exists()){
			message = confirmWidget.getErrorMsg();
			if(isOK){
				confirmWidget.clickOK();
				ajax.waitLoading();
				slipInvListPg.waitLoading();
			} else {
				confirmWidget.clickCancel();
				ajax.waitLoading();
				invDetail.waitLoading();
			}
		}
		
		return message;
	}

	/**
	 * Modify Online permit print info
	 * @param confirmationPeriod
	 * @param confirmationPeriodStartTime
	 * @param isAM   --true: Select 'AM'
	 *               --false: Select 'PM'
	 */
	public void modifyOnlinePermitPrint(String onlinePermitPrintPeriod, String onlinePermitPrintPeriodStartTime, boolean isAM){
		InvMgrFacilityDetailPage invDetails = InvMgrFacilityDetailPage
		.getInstance();
		invDetails.setupOnlinePermitPrint(onlinePermitPrintPeriod, onlinePermitPrintPeriodStartTime, isAM);
		invDetails.clickApply();
		invDetails.waitLoading();
	}
	
	/**
	 * assign POS Supplier to warehouse or facility
	 * @param posSupplierIds
	 */
	public void assignPOSSupplier(String... posSupplierIds) {
		InvMgrPOSSupplierSearchPage supplierSearchPg = InvMgrPOSSupplierSearchPage.getInstance();
		
		StringBuffer sb = new StringBuffer();
		for(String id : posSupplierIds) {
			sb.append(id + " ");
		}
		logger.info("Assign POS supplier - " + sb.toString() + " to warehouse/facility.");
		for(String id : posSupplierIds) {
			supplierSearchPg.searchPosSupplierById(id);
			supplierSearchPg.selectSupplierCheckBox(id);
			supplierSearchPg.clickAssignSelectSupplier();
		    ajax.waitLoading();
		    supplierSearchPg.waitLoading();
		}
	}
	
	/**
	 * unassign POS supplier.
	 * @param posSupplierIdList
	 */
	public void unassignPOSSupplier(String... posSupplierIds ){
		InvMgrPOSSupplierSearchPage supplierSearchPg = InvMgrPOSSupplierSearchPage.getInstance();
		InvMgrConfirmDialogWidget confirmWgt = InvMgrConfirmDialogWidget.getInstance();
		
		StringBuffer sb = new StringBuffer();
		for(String id : posSupplierIds) {
			sb.append(id + " ");
		}
		logger.info("Unassign POS Supplier - " + sb.toString() + " from warehouse/facility.");
		for(String id : posSupplierIds) {
			supplierSearchPg.searchPosSupplierById(id);
			supplierSearchPg.selectSupplierCheckBox(id);
			supplierSearchPg.clickUnassignSelectedSupplier();
			ajax.waitLoading();
			Object page = browser.waitExists(confirmWgt, supplierSearchPg);
			if(confirmWgt ==  page) {
				confirmWgt.clickOK();
				ajax.waitLoading();
			}
		}
	 }
	
	/**
	 * switch ware house location to contract.
	 */
	public void swichWarehouseToContactPosSupllerSearchPg(){
		InvMgrTopMenuPage topMenuPg = InvMgrTopMenuPage.getInstance();
		InvMgrPOSSupplierSearchPage supplierSarchPg = InvMgrPOSSupplierSearchPage.getInstance();
		logger.info("Change warehouse location to contract");
		topMenuPg.clickHome();
		ajax.waitLoading();
		topMenuPg.gotoSpecifiedDetailPage("POS Supplier Setup");
		ajax.waitLoading();
		supplierSarchPg.waitLoading();
	}
	
	/**
	 * Go to POS Purchase Order page from top menu.
	 */
	public void gotoPOSPurchaseOrderPg(){
		InvMgrTopMenuPage topMenu = InvMgrTopMenuPage.getInstance();
		InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
		
		logger.info("Go to POS Purchase Order page.");
		topMenu.gotoSpecifiedDetailPage("POS Purchase Order");
		posPurchaseOrderPg.waitLoading();
	}
	
	/**
	 * Go to Receive POS Purchase Order Page from search POS purchase order page.
	 * @param index
	 */
	public void gotoReceivePOSPurchaseOrderPage(int index){
		InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
		InvMgrReceivePurchaseOrderPage reveivePage = InvMgrReceivePurchaseOrderPage.getInstance();
		
		logger.info("Go to Receive POS Purchase Order Page from search POS purchase order page.");
		posPurchaseOrderPg.clickReceiveQty(index);
		reveivePage.waitLoading();
	}
	
	/**
	 * Receive POS purchase order, click Cancel in receive purchase order page
	 * @param itemInfoList
	 */
	public void receiveQtyClickOK(ArrayList<POSPurchaseOrderItemInfo> itemInfoList){
		this.receiveQty(itemInfoList, "OK");
	}
	
	/**
	 * Receive POS purchase order, click Cancel in receive purchase order page
	 * @param itemInfoList
	 */
	public void receiveQtyClickCancel(ArrayList<POSPurchaseOrderItemInfo> itemInfoList){
		this.receiveQty(itemInfoList, "Cancel");
	}

	/**
	 * Receive POS purchase order.
	 * @param itemInfoList
	 */
	private void receiveQty(ArrayList<POSPurchaseOrderItemInfo> itemInfoList, String operation){
		InvMgrReceivePurchaseOrderPage reveivePage = InvMgrReceivePurchaseOrderPage.getInstance();
		InvMgrPOSPurchaseOrderSearchPage searchPosPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
		
		logger.info("Receive POS purchase order.");
		// set up receive info for all of the items
		reveivePage.setUpAllReceiveInfo(itemInfoList);
		if("OK".equalsIgnoreCase(operation)){		
			reveivePage.clickOK();
			ajax.waitLoading();
			ConfirmationDialogWidget confirm = new ConfirmationDialogWidget();
			Object page = browser.waitExists(confirm, reveivePage, searchPosPurchaseOrderPg);
			if(page == confirm){
				confirm.clickOK();
				ajax.waitLoading();
				page = browser.waitExists(reveivePage, searchPosPurchaseOrderPg);
				if(page == reveivePage){
					// received failed
					logger.error(reveivePage.getErrorMsg());
				} else if(page == searchPosPurchaseOrderPg) {
					// received successfully
					logger.info("Received successfully.");
				}
			}
		} else if("Cancel".equalsIgnoreCase(operation)){		
			reveivePage.clickCancel();
			ajax.waitLoading();
			searchPosPurchaseOrderPg.waitLoading();
		} else {
			throw new ErrorOnPageException("There isn't such operation!");
		}
	}
	
	/**
	 * Close the purchase order, click OK in cancel purchase order page
	 */
	public void closePurchaseOrderClickOK(){
		this.closePurchaseOrder("OK");
	}
	
	/**
	 * Close the purchase order, click Cancel in cancel purchase order page
	 */
	public void closePurchaseOrderClickCancel(){
		this.closePurchaseOrder("Cancel");
	}
	
	/**
	 * Close the purchase order, click Apply in cancel purchase order page
	 */
	public void closePurchaseOrderClickApply(){
		this.closePurchaseOrder("Apply");
	}
	
	/**
	 * Close the purchase order.
	 */
	private void closePurchaseOrder(String operation){
		InvMgrClosePOSPurchaseOrderPage closeOrderPage = InvMgrClosePOSPurchaseOrderPage.getInstance();
		InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
		ConfirmationDialogWidget confirm = new ConfirmationDialogWidget();
		
		logger.info("Close the purchase order.");
		posPurchaseOrderPg.selectRadioButton(0);
		posPurchaseOrderPg.clickClosePurchaseOrder();
		ajax.waitLoading();
		closeOrderPage.waitLoading();
		closeOrderPage.setInternalNotes("close purchase order.");

		if("OK".equalsIgnoreCase(operation) ){
			closeOrderPage.clickOK();
			ajax.waitLoading();
			Object page = browser.waitExists(confirm, closeOrderPage, posPurchaseOrderPg);
			if(page == confirm){
				confirm.clickOK();
				ajax.waitLoading();
				posPurchaseOrderPg.waitLoading();
				logger.info("Closed successfully.");
			}
		} else if("Apply".equalsIgnoreCase(operation)){
			closeOrderPage.clickApply();
			ajax.waitLoading();
		} else if("Cancel".equalsIgnoreCase(operation)){
			closeOrderPage.clickCancel();
			ajax.waitLoading();
			posPurchaseOrderPg.waitLoading();
		}
	}
	
	/**
	 * Go to POS purchase order detail page from search page by PO#.
	 * @param poNum
	 */
	public void gotoPOSPurchaseOrderDetailPage(String poNum){
		InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
		InvMgrPurchaseOrderDetailsPage detailPage = InvMgrPurchaseOrderDetailsPage.getInstance();

		logger.info("Go to POS purchase order detail page from search page by PO#.");
		posPurchaseOrderPg.clickPONum(poNum);
		ajax.waitLoading();
		detailPage.waitLoading();
	}
	
	/**
	 * Create new POS purchase order.
	 * @param purchaseOrderInfo
	 * @return
	 */
	public String createPOSPurchaseOrder(POSPurchaseOrderInfo purchaseOrderInfo){
		InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
		InvMgrNewPurchaseOrderPage newOrderPage = InvMgrNewPurchaseOrderPage.getInstance();
		InvMgrPurchaseOrderDetailsPage orderDetailPg = InvMgrPurchaseOrderDetailsPage.getInstance();
		ConfirmationDialogWidget confirm = new ConfirmationDialogWidget();
		String returnValue = "";
		
		logger.info("Create new POS purchase order.");
		posPurchaseOrderPg.clickCreatePurchaseOrder();
    	ajax.waitLoading();
    	newOrderPage.waitLoading();
    	newOrderPage.setupPOSPurchaseOrderInfo(purchaseOrderInfo);
    	newOrderPage.clickApply();
    	ajax.waitLoading();
		Object page = browser.waitExists(confirm, newOrderPage);
		if(page == confirm){
			confirm.clickOK();
	    	ajax.waitLoading();
			orderDetailPg.waitLoading();
			returnValue = orderDetailPg.getPONum();
			orderDetailPg.clickOK();
	    	ajax.waitLoading();
	    	page = browser.waitExists(confirm, orderDetailPg);
			if(page == confirm){
				confirm.clickOK();
		    	ajax.waitLoading();
		    	posPurchaseOrderPg.waitLoading();
			}
		}
    	return returnValue;
	}
	
	/**
	 * Cancel the purchase order, click OK in cancel purchase order page.
	 */
	public void cancelPOSPurchaseOrderClickOK(){
		this.cancelPOSPurchaseOrder("OK");
	}
	
	/**
	 * Cancel the purchase order, click Apply in cancel purchase order page.
	 */
	public void cancelPOSPurchaseOrderClickApply(){
		this.cancelPOSPurchaseOrder("Apply");
	}
	
	/**
	 * Cancel the purchase order, click Cancel in cancel purchase order page.
	 */
	public void cancelPOSPurchaseOrderClickCancel(){
		this.cancelPOSPurchaseOrder("Cancel");
	}
	
	/**
	 * Cancel the purchase order.
	 * @param operation
	 */
	private void cancelPOSPurchaseOrder(String operation){
		InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
		InvMgrCancelPOSPurchaseOrderPage cancelOrderPage = InvMgrCancelPOSPurchaseOrderPage.getInstance();
		ConfirmationDialogWidget confirm = new ConfirmationDialogWidget();
		
		logger.info("Cancel POS purchase order.");
		posPurchaseOrderPg.selectRadioButton(0);
		posPurchaseOrderPg.clickCancelPurchaseOrder();
		ajax.waitLoading();
		cancelOrderPage.waitLoading();
		cancelOrderPage.setInternalNotes("cancel purchase order.");
		if("OK".equalsIgnoreCase(operation) ){
			cancelOrderPage.clickOK();
			ajax.waitLoading();
			Object page = browser.waitExists(confirm, cancelOrderPage, posPurchaseOrderPg);
			if(page == confirm){
				confirm.clickOK();
				ajax.waitLoading();
				posPurchaseOrderPg.waitLoading();
				logger.info("Cancel successfully.");
			}
		} else if("Apply".equalsIgnoreCase(operation)){
			cancelOrderPage.clickApply();
			ajax.waitLoading();
		} else if("Cancel".equalsIgnoreCase(operation)){
			cancelOrderPage.clickCancel();
			ajax.waitLoading();
			posPurchaseOrderPg.waitLoading();
		}
    }
    
	/**
	 * 
	 * @param orderInfo
	 * @param AddOrRemove
	 * @param index  using for remove order item.when want to add order item, no need to set this value.
	 * @param operation OK or Apply or Cancel
	 */
	public void editPOSPurchaseOrder(POSPurchaseOrderInfo orderInfo, String AddOrRemove, int index, String operation){
		InvMgrPurchaseOrderDetailsPage detailPage = InvMgrPurchaseOrderDetailsPage.getInstance();
		InvMgrPOSPurchaseOrderSearchPage searchPOSPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
		ConfirmationDialogWidget confirm = new ConfirmationDialogWidget();
		
		detailPage.editPOSPurchaseOrderInfo(orderInfo, AddOrRemove, index);
		if("OK".equalsIgnoreCase(operation) ){
			detailPage.clickOK();
			ajax.waitLoading();
			Object page = browser.waitExists(confirm, detailPage, searchPOSPurchaseOrderPg);
			if(page == confirm){
				confirm.clickOK();
				ajax.waitLoading();
				searchPOSPurchaseOrderPg.waitLoading();
			}
		} else if("Apply".equalsIgnoreCase(operation)){
			detailPage.clickApply();
			ajax.waitLoading();
			Object page = browser.waitExists(confirm, detailPage, searchPOSPurchaseOrderPg);
			if(page == confirm){
				confirm.clickOK();
				ajax.waitLoading();
				detailPage.waitLoading();
			}
		} else if("Cancel".equalsIgnoreCase(operation)){
			detailPage.clickCancel();
			ajax.waitLoading();
			searchPOSPurchaseOrderPg.waitLoading();
		}
		logger.info(operation + " successfully.");
	}
	
    /**
     * Clean up pos purchase order.
     */
    public void cancelPOSPurchaseOrder(POSPurchaseOrderInfo purchaseOrderInfo){
    	InvMgrPOSPurchaseOrderSearchPage searchPOSPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
    	
    	logger.info("Clean up. Cancel the POS purchase order which PO# is:"+purchaseOrderInfo.searchPONum);
    	searchPOSPurchaseOrderPg.searchPOPurchaseOrder(purchaseOrderInfo);
		this.cancelPOSPurchaseOrderClickOK();
    }
    
    /**
     * this work flow starts with POS Supplier details page, click cancel button, and ends with POS Supplier search page
     */
	public void gotoPOSSupplierSearchPageFromDetailsPage() {
		InvMgrPOSSupplierDetailsPage supplierDetailsPage = InvMgrPOSSupplierDetailsPage.getInstance();
		InvMgrPOSSupplierSearchPage supplierSearchPage = InvMgrPOSSupplierSearchPage.getInstance();
		
//		supplierDetailsPage.clickCancel();
		supplierDetailsPage.clickOkbutton();
		ajax.waitLoading();
		supplierSearchPage.waitLoading();
	}
	
	/**
	 * This method used to delete facility in db by name, the facility may not exist in DB
	 */
	public void deleteFacilityFromDBByName(String facilityName, String schema) {
		logger.info("Delete facility(facilityName#=" + facilityName + ") from DB.");
		db.resetSchema(schema);
		String sql = "update d_loc set delete_ind=1 where name='" + facilityName +"'";
		int result = db.executeUpdate(sql);
		if (result < 1) {
			logger.info("No such facility exist in db with the name");
		} else {
			logger.info("Delete facility sucessfully.");
		}
	}
	
	/**
	 * This method used to update a facility information at facility detail page,
	 * it starts from facility home page and end at facility detail page.
	 * @param fd
	 */
	public void editNewFacilityAndClickApply(FacilityData fd) {
		InvMgrAddFacilityDetailsPage editFacilityDetailPg = InvMgrAddFacilityDetailsPage
				.getInstance();
		logger.info("Go to a facility's detail page and update its information.");
		this.gotoFacilityDetailPageById(fd.facilityID);
		editFacilityDetailPg.setupFacilityDetailsData(fd);
		editFacilityDetailPg.clickApply();
		editFacilityDetailPg.waitLoading();
	}
	
	/**
	 * Go to Master POS Details Page from Master POS List page
	 * @param id
	 * @author Lesley Wang
	 * @date Aug 20, 2012
	 */
	public void gotoMasterPOSDetailsPgFromListPg(String id) {
		InvMgrPosSetupDetailsPage posSetupDetailsPg = InvMgrPosSetupDetailsPage.getInstance();
		InvMgrMasterPosSearchPage masterPosSetupPg = InvMgrMasterPosSearchPage.getInstance();
		
		logger.info("Go to Master POS Details Page from Master POS List page...");
		masterPosSetupPg.searchPOSById(id);
		masterPosSetupPg.clickProductID(id);
		posSetupDetailsPg.waitLoading();
	}
	
	/**
	 * Go to Master POS List Page from Master POS Details page. 
	 * @param isSaved - if true, click OK button; otherwise, click Cancel button
	 * @author Lesley Wang
	 * @date Aug 21, 2012
	 */
	public void gotoMasterListPgFromPOSDetailsPg(boolean isSaved) {
		InvMgrPosSetupDetailsPage posSetupDetailsPg = InvMgrPosSetupDetailsPage.getInstance();
		InvMgrMasterPosSearchPage masterPosSetupPg = InvMgrMasterPosSearchPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Go to Master POS List Page from Master POS Details page...");
		if (isSaved) {
			posSetupDetailsPg.clickOkButton();
		} else {
			posSetupDetailsPg.clickCancelButton();
		}
		ajax.waitLoading();
		Object page = browser.waitExists(confirmWidget, masterPosSetupPg);
		if (page == confirmWidget) {
			confirmWidget.clickOK();
			ajax.waitLoading();
			masterPosSetupPg.waitLoading();
		}
		
	}
	
	/*
	 * go to warehouse details page by warehouse id.
	 */
	public void gotoWarehouseDetailPgbyId(String warehouseId){
		InvMgrWarehouseSearchPage whouseSearchPg = InvMgrWarehouseSearchPage.getInstance();
		InvMgrWarehouseDetailsPage whouseDetailsPg = InvMgrWarehouseDetailsPage
				.getInstance();
		logger.info("go to warehouse details pge");
		whouseSearchPg.searchWarehouseById(warehouseId);
		whouseSearchPg.clickWarehouseID(warehouseId);
		ajax.waitLoading();
		whouseDetailsPg.waitLoading();
	}
	/**
	 * get warehouse id by name from DB;
	 * @param schema
	 * @param whouseName
	 * @return
	 */
	public String getWarehouseIdByNameFromDB(String schema,String whouseName){
		logger.info("get warehouse id by name from DB.");
		db.resetSchema(schema);
		String sql = "select id from D_LOC_HIERARCHY where name_40='" + whouseName +"'";
		String result = db.executeQuery(sql, "ID", 0);
		return result;
	}
	
	/**
	 * Change POS Product Available Location on POS Setup Details page.
	 * @param availableLoc
	 * @author Lesley Wang
	 * @date Aug 23, 2012
	 */
	public void changePOSProductAvailableLocation(String availableLoc) {
		InvMgrPosSetupDetailsPage posDetailsPg = InvMgrPosSetupDetailsPage.getInstance();
		OrmsPOSOverwriteRevenueLocationWidget widget = OrmsPOSOverwriteRevenueLocationWidget.getInstance();
		logger.info("Change POS Product Available Location to " + availableLoc + " on POS Setup Details page...");
		
		posDetailsPg.selectAvaLocation(availableLoc);
		ajax.waitLoading();
		Object page = browser.waitExists(widget, posDetailsPg);
		if (page == widget) {
			widget.clickOK();
			ajax.waitLoading();
			posDetailsPg.waitLoading();
		}
	}
	/**
	 * go to warehouse stock transfer receiving locations.
	 */
	public void gotoWhousStcTransRecLocationsPg(){
		InvMgrWarehouseDetailsPage whousDetailsPg = InvMgrWarehouseDetailsPage.getInstance();
		InvMgrStcTransRecLocationsPage stcTransRecLocPg = InvMgrStcTransRecLocationsPage.getInstance();
		
		logger.info("Go to stock transfer receiving locations page");
		whousDetailsPg.clickStcTransRecLocaions();
		ajax.waitLoading();
		stcTransRecLocPg.waitLoading();
	}
	/**
	 * get location type id by location type.
	 * @param schema
	 * @param locaionType
	 * @return
	 */
	public String getLocationTypeIdByLocationTypeTypeFromDB(String schema,String locaionType){
		logger.info("get locaiton type id from DB by location type.");
		db.resetSchema(schema);
		String sql = "select id from D_LOC_CAT where name = '"+locaionType+"'";
		String result = db.executeQuery(sql, "id", 0);
		return result;
	}
	/**
	 * get location 
	 * @param schema
	 * @param locationName
	 * @return
	 */
	public String getLocaionTypeIdByLocationNameFromDB(String schema,String locationName){
		logger.info("get locaiton type id by location name from DB.");
		db.resetSchema(schema);
		String sql = "select TYPE_ID from D_LOC where NAME = '"+locationName+"'";
		String result = db.executeQuery(sql, "type_id", 0);
		return result;
	}
	
	/**
	 * Unassign POS from Warehouse
	 * @param posNames
	 * @param isOK. If true, click Save on the dialog; otherwise, click Cancel
	 * @author Lesley Wang
	 * @date Aug 24, 2012
	 */
	public void unassignPOSFromWarehouse(boolean isOK, String... posNames) {
		InvMgrPosSetupListPage posSetupListPg = InvMgrPosSetupListPage.getInstance();
		OrmsConfirmDialogWidget widget = OrmsConfirmDialogWidget.getInstance();
		logger.info((isOK ? "Save" : "Cancel") + " to unassign POS from warehouse: " + posNames.toString());
		
		for(String posName : posNames){
			posSetupListPg.selectCheckBox(posName);
		}
		
		posSetupListPg.clickUnassignSelectedPOSProducts();
		ajax.waitLoading();
		widget.waitLoading();
		if (isOK) {
			widget.clickOK();
		} else {
			widget.clickCancel();
		}
		ajax.waitLoading();
		posSetupListPg.waitLoading();
	}
	
	/** Save to unassign POS from warehouse */
	public void unassignPOSFromWarehouse(String... posNames) {
		unassignPOSFromWarehouse(true, posNames);
	}
	/** Search pos and unassign POS from warehouse*/
	public void unassignPOSFromWarehouse(POSInfo pos) {
		InvMgrPosSetupListPage posSetupPg = InvMgrPosSetupListPage.getInstance();
		
		logger.info("Search and unassign POS details page:" + pos.product + " " + pos.productID);
		posSetupPg.searchPOSProduct(pos);
		this.unassignPOSFromWarehouse(pos.product);
	}
	/**
	 * Go to warehouse POS setup details page from setup list page
	 * @param id
	 * @author Lesley Wang
	 * @date Aug 24, 2012
	 */
	public void gotoWarehousePOSSetupDetailsPgFromSetupListPg(String id) {
		InvMgrPosSetupListPage posSetupPg = InvMgrPosSetupListPage.getInstance();
		InvMgrPosSetBarcodePage posBarcodePg = InvMgrPosSetBarcodePage.getInstance();
		
		logger.info("Go to warehouse POS setup details page from setup list page:" + id);
		posSetupPg.clickProductID(id);
		posBarcodePg.waitLoading();
	}
	/** Search and go to Warehosue POS details page from setup list page*/
	public void gotoWarehousePOSSetupDetailsPgFromSetupListPg(POSInfo pos) {
		InvMgrPosSetupListPage posSetupPg = InvMgrPosSetupListPage.getInstance();
		
		logger.info("Search and go to warehouse POS details page:" + pos.product + " " + pos.productID);
		posSetupPg.searchPOSProduct(pos);
		this.gotoWarehousePOSSetupDetailsPgFromSetupListPg(pos.productID);
	}
	
	/**
	 * Go to warehouse POS setup list page from POS setup details page
	 * @param isOK
	 * @author Lesley Wang
	 * @date Aug 27, 2012
	 */
	public void gotoWarehousePOSSetupListPgFromSetupDetailsPg(boolean isOK) {
		InvMgrPosSetupListPage posSetupPg = InvMgrPosSetupListPage.getInstance();
		InvMgrPosSetBarcodePage posBarcodePg = InvMgrPosSetBarcodePage.getInstance();
		
		logger.info("Go to warehouse POS setup list page from setup details page by clicking " +
				(isOK ? "OK" : "Cancel") + " button...");
		if (isOK) {
			posBarcodePg.clickOkButton();
		} else {
			posBarcodePg.clickCancelButton();
		}
		posSetupPg.waitLoading();
	}
	
	public void gotoWarehousePOSSetupListPgFromSetupDetailsPg() {
		this.gotoWarehousePOSSetupListPgFromSetupDetailsPg(true);
	}
	
	/**
	 * -----------------------------------------Marina Manager--------------------------------------------
	 */
	
	public void gotoDockSlipSetupPage() {
		InvMgrTopMenuPage topMenuPage = InvMgrTopMenuPage.getInstance();
		InvMgrDockListPage dockListPage = InvMgrDockListPage.getInstance();
		
		logger.info("Goto Docks/Slip Set-up page from top drop down list.");
		topMenuPage.gotoSpecifiedDetailPage("Dock/Slip Set-up");
		dockListPage.waitLoading();
	}
	
	public void gotoDocksPage() {
		InvMgrDockSlipCommonPage dockSlipCommonPage = InvMgrDockSlipCommonPage.getInstance();
		InvMgrDockListPage dockListPage = InvMgrDockListPage.getInstance();
		
		logger.info("Goto Docks list page.");
		dockSlipCommonPage.clickDocksTab();
		ajax.waitLoading();
		dockListPage.waitLoading();
	}
	
	public void gotoListSearchPageFromTopMenu(){
		InvMgrTopMenuPage topMenuPage = InvMgrTopMenuPage.getInstance();
		InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
		
		logger.info("Go to list search page from top menu.");
		topMenuPage.gotoSpecifiedDetailPage("List Set-up");
		listSearchPg.waitLoading();
	}
	
	public void gotoAddListDetailPageFromListSearchPage(){
		InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
		InvMgrListDetailPage listDetailPg = InvMgrListDetailPage.getInstance();
		
		logger.info("Go to add list detail page from list search page.");
		listSearchPg.clickAddNew();
		ajax.waitLoading();
		listDetailPg.waitLoading();
	}
	
	public String addList(ListInfo list){
		InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
		InvMgrListDetailPage listDetailPg = InvMgrListDetailPage.getInstance();
		
		logger.info("Add list info.");
		this.gotoAddListDetailPageFromListSearchPage();
		listDetailPg.setListInfo(list);
		listDetailPg.clickApply();
		ajax.waitLoading();
		listDetailPg.waitLoading();
		String value = "";
		if(listDetailPg.checkErrorMessageIsExisting()){
			value = listDetailPg.getErrorMessage();
		}else{
			value = listDetailPg.getListID();
		}
		
		listDetailPg.clickOK();
		ajax.waitLoading();
		browser.waitExists(listSearchPg,listDetailPg);
		
		return value;
	}
	
	public String editList(ListInfo list){
		InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
		InvMgrListDetailPage listDetailPg = InvMgrListDetailPage.getInstance();
		
		logger.info("Edit list which ID is:"+list.getListID());
		this.gotoListDetailPageSearchByListID(list.getListID());
		
		listDetailPg.setListInfo(list);
		listDetailPg.clickApply();
		ajax.waitLoading();
		listDetailPg.waitLoading();
		String value = "";
		if(listDetailPg.checkErrorMessageIsExisting()){
			value = listDetailPg.getErrorMessage();
		}
		
		listDetailPg.clickOK();
		ajax.waitLoading();
		browser.waitExists(listSearchPg,listDetailPg);
		return value;
	}

	public void cancelFromListDetailsPage() {
		InvMgrListDetailPage detailsPage = InvMgrListDetailPage.getInstance();
		InvMgrListSearchPage searchPage = InvMgrListSearchPage.getInstance();
		
		detailsPage.clickCancel();
		ajax.waitLoading();
		searchPage.waitLoading();
	}
	
	public void closeList(String listID, String closeReason, String closeNotes){
		InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
		InvMgrCloseListWidget closeWidget = InvMgrCloseListWidget.getInstance();
		
		logger.info("Clost list which ID is:"+listID);
		listSearchPg.searchListByListID(listID);
		listSearchPg.selectListByID(listID);
		listSearchPg.clickClose();
		ajax.waitLoading();
		closeWidget.waitLoading();
		
		closeWidget.selectCloseReason(closeReason);
		closeWidget.setCloseNote(closeNotes);
		closeWidget.clickOK();
		ajax.waitLoading();
		listSearchPg.waitLoading();
	}
	
	public void assignSlipToList(ListInfo list) {
		InvMgrListSearchPage listPage = InvMgrListSearchPage.getInstance();
		InvMgrListDetailPage detailsPage = InvMgrListDetailPage.getInstance();
		InvMgrListParticpantPage listParticipantPg = InvMgrListParticpantPage.getIntance();
		
		logger.info("Assign slip to list: " + list.getListName());
		List<String> needToAssignSlipCode = new ArrayList<String>();
		List<String> hasAssignedSlipCode =  new ArrayList<String>();
		if(!detailsPage.exists()) {
			listPage.searchListByListNameAndStatus(list.getListName(), list.getListStatus());
			list.setListID(listPage.getListIDByName(list.getListName()));
			this.gotoListDetailPageSearchByListID(list.getListID());
		}
		this.gotoListParticipantPageFromListDetailPage();
		for(int i=0;i<list.getAssignedSlipCode().size();i++) {
			listParticipantPg.searchSlipByCodeAndOther(list.getAssignedSlipCode().get(i), true, null, null, null);
			if(listParticipantPg.checkErrorMessageIsExisting()){
				needToAssignSlipCode.add(list.getAssignedSlipCode().get(i));
			}else{
				if(listParticipantPg.checkSlipIsExistingByCode(list.getAssignedSlipCode().get(i))){
					hasAssignedSlipCode.add(list.getAssignedSlipCode().get(i));
				}else{
					needToAssignSlipCode.add(list.getAssignedSlipCode().get(i));
				}
			}
		}
		
		List<String> notAssignedSlipCode = new ArrayList<String>();
		for(int i=0; i<needToAssignSlipCode.size();i++){
			listParticipantPg.searchSlipByCodeAndOther(list.getAssignedSlipCode().get(i), false, null, null, null);
			if(!listParticipantPg.checkErrorMessageIsExisting()){
				if(listParticipantPg.checkSlipIsExistingByCode(needToAssignSlipCode.get(i))){
					listParticipantPg.assignSlipBySlipCode(needToAssignSlipCode.get(i));
					hasAssignedSlipCode.add(needToAssignSlipCode.get(i));
				}else{
					notAssignedSlipCode.add(needToAssignSlipCode.get(i));
				}
			}else{
				notAssignedSlipCode.add(needToAssignSlipCode.get(i));
			}
		}
		
		if(hasAssignedSlipCode.size() != list.getAssignedSlipCode().size()){
			throw new ErrorOnPageException("Not All slip assign to this list, list id = " + list.getListID() + ", list name = " + list.getListName()
					+ "\n Expected assigned slip code:" + list.getAssignedSlipCode() 
					+ "\n Actul Assigned slip code:" + hasAssignedSlipCode
					+ "\n Failed Assigned slip code:" + notAssignedSlipCode);
		}else{
			logger.info("Success assign slip code to this list, list id = " + list.getListID() + ", list name = " + list.getListName()
					+" Assigned slip code:" + list.getAssignedSlipCode());
		}
	}
	
	public void gotoListDetailPageSearchByListID(String listID){
		InvMgrListSearchPage listSearchPg = InvMgrListSearchPage.getInstance();
		InvMgrListDetailPage listDetailPg = InvMgrListDetailPage.getInstance();
		
		logger.info("Go to list detail page search by list ID = " + listID);
		listSearchPg.searchListByListID(listID);
		listSearchPg.clickListID(listID);
		ajax.waitLoading();
		listDetailPg.waitLoading();
	}
	
	public void gotoListParticipantPageFromListDetailPage(){
		InvMgrListDetailPage listDetailPg = InvMgrListDetailPage.getInstance();
		InvMgrListParticpantPage listParticipantPg = InvMgrListParticpantPage.getIntance();
		
		logger.info("Go to list participant page from list detail page.");
		listDetailPg.clickListParticipationLabel();
		ajax.waitLoading();
		listParticipantPg.waitLoading();
	}
	
	public void gotoSlipListPage() {
		InvMgrDockSlipCommonPage dockSlipCommonPage = InvMgrDockSlipCommonPage.getInstance();
		InvMgrSlipListPage slipListPage = InvMgrSlipListPage.getInstance();
		
		logger.info("Goto Slip list page.");
		dockSlipCommonPage.clickSlipsTab();
		ajax.waitLoading();
		slipListPage.waitLoading();
	}
	
	public void gotoDockDetailPage(String dockName){
		InvMgrDockListPage dockListPage = InvMgrDockListPage.getInstance();
		InvMgrDockDetailsPage dockDetailPage = InvMgrDockDetailsPage.getInstance();
		
		PagingComponent turnPageComponent = new PagingComponent();
		boolean found=dockListPage.isDockExist(dockName);
		while(!found && turnPageComponent.nextExists()) {
			turnPageComponent.clickNext();
			ajax.waitLoading();
			dockListPage.waitLoading();
			found=dockListPage.isDockExist(dockName);
		}
		if(!found)
			throw new ActionFailedException("Could not find dock:"+dockName);
		dockListPage.clickDockNameLink(dockName);
		ajax.waitLoading();
		dockDetailPage.waitLoading();
	}
	
	
	public void gotoSlipNSSGroupListPage() {
		InvMgrDockSlipCommonPage dockSlipCommonPage = InvMgrDockSlipCommonPage.getInstance();
		InvMgrSlipNSSGroupListPage nssGroupsPage = InvMgrSlipNSSGroupListPage.getInstance();
		
		logger.info("Goto NSS Groups list page.");
		dockSlipCommonPage.clickNSSGroups();
		ajax.waitLoading();
		nssGroupsPage.waitLoading();
	}
	
	public void gotoSlipNSSGroupDetailsPage(String id) {
		InvMgrSlipNSSGroupListPage nssGroupListPage = InvMgrSlipNSSGroupListPage.getInstance();
		InvMgrSlipNSSGroupDetailsPage nssGroupDetailsPage = InvMgrSlipNSSGroupDetailsPage.getInstance();
		
		logger.info("Goto Slip NSS Group(ID=" + id + ") details page from list page.");
		nssGroupListPage.searchSlip("Slip ID", id);
		nssGroupListPage.clickIdLink(id);
		ajax.waitLoading();
		nssGroupDetailsPage.waitLoading();
	}
	
	public void deleteSlipNSSGroup(String id) {
		logger.info("Delete Slip NSS Group - " + id);
		gotoSlipNSSGroupDetailsPage(id);
		deleteSlipNSSGroup();
	}
	
	public void deleteSlipNSSGroup() {
		InvMgrSlipNSSGroupDetailsPage nssGroupDetailsPage = InvMgrSlipNSSGroupDetailsPage.getInstance();
		InvMgrSlipNSSGroupListPage nssGroupListPage = InvMgrSlipNSSGroupListPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		nssGroupDetailsPage.clickDeleteThisSlip();
		ajax.waitLoading();
		Object pages = browser.waitExists(confirmWidget,nssGroupListPage);
		if(confirmWidget == pages){
			confirmWidget.clickOK();
			ajax.waitLoading();
			nssGroupListPage.waitLoading();
		}
	}
	
	/**
	 * add Slip NSS Groups
	 * @param slip
	 * @return
	 */
	public String addSlipNSSGroup(SlipInfo slip) {
		InvMgrSlipNSSGroupListPage nssGroupListPage = InvMgrSlipNSSGroupListPage.getInstance();
		InvMgrSlipNSSGroupDetailsPage nssGroupDetailPage = InvMgrSlipNSSGroupDetailsPage.getInstance();
		
		logger.info("Add Slip NSS Group.");
		nssGroupListPage.clickAddNewGroup();
		ajax.waitLoading();
		nssGroupDetailPage.waitLoading();
		nssGroupDetailPage.setupSlipInfo(slip);
		nssGroupDetailPage.clickApply();
		ajax.waitLoading();
		nssGroupDetailPage.waitLoading();
		String toReturn;
		if(nssGroupDetailPage.isErrorMessageExists()) {
			toReturn = nssGroupDetailPage.getErrorMessage();
			logger.error("Error Message occurs: " + toReturn);
			nssGroupDetailPage.clickCancel();
		} else {
			toReturn = nssGroupDetailPage.getSlipID();
			logger.info("----New add Slip NSS Group id is: " + toReturn);
			nssGroupDetailPage.clickOK();
		}
		
		nssGroupListPage.waitLoading();
		
		return toReturn;
	}
	
	/**
	 * edit slip NSS Group info
	 * @param slip
	 * @return
	 */
	public String editSlipNSSGroup(SlipInfo slip) {
		InvMgrSlipNSSGroupListPage nssGroupListPage = InvMgrSlipNSSGroupListPage.getInstance();
		InvMgrSlipNSSGroupDetailsPage nssGroupDetailPage = InvMgrSlipNSSGroupDetailsPage.getInstance();
		
		logger.info("Edit Slip NSS Group(ID=" + slip.getId() + ").");
		gotoSlipNSSGroupDetailsPage(slip.getId());
		nssGroupDetailPage.setupSlipInfo(slip);
		nssGroupDetailPage.clickApply();
		ajax.waitLoading();
		nssGroupDetailPage.waitLoading();
		String slipId = nssGroupDetailPage.getSlipID();
		logger.info("----Edit Slip NSS Group id is: " + slipId);
		nssGroupDetailPage.clickOK();
		ajax.waitLoading();
		nssGroupListPage.waitLoading();
		
		return slipId;
	}
	
	/**
	 * Add specific Slip
	 * @param slip
	 * @return
	 */
	public String addSlip(SlipInfo slip) {
		InvMgrSlipListPage slipListPage = InvMgrSlipListPage.getInstance();
		InvMgrSlipDetailsPage slipDetailsPage = InvMgrSlipDetailsPage.getInstance();
		
		logger.info("Add Slip.");
		slipListPage.clickAddNewSlip();
		ajax.waitLoading();
		slipDetailsPage.waitLoading();
		slipDetailsPage.setupSlipInfo(slip);
		slipDetailsPage.clickApply();
		ajax.waitLoading();
		slipDetailsPage.waitLoading();
		String toReturn;
		if(slipDetailsPage.isErrorMessageExists()) {
			toReturn = slipDetailsPage.getErrorMessage();
			logger.error("Error message occurs: " + toReturn);
			slipDetailsPage.clickCancel();
		} else {
			toReturn = slipDetailsPage.getSlipID();
			logger.info("New add Slip id is: " + toReturn);
			slipDetailsPage.clickOK();
		}
		
		
		slipListPage.waitLoading();
		return toReturn;
	}
	
	/**
	 * edit slip info
	 * @param slip
	 * @return
	 */
	public String editSlip(SlipInfo slip) {
		InvMgrSlipListPage listPage = InvMgrSlipListPage.getInstance();
		InvMgrSlipDetailsPage detailsPage = InvMgrSlipDetailsPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Edit Slip(ID=" + slip.getId() + ")");
		listPage.searchSlip("Slip ID", slip.getId());
		gotoSlipDetailsPage(slip.getId());
		detailsPage.setupSlipInfo(slip);
		detailsPage.clickApply();
		ajax.waitLoading();
		Object pages = browser.waitExists(confirmWidget,detailsPage);
		if(confirmWidget == pages){
			confirmWidget.clickOK();
			ajax.waitLoading();
			detailsPage.waitLoading();
		}
		String slipId = detailsPage.getSlipID();
		logger.info("----Edit Slip id is: " + slipId);
		detailsPage.clickOK();
		ajax.waitLoading();
		listPage.waitLoading();
		
		return slipId;
	}
	
	public void gotoSlipDetailsPage(String id) {
		InvMgrSlipListPage listPage = InvMgrSlipListPage.getInstance();
		InvMgrSlipDetailsPage detailsPage = InvMgrSlipDetailsPage.getInstance();
		
		logger.info("Goto Slip(ID=" + id + ") details page.");
		listPage.searchSlip("Slip ID", id);
		listPage.clickIdLink(id);
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	public void gotoNssGroupSlipDetailsPage(String id){
		InvMgrSlipNSSGroupListPage nssGroupListPg = InvMgrSlipNSSGroupListPage.getInstance();
		InvMgrSlipNSSGroupDetailsPage slipNssGroupDetailPg = InvMgrSlipNSSGroupDetailsPage.getInstance();
		
		logger.info("Goto Slip(ID=" + id + ") details page.");
		nssGroupListPg.searchSlip("Slip ID", id);
		nssGroupListPg.clickIdLink(id);
		ajax.waitLoading();
		slipNssGroupDetailPg.waitLoading();
	}
	
	public void deleteSlip() {
		InvMgrSlipDetailsPage detailsPage = InvMgrSlipDetailsPage.getInstance();
		InvMgrSlipListPage listPage = InvMgrSlipListPage.getInstance();
		
		detailsPage.clickDeleteThisSlip();
		ajax.waitLoading();
		listPage.waitLoading();
	}
	
	public void deleteNssGroupSlip(){
		InvMgrSlipNSSGroupListPage nssGroupListPg = InvMgrSlipNSSGroupListPage.getInstance();
		InvMgrSlipNSSGroupDetailsPage slipNssGroupDetailPg = InvMgrSlipNSSGroupDetailsPage.getInstance();
		
		slipNssGroupDetailPg.clickDeleteThisSlip();
		ajax.waitLoading();
		nssGroupListPg.waitLoading();
		
	}
	
	public void deleteSlip(String id) {
		logger.info("Delete Slip - " + id);
		gotoSlipDetailsPage(id);
		deleteSlip();
	}
	
	public void delectNssGropSlip(String id){
		logger.info("Delete Nss Group Slip - " + id);
		this.gotoNssGroupSlipDetailsPage(id);
		this.deleteNssGroupSlip();
	}
	
	public void activateSlip(String id) {
		InvMgrSlipListPage listPage = InvMgrSlipListPage.getInstance();
		
		logger.info("Activate Slip - " + id);
		listPage.activateSlip(id);
	}
	
	public void deactivateSlip(String id) {
		InvMgrSlipListPage listPage = InvMgrSlipListPage.getInstance();
		
		logger.info("Deactivate Slip - " + id);
		listPage.deactivateSlip(id);
	}
	
	public String deactivateSlip(String id, boolean isOK1, boolean isOK2) {
		InvMgrSlipListPage listPage = InvMgrSlipListPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Deactive slip by id:"+id);
		listPage.searchSlip("Slip ID", id);
		listPage.selectSlipById(id);
		listPage.clickDeactivate();
		ajax.waitLoading();
		browser.waitExists(confirmWidget, listPage);
		String toReturn = "";
		if(confirmWidget.exists()){
			toReturn = confirmWidget.getMessage();
			if(isOK1){
				confirmWidget.clickOK();
				ajax.waitLoading();
				browser.waitExists(confirmWidget, listPage);
				if(confirmWidget.exists()){
					toReturn = confirmWidget.getMessage();
					if(isOK2){
						confirmWidget.clickOK();
					}else{
						confirmWidget.clickCancel();
					}
				}
			} else {
				confirmWidget.clickCancel();
			}
			ajax.waitLoading();
			listPage.waitLoading();
		}
		
		if(listPage.isErrorMessageExists()){
			toReturn = listPage.getErrorMessage();
		}
		return toReturn;
	}
	
	
	public void activateSlipNSSGroup(String id) {
		InvMgrSlipNSSGroupListPage listPage = InvMgrSlipNSSGroupListPage.getInstance();
		
		logger.info("Activate Slip NSS Group - " + id);
		listPage.activateSlip(id);
	}
	
	public void deactivateSlipNSSGroup(String id) {
		InvMgrSlipNSSGroupListPage listPage = InvMgrSlipNSSGroupListPage.getInstance();
		
		logger.info("Deactivate Slip - " + id);
		listPage.deactivateSlip(id);
	}
	
	/**
	 * duplicate Slip, this workflow starts with Slip List page and also ends with Slip List page
	 * @param oldId
	 * @param toSlip
	 * @return
	 */
	public String duplicateSlip(String oldId, SlipInfo toSlip) {
		InvMgrSlipDetailsPage detailsPage = InvMgrSlipDetailsPage.getInstance();
		InvMgrSlipListPage listPage = InvMgrSlipListPage.getInstance();
		
		logger.info("Duplicate Slip(ID=" + oldId + ") to a new slip.");
		listPage.searchSlip("Slip ID", oldId);
		gotoSlipDetailsPage(oldId);
		String id = detailsPage.duplicateSlip(toSlip);
		detailsPage.clickOK();
		ajax.waitLoading();
		listPage.waitLoading();
		
		return id;
	}
	
	/**
	 * duplicate Slip NSS Group
	 * @param oldId
	 * @param toSlip
	 * @return
	 */
	public String duplicateSlipNSSGroup(String oldId, SlipInfo toSlip) {
		InvMgrSlipNSSGroupDetailsPage detailsPage = InvMgrSlipNSSGroupDetailsPage.getInstance();
		InvMgrSlipNSSGroupListPage listPage = InvMgrSlipNSSGroupListPage.getInstance();
		
		logger.info("Duplicate Slip NSS Group(ID=" + oldId + ").");
		gotoSlipNSSGroupDetailsPage(oldId);
		String id = detailsPage.duplicateSlip(toSlip);
		detailsPage.clickOK();
		ajax.waitLoading();
		listPage.waitLoading();
		
		return id;
	}
	
	public void logoutInvMgrFromLaunchPad() {
		OrmsLaunchPadPage launchPadPg = OrmsLaunchPadPage.getInstance();
//		OrmsSigninPage ormsSgInPg = OrmsSigninPage.getInstance();
		OrmsHomePage homePage = OrmsHomePage.getInstance();
		
		launchPadPg.clickSignOut();
//		ormsSgInPg.waitLoading();
		homePage.waitLoading();
	}
	
	public void gotoHomePage() {
		InvMgrTopMenuPage topMenuPage = InvMgrTopMenuPage.getInstance();
		InvMgrHomePage homePage = InvMgrHomePage.getInstance();
		
		logger.info("Goto Home page.");
		topMenuPage.clickHome();
		homePage.waitLoading();
	}
	
	public void gotoSelectFacilityPage() {
		InvMgrTopMenuPage topMenuPage = InvMgrTopMenuPage.getInstance();
		InvMgrHomePage homePage = InvMgrHomePage.getInstance();
		
		logger.info("Goto Select Facility page.");
		topMenuPage.clickSelectFacility();
		homePage.waitLoading();
	}
	
	public String addDockArea(DockInfo dock) {
		InvMgrDockListPage listPage = InvMgrDockListPage.getInstance();
		InvMgrDockDetailsPage detailsPage = InvMgrDockDetailsPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Add Dock.");
		listPage.clickAddNewDockArea();
		ajax.waitLoading();
		detailsPage.waitLoading();
		detailsPage.setupDockInfo(dock);
		detailsPage.clickApply();
		ajax.waitLoading();
		detailsPage.waitLoading();
		String toReturn = "";
		if(detailsPage.isErrorMessageExists()) {
			toReturn = detailsPage.getErrorMessage();
//			detailsPage.clickCancel();
		} else {
			detailsPage.clickOK();
			ajax.waitLoading();
			
			Object page = browser.waitExists(confirmWidget, listPage);
			if(page == confirmWidget) {
				confirmWidget.clickOK();
				ajax.waitLoading();
			}
			listPage.waitLoading();
		}
		
		return toReturn;
	}

	/**
	 * If there is error message on dock detail page, the end page is detail page.
	 * If there isn't message on dock detail page, will go back to dock list page.
	 * @param oldName
	 * @param dock
	 * @param isClickOKinConfirm
	 * @return
	 */
	public String editDock(String oldName, DockInfo dock, boolean isClickOKinConfirm){
		InvMgrDockListPage listPage = InvMgrDockListPage.getInstance();
		InvMgrDockDetailsPage detailsPage = InvMgrDockDetailsPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		this.gotoDockDetailPage(oldName);
		detailsPage.setupDockInfo(dock);
		detailsPage.clickApply();
		ajax.waitLoading();
		browser.waitExists(confirmWidget, detailsPage);
		String toReturn = "";
		if(confirmWidget.exists()){
			toReturn = confirmWidget.getMessage();
			if(isClickOKinConfirm){
				confirmWidget.clickOK();
				ajax.waitLoading();
				detailsPage.clickOK();
				ajax.waitLoading();
				listPage.waitLoading();
			} else {
				confirmWidget.clickCancel();
				ajax.waitLoading();
				detailsPage.waitLoading();
			}
		} else {
			if(detailsPage.isErrorMessageExists()) {
				toReturn = detailsPage.getErrorMessage();
			} else {
				detailsPage.clickOK();
				ajax.waitLoading();
				listPage.waitLoading();
			}
		}

		return toReturn;
	}

	/**
	 * If there is error message or confirm message, will stop at the error page(dock detail page or confirm widget).
	 * If there isn't any error message, will return to dock list page.
	 * @param oldName
	 * @param dock
	 * @return
	 */
	public String editDock(String oldName, DockInfo dock){
		return editDock(oldName, dock, true);
	}
	
	public void gotoSeasonsPageFromDetailsPage() {
		gotoSeasonsPageFromDetailsPage(true);
	}
	
	public void gotoSeasonsPageFromDetailsPage(boolean clickOK) {
		InvMgrSeasonDetailPage detailsPage = InvMgrSeasonDetailPage.getInstance();
		InvMgrFacilityBookingRulesPage seasonPage = InvMgrFacilityBookingRulesPage.getInstance();
		
		logger.info("Go back to season search/list page from details page.");
		if(clickOK) {
			detailsPage.clickOK();
		} else {
			detailsPage.clickCancel();
		}
		ajax.waitLoading();
		seasonPage.waitLoading();
	}
	
	public void gotoSeasonsPgByClickSeasonTab() {
		InvMgrSeasonClosureCommonPage commonPg = InvMgrSeasonClosureCommonPage.getInstance();
		InvMgrFacilityBookingRulesPage listPg = InvMgrFacilityBookingRulesPage.getInstance();
		
		logger.info("Go to seasons list page by click season table.");

		commonPg.clickSeasonsTab();
		ajax.waitLoading();
		listPg.waitLoading();
	}
	
	public String updateSeasonDetails(SeasonData updatedSeason) {
		InvMgrSeasonDetailPage seasonDetailPg = InvMgrSeasonDetailPage.getInstance();
		InvMgrConfimActionPage confirmPg = InvMgrConfimActionPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget=OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Update season details.");
		seasonDetailPg.setUpSeason(updatedSeason);
		seasonDetailPg.clickApply();
		Object page=browser.waitExists(confirmPg, confirmWidget, seasonDetailPg);
		if(page==confirmPg) {
			confirmPg.clickOK();
			seasonDetailPg.waitLoading();
		}else if(page==confirmWidget) {
			confirmWidget.clickOK();
			ajax.waitLoading();
			seasonDetailPg.waitLoading();
		}
		
		String toReturn="";
		if(!seasonDetailPg.isErrorMessageExists()) {
			toReturn=seasonDetailPg.getSeasonID();
		} else {
			toReturn = seasonDetailPg.getWarningMessage();
			if(toReturn.contains("The changes have been made successfully")) {
				toReturn=seasonDetailPg.getSeasonID();//handle with update season successfully
			}
		}
		return toReturn;
	}
	
	public void gotoSeasonDetailsPgByID(SeasonData season) {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage.getInstance();
		InvMgrSeasonDetailPage seasonDetailPg = InvMgrSeasonDetailPage.getInstance();
		
		logger.info("Search and go to season(ID=" + season.m_SeasonID + ") details page.");
		seasonPg.searchSeason(season);
		seasonPg.gotoSeasonDetails(season.m_SeasonID);
		seasonDetailPg.waitLoading();
	}
	
	public String gotoSeasonDetailsPg(SeasonData season) {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage.getInstance();
		InvMgrSeasonDetailPage seasonDetailPg = InvMgrSeasonDetailPage.getInstance();
		
		logger.info("Search season and go to season details page.");
		seasonPg.searchSeason(season);
		String id=seasonPg.getSeasonID();
		logger.info("Get season id:"+id);
		seasonPg.gotoSeasonDetails(id);
		seasonDetailPg.waitLoading();
		
		return id;
	}
	
	public void gotoDockSlipPgFromDockListPgByClickSlipNumber(String dockName){
		InvMgrDockListPage dockListPg = InvMgrDockListPage.getInstance();
		InvMgrDockSlipsListPage dockSlipPg = InvMgrDockSlipsListPage.getInstance();
		PagingComponent paging = new PagingComponent();
		
		logger.info("Go to dock slip page from dock list page.");
		while(!dockListPg.isDockExist(dockName) && paging.nextExists()) {
			paging.clickNext();
			ajax.waitLoading();
			dockListPg.waitLoading();
		}
		dockListPg.clickSlipNumberLink(dockName);
		ajax.waitLoading();
		dockSlipPg.waitLoading();
	}
	
	public String[] removeSlipByID(String id){
		InvMgrDockSlipsListPage dockSlipPg = InvMgrDockSlipsListPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Remove slip by ID.");
		
		dockSlipPg.removeSlip(id);
		List<String> msgs = new ArrayList<String>();
		Object page = browser.waitExists(confirmWidget, dockSlipPg);//common warning message
		if(page == confirmWidget){
			msgs.add(confirmWidget.getMessage());
			confirmWidget.clickOK();
			ajax.waitLoading();
			page = browser.waitExists(confirmWidget, dockSlipPg);//reservation confirmation message
			if(page == confirmWidget) {
				msgs.add(confirmWidget.getMessage());
				confirmWidget.clickOK();
				ajax.waitLoading();
				page = browser.waitExists(confirmWidget, dockSlipPg);//2nd reservation confirmation message, if the reservation number changes
				if(page == confirmWidget) {
					msgs.add(confirmWidget.getErrorMsg());
					confirmWidget.clickOK();
					ajax.waitLoading();
					dockSlipPg.waitLoading();
				}
			}
		}
		
		
		String toReturns[] = msgs.toArray(new String[0]);
		return toReturns;
	}
	
	/**
	 * Click OK in confirm widget when delete dock.
	 * @param name
	 * @param isOK
	 * @return
	 */
	public String deleteDock(String name) {
		String toReturn = deleteDock(name, true);
		return toReturn;
	}
	
	public String deleteDock(String name, boolean isOK) {
		InvMgrDockListPage dockListPg = InvMgrDockListPage.getInstance();
		OrmsConfirmDialogWidget confirmWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Delete dock by name:"+name);
		dockListPg.selectDock(name);
		dockListPg.clickDelete();
		ajax.waitLoading();
		browser.waitExists(confirmWidget, dockListPg);
		String toReturn = "";
		if(confirmWidget.exists()){
			toReturn = confirmWidget.getMessage();
			if(isOK){
				confirmWidget.clickOK();
			} else {
				confirmWidget.clickCancel();
			}
			ajax.waitLoading();
			dockListPg.waitLoading();
		}
		
		if(dockListPg.isErrorMessageExists()){
			toReturn = dockListPg.getErrorMessage();
		}
		return toReturn;
	}
	
	/**
	 * This method was used to verify error message when create a new season with overlap season date
	 * @param season
	 * @param expectedMsg
	 */
	public void verifyErrorMsgForCreateOverlapSeason(SeasonData season, String expectedMsg) {
		InvMgrFacilityBookingRulesPage seasonPg = InvMgrFacilityBookingRulesPage.getInstance();
		InvMgrSeasonDetailPage seasonDetailsPg = InvMgrSeasonDetailPage.getInstance();
		
		logger.info("Add a new season.");
		seasonPg.clickAddNew();
		seasonDetailsPg.waitLoading();

		//add this for marina manager 
		if(seasonDetailsPg.isPrdCategoryExisted()) {
			seasonDetailsPg.selectPrdCategory(season.m_PrdCategory);
			seasonDetailsPg.waitLoading();
		}
				
		// Set up season data in Season Details page
		seasonDetailsPg.setUpSeason(season);
		seasonDetailsPg.clickApply();
		ajax.waitLoading();
		seasonDetailsPg.waitLoading();
		
		seasonDetailsPg.verifyErrorMsgForOverlapSeason(expectedMsg);
	}
	
	/**
	 * This method was used to verify error message when update an existing season with overlap season date
	 * @param season
	 * @param expectedMsg
	 */
	public void verifyErrorMsgForUpdateOverlapSeason(SeasonData season, String expectedMsg) {
		InvMgrSeasonDetailPage seasonDetailsPg = InvMgrSeasonDetailPage.getInstance();
		this.updateSeasonDetails(season);
		seasonDetailsPg.verifyErrorMsgForOverlapSeason(expectedMsg);
	}
	
	/**
	 * This method was used to delete overlap season, and current used for clean up data of testCases.regression.basic.orms.inventory.season test cases
	 * @param season
	 */
	public void deleteOverlapSeason(SeasonData season) {
		InvMgrFacilityBookingRulesPage seasonListPg = InvMgrFacilityBookingRulesPage.getInstance();
		logger.info("Search for "+season.m_PrdCategory+" season info with type:"+season.m_SeasonType+" loop:"+season.m_Loop+" site:"+season.m_SiteType+" startdate:"+season.m_StartDate+" enddate:"+season.m_EndDate);
		seasonListPg.searchSeason(season.m_SeasonType, season.m_Loop, season.m_SiteType, season.m_PrdCategory, season.m_StartDate, season.m_EndDate);
		
		if(seasonListPg.isSeasonFound()) {
			List<String> idlist=seasonListPg.getSeasonIDsByStartAndEndDate(season.m_SeasonType, season.m_StartDate, season.m_EndDate);
			if(idlist.size()<1)
				throw new ErrorOnDataException("The searched out season details info were not exactly same season need to clean up. Please delete those seasons manually.");
			else {
				logger.info("Season "+idlist+" need to be deleted.");
				String[] ids = new String[idlist.size()];
				idlist.toArray(ids);
				deleteSeason(true, ids);
				logger.info("Season "+idlist+" has been deleted.");
			}
		}else {
			logger.info("No season found for clean up.");
		}
	}
	
	public void deleteSeason(SeasonData season) {
		deleteSeason(season, true);
	}
	
	/**
	 * This method was used to search and delete season
	 * @param season
	 */
	public String deleteSeason(SeasonData season, boolean clickOK) {
		InvMgrFacilityBookingRulesPage seasonListPg = InvMgrFacilityBookingRulesPage.getInstance();
//		InvMgrConfimActionPage confirmActionPg = InvMgrConfimActionPage.getInstance();
		OrmsConfirmDialogWidget confirmDialogWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Delete season "+season.m_SeasonID);
		seasonListPg.searchSeason(season);
		if(StringUtil.isEmpty(season.m_SeasonID)) {
			season.m_SeasonID=seasonListPg.getSeasonID();//use the first search result id
		}
		boolean found=seasonListPg.isSeasonIDExisted(season.m_SeasonID);
		if(!found)
			throw new ErrorOnDataException("Could not find season with id:"+season.m_SeasonID+" on page.");
		
		seasonListPg.selectSeasonCheckBoxByID(season.m_SeasonID);
		seasonListPg.clickDelete();
		ajax.waitLoading();
		Object pages = browser.waitExists(confirmDialogWidget,seasonListPg);
		String msg = "";
		if(confirmDialogWidget == pages){
			confirmDialogWidget.waitLoading();
			msg = confirmDialogWidget.getMessage();
			if(clickOK) {
				confirmDialogWidget.clickOK();
			} else {
				confirmDialogWidget.clickCancel();
			}
			ajax.waitLoading();
			seasonListPg.waitLoading();
		}
		if(seasonListPg.isErrorMsgExists()) {
			msg = seasonListPg.getErrorMessage();
		}
		
		return msg;
	}
	
	public void deactivateSeason(SeasonData season){
		InvMgrFacilityBookingRulesPage seasonListPg = InvMgrFacilityBookingRulesPage.getInstance();
//		InvMgrConfimActionPage confirmActionPg = InvMgrConfimActionPage.getInstance();
		OrmsConfirmDialogWidget confirmDialogWidget = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Deactivate season "+season.m_SeasonID);
		seasonListPg.searchSeason(season);
		if(StringUtil.isEmpty(season.m_SeasonID))
			season.m_SeasonID=seasonListPg.getSeasonID();//use the first search result id
		
		boolean found=seasonListPg.isSeasonIDExisted(season.m_SeasonID);
		if(!found)
			throw new ErrorOnDataException("Could not find season with id:"+season.m_SeasonID+" on page.");
		
		seasonListPg.deactivateSeason(season.m_SeasonID);
		confirmDialogWidget.waitLoading();
		confirmDialogWidget.clickOK();
		ajax.waitLoading();
//		confirmActionPg.waitExists();
//		confirmActionPg.clickOK();
		seasonListPg.waitLoading();
		season.m_searchStatus = INACTIVE_STATUS;
	}
	
	/**
	 * Change facility status, starting from Facility Details page, ending at Inventory Home page.
	 * @param status
	 */
	public void changeFacilityStatus(String status) {
		InvMgrAddFacilityDetailsPage faDetailsPg = InvMgrAddFacilityDetailsPage.getInstance();
		InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();
		logger.info("Change facility status to " + status);
		faDetailsPg.selectFacilityStatus(status);
		faDetailsPg.clickOK();
		invHmPg.waitLoading();
	}
	
	public void gotoTourTicketPgFromTourDetailsPg() {
		InvMgrTourDetailsPage tourDetail=InvMgrTourDetailsPage.getInstance();
		InvMgrComboTourDetailsPage comboTourDetail=InvMgrComboTourDetailsPage.getInstance();
		InvMgrTourTicketsPage ticketPg=InvMgrTourTicketsPage.getInstance();
		
		logger.info("Go to tour ticket page from tour details page.");
		
		if(tourDetail.exists())	
			tourDetail.clickTourTickets();
		else if(comboTourDetail.exists())
			comboTourDetail.clickTourTickets();
		ticketPg.waitLoading();
	}
	/**
	 * deactive slip.
	 * @param id
	 */
	public void deactiveSlip(String id){
		OrmsConfirmDialogWidget confirmWgt = OrmsConfirmDialogWidget.getInstance();
		InvMgrSlipListPage slipListPg = InvMgrSlipListPage.getInstance();
		slipListPg.selectSlipById(id);
		slipListPg.clickDeactivate();
		ajax.waitLoading();
		Object page = browser.waitExists(confirmWgt,slipListPg);
		if(page == confirmWgt){
			confirmWgt.clickOK();
			ajax.waitLoading();
			slipListPg.waitLoading();
		}
	}
	
	public void gotoInventoryDetailPgFromInventoryListPg(String invID){
		InvMgrSlipInventoryListPage slipInvListPg = InvMgrSlipInventoryListPage.getInstance();
		InvMgrInventoryDetailPage invDetailPg = InvMgrInventoryDetailPage.getInstance();
		
		logger.info("Go to inventory detail page by inventory ID:"+invID);
		slipInvListPg.clickInventoryID(invID);
		ajax.waitLoading();
		invDetailPg.waitLoading();
	}
	
	public void gotoSeasonSlipPgFromSeasonDetailsPg() {
		InvMgrSeasonDetailPage seasonDetailPg = InvMgrSeasonDetailPage.getInstance();
		InvMgrSeasonSlipsPage seasonSlipPg = InvMgrSeasonSlipsPage.getInstance();
		
		logger.info("Go to season slip page from season details page.");
		
		seasonDetailPg.clickSeasonSlipsTab();
		seasonSlipPg.waitLoading();
	}
	
	public void unassignSlipByID(String id) {
		InvMgrSeasonSlipsPage seasonSlipPg = InvMgrSeasonSlipsPage.getInstance();
		OrmsConfirmDialogWidget confirmWgt = OrmsConfirmDialogWidget.getInstance();
		
		logger.info("Unassign slip by id "+id);
		seasonSlipPg.selectSlipCheckBoxBySlipID(id);
		seasonSlipPg.clickUnassign();
		ajax.waitLoading();
		confirmWgt.clickOK();
		ajax.waitLoading();
		seasonSlipPg.waitLoading();
	 }
	
	public void setupContractLengthAndStartDate(String contractLength, String startDate) {
		InvMgrSlipDetailsPage slipdetailsPg = InvMgrSlipDetailsPage.getInstance();
		InvMgrSlipListPage slipListPg = InvMgrSlipListPage.getInstance();
		
		logger.info("Set up slip contract length="+contractLength+", startDate="+startDate);
		
		if(StringUtil.isEmpty(contractLength) || Integer.valueOf(contractLength)==0)
			slipdetailsPg.setSeasonalContractLength();
		else
			slipdetailsPg.setSeasonalContractLength(Integer.valueOf(contractLength));
		
		if(StringUtil.isEmpty(startDate) || Integer.valueOf(startDate)==0)
			slipdetailsPg.setSeasonalContractStartDate();
		else
			slipdetailsPg.setSeasonalContractStartDate(Integer.valueOf(startDate));
		
		slipdetailsPg.clickApply();
		ajax.waitLoading();
		slipdetailsPg.clickOK();
		slipListPg.waitLoading();
	}
	
	public void gotoMarinaMapPg() {
		InvMgrTopMenuPage invTopPg = InvMgrTopMenuPage.getInstance();
		InvMgrMarinaMapViewPage marinaMapPg = InvMgrMarinaMapViewPage.getInstance();
		logger.info("Go to Marina Map View Page");

		invTopPg.clickMarinaMap();
		marinaMapPg.waitLoading();
	}
	
	public void addSlipToMarinaMap(String dock, String slip) {
		InvMgrMarinaMapViewPage marinaMapPg = InvMgrMarinaMapViewPage.getInstance();
		
		logger.info("Add slip "+dock+" : "+slip+" to marina map.");
		marinaMapPg.selectDockArea(dock);
		Browser.sleep(5);
		marinaMapPg.selectUnMappedSlip(slip);
		marinaMapPg.clickAdd();
	}
	
	public void saveAndCloseMarinaMap() {
		InvMgrMarinaMapViewPage marinaMapPg = InvMgrMarinaMapViewPage.getInstance();
		
		logger.info("Save and close marina map.");
		marinaMapPg.clickSaveEdits();
		ajax.waitLoading();
		marinaMapPg.waitLoading();
		
		marinaMapPg.clickX();
	}
	
	public void setupSeasonalContractSetting(String contractSetting, String contractLength){
		InvMgrFacilityDetailPage facilityDetailPg = InvMgrFacilityDetailPage.getInstance();
		InvMgrHomePage InvHmPg = InvMgrHomePage.getInstance();
		
		logger.info("Set up seasonal contract setting info at facility detail page." +
				" Contract Setting = " + contractSetting + "; Contract length = " + contractLength);
		
		facilityDetailPg.setSeasonalContractSettingInfo(contractSetting, contractLength);
		facilityDetailPg.clickOK();
		InvHmPg.waitLoading();
	}
	
	public void gotoLoyaltyProgramSearchPage() {
		InvMgrHomePage homePage = InvMgrHomePage.getInstance();
		InvMgrLoyaltyProgramListPage listPage = InvMgrLoyaltyProgramListPage.getInstance();
		
		logger.info("Goto Loyalty Program search/list page from home page.");
		String selectedOption = homePage.getDefaultPageName();
		String target = "Loyalty Program Setup";
		if(!selectedOption.equalsIgnoreCase(target)) {
			homePage.selectPageFromDropDownList(target);
		} else {
			listPage.clickLoyaltyProgramsTab();
			ajax.waitLoading();
		}
		listPage.waitLoading();
	}
	
	public String addNewLoyaltyProgram(LoyaltyProgram lp) {
		InvMgrLoyaltyProgramListPage listPage = InvMgrLoyaltyProgramListPage.getInstance();
		InvMgrLoyaltyProgramLocationSearchPage locationPage = InvMgrLoyaltyProgramLocationSearchPage.getInstance();
		InvMgrLoyaltyProgramDetailsPage detailsPage = InvMgrLoyaltyProgramDetailsPage.getInstance();
		
		logger.info("Add new Loyalty Program.");
		listPage.clickAddNew();
		ajax.waitLoading();
		locationPage.waitLoading();
		locationPage.searchLocation(lp.getLocation(), lp.getLocationCategory());
		locationPage.selectLocation(lp.getLocation());
		ajax.waitLoading();
		detailsPage.waitLoading();
		detailsPage.setLoyaltyProgram(lp);
		detailsPage.clickApply();
		ajax.waitLoading();
		detailsPage.waitLoading();
		
		String toReturn = "";
		if(!detailsPage.isErrorMsgExists()) {
			toReturn = detailsPage.getProgramID();
			detailsPage.clickOK();
			ajax.waitLoading();
			listPage.waitLoading();
		} else {
			toReturn = detailsPage.getErrorMsg();
		}
		
		return toReturn;
	}
	
	public void gotoLoyaltyProgramDetailsPage(String name) {
		InvMgrLoyaltyProgramListPage listPage = InvMgrLoyaltyProgramListPage.getInstance();
		
		listPage.searchLoyaltyProgram(OrmsConstants.ACTIVE_STATUS);
		String id = listPage.getLoyaltyProgramID(name);
		this.gotoLoyaltyProgramDetailsPage(OrmsConstants.ACTIVE_STATUS, id);
	}
	
	public void gotoLoyaltyProgramDetailsPage(String status, String id) {
		InvMgrLoyaltyProgramListPage listPage = InvMgrLoyaltyProgramListPage.getInstance();
		InvMgrLoyaltyProgramDetailsPage detailsPage = InvMgrLoyaltyProgramDetailsPage.getInstance();
		PagingComponent paging = new PagingComponent();
		
		logger.info("Goto Loyalty Program(ID=" + id + ") details page.");
		listPage.searchLoyaltyProgram(status);
		boolean exist = false;
		do {
			exist = listPage.isLoyaltyProgramExists(id);
		} while(!exist && paging.clickNext());
		
		listPage.clickID(id);
		ajax.waitLoading();
		detailsPage.waitLoading();
	}
	
	public String editLoyaltyProgram(LoyaltyProgram program) {
		InvMgrLoyaltyProgramDetailsPage detailsPage = InvMgrLoyaltyProgramDetailsPage.getInstance();
		InvMgrLoyaltyProgramListPage listPage = InvMgrLoyaltyProgramListPage.getInstance();
		
		logger.info("Update Loyalty Program(ID=" + program.getId() + ").");
		detailsPage.setLoyaltyProgram(program);
		detailsPage.clickApply();
		ajax.waitLoading();
		detailsPage.waitLoading();
		
		String toReturn = "";
		if(!detailsPage.isErrorMsgExists()) {
			toReturn = detailsPage.getProgramID();
			detailsPage.clickOK();
			ajax.waitLoading();
			listPage.waitLoading();
		} else {
			toReturn = detailsPage.getErrorMsg();
		}
		
		return toReturn;
	}
	
	public void activateLoyaltyProgram(String id) {
		logger.info("Activate Loyalty Program(ID=" + id + ").");
		InvMgrLoyaltyProgramListPage.getInstance().changeLoyaltyProgramStatus(id, OrmsConstants.ACTIVE_STATUS);
	}
	
	public void deactivateLoyaltyProgram(String id) {
		logger.info("Deactivate Loyalty Program(ID=" + id + ").");
		InvMgrLoyaltyProgramListPage.getInstance().changeLoyaltyProgramStatus(id, OrmsConstants.INACTIVE_STATUS);
	}
	
	public void gotoPointTypesViewListPage() {
		InvMgrHomePage homePage = InvMgrHomePage.getInstance();
		InvMgrLoyaltyProgramListPage listPage = InvMgrLoyaltyProgramListPage.getInstance();
		InvMgrPointTypeListPage typeListPg=InvMgrPointTypeListPage.getInstance();
		
		logger.info("Goto Point Types View List page from home page.");
		homePage.selectPageFromDropDownList("Loyalty Program Setup");
		listPage.waitLoading();
		listPage.clickPointTypesTab();
		ajax.waitLoading();
		typeListPg.waitLoading();
		
	}
	
	public String addNewPointTypes(PointType point) {
		InvMgrPointTypeListPage typeListPg=InvMgrPointTypeListPage.getInstance();
		InvMgrPointTypeDetailsWidget  detailPg=InvMgrPointTypeDetailsWidget.getInstance();
		
		logger.info("Add new Point Types.");
		typeListPg.clickAddNew();
		ajax.waitLoading();
		detailPg.waitLoading();
		detailPg.setName(point.getName());
		detailPg.selectRedeemableType(point.getRedeemableType());
		detailPg.clickOK();
		ajax.waitLoading();
		typeListPg.waitLoading();
		
		String toReturn = typeListPg.getPointTypeIdByType(point.getName());
		
		return toReturn;
	}
	
	public String addNewEarnSchedule(LoyaltyProgramSchedule schedule) {
		InvMgrLoyaltyProgramDetailsPage detailsPage = InvMgrLoyaltyProgramDetailsPage.getInstance();
		InvMgrEarnScheduleListPage earnListPage=InvMgrEarnScheduleListPage.getInstance();
		InvMgrScheduleFindLocationPage findLocPg=InvMgrScheduleFindLocationPage.getInstance();
		InvMgrEarnScheduleDetailPage earndetailPage=InvMgrEarnScheduleDetailPage.getInstance();
		
		detailsPage.clickEarnSchedule();
		ajax.waitLoading();
		Object page = browser.waitExists(earnListPage, findLocPg);
		if(page == earnListPage) {
			earnListPage.clickAddNew();
			ajax.waitLoading();
		}

		findLocPg.searchByLocationName(schedule.getLocation(), schedule.getLocationCategory());
		findLocPg.selectLocation(schedule.getLocation());
		ajax.waitLoading();
		earndetailPage.waitLoading();
		
		earndetailPage.selectProductCategory(schedule.getProductCategory());
		ajax.waitLoading();
		earndetailPage.setupEarnSchedule(schedule);
		earndetailPage.clickApply();
		ajax.waitLoading();
		earndetailPage.waitLoading();
		String returnValue = "";
		if(earndetailPage.isErrorMsgExists()) {
			returnValue = earndetailPage.getErrorMsg();
		} else {
			returnValue = earndetailPage.getEarnScheduleID();
		}
		
		earndetailPage.clickOk();
		ajax.waitLoading();
		earnListPage.waitLoading();
		
		return returnValue;
	}
	
	public void changeEarnScheduleStatus(String earnSchdId, String status) {
		InvMgrEarnScheduleListPage listPg=InvMgrEarnScheduleListPage.getInstance();
		
		logger.info("Change Earn Schedule(ID=" + earnSchdId + ") Status to " + status);

		if(!listPg.isEarnScheduleExists(earnSchdId)) {
			listPg.searchByEarnScheduleId(earnSchdId);
		}
		listPg.selectScheduleCheckBox(earnSchdId);
		if (status.equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS)) {
			listPg.clickActive();
			ajax.waitLoading();
			listPg.waitLoading();
			String errorMsg = listPg.getErrorMsg();
			if(!StringUtil.isEmpty(errorMsg)) {
				String existingIDs[] = RegularExpression.getMatches(errorMsg, "\\d+");
				for(int i = 1; i < existingIDs.length; i ++) {
					this.changeEarnScheduleStatus(existingIDs[i], OrmsConstants.INACTIVE_STATUS);
				}
				
				if(!listPg.isEarnScheduleExists(earnSchdId)) {
					listPg.searchByEarnScheduleId(earnSchdId);
				}
				this.changeEarnScheduleStatus(earnSchdId, status);
			}
		} else if (status.equalsIgnoreCase(OrmsConstants.INACTIVE_STATUS)) {
			listPg.clickDeactive();
			ajax.waitLoading();
		} else {
			throw new ItemNotFoundException("Unkown Status " + status);
		}
		listPg.waitLoading();
	}
	
	public String addNewRedeemSchedule(LoyaltyProgramSchedule schedule) {
		InvMgrLoyaltyProgramDetailsPage detailsPage = InvMgrLoyaltyProgramDetailsPage.getInstance();
		InvMgrRedeemScheduleListPage redeemListPage=InvMgrRedeemScheduleListPage.getInstance();
		InvMgrScheduleFindLocationPage findLocPg=InvMgrScheduleFindLocationPage.getInstance();
		InvMgrRedeemScheduleDetailPage redeemDetailPage=InvMgrRedeemScheduleDetailPage.getInstance();
		
		detailsPage.clickRedeemSchedule();
		ajax.waitLoading();
		Object page = browser.waitExists(redeemListPage, findLocPg);
		if(page == redeemListPage) {
			redeemListPage.clickAddNew();
			ajax.waitLoading();
		}
		
		findLocPg.searchByLocationName(schedule.getLocation(), schedule.getLocationCategory());
		findLocPg.selectLocation(schedule.getLocation());
		ajax.waitLoading();
		redeemDetailPage.waitLoading();
		
		redeemDetailPage.selectProductCategory(schedule.getProductCategory());
		ajax.waitLoading();
		redeemDetailPage.setupRedeemSchedule(schedule);
		redeemDetailPage.clickApply();
		ajax.waitLoading();
		redeemDetailPage.waitLoading();
		String returnValue=redeemDetailPage.getRedeemScheduleID();
		redeemDetailPage.clickOk();
		ajax.waitLoading();
		redeemListPage.waitLoading();
		
		return returnValue;
	}
	
	public void changeRedeemScheduleStatus(String redeemSchdId, String status) {
		InvMgrRedeemScheduleListPage redeemListPage=InvMgrRedeemScheduleListPage.getInstance();
		
		logger.info("Change Redeem Schedule(ID=" + redeemSchdId + ") Status to " + status);

		if(!redeemListPage.isRedeemScheduleExists(redeemSchdId)) {
			redeemListPage.searchByRedeemScheduleId(redeemSchdId);
		}
		redeemListPage.selectScheduleCheckBox(redeemSchdId);
		if (status.equalsIgnoreCase(OrmsConstants.ACTIVE_STATUS)) {
			redeemListPage.clickActive();
			ajax.waitLoading();
			redeemListPage.waitLoading();
			String errorMsg = redeemListPage.getErrorMsg();
			if(!StringUtil.isEmpty(errorMsg)) {
				String existingIDs[] = RegularExpression.getMatches(errorMsg, "\\d+");
				for(int i = 1; i < existingIDs.length; i ++) {
					this.changeEarnScheduleStatus(existingIDs[i], OrmsConstants.INACTIVE_STATUS);
				}
				
				if(!redeemListPage.isRedeemScheduleExists(redeemSchdId)) {
					redeemListPage.searchByRedeemScheduleId(redeemSchdId);
				}
				this.changeEarnScheduleStatus(redeemSchdId, status);
			}
		} else if (status.equalsIgnoreCase(OrmsConstants.INACTIVE_STATUS)) {
			redeemListPage.clickDeactive();
			ajax.waitLoading();
		} else {
			throw new ItemNotFoundException("Unkown Status " + status);
		}
		redeemListPage.waitLoading();
	}
	
	public void gotoEquipmentSetUpPage(){
		InvMgrTopMenuPage invTpMenuPg = InvMgrTopMenuPage.getInstance();
		InvMgrEquipmentSearchPage eqSearchPg = InvMgrEquipmentSearchPage.getInstance();
		logger.info("Go to equipment search page.");
		invTpMenuPg.gotoSpecifiedDetailPage("Equipment Set-up");
		ajax.waitLoading();
		eqSearchPg.waitLoading();
	}
	
	public void gotoEquipmentInvSetUpPage(){
		InvMgrTopMenuPage invTpMenuPg = InvMgrTopMenuPage.getInstance();
		InvMgrEquipmentInventorySearchPage eqSearchPg = InvMgrEquipmentInventorySearchPage.getInstance();
		logger.info("Go to equipment inventory search page.");
		invTpMenuPg.gotoSpecifiedDetailPage("Equipment Inventory Set-up");
		ajax.waitLoading();
		eqSearchPg.waitLoading();
	}
	
	/**
	 * Add a new equipment
	 * @param eq
	 * @return
	 */
	public String addEquipment(Data<EquipmentAttr> eq){
		InvMgrEquipmentSearchPage eqSearchPg = InvMgrEquipmentSearchPage.getInstance();
		InvMgrEquipmentDetailPage eqDetailPg = InvMgrEquipmentDetailPage.getInstance();
		this.gotoEquipmentSetUpPage();
		eqSearchPg.clickAddNew();
		ajax.waitLoading();
		eqDetailPg.waitLoading();
		eqDetailPg.setUpEquipmentAndClickApply(eq);
		ajax.waitLoading();
		String idOrErrMsg = eqDetailPg.getEquipmentId();
		if(idOrErrMsg.matches("NEW")){
			idOrErrMsg = eqDetailPg.getErrorMsg();
		}else{
			eqDetailPg.clickOk();
			ajax.waitLoading();
			eqSearchPg.waitLoading();
		}
		return idOrErrMsg;
	}
	
	/**
	 * Add a new inventory for equipment
	 * @param eqInv
	 */
	public void addEquipmentInv(Data<EquipmentInvAttr> eqInv){
		InvMgrEquipmentInventorySearchPage eqInvSearchPg = InvMgrEquipmentInventorySearchPage.getInstance();
		InvMgrEquipmentInventoryDetailPage eqInvDetailPg = InvMgrEquipmentInventoryDetailPage.getInstance();
		this.gotoEquipmentInvSetUpPage();
		eqInvSearchPg.clickAddNew();
		ajax.waitLoading();
		eqInvDetailPg.waitLoading();
		eqInvDetailPg.setUpEquipmentInfo(eqInv);
		eqInvDetailPg.clickOk();
		ajax.waitLoading();
		browser.waitExists(eqInvSearchPg , eqInvDetailPg);
	}
	
	public void gotoPOSPurchaseOrderDetailPageFromSearchPage(String poNum){
		InvMgrPOSPurchaseOrderSearchPage posPurchaseOrderPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
		InvMgrPurchaseOrderDetailsPage detailPage = InvMgrPurchaseOrderDetailsPage.getInstance();

		logger.info("Go to POS purchase order detail page from search page by PO#.");
		posPurchaseOrderPg.searchPosPurchaseOrderByPONum(poNum);
		posPurchaseOrderPg.clickPONum(poNum);
		ajax.waitLoading();
		detailPage.waitLoading();
	}
	
	public void gotoReceivePOSPurchaseOrderPage(String poNum){
		InvMgrPOSPurchaseOrderSearchPage ordSearchPg = InvMgrPOSPurchaseOrderSearchPage.getInstance();
		InvMgrReceivePurchaseOrderPage reveivePage = InvMgrReceivePurchaseOrderPage.getInstance();
		
		logger.info("Go to Receive POS Purchase Order Page from search POS purchase order page.");
		int index = ordSearchPg.getRowNumForPoNum(poNum);
		ordSearchPg.clickReceiveQty(index-1);
		ajax.waitLoading();
		reveivePage.waitLoading();
	}
	
	public void gotoEquipmentDetailsByID(String id) {
		InvMgrEquipmentSearchPage eqSearchPg = InvMgrEquipmentSearchPage.getInstance();
		InvMgrEquipmentDetailPage eqDetailsPg = InvMgrEquipmentDetailPage.getInstance();
		
		logger.info("Go to equipment details page by id "+id);
		
		eqSearchPg.clickEqID(id);
		ajax.waitLoading();
		eqDetailsPg.waitLoading();
	}
	
	public void gotoEquipmentHourPageFromEquipmentDetailsPage() {
		InvMgrEquipmentDetailPage detailsPage = InvMgrEquipmentDetailPage.getInstance();
		InvMgrEquipmentHoursPage hoursPage = InvMgrEquipmentHoursPage.getInstance();
		
		logger.info("Go to equipment hours page from equipment details page.");
		detailsPage.clickEquipmentHoursTab();
		ajax.waitLoading();
		hoursPage.waitLoading();
	}
	
	public void AddEquipmentHour(Data<EquipmentHourAttr> equipHour) {
		InvMgrEquipmentHoursPage hoursPage = InvMgrEquipmentHoursPage.getInstance();
		InvMgrAddEquipmentHourWidget equipmentHourWidget = InvMgrAddEquipmentHourWidget.getInstance();
		
		logger.info("Add equipment available hours.");
		hoursPage.clickAddNew();
		ajax.waitLoading();
		equipmentHourWidget.waitLoading();
		equipmentHourWidget.setupAvailableHour(equipHour);
		equipmentHourWidget.clickOK();
		ajax.waitLoading();
		hoursPage.waitLoading();
	}
}
