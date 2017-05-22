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
 * @Date  Apr 21, 2014
 */
public enum ORVPermitPOSAttr implements DataAttribute{
	typeOfPermit, startDate, deliveryMethod, permitOffice,
	driverLicenseNum, driverLicenseCountry, driverLicenseState,
	vehiclePlateNum, vehiclePlateCountry, vehiclePlateState,
	registeredOwnerName, vehicleYear, vehicleMake, vehicleModel, vehicleColor;
	
	public Class<?> getType(){
		return String.class;
	}

	public String getStrValue(Data<ORVPermitPOSAttr> src){
		String value = src.stringValue(valueOf(ORVPermitPOSAttr.class, name()));
		return value;
	}
	
	public void setValue(Data<ORVPermitPOSAttr> src, Object obj){
		src.put(valueOf(ORVPermitPOSAttr.class, name()), obj);
	}	
}

