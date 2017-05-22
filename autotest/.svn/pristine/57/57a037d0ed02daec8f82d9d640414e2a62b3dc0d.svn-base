package com.activenetwork.qa.awo.datacollection.datadefinition.orms.license;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;
/**
 * @Description: Privilege Purchase Authorization
 * 
 * @author Jane
 * @Date  May 7, 2014
 */
public enum PrivilegePurchaseAuthorization implements DataAttribute {

	ID, 
	Status,
	AuthorizationType, 
	AuthedPrivilege, 
	AuthedPrivLicenseYear,
	HunterHostNum,
	HunterHostRelationship,
	AuthedReason;
	
	private Class<?> type;
	private PrivilegePurchaseAuthorization() {
		type=String.class;
	};
	
	private PrivilegePurchaseAuthorization(Class<?> type){
		this.type=type;
	};
	
	public Class<?> getType(){
		return type;
	}

	public String getStrValue(Data<PrivilegePurchaseAuthorization> src){
		String value = src.stringValue(valueOf(PrivilegePurchaseAuthorization.class, name()));
		return value;
	}

	public Object getValue(Data<PrivilegePurchaseAuthorization> src){
		Object value = src.get(valueOf(PrivilegePurchaseAuthorization.class, name()));
		return value;
	}
	
	public void setValue(Data<PrivilegePurchaseAuthorization> src, Object obj){
		src.put(valueOf(PrivilegePurchaseAuthorization.class, name()), obj);
	}	

	public static Data<PrivilegePurchaseAuthorization> init(){
		Data<PrivilegePurchaseAuthorization> src =  new Data<PrivilegePurchaseAuthorization>();
		return src;
	}
	
}
