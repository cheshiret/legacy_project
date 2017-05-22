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
 * @Date  Jan 7, 2014
 */
public enum SearchFacilityAttr implements DataAttribute{
	facilityId, facilityName, city, county, status;
	
	public Class<?> getType(){
		return String.class;
	}

	public String getStrValue(Data<SearchFacilityAttr> src){
		String value = src.stringValue(valueOf(SearchFacilityAttr.class, name()));
		return value;
	}
	
	public void setValue(Data<SearchFacilityAttr> src, Object obj){
		src.put(valueOf(SearchFacilityAttr.class, name()), obj);
	}	

	public static Data<SearchFacilityAttr> init(){
		Data<SearchFacilityAttr> src =  new Data<SearchFacilityAttr>();
		return src;
	}
}
