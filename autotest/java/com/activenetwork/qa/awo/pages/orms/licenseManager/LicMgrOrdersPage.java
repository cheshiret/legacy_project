package com.activenetwork.qa.awo.pages.orms.licenseManager;
//package testAPI.pages.orms.licenseManager;
//
//import runtime.Browser;
//import runtime.HtmlObject;
//import runtime.ITable;
//import runtime.IText;
//
///**
// * ScriptName: LicMgrOrdersPage
// * Description:
// * @date: Jan 16, 2011
// * @author qchen
// *
// */
//public class LicMgrOrdersPage extends LicMgrCommonTopMenuPage {
//	private static LicMgrOrdersPage _instance = null;
//
//	protected LicMgrOrdersPage() {
//
//	}
//
//	public static LicMgrOrdersPage getInstance() {
//		if(null == _instance) {
//			_instance = new LicMgrOrdersPage();
//		}
//
//		return _instance;
//	}
//
//	@Override
//	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "OrdersTabs");
//	}
//
//	/**
//	 * Switch different sub page with clicking each tab
//	 * @param tabName
//	 */
//	private void switchOrderTab(String tabName) {
//		if(null == tabName || tabName.length() == 0) {
//			throw new ActionFailedException("Can't find specified Tab with tab name - " + tabName);
//		}
//
//		browser.clickGuiObject(".class", "Html.A", ".text", tabName.trim());
//		ajax.waitLoading();
//	}
//
//	/**
//	 * Click Privilege Orders tab
//	 */
//	public void clickPrivilegeOrders() {
//		switchOrderTab("Privilege Orders");
//	}
//
//	/**
//	 * Click Vehicle Orders tab
//	 */
//	public void clickVehicleOrders() {
//		switchOrderTab("Vehicle Orders");
//	}
//
//	/**
//	 * Click Consumables Orders tab
//	 */
//	public void clickConsumablesOrders() {
//		switchOrderTab("Consumables Orders");
//	}
//
//	/**
//	 * Click Supplies Orders tab
//	 */
//	public void clickSuppliesOrders() {
//		switchOrderTab("Supplies Orders");
//	}
//
//	/**
//	 * Click Receipts tab
//	 */
//	public void clickReceipts() {
//		switchOrderTab("Receipts");
//	}
//
//	/**
//	 * Select privilege orders search type
//	 * @param searchType
//	 */
//	public void setPrivilegeOrderSearchType(String searchType) {
//		browser.selectDropdownList(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.searchTypeID", false), searchType, true);
//	}
//
//	/**
//	 * Set privilege orders search value
//	 * @param searchValue
//	 */
//	public void setPrivilegeOrdersSearchValue(String searchValue) {
//		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.searchValue", false), searchValue, true);
//	}
//
//	/**
//	 * Set privilege orders product code
//	 * @param productCode
//	 */
//	public void setPrivilegeOrdersProductCode(String productCode) {
//		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.productCode", false), productCode, true);
//	}
//
//	/**
//	 * Select privilege orders verification status
//	 * @param verificationStatus
//	 */
//	public void selectPrivilegeOrdersVerificationStatus(String verificationStatus) {
//		browser.selectDropdownList(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.verificationStatus", false), verificationStatus, true);
//	}
//
//	/**
//	 * Select privilege orders purchase type
//	 * @param purchaseType
//	 */
//	public void selectPrivilegeOrdersPurchaseType(String purchaseType) {
//		browser.selectDropdownList(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.purchaseType", false), purchaseType, true);
//	}
//
//	/**
//	 * Select privilege orders residency status
//	 * @param residencyStatus
//	 */
//	public void selectPrivilegeOrdersResidencyStatus(String residencyStatus) {
//		browser.selectDropdownList(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.residencyStatus", false), residencyStatus, true);
//	}
//
//	/**
//	 * Set privilege orders from date
//	 * @param fromDate
//	 */
//	public void setPrivilegeOrdersFromDate(String fromDate) {
//		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.createFromDate_ForDisplay", false), fromDate, true);
//	}
//
//	/**
//	 * Set privilege orders to date
//	 * @param toDate
//	 */
//	public void setPrivilegeOrdersToDate(String toDate) {
//		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.createToDate_ForDisplay", false), toDate, true);
//	}
//
//	/**
//	 * Set privilege orders sales location
//	 * @param salesLocation
//	 */
//	public void setPrivilegeOrdersSalesLocation(String salesLocation) {
//		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.salesLocation", false), salesLocation, true);
//	}
//
//	/**
//	 * Set privilege orders first name
//	 * @param fName
//	 */
//	public void setPrivilegeOrdersOperatorFirstName(String fName) {
//		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.firstName", false), fName, true);
//	}
//
//	/**
//	 * Set privilege orders last name
//	 * @param lName
//	 */
//	public void setPrivilegeOrdersOperatorLastName(String lName) {
//		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.lastName", false), lName, true);
//	}
//
//	/**
//	 * Click search button for all 4 sub-pages
//	 */
//	public void clickSearch() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Search", true);
//		ajax.waitLoading();
//	}
//
//	/**
//	 * Click Go button in Receipts sub-page
//	 */
//	public void clickGo() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Go", true);
//	}
//
//	/**
//	 * Select a specified privilege order according privilege order number
//	 * @param privilegeOrdNum
//	 */
//	public void selectPrivilegeOrder(String privilegeOrdNum) {
//		if(privilegeOrdNum == null || privilegeOrdNum.length() == 0) {
//			throw new ItemNotFoundException("Please set the Privilege Order Number.");
//		}
//
//		browser.clickGuiObject(".class", "Html.A", ".text", privilegeOrdNum, true);
//		ajax.waitLoading();
//	}
//
//	/**
//	 * Void or reverse privilege order
//	 */
//	public void voidOrReversePrivilegeOrder() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Void/Reverse", true);
//	}
//
//	/**
//	 * Undo void or reverse privilege order
//	 */
//	public void undoVoidOrReversePrivilegeOrder() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Undo Void/Reverse", true);
//	}
//
//	/**
//	 * Check whether the 'Invalidate Order' button exists or not
//	 * @return
//	 */
//	public boolean isInvalidatePrivilegeOrderExists() {
//		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Invalidate Order");
//	}
//
//	/**
//	 * Check whether the 'Reactive Order' button exists or not
//	 * @return
//	 */
//	public boolean isReactivePrivilegeOrderExists() {
//		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Reactivate Order");
//	}
//
//	/**
//	 * Invalidate privilege order
//	 */
//	public void invalidatePrivilegeOrder() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Invalidate Order", true);
//	}
//
//	/**
//	 * Reactivate privilege order
//	 */
//	public void reactivatePrivilegeOrder() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Reactivate Order", true);
//	}
//
//	/**
//	 * Reprint privilege
//	 */
//	public void reprintPrivilege() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Reprint Privilege", true);
//	}
//
//	/**
//	 * Add the current privilege order to cart
//	 */
//	public void addPrivilegeOrderToCart() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Add to Cart", true);
//	}
//
//	/**
//	 * Click Fees button to goto privilege order fee detail page
//	 */
//	public void clickPrivilegeOrderFees() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Fees", true);
//	}
//
//	/**
//	 * Click Receipts button go to privilege order receipts detail page
//	 */
//	public void clickPrivilegeOrderReceipts() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Receipts", true);
//	}
//
//	/**
//	 * Click History button to goto privilege order history page
//	 */
//	public void clickPrivilegeOrderHistory() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "History", true);
//	}
//
//	/**
//	 * Method to get the current privilege order's all attributes' value
//	 * @param attributeName
//	 * @param index
//	 * @return
//	 */
//	private String getPrivilegeOrderAttributeValueByName(String attributeName, int index) {
//		HtmlObject divObjs[] = null;
//		HtmlObject textFieldObjs[] = null;
//		String attributeValue = "";
//		Property property[] = new Property[3];
//
//		if(attributeName.equalsIgnoreCase("Receipt #") || attributeName.equalsIgnoreCase("MDWFP #")) {
//			//get the DIV object which includes a Link object and a Label object according to the corresponding attribute name
//			property[0] = new Property(".class", "Html.DIV");
//			property[1] = new Property(".className", "inputwithrubylabel");
//			property[2] = new Property(".text", new RegularExpression(attributeName, false));
//			divObjs = browser.getHtmlObject(property);
//
//			if(divObjs.length > 0){
//				attributeValue = divObjs[0].getProperty(".text").split(attributeName)[1].trim();
//			}
//		} else {
//			//get the DIV object which includes a IText and a Label object according to the corresponding attribute name
//			property[0] = new Property(".class", "Html.DIV");
//			property[1] = new Property(".className", "inputwithrubylabel readonly");
//			property[2] = new Property(".text", attributeName);
//			divObjs = browser.getHtmlObject(property);
//
//			//get the TextField object according the matched label
//			textFieldObjs = browser.getTextField(new Property[]{new Property(".className", "readonly")}, divObjs[index]);
//			if(textFieldObjs.length > 0) {
//				attributeValue = ((IText)textFieldObjs[0]).getText().trim();
//			}
//		}
//
//		if(divObjs.length == 0) {
//			throw new ItemNotFoundException("Can't find attribute according to the attribute name - " + attributeName);
//		}
//
//		Browser.unregister(divObjs);
//		Browser.unregister(textFieldObjs);
//		return attributeValue;
//	}
//
//	/**
//	 * Overloaded method to get the current privilege order's all attributes' value
//	 * @param attributeName
//	 * @return
//	 */
//	private String getPrivilegeOrderAttributeValueByName(String attributeName) {
//		return getPrivilegeOrderAttributeValueByName(attributeName, 0);
//	}
//
//	/**
//	 * Get the privilege order number
//	 * @return
//	 */
//	public String getPrivilegeOrderNum() {
//		return getPrivilegeOrderAttributeValueByName("Order #");
//	}
//
//	/**
//	 * Get the privilege order verification status
//	 * @return
//	 */
//	public String getPrivilegeOrderVerificationStatus() {
//		return getPrivilegeOrderAttributeValueByName("Verification Status");
//	}
//
//	/**
//	 * Get the privilege order sales location
//	 * @return
//	 */
//	public String getPrivilegeOrderSalesLocation() {
//		return getPrivilegeOrderAttributeValueByName("Sales Location");
//	}
//
//	/**
//	 * Get the privilege order creation date
//	 * @return
//	 */
//	public String getPrivilegeOrderCreationDate() {
//		return getPrivilegeOrderAttributeValueByName("Creation Date");
//	}
//
//	/**
//	 * Get the privilege order creation user
//	 * @return
//	 */
//	public String getPrivilegeOrderCreationUser() {
//		return getPrivilegeOrderAttributeValueByName("Creation User");
//	}
//
//	/**
//	 * Get the privilege order balance
//	 * @return
//	 */
//	public String getPrivilegeOrderBalance() {
//		return getPrivilegeOrderAttributeValueByName("Balance");
//	}
//
//	/**
//	 * Get the privilege order price
//	 * @return
//	 */
//	public String getPrivilegeOrderPrice() {
//		return getPrivilegeOrderAttributeValueByName("Price");
//	}
//
//	/**
//	 * Get the privilege order paid
//	 * @return
//	 */
//	public String getPrivilegeOrderPaid() {
//		return getPrivilegeOrderAttributeValueByName("Paid");
//	}
//
//	/**
//	 * Get the privilege order un-issued refund
//	 * @return
//	 */
//	public String getPrivilegeOrderUnissuedRefund() {
//		return getPrivilegeOrderAttributeValueByName("Unissued Refund");
//	}
//
//	/**
//	 * Get the privilege order confirmation status
//	 * @return
//	 */
//	public String getPrivilegeOrderConfirmationStatus() {
//		return getPrivilegeOrderAttributeValueByName("Confirmation Status");
//	}
//
//	/**
//	 * Get the privilege order invoice number
//	 * @return
//	 */
//	public String getPrivilegeOrderInvoiceNum() {
//		return getPrivilegeOrderAttributeValueByName("Invoice #");
//	}
//
//	/**
//	 * Get the privilege order receipt number
//	 * @return
//	 */
//	public String getPrivilegeOrderReceiptNum() {
//		return getPrivilegeOrderAttributeValueByName("Receipt #");
//	}
//
//	/**
//	 * Get the privilege order billing customer name
//	 * @return
//	 */
//	public String getPrivilegeOrderBillingCustName() {
//		return getPrivilegeOrderAttributeValueByName("Customer Name");
//	}
//
//	/**
//	 * Get the privilege order billing customer phone number
//	 * @return
//	 */
//	public String getPrivilegeOrderBillingCustPhoneNum() {
//		return getPrivilegeOrderAttributeValueByName("Phone Number");
//	}
//
//	/**
//	 * Get the privilege order billing customer email address
//	 * @return
//	 */
//	public String getPrivilegeOrderBillingCustEmailAddress() {
//		return getPrivilegeOrderAttributeValueByName("Email Address");
//	}
//
//	/**
//	 * Get the privilege order privilege customer number
//	 * @return
//	 */
//	public String getPrivilegeOrderPrivCustNum() {
//		return getPrivilegeOrderAttributeValueByName("MDWFP #");
//	}
//
//	/**
//	 * Get the privilege order privilege customer class
//	 * @return
//	 */
//	public String getPrivilegeOrderPrivCustClass() {
//		return getPrivilegeOrderAttributeValueByName("Customer Class");
//	}
//
//	/**
//	 * Get the privilege order privilege customer name
//	 * @return
//	 */
//	public String getPrivilegeOrderPrivCustName() {
//		return getPrivilegeOrderAttributeValueByName("Customer Name", 1);
//	}
//
//	/**
//	 * Get the privilege order privilege customer business
//	 * @return
//	 */
//	public String getPrivilegeOrderPrivCustBusinessNmae() {
//		return getPrivilegeOrderAttributeValueByName("Business Name");
//	}
//
//	/**
//	 * Get the privilege order privilege customer phone number
//	 * @return
//	 */
//	public String getPrivilegeOrderPrivCustPhoneNum() {
//		return getPrivilegeOrderAttributeValueByName("Phone Number", 1);
//	}
//
//	/**
//	 * Get the privilege order privilege customer email address
//	 * @return
//	 */
//	public String getPrivilegeOrderPrivCustEmailAddress() {
//		return getPrivilegeOrderAttributeValueByName("Email Address", 1);
//	}
//
//	/**
//	 * Get the privilege order identifier used
//	 * @return
//	 */
//	public String getPrivilegeOrderIdentifierUsed() {
//		return getPrivilegeOrderAttributeValueByName("Identifier Used");
//	}
//
//	/**
//	 * Get the privilege order identifier number
//	 * @return
//	 */
//	public String getPrivilegeOrderIdentifierNum() {
//		return getPrivilegeOrderAttributeValueByName("Identifier #");
//	}
//
//	/**
//	 * Get the privilege order identifier verification status
//	 * @return
//	 */
//	public String getPrivilegeOrderIdentifierVerificationStatus() {
//		return getPrivilegeOrderAttributeValueByName("Verification Status", 1);
//	}
//
//	/**
//	 * Get the privilege order identifier additional proof of residency
//	 * @return
//	 */
//	public String getPrivilegeOrderIdentifierAdditionalProofOfResidency() {
//		return getPrivilegeOrderAttributeValueByName("Additional Proof of Residency");
//	}
//
//	/**
//	 * Click OK button
//	 */
//	public void clickOK() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
//	}
//
//	/**
//	 * Click the privilege number link to goto the privilege detail page
//	 * @param privilegeNumber
//	 */
//	public void selectPrivilegeByPrivilegeNum(String privilegeNumber) {
//		browser.clickGuiObject(".class", "Html.A", ".text", privilegeNumber, true);
//	}
//
//	public void selectPrivilegeByPrivilegeName(String privilegeName) {
//	}
//
//	/**
//	 * Select the first privilege by clicking the privilege number
//	 */
//	public String selectFirstPrivilege() {
//		HtmlObject itemsLsitTable[] = browser.getTableTestObject(".id", "privilegeOrderItemsList");
//		String privilegeNumber = "";
//		if(itemsLsitTable.length > 0) {
//			privilegeNumber = ((ITable)itemsLsitTable[0]).getCellValue(1, 1).trim();
//		}
//
//		browser.clickGuiObject(".class", "Html.A", ".text", privilegeNumber, true);
//		Browser.unregister(itemsLsitTable);
//		return privilegeNumber;
//	}
//
//	/**
//	 * Parse the privilege order items list table and return the needed column value with privilege number
//	 * @param privilegeNumber
//	 * @param needColumnName
//	 * @return
//	 */
//	private String parsePrivilegeOrderItemsListTable(String privilegeNumber, String needColumnName) {
//		HtmlObject objs[] = browser.getTableTestObject(".id", "privilegeOrderItemsList");
//		if(objs.length == 0) {
//			throw new ItemNotFoundException("Can't find Privilege Order Items List table.");
//		}
//		ITable itemsLsitTable = (ITable)objs[0];
//
//		//get the row number by matching the privilege number
//		int rowNum = -1;
//		for(int i = 0; i < itemsLsitTable.rowCount(); i ++) {
//			if(itemsLsitTable.getCellValue(i, 1).trim().equalsIgnoreCase(privilegeNumber)) {
//				rowNum = i;
//				break;
//			}
//		}
//
//		if(rowNum == -1) {
//			throw new ItemNotFoundException("Can't find the privilege order item by the privilege number - " + privilegeNumber
//					+ " Please doublecheck it.");
//		}
//
//		//get the needed value
//		String toReturn = "";
//		if(needColumnName.equalsIgnoreCase("Product")) {
//			toReturn = itemsLsitTable.getCellValue(rowNum, 0);
//		} else if(needColumnName.equalsIgnoreCase("Item Status")) {
//			toReturn = itemsLsitTable.getCellValue(rowNum, 2);
//		} else if(needColumnName.equalsIgnoreCase("Purchase Type")) {
//			toReturn = itemsLsitTable.getCellValue(rowNum, 3);
//		} else if(needColumnName.equalsIgnoreCase("Items Price")) {
//			toReturn = itemsLsitTable.getCellValue(rowNum, 4);
//		} else {
//			throw new ItemNotFoundException("Can't find the column name - " + needColumnName);
//		}
//
//		Browser.unregister(objs);
//		return toReturn;
//	}
//
//	/**
//	 * Get specific privilege order item's product name
//	 * @param privilegeNumber
//	 * @return
//	 */
//	public String getPrivilegeOrderItemProductName(String privilegeNumber) {
//		return parsePrivilegeOrderItemsListTable(privilegeNumber, "Product");
//	}
//
//	/**
//	 * Get specific privilege order item status
//	 * @param privilegeNumber
//	 * @return
//	 */
//	public String getPrivilegeOrderItemStatus(String privilegeNumber) {
//		return parsePrivilegeOrderItemsListTable(privilegeNumber, "Item Status");
//	}
//
//	/**
//	 * Get specific privilege order item purchase type
//	 * @param privilegeNumber
//	 * @return
//	 */
//	public String getPrivilegeOrderItemPurchaseType(String privilegeNumber) {
//		return parsePrivilegeOrderItemsListTable(privilegeNumber, "Purchase Type");
//	}
//
//	/**
//	 * Get specific privilege order item price
//	 * @param privilegeNumber
//	 * @return
//	 */
//	public String getPrivilegeOrderItemPrice(String privilegeNumber) {
//		return parsePrivilegeOrderItemsListTable(privilegeNumber, "Item Price");
//	}
//
//	public String getAllPrivilegesNum(){
//		HtmlObject objs[] = browser.getTableTestObject(".id", "privilegeOrderItemsList");
//		ITable grid = (ITable)objs[0];
//
//		StringBuffer sb = new StringBuffer("");
//		int rows=grid.rowCount();
//		for(int i=1;i<rows;i++){
//			sb.append(grid.getCellValue(i, 1)).append(" ");
//		}
//
//		Browser.unregister(objs);
//		return sb.toString().trim();
//	}
//
//	/**
//	 * Click the order number link to display the order detail
//	 * @param orderNum
//	 */
//	public void clickOrderNum(String orderNum) {
//		browser.clickGuiObject(".class", "Html.A", ".text", orderNum);
//	}
//
//	/**
//	 * Select the search type. Such as "Order #"/"Receipt #"/"Identifier #"/"Privilege #"
//	 * @param searchType
//	 */
//	public void selectPrivilegeOrderSearchType(String searchType) {
//		if(searchType.length() > 0) {
//			browser.selectDropdownList(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.searchTypeID", false), searchType, true);
//		} else {
//			browser.selectDropdownList(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.searchTypeID", false), 0, true);
//		}
//	}
//
//	/**
//	 * Set the search value
//	 * @param searchValue
//	 */
//	public void setPrivilegeOrderSearchValue(String searchValue) {
//		browser.setTextField(".id", new RegularExpression("PrivilegeOrderSearchCriteria-\\d+\\.searchValue", false), searchValue, true);
//	}
//
//	/**
//	 * Click 'Invalidate Order' button to invalidate the privilege current order in order page
//	 * @param reason
//	 * @param note
//	 */
//	public void invalidatePrivilegeOrder(String reason, String note) {
//		LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget widget = LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget.getInstance();
//
//		this.invalidatePrivilegeOrder();
//		widget.waitExists();
//		widget.setInfo(reason, note);
//
//		this.waitExists();
//	}
//
//	/**
//	 * Click 'Reactive Order' button to reactive the current privilege order in order page
//	 * @param reason
//	 * @param note
//	 */
//	public void reactivatePrivilegeOrder(String reason, String note) {
//		LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget widget = LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget.getInstance();
//
//		this.reactivatePrivilegeOrder();
//		widget.waitExists();
//		widget.setInfo(reason, note);
//
//		this.waitExists();
//	}
//
//	/**
//	 * Goto privilege detail page from privilege order page
//	 * @param privilegeNum
//	 */
//	public String gotoFirstPrivilegeItemDetailPg(){
//		LicMgrPrivilegeItemDetailPage detailPg = LicMgrPrivilegeItemDetailPage.getInstance();
//
//		String priv_num = selectFirstPrivilege();
//		detailPg.waitExists();
//		return priv_num;
//	}
//
//	/**
//	 * Select privilege and click the 'Void/Reverse' or 'Undo Void/Reverse' button to goto VoidUndoVoid privilege page
//	 * @param operation - 'Void'/'Reverse' or 'Undo Void'/'Undo Reverse'
//	 * @param privilegeNum
//	 */
//	public void voidReversePrivilegeByNum(String operation, String privilegeNum) {
//		HtmlObject objs[] = browser.getTableTestObject(".id", "privilegeOrderItemsList");
//		if(objs.length == 0) {
//			throw new ItemNotFoundException("Can't find Privilege Order Items List table.");
//		}
//		ITable itemsLsitTable = (ITable)objs[0];
//
//		//get the row number by matching the privilege number
//		int rowNum = -1;
//		for(int i = 0; i < itemsLsitTable.rowCount(); i ++) {
//			if(itemsLsitTable.getCellValue(i, 1).trim().equalsIgnoreCase(privilegeNum)) {
//				rowNum = i;
//				break;
//			}
//		}
//
//		if(rowNum == -1) {
//			throw new ItemNotFoundException("Can't find the privilege order item by the privilege number - " + privilegeNum + " Please doublecheck it.");
//		}
//
//		HtmlObject buttonObjs[] = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("(Undo )?Void/Reverse", false));
//		if(buttonObjs.length == 0) {
//			throw new ItemNotFoundException("Can't find any 'Void/Reverse' or 'Undo Void/Reverse' button.");
//		}
//
//		String buttonName = buttonObjs[rowNum - 1].text().trim();
//		if(operation.equalsIgnoreCase("Void") || operation.equalsIgnoreCase("Reverse")) {
//			if(buttonName.equalsIgnoreCase("Void/Reverse")) {
//				buttonObjs[rowNum - 1].click();
//				ajax.waitLoading();
//			} else {
//				throw new ItemNotFoundException("The 'Void/Reverse' button can't be found, the button actually named - " + buttonName);
//			}
//		} else if(operation.contains("Undo")) {
//			if(buttonName.equalsIgnoreCase("Undo Void/Reverse")) {
//				buttonObjs[rowNum - 1].click();
//				ajax.waitLoading();
//			} else {
//				throw new ItemNotFoundException("The 'Undo Void/Reverse' button can't be found, the button actually named - " + buttonName);
//			}
//		}
//
//		Browser.unregister(objs);
//		Browser.unregister(buttonObjs);
//	}
//}
