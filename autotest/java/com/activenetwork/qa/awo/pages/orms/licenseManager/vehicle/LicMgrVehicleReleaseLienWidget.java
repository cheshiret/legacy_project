package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @Description: Release vehicle title lien
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang8
 * @Date  Jun 8, 2012
 */
public class LicMgrVehicleReleaseLienWidget extends DialogWidget{
	private static LicMgrVehicleReleaseLienWidget _instance = null;

	protected LicMgrVehicleReleaseLienWidget() {
          super("Release Lien");
	}

	public static LicMgrVehicleReleaseLienWidget getInstance() {
		if (_instance == null) {
			_instance = new LicMgrVehicleReleaseLienWidget();
		}
		return _instance;
	}
	/**
	 * set date of release
	 * @param releaseDate
	 */
	public void setDateOfRelease(String releaseDate){
		browser.setTextField(".id", new RegularExpression("TitleLienView-\\d+\\.releaseDate_ForDisplay",false), releaseDate);
	}
	
	
}
