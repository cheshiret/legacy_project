/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.reversetransaction;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderItems;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrRegistrationDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: The case is used to verify the work flow of Reverse Vehicle Transaction with a 'register vehicle' transaction type.
 * @Preconditions:
 * 1. make sure the customer "QA-Refund TEST-Refund" and the vehicle product "tta - advTAN" exist.
 * @SPEC: <Reverse Vehicle Transaction> and <Get Vehicle Transaction List for Reversal>
 * @Task#: Auto-1004
 * 
 * @author Lesley Wang
 * @Date  Jun 18, 2012
 */
public class RegisterVehicle extends LicenseManagerTestCase {

	private OrmsOrderCartPage lmOrderCartPg = OrmsOrderCartPage.getInstance();
	private LicMgrVehicleOrderDetailsPage vehOrdDetailsPg = LicMgrVehicleOrderDetailsPage.getInstance();
	private OrderItems ordItem = new OrderItems(); 
	private LicMgrRegistrationDetailsPage vehRegDetailsPg = LicMgrRegistrationDetailsPage.getInstance();
	private BoatInfo vehicle = new BoatInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);	
		//register a vehicle
		lm.registerVehicleToOrderCart(cust, vehicle);
		String ordNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		lm.finishOrder();
		
		//reverse vehicle transaction
		lm.gotoVehicleOrderDetailPage(ordNum);
		ordItem = vehOrdDetailsPg.getOrderItemByProdNmAndType(vehicle.registration.product, 
				vehicle.registration.purchaseType);
		lm.reverseVehicleOrderItemOnOrdDetailsPg(vehicle.operationReason, vehicle.operationNote, 
				vehicle.registration.product, vehicle.registration.purchaseType);
		boolean result = lmOrderCartPg.compareTotalPrice("0.00");
		lm.processOrderCart(pay, false);
		
		//check the vehicle transaction status and price on order details page
		lm.gotoVehicleOrderDetailPage(ordNum);
		ordItem.itemPrice = "0.0";
		ordItem.itemStatus = REVERSED_STATUS;
		vehOrdDetailsPg.compareOrderItem(ordItem);
		
		//check the vehicle registration status on registration details page
		lm.gotoRegTitInspDetailsPgFromVehOrderDetailsPg(ordItem.registId);
		result &= vehRegDetailsPg.verifyRegStatus(REVERSED_STATUS);
		
		if (!result) {
			throw new ErrorOnPageException("Reverse vehicle registration is wrong! Please check log info!");
		} else {
			logger.info("Reverse vehicle registration is correct!");
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		cust.lName = "TEST-Refund";
		cust.fName = "QA-Refund";
		cust.residencyStatus = "Non Resident";
		
		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "RevRegVeh"+DataBaseFunctions.getEmailSequence();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = String.valueOf(DateFunctions.getCurrentYear());
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "PLEASURE";//"Personal Pleasure";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.operationReason = "14 - Other";;
		vehicle.operationNote = "QA Auto Regresssion Test";	
		
		vehicle.registration.product = "tta - advTAN";
		vehicle.registration.purchaseType = "Original";
	}
}
