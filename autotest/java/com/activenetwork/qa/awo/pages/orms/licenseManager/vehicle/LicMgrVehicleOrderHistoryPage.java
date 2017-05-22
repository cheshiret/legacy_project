/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

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
 * @Date May 30, 2012
 */
public class LicMgrVehicleOrderHistoryPage extends LicMgrOrderCommonPage {

	private static LicMgrVehicleOrderHistoryPage _instance = null;

	protected LicMgrVehicleOrderHistoryPage() {
	}

	public static LicMgrVehicleOrderHistoryPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrVehicleOrderHistoryPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",
				"privilegeOrderHisList");
	}

	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", false);
	}

	public List<OrderHistory> getHistoryInfos() {
		List<OrderHistory> history = new ArrayList<OrderHistory>();

		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"privilegeOrderHisList");
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
		return history;
	}

}
