package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderItems;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderDetailsCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 27, 2011
 */
public class LicMgrPrivilegeOrderDetailsPage extends LicMgrOrderDetailsCommonPage {

	private static LicMgrPrivilegeOrderDetailsPage _instance = null;
	
	protected LicMgrPrivilegeOrderDetailsPage() {}
	
	public static LicMgrPrivilegeOrderDetailsPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrPrivilegeOrderDetailsPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
//		return this.checkLastTrailValueIs("Privilege Order Details");
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "PrivilegeOrderDetailsUI");
	}
	
	public int getNumOfVoidOrReverseButton(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Void/Reverse");
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find Void/Reverse button");
		}
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public int getNumOfUndoVoidOrReverseButton(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Undo Void/Reverse");
		if(objs.length<1){
			throw new ObjectNotFoundException("Failed to find Undo Void/Reverse button");
		}
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	/**
	 * Void or reverse  order
	 */
	public void voidOrReverseOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Void/Reverse", true);
	}
	
	public void voidOrReverseOrder(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Void/Reverse", true, index);
	}
	
	/**
	 * Undo void or reverse  order
	 */
	public void undoVoidOrReverseOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Undo Void/Reverse", true);
	}

	public void undoVoidOrReverseOrder(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Undo Void/Reverse", true, index);
	}

	
	/**
	 * Check whether the 'Invalidate Order' button exists or not
	 * @return
	 */
	public boolean isInvalidateOrderExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Invalidate Order");
	}
	
	/**
	 * Check whether the 'Reactive Order' button exists or not
	 * @return
	 */
	public boolean isReactiveOrderExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Reactivate Order");
	}
	
	/**
	 * Invalidate  order
	 */
	public void invalidateOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Invalidate Order", true);
	}
	
	/**
	 * Reactivate  order
	 */
	public void reactivateOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reactivate Order", true);
	}
	
	/**
	 * Reprint 
	 */
	public void clickReprint() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Reprint (Privilege|Licence)", false), true);
	}
	
	public boolean isReprintEnabled(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("Reprint (Privilege|Licence)", false));
	}
	
	/**
	 * Add the current  order to cart
	 */
	public void clickAddToCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add to Cart", true);
	}

	/**
	 * View Fees infos
	 */
	public void clickFees() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Fees", true);
	}
	
	public String getVerificationStatus() {
		return this.getOrderAttributeValueByName("Verification Status", 0);
	}
	
	/**
	 * Get the order creation date
	 * @return
	 */
	public String getCreationDate() {
		return this.getOrderAttributeValueByName("Creation Date", 0);
	}
	
	/**
	 * Get the order creation user
	 * @return
	 */
	public String getCreationUser() {
		return this.getOrderAttributeValueByName("Creation User", 0);
	}
	
	public String getBillingCustomerName() {
		return this.getOrderAttributeValueByName("Customer Name", 0);
	}
	
	public String getBillingCustomerPhoneNum() {
		return this.getOrderAttributeValueByName("Phone Number", 0);
	}
	
	public String getBillingCustomerEmailAddress() {
		return this.getOrderAttributeValueByName("Email Address", 0);
	}
	
	public String getPrivilegeCustomerNum() {
		return this.getOrderAttributeValueByName("MDWFP #");
	}
	
	public String getPrivilegeCustomerClass() {
		return this.getOrderAttributeValueByName("Customer Class");
	}
	
	public String getPrivilegeCustomerName() {
		return this.getOrderAttributeValueByName("Customer Name", 1);
	}
	
	public String getPrivilegeCustomerBusinessName() {
		return this.getOrderAttributeValueByName("Business Name");
	}
	
	public String getPrivilegeCustomerPhoneNum() {
		return this.getOrderAttributeValueByName("Phone Number", 1);
	}
	
	public String getPrivilegeCustomerEmailAddress() {
		return this.getOrderAttributeValueByName("Email Address", 1);
	}
	
	public String getThirdPartySuspensionCheckStatus() {
		return this.getOrderAttributeValueByName("Third-Party Suspension Check Status");
	}
	
	public String getIdentifierType() {
		return this.getOrderAttributeValueByName("Identifier Used in the Purchase");
	}
	
	public String getIdentifierNum() {
		return this.getOrderAttributeValueByName("Identifier #");
	}
	
	public String getReceiptNum() {
		return this.getOrderAttributeValueByName("Receipt #");
	}
	
	public String getIdentifierVerificationStatus() {
		return this.getOrderAttributeValueByName("Verification Status", 1);
	}
	
	public String getAdditionalProofOfResidency() {
		return this.getOrderAttributeValueByName("Additional Proof of Residency");
	}
	
	public String getTAN() {
		return this.getOrderAttributeValueByName("TAN", 0);
	}
	
	/**
	 * Click the privilege number link to goto the privilege detail page
	 * @param privilegeNumber
	 */
	public void selectPrivilegeByPrivilegeNum(String privilegeNumber) {
		browser.clickGuiObject(".class", "Html.A", ".text", privilegeNumber, true);
	}
	
	/**
	 * Select privilege and click the 'Void/Reverse' or 'Undo Void/Reverse' button to goto VoidUndoVoid privilege page
	 * @param operation - 'Void'/'Reverse' or 'Undo Void'/'Undo Reverse'
	 * @param privilegeNum
	 */
	public void operatePrivilegeByNum(String operation, String privilegeNum) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "privilegeOrderItemsList");
		if(objs.length == 0) {
			throw new ItemNotFoundException("Can't find Privilege Order Items List table.");
		}
		IHtmlTable itemsLsitTable = (IHtmlTable)objs[0];
		
		//get the row number by matching the privilege number
		int rowNum = -1;
		for(int i = 0; i < itemsLsitTable.rowCount(); i ++) {
			if(itemsLsitTable.getCellValue(i, 1).trim().equalsIgnoreCase(privilegeNum)) {
				rowNum = i;
				break;
			}
		}
		
		if(rowNum == -1) {
			throw new ItemNotFoundException("Can't find the privilege order item by the privilege number - " + privilegeNum + " Please doublecheck it.");
		}
		
		IHtmlObject buttonObjs[] = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("(Undo )?Void/Reverse", false));
		if(buttonObjs.length == 0) {
			throw new ItemNotFoundException("Can't find any 'Void/Reverse' or 'Undo Void/Reverse' button.");
		}
		
		String buttonName = buttonObjs[rowNum - 1].text().trim();
		if(operation.equalsIgnoreCase("Void") || operation.equalsIgnoreCase("Reverse")) {
			if(buttonName.equalsIgnoreCase("Void/Reverse")) {
				buttonObjs[rowNum - 1].click();
				ajax.waitLoading();
			} else {
				throw new ItemNotFoundException("The 'Void/Reverse' button can't be found, the button actually named - " + buttonName);
			}
		} else if(operation.contains("Undo")) {
			if(buttonName.equalsIgnoreCase("Undo Void/Reverse")) {
				buttonObjs[rowNum - 1].click();
				ajax.waitLoading();
			} else {
				throw new ItemNotFoundException("The 'Undo Void/Reverse' button can't be found, the button actually named - " + buttonName);
			}
		}
		
		Browser.unregister(objs);
		Browser.unregister(buttonObjs);
	}
	
	/**
	 * Get specific privilege order item's product name
	 * @param privilegeNumber
	 * @return
	 */
	public String getPrivilegeOrderItemProductName(String privilegeNumber) {
		return parsePrivilegeOrderItemsListTable(privilegeNumber, "Product");
	}
	
	/**
	 * Get specific privilege order item status
	 * @param privilegeNumber
	 * @return
	 */
	public String getPrivilegeOrderItemStatus(String privilegeNumber) {
		return parsePrivilegeOrderItemsListTable(privilegeNumber, "Item Status");
	}
	
	/**
	 * Get specific privilege order item purchase type
	 * @param privilegeNumber
	 * @return
	 */
	public String getPrivilegeOrderItemPurchaseType(String privilegeNumber) {
		return parsePrivilegeOrderItemsListTable(privilegeNumber, "Purchase Type");
	}
	
	/**
	 * Get specific privilege order item price
	 * @param privilegeNumber
	 * @return
	 */
	public String getPrivilegeOrderItemPrice(String privilegeNumber) {
		return parsePrivilegeOrderItemsListTable(privilegeNumber, "Item Price").split("\\$")[1].trim();
	}
	
	public String getAllPrivilegesNum(){
		IHtmlObject objs[] = browser.getTableTestObject(".id", "privilegeOrderItemsList");
		IHtmlTable grid = (IHtmlTable)objs[0];
		
		StringBuffer sb = new StringBuffer("");
		int rows=grid.rowCount();
		for(int i=1;i<rows;i++){
			sb.append(grid.getCellValue(i, 1)).append(" ");
		}
		
		Browser.unregister(objs);
		return sb.toString().trim();
	}
	
	/**
	 * Parse the privilege order items list table and return the needed column value with privilege number
	 * @param privilegeNumber
	 * @param needColumnName
	 * @return
	 */
	private String parsePrivilegeOrderItemsListTable(String privilegeNumber, String needColumnName) {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "privilegeOrderItemsList");
		if(objs.length == 0) {
			throw new ItemNotFoundException("Can't find Privilege Order Items List table.");
		}
		IHtmlTable itemsLsitTable = (IHtmlTable)objs[0];
		
		//get the row number by matching the privilege number
		int rowNum = -1;
		for(int i = 0; i < itemsLsitTable.rowCount(); i ++) {
			if(itemsLsitTable.getCellValue(i, 1).trim().equalsIgnoreCase(privilegeNumber)) {
				rowNum = i;
				break;
			}
		}
		
		if(rowNum == -1) {
			throw new ItemNotFoundException("Can't find the privilege order item by the privilege number - " + privilegeNumber
					+ " Please doublecheck it.");
		}
		
		//get the needed value
		String toReturn = "";
		if(needColumnName.equalsIgnoreCase("Product")) {
			toReturn = itemsLsitTable.getCellValue(rowNum, 0);
		} else if(needColumnName.equalsIgnoreCase("Item Status")) {
			toReturn = itemsLsitTable.getCellValue(rowNum, 2);
		} else if(needColumnName.equalsIgnoreCase("Purchase Type")) {
			toReturn = itemsLsitTable.getCellValue(rowNum, 3);
		} else if(needColumnName.equalsIgnoreCase("Item Price")) {
			toReturn = itemsLsitTable.getCellValue(rowNum, 4);
		} else {
			throw new ItemNotFoundException("Can't find the column name - " + needColumnName);
		}
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * Get all order item info
	 * @return
	 */
	public List<OrderItems> getAllOrderItemInfo(){
		List<OrderItems> orderList = new ArrayList<OrderItems>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeOrderItemsList");
		if(objs.length <1){
			throw new ErrorOnPageException("Can't find the privilege order list");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		OrderItems orderItem;
		for(int i=1;i<table.rowCount();i++){
			orderItem = new OrderItems();
			orderItem.product = table.getCellValue(i, 0).replace(" ", "");
			orderItem.privilegeNum = table.getCellValue(i, 1);
			orderItem.itemStatus = table.getCellValue(1, 2);
			orderItem.purchaseType = table.getCellValue(i, 3);
			orderItem.itemPrice = table.getCellValue(i, 4);
			orderList.add(orderItem);
		}
		Browser.unregister(objs);
		return orderList;
	}
	
	/**
	 * Parse the privilege order items list table and return specific order list.
	 * @param productName
	 * @return the order list item.
	 */
	public List<OrderItems> getOrderInfo(String productName){
		List<OrderItems> orderList = new ArrayList<OrderItems>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeOrderItemsList");
		if(objs.length <1){
			throw new ErrorOnPageException("Can't find the privilege order list");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		for(int i=0;i<table.rowCount();i++){
			OrderItems orderItem = new OrderItems();
			orderItem.product = table.getCellValue(i, 0).replace(" ", "");
			if(orderItem.product.contains(productName)){
				orderItem.privilegeNum = table.getCellValue(i, 1);
				orderItem.itemStatus = table.getCellValue(1, 2);
				orderItem.purchaseType = table.getCellValue(i, 3);
				orderItem.itemPrice = table.getCellValue(i, 4);
//			if(orderItem.product.contains(productName)){
				orderList.add(orderItem);
			}
		}
		Browser.unregister(objs);
		return orderList;
	}
	/**
	 * Compare the privilege order details info.
	 * @param product
	 * @return boolean-- the compare boolean value.
	 */
	public boolean comparePrivilegeOrderDetailInfo(OrderInfo expectedOrder,Customer expectedCust){
		boolean pass = true;
		String temp =this.getOrderNum();
		if(!expectedOrder.orderNum.equals(temp)){
			pass &= false;
			logger.error("The orderNum should be "+expectedOrder.orderNum+" but the actual is "+temp+"");
		}
		temp = this.getSalesLocation();
		if(!temp.contains(expectedOrder.saleLocation)){
			pass &= false;
			logger.error("The sales location should be"+expectedOrder.saleLocation+" but the actual is "+temp+"");
		}
		temp = this.getCreationUser();
		if(!expectedOrder.creationUser.equals(temp)){
			pass &= false;
			logger.error("The creattion user should be"+expectedOrder.creationUser+" but the actual is "+temp+"");
		}
		temp  = this.getBalance();
		if(!expectedOrder.balance.equals(temp)){
			pass &= false;
			logger.error("The balance should be"+expectedOrder.balance+" but the actual is "+temp+"");
		}
		temp = this.getPriceInfo();
		if(!expectedOrder.orderPrice.equals(temp)){
			pass &= false;
			logger.error("The price should be "+expectedOrder.orderPrice+" but the actual is "+temp+"");
		}
		temp  = this.getPaid();
		if(!expectedOrder.orderPaid.equals(temp)){
			pass &= false;
			logger.error("The paid should be"+expectedOrder.orderPaid+"but the actual is "+temp+"");
		}
		temp = this.getUnissuedRefund();
		if(!expectedOrder.unissuedRefund.equals(temp)){
			pass &= false;
			logger.error("The unisued refund should be "+expectedOrder.unissuedRefund+" but the actual is "+temp+"");
		}
		temp = this.getConfirmationStatus();
		if(!expectedOrder.confirmStatus.equals(temp)){
			pass &= false;
			logger.error("The confirm status should be "+expectedOrder.confirmStatus+" but the actual is "+temp+"");
		}
		temp = this.getReceiptNum();
		if(!expectedOrder.receiptNum.equals(temp)){
			pass &= false;
			logger.error("The receipt number shoulde be "+expectedOrder.receiptNum+" but the actual is "+temp+"");
		}
		temp = this.getBillingCustomerName();
		String name = expectedCust.lName+","+expectedCust.fName;
		if(!name.equals(temp)){
			pass &= false;
			logger.error("The billing customer name should be "+name+" but the actual is "+temp+"");
		}
		temp = this.getBillingCustomerPhoneNum();
		if(!expectedCust.phoneContact.equals(temp)){
			pass &= false;
			logger.error("The billing customer Number should be "+expectedCust.phoneContact+" but the actual is "+temp+"");
		}
		temp = this.getBillingCustomerEmailAddress();
		if(!expectedCust.email.equals(temp)){
			pass &= false;
			logger.error("The billing customer email address should be "+expectedCust.email+" but the actual is "+temp+"");
		}
		temp = this.getPrivilegeCustomerName();
		if(!name.equals(temp)){
			pass &= false;
			logger.error("The privilege customer name should be "+name+" but the actual is "+temp+"");
		}
		temp = this.getPrivilegeCustomerClass();
		if(!expectedCust.customerClass.equals(temp)){
			pass &= false;
			logger.error("The privilege customer class should be"+expectedCust.customerClass+" but the actual is "+temp+"");
		}
		temp = this.getPrivilegeCustomerBusinessName();
		if(!expectedCust.businessName.equals(temp)){
			pass &= false;
			logger.error("The privilege customer busniess name should be "+expectedCust.businessName+" but the actual is "+temp+"");
		}
		temp = this.getPrivilegeCustomerNum();
		if(!expectedCust.identifier.identifierNum.equals(temp)){
			pass &= false;
			logger.error("The privilege customer number should be "+expectedCust.licenseNum+" but the actual is "+temp+"");
		}
		temp = this.getPrivilegeCustomerPhoneNum();
		if(!expectedCust.phoneContact.equals(temp)){
			pass &= false;
			logger.error("The privilege customer phone number should be "+expectedCust.phoneContact+" but the actual is "+temp+"");
		}
		for(int i= 0;i<expectedOrder.orderList.size();i++){
			List<OrderItems> list = this.getOrderInfo(expectedOrder.orderList.get(i).product);
			if(list.size()<1){
				throw new ErrorOnPageException("Can't find the order info");
			}
			if(!list.get(i).product.contains(expectedOrder.orderList.get(i).product)){
				pass &= false;
				logger.error("The privilege order product should be "+expectedOrder.orderList.get(i).product+" but the actual is "+list.get(i).product+"");
			}
			if(!expectedOrder.orderList.get(i).itemStatus.equals(list.get(i).itemStatus)){
				pass &= false;
				logger.error("The privilege order item status should be "+expectedOrder.orderList.get(i).itemStatus+" but the actual is "+list.get(i).itemStatus+"");
			}
			if(!expectedOrder.orderList.get(i).purchaseType.equals(list.get(i).purchaseType)){
				pass &= false;
				logger.error("The privilege order item purchase type should be "+expectedOrder.orderList.get(i).purchaseType+" but the actual is "+list.get(i).purchaseType+"");
			}
			if(!expectedOrder.orderList.get(i).itemPrice.trim().equals(list.get(i).itemPrice.trim())){
				pass &= false;
				logger.error("The privilege order item price should be "+expectedOrder.orderList.get(i).itemPrice+"but the actual is "+list.get(i).itemPrice+"");
			}
		}
		return pass;
	
	}
	
	public void verifyTANCode(String TANCode){				
		String TANFromUI = getTAN();
		
		if(TANFromUI.equals(TANCode)){
			logger.info("Verify TAN Code successfully on order detail page");
		}else{
			logger.error("TAN Code display un-correctly on order detail page");
			throw new ErrorOnPageException("Expected TAN Code is:"+TANCode
					+", and actual TAN Code on order detail page is:"+TANFromUI);
		}
	}

	/**
	 * 
	 * @param expectedPrivStatus
	 * @param isReprintEnabled
	 */
	public void verifyIfReprintButtonEnabled(String expectedPrivStatus, boolean isReprintEnabled) {
		logger.info("Verify whether the privilege 'Reprint' button is enabled or not when order status is " + expectedPrivStatus);
		boolean resultOnPage = this.isReprintEnabled();
		boolean expectedResult = true;
		if(expectedPrivStatus.equals(OrmsConstants.ACTIVE_STATUS)) {
			expectedResult = true;
		} else if(expectedPrivStatus.equals(OrmsConstants.VOIDED_STATUS) || expectedPrivStatus.equals(OrmsConstants.INVALID_STATUS) || expectedPrivStatus.equals(OrmsConstants.REVOKED_STATUS)) {
			expectedResult = false;
		} else throw new ErrorOnPageException("Unkown expected status - " + expectedPrivStatus);
		
		if(resultOnPage != expectedResult) {
			throw new ErrorOnPageException("The privilege status is " + expectedPrivStatus + ", so the 'Reprint' button's status should be " + (isReprintEnabled ? " Enabled." : " Disabled."));
		} logger.info("----The privilege 'Reprint' button is really " + (isReprintEnabled ? "Enabled." : "Disabled."));
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
	
	public void clickNoteAndAlert(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Notes \\& Alerts", false), true);
	}
}
