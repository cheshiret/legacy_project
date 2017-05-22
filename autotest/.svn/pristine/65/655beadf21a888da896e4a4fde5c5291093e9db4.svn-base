package com.activenetwork.qa.awo.pages.orms.common.equipment;

import com.activenetwork.qa.awo.pages.orms.OrmsPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsEquipmentRentalOrderDetailsPage extends OrmsPage {
	static private OrmsEquipmentRentalOrderDetailsPage _instance = null;
	
	static public OrmsEquipmentRentalOrderDetailsPage getInstance(){
		if (null == _instance) {
			_instance = new OrmsEquipmentRentalOrderDetailsPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return checkLastTrailValueIs("Equipment Rental Order Details");
	}
	
	protected Property[] voidButton() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("^Void$", false));
	}
	
	public void clickVoid() {
		browser.clickGuiObject(voidButton());
	}
}