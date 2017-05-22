package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @author ssong
 * @date Dec 5, 2011
 */
public class LicMgrCustomerOrdersPage extends LicMgrCustomerDetailsPage{
	
	private static LicMgrCustomerOrdersPage _instance = null;
	
	protected LicMgrCustomerOrdersPage(){
		
	}
	
	public static LicMgrCustomerOrdersPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrCustomerOrdersPage();
		}
		return _instance;
	}
	
	public boolean exists(){//check customer order table exists
		return browser.checkHtmlObjectExists(".id","HFOrderSearchList");
	}
	
	private final String ORDER_NUM_TAN_COL = "Order # / TAN";
	private final String RECEIPT_NUM_COL = "Receipt#";
	private final String ORDER_DATE_COL = "Order Date";
	private final String PRODUCT_COL = "Product";
	private final String ORDER_ITEM_STATUS_COL = "Order Item Status";
	private final String ID_MI_HULLID_SERIAL_NUM_ID_COL = "ID/Reg. Number Hull ID/Serial#";//ID/MI # Hull ID/Serial#
	private final String IS_BILLING_CUSTOMER_COL = "Billing Cust?";
	private final String SALES_LOCATION_COL = "Sales Location (Agent)";
	private final String ORDER_PRICE_COL = "Order Price";
	private final String BALANCE_COL = "Balance";
	
	public IHtmlObject[] getCustOrderTable() {
		IHtmlObject[] objs = browser.getTableTestObject(".id","HFOrderSearchList");
		if(objs.length<1)
			throw new ItemNotFoundException("Could not get customer order table by id HFOrderSearchList.");
		return objs;
	}
	
	public List<OrderInfo> getCustomerOrders(){
		IHtmlObject[] objs = browser.getTableTestObject(".id","HFOrderSearchList");
		IHtmlTable grid = (IHtmlTable)objs[0];
		OrderInfo order;
		List<OrderInfo> orders = new ArrayList<OrderInfo>();
		if(grid.rowCount()>1){
			for(int i=1;i<grid.rowCount();i++){
				order = new OrderInfo();
				order.orderNum = grid.getCellValue(i, 0);
				order.receiptNum = grid.getCellValue(i, 1);
				order.orderDate = grid.getCellValue(i, 2);
				order.product = grid.getCellValue(i, 3);
				order.status = grid.getCellValue(i, 4);
				order.billingCustomer = grid.getCellValue(i, 6);
				order.saleLocation = grid.getCellValue(i, 7);
				order.orderPrice = grid.getCellValue(i, 8);
				order.balance = grid.getCellValue(i, 9);
				orders.add(order);
			}
		}
		Browser.unregister(objs);
		return orders;
	}
	/**
	 * Click the order number value.
	 * @param id-- the value of order number.
	 */
	public void clickOrderNumber(String id){
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
	/**
	 * Compare the customer orders info.
	 * @param expectedOrder-- the expected order info.
	 * @return -- the boolean value.
	 */
	public boolean compareOrdersInfo(OrderInfo expectedOrder){
		boolean pass = true;
		IHtmlObject[] objs = browser.getTableTestObject(".id","HFOrderSearchList");
		IHtmlTable grid = (IHtmlTable)objs[0];
//		List<String> text = grid.getRowValues(0);
		int row = grid.findRow(0, expectedOrder.orderNum);
		List<String> rowList = grid.getRowValues(row);
		String temp = "";
		if(rowList.size()<1){
			pass &= false;
			logger.error("Can't find the "+expectedOrder.orderNum+" order inf");
		}
		temp = rowList.get(grid.findColumn(0, ORDER_NUM_TAN_COL));
		if(!temp.equals(expectedOrder.orderNum)){
			pass &= false;
			logger.error("The order number should be "+expectedOrder.orderNum+" but actual value is "+temp+"");
		}
		temp = rowList.get(grid.findColumn(0, RECEIPT_NUM_COL));
		if(!temp.equals(expectedOrder.receiptNum)){
			pass &= false;
			logger.error("The order receipt num should be "+expectedOrder.receiptNum+" but actual value is "+temp+"");
		}
		
		temp = rowList.get(grid.findColumn(0, ORDER_DATE_COL));
		if(0!=DateFunctions.compareDates(temp, expectedOrder.orderDate)){
			pass &= false;
			logger.error("The order date should be "+expectedOrder.orderDate+" but actual value is "+temp+"");
		}
//		temp = rowList.get(3);
//		if(!temp.equals(expectedOrder.orderType)){
//			pass &= false;
//			logger.error("The order date should be "+expectedOrder.orderType+" but actual value is "+temp+"");
//		}
		temp  = rowList.get(grid.findColumn(0, IS_BILLING_CUSTOMER_COL));
		if(!temp.equals(expectedOrder.billingCustomer)){
			pass &= false;
			logger.error("The order biiling customer should be "+expectedOrder.billingCustomer+" but actual value is "+temp+"");
		}
		temp  = rowList.get(grid.findColumn(0, SALES_LOCATION_COL));
		if(!temp.contains(expectedOrder.saleLocation)){
			pass &= false;
			logger.error("The order sales location should be "+expectedOrder.saleLocation+" but actual value is "+temp+"");
		}
//		temp  = rowList.get(7);
//		if(!temp.equals(expectedOrder.numOfItems)){
//			pass &= false;
//			logger.error("The order num of items should be "+expectedOrder.numOfItems+" but actual value is "+temp+"");
//		}
		temp  = rowList.get(grid.findColumn(0, ORDER_PRICE_COL));
		if(!temp.equals(expectedOrder.orderPrice)){
			pass &= false;
			logger.error("The order price should be "+expectedOrder.orderPrice+" but actual value is "+temp+"");
		}
		temp  = rowList.get(grid.findColumn(0, BALANCE_COL));
		if(!temp.equals(expectedOrder.balance)){
			pass &= false;
			logger.error("The order balance should be "+expectedOrder.balance+" but actual value is "+temp+"");
		}
		return pass;
	}
	
	public void setLicenseYear(String lyear) {
		browser.setTextField("id", new RegularExpression("SearchCustomerOrderCriteria-\\d+\\.fiscalYear", false), lyear);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
		
	public void searchOrderInLicenseYear(String licenseyear) {
		logger.info("Search order sold in license year "+licenseyear);
		setLicenseYear(licenseyear);
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public OrderInfo getOrderInfosByOrderNum(String ordNum) {
		logger.info("Get order info by order number "+ordNum);
		
		IHtmlObject[] objs = browser.getTableTestObject(".id","HFOrderSearchList");
		if(objs.length < 1)
			throw new ItemNotFoundException("Could not find order search list table on page.");
		IHtmlTable table = (IHtmlTable)objs[0];
		
		OrderInfo order = null;
		for(int i=1;i<table.rowCount();i++){
			String temp = table.getCellValue(i, table.findColumn(0, ORDER_NUM_TAN_COL));
			if(temp.startsWith(ordNum)){
				order = new OrderInfo();
				order.orderNum = ordNum;
				order.tan = temp.replace(ordNum, "").trim();
				order.receiptNum = table.getCellValue(i, table.findColumn(0, RECEIPT_NUM_COL));
				order.orderDate = table.getCellValue(i, table.findColumn(0, ORDER_DATE_COL));
				String product = table.getCellValue(i, table.findColumn(0, PRODUCT_COL));
				order.productCode = product.split("\\-")[0].trim();
				order.productPurchaseType = product.substring(product.indexOf("(")+1, product.indexOf(")"));
				order.status = table.getCellValue(i, table.findColumn(0, ORDER_ITEM_STATUS_COL));
				//miNum + Hull ID
				order.miNum = table.getCellValue(i, table.findColumn(0, ID_MI_HULLID_SERIAL_NUM_ID_COL));
				RegularExpression reg = new RegularExpression("^Sales Location.*", false);
				order.saleLocation = table.getCellValue(i, table.findColumn(0, reg));
				order.orderPrice = table.getCellValue(i, table.findColumn(0, ORDER_PRICE_COL)).replaceAll("\\$", "");
				break;
			}
		}
		Browser.unregister(objs);
		return order;
	}
	
	public List<String> getCustOrdTableCols() {
		IHtmlObject[] objs = this.getCustOrderTable();
		IHtmlTable grid = (IHtmlTable)objs[0];
		List<String> cols=grid.getRowValues(0);
		if(cols==null || cols.size()<1)
			throw new ItemNotFoundException("Could not get any column names in customer order table.");
		Browser.unregister(objs);
		return cols;
	}
	
	public OrderInfo getOrderInfoByOrderNum(String ordNum){
		logger.info("Get order info by order number "+ordNum);
		IHtmlObject[] objs = this.getCustOrderTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		OrderInfo order = null;
		for(int i=1;i<table.rowCount();i++){
			String temp = table.getCellValue(i, table.findColumn(0, ORDER_NUM_TAN_COL));
			if(temp.startsWith(ordNum)){
				order = new OrderInfo();
				order.orderNum = ordNum;
				order.tan = temp.replace(ordNum, "").trim();
				order.receiptNum = table.getCellValue(i, table.findColumn(0, RECEIPT_NUM_COL));
				order.orderDate = table.getCellValue(i, table.findColumn(0, ORDER_DATE_COL));
				order.product = table.getCellValue(i, table.findColumn(0, PRODUCT_COL));
				order.status = table.getCellValue(i, table.findColumn(0, ORDER_ITEM_STATUS_COL));
				//miNum + Hull ID
				order.miNum = table.getCellValue(i, table.findColumn(0, ID_MI_HULLID_SERIAL_NUM_ID_COL));
				RegularExpression reg = new RegularExpression("^Sales Location.*", false);
				order.saleLocation = table.getCellValue(i, table.findColumn(0, reg));
				order.orderPrice = table.getCellValue(i, table.findColumn(0, ORDER_PRICE_COL)).replaceAll("\\$", "");
				break;
			}
		}
		Browser.unregister(objs);
		return order;
	}
	
	public List<String> getAllOrderNums(String status) {
		IHtmlObject objs[] = this.getCustOrderTable();
		IHtmlTable table = (IHtmlTable)objs[0];
//		PagingComponent paging = new PagingComponent();
		
		List<String> toReturns = new ArrayList<String>();
		int orderNumIndex = table.findColumn(0, ORDER_NUM_TAN_COL);
		int statusColIndex = table.findColumn(0, ORDER_ITEM_STATUS_COL);
		
//		do {
			objs = this.getCustOrderTable();
			table = (IHtmlTable)objs[0];
			
			List<String> orderNums = table.getColumnValues(orderNumIndex);
			List<String> statuss = table.getColumnValues(statusColIndex);
			
			for(int i = 0; i < orderNums.size(); i ++) {
				if(statuss.get(i).equalsIgnoreCase(status)) {
					toReturns.add(orderNums.get(i));
				}
			}
//		} while(paging.clickNext());
		
		Browser.unregister(objs);
		return toReturns;
	}
	
	public List<String> getAllActiveOrderNums() {
		return getAllOrderNums(OrmsConstants.ACTIVE_STATUS);
	}
}
