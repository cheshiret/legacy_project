/**
 * 
 */
package com.activenetwork.qa.awo.testcases.abstractcases.web;

import com.activenetwork.qa.awo.keywords.web.MarketingSpotManagerApplication;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Dec 16, 2012
 */
public abstract class MarketingSpotManagerTestCase extends WebTestCase {

	protected MarketingSpotManagerApplication spotMgr;

	protected MarketingSpotManagerTestCase() {
		url =  TestProperty.getProperty(env + ".spotmgr.url");
		spotMgr = MarketingSpotManagerApplication.getInstance();
	}

}
