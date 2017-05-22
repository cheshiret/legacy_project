package com.activenetwork.qa.awo.datacollection.datadefinition.orms.license;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;

/**
 * @Description: Hunt Assignment Data collection
 * 
 * @author Lesley Wang
 * @Date  Jan 16, 2014
 */
public enum HuntAssignmentAttr implements DataAttribute {
	AssignID, Status, HuntCode, HuntDes, HuntSpecies, HuntSpeciesSubType, HuntWeapon, 
	HuntLoc, HuntDatePeriod, CreationDateTime, CreationUser, ModifiedDateTime, ModifiedUser;

	public Class<?> getType(){
		return String.class;
	}

	public String getStrValue(Data<HuntAssignmentAttr> src){
		String value = src.stringValue(valueOf(HuntAssignmentAttr.class, name()));
		return value;
	}

	public void setValue(Data<HuntAssignmentAttr> src, Object obj){
		src.put(valueOf(HuntAssignmentAttr.class, name()), obj);
	}	

	public static Data<HuntAssignmentAttr> init(){
		Data<HuntAssignmentAttr> src =  new Data<HuntAssignmentAttr>();
		return src;
	}
}
