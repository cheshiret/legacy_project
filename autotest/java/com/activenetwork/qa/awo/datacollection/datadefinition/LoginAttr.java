package com.activenetwork.qa.awo.datacollection.datadefinition;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;

public enum LoginAttr implements DataAttribute {
	url,
	userName,
	password,
	envType,
	startCall,
	contract,
	contractAbbrev, 
	location,
	station,
	park,
	role,
	email,
	;
	
	private Class<?> type=String.class;

	@Override
	public Class<?> getType() {
		return type;
	}
	
	public String getStrValue(Data<LoginAttr> src)
	{
		String value = src.stringValue(valueOf(LoginAttr.class, name()));
		return value;
	}
	
	public void setValue(Data<LoginAttr> src, Object obj)
	{
		src.put(valueOf(LoginAttr.class, name()), obj);
	}	
}
