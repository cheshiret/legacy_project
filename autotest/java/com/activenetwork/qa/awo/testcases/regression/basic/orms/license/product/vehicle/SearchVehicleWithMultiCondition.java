package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehiclesSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 1.search by model year and Boat Information - Boat Use is Other and Last Name
 * 2.search by Manufacturer and vehicle status
 * 3.search by vehicle type and Phone and search by Owner
 * @Preconditions:
 * 1.customer(TEST-TransferRule7, QA-TransferRule7) must exist
 * 2.vehicle(DZ1 - ViewVehicleRegistrationHi) must exist
 * @SPEC:Search Vehicle.UCS
 * @Task#:Auto-1005
 * 
 * @author nding1
 * @Date  Jul 3, 2012
 */
public class SearchVehicleWithMultiCondition extends LicenseManagerTestCase {
	private BoatInfo searchVehicle = new BoatInfo();
	private BoatInfo vehicle = new BoatInfo();
	private boolean result = true;
	private LicMgrVehiclesSearchPage vehicleSearchPg = LicMgrVehiclesSearchPage.getInstance();
	private OrmsOrderSummaryPage orderSummaryPage = OrmsOrderSummaryPage.getInstance();

	public void execute(){
		lm.loginLicenseManager(login);

		// data prepare
		searchVehicle.hullIdSerialNum = vehicle.hullIdSerialNum;
		String msg = lm.searchVehicle(searchVehicle);
 		if(!StringUtil.isEmpty(msg)) {// registration doesn't exist.
			lm.gotoHomePage();
			lm.registerVehicleToOrderCart(cust, vehicle);
			lm.processOrderCartToOrderSummaryPage(pay, true);
			vehicle.registration.miNum = orderSummaryPage.getMINum();
			lm.finishOrder();

			lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
			lm.titleVehicleToOrderCartFromVehicleDetailPage(vehicle);
			lm.processOrderToOrderSummary(pay);
			vehicle.title.titleNum = orderSummaryPage.getTitleNum();
			lm.finishOrder();
		} else {
			vehicle.registration.miNum = vehicleSearchPg.getColumnByName("ID/MI #").get(0);
			vehicle.title.titleNum = vehicleSearchPg.getColumnByName("Title #").get(0);
			
			// need to title.
			if(StringUtil.isEmpty(vehicle.title.titleNum)){
				lm.gotoHomePage();
				lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
				lm.titleVehicleToOrderCartFromVehicleDetailPage(vehicle);
				lm.processOrderToOrderSummary(pay);
				vehicle.title.titleNum = orderSummaryPage.getTitleNum();
				lm.finishOrder();
			}
		}

		// go to search registration page
		lm.gotoSearchVehiclePage();
		
		// 1.search by model year and Boat Information - Boat Use is Personal Pleasure and Last Name
		searchVehicle.hullIdSerialNum = StringUtil.EMPTY;
		searchVehicle.modelYear = vehicle.modelYear;
		searchVehicle.vehicleSearchType = "Boat Information - Boat Use";
		searchVehicle.vehicleSearchValue = vehicle.boatUse;
		searchVehicle.lastName = cust.lName;
		searchVehicle.firstName = cust.fName;
		lm.searchVehicle(searchVehicle);
		this.verifySearchReault("Model year", searchVehicle.modelYear);
		this.verifySearchReault("Customer(Owner)", searchVehicle.lastName+","+searchVehicle.firstName);
		this.verifySearchReault("Hull ID/Serial #", vehicle.hullIdSerialNum);

		// 2.search by Manufacturer and vehicle status and Last Name
		searchVehicle.modelYear = StringUtil.EMPTY;
		searchVehicle.vehicleSearchType = StringUtil.EMPTY;
		searchVehicle.vehicleSearchValue = StringUtil.EMPTY;
		searchVehicle.manufacturerName = vehicle.manufacturerName;
		searchVehicle.status = OrmsConstants.ACTIVE_STATUS;
		lm.searchVehicle(searchVehicle);
		this.verifySearchReault("Manufacturer", searchVehicle.manufacturerName);
		this.verifySearchReault("Status", searchVehicle.status);
		this.verifySearchReault("Customer(Owner)", searchVehicle.lastName+","+searchVehicle.firstName);
		
		// 3.search by vehicle type and Last Name
		searchVehicle.manufacturerName = StringUtil.EMPTY;
		searchVehicle.status = StringUtil.EMPTY;
		searchVehicle.searchyByOwnerOrCoowner = "Owner";
		searchVehicle.type = vehicle.type;
		lm.searchVehicle(searchVehicle);
		this.verifySearchReault("Customer(Owner)", searchVehicle.lastName+","+searchVehicle.firstName);
		this.verifySearchReault("Type", searchVehicle.type);

		if(!result){
			throw new ErrorOnPageException("---Not all of the check points are passed!");
		}
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		//login.station = "Station 1 AM";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		// customer info
		cust.lName = "TEST-TransferRule112";
		cust.fName = "QA-TransferRule112";
		
		// default value
		searchVehicle.isIncludeAreaCode = true;

		// registration info
		vehicle.isIncludeAreaCode = true;// default value
		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "ForMultiSearch";// Don't change!
		vehicle.manufacturerName = "Sony";
		vehicle.yearBuilt = "1995";
		vehicle.modelYear = "1995";
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "OTHER";;// IMPORTANT:Don't change this data.
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = "REG - RegisterBoat";

		// title info
		vehicle.title.boatValue = "202";
		vehicle.title.setProductCode("TFL");
		vehicle.title.product = "TFL - TitleForLien";
		vehicle.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;
	}
	
	/**
	 * Verify search result.
	 * @param searchBy
	 * @param searchValue
	 */
	private void verifySearchReault(String searchBy, String searchValue){
		logger.info("Verify search result.");
		List<String> columnList = vehicleSearchPg.getColumnByName(searchBy);
		if(!columnList.contains(searchValue)){
			result &= false;
			logger.error("---"+searchBy + " which value is "+searchValue+" should be contained in the search result.");
		} else {
			logger.info("Search result is correct.");
		}
	}
}
