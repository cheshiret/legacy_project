package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseManagerPage;


public abstract class LicMgrCommonTopMenuPage extends LicenseManagerPage {
	
	public void clickHome() {
		browser.clickGuiObject(".class","Html.A",".text","Home");
	}
	
	public void clickVendors() {
		browser.clickGuiObject(".class","Html.A",".text","Vendors");
	}

}
