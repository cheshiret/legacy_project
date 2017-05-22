package com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;
/**
 * This data collection is for the search of equipment 
 * @author Phoebe
 */
public enum EquipmentSearchInvAttr implements DataAttribute {
	facility, equipmentType, equipmentName, equipmentCode, equipmentInfo, startDate, startTime, startAMPM, endDate, endTime, endAMPM, quantity, isOverrideEquipmentHours(Boolean.class),
	isShowAvailableOnly(Boolean.class);
	private Class<?> type;
	private EquipmentSearchInvAttr() {
		type=String.class;
	};
	
	private EquipmentSearchInvAttr(Class<?> type){
		this.type=type;
	};
	
	public Class<?> getType() {
		return type;
	}

	public String getStrValue(Data<EquipmentSearchInvAttr> src){
		String value = src.stringValue(valueOf(EquipmentSearchInvAttr.class, name()));
		return value;
	}

	public void setValue(Data<EquipmentSearchInvAttr> src, Object obj){
		src.put(valueOf(EquipmentSearchInvAttr.class, name()), obj);
	}	

	public static Data<EquipmentSearchInvAttr> init(){
		Data<EquipmentSearchInvAttr> src =  new Data<EquipmentSearchInvAttr>();
		return src;
	}
}
