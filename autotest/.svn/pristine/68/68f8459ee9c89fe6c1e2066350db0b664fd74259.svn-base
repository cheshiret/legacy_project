/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.callManager;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang7
 * @Date  Mar 2, 2012
 */
public class CallMgrVehicleRegistrationConfirmVehiclePage extends
		CallMgrCommonTopMenuPage {
	static private CallMgrVehicleRegistrationConfirmVehiclePage _instance = null;
	
	static public CallMgrVehicleRegistrationConfirmVehiclePage getInstance(){
		if (null == _instance) {
			_instance = new CallMgrVehicleRegistrationConfirmVehiclePage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return checkLastTrailValueIs("Confirm Vehicle");
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
}
