/**
 * 
 */
package com.activenetwork.qa.awo.datacollection.datadefinition.orms.license;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;

/**
 * @author ssong
 * @Date  Mar 25, 2014
 */
public enum LandownerCountyQuantityAttr implements DataAttribute{
	lANDOWNER_PRIVILEGE_TYPE,lICENSE_YEAR,COPY_FROM_YEAR(Boolean.class),FROM_YEAR,ENTER_QUANTITY(Boolean.class),QUANTITY;
	
	private Class<?> type;
	private LandownerCountyQuantityAttr(){
		type=String.class;
	}
	private LandownerCountyQuantityAttr(Class<?> type) {
		this.type=type;
	}
	
	@Override
	public Class<?> getType() {
		return type;
	}	

	public static Data<LandownerCountyQuantityAttr> init(){
		Data<LandownerCountyQuantityAttr> src =  new Data<LandownerCountyQuantityAttr>();
		return src;
	}
}
