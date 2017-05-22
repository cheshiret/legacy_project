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
 * @Date  Apr 26, 2014
 */
public enum SerializePassAttr implements DataAttribute{
	isAnnualDayUsePass(Boolean.class), isAnnualCampingPass(Boolean.class), isInState(Boolean.class), isOutOfState(Boolean.class);

	private Class<?> type;
	private SerializePassAttr(){
		type=String.class;
	}
	private SerializePassAttr(Class<?> type) {
		this.type=type;
	}

	public Class<?> getType() {
		return type;
	}
	public Object getValue(Data<SerializePassAttr> src)
	{
		Object value = src.get(valueOf(SerializePassAttr.class, name()));
		return value;
	}
	public String getStrValue(Data<SerializePassAttr> src)
	{
		String value = src.stringValue(valueOf(SerializePassAttr.class, name()));
		return value;
	}

	public void setValue(Data<SerializePassAttr> src, Object obj)
	{
		src.put(valueOf(SerializePassAttr.class, name()), obj);
	}	
}
