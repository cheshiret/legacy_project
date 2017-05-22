/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.common;

import java.math.BigDecimal;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.pages.web.uwp.UwpSchConstants;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 17, 2013
 */
public abstract class UwpConfirmationCommonPage extends UwpPage {

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("(backhome|shophdrtop)", false));
	}

	/**
	 * Click on 'Current Reservations' link
	 */
	public void gotoCurrentReservations() {
		browser.clickGuiObject(".id", "viewcrr");
	}
	
	/**
	 * Go back to Home page
	 */
	public void gotoHomePage() {
		browser.clickGuiObject(".id", "backhome");
	}

	/**
	 * Retrieve text on confrimation page.
	 * @return - text on page
	 */
	public String getTextInConfirmPage() {
		IHtmlObject[] obj = browser.getHtmlObject(".id", "shoppingitems1");

		String content = obj[0].getProperty(".text");
		Browser.unregister(obj);

		return content;
	}
	
	public String[] getAllReservationNums(){
		String tableText = getTextInConfirmPage();
		return RegularExpression.getMatches(tableText, UwpSchConstants.RESVNO);
	}

	public String getAllResNums() {
		String[] nums = this.getAllReservationNums();
		String allNum = "";
		for (String num : nums) {
			allNum += num + " ";
		}
		return allNum.trim();
	}
	
	/**
	* Retrieve the number of contracts which the order items belong to.
	 * @return - number of contracts
	 */
	public int numberOfContracts() {
		IHtmlObject[] objs = browser.getHtmlObject(".className","partition");
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	/**
	 * Retrieve of the number of orders in confirmation page.
	 * @return number of orders
	 */
	public int numberOfOrders() {
		String tableText = getTextInConfirmPage();
		int numOfItems = tableText.split("#").length - 1;
		return numOfItems;
	}
	
	/**
	 * Get payment amount 
	 * @param divClassName
	 * @param divStartingText
	 * @return
	 */
	public BigDecimal getPaymentAmount(String divClassName, String divStartingText){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", divClassName, ".text", new RegularExpression(""+divStartingText+".*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "money");

		IHtmlObject[] objs =browser.getHtmlObject(Property.atList(p1, p2));
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException(divStartingText+" money object can't be found.");
		}

		String paymentValue = objs[0].text().replace("$", "").trim(); 
		BigDecimal paymentAmount = BigDecimal.ZERO;
		if(paymentValue.contains("(")){ //$(31.20)
			paymentAmount = BigDecimal.ZERO.subtract(new BigDecimal(RegularExpression.getMatches(paymentValue, "\\d+\\.\\d+")[0]));
		}else
			paymentAmount  = new BigDecimal(paymentValue);

		Browser.unregister(objs);
		return paymentAmount;
	}
	
	/** Subtotal = camping fee = use fee + attribute fee */
	public BigDecimal getSubtotalAmount() {
		return this.getPaymentAmount("subtotal", "Subtotal");
	}
	
	/** Reservation fee = Transaction fee */
	public BigDecimal getResFeeAmount() {
		return this.getPaymentAmount("subtotal", "Reservation Fee");
	}
	
	/**Taxes = use t + attribute t + transaction t */
	public BigDecimal getTaxesAmount() {
		return this.getPaymentAmount("subtotal", "Taxes");
	}
	
	public BigDecimal getTotalAmount() {
		return this.getPaymentAmount("total", "Total");
	}

	public BigDecimal getPaidAmount(){
		return this.getPaymentAmount("subtotal", "Amount Paid");
	}
	
	public BigDecimal getBalanceAmount(){
		return this.getPaymentAmount("subtotal", "Balance");
	}
}
