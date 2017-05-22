package com.activenetwork.qa.awo.pages.orms.licenseManager;

/**
 * @Description: Customer Unlocked Privileges List page, customer details page -> Unlocked Privileges tab
 * @author Lesley Wang
 * @Date  Aug 13, 2013
 */
public class LicMgrCustomerUnlockedPrivilegesPage extends
		LicMgrCustomerDetailsPage {

	private static LicMgrCustomerUnlockedPrivilegesPage instance = null;

	private LicMgrCustomerUnlockedPrivilegesPage() {
	};

	public static LicMgrCustomerUnlockedPrivilegesPage getInstance() {
		if (instance == null) {
			instance = new LicMgrCustomerUnlockedPrivilegesPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",
				"UnlockedPrivilegeListGrid");
	}
	
	public void selectAllChecoBox() {
		browser.selectCheckBox(".name", "all_slct");
	}
	
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}
	
	public void deactivateAllUnlockedPriv() {
		this.selectAllChecoBox();
		this.waitLoading();
		this.clickDeactivate();
		ajax.waitLoading();
		this.waitLoading();
	}
}
