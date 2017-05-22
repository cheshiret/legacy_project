/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.adminManager;

/**
 * @Description: this is a common parent page under user's tab,used to switch
 *               between different tabs
 * 
 * @author ssong
 * @Date Mar 26, 2012
 */
public abstract class AdmMgrUserCommonTabPage extends AdminManagerPage {

	public void clickUserDetails() {
		browser.clickGuiObject(".class", "Html.A", ".text", "User's Details");
	}

	public void clickUserLocation() {
		browser.clickGuiObject(".class", "Html.A", ".text", "User's Locations");
	}

	public void clickUserRole() {
		browser.clickGuiObject(".class", "Html.A", ".text", "User's Roles");
	}

	public void clickUserEnviornment() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"User's Environments");
	}
}
