package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

public class LicMgrRemoveIndentifierConfirmationDialog extends DialogWidget {

	private static LicMgrRemoveIndentifierConfirmationDialog instance = null;

	private LicMgrRemoveIndentifierConfirmationDialog() {
		super("Remove Identifier\\(s\\)");
	}

	public static LicMgrRemoveIndentifierConfirmationDialog getInstance() {
		if (instance == null) {
			instance = new LicMgrRemoveIndentifierConfirmationDialog();
		}
		return instance;
	}

	public String getMessage() {
		return browser.getObjectText(".class","Html.SPAN",".className","messagebox_text").trim();
	}
}
