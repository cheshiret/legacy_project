package com.activenetwork.qa.awo.pages.orms.common.equipment;

import com.activenetwork.qa.awo.pages.orms.OrmsPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsVoidEquipmentRentalOrderPage extends OrmsPage {
	static private OrmsVoidEquipmentRentalOrderPage _instance = null;
	
	static public OrmsVoidEquipmentRentalOrderPage getInstance(){
		if (null == _instance) {
			_instance = new OrmsVoidEquipmentRentalOrderPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return checkLastTrailValueIs("Void Equipment Rental Order");
	}
	
	protected Property[] voidReasonDropdownList() {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression("FacilityOrderView-\\d+\\.profileView\\.reasonCode", false));
	}
	
	protected Property[] voidNotesTextArea() {
		return Property.concatPropertyArray(this.input("textarea"), ".id", new RegularExpression("FacilityOrderView-\\d+\\.profileView\\.reasonNote", false));
	}
	
	protected Property[] voidOrderButton() {
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("^Void Order$", false));
	}
	
	public void selectVoidReason(String reason) {
		browser.selectDropdownList(voidReasonDropdownList(), reason);
	}
	
	public void setVoidNotes(String notes) {
		browser.setTextArea(voidNotesTextArea(), notes);
	}
	
	public void clickVoidOrder() {
		browser.clickGuiObject(voidOrderButton());
	}
	
}