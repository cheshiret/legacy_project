package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.order;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Transfer Registration from license manager, verify order cart info and order summary info 
 * @Preconditions: 
 * 				 1. A Vehicle registration product: tta - advTAN
 *               2. this vehicle product should have original fee, duplicate fee, renewal fee, transfer fee
 *               3. this product should be assign to WAL-MART agent
 * @SPEC:  Order Cart - Vehicle.UI and Order Summary - Vehicle.UI
 * @Task#: AUTO-1001
 * 
 * @author vzhang
 * @Date  Jun 6, 2012
 * 
 */
public class OrderCartAndSummary_TranRegistration extends LicenseManagerTestCase{
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
	private Customer tranToCust = new Customer();
	private BoatInfo vehicle = new BoatInfo();
	private String productItemInfo,vehicleInfo,vehicleMiInfo,licenseYear;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);	
		//registration of vehicle order
		lm.registerVehicleToOrderCart(cust, vehicle);
		
		lm.processOrderCartToOrderSummaryPage(pay, false);
		vehicle.registration.miNum = lmOrdSumPg.getVehicleMINum();		
		lm.finishOrder();

		//transfer registration vehicle, just transfer customer
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.transferVehicleToOrderCartFromDetailsPage(tranToCust,vehicle);
		
		//verify order cart info
		cust.licenseNum = lm.getCustomerNum(cust, schema);
		tranToCust.licenseNum = tranToCust.identifier.identifierNum;
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
		
		cust.fName = "QA-VclOrdCartSum1";
		cust.lName = "TEST-VclOrdCartSum1";
		cust.dateOfBirth = "Jan 01 1981";
		cust.licenseType = "MDWFP #";
		cust.mailingAddr.address = "aac Street";
		cust.mailingAddr.supplementalAddr = "Auto test";
		cust.mailingAddr.zip = "12020";
		cust.mailingAddr.city = "Ballston Spa";
		cust.mailingAddr.state = "New York";

		vehicle.hullIdSerialNum = "OrdCartTR"+DataBaseFunctions.getEmailSequence();
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
		
		tranToCust.lName = "TEST-Basic1";
		tranToCust.fName = "QA-Basic1";
		tranToCust.mailingAddr.address = "Xian";
		tranToCust.mailingAddr.supplementalAddr = "";
		tranToCust.mailingAddr.zip = "36925";
		tranToCust.mailingAddr.city = "York";
		tranToCust.mailingAddr.state = "Alabama";
		tranToCust.identifier.identifierType = "MDWFP #";
		tranToCust.identifier.identifierNum = lm.getCustomerNumByCustName(tranToCust.lName, tranToCust.fName, schema);
		
		licenseYear = lm.getFiscalYear(schema);
		productItemInfo = "(" + licenseYear + ")" + vehicle.registration.product.replace(" - ", "-") + "(Transfer)";
		vehicleInfo ="Boat " + vehicle.hullIdSerialNum;
	}
	
	private void verifyItemInfoInOrderCartPg(){
		logger.info("Verify order items info in order cart page.");
		String expValue;
		boolean result = true;		
		
		//verify transaction name
		expValue = "Transfer Registration";
		result &= ormsOrderCartPg.verifyTransactionName(expValue);
		
		//verify product item info
		result &= ormsOrderCartPg.verifyProductItemInfo(productItemInfo);
		
		//verify from customer info
		result &= ormsOrderCartPg.verifyCustomerInfo(cust, "From: ");
		//verify to customer info
		result &= ormsOrderCartPg.verifyCustomerInfo(tranToCust, "To: ");
		
		//verify registration vehicle info
		result &= ormsOrderCartPg.checkCustInfoOrVehiclInfo(vehicleInfo);		
		
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
		expValue = "Transfer Registration , Make Payment";
		result &= lmOrdSumPg.verifyTransactionInfo(expValue);
		
		//verify product item info
		result &= lmOrdSumPg.verifyProductItemInfo(productItemInfo);
		
		//verify from customer info
		result &= lmOrdSumPg.verifyCustomerInfo(cust, "From: ");
		//verify to customer info
		result &= lmOrdSumPg.verifyCustomerInfo(tranToCust, "To: ");
		
		//verify registration vehicle info
		result &= lmOrdSumPg.checkCustInfoOrVehiclInfo(vehicleInfo);	
		
		//verify vehicle MI number
		result &= lmOrdSumPg.checkCustInfoOrVehiclInfo(vehicleMiInfo);	
		
		if(!result){
			throw new ErrorOnDataException("UI displayed info is not correct at order summary page, please check error log.");
		}
	}

}
