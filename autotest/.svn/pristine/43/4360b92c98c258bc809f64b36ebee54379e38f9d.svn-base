/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.reversetransaction;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderItems;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrRegistrationDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;


/**
 * @Description: The case is used to verify the work flow of Reverse Vehicle Transaction with:
 * 1. 'duplicate registration' transaction type, and
 * 2. the source payment of the transaction is in Deferred Payment group, and
 * 3. has a refund generated after reverse, and
 * 4. the location where the reverse happens is the same one where the transaction was originally processed.
 * @Preconditions:
 * 1. make sure the customer "QA-Refund TEST-Refund" and the vehicle product "tta - advTAN" exist.
 * 2. make sure the location 'RefundTest' has the payment type 'Cash*'.
 * @SPEC: <Reverse Vehicle Transaction> and <Get Vehicle Transaction List for Reversal>
 * @Task#: Auto-1004
 * 
 * @author Lesley Wang
 * @Date  Jun 20, 2012
 */
public class DuplicateVehicleReg extends LicenseManagerTestCase {

	private OrmsOrderSummaryPage ordSummaryPg = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleOrderDetailsPage vehOrdDetailsPg = LicMgrVehicleOrderDetailsPage.getInstance();
	private OrderItems ordItem = new OrderItems(); 
	private LicMgrRegistrationDetailsPage vehRegDetailsPg = LicMgrRegistrationDetailsPage.getInstance();
	private BoatInfo vehicle = new BoatInfo();
	private String meg;
	boolean result = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);	
		//register a vehicle
		lm.registerVehicleToOrderCart(cust, vehicle);
		String regOrdNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		vehicle.registration.miNum = ordSummaryPg.getMINum();
		lm.finishOrder();
		
		// Duplicate the registration
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.duplicateOrRenewalRegistrationVehicleToOrderCartFromRegistrationDetailPg(
				vehicle.registration.purchaseType, vehicle.registration.product);
		pay.payType = Payment.PAY_DEF_CASH;
		String dupOrdNum = lm.processOrderCartToOrderSummaryPage(pay, false).split(" ")[0];
		lm.finishOrder();
		
		//reverse vehicle duplicate registration transaction
		lm.gotoVehicleOrderDetailPage(dupOrdNum);
		ordItem = vehOrdDetailsPg.getOrderItemByProdNmAndType(vehicle.registration.product,
				vehicle.registration.purchaseType);
		lm.reverseVehicleOrderItemOnOrdDetailsPg(vehicle.operationReason, vehicle.operationNote, 
				vehicle.registration.product, vehicle.registration.purchaseType);
		
		// verify the message after reverse on vehicle order details page
		result &= vehOrdDetailsPg.compareTopMeg(meg); // Block by defect-35170
		
		// verify the order item info after reverse on vehicle order details page
		ordItem.itemPrice = "0.0";
		ordItem.itemStatus = REVERSED_STATUS;
		vehOrdDetailsPg.compareOrderItem(ordItem); // Block by defect-34673, and will be fixed in 3.03.00
		
		//check the vehicle registration status on registration details page
		lm.gotoRegTitInspDetailsPgFromVehOrderDetailsPg(ordItem.registId);
		result &= vehRegDetailsPg.verifyRegStatus(ACTIVE_STATUS);
		result &= vehRegDetailsPg.verifyRegNumOfDup("0");
		
		if (!result) {
			throw new ErrorOnPageException("Reverse vehicle duplicate registration is wrong! Please check log info!");
		} else {
			logger.info("Reverse vehicle duplicate registration is correct!");
		}
		
		// Clean Up
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(regOrdNum, vehicle.operationReason, vehicle.operationNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF HQ Role/RefundTest";
		
		cust.lName = "TEST-Refund";
		cust.fName = "QA-Refund";
		cust.residencyStatus = "Non Resident";
		
		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "RevDupVehReg"+DataBaseFunctions.getEmailSequence();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = String.valueOf(DateFunctions.getCurrentYear());
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "PLEASURE";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.operationReason = "14 - Other";;
		vehicle.operationNote = "QA Auto Regresssion Test";	
		
		vehicle.registration.product = "tta - advTAN";
		vehicle.registration.purchaseType = "Duplicate";
		
		meg = "The reversal was processed successfully.";
	}
}
