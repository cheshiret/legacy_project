package com.activenetwork.qa.awo.datacollection.datadefinition.orms.license;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;

/**
 * @Description: Allocation Type data collection
 * 
 * @author Lesley Wang
 * @Date  Mar 25, 2014
 */
public enum AllocationType implements DataAttribute {
	ID, 
	Status,
	Code,
	AllocationType, 
	Species;
	
	private Class<?> type;
	private AllocationType(){
		type=String.class;
	}
	private AllocationType(Class<?> type) {
		this.type=type;
	}
	
	@Override
	public Class<?> getType() {
		return type;
	}

	public String getStrValue(Data<AllocationType> src){
		String value = src.stringValue(valueOf(AllocationType.class, name()));
		return value;
	}

	public void setValue(Data<AllocationType> src, Object obj){
		src.put(valueOf(AllocationType.class, name()), obj);
	}	

	public static Data<AllocationType> init(){
		Data<AllocationType> src =  new Data<AllocationType>();
		return src;
	}
}
