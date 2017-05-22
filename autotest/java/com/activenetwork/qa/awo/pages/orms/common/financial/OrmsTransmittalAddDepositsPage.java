package com.activenetwork.qa.awo.pages.orms.common.financial;

import java.util.List;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsTransmittalAddDepositsPage extends OrmsTransmittalMainPage {
	protected OrmsTransmittalAddDepositsPage() {
	}

	private static OrmsTransmittalAddDepositsPage _instance = null;

	public static OrmsTransmittalAddDepositsPage getInstance() {
		if (null == _instance)
			_instance = new OrmsTransmittalAddDepositsPage();
		return _instance;
	}
	
	/**
	 * using Search by drop down list as page mark.
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("grid_\\d+_LIST", false));
	}
	
	public void clickIncludeTransmittal(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Include in Transmittal", false));
	}
	
	private IHtmlTable getDepositTable(){
		IHtmlObject objs[] = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+_LIST", false));
		if(objs.length < 1){
			throw new ItemNotFoundException("Can't find the deposit info table.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		if(table.rowCount() <= 1){
			throw new ItemNotFoundException("Can't find the deposit info table.");
		} else {
			Browser.unregister(objs);
			return table;
		}
	}
	
	public List<String> getColumnByName(String columnName){
		IHtmlTable table = this.getDepositTable();
		int col = table.findColumn(0, columnName);
		if(col < 0){
			throw new ItemNotFoundException("Can't find column by given column name "+columnName);
		}
		List<String> colList = table.getColumnValues(col);
		colList.remove(0); // remove title
		return colList;
	}
	
	public List<String> getDepostIDList(){
		return this.getColumnByName("DEPOSIT id");
	}
	
	public void selectDeposits(String... depositIDs){
		for(String depostID : depositIDs){
			browser.selectCheckBox(".value", depostID);
		}
	}
	
}
