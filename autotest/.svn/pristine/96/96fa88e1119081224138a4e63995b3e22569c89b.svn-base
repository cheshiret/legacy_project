package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.page.HtmlPopupPage;

public class OrmsProcessReportPopupPage extends HtmlPopupPage {
	private static OrmsProcessReportPopupPage _instance;
	
	protected OrmsProcessReportPopupPage () {
		super("title","Processing Report");

	}
	
	public static OrmsProcessReportPopupPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsProcessReportPopupPage();
		}
		
		return _instance;
	}
	
	public void waitForDisappear() {
		boolean disappeared= (popup.exists()==false)? true:false;
		
//		int count=0;
//		int timeout=5;
//		while(!disappeared && count<timeout) {
//			Browser.sleep(1);
//			if(!popup.exists()){
//				disappeared=true;
//			}
//			count++;
//		}
		
		if(!disappeared){
			logger.warn("Popup doesn't automatically close in "+timeout+" seconds. Force close!");
			close();
		}
	}
	
	public boolean exists() {
		popup=Browser.getInstance().getBrowser(attributeName, value);
		return popup!=null;
	}
}
