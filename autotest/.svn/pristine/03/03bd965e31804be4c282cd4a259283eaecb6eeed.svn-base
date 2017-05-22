/*
 * $Id: FinMgrTaxAssignLocSearchPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.financeManager.tax;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author CGuo
 */
public class FinMgrTaxAssignLocSearchPage extends FinanceManagerPage {

	/**
	 * Script Name   : FinMgrTaxAssignLocSearchPage
	 * Generated     : Dec 22, 2004 4:46:21 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/12/22
	 */
  
  	/**
	 * A handle to the unique Singleton instance.
	 */
	private static FinMgrTaxAssignLocSearchPage _instance = null;

	/**
	 * @return The unique instance of this class.
	 */
	public static FinMgrTaxAssignLocSearchPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrTaxAssignLocSearchPage();
		}

		return _instance;
	}

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrTaxAssignLocSearchPage() {
	}

	/**
	 * Check this page is exists
	 */
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", "Location");
		return browser.checkHtmlObjectExists(".id", "locationList_LIST");//Quentin[20131014]
	}

	/**
	 * This method is used to wait ajax refresh
	 *
	 */
	public void waitAjaxRefresh() {
	  	ajax.waitLoading();
	}
	
	/**
	 * Click Cancel Button
	 *
	 */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	/**
	 * Click Go Button
	 *
	 */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false));
	}

	/**
	 * Select Category from drop down list
	 * @param category
	 */
	public void selectCategory(String category) {
		browser.selectDropdownList(".id", "LocationSearchCriteria.locationCategoryId", category);
		ajax.waitLoading();
	}

	/**
	 * Select Search Type from drop down list
	 * @param searchType
	 */
	public void selectSearchType(String searchType) {
	  browser.selectDropdownList(".id", "LocationSearchCriteria.searchBy", searchType);
	}
	/**
	 * Input search by value
	 * @param value
	 */
	public void setSearchByValue(String value) {
		browser.setTextField(".id", "LocationSearchCriteria.searchByValue", value);
	}

	public void searchLocation(String locationCategory, String location){
		if(!location.equals("")) {
			setSearchByValue(location);
		}
		if(!locationCategory.equals("")) {
			selectCategory(locationCategory);
		}
		clickGo();	
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void selectLocation(String locationCategory, String location) throws PageNotFoundException {	
		searchLocation(locationCategory, location);
		selectLocation(location);
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
		}
		if(expectRow==-1){
			throw new ErrorOnPageException("Not Found Expected Location "+location);
		}
		Browser.unregister(objs);
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("Select",false),expectRow-1-empty_row_num);
	}
	
	/**
	 * Click Select Button
	 *
	 */
	public void clickSelectButton() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "select");
	  	ajax.waitLoading();
	}
	
	
	 /**
	   * Check whether there have next page,if have,click Next Button
	   * @return
	   */
	  public boolean gotoNext() {
	    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.A", ".text", "Next");
	    boolean toReturn = false;
	    if ( objs.length > 0 ) {
	      toReturn = true;
	      
	      ILink link = (ILink)objs[0];
	      link.click();
	    }
	    Browser.unregister( objs );

	    this.waitLoading();

	    return toReturn;
	  }
}
