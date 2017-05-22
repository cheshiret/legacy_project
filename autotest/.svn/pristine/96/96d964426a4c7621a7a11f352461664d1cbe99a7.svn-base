/*
 * Created on Jan 20, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.adminManager;

import com.activenetwork.qa.testapi.PageNotFoundException;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdmTopMenuPage extends AdmMgrCommonTopMenuPage{

  /**
	 * A handle to the unique Singleton instance.
	 */
	static private AdmTopMenuPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected AdmTopMenuPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public AdmTopMenuPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new AdmTopMenuPage();
		}

		return _instance;
	}

	/**Determine whether the object exist*/
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "CatalogDropDown");
	}
	
	


}
