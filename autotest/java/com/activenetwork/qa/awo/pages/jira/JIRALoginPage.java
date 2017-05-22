/*
 * Created on Dec 29, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.jira;

import com.activenetwork.qa.awo.pages.JiraPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;


/**
 * @author dsui
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JIRALoginPage extends JiraPage {
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private JIRALoginPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected JIRALoginPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public JIRALoginPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new JIRALoginPage();
		}
		return _instance;
	}

	/** Determine if the JIRA Login page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id","usernameinput");
	}
	
	/**
	 * Set username
	 * @param username
	 */
	public void setUserName(String username)
	{
	  browser.setTextField(".name","os_username",username);
	}
	
	/**
	 * Set Password
	 * @param psw
	 */
	public void setPSW(String psw)
	{
	  browser.setTextField(".name","os_password",psw);
	}
	
	/**Click Login Button*/
	public void clickLogin()
	{
	  browser.clickGuiObject(".id","login");
	}
	
	public void loginJira(String username, String psw){
		setUserName(username);
		setPSW(psw);
		clickLogin();
	}

	/**
	 * JIRA login page wait exist
	 * @param timeOut
	 */
	public void jiraLoginPageWaitExists(int timeOut){
		JIRALoginPage loginPg = JIRALoginPage.getInstance();
		
		int count = 0;
		while(!loginPg.exists()){
			Browser.sleep(1);
			count++;
			if(count>timeOut){
				throw new ItemNotFoundException("Can not found....");
			}
		}
	}
}
