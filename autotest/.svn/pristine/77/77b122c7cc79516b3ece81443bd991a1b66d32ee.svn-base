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
 * @Date  Jan 21, 2014
 */
public enum AuditAttr implements DataAttribute{
	auditID, auditStatus, propertyID, cust, lName, fName, custID,
	documentType, documentDate, contactDate, auditYear, auditComment,
	creationApplication, creationDate, creationUser;

	public Class<?> getType(){
		return String.class;
	}

	public String getStrValue(Data<AuditAttr> src){
		String value = src.stringValue(valueOf(AuditAttr.class, name()));
		return value;
	}

	public void setValue(Data<AuditAttr> src, Object obj){
		src.put(valueOf(AuditAttr.class, name()), obj);
	}	

	public static Data<AuditAttr> init(){
		Data<AuditAttr> src =  new Data<AuditAttr>();
		return src;
	}
}

