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
 * @Date  Jan 16, 2014
 */
public enum OwnerAttr implements DataAttribute{
	conservationNum, lastName, firstName, businessName, dateOfBirth,
	ownerID, ownershipStatus, 
	propertyID, typeOfOwnership, yearOwned, corporation, ownerComments,
	cust, custID,
	applicationName, creationDate, creationUser;

	public Class<?> getType(){
		return String.class;
	}

	public String getStrValue(Data<OwnerAttr> src){
		String value = src.stringValue(valueOf(OwnerAttr.class, name()));
		return value;
	}

	public void setValue(Data<OwnerAttr> src, Object obj){
		src.put(valueOf(OwnerAttr.class, name()), obj);
	}	

	public static Data<OwnerAttr> init(){
		Data<OwnerAttr> src =  new Data<OwnerAttr>();
		return src;
	}
}
