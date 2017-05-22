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
 * @Description: Reverse Title vehicle from license manager, verify order cart info and order summary info 
 * @Preconditions: 
 * 				 1. A Vehicle registration product: tta - advTAN
 *               2. A vehicle title product: OCS - TitleForOrdCartAndSummay
 *               3. this vehicle registration product should have original fee, duplicate fee, renewal fee
 *               4. this title vehicle product should have original fee
 *               5. these product should be assign to WAL-MART agent
 * @SPEC:  Order Cart - Vehicle.UI and Order Summary - Vehicle.UI
 * @Task#: AUTO-1001
 * 
 * @author vzhang
 * @Date  Jun 6, 2012
 * 
 */
public class OrderCartAndSummary_ReverseTitle extends LicenseManagerTestCase{
	private String reserveRea = "";
	private String reserveNote = "";
	private String orderNum = "";
	private BoatInfo vehicle = new BoatInfo();
	private OrmsOrderSummaryPage lmOrdSumPg = OrmsOrderSummaryPage.getInstance();
	private OrmsOrderCartPage ormsOrderCartPg = OrmsOrderCartPage.getInstance();
	private String productItemInfo,vehicleInfo,vehicleMiInfo,reverseMarkInfo, titleNumInfo,licenseYear;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);	
		//registration of vehicle order
		lm.registerVehicleToOrderCart(cust, vehicle);
		
		lm.processOrderCartToOrderSummaryPage(pay, false);
		vehicle.registration.miNum = lmOrdSumPg.getVehicleMINum();			
		lm.finishOrder();
		
		//title vehicle
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.titleVehicleToOrderCartFromVehicleDetailPage(vehicle);
		orderNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		vehicle.title.titleNum = lmOrdSumPg.getTitleNum();
		lm.finishOrder();
		
		//reverse title order
		lm.gotoVehicleOrderDetailPage(orderNum);
		lm.reverseVehicleOrderToOrderCartFromVehicleDetailPg(reserveRea,reserveNote);
		
		vehicleMiInfo = "MI #:" + vehicle.registration.miNum;
		titleNumInfo = "Title #:" + vehicle.title.titleNum;
		cust.licenseNum = lm.getCustomerNum(cust, schema);
		this.verifyItemInfoInOrderCartPg();
		
		//verify info from order summary page
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
		vehicle.hullIdSerialNum = "OrdCartRT"+DataBaseFunctions.getEmailSequence();
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
		vehicle.title.product = "OCS - TitleForOrdCartAndSummay";
		vehicle.title.purchaseType = "Original";
		//vehicle.vehicleValue = "45";	
		vehicle.title.boatValue = "45";
		
		licenseYear = lm.getFiscalYear(schema);
		productItemInfo = "(" + licenseYear+ ")" + vehicle.title.product.replace(" - ", "-") + "(Original)";
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
		
		//verify vehicle title number info
		result &= ormsOrderCartPg.checkCustInfoOrVehiclInfo(titleNumInfo);	
		
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
		
		//verify vehicle im number
		result &= lmOrdSumPg.checkCustInfoOrVehiclInfo(vehicleMiInfo);	
		
		//verify vehicle title number info
		result &= lmOrdSumPg.checkCustInfoOrVehiclInfo(titleNumInfo);	
		
		if(!result){
			throw new ErrorOnDataException("UI displayed info is not correct at order summary page, please check error log.");
		}
	}

}
