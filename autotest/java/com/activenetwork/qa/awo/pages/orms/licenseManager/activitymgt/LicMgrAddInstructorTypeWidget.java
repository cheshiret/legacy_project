package com.activenetwork.qa.awo.pages.orms.licenseManager.activitymgt;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 13, 2014
 */
public class LicMgrAddInstructorTypeWidget extends DialogWidget{
	static class SingletonHolder {
		protected static LicMgrAddInstructorTypeWidget _instance = new LicMgrAddInstructorTypeWidget();
	}

	protected LicMgrAddInstructorTypeWidget() {
		super("Add Instructor Type");
	}

	public static LicMgrAddInstructorTypeWidget getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] addInstructorType(){
		return Property.concatPropertyArray(table(), ".text", new RegularExpression("^Add Instructor Type.*", false));
	}
	
	protected Property[] name(){
		return Property.toPropertyArray(".id", new RegularExpression("InstructorTypeView-\\d+\\.name", false));
	}
	
	protected Property[] ok(){
		return Property.concatPropertyArray(a(), ".text", "OK");
	}
	
	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".text", "Cancel");
	}
	/** Page Object Property Definition END */
	
	public void setInstructorType(String instructorType){
		browser.setTextField(name(), instructorType);
	}
	public void clickOK(){
		browser.clickGuiObject(Property.atList(addInstructorType(), ok()));
	}
	
	public void clickCancel(){
		browser.clickGuiObject(Property.atList(addInstructorType(), cancel()));
	}
	
	public void addInstructorType(String instructorType){
		setInstructorType(instructorType);
		clickOK();
		ajax.waitLoading();
	}
}

