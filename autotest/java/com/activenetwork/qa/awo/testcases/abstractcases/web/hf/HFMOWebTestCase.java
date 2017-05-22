package com.activenetwork.qa.awo.testcases.abstractcases.web.hf;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Swang
 * @Date  Jun 04, 2013
 */
public abstract class HFMOWebTestCase extends HFWebTestCase {
	
	public HFMOWebTestCase() {
		super();
		url =  TestProperty.getProperty(env + ".web.hfmo.url");
		schema = DataBaseFunctions.getSchemaName("MO", env);
		conmmonSchema = DataBaseFunctions.getSchemaName("COMMON", env);
		loginLM.contract = "MO Contract";
		pay = new Payment("master");
	} 
}
