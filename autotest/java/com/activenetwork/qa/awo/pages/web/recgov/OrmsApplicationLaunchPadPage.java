package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.UwpPage;

public class OrmsApplicationLaunchPadPage extends UwpPage {
	private static OrmsApplicationLaunchPadPage _instance = null;

	public static OrmsApplicationLaunchPadPage getInstance() {
		if (null == _instance)
			_instance = new OrmsApplicationLaunchPadPage();

		return _instance;
	}

	protected OrmsApplicationLaunchPadPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "launchPad");
	}
	
	/**
	 * Select contract.
	 * @param contract
	 */
	public void selectContract(String contract) {
		browser.selectDropdownList(".id", "selected_contract", contract);
	}
	
	/**
	 * Select location.
	 * @param location
	 */
	public void selectLocation(String location) {
		browser.selectDropdownList(".id", "selected_loc", location);
	}
	
	/**
	 * Select locale.
	 * @param locale
	 */
	public void selectLocale(String locale) {
		browser.selectDropdownList(".id", "selected_locale", locale);
	}
	
	/**Click sign out.*/
	public void clickSignOut() {
		browser.clickGuiObject(".class", "Html.A",".text","Sign out");
	}
	
	/**
	 * Click on Finance Manager link.
	 */
	public void clickFinanceManager() {
		browser.clickGuiObject(".class", "Html.A",".text","FinanceManager");
	}
	
	/**
	 * Click on Admin Manager link.
	 */
	public void clickAdminManager() {
		browser.clickGuiObject(".class", "Html.A",".text","AdminManager");
	}
	
	/**
	 * Click on Call Manager link.
	 */
	public void clickCallManager() {
		browser.clickGuiObject(".class", "Html.A",".text","CallManager");
	}
	
	/**
	 * Click on Operations Manager link.
	 */
	public void clickOperationsManager() {
		browser.clickGuiObject(".class", "Html.A",".text","OperationsManager");
	}
	
	/**
	 * Click on Inventory Manager link.
	 */
	public void clickInventoryManager() {
		browser.clickGuiObject(".class", "Html.A",".text","InventoryManager");
	}
	
	/**
	 * Click on System Manager link.
	 */
	public void clickSystemManager() {
		browser.clickGuiObject(".class", "Html.A",".text","SystemManager");
	}
	
	/**
	 * Click on Resource Manager link.
	 */
	public void clickResourceManager() {
		browser.clickGuiObject(".class", "Html.A",".text","ResourceManager");
	}
	
	/** Click on NRRS contract Marketing Website link */
	public void clickMarketingSite() {
		browser.clickGuiObject(".class", "Html.A",".text","NRRS Marketing Website");
	}
	
	/** Click on NRRS contract Support Center link */
	public void clickSupportCenter() {
		browser.clickGuiObject(".class", "Html.A",".text","Support Center");
	}
}
