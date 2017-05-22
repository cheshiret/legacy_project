/*
 * $Id: FldMgrVoidResevPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $
 */

package com.activenetwork.qa.awo.pages.orms.common.camping;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * Description   : OrmsVoidReservationPage
 * @author Jdu
 */
public class OrmsVoidReservationPage extends OrmsPage {

	/**
	 * Script Name   : <b>FldMgrVoidResevPage</b>
	 * Generated     : <b>May 22, 2009 3:41:44 PM</b> 
	 * Description   : XDE Tester Script
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2009/5/22
	 * @author Jdu
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsVoidReservationPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsVoidReservationPage() {
		browser = Browser.getInstance();
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsVoidReservationPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsVoidReservationPage();
		}
		return _instance;
	}

	/** Determine if the page object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Complete Void");
	}
	
	/**
	 * Check void reason drop down list exist
	 */
	public boolean checkVoidReasonExist(){
		return browser.checkHtmlObjectExists(".id", "reasonID");
	}
	
	/**
	 * Select void reason from drop down list
	 * @param voidReason
	 */
	public void selectVoidReason(String voidReason){
		browser.selectDropdownList(".id", "reasonID", voidReason);
	}

	/** Click the button to complete voiding of reservation */
	public void clickCompleteVoid() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Complete Void", true);
	}

	/** Set reservation voiding notes */
	public void setNotes(String notes) {
		browser.setTextArea(".id", new RegularExpression("userNotes|notes|MarinaOrderView-\\d+\\.profileView\\.actionReasonDesc", false), notes);
	}

	/**Click Don't void button*/
	public void clickDontVoid() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Don't Void");
	}
	
	/**Check the title of the Void Reservation page*/
	public void checkVoidPageTitle(){
		IHtmlObject [] objs = browser.getTableTestObject(".text", new RegularExpression("^Void Reservation Reservation.*",false));
		IHtmlTable table = (IHtmlTable) objs[0];
		if(!table.getCellValue(0, 0).equalsIgnoreCase("Void Reservation")){
			throw new ErrorOnPageException("The page title is not --- Void Reservation");
		}
		
		Browser.unregister(objs);
	}
	
	/**Check the "Complete Void" button in the Void Reservation page*/
	public void checkCompleteButtonExist(){
		if(!browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Complete Void")){
			throw new ErrorOnPageException("Complete Void button cannot be found in Void Reservation page.");
		}
	}
	
	/**Check the "Don't Void" button in the Void Reservation page*/
	public void checkDontVoidButtonExist(){
		if(!browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Don't Void")){
			throw new ErrorOnPageException("Don't Void button cannot be found in Void Reservation page.");
		}
	}
	
	/**Get the warning message*/
	public String getWarningMsg(){
		IHtmlObject [] objs = browser.getHtmlObject(".id", "NOTSET");
		String warningMsg = objs[0].getProperty(".text");
		
		Browser.unregister(objs);
		return warningMsg;
	}
	
	/**Check the "Complete Void" button is disable if we don't type the reason */
	public boolean checkCompleteVoidButtonDisabled(){
		IHtmlObject[] objs= browser.getHtmlObject(".class","Html.A",".text","Complete Void");
		boolean str = Boolean.parseBoolean((objs[0].getProperty(".disabled")));
		Browser.unregister(objs);
		return str;
	}
	
	public String getState(){
		return browser.getObjectText(".class", "Html.DIV", ".text", new RegularExpression("^State", false)).replaceAll("State", StringUtil.EMPTY);
	}
	
	private String getAttributeByName(String name) {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^" + name, false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute by Name - " + name);
		}
		
		String text = objs[0].text().replaceAll(name, "").trim();
		Browser.unregister(objs);
		
		return text;
	}
	
	public void clickResNumber(String ordNum){
		browser.clickGuiObject(".class", "Html.A", ".text",ordNum);
	}
	
	public void clickSlipInfoButton(String slipCode, String SlipName){
		browser.clickGuiObject(".class", "Html.A", ".text",slipCode + " - " + SlipName);
	}
	
	public void clickCustInfoButton(String lname, String fname){
		browser.clickGuiObject(".class", "Html.A", ".text",lname + "," + fname);
	}
	
	public void clickInvoiceNum(String invoiceNum){
		browser.clickGuiObject(".class", "Html.A", ".text",invoiceNum);
	}
	
	public String getSlipResNumber(){
		return this.getAttributeByName("Slip Reservation #");
	}
	
	public String getArrivalDate(){
		return this.getAttributeByName("Arrival Date");
	}
	
	public String getDepartureDate(){
		return this.getAttributeByName("Departure Date");
	}
	
	public String getNights(){
		return this.getAttributeByName("Nights");
	}
	
	public String getStatus(){
		return this.getAttributeByName("Status");
	}
	
	public String getOrderStatus(){
		return this.getAttributeByName("Order Status");
	}
	
	public String getMarina(){
		return this.getAttributeByName("Marina");
	}
	
	public String getDockArea(){
		return this.getAttributeByName("Dock/Area");
	}
	
	public String getSlipInfo(){
		return this.getAttributeByName("Slip#");
	}
	
	public String getInvoiceNum(){
		return this.getAttributeByName("Invoice#");
	}
	
	public String getInvoiceCreateDate(){
		return this.getAttributeByName("Date");
	}
	
	public String getInvoiceCreateBy(){
		return this.getAttributeByName("Create By").replaceAll(", ", ",");
	}
	
	public String getPaid(){
		return this.getAttributeByName("Paid");
	}
	
	public String getConfirmationStatus(){
		return this.getAttributeByName("Confirmation Status");
	}
	
	public String getBalance(){
		return this.getAttributeByName("Balance");
	}
	
	public String getCustName(){
		return this.getAttributeByName("Name");
	}
	
	public String getCustPhone(){
		return this.getAttributeByName("Phone");
	}
	
	public String getCustEmailAddr(){
		return this.getAttributeByName("Email Address");
	}
}
