package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;

public abstract class LicenseManagerPage extends OrmsPage {
	
	/**
	 * This method is used to verify some records in a table display sorted order
	 * @param propertyKey
	 * @param propertyValue
	 * @param colIndex
	 * @param values
	 * @param isAsc
	 * @return
	 */
	public boolean verifyTableRecordsDisplaySort(String tablePropertyKey, Object tablePropertyValue, int colIndex, List<String> values, boolean isAsc) {
		IHtmlObject objs[] = browser.getTableTestObject(tablePropertyKey, tablePropertyValue);
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find target table object.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		List<Integer> rowNumList = new ArrayList<Integer>();
		for(int i = 0; i < values.size(); i ++) {
			rowNumList.add(table.findRow(colIndex, values.get(i)));
		}
		Browser.unregister(objs);
		
		if(rowNumList.size() == 0) {
			throw new ItemNotFoundException("Can't find any matched record in this table.");
		}
		
		boolean result = true;
		for(int j = 0; j < rowNumList.size() - 1; j ++) {
			if(rowNumList.get(j) > rowNumList.get(j + 1)) {
				result &= isAsc ? false : true;
			} else {
				result &= isAsc ? true : false;
			}
			if(result==false){
				return result;
			}
		}
		return result;
	}
	
	/**
	 * Method uses to verify table records display sorted by ascending order in first column(id)
	 * @param propertyKey - table id
	 * @param propertyValue - table id value
	 * @param values - it must be a list with expected sorted order
	 * @return
	 */
	public boolean verifyTableRecordsDisplaySort(String tablePropertyKey, Object tablePropertyValue, List<String> values) {
		return verifyTableRecordsDisplaySort(tablePropertyKey, tablePropertyValue, 0, values, true);
	}
	
	/**
	 * This method is used to verify the search filter functionality
	 * @param tablePropertyKey
	 * @param tablePropertyValue
	 * @param searchBy 
	 * @param searchValue
	 */
	public void verifySearchResultMatchCriteria(String tablePropertyKey, Object tablePropertyValue, String searchBy, String searchValue) {
		IHtmlObject[] objs = browser.getTableTestObject(tablePropertyKey, tablePropertyValue);
		
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find target table object.");
		}
		IHtmlTable grid = (IHtmlTable)objs[0];
		
		int colNum = grid.findColumn(0, searchBy);
		List<String> values = grid.getColumnValues(colNum);
		Browser.unregister(objs);
		for(int i = 1; i < values.size(); i++) {
			if(searchBy.equalsIgnoreCase("Location Class")) {
				if(!values.get(i).contains(searchValue)) {
					throw new ErrorOnPageException(searchBy + " value not correct on Row "+i);
				}
			} else {
				if(!values.get(i).equalsIgnoreCase(searchValue)) {
					throw new ErrorOnPageException(searchBy + " value not correct on Row "+i + ", Expected value is: " + searchValue + ", but actual is: " + values.get(i));
				}
			}
		}
	}
}
