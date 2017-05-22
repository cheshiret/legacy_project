/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.cust;

import java.util.List;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description:This page dedicate for license/fiscal year select widget after click 'Print customer record' button in customer detail page
 * 
 * @author ssong
 * @Date  Jun 17, 2014
 */
public class LicMgrCustomerLicenseYearWidget extends DialogWidget{

	private static LicMgrCustomerLicenseYearWidget _instance = null;
	
	protected LicMgrCustomerLicenseYearWidget() {
		
	}
	
	public static LicMgrCustomerLicenseYearWidget getInstance () {
		if (null == _instance) {
			_instance = new LicMgrCustomerLicenseYearWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists()&& browser.checkHtmlObjectExists(licenseYearCountDropdown());
	}
	
	protected Property[] licenseYearCountDropdown(){
		return Property.addToPropertyArray(select(), ".id", "customerReportLicenseFiscalYear");
	}
	
	public void selectLicenseYearCount(String licenseYearCount){
		browser.selectDropdownList(licenseYearCountDropdown(), licenseYearCount);
	}
	
	public List<String> getLicenseYearCount(){
		return browser.getDropdownElements(licenseYearCountDropdown());
	}
}
