package com.activenetwork.qa.awo.datacollection.datadefinition.web.maintenanceapps;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;

/**
 * @Description: marketing spot attributes
 * 
 * @author Lesley Wang
 * @Date  Nov 28, 2013
 */
public enum MarketingSpotAttr implements DataAttribute {
	title, description, spotLoc, spotContent, priority, isActive, sites, pages, parks, states;
	
	@Override
	public Class<?> getType() {
		return String.class;
	}
	
	public String getStrValue(Data<MarketingSpotAttr> src){
		String value = src.stringValue(valueOf(MarketingSpotAttr.class, name()));
		return value;
	}
	
	public void setValue(Data<MarketingSpotAttr> src, Object obj){
		src.put(valueOf(MarketingSpotAttr.class, name()), obj);
	}	

	public static Data<MarketingSpotAttr> init(){
		Data<MarketingSpotAttr> src =  new Data<MarketingSpotAttr>();
		return src;
	}
}
