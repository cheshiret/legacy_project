package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: This is Voucher/Internal GC Records sub label page:
 *               FM/LM-->EFT-->Invoices---><click id>-->Voucher/Internal GC Records
 * @author pchen
 * @Date August 15, 2012
 */
public class FinMgrVoucherInternalGCRecordsTabInInvoicesDetailPage extends
		FinMgrInvoiceDetailPage {
	static private FinMgrVoucherInternalGCRecordsTabInInvoicesDetailPage _instance = null;

	protected FinMgrVoucherInternalGCRecordsTabInInvoicesDetailPage() {
	}

	static public FinMgrVoucherInternalGCRecordsTabInInvoicesDetailPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrVoucherInternalGCRecordsTabInInvoicesDetailPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id",
				"content_ViewEFTInvoicesDetailTabs", ".text",
				new RegularExpression("^Filter.*", false));
	}

	private String prefix = "DailyEFTJobRecordsSearchCriteria-\\d+\\.";

	/**
	 * Set up voucher id
	 * 
	 * @param voucherId
	 */
	public void setVoucherId(String voucherId) {
		browser.setTextField(".id", new RegularExpression(prefix + "voucherID",
				false), voucherId);
	}

	/**
	 * Set up gift card order number
	 * 
	 * @param gcOrderNumber
	 */
	public void setVoucherGCOrderNum(String gcOrderNumber) {
		browser.setTextField(".id", new RegularExpression(prefix
				+ "gcOrderNumber", false), gcOrderNumber);
	}

	/**
	 * Set up remittance number
	 * 
	 * @param remittanceNum
	 */
	public void setRemittanceNum(String remittanceNum) {
		browser.setTextField(".id", new RegularExpression(prefix
				+ "remittanceNum", false), remittanceNum);
	}

	/**
	 * Set up account code
	 * 
	 * @param accountCode
	 */
	public void setAccountCode(String accountCode) {
		browser.setTextField(".id", new RegularExpression(prefix
				+ "accountCode", false), accountCode);
	}

	/**
	 * Set up account description
	 * 
	 * @param accountDesc
	 */
	public void setAccountDesc(String accountDesc) {
		browser.setTextField(".id", new RegularExpression(prefix
				+ "accountDescription", false), accountDesc);
	}

	/**
	 * Click button go
	 */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	/**
	 * Search by voucher id
	 */
	public void searchByvoucherId(String voucherId){
		this.searchByCriteria(voucherId, "", "", "", "");
	}
	
	/**
	 * Search by gift card order number
	 */
	public void searchByGCOrderNum(String gcOrderNum){
		this.searchByCriteria("", gcOrderNum, "", "", "");
	}
	
	/**
	 * Search by remittance number
	 */
	public void searchByRemittanceNum(String remittanceNum){
		this.searchByCriteria("", "", remittanceNum, "", "");
	}
	
	/**
	 * Search by account code
	 */
	public void searchByAccountCode(String accountCode){
		this.searchByCriteria("", "", "", accountCode, "");
	}
	
	/**
	 * Search by account Desc
	 */
	public void searchByAccountDesc(String accountDesc){
		this.searchByCriteria("", "", "", "", accountDesc);
	}
	
	
	/**
	 * set all search criteria to search records
	 * 
	 */
	public void searchByCriteria(String voucherId, String gcOrderNum, String remittanceNum,
			String accountCode, String accountDesc) {

		if (!StringUtil.isEmpty(voucherId)) {
			this.setVoucherId(voucherId);
		}
		if (!StringUtil.isEmpty(gcOrderNum)) {
			this.setVoucherGCOrderNum(gcOrderNum);
		}
		if (!StringUtil.isEmpty(remittanceNum)) {
			this.setRemittanceNum(remittanceNum);
		}
		if (!StringUtil.isEmpty(accountCode)) {
			this.setAccountCode(accountCode);
		}
		if (!StringUtil.isEmpty(accountDesc)) {
			this.setAccountDesc(accountDesc);
		}
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	public void clearSearchCriteria() {
		this.setVoucherId(StringUtil.EMPTY);
		this.setVoucherGCOrderNum(StringUtil.EMPTY);
		this.setRemittanceNum(StringUtil.EMPTY);
		this.setAccountCode(StringUtil.EMPTY);
		this.setAccountDesc(StringUtil.EMPTY);
	}

	/**
	 * get total all records on the page.
	 * 
	 * @param
	 * @return List of records.
	 */
	public List<List<String>> getAllRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<List<String>> records = new ArrayList<List<String>>();
		int rows;
		int columns;

		do {
			objs = browser.getTableTestObject(".id",
					"EFTInvoiceDetailsList_LIST");

			if (objs.length < 1) {
				throw new ItemNotFoundException(
						"Can't find Voucher Internal GC records table object.");
			}

			table = (IHtmlTable) objs[0];
			rows = table.rowCount();
			columns = table.columnCount();
			logger.info("Find record on page FinMgrDailyEFTJobVoucherGCPage, "
					+ rows + " rows, " + columns + " columns.");

			for (int i = 1; i < rows; i++) {

				records.add(table.getRowValues(i));

			}

		} while (gotoNext());

		Browser.unregister(objs);
		return records;
	}

	/**
	 * If "Next" button is available, click it
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Next");
		boolean toReturn = false;

		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}

		Browser.unregister(objs);
		this.waitLoading();

		return toReturn;
	}
}
