package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 14, 2012
 */
public class LicMgrStoreSelectCountyWidget extends DialogWidget {
	
	private static LicMgrStoreSelectCountyWidget _instance = null;
	
	private LicMgrStoreSelectCountyWidget() {
		super("Select County");
	}
	
	public static LicMgrStoreSelectCountyWidget getInstance() {
		if(_instance == null) {
			_instance = new LicMgrStoreSelectCountyWidget();
		}
		
		return _instance;
	}
	
	public void selectCounty(String county) {
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.county", false), county, true, getWidget()[0]);
	}
	
	public void selectCounty(int index) {
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.county", false), index, true, getWidget()[0]);
	}
}
