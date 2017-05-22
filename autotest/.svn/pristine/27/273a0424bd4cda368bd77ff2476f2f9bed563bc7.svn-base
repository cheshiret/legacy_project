package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import java.util.ArrayList;
import java.util.List;

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
 * @author jwang
 * @Date  Feb 15, 2012
 */
public class LicMgrStoreDaliySalesActivityCommonPage extends LicMgrTopMenuPage {
	private static LicMgrStoreDaliySalesActivityCommonPage _instance = null;
	
	protected LicMgrStoreDaliySalesActivityCommonPage() {}
	
	public static LicMgrStoreDaliySalesActivityCommonPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreDaliySalesActivityCommonPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "salesActivityForm");		
	}
	
	public void clickShowActivitySummary() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activity Summary");
	}
	
	public void clickShowActivityByPaymentType(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Activity by Payment Type");
	}
	
	public void clickShowActivityByUser(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Activity by User");
	}
	
	public void clickShowActivityByRegister(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Activity by Register");
	}
	
	public void clickShowActivityTransaction(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Activity Transaction");
	}
	
	public void clickShowActivityByCustomer(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Activity by Customer");
	}
	
	public void clickReturnToAgentDetails(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Return to Agent Details");
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getTotalsValue(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "LocationDailyDSalesList");
		List<String> value = new ArrayList<String>();
		
		if(objs.length < 1){
			throw new ErrorOnPageException("Could not get table by id LocationDailyDSalesList.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowCount = table.rowCount();
		if(rowCount <= 1){
			throw new ErrorOnPageException("There is no records on this page");
		}
		
		// get column num for '# of Receipts'
		int col = table.findColumn(0, "# of Receipts");
		if(col < 0){
			throw new ErrorOnPageException("Could not get col num for '# of Receipts'");
		}
		
		int colNum = table.columnCount();
		for(int i=1;i<colNum;i++){
			if(table.getCellValue(i, col).equalsIgnoreCase("Totals")){
				value.addAll(table.getRowValues(i));
				break;
			}	
		}
		
		Browser.unregister(objs);
		
		return value;
	}
}
