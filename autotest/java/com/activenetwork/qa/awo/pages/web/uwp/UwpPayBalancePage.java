/*
 * Created on Oct 29, 2009
 *
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;

/**
 * @author QA
 *
 */
public class UwpPayBalancePage extends UwpPage {

	private static UwpPayBalancePage _instance = null;

	public static UwpPayBalancePage getInstance() {
		if (null == _instance)
			_instance = new UwpPayBalancePage();

		return _instance;
	}

	public UwpPayBalancePage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.BUTTON", ".text",
				"Pay Balance");
	}
	
	/**
	 * Click on Pay Balance link.
	 */
	public void clickPayBalance() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".text", "Pay Balance");
	}
	
	/**
	 * Fill in payment information.
	 * @param pay - payment information
	 */
	public void setupPayment(Payment pay) {
		selectAllPaymentTypes(pay.payType);
		setAllCardNumbers(pay.creditCardNumber);
		setAllSecurityCodes(pay.securityCode);
		setAllExpireMonthes(pay.expiryMon);
		setAllExpireYears(pay.expiryYear);
		setAllCardHolderNames(pay.lName, pay.fName);
		setAllPostCode(pay.zip);
	}
	
	/**
	 * Fill in Post Code field.
	 * @param code - post code
	 */
	public void setPostCode(String code) {
		if (code.length() < 1)
			code = "12345";
		browser.setTextField(".id", "ccPostCode", code);
	}
	
	/**
	 * Fill in all post code fields with the same code.
	 * @param code - post code
	 */
	public void setAllPostCode(String code) {
		if (code.length() < 1)
			code = "12345";
		IHtmlObject[] objs = browser.getTextField(".id", "ccPostCode");
		if (objs.length > 0) {
			for (int i = 0; i < objs.length; i++) {
				((IText)objs[i]).setText(code);
			}
		}

		Browser.unregister(objs);
	}
	
	/**
	 * Select payment type.
	 * @param type - payment type
	 */
	public void selectPaymentType(String type) {
		browser.selectDropdownList(".id", "cardtype", type);
	}
	
	/**
	 * Select all payment type fields with same type.
	 * @param type - payment type
	 */
	public void selectAllPaymentTypes(String type) {
		IHtmlObject[] objs = browser.getDropdownList(".name", "cardtype");
		for (int i = 0; i < objs.length; i++) {
			((ISelect)objs[i]).select(type);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Fill in all card number fields with same number.
	 * @param num - card number
	 */
	public void setAllCardNumbers(String num) {
		IHtmlObject[] objs = browser.getTextField(".name", "cardNumber");
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(num);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Fill in card number.
	 * @param num - card number
	 */
	public void setCardNumber(String num) {
		browser.setTextField(".name", "cardNumber", num);
	}
	
	/**
	 * Fill in all expire month fields with same month.
	 * @param mon - expire month
	 */
	public void setAllExpireMonthes(String mon) {
		IHtmlObject[] objs = browser.getTextField(".name", "expMonth");
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(mon);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Fill in expire month.
	 * @param mon - expire month
	 */
	public void setExpireMonth(String mon) {
		browser.setTextField(".id", "exdate", mon);
	}
	
	/**
	 * Fill in all expire year fields with same year.
	 * @param year - expire year
	 */
	public void setAllExpireYears(String year) {
		IHtmlObject[] objs = browser.getTextField(".name", "expYear");
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(year);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Fill in expire year.
	 * @param year - expire year
	 */
	public void setExpireYear(String year) {
		browser.setTextField(".id", "exyear", year);
	}
	
	/**
	 * Fill in all card holder fields with same first and last name.
	 * @param lName - first name
	 * @param fName - last name
	 */
	public void setAllCardHolderNames(String lName, String fName) {
		IHtmlObject[] objs = browser.getTextField(".name", "firstName");
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(fName);
		}
		Browser.unregister(objs);

		objs = browser.getTextField(".name","lastName");
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(lName);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Fill in card holder info.
	 * @param lName - frist name
	 * @param fName - last name
	 */
	public void setCardHolderName(String lName, String fName) {
		browser.setTextField(".id", "fname", fName);
		browser.setTextField(".id", "lname", lName);
	}
	
	/**
	 * Fill in all security code fields with same code.
	 * @param code - security code
	 */
	public void setAllSecurityCodes(String code) {
		IHtmlObject[] objs = browser.getTextField(".name", "securityCode");
		for (int i = 0; i < objs.length; i++) {
			((IText)objs[i]).setText(code);
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Fill in security code.
	 * @param code - security code
	 */
	public void setSecurityCode(String code) {
		browser.setTextField(".id", "securitycodeid", code);
	}
	
	/**
	 * This table contain "Item", "Details" and "Transaction Details" 
	 * @return
	 */
	public IHtmlObject[] getItemsOrderTable(){
		return browser.getTableTestObject(".id", "shoppingitems", ".className", "items order");
	}
	
	/**
	 * Get the Items order table content
	 * "Item"
	 * "Details"
	 * "Transaction Details" 
	 * @return
	 */
	public String getItemsOrderContent(){
		String itemsOrderContent = "";
		IHtmlObject[] objs = this.getItemsOrderTable();
		if(null==objs || objs.length<0){
			throw new ObjectNotFoundException("Items order table can't be found.");
		}
		
		itemsOrderContent = objs[0].text();
		
		Browser.unregister(objs);
		return itemsOrderContent;
	}
	
	public String getItemsSectionContent(){
		return this.getItemsOrderContent().split("Transaction Details")[1].split("Entry Date")[0].trim();
	}
	
	public String getDetailsSectionContent(){
		return "Entry Date"+this.getItemsOrderContent().split("Entry Date")[1].split("Reservation Total")[0].trim();
	}
	
	public String getTransactionDetailsContent(){
		return "Reservation Total"+this.getItemsOrderContent().split("Reservation Total")[1].trim();
	}
}
