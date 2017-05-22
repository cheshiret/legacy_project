/*
 * Created on Sep 8, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsDepositPage extends OrmsPage {

  /**
   * Protected constructor
   *
   */
	protected OrmsDepositPage() {
	}

	private static OrmsDepositPage _instance = null;

	/**
	 * Singleton pattern,get Instance of current Class
	 * @return Instance of current class
	 */
	public static OrmsDepositPage getInstance() {
		if (null == _instance)
			_instance = new OrmsDepositPage();
		return _instance;
	}

	/**
	 * Use Deposits Link as page mark,check it exists or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression(
						"Deposit ID.*", false));
	}

	/**
	 * Click Go Button
	 *
	 */
	public void clickGo() {
		browser.clickGuiObject(".text", "Search", ".class", "Html.A");
	}

	/**
	 * Select search Type
	 * @param condition searchType
	 */
	public void selectSearchBy(String condition) {
		browser.selectDropdownList(".class", "Html.SELECT", ".id", "searchBy",
				condition);
	}

	/**
	 * Input search Value
	 * @param criteria
	 */
	public void setSearchByValue(String criteria) {
		browser.setTextField(".id", "criteria", criteria);
	}

	/**
	 * Search Deposit By Deposit Id
	 * @param depositId
	 */
	public void searchByDepositID(String depositId)
	{
	  selectSearchBy("Deposit ID");
	  setSearchByValue(depositId);
	  clickGo();
	  waitLoading();
	}
	
	public void clickDepositId(String depositId){
		browser.clickGuiObject(".class","Html.A",".text",depositId);
	}
	/**
	 * Click Add new deposit Button
	 *
	 */
	public void clickNewDeposit() {
		browser.clickGuiObject(".class", "Html.A", ".text", "New Deposit");
	}

	/**
	 * Check specific deposit is given status or not
	 * @param depositID
	 * @return Specific deposit is given status or not
	 */
	public boolean isReconciliationSuccess(String depositID,String status) {
		RegularExpression rex = new RegularExpression(
				"Deposit ID Status Total Amount.*|Deposit ID Tracking No Status Adjusted.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);

		IHtmlTable tableGrid=(IHtmlTable)objs[0];
		for (int i = 0; i < tableGrid.rowCount(); i++) {
			if (tableGrid.getCellValue(i, 0).toString().equals(depositID)) {
				if (tableGrid.getCellValue(i, 1).toString().equalsIgnoreCase(// status is the 3rd column in 3.03
						status)) {
					Browser.unregister(objs);
					return true;
				} else {
					Browser.unregister(objs);
					return false;
				}
			}
		}
		Browser.unregister(objs);
		return false;
	}

	/**
	 * Check Specific Deposit Id exists or not
	 * @param depositID
	 * @return specific deposit exists or not
	 */
	public boolean checkDepositExists(String depositID) {
		RegularExpression rex = new RegularExpression(
				"Deposit ID Status Total Amount.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);

		IHtmlTable tableGrid =(IHtmlTable)objs[0];
		for (int i = 0; i < tableGrid.rowCount(); i++) {
			if (tableGrid.getCellValue(i, 0).toString().equals(depositID)) {
				return true;
			}
		}
		Browser.unregister(objs);
		return false;
	}
}
