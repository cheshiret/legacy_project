package com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

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
public class InvMgrDockSlipCommonPage extends InvMgrTopMenuPage {
	
	private static InvMgrDockSlipCommonPage _instance = null;
	
	protected InvMgrDockSlipCommonPage() {}
	
	public static InvMgrDockSlipCommonPage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrDockSlipCommonPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "dockSlipTabPanel");
	}
	
	public void clickDocksTab() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Docks", true);
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Docks", true);//Quentin[20131008] 3.05 changes
	}
	
	public void clickSlipsTab() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Slips", true);
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Slips", true);//Quentin[20131008] 3.05 changes
	}
	
	public void clickNSSGroups() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "NSS Groups", true);
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "NSS Groups", true);//Quentin[20131008] 3.05 changes
	}
	
	public String getFacilityName() {
		return null;
	}
	
	public String getRegionName() {
		return null;
	}
	
	public String getAgencyName() {
		return null;
	}
	
	public boolean isErrorMessageExists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	public String getErrorMessage() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	public String getSuccessMsg(){
		return browser.getObjectText(".class", "Html.DIV", ".className", "message msgsuccess");
	}
}
