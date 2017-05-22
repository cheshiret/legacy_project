package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrValidFromDeteTimeWidget extends DialogWidget {
	private static LicMgrValidFromDeteTimeWidget _instance = null;

	protected LicMgrValidFromDeteTimeWidget() {
	}

	public static LicMgrValidFromDeteTimeWidget getInstance() {
		if (null == _instance) {
			_instance = new LicMgrValidFromDeteTimeWidget();
		}
		return _instance;
	}
	
	/**Set the Valid From Date Time*/
	public void setValidFromDateTime(String date){
		browser.setTextField(".id", new RegularExpression("ValidFromDate-\\d+.dateTime_ForDisplay",false), date);
	}
	
	/**Verify exist*/
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression("Privilege Valid From Date/Time.*", false));
	}
}
