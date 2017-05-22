package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 2, 2013
 */
public class LicMgrPrivilegeLotteryManageChoicePage extends LicMgrTopMenuPage {
	
	private static LicMgrPrivilegeLotteryManageChoicePage _instance = null;
	private LicMgrPrivilegeLotteryManageChoicePage() {}
	
	public static LicMgrPrivilegeLotteryManageChoicePage getInstance() {
		if(_instance == null) {
			_instance = new LicMgrPrivilegeLotteryManageChoicePage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "ChoiceListGrid");
	}
	
	private IHtmlObject[] getHuntChoiceListTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "ChoiceListGrid");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find Hunt Choice list table object.");
		}
		
		return objs;
	}
	
	private int getHuntChoiceRowIndex(String huntCode) {
		IHtmlObject objs[] = this.getHuntChoiceListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int colIndex = table.findColumn(0, "Code / Description");
		int rowIndex = table.findRow(colIndex, new RegularExpression("^" + huntCode, false));
		
		Browser.unregister(objs);
		return rowIndex;
	}
	
	public void clickChoiceUp(String code) {
		int rowIndex = this.getHuntChoiceRowIndex(code);
		browser.clickGuiObject(".class", "Html.IMG", ".className", "ra_moveup cdisabled", rowIndex - 1);
	}
	
	public void clickChoiceDown(String code) {
		int rowIndex = this.getHuntChoiceRowIndex(code);
		browser.clickGuiObject(".class", "Html.IMG", ".className", "ra_movedown cdisabled", rowIndex - 1);
	}
	
	public void clickChoiceRemove(String code) {
		int rowIndex = this.getHuntChoiceRowIndex(code);
		browser.clickGuiObject(".class", "Html.IMG", ".className", "ra_remove", rowIndex - 1);
	}
	
	public void clickAddChoice() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add Choice|Add\\/Edit", false));
	}
	
	public void clickAddToCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add to Cart");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public String getErrorMsg() {
		return browser.getObjectText(".id", "NOTSET");
	}
}
