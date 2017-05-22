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
 * @Description: Reverse Correct vehicle from license manager, verify order cart info and order summary info 
 * @Preconditions: 
 * 				 1. A Vehicle registration product: tta - advTAN
 *               2. A vehicle title product: OCS - TitleForOrdCartAndSummay
 *               3. this vehicle registration product should have original fee, duplicate fee, renewal fee
 *               4. this title vehicle product should have original fee, correct fee
 *               5. these product should be assign to WAL-MART agent
 * @SPEC:  Order Cart - Vehicle.UI and Order Summary - Vehicle.UI
 * @Task#: AUTO-1001
 * 
 * @author vzhang
 * @Date  Jun 6, 2012
 * 
 */
public class OrderCartAndSummary_ReversCorrectTitle extends LicenseManagerTestCase{
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
	private String correctReason,productItemInfo,vehicleInfo,vehicleMiInfo,reverseMarkInfo,licenseYear;
	private String reserveRea = "";
	private String reserveNote = "";
	private String orderNum = "";
	private BoatInfo vehicle = new BoatInfo();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);	
		//registration of vehicle order
		lm.registerVehicleToOrderCart(cust, vehicle);
		
		lm.processOrderCartToOrderSummaryPage(pay, false);
		vehicle.registration.miNum = lmOrdSumPg.getVehicleMINum();		
		lm.finishOrder();
		
		//Title vehicle
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.titleVehicleToOrderCartFromVehicleDetailPage(vehicle);
		lm.processOrderCart(pay, false);
		
		//correct title vehicle
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.correctTitileVehicleToOrderCartFromRegistrationDetailPg(vehicle, correctReason);
		orderNum = lm.processOrderCart(pay, false).split(" ")[0];
		
		//reverse vehicle order
		lm.gotoVehicleOrderDetailPage(orderNum);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(reserveRea,reserveNote);
			
		//verify order cart info
		cust.licenseNum = lm.getCustomerNum(cust, schema);
		vehicleMiInfo = "MI #:" + vehicle.registration.miNum;
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
		vehicle.hullIdSerialNum = "OrdCartT"+DataBaseFunctions.getEmailSequence();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = String.valueOf(DateFunctions.getCurrentYear());
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "OTHER";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		
		vehicle.registration.product = "tta - advTAN";
		vehicle.title.product = "OCS - TitleForOrdCartAndSummay";
		vehicle.title.purchaseType = "Original";
		//vehicle.vehicleValue = "45";	
		vehicle.title.boatValue = "45";
		
		correctReason = "Other";
		reserveRea = "14 - Other";
		reserveNote = "QA Auto Regresssion Test";	
		licenseYear = lm.getFiscalYear(schema);
		productItemInfo = "(" + licenseYear + ")" + vehicle.title.product.replace(" - ", "-") + "(Corrected)";
		vehicleInfo = vehicle.type  + " " + vehicle.hullIdSerialNum;
		reverseMarkInfo = "Transactions to be reversed are marked with *";
	}
	
	private void verifyItemInfoInOrderCartPg(){
		logger.info("Verify order items info in order cart page.");
		String expValue;
		boolean result = true;		
		
		//verify transaction name
		expValue = "Reverse (" + orderNum + ")";
		result &= ormsOrderCartPg.verifyTransactionName(expValue);
		
		//verify order item info
		result &= ormsOrderCartPg.verifyProductItemInfo(productItemInfo);
		
		//verify reverse transfer mark info
		result &= ormsOrderCartPg.verifyReversTransMarkInfo(reverseMarkInfo);
		
		//verify customer info
		result &= ormsOrderCartPg.verifyCustomerInfo(cust, null);
		
		//verify vehicle info about vehicle type and hullIdSerialNum
		result &= ormsOrderCartPg.checkCustInfoOrVehiclInfo(vehicleInfo);	
		
		//verify vehicle MI number
		result &= ormsOrderCartPg.checkCustInfoOrVehiclInfo(vehicleMiInfo);	
		
		if(!result){
			throw new ErrorOnDataException("UI displayed info is not correct at order cart page, please check error log.");
		}
	}
	
	private void verifyItemInfoInOrderSummaryPg(){
		logger.info("Verify order items info in order summary page.");
		String expValue;
		boolean result = true;
		
		//verify transaction name
//		expValue = "Reverse Vehicle, Make Payment";
		expValue = "Reverse";
		result &= lmOrdSumPg.verifyTransactionInfo(expValue);
		
		//verify order item info
		result &= lmOrdSumPg.verifyProductItemInfo(productItemInfo);
		
		//verify customer info
		result &= lmOrdSumPg.verifyCustomerInfo(cust, null);
		
		//verify vehicle info about vehicle type and hullIdSerialNum
		result &= lmOrdSumPg.checkCustInfoOrVehiclInfo(vehicleInfo);	
		
		//verify vehicle MI number
		result &= lmOrdSumPg.checkCustInfoOrVehiclInfo(vehicleMiInfo);	
		
		if(!result){
			throw new ErrorOnDataException("UI displayed info is not correct at order summary page, please check error log.");
		}
	}

}
