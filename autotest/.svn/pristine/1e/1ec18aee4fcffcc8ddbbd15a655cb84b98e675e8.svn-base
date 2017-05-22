package com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAssignUnassignInventoryTypeWidget extends DialogWidget{
	
	private static LicMgrAssignUnassignInventoryTypeWidget _instance = null;
	
	protected LicMgrAssignUnassignInventoryTypeWidget(){
		
	}
	
	public static LicMgrAssignUnassignInventoryTypeWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAssignUnassignInventoryTypeWidget();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN",
				".text", "Assign/Unassign Privilege Products to/from an Inventory Type");
	}
	
	public IHtmlObject[] getPrivilegeProductDIVObject(String privielgeInfo){
		Property[] divProperty = new Property[2];
		divProperty[0] = new Property(".class","Html.SPAN");
		divProperty[1] = new Property(".text",privielgeInfo);
		
		IHtmlObject[] spanObjs = browser.getHtmlObject(divProperty);
		
		return spanObjs;
	}
	
	public void selectOrUnselectPrivilegeProductCheckBox(String privielgeInfo, boolean isSelect){		
		IHtmlObject[] spanObjs = this.getPrivilegeProductDIVObject(privielgeInfo);
		if(spanObjs.length<1){
			throw new ItemNotFoundException("Did not found privilege product SPAN object. Privilege Info = " + privielgeInfo);
		}
		Property[] checkBoxProperty = new Property[1];
		checkBoxProperty[0] = new Property(".id", new RegularExpression("^PrivilegeInventoryTypeAssignmentDialog-\\d+\\.selectedPrivileges_\\d+",false));
		
		if(isSelect){
			browser.selectCheckBox(checkBoxProperty, 0, true,spanObjs[0]);
		}else {
			browser.unSelectCheckBox(checkBoxProperty, 0, spanObjs[0]);
		}

		Browser.unregister(spanObjs);	
	}
	
	public void selectPrivielgeProductCheckBox(String privielgeInfo){
		this.selectOrUnselectPrivilegeProductCheckBox(privielgeInfo, true);
	}
	
	public void unSelectPrivilegeProductCheckBox(String privielgeInfo){
		this.selectOrUnselectPrivilegeProductCheckBox(privielgeInfo, false);
	}
	
	public boolean checkPrivilegeProductWhetherSelected(String privielgeInfo){
		IHtmlObject[] divObjs = this.getPrivilegeProductDIVObject(privielgeInfo);
		if(divObjs.length<1){
			throw new ItemNotFoundException("Did not found privilege product div object. Privilege Info = " + privielgeInfo);
		}
		
		Property[] checkBoxProperty = new Property[1];
		checkBoxProperty[0] = new Property(".id", new RegularExpression("^PrivilegeInventoryTypeAssignmentDialog-\\d+\\.selectedPrivileges_\\d+",false));
		IHtmlObject[] checkBoxObjs = browser.getCheckBox(checkBoxProperty, divObjs[0]);
		if(checkBoxObjs.length<1){
			
		}
		ICheckBox priviCheckBox = (ICheckBox)checkBoxObjs[0];
		
		boolean selected = priviCheckBox.isSelected();
		Browser.unregister(checkBoxObjs);
		Browser.unregister(divObjs);
		return selected;
	}

}
