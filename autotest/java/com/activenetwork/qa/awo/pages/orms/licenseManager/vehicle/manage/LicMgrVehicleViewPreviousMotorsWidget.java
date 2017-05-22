package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @author ssong
 * @date Dec 6, 2011
 */
public class LicMgrVehicleViewPreviousMotorsWidget extends DialogWidget{
	private static LicMgrVehicleViewPreviousMotorsWidget _instance = null;
	
	protected LicMgrVehicleViewPreviousMotorsWidget(){
		
	}
	
	public static LicMgrVehicleViewPreviousMotorsWidget getInstance(){
		if(_instance == null){
			_instance = new LicMgrVehicleViewPreviousMotorsWidget();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class","Html.DIV",".text","View Previous Associated Vehicles");
	}
	
	public void clickOk(){
		browser.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public List<MotorInfo> getVehiclePrevMotors(){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^ID Status Serial# Manufacturer.*",false));
		IHtmlTable grid = (IHtmlTable)objs[1];
		MotorInfo motor;
		List<MotorInfo> motors = new ArrayList<MotorInfo>();
		
		for(int i=1;i<grid.rowCount();i++){
			motor = new MotorInfo();
			motor.id = grid.getCellValue(i, 0);
			motor.status = grid.getCellValue(i, 1);
			motor.setSerialNum(grid.getCellValue(i, 2));
			motor.setManufacturerName(grid.getCellValue(i, 3));
			motor.setModelYear(grid.getCellValue(i, 4));
			motor.setHorsePower(grid.getCellValue(i, 5));
			motor.title.titleNum = grid.getCellValue(i, 6);
			motor.setAssignmentDetails(grid.getCellValue(i, 7));
			motor.setRemoveDetails(grid.getCellValue(i, 8));
			motors.add(motor);
		}
		Browser.unregister(objs);
		return motors;
	}
}
