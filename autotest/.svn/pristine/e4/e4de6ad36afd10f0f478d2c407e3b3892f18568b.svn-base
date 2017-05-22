package com.activenetwork.qa.awo.datacollection.datadefinition.orms.license;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;

/**
 * @Description: Allocation Type License Year data collection
 * 
 * @author Lesley Wang
 * @Date  Jan 29, 2014
 */
public enum AllocationTypeLicenseYear implements DataAttribute {
	ID, 
	AllocationType,
	AllocationCode,
	LicenseYear, 
	TotalQuota, 
	TagQty,
	location;
	
	private Class<?> type;
	private AllocationTypeLicenseYear(){
		type=String.class;
	}
	private AllocationTypeLicenseYear(Class<?> type) {
		this.type=type;
	}
	
	@Override
	public Class<?> getType() {
		return type;
	}

	public String getStrValue(Data<AllocationTypeLicenseYear> src){
		String value = src.stringValue(valueOf(AllocationTypeLicenseYear.class, name()));
		return value;
	}

	public void setValue(Data<AllocationTypeLicenseYear> src, Object obj){
		src.put(valueOf(AllocationTypeLicenseYear.class, name()), obj);
	}	

	public static Data<AllocationTypeLicenseYear> init(){
		Data<AllocationTypeLicenseYear> src =  new Data<AllocationTypeLicenseYear>();
		return src;
	}
}
