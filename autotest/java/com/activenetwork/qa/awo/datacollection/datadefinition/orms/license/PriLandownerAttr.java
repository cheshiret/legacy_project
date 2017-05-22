package com.activenetwork.qa.awo.datacollection.datadefinition.orms.license;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;

public enum PriLandownerAttr implements DataAttribute{
	privilegeCode, minAcresToQuantity, maxCountyDeclaration,
	isAsBonusOnly, bonusPriName;
	
	public Class<?> getType(){
		return String.class;
	}

	public String getStrValue(Data<PriLandownerAttr> src){
		String value = src.stringValue(valueOf(PriLandownerAttr.class, name()));
		return value;
	}
	
	public void setValue(Data<PriLandownerAttr> src, Object obj){
		src.put(valueOf(PriLandownerAttr.class, name()), obj);
	}	

	public static Data<PriLandownerAttr> init(){
		Data<PriLandownerAttr> src =  new Data<PriLandownerAttr>();
		return src;
	}
}