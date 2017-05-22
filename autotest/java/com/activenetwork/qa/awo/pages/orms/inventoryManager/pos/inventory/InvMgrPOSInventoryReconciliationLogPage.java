package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Jul 30, 2012
 */
public class InvMgrPOSInventoryReconciliationLogPage extends InvMgrPOSPhysicalInventoryReconciliationCommonPage {
	
	private static InvMgrPOSInventoryReconciliationLogPage _instance = null;
	
	private InvMgrPOSInventoryReconciliationLogPage() {}
	
	public static InvMgrPOSInventoryReconciliationLogPage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrPOSInventoryReconciliationLogPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "reconciliationLog");
	}
	
	public IHtmlObject[] getPOSInventoryReconciliationLogTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id","reconciliationLog");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find POS Inventory Reconciliation Log table object.");
		}
		
		return objs;
	}
	
	public List<List<String>> getPOSInventoryReconciliationLogs() {
		IHtmlObject objs[] = getPOSInventoryReconciliationLogTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		List<List<String>> allLogs = new ArrayList<List<String>>();
		List<String> log = new ArrayList<String>();
		for(int i = 1; i < table.rowCount(); i ++) {
			log = table.getRowValues(i);
			allLogs.add(log);
		}
		
		Browser.unregister(objs);
		return allLogs;
	}
	
	public List<String> getPOSInventoryReconciliationLog(String id) {
		IHtmlObject objs[] = getPOSInventoryReconciliationLogTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowIndex = table.findRow(0, id);
		List<String> log = table.getRowValues(rowIndex);
		Browser.unregister(objs);
		return log;
	}
	
	public boolean compareInventoryReconciliationLogInfo(List<String> expected) {
		boolean result = true;
		List<String> actual = getPOSInventoryReconciliationLog(expected.get(0));
		
		result &= MiscFunctions.compareResult("POS Inventory Reconciliation - ID", expected.get(0), actual.get(0));
		result &= MiscFunctions.compareResult("POS Inventory Reconciliation - Date&Time", expected.get(1), actual.get(1));
		result &= MiscFunctions.compareResult("POS Inventory Reconciliation - User", expected.get(2), actual.get(2));
		result &= MiscFunctions.compareResult("POS Inventory Reconciliation - Product Reconcile", expected.get(3), actual.get(3));
		result &= MiscFunctions.compareResult("POS Inventory Reconciliation - Reconciliation Exception", expected.get(4), actual.get(4));

		return result;
	}
}
