package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 6, 2012
 */
public class InvMgrPOSInventoryCallCenterAllocationPage extends InvMgrPOSInventoryAllocationCommonPage {
	
	private static InvMgrPOSInventoryCallCenterAllocationPage _instance = null;
	
	private InvMgrPOSInventoryCallCenterAllocationPage() {}
	
	public static InvMgrPOSInventoryCallCenterAllocationPage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrPOSInventoryCallCenterAllocationPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
//		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^(Call Center POS Serialized Inventory|Call Center POS Inventory)", false), ".background", new RegularExpression("tab_body_slct", false)));
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression("Call Center POS Inventory", false)) && browser.checkHtmlObjectExists(".id", new RegularExpression("WebCallPOSInvSearchBar", false));
	}
	
	/**
	 * If you want to add any method in here, please consider if it is common for Web and CallCenter, if it is, please put it in super class
	 */
}
