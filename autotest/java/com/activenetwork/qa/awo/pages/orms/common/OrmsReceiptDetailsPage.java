package com.activenetwork.qa.awo.pages.orms.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EventInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReceiptInfo;
import com.activenetwork.qa.awo.pages.orms.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 11, 2014
 */
public class OrmsReceiptDetailsPage extends OrmsPage {
	private static OrmsReceiptDetailsPage _instance = null;
	
	protected OrmsReceiptDetailsPage() {}
	
	public static OrmsReceiptDetailsPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsReceiptDetailsPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return checkLastTrailValueIs("Receipt Detail");
	}
	
	
	private Property[] addToCart() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Add to Cart");
	}
	
	private Property[] reprintReceipt() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Reprint Receipt");
	}
	private Property[] requestHFConfirmLetter() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Request H&F Conf Letter");
	}
	
	/**
	 * for Venue Manager
	 * @return
	 */
	private Property[] reprintTicketReceipt() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Reprint Ticket Receipt");
	}
	
	/**
	 * for License Manager
	 * @return
	 */
	private Property[] requestHFConfLetter() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Request H&F Conf Letter");
	}
	
	private Property[] attributeSpan(String attrName) {
		return Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^" + attrName, false));
	}
	
	private Property[] transactionsTR() {
		return Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^Transaction\\(s\\)", false));
	}
	
	private Property[] receiptItemTR() {
		return Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^Receipt Item", false));
	}
	
	private Property[] orderNum() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Order \\# \\:", false));
	}
	
	private Property[] orderNumLink(String num) {
		return Property.toPropertyArray(".class", "Html.A", ".text", num);
	}
	
	private Property[] facility() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".className", "orderItemDetail", ".text", new RegularExpression("Facility:", false));
	}
	
	private Property[] category() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".className", "orderItemDetail", ".text", new RegularExpression("Category:", false));
	}
	
	private Property[] hfCustomerNameNum() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression(".*\\,.*\\([0-9]+\\)", false));
	}
	
	private Property[] residencyStatus() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("Resident", false));
	}
	
	private Property[] hullIdSerialNum() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("(Boat|Motor|Dealer)\\s.*", false));
	}
	
	private Property[] miNum() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^(MI \\#|Reg\\. Number)\\:MI[0-Z]+", false));
	}
	
	private Property[] privilegePurchasingName() {
//		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("\\([0-9]{4}\\)(\\s)?[0-Z]{1,3}(\\s)?\\-.*(\\(.*\\))?", false));

		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("\\([0-9]{4}\\)(\\s)?[0-Z]{1,4}(\\s)?\\-.*(\\(.*\\))?", false));
	}
	private Property[] activityPurchasingName() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("\\([0-9]{4}\\)(\\s)?[0-Z]{1,4}(\\s)?\\-.*(\\(.*\\))?", false));
	}
	
	private Property[] subscriber() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Subscriber(\\s)?:", false));
	}
	
	private Property[] deliveryAddress() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Delivery Address(\\s)?:", false));
	}
	
	private Property[] contactEmailOrPhone() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Contact (Email|Phone)(\\s)?:", false));
	}
	
	private Property[] qtyApproved() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("Qty Approved\\:\\d+", false));
	}
	
	private Property[] lotteryName() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Lottery :", false));
	}
	
	private Property[] preferredArea() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Preferred Area :", false));
	}
	
	private Property[] occupant() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Occupant :", false));
	}
	
	private Property[] preferredArrivalDate() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Preferred Arrival Date :", false));
	}
	
	private Property[] preferredLengthOfStay() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Preferred Length of stay :", false));
	}
	
	private Property[] tourName() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".className", "orderItemDetail");
	}
	
	private Property[] entranceName() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("^Entrance:", false), ".href", new RegularExpression("EntranceDetail\\.do\\?entranceID", false));
	}
	
	private Property[] permitType() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Permit Type:", false));
	}
	
	private Property[] permitGroupLeader() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Group Leader:", false));
	}
	
	private Property[] permitGroupSize() {
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Group Size:", false));
	}
	
	private Property[] arrivalDate() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".className", "orderItemDetail", ".text", new RegularExpression("[a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4}", false));
	}
	
	private Property[] deliveryMethod() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".className", "orderItemDetail", ".text", new RegularExpression("\\(.*\\)", false));
	}
	
	private Property[] receiptItemTable() {
		return Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^(Order \\#(\\s)?\\:(\\s)?)?(M)?[0-9]{1,2}\\-[0-9]{1,} | ^Slip Reservation #:  M[0-9]{1,2}\\-[0-9]{1,}.*", false));
	}
	
	private Property[] receiptFeesSummaryTable() {
		return Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("Receipt Fees Summary", false));
	}
	
	private Property[] amountsTable() {
		return Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^Amounts", false));
	}
	
	private Property[] feeAmountSpan() {
		return Property.toPropertyArray(".class", "Html.SPAN", ".className", new RegularExpression("cell_currency_right(Positive)?", false));
	}
	
	private Property[] paymentID() {
		return Property.toPropertyArray(".id", "amounts", ".text", new RegularExpression("\\d+", false));
	}
	private Property[] activitySessionInfo(){//Thu Jul 30 2015 1:12 AM - 11:59 PM CDT
		return Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("[a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4} [0-9]{1,2}:[0-9]{2} (AM|PM) \\- [0-9]{1,2}:[0-9]{2} (AM|PM)", false));
	}
	private Property[] voucherRedeemedLabel() {
		return Property.toPropertyArray(".class","Html.LABEL",".text","Vouchers Redeemed");
	}
	private Property[] voucherId(String voucherId) {
		return Property.toPropertyArray(".text",voucherId,".class","Html.A");
	}
	private Property[] payementsLabel() {
		return Property.toPropertyArray(".class","Html.LABEL",".text","Payments");
	}
	private Property[] payementId(String paymentId) {
		return Property.toPropertyArray(".text",paymentId,".class","Html.A");
	}
	private Property[] payementsSpan() {
		return Property.toPropertyArray(".class", "Html.SPAN",".text",new RegularExpression("Payments",false));
	}
	private Property[] payementsTr() {
		return Property.toPropertyArray(".class", "Html.TR",".text",new RegularExpression("Payments",false));
	}
	private Property[] voucherRedeemedSpan() {
		return Property.toPropertyArray(".class", "Html.SPAN",".text",new RegularExpression("Vouchers Redeemed",false));
	}
	private boolean isPaymentLabelExist(IHtmlObject top){
		return browser.checkHtmlObjectDisplayed(payementsLabel(), top);
	}
	private boolean isVoucherRedeemedLabelExist(IHtmlObject top){
		return browser.checkHtmlObjectDisplayed(voucherRedeemedLabel(), top);
	}
	private boolean isPaymentIdExist(String paymentId,IHtmlObject top){
		return browser.checkHtmlObjectDisplayed(payementId(paymentId), top);
	}
	private boolean isVoucherIdInRedeemedSectionExist(String paymentId,IHtmlObject top){
		return browser.checkHtmlObjectDisplayed(voucherId(paymentId), top);
	}
	public String getEventID() {
		return getAttributeValueByName("Event ID");
	}
	
	public String getEventName() {
		return getAttributeValueByName("Event Name");
	}
	
	public String getEventStartDate() {
		return getAttributeValueByName("Start Date");
	}
	
	public String getEventEndDate() {
		return getAttributeValueByName("End Date");
	}
	
	public String getEventLocation() {
		return getAttributeValueByName("Location");
	}
	
	public EventInfo getReceiptEventInfo() {
		EventInfo event = new EventInfo();
		event.eventID = this.getEventID();
		event.eventName = this.getEventName();
		event.eventStart = this.getEventStartDate();
		event.eventEnd = this.getEventEndDate();
		event.location = this.getEventLocation();
		
		return event;
	}
	
	public boolean compareReceiptEventInfo(EventInfo expected) {
		EventInfo actual = this.getReceiptEventInfo();
		boolean result = true;
		
		result &= MiscFunctions.compareResult("Event ID", expected.eventID, actual.eventID);
		result &= MiscFunctions.compareResult("Event Name", expected.eventName, actual.eventName);
		result &= MiscFunctions.compareResult("Event Start Date", expected.eventStart, actual.eventStart);
		result &= MiscFunctions.compareResult("Event End Date", expected.eventEnd, actual.eventEnd);
		result &= MiscFunctions.compareResult("Event Location", expected.location, actual.location);
		
		return result;
	}
	
	public void verifyReceiptEventSectionInfo(EventInfo expected) {
		if(!compareReceiptEventInfo(expected)) throw new ErrorOnPageException("Receipt details - Event(ID=" + expected.eventID + ") section info is NOT correct.");
		logger.info("Receipt details - Event(ID=" + expected.eventID +") section info is correct.");
	}
	
	public boolean isAddToCartEnabled() {
		return browser.checkHtmlObjectExists(addToCart());
	}
	
	public void verifyAddToCartEnabled(boolean expected) {
		if(!MiscFunctions.compareResult("'Add to Cart' button is enabled", expected, this.isAddToCartEnabled()))
			throw new ErrorOnPageException("'Add to Cart' shall be " + (expected ? "" : "NOT ") + "enabled.");
	}
	
	public void clickAddToCart() {
		browser.clickGuiObject(addToCart());
	}
	
	public boolean isReprintReceiptEnabled() {
		return browser.checkHtmlObjectExists(reprintReceipt());
	}
	
	public void verifyReprintReceiptEnabled(boolean expected) {
		if(!MiscFunctions.compareResult("'Reprint Receipt' button is enabled", expected, this.isReprintReceiptEnabled()))
			throw new ErrorOnPageException("'Reprint Receipt' shall be " + (expected ? "" : "NOT ") + "enabled.");
	}
	public boolean isRequestHFConfirmLetterEnabled() {
		return browser.checkHtmlObjectExists(requestHFConfirmLetter());
	}
	
	public void verifyRequestHFConfirmLetterEnabled(boolean expected) {
		if(!MiscFunctions.compareResult("'Request H&F Conf Letter' button is enabled", expected, this.isRequestHFConfirmLetterEnabled()))
			throw new ErrorOnPageException("'Request H&F Conf Letter' shall be " + (expected ? "" : "NOT ") + "enabled.");
	}

	public boolean isReprintTicketReceiptEnabled() {
		return browser.checkHtmlObjectExists(reprintTicketReceipt());
	}
	
	public void verifyReprintTicketReceiptEnabled(boolean expected) {
		if(!MiscFunctions.compareResult("'Reprint Ticket Receipt' button is enabled", expected, this.isReprintReceiptEnabled()))
			throw new ErrorOnPageException("'Reprint Ticket Receipt' shall be " + (expected ? "" : "NOT ") + "enabled.");
	}
	
	public void clickRequestHFConfLetter() {
		browser.clickGuiObject(requestHFConfLetter());
	}
	
	public boolean isRequestHFConfLetterEnabled() {
		return browser.checkHtmlObjectExists(requestHFConfLetter());
	}
	
	public String getAlertMsg(){
		String msg = "";
		IHtmlObject[] notSet = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		if(notSet.length>0) {
			msg = notSet[0].text();
		}
	
		Browser.unregister(notSet);
		return msg; 
	}
	
	private String getAttributeValueByName(String name) {
		IHtmlObject objs[] = browser.getHtmlObject(attributeSpan(name));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find Receipt attribute - " + name);
		}
		
		String text = objs[0].text();
		String value = text.substring(name.length()).trim();
		
		Browser.unregister(objs);
		return value;
	}
	
	public String getReceiptNum() {
		return getAttributeValueByName("Receipt #");
	}
	
	public String getReceiptDateTime() {
		return getAttributeValueByName("Receipt Date & Time");
	}
	
	public String getSalesLocation() {
		return getAttributeValueByName("Sales Location");
	}
	
	public String getCreatedBy() {
		return getAttributeValueByName("Created By");
	}
	
	public String getCustomerFirstName() {
		String name = getAttributeValueByName("Name");
		return name.split(",")[1].trim();
	}
	
	public String getCustomerLastName() {
		String name = getAttributeValueByName("Name");
		return name.split(",")[0].trim();
	}
	
	public String getCustomerPhone() {
		return getAttributeValueByName("Phone");
	}
	
	public String getCustomerEmail() {
		return getAttributeValueByName("Email");
	}
	
	public String getCustomerOrganizationName() {
		return getAttributeValueByName("Organization Name");
	}
	
	public String getTransactions() {
		IHtmlObject trObjs[] = browser.getHtmlObject(transactionsTR());
		if(trObjs.length < 1) throw new ItemNotFoundException("Cannot find Transaction(s) TR object.");
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TD", trObjs[0]);
		if(objs.length < 2) throw new ItemNotFoundException("Cannot find Transaction names TD object.");
		String name = objs[1].text();
		
		Browser.unregister(objs);
		Browser.unregister(trObjs);
		return name;
	}
	
	public String getOrderNum() {
		IHtmlObject objs[] = getReceiptItemTable();
		String nums[] = RegularExpression.getMatches(objs[0].text(), "(M)?[0-9]{1,2}\\-[0-9]{1,}");
		if(nums.length < 1) throw new ErrorOnPageException("Cannot find Order Number.");
		
		Browser.unregister(objs);
		return nums[0];
	}
	
	public boolean isOrderNumDisplayedAsLink(String num) {
		return browser.checkHtmlObjectExists(orderNumLink(num));
	}
	
	public void clickOrderNum(String num) {
		browser.clickGuiObject(orderNumLink(num));
	}
	
	public String getFacility() {
		String text = browser.getObjectText(facility());//Ticket Order
		
		if(StringUtil.isEmpty(text)) {//POS Order, Supplies Order
			IHtmlObject objs[] = getReceiptItemTable();
			IHtmlTable table = (IHtmlTable)objs[0];
			text = table.getCellValue(1, 0);
		}
		
		if(text.contains("Facility:")) {
			text = text.split("\\:")[1].trim();
		}
		if(text.matches(".*\\(\\d+\\)")) {
			text = text.split("\\(")[0].trim();
		}
		
		return text;
	}
	
	public String getCategory() {
		String text = browser.getObjectText(category());
		if(!StringUtil.isEmpty(text)) {
			text = text.split(":")[1].trim();
		}
		
		return text;
	}
	
	public String getTourName() {
		IHtmlObject objs[] = browser.getHtmlObject(tourName());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Tour Name span object.");
		String name = objs[1].text();
		
		Browser.unregister(objs);
		return name;
	}
	
	public String getPermitType() {
		return browser.getObjectText(permitType()).split("\\:")[1].trim();
	}
	
	public String getPermitEntranceName() {
		return browser.getObjectText(entranceName()).split("\\:")[1].trim();
	}
	
	public String getPermitGroupLeader() {
		return browser.getObjectText(permitGroupLeader()).split("\\:")[1].trim();
	}
	
	public String getPermitGroupSize() {
		String text = browser.getObjectText(permitGroupSize());
		String sizes[] = RegularExpression.getMatches(text, "Group Size: \\d+");
		if(sizes.length < 1) throw new ItemNotFoundException("Cannot find Group Size");
		
		String size = sizes[0].split(":")[1].trim();
		
		return size;
	}
	
	public String getPOSName() {
		IHtmlObject objs[] = getReceiptItemTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		String name = table.getCellValue(3, 0);
		
		Browser.unregister(objs);
		return name;
	}
	
	public String getGiftCardName() {
		IHtmlObject objs[] = getReceiptItemTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		String name = table.getCellValue(0, 1);
		
		Browser.unregister(objs);
		return name;
	}
	
	public String getPrivilegePurchasingName() {
		IHtmlObject objs[] = browser.getHtmlObject(privilegePurchasingName());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Privilege Purchasing Name TD object.");
		String text = objs[objs.length - 1].text();
		String name = "";
		if(!StringUtil.isEmpty(text)) {
			name = text.split("\\)")[1].trim();
		}
		
		Browser.unregister(objs);
		return name;
	}
	
	public String getVehiclePurchasingName() {
		IHtmlObject objs[] = browser.getHtmlObject(privilegePurchasingName());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Vehicle Purchasing Name TD object.");
		String text = objs[objs.length - 1].text();
		String name = "";
		if(!StringUtil.isEmpty(text)) {
			name = text.split("\\)")[1].split("\\(")[0].trim();
		}
		
		Browser.unregister(objs);
		return name;
	}
	
	
	public String getLicenseOrFiscalYear() {
		IHtmlObject objs[] = browser.getHtmlObject(privilegePurchasingName());
		if(null==objs||objs.length < 1) throw new ItemNotFoundException("Cannot find Privilege Purchasing Name TD object.");
		String text = objs[objs.length - 1].text();
		String year = "";
		if(!StringUtil.isEmpty(text)) {
			year = RegularExpression.getMatches(text, "[0-9]{4}")[0];
		}
		
		Browser.unregister(objs);
		return year;
	}
	
	public String getPurchaseType() {
		IHtmlObject objs[] = browser.getHtmlObject(privilegePurchasingName());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Privilege Purchasing Name TD object.");
		String text = objs[objs.length - 1].text();
		String type = "";
		if(!StringUtil.isEmpty(text)) {//(2014)RV1-RegisterVehicleBoat(Original)
			type = text.split("-")[1].split("\\(")[1].replace("\\)", StringUtil.EMPTY);
		}
		
		Browser.unregister(objs);
		return type;
	}
	
	public String getCustomerName() {
		IHtmlObject objs[] = browser.getHtmlObject(hfCustomerNameNum());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find HF Customer Name Num TD object.");
		
		String text = objs[objs.length - 1].text();
		String custName = "";
		if(!StringUtil.isEmpty(text)) {
			custName = text.split("\\(")[0].trim();
		}
		
		Browser.unregister(objs);
		return custName;
	}
	
	public String getCustomerNum() {
		IHtmlObject objs[] = browser.getHtmlObject(hfCustomerNameNum());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find HF Customer Name Num TD object.");
		
		String text = objs[objs.length - 1].text();
		String custNum = "";
		if(!StringUtil.isEmpty(text)) {
			custNum = RegularExpression.getMatches(text, "\\(\\d+\\)")[0].replaceAll("\\(|\\)", StringUtil.EMPTY);
		}
		
		Browser.unregister(objs);
		return custNum;
	}
	
	public String getCustomerResidencyStatus() {
		IHtmlObject objs[] = browser.getHtmlObject(residencyStatus());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Customer Residency Status TD object.");
		String text = objs[objs.length - 1].text();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getHullIdSerialNum() {
		IHtmlObject objs[] = browser.getHtmlObject(hullIdSerialNum());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Hull ID Serial Num TD object.");
		String text = objs[objs.length - 1].text().replaceAll("Boat|Motor|Dealer", StringUtil.EMPTY).trim();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getMINum() {
		IHtmlObject objs[] = browser.getHtmlObject(miNum());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find MI # TD object.");
		String text = objs[objs.length - 1].text().split("\\:")[1].trim();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getSubscriber() {
		IHtmlObject objs[] = browser.getHtmlObject(subscriber());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Subscriber object.");
		String text = objs[objs.length - 1].text().split("\\:")[1].trim();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getDeliveryAddress() {
		IHtmlObject objs[] = browser.getHtmlObject(deliveryAddress());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Delivery Address object.");
		String text = objs[objs.length - 1].text().split("\\:")[1].trim();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getContactEmailOrPhone() {
		IHtmlObject objs[] = browser.getHtmlObject(contactEmailOrPhone());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Contact Email/Phone object.");
		String text = objs[objs.length - 1].text().split("\\:")[1].trim();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getSupplyPurchasingName() {
		IHtmlObject objs[] = browser.getHtmlObject(privilegePurchasingName());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Supply Purchasing Name TD object.");
		String text = objs[objs.length - 1].text();
		String name = "";
		if(!StringUtil.isEmpty(text)) {
			name = text.split("\\)")[1].trim();
		}
		
		Browser.unregister(objs);
		return name;
	}
	
	/**
	 * for Supplies Order
	 * @return
	 */
	public String getQtyApproved() {
		IHtmlObject objs[] = browser.getHtmlObject(qtyApproved());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Qty Approved TD object.");
		String text = objs[objs.length - 1].text().split("\\:")[1].trim();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getLotteryName() {
		IHtmlObject objs[] = browser.getHtmlObject(lotteryName());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Lottery Name TD object.");
		String text = objs[objs.length - 1].text().split("\\:")[1].trim();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getPreferredArea() {
		IHtmlObject objs[] = browser.getHtmlObject(preferredArea());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Preferred Area TD object.");
		String text = objs[objs.length - 1].text().split("\\:")[1].trim();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getOccupant() {
		IHtmlObject objs[] = browser.getHtmlObject(occupant());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Occupant TD object.");
		String text = objs[objs.length - 1].text().split("\\:")[1].trim();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getPreferredArrivalDate() {
		IHtmlObject objs[] = browser.getHtmlObject(preferredArrivalDate());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Preferred Arrival Date TD object.");
		String text = objs[objs.length - 1].text().split("\\:")[1].trim();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getPreferredLengthOfStay() {
		IHtmlObject objs[] = browser.getHtmlObject(preferredLengthOfStay());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Preferred Length Of Stay TD object.");
		String text = objs[objs.length - 1].text().split("\\:")[1].trim();
		
		Browser.unregister(objs);
		return text;
	}
	
	public String getArrivalDate() {
		IHtmlObject objs[] = getReceiptItemTable();
		String dates[] = RegularExpression.getMatches(objs[0].text(), "[a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4}");
		String date = "";
		if(dates.length > 0) {
			date = dates[0].split(",")[0].trim();
		}
		
		Browser.unregister(objs);
		return date;
	}
	
	public String getDeliveryMethod() {
		String method = browser.getObjectText(deliveryMethod());
		if(!StringUtil.isEmpty(method)) {
			method = method.replaceAll("\\(", StringUtil.EMPTY).replaceAll("\\)", StringUtil.EMPTY);
		}
		
		return method;
	}

	public ReceiptInfo getReceiptInfo() {
		ReceiptInfo rct = new ReceiptInfo();
		
		rct.id = getReceiptNum();
		rct.receiptDateAndTime = getReceiptDateTime();
		rct.salesLocation = getSalesLocation();
		rct.createdBy = getCreatedBy();
		
		return rct;
	}
	
	public Customer getCustomerInfo() {
		Customer cust = new Customer();
		
		cust.fName = getCustomerFirstName();
		cust.lName = getCustomerLastName();
		cust.hPhone = getCustomerPhone();
		cust.email = getCustomerEmail();
		cust.organization = getCustomerOrganizationName();
		
		return cust;
	}
	//for Activity Registration Orders
	public String getActivityPurchasingName() {
		IHtmlObject objs[] = browser.getHtmlObject(privilegePurchasingName());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Activity Purchasing Name TD object.");
		String text = objs[objs.length - 1].text();
		String name = "";
		if(!StringUtil.isEmpty(text)) {
			name = text.split("\\)")[1].trim();
		}
		
		Browser.unregister(objs);
		return name;
	}
	//currently only for activity reg session,if other product can fullfill condition please refactor method name
	public String getActivitySessionInfo(){
		IHtmlObject objs[] = browser.getHtmlObject(activitySessionInfo());
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find Activity Session TD object.");
		}
		String text = objs[objs.length - 1].text();
		Browser.unregister(objs);
		return text;

	}
	
	
	//added by Summer
	public List<String> getQtyInfo(){
		IHtmlObject objs[] = getReceiptItemTable();
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find qty TD object.");
		}
		IHtmlTable table=(IHtmlTable)objs[objs.length - 1];
		List<String> qtyList = new ArrayList<String>(); 
		for(int i=0;i<table.rowCount();i++){
			for(int j=0;j<table.columnCount();j++){
				String text=table.getCellValue(i, j);
				if((null!=text&&!StringUtil.isEmpty(text))&&text.matches("\\d+"))
					qtyList.add(text);
			}
		}
		Browser.unregister(objs);
		return qtyList;

	}
	public boolean compareReceiptInfo(ReceiptInfo expected) {
		ReceiptInfo actual = getReceiptInfo();
		boolean result = true;
		
		result &= MiscFunctions.compareResult("Receipt #", expected.id, actual.id);
		result &= MiscFunctions.compareResult("Receipt Date & Time", expected.receiptDateAndTime, actual.receiptDateAndTime);
		result &= MiscFunctions.compareResult("Sales Location", expected.salesLocation, actual.salesLocation);
		result &= MiscFunctions.compareResult("Created By", expected.createdBy, actual.createdBy);
		
		return result;
	}
	
	public void verifyReceiptInfo(ReceiptInfo expected) {
		if(!compareReceiptInfo(expected)) throw new ErrorOnPageException("Receipt info is NOT correct.");
		logger.info("Receipt info is correct.");
	}
	
	public boolean compareCustomerInfo(Customer expected) {
		Customer actual = getCustomerInfo();
		boolean result = true;
		
		result &= MiscFunctions.compareResult("Customer First Name", expected.fName, actual.fName);
		result &= MiscFunctions.compareResult("Customer Last Name", expected.lName, actual.lName);
		result &= MiscFunctions.compareResult("Customer Phone", expected.hPhone, actual.hPhone);
		result &= MiscFunctions.compareResult("Customer Email", expected.email, actual.email);
		result &= MiscFunctions.compareResult("Customer Organization Name", expected.organization, actual.organization);
		
		return result;
	}
	
	public void verifyCustomerInfo(Customer expected) {
		if(!compareCustomerInfo(expected)) throw new ErrorOnPageException("Receipt customer info is NOT correct.");
		logger.info("Receipt customer info is correct.");
	}
	
	private IHtmlObject[] getReceiptItemTable() {
		IHtmlObject objs[] = browser.getTableTestObject(receiptItemTable());
		if(objs == null) {
			objs = browser.getTableTestObject( ".text", new RegularExpression("^Slip Reservation #:.*|^List Entry Reservation #:.*", false));
		}
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Receipt Item table object.");
		
		return objs;
	}
	
	public HashMap<String, Double> getAllFeeTypesAmountsInReceipItemSection(){
		IHtmlObject[] objs=this.getReceiptItemTable();
		
		//get all fee amounts in Receipt Item section
		IHtmlObject spanObjs[] = browser.getHtmlObject(feeAmountSpan(), objs[0]);
		if(spanObjs.length < 1) throw new ItemNotFoundException("Cannot find Fee amount span objects.");
		List<String> amounts = new ArrayList<String>();
		for(int i = 0; i < spanObjs.length; i ++) {
			amounts.add(spanObjs[i].text());
		}
		Browser.unregister(spanObjs);
		
		IHtmlTable grid = (IHtmlTable)objs[0];
		int rowCount = grid.rowCount();
		List<String> feeTypes = new ArrayList<String>();
		String feeType = "";
		int colIndexes[];
		boolean found = false;
		for(int i = 0; i < amounts.size(); i ++) {
			for(int j = 0; j < rowCount; j ++) {
				colIndexes = grid.findColumns(0, j, amounts.get(i));
				if(colIndexes != null && colIndexes.length > 0) {
					for(int k = 0; k < colIndexes.length; k ++) {
						feeType = grid.getCellValue(j, colIndexes[k] - 1);
						if(!StringUtil.isEmpty(feeType) && !feeTypes.contains(feeType)) {
							feeTypes.add(feeType);
							feeType = null;
							found = true;
							break;
						}
					}
					
					if(found) {
						found = false;
						break;
					}
				}
			}
		}
		Browser.unregister(objs);
		
		HashMap<String, Double> typeAmounts = new HashMap<String, Double>();
		if(feeTypes.size() != amounts.size()) throw new ErrorOnPageException("Fee Type count must equal to Amount count."); 
		for(int i = 0; i < feeTypes.size(); i ++) {
			typeAmounts.put(feeTypes.get(i), Double.parseDouble(amounts.get(i).substring(1)));
		}
		
		return typeAmounts;
	}
	
	public IHtmlObject[] getAmountsTable() {
		IHtmlObject objs[] = browser.getTableTestObject(amountsTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Amounts section table object.");
		return objs;
	}
	
	public HashMap<String, Double> getAllOrderAmounts(){
		IHtmlObject[] objs=this.getAmountsTable();
		
		//get all fee amounts in Amounts section
		IHtmlObject spanObjs[] = browser.getHtmlObject(feeAmountSpan(), objs[0]);
		if(spanObjs.length < 1) throw new ItemNotFoundException("Cannot find Fee amount span objects in Amounts section.");
		List<String> amounts = new ArrayList<String>();
		for(int i = 0; i < spanObjs.length; i ++) {
			amounts.add(spanObjs[i].text());
		}
		Browser.unregister(spanObjs);
		
		IHtmlObject subTableObjs[] = browser.getTableTestObject(Property.toPropertyArray(".class", "Html.TABLE"), objs[0]);
		if(subTableObjs.length < 1) throw new ItemNotFoundException("Cannot find sub table object in Amounts section.");
		IHtmlTable grid = (IHtmlTable)subTableObjs[1];
		int rowCount = grid.rowCount();
		List<String> feeTypes = new ArrayList<String>();
		String feeType = "";
		int colIndexes[];
		boolean found = false;
		for(int i = 0; i < amounts.size(); i ++) {
			for(int j = 0; j < rowCount; j ++) {
				colIndexes = grid.findColumns(0, j, amounts.get(i));
				if(colIndexes != null && colIndexes.length > 0) {
					for(int k = 0; k < colIndexes.length; k ++) {
						feeType = grid.getCellValue(j, colIndexes[k] - 1);
						if(!StringUtil.isEmpty(feeType) && !feeTypes.contains(feeType)) {
							feeTypes.add(feeType);
							feeType = null;
							found = true;
							break;
						}
					}
					
					if(found) {
						found = false;
						break;
					}
				}
			}
		}
		Browser.unregister(objs);
		
		HashMap<String, Double> typeAmounts = new HashMap<String, Double>();
		if(feeTypes.size() != amounts.size()) throw new ErrorOnPageException("Amounts section - Fee Type count must equal to Amount count."); 
		for(int i = 0; i < feeTypes.size(); i ++) {
			double amt = 0;
			if(amounts.get(i).contains("(")) {
				amt = -Double.parseDouble(amounts.get(i).replaceAll("\\(", StringUtil.EMPTY).replaceAll("\\)", StringUtil.EMPTY).trim().substring(1));
			} else {
				amt = Double.parseDouble(amounts.get(i).substring(1));
			}
			
			typeAmounts.put(feeTypes.get(i), amt);
		}
		
		return typeAmounts;
	}
	
	public ReceiptInfo getReceiptItemInfo() {
		ReceiptInfo rcpt = new ReceiptInfo();
		
		rcpt.orderNum = getOrderNum();
		rcpt.facility = getFacility();
		
		rcpt.arrivalDate = getArrivalDate();
		if(rcpt.orderNum.startsWith("2-") && this.getTransactions().contains(OrmsConstants.TRANNAME_SUBMIT_LOTTERY_ENTRY)) {//Site Lottery Order
			rcpt.productName = getLotteryName();
			rcpt.preferredArea = getPreferredArea();
			rcpt.occupant = getOccupant();
			rcpt.arrivalDate = getPreferredArrivalDate();
			rcpt.preferredLengthOfStay = getPreferredLengthOfStay();
		} else if(rcpt.orderNum.startsWith("4-")) {//Ticket Order
			rcpt.category = getCategory();
			rcpt.productName = getTourName();
			rcpt.deliveryMethod = getDeliveryMethod();
		} else if(rcpt.orderNum.startsWith("6-")) {//Permit Order
			rcpt.productName = getPermitEntranceName();
			rcpt.permitType = getPermitType();
			rcpt.groupLeader = getPermitGroupLeader();
			rcpt.groupSize = getPermitGroupSize();
		} else if(rcpt.orderNum.startsWith("7-")) {
			if(this.getTransactions().contains("POS")) {//POS Order
				rcpt.productName = getPOSName();
			} else {//Gift Card
				rcpt.productName = getGiftCardName();
			}
		} else if(rcpt.orderNum.startsWith("8-")) {//Privilege Order
			rcpt.hfCustomerName = getCustomerName();
			rcpt.hfCustomerNum = getCustomerNum();
			rcpt.hfCustomerresidencyStatus = getCustomerResidencyStatus();
			rcpt.licenseYear = getLicenseOrFiscalYear();
			rcpt.productName = getPrivilegePurchasingName();
		} else if(rcpt.orderNum.startsWith("9-")) {//Vehicle Order
			rcpt.hfCustomerName = getCustomerName();
			rcpt.hfCustomerNum = getCustomerNum();
			rcpt.hullIdSerialNum = getHullIdSerialNum();
			rcpt.miNum = getMINum();
			
			rcpt.licenseYear = getLicenseOrFiscalYear();
			rcpt.productName = getVehiclePurchasingName();
			rcpt.purchaseType = getPurchaseType();
		} else if(rcpt.orderNum.startsWith("21-")) {//Supplies Order
			rcpt.productName = getSupplyPurchasingName();
			rcpt.licenseYear = getLicenseOrFiscalYear();
			rcpt.qtyApprived = getQtyApproved();
		} else if(rcpt.orderNum.startsWith("3-")) {
			String transaction = this.getTransactions();
			
			if(transaction.contains(OrmsConstants.TRANNAME_PURCHARSE_POS)) {//normal POS
				rcpt.productName = getPOSName();
			} else {//Consumable-WildlifePOS/Donation/Subscription
				rcpt.productName = getPrivilegePurchasingName();
				rcpt.licenseYear = getLicenseOrFiscalYear();
				
				if(this.getTransactions().contains(OrmsConstants.TRANNAME_PURCHASE_SUBSCRIPTION)) {
					rcpt.subscriber = getSubscriber();
					rcpt.deliveryAddress = getDeliveryAddress();
					rcpt.custEmail = getContactEmailOrPhone();
				}
			}
		} else if(rcpt.orderNum.startsWith("30-")){//Activity Registration Order
			rcpt.licenseYear=this.getLicenseOrFiscalYear();
			rcpt.productName=this.getActivityPurchasingName();
			rcpt.activitySessionInfo=this.getActivitySessionInfo();
			rcpt.hfCustomerName = getCustomerName();
			rcpt.qty = getQtyInfo();
		}
		
		rcpt.feeTypesAmounts = getAllFeeTypesAmountsInReceipItemSection();
		
		return rcpt;
	}
	
	public boolean compareReceiptTransaction(String expected) {
		return getTransactions().contains(expected);
	}
	
	public void verifyReceiptTransaction(String expected) {
		if(!compareReceiptTransaction(expected)) throw new ErrorOnPageException("Receipt Transaction(s) info is NOT correct.");
		logger.info("Receipt Transaction(s) info is correct as: " + expected);
	}
	
	public boolean compareReceiptItemInfo(ReceiptInfo expected) {
		ReceiptInfo actual = this.getReceiptItemInfo();
		boolean result = true;
		
		result &= MiscFunctions.compareResult("Order Num", expected.orderNum, actual.orderNum);
		if(!StringUtil.isEmpty(expected.facility)) {
			result &= MiscFunctions.compareResult("Facility", expected.facility, actual.facility);
		}
		if(!StringUtil.isEmpty(expected.category)) {
			result &= MiscFunctions.compareResult("Permit/Ticket Category", expected.category, actual.category);
		}
		
		result &= MiscFunctions.compareResult("Product Name", expected.productName, actual.productName);
		
		if(!StringUtil.isEmpty(expected.arrivalDate)) {
			result &= MiscFunctions.compareResult("Arrival Date", expected.arrivalDate, actual.arrivalDate);
		}
		if(!StringUtil.isEmpty(expected.deliveryMethod)) {
			result &= MiscFunctions.compareResult("Delivery Method", expected.deliveryMethod, actual.deliveryMethod);
		}
		if(!StringUtil.isEmpty(expected.permitType)) {
			result &= MiscFunctions.compareResult("Permit Type", expected.permitType, actual.permitType);
		}
		if(!StringUtil.isEmpty(expected.groupLeader)) {
			result &= MiscFunctions.compareResult("Permit Group Leader", expected.groupLeader, actual.groupLeader);
		}
		if(!StringUtil.isEmpty(expected.groupSize)) {
			result &= MiscFunctions.compareResult("Permit Group Size", expected.groupSize, actual.groupSize);
		}
		if(!StringUtil.isEmpty(expected.hfCustomerName)) {
			result &= MiscFunctions.compareResult("Customer Name", expected.hfCustomerName, actual.hfCustomerName);
		}
		if(!StringUtil.isEmpty(expected.hfCustomerNum)) {
			result &= MiscFunctions.compareResult("Customer Num", expected.hfCustomerNum, actual.hfCustomerNum);
		}
		if(!StringUtil.isEmpty(expected.hfCustomerresidencyStatus)) {
			result &= MiscFunctions.compareResult("Customer Residency Status", expected.hfCustomerresidencyStatus, actual.hfCustomerresidencyStatus);
		}
		if(!StringUtil.isEmpty(expected.licenseYear)) {
			result &= MiscFunctions.compareResult("License Year", expected.licenseYear, actual.licenseYear);
		}
		if(!StringUtil.isEmpty(expected.subscriber)) {
			result &= MiscFunctions.compareResult("Subscriber", expected.subscriber, actual.subscriber);
		}
		if(!StringUtil.isEmpty(expected.deliveryAddress)) {
			result &= MiscFunctions.compareResult("Delivery Address", expected.deliveryAddress, actual.deliveryAddress);
		}
		if(!StringUtil.isEmpty(expected.custEmail)) {
			result &= MiscFunctions.compareResult("Contact Email", expected.custEmail, actual.custEmail);
		}
		if(!StringUtil.isEmpty(expected.preferredArea)) {
			result &= MiscFunctions.compareResult("Preferred Area", expected.preferredArea, actual.preferredArea);
		}
		if(!StringUtil.isEmpty(expected.occupant)) {
			result &= MiscFunctions.compareResult("Occupant", expected.occupant, actual.occupant);
		}
		if(!StringUtil.isEmpty(expected.preferredLengthOfStay)) {
			result &= MiscFunctions.compareResult("Preferred Length Of Stay", expected.preferredLengthOfStay, actual.preferredLengthOfStay);
		}
//		verify activity session info
		if(!StringUtil.isEmpty(expected.activitySessionInfo)){
			result &= MiscFunctions.compareResult("Activity Session Info", expected.activitySessionInfo,actual.activitySessionInfo);
		}
		//verify qty info
		if(0<expected.qty.size()){
			result &= MiscFunctions.compareResult("Qty Info size", expected.qty.size(), actual.qty.size());
			for(int i=0;i<expected.qty.size();i++) {
				result &= MiscFunctions.compareResult("Qty Info",expected.qty.get(i), actual.qty.get(i));
			}

		}
		result &= MiscFunctions.compareResult("Fee types and amounts size", expected.feeTypesAmounts.size(), actual.feeTypesAmounts.size());
		if(expected.feeTypesAmounts.size() == actual.feeTypesAmounts.size()) {
			for(Map.Entry<String, Double> me : expected.feeTypesAmounts.entrySet()) {
				result &= MiscFunctions.compareResult("Fee Type and Amount - " + me.getKey(), me.getValue(), actual.feeTypesAmounts.get(me.getKey()));
			}
		}
		
		return result;
	}
	
	public void verifyReceiptItemInfo(ReceiptInfo expected) {
		if(!compareReceiptItemInfo(expected)) throw new ErrorOnPageException("Receipt Item info is NOT correct.");
		logger.info("Receipt Item info is correct.");
	}
	
	public boolean compareReceiptAmountsInfo(ReceiptInfo expected) {
		ReceiptInfo actual = new ReceiptInfo();
		actual.amounts = this.getAllOrderAmounts();
		boolean result = true;
		
		//workaround
		String workAround = "Refund to Existing Voucher";
		if(actual.amounts.containsKey(workAround)) {
			Double temp = actual.amounts.get(workAround);
			actual.amounts.remove(workAround);
			actual.amounts.put("Refund to existing Voucher", temp);
		}
		
		//verify fee types and corresponding amounts in Amounts section
		result &= MiscFunctions.compareResult("Fee types and amounts size", expected.amounts.size(), actual.amounts.size());
				
		if(expected.amounts.size() == actual.amounts.size()) {
			for(Map.Entry<String, Double> me : expected.amounts.entrySet()) {
				result &= MiscFunctions.compareResult("Amounts section - Fee Type and Amount - " + me.getKey(), me.getValue(), actual.amounts.get(me.getKey()));
			}
		}
		
		return result;
	}
	
	public void verifyReceiptAmountsInfo(ReceiptInfo expected) {
		if(!compareReceiptAmountsInfo(expected)) throw new ErrorOnPageException("Receipt Amounts info is NOT correct.");
		logger.info("Receipt Amounts info is correct.");
	}
	//added by Summer
	public void verifyVoucherRedeemed(String voucherId){
		boolean result = true;
		logger.info("Verify voucher Redeemed for voucher#"+voucherId+" exist.");
		IHtmlObject objs[] = browser.getHtmlObject(voucherRedeemedSpan());
		if(objs.length<1){
			throw new ErrorOnPageException("Cannot find 'Voucher Redeemed' span object.");
		}
		result &= MiscFunctions.compareResult("Payment id", true,this.isVoucherIdInRedeemedSectionExist(voucherId, objs[objs.length-1]));
		if(!result){
			throw new ErrorOnPageException("Voucher redeemed information is wrong");
		}
		Browser.unregister(objs);
	}
	//added by Summer
	public void verifyPaymentExist(String paymentId){
		boolean result = true;
		logger.info("Verify Payment#"+paymentId+" exist.");
		IHtmlObject objs[] = browser.getHtmlObject(payementsTr());
		if(objs.length<1){
			throw new ErrorOnPageException("Cannot find 'Payements' span object.");
		}
		result &= MiscFunctions.compareResult("Payment id", true,this.isPaymentIdExist(paymentId, objs[objs.length-1]));
		if(!result){
			throw new ErrorOnPageException("Payment information is wrong");
		}
		Browser.unregister(objs);
	}
	public void clickPermitEntranceName() {
		browser.clickGuiObject(entranceName());
	}
	
	public void verifyPermitEntranceAsLink() {
		if(!browser.checkHtmlObjectExists(entranceName()))
			throw new ErrorOnPageException("Permit Entrance name shall be displayed as link.");
	}
	
	public HashMap<String, Double> getReceiptFeesSummaryInfo() {
		IHtmlObject objs[] = browser.getTableTestObject(receiptFeesSummaryTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Receipt Fees Summary table object.");
		IHtmlObject feeSectionObjs[] = browser.getTableTestObject(Property.toPropertyArray(".class", "Html.TABLE"), objs[0]);
		if(feeSectionObjs.length < 1) throw new ItemNotFoundException("Cannot find Receipt Fees Summary - Fee Section table object.");
		
		IHtmlTable table = (IHtmlTable)feeSectionObjs[1];
		HashMap<String, Double> feeTypesAmounts = new HashMap<String, Double>();
		feeTypesAmounts.put(table.getCellValue(0, 3), Double.parseDouble(table.getCellValue(0, 4).substring(1)));
		for(int i = 1; i < table.rowCount(); i ++) {
			feeTypesAmounts.put(table.getCellValue(i, 0), Double.parseDouble(table.getCellValue(i, 1).substring(1)));
		}
		
		Browser.unregister(feeSectionObjs);
		Browser.unregister(objs);
		
		return feeTypesAmounts;
	}
	
	public boolean compareReceiptFeesSummaryInfo(ReceiptInfo expected) {
		boolean result = true;
		HashMap<String, Double> actual = getReceiptFeesSummaryInfo();
		result &= MiscFunctions.compareResult("Receipt Fees Summary section - fee records size", expected.feeTypesAmounts.size(), actual.size());
		if(result) {
			for(Map.Entry<String, Double> me : expected.feeTypesAmounts.entrySet()) {
				result &= MiscFunctions.compareResult("Receipt Fees Summary section - Fee Type and Amount - " + me.getKey(), me.getValue(), actual.get(me.getKey()));
			}
		}
		
		return result;
	}
	
	public void verifyReceiptFeesSummaryInfo(ReceiptInfo expected) {
		if(!compareReceiptFeesSummaryInfo(expected)) throw new ErrorOnPageException("Receipt Fees Summary section info is NOT correct.");
		logger.info("Receipt Fees Summary section info is correct.");
	}
	public void verifyReceiptFeesSummaryInfoBlank() {
		IHtmlObject objs[] = browser.getTableTestObject(receiptFeesSummaryTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Receipt Fees Summary table object.");
		IHtmlObject feeSectionObjs[] = browser.getTableTestObject(Property.toPropertyArray(".class", "Html.TABLE"), objs[0]);
		if(feeSectionObjs.length < 2){
			throw new ItemNotFoundException("Cannot find Receipt Fees Summary - Fee Section table object.");
		}
		if(StringUtil.isEmpty(feeSectionObjs[1].text())) {
			logger.info("Fee Summery Section is blank");
		} else {
			throw new ErrorOnPageException("Fee Summery Section is blank");
		}
		Browser.unregister(feeSectionObjs);
		Browser.unregister(objs);
		
	}
	public void clickPaymentID() {
		browser.clickGuiObject(paymentID());
	}
}
