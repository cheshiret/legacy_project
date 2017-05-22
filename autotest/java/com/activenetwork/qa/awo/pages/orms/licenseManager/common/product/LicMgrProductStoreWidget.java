package com.activenetwork.qa.awo.pages.orms.licenseManager.common.product;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  May 16, 2011
 */
public class LicMgrProductStoreWidget extends DialogWidget {
	
	private static LicMgrProductStoreWidget _instance = null;
	
	protected LicMgrProductStoreWidget() {}
	
	public static LicMgrProductStoreWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrProductStoreWidget();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", "Agentsclose");
	}
	
	/**
	 * Get the all stores info within each location class in Store widget
	 * @return
	 */
	public List<StoreInfo> getAllStoresInfo() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Stores table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[1];
		
		List<StoreInfo> stores = new ArrayList<StoreInfo>();
		StoreInfo store = null;
		for(int i = 1; i < table.rowCount(); i ++) {
			if(table.rowCount() > 1) {
				store = new StoreInfo();
			}
			store.isAssignedProductThruLocationClass = table.getCellValue(i, 0).trim().equalsIgnoreCase("Yes") ? true:false;
			store.belongedVendorID = table.getCellValue(i, 1).trim();
			store.belongedVendorName = table.getCellValue(i, 2).trim();
			store.storeName = table.getCellValue(i, 3).trim();
			store.storeStatusReason = table.getCellValue(i, 4).trim();
			stores.add(store);
		}
		
		Browser.unregister(objs);
		return stores;
	}
	
	public boolean isStoreAssigned(String agent,String vendor){
		List<StoreInfo> list=this.getAllStoresInfo();
		for(StoreInfo store:list){
			if(store.isAssignedProductThruLocationClass&&store.belongedVendorName.equals(vendor)&&store.storeName.equals(agent)){
				return true;
			}
		}
		return false;
	}
}
