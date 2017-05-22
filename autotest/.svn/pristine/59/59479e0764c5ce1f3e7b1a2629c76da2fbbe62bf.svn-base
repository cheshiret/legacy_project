/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @Date  Jul 4, 2013
 */
public class LicMgrIncludePrdRevenueSummaryConfirmWidget extends DialogWidget {
	private static LicMgrIncludePrdRevenueSummaryConfirmWidget _instance = null;
	
	protected LicMgrIncludePrdRevenueSummaryConfirmWidget() {
		super(new RegularExpression("Include Product Revenue Summary.*", false));
	}
	
	public static LicMgrIncludePrdRevenueSummaryConfirmWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrIncludePrdRevenueSummaryConfirmWidget();
		}
		
		return _instance;
	}
	
	public void selectYesOrNoRadio(boolean isInclude){
		browser.selectRadioButton(".id",new RegularExpression("RadioButtonGroup-\\d+\\.selectedValue", false),".value",String.valueOf(isInclude));
	}
	
}
