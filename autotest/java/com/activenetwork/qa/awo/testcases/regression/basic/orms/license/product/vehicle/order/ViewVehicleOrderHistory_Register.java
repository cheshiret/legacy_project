/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.order;

import java.util.HashMap;
import java.util.Map;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:view order history work flow for Registration vehicle
 *              check point:
 *              1.view history for register vehicle reservation
 * @Preconditions:
 * @SPEC:View Vehicle Order History 
 * @Task#:AUTO-1000
 * 
 * @author szhou
 * @Date May 30, 2012
 */
public class ViewVehicleOrderHistory_Register extends LicenseManagerTestCase {
	private OrderInfo order = new OrderInfo();
	private OrderHistory expectHistory = new OrderHistory();
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
		order.orderNum = lm.processOrderCart(pay).split(" ")[0];

		/* go to vehicle order history page */
		lm.gotoVehicleOrderDetailPage(order.orderNum);
		lm.gotoVehicleOrderHistoryPage();

		/* verify vehicle order history */
		this.setHistoryValueForRegister(vehicleRTI.getPrdName());
        this.compareHistoryData(expectHistory);
		lm.logOutLicenseManager();
	}
	
	private void setHistoryValueForRegister(String prd){
		String[] info = lm.getRegisterVehicleOrderInfoUsingPrdname(schema, prd).get(0);
		
		expectHistory.transaction=TRANNAME_REGISTER_VEHICLE;
		expectHistory.infoTransaction ="Registration ID:"+info[5];
		expectHistory.transactionLocation="WAL-MART";
		expectHistory.user=DataBaseFunctions.getLoginUserName(login.userName);
	}

	private void compareHistoryData(OrderHistory history) {
		LicMgrVehicleOrderHistoryPage historyPage = LicMgrVehicleOrderHistoryPage
				.getInstance();
		
		OrderHistory actually=historyPage.getHistoryInfos().get(1);
		boolean result=true;
		result=MiscFunctions.compareResult("Transaction", history.transaction, actually.transaction);
		result&=MiscFunctions.compareResult("Information at time of transaction", history.infoTransaction , actually.infoTransaction );
		result&=MiscFunctions.compareResult("Transaction Location", history.transactionLocation, actually.transactionLocation);
		result&=MiscFunctions.compareResult("User", history.user, actually.user);
		
		if(!result){
			throw new ErrorOnPageException("Data comparing is not correct,please check the log file...");
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		// login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		/* add vehicle */
		vehicleRTI.setPrdCode("VHR");
		vehicleRTI.setPrdName("ViewOrderHistoryR");
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
		cust.identifier.identifierNum = "AutoBasic000019";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 08 1988";
		cust.lName = "TEST-Basic19";
		cust.fName = "QA-Basic19";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";
	}
}
