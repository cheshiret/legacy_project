package com.activenetwork.qa.awo.pages.orms.licenseManager.common.product;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 23, 2012
 */
public abstract class LicMgrPrivilegeInventoryCommonPage extends LicMgrCommonTopMenuPage {
	
	public void clickInventoryTypes(){
		browser.clickGuiObject(".id", new RegularExpression("^InventoryTabBar_\\d+",false), ".text", "Inventory Types");
	}
	
	public void clickInventoryTypeLicenseYears(){
		browser.clickGuiObject(".id", new RegularExpression("^InventoryTabBar_\\d+",false), ".text", new RegularExpression("Inventory Type (License|Licence) Years", false));
	}
	
	public void clickPrivilegeInventory(){
		browser.clickGuiObject(".id", new RegularExpression("^InventoryTabBar_\\d+",false), ".text", new RegularExpression("(Privilege|Licence) Inventory", false));
	}
	
	public void switchToInventoryTypesTab() {
		clickInventoryTypes();
		ajax.waitLoading();
	}
	
	public void switchToInventoryTypeLicenseYearsTab() {
		clickInventoryTypeLicenseYears();
		ajax.waitLoading();
	}
	
	public void switchToPrivilegeInventoryTab() {
		clickPrivilegeInventory();
		ajax.waitLoading();
	}
}
