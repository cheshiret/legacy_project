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
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleTitleListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Reactive vehicle title work flow
 *              check point:1. reactive vehicle title from surrendered status work flow
 *                          2. verify vehicle title status on the vehicle detail page
 *                          3. verify transaction information in the database
 *                          4. verify vehicle instance information in the database
 * @Preconditions:vehicle product for register and title
 * @SPEC:Reactivate Vehicle Title 
 * @Task#:AUTO-1010 
 * 
 * @author szhou
 * @Date Jun 18, 2012
 */
public class ReactiveVehicleTitle_FromSurrendered extends
		LicenseManagerTestCase {
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage
			.getInstance();
	private VehicleRTI regVehicleRTI = new VehicleRTI();
	private VehicleRTI titleVehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	private String locationClass, switchLocation;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		/* data prepared */
		if (!lm.verifyProductExistInSys(schema, regVehicleRTI.getPrdCode(), regVehicleRTI.getPrdName())) {
			// add vehicle product
			this.addVehicleProduct(regVehicleRTI, locationClass, pricing);
		}
		if (!lm.verifyProductExistInSys(schema, titleVehicleRTI.getPrdCode(),
				titleVehicleRTI.getPrdName())) {
			// add vehicle product for title
			pricing.prodCode = titleVehicleRTI.getPrdCode();
			this.addVehicleProduct(titleVehicleRTI, locationClass, pricing);
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
		String orderNum = lm.processOrderCart(pay).split(" ")[0];

		// surrender title
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.surrenderTitleFromTitleList(vehicle.title.product);

		// reactive title
		lm.reactiveTitleByPrd(vehicle.title.product);
		// verify vehicle title status in the vehicle detail page
		this.verifyStatusForVehicle(ACTIVE_STATUS,
				vehicle.title.product);
		// verify vehicle title instance has new record for reactive title
		OrderTransactionInfo transinfo = this.setTransactionInfo(orderNum);
		this.verifyTransactionInfoInDB(transinfo, schema, orderNum,
				TRANTYPE_REACTIVATE_VEHICLE_TITLE);
		this.verifyVehicleInstInfoInDB(vehicle, schema, orderNum);

		lm.logOutLicenseManager();

	}

	private void verifyStatusForVehicle(String status, String product) {
		LicMgrVehicleTitleListPage titlePg = LicMgrVehicleTitleListPage.getInstance();

		/* select active status title */
		titlePg.unselectTransferableStatusCheckBox();
		titlePg.unselectSurrenderedStatusCheckBox();
		titlePg.selectActiveStautsCheckBox();
		titlePg.clickGobutton();
		ajax.waitLoading();

		vehicle.title.titleId = titlePg
				.getTitleItemIdByPrdName(product);
		vehicle.title.titleNum = titlePg
				.getTitleItemNumByPrdName(product);
		vehicle.title.status = titlePg
				.getTitleItemStatusByPrdName(product);
		if (!status.equals(vehicle.title.status)) {
			throw new ErrorOnPageException("Status is not correct...", status,
					vehicle.title.status);
		}

	}

	private OrderTransactionInfo setTransactionInfo(String ordernum) {

		OrderTransactionInfo transinfo = new OrderTransactionInfo();

		transinfo.setOrderID(ordernum);
		transinfo.setTransactionType(TRANTYPE_REACTIVATE_VEHICLE_TITLE);
		transinfo.setTransactionDate(DateFunctions.getToday());
		transinfo.setSalesChannel(SALESCHAN_FIELD);
		transinfo.setLocation("WAL-MART");

		return transinfo;
	}

	private void verifyVehicleInstInfoInDB(Vehicle v,
			String schema, String ordnum) {
		boolean result = true;
		String[] info = lm.getOrderVehicleInstInfo(schema, ordnum);

		result &= MiscFunctions.compareResult("product name", v.title.product.split("-")[1].trim(), info[7]);
		result &= MiscFunctions.compareResult("Title Number", v.title.titleNum, info[1]);
		result &= MiscFunctions.compareResult("Title ID",
				v.title.titleId, info[0]);
		result &= MiscFunctions.compareResult("Status", String.valueOf(ACTIVE), info[6]);

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

		regVehicleRTI.setPrdCode("RRV");
		regVehicleRTI.setPrdName("ReactiveVehicleTitleRS");
		regVehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		regVehicleRTI.setPrdGroup("Registration");
		regVehicleRTI.setVehicleType("Boat");
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("Individual", true);
		regVehicleRTI.setCustClass(map);
		regVehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		regVehicleRTI.setValidMonths("2");
		regVehicleRTI.setAdvanceRenewalDays("1");
		regVehicleRTI.setLateRenewal("1");
		regVehicleRTI.setLateRenewUnit("Day");
		Map<String, Boolean> map1 = new HashMap<String, Boolean>();
		map1.put("Personal Pleasure", true);
		regVehicleRTI.setBoatUseTyp(map1);
		regVehicleRTI.setMinLenthOfFt("7");
		regVehicleRTI.setMinLenthOfIn("5");
		regVehicleRTI.setMaxLenthOfFt("20");
		regVehicleRTI.setMaxLenthOfIn("14");

		titleVehicleRTI.setPrdCode("RTV");
		titleVehicleRTI.setPrdName("ReactiveVehicleTitleTS");
		titleVehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		titleVehicleRTI.setPrdGroup("Title");
		titleVehicleRTI.setVehicleType("Boat");
		titleVehicleRTI.setCustClass(map);
		titleVehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		titleVehicleRTI.setValidMonths("2");
		titleVehicleRTI.setAdvanceRenewalDays("1");
		titleVehicleRTI.setLateRenewal("1");
		titleVehicleRTI.setLateRenewUnit("Day");
		titleVehicleRTI.setBoatUseTyp(map1);
		titleVehicleRTI.setMinLenthOfFt("7");
		titleVehicleRTI.setMinLenthOfIn("5");
		titleVehicleRTI.setMaxLenthOfFt("20");
		titleVehicleRTI.setMaxLenthOfIn("14");

		/* register vehicle */
		vehicle.hullIdSerialNum = "reactive" + DateFunctions.getCurrentTime();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "1997";
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "PLEASURE";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = regVehicleRTI.getPrdCode() + " - " + regVehicleRTI.getPrdName();

		/* title vehicle */
		vehicle.title.product = titleVehicleRTI.getPrdCode() + " - " + titleVehicleRTI.getPrdName();
		vehicle.title.purchaseType = "Original";
		vehicle.title.boatValue = "45";
		
		pricing.prodType = "Vehicle";
		pricing.prodCode = regVehicleRTI.getPrdCode();
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
		cust.identifier.identifierNum = "AutoBasic000022";
		cust.dateOfBirth = "Jun 12 1988";
		cust.lName = "TEST-Basic22";
		cust.fName = "QA-Basic22";
		cust.customerClass = OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS;
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}
}
