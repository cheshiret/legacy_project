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
 * @Description:search vehicle order work flow--using compound condition in the administrator location
 *              check point:1. search vehicle using order number without dash and product group
 *                          2. verify that if using order number as search condition, then other conditions will be ignored
 *                          3. search vehicle using receipt number and purchase type
 *                          4. search vehicle using order date and product code
 *                          5. search vehicle using sale location and vehicle type
 *                          6. search vehicle using vehicle number and vehicle search type
 *                          7. search vehicle using Hull ID and operator name
 *                          
 * @Preconditions:1. vehicle product
 *                2. registration vehicle product
 * @SPEC:Use Case Specification: Search Vehicle Order
 *       Search Vehicle Order [TC:004994]
 *       Search Vehicle Order [TC:038401]
 * @Task#:AUTO-1000
 * 
 * @author szhou
 * @Date May 28, 2012
 */
public class SearchVehicleOrderUsingCompoundCondition extends
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
		// registration vehicle
		lm.registerVehicleToOrderCart(cust, vehicle);
		lm.processOrderToOrderSummary(pay);
		this.setVehicleInfoFromUI();
		lm.finishOrder();
		login.location = "HF Administrator - Auto-Mississippi Department of Wildlife, Fisheries, and Parks";
		lm.switchLocationInHomePage(login.location);
		

		/* search order */
		// go to order detail page
		lm.gotoVehicleOrderPgFromTop();
		// search vehicle order by order number and product group, verify if using order number other condition will be ignored
		search.searchType = "Order #";
		search.searchValue = search.orderNum.replace("-", "");
		search.productGrp = "Title";
		lm.searchVehicleOrder(search);
		vehicleOrderSearchPg.verifyOrderInSearchList(search.orderNum);

		// search vehicle order by receipt number and purchase type
		search.cleanupVehicleOrderSearchCriteria();
		search.searchType = "Receipt #";
		search.searchValue = search.receiptNum;
		search.purchaseType = pricing.purchaseType;
		lm.searchVehicleOrder(search);
		vehicleOrderSearchPg.verifySearchResultsForCol(search.purchaseType,
				"Product/Purchase Type");
		vehicleOrderSearchPg.verifyOrderInSearchList(search.orderNum);

		// search vehicle order by order date and product code
		search.cleanupVehicleOrderSearchCriteria();
		search.orderFromDate = search.orderDate;
		search.orderToDate = search.orderFromDate;
		search.productCode = vehicleRTI.getPrdCode();
		lm.searchVehicleOrder(search);
		vehicleOrderSearchPg.verifySearchResultsForCol(search.productCode,
				"Product/Purchase Type");
		vehicleOrderSearchPg.verifyOrderInSearchList(search.orderNum);

		// search vehicle order by sale location and vehicle type
		search.cleanupVehicleOrderSearchCriteria();
		search.saleLocation = "WAL-MART";
		search.vehicleType = vehicle.type;
		lm.searchVehicleOrder(search);
	    vehicleOrderSearchPg.verifyOrderInSearchList(search.orderNum);//defect

		// search vehicle order by vehicle number and vehicle search type
		search.cleanupVehicleOrderSearchCriteria();
		search.miNum = vehicle.registration.miNum;
		search.vehicleSearchType = "Boat Use";
		search.vehicleSearchValue = vehicle.boatUse;
		lm.searchVehicleOrder(search);
		vehicleOrderSearchPg.verifyOrderInSearchList(search.orderNum);

		// search vehicle order by Hull ID and operator name
		search.cleanupVehicleOrderSearchCriteria();
		search.hullIdSerialNum = vehicle.hullIdSerialNum;
		search.operatorFirstName = "QA-Auto";
		search.operatorLastName = "Test-Auto";
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
		vehicleRTI.setPrdCode("SO2");
		vehicleRTI.setPrdName("SearchOrderUsingCompound");
		vehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		vehicleRTI.setPrdGroup("Registration");
		vehicleRTI.setVehicleType("Boat");
		Map<String, Boolean> map1 = new HashMap<String, Boolean>();
		map1.put(OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS, true);
		vehicleRTI.setCustClass(map1);
		vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		vehicleRTI.setValidMonths("2");
		vehicleRTI.setAdvanceRenewalDays("1");
		vehicleRTI.setLateRenewal("1");
		vehicleRTI.setLateRenewUnit("Day");
		Map<String, Boolean> map2 = new HashMap<String, Boolean>();
		map2.put("Personal Pleasure", true);
		vehicleRTI.setBoatUseTyp(map2);
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
		vehicle.registration.miNum = ordSummaryPg.getMINum().split("MI")[1];
		search.receiptNum = ordSummaryPg.getReceiptNum();
		search.orderNum = ordSummaryPg.getAllOrdNums()/*.replace("-", "")*/.split(" ")[0];
	}
	
	private void setVehicleOrderInfoFromDB(String schema, String prd) {
		String[] info = lm.getRegisterVehicleOrderInfoUsingPrdname(schema, prd).get(0);

		search.orderNum = info[0];
		search.receiptNum = info[1];
		search.orderDate = info[2].split(" ")[0];
		vehicle.registration.miNum = info[3].split("MI")[1];
		vehicle.hullIdSerialNum = info[4];
	}
}
