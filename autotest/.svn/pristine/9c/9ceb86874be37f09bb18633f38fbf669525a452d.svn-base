package com.activenetwork.qa.awo.datacollection.datadefinition.orms.license;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;

/**
 * @Description: Outfitter Allocation data collection
 * 
 * @author Lesley Wang
 * @Date  Mar 25, 2014
 */
public enum OutfitterAllocation implements DataAttribute {
	ID, 
	Outfitter,
	AllocationType,
	AllocationCode,
	LicenseYear,
	Quantity, 
	Location,
	Outfitters(String[].class), 
	Quantities(String[].class);
	
	private Class<?> type;
	private OutfitterAllocation(){
		type=String.class;
	}
	private OutfitterAllocation(Class<?> type) {
		this.type=type;
	}
	
	@Override
	public Class<?> getType() {
		return type;
	}

	public String getStrValue(Data<OutfitterAllocation> src){
		String value = src.stringValue(valueOf(OutfitterAllocation.class, name()));
		return value;
	}

	public void setValue(Data<OutfitterAllocation> src, Object obj){
		src.put(valueOf(OutfitterAllocation.class, name()), obj);
	}	

	public static Data<OutfitterAllocation> init(){
		Data<OutfitterAllocation> src =  new Data<OutfitterAllocation>();
		return src;
	}
}
