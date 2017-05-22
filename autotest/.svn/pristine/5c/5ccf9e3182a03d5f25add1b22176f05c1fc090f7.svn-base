/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/** 
 * @author ssong
 * @Date  Jan 24, 2014
 */
public class InvMgrPOSInventoryFieldAllocationPage extends InvMgrPOSInventoryAllocationCommonPage {
	
	private static InvMgrPOSInventoryFieldAllocationPage _instance = null;
	
	private InvMgrPOSInventoryFieldAllocationPage() {}
	
	public static InvMgrPOSInventoryFieldAllocationPage getInstance() {
		if(_instance == null) {
			_instance = new InvMgrPOSInventoryFieldAllocationPage();
		}
		
		return _instance;
	}
	
	protected Property[] facilityInput(){
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("FieldPOSInvSearchCriteria-\\d+.facilityName", false));
	}
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(facilityInput());
	}
	
	
	
}
