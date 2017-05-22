package com.activenetwork.qa.awo.pages.orms.licenseManager.fileimports;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

/**
 * @Description: Import Confirm widget, shown after import file and click OK, including Import Unlocked Privileges, .
 * @author Lesley Wang
 * @Date  Aug 5, 2013
 */
public class LicMgrImportConfirmWidget extends DialogWidget {
	private static LicMgrImportConfirmWidget instance = null;

	private LicMgrImportConfirmWidget() {
		super("Import ((Unlocked Privileges)|(Awarded Licences)|Points)");
	}
	
	public static LicMgrImportConfirmWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrImportConfirmWidget();
		}
		return instance;
	}
}
