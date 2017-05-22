/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * Script Name   : <b>OrmsTransferRefundConfirmActionPage</b>
 * Generated     : <b>March 01st, 2012</b>
 * Description   : Design test script for ORMS common financial page, transfer refund as payment confirm action page
 *
 * @since  2012/03/01
 * @author FLiu
 */
public class OrmsTransferRefundConfirmActionPage extends OrmsPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsTransferRefundConfirmActionPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsTransferRefundConfirmActionPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsTransferRefundConfirmActionPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsTransferRefundConfirmActionPage();
		}

		return _instance;
	}

	/** Determine if the reconcile deposit confirm action page exists */
	public boolean exists() {
		//use the required transfer notes test area as the pageMark
		return browser.checkHtmlObjectExists(".class","Html.TEXTAREA",".id","payrefdetailaddnotes");
	}
	
	/**
	 * Set the note for explain why to transfer refund as payment
	 * @param note
	 */
	public void setNote(String note){
		browser.setTextArea(".id", "payrefdetailaddnotes", note);
	}
	
	/**
	 * Click OK button
	 */
	public void clickOk(){
		browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	/**
	 * Click Cancel button
	 */
	public void clickCancel(){
		browser.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	/**
	 * Input Order ID (Order #)
	 * @param orderID
	 */
	public void setOrderNum(String orderID) {
		browser.setTextField(".id", "ordNo", orderID);
	}

	/**
	 * Select Order Type
	 * @param orderType
	 */
	public void selectSearchType(String orderType) {
		browser.selectDropdownList(".id", "ordTyp", orderType);
	}
	
	/**
	 * Input Balance >= amount
	 * @param balMinAmount
	 */
	public void setBalanceMinAmount(String balMinAmount) {
		browser.setTextField(".id", "ordBal1", balMinAmount);
	}
	
	/**
	 * Input Balance <= amount
	 * @param balMaxAmount
	 */
	public void setBalanceMaxAmount(String balMaxAmount) {
		browser.setTextField(".id", "ordBal2", balMaxAmount);
	}

	/**
	 * Click Go button
	 */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false));
	}
	
	/**
	 * Select radio button of specific Order ID
	 * @param orderID
	 */
	public void selectFirstOrderRadioButton() {
		browser.selectRadioButton(".id", "orderid");
		

	}

}
