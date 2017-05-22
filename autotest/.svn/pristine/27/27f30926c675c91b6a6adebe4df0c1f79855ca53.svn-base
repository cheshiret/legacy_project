package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.testapi.util.Property;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang5
 * @Date  Apr 15, 2014
 */
public class RecgovCapeHattterasORVPermitSaleOverviewPage extends UwpHeaderBar {
	static class SingletonHolder {
		protected static RecgovCapeHattterasORVPermitSaleOverviewPage _instance = new RecgovCapeHattterasORVPermitSaleOverviewPage();
	}

	protected RecgovCapeHattterasORVPermitSaleOverviewPage() {
	}

	public static RecgovCapeHattterasORVPermitSaleOverviewPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] purchaseORVPermits(){
		return Property.toPropertyArray(".id", "button", ".text", "Purchase ORV Permits");
	}
	/** Page Object Property Definition Begin */
	
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(purchaseORVPermits());
	}
	
	public void clickPurchaseOrvPermits(){
		browser.clickGuiObject(purchaseORVPermits());
	}
}
