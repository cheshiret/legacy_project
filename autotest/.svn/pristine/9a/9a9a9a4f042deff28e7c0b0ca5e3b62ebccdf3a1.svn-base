package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title;

import java.util.Random;
import java.util.TimeZone;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TitleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the searching functionality works correctly in Vehicle Title search/list page
 * @Preconditions: 1. an existing Vehicle product
 * 							2. register this Vehicle
 * 							3. title this vehicle
 * @SPEC: <<Search Vehicle Title.UCS>> TC: 004711
 * @Task#: AUTO-1008
 * 
 * @author qchen
 * @Date  Jun 27, 2012
 */
public class SearchVehicleTitle extends LicenseManagerTestCase {
	private VehicleRTI regVehicleRTI = new VehicleRTI();
	private VehicleRTI titleVehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	private OrmsOrderSummaryPage summaryPage = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleTitleSearchPage titleSearchPage = LicMgrVehicleTitleSearchPage.getInstance();
	private TitleInfo title = new TitleInfo();
	private TimeZone timeZone = null;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		//register vehicle
		lm.registerVehicleToOrderCart(cust, vehicle);
		lm.processOrderToOrderSummary(pay);
		vehicle.registration.miNum = summaryPage.getMINum();
		String ordNum1 = summaryPage.getAllOrdNums().split(" ")[0];
		lm.finishOrder();
		
		//title vehicle
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.titleVehicleToOrderCartFromVehicleDetailPage(vehicle);
		lm.processOrderToOrderSummary(pay);
		title.titleNum = summaryPage.getTitleNum();
		String ordNum2 = summaryPage.getAllOrdNums().split(" ")[0];
		title.setOrderNum(ordNum2);
		title.setReceiptNum(summaryPage.getReceiptNum());
		lm.finishOrder();
			
		//go to vehicle title search/list page to verify the searching functionality
		lm.gotoVehicleTitleSearchPage();
		this.verifySearchTitleCorrectly();

		//clean up
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(ordNum1, vehicle.operationReason, vehicle.operationNote);
		lm.processOrderCart(pay);
		lm.voidRegisterVehicleOrder(ordNum2, vehicle.operationReason, vehicle.operationNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		regVehicleRTI.setPrdCode("RV1");
		regVehicleRTI.setPrdName("RegisterVehicleBoat");
		regVehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		regVehicleRTI.setPrdGroup("Registration");
		regVehicleRTI.setVehicleType("Boat");
		
		titleVehicleRTI.setPrdCode("TV1");
		titleVehicleRTI.setPrdName("TitleVehicleBoat");
		titleVehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		titleVehicleRTI.setPrdGroup("Title");
		titleVehicleRTI.setVehicleType("Boat");
		
		//vehicle registration info
		vehicle.hullIdSerialNum = "Auto" + DateFunctions.getCurrentTime();
		vehicle.manufacturerName = "Sony";
		vehicle.modelYear = String.valueOf(DateFunctions.getCurrentYear());
		vehicle.feet = "99";
		vehicle.inches = "9";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "PLEASURE";
		vehicle.propulsion = "OTHER";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Jet Boat";
		
		//vehicle registration info
		vehicle.registration.product = regVehicleRTI.getPrdCode() + " - " + regVehicleRTI.getPrdName();
		
		//vehicle title info
		vehicle.title.product = titleVehicleRTI.getPrdCode() + " - " + titleVehicleRTI.getPrdName();
		vehicle.title.purchaseType = "Original";
		vehicle.title.boatValue = "299";

		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000023";
		cust.dateOfBirth = "Jun 12 1988";
		cust.lName = "TEST-Basic23";
		cust.fName = "QA-Basic23";
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		
	}
	
	private void checkTitleExists(String titleNum, boolean expected) {
		boolean exists = titleSearchPage.checkTitleExistsIdentifiedByTitleNum(titleNum);
		if(exists != expected) {
			throw new ErrorOnPageException("Vehicle Title(#=" + titleNum + ") " + (expected ? "doesn't" : "") + " exist in the searching result list.");
		} logger.info("Vehicle Title(#=" + titleNum + ") really " + (expected ? "" : "NOT") + " exists in searching result list.");
	}
	
	private void clearTitle() {
		title.setTitleSearchType("");
		title.setTitleSearchValue("");
		title.status = "";
		title.setProductCode("");
		title.setCreationValidFrom("");
		title.setCreationValidTo("");
		title.setVehicleIdMiNum("");
		title.setHullIdSerialNum("");
		title.setVehicleType("");
		title.setVehicleSearchType("");
		title.setVehicleSearchValue("");
	}
	
	private void verifySearchTitleCorrectly() {
		//a. title number
		titleSearchPage.searchTitle("Title #", title.titleNum);
		this.checkTitleExists(title.titleNum, true);
		
		//b. order number
		titleSearchPage.searchTitle("Order #", title.getOrderNum());
		this.checkTitleExists(title.titleNum, true);
		//system shall permit the User to search with or without the dash(e.g. 9-12345=912345)
		titleSearchPage.searchTitle("Order #", title.getOrderNum().replace("-", ""));
		this.checkTitleExists(title.titleNum, true);//DEFECT-35357 has been fixed
		
		//c. receipt number
		titleSearchPage.searchTitle("Receipt #", title.getReceiptNum());
		this.checkTitleExists(title.titleNum, true);
		
		//d. status - Active
		title.setTitleSearchType("Title #");
		title.setTitleSearchValue(title.titleNum);
		title.status = OrmsConstants.ACTIVE_STATUS;
		titleSearchPage.searchTitle(title);
		this.checkTitleExists(title.titleNum, true);
		
		//e. status - other than 'Active'
		title.status = new String[]{OrmsConstants.REVERSED_STATUS, OrmsConstants.TRANSFERABLE_STATUS, OrmsConstants.TRANSFERRED_STATUS}[new Random().nextInt(2)];
		titleSearchPage.searchTitle(title);
		this.checkTitleExists(title.titleNum, false);
		
		//f. product code
		clearTitle();
		title.setProductCode(titleVehicleRTI.getPrdCode());
		titleSearchPage.clearSearchCriteria();
		titleSearchPage.searchTitle(title);
		this.checkTitleExists(title.titleNum, true);
		
		//g. creation valid from, creation valid to
		clearTitle();
		title.setCreationValidFrom(DateFunctions.getToday(timeZone));
		title.setCreationValidTo(DateFunctions.getToday(timeZone));
		titleSearchPage.searchTitle(title);
		this.checkTitleExists(title.titleNum, true);
		
		//h. vehicle ID/MI #
		clearTitle();
		title.setVehicleIdMiNum(vehicle.registration.miNum);
		titleSearchPage.clearSearchCriteria();
		titleSearchPage.searchTitle(title);
		this.checkTitleExists(title.titleNum, true);
		//system shall permit the user to search with or without the 2-character state prefix(e.g. MI1234AA=1234AA)
		title.setVehicleIdMiNum(vehicle.registration.miNum.replace("MI", ""));
		titleSearchPage.searchTitle(title);
		this.checkTitleExists(title.titleNum, true);
		
		//i. hull ID/Serial #
		clearTitle();
		title.setHullIdSerialNum(vehicle.hullIdSerialNum);
		titleSearchPage.clearSearchCriteria();
		titleSearchPage.searchTitle(title);
		this.checkTitleExists(title.titleNum, true);
		
		//j. vehicle type
		clearTitle();
		title.setCreationValidFrom(DateFunctions.getToday(timeZone));
		title.setVehicleType(titleVehicleRTI.getVehicleType());
		titleSearchPage.clearSearchCriteria();
		titleSearchPage.searchTitle(title);
		this.checkTitleExists(title.titleNum, true);
		
		//k. vehicle search type - 'Boat Use'
		clearTitle();
		title.setCreationValidFrom(DateFunctions.getToday(timeZone));
		title.setVehicleSearchType("Boat Use");
		title.setVehicleSearchValue(vehicle.boatUse);
		titleSearchPage.clearSearchCriteria();
		titleSearchPage.searchTitle(title);
		this.checkTitleExists(title.titleNum, true);
		
		//l. vehicle search type - 'Type of Boat'
		clearTitle();
		title.setCreationValidFrom(DateFunctions.getToday(timeZone));
		title.setVehicleSearchType("Type of Boat");
		title.setVehicleSearchValue(vehicle.typeOfBoat);
		titleSearchPage.clearSearchCriteria();
		titleSearchPage.searchTitle(title);
		this.checkTitleExists(title.titleNum, true);
	}
}
