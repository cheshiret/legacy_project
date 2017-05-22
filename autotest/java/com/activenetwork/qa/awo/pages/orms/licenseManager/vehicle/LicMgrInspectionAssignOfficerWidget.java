package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * This page is used to assign officer in inspections detail page
 * @author Pchen
 * @date Aug 10, 2012
 */

public class LicMgrInspectionAssignOfficerWidget extends DialogWidget {
	private static LicMgrInspectionAssignOfficerWidget _instance = null;

	protected LicMgrInspectionAssignOfficerWidget() {
		super("Edit Lien");
	}

	public static LicMgrInspectionAssignOfficerWidget getInstance() {
		if (_instance == null) {
			_instance = new LicMgrInspectionAssignOfficerWidget();
		}
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(
						".id", new RegularExpression(
								"VehicleInspectionInstanceView-\\d+\\.vehicleInspection.countyDistrict.district.name",
								false));
	}

	public void clickOk() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", 1);
	}

	public void selectOfficer(String officer) {
		browser.selectDropdownList(".id", new RegularExpression(
				"VehicleInspectionInstanceView-\\d+\\.vehicleInspection\\.assignedOfficer", false),
				officer);
	}
}
