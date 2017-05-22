package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddVehicleTypeManufacturerWidget extends DialogWidget{
	private static LicMgrAddVehicleTypeManufacturerWidget _instance = null;

	protected LicMgrAddVehicleTypeManufacturerWidget() {
          super("Add New Vehicle Type Manufacturer");
	}

	public static LicMgrAddVehicleTypeManufacturerWidget getInstance() {
		if (_instance == null) {
			_instance = new LicMgrAddVehicleTypeManufacturerWidget();
		}
		return _instance;
	}
	/**
	 * set MIC.
	 * @param MIC nubmer
	 * VehicleManufacturerView-128687146.mic
	 */
	public void setMIC(String mic){
		if(mic!=null)
		{
			browser.setTextField(".id", new RegularExpression("VehicleManufacturerView-\\d+\\.mic",false), mic);
		}
	}
	/**
	 * set manufacturer name
	 * @param name
	 */
	public void setManufacturerName(String name){
		if(name!=null)
		{
			browser.setTextField(".id", new RegularExpression("VehicleManufacturerView-\\d+\\.name",false), name);
		}
	}
	/**
	 * set manufacturer print name
	 * @param pirnt name
	 */
	public void setManufacturerPrintName(String name){
		if(name!=null)
		{
			browser.setTextField(".id", new RegularExpression("VehicleManufacturerView-\\d+\\.printName",false), name);
		}
	}
	
	public String getErrorMsg(){
		String msg = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		if(objs.length>0)
		{
			msg = objs[0].text();
		}
		Browser.unregister(objs);
		return msg;
	}
	
	public void setupManufacturerInfo(String mic, String name, String printName) {
		setMIC(mic);
		setManufacturerName(name);
		setManufacturerPrintName(printName);
	}
}
