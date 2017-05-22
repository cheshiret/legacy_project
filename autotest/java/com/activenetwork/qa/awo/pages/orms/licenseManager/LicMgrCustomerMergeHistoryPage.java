package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

/**
 * 
 * @author ssong
 * @date Dec 5, 2011
 */
public class LicMgrCustomerMergeHistoryPage extends LicMgrCustomerDetailsPage{
	public static final String MERGE_ID = "Merge ID";
	public static final String MERGE_STATUS = "Merge Status";
	public static final String MERGE_FROM_CUST = "Merge From Customer";
	public static final String MERGE_TO_CUST = "Merge To Customer";
	public static final String MERGE_DETAIL = "Merge Details";
	
	private static LicMgrCustomerMergeHistoryPage _instance = null;
	
	protected LicMgrCustomerMergeHistoryPage(){
		
	}
	
	public static LicMgrCustomerMergeHistoryPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrCustomerMergeHistoryPage();
		}
		return _instance;
	}
	
	public boolean exists(){//check customer order table exists
		return browser.checkHtmlObjectExists(".id","profileMergeList");
	}
	
	 public List<Map<String,String>> getAllRecordOnPage(){
			IHtmlObject objs[] = null;
			IHtmlTable table = null;
			List<Map<String,String>> records = new ArrayList<Map<String,String>>();
			int rows;
			int columns;
			Map<String,String> info;
			
			//RegularExpression rex = new RegularExpression("^Discount Schedule# Active.*",false);
			objs = browser.getHtmlObject(".class", "Html.TABLE", ".id", "profileMergeList");
			
			if(objs.length < 1) {
						throw new ItemNotFoundException("Can't find discount schedule table object.");
					}
			
			table = (IHtmlTable)objs[0];
			rows = table.rowCount();
			columns = table.columnCount();
			logger.info("Find record on page, "+rows+" rows, "+columns+" columns.");
			
			for(int i = 1; i < rows; i ++) {
				info = new HashMap<String,String>();
				info.put(MERGE_ID, table.getCellValue(i, table.findColumn(0, MERGE_ID)));
				info.put(MERGE_STATUS, table.getCellValue(i, table.findColumn(0, MERGE_STATUS)));
				info.put(MERGE_FROM_CUST, table.getCellValue(i, table.findColumn(0, MERGE_FROM_CUST)));
				info.put(MERGE_TO_CUST, table.getCellValue(i, table.findColumn(0, MERGE_TO_CUST)));
				info.put(MERGE_DETAIL, table.getCellValue(i, table.findColumn(0, MERGE_DETAIL)));				
				records.add(info);			
			}
			
			
			Browser.unregister(objs);
			return records;
		}
	 
	}
