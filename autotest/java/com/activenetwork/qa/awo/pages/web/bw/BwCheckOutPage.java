package com.activenetwork.qa.awo.pages.web.bw;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class BwCheckOutPage extends UwpPage {

	private static BwCheckOutPage _instance = null;

	public static BwCheckOutPage getInstance() {
		if (null == _instance)
			_instance = new BwCheckOutPage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.BUTTON", ".text",
				"Complete this Purchase");
	}

	/**Click Complete This Purchase button.*/
	public void clickCompleteThisPurchase() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".id","chkout");
	}

	/**
	 * Select acknowledge to accept the statement.
	 */
	public void selectAcknowlegeAccepted() {
		browser.selectCheckBox(".name", "acknowlegeAccepted");
	}

	/**
	 * Select accept statement checkbox and click complete this purchase button.
	 */
	public void checkOut() {
		this.selectAcknowlegeAccepted();
		this.clickCompleteThisPurchase();
	}

	/**
	 * Verify payment amount is equal to given grand total, and equal to total amount.
	 * @param grandTotal - given grand total
	 * @return
	 * @throws PageNotFoundException
	 */
	public double verifyFees(double grandTotal) throws PageNotFoundException {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.FORM",".id", "checkoutCartForm");
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		int i = text.indexOf("Total");
		int j = text.indexOf("Acknowledgement");
		text = text.substring(i, j);
		String[] fees = RegularExpression.getMatches(text, "[0-9]+\\.[0-9][0-9]");
		if (Double.parseDouble(fees[1]) != grandTotal)
			throw new PageNotFoundException(
					"Payment amount is not equial to the given grand total");
		if (Double.parseDouble(fees[1]) != Double.parseDouble(fees[0]))
			throw new PageNotFoundException(
					"Payment amount is not equial to total amount");

		return Double.parseDouble(fees[1]);
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
	 * Fill in post code field, if not provided, fill in 12345
	 * @param code - post code
	 */
	public void setPostCode(String code) {
		if (code !=null && code.length()>0)
			code = "12345";
		browser.setTextField(".name", "postalCode", code);
	}

	/**
	 * Select card type field.
	 * @param type - card type
	 */
	public void selectCardType(String type) {
		browser.selectDropdownList(".name", "cardType", type);
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
			toReturn = objs[0].getProperty(".checked").toString()
					.equalsIgnoreCase("false");
		}
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Fill in card number.
	 * @param cardNum - card number
	 */
	public void setCartNumber(String cardNum) {
		browser.setTextField(".name", "cardNumber", cardNum);
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
	
}
