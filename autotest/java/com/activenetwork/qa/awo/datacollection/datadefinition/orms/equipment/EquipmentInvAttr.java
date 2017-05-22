package com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;
/**
 * This is the data information for equipment inventory
 * @author Phoebe
 */
public enum EquipmentInvAttr implements DataAttribute {
	equipmentType, equipment, startDate, startTime, startAMPM, endDate, endTime, endAMPM, totalQuantity, isActive(Boolean.class);
	private Class<?> type;
	private EquipmentInvAttr() {
		type=String.class;
	};
	
	private EquipmentInvAttr(Class<?> type){
		this.type=type;
	};
	
	public Class<?> getType() {
		return type;
	}

	public String getStrValue(Data<EquipmentInvAttr> src){
		String value = src.stringValue(valueOf(EquipmentInvAttr.class, name()));
		return value;
	}

	public void setValue(Data<EquipmentInvAttr> src, Object obj){
		src.put(valueOf(EquipmentInvAttr.class, name()), obj);
	}	

	public static Data<EquipmentInvAttr> init(){
		Data<EquipmentInvAttr> src =  new Data<EquipmentInvAttr>();
		return src;
	}
}
