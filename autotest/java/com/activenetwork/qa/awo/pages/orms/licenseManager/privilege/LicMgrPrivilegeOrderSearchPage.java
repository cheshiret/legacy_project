package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderSearchCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrPrivilegeOrderSearchPage extends LicMgrOrderSearchCommonPage {
	private static LicMgrPrivilegeOrderSearchPage _instance = null;

	protected LicMgrPrivilegeOrderSearchPage() {

	}

	public static LicMgrPrivilegeOrderSearchPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrPrivilegeOrderSearchPage();
		}

		return _instance;
	}

	public boolean exists() {
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id",new RegularExpression("SearchBar_\\d+",false));
		Property[] p2=Property.toPropertyArray(".class","Html.SELECT",".name",new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.searchTypeID",false));
		return browser.checkHtmlObjectExists(Property.atList(p1,p2));
	}
	/**
	 * Select the search type
	 * @param String -- search type.
	 */
	public void selectSearchType(String searchType){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.searchTypeID",false), searchType);
	}

	/**
	 * Set the search value
	 * @param String -- search value.
	 */
	public void setSearchValue(String searchValue){
		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.searchValue",false), searchValue);
	}
	/**
	 * Set the product code.
	 * @param String -- product code.
	 */
	public void setProductCode(String code){
		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.productCode",false), code);
	}

	/**
	 * select the verification status
	 * @param String -- verification status.
	 */
	public void selectVerificationSatus(String status){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.verificationStatus",false), status);
	}
	/**
	 * select the purchase type.
	 * @param String -- purchase type.
	 */
	public void selectPurchaseType(String purchaseType){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.purchaseType",false), purchaseType);
	}
	/**
	 * select the residency status
	 * @param String -- residency  status.
	 */
	public void selectResidencyStatus(String resStatus){
		browser.selectDropdownList(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.residencyStatus",false), resStatus);
	}
	/**
	 * set order from date.
	 * @param String -- order from date.
	 */
	public void setOrderFromDate(String fromDate){
		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.createFromDate_ForDisplay",false), fromDate, IText.Event.LOSEFOCUS);
	}
	/**
	 * set order to date.
	 * @param String -- order to date.
	 */
	public void setOrderToDate(String toDate){
		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.createToDate_ForDisplay",false), toDate, IText.Event.LOSEFOCUS);
	}
	/**
	 * set sales location .
	 * @param String -- sales location.
	 */
	public void setSaleLocation(String location){
		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.salesLocation",false), location);
	}
	/**
	 * set operator first name .
	 * @param String -- operator first name.
	 */
	public void setOperatorFirstName(String fName){
		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.firstName",false), fName);
	}
	/**
	 * set operator last name .
	 * @param String -- operator last name.
	 */
	public void setOperatorLastName(String lName){
		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.lastName",false), lName);
	}

	/**
	 * Click search button
	 */
//	public void clickSearch() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Search", true);
//		ajax.waitLoading();
//	}

	@Override
	public void setupOrderSearchCriteria(Object object) {
		// TODO Finish it

	}

	/**
	 * Click the next button
	 * */
	public boolean clickNext(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				new RegularExpression("Next", false));

		String isDisable = objs[0].getProperty(".isDisabled");
		boolean toReturn = false;
		if (isDisable.equals("true")) {
			toReturn = true;
			objs[0].click();
		}
		Browser.unregister(objs);
		ajax.waitLoading();
		return toReturn;
	}

	/**
	 * Search the privilege by privilege orderInfo.
	 * @param OrderInfo - the info of order.
	 * @return
	 */
	public void setPrivilegeOrderSearchCriteria(OrderInfo searchOrder){
		if(searchOrder.orderType.length()>0){
			this.selectSearchType(searchOrder.orderType);
		}
		if(searchOrder.value.length()>0){
			this.setSearchValue(searchOrder.value);
		}
		if(searchOrder.productCode.length()>0){
			this.setProductCode(searchOrder.productCode);
		}
		if(searchOrder.verificationStatus.length()>0){
			this.selectVerificationSatus(searchOrder.verificationStatus);
		}
		if(searchOrder.purchaseType.length()>0){
			this.selectPurchaseType(searchOrder.purchaseType);
		}
		if(searchOrder.residencyStatus.length()>0){
			this.selectResidencyStatus(searchOrder.residencyStatus);
		}
		if(searchOrder.orderFromDate.length()>0){
			this.setOrderFromDate(searchOrder.orderFromDate);
		}
		if(searchOrder.orderToDate.length()>0){
			this.setOrderToDate(searchOrder.orderToDate);
		}
		if(searchOrder.saleLocation.length()>0){
			this.setSaleLocation(searchOrder.saleLocation);
		}
		if(searchOrder.operatorFirstName.length()>0){
			this.setOperatorFirstName(searchOrder.operatorFirstName);
		}
		if(searchOrder.operatorLastName.length()>0){
			this.setOperatorLastName(searchOrder.operatorLastName);
		}
	}

	/**
	 * Clear up the search criteria.
	 */
	public void clearSearchCriteria() {
		this.selectSearchType("Order #");
		this.setSearchValue("");
		this.setProductCode("");
		this.setProductCode("");
		this.selectVerificationSatus("");
		this.selectPurchaseType("");
		this.selectResidencyStatus("");
		this.setOrderFromDate("");
		this.setOrderToDate("");
		this.setSaleLocation("");
		this.setOperatorFirstName("");
		this.setOperatorLastName("");
	}

	/**
	 * Clear up the search criteria.
	 */
	public void searchPrivilegeOrder(OrderInfo searchOrder) {
		this.setPrivilegeOrderSearchCriteria(searchOrder);
		this.clickSearch();
		ajax.waitLoading();
	}

	/**
	 * Get the column index.
	 */
	public int getColIndex(String colName) {
		int colNum = 0;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".id", "privilegeOrderList_LIST");
		;
		if (objs.length > 0) {
			IHtmlTable cusTableGrid = (IHtmlTable) objs[0];
			colNum = cusTableGrid.findColumn(0, colName);
		} else
			throw new ObjectNotFoundException("Object can't find.");

		Browser.unregister(objs);
		return colNum;
	}

	public IHtmlTable getPrivOrderListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeOrderList_LIST", ".className", "table table-striped gridView");
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find the specific privilege order");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		Browser.unregister(objs);
		return table;
	}
	
	public IHtmlObject[] getPrivOrderListRecordsObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.concatPropertyArray(tr(), ".className", "oddRow"));
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find any oddRow TR in privilege order list page");
		}
		return objs;
	}
	/**
	 * Get all order info list
	 *
	 * @return -- get all the order info list.
	 */
	public List<List<String>> getAllOrderInfoInList() {
		List<List<String>> orderInfoList = new ArrayList<List<String>>();
		List<String> orderRowInfo = new ArrayList<String>();
		do {
			IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeOrderList_LIST");
			if (objs.length < 1) {
				throw new ErrorOnDataException(
						"Can't find the specific privilege order");
			}
			IHtmlTable table = (IHtmlTable) objs[0];
			if (table.rowCount() > 1) {
				for (int i = 1; i < table.rowCount(); i++) {
					orderRowInfo = table.getRowValues(i);
					orderInfoList.add(orderRowInfo);
				}
			} else {
				throw new ErrorOnDataException(
						"No privilege order info is retrived!");
			}
			Browser.unregister(objs);
		} while (this.clickNext());

		return orderInfoList;
	}

//	public boolean checkOrderExist(String orderNum) {
//		IHtmlTable table = getPrivOrderListTable();
//		System.out.println(table.columnCount());
//		System.out.println(table.rowCount());
//		System.out.println(12);
//		if(table.getColumnValues(0).toString().contains(orderNum)){
//			return true;
//		}else return false;
//	}
	
	public boolean checkOrderExist(String orderNum) {
		boolean result = false;
		
		IHtmlObject[] objs = getPrivOrderListRecordsObjs();
		for(int i=0; i<objs.length; i++){
			if(objs[i].text().contains(orderNum)){
				result = true;
				break;
			}else result = false;
		}
		
		Browser.unregister(objs);
		return result;
	}
	
	public void verifyOrderRecordExist(String orderNum, boolean existed){
		boolean resultFromUI = checkOrderExist(orderNum);
		if(resultFromUI!=existed){
			throw new ErrorOnPageException("Order number:"+orderNum+" record should "+(existed?"":"not ")+"exist");
		}
		logger.info("Successfully verify order number:"+orderNum+" record "+(existed?"exists":"doesn't exist "));
	}
	
	/**
	 * Verify the search privilege order result info.
	 *
	 * @param orderInfo
	 *            -- the order info get from order list table.
	 * @param searchCriteria
	 *            -- the search criteria.
	 * @param colName
	 *            -- the column name.
	 */
	public void verifySearchPrivilegeOrderResult(List<List<String>> orderInfo,
			String searchCriteria, String colName) {
		List<String> orderRowInfo = new ArrayList<String>();
		if (searchCriteria.length() > 0) {
			int colIndex = this.getColIndex(colName);
			if (orderInfo.size() >= 1)
				for (int i = 0; i < orderInfo.size(); i++) {
					orderRowInfo = orderInfo.get(i);
					if (colName.equals("Customer")||colName.equals("Sales Location")) {
						if (!orderRowInfo.get(colIndex)
								.contains(searchCriteria)) {
							throw new ErrorOnDataException(searchCriteria
									+ orderRowInfo.get(colIndex)
									+ " doesn't match customer info");
						}
					}else if(colName.equals("Creation Date")) {
//						System.out.println(orderRowInfo.get(colIndex));
//						String temp = orderRowInfo.get(colIndex).subSequence(0, 10)+ orderRowInfo.get(colIndex).split("EDT")[1];
						String temp = orderRowInfo.get(colIndex);
						if(!DateFunctions.formatDate(temp, "E MMM dd yyyy").equals(DateFunctions.formatDate(searchCriteria, "E MMM dd yyyy"))){
								throw new ErrorOnDataException(searchCriteria
										+ temp
										+ " doesn't match customer info");
						}
						
					}
					else {
						if (!orderRowInfo.get(colIndex).toString().equals(
								searchCriteria)) {
							throw new ErrorOnDataException(searchCriteria
									+ orderRowInfo.get(colIndex)
									+ " doesn't match customer info");
						}
					}
				}
			else {
				throw new ErrorOnDataException("No matched " + colName
						+ " found");
			}
		}
	}

	/**
	 * Verify the search privilege order result info.
	 *
	 * @param orderInfo
	 *            -- the order info get from order list table.
	 */

	public void verifySearchPrivilegeOrderResult(OrderInfo searchOrder) {
		List<List<String>> searchList = new ArrayList<List<String>>();
		searchList = this.getAllOrderInfoInList();
		if (searchOrder.orderType.equals("Order #")
				&& searchOrder.value.length() > 0) {
			this.verifySearchPrivilegeOrderResult(searchList,
					searchOrder.value, "Order #");
		}
		if (searchOrder.orderType.equals("Receipt #")
				&& searchOrder.value.length() > 0) {
			this.verifySearchPrivilegeOrderResult(searchList,
					searchOrder.value, "Receipt #");
		}
		if (searchOrder.verificationStatus.length() > 0) {
			this.verifySearchPrivilegeOrderResult(searchList,
					searchOrder.verificationStatus, "Verification Status");
		}
		if (searchOrder.operatorFirstName.length() > 0) {
			this.verifySearchPrivilegeOrderResult(searchList,
					searchOrder.operatorFirstName, "Customer");
		}
		if (searchOrder.operatorLastName.length() > 0) {
			this.verifySearchPrivilegeOrderResult(searchList,
					searchOrder.operatorLastName, "Customer");
		}
		if (searchOrder.saleLocation.length() > 0) {
			this.verifySearchPrivilegeOrderResult(searchList,
					searchOrder.saleLocation, "Sales Location");
		}
		if (searchOrder.numOfItems.length() > 0) {
			this.verifySearchPrivilegeOrderResult(searchList,
					searchOrder.numOfItems, "# of Items");
		}
		if (searchOrder.orderPrice.length() > 0) {
			this.verifySearchPrivilegeOrderResult(searchList,
					searchOrder.orderPrice, "Order Price");
		}
		if (searchOrder.balance.length() > 0) {
			this.verifySearchPrivilegeOrderResult(searchList,
					searchOrder.balance, "Balance");
		}

		if(searchOrder.orderFromDate.length()>0){
			this.verifySearchPrivilegeOrderResult(searchList, searchOrder.orderFromDate, "Creation Date");
		}
		if(searchOrder.orderToDate.length()>0){
			this.verifySearchPrivilegeOrderResult(searchList, searchOrder.orderToDate, "Creation Date");
		}

	}

	public String getOrderItemStatus(String priPurchasingName){
		String status;

		IHtmlObject[] objs=browser.getTableTestObject(".id", "privilegeOrderItemsList");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find Order item list table");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int row=table.findRow(0, "("+DateFunctions.getCurrentYear()+") "+priPurchasingName.replaceAll("-", " - "));
		status=table.getCellValue(row, 2);
		Browser.unregister(objs);
		return status;
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

}
