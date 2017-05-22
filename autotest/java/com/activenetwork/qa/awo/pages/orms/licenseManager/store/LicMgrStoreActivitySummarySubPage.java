package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

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
 * @author jwang
 * @Date  Feb 15, 2012
 */
public class LicMgrStoreActivitySummarySubPage extends
		LicMgrStoreDaliySalesActivityCommonPage {
	
	private final String[] colName = new String[]{"# of Receipts",
			"Sales (+)",
			"Voids Pending Doc Return (+)",
			"Vendor Fees (-)",
			"Net Amount (=)"};
	
	private static LicMgrStoreActivitySummarySubPage _instance = null;
	
	protected LicMgrStoreActivitySummarySubPage() {}
	
	public static LicMgrStoreActivitySummarySubPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrStoreActivitySummarySubPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "LocationDailyDSalesList",
											".text",new RegularExpression("^# of Receipts.*",false));		
	}
		
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	/**
	 * Get column value by column name
	 * @param columnName
	 * @return
	 */
	public String getValueByColumnName(int index){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "LocationDailyDSalesList");
		String value = "";
		
		if(objs.length < 1){
			throw new ErrorOnPageException("Could not get table by id LocationDailyDSalesList.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int col = table.findColumn(0, colName[index]);
		if(col < 0){
			throw new ErrorOnPageException("Could not get column num by column name "+colName[index]+".");
		}
		
		if(table.rowCount()>1){
			value = table.getCellValue(1, col);
		}
		
		if("" != value){
			value =  value.replaceAll("\\$", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(",", "");
		}
		
		Browser.unregister(objs);
		return value;
	}
	
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
}
