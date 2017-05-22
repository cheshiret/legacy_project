package com.activenetwork.qa.awo.pages.orms.common.marina;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding
 * @Date  May 15, 2013
 */
public class OrmsSlipAlertsPage extends OrmsPage {

	private static OrmsSlipAlertsPage _instance = null;
	
	private OrmsSlipAlertsPage() {}
	
	public static OrmsSlipAlertsPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsSlipAlertsPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "marinamanager.notealert.slipalert.alerts");
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("OK", false));
	}

	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Cancel", false));
	}
	
	public String getAlertsContent() {
		String content = browser.getObjectText(".class", "Html.Table", ".text", new RegularExpression("^Slip Alerts.*", false));
		return content;
	}
}
