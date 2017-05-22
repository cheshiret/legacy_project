package com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;

public enum EquipmentAvailableHourAttr implements DataAttribute {
	startTime,
	startTimeAMPM,
	endTime,
	endTimeAMPM;
	
	private Class<?> type;
	private EquipmentAvailableHourAttr() {
		type=String.class;
	};
	
	private EquipmentAvailableHourAttr(Class<?> type){
		this.type=type;
	};
	
	public Class<?> getType(){
		return type;
	}

	public String getStrValue(Data<EquipmentAvailableHourAttr> src){
		String value = src.stringValue(valueOf(EquipmentAvailableHourAttr.class, name()));
		return value;
	}

	public void setValue(Data<EquipmentAvailableHourAttr> src, Object obj){
		src.put(valueOf(EquipmentAvailableHourAttr.class, name()), obj);
	}	

	public static Data<EquipmentAvailableHourAttr> init(){
		Data<EquipmentAvailableHourAttr> src =  new Data<EquipmentAvailableHourAttr>();
		return src;
	}
}