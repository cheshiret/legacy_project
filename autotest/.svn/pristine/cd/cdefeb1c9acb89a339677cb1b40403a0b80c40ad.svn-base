/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jane
 * @Date  Dec 14, 2012
 */
public class InvMgrSeasonClosureCommonPage extends InvMgrTopMenuPage {
	private static InvMgrSeasonClosureCommonPage _instance = null;
	
	protected InvMgrSeasonClosureCommonPage() {}
	
	public static InvMgrSeasonClosureCommonPage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrSeasonClosureCommonPage();
		}
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.Table", ".text", new RegularExpression("^seasonsclosures$", false));
	}
	
	public void clickSeasonsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Seasons", true);
	}
	
	public void clickClosuresTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Closures", true);
	}
}
