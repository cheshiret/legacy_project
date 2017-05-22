/*
 * Created on Nov 23, 2009
 */
package com.activenetwork.qa.awo.pages.orms.systemManager;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author vzhao
 */
public class SysMgrCacheRefreshPage extends SystemManagerPage{
  	private static SysMgrCacheRefreshPage _instance = null;

	protected SysMgrCacheRefreshPage() {
	}

	public static SysMgrCacheRefreshPage getInstance() {
		if (null == _instance)
			_instance = new SysMgrCacheRefreshPage();

		return _instance;
	}

	public boolean exists() {
		//use System Manager logo as pageMark
		return browser.checkHtmlObjectExists(".class", "Html.IMG", ".src",
				new RegularExpression("sysmgr\\.gif", false));
	}
	
	/**
	 * Sselect table from drop down list.
	 * @param table - property value
	 */
	public void selectTable(String table){
	  browser.selectDropdownList(".id","configurable",table);
	}
	
	/**
	 * Fill in query command.
	 * @param command - query command
	 */
	public void setQueryCommand(String command){
	  browser.setTextArea(".id","configurablesql",command);
	}
	
	/**Click on Execute Refresh link to execute the query.*/
	public void clickExecuteRefresh(){
	  browser.clickGuiObject(".class","Html.A",".text","Execute Refresh");
	}
}
