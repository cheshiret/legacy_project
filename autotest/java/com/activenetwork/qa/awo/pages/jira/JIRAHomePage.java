/*
 * Created on Dec 29, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.jira;


import com.activenetwork.qa.awo.pages.JiraPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.KeyInput;



/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JIRAHomePage extends JiraPage{
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private JIRAHomePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected JIRAHomePage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public JIRAHomePage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new JIRAHomePage();
		}
		return _instance;
	}

	/** Determine if the JIRA Login page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id","create_link");
	}
	
	public void doQuickSearch(String info)
	{
	  if(!info.equals(""))
	  {
		  
		browser.setTextField(".id","quickSearchInput",info);
		browser.inputKey(KeyInput.get(KeyInput.ENTER));
//		HtmlObject[] search=browser.getHtmlObject(".id","quickSearchInput");
//		((IText)search[0]).setText("{ENTER}");
//		try{
//			Robot rob = new Robot();
//			rob.keyPress(KeyEvent.VK_ENTER);
//		}catch(AWTException e){
//			e.printStackTrace();
//		}
//		this.waitExists();
	  }
	}
	
}
