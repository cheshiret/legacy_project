package com.activenetwork.qa.testapi.verification;

import com.activenetwork.qa.testapi.datacollection.Data;

public interface Checkable {
	public void check(Data<?>... data);
	public String getName();

}
