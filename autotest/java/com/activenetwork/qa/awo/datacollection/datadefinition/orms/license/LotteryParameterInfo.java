package com.activenetwork.qa.awo.datacollection.datadefinition.orms.license;

import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.datacollection.DataAttribute;

/**
 * @Description: Lottery parameter info
 * @Preconditions:
 * @SPEC: 
 * @LinkSetUp:
 * @Task#: 
 * 
 * @author Lesley Wang
 * @Date  Feb 13, 2014
 */
public enum LotteryParameterInfo implements DataAttribute {
	ID, Status, Description, Value, IsPrintParam(Boolean.class);
	
	private Class<?> type;
	private LotteryParameterInfo() {
		type=String.class;
	};
	
	private LotteryParameterInfo(Class<?> type){
		this.type=type;
	};
	
	public Class<?> getType() {
		return type;
	}

	public String getStrValue(Data<LotteryParameterInfo> src){
		String value = src.stringValue(valueOf(LotteryParameterInfo.class, name()));
		return value;
	}

	public void setValue(Data<LotteryParameterInfo> src, Object obj){
		src.put(valueOf(LotteryParameterInfo.class, name()), obj);
	}	

	public static Data<LotteryParameterInfo> init(){
		Data<LotteryParameterInfo> src =  new Data<LotteryParameterInfo>();
		return src;
	}
}
