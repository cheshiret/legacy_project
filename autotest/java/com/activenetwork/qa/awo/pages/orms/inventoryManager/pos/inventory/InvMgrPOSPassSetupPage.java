/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory;

import com.activenetwork.qa.testapi.util.Property;

/**
 * @author ssong
 * @Date  Jan 23, 2014
 */
public class InvMgrPOSPassSetupPage extends InvMgrPOSInventoryManagementCommonPage{

	private static InvMgrPOSPassSetupPage _instance = null;
	
	private InvMgrPOSPassSetupPage(){}
	
	public static InvMgrPOSPassSetupPage getInstance(){
		if(_instance==null){
			_instance = new InvMgrPOSPassSetupPage();
		}
		return _instance;
	}
	
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(passSetupDiv());
	}
	
	protected Property[] passSetupDiv(){
		return Property.addToPropertyArray(div(),".id", "POS Passes Setup");
	}
	
	protected Property[] addPassNum(){
		return Property.toPropertyArray(".class","Html.A",".text","Add Pass Number");
	}
	
	public void clickAddPassNum(){
		browser.clickGuiObject(addPassNum());
	}
	
}
