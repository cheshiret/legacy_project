package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * This page will be displayed when purchase privilege which extension type is 'License Extension' by individual customer, and BypassAllocationInd was true
 * Please refer to test case LicenseExtension
 */
public class LicMgrLicenseExtensionPrivilegeSaleWidget extends DialogWidget {
	private static LicMgrLicenseExtensionPrivilegeSaleWidget _instance = null;
	
	protected LicMgrLicenseExtensionPrivilegeSaleWidget(){	
		
	}
	
	public static LicMgrLicenseExtensionPrivilegeSaleWidget getInstance(){
		if(null ==_instance ){
			_instance = new LicMgrLicenseExtensionPrivilegeSaleWidget();
		}
		return _instance;
	}
	
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(licenseExtensionPrivilegeSaleTable());
	}
	
	protected Property[] licenseExtensionPrivilegeSaleTable() {
		return Property.concatPropertyArray(table(), ".id", "LicenseExtensionPrivilegeSaleUIForm");
	}
	
	protected Property[] outfitterNumField() {
		return Property.concatPropertyArray(input("text"), ".name", new RegularExpression("PrivilegeAllocationExtensionSalesBean-\\d+\\.outfitterNum", false));
	}
	
	protected Property[] huntLocationList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("PrivilegeAllocationExtensionSalesBean-\\d+\\.outfitterAllocation", false));
	}
	
	protected Property[] originalInstanceList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("PrivilegeAllocationExtensionSalesBean-\\d+\\.originalInstance", false));
	}
	
	protected Property[] extendedPrivilegeProductList() {
		return Property.concatPropertyArray(select(), ".id", new RegularExpression("PrivilegeAllocationExtensionSalesBean-\\d+\\.extendedPrivilegeProduct", false));
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
	
	public String getOriginalInstanceListValue() {
		return browser.getDropdownListValue(originalInstanceList(), 0);
	}
	
	public String getExtendedPrivilegeProductListValue() {
		return browser.getDropdownListValue(extendedPrivilegeProductList(), 0);
	}
	
	public boolean isOriginalInstanceListEditable() {
		return browser.checkHtmlObjectEnabled(originalInstanceList());
	}
	
	public boolean isExtendedPrivilegeProductListEditable() {
		return browser.checkHtmlObjectEnabled(extendedPrivilegeProductList());
	}
	
	public void selectOriginalInstance(String instance) {
		browser.selectDropdownList(originalInstanceList(), instance);
	}
	
	public void selectExtendedPrivilegeProduct(String prd) {
		browser.selectDropdownList(extendedPrivilegeProductList(), prd);
	}
	
	public List<String> getOriginalInstanceList() {
		return browser.getDropdownElements(originalInstanceList());
	}
	
	public List<String> getExtendedPrivilegeProductList() {
		return browser.getDropdownElements(extendedPrivilegeProductList());
	}
}
