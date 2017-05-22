package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderItems;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderDetailsCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date Oct 27, 2011
 */
public class LicMgrVehicleOrderDetailsPage extends LicMgrOrderDetailsCommonPage {

	private static LicMgrVehicleOrderDetailsPage _instance = null;

	protected LicMgrVehicleOrderDetailsPage() {
	}

	public static LicMgrVehicleOrderDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrVehicleOrderDetailsPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",
				"veh_order_det");
	}

	/**
	 * Click Reverse Order link
	 */
	public void clickReverseOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reverse Order");
	}

	/**
	 * Click Add To Cart link
	 */
	public void clickAddToCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add To Cart");
	}

	public void clickCustomer() {
		browser.clickGuiObject(".class", "Html.A", ".id",
				new RegularExpression(
						"VehicleOrderDetailsView-\\d+.vehicleCustNum", false));
	}

	public void clickVehicle() {
		browser.clickGuiObject(".class", "Html.A", ".id",
				new RegularExpression("VehicleDetailView-\\d+.vehicleNum",
						false));
	}

	public void clickReceipt(String num) {
		browser.clickGuiObject(".class", "Html.A", ".text", num);
	}

	public boolean checkAddToCartExist() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text",
				"Add To Cart");
	}

	/**
	 * Get the order creation date
	 * 
	 * @return
	 */
	public String getCreationDate() {
		return this.getOrderAttributeValueByName("Creation Date");
	}

	/**
	 * Get the order creation user
	 * 
	 * @return
	 */
	public String getCreationUser() {
		return this.getOrderAttributeValueByName("Creation User");
	}

	public String getMINum() {
		return this.getOrderAttributeValueByName("Reg. Number");//MI #
	}

	public String getHullID() {
		return this.getOrderAttributeValueByName("Hull ID/Serial #");
	}

	public String getManufacturerName() {
		return this.getOrderAttributeValueByName("Manufacturer Name");
	}

	public String getManufacturerPrintName() {
		return this.getOrderAttributeValueByName("Manufacturer Print Name");
	}

	public String getModelYear() {
		return this.getOrderAttributeValueByName("Model Year");
	}

	public String getBillingCustomerName() {
		return this.getOrderAttributeValueByName("Name");
	}

	public String getBillingCustomerPhoneNum() {
		return this.getOrderAttributeValueByName("Phone");
	}

	public String getBillingCustomerEmailAddress() {
		return this.getOrderAttributeValueByName("Email");
	}

	public String getVehicleCustomerNum() {
		return this.getOrderAttributeValueByName("MDWFP #");
	}

	public String getVehicleCustomerStatus() {
		return this.getOrderAttributeValueByName("Status");
	}

	public String getVehicleCustomerClass() {
		return this.getOrderAttributeValueByName("Customer Class");
	}

	public String getVehicleCustomerFirstName() {
		return this.getOrderAttributeValueByName("First Name");
	}

	public String getVehicleCustomerMiddleName() {
		return this.getOrderAttributeValueByName("Middle Name");
	}

	public String getVehicleCustomerLastName() {
		return this.getOrderAttributeValueByName("Last Name");
	}

	public String getVehicleCustomerSuffix() {
		return this.getOrderAttributeValueByName("Suffix");
	}

	public String getVehicleCustomerDateOfBirth() {
		return this.getOrderAttributeValueByName("Date of Birth");
	}

	public String getTAN() {
		return this.getOrderAttributeValueByName("TAN", 0);
	}

	public void verifyTANCode(String TANCode) {
		String TANFromUI = getTAN();

		if (!TANFromUI.equals(TANCode)) {
			throw new ErrorOnPageException(
					"TAN Code display un-correctly on order detail page",
					TANCode, TANFromUI);
		}

		logger.info("Verify TAN Code successfully on order detail page");
	}

	public OrderInfo getOrderInfo() {
		OrderInfo info = new OrderInfo();

		info.orderNum = this.getOrderNum();
		info.saleLocation = this.getSalesLocation();
		info.createDate = this.getCreationDate();
		info.creationUser = this.getCreationUser();
		info.orderPrice = this.getPrice();
		info.balance = this.getBalance();
		info.orderPaid = this.getPaid();
		info.unissuedRefund = this.getUnissuedRefund();
		info.confirmStatus = this.getConfirmationStatus();
		info.invoiceNum = this.getInvoiceNum();
		info.receiptNum = this.getReceiptNum();
		info.billingCustomer = this.getBillingCustomerName();
		info.billingCustPhone = this.getBillingCustomerPhoneNum();
		info.billingCustMail = this.getBillingCustomerEmailAddress();

		return info;
	}

	public BoatInfo getBoatInfo() {
		BoatInfo vehicle = new BoatInfo();

		vehicle.registration.miNum = this.getMINum();
		vehicle.hullIdSerialNum = this.getHullID();
		vehicle.manufacturerName = this.getManufacturerName();
		vehicle.modelYear = this.getModelYear();

		return vehicle;
	}

	public Customer getCustomerInfo() {
		Customer customer = new Customer();

		customer.fName = this.getVehicleCustomerFirstName();
		customer.mName = this.getVehicleCustomerMiddleName();
		customer.lName = this.getVehicleCustomerLastName();
		customer.dateOfBirth = this.getVehicleCustomerDateOfBirth();
		customer.customerClass = this.getVehicleCustomerClass();
		customer.custNum = this.getVehicleCustomerNum();
		customer.status = this.getVehicleCustomerStatus();

		return customer;
	}

	public List<OrderItems> getOrderItemInfo() {
		List<OrderItems> items = new ArrayList<OrderItems>();
//		HtmlObject[] objs = browser.getTableTestObject(".id",
//				new RegularExpression("grid_\\d+", false), ".text",
//				new RegularExpression("Product.*", false));
//		if (objs.length < 0) {
//			throw new ErrorOnPageException("Cann't find order item table...");
//		}
		IHtmlObject[] objs = this.getOrderItemsListTable();

		IHtmlTable table = (IHtmlTable) objs[0];
		for (int i = 0; i < table.rowCount(); i++) {
			OrderItems item = new OrderItems();
			List<String> row = table.getRowValues(i);
			item.product = row.get(1);
			item.registId = row.get(2);
			item.itemStatus = row.get(3);
			item.purchaseType = row.get(4);
			item.fiscalYear = row.get(5);
			item.itemPrice = row.get(6);
			items.add(item);
		}
		Browser.unregister(objs);
		return items;
	}

	private void compareData(String expect, String actual) {
		if (!expect.equals(actual)) {
			throw new ErrorOnPageException("Data comparing is not correct...",
					expect, actual);
		}
	}

	private void comparePriceData(String expect, String actual) {
		if (new BigDecimal(expect).setScale(2).compareTo(
				new BigDecimal(actual).setScale(2)) != 0) {
			throw new ErrorOnPageException("Data comparing is not correct...",
					expect, actual);
		}
	}

	public void compareOrderInfo(OrderInfo order) {
		OrderInfo info = this.getOrderInfo();

		this.compareData(order.orderNum, info.orderNum);
		this.compareData(order.saleLocation, info.saleLocation.split("\\(")[0]);
		this.compareData(DateFunctions.formatDate(order.orderDate,
				"EEE MMM dd yyyy"), info.createDate);
		this.compareData(order.creationUser, info.creationUser);
		this.comparePriceData(order.balance, info.balance.split("\\$")[1]);
		this.comparePriceData(order.orderPrice, info.orderPrice);
		this.comparePriceData(order.orderPaid, info.orderPaid.split("\\$")[1]);
		// this.compareData(order.invoiceNum, info.invoiceNum);
		this.compareData(order.receiptNum, info.receiptNum);
		this.compareData(order.billingCustomer, info.billingCustomer);
		this.compareData(order.billingCustPhone, info.billingCustPhone);
		this.compareData(order.billingCustMail, info.billingCustMail);
	}

	public void compareBoatInfo(BoatInfo vehicle) {
		BoatInfo info = this.getBoatInfo();

		this.compareData(vehicle.registration.miNum, info.registration.miNum);
		this.compareData(vehicle.hullIdSerialNum, info.hullIdSerialNum);
		this.compareData(vehicle.manufacturerName, info.manufacturerName);
		this.compareData(vehicle.modelYear, info.modelYear);
	}

	public void compareCustomerInfo(Customer customer) {
		Customer cust = this.getCustomerInfo();

		this.compareData(customer.custNum, cust.custNum);
		this.compareData(customer.customerClass, cust.customerClass);
		this.compareData(customer.dateOfBirth, cust.dateOfBirth);
		this.compareData(customer.fName, cust.fName);
		this.compareData(customer.mName, cust.mName);
		this.compareData(customer.lName, cust.lName);
	}

	public void compareOrderItemInfo(List<OrderItems> items) {
		List<OrderItems> infos = this.getOrderItemInfo();
		infos.remove(0);

		if (items.size() != infos.size()) {
			throw new ErrorOnPageException(
					"order item size is not correct...,expect size is"
							+ items.size() + ",but actually size is"
							+ infos.size());
		}

		for (int i = 0; i < items.size(); i++) {
			OrderItems expect = items.get(i);
			OrderItems actual = infos.get(i);

			this.compareData(expect.product, actual.product);
			this.compareData(expect.registId, actual.registId);
			this.compareData(expect.itemStatus, actual.itemStatus);
			// this.compareData(expect.purchaseType, actual.purchaseType);
			this.compareData(expect.fiscalYear, actual.fiscalYear);
			this.comparePriceData(expect.itemPrice, actual.itemPrice);
		}
	}

	/**
	 * Get Order Item list table on order details page
	 */
	public IHtmlObject[] getOrderItemsListTable() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", 
				new RegularExpression("grid_\\d+", false), ".text",
				new RegularExpression("Product.*", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Vehicle Order Items List table.");
		}
		return objs;
	}
	
	/**
	 * Get the order item row index by the product name and type
	 */
	private int getOrderItemRowIndex(String product, String type) {
		IHtmlObject objs[] = this.getOrderItemsListTable();
		IHtmlTable itemsListTable = (IHtmlTable)objs[0];
		
		product = product.replace(" - ", "-");
		//get the row index by matching the product name and purchase type
		int rowNum = -1;
		for(int i = 1; i < itemsListTable.rowCount(); i++) { // starts from 1
			if(itemsListTable.getCellValue(i, 1).trim().equalsIgnoreCase(product) && 
					itemsListTable.getCellValue(i, 4).trim().equalsIgnoreCase(type)) {
				logger.info("Get Vehicle Order Item row number " + 
						" by product name=" + product + " and purchase type=" + type);
				rowNum = i;
				break;
			}
		}
		Browser.unregister(objs);
		
		if(rowNum == -1) {
			throw new ItemNotFoundException("Can't find the vehicle order item by " +
					"product name=" + product + " and purchase type=" + type);
		}
		
		return rowNum;
	}
	
	/**
	 * Select the order item check box by the product name and type.
	 */
	public void selectOrderItemByProductNmAndType(String name, String purchaseType) {
		int index = this.getOrderItemRowIndex(name, purchaseType);
		browser.selectCheckBox(".id", 
				new RegularExpression("GenericGrid-\\d+.selectedItems", false), index-1);		
	}
	
	/**
	 * Click the ID link on the order items list.
	 */
	public void clickOrderItemRegTitleInspID(String ID) {
		browser.clickGuiObject(".class", "Html.A", ".text", ID);
	}
	
	/**
	 * Click Reverse button
	 */
	public void clickReverse() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reverse");
	}
	
	/**
	 * Get order item info by the product and purchase type.
	 */
	public OrderItems getOrderItemByProdNmAndType(String product, String type) {
		int index = this.getOrderItemRowIndex(product, type);
		IHtmlObject objs[] = this.getOrderItemsListTable();
		IHtmlTable itemsListTable = (IHtmlTable)objs[0];
		OrderItems item = new OrderItems();
		
		List<String> row = itemsListTable.getRowValues(index);
		item.product = row.get(1);
		item.registId = row.get(2);
		item.itemStatus = row.get(3);
		item.purchaseType = row.get(4);
		item.fiscalYear = row.get(5);
		item.itemPrice = row.get(6);
		
		Browser.unregister(objs);
		return item;
	}
	
	/**
	 * Compare order item. Find the order item with the product and purchase type on the page and then compare.
	 */
	public void compareOrderItem(OrderItems item) {
		OrderItems actItems = this.getOrderItemByProdNmAndType(item.product, item.purchaseType);
		this.compareData(item.registId, actItems.registId);
		this.compareData(item.itemStatus, actItems.itemStatus);
		this.compareData(item.fiscalYear, actItems.fiscalYear);
		this.comparePriceData(item.itemPrice, actItems.itemPrice);
		
		logger.info("The order item info is correct!");
	}
	
	/**
	 * Get the error message on top of the page
	 */
	public String getTopInfoMeg() {
		return browser.getObjectText(".classname", "message msgsuccess", ".id", "NOTSET");
	}
	
	/**
	 * Compare the error message on top of the page
	 */
	public boolean compareTopMeg(String expMeg) {
		String meg = this.getTopInfoMeg();
		return MiscFunctions.compareResult("The Info Message on top of the page", expMeg, meg);
	}
	
	/**
	 * get alert message.
	 * @return
	 */
	public String getAlertMsg(){
		String msg = "";
		IHtmlObject[] notSet = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		if(notSet.length>0)
		{
		 msg = notSet[0].text();
		}
		
		Browser.unregister(notSet);
		return msg; 
	}
	
	public void clickVehicleMiNum(String miNum) {
		browser.clickGuiObject(".class", "Html.A", ".text", miNum);
	}

}
