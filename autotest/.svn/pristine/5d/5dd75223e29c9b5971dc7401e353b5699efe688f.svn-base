/*
 * $Id: AdmMgrRuleListPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.adminManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @author CGuo
 */
public class AdmMgrRuleListPage extends AdminManagerPage {

	/**
	 * Script Name   : AdmRuleListPage
	 * Generated     : Jul 11, 2007 10:51:44 AM
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 *
	 * @since  2007/07/11
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmMgrRuleListPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmMgrRuleListPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmMgrRuleListPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmMgrRuleListPage();
		}

		return _instance;
	}

	/**Check the object exist*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Minimum Stay");
	}

	/**Click the rule name*/
	public void enterRuleDetail(String ruleName) throws PageNotFoundException {
		if(ruleName.contains("(Permits)")) {
			ruleName = ruleName.replace("(Permits)", "").trim();
		}
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(StringUtil.convertToRegex(ruleName), false), true);
	}

	/**Click the find rules button*/
	public void clickFindRule() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Find Rules");
	}
	
	public void clickRuleName(String ruleName){
	   browser.clickGuiObject(".class","Html.A",".text",ruleName);
	}
	
	public List<String> getRuleNameList(){
		List<String> ruleList = new ArrayList<String>();
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^Rule Name.*", false));
		if(objs.length < 1){
			throw new ErrorOnPageException("Could not find rule list table");
		}
		IHtmlTable ruleTable = (IHtmlTable)objs[0];
		for(int i=1; i<ruleTable.rowCount(); i++){
			String cell = ruleTable.getCellValue(i, 0);
			if(null != cell	&& cell.length()>0){
				ruleList.add(cell);
			}
		}
		Browser.unregister(objs);
		return ruleList;
	}

}
