package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Nov 22, 2013
 */
public class RecgovEntryAndExitSelectionPage extends UwpPage{
	static class SingletonHolder {
		protected static RecgovEntryAndExitSelectionPage _instance = new RecgovEntryAndExitSelectionPage();
	}

	protected RecgovEntryAndExitSelectionPage() {
	}

	public static RecgovEntryAndExitSelectionPage getInstance() {
		return SingletonHolder._instance;
	}
	
	protected Property[] productname(){
		return Property.concatPropertyArray(div(), ".id", "productname", ".text", "Entry & Exit Selection");
	}
	
	protected Property[] findPermits(){
		return Property.toPropertyArray(".name", "permitAvailabilitySearchButton", ".id", new RegularExpression("findPermit_\\d+", false));
	}
	
	protected Property[] changePermitTypeOrEntry(){
		return Property.concatPropertyArray(a(), ".text", "Change Permit Type/Entry");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(productname()) && browser.checkHtmlObjectExists(findPermits());
	}
	
	public void clickChangePermitTypeOrEntry(){
		browser.clickGuiObject(changePermitTypeOrEntry());
	}
}
