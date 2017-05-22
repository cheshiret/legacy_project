/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.reversetransaction;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderItems;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsOrderCartPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrRefundWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrInspectionDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderDetailsPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: The case is used to verify the work flow of Reverse Vehicle Transaction with:
 * 1. 'inspect vehicle' transaction type, and
 * 2. the source payment of the transaction is in Deferred Payment group, and
 * 3. has refund generated after reverse, and 
 * 4. the location where the reverse happens is different from the one where the transaction was originally processed.
 * 5. the store has been closed where the vehicle transaction was originally processed.
 * @Preconditions:
 * 1. make sure the customer "QA-Refund TEST-Refund" exists.
 * 2. make sure the vehicle product "INS - InspectionBoat" exist.
 * 3. make sure the location 'AgentForClose' exist and it has the payment type 'Personal Check*'.
 * 4. make sure the role/location "HF HQ Role/AgentForClose" has been assigned to qa-auto-fm
 * 5. make sure the reverse reason "14 - Other" exists.
 * @SPEC: <Reverse Vehicle Transaction> and <Get Vehicle Transaction List for Reversal>
 * @Task#: Auto-1004
 * 
 * @author Lesley Wang
 * @Date  Jun 21, 2012
 */
public class InspectVehicle extends LicenseManagerTestCase {

	private OrderItems ordItem = new OrderItems(); 
	private StoreInfo store = new StoreInfo();
	private LicMgrVehicleOrderDetailsPage vehOrdDetailsPg = LicMgrVehicleOrderDetailsPage.getInstance();
	private LicMgrInspectionDetailsPage vehInspDetailsPg = LicMgrInspectionDetailsPage.getInstance();
	private LicMgrRefundWidget refundWidget = LicMgrRefundWidget.getInstance();
	private OrmsOrderCartPage lmOrderCartPg = OrmsOrderCartPage.getInstance();
	private BoatInfo vehicle = new BoatInfo();
	private String admLoc, revLocNm, revLoc, reverseMsg, type, product;
	private boolean result = true;
	
	@Override
	public void execute() {
		// Update the store's status to Active in DB
		lm.updateStoreStatusFromDB(schema, store.storeName, ACTIVE);
		
		// Turn on the config of issue refunds in cash for the location where reverse happens
		lm.configIssueRefundsInCash(schema, revLocNm, true);
		
		// Login in and Request a inspection
		lm.loginLicenseManager(login);	
		lm.inspectVehicleToOrderCartFromHomePg(cust, vehicle);
		String price = lmOrderCartPg.getTotalPriceAmount();
		String inspOrdNum = lm.processOrderCartToOrderSummaryPage(pay, false);
		lm.finishOrder();

		// Switch to admin location to close the store
		lm.switchLocationInHomePage(admLoc);
		lm.gotoStoreDetailPage(store.storeName);
		lm.updateStoreStatus(store.status);
		
		// Switch to reverse location
		lm.switchLocationInHomePage(revLoc);	
		
		// Reverse inspect vehicle transaction
		lm.gotoVehicleOrderDetailPage(inspOrdNum);
		ordItem = vehOrdDetailsPg.getOrderItemByProdNmAndType(product, type);
		lm.reverseVehicleOrderItemOnOrdDetailsPg(vehicle.operationReason, vehicle.operationNote, 
				product, type);
		
		// Verify Refund Widget
		result &= this.verifyRefundWidget(price, store.storeName);
		
		// Select to issue the refund to customer now
		lm.issueRefundToCustFromRefundWidgetToOrdDetailsPg();
		
		// verify the message after reverse on vehicle order details page
		result &= vehOrdDetailsPg.compareTopMeg(reverseMsg); 
		
		// verify the order item info after reverse on vehicle order details page
		ordItem.itemPrice = "0.0";
		ordItem.itemStatus = REVERSED_STATUS;
		vehOrdDetailsPg.compareOrderItem(ordItem);
		
		//check the vehicle title status and duplicates number on title details page
		lm.gotoRegTitInspDetailsPgFromVehOrderDetailsPg(ordItem.registId);
		result &= vehInspDetailsPg.compareInspStatus(REVERSED_STATUS);
		
		if (!result) {
			throw new ErrorOnPageException("Reverse inspect vehicle transaction is wrong! Please check log info!");
		} else {
			logger.info("Reverse inspect vehicle transaction is correct!");
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env+".db.schema.prefix") + "MS";
		
		store.storeName = "AgentForClose";
		store.status = "Inactive-Request Agent Closure";
		
		login.contract = "MS Contract";
		login.location = "HF HQ Role/" + store.storeName;
		
		cust.lName = "TEST-Refund";
		cust.fName = "QA-Refund";
		cust.residencyStatus = "Non Resident";
		
		vehicle.type = "Boat";
		vehicle.inspection.setAddress("Auto test for reverse inspection vehicle");
		vehicle.inspection.setZip("39296");
		vehicle.inspection.setCountry("United States");
		vehicle.inspection.setCity("Jackson");
		vehicle.inspection.setState("Mississippi");
		vehicle.inspection.setCounty("Adams");
		vehicle.inspection.setDayPhone("1234567899");
		vehicle.inspection.setInspectionReason("Boat Description");
		vehicle.operationReason = "14 - Other";
		vehicle.operationNote = "QA Auto Regresssion Test";	
		
		product = "INS - InspectionBoat";
		vehicle.inspection.setProduct(product);
		
		pay.payType = Payment.PAY_DEF_PER_CHQ;
		pay.checkNumber = "123456";
		
		admLoc = "HF Administrator - Auto-" + TestProperty.getProperty("ms.admin.location");
		revLocNm = "WAL-MART";
		revLoc = "HF HQ Role - Auto-" + revLocNm;
		type = "Original";
		reverseMsg = "The reversal was processed successfully.";
	}
	
	private boolean verifyRefundWidget(String price, String storeNm) {
		boolean res = true;
		
		// Verify the message and the store id and store name in the option 2 on Refund Widget
		res &= refundWidget.compareMessage(price);
		
		/*
		 * Not config Post refund as credit, there should be only one radio: Issue refund to customer
		// Verify the store id and store name in the option 2 on Refund Widget
//		String storeID = lm.getAgentID(schema, storeNm);
//		res &= refundWidget.compareStoreInfo(storeID, storeNm);
		 */
		
		// Verify the status of the two radio buttons
		res &= refundWidget.compareIssueRefundToCustRadioStatus(true);
//		res &= refundWidget.comparePostRefundAsCreditRadioStatus(false);
		res &= refundWidget.verifyPostRefundAsCreditRadioNOTExist();
		
		return res;
	}
}
