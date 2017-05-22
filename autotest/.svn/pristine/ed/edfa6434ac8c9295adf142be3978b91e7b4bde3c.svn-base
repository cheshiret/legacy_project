package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * This page will be displayed when purchase privilege which extension type is 'Location Extension' by individual customer, and BypassAllocationInd was true
 * Please refer to test case LocationExtension
 */
public class LicMgrSelectOriginalLicenseWidget extends DialogWidget {
	private static LicMgrSelectOriginalLicenseWidget _instance = null;
	
	protected LicMgrSelectOriginalLicenseWidget(){	
		
	}
	
	public static LicMgrSelectOriginalLicenseWidget getInstance(){
		if(null ==_instance ){
			_instance = new LicMgrSelectOriginalLicenseWidget();
		}
		return _instance;
	}
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(selectOriginalLicenseTable());
	}
	
	protected Property[] selectOriginalLicenseTable() {
		return Property.concatPropertyArray(table(), ".id", "SelectOriginalLicenseUI");
	}
	
	protected Property[] originalInstanceRadioBtn() {
		return Property.concatPropertyArray(input("radio"), ".id", new RegularExpression("PrivilegeAllocationExtensionSalesBean-\\d+\\.originalInstance", false));
	}
	
	public IHtmlObject[] getSelectOriginalLicenseTable() {
		IHtmlObject[] objs = browser.getHtmlObject(selectOriginalLicenseTable());
		if(objs.length<1)
			throw new ItemNotFoundException("Could not get select original license table.");
		return objs;
	}
	
	public void selectLicense(String num) {
		logger.info("Select license "+num);
		IHtmlObject[] objs =  this.getSelectOriginalLicenseTable();
		ITable table = (ITable)objs[0];
		boolean selected = false;
		for(int i=1;i<table.rowCount();i++) {
			String content = table.getCellValue(i, 0);
			if(content.contains(num)) {
				browser.selectRadioButton(originalInstanceRadioBtn(), i);
				selected = true;
				break;
			}
		}
		if(!selected)
			throw new ItemNotFoundException("Could not find privilege with number "+num);
		Browser.unregister(objs);
	}
}
