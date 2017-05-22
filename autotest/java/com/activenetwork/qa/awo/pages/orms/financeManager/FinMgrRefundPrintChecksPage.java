package com.activenetwork.qa.awo.pages.orms.financeManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * Script Name   : <b>FinMgrRefundPrintChecksPage</b>
 * Generated     : <b>Feb 29, 2012</b>
 * Description   : Design test script for Finance manager refund menu, Print Checks tab page
 *
 * @since  2012/02/28
 * @author FLiu
 */

public class FinMgrRefundPrintChecksPage extends FinanceManagerPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRefundPrintChecksPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRefundPrintChecksPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRefundPrintChecksPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrRefundPrintChecksPage();
		}

		return _instance;
	}

	/** 
	 * Determine if the page object exists 
	 */
	public boolean exists() {
		//use Print Flag dropdown list as the pageMark
		
		return browser.checkHtmlObjectExists(".id", "payrefprintflag");
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
	 * Select print flag status
	 * @param flagStatus
	 */
	public void selectPrintFlagStatus(String flagStatus) {
		browser.selectDropdownList(".id", "payrefprintflag", flagStatus);
	}

	/**
	 * Set minimum threshold amount
	 * @param minThreshAmount
	 */
	public void setMinThreshAmount(String minThreshAmount) {
		browser.setTextField(".id", "payrefminthresh", minThreshAmount);
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
	 */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false));
	}

	/**
	 * Select refund checkBox of specific refund ID
	 * @param refundID
	 */
	public void selectRefundCheckBox(String refundID) {
		browser.selectCheckBox(".id", new RegularExpression("row_\\d+_checkbox", false), ".value", refundID);
	}

	/**
	 * Click Flag selected checks as "Printing" button
	 *
	 */
	public void clickFlagchecksasPrinting() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Flag selected checks as \"Printing\"", false));
	}

	/**
	 * Click Mark Status as "Issued" button
	 *
	 */
	public void clickMarkStatusasIssued() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Mark Status as \"Issued\"", false));
	}

	/**
	 * Search and Issue Specific Refund
	 * @param refundID
	 * @param minThreshAmount
	 */
	public void setPrintFlagasPrinting( String refundID, String minThreshAmount) {
		ConfirmDialogPage confirmPage = ConfirmDialogPage.getInstance();
		
		//Search the not printed check refund by orderNum
		selectSearchType("Refund Id");
		setSearchValue(refundID);
		setMinThreshAmount(minThreshAmount);
		clickGo();
		this.waitLoading();
		//Select the refund and set the print flag as Printing
		selectRefundCheckBox(refundID);
		clickFlagchecksasPrinting();
		confirmPage.setDismissible(false);
		confirmPage.waitLoading();
		confirmPage.clickOK();
		this.waitLoading();
	}
	
	/**
	 * Search and Issue Specific Refund
	 * @param refundID
	 * @param minThreshAmount
	 */
	public void searchAndMarkIssuedRefund( String refundID, String FlagStatus) {
		ConfirmDialogPage confirmPage = ConfirmDialogPage.getInstance();
		
		//Search the printing check refund by orderNum
		selectSearchType("Refund Id");
		setSearchValue(refundID);
		selectPrintFlagStatus(FlagStatus);
		clickGo();
		this.waitLoading();
		//Select the refund and mark status as Issued
		selectRefundCheckBox(refundID);
		clickMarkStatusasIssued();
		confirmPage.setDismissible(false);
		confirmPage.waitLoading();
		confirmPage.clickOK();
		this.waitLoading();
	}
	
	/**
	 * Click Refunds Link
	 */
	public void clickRefundsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Refunds");
	}
	
	/**
	 * Search Refund By Order Number
	 * @param orderNum
	 * @param flagStatus
	 * @param minThreshAmount
	 */
	public void searchRefundByOrderNum(String orderNum,	String minThreshAmount) {
		
		//Search the check refund by orderNum
		selectSearchType("Order #");
		setSearchValue(orderNum);
		setMinThreshAmount(minThreshAmount);
		clickGo();
		waitLoading();		
	}
	
	/**
	 * Check there have refund display
	 * @return
	 */
	public boolean checkRefundExists() {
		RegularExpression rex = new RegularExpression(
				"javascript:invokeAction\\(.*\"OpMgrRefundDetails.do\",.*\"RefundDetails\",.*\"PayRefundWorker\".*", false);
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", rex);
	}
	
	/**
	 * Get all refund Ids in the search list
	 * @return  a vector store many refund Ids with the same order Ids
	 */
	
	public List<String> getRefundIDS() {
		List<String> refundIDS = new ArrayList<String>();
		RegularExpression rex = new RegularExpression("^Refund Id Order # Customer Amount.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];

		for (int i = 1; i < tableGrid.rowCount(); i++) {
			//notice: Refund Id is the second column but not the first one; 
			//notice: it may get some invalid values like "null" which is hidden in the UI so non-null judge is necessary.
			if(null != tableGrid.getCellValue(i, 1)){
				refundIDS.add(tableGrid.getCellValue(i, 1).toString());
			}
		}
		Browser.unregister(objs);
		return refundIDS;
	}
}
