package com.activenetwork.qa.awo.datacollection.datadefinition.orms.license;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jan 9, 2014
 */
public enum FacilityPrdAttr implements DataAttribute{
	prdCode, prdName, prdStatus, prdDesc, prdType, capacity, handicappedAccessible;

	public Class<?> getType(){
		return String.class;
	}

	public String getStrValue(Data<FacilityPrdAttr> src){
		String value = src.stringValue(valueOf(FacilityPrdAttr.class, name()));
		return value;
	}

	public void setValue(Data<FacilityPrdAttr> src, Object obj){
		src.put(valueOf(FacilityPrdAttr.class, name()), obj);
	}	

	public static Data<FacilityPrdAttr> init(){
		Data<FacilityPrdAttr> src =  new Data<FacilityPrdAttr>();
		return src;
	}
}
