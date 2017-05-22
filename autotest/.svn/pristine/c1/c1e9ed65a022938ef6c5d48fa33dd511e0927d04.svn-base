package com.activenetwork.qa.awo.pages.orms.inventoryManager.equipment;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class InvMgrEquipmentHoursPage extends InvMgrCommonTopMenuPage {
	static private InvMgrEquipmentHoursPage _instance = null;

	protected InvMgrEquipmentHoursPage() {
	}

	static public InvMgrEquipmentHoursPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new InvMgrEquipmentHoursPage();
		}
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", "Equipment Hours");
	}
	
	protected Property[] addNewBtn(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Add New", false));
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(this.addNewBtn());
	}
}