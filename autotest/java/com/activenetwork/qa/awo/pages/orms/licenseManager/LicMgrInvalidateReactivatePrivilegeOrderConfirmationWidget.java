package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * ScriptName: LicMgrInvalidatePrivilegeOrderConfirmationWidget
 * Description:
 * @date: Jan 17, 2011
 * @author qchen
 *
 */
public class LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget extends DialogWidget {
	private static LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget _instance = null;
	
	protected LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget() {
		
	}
	
	public static LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrInvalidateReactivatePrivilegeOrderConfirmationWidget();
		}
		
		return _instance;
	}
	
	/**
	 * Select a reason for invalidating/reactivating the current privilege order
	 * @param reason
	 */
	public void selectReason(String reason) {
		if(null != reason && reason.length() > 0) {
			browser.selectDropdownList(".id", new RegularExpression("[PrivilegeOrderActionUIModel|PrivilegeInstanceStatusChangeUIModel]-\\d+\\.selectedReason", false), reason, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("[PrivilegeOrderActionUIModel|PrivilegeInstanceStatusChangeUIModel]-\\d+\\.selectedReason", false), 1, true);
		}
	}
	
	/**
	 * Set a note for invalidating/reactivating the current privilege order
	 * @param note
	 */
	public void setNote(String note) {
		if(StringUtil.isEmpty(note)) {
			note="Automation test";
		}
		browser.setTextArea(".id", new RegularExpression("[PrivilegeOrderActionUIModel|PrivilegeInstanceStatusChangeUIModel]-\\d+\\.notes", false), note);
	}
	
	/**
	 * Set the invalidate/reactive reason and note
	 * @param reason
	 * @param note
	 */
	public void setInfo(String reason, String note) {
		selectReason(reason);
		setNote(note);
		clickOK();
		ajax.waitLoading();
	}
}
