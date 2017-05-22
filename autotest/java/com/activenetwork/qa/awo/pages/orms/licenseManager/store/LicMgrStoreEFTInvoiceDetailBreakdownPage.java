/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author szhou
 * 
 */
public class LicMgrStoreEFTInvoiceDetailBreakdownPage extends
		LicMgrStoreEFTInvoiceTopMeunPage {
	private static LicMgrStoreEFTInvoiceDetailBreakdownPage _instance = null;

	protected LicMgrStoreEFTInvoiceDetailBreakdownPage() {
	}

	public static LicMgrStoreEFTInvoiceDetailBreakdownPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrStoreEFTInvoiceDetailBreakdownPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Show Breakdown by Payment Type");
	}

	public void clickShowByPaymentType() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Show Breakdown by Payment Type");
	}

	public void clickShowByAccount() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Show Breakdown by Account");
	}

	public void clickShowByUser() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Show Breakdown by User");
	}

	public void clickShowByRegister() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Show Breakdown by Register");
	}

	public void clickShowByProduct() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Show Breakdown by Product");
	}

	public List<String[]> getBreakdownByUserInfo() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^User# of ReceiptsSales.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];
		List<String[]> infos = new ArrayList<String[]>();
		for (int i = 0; i < table.rowCount(); i++) {
			List<String> rowValues = table.getRowValues(i);
			String[] info = new String[table.columnCount()];
			for (int j = 0; j < rowValues.size() - 1; j++) {
				info[j] = rowValues.get(j);
			}
			infos.add(info);
		}
		Browser.unregister(objs);

		return infos;
	}

}
