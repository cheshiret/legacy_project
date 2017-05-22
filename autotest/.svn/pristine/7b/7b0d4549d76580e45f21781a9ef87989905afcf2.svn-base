package com.activenetwork.qa.awo.datacollection.datadefinition.web.marina;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Nov 15, 2013
 */
public enum BoatAttr implements DataAttribute{
	existedBoatName, boatName, registrationNum, boatType, length_ft, width_ft, depth_ft, boatCategory, capacity, color,
	horsePower, year, hullIden, model, manufacture, motorManufacture, construction, trailerType, trailerLicense;
	
	public Class<?> getType(){
		return String.class;
	}

	public String getStrValue(Data<BoatAttr> src){
		String value = src.stringValue(valueOf(BoatAttr.class, name()));
		return value;
	}
	
	public void setValue(Data<BoatAttr> src, Object obj){
		src.put(valueOf(BoatAttr.class, name()), obj);
	}	

	public static Data<BoatAttr> initializeEmptyBoat(){
		Data<BoatAttr> src =  new Data<BoatAttr>();
		BoatAttr.boatName.setValue(src, StringUtil.EMPTY);	
		BoatAttr.registrationNum.setValue(src, StringUtil.EMPTY);
		BoatAttr.boatType.setValue(src, "--Please Select--");
		BoatAttr.length_ft.setValue(src, StringUtil.EMPTY); 
		BoatAttr.width_ft.setValue(src, StringUtil.EMPTY);
		BoatAttr.depth_ft.setValue(src, StringUtil.EMPTY);		
		BoatAttr.boatCategory.setValue(src, "--Please Select--");
		BoatAttr.capacity.setValue(src, StringUtil.EMPTY);
		BoatAttr.color.setValue(src, StringUtil.EMPTY);		
		BoatAttr.horsePower.setValue(src, StringUtil.EMPTY);
		BoatAttr.year.setValue(src, StringUtil.EMPTY);
		BoatAttr.hullIden.setValue(src, StringUtil.EMPTY);
		BoatAttr.model.setValue(src, StringUtil.EMPTY);
		BoatAttr.manufacture.setValue(src, StringUtil.EMPTY);
		BoatAttr.motorManufacture.setValue(src, StringUtil.EMPTY);
		BoatAttr.construction.setValue(src, StringUtil.EMPTY);
		BoatAttr.trailerType.setValue(src, StringUtil.EMPTY);
		BoatAttr.trailerLicense.setValue(src, StringUtil.EMPTY);
		return src;		
	}
	
	public static Data<BoatAttr> initializeBoatInfo(){
		Data<BoatAttr> src =  new Data<BoatAttr>();
		BoatAttr.boatName.setValue(src, "Automation_BoatName");	
		BoatAttr.registrationNum.setValue(src, "12345");
		BoatAttr.boatType.setValue(src, "Motor Boat");
		BoatAttr.length_ft.setValue(src, "50.0"); 
		BoatAttr.width_ft.setValue(src, "10.0");
		BoatAttr.depth_ft.setValue(src, "20.0");		
		BoatAttr.boatCategory.setValue(src, "Individual");
		BoatAttr.capacity.setValue(src, "150");
		BoatAttr.color.setValue(src, "Blue");		
		BoatAttr.horsePower.setValue(src, "Yes");
		BoatAttr.year.setValue(src, String.valueOf(DateFunctions.getCurrentYear()));
		BoatAttr.hullIden.setValue(src, "12345");
		BoatAttr.model.setValue(src, "Automation_Model");
		BoatAttr.manufacture.setValue(src, "Automation_Manufacture");
		BoatAttr.motorManufacture.setValue(src, "Automation_MotorM");
		BoatAttr.construction.setValue(src, "Automation_Construction");
		BoatAttr.trailerType.setValue(src, "Automation_TrailerT");
		BoatAttr.trailerLicense.setValue(src, "Automation_TrailerL");
		return src;		
	}
}
