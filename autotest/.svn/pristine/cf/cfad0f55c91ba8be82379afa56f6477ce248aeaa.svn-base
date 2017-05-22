/*
 * $Id: CallMgrCancelReservPage.java 5781 2008-06-05 16:57:40Z I2K-NET\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.camping;

import java.util.List;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OrmsCancelReservationPage extends OrmsPage {

	/**
	 * Script Name : CallMgrCancelReservPage Generated : Oct 28, 2004 10:23:42
	 * AM Original Host : WinNT Version 5.1 Build 2600 (Service Pack 2)
	 * 
	 * @since 2004/10/28
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsCancelReservationPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsCancelReservationPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsCancelReservationPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsCancelReservationPage();
		}

		return _instance;
	}

	/** Determine if the page object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Complete Cancellation");
	}

	/** Click on the "Complete Cancellation" button */
	public void clickCompleteCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Complete Cancellation");
	}

	/** Set cancellation notes */
	public void setNotes(String notes) {
		browser.setTextArea(".id", new RegularExpression("notes|MarinaOrderView-\\d+\\.profileView.actionReasonDesc", false), notes);
	}

	/** Choose cancellation reason by passing in reason name */
	public void selectReason(String reason) {
		browser.selectDropdownList(".id", new RegularExpression("reasonID|MarinaOrderView-\\d+\\.profileView.actionReason", false), reason);
	}

	/** Choose cancellation reason by menu index */
	public void selectReason(int index) {
		browser.selectDropdownList(".id", new RegularExpression("reasonID|MarinaOrderView-\\d+\\.profileView.actionReason", false), index);
	}

	/** Click Don't cancel button */
	public void clickDontCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Don't Cancel");
	}

	/** Check All options in the drop down list */
	public void checkAllCancellationReason() {
		IHtmlObject objs[] = browser.getDropdownList(".id", "reasonID");

		List<String> options = ((ISelect) objs[0]).getAllOptions();
		String optionsKnown[] = { "Bad Weather", "Reschedule", "Cancellation",
				"Void" };

		for (int i = 0; i < optionsKnown.length; i++) {
			int counter = 1;
			while (!optionsKnown[i].equalsIgnoreCase(options.get(counter))) {
				counter++;
			}
			if (counter > options.size()) {
				throw new TestCaseFailedException("the option "
						+ optionsKnown[i] + "cannot be found in dropdown list.");
			}
		}
		Browser.unregister(objs);
	}
	
	/**check the "Emergency Cancellation" option is in the drop down list when we 
	 * cancel a reservation and the contract is using the Voucher*/
	public void checkCancellationReasonInVoucher(){
		IHtmlObject [] objs = browser.getDropdownList(".id", "reasonID");
		String cancellationReason = "Emergency Cancellation";
		int counter = 0;
		
		List<String> options = ((ISelect) objs[0]).getAllOptions();
		while(!options.get(counter).equalsIgnoreCase(cancellationReason)){
			counter++;
			if(counter>options.size()){
				throw new TestCaseFailedException("The Emergency Cancellation cannot be found in drop-down list.");
			}
		}
		Browser.unregister(objs);
	}
	
	public String getState(){
		return browser.getObjectText(".class", "Html.DIV", ".text", new RegularExpression("^State", false)).replaceAll("State", StringUtil.EMPTY);
	}
	
	public String getMessage(){
		return browser.getObjectText(".className", "message", ".id", "NOTSET");
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
