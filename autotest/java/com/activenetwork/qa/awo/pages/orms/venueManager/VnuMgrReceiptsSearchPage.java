/*
 * Created on Oct 20, 2009
 */
package com.activenetwork.qa.awo.pages.orms.venueManager;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class VnuMgrReceiptsSearchPage extends VenueManagerPage {
	private static VnuMgrReceiptsSearchPage _instance = null;

	public static VnuMgrReceiptsSearchPage getInstance() {
		if (null == _instance) {
			_instance = new VnuMgrReceiptsSearchPage();
		}
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Receipt Search/List");
	}

	/**
	 * Fill in receipt number field.
	 * @param receiptNum receipt number
	 */
	public void setReceiptNum(String receiptNum) {
		browser.setTextField(".id", "receiptNumber", receiptNum);
	}

	/**
	 * Fill in order number.
	 * @param orderNum - order number
	 */
	public void setOrderNum(String orderNum) {
		browser.setTextField(".id", "reservationNumber", orderNum);
	}

	/**
	 * Fill in receipt from date.
	 * @param reFromDate - from date
	 */
	public void setReceiptFromDate(String reFromDate) {
		browser.setTextField(".id", "receiptStartDate_ForDisplay", reFromDate);
	}

	/**
	 * Fill in receipt end date.
	 * @param reToDate - end date
	 */
	public void setReceiptToDate(String reToDate) {
		browser.setTextField(".id", "receiptEndDate_ForDisplay", reToDate);
	}

	/**
	 * Fill in receipt location field.
	 * @param localtion
	 */
	public void setLocaltion(String localtion) {
		browser.setTextField(".id", "receiptLocation", localtion);
	}

	/**
	 * Fill in sales station.
	 * @param saleStation - sale station
	 */
	public void setSalesStation(String saleStation) {
		browser.setTextField(".id", "receiptStation", saleStation);
	}

	/**
	 * Fill in registry id.
	 * @param registID - registry id
	 */
	public void setRegistID(String registID) {
		browser.setTextField(".id", "registryID", registID);
	}

	/**
	 * Fill in customer phone number.
	 * @param cusPhone - phone number
	 */
	public void setCustomerPhone(String cusPhone) {
		browser.setTextField(".id", "phone", cusPhone);
	}

	/**
	 * Fill in customer last name.
	 * @param lName - last name
	 */
	public void setLastName(String lName) {
		browser.setTextField(".id", "lastName", lName);
	}

	/**
	 * Fill in customer first name.
	 * @param fName - first name
	 */
	public void setFirstName(String fName) {
		browser.setTextField(".id", "firstName", fName);
	}

	/**
	 * Fill in customer email address.
	 * @param email - email account
	 */
	public void setEmail(String email) {
		browser.setTextField(".id", "email", email);
	}

	/**
	 * Fill in check number.
	 * @param checkNum - check number
	 */
	public void setCheckNum(String checkNum) {
		browser.setTextField(".id", "checkNumber", checkNum);
	}

	/**
	 * Fill in the receipt start price.
	 * @param receiptPrice - start price
	 */
	public void setTotalRecepitPriceMore(String receiptPrice) {
		browser.setTextField(".id", "startPrice", receiptPrice);
	}

	/**
	 * Fill in the receipt end price.
	 * @param receipt - end price
	 */
	public void setTotalRecepitPriceLess(String receipt) {
		browser.setTextField(".id", "endPrice", receipt);
	}

	/**
	 * Select receipt contains from dropdown list.
	 * @param contain - items of contain
	 */
	public void selectReceiptContains(String contain) {
		browser.selectDropdownList(".id", "receiptContains", contain);
	}

	/**
	 * Select payment type from dropdown list.
	 * @param type - payment type
	 */
	public void selectPaymentType(String type) {
		browser.selectDropdownList(".id", "paymentType", type);
	}

	/**Select include area code field.*/
	public void checkAreaCode() {
		browser.selectCheckBox(".id", "includeAreaCode");
	}

	/**Unselect include area code field.*/
	public void uncheckAreaCode() {
		browser.unSelectCheckBox(".id", "includeAreaCode");
	}

	/**Click on Go to do searching.*/
	public void clickGo() {
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("^Search$", false), ".href", new RegularExpression("ReceiptList\\.do", false)));
	}
	
	/**
	 * Get the receipt from the details page
	 * @return receipt
	 */
	public String getReceipt(){
		String searResTable = "";
	  	IHtmlObject[] searRes_1 = browser.getTableTestObject(".class",
			"Html.TABLE", ".text", new RegularExpression(
					"^Receipt # Reservations POS Sales.*", false)); 
	  	IHtmlObject[] searRes_2 = browser.getTableTestObject(".class",
				"Html.TABLE", ".text", new RegularExpression(
						"^Receipt # Orders Receipt Date & Time.*", false)); //QA4
	  	if(searRes_1.length>0){
		  	searResTable = ((IHtmlTable) searRes_1[0]).getCellValue(1, 9);
	  	}else if(searRes_2.length>0){
	  		searResTable = ((IHtmlTable) searRes_2[0]).getCellValue(1, 5);//QA4
	  		//Remove the "$" before compare
	  		if(searResTable.contains("$")){
	  			searResTable = searResTable.trim().replaceAll("\\$", "");
	  		}
	  	}else throw new ItemNotFoundException("Item can't find.");

	  	return searResTable;
	}
	/**
	 * get 
	 */
	public String getReceiptNum(){
		String receiptNum = "";
	  	IHtmlObject[] searRes_1 = browser.getTableTestObject(".class",
			"Html.TABLE", ".text", new RegularExpression(
					"^Receipt # Reservations POS Sales.*", false)); 
	  	IHtmlObject[] searRes_2 = browser.getTableTestObject(".class",
				"Html.TABLE", ".text", new RegularExpression(
						"^Receipt # Orders Receipt Date & Time.*", false)); //QA4
	  	if(searRes_1.length>0){
		  	receiptNum = ((IHtmlTable) searRes_1[0]).getCellValue(1, 0);
	  	}else if(searRes_2.length>0){
	  		receiptNum = ((IHtmlTable) searRes_2[0]).getCellValue(1, 0);//QA4
	  	}else throw new ItemNotFoundException("Item can't find.");

	  	return receiptNum;
	}
	
	
	/**
	 * click receipt number.
	 * @param receiptNum
	 */
	public void clickReceiptNum(String receiptNum){
		browser.clickGuiObject(".class", "Html.A", ".text", this.getReceiptNum());
	}
}
