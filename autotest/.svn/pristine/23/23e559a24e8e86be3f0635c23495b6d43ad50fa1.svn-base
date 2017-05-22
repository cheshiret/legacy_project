package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityQuotaType;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 *
 * @author fliu
 * @Date  March 13, 2012
 */
public class InvMgrQuotaTypeListPage extends InventoryManagerPage {

	private static InvMgrQuotaTypeListPage _instance = null;

	public static InvMgrQuotaTypeListPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrQuotaTypeListPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.A",".text", "Add New");
	}

	public void clickAddNew() {
		browser.clickGuiObject(".class","Html.A",".text", "Add New", true);
	}

	public List<List<String>> getQuotaTypeList(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Htnl.Table", ".text", new RegularExpression("Quota Type Code.*", false));
		if(objs == null || objs.length < 0) {
			throw new ErrorOnPageException("Can't find the table.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		List<List<String>> quotaTypeList = new ArrayList<List<String>>();
		if(table.rowCount() > 2){
			// 1st line is title.
			for(int row=1; row<table.rowCount();row++){
				quotaTypeList.add(table.getRowValues(row));
			}

		}
		return quotaTypeList;
	}

	public void clickQuotaTypeCodeLink(String quotaTypeCode){
		browser.clickGuiObject(".class", "Html.A", ".text", quotaTypeCode);
	}

	public String getSuccessMsg(){
		String successMsg = "";
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".id", new RegularExpression("NOTSET", false));
		if(objs != null && objs.length > 0){
			successMsg=objs[0].getProperty(".text");
		}
		Browser.unregister(objs);
		return successMsg;
	}

	public boolean checkQuotaTypeExists(String quotaTypeCode) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", quotaTypeCode);
	}

}
