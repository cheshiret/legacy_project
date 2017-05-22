package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.order;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Registration from license manager, verify order cart info and order summary info 
 * @Preconditions: 
 * 				 1. A Vehicle registration product: tta - advTAN
 *               2. this vehicle product should have original fee, duplicate fee, renewal fee
 *               3. this product should be assign to WAL-MART agent
 * @SPEC:  Order Cart - Vehicle.UI and Order Summary - Vehicle.UI
 * @Task#: AUTO-1001
 * 
 * @author vzhang
 * @Date  Jun 6, 2012
 * 
 */
public class OrderCartAndSummary_Registration extends LicenseManagerTestCase{
	private OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private String productItemInfo;
	private String vehicleInfo,licenseYear;
	private BoatInfo vehicle = new BoatInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);	
		//registration of vehicle order
		lm.registerVehicleToOrderCart(cust, vehicle);
		cust.licenseNum = lm.getCustomerNum(cust, schema);
		//verify order cart info
		this.verifyItemInfoInOrderCartPg();
		
		//verify order summary info
		lm.processOrderCartToOrderSummaryPage(pay, false);
		this.verifyItemInfoInOrderSummaryPg();
		
		lm.finishOrder();
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		cust.fName = "QA-VehicleOrdCart";
		cust.lName = "Test-VehicleOrdCart";
		cust.dateOfBirth = "Jan 01 1980";
		cust.licenseType = "MDWFP #";
		cust.mailingAddr.address = "aac Street";
		cust.mailingAddr.supplementalAddr = "Auto test";
		cust.mailingAddr.zip = "12020";
		cust.mailingAddr.city = "Ballston Spa";
		cust.mailingAddr.state = "New York";
		
		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "OrdCartR"+DataBaseFunctions.getEmailSequence();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = String.valueOf(DateFunctions.getCurrentYear());
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "PLEASURE";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = "tta - advTAN";
		
		licenseYear = lm.getFiscalYear(schema);
		productItemInfo = "(" + licenseYear + ")" + vehicle.registration.product.replace(" - ", "-") + "(Original)";
		vehicleInfo = vehicle.type  + " " + vehicle.hullIdSerialNum;
	}
	
	private void verifyItemInfoInOrderCartPg(){
		logger.info("Verify order items info in order cart page.");
		String expValue;
		boolean result = true;		
		
		//verify transaction name
		//expValue = "Register Vehicle";
		expValue = "Registration";
		result &= ormsOrderCartPg.verifyTransactionName(expValue);
		
		//verify order item info
		result &= ormsOrderCartPg.verifyProductItemInfo(productItemInfo);
		
		//verify customer info
		result &= ormsOrderCartPg.verifyCustomerInfo(cust, null);
		
		//verify vehicle info about vehicle type and hullIdSerialNum
		expValue = vehicle.type  + " " + vehicle.hullIdSerialNum;
		result &= ormsOrderCartPg.checkCustInfoOrVehiclInfo(vehicleInfo);		
		
		if(!result){
			throw new ErrorOnDataException("UI displayed info is not correct at order cart page, please check error log.");
		}
	}
	
	private void verifyItemInfoInOrderSummaryPg(){
		logger.info("Verify order items info in order summary page.");
		String expValue;
		boolean result = true;
		
		//verify transaction name
		//expValue = "Register Vehicle, Make Payment";
		expValue = "Registration, Make Payment";
		result &= lmOrdSumPg.verifyTransactionInfo(expValue);
		
		//verify order item info
		result &= lmOrdSumPg.verifyProductItemInfo(productItemInfo);
		
		//verify customer info
		result &= lmOrdSumPg.verifyCustomerInfo(cust, null);
		
		//verify vehicle info about vehicle type and hullIdSerialNum
		result &= lmOrdSumPg.checkCustInfoOrVehiclInfo(vehicleInfo);		
		
		if(!result){
			throw new ErrorOnDataException("UI displayed info is not correct at order summary page, please check error log.");
		}
	}
}
