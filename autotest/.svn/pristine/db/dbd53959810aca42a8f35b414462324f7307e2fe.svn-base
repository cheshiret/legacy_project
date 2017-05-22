package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
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
public abstract class InvMgrPOSInventoryManagementCommonPage extends InvMgrCommonTopMenuPage{
	
	public String getWarehouseName() {
		return getAttributeValueByName("Warehouse");
	}
	
	public String getRegion() {
		return getAttributeValueByName("Region");
	}
	
	public String getAgency() {
		return getAttributeValueByName("Agency");
	}
	
	private String getAttributeValueByName(String name) {
		Property p[] = new Property[3];
		p[0] = new Property(".class", "Html.DIV");
		p[1] = new Property(".className", "inputwithrubylabel");
		p[2] = new Property(".text", new RegularExpression("^" + name, false));
		
		IHtmlObject objs[] = browser.getHtmlObject(p);
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find DIV object which name is: " + name);
		}
		
		String text = objs[0].getProperty(".text").split(name)[1].trim();
		Browser.unregister(objs);
		
		return text;
	}
	
	public void clickPOSInventoryManagementTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "POS Inventory Management");
	}
	
	public void clickWebPOSInventory() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Web POS Inventory|Web POS Serialized Inventory)",false));
	}
	
	public void clickCallCenterPOSInventory() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Call Center POS Inventory|Call Center POS Serialized Inventory)",false));
	}
	
	public void clickFieldPOSInventory() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", new RegularExpression("Field POS Inventory",false));
	}
}
