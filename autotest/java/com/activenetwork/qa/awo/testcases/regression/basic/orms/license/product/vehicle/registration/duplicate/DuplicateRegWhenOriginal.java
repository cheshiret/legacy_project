package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.registration.duplicate;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: Register a vehicle, and then inactivate this customer.
 * Try to duplicate the registration, should display error message.
 * @Preconditions:
 * @SPEC: Duplicate vehicle registration [TC:004988]
 * @Task#: Auto-1790
 * @LinkSetUp:  d_hf_add_cust_profile:id=2410
 * 
 * @author nding1
 * @Date  Dec 4, 2013
 */
public class DuplicateRegWhenOriginal extends LicenseManagerTestCase{
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
    private VehicleRTI vehicleRTI = new VehicleRTI();
    private BoatInfo vehicle = new BoatInfo();
    private  OrmsOrderCartPage orderCartPg = OrmsOrderCartPage.getInstance();
    
	public void execute() {
		lm.loginLicenseManager(login);
		
		// register a vehicle(this order is for duplicate)
		lm.registerVehicleToOrderCart(cust, vehicle);
		lm.processOrderCartToOrderSummaryPage(pay, false);
		vehicle.registration.miNum = lmOrdSumPg.getMINum();
		lm.finishOrder();
		
		// register a vehicle to order cart
		vehicle.hullIdSerialNum = "Original" + DateFunctions.getCurrentTime();
		lm.registerVehicleToOrderCart(cust, vehicle);
		
		// request duplicate registration
		orderCartPg.clickSearch("Vehicles");
		
		// try to duplicate registration
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		vehicle.registration.purchaseType = DUPLICATE_PURCHASE_TYPE;
		lm.duplicateOrRenewalRegistrationVehicleToOrderCartFromRegistrationDetailPg(vehicle.registration.purchaseType, vehicle.registration.product);
		
		// verify transaction type
		List<String> allTransaction = orderCartPg.getAllTransactionTypeName();
		if(!allTransaction.contains("Duplicate Registration")){
			throw new ErrorOnPageException("Should exist one transaction Type which name is Duplicate Registration!");
		}
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = DataBaseFunctions.getSchemaName("MS", env);

		cust.identifier.identifierType = "Tax ID";
		cust.identifier.identifierNum = "87542167";
		cust.lName = "TEST-DupRegVehicle1";
		cust.fName = "QA-LicenseM1";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Sep 01 1979";
		cust.customerClass = "Individual";
		
		vehicleRTI.setPrdCode("DTR");
		vehicleRTI.setPrdName("DuplicateVehicleTitleR");
		vehicle.hullIdSerialNum = "Duplicate" + DateFunctions.getCurrentTime();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "1984";
		vehicle.feet = "15";
		vehicle.inches = "4";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "OTHER";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = vehicleRTI.getPrdCode() + " - " + vehicleRTI.getPrdName();
		vehicle.registration.status = "Active";
		vehicle.operationReason = "14 - Other";
		vehicle.operationNote = "QA Automation";
		vehicle.registration.purchaseType = ORIGINAL_PURCHASE_TYPE;
	}
}