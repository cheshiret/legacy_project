/**
 * SupportCenterTestCase.java
 * Jul 26, 2011
 * QA
 */
package com.activenetwork.qa.awo.testcases.abstractcases;

import com.activenetwork.qa.awo.datacollection.legacy.RequestInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.SupportCenter;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Functional Test Script
 * @author eliang
 * @Date Jul 26, 2011
 */
public abstract class SupportCenterTestCase extends AWOTestCase {

	protected SupportCenter sc;
	protected LoginInfo login;
	protected RequestInfo request;

	public SupportCenterTestCase() {
		super();
		sc = SupportCenter.getInstance();
		login = new LoginInfo();
		request = new RequestInfo();

		//disable loadbalance as support center flex cases only work on the default web server
		TestProperty.putProperty(env+".orms.loadbalance","false");
		login.url = AwoUtil.getOrmsURL(env, "launchpad");
		login.userName = TestProperty.getProperty("orms.sc.user");
		login.password = TestProperty.getProperty("orms.sc.pw");
	}
}
