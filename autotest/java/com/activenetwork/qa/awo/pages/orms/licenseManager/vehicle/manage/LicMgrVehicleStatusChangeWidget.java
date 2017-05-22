/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author pzhu
 * @Date  May 30, 2012
 */

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;


public class LicMgrVehicleStatusChangeWidget extends DialogWidget {

	
	private static LicMgrVehicleStatusChangeWidget instance = null;

	private LicMgrVehicleStatusChangeWidget() {
		super("Vehicle Status Change");
	}

	public static LicMgrVehicleStatusChangeWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrVehicleStatusChangeWidget();
		}
		return instance;
	}

	public void setStatusChangeNote(String note)
	{
		browser.setTextArea(".id", new RegularExpression("VehicleStatusChangeDialog-\\d+\\.note",false), note);
	}
	

}
