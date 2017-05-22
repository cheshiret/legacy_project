package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Dec 20, 2011
 */
public class LicMgrFiscalYearConfigurationPage extends LicMgrProductConfigurationPage {
	
	private static LicMgrFiscalYearConfigurationPage _instance = null;
	
	protected LicMgrFiscalYearConfigurationPage() {}
	
	public static LicMgrFiscalYearConfigurationPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrFiscalYearConfigurationPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "fiscalYears");
	}
}
