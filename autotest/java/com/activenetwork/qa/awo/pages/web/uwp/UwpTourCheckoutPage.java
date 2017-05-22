package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.regex.Matcher;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author jchen
 */
public class UwpTourCheckoutPage extends UwpPage {
	
	private static UwpTourCheckoutPage _instance = null;

	public static UwpTourCheckoutPage getInstance() {
		if (null == _instance)
			_instance = new UwpTourCheckoutPage();

		return _instance;
	}

	public UwpTourCheckoutPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "chkout",".text","Complete this Purchase");
	}

	/**
	 * Select payment type.
	 * @param type - payment type
	 */
	public void selectPaymentType(String type) {
		browser.selectDropdownList(".id", "cardTypeId_1", type);
	}

	/**
	 * fill in first card number field.
	 * @param num - card number 
	 */
	public void setCardNumber(String num) {
		browser.setTextField(".id", "cardnum_1", num);
	}

	/**
	 * Fill in the first card expire month field.
	 * @param mon - expire month
	 */
	public void setExpireMonth(String mon) {
		browser.setTextField(".id", "expmonth_1", mon);
	}

	/**
	 * Fill in the first card expire year field.
	 * @param year - expire year
	 */
	public void setExpireYear(String year) {
		browser.setTextField(".id", "expyear_1", year);
	}

	/**
	 * Fill in the first First name field.
	 * @param fName - First Name
	 */
	public void setFirstName(String fName) {
		browser.setTextField(".id", "fname_1", fName);
	}

	/**
	 * Fill in the first Last name field.
	 * @param fName - Last Name
	 */
	public void setLastName(String lName) {
		browser.setTextField(".id", "lname_1", lName);
	}
	
	/**
	 * Fill in the first security code field.
	 * @param code - security code
	 */
	public void setPostCode(String code) {
	  	browser.setTextField(".id", "ccPostCode_1", code);
	}
	
	/**
	 * Fill in the first security code field.
	 * @param code - security code
	 */
	public void setSecurityCode(String secCode){
	  	browser.setTextField(".id", "seccode_1", secCode);
	}
	
	/**
	 * Retrieve entire order cart info.
	 * @return - text on order cart
	 */
	public String getEquipment() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "equiptment");
		
		String text = (String) objs[0].getProperty(".text");
		Browser.unregister(objs);
		return text;
	}

	/**
	 * select to accept acknowlege statement.
	 */
	public void selectAcknowlegeAcceptedCheckBox() {
		browser.selectCheckBox(".name", "acknowlegeAccepted");
	}

	/**
	 * Click on Check Out Cart button.
	 */
	public void clickCheckOut() {
		browser.clickGuiObject(".id", "chkout");
	}
	
	/**
	 * Check whether the post code field exists.
	 * @return - true - found; false - not found
	 */
	public boolean checkPostCodeExists() {
	  	return browser.checkHtmlObjectExists(".id", new RegularExpression("ccPostCode_[0-9]+", false));
	}
	
	/**
	 * Check whether the Security code field exists.
	 * @return - true - found; false - not found
	 */
	public boolean checkSecurityCodeExists() {
	  	return browser.checkHtmlObjectExists(".id", new RegularExpression("^seccode_[0-9]+", false));
	}
	
	/**
	 * Fill in all paymen info and check out cart.
	 * @param pay - payment info
	 * @return pay amount
	 * @throws ItemNotFoundException
	 */
	public String completePurchase(Payment pay) throws ItemNotFoundException {
		String text = this.getEquipment();

		String fullPayment = ".*Full Payment: \\$([0-9]+.[0-9][0-9]).*";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(fullPayment);
		Matcher m = p.matcher(text);

		if (m.matches()) {
			String parseamount = m.group(1).trim();
			if (Double.parseDouble(parseamount) == Double.parseDouble(pay.amount)) {
				this.selectPaymentType(pay.payType);
				this.setCardNumber(pay.creditCardNumber);
				
				if(this.checkSecurityCodeExists()){
				  	if(pay.securityCode.length()<1){
				  	  	pay.securityCode="123";
				  	}
				  	this.setSecurityCode(pay.securityCode);	
				}
				
				this.setExpireMonth(pay.expiryMon);
				this.setExpireYear(pay.expiryYear);
				this.setFirstName(pay.cardHolder.split(" ", 2)[0]);
				this.setLastName(pay.cardHolder.split(" ", 2)[1]);
				
				if(this.checkPostCodeExists()){
				  	if(pay.zip.length()<1){
				  	  pay.zip="12345";
				  	}
				  	this.setPostCode(pay.zip);
				}
				this.selectAcknowlegeAcceptedCheckBox();
				this.clickCheckOut();
				return pay.amount;
			} else {
				throw new ItemNotFoundException("The amount in Payment page: "
						+ parseamount
						+ " doesn't match thea mount in Shopping cart page: "
						+ pay.amount);
			}
		} else {
			throw new ItemNotFoundException(
					"The code doesn't parse the total amount right in the payment page");
		}
	}
	
	/**
	 * Retrieve the hidden payment fields, used for verify free reservation.
	 * @return - number of hidden payment fields
	 */
	public int getHiddenPaymentFields() {
	  	RegularExpression reg = new RegularExpression("^cardnum_[0-9]+", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.INPUT.hidden", ".id", reg);
		int num = objs.length;
		
		Browser.unregister(objs);
		return num;
	}
}
