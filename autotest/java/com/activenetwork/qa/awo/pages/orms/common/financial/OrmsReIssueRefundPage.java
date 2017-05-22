/*
 * $Id: FldMgrReIssueRefundPage.java 7017 2009-01-16 23:15:02Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OrmsReIssueRefundPage extends OrmsPage {

	/**
	 * Script Name   : FldMgrReIssueRefundPage
	 * Generated     : Jan 31, 2006 2:04:24 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2006/01/31
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsReIssueRefundPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsReIssueRefundPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsReIssueRefundPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsReIssueRefundPage();
		}

		return _instance;
	}
	/** 
	 * Determine if the page object exists 
	 */
	public boolean exists() {
		//use Re-Issue Refund tab as the pageMark

		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Re-Issue Refund");
	}

	/**
	 * Click Cancel Button
	 *
	 */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}

	/**
	 * Click Ok Button
	 *
	 */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/**
	 * Select payment type
	 * @param type payment type
	 */
	public void selectPaymentType(String type) {
		browser.selectDropdownList(".id", new RegularExpression(
				"issuerefundpaymenttype_\\d+_\\d+", false), type);
	}

	/**
	 * ReIssue refund By Personal Check
	 * @param checkNumber
	 * @param date
	 * @param holder
	 * @param approvement
	 * @param changeAllowed
	 * @param reason
	 * @throws PageNotFoundException
	 */
	public void reIssueByPerChq(String checkNumber, String date, String holder,
			String approvement, String changeAllowed, String reason)
			throws PageNotFoundException {
		this.selectPaymentType("PER CHQ");
		this.waitLoading();
		browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_0refund_\\d+", false), checkNumber);
		browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_1refund_\\d+", false), date);
		browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_2refund_\\d+", false), holder);
		browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_3refund_\\d+", false), approvement);
		browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_4refund_\\d+", false), changeAllowed);
		browser.setTextField(".id", "reissueReason", reason);
		this.clickOK();
	}

	/**
	 * ReIssue refund By Cash
	 * @param approvement
	 * @param changeAllowed
	 * @param reason
	 * @throws PageNotFoundException
	 */
	public void reIssueByCash(String approvement, String changeAllowed,
			String reason) throws PageNotFoundException {
	  	logger.info("ReIssue Refund by Cash!");
		this.selectPaymentType("CASH");
		this.waitLoading();
		browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_0refund_\\d+", false), approvement);
		browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_1refund_\\d+", false), changeAllowed);
		browser.setTextField(".id", "reissueReason", reason);
		this.clickOK();
	}
	
	/**
	 * This method used to input refund attributes for reIssue refund
	 * @param pay
	 * @param approvement
	 * @param changeAllowed
	 * @param reason
	 */
	public void setUpRefundAttributes(Payment pay,String approvement, String changeAllowed,String reason)
	{
	  	selectPaymentType(pay.payType);
		waitLoading();
		if (pay.payType.equalsIgnoreCase("Certified Check")
				|| pay.payType.equalsIgnoreCase("Money Order")|| pay.payType.equalsIgnoreCase("MON ORD")|| pay.payType.equalsIgnoreCase("CERT CHQ")) {
		  browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_0refund_\\d+", false), pay.checkNumber);
		  browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_1refund_\\d+", false), approvement);
		  browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_2refund_\\d+", false), changeAllowed);
		  browser.setTextField(".id", "reissueReason", reason);
		} else if (pay.payType.equalsIgnoreCase("Personal Check")||pay.payType.equalsIgnoreCase("PER CHQ")) {
		  browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_0refund_\\d+", false), pay.checkNumber);
		  browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_1refund_\\d+", false), pay.checkDate);
		  browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_2refund_\\d+", false), pay.checkName);
		  browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_3refund_\\d+", false), approvement);
		  browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_4refund_\\d+", false), changeAllowed);
		  browser.setTextField(".id", "reissueReason", reason);
		} else if (pay.payType.equalsIgnoreCase("Cash")) {
		  browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_0refund_\\d+", false), approvement);
		  browser.setTextField(".id", new RegularExpression(
				"row_\\d_input_1refund_\\d+", false), changeAllowed);
		  browser.setTextField(".id", "reissueReason", reason);
		}
		else if (pay.payType.equalsIgnoreCase("HIST CASH")|| pay.payType.equalsIgnoreCase("HIST PCHK")|| pay.payType.equalsIgnoreCase("HIST CR CD")) {
			  browser.setTextField(".id", "reissueReason", reason);
			}
	}
	/**
	 * Click Refunds Link
	 */
	public void clickRefundsTab()
	{
		browser.clickGuiObject(".class", "Html.A", ".text", "Refunds");
	}
}
