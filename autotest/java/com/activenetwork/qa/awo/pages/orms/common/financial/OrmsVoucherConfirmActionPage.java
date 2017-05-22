/*
 * Created on Dec 8, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsVoucherConfirmActionPage extends OrmsPage
{
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsVoucherConfirmActionPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsVoucherConfirmActionPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsVoucherConfirmActionPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsVoucherConfirmActionPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TEXTAREA", ".id", "reason_note");
	}
	
	/**
	 * Input reason
	 * @param reason
	 */
	public void setReason(String reason)
	{
	  	browser.setTextArea(".id", "reason_note",reason);
	}
	
	/**
	 * Click OK button
	 *
	 */
	public void clickOk()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","OK");
	}
}
