/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @Date  Jan 23, 2014
 */
public class InvMgrAddPOSPassNumberWidget extends DialogWidget {
	
	private static InvMgrAddPOSPassNumberWidget instance = null;

	private InvMgrAddPOSPassNumberWidget() {
		super("Add Pass Number");
	}

	public static InvMgrAddPOSPassNumberWidget getInstance() {
		if (instance == null) {
			instance = new InvMgrAddPOSPassNumberWidget();
		}
		return instance;
	}
	
	protected Property[] passName(){
		return Property.concatPropertyArray(select(),".id",new RegularExpression("ProductSerialNumberView-\\d+\\.product",false));
	}
	
	protected Property[] passNumProp(){
		return Property.concatPropertyArray(select(),".id",new RegularExpression("ProductSerialNumberView-\\d+\\.serialNumber",false));
	}
	
	protected Property[] apply(){
		return Property.concatPropertyArray(a(),".text","Apply");
	}
	
	protected Property[] ok(){
		return Property.concatPropertyArray(a(),".text","OK");
	}
	
	public void clickApply(){
		browser.clickGuiObject(apply());
	}
	
	public void clickOk(){
		browser.clickGuiObject(ok());
	}
	
	public void selectPassName(String pos){
		browser.selectDropdownList(passName(), pos);
	}
	
	public void setPassNumber(String passNum){
		browser.setTextField(passNumProp(), passNum);
	}
	
	public void setupPassNum(String passName,String passNum){
		selectPassName(passName);
		setPassNumber(passNum);
		clickApply();
		ajax.waitLoading();
	}
	
}
