package com.activenetwork.qa.awo.testcases.abstractcases.web.hf;

import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Mar 1, 2013
 */
public abstract class HFSKWebTestCase extends HFWebTestCase {
	
	public HFSKWebTestCase() {
		super();
		url =  TestProperty.getProperty(env + ".web.hfsk.url");
		cus.password = TestProperty.getProperty("web.login.pw");
		schema = DataBaseFunctions.getSchemaName("SK", env);
		loginLM.contract = "SK Contract";
	} 
	
	
}
