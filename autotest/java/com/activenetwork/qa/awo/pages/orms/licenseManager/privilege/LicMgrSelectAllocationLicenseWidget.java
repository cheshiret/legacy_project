package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @Description: Privilege Select Authorization Record Widget, displayed when purchase an authorized privilege. 
 * @author Jane Wang
 * @Date  Apr 23, 2014
 */
public class LicMgrSelectAllocationLicenseWidget extends
		DialogWidget {
	private static LicMgrSelectAllocationLicenseWidget _instance = null;
	
	protected LicMgrSelectAllocationLicenseWidget() {
		
	}
	
	public static LicMgrSelectAllocationLicenseWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrSelectAllocationLicenseWidget();
		}
		
		return _instance;
	}
	
	/** Page Object Property Definition Begin*/
	protected Property[] allocationLicenseTable() {
		return Property.concatPropertyArray(table(), ".id", new RegularExpression("PrivilegeProductForTransaction-\\d+\\.selectedAllocationLicense", false));
	}
	
	protected Property[] allocationLicenseRadionBtn() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("PrivilegeProductForTransaction-\\d+\\.selectedAllocationLicense\\d+", false));
	}
	/** Page Object Property Definition END*/
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(allocationLicenseTable());
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	private IHtmlObject[] getAllocationLicenseTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(allocationLicenseTable());
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find Allocation License table object.");
		}
		
		return objs;
	}
	
	public void selectAllocationLicenseByAuthNum(String authNum) {
		IHtmlObject tableObjs[] = this.getAllocationLicenseTableObject();
		IHtmlTable table = (IHtmlTable)tableObjs[0];
		int rowIndex = -1;
		for(int i=0; i<table.rowCount(); i++) {
			String content = table.getCellValue(i, 0);
			if(content.contains(authNum)) {
				rowIndex = i;
				break;
			}
		}
		Browser.unregister(tableObjs);
		if(rowIndex<0)
			throw new ItemNotFoundException("Authorized number was not found.");
			
		browser.selectRadioButton(allocationLicenseRadionBtn(), rowIndex);	
	}
}
