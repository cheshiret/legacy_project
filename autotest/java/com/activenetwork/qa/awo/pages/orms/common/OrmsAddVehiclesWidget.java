package com.activenetwork.qa.awo.pages.orms.common;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer.VehicleInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Feb 19, 2014
 */
public class OrmsAddVehiclesWidget extends DialogWidget {
	private static OrmsAddVehiclesWidget _instance = null;
	
	private OrmsAddVehiclesWidget() {
		super("Add Vehicles");
	}
	
	public static OrmsAddVehiclesWidget getInstance() {
		if(_instance == null) {
			_instance = new OrmsAddVehiclesWidget();
		}
		
		return _instance;
	}

	private Property[] numberOfVehicles() {
		return Property.toPropertyArray(".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue", false));
	}
	
	private String prefix = "campingInfoContainer_Vehicle_\\d+_vehicle-row\\:";
	
	private Property[] plate(int index) {
		return Property.toPropertyArray(".id", new RegularExpression(prefix + index + "_license", false));
	}
	
	private Property[] state(int index) {
		return Property.toPropertyArray(".id", new RegularExpression(prefix + index + "_state", false));
	}
	
	private Property[] make(int index) {
		return Property.toPropertyArray(".id", new RegularExpression(prefix + index + "_make", false));
	}
	
	private Property[] model(int index) {
		return Property.toPropertyArray(".id", new RegularExpression(prefix + index + "_model", false));
	}
	
	private Property[] color(int index) {
		return Property.toPropertyArray(".id", new RegularExpression(prefix + index + "_color", false));
	}
	
	private Property[] addVehicles() {
		return Property.toPropertyArray(".class", "Html.A", ".text","OK");
	}

	private Property[] panelBar() {
		return Property.toPropertyArray(".id", new RegularExpression("checkBoxPanelBar_Vehicle_\\d+", false));
	}
	
	private IHtmlObject[] getAddNewVehiclesPanelObject() {
		IHtmlObject objs[] = browser.getHtmlObject(panelBar());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Add New Vehicles panel bar object.");
		return objs;
	}
	
	public void selectNumberOfVehicles(int num) {
		browser.selectDropdownList(numberOfVehicles(), String.valueOf(num), true, getAddNewVehiclesPanelObject()[1]);
	}
	
	public void setPlate(String plate, int index) {
		browser.setTextField(plate(index), plate);
	}
	
	public void selectState(String state, int index) {
		browser.selectDropdownList(state(index), state);
	}
	
	public void selectMake(String make, int index) {
		browser.selectDropdownList(make(index), make);
	}
	
	public void setModel(String model, int index) {
		browser.setTextField(model(index), model);
	}
	
	public void selectColor(String color, int index) {
		browser.selectDropdownList(color(index), color);
	}
	
	public void clickAddVehicles() {
		browser.clickGuiObject(addVehicles(), true, 0, getWidget()[0]);
	}
	
	public void setVehiclesInfo(List<VehicleInfo> vehicles) {
		int size = vehicles.size();
		this.selectNumberOfVehicles(size);
		ajax.waitLoading();
		this.waitLoading();
		for(int i = 0; i < size; i ++) {
			this.setVehicleInfo(vehicles.get(i), i + 3);
		}
	}
	
	public void setVehicleInfo(VehicleInfo vehicle) {
		int index = 1;
		this.selectNumberOfVehicles(index);
		ajax.waitLoading();
		this.waitLoading();
//		this.setVehicleInfo(vehicle, index);
		this.setVehicleInfo(vehicle, index + 2);//Quentin[20140619] ui changes
	}
	
	private void setVehicleInfo(VehicleInfo vehicle, int index) {
		if(!StringUtil.isEmpty(vehicle.getPlateNum())) {
			this.setPlate(vehicle.getPlateNum(), index);
		}
		if(!StringUtil.isEmpty(vehicle.getState())) {
			this.selectState(vehicle.getState(), index);
		}
		if(!StringUtil.isEmpty(vehicle.getMake())) {
			this.selectMake(vehicle.getMake(), index);
		}
		if(!StringUtil.isEmpty(vehicle.getModel())) {
			this.setModel(vehicle.getModel(), index);
		}
		if(!StringUtil.isEmpty(vehicle.getColor())) {
			this.selectColor(vehicle.getColor(), index);
		}
	}
	
	public void addVehicles(List<VehicleInfo> vehicles){
		setVehiclesInfo(vehicles);
		clickAddVehicles();
		ajax.waitLoading();
	}
}
