package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.pospurchaseorder;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPurchaseOrderInfo;
import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  Jun 18, 2012
 */
public class InvMgrPOSPurchaseOrderSearchPage extends InvMgrCommonTopMenuPage {
    public static InvMgrPOSPurchaseOrderSearchPage instance = null;
    
    private InvMgrPOSPurchaseOrderSearchPage(){};
    
    public static InvMgrPOSPurchaseOrderSearchPage getInstance(){
    	if(null == instance){
    		instance = new InvMgrPOSPurchaseOrderSearchPage();
    	}
		return instance;
    }
    
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("SearchBar_\\d+",false));
	}
	
	private String prefix = "POSearchCriteria-\\d+.";
	public void setPONum(String PONum){
		browser.setTextField(".id", new RegularExpression(prefix+"poId",false), PONum);
	}
	
	public void setStatus(String status){
		if(!"".equals(status)) {
			browser.selectDropdownList(".id", new RegularExpression(prefix+"status",false), status);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefix+"status",false), 0);
		}
	}
	
	public void setSupplierName(String supplierName){
		browser.setTextField(".id", new RegularExpression(prefix+"supplierName",false), supplierName);
	}
	
	public void setStartDate(String startDate){
		browser.setTextField(".id", new RegularExpression(prefix+"fromDate_ForDisplay",false), startDate);
	}
	
	public void setEndDate(String endDate){
		browser.setTextField(".id", new RegularExpression(prefix+"toDate_ForDisplay",false), endDate);
	}
	
	public void setSearchCriteria(POSPurchaseOrderInfo searchInfo){
		if(null != searchInfo.searchPONum){
			this.setPONum(searchInfo.searchPONum);
		}
		if(null != searchInfo.searchStatus){
			this.setStatus(searchInfo.searchStatus);
		}
		if(null != searchInfo.searchSupplierName){
			this.setSupplierName(searchInfo.searchSupplierName);
		}
		if(null != searchInfo.searchCreationStartDate){
			this.setStartDate(searchInfo.searchCreationStartDate);
		}
		if(null != searchInfo.searchCreationEndDate){
			this.setEndDate(searchInfo.searchCreationEndDate);
		}
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void searchPOPurchaseOrder(POSPurchaseOrderInfo searchInfo){
		this.setSearchCriteria(searchInfo);
		this.clickSearch();
		ajax.waitLoading();
	}
	
	private IHtmlTable getPOListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("polist_LIST", false));
		if(objs.length <= 0){
			throw new ItemNotFoundException("Can't find search result list.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
//		Browser.unregister(objs);
		return table;
	}

	public POSPurchaseOrderInfo getAAOrderInfo(String poNum){
		return this.getPOSPurchaseOrderInfo("", poNum);
	}
	
	public POSPurchaseOrderInfo getOrderInfoWithOpenStatus(String poNum){
		return this.getPOSPurchaseOrderInfo("1", poNum);
	}
	
	public POSPurchaseOrderInfo getOrderInfoWithCloseStatus(String poNum){
		return this.getPOSPurchaseOrderInfo("2", poNum);
	}
	
	public POSPurchaseOrderInfo getOrderInfoWithCancelStatus(String poNum){
		return this.getPOSPurchaseOrderInfo("3", poNum);
	}
	
	private static final String PO_NUM_COL = "PO #";
	private static final String SUPPLIER_NAME_COL = "Supplier Name";
	private static final String ITEMS_ORDERED_COL = "Items Ordered";
	//private static final String ITEMS_RECEIVED_COL = "Items Received";
	private static final String ORDER_CREATION_DATE = "Order Creation Date";
	private static final String ORDER_CLOSED_DATE = "Order Closed Date";
	private static final String ORDER_CANCELLATION_DATE = "Order Cancellation Date";
	private static final String RECEIVE_COL = "Receive";
	
	private POSPurchaseOrderInfo getPOSPurchaseOrderInfo(String status, String poNum){
		IHtmlTable table = this.getPOListTable();
		POSPurchaseOrderInfo orderInfo = new POSPurchaseOrderInfo();
		int poNumColIndex = table.findColumn(0, PO_NUM_COL);
		int supplierNameColIndex = table.findColumn(0, SUPPLIER_NAME_COL);
		int itemsOrderedColIndex = table.findColumn(0, ITEMS_ORDERED_COL);
		int orderCreationDateColIndex = table.findColumn(0, ORDER_CREATION_DATE);
		
		int row = 0;
		if(!"1".equals(status)){// status is blank or closed or cancelled
			row = table.findRow(0, poNum);
			orderInfo.poNum = table.getCellValue(row, poNumColIndex);
			orderInfo.supplierName = table.getCellValue(row, supplierNameColIndex);
			orderInfo.itemOrdered = table.getCellValue(row, itemsOrderedColIndex);
			orderInfo.creationDate = table.getCellValue(row, orderCreationDateColIndex);
			if("2".equalsIgnoreCase(status)){//2--closed
				orderInfo.closedDate = table.getCellValue(row, table.findColumn(0, ORDER_CLOSED_DATE));
			} else if("3".equals(status)) {//3--Cancelled
				orderInfo.cancelledDate = table.getCellValue(row, table.findColumn(0, ORDER_CANCELLATION_DATE));
			}
		} else {// open status
			int colNum = table.findColumn(0, "PO #");
			row = table.findRow(colNum, poNum);
			orderInfo.poNum = table.getCellValue(row, poNumColIndex);
			orderInfo.supplierName = table.getCellValue(row, supplierNameColIndex);
			orderInfo.itemOrdered = table.getCellValue(row, itemsOrderedColIndex);
			orderInfo.creationDate = table.getCellValue(row, orderCreationDateColIndex);
			orderInfo.receive = table.getCellValue(row, table.findColumn(0, RECEIVE_COL));
		}
		
		return orderInfo;
	}

	public boolean verifyPONumExist(String exceptPONum){
		PagingComponent turnPage = new PagingComponent();
		List<String> tempList;
		boolean isExist = false;
		do{
			IHtmlTable table = this.getPOListTable();
			int col = table.findColumn(0, "PO #");
			tempList = new ArrayList<String>();
			tempList = table.getColumnValues(col);
			if(tempList.contains(exceptPONum)){
				isExist = true;
				break;
			} else {
				if(turnPage.nextExists()){
					turnPage.clickNext();
				} else {
					break;
				}
			}
		} while(!isExist);
		return isExist;
	}
	
	public List<String> getColumnByName(String columnName){
		List<String> valueList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();

		IHtmlTable table = this.getPOListTable();
		int col = table.findColumn(0, columnName);
		tempList = table.getColumnValues(col);
		tempList.remove(0);
		valueList.addAll(tempList);

		PagingComponent turnPageComponent = new PagingComponent();
		while (turnPageComponent.nextExists(true)) {
			turnPageComponent.clickNext();
			ajax.waitLoading();
			
			table = this.getPOListTable();
			col = table.findColumn(0, columnName);
			tempList = new ArrayList<String>();
			tempList = table.getColumnValues(col);
			tempList.remove(0);
			valueList.addAll(tempList);
		}
		return valueList;
	}
	
	public void clickCreatePurchaseOrder(){
		if(browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Create Purchase Order")){
			browser.clickGuiObject(".class", "Html.A", ".text", "Create Purchase Order");
		}
	}
	
	public void clickClosePurchaseOrder(){
		if(browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Close Purchase Order")){
			browser.clickGuiObject(".class", "Html.A", ".text", "Close Purchase Order");
		}
	}
	
	public void clickCancelPurchaseOrder(){
		if(browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Cancel Purchase Order")){
			browser.clickGuiObject(".class", "Html.A", ".text", "Cancel Purchase Order");
		}
	}
	
	public void clickPrintPurchaseOrder(){
		if(browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Print Purchase Order")){
			browser.clickGuiObject(".class", "Html.A", ".text", "Print Purchase Order");
		}
	}
	
	public void clickPONum(String poNum){
		browser.clickGuiObject(".class", "Html.A", ".text", poNum, true);
	}
	
	public void clearSearchCriteria(){
		this.setPONum("");
		this.setStatus("");
		this.setSupplierName("");
		this.setStartDate("");
		this.setEndDate("");
	}
	
	public boolean checkReceiveExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Receive Qty");
	}
	
	public void clickReceiveQty(int index){
		browser.clickGuiObject(".class", "Html.A", ".text", "Receive Qty", true, index);
	}

	public void selectRadioButton(int index){
		browser.selectRadioButton(".id", new RegularExpression("GenericGrid-\\d+.selectedItems", false), index);
	}
	
	public void setRowsPerPage(String rows){
		browser.selectDropdownList(".id", new RegularExpression("GridPagingBar-\\d+.rowsPerPage", false), rows, true);
	}
	
	public void clickColumnName(String columnName){
		browser.clickGuiObject(".class", "Html.A", ".text", columnName);
	}
	
	public void searchPosPurchaseOrderByPONum(String PONum){
		this.setPONum(PONum);
		this.clickSearch();
		ajax.waitLoading();
	}
	
	public int getRowNumForPoNum(String poNum) {
		IHtmlTable table = this.getPOListTable();
		int index= -1;
		index = table.findRow(table.findColumn(0, PO_NUM_COL), poNum);
		Browser.unregister(table);
		if(index<0)
			throw new ItemNotFoundException("Could not get row number for po num "+poNum);
		return index;
	}
}
