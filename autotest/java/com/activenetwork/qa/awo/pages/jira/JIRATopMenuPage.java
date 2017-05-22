/*
 * Created on Dec 30, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.jira;

import com.activenetwork.qa.testapi.PageNotFoundException;

/**
 * @author dsui
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JIRATopMenuPage extends JiraCommonTopMenuPage{
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private JIRATopMenuPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected JIRATopMenuPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public JIRATopMenuPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new JIRATopMenuPage();
		}
		return _instance;
	}

	/** Determine if the JIRA top menu page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.IMG",".alt","Reserve America JIRA 3.13 QA");
	}
	

}
