/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.landowner;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @author ssong
 * @Date  Mar 26, 2014
 */
public class LicMgrLandownerCountyQtyChangeHistoryPage extends LicMgrLandownerCountyQtyListCommonPage{

private static LicMgrLandownerCountyQtyChangeHistoryPage _instance;
	
	private LicMgrLandownerCountyQtyChangeHistoryPage(){
		
	}
	
	public static LicMgrLandownerCountyQtyChangeHistoryPage getInstance(){
		if(_instance==null){
			_instance = new LicMgrLandownerCountyQtyChangeHistoryPage();
		}
		return _instance;
	}
	
	private Property[] changeHistoryTable(){
		return Property.addToPropertyArray(table(), ".id", "ConfigurableChangeList_LIST");
	}
	
	private Property[] returnBtn(){
		return Property.addToPropertyArray(a(), ".text", "Return");
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(changeHistoryTable());
	}
	
	protected IHtmlObject[] getChangeHistoryListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(changeHistoryTable());
		if(objs==null||objs.length<1){
			throw new ItemNotFoundException("Not Found Change History Table.");
		}
		return objs;
	}
	
	public List<List<String>> getChangeHistory(){
		IHtmlObject[] objs = this.getChangeHistoryListTable();
		IHtmlTable grid = (IHtmlTable)objs[0];
		
		List<List<String>> infos = new ArrayList<>();
		for(int i=1;i<grid.rowCount();i++){
			infos.add(grid.getRowValues(i));
		}
		Browser.unregister(objs);
		return infos;
	}
	
	public void clickReturn(){
		browser.clickGuiObject(returnBtn());
	}
}
