package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 9, 2014
 */
public class LicMgrAddFacilityProductTypeWidget extends DialogWidget{
	static class SingletonHolder {
		protected static LicMgrAddFacilityProductTypeWidget _instance = new LicMgrAddFacilityProductTypeWidget();
	}

	protected LicMgrAddFacilityProductTypeWidget() {
		super("Add Facility Product Type");
	}

	public static LicMgrAddFacilityProductTypeWidget getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	
	protected Property[] addFacilityPrdGroupBar(){
		return Property.concatPropertyArray(table(), ".id", "addFacilityProductGroupBar");
	}
	
	protected Property[] facilityPrdGroupName(){
		return Property.toPropertyArray(".id", new RegularExpression("ProductGroupView-\\d+.productGroupName", false));
	}
	
	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".text", "OK");
	}
	
	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".text", "Cancel");
	}
	/** Page Object Property Definition END */
	
	public void setFacilityPrdType(String facilityPrdGroupName){
		browser.setTextField(facilityPrdGroupName(), facilityPrdGroupName);
	}
	
	public void clickOK(){
		browser.clickGuiObject(Property.atList(addFacilityPrdGroupBar(),ok()));
	}
	
	public void clickCancel(){
		browser.clickGuiObject(Property.atList(addFacilityPrdGroupBar(), cancel()));
	}
}


