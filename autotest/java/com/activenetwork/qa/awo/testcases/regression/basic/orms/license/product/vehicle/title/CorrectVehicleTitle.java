/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.title;

import java.util.HashMap;
import java.util.Map;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderTransactionInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PricingInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDetailPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleTitleListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Correct vehicle title work flow
 *              check point:1. correct vehicle title work flow
 *                          2. verify transaction type on the order cart
 *                          3. verify transaction information in the database
 *                          4. verify vehicle instance information in the database
 * @Preconditions:vehicle product for register and title
 * @SPEC:Correct Vehicle Title 
 * @Task#:AUTO-1010 
 * 
 * @author szhou
 * @Date Jun 18, 2012
 */
public class CorrectVehicleTitle extends LicenseManagerTestCase {
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage
			.getInstance();
	private String correctReason, locationClass, switchLocation;
	private VehicleRTI titleVehicleRTI = new VehicleRTI();
	private PricingInfo correctPricing = new PricingInfo();
	private VehicleRTI vehicleRTI = new VehicleRTI();
	BoatInfo vehicle = new BoatInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		/* data prepared */
		if (!lm.verifyProductExistInSys(schema, vehicleRTI.getPrdCode(), vehicleRTI.getPrdName())) {
			// add vehicle product
			this.addVehicleProduct(vehicleRTI, locationClass, pricing);
		}
		if (!lm.verifyProductExistInSys(schema, titleVehicleRTI.getPrdCode(),
				titleVehicleRTI.getPrdName())) {
			// add vehicle product for title
			pricing.prodCode = titleVehicleRTI.getPrdCode();
			this.addVehicleProduct(titleVehicleRTI, locationClass, pricing,
					correctPricing);
		}

		lm.switchLocationInHomePage(switchLocation);
		// registration vehicle
		lm.registerVehicleToOrderCart(cust, vehicle);
		lm.processOrderToOrderSummary(pay);
		vehicle.registration.miNum = lmOrdSumPg.getVehicleMINum();
		lm.finishOrder();

		// title vehicle
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.titleVehicleToOrderCartFromVehicleDetailPage(vehicle);
		lm.processOrderToOrderSummary(pay);
		lm.finishOrder();

		// correct title vehicle
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.correctTitileVehicleToOrderCartFromRegistrationDetailPg(
				vehicle, correctReason);
		// verify transaction type in the order cart page
		this.verifyTransTypeAndOrderType();
		String orderNum = lm.processOrderCart(pay).split(" ")[0];

		// verify vehicle title instance has new record for correct title
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		OrderTransactionInfo transinfo = this.setTransactionInfo(orderNum);
		this.setVehicleInstInfo(vehicle.title.product);
		this.verifyTransactionInfoInDB(transinfo, schema, orderNum,
				TRANTYPE_CORRECT_VEHICLE_TITLE);
		this.verifyVehicleInstInfoInDB(vehicle, schema, orderNum);

		lm.logOutLicenseManager();
	}
	
	private void verifyTransTypeAndOrderType(){
		OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
		String transaction=ormsOrderCartPg.getFirstTransactionName().split("\\(")[0].trim();
		if(!TRANNAME_CORRECT_VEHICLE_TITLE.equals(transaction)){
			throw new ErrorOnDataException("transaction name or order type is not correct.",TRANNAME_CORRECT_VEHICLE_TITLE,transaction);
		}
	}

	private void setVehicleInstInfo(String product){
		LicMgrVehicleDetailPage vehicleDetailsPg = LicMgrVehicleDetailPage.getInstance();
		LicMgrVehicleTitleListPage titlePg=LicMgrVehicleTitleListPage.getInstance();
		
		/*select active status title*/
		vehicleDetailsPg.clickTitlesTab();
		ajax.waitLoading();
		titlePg.waitLoading();
		titlePg.unselectTransferableStatusCheckBox();
		titlePg.unselectSurrenderedStatusCheckBox();
		titlePg.selectActiveStautsCheckBox();
		titlePg.clickGobutton();
		ajax.waitLoading();
		
		vehicle.title.titleId=titlePg.getTitleItemIdByPrdName(product);
		vehicle.title.titleNum=titlePg.getTitleItemNumByPrdName(product);
		vehicle.title.status=titlePg.getTitleItemStatusByPrdName(product);
		vehicle.title.numOfCorrections=titlePg.getTitleItemCorrectCountsByPrdName(product);
		
	}

	private OrderTransactionInfo setTransactionInfo(String ordernum) {

		OrderTransactionInfo transinfo = new OrderTransactionInfo();

		transinfo.setOrderID(ordernum);
		transinfo.setTransactionType(TRANTYPE_CORRECT_VEHICLE_TITLE);
		transinfo.setTransactionDate(DateFunctions.getToday());
		transinfo.setSalesChannel(SALESCHAN_FIELD);
		transinfo.setLocation("WAL-MART");
	
		return transinfo;
	}

	private void verifyVehicleInstInfoInDB(Vehicle vehicle,
			String schema, String ordnum) {
		boolean result = true;
		String[] info = lm.getOrderVehicleInstInfo(schema, ordnum);

		result &= MiscFunctions.compareResult("product name", vehicle.title.product.split("-")[1].trim(), info[7]);
		result &= MiscFunctions.compareResult("Title Number", vehicle.title.titleNum, info[1]);
		result &= MiscFunctions.compareResult("Title ID",
				vehicle.title.titleId, info[0]);
		result &= MiscFunctions.compareResult("Status", String.valueOf(ACTIVE), info[6]);
		result &= MiscFunctions.compareResult("Correct counts ", vehicle.title.numOfCorrections, info[4]);

		if (!result) {
			throw new ErrorOnPageException(
					"Vehicle Instance info is not correct.Please check log file...");
		}
	}

	private void verifyTransactionInfoInDB(OrderTransactionInfo transinfo,
			String schema, String ordnum, String transID) {
		boolean result = true;
		String[] info = lm.getOrderTransInfoByOrderItemAndTransType(schema,
				ordnum, transID);

		result &= MiscFunctions.compareResult("Order Number ", info[0],
				transinfo.getOrderID());
		result &= MiscFunctions.compareResult("Transaction Type ", info[9],
				transinfo.getTransactionType());
		result &= MiscFunctions.compareResult("Transaction Date ",
				DateFunctions.formatDate(info[2].split(" ")[0], "MM/dd/yyyy"),
				transinfo.getTransactionDate());
		result &= MiscFunctions.compareResult("Sales Channel ", info[5],
				transinfo.getSalesChannel());
		result &= MiscFunctions.compareResult("Location ", info[3], transinfo
				.getLocation());

		if (!result) {
			throw new ErrorOnPageException(
					"Order transaction info is not correct.Please check log file...");
		}
	}

	private void addVehicleProduct(VehicleRTI vrti,
			String locationClass, PricingInfo... prices) {
		logger.info("Add vehicle product : " + vrti.getPrdName()
				+ " for data prepared...");

		// add vehicle product
		lm.gotoVehicleSearchListPageFromTopMenu();
		lm.addVehicleProduct(vrti);

		// add price
		ILicMgrProductPricingPage pricingPage = lm
				.gotoProductPricingPageFromListPage(prices[0].prodType,
						vrti.getPrdCode());
		for (int i = 0; i < prices.length; i++) {
			lm.addPricingForProduct(prices[i], pricingPage);
		}

		// assign location
		lm.gotoVehicleProductDetailsPage(vrti.getPrdCode());
		lm.assignVehicleToStoresThruLocationClass(locationClass);
		lm.gotoHomePage();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		switchLocation = "HF HQ Role - Auto-WAL-MART";

		/* add vehicle */
		locationClass = "06-State Parks Agent";
		
		vehicleRTI.setPrdCode("CVR");
		vehicleRTI.setPrdName("CorrectVehicleTitleR");
		vehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		vehicleRTI.setPrdGroup("Registration");
		vehicleRTI.setVehicleType("Boat");
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("Individual", true);
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

		titleVehicleRTI.setPrdCode("CVT");
		titleVehicleRTI.setPrdName("CorrectVehicleTitleT");
		titleVehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		titleVehicleRTI.setPrdGroup("Title");
		titleVehicleRTI.setVehicleType("Boat");
		Map<String, Boolean> map2 = new HashMap<String, Boolean>();
		map2.put("Individual", true);
		titleVehicleRTI.setCustClass(map2);
		titleVehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		titleVehicleRTI.setValidMonths("2");
		titleVehicleRTI.setAdvanceRenewalDays("1");
		titleVehicleRTI.setLateRenewal("1");
		titleVehicleRTI.setLateRenewUnit("Day");
		Map<String, Boolean> map3 = new HashMap<String, Boolean>();
		map3.put("Personal Pleasure", true);
		titleVehicleRTI.setBoatUseTyp(map3);
		titleVehicleRTI.setMinLenthOfFt("7");
		titleVehicleRTI.setMinLenthOfIn("5");
		titleVehicleRTI.setMaxLenthOfFt("20");
		titleVehicleRTI.setMaxLenthOfIn("14");
		
		/* register vehicle */
		vehicle.hullIdSerialNum = "correct" + DateFunctions.getCurrentTime();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "1997";
		vehicle.yearBuilt = String.valueOf(DateFunctions.getCurrentYear());
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "PLEASURE";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = vehicleRTI.getPrdCode() + " - " + vehicleRTI.getPrdName();

		/* title vehicle */
		vehicle.title.product = titleVehicleRTI.getPrdCode() + " - " + titleVehicleRTI.getPrdName();
		vehicle.title.purchaseType = "Original";
		vehicle.title.boatValue = "45";

		/* correct vehicle */
		correctReason = "Other";

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

		correctPricing.prodType = "Vehicle";
		correctPricing.prodCode = titleVehicleRTI.getPrdCode();
		correctPricing.status = "Active";
		correctPricing.locationClass = "All";
		correctPricing.licYearFrom = String.valueOf(DateFunctions
				.getCurrentYear());
		correctPricing.licYearTo = String.valueOf(DateFunctions
				.getCurrentYear() + 1);
		correctPricing.purchaseType = "Corrected";
		correctPricing.effectFrom = DateFunctions.getToday();
		correctPricing.effectTo = DateFunctions.getDateAfterToday(365);
		correctPricing.vendorFee = "10.00";
		correctPricing.stateTransFee = "5.00";
		correctPricing.stateFee = "3.00";
		correctPricing.transFee = "1.00";

		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic00006";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 08 1988";
		cust.lName = "TEST-Basic7";
		cust.fName = "QA-Basic7";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

	}

}
