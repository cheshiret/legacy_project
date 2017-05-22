package com.activenetwork.qa.awo.pages.orms.inventoryManager;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.page.IPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;


public class InvMgrMarinaMapViewPage extends InventoryManagerPage implements IPopupPage{
  
    private static InvMgrMarinaMapViewPage _instance = null;
    
    private Object urlValue;
    
	public static InvMgrMarinaMapViewPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrMarinaMapViewPage();
		}

		return _instance;
	}

	protected InvMgrMarinaMapViewPage() {
		browser = null;
		urlValue = new RegularExpression(".*MarinaMgrMapEditPage.*", false);
	}
	
	public boolean exists() {
		browser = Browser.getInstance().getBrowser("url", urlValue);
		return browser!=null;
	}
	
	public void selectDockArea(String dock) {
		browser.selectDropdownList(".id", "docks_area", dock);
	}
	
	public void selectMappedSlip(String slip) {
		browser.selectDropdownList(".className", new RegularExpression(".*mappedProducts$", false), slip);
	}
	
	public void selectMappedSlip(String dock, String slip) {
		browser.selectDropdownList(".className", new RegularExpression(".*mappedProducts$", false), dock+" : "+slip);
	}
	
	public void selectUnMappedSlip(String slip) {                         
		browser.selectDropdownList(".className", new RegularExpression(".*unmappedProducts$", false), slip);
	}
	
	public void clickAdd() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}
	
	public void clickSaveEdits() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Save Edits");
	}
	
	public void clickX() {
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression(".*RAMap\\.close()", false));
	}
}
