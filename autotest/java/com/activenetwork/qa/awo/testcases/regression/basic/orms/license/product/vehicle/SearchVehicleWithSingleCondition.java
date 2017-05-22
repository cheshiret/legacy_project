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
 * 1. search by ID/MI #
 * 2. search by Hull ID/Serial #
 * 3. search by Title #
 * 4. search by Last Name
 * 5. search by Business Name
 * @Preconditions:
 * @SPEC:Search Vehicle.UCS
 * @Task#:Auto-1005
 * 
 * @author nding1
 * @Date  Jul 3, 2012
 */
public class SearchVehicleWithSingleCondition extends LicenseManagerTestCase {
	private String message = "";
	private boolean result = true;
	private LicMgrVehiclesSearchPage vehicleSearchPg = LicMgrVehiclesSearchPage.getInstance();
	private BoatInfo vehicle = new BoatInfo();
	private BoatInfo searchInfo = new BoatInfo();
	private OrmsOrderSummaryPage orderSummaryPage = OrmsOrderSummaryPage.getInstance();
	
	public void execute(){
		lm.loginLicenseManager(login);

		// data prepare
		searchInfo.hullIdSerialNum = vehicle.hullIdSerialNum;
		String msg = lm.searchVehicle(searchInfo);
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
		
		// 1. search by ID/MI #
		searchInfo.hullIdSerialNum = StringUtil.EMPTY;
		searchInfo.id = vehicle.registration.miNum;
		lm.searchVehicle(searchInfo);
		this.verifySearchReault("ID/MI #", searchInfo.id);
		
		// 2. search by Hull ID/Serial #
		searchInfo.id = StringUtil.EMPTY;
		searchInfo.hullIdSerialNum = vehicle.hullIdSerialNum;
		lm.searchVehicle(searchInfo);
		this.verifySearchReault("Hull ID/Serial #", searchInfo.hullIdSerialNum);

		// 3. search by Title #
		searchInfo.hullIdSerialNum = StringUtil.EMPTY;
		searchInfo.title.titleNum = vehicle.title.titleNum;
		lm.searchVehicle(searchInfo);
		this.verifySearchReault("Title #", searchInfo.title.titleNum);
		
		// 4. search by Last Name
		searchInfo.title.titleNum = StringUtil.EMPTY;
		searchInfo.lastName = cust.lName;
		lm.searchVehicle(searchInfo);
		this.verifySearchReault("Customer(Owner)", searchInfo.lastName+","+cust.fName);

		// 5. search by Business Name
		searchInfo.lastName = StringUtil.EMPTY;
		searchInfo.businessName = "@#$%%^^)(*&^";
		String actulaMsg = lm.searchVehicle(searchInfo);
		// verify search result
		if(!actulaMsg.equals(message)){
			result &= false;
			logger.error("---Warning message should be "+message+", but actual messge is "+actulaMsg);
		}

		if(!result){
			throw new ErrorOnPageException("---Not all of the check points are passed!");
		}
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param){
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		// customer info
		cust.lName = "TEST-TransferRule77";
		cust.fName = "QA-TransferRule77";

		// default value
		searchInfo.searchyByOwnerOrCoowner = "Owner";
		searchInfo.isIncludeAreaCode = true;

		// registration info
		vehicle.isIncludeAreaCode = true;// default value
		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "ForSingleSearch";// Don't change!
		vehicle.manufacturerName = "Sony";
		vehicle.yearBuilt = "1995";
		vehicle.modelYear = "1995";
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "OTHER";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = "REG - RegisterBoat";

		// title info
		vehicle.title.boatValue = "202";
		vehicle.title.setProductCode("TFL");
		vehicle.title.product = "TFL - TitleForLien";
		vehicle.title.purchaseType = OrmsConstants.ORIGINAL_PURCHASE_TYPE;

		message = "No Vehicles found matching the search criteria. Please re-enter.";
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
			logger.error("---"+searchBy + "  which value is "+searchValue+" should be contained in the search result.");
		} else {
			logger.info("When search by "+searchBy+"(value is:"+searchValue+"), the search result is correct.");
		}
	}
}
