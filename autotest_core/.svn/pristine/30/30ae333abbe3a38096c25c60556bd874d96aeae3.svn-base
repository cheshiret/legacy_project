package com.activenetwork.qa.testapi.page;

import com.activenetwork.qa.testapi.TestApiConstants;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.browser.IBrowser;
import com.activenetwork.qa.testapi.interfaces.page.Loadable;

/**
 * This class is used to generally handle Ajax synchronization, which become more and more
 * popular in various web applications. Due implementation differences between different web application,
 * the implementation should base on the specific web application under test.
 * @author jdu
 *
 */
public abstract class Ajax implements Loadable {
	protected IBrowser browser=Browser.getInstance();
	protected static Ajax _instance=null;
	
	@Override
	public void waitLoading() {
		browser.waitDisappear(TestApiConstants.VERY_LONG_SLEEP, this);
	}

	@Override
	public String getName() {
		String name=this.getClass().getSimpleName();
		
		return name;
	}

}
