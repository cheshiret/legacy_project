package com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;

public enum EquipmentRental implements DataAttribute {

	facility, 
	equipType,
	equipCode,
	equipName,
	startDate,
	startTime,
	startTimeAMPM,
	endDate,
	endTime,
	endTimeAMPM,
	quantity,
	isOverrideEquipmentHours(boolean.class),
	isShowAvailableOnly(boolean.class),
	voidReason,
	voidNotes;
	
	private Class<?> type;
	private EquipmentRental(){
		type=String.class;
	}
	private EquipmentRental(Class<?> type) {
		this.type=type;
	}
	
	@Override
	public Class<?> getType() {
		return type;
	}

	public String getStrValue(Data<EquipmentRental> src){
		String value = src.stringValue(valueOf(EquipmentRental.class, name()));
		return value;
	}

	public void setValue(Data<EquipmentRental> src, Object obj){
		src.put(valueOf(EquipmentRental.class, name()), obj);
	}	

	public static Data<EquipmentRental> init(){
		Data<EquipmentRental> src =  new Data<EquipmentRental>();
		return src;
	}

}
