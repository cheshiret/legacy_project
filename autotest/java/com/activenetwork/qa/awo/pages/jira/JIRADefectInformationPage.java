/*
 * Created on Dec 29, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.jira;


import com.activenetwork.qa.awo.pages.JiraPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;


/**
 * @author dsui
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JIRADefectInformationPage extends JiraPage {
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private JIRADefectInformationPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected JIRADefectInformationPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public JIRADefectInformationPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new JIRADefectInformationPage();
		}
		return _instance;
	}

	/** Determine if the JIRA Login page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text","Issue Details (XML | Word | Printable )");
	}
	
	/**
	 * Check whether the searched request number exist
	 * @param reqnum
	 * @return
	 */
	public boolean checkReqNumExist(String reqnum)
	{
	  boolean exist=false;
	  
	  if(browser.checkHtmlObjectExists(".id","issue_key_"+reqnum)){
	    exist=true;
	  }
	  
	  return exist;    
	}
	
	/**
	 * Get all header information
	 * @return
	 */
	public String getHeaderInfo()
	{
	  IHtmlObject[] summ=browser.getHtmlObject(".id","issue_header");
	  String summary=summ[0].getProperty(".text").toString();
	  
	  return summary;
	   
	}
	
	/**Click cancel request link*/
	public void clickCancelRequest(){
	  browser.clickGuiObject(".class","Html.A",".text","Cancel Request");
	}
}
