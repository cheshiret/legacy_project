/*
 * $Id: OrmsEventNegotiatePricePage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.event;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author jdu
 */
public class OrmsEventNegotiatePricePage extends OrmsPage {

	/**
	 * Script Name   : OrmsEventNegotiatePricePage
	 * Generated     : Jan 25, 2008 1:41:17 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2008/01/25
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsEventNegotiatePricePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsEventNegotiatePricePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsEventNegotiatePricePage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsEventNegotiatePricePage();
		}

		return _instance;
	}
	
	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("Negotiate Price$", false));
	}

	/**Click OK*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**Click cancel*/
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**Click Fill selected value*/
	public void clickFillSelectedValue() {
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.INPUT.button");
		p[1] = new Property(".id", "fillSelectedValuesButton");
		browser.clickGuiObject(p,true);
	}

	/**
	 * Input Filling Value
	 * @param value
	 */
	public void setFillingValue(String value) {
		browser.setTextField(".id", "fillingValue", value, true);
	}

	/**
	 * Input note
	 * @param note
	 */
	public void setNote(String note) {
		browser.setTextArea(".id", "negotiatedPriceViewNote", note, true);
	}

	/**Check SelectAll checkbox*/
	public void checkSelectAll() {
//		HtmlObject[] frames=getTransactionFrame();
//		browser.selectCheckBox(".name", "all_slct",0,frames[0]);
		browser.selectCheckBox(".name", "all_slct");
//		Browser.unregister(frames);
	}

	/**
	 * Do NegotiatePrice
	 * @param price
	 * @param note
	 */
	public void doNegotiatePrice(String price, String note) {
		this.negotiate(price, note);
		this.clickOK();
	}
	
	public void negotiate(String price, String note){
		System.out.print("");
		this.setFillingValue(price);
		this.checkSelectAll();
		this.clickFillSelectedValue();
		this.setNote(note);
	}
	
	public String getErrorMsgTwo(){
		IHtmlObject[] error=browser.getHtmlObject(".id", "error.event.rate2");
		String msg=error[0].getProperty(".text");
		return msg;
	}
	
	public String getErrorMsgThree(){
		IHtmlObject[] error=browser.getHtmlObject(".id", "error.event.rate3");
		String msg=error[0].getProperty(".text");
		return msg;
	}
	
	public String getErrorMsgFour(){
		IHtmlObject[] error=browser.getHtmlObject(".id", "error.event.rate4");
		String msg=error[0].getProperty(".text");
		return msg;
	}
	
	public String getErrorMsgFive(){
		IHtmlObject[] error=browser.getHtmlObject(".id", "error.event.note5");
		String msg=error[0].getProperty(".text");
		return msg;
	}

}
