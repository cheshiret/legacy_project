package com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.component.PagingComponent;
import com.activenetwork.qa.awo.pages.orms.common.pos.OrmsPOSCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
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
 * @Date  Oct 8, 2012
 */
public class OrmsPOSInventoryReconciliationLogPage extends OrmsPOSCommonPage {
	
	private static OrmsPOSInventoryReconciliationLogPage _instance = null;
	
	private OrmsPOSInventoryReconciliationLogPage() {}
	
	public static OrmsPOSInventoryReconciliationLogPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsPOSInventoryReconciliationLogPage();
		}
		
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "reconciliationLog");
	}

	public IHtmlObject[] getPOSInventoryReconciliationLogTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id",new RegularExpression("reconciliationLog(_LIST)?", false));
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
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		int rowIndex = 0;
		List<String> log = new ArrayList<String>();
		PagingComponent turnPage = new PagingComponent();
		do {
			objs = getPOSInventoryReconciliationLogTableObject();
			table = (IHtmlTable)objs[0];
			
			rowIndex = table.findRow(0, id);
			if(rowIndex >= 0) {
				log = table.getRowValues(rowIndex);
			}
		} while(turnPage.clickNext() && rowIndex < 1);
		
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
