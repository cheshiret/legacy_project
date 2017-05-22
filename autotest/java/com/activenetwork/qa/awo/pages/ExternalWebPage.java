package com.activenetwork.qa.awo.pages;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.page.HtmlMainPage;

/**
 * This class wraps web page opened by the main browser such as ESL external web page, report web page etc.
 * @author jdu
 *
 */
public class ExternalWebPage extends HtmlMainPage {
	protected String attributeName;
	protected Object value;
	
	protected ExternalWebPage() {
		this(null,null);
	}
	
	protected ExternalWebPage(String attributeName,Object value) {
		this.browser=null;
		this.attributeName=attributeName;
		this.value=value;
	}
	
	@Override
	public boolean exists() {
		browser=Browser.getInstance().getBrowser(attributeName, value);
		return browser!=null;
	}
	
	public void close() {
		browser.close();
	}
	
	

}
