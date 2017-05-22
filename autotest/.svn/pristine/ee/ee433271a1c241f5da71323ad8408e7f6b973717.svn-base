package com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip;

import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 10, 2012
 */
public abstract class InvMgrDockDetailsCommonPage extends InvMgrDockSlipCommonPage {
	
	public String getDockAreaName() {
		return browser.getObjectText(".id", new RegularExpression("DockView-\\d+\\.dockName", false)).replaceAll("Dock/Area Name", StringUtil.EMPTY);
	}
	
	public String getDescription() {
		return browser.getObjectText(".id", new RegularExpression("DockView-\\d+\\.description", false)).replaceAll("Description", StringUtil.EMPTY);
	}
	
	public void clickDockAreaDetailsTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Dock/Area Details");
	}
	
	public void clickDocksSlipsTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Dock's Slips");
	}
}
