/*
 * Created on Dec 8, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class OrmsOrderCartEndPage extends OrmsPage {
  
	/**
	 * A handle to the unique Singleton instance.
	 */
//  	private static RALogger logger = RALogger.getInstance();
  	static private OrmsOrderCartEndPage _instance = null;

  	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	private OrmsOrderCartEndPage() {
	}
	
	/**
	 * @return The unique instance of this class.
	 */
	public static OrmsOrderCartEndPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsOrderCartEndPage();
		}
		return _instance;
	}
	
	/** Determine if the associated web object exists */
	public boolean exists() { // use the Process Order button as pageMark
		return browser.checkHtmlObjectExists(".class","Html.A",".text","Don't Cancel");
	}
	
	/**Click the Don't cancel button*/
	public void clickDoNotCancel(){
	   browser.clickGuiObject(".class","Html.A",".text","Don't Cancel");
	}
	
	/**Click the ok to cancel cart link*/
	public void clickCancelCart(){
	  browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("Ok to Cancel Cart", false));
	}
}
