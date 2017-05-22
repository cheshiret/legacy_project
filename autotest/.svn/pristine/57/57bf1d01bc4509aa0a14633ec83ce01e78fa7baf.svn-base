package com.activenetwork.qa.awo.testcases.abstractcases.web.hf;

import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jun 4, 2014
 */
public abstract class HFABWebTestCase extends HFWebTestCase {
	
	public HFABWebTestCase() {
		super();
		url =  TestProperty.getProperty(env + ".web.hfab.url");
		cus.password = TestProperty.getProperty("web.login.pw");
		schema = DataBaseFunctions.getSchemaName("AB", env);
		loginLM.contract = "AB Contract";
	} 
}
