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
 * @Date  Jul 30, 2012
 */
public abstract class InvMgrPOSPhysicalInventoryReconciliationCommonPage extends InvMgrCommonTopMenuPage {
	
	public void clickPOSInventoryReconciliationTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "POS Inventory Reconciliation");
	}
	
	public void clickPOSInventoryFileLogTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "POS Inventory File Log");
	}
	
	public void clickPOSInventoryReconciliationLogTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "POS Inventory Reconciliation Log");
	}
	
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
}
