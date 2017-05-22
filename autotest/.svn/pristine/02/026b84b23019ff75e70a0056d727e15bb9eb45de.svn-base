/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:It is for UWP Oops error page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Oct 31, 2012
 */
public class UwpOopsErrorPage extends UwpHeaderBar {
	public final String ERROR_REQUEST_NOT_COMPLETED = "Your request cannot be completed";
	
	private static UwpOopsErrorPage _instance = null;
	
	public static UwpOopsErrorPage getInstance() {
		if (null == _instance)
			_instance = new UwpOopsErrorPage();

		return _instance;
	}

	private UwpOopsErrorPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", 
				".classname", "pageerror wide", ".text", new RegularExpression("^oops.*", false)));
	}
	
	public String getTechnicalErrorMessage() {
		return browser.getObjectText(".class", "Html.DIV", ".classname", "technical");
	}
	
}
