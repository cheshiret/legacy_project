package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrDonationDialogWidget extends DialogWidget {
	
	private static LicMgrDonationDialogWidget instance = null;

	private LicMgrDonationDialogWidget() {
		super(new RegularExpression("Would you like to make a donation.*",false));
	}

	public static LicMgrDonationDialogWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrDonationDialogWidget();
		}
		return instance;
	}
}
