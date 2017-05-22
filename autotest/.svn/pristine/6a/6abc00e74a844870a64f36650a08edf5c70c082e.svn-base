/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.landowner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @author ssong
 * @Date  Mar 24, 2014
 */
public class LicMgrLandownerViewCountyQtyListPage extends LicMgrLandownerCountyQtyListCommonPage{

	private static LicMgrLandownerViewCountyQtyListPage _instance;
	
	private LicMgrLandownerViewCountyQtyListPage(){
		
	}
	
	public static LicMgrLandownerViewCountyQtyListPage getInstance(){
		if(_instance==null){
			_instance = new LicMgrLandownerViewCountyQtyListPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(editQuantity());
	}
	
	private Property[] editQuantity(){
		return Property.addToPropertyArray(a(), ".text", "Edit Quantity");
	}
	
	private Property[] countyQtyListTable(){
		return Property.addToPropertyArray(table(), ".id", "countyQuantityListGrid_LIST");
	}
	
	public void clickEditQuantity(){
		browser.clickGuiObject(editQuantity());
	}
	
	protected IHtmlObject[] getCountyQtyListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(countyQtyListTable());
		if(objs==null||objs.length<1){
			throw new ItemNotFoundException("Not Found County Qty List Table.");
		}
		return objs;
	}
	
	/**
	 * Check quantity for special county
	 * @param county
	 * @param qty
	 */
	public void checkQty(String county,String qty){
		if(!checkQtyCorrect(county, qty)){
			throw new ErrorOnPageException("County Quantity Not Correct for County-"+county);
		}
	}
	
	public boolean checkQtyCorrect(String county,String qty){
		IHtmlTable grid = (IHtmlTable)getCountyQtyListTable()[0];
		int rowNum = grid.findRow(1, county);
		String actualQty = grid.getCellValue(rowNum, 2);
		Browser.unregister(grid);
		
		return actualQty.equals(qty);
	}
	
	public void checkQty(HashMap<String, String> map){
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			String value = map.get(key);
			checkQty(key,value);
		}
	}
	
	/**
	 * Check All County have same quantity
	 * @param qty
	 */
	public void checkQty(String qty){
		IHtmlTable grid = (IHtmlTable)getCountyQtyListTable()[0];
		List<String> values = grid.getColumnValues(2);
		
		Browser.unregister(grid);
		
		values.remove(0);
		for(String tmp:values){
			if(!tmp.equals(qty)){
				throw new ErrorOnPageException("Quantity Not Correct",qty,tmp);
			}
		}
	}
}
