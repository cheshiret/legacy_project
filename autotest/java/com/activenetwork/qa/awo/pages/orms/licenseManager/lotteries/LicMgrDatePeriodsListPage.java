/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodInfo;
import com.activenetwork.qa.testapi.ItemNotFoundException;
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
 * @author pzhu
 * @Date  Sep 17, 2012
 */

public class LicMgrDatePeriodsListPage extends LicMgrLotteriesCommonPage{
	private static LicMgrDatePeriodsListPage _instance = null;
	
	protected LicMgrDatePeriodsListPage (){}
	
	public static LicMgrDatePeriodsListPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrDatePeriodsListPage();
		}
		
		return _instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","date period");
	}
	
	public void clickCode(String code){
		browser.clickGuiObject(".class", "Html.A", ".text", code,true);
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(".id", new RegularExpression("DatePeriodSearchCriteria-\\d+\\.status",false), status);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	public void clickAddDatePeriod(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Date Period");
	}
	public void searchByStatus(String status)
	{
		this.selectStatus(status);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<DatePeriodInfo> getAllRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<DatePeriodInfo> records = new ArrayList<DatePeriodInfo>();
		int rows;
		int columns;
		DatePeriodInfo info;
		
		
		objs = browser.getTableTestObject(".id", "date period");
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find Date Period table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page LicMgrDatePeriodsListPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			info = new DatePeriodInfo();
			info.setCode(table.getCellValue(i, table.findColumn(0, "Code")));
			info.setDescription(table.getCellValue(i, table.findColumn(0, "Description")));
			info.setStatus(table.getCellValue(i, table.findColumn(0, "Status")));
			info.setPrivilegeLotteryProduct(table.getCellValue(i, table.findColumn(0, "Privilege Lottery Product")));
			info.setHunt(table.getCellValue(i, table.findColumn(0, "Hunt")));
			records.add(info);			
		}
		
		
		Browser.unregister(objs);
		return records;
	}
	
	public boolean isDatePeriodExists(String code) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", code);
	}
}
