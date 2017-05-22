/*
 * Created on Dec 30, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.jira;

import com.activenetwork.qa.awo.pages.JiraPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author dsui
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JIRALogOutPage extends JiraPage {
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private JIRALogOutPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected JIRALogOutPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public JIRALogOutPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new JIRALogOutPage();
		}
		return _instance;
	}

	/** Determine if the JIRA Logout page exists */
	public boolean exists() {
	  
	  RegularExpression reg=new RegularExpression("^Logout You are now logged out.*",false);
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text",reg);
	}
}
