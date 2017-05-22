package com.activenetwork.qa.awo.pages.orms.inventoryManager.equipment;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This is the equipment search page, to go to this page, select "Equipment Set-up" in facility detail page
 * @author Pchen
 *
 */
public class InvMgrEquipmentSearchPage extends InventoryManagerPage{
	private static InvMgrEquipmentSearchPage _instance = null;

	public static InvMgrEquipmentSearchPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrEquipmentSearchPage();
		}

		return _instance;
	}

	protected InvMgrEquipmentSearchPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(this.equipmentSearchTable());
	}
	
	protected Property[] equipmentSearchTable(){
		return Property.toPropertyArray(".id", "equipmentproductgrid_LIST");
	}
	
	protected Property[] addNewBtn(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Add New",false));
	}
	
	public void clickAddNew(){
		browser.clickGuiObject(this.addNewBtn());
	}
	
	public void clickEqID(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
}
