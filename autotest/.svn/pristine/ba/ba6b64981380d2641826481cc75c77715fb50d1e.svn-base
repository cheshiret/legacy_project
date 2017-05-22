package com.activenetwork.qa.awo.testcases.abstractcases;

import java.util.Properties;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.pages.AwoAjax;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.page.Ajax;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public abstract class OrmsTestCase extends AWOTestCase {
	/**
	 * Script Name   : <b>OrmsTestCase</b>
	 * Generated     : <b>Jan 16, 2009 4:00:53 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/01/16
	 * @author QA
	 */
	protected LoginInfo login;

	protected Payment pay;

	protected Customer cust;
	
	protected Properties translation;
	
	protected Ajax ajax=AwoAjax.getInstance();

	protected OrmsTestCase() {
		super();
		login = new LoginInfo();
		cust = new Customer();
		pay = new Payment("Visa");
		product = "orms";
		login.url = AwoUtil.getOrmsURL(env);
	}
	
}
