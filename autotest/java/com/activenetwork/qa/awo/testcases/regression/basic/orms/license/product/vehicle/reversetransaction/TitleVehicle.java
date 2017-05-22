/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.reversetransaction;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderItems;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderSummaryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrRefundWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleTitleDetialsInfoPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: The case is used to verify the work flow of Reverse Vehicle Transaction with:
 * 1. 'title vehicle' transaction type, and
 * 2. the source payment of the transaction is in Deferred Payment group, and
 * 3. has a refund generated after reverse, and
 * 4. the location where the reverse happens is different from the one where the transaction was originally processed.
 * 5. the location where the reverse happens is configured to be able to issue Refunds in Cash.
 * 6. Post refund as Credit to the location where the sale was processed.
 * @Preconditions:
 * 1. make sure the customer "QA-Refund TEST-Refund" exists.
 * 2. make sure the vehicle products "tta - advTAN" and "Rev - ReverseVehTran" exist.
 * 3. make sure the location 'RefundTest' has the payment type 'Personal Check*'.
 * @SPEC: <Reverse Vehicle Transaction> and <Get Vehicle Transaction List for Reversal>
 * @Task#: Auto-1004
 * 
 * @author Lesley Wang
 * @Date  Jun 20, 2012
 */
public class TitleVehicle extends LicenseManagerTestCase {

	private OrderItems ordItem = new OrderItems(); 
	private OrmsOrderSummaryPage ordSummaryPg = OrmsOrderSummaryPage.getInstance();
	private LicMgrVehicleOrderDetailsPage vehOrdDetailsPg = LicMgrVehicleOrderDetailsPage.getInstance();
	private LicMgrVehicleTitleDetialsInfoPage vehTitDetailsPg = LicMgrVehicleTitleDetialsInfoPage.getInstance();
	private LicMgrRefundWidget refundWidget = LicMgrRefundWidget.getInstance();
	private OrmsOrderCartPage lmOrderCartPg = OrmsOrderCartPage.getInstance();
	private BoatInfo vehicle = new BoatInfo();
	private String orgLoc, revLoc, revLocNm, reverseMsg;
	private boolean result = true;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);	
		
		//register a vehicle
		lm.registerVehicleToOrderCart(cust, vehicle);
		String regOrdNum = lm.processOrderCartToOrderSummaryPage(pay, false);
		vehicle.registration.miNum = ordSummaryPg.getMINum();
		lm.finishOrder();
		
		// Title the vehicle
		lm.gotoVehicleDetailsPgByMiNum(vehicle.registration.miNum);
		lm.titleVehicleToOrderCartFromVehicleDetailPage(vehicle);
		String price = lmOrderCartPg.getTotalPriceAmount();
		pay.payType = Payment.PAY_DEF_PER_CHQ;
		String titOrdNum = lm.processOrderCartToOrderSummaryPage(pay, false);
		lm.finishOrder();
		
		// Switch to another location and turn on the config of issue to Cash in DB for the location
		lm.switchLocationInHomePage(revLoc);	
		lm.configIssueRefundsInCash(schema, revLocNm, true);

		// Reverse title vehicle transaction
		lm.gotoVehicleOrderDetailPage(titOrdNum);
		ordItem = vehOrdDetailsPg.getOrderItemByProdNmAndType(vehicle.title.product, vehicle.title.purchaseType);
		lm.reverseVehicleOrderItemOnOrdDetailsPg(vehicle.operationReason, vehicle.operationNote, 
				vehicle.title.product, vehicle.title.purchaseType);
		
		// Verify Refund Widget
		result &= this.verifyRefundWidget(price, orgLoc);
		
		// Select to post the refund as credit
		lm.postRefundAsCreditFromRefundWidgetToOrdDetailsPg();
		
		// verify the message after reverse on vehicle order details page
		result &= vehOrdDetailsPg.compareTopMeg(reverseMsg); 
		
		// verify the order item info after reverse on vehicle order details page
		ordItem.itemPrice = "0.0";
		ordItem.itemStatus = REVERSED_STATUS;
		vehOrdDetailsPg.compareOrderItem(ordItem); // Block by defect-34673, and will be fixed in 3.03.00
		
		//check the vehicle title status on title details page
		lm.gotoRegTitInspDetailsPgFromVehOrderDetailsPg(ordItem.registId);
		result &= vehTitDetailsPg.compareVehTitleStatus(REVERSED_STATUS);
		
		if (!result) {
			throw new ErrorOnPageException("Reverse title vehicle transcation is wrong! Please check log info!");
		} else {
			logger.info("Reverse title vehicle transcation is correct!");
		}
		
		// Clean Up
		lm.gotoHomePage();
		lm.voidRegisterVehicleOrder(regOrdNum, vehicle.operationReason, vehicle.operationNote);
		lm.processOrderCart(pay);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env+".db.schema.prefix") + "MS";
		
		orgLoc = "RefundTest";
		login.contract = "MS Contract";
		login.location = "HF HQ Role/" + orgLoc;
		
		cust.lName = "TEST-Refund";
		cust.fName = "QA-Refund";
		cust.residencyStatus = "Non Resident";
		
		vehicle.type = "Boat";
		vehicle.hullIdSerialNum = "RevTitleVeh"+DataBaseFunctions.getEmailSequence();
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
		
		vehicle.title.product = "Rev - ReverseVehTran";
		vehicle.title.purchaseType = "Original";
		vehicle.title.boatValue = "2";
		
		revLocNm = "WAL-MART";
		revLoc = "HF HQ Role - Auto-" + revLocNm;
		reverseMsg = "The reversal was processed successfully.";
		
		pay.checkNumber = "123456";
	}
	
	private boolean verifyRefundWidget(String price, String storeNm) {
		boolean res = true;
		
		// Verify the message and the store id and store name in the option 2 on Refund Widget
		res &= refundWidget.compareMessage(price);
		
		// Verify the store id and store name in the option 2 on Refund Widget
		String storeID = lm.getAgentID(schema, storeNm);
		res &= refundWidget.compareStoreInfo(storeID, storeNm);
		
		// Verify the status of the two radio buttons
		res &= refundWidget.compareIssueRefundToCustRadioStatus(true);
		res &= refundWidget.comparePostRefundAsCreditRadioStatus(true);
		
		return res;
	}
}
