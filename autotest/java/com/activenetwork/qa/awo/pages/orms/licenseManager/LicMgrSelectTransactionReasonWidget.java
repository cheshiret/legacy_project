package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class LicMgrSelectTransactionReasonWidget extends DialogWidget{
	private static LicMgrSelectTransactionReasonWidget _instance = null;
	
	protected LicMgrSelectTransactionReasonWidget() {
	}
	
	public static LicMgrSelectTransactionReasonWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrSelectTransactionReasonWidget();
		}
		return _instance;
	}
	
	public void selectReason(String reason){
		if (StringUtil.isEmpty(reason)) {
			browser.selectDropdownList(".id", new RegularExpression("TransactionReasonView-\\d+\\.id",false), 1, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("TransactionReasonView-\\d+\\.id",false), reason);
		}
	}
}
