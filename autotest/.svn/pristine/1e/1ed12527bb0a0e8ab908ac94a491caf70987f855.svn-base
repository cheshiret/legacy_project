/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang8
 * @Date  Jun 20, 2012
 */
public class LicMgrAddMotorWidget extends DialogWidget{
	private static LicMgrAddMotorWidget _instance = null;

	protected LicMgrAddMotorWidget() {
          super("Add Motor to Boat Dialog");
	}

	public static LicMgrAddMotorWidget getInstance() {
		if (_instance == null) {
			_instance = new LicMgrAddMotorWidget();
		}
		return _instance;
	}
	/**
	 * set serial 
	 * @param serial
	 */
	public void setSerial(String serial){
		browser.setTextField(".id", new RegularExpression("VehicleDetailView-\\d+\\.serialNum",false), serial,this.getWidget()[0]);
	}
	/**
	 * set Manufacturer Name
	 * @param factName
	 */
	public void setManufacturerName(String factName){
	    browser.setTextField(".id", new RegularExpression("VehicleDetailView-\\d+\\.manufacturerName",false), factName,this.getWidget()[0]);
	}
	/**
	 * set model year.
	 * @param modelYear
	 */
	public void setModelYear(String modelYear){
		browser.setTextField(".id", new RegularExpression("VehicleDetailView-\\d+\\.modelYear",false), modelYear,this.getWidget()[0]);
	}
	/**
	 * set horse Powser.
	 * @param horsePower
	 */
	public void setHorsePower(String horsePower){
		browser.setTextField(".id", new RegularExpression("VehicleDetailView-\\d+\\.horsePower",false), horsePower,this.getWidget()[0]);
	}
	/*
	 * select motor fuel
	 */
	public void selectMotorFuel(String motorFuel){
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.LABEL",".text", "Motor Fuel");
		String id = objs[0].getProperty("for");
		
		if(motorFuel.equals("")){
			browser.selectDropdownList(".id", id, 0);
		}else{
			browser.selectDropdownList(".id", id, motorFuel);
		}
		
		Browser.unregister(objs);
		
	}
	
	/**
	 * select customer exist motors info.
	 * @param motorInfo
	 */
	public void selectCustExistMotors(String motorInfo){
		Property[] property = new Property[1];
		property[0] = new Property(".id", new RegularExpression("VehicleDetailView-\\d+\\.id",false));
		browser.selectDropdownList(property, motorInfo, false, this.getWidget()[0]);
	}
	/**
	 * select customer's existing motors radio box.
	 */
	public void selectCustExistMotorRadioBox(){
		Property[] property = new Property[1];
		property[0] = new Property(".id", new RegularExpression("AddChildVehiclesUI-\\d+\\.selectedSection",false));
		browser.selectRadioButton(property, false, 0, this.getWidget()[0]);
	}
	/**
	 * select customer add new motor radio box.
	 */
	public void selectAddNewMotorRadioBox(){
		Property[] property = new Property[1];
		property[0] = new Property(".id", new RegularExpression("AddChildVehiclesUI-\\d+\\.selectedSection",false));
		browser.selectRadioButton(property, false, 1, this.getWidget()[0]);
	}
	/**
	 * set Motor Details info.
	 * @param motor
	 */
   public void setMotorDetialsInfo(MotorInfo motor){
	   if(motor.getCustomerExistingMotor() != null && motor.getCustomerExistingMotor().length() > 0){
		   this.selectCustExistMotorRadioBox();
		   this.selectCustExistMotors(motor.getCustomerExistingMotor());//update by pzhu
	   }else{
		   this.selectAddNewMotorRadioBox();
		   this.setSerial(motor.getSerialNum());
		   this.setManufacturerName(motor.getManufacturerName());
		   ajax.waitLoading();
		   //To Do need add the new manufacturer Name;
		   this.setModelYear(motor.getModelYear());
		   this.setHorsePower(motor.getHorsePower());
		   this.selectMotorFuel(motor.getMotorFuel());
	   }
   }
}
