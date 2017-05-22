package com.activenetwork.qa.testapi.page;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.browser.ISimpleBrowser;
import com.activenetwork.qa.testapi.interfaces.page.IPopupPage;

public abstract class HtmlPopupPage extends HtmlPage implements IPopupPage {

	protected ISimpleBrowser popup;
	protected String attributeName;
	protected Object value;
	protected boolean beforePageLoading;
	
	protected HtmlPopupPage(String attributeName,Object value) {
		timeout=Browser.LONG_SLEEP;
		popup=null;
		this.attributeName=attributeName;
		this.value=value;
		beforePageLoading=true;
		
	}
	
	@Override
	public boolean exists() {
		popup=Browser.getInstance().getHTMLDialog(attributeName, value);
		return popup!=null;
	}

	public void close() {
		popup.close();
	}
	
    public void setBeforePageLoading(boolean option) {
    	beforePageLoading=option;
    }
    
    public boolean isBeforePageLoading() {
    	return beforePageLoading;
    }

}
