/**
 * 
 */
package com.activenetwork.qa.awo.testcases.abstractcases.web;

import com.activenetwork.qa.awo.keywords.web.MaintenanceApplication;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Jan 6, 2013
 */
public abstract class AdminDoTestCase extends WebTestCase {

	protected MaintenanceApplication ma;

	protected AdminDoTestCase() {
		url =  TestProperty.getProperty(env + ".web.ra.url") + "admin.do";
		ma = MaintenanceApplication.getInstance();
	}

}
