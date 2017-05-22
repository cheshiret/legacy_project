package com.activenetwork.qa.awo.datacollection.datadefinition.web.pos;

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
 * @Date  Apr 23, 2014
 */
public enum InteragencyPassAttr implements DataAttribute{
	quantity, isExpeditedDeliveryMethod(Boolean.class), interagencyPassName;
	
	private Class<?> type;
	private InteragencyPassAttr(){
		type=String.class;
	}
	private InteragencyPassAttr(Class<?> type) {
		this.type=type;
	}
	
	public Class<?> getType() {
		return type;
	}
	public Object getValue(Data<InteragencyPassAttr> src)
	{
		Object value = src.get(valueOf(InteragencyPassAttr.class, name()));
		return value;
	}
	public String getStrValue(Data<InteragencyPassAttr> src)
	{
		String value = src.stringValue(valueOf(InteragencyPassAttr.class, name()));
		return value;
	}
	
	public void setValue(Data<InteragencyPassAttr> src, Object obj)
	{
		src.put(valueOf(InteragencyPassAttr.class, name()), obj);
	}	
}