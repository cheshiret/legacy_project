/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.order;

import java.util.HashMap;
import java.util.Map;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:search vehicle order work flow--using single condition in the sale agent location
 *              check point:1. search vehicle using order number
 *                          2. search vehicle using receipt number
 *                          3. search vehicle using order date
 *                          4. search vehicle using sale location
 *                          5. search vehicle using vehicle number
 *                          6. search vehicle using Hull ID
 *                          
 * @Preconditions:1. vehicle product
 *                2. registration vehicle product
 * @SPEC:Use Case Specification: Search Vehicle Order
 *       Search Vehicle Order [TC:004994]
 *       Search Vehicle Order [TC:038401]
 * @Task#:AUTO-1000
 * 
 * @author szhou
 * @Date May 24, 2012
 */
public class SearchVehicleOrderUsingSingleCondition extends
		LicenseManagerTestCase {
	private LicMgrVehicleOrderSearchPage vehicleOrderSearchPg = LicMgrVehicleOrderSearchPage
			.getInstance();
	private OrderInfo search = new OrderInfo();
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		/* data prepared */
		if (!lm.verifyProductExistInSys(schema, vehicleRTI.getPrdCode(), vehicleRTI.getPrdName())) {
			// add vehicle product
			lm.gotoVehicleSearchListPageFromTopMenu();
			lm.addVehicleProduct(vehicleRTI);
			// add price
			ILicMgrProductPricingPage pricingPage = lm
					.gotoProductPricingPageFromListPage(pricing.prodType,
							pricing.prodCode);
			lm.addPricingForProduct(pricing, pricingPage);
			// assign location
			lm.gotoVehicleProductDetailsPage(vehicleRTI.getPrdCode());
			lm.assignVehicleToStoresThruLocationClass("06-State Parks Agent");
			lm.gotoHomePage();
		}
		lm.switchLocationInHomePage("HF HQ Role - Auto-WAL-MART");
		/*if (!lm.verifyOrderExistInSys(schema, vehicleRTI.getPrdName(),
				TRANTYPE_REGISTER_VEHICLE)) {*/
			// registration vehicle
			lm.registerVehicleToOrderCart(cust, vehicle);
			lm.processOrderToOrderSummary(pay);
			this.setVehicleInfoFromUI();
			lm.finishOrder();
	/*	} else {
			this.setVehicleOrderInfoFromDB(schema, vehicleRTI.getPrdName());
		}*/

		/* search order */
		// go to order detail page
		lm.gotoVehicleOrderPgFromTop();
		// search vehicle order by order number
		search.searchType = "Order #";
		search.searchValue = search.orderNum;
		lm.searchVehicleOrder(search);
		vehicleOrderSearchPg.verifySearchResultsForCol(search.searchValue,
				"Order # / TAN");
		vehicleOrderSearchPg.verifyOrderInSearchList(search.orderNum);

		// search vehicle order by receipt number
		search.searchType = "Receipt #";
		search.searchValue = search.receiptNum;
		lm.searchVehicleOrder(search);
		vehicleOrderSearchPg.verifySearchResultsForCol(search.searchValue,
				"Receipt #");
		vehicleOrderSearchPg.verifyOrderInSearchList(search.orderNum);

		// search vehicle order by order date
		search.cleanupVehicleOrderSearchCriteria();
		search.orderFromDate = search.orderDate;
		search.orderToDate = search.orderFromDate;
		lm.searchVehicleOrder(search);
		vehicleOrderSearchPg.verifySearchResultsForCol(DateFunctions
//				.formatDate(search.orderFromDate, "EEE MMM dd yyyy"),
				.formatDate(search.orderFromDate, "EEE MMM d yyyy"),
				"Order Date");
		vehicleOrderSearchPg.verifyOrderInSearchList(search.orderNum);

		// search vehicle order by sale location - combine with Order Date to reduce the order records
		//search.cleanupVehicleOrderSearchCriteria();
		search.saleLocation = "WAL-MART";
		lm.searchVehicleOrder(search);
		vehicleOrderSearchPg.verifySearchResultsForCol(search.saleLocation,
				"Sales Location (Agent ID)");
        vehicleOrderSearchPg.verifyOrderInSearchList(search.orderNum);// defect

		// search vehicle order by vehicle number
		search.cleanupVehicleOrderSearchCriteria();
		search.miNum = vehicle.registration.miNum;
		lm.searchVehicleOrder(search);
		vehicleOrderSearchPg.verifySearchResultsForCol(search.miNum, "Vehicle");
		vehicleOrderSearchPg.verifyOrderInSearchList(search.orderNum);

		// search vehicle order by Hull ID
		search.cleanupVehicleOrderSearchCriteria();
		search.hullIdSerialNum = vehicle.hullIdSerialNum;
		lm.searchVehicleOrder(search);
		vehicleOrderSearchPg.verifyOrderInSearchList(search.orderNum);

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		/* add vehicle */
		vehicleRTI.setPrdCode("SO1");
		vehicleRTI.setPrdName("Search Order Using Single");
		vehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		vehicleRTI.setPrdGroup("Registration");
		vehicleRTI.setVehicleType("Boat");
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put(OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS, true);
		vehicleRTI.setCustClass(map);
		vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		vehicleRTI.setValidMonths("2");
		vehicleRTI.setAdvanceRenewalDays("1");
		vehicleRTI.setLateRenewal("1");
		vehicleRTI.setLateRenewUnit("Day");
		Map<String, Boolean> map1 = new HashMap<String, Boolean>();
		map1.put("Personal Pleasure", true);
		vehicleRTI.setBoatUseTyp(map1);
		vehicleRTI.setMinLenthOfFt("7");
		vehicleRTI.setMinLenthOfIn("5");
		vehicleRTI.setMaxLenthOfFt("20");
		vehicleRTI.setMaxLenthOfIn("14");
		
		/* register vehicle */
		vehicle.hullIdSerialNum = "search" + DateFunctions.getCurrentTime();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "1997";
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "PLEASURE";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = vehicleRTI.getPrdCode() + " - " + vehicleRTI.getPrdName();
		search.orderDate = DateFunctions.getToday();

		pricing.prodType = "Vehicle";
		pricing.prodCode = vehicleRTI.getPrdCode();
		pricing.status = "Active";
		pricing.locationClass = "All";
		pricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		pricing.licYearTo = String.valueOf(DateFunctions.getCurrentYear() + 1);
		pricing.purchaseType = "Original";
		pricing.effectFrom = DateFunctions.getToday();
		pricing.effectTo = DateFunctions.getDateAfterToday(365);
		pricing.vendorFee = "20.00";
		pricing.stateTransFee = "15.00";
		pricing.stateFee = "10.00";
		pricing.transFee = "3.00";

		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000017";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 08 1988";
		cust.lName = "TEST-Basic17";
		cust.fName = "QA-Basic17";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

	}

	private void setVehicleInfoFromUI(){
		OrmsOrderSummaryPage ordSummaryPg = OrmsOrderSummaryPage
		.getInstance();
		vehicle.registration.miNum = ordSummaryPg.getMINum();
		search.receiptNum = ordSummaryPg.getReceiptNum();
		search.orderNum = ordSummaryPg.getAllOrdNums();
	}
	
	private void setVehicleOrderInfoFromDB(String schema, String prd) {
		String[] info = lm.getRegisterVehicleOrderInfoUsingPrdname(schema, prd).get(0);

		search.orderNum = info[0];
		search.receiptNum = info[1];
		search.orderDate = info[2].split(" ")[0];
		vehicle.registration.miNum = info[3];
		vehicle.hullIdSerialNum = info[4];
	}
}
