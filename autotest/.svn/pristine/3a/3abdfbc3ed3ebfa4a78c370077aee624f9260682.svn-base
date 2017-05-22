/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OrderHistory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrOrderCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date March 27, 2013
 */
public class LicMgrConsumableOrderHistoryPage extends LicMgrOrderCommonPage {
	private static LicMgrConsumableOrderHistoryPage _instance = null;

	protected LicMgrConsumableOrderHistoryPage() {
	}

	public static LicMgrConsumableOrderHistoryPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrConsumableOrderHistoryPage();
		}

		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",
				"orderhistoryGrid");
	}

	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", false);
	}

	public List<OrderHistory> getHistoryInfos() {
		List<OrderHistory> history = new ArrayList<OrderHistory>();

		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"orderhistoryGrid");
		if (objs.length < 0) {
			throw new ErrorOnPageException(
					"Cann't find vehicle order history table...");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		for (int i = 0; i < table.rowCount() - 1; i++) {
			OrderHistory info = new OrderHistory();
			List<String> row = table.getRowValues(i);
			info.date = row.get(0);
			info.transaction = row.get(1);
			info.infoTransaction = row.get(2);
			info.transactionLocation = row.get(3);
			info.user = row.get(4);
			history.add(info);
		}
        Browser.unregister(objs);
        history.remove(0);
		return history;
	}

}
