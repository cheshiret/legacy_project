package com.activenetwork.qa.testapi.verification;

import com.activenetwork.qa.testapi.datacollection.Data;

public class CheckPoint{
	private Data<?>[] data;
	private Checkable target;

	public CheckPoint(Checkable target, Data<?>... data) {
		this.data=data;
		this.target=target;
	}
		
	public void check() {
			target.check(data);
	}
	
	@Override
	public String toString() {
		String dataNames="";
		if(data!=null) {
			for(Data<?> d:data) {
				if(d!=null)
					dataNames+="("+d.getDataName()+")";
			}
		}
		
		return "[target="+target.getName()+", data="+dataNames+"]";
	}
}
