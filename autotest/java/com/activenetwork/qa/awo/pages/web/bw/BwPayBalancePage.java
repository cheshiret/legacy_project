package com.activenetwork.qa.awo.pages.web.bw;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class BwPayBalancePage extends UwpPage {

	private static BwPayBalancePage _instance = null;

	public static BwPayBalancePage getInstance() {
		if (null == _instance)
			_instance = new BwPayBalancePage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.BUTTON", ".text", "Pay Balance");
	}

	/**
	 * Click on pay balance link.
	 */
	public void clickPayBalance() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".text", "Pay Balance");
	}

	/**
	 * Retrieve the paid amount.
	 * @return - amount
	 */
	public double getPaymentAmount() {
//		RegularExpression regex = new RegularExpression("Payment (Method|Information).*",false);
		IHtmlObject[] objs = browser.getTableTestObject(".id", "equiptment");
		if(objs.length<1) {
			throw new ItemNotFoundException("Failed to find the test objects.");
		}
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		double amount = Double.parseDouble(RegularExpression.getMatches(text,"[0-9]+\\.[0-9][0-9]")[0]);
		return amount;
	}
	
	/**
	 * Fill in card number.
	 * @param cardNum - card number
	 */
	public void setCartNumber(String cardNum) {
		browser.setTextField(".name", "cardNumber", cardNum);
	}
	
	/**
	 * Select card type field.
	 * @param type - card type
	 */
	public void selectCardType(String type) {
		browser.selectDropdownList(".name", "cardType", type);
	}
	
	/**
	 * Fill in security code field.
	 * @param secCode - security code
	 */
	public void setSecurityCode(String secCode) {
		browser.setTextField(".name", "securityCode", secCode);
	}
	
	/**
	 * Fill in expire month field.
	 * @param mon - expire month
	 */
	public void setExpireMonth(String mon) {
		browser.setTextField(".name", "expMonth", mon);
	}
	
	/**
	 * Fill in expire year field.
	 * @param year - expire year
	 */
	public void setExpireYear(String year) {
		browser.setTextField(".name", "expYear", year);
	}
	
	/**
	 * Fill in first name.
	 * @param fName - first name
	 */
	public void setFirstName(String fName) {
		browser.setTextField(".name", "firstName", fName);
	}

	/**
	 * Fill in last name.
	 * @param lName - last name
	 */
	public void setLastName(String lName) {
		browser.setTextField(".name", "lastName", lName);
	}
	
	/**
	 * Fill in post code field, if not provided, fill in 12345
	 * @param code - post code
	 */
	public void setPostCode(String code) {
		if (code !=null && code.length()>0)
			code = "12345";
		browser.setTextField(".name", "postalCode", code);
	}
	
	/**
	 * Fill in all payment fields.
	 * @param pay - payment info
	 */
	public void setupPayment(Payment pay) {
		this.selectCardType(pay.payType);
		this.setCartNumber(pay.creditCardNumber);
		this.setSecurityCode(pay.securityCode);
		this.setExpireMonth(pay.expiryMon);
		this.setExpireYear(pay.expiryYear);
		this.setFirstName(pay.cardHolder.split(" ", 2)[0]);
		this.setLastName(pay.cardHolder.split(" ", 2)[1]);
		this.setPostCode(pay.zip);
	}
	
	/**
	 * Verify whether payment field is available to do payment.
	 * @return
	 */
	public boolean needPayment() {
		boolean toReturn = false;
		IHtmlObject[] objs = browser.getRadioButton(".id", "payCCOnFile");
		if (objs.length < 1) {
			Browser.unregister(objs);
			objs = browser.getTextField(".name", "cardNumber");
			toReturn = objs.length > 0;
		} else {
			toReturn = objs[0].getProperty(".checked").toString().equalsIgnoreCase("false");
		}
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * Get error message on the top of page
	 * @return
	 */
	public String getErrorMsg() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".className", "msg error");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Error Msg DIV object.");
		}
		String msg = objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		return msg;
	}
}
