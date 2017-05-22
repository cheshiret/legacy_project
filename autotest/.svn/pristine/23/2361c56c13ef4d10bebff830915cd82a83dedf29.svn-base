/*
 * Created on Dec 22, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.feeSchedule;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrFeeFindLocationPage extends FinanceManagerPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrFeeFindLocationPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrFeeFindLocationPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrFeeFindLocationPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrFeeFindLocationPage();
		}

		return _instance;
	}

	/**
	 * return whether the page mark display in the page
	 */
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("^\\s*Location\\s*$", false));
		return browser.checkHtmlObjectExists(".id", "showCategory");
	}
	
	/**
	 * Select Search by value from drop down list
	 *
	 */
	public void selectSearchBy(String searchType) {
	  	browser.selectDropdownList(".id", "location_search", searchType);
	}
	
	/**
	 * Input search value
	 * @param value
	 */
	public void setSearchValue(String value) {
	  	browser.setTextField(".id", "locationName", value);
	}
	
	/**
	 * Select Location category from drop down list
	 * @param category
	 */
	public void selectLocationCategory(String category) {
	  	browser.selectDropdownList(".id", "showCategory", category);
	}
	
	/**
	 * Click Go button
	 *
	 */
	public void clickGo() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Go|Search", false));//Shane: it turns to Search in 3.05 build
	}
	
	public boolean checkSelectButtonExists() {
	  	return browser.checkHtmlObjectExists(".class", "Html.A", ".text",new RegularExpression("Select",false));
	}
	
	/**
	 * Click Select Button
	 *
	 */
	public void clickSelect() {
	  	browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("Select",false));
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void selectLocation(String location){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression(" ?Location ID.*", false));
		
		IHtmlTable grid = (IHtmlTable)objs[0];
		int expectRow = -1;
		int empty_row_num = 0;
		for(int i=1;i<grid.rowCount();i++){
			String value = grid.getCellValue(i, 2);
			if(value==null){
				empty_row_num++;
				continue;
			}
			if(value.equalsIgnoreCase(location)){
				expectRow = i;
				break;
			}
			if(location.contains("%")){
				expectRow=i;
				break;
			}
		}
		if(expectRow==-1){
			throw new ErrorOnPageException("Not Found Expected Location: "+location);
		}
		Browser.unregister(objs);
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("Select",false),expectRow-1-empty_row_num);
	}
	
	/**
	 * Search location by location name.
	 * @param location
	 * @param locationCategory
	 */
	public void searchByLocationName(String location, String locationCategory) {
	  	selectSearchBy("Location Name");
	  	if(!StringUtil.isEmpty(location)) {
	  	  setSearchValue(location);
	  	  waitLoading();
		}
		if(!StringUtil.isEmpty(locationCategory)) {
		  selectLocationCategory(locationCategory);
		}
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
}
