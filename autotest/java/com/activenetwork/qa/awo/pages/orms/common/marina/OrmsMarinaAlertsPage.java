package com.activenetwork.qa.awo.pages.orms.common.marina;

import com.activenetwork.qa.awo.pages.orms.OrmsPage;
import com.activenetwork.qa.testapi.util.Property;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Dec 10, 2013
 */
public class OrmsMarinaAlertsPage extends OrmsPage {
	public static OrmsMarinaAlertsPage _instance = null;
	
	private OrmsMarinaAlertsPage() {}
	
	public static OrmsMarinaAlertsPage getInstance() {
		if(_instance == null) {
			_instance =  new OrmsMarinaAlertsPage();
		}
		
		return _instance;
	}
	
	private Property[] marinaAlerts() {
		return Property.toPropertyArray(".id", "marinamanager.notealert.marinaalerts");
	}
	
	private Property[] ok() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK");
	}
	
	private Property[] cancel() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Cancel");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(marinaAlerts());
	}
	
	public void clickOK() {
		browser.clickGuiObject(ok());
	}
	
	public void clickCancel() {
		browser.clickGuiObject(cancel());
	}
}
