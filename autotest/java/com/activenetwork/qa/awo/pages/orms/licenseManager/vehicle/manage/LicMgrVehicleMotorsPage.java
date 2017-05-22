package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @author ssong
 * @date Dec 6, 2011
 */
public class LicMgrVehicleMotorsPage extends LicMgrVehicleDetailPage{
	
	private static LicMgrVehicleMotorsPage _instance = null;
	
	protected LicMgrVehicleMotorsPage(){
		
	}
	
	public static LicMgrVehicleMotorsPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrVehicleMotorsPage();
		}
		return _instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(".class","Html.A",".text","View Previous Motors");
	}
	
	public void clickViewPrevMotors(){
		browser.clickGuiObject(".class","Html.A",".text","View Previous Motors");
	}
	
	public List<MotorInfo> getVehicleMotors(){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^ID Status Serial#.*",false));
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find table by text ID Status Serial#...");
		IHtmlTable grid = (IHtmlTable)objs[0];

		MotorInfo motor;
		List<MotorInfo> motors = new ArrayList<MotorInfo>();
		for(int i=1;i<grid.rowCount();i++){
			motor = new MotorInfo();
			motor.id = grid.getCellValue(i, 1);
			motor.status = grid.getCellValue(i, 2);
			motor.setSerialNum(grid.getCellValue(i, 3));
			motor.setManufacturerName(grid.getCellValue(i, 4));
			motor.setModelYear(grid.getCellValue(i, 5));
			motor.setHorsePower(grid.getCellValue(i, 6));
			motor.title.titleNum = grid.getCellValue(i, 7);
			motor.setAssignmentDetails(grid.getCellValue(i, 8));
			motors.add(motor);
		}
		Browser.unregister(objs);
		return motors;
	}
	/**
	 * get motor id
	 * @param motor
	 * @return
	 */
	public String getMotorBoatId(MotorInfo motor){
		String id = null;
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^ID Status Serial#.*",false));
		IHtmlTable grid = (IHtmlTable)objs[0];
		for(int i =1;i<grid.rowCount();i++){
			if(grid.getCellValue(i, 2).equals(motor.status)
					&& grid.getCellValue(i, 3).equals(motor.getSerialNum())
					&& grid.getCellValue(i, 4).equalsIgnoreCase(motor.getManufacturerName())
					&& grid.getCellValue(i,5).equals(motor.getModelYear())
					&& (StringUtil.compareNumStrings(grid.getCellValue(i, 6), motor.getHorsePower())==0)
					&& grid.getCellValue(i, 7).equals(motor.title.titleNum)) {

			//			&& grid.getCellValue(i, 8).equals(motor.title.motorValue)){
				
				id = grid.getCellValue(i, 1);
				break;
			}
		}
		if(id == null) {
			throw new ItemNotFoundException("Can't find motor id which motor serial num is: " + motor.getSerialNum());
		}
		Browser.unregister(objs);
		return id;
	}
	/**
	 * check motor exist in motor list or not.
	 * @param motorId
	 * @return
	 */
	public boolean checkMotorExist(String motorId){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^ID Status Serial#.*",false));
		IHtmlTable grid = (IHtmlTable)objs[0];
		boolean isExist = grid.getColumnValues(1).contains(motorId);
		Browser.unregister(objs);
		return isExist;
	}
	
	public boolean verifyVehicleMotorInfo(MotorInfo motor){
		List<MotorInfo> motors = this.getVehicleMotors();
		Boolean result = true;
		if (null != motors && motors.size() > 0) {
			String status = "";
			String serialNum = "";
			String manufacturer = "";
			String modelYear = "";
			String horsePower = "";
			String titleNum = "";
			String assignDetails = "";

			for (int i = 0; i < motors.size(); i++) {
				serialNum = motors.get(i).getSerialNum();
				if (serialNum.equalsIgnoreCase(motor.getSerialNum())) {
					status = motors.get(i).status;
					manufacturer = motors.get(i).getManufacturerName();
					modelYear = motors.get(i).getModelYear();
					horsePower = motors.get(i).getHorsePower().contains(".") ? motors
							.get(i).getHorsePower().substring(0,
							motors.get(i).getHorsePower().indexOf(".")) : motors
							.get(i).getHorsePower();
					titleNum = motors.get(i).title.titleNum;
					assignDetails = motors.get(i).getAssignmentDetails();
					break;
				}
			}
			result &= MiscFunctions.compareResult("Motor status",motor.status,status);
			result &= MiscFunctions.compareResult("Motor manufacturer",motor.getManufacturerName(),manufacturer);
			result &= MiscFunctions.compareResult("Motor modelYear",motor.getModelYear(),modelYear);
			result &= MiscFunctions.compareResult("Motor horsePower",motor.getHorsePower(),horsePower);
			
			if (!StringUtil.isEmpty(motor.title.titleNum)){
				result &= MiscFunctions.compareResult("Motor titleNum was not correct.",motor.title.titleNum,titleNum);
			}

			if (!StringUtil.isEmpty(motor.getAssignmentDetails())) {
				result &= MiscFunctions.compareResult("Motor assignDetails was not correct.",motor.getAssignmentDetails(),assignDetails);
			}
		} else {
			throw new ErrorOnPageException(
					"Could not find any vehicle motor info on page.");
		}

		return result;
	}
	
	/**
	 * click add motor to boat.
	 */
	public void clickAddMotorToBoat(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Motor to Boat");
	}
	
	/**
	 * select motor check box.
	 * @param index
	 */
	public void selectMotorCheckbox(int index){
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems",false), index);
	}
	
	/**
	 * select motor checke box.
	 * @param id
	 */
	public void selectMotorCheckBox(String id){
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^ID Status Serial#.*",false));
		IHtmlTable grid = (IHtmlTable)objs[0];
		for(int i=0;i<grid.rowCount();i++){
			System.out.println(grid.getCellValue(i,1));
			if(grid.getCellValue(i,1).equals(id)){
				this.selectMotorCheckbox(i-1);
			}
		}
		Browser.unregister(objs);
	}
	/**
	 * click remove motor from boat.
	 */
	public void clickRemoveMotorFromBoat(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove Motor from Boat");
	}
	/**
	 * remove motor.
	 * @param id
	 */
	public void removeMotor(String id){
		this.selectMotorCheckBox(id);
		this.clickRemoveMotorFromBoat();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * click Motor identified by id
	 * @param id
	 */
	public void clickMotorId(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id);
	}
}
