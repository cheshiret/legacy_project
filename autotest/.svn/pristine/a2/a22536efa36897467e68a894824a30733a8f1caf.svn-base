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
 * @Date  Mar 19, 2012
 */
public class LicMgrStoreActivityPaymentTypeSubPage extends
		LicMgrStoreDaliySalesActivityCommonPage {

	private static LicMgrStoreActivityPaymentTypeSubPage _instance = null;
	
	protected LicMgrStoreActivityPaymentTypeSubPage() {}
	
	public static LicMgrStoreActivityPaymentTypeSubPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreActivityPaymentTypeSubPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "LocationDailyDSalesList",
											".text",new RegularExpression("^Payment/Refund Group.*",false));		
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public List<String> getAllPmtTypes(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "LocationDailyDSalesList");
		List<String> value = new ArrayList<String>();
		
		if(objs.length < 1){
			throw new ErrorOnPageException("Could not get table by id LocationDailyDSalesList.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowCount = table.rowCount();
		if(rowCount <= 1){
//			throw new ErrorOnPageException("There is no payment type records on this page");
			return null;
		}
		
		int rowNum = table.rowCount();
		for(int i=1;i<rowNum;i++){
			value.add(table.getCellValue(i, 1));
//			System.out.println(i+":"+table.getCellValue(i, 1));
		}
		
		Browser.unregister(objs);
		
		return value;
	}
	
	public List<String> getRowValueByPmtType(String pmtType){
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
		
		// get row num by payment type
		int row = table.findRow(1, pmtType);
		if(row < 0){
//			throw new ErrorOnPageException("Could not get row num by payment type "+pmtType+".");
			return null;
		}
		
		int colNum = table.columnCount();
		for(int i=0;i<colNum;i++){
			value.add(table.getCellValue(row, i));
//			System.out.println(i+":"+table.getCellValue(row, i));
		}
		
		Browser.unregister(objs);
		
		return value;
	}
}
