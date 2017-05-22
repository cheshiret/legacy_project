package com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment;

import java.util.List;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;

public enum EquipmentHourAttr implements DataAttribute {
	id, 
	startDate, 
	endDate, 
	description, 
	status, 
	sunAvailableHours(List.class),
	monAvailableHours(List.class), 
	tueAvailableHours(List.class),
	wedAvailableHours(List.class),
	thuAvailableHours(List.class),
	friAvailableHours(List.class),
	satAvailableHours(List.class);
	
	private Class<?> type;
	private EquipmentHourAttr() {
		type=String.class;
	};
	
	private EquipmentHourAttr(Class<?> type){
		this.type=type;
	};
	
	public Class<?> getType(){
		return type;
	}

	public String getStrValue(Data<EquipmentHourAttr> src){
		String value = src.stringValue(valueOf(EquipmentHourAttr.class, name()));
		return value;
	}

	public void setValue(Data<EquipmentHourAttr> src, Object obj){
		src.put(valueOf(EquipmentHourAttr.class, name()), obj);
	}	

	public static Data<EquipmentHourAttr> init(){
		Data<EquipmentHourAttr> src =  new Data<EquipmentHourAttr>();
		return src;
	}
}