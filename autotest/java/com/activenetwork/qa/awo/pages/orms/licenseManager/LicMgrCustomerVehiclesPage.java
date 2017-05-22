package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @author ssong
 * @date Dec 5, 2011
 */
public class LicMgrCustomerVehiclesPage extends LicMgrCustomerDetailsPage{
	
	private static LicMgrCustomerVehiclesPage _instance = null;
	
	protected LicMgrCustomerVehiclesPage(){
		
	}
	
	public static LicMgrCustomerVehiclesPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrCustomerVehiclesPage();
		}
		return _instance;
	}
	
	public boolean exists(){//check customer vehicles table exists
		return browser.checkHtmlObjectExists(".id","CustomerVehicleSearchList");
	}
	
	public void clickViewCustPrivVehicles(){
		browser.clickGuiObject(".class","Html.A",".text","View Customer's Previous Vehicles");
	}
	
	public List<BoatInfo> getCustomerVehicles(){
		IHtmlObject[] objs = browser.getTableTestObject(".id","CustomerVehicleSearchList");
		IHtmlTable grid = (IHtmlTable)objs[0];
		BoatInfo vp;
		List<BoatInfo> vehicles = new ArrayList<BoatInfo>();
		
		if(grid.rowCount()>1){
			for(int i=1;i<grid.rowCount();i++){
				vp = new BoatInfo();
				vp.id = grid.getCellValue(i, 0);
				vp.status = grid.getCellValue(i, 1);
				vp.type = grid.getCellValue(i, 2);
				vp.hullIdSerialNum = grid.getCellValue(i, 3);
				vp.manufacturerName = grid.getCellValue(i, 4);
				vp.modelYear = grid.getCellValue(i, 5);
				vp.regExpiry = grid.getCellValue(i, 6);
				vp.title.titleNum = grid.getCellValue(i, 7);
				vp.assoicatedVehicles = grid.getCellValue(i, 8);
				vehicles.add(vp);
			}
		}
		Browser.unregister(objs);
		return vehicles;
	}
	
	public String getColValueByID(String id, int col){
		IHtmlObject[] objs = browser.getTableTestObject(".id","CustomerVehicleSearchList");
		IHtmlTable grid = (IHtmlTable)objs[0];
		String value = "";
		if(grid.rowCount()>1){
			for(int i=1;i<grid.rowCount();i++){
				if(grid.getCellValue(i, 0).equalsIgnoreCase(id)){
					value = grid.getCellValue(i, col);
				}
			}
		}
		Browser.unregister(objs);
		return value;
	}
	
	public void clickIDMINum(String IDMINum){
		browser.clickGuiObject(".class", "Html.A", ".text", IDMINum);
	}
	
	public void clickGoToNewVehicle(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^New:.*", false));
		Property[] property = new Property[2];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", "Go");
		browser.clickGuiObject(property, true, 0, objs[0]);
		Browser.unregister(objs);
	}
	
	public void selectNewVehicleType(String type){
		String option = retrieveMatchedOption(".id", new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue|VehicleSearchCriteria-\\d+\\.vehicleTypeIDForNewVehicle",
				false), type+".*");
		browser.selectDropdownList(".id", new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue|VehicleSearchCriteria-\\d+\\.vehicleTypeIDForNewVehicle",
				false), option);
	}
	
	public void unselectVehicleTypes(){
		browser.unSelectCheckBox(".id", new RegularExpression("SearchCustomerVehicleCriteria-\\d+\\.vehicleTypes_\\d+", false), 0);
		browser.unSelectCheckBox(".id", new RegularExpression("SearchCustomerVehicleCriteria-\\d+\\.vehicleTypes_\\d+", false), 1);
		browser.unSelectCheckBox(".id", new RegularExpression("SearchCustomerVehicleCriteria-\\d+\\.vehicleTypes_\\d+", false), 2);
	}
	
	public void selectVehicleTypes(List<String> types){
		unselectVehicleTypes();
		for(String type:types){
			if(type.equalsIgnoreCase("Boat")){
				browser.selectCheckBox(".id", new RegularExpression("SearchCustomerVehicleCriteria-\\d+\\.vehicleTypes_\\d+", false), 0);
			}else if(type.equalsIgnoreCase("Motor")){
				browser.selectCheckBox(".id", new RegularExpression("SearchCustomerVehicleCriteria-\\d+\\.vehicleTypes_\\d+", false), 1);
			}else if(type.equalsIgnoreCase("Dealer")){
				browser.selectCheckBox(".id", new RegularExpression("SearchCustomerVehicleCriteria-\\d+\\.vehicleTypes_\\d+", false), 2);
			}else{
				throw new ErrorOnDataException("Unknown vehicle type: "+type);
			}
		}
	}
	
	public void clickGoToSearch(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Filter.*", false));
		Property[] property = new Property[2];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", "Go");
		browser.clickGuiObject(property, true, 0, objs[0]);
		Browser.unregister(objs);
	}
	
	/**
	 * Verify vehicle info in vehicle list
	 * @param vehicle
	 */
	public void verifyVehicleInfoInList(Vehicle vehicle){
		IHtmlObject[] objs = browser.getTableTestObject(".id","CustomerVehicleSearchList");
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find any table by id CustomerVehicleSearchList");
		IHtmlTable table = (IHtmlTable)objs[0];
		
		logger.info("Verify vehicle details info with ID/MI#"+vehicle.registration.miNum);
		
		String miNum = "";
		String status = "";
		String type = "";
		String hullID = "";
		String manufacturer = "";
		String modelYear = "";
		String regExpiry = "";
		String titleNum  = "";
		String assoicatedVehicles = "";
		
		for(int i=1; i<table.rowCount(); i++){
			miNum = table.getCellValue(i, 0);
			if(miNum.equalsIgnoreCase(vehicle.registration.miNum)){
				status = table.getCellValue(i, 1);
				type = table.getCellValue(i, 2);
				hullID = table.getCellValue(i, 3);
				manufacturer = table.getCellValue(i, 4);
				modelYear = table.getCellValue(i, 5);
				regExpiry = table.getCellValue(i, 6);
				titleNum = table.getCellValue(i, 7);
				assoicatedVehicles = table.getCellValue(i, 8).replaceAll("\\s+", "");
				break;
			}
		}
		
		MiscFunctions.compareResult("Vehicle status was not correct in customer vehicle list.",  vehicle.status, status);
		MiscFunctions.compareResult("Vehicle type was not correct in customer vehicle list.", vehicle.type, type);
		String temp = "";
		
		if(vehicle instanceof BoatInfo) {
			temp = ((BoatInfo)vehicle).hullIdSerialNum;
		} else if(vehicle instanceof MotorInfo) {
			temp = ((MotorInfo)vehicle).getSerialNum();
		}
		MiscFunctions.compareResult("Vehicle hull ID was not correct in customer vehicle list.", temp, hullID);
		
		if(vehicle instanceof BoatInfo) {
			temp = ((BoatInfo)vehicle).manufacturerName;
		} else if(vehicle instanceof MotorInfo) {
			temp = ((MotorInfo)vehicle).getManufacturerName();
		}
		MiscFunctions.compareResult("Vehicle manufacturer was not correct in customer vehicle list.", temp, manufacturer);
		
		if(vehicle instanceof BoatInfo) {
			temp = ((BoatInfo)vehicle).modelYear;
		} else if(vehicle instanceof MotorInfo) {
			temp = ((MotorInfo)vehicle).getModelYear();
		}
		MiscFunctions.compareResult("Vehicle model Year was not correct in customer vehicle list.", temp, modelYear);
		
		if(!StringUtil.isEmpty(vehicle.regExpiry)){
			MiscFunctions.compareResult("Vehicle regExpiry was not correct in customer vehicle list.", vehicle.regExpiry, regExpiry);
		}
		
		if(!StringUtil.isEmpty(vehicle.title.titleNum)){
			MiscFunctions.compareResult("Vehicle titleNum was not correct in customer vehicle list.", vehicle.title.titleNum, titleNum);
		}

		if(vehicle instanceof BoatInfo && !StringUtil.isEmpty(((BoatInfo)vehicle).assoicatedVehicles)) {
			MiscFunctions.compareResult("Vehicle assoicatedVehicles was not correct in customer vehicle list.", ((BoatInfo)vehicle).assoicatedVehicles, assoicatedVehicles);
		}
		
		logger.info("Vehicle details info are correct in cusotmer vehicle list page.");
		Browser.unregister(objs);
	}
}
