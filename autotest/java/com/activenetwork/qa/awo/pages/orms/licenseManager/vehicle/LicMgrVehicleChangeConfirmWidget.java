/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

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



public class LicMgrVehicleChangeConfirmWidget extends DialogWidget {

	
	private static LicMgrVehicleChangeConfirmWidget instance = null;

	private LicMgrVehicleChangeConfirmWidget() {
		super("Please Confirm");
	}

	public static LicMgrVehicleChangeConfirmWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrVehicleChangeConfirmWidget();
		}
		return instance;
	}
	


}
