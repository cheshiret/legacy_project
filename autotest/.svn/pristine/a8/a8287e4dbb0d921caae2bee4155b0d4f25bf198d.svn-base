package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * This page will be displayed when purchase allocation privilege by individual customer, and BypassAllocationInd was true
 * Please refer to test case BypassAllocationLicense
 */
public class LicMgrBypassAllocationLicenseSaleWidget extends DialogWidget {
	private static LicMgrBypassAllocationLicenseSaleWidget _instance = null;
	
	protected LicMgrBypassAllocationLicenseSaleWidget(){	
		
	}
	
	public static LicMgrBypassAllocationLicenseSaleWidget getInstance(){
		if(null ==_instance ){
			_instance = new LicMgrBypassAllocationLicenseSaleWidget();
		}
		return _instance;
	}
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(bypassAllocationLicenseSaleTable());
	}
	
	protected Property[] bypassAllocationLicenseSaleTable() {
		return Property.concatPropertyArray(table(), ".id", "BypassAllocationLicenseSaleForm");
	}
	
	protected Property[] outfitterNumField() {
		return Property.concatPropertyArray(input("text"), ".name", new RegularExpression("PrivilegeAllocationExtensionSalesBean-\\d+\\.outfitterNum", false));
	}
	
	protected Property[] huntLocationList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("PrivilegeAllocationExtensionSalesBean-\\d+\\.outfitterAllocation", false));
	}
	
	public void setOutfitterNum(String num) {
		browser.setTextField(outfitterNumField(), num);
	}
	
	public void selectHuntLocation(String location) {
		browser.selectDropdownList(huntLocationList(), location);
	}
	
	public void clickSearch() {
		this.clickButtonByText("Search");
	}
	
	public boolean isLocationEditable() {
		return browser.checkHtmlObjectEnabled(huntLocationList());
	}
}
