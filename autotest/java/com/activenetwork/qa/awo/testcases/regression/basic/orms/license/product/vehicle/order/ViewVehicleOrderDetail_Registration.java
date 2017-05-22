/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle.order;

import java.util.HashMap;
import java.util.Map;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderItems;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleRTI;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReceiptDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.ILicMgrProductPricingPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderFeesDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.LicMgrVehicleOrderHistoryPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage.LicMgrVehicleDetailPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:view order detail work flow for Registration vehicle
 *              check point:
 *              1.verify order info,charges info and bill customer info
 *              2.verify boat info
 *              3.verify customer info
 *              4.verify order item info
 *              5.verify fee button,history button,reverse order button,add to cart button
 *              6.verify click customer,click receipt,click vehicle
 *              
 * @Preconditions:
 * @SPEC:View Vehicle Order Details 
 * @Task#:AUTO-1000
 * 
 * @author szhou
 * @Date May 28, 2012
 */
public class ViewVehicleOrderDetail_Registration extends LicenseManagerTestCase {
	private LicMgrVehicleOrderDetailsPage vehicleOrderDetailhPg = LicMgrVehicleOrderDetailsPage
			.getInstance();
	private OrderInfo order = new OrderInfo();
	private VehicleRTI vehicleRTI = new VehicleRTI();
	private BoatInfo vehicle = new BoatInfo();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		/* data prepared */
		if (!lm.verifyProductExistInSys(schema, vehicleRTI.getPrdCode(), vehicleRTI.getPrdName())) {
			// add vehicle product
			lm.gotoVehicleSearchListPageFromTopMenu();
			lm.addVehicleProduct(vehicleRTI);
			// add price
			ILicMgrProductPricingPage pricingPage = lm
					.gotoProductPricingPageFromListPage(pricing.prodType,
							pricing.prodCode);
			lm.addPricingForProduct(pricing, pricingPage);
			// assign location
			lm.gotoVehicleProductDetailsPage(vehicleRTI.getPrdCode());
			lm.assignVehicleToStoresThruLocationClass("06-State Parks Agent");
			lm.gotoHomePage();
		}
		lm.switchLocationInHomePage("HF HQ Role - Auto-WAL-MART");
//		if (!lm.verifyOrderExistInSys(schema, vehicleRTI.getPrdName(),
//				TRANTYPE_REGISTER_VEHICLE)) {
			// registration vehicle
			lm.registerVehicleToOrderCart(cust, vehicle);
			lm.processOrderToOrderSummary(pay);
			lm.finishOrder();
//		}

		/* search order */
		this.setVehicleOrderInfoFromDB(schema, vehicleRTI.getPrdName());
		lm.gotoVehicleOrderDetailPage(order.orderNum);

		/* verify order details */
		// verify order info,charges info and bill customer info
		vehicleOrderDetailhPg.compareOrderInfo(order);
		// verify boat info
		vehicleOrderDetailhPg.compareBoatInfo(vehicle);
		// verify customer info
		vehicleOrderDetailhPg.compareCustomerInfo(cust);
		// verify order item info
		vehicleOrderDetailhPg.compareOrderItemInfo(order.orderList);

		// verify fee button
		lm.gotoVehicleOrderFeesDetailPage();
		this.goBackToOrderDetailPgFromFeePg();
		// verify history button
		lm.gotoVehicleOrderHistoryPage();
		this.goBackToOrderDetailPgFromHistoryPg();
		// verify reverse order button
		this.verifyReverseOrder();
		// verify add to cart button
		this.verifyAddToCart();
		// verify click customer
		this.verifyCustomerLink();
		// verify click vehicle
		this.verifyVehicleLink();//defect
		// verify click receipt
		this.verifyReceiptLink(order.receiptNum);

		lm.logOutLicenseManager();
	}
	
	private void verifyReceiptLink(String recp) {
		OrmsReceiptDetailsPage receipt = OrmsReceiptDetailsPage.getInstance();

		vehicleOrderDetailhPg.clickReceipt(recp);
		ajax.waitLoading();
		receipt.waitLoading();
	}

	private void verifyVehicleLink() {
		LicMgrVehicleDetailPage vehicle = LicMgrVehicleDetailPage.getInstance();

		vehicleOrderDetailhPg.clickVehicle();
		ajax.waitLoading();
		vehicle.waitLoading();
		vehicle.clickCancel();
		ajax.waitLoading();
		vehicleOrderDetailhPg.waitLoading();
	}

	private void verifyCustomerLink() {
		LicMgrCustomerDetailsPage custDetailPg = LicMgrCustomerDetailsPage
				.getInstance();

		vehicleOrderDetailhPg.clickCustomer();
		ajax.waitLoading();
		custDetailPg.waitLoading();
		custDetailPg.clickCancel();
		ajax.waitLoading();
		vehicleOrderDetailhPg.waitLoading();
	}

	private void verifyAddToCart() {
		if (!vehicleOrderDetailhPg.checkAddToCartExist()) {
			throw new ErrorOnPageException(
					"add to cart is not in the order detail page");
		}
	}

	private void verifyReverseOrder() {
		LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget confirmWidget = LicMgrVoidReverseOrUndoVoidReverseOrderConfirmWidget
				.getInstance();

		vehicleOrderDetailhPg.clickReverseOrder();
		ajax.waitLoading();
		confirmWidget.waitLoading();
		confirmWidget.clickCancel();
		ajax.waitLoading();
		vehicleOrderDetailhPg.waitLoading();
	}

	private void goBackToOrderDetailPgFromHistoryPg() {
		LicMgrVehicleOrderDetailsPage vehicleOrderDetailsPage = LicMgrVehicleOrderDetailsPage
				.getInstance();
		LicMgrVehicleOrderHistoryPage historyPage = LicMgrVehicleOrderHistoryPage
				.getInstance();

		historyPage.clickOK();
		ajax.waitLoading();
		vehicleOrderDetailsPage.waitLoading();
	}

	private void goBackToOrderDetailPgFromFeePg() {
		LicMgrVehicleOrderDetailsPage vehicleOrderDetailsPage = LicMgrVehicleOrderDetailsPage
				.getInstance();
		LicMgrOrderFeesDetailsPage feesPage = LicMgrOrderFeesDetailsPage
				.getInstance();

		feesPage.clickCancel();
		ajax.waitLoading();
		vehicleOrderDetailsPage.waitLoading();
	}

	@Override
	public void wrapParameters(Object[] param) {
		// login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		/* add vehicle */
		vehicleRTI.setPrdCode("VOD");
		vehicleRTI.setPrdName("View Order Detail");
		vehicleRTI.setStatus(OrmsConstants.ACTIVE_STATUS);
		vehicleRTI.setPrdGroup("Registration");
		vehicleRTI.setVehicleType("Boat");
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put(OrmsConstants.INDIVIDUAL_CUSTOMER_CLASS, true);
		vehicleRTI.setCustClass(map);
		vehicleRTI.setValidToDate("Purchase Date/Previous Valid To Date plus Valid Months");
		vehicleRTI.setValidMonths("2");
		vehicleRTI.setAdvanceRenewalDays("1");
		vehicleRTI.setLateRenewal("1");
		vehicleRTI.setLateRenewUnit("Day");
		Map<String, Boolean> map1 = new HashMap<String, Boolean>();
		map1.put("Personal Pleasure", true);
		vehicleRTI.setBoatUseTyp(map1);
		vehicleRTI.setMinLenthOfFt("7");
		vehicleRTI.setMinLenthOfIn("5");
		vehicleRTI.setMaxLenthOfFt("20");
		vehicleRTI.setMaxLenthOfIn("14");
		
		/* register vehicle */
		vehicle.hullIdSerialNum = "search" + DateFunctions.getCurrentTime();
		vehicle.manufacturerName = "YAMA";
		vehicle.modelYear = "1997";
		vehicle.feet = "15";
		vehicle.inches = "10";
		vehicle.hullMaterial = "Steel";
		vehicle.boatUse = "PLEASURE";
		vehicle.propulsion = "Sail";
		vehicle.fuelType = "Gasoline";
		vehicle.typeOfBoat = "Open";
		vehicle.registration.product = vehicleRTI.getPrdCode() + " - " + vehicleRTI.getPrdName();

		pricing.prodType = "Vehicle";
		pricing.prodCode = vehicleRTI.getPrdCode();
		pricing.status = "Active";
		pricing.locationClass = "All";
		pricing.licYearFrom = String.valueOf(DateFunctions.getCurrentYear());
		pricing.licYearTo = String.valueOf(DateFunctions.getCurrentYear() + 1);
		pricing.purchaseType = "Original";
		pricing.effectFrom = DateFunctions.getToday();
		pricing.effectTo = DateFunctions.getDateAfterToday(365);
		pricing.vendorFee = "20.00";
		pricing.stateTransFee = "15.00";
		pricing.stateFee = "10.00";
		pricing.transFee = "3.00";

		cust.identifier.identifierType = "Green Card";
		cust.identifier.identifierNum = "AutoBasic000017";
		cust.customerClass = "Individual";
		cust.dateOfBirth = "Jun 08 1988";
		cust.lName = "TEST-Basic17";
		cust.fName = "QA-Basic17";
		cust.identifier.country = "Canada";
		cust.residencyStatus = "Non Resident";

	}

	private void setVehicleOrderInfoFromDB(String schema, String prd) {
		String[] info = lm.getRegisterVehicleOrderInfoUsingPrdname(schema, prd).get(0);

		order.orderNum = info[0];
		order.receiptNum = info[1];
		order.orderDate = info[2].split(" ")[0];
		order.saleLocation = "WAL-MART";
		order.creationUser = "Test-Auto,QA-Auto";
		order.balance = info[11];
		order.orderPrice = info[10];
		order.orderPaid = info[12];
		order.confirmStatus = info[13];
		order.invoiceNum = info[14];
		OrderItems item = new OrderItems();
		item.product = vehicleRTI.getPrdCode() + "-" + vehicleRTI.getPrdName();
		item.registId = info[5];
		item.itemStatus = "Active";
		item.purchaseType = info[8];
		item.fiscalYear = info[7];
		item.itemPrice = info[6];
		order.orderList.add(item);
		order.billingCustomer = cust.lName + "," + cust.fName;
		order.billingCustMail = lm.getHFCustomerEmailInfo(schema, info[17]);

		vehicle.registration.miNum = info[3];
		vehicle.hullIdSerialNum = info[4];

		cust.custNum = info[16];
	}

}
