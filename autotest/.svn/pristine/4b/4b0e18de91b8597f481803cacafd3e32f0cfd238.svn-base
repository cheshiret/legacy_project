package com.activenetwork.qa.awo.pages.orms.financeManager.eft;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:Finding path: Fnm->EFT->invoicing jobs->invoicing job
 *                      detail->invoice detail page-> Store Adjustment records
 *                      page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date Aug 14, 2012
 */
public class FinMgrInvoiceStoreAdjustmentRecordsPage extends
		FinMgrInvoiceDetailPage {
	private static FinMgrInvoiceStoreAdjustmentRecordsPage _instance = null;

	private FinMgrInvoiceStoreAdjustmentRecordsPage() {
	}

	public static FinMgrInvoiceStoreAdjustmentRecordsPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrInvoiceStoreAdjustmentRecordsPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser
				.checkHtmlObjectExists(
						".id",
						new RegularExpression(
								"DailyEFTJobRecordsSearchCriteria-\\d+\\.remittanceNum",
								false));
	}

	public void setRemittanceNum(String remittanceNum) {
		browser.setTextField(
				".id",
				new RegularExpression(
						"DailyEFTJobRecordsSearchCriteria-\\d+\\.remittanceNum",
						false), remittanceNum);
	}

	public void setAccountCode(String accountCode) {
		browser.setTextField(".id", new RegularExpression(
				"DailyEFTJobRecordsSearchCriteria-\\d+\\.accountCode", false),
				accountCode);
	}

	public void setAccountDescription(String accountDescription) {
		browser.setTextField(".id", new RegularExpression(
				"DailyEFTJobRecordsSearchCriteria-\\d+\\.accountDescription",
				false), accountDescription);
	}

	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	public void searchByCriteria(String remittanceNum, String accountCode,
			String accountDscr) {
		this.setRemittanceNum(remittanceNum);
		this.setAccountCode(accountCode);
		this.setAccountDescription(accountDscr);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	public void searchDepositAdjustmentByRemittanceNum(String remittanceNum) {
		this.searchByCriteria(remittanceNum, "", "");
	}

	public void searchDepositAdjustmentByAccountCode(String accountCode) {
		this.searchByCriteria("", accountCode, "");
	}

	public void searchDepositAdjustmentByAccountDscr(String accountDscr) {
		this.searchByCriteria("", "", accountDscr);
	}
	
	public boolean verifyContentsInTheColumn(String colName, String value) {
		boolean result = true;

		IHtmlObject objs[] = browser.getTableTestObject(".id",
				"EFTInvoiceDetailsList_LIST");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Payment Allocation table object.");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		int col = table.findColumn(0, colName);
		List<String> values = table.getColumnValues(col);

		for (int i = 1; i < values.size(); i++) {
			String info = values.get(i);
			if (!info.equals(value)) {
				result &= false;
				logger.info(info + " is not equal " + value + " at row#" + i
						+ 1);
			}
		}

		Browser.unregister(objs);
		return result;
	}
	
	public List<String> getAccountCode() {
		IHtmlObject objs[] = browser.getTableTestObject(".id",
				"EFTInvoiceDetailsList_LIST");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Payment Allocation table object.");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		List<String> values = table.getColumnValues(4);
		List<String> number = new ArrayList<String>();

		for (int i = 1; i < values.size(); i++) {
			String info = values.get(i).trim();
			number.add(info);
		}

		Browser.unregister(objs);
		return number;
	}
	
	public List<String> getRemittanceNumber() {
		IHtmlObject objs[] = browser.getTableTestObject(".id",
				"EFTInvoiceDetailsList_LIST");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Payment Allocation table object.");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		List<String> values = table.getColumnValues(7);
		List<String> number = new ArrayList<String>();

		for (int i = 1; i < values.size(); i++) {
			String info = values.get(i).trim();
			number.add(info);
		}

		Browser.unregister(objs);
		return number;
	}
}
