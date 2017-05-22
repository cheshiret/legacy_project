/*
 * Created on Jan 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.partnerInvoice;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRevenueLocationSummaryPage extends FinanceManagerPage {

  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRevenueLocationSummaryPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRevenueLocationSummaryPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRevenueLocationSummaryPage getInstance(){
		if (null == _instance) {
			_instance = new FinMgrRevenueLocationSummaryPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
	  return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("Revenue Location Total.*",false));
	}
	
	public void selectSearchBy(String searchBy) {
	  	browser.selectDropdownList(".id", "searchBy", searchBy);
	}
	
	public void setSearchValue(String searchValue) {
	  	browser.setTextField(".id", "loc-criteria", searchValue);
	}
	
	public void clickGo() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false));
	}
	
	public void searchByLocation(String loc) {
	  	selectSearchBy("Revenue Location");
	  	setSearchValue(loc);
	  	clickGo();
	  	waitLoading();
	}
	
	public void clickLocation(String loc) {
		if(browser.checkHtmlObjectExists(".class", "Html.A", ".text", loc)){
		  	browser.clickGuiObject(".class", "Html.A", ".text", loc, true);
		} else {
			throw new ItemNotFoundException("Can't find Location link...");
		}
	}
}
