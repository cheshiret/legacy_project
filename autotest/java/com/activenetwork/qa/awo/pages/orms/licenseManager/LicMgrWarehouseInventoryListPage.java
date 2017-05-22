/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;




import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  Aug 6, 2012
 */


public class LicMgrWarehouseInventoryListPage extends LicMgrCommonTopMenuPage {
	private static LicMgrWarehouseInventoryListPage _instance = null;
	
	public static LicMgrWarehouseInventoryListPage getInstance() {
		if (null == _instance) {
			_instance = new LicMgrWarehouseInventoryListPage();
		}

		return _instance;
	}

	protected LicMgrWarehouseInventoryListPage() {

	}
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "warehouseInventory_LIST");
		}
	
	
	public String[][] getWarehouseInventoryList()
	{
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".id", "warehouseInventory_LIST");
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		String[][] tableValues = tableGrid.getTableValues();
		Browser.unregister(objs);
		return tableValues;
	}
		
}
