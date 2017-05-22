package com.activenetwork.qa.awo.pages.orms.adminManager;

public abstract class AdmMgrCommonTopMenuPage extends AdminManagerPage {
	
	/**Click Logout link*/
	public void clickLogout(){
	  browser.clickGuiObject(".class","Html.A",".text","Sign out");
	}
	
	public void clickHome(){
		browser.clickGuiObject(".class","Html.A",".text","Home");
	}
	
	public void clickSwitch(){
		browser.clickGuiObject(".class","Html.A",".text","Switch");
	}
	/**
	 * Select the catalog from the dropdown list
	 * @param pageName -- the catalog will be selected
	 */
	public void selectPageName(String pageName) {
		browser.selectDropdownList(".id", "CatalogDropDown", pageName);
	}
}
