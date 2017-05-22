/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrStoreDaliySalesActivityCommonPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jane Wang
 * @Date  Mar 26, 2012
 */
public class LicMgrStoreActivityUserSubPage extends
		LicMgrStoreDaliySalesActivityCommonPage {
	private static LicMgrStoreActivityUserSubPage _instance = null;
	
	protected LicMgrStoreActivityUserSubPage() {}
	
	public static LicMgrStoreActivityUserSubPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreActivityUserSubPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "LocationDailyDSalesList",
											".text",new RegularExpression("^User.*",false));		
	}
		
	/**
	 * Get records number for user sub page
	 * @return
	 */
	public int getRecordsCount(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "LocationDailyDSalesList");
		if(objs.length < 1){
			throw new ErrorOnPageException("Could not get table by id LocationDailyDSalesList.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int recordCount=table.rowCount()-1;
		Browser.unregister(objs);
		return recordCount;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getAllUser(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "LocationDailyDSalesList");
		List<String> value = new ArrayList<String>();
		
		if(objs.length < 1){
			throw new ErrorOnPageException("Could not get table by id LocationDailyDSalesList.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowCount = table.rowCount();
		if(rowCount <= 1){
			return null;
		}
		
		int rowNum = table.rowCount();
		for(int i=1;i<rowNum;i++){
			value.add(table.getCellValue(i, 0));
		}
		
		Browser.unregister(objs);
		
		return value;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<String> getRowValueByUser(String user){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "LocationDailyDSalesList");
		List<String> value = new ArrayList<String>();
		
		if(objs.length < 1){
			throw new ErrorOnPageException("Could not get table by id LocationDailyDSalesList.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowCount = table.rowCount();
		if(rowCount <= 1){
			throw new ErrorOnPageException("There is no payment type records on this page");
		}
		
		// get row num by user
		int row = table.findRow(0, user);
		if(row < 0){
			return null;
		}
		
		int colNum = table.columnCount();
		for(int i=0;i<colNum;i++){
			value.add(table.getCellValue(row, i));
		}
		
		Browser.unregister(objs);
		
		return value;
	}
}
