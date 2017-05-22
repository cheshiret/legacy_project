package com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.lottery;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Feb 13, 2014
 */
public enum PointTypesAttr implements DataAttribute{
	id, code, displayCode, description, maxAllowed, lyLimit, transitionSettings;

	public Class<?> getType(){
		return String.class;
	}

	public String getStrValue(Data<PointTypesAttr> src){
		String value = src.stringValue(valueOf(PointTypesAttr.class, name()));
		return value;
	}

	public void setValue(Data<PointTypesAttr> src, Object obj){
		src.put(valueOf(PointTypesAttr.class, name()), obj);
	}	

	public static Data<PointTypesAttr> init(){
		Data<PointTypesAttr> src =  new Data<PointTypesAttr>();
		return src;
	}
}
