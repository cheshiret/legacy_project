/*
 * Created on Dec 30, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.jira;

import com.activenetwork.qa.awo.pages.JiraPage;
import com.activenetwork.qa.testapi.PageNotFoundException;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JIRACancelRequestPage extends JiraPage {
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private JIRACancelRequestPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected JIRACancelRequestPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public JIRACancelRequestPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new JIRACancelRequestPage();
		}
		return _instance;
	}

	/** Determine if the JIRA cancel request page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id","Cancel Request");
	}
	
	/**
	 * Set comment information for cancel request
	 * @param comm
	 */
	public void setComment(String comm){
	  browser.setTextArea(".id","comment",comm);
	}
	
	public void clickCancelRequestButton(){
	  browser.clickGuiObject(".id","Cancel Request");
	}
	
}
