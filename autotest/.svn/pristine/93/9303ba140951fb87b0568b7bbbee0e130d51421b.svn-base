package com.activenetwork.qa.awo.pages.orms.licenseManager.common.product;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
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
public class LicMgrProductAssignedStoresWidget extends DialogWidget {
	
	private static LicMgrProductAssignedStoresWidget _instance = null;
	
	protected LicMgrProductAssignedStoresWidget() {}
	
	public static LicMgrProductAssignedStoresWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrProductAssignedStoresWidget();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", "Assigned Agentsclose");
	}
	
	private IHtmlObject[] getAssignedAgentsTables(){
		IHtmlObject[] objs=browser.getTableTestObject(".id", new RegularExpression("grid_\\d+",false), ".text", new RegularExpression("^Vendor ID.*",false));
		if(objs.length<1){
			throw new ObjectNotFoundException();
		}
		return objs;
	}
	
	public boolean checkAgentExisting(String agent,String vendor){
		List<StoreInfo> list=this.getAllStoresInfo();
		for(StoreInfo store:list){
			if(store.storeName.equals(agent)&&store.belongedVendorName.equals(vendor)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get the all Assigned stores info within each location class in Assigned Store widget
	 * @return
	 */
	public List<StoreInfo> getAllStoresInfo() {
		IHtmlObject objs[] = getAssignedAgentsTables();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		List<StoreInfo> stores = new ArrayList<StoreInfo>();
		StoreInfo store = null;
		for(int i = 1; i < table.rowCount(); i ++) {
			if(table.rowCount() > 1) {
				store = new StoreInfo();
			}
			store.isAssignedProductThruLocationClass = true;
			store.belongedVendorID = table.getCellValue(i, 0).trim();
			store.belongedVendorName = table.getCellValue(i, 1).trim();
			store.storeName = table.getCellValue(i, 2).trim();
			store.storeStatusReason = table.getCellValue(i, 3).trim();
			stores.add(store);
		}
		
		Browser.unregister(objs);
		return stores;
	}
}
