/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration.renewal;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrRegistrationDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleRegistrationWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: It is used to verify the new Vehicle order is added to the cart correctly
 *  when renew a vehicle registration.
 * @Preconditions:
 * 1. Make sure the customer "QA-Refund TEST-Refund" exists.
 * 2. Make sure the vehicle product "Rew - RenewVehReg" exists and its has the original and renewal prices.
 * @SPEC: <Get Vehicle Product List for Renewal> and <Renewal Vehicle Registration>
 * @Task#: Auto-995
 * 
 * @author Lesley Wang
 * @Date  Jun 12, 2012
 */
public class Renew_AddOrder extends LicenseManagerTestCase {
	private LicMgrRegistrationDetailsPage regisDetailPg = LicMgrRegistrationDetailsPage.getInstance();
	private OrmsOrderCartPage lmOrderCartPg = OrmsOrderCartPage.getInstance();
	private LicMgrVehicleRegistrationWidget regWidget = LicMgrVehicleRegistrationWidget.getInstance();
	private String reason;
	private String comment;
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	
	@Override
	public void execute() {
		// Register a vehicle product
		lm.loginLicenseManager(login);
		lm.registerVehicleToOrderCart(cust, vehicle);
		String ordNum1 = lm.processOrderCart(pay, false).split(" ")[0];
		
		// Search the vehicle and go to Vehicle Details page
		lm.gotoVehiclesDetailsPgBySerialNum(vehicle.hullIdSerialNum);
		
		// Get the Vehicle product price for renewal and add it to order cart page
		lm.gotoVehRegisProdWidgetFromVehDetaildPg();
		vehicle.registration.creationPrice = regWidget.getRegisProdPrice(vehicle.registration.product, vehicle.registration.purchaseType);
		lm.selectVehRegProdToOrderCartPg(vehicle.registration.purchaseType, vehicle.registration.product);
		
		// verify the order transaction type
		boolean result = true;
		result &= lmOrderCartPg.verifyTransactionName("Renew Registration");
		
		// Finish processing the order cart
		String ordNum2 = lm.processOrderCart(pay, false).split(" ")[0];
		
		// View the renewal registration details and verify the product name, valid dates and creation price
		vehicle.registration.searchValue = ordNum2;
		lm.gotoRegisDetailsPgFromHomePg(vehicle.registration);
		
		// Verify product name on Registration Details page
		result &= regisDetailPg.verifyRenewRegProductName(vehicle.registration.product);
		
		// Get the creation price from DB and verify price on Registration Details page
		result &= regisDetailPg.verifyRenewRegPrice(vehicle.registration.creationPrice);
		
		// Get the valid dates and verify valid dates on Registration Details page
		result &= this.verifyValidDates();
		
		if (!result) {
			throw new ErrorOnPageException("The renew registration is not correct! Please check the error logger!");
		}
		
		// Clean Up
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(ordNum2, reason, comment);
		lm.processOrderCart(pay);
		lm.voidRegisterVehicleOrder(ordNum1, reason, comment);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		cust.lName = "TEST-TransferRule11";
		cust.fName = "QA-TransferRule11";
		cust.residencyStatus = "Non Resident";
		
		vehicleRTI.setPrdCode("Rew");
		vehicleRTI.setPrdName("RenewVehReg");
//		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "VehOrd"+Integer.toString((int)(Math.random()*100000))+"T"+Integer.toString((int)(Math.random()*100000)); //random value hull ID
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = Integer.toString(DateFunctions.getCurrentYear());
		vehicle.feet = "2";  //this value should be in the range of the related record in 'AddVehicleProduct.datapool'
		vehicle.inches = "0";//this value should be in the range of the related record in 'AddVehicleProduct.datapool'
		vehicle.hullMaterial = "Other";
		vehicle.boatUse = "Other";//this value should be equal to the related record in 'AddVehicleProduct.datapool'
		vehicle.propulsion = "Other";
		vehicle.fuelType = "Other";
		vehicle.typeOfBoat = "Other";
		
		vehicle.registration.product = vehicleRTI.getPrdCode() + " - " + vehicleRTI.getPrdName();
		vehicle.registration.purchaseType = "Renewal";
		List<String> dates = lm.registerVehicleValidDateCalc(vehicleRTI.getPrdCode(), schema);
		vehicle.registration.validFromDate = dates.get(0);
		vehicle.registration.validToDate = dates.get(1);
		vehicle.registration.searchType = "Order #";
		
		reason = "14 - Other";
		comment = "Auto Test";
	}
	
	/**
	 * Verify valid dates in Registration Details page
	 * @return
	 */
	private boolean verifyValidDates() {
		String format = "EEE MMM d yyyy";
		String newValidFromDate = DateFunctions.calculateDate(vehicle.registration.validToDate, 0, 0, 1, format);
		String newValidToDate = DateFunctions.calculateDate(vehicle.registration.validToDate, 0, 12, 0, format);
		newValidToDate = DateFunctions.getLastDateOfMonth(newValidToDate, format);
		
		return regisDetailPg.verifyRenewRegValidFromDates(newValidFromDate) && 
			regisDetailPg.verifyRenewRegValidToDates(newValidToDate);
	}
}
