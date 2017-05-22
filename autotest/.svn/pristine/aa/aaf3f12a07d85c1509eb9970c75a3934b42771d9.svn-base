package com.activenetwork.qa.awo.testcases.abstractcases;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public abstract class FinanceManagerTestCase extends OrmsTestCase {
	/**
	 * Script Name   : <b>FinanceManagerTestCase</b>
	 * Generated     : <b>Sep 7, 2009 10:45:51 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/09/07
	 * @author QA
	 */
	protected FinanceManager fnm;
	protected ReservationInfo res;
	
	public FinanceManagerTestCase() {
		super();
		fnm = FinanceManager.getInstance();
		res = new ReservationInfo();
		//initialize login finance manager parameters
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
		//initialize customer info
		res.lastName = TestProperty.getProperty("app.cust.lname");
		res.firstName = TestProperty.getProperty("app.cust.fname");
		res.email=fnm.getNextEmail();
	}
	
}
