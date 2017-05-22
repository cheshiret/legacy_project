/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.landowner;

import java.util.HashMap;
import java.util.Iterator;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @Date  Mar 24, 2014
 */
public class LicMgrEditLandownerCountyQtyPage extends LicMgrLandownerCountyQtyListCommonPage{

	private static LicMgrEditLandownerCountyQtyPage _instance;
	
	private LicMgrEditLandownerCountyQtyPage(){
		
	}
	
	public static LicMgrEditLandownerCountyQtyPage getInstance(){
		if(_instance==null){
			_instance = new LicMgrEditLandownerCountyQtyPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(saveBtn());
	}
	
	private Property[] saveBtn(){
		return Property.addToPropertyArray(a(), ".text", "Save");
	}
	
	private Property[] cancelBtn(){
		return Property.addToPropertyArray(a(), ".text", "Cancel");
	}
	
	private Property[] countyQtyListTable(){
		return Property.addToPropertyArray(table(), ".id", "countyQuantityListGrid_LIST");
	}
	
	private Property[] newQtyInput(){
		return Property.addToPropertyArray(input("text"), ".id", new RegularExpression("LandownerPrivilegeTypeLicenseYearQuantityView-\\d+\\.newQty",false));
	}
	
	public void clickSave(){
		browser.clickGuiObject(saveBtn());
	}
	
	public void clickCancel(){
		browser.clickGuiObject(cancelBtn());
	}
	
	protected IHtmlObject[] getCountyQtyListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(countyQtyListTable());
		if(objs==null||objs.length<1){
			throw new ItemNotFoundException("Not Found County Qty List Table.");
		}
		return objs;
	}
	
	public void setNewQuantity(String newQty,int index){
		browser.setTextField(newQtyInput(), newQty,index);
	}
	
	public void editCountyQty(String county,String qty){
		IHtmlTable grid = (IHtmlTable)getCountyQtyListTable()[0];
		int rowNum = grid.findRow(1, county);
		Browser.unregister(grid);
		setNewQuantity(qty, rowNum-1);
	}
	
	public void editCountQty(HashMap<String, String> map){
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()){
			String county = iter.next();
			this.editCountyQty(county, map.get(county));
		}
		clickSave();
		ajax.waitLoading();
	}
	
}
