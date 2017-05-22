package com.activenetwork.qa.awo.pages.orms.licenseManager.common;

import com.activenetwork.qa.testapi.ActionFailedException;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 27, 2011
 */
public abstract class LicMgrOrderCommonPage extends LicMgrCommonTopMenuPage {
	
	/**
	 * Switch different sub page with clicking each tab
	 * @param tabName
	 */
	private void switchOrderTab(String tabName) {
		if(null == tabName || tabName.length() == 0) {
			throw new ActionFailedException("Can't find specified Tab with tab name - " + tabName);
		}
		
		browser.clickGuiObject(".class", "Html.A", ".text", tabName.trim());
		ajax.waitLoading();
	}
	
	/**
	 * Click Privilege Orders tab
	 */
	public void clickPrivilegeOrdersTab() {
		switchOrderTab("Privilege Orders");
	}
	
	public void clickApplicationOrdersTab() {
		switchOrderTab("Application Orders");
	}
	
	public void clickActivityRegistrationOrdersTab() {
		switchOrderTab("Activity Registration Orders");
	}
	
	/**
	 * Click Vehicle Orders tab
	 */
	public void clickVehicleOrdersTab() {
		switchOrderTab("Vehicle Orders");
	}
	
	/**
	 * Click Consumables Orders tab
	 */
	public void clickConsumablesOrdersTab() {
		switchOrderTab("Consumables Orders");
	}

	/**
	 * Click Supplies Orders tab
	 */
	public void clickSuppliesOrdersTab() {
		switchOrderTab("Supplies Orders");
	}
	
	/**
	 * Click Receipts tab
	 */
	public void clickReceiptsTab() {
		switchOrderTab("Receipts");
	}
}
