package com.activenetwork.qa.awo.datacollection.datadefinition;

import com.activenetwork.qa.testapi.datacollection.DataAttribute;

public enum CustomerAttr implements DataAttribute {
	l_name,f_name,m_name,dob,email,address,city,state,country,zip,exists(Boolean.class);
	
	private Class<?> type;
	private CustomerAttr() {
		type=String.class;
	};
	
	private CustomerAttr(Class<?> type){
		this.type=type;
	};
	
	public Class<?> getType() {
		return type;
	}
}
