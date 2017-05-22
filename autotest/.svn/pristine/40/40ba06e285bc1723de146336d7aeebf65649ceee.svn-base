package com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInventoryFileLogInfo;
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
public class OrmsPOSInventoryFileLogPage extends OrmsPOSCommonPage {
	
private static OrmsPOSInventoryFileLogPage _instance = null;
	
	private OrmsPOSInventoryFileLogPage() {}
	
	public static OrmsPOSInventoryFileLogPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsPOSInventoryFileLogPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "inventoryFileLog");
	}

	private IHtmlObject[] getPOSInventoryFileLogTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id",new RegularExpression("inventoryFileLog(_LIST)?", false));
		
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find POS Inventory File Log table object.");
		}
		
		return objs;
	}
	
	public List<List<String>> getPOSInventoryFileLogs() {
		IHtmlObject objs[] = getPOSInventoryFileLogTableObject();
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
	
	private int getRowIndexByValue(String columnName, String value) {
		IHtmlObject objs[] = getPOSInventoryFileLogTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int columnIndex = table.findColumn(0, columnName);
		int rowIndex = table.findRow(columnIndex, value);
		
		Browser.unregister(objs);
		return rowIndex;
	}
	
	/**
	 * get POS Inventory File log uniquely identified by File Name(because the imported inventory file can't be duplicated)
	 * @param fileName
	 * @return
	 */
	public POSInventoryFileLogInfo getPOSInventoryFileLogByFileName(String fileName) {
		IHtmlObject objs[] = getPOSInventoryFileLogTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowIndex = getRowIndexByValue("File Name", fileName);
		List<String> logList = table.getRowValues(rowIndex);
		
		POSInventoryFileLogInfo fileLog = new POSInventoryFileLogInfo();
		fileLog.setFileImportID(logList.get(0));
		fileLog.setDate(logList.get(1).split(" ")[0]);
		fileLog.setDate(logList.get(1).split(" ")[1]);
		fileLog.setFileName(fileName);
		fileLog.setImportUser(logList.get(3));
		fileLog.setNumberOfRecordsImports(Integer.parseInt(logList.get(4)));
		fileLog.setReconciliationID(logList.get(5));
		fileLog.setExceptions(Integer.parseInt(logList.get(6)));
		
		Browser.unregister(objs);
		return fileLog;
	}
	
	/**
	 * get POS Inventory File Log uniquely identified by Import ID
	 * @param id
	 * @return
	 */
	public POSInventoryFileLogInfo getPOSInventoryFileLogByImportID(String id) {
		IHtmlObject objs[] = getPOSInventoryFileLogTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int rowIndex = getRowIndexByValue("File Import ID", id);
		List<String> logList = table.getRowValues(rowIndex);
		
		POSInventoryFileLogInfo fileLog = new POSInventoryFileLogInfo();
		fileLog.setFileImportID(id);
		fileLog.setDate(logList.get(1).split(" ")[0]);
		fileLog.setTime(logList.get(1).split(" ")[1]);
		fileLog.setFileName(logList.get(2));
		fileLog.setImportUser(logList.get(3));
		fileLog.setNumberOfRecordsImports(Integer.parseInt(logList.get(4)));
		fileLog.setReconciliationID(logList.get(5));
		fileLog.setExceptions(Integer.parseInt(logList.get(6)));
		
		Browser.unregister(objs);
		return fileLog;
	}
	
	public boolean compareInventoryFileLogInfo(POSInventoryFileLogInfo expected) {
		POSInventoryFileLogInfo actual = getPOSInventoryFileLogByImportID(expected.getFileImportID());
		boolean result = true;
		result &= MiscFunctions.compareResult("File Import ID", expected.getFileImportID(), actual.getFileImportID());
		result &= MiscFunctions.compareResult("Date & Time", expected.getDate(), actual.getDate());
		result &= MiscFunctions.compareResult("File Name", expected.getFileName(), actual.getFileName());
		result &= MiscFunctions.compareResult("Import User", expected.getImportUser(), actual.getImportUser());
		result &= MiscFunctions.compareResult("Number of Records Imports", expected.getNumberOfRecordsImports(), actual.getNumberOfRecordsImports());
		result &= MiscFunctions.compareResult("Reconciliation ID", expected.getReconciliationID(), actual.getReconciliationID());
		result &= MiscFunctions.compareResult("Exceptions", expected.getExceptions(), actual.getExceptions());
		
		return result;
	}
}
