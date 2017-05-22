/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: It is for the error page 'Page Not Found' in RA web site.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Oct 31, 2012
 */
public class UwpPageNotFoundErrorPage extends UwpHeaderBar {
	public final String ERROR_DOCNOTFOUND = "404 - Document Not Found";
	
	private static UwpPageNotFoundErrorPage _instance = null;
	
	public static UwpPageNotFoundErrorPage getInstance() {
		if (null == _instance)
			_instance = new UwpPageNotFoundErrorPage();

		return _instance;
	}

	private UwpPageNotFoundErrorPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.DIV", 
				".classname", "fullpageerror", ".text", new RegularExpression("^page not found.*", false)));
	}
	
	public String getTechnicalErrorMessage() {
		return browser.getObjectText(".class", "Html.DIV", ".classname", "technical");
	}
	
}
