package com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.cust;

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
 * @Date  Jan 13, 2014
 */
public enum PropertyAttr implements DataAttribute{
	propertyID, propertyCounty, propertyAcres, propertyComments,
	creationApplication, creationData, creationUser,
	section, location, survey, parcel, range, directions,
	address, addressZip, addressCountry, addressInvalidateStatus, supplementalAddress, addressCity, addressState, addressCounty,
	typeOfOwnership, yearOwned, corporation, ownershipComments, ownershipStatus;
	
	public Class<?> getType(){
		return String.class;
	}

	public String getStrValue(Data<PropertyAttr> src){
		String value = src.stringValue(valueOf(PropertyAttr.class, name()));
		return value;
	}
	
	public void setValue(Data<PropertyAttr> src, Object obj){
		src.put(valueOf(PropertyAttr.class, name()), obj);
	}	

	public static Data<PropertyAttr> init(){
		Data<PropertyAttr> src =  new Data<PropertyAttr>();
		return src;
	}
}