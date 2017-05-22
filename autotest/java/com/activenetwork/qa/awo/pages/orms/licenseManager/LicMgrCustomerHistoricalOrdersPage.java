package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HistoricalOrder;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrCustomerHistoricalOrdersPage extends LicMgrCustomerDetailsPage {
	 
		private static LicMgrCustomerHistoricalOrdersPage instance=null;
		
		private LicMgrCustomerHistoricalOrdersPage(){};
		
		public static LicMgrCustomerHistoricalOrdersPage getInstance(){
			if(instance==null){
				instance=new LicMgrCustomerHistoricalOrdersPage();
			}
			return instance;
		}
		
		private final String ORDER_NUM_TAN_COL = "Order #";
		private final String RECEIPT_NUM_COL = "Receipt #";
		private final String ORDER_DATE_COL = "Order Date";
		private final String PRODUCT_COL = "Product";
		private final String ORDER_ITEM_STATUS_COL = "Order Item Status";
		private final String SALES_LOCATION_COL = "Sales Location(Agent)";
		private final String ORDER_PRICE_COL = "Order Price";
		private final String BALANCE_COL = "Balance";
		
		@Override
		public boolean exists() {
			return browser.checkHtmlObjectExists(".id","HistoricalOrdersListUI_table");
		}
		
		/**Click button Go*/
		public void clickGo(){
			browser.clickGuiObject(".class", "Html.A", ".text","Go");
		}
		
		
		 /**
		 * get total all records on the page.
		 * @param
		 * @return List of records.
		 */
		public List<HistoricalOrder> getAllRecordsOnPage() {
			IHtmlObject objs[] = null;
			IHtmlTable table = null;
			List<HistoricalOrder> records = new ArrayList<HistoricalOrder>();
			int rows;
			int columns;
			HistoricalOrder ord;
			
			
			do{
				objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+_LIST",false));
				
				if(objs.length < 1) {
							throw new ItemNotFoundException("Can't historical order table object.");
						}
				
				table = (IHtmlTable)objs[0];
				rows = table.rowCount();
				columns = table.columnCount();
				logger.info("Find record on page LicMgrCustomerHistoricalOrdersPage, "+rows+" rows, "+columns+" columns.");
				
				for(int i = 1; i < rows; i ++) {
					ord = new HistoricalOrder();
					ord.setOrdNum(table.getCellValue(i, table.findColumn(0, ORDER_NUM_TAN_COL)));
					ord.setReceipt(table.getCellValue(i, table.findColumn(0, RECEIPT_NUM_COL)));
					ord.setOrderDate(table.getCellValue(i, table.findColumn(0, ORDER_DATE_COL)));
					ord.setProductName(table.getCellValue(i, table.findColumn(0, PRODUCT_COL)));
					ord.setOrderItemStatus(table.getCellValue(i, table.findColumn(0, ORDER_ITEM_STATUS_COL)));
					ord.setSalesLocation(table.getCellValue(i, table.findColumn(0, SALES_LOCATION_COL)));
					ord.setOrderPrice(table.getCellValue(i, table.findColumn(0, ORDER_PRICE_COL)));
					ord.setBalance(table.getCellValue(i, table.findColumn(0, BALANCE_COL)));
					records.add(ord);			
				}

			}while(gotoNext());
			Browser.unregister(objs);
			
			return records;
		}
	    
		
		

		/**
		 * If "Next" button is available, click it
		 *
		 */
		public boolean gotoNext() {
			IHtmlObject[] pageingBar = browser.getHtmlObject(".class", "Html.TABLE", ".id", new RegularExpression("pagingbar_\\d+",false));
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Next", pageingBar[0]);
			boolean toReturn = false;

			if (objs.length > 0) {
				toReturn = true;
				objs[0].click();
			}

			ajax.waitLoading();
			Browser.unregister(pageingBar);
			Browser.unregister(objs);
			this.waitLoading();

			return toReturn;
		}
		
		public IHtmlObject[] getCustHisOrdTable() {
			IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+_LIST", false));
			if(objs.length<1)
				throw new ItemNotFoundException("Could not get customer historical order table.");
			return objs;
		}
		
		public OrderInfo getOrderInfoByOrderNum(String ordNum){
			logger.info("Get order info by order number "+ordNum+"");
			
			IHtmlObject[] grid = this.getCustHisOrdTable();
			IHtmlTable table = (IHtmlTable)grid[0];
			
			OrderInfo order = null;
			for(int i=1;i<table.rowCount();i++){
				String temp = table.getCellValue(i, table.findColumn(0, ORDER_NUM_TAN_COL));
				if(temp.startsWith(ordNum)){
					order = new OrderInfo();
					order.orderNum = ordNum;
					order.receiptNum = table.getCellValue(i, table.findColumn(0, RECEIPT_NUM_COL));
					order.orderDate = table.getCellValue(i, table.findColumn(0, ORDER_DATE_COL));
					order.product = table.getCellValue(i, table.findColumn(0, PRODUCT_COL));
					order.status = table.getCellValue(i, table.findColumn(0, ORDER_ITEM_STATUS_COL));
					RegularExpression reg = new RegularExpression("^Sales Location.*", false);
					order.saleLocation = table.getCellValue(i, table.findColumn(0, reg));
					order.orderPrice = table.getCellValue(i, table.findColumn(0, ORDER_PRICE_COL)).replaceAll("\\$", "");
					break;
				}
			}
			Browser.unregister(grid);
			return order;
		} 
		
		
}
