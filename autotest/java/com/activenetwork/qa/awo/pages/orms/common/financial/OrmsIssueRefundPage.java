package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * Description   : XDE Tester Script
 * @author CGuo
 */
public class OrmsIssueRefundPage extends OrmsPage {

	/**
	 * Script Name   : <b>FldMgrPaymentDetailPage</b>
	 * Generated     : <b>Sep 30, 2004 6:34:21 PM</b>
	 * Description   : XDE Tester Script
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/09/30
	 * @author CGuo
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsIssueRefundPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsIssueRefundPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsIssueRefundPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsIssueRefundPage();
		}

		return _instance;
	}

	/**
	 * Determine if the page object exists
	 */
	public boolean exists() {
		//use Go button as the pageMark
		//.href: javascript:invokeAction(%20"OpMgrIssueRefunds.do",%20"SearchIssuedRefunds",%20"PayRefundWorker",%20""%20%20)

		return browser.checkHtmlObjectExists(".text", new RegularExpression("Search", false),
						".href", new RegularExpression("javascript:invokeAction(.*\"OpMgrIssueRefunds.do\",.*\"SearchIssuedRefunds\",.*\"PayRefundWorker\",.*\"\".*)", false));
	}

	/**
	 * Select Search Type
	 * @param type
	 */
	public void selectSearchType(String type) {
		browser.selectDropdownList(".id", "payreftypeid", type);
	}

	/**
	 * Input search Value
	 * @param value
	 */
	public void setSearchValue(String value) {
		browser.setTextField(".id", "payreftypesearchvalue", value);
	}

	/**
	 * Select source payment method
	 * @param sourcePmt
	 */
	public void selectSourcePaymentMethods(String sourcePmt) {
		browser.selectDropdownList(".id", "sourcePaymentMethod", sourcePmt);
	}

	/**
	 * Select payment collect location
	 * @param location
	 */
	public void selectPaymentCollectLocation(String location) {
		browser.selectDropdownList(".id", "searchsourcepmtslschnl", location);
	}

	/**
	 * Click Go button
	 *
	 */
	public void clickGo() {
		IHtmlObject[] objs = getTransactionFrame();
		if(objs.length>0){
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false),false,0,objs[0]);
		}else{
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false));
		}
	}

	/**
	 * Select refund checkBox of specific refund ID
	 * @param refundID
	 */
	public void selectRefundCheckBox(String refundID) {
		browser.selectCheckBox(".id", new RegularExpression("row_\\d+_checkbox", false), ".value", refundID);
	}

	/**
	 * Select refund Type for specific refund Id
	 * @param refundID
	 * @param refundType
	 */
	public void selectRefundType(String refundID, String refundType) {
		browser.selectDropdownList(".id", new RegularExpression("issuerefundpaymenttype_" + refundID + "_\\d+", false),
				refundType);
	}

	/**
	 * Input approvement value for specific Refund ID
	 * @param refundID
	 * @param approveNotes
	 */
	public void setRefundApprovement(String refundID, String approveNotes) {
		browser.setTextField(".id", new RegularExpression("row_\\d+_input_0refund_" + refundID, false), approveNotes);
	}

	/**
	 * Input changeAllowed value for specific refund ID
	 * @param refundID
	 * @param changeAllowedNotes
	 */
	public void setChangeAllowed(String refundID, String changeAllowedNotes) {
		browser.setTextField(".id", new RegularExpression("row_\\d+_input_1refund_" + refundID, false),
				changeAllowedNotes);
	}

	/**
	 * Click Mark Issued button
	 *
	 */
	public void clickMarkIssued() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Mark Issued");
	}

	/**
	 * Issue one refund for specific refund Id with given parameters
	 * @param refundID
	 * @param refundType
	 * @param approvement
	 * @param changeAllowed
	 */
	public void issueRefund(String refundID, String refundType,
			String approvement, String changeAllowed) {
		this.selectRefundCheckBox(refundID);
		this.selectRefundType(refundID, refundType);
		this.setRefundApprovement(refundID, approvement);
		this.setChangeAllowed(refundID, changeAllowed);
		this.clickMarkIssued();
	}

	/**
	 * Input refund attributes value for specific refund with given parameters
	 * @param refundID
	 * @param pay
	 * @param approvement
	 * @param changeAllowed
	 */
	public void setupRefundAttributes(String refundID, Payment pay,
			String approvement, String changeAllowed) {
	  	selectRefundType(refundID, pay.payType);
		waitLoading();
		if (pay.payType.equalsIgnoreCase("Certified Check")
				|| pay.payType.equalsIgnoreCase("Money Order")|| pay.payType.equalsIgnoreCase("MON ORD")|| pay.payType.equalsIgnoreCase("CERT CHQ")) {
			browser.setTextField(".id", new RegularExpression(
					"row_\\d+_input_0refund_" + refundID, false),
					pay.checkNumber);
			browser.setTextField(".id", new RegularExpression(
					"row_\\d+_input_1refund_" + refundID, false), approvement);
			browser
					.setTextField(".id", new RegularExpression(
							"row_\\d+_input_2refund_" + refundID, false),
							changeAllowed);
		} else if (pay.payType.equalsIgnoreCase("Personal Check")||pay.payType.equalsIgnoreCase("PER CHQ")) {
			browser.setTextField(".id", new RegularExpression(
					"row_\\d+_input_0refund_" + refundID, false),
					pay.checkNumber);
			browser
					.setTextField(".id", new RegularExpression(
							"row_\\d+_input_1refund_" + refundID, false),
							pay.checkDate);
			browser
					.setTextField(".id", new RegularExpression(
							"row_\\d+_input_2refund_" + refundID, false),
							pay.checkName);
			browser.setTextField(".id", new RegularExpression(
					"row_\\d+_input_3refund_" + refundID, false), approvement);
			browser
					.setTextField(".id", new RegularExpression(
							"row_\\d+_input_4refund_" + refundID, false),
							changeAllowed);
		} else if (pay.payType.equalsIgnoreCase("Cash")) {
			browser.setTextField(".id", new RegularExpression(
					"row_\\d+_input_0refund_" + refundID, false), approvement);
			browser
					.setTextField(".id", new RegularExpression(
							"row_\\d+_input_1refund_" + refundID, false),
							changeAllowed);
		} else if(pay.payType.equalsIgnoreCase("Gift Card")){
		    browser.setTextField(".id",new RegularExpression("row_\\d+_input_0refund_" + refundID, false),pay.giftCardNum);
		}
	}

	/**
	 * Search and Issue Specific Refund
	 * @param refundID
	 * @param pay
	 * @param approvement
	 * @param changeAllowed
	 */
	public void searchAndIssueRefund(String refundID, Payment pay,
			String approvement, String changeAllowed) {
		selectSearchType("Refund ID");
		setSearchValue(refundID);
		clickGo();
		waitLoading();
		selectRefundCheckBox(refundID);
		setupRefundAttributes(refundID, pay, approvement, changeAllowed);
		clickMarkIssued();
		waitLoading();
	}

	/**
	 * Click Refunds Link
	 *
	 */
	public void clickRefundsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Refunds");
	}
}
