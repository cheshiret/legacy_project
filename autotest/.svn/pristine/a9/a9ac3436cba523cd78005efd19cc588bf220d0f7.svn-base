package com.activenetwork.qa.awo.pages.orms.licenseManager.pos;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA-qchen
 * @Date  May 27, 2011
 */
public class LicMgrConsumableProductFiscalYearPage extends LicMgrConsumableProductDetailsPage {
	
	private static LicMgrConsumableProductFiscalYearPage _instance = null;
	
	private LicMgrConsumableProductFiscalYearPage() {}
	
	public static LicMgrConsumableProductFiscalYearPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrConsumableProductFiscalYearPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Fiscal Year");
	}
	
	public void checkShowCurrentRecordsOnly() {
		browser.selectCheckBox(".id", new RegularExpression("ProductFiscalYearFilter-\\d+\\.showCurrentRecordsOnly", false));
	}
	
	public void uncheckShowCurrentRecordsOnly() {
		browser.unSelectCheckBox(".id", new RegularExpression("ProductFiscalYearFilter-\\d+\\.showCurrentRecordsOnly", false));
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", new RegularExpression("ProductFiscalYearFilter-\\d+\\.status", false), status);
	}
	
	public void selectFiscalYear(String fiscalYear) {
		browser.selectDropdownList(".id", new RegularExpression("ProductFiscalYearFilter-\\d+\\.fiscalYear", false), fiscalYear);
	}
	
	public void selectLocationClass(String locationClass) {
		browser.selectDropdownList(".id", new RegularExpression("ProductFiscalYearFilter-\\d+\\.locationClassID", false), locationClass);
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
}
