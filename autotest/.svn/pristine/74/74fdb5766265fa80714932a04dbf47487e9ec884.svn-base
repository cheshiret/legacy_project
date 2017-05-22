package com.activenetwork.qa.awo.datacollection.datadefinition.orms.license;

import java.util.List;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;
import com.activenetwork.qa.testapi.datacollection.StringData;
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
public enum ActivityAttr implements DataAttribute{
	activityID, activityCode, activityName, activityStatus, capacity, prdGroup, activityType, custClasses(String[].class),
	displayCategory, displaySubCategory, reportCategory, pricingNote, activeLongDesc, facility, facilityPrd, appliesToAllSessions(Boolean.class),
	primaryInstructor, secondaryInstructor,
	activitySessions(List.class),
	activitySessions_Date, activitySessionsTime, activitySessions_StartTime, activitySessions_startTimeAPM, activitySessions_EndTime,activitySessions_EndTimeAPM;

	private Class<?> type;
	private ActivityAttr() {
		type=String.class;
	};
	
	private ActivityAttr(Class<?> type){
		this.type=type;
	};
	
	public Class<?> getType(){
		return type;
	}

	public String getStrValue(Data<ActivityAttr> src){
		String value = src.stringValue(valueOf(ActivityAttr.class, name()));
		return value;
	}

	public Object getValue(Data<ActivityAttr> src){
		Object value = src.get(valueOf(ActivityAttr.class, name()));
		return value;
	}
	
	public void setValue(Data<ActivityAttr> src, Object obj){
		src.put(valueOf(ActivityAttr.class, name()), obj);
	}	

	public static Data<ActivityAttr> init(){
		Data<ActivityAttr> src =  new Data<ActivityAttr>();
		return src;
	}
}
