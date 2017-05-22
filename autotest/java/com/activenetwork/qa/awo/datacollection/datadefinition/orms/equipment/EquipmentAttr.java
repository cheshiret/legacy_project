package com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment;

import java.util.List;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;
/**
 * This is the data info for equipment
 * @author pchen
 *
 */
public enum EquipmentAttr  implements DataAttribute{
	code, 
	name, 
	descrition, 
	equipmentType, 
	usage, 
	status, 
	webVisible, 
	allGeneralPublicSales, 
	checkOutLagTime, 
	checkInLagTime,
	equipmentHours(List.class);
		
	private Class<?> type;
	private EquipmentAttr() {
		type=String.class;
	};
	
	private EquipmentAttr(Class<?> type){
		this.type=type;
	};
	
	public Class<?> getType(){
		return type;
	}

	public String getStrValue(Data<EquipmentAttr> src){
		String value = src.stringValue(valueOf(EquipmentAttr.class, name()));
		return value;
	}

	public void setValue(Data<EquipmentAttr> src, Object obj){
		src.put(valueOf(EquipmentAttr.class, name()), obj);
	}	

	public static Data<EquipmentAttr> init(){
		Data<EquipmentAttr> src =  new Data<EquipmentAttr>();
		return src;
	}
}
