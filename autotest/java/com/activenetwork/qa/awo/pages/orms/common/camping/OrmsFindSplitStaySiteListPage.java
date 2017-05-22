/*
 * Created on Jan 8, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.camping;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
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
public class OrmsFindSplitStaySiteListPage extends OrmsPage{

  static private OrmsFindSplitStaySiteListPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsFindSplitStaySiteListPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsFindSplitStaySiteListPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsFindSplitStaySiteListPage();
		}

		return _instance;
	}

	/** Determine if the page object exists */
	public boolean exists() {
//	  RegularExpression reg=new RegularExpression("^ ?Find Site.*Find Split Stay$",false);
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",reg);
		return checkLastTrailValueIs("Find Split Stay");
	}
	
	/**
	 * Select one specific site for make split stay reservatiion
	 * @param col
	 * @param sitenum
	 */
	
	public void selectSpecSite(int col,String sitenum)
	{
	  	RegularExpression reg = new RegularExpression("^\\W*Site\\# \\(Name\\).*",false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", reg);
		int row=((IHtmlTable)objs[0]).findRow(col, sitenum);

		browser.selectRadioButton(".id","selected_site_index",row-2);
		Browser.unregister(objs);
	}
}
