package com.activenetwork.qa.awo.pages.web;

import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class WebPrintPopupPage extends HtmlPopupPage{

	private static WebPrintPopupPage _instance=null;
	private static RegularExpression titlePattern=new RegularExpression(".*reserveamerica\\.com.*UwpPrintAction\\.do.*",false);
	
	protected WebPrintPopupPage() {
		super("title", titlePattern);
	}
	
	public static WebPrintPopupPage getInstance() {
		if(null==_instance) {
			_instance=new WebPrintPopupPage();
		}
		
		return _instance;
	}
	
	public void waitForDisappear() {
		boolean disappeared=popup==null?true:popup.exists();
		int count=0;
		while(!disappeared && count<timeout) {
			Browser.sleep(1);
			disappeared=popup.exists();
		}
		
		if(!disappeared){
			throw new ActionFailedException("Print popup doesn't automatically close in "+timeout+" seconds");
		}
	}

}
