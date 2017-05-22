package com.activenetwork.qa.awo.datacollection.datadefinition.orms.license;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;

public enum ActivitySession implements DataAttribute {
	Date, StartTime, StartTimeAPM, EndTime, EndTimeAPM, Facility, FacilityAddr, Product;

	private Class<?> type;
	private ActivitySession(){
		type=String.class;
	}
	
	private ActivitySession(Class<?> type) {
		this.type=type;
	}
	
	public Class<?> getType() {
		return type;
	}
	
	public Object getValue(Data<ActivityAttr> src) {
		Object value = src.get(valueOf(ActivityAttr.class, name()));
		return value;
	}
	
	public String getStrValue(Data<ActivityAttr> src) {
		String value = src.stringValue(valueOf(ActivityAttr.class, name()));
		return value;
	}
	
	public void setValue(Data<ActivityAttr> src, Object obj) {
		src.put(valueOf(ActivityAttr.class, name()), obj);
	}	
	
	
	public static Data<ActivityAttr> init()	{
		Data<ActivityAttr> src =  new Data<ActivityAttr>();
		return src;
	}

}